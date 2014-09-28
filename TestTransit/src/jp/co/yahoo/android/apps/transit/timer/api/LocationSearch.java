// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.timer.api;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import java.util.Timer;
import java.util.TimerTask;
import jp.co.yahoo.android.apps.transit.timer.util.CountdownUtil;
import jp.co.yahoo.android.apps.transit.viewparts.CustomDialogTitle;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

// Referenced classes of package jp.co.yahoo.android.apps.transit.timer.api:
//            SearchStationThread, RevGeocoder

public class LocationSearch
    implements LocationListener
{
    public class GetAddressFromLocation extends Thread
    {

        private Location location;
        final LocationSearch this$0;

        private String getAddressFromLocation(Location location1)
        {
            JSONArray jsonarray;
            RevGeocoder revgeocoder = new RevGeocoder(context);
            revgeocoder.setLat(Double.toString(location1.getLatitude()));
            revgeocoder.setLon(Double.toString(location1.getLongitude()));
            revgeocoder.setDatum("wgs");
            revgeocoder.execute();
            jsonarray = revgeocoder.getFeature();
            if (jsonarray == null)
            {
                break MISSING_BLOCK_LABEL_79;
            }
            if (jsonarray.length() > 0)
            {
                return jsonarray.getJSONObject(0).getJSONObject("Property").getString("Address");
            }
            returnError();
            return "";
            JSONException jsonexception;
            jsonexception;
            returnError();
            return "";
        }

        private void returnError()
        {
            Bundle bundle = new Bundle();
            bundle.putInt(getString(0x7f0d01ce), 0x7f0c0077);
            bundle.putString(getString(0x7f0d01cf), getString(0x7f0d01d0));
            bundle.putString(getString(0x7f0d01b9), getString(0x7f0d00b2));
            bundle.putString(getString(0x7f0d01ba), getString(0x7f0d015e));
            Message message = mainHandler.obtainMessage();
            message.obj = bundle;
            mainHandler.sendMessage(message);
        }

        public void run()
        {
            String s = getAddressFromLocation(location);
            Bundle bundle = new Bundle();
            bundle.putInt(getString(0x7f0d01ce), 0x7f0c0078);
            bundle.putString(getString(0x7f0d01cf), getString(0x7f0d01d0));
            bundle.putString(getString(0x7f0d01a2), s);
            bundle.putString(getString(0x7f0d01a4), Double.toString(location.getLongitude()));
            bundle.putString(getString(0x7f0d01a3), Double.toString(location.getLatitude()));
            Message message = mainHandler.obtainMessage();
            message.obj = bundle;
            mainHandler.sendMessage(message);
        }

        public GetAddressFromLocation(Location location1)
        {
            this$0 = LocationSearch.this;
            super();
            location = location1;
        }
    }

    public static interface LocationSearchListener
    {

        public abstract void onError(String s, String s1);

        public abstract void onSuccess(String s, Bundle bundle);

        public abstract void onTimeout(String s, String s1);
    }


    public static final long LOCATION_LISTEN_TIMEOUT = 10000L;
    private Boolean bAddress;
    protected boolean bCanceled;
    private boolean bDispDialog;
    private boolean bLoaded;
    private Boolean bStation;
    private Bundle condition;
    private Context context;
    private boolean isFinishActivity;
    private LocationSearchListener listener;
    private Timer locationGivupTimer;
    private LocationManager locman;
    private Handler mainHandler;
    private ProgressDialog progressDialog;
    private boolean retry;
    private String sDialogMessage;
    protected Thread thread;

    public LocationSearch(Context context1, LocationSearchListener locationsearchlistener)
    {
        locationGivupTimer = new Timer();
        bDispDialog = true;
        bLoaded = false;
        sDialogMessage = null;
        condition = null;
        bStation = Boolean.valueOf(false);
        bAddress = Boolean.valueOf(false);
        bCanceled = false;
        retry = false;
        isFinishActivity = false;
        context = context1;
        listener = locationsearchlistener;
        mainHandler = new Handler() {

            final LocationSearch this$0;

            public void handleMessage(Message message)
            {
                if (bCanceled)
                {
                    if (progressDialog != null && progressDialog.isShowing())
                    {
                        progressDialog.dismiss();
                    }
                } else
                {
                    bCanceled = true;
                    Bundle bundle = (Bundle)message.obj;
                    int i = bundle.getInt(getString(0x7f0d01ce));
                    String s = bundle.getString(getString(0x7f0d01cf));
                    String s1 = bundle.getString(getString(0x7f0d01b9));
                    String s2 = bundle.getString(getString(0x7f0d01ba));
                    if (i > 0)
                    {
                        if (i == 0x7f0c0078)
                        {
                            removeUpdateFromLocationManager();
                            Bundle bundle1 = bundle.getBundle(getString(0x7f0d0240));
                            if ((bundle1 == null || bundle1.size() < 1) && retry)
                            {
                                bCanceled = false;
                                condition.remove(getString(0x7f0d01a3));
                                condition.remove(getString(0x7f0d01a4));
                                SearchStationThread searchstationthread = new SearchStationThread(context, mainHandler);
                                searchstationthread.setCondtion(condition);
                                searchstationthread.start();
                                return;
                            }
                            if (progressDialog != null && progressDialog.isShowing())
                            {
                                progressDialog.dismiss();
                            }
                            listener.onSuccess(s, bundle);
                            return;
                        }
                        if (i == 0x7f0c0077)
                        {
                            removeUpdateFromLocationManager();
                            if (progressDialog != null && progressDialog.isShowing())
                            {
                                progressDialog.dismiss();
                            }
                            onError(s, s1);
                            return;
                        }
                        if (i == 0x7f0c0079)
                        {
                            removeUpdateFromLocationManager();
                            if (progressDialog != null && progressDialog.isShowing())
                            {
                                progressDialog.dismiss();
                            }
                            onTimeout(s, s1, s2);
                            return;
                        }
                    }
                }
            }

            
            {
                this$0 = LocationSearch.this;
                super();
            }
        };
    }

    private String getString(int i)
    {
        return context.getString(i);
    }

    private void startLocationChangeListening()
    {
        removeUpdateFromLocationManager();
        locman = (LocationManager)context.getSystemService("location");
        boolean flag = locman.isProviderEnabled("network");
        boolean flag1 = false;
        if (flag)
        {
            locman.requestLocationUpdates("network", 0L, 0.0F, this);
            flag1 = true;
        }
        if (locman.isProviderEnabled("gps"))
        {
            locman.requestLocationUpdates("gps", 0L, 0.0F, this);
            flag1 = true;
        }
        if (!flag1)
        {
            if (progressDialog != null && progressDialog.isShowing())
            {
                progressDialog.dismiss();
            }
            removeUpdateFromLocationManager();
            onError(getString(0x7f0d01d0), getString(0x7f0d054a));
        }
    }

    public void getCurrentAddress()
    {
        bAddress = Boolean.valueOf(true);
        getCurrentPlace();
    }

    public void getCurrentPlace()
    {
        bLoaded = false;
        bCanceled = false;
        if (bDispDialog)
        {
            if (CountdownUtil.isEmpty(sDialogMessage))
            {
                sDialogMessage = getString(0x7f0d04a1);
            }
            progressDialog = new ProgressDialog(context);
            progressDialog.setCustomTitle((new CustomDialogTitle(context, context.getString(0x7f0d04aa), 0)).setIconInfo());
            progressDialog.setMessage(sDialogMessage);
            progressDialog.setIndeterminate(true);
            progressDialog.setCancelable(true);
            progressDialog.show();
            progressDialog.setOnCancelListener(new android.content.DialogInterface.OnCancelListener() {

                final LocationSearch this$0;

                public void onCancel(DialogInterface dialoginterface)
                {
                    if (locationGivupTimer != null)
                    {
                        locationGivupTimer.cancel();
                    }
                    thread = null;
                    locman.removeUpdates(LocationSearch.this);
                    bCanceled = true;
                    if (isFinishActivity)
                    {
                        ((Activity)context).finish();
                    }
                }

            
            {
                this$0 = LocationSearch.this;
                super();
            }
            });
        }
        locationGivupTimer = new Timer();
        locationGivupTimer.schedule(new TimerTask() {

            final LocationSearch this$0;

            public void run()
            {
                Bundle bundle = new Bundle();
                bundle.putInt(getString(0x7f0d01ce), 0x7f0c0079);
                bundle.putString(getString(0x7f0d01cf), getString(0x7f0d01d0));
                bundle.putString(getString(0x7f0d01b9), getString(0x7f0d010c));
                bundle.putString(getString(0x7f0d01ba), getString(0x7f0d01ba));
                Message message = mainHandler.obtainMessage();
                message.obj = bundle;
                mainHandler.sendMessage(message);
            }

            
            {
                this$0 = LocationSearch.this;
                super();
            }
        }, 10000L);
        startLocationChangeListening();
    }

    public void getCurrentStation(Bundle bundle)
    {
        condition = bundle;
        bStation = Boolean.valueOf(true);
        getCurrentPlace();
    }

    public void getCurrentStation(Bundle bundle, boolean flag)
    {
        retry = flag;
        getCurrentStation(bundle);
    }

    protected void onError(String s, String s1)
    {
        listener.onError(s, s1);
    }

    public void onLocationChanged(Location location)
    {
        if (bLoaded)
        {
            return;
        }
        bLoaded = true;
        if (locationGivupTimer != null)
        {
            locationGivupTimer.cancel();
        }
        if (bAddress.booleanValue())
        {
            GetAddressFromLocation getaddressfromlocation = new GetAddressFromLocation(location);
            thread = getaddressfromlocation;
            getaddressfromlocation.start();
            return;
        }
        if (bStation.booleanValue())
        {
            condition.putString(getString(0x7f0d01a3), String.valueOf(location.getLatitude()));
            condition.putString(getString(0x7f0d01a4), String.valueOf(location.getLongitude()));
            SearchStationThread searchstationthread = new SearchStationThread(context, mainHandler);
            thread = searchstationthread;
            searchstationthread.setCondtion(condition);
            searchstationthread.start();
            return;
        } else
        {
            Bundle bundle = new Bundle();
            bundle.putInt(getString(0x7f0d01ce), 0x7f0c0078);
            bundle.putString(getString(0x7f0d01cf), getString(0x7f0d01d0));
            bundle.putString(getString(0x7f0d01a4), Double.toString(location.getLongitude()));
            bundle.putString(getString(0x7f0d01a3), Double.toString(location.getLatitude()));
            Message message = mainHandler.obtainMessage();
            message.obj = bundle;
            mainHandler.sendMessage(message);
            return;
        }
    }

    public void onProviderDisabled(String s)
    {
    }

    public void onProviderEnabled(String s)
    {
    }

    public void onStatusChanged(String s, int i, Bundle bundle)
    {
    }

    protected void onTimeout(String s, String s1, String s2)
    {
        listener.onTimeout(s, s1);
    }

    public void removeUpdateFromLocationManager()
    {
        if (locman != null)
        {
            locman.removeUpdates(this);
        }
    }

    public void setDialogMessage(boolean flag, String s)
    {
        bDispDialog = flag;
        sDialogMessage = s;
    }

    public void setIsFinishActivity(boolean flag)
    {
        isFinishActivity = flag;
    }










}

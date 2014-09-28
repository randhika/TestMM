// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.api;

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
import jp.co.yahoo.android.apps.transit.api.data.APIError;
import jp.co.yahoo.android.apps.transit.common.util.TransitUtil;
import jp.co.yahoo.android.apps.transit.viewparts.CustomDialogTitle;

// Referenced classes of package jp.co.yahoo.android.apps.transit.api:
//            RevGeocoder, StationSearch, ApiBase

public class LocationSearch
    implements LocationListener
{
    public static interface LocationSearchlListener
    {

        public abstract void onError(String s, String s1);

        public abstract void onSuccess(String s, Bundle bundle);

        public abstract void onTimeout(String s, String s1);
    }


    public static final long LOCATION_LISTEN_TIMEOUT = 10000L;
    private Boolean bAddress;
    protected boolean bCanceled;
    private boolean bDispDialog;
    private boolean bErroMsg;
    private boolean bLoaded;
    private Boolean bStation;
    protected boolean bTimeout;
    private Bundle condition;
    private Context context;
    private boolean isFinishActivity;
    private LocationSearchlListener listener;
    private Location location;
    private Timer locationGivupTimer;
    private LocationManager locman;
    private Handler mainHandler;
    private RevGeocoder objAdrSearch;
    private StationSearch objStSearch;
    private ProgressDialog progressDialog;
    private String sDialogMessage;
    protected Thread thread;

    public LocationSearch(Context context1, LocationSearchlListener locationsearchllistener)
    {
        locationGivupTimer = new Timer();
        bDispDialog = true;
        bErroMsg = true;
        bLoaded = false;
        sDialogMessage = null;
        condition = null;
        bStation = Boolean.valueOf(false);
        bAddress = Boolean.valueOf(false);
        objStSearch = null;
        objAdrSearch = null;
        bCanceled = false;
        bTimeout = false;
        isFinishActivity = false;
        context = context1;
        listener = locationsearchllistener;
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
            if (locationGivupTimer != null)
            {
                locationGivupTimer.cancel();
            }
            onError(getString(0x7f0d01d0), getString(0x7f0d054a));
        }
    }

    protected void getAddress(Location location1)
    {
        objAdrSearch = new RevGeocoder(context, new ApiBase.ApiListener() {

            final LocationSearch this$0;

            public boolean onCanceled()
            {
                return false;
            }

            public boolean onError(APIError apierror)
            {
                if (progressDialog != null && progressDialog.isShowing())
                {
                    progressDialog.dismiss();
                }
                if (bCanceled || bTimeout)
                {
                    return false;
                } else
                {
                    LocationSearch.this.onError(getString(0x7f0d01d0), getString(0x7f0d00b2));
                    return false;
                }
            }

            public boolean onSuccess(ApiBase apibase, Object obj)
            {
                if (progressDialog != null && progressDialog.isShowing())
                {
                    progressDialog.dismiss();
                }
                if (!bCanceled && !bTimeout)
                {
                    Bundle bundle = objAdrSearch.getResults();
                    if (bundle == null || !bundle.containsKey(getString(0x7f0d01a2)))
                    {
                        LocationSearch.this.onError(getString(0x7f0d01d0), getString(0x7f0d00b2));
                    }
                    String as[] = bundle.getStringArray(getString(0x7f0d01a2));
                    if (as == null || as.length < 1)
                    {
                        LocationSearch.this.onError(getString(0x7f0d01d0), getString(0x7f0d00b2));
                    }
                    if (listener != null)
                    {
                        Bundle bundle1 = new Bundle();
                        bundle1.putInt(getString(0x7f0d01ce), 0x7f0c0078);
                        bundle1.putString(getString(0x7f0d01cf), getString(0x7f0d01d0));
                        bundle1.putString(getString(0x7f0d01a2), as[0]);
                        bundle1.putString(getString(0x7f0d01a4), objAdrSearch.getParameter("lon"));
                        bundle1.putString(getString(0x7f0d01a3), objAdrSearch.getParameter("lat"));
                        listener.onSuccess(getString(0x7f0d01d0), bundle1);
                        return false;
                    }
                }
                return false;
            }

            
            {
                this$0 = LocationSearch.this;
                super();
            }
        });
        objAdrSearch.setDialogDisplay(false);
        objAdrSearch.setLat(Double.toString(location1.getLatitude()));
        objAdrSearch.setLon(Double.toString(location1.getLongitude()));
        objAdrSearch.setDatum("wgs");
        objAdrSearch.request();
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
            if (TransitUtil.isEmpty(sDialogMessage))
            {
                sDialogMessage = getString(0x7f0d04a4);
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
        mainHandler = new Handler() {

            final LocationSearch this$0;

            public void handleMessage(Message message)
            {
                if (progressDialog != null && progressDialog.isShowing())
                {
                    progressDialog.dismiss();
                }
                if (objStSearch != null)
                {
                    objStSearch.stopRequest();
                }
                if (objAdrSearch != null)
                {
                    objAdrSearch.stopRequest();
                }
                onTimeout(getString(0x7f0d01d0), getString(0x7f0d010c), getString(0x7f0d01ba));
            }

            
            {
                this$0 = LocationSearch.this;
                super();
            }
        };
        locationGivupTimer = new Timer();
        locationGivupTimer.schedule(new TimerTask() {

            final LocationSearch this$0;

            public void run()
            {
                bTimeout = true;
                Bundle bundle = new Bundle();
                bundle.putInt(getString(0x7f0d01ce), 0x7f0c0079);
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

    public Location getLocation()
    {
        return location;
    }

    protected void getStation(Location location1)
    {
        objStSearch = new StationSearch(context, new ApiBase.ApiListener() {

            final LocationSearch this$0;

            public boolean onCanceled()
            {
                return false;
            }

            public boolean onError(APIError apierror)
            {
                if (progressDialog != null && progressDialog.isShowing())
                {
                    progressDialog.dismiss();
                }
                if (bCanceled || bTimeout)
                {
                    return false;
                } else
                {
                    LocationSearch.this.onError(getString(0x7f0d01d1), getString(0x7f0d010a));
                    return false;
                }
            }

            public boolean onSuccess(ApiBase apibase, Object obj)
            {
                if (progressDialog != null && progressDialog.isShowing())
                {
                    progressDialog.dismiss();
                }
                if (!bCanceled && !bTimeout)
                {
                    Bundle bundle = objStSearch.getResults();
                    if (listener != null)
                    {
                        Bundle bundle1 = new Bundle();
                        bundle1.putBundle(getString(0x7f0d0240), bundle);
                        listener.onSuccess(getString(0x7f0d01d1), bundle1);
                        return false;
                    }
                }
                return false;
            }

            
            {
                this$0 = LocationSearch.this;
                super();
            }
        });
        objStSearch.setDialogDisplay(false);
        condition.putString(getString(0x7f0d01a3), String.valueOf(location1.getLatitude()));
        condition.putString(getString(0x7f0d01a4), String.valueOf(location1.getLongitude()));
        objStSearch.setParamCond(condition);
        objStSearch.request();
    }

    public boolean isErroMsg()
    {
        return bErroMsg;
    }

    public boolean isLocationEnabled()
    {
        if (locman == null)
        {
            locman = (LocationManager)context.getSystemService("location");
        }
        return locman.isProviderEnabled("network") || locman.isProviderEnabled("gps");
    }

    protected void onError(String s, String s1)
    {
        if (!bErroMsg)
        {
            s1 = null;
        }
        if (listener != null)
        {
            listener.onError(s, s1);
        }
    }

    public void onLocationChanged(Location location1)
    {
        removeUpdateFromLocationManager();
        if (!bLoaded)
        {
            location = location1;
            bLoaded = true;
            if (locationGivupTimer != null)
            {
                locationGivupTimer.cancel();
            }
            if (bAddress.booleanValue())
            {
                getAddress(location1);
                return;
            }
            if (bStation.booleanValue())
            {
                getStation(location1);
                return;
            }
            Bundle bundle = new Bundle();
            bundle.putInt(getString(0x7f0d01ce), 0x7f0c0078);
            bundle.putString(getString(0x7f0d01cf), getString(0x7f0d01d0));
            bundle.putString(getString(0x7f0d01a4), Double.toString(location1.getLongitude()));
            bundle.putString(getString(0x7f0d01a3), Double.toString(location1.getLatitude()));
            if (progressDialog != null && progressDialog.isShowing())
            {
                progressDialog.dismiss();
            }
            if (listener != null)
            {
                listener.onSuccess(getString(0x7f0d01d0), bundle);
                return;
            }
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
        if (!bErroMsg)
        {
            s1 = null;
        }
        if (listener != null)
        {
            listener.onTimeout(s, s1);
        }
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

    public void setErroMsg(boolean flag)
    {
        bErroMsg = flag;
    }

    public void setIsFinishActivity(boolean flag)
    {
        isFinishActivity = flag;
    }

    public void setLocationSearchlListener(LocationSearchlListener locationsearchllistener)
    {
        listener = locationsearchllistener;
    }

    public void stopRequest()
    {
        if (objStSearch != null)
        {
            objStSearch.stopRequest();
        }
        if (objAdrSearch != null)
        {
            objAdrSearch.stopRequest();
        }
        if (progressDialog != null)
        {
            progressDialog.cancel();
        }
        removeUpdateFromLocationManager();
    }










}

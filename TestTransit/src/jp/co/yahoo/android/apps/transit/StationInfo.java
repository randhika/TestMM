// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import java.net.URLEncoder;
import java.util.ArrayList;
import jp.co.yahoo.android.apps.transit.api.ApiBase;
import jp.co.yahoo.android.apps.transit.api.RegistSearchSSO;
import jp.co.yahoo.android.apps.transit.api.StationSearch;
import jp.co.yahoo.android.apps.transit.api.data.APIError;
import jp.co.yahoo.android.apps.transit.api.data.DiainfoData;
import jp.co.yahoo.android.apps.transit.api.data.RailDirectionData;
import jp.co.yahoo.android.apps.transit.api.data.StationData;
import jp.co.yahoo.android.apps.transit.common.StringUtil;
import jp.co.yahoo.android.apps.transit.common.util.TransitUtil;
import jp.co.yahoo.android.apps.transit.viewparts.TransitDialogBuilder;
import jp.co.yahoo.yconnect.core.oauth2.BearerToken;
import jp.co.yahoo.yconnect.sso.YTcookieChecker;

// Referenced classes of package jp.co.yahoo.android.apps.transit:
//            TransitBaseActivity, RailDirection, TimeTableActivity

public class StationInfo extends TransitBaseActivity
{

    private Button btnRegist;
    private AlertDialog diaMap;
    private LayoutInflater inflater;
    private StationSearch objSearch;
    private RegistSearchSSO regist;
    private Resources res;
    private StationData station;
    private BearerToken taken;

    public StationInfo()
    {
        station = null;
        objSearch = null;
    }

    private void getStation()
    {
        objSearch = new StationSearch(this, new jp.co.yahoo.android.apps.transit.api.ApiBase.ApiListener() {

            final StationInfo this$0;

            public boolean onCanceled()
            {
                showStationInfo();
                return false;
            }

            public boolean onError(APIError apierror)
            {
                showStationInfo();
                return false;
            }

            public boolean onSuccess(ApiBase apibase, Object obj)
            {
                Bundle bundle = objSearch.getResults();
                if (bundle != null && bundle.size() > 0)
                {
                    station = (StationData)bundle.getSerializable("0");
                }
                showStationInfo();
                return false;
            }

            
            {
                this$0 = StationInfo.this;
                super();
            }
        });
        objSearch.setQuery(station.getId());
        objSearch.request();
    }

    private void launchTimetable()
    {
        Intent intent = new Intent(this, jp/co/yahoo/android/apps/transit/RailDirection);
        intent.putExtra(getString(0x7f0d023e), station);
        intent.putExtra(getString(0x7f0d01df), getResources().getInteger(0x7f0c0062));
        startActivityForResult(intent, getResources().getInteger(0x7f0c0053));
    }

    private void postStation()
    {
        setRegist();
        if (taken == null)
        {
            TransitUtil.login(this);
            return;
        } else
        {
            regist.resetParam();
            regist.setStationName(station.getName());
            regist.setMethod("POST");
            regist.requestAsync(true);
            return;
        }
    }

    private void setRegist()
    {
        taken = TransitUtil.getAccessToken(this);
        if (taken != null)
        {
            regist = new RegistSearchSSO(this, taken, new jp.co.yahoo.android.apps.transit.api.AuthApiBase.AuthApiListener() {

                final StationInfo this$0;

                public boolean onCanceled()
                {
                    return false;
                }

                public boolean onError(APIError apierror)
                {
                    showErrorMessageDialog(apierror.getMessage(), getString(0x7f0d014f));
                    return false;
                }

                public boolean onSuccess(Bundle bundle)
                {
                    Toast.makeText(StationInfo.this, getString(0x7f0d00ab), 0).show();
                    return false;
                }

                public volatile boolean onSuccess(Object obj)
                {
                    return onSuccess((Bundle)obj);
                }

            
            {
                this$0 = StationInfo.this;
                super();
            }
            });
        }
    }

    private void settingAroundInfo(int i, final String displayTxt, final String urlTxt, String s)
    {
        res = getResources();
        LinearLayout linearlayout = (LinearLayout)findViewById(0x7f0a031d);
        LinearLayout linearlayout1 = (LinearLayout)inflater.inflate(0x7f030072, null);
        ImageView imageview = (ImageView)linearlayout1.findViewById(0x7f0a0221);
        TextView textview = (TextView)linearlayout1.findViewById(0x7f0a0222);
        imageview.setImageResource(i);
        textview.setText(displayTxt);
        textview.setGravity(16);
        linearlayout1.setTag(s);
        linearlayout1.setOnClickListener(new android.view.View.OnClickListener() {

            final StationInfo this$0;
            final String val$displayTxt;
            final String val$urlTxt;

            public void onClick(View view)
            {
                String s1 = (String)view.getTag();
                touchTapRD(s1);
                StationInfo stationinfo = StationInfo.this;
                String s2 = getString(0x7f0d0552);
                Object aobj[] = new Object[4];
                aobj[0] = URLEncoder.encode(StringUtil.trim(urlTxt));
                aobj[1] = displayTxt;
                aobj[2] = station.getLat().toString();
                aobj[3] = station.getLon().toString();
                stationinfo.startBrowser(String.format(s2, aobj));
            }

            
            {
                this$0 = StationInfo.this;
                urlTxt = s;
                displayTxt = s1;
                super();
            }
        });
        ImageView imageview1 = (ImageView)inflater.inflate(0x7f030059, null);
        linearlayout.addView(linearlayout1);
        linearlayout.addView(imageview1);
    }

    private void settingStationInfo(int i, final String mainText, String s, final String url)
    {
        LinearLayout linearlayout;
        res = getResources();
        linearlayout = (LinearLayout)findViewById(0x7f0a031c);
        if (!mainText.equals(getString(0x7f0d0505))) goto _L2; else goto _L1
_L1:
        diaMap = (new TransitDialogBuilder(this)).setNegativeButton(getString(0x7f0d033b), new android.content.DialogInterface.OnClickListener() {

            final StationInfo this$0;

            public void onClick(DialogInterface dialoginterface, int j)
            {
                dialoginterface.cancel();
            }

            
            {
                this$0 = StationInfo.this;
                super();
            }
        }).setView(((LayoutInflater)getSystemService("layout_inflater")).inflate(0x7f030086, null)).create();
        LinearLayout linearlayout3 = (LinearLayout)inflater.inflate(0x7f030073, null);
        ImageView imageview4 = (ImageView)linearlayout3.findViewById(0x7f0a0049);
        TextView textview3 = (TextView)linearlayout3.findViewById(0x7f0a0063);
        TextView textview4 = (TextView)linearlayout3.findViewById(0x7f0a0064);
        imageview4.setImageResource(i);
        textview3.setText(mainText);
        textview4.setText(s);
        linearlayout3.setOnClickListener(new android.view.View.OnClickListener() {

            final StationInfo this$0;
            final String val$url;

            public void onClick(View view)
            {
                touchTapRD(getString(0x7f0d03f5));
                String s1 = getString(0x7f0d0059);
                if (TransitUtil.isAppInstalled(StationInfo.this, s1))
                {
                    Intent intent = new Intent();
                    intent.setPackage(s1);
                    intent.setAction("android.intent.action.VIEW");
                    intent.setData(Uri.parse(url));
                    try
                    {
                        startActivity(intent);
                        return;
                    }
                    catch (Exception exception)
                    {
                        return;
                    }
                } else
                {
                    diaMap.show();
                    return;
                }
            }

            
            {
                this$0 = StationInfo.this;
                url = s;
                super();
            }
        });
        ImageView imageview5 = (ImageView)inflater.inflate(0x7f030059, null);
        linearlayout.addView(linearlayout3);
        linearlayout.addView(imageview5);
_L4:
        return;
_L2:
        LinearLayout linearlayout2;
        ArrayList arraylist;
        if (!mainText.equals(getString(0x7f0d0509)))
        {
            continue; /* Loop/switch isn't completed */
        }
        linearlayout2 = (LinearLayout)inflater.inflate(0x7f030072, null);
        arraylist = station.getRailDirection();
        if (arraylist == null || arraylist.size() <= 0) goto _L4; else goto _L3
_L3:
        ImageView imageview2 = (ImageView)linearlayout2.findViewById(0x7f0a0221);
        TextView textview2 = (TextView)linearlayout2.findViewById(0x7f0a0222);
        imageview2.setImageResource(i);
        textview2.setText(mainText);
        textview2.setGravity(16);
        linearlayout2.setOnClickListener(new android.view.View.OnClickListener() {

            final StationInfo this$0;

            public void onClick(View view)
            {
                touchTapRD(getString(0x7f0d0422));
                launchTimetable();
            }

            
            {
                this$0 = StationInfo.this;
                super();
            }
        });
        ImageView imageview3 = (ImageView)inflater.inflate(0x7f030059, null);
        linearlayout.addView(linearlayout2);
        linearlayout.addView(imageview3);
        return;
        if ((!mainText.equals(getString(0x7f0d0501)) || !station.isExit()) && (!mainText.equals(getString(0x7f0d0502)) || !station.isFacility())) goto _L4; else goto _L5
_L5:
        LinearLayout linearlayout1 = (LinearLayout)inflater.inflate(0x7f030073, null);
        ImageView imageview = (ImageView)linearlayout1.findViewById(0x7f0a0049);
        TextView textview = (TextView)linearlayout1.findViewById(0x7f0a0063);
        TextView textview1 = (TextView)linearlayout1.findViewById(0x7f0a0064);
        imageview.setImageResource(i);
        textview.setText(mainText);
        textview1.setText(s);
        linearlayout1.setOnClickListener(new android.view.View.OnClickListener() {

            final StationInfo this$0;
            final String val$mainText;
            final String val$url;

            public void onClick(View view)
            {
                StationInfo stationinfo;
                String s1;
                Object aobj[];
                if (mainText.equals(getString(0x7f0d0501)))
                {
                    touchTapRD(getString(0x7f0d03dc));
                } else
                {
                    touchTapRD(getString(0x7f0d03dd));
                }
                stationinfo = StationInfo.this;
                s1 = url;
                aobj = new Object[1];
                aobj[0] = station.getId();
                stationinfo.startBrowser(String.format(s1, aobj));
            }

            
            {
                this$0 = StationInfo.this;
                mainText = s;
                url = s1;
                super();
            }
        });
        ImageView imageview1 = (ImageView)inflater.inflate(0x7f030059, null);
        linearlayout.addView(linearlayout1);
        linearlayout.addView(imageview1);
        return;
    }

    private void showAroundInfo()
    {
        int ai[] = {
            0x7f020198, 0x7f0201a7, 0x7f0201d2, 0x7f0201cb, 0x7f0201bb, 0x7f02019a, 0x7f020194, 0x7f020195, 0x7f0201b8, 0x7f0201cd
        };
        String as[] = new String[10];
        as[0] = getString(0x7f0d04ff);
        as[1] = getString(0x7f0d0503);
        as[2] = getString(0x7f0d0508);
        as[3] = getString(0x7f0d0506);
        as[4] = getString(0x7f0d04fd);
        as[5] = getString(0x7f0d0500);
        as[6] = getString(0x7f0d04fc);
        as[7] = getString(0x7f0d04fe);
        as[8] = getString(0x7f0d0504);
        as[9] = getString(0x7f0d0507);
        String as1[] = new String[10];
        as1[0] = getString(0x7f0d04ff);
        as1[1] = getString(0x7f0d0503);
        as1[2] = getString(0x7f0d0508);
        as1[3] = getString(0x7f0d0506);
        as1[4] = getString(0x7f0d04fd).substring(0, 3);
        as1[5] = getString(0x7f0d0500);
        as1[6] = getString(0x7f0d04fc);
        as1[7] = getString(0x7f0d04fe);
        as1[8] = getString(0x7f0d0504);
        as1[9] = getString(0x7f0d0507);
        String as2[] = new String[10];
        as2[0] = getString(0x7f0d03cb);
        as2[1] = getString(0x7f0d03df);
        as2[2] = getString(0x7f0d03de);
        as2[3] = getString(0x7f0d040a);
        as2[4] = getString(0x7f0d03ee);
        as2[5] = getString(0x7f0d03d2);
        as2[6] = getString(0x7f0d03c9);
        as2[7] = getString(0x7f0d03ca);
        as2[8] = getString(0x7f0d03eb);
        as2[9] = getString(0x7f0d0410);
        for (int i = 0; i < ai.length; i++)
        {
            settingAroundInfo(ai[i], as[i], as1[i], as2[i]);
        }

    }

    private void showStationInfo()
    {
        ((TextView)findViewById(0x7f0a02a2)).setText(station.getName());
        int ai[] = {
            0x7f0201bd, 0x7f0201d8, 0x7f0201a4, 0x7f0201a5
        };
        String as[] = new String[4];
        as[0] = getString(0x7f0d0505);
        as[1] = getString(0x7f0d0509);
        as[2] = getString(0x7f0d0501);
        as[3] = getString(0x7f0d0502);
        String as1[] = new String[4];
        as1[0] = getString(0x7f0d0547);
        as1[1] = "";
        as1[2] = getString(0x7f0d0548);
        as1[3] = getString(0x7f0d0548);
        String as2[] = new String[4];
        as2[0] = "geo:" + station.getLat() + "," + station.getLon();
        as2[1] = "";
        String s = getString(0x7f0d056d);
        Object aobj[] = new Object[1];
        aobj[0] = station.getId();
        as2[2] = String.format(s, aobj);
        String s1 = getString(0x7f0d056f);
        Object aobj1[] = new Object[1];
        aobj1[0] = station.getId();
        as2[3] = String.format(s1, aobj1);
        for (int i = 0; i < ai.length; i++)
        {
            settingStationInfo(ai[i], as[i], as1[i], as2[i]);
        }

    }

    private void startBrowser(String s)
    {
        TransitUtil.openURL(this, s);
    }

    public void leadToMapAppOnMarket(View view)
    {
        leadToAppOnMarket(getString(0x7f0d0059));
        if (diaMap != null && diaMap.isShowing())
        {
            diaMap.dismiss();
        }
    }

    protected void onActivityResult(int i, int j, Intent intent)
    {
        super.onActivityResult(i, j, intent);
        if (-1 == j && getResources().getInteger(0x7f0c0053) == i)
        {
            StationData stationdata = (StationData)intent.getSerializableExtra(getString(0x7f0d023e));
            RailDirectionData raildirectiondata = (RailDirectionData)intent.getSerializableExtra(getString(0x7f0d051d));
            Intent intent1 = new Intent(this, jp/co/yahoo/android/apps/transit/TimeTableActivity);
            intent1.putExtra(getString(0x7f0d051d), raildirectiondata);
            intent1.putExtra(getString(0x7f0d023e), stationdata);
            startActivityForResult(intent1, getResources().getInteger(0x7f0c0065));
        }
    }

    public void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setContentView(0x7f0300a8);
        setTitle(getString(0x7f0d050d));
        inflater = (LayoutInflater)getSystemService("layout_inflater");
        Intent intent = getIntent();
        if ("android.intent.action.VIEW".equals(intent.getAction()))
        {
            Uri uri = getIntent().getData();
            if (uri == null)
            {
                showErrorMessageDialog(getString(0x7f0d0106), getString(0x7f0d014f));
                return;
            }
            String s = uri.getQueryParameter(getString(0x7f0d023f));
            station = new StationData();
            station.setId(s);
        } else
        {
            station = (StationData)intent.getSerializableExtra(getString(0x7f0d023e));
        }
        btnRegist = (Button)findViewById(0x7f0a031b);
        btnRegist.setOnClickListener(new android.view.View.OnClickListener() {

            final StationInfo this$0;

            public void onClick(View view)
            {
                touchTapRD(getString(0x7f0d040c));
                postStation();
            }

            
            {
                this$0 = StationInfo.this;
                super();
            }
        });
        if (station.getDiainfo() == null || TransitUtil.isEmpty(station.getDiainfo().getRailCode()))
        {
            getStation();
        } else
        {
            showStationInfo();
        }
        showAroundInfo();
        dispAd(this, "2080124757", "pv,bottom");
    }

    protected void onLoginResult()
    {
        super.onLoginResult();
        if (YTcookieChecker.chkYTcookie())
        {
            postStation();
        }
    }

    public void onStop()
    {
        super.onStop();
        if (objSearch != null)
        {
            objSearch.stopRequest();
        }
    }





/*
    static StationData access$202(StationInfo stationinfo, StationData stationdata)
    {
        stationinfo.station = stationdata;
        return stationdata;
    }

*/




}

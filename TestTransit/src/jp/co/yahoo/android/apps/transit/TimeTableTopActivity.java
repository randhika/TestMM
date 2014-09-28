// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import jp.co.yahoo.android.apps.transit.api.ApiBase;
import jp.co.yahoo.android.apps.transit.api.LocationSearch;
import jp.co.yahoo.android.apps.transit.api.RegistSearchSSO;
import jp.co.yahoo.android.apps.transit.api.StationSearch;
import jp.co.yahoo.android.apps.transit.api.data.APIError;
import jp.co.yahoo.android.apps.transit.api.data.RailDirectionData;
import jp.co.yahoo.android.apps.transit.api.data.StationData;
import jp.co.yahoo.android.apps.transit.common.util.TransitUtil;
import jp.co.yahoo.android.apps.transit.viewparts.CustomDialogTitle;
import jp.co.yahoo.android.apps.transit.viewparts.GrayTitleBar;
import jp.co.yahoo.yconnect.core.oauth2.BearerToken;
import jp.co.yahoo.yconnect.sso.YTcookieChecker;

// Referenced classes of package jp.co.yahoo.android.apps.transit:
//            TransitBaseActivity, RailDirection, StationInfoList, TimeTableActivity, 
//            InputSearch

public class TimeTableTopActivity extends TransitBaseActivity
    implements jp.co.yahoo.android.apps.transit.api.LocationSearch.LocationSearchlListener
{
    class StationClickListener
        implements android.view.View.OnClickListener
    {

        final TimeTableTopActivity this$0;
        int type;

        public void onClick(View view)
        {
            if (type == 0)
            {
                touchTapRD(getString(0x7f0d040d));
            } else
            if (type == 1)
            {
                touchTapRD(getString(0x7f0d03e8));
            }
            objStation = (StationData)view.getTag();
            if (TransitUtil.isEmpty(objStation.getId()))
            {
                getStationId(objStation.getName());
                return;
            } else
            {
                launchDirection(objStation);
                return;
            }
        }

        public StationClickListener(int i)
        {
            this$0 = TimeTableTopActivity.this;
            super();
            type = i;
        }
    }


    private final int NEAR_MAX_NUM = 20;
    private final int REGIST_MAX_NUM = 5;
    private boolean bError;
    private boolean bNearStationCompleted;
    private boolean bRegistCompleted;
    private boolean bRetry;
    private LinearLayout lsArround;
    private LinearLayout lsNoArround;
    private LinearLayout lsNologin;
    private LinearLayout lsRegist;
    private int nReqCode;
    private Bundle nearStations;
    private LocationSearch objLocationSearch;
    private RegistSearchSSO objRegistSearch;
    private StationData objStation;
    private StationSearch objStationSearch;
    private ProgressDialog progressDialog;
    private Bundle registinfo;
    private BearerToken taken;
    private TextView txtAroundNow;

    public TimeTableTopActivity()
    {
        objStation = null;
        nReqCode = 0;
        bRegistCompleted = false;
        bNearStationCompleted = false;
        bRetry = false;
        bError = false;
    }

    private void cancelAroundSearch()
    {
        if (!bNearStationCompleted)
        {
            bNearStationCompleted = true;
            lsNoArround.setVisibility(0);
            txtAroundNow.setVisibility(8);
            setCompleted();
        }
    }

    private void getStationId(String s)
    {
        objStationSearch = new StationSearch(this, new jp.co.yahoo.android.apps.transit.api.ApiBase.ApiListener() {

            final TimeTableTopActivity this$0;

            public boolean onCanceled()
            {
                launchDirection(objStation);
                return false;
            }

            public boolean onError(APIError apierror)
            {
                launchDirection(objStation);
                return false;
            }

            public boolean onSuccess(ApiBase apibase, Object obj)
            {
                Bundle bundle = objStationSearch.getResults();
                if (bundle != null && bundle.containsKey("0"))
                {
                    StationData stationdata = (StationData)bundle.getSerializable("0");
                    objStation.setId(stationdata.getId());
                }
                launchDirection(objStation);
                return false;
            }

            
            {
                this$0 = TimeTableTopActivity.this;
                super();
            }
        });
        objStationSearch.setLocoMode("false");
        if (s.equals("\u4F59\u90E8"))
        {
            objStationSearch.setSort("-int_custom02");
        } else
        {
            objStationSearch.setSort("hybrid+-int_custom02");
        }
        objStationSearch.setExactName(s);
        objStationSearch.setResultCount(1);
        objStationSearch.request();
    }

    private void launchDirection(StationData stationdata)
    {
        Intent intent = new Intent(this, jp/co/yahoo/android/apps/transit/RailDirection);
        intent.putExtra(getString(0x7f0d023e), stationdata);
        intent.putExtra(getString(0x7f0d01df), nReqCode);
        startActivityForResult(intent, getResources().getInteger(0x7f0c0053));
    }

    private void launchStationList(Bundle bundle, Bundle bundle1, String s)
    {
        Intent intent = new Intent(this, jp/co/yahoo/android/apps/transit/StationInfoList);
        if (bundle != null)
        {
            Bundle bundle2 = new Bundle();
            bundle2.putBundle(getString(0x7f0d0240), bundle);
            intent.putExtra(getString(0x7f0d022c), bundle2);
        } else
        {
            intent.putExtra(getString(0x7f0d022c), bundle1);
        }
        intent.putExtra(getString(0x7f0d01df), nReqCode);
        intent.putExtra(getString(0x7f0d01d3), s);
        startActivityForResult(intent, getResources().getInteger(0x7f0c0063));
    }

    private void launchTimetable(StationData stationdata, RailDirectionData raildirectiondata)
    {
        Intent intent = new Intent(this, jp/co/yahoo/android/apps/transit/TimeTableActivity);
        intent.putExtra(getString(0x7f0d051d), raildirectiondata);
        intent.putExtra(getString(0x7f0d023e), stationdata);
        startActivityForResult(intent, getResources().getInteger(0x7f0c0065));
    }

    private void setCompleted()
    {
        if (bRegistCompleted)
        {
            if (progressDialog != null && progressDialog.isShowing())
            {
                progressDialog.dismiss();
            }
            showRegistInfoList();
        }
        if (bNearStationCompleted)
        {
            showNearStationList();
        }
        if (bRegistCompleted && bNearStationCompleted && bError)
        {
            showErrorMessageDialog(getString(0x7f0d00b2), getString(0x7f0d015e));
            bError = false;
        }
    }

    private void setRegist()
    {
        taken = TransitUtil.getAccessToken(this);
        if (taken == null)
        {
            bRegistCompleted = true;
            if (lsRegist != null)
            {
                lsRegist.setVisibility(8);
            }
            lsNologin.setVisibility(0);
            return;
        }
        if (!isFinishing())
        {
            progressDialog.show();
        }
        objRegistSearch = new RegistSearchSSO(this, taken, new jp.co.yahoo.android.apps.transit.api.AuthApiBase.AuthApiListener() {

            final TimeTableTopActivity this$0;

            public boolean onCanceled()
            {
                bRegistCompleted = true;
                if (lsRegist != null)
                {
                    lsRegist.setVisibility(8);
                }
                lsNologin.setVisibility(0);
                bError = true;
                setCompleted();
                return false;
            }

            public boolean onError(APIError apierror)
            {
                bRegistCompleted = true;
                if (lsRegist != null)
                {
                    lsRegist.setVisibility(8);
                }
                lsNologin.setVisibility(0);
                bError = true;
                setCompleted();
                return false;
            }

            public boolean onSuccess(Bundle bundle)
            {
                registinfo = bundle;
                bRegistCompleted = true;
                LinearLayout linearlayout = (LinearLayout)findViewById(0x7f0a0185);
                if (registinfo == null)
                {
                    linearlayout.setVisibility(0);
                } else
                {
                    linearlayout.setVisibility(8);
                }
                setCompleted();
                return false;
            }

            public volatile boolean onSuccess(Object obj)
            {
                return onSuccess((Bundle)obj);
            }

            
            {
                this$0 = TimeTableTopActivity.this;
                super();
            }
        });
        objRegistSearch.setSearchType(getString(0x7f0d023e));
        objRegistSearch.setWaitMsg(false);
        objRegistSearch.requestAsync(false);
    }

    private void showAllRegistStation()
    {
        if (registinfo == null)
        {
            return;
        } else
        {
            launchStationList(registinfo, null, getString(0x7f0d0546));
            return;
        }
    }

    private void showNearStationList()
    {
        if (nearStations == null)
        {
            return;
        }
        if (lsArround != null)
        {
            break;
        }
        lsArround = (LinearLayout)findViewById(0x7f0a0187);
        do
        {
            lsNoArround.setVisibility(8);
            txtAroundNow.setVisibility(8);
            LayoutInflater layoutinflater = (LayoutInflater)getSystemService("layout_inflater");
            StationClickListener stationclicklistener = new StationClickListener(1);
            int i = 0;
            while (i < nearStations.size() && i < 20) 
            {
                StationData stationdata = (StationData)nearStations.getSerializable(String.valueOf(i));
                TextView textview = (TextView)layoutinflater.inflate(0x7f030077, null);
                textview.setText(stationdata.getName());
                textview.setClickable(true);
                textview.setTag(stationdata);
                textview.setOnClickListener(stationclicklistener);
                ImageView imageview = (ImageView)layoutinflater.inflate(0x7f030059, null);
                lsArround.addView(textview);
                lsArround.addView(imageview);
                lsArround.setVisibility(0);
                i++;
            }
        } while (false && bRetry);
    }

    private void showRegistInfoList()
    {
        if (registinfo != null && lsRegist == null)
        {
            lsRegist = (LinearLayout)findViewById(0x7f0a0184);
            if (lsNologin != null && lsNologin.getVisibility() == 0)
            {
                lsNologin.setVisibility(8);
            }
            if (registinfo.size() > 5)
            {
                TextView textview1 = (TextView)((GrayTitleBar)findViewById(0x7f0a0061)).findViewById(0x7f0a01a2);
                textview1.setText(0x7f0d0538);
                textview1.setVisibility(0);
                textview1.setOnClickListener(new android.view.View.OnClickListener() {

                    final TimeTableTopActivity this$0;

                    public void onClick(View view)
                    {
                        touchTapRD(getString(0x7f0d040e));
                        showAllRegistStation();
                    }

            
            {
                this$0 = TimeTableTopActivity.this;
                super();
            }
                });
            }
            LayoutInflater layoutinflater = (LayoutInflater)getSystemService("layout_inflater");
            StationClickListener stationclicklistener = new StationClickListener(0);
            int i = 0;
            while (i < registinfo.size() && i < 5) 
            {
                StationData stationdata = (StationData)registinfo.getSerializable(String.valueOf(i));
                TextView textview = (TextView)layoutinflater.inflate(0x7f030077, null);
                textview.setText(stationdata.getName());
                textview.setClickable(true);
                textview.setTag(stationdata);
                textview.setOnClickListener(stationclicklistener);
                ImageView imageview = (ImageView)layoutinflater.inflate(0x7f030059, null);
                lsRegist.addView(textview);
                lsRegist.addView(imageview);
                lsRegist.setVisibility(0);
                i++;
            }
        }
    }

    public void aroundSearch()
    {
        Bundle bundle = new Bundle();
        bundle.putString(getString(0x7f0d01b6), "10");
        bundle.putString(getString(0x7f0d018e), "dist");
        objLocationSearch = new LocationSearch(this, this);
        objLocationSearch.setErroMsg(false);
        objLocationSearch.setDialogMessage(false, null);
        objLocationSearch.getCurrentStation(bundle);
    }

    public void aroundSearch(View view)
    {
        bRetry = true;
        lsNoArround.setVisibility(8);
        txtAroundNow.setVisibility(0);
        aroundSearch();
    }

    public void loginRequest(View view)
    {
        TransitUtil.login(this);
    }

    protected void onActivityResult(int i, int j, Intent intent)
    {
        super.onActivityResult(i, j, intent);
        if (-1 == j)
        {
            if (getResources().getInteger(0x7f0c0063) == i)
            {
                launchDirection((StationData)intent.getSerializableExtra(getString(0x7f0d023e)));
            } else
            if (getResources().getInteger(0x7f0c0046) == i)
            {
                StationData stationdata = (StationData)intent.getSerializableExtra(getString(0x7f0d023e));
                if (stationdata != null && !TransitUtil.isEmpty(stationdata.getName()))
                {
                    if (TransitUtil.isEmpty(stationdata.getId()))
                    {
                        Bundle bundle = new Bundle();
                        bundle.putString(getString(0x7f0d0241), stationdata.getName());
                        bundle.putString(getString(0x7f0d024b), "4");
                        bundle.putString(getString(0x7f0d018e), "hybrid+-int_custom02");
                        launchStationList(null, bundle, "");
                        return;
                    } else
                    {
                        launchDirection(stationdata);
                        return;
                    }
                }
            } else
            if (getResources().getInteger(0x7f0c0053) == i)
            {
                launchTimetable((StationData)intent.getSerializableExtra(getString(0x7f0d023e)), (RailDirectionData)intent.getSerializableExtra(getString(0x7f0d051d)));
                return;
            } else
            {
                Intent intent1 = new Intent(getApplicationContext(), jp/co/yahoo/android/apps/transit/TimeTableTopActivity);
                intent1.setAction("android.intent.action.VIEW");
                startActivityInCurrentMenu(intent1);
                finish();
                return;
            }
        } else
        if (getResources().getInteger(0x7f0c0063) == i && intent != null && intent.hasExtra(getString(0x7f0d01cd)))
        {
            showErrorMessageDialog(intent.getStringExtra(getString(0x7f0d01cd)), getString(0x7f0d014f));
            return;
        }
    }

    public void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setContentView(0x7f030035);
        setTitle(getString(0x7f0d0539));
        nReqCode = getResources().getInteger(0x7f0c0066);
        lsNologin = (LinearLayout)findViewById(0x7f0a0186);
        lsNoArround = (LinearLayout)findViewById(0x7f0a0188);
        txtAroundNow = (TextView)findViewById(0x7f0a006d);
        aroundSearch(lsArround);
        progressDialog = new ProgressDialog(this);
        progressDialog.setCustomTitle((new CustomDialogTitle(this, getString(0x7f0d04aa), 0)).setIconInfo());
        progressDialog.setMessage(getString(0x7f0d04a5));
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(true);
        setRegist();
        dispAd(this, "2080124765", "pv,bottom");
    }

    public void onError(String s, String s1)
    {
        cancelAroundSearch();
    }

    protected void onLoginResult()
    {
        super.onLoginResult();
        if (YTcookieChecker.chkYTcookie())
        {
            setRegist();
        }
    }

    public void onStop()
    {
        super.onStop();
        if (objLocationSearch != null)
        {
            objLocationSearch.stopRequest();
            cancelAroundSearch();
        }
        if (objStationSearch != null)
        {
            objStationSearch.stopRequest();
        }
    }

    public void onSuccess(String s, Bundle bundle)
    {
        bNearStationCompleted = true;
        nearStations = bundle;
        if (bundle != null)
        {
            nearStations = bundle.getBundle(getString(0x7f0d0240));
        }
        setCompleted();
    }

    public void onTimeout(String s, String s1)
    {
        cancelAroundSearch();
    }

    public void showKeywordSearch(View view)
    {
        Intent intent = new Intent(this, jp/co/yahoo/android/apps/transit/InputSearch);
        intent.putExtra(getString(0x7f0d0233), 1);
        intent.putExtra(getString(0x7f0d01c3), false);
        intent.putExtra(getString(0x7f0d01de), false);
        intent.putExtra(getString(0x7f0d01df), nReqCode);
        startActivityForResult(intent, getResources().getInteger(0x7f0c0046));
    }



/*
    static Bundle access$002(TimeTableTopActivity timetabletopactivity, Bundle bundle)
    {
        timetabletopactivity.registinfo = bundle;
        return bundle;
    }

*/



/*
    static boolean access$102(TimeTableTopActivity timetabletopactivity, boolean flag)
    {
        timetabletopactivity.bRegistCompleted = flag;
        return flag;
    }

*/





/*
    static boolean access$502(TimeTableTopActivity timetabletopactivity, boolean flag)
    {
        timetabletopactivity.bError = flag;
        return flag;
    }

*/




/*
    static StationData access$702(TimeTableTopActivity timetabletopactivity, StationData stationdata)
    {
        timetabletopactivity.objStation = stationdata;
        return stationdata;
    }

*/


}

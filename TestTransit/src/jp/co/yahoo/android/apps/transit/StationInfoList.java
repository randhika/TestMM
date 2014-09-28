// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import jp.co.yahoo.android.apps.transit.api.ApiBase;
import jp.co.yahoo.android.apps.transit.api.StationSearch;
import jp.co.yahoo.android.apps.transit.api.data.APIError;
import jp.co.yahoo.android.apps.transit.api.data.DiainfoData;
import jp.co.yahoo.android.apps.transit.api.data.StationData;
import jp.co.yahoo.android.apps.transit.common.util.TransitUtil;

// Referenced classes of package jp.co.yahoo.android.apps.transit:
//            TransitBaseActivity, StationInfo, RailDirection

public class StationInfoList extends TransitBaseActivity
{
    class StationClickListener
        implements android.view.View.OnClickListener
    {

        final StationInfoList this$0;

        public void onClick(View view)
        {
            objStation = (StationData)view.getTag();
            if (TransitUtil.isEmpty(objStation.getId()))
            {
                getStationId(objStation.getName());
                return;
            } else
            {
                launch();
                return;
            }
        }

        StationClickListener()
        {
            this$0 = StationInfoList.this;
            super();
        }
    }


    private boolean bDiainfo;
    private int nReqCode;
    private StationSearch objSearch;
    private StationData objStation;
    private Bundle stationList;

    public StationInfoList()
    {
        stationList = null;
        objStation = null;
        objSearch = null;
        nReqCode = 0;
        bDiainfo = false;
    }

    private void dispatchPage(int i)
    {
        String s;
        Resources resources;
        s = "2080124758";
        resources = getResources();
        if (i != resources.getInteger(0x7f0c0044)) goto _L2; else goto _L1
_L1:
        s = "2080126928";
_L4:
        dispAd(this, s, "pv");
        return;
_L2:
        if (i == resources.getInteger(0x7f0c0040))
        {
            s = "2080126928";
        } else
        if (i == resources.getInteger(0x7f0c0064))
        {
            s = "2080124758";
        } else
        if (i == resources.getInteger(0x7f0c0066))
        {
            s = "2080124762";
        } else
        if (i == resources.getInteger(0x7f0c0056))
        {
            s = "2080124755";
        }
        if (true) goto _L4; else goto _L3
_L3:
    }

    private void displayList()
    {
        if (stationList == null || stationList.size() < 1)
        {
            showErrorMessageDialog(getString(0x7f0d010a), getString(0x7f0d014f));
        } else
        {
            if (nReqCode == getResources().getInteger(0x7f0c0044) || nReqCode == getResources().getInteger(0x7f0c0040))
            {
                bDiainfo = true;
            }
            LayoutInflater layoutinflater = (LayoutInflater)getSystemService("layout_inflater");
            Toast.makeText(this, (new StringBuilder()).append(Integer.toString(stationList.size())).append(getString(0x7f0d00a3)).toString(), 0).show();
            StationClickListener stationclicklistener = new StationClickListener();
            LinearLayout linearlayout = (LinearLayout)findViewById(0x7f0a0321);
            int i = 0;
            while (i < stationList.size()) 
            {
                StationData stationdata = (StationData)stationList.getSerializable(String.valueOf(i));
                TextView textview = (TextView)layoutinflater.inflate(0x7f030077, null);
                textview.setTag(stationdata);
                textview.setText(stationdata.getName());
                linearlayout.addView(textview);
                DiainfoData diainfodata = stationdata.getDiainfo();
                if (bDiainfo && (diainfodata == null || TransitUtil.isEmpty(diainfodata.getRailCode())))
                {
                    textview.setEnabled(false);
                    textview.setTextColor(getResources().getColor(0x7f090036));
                } else
                {
                    textview.setOnClickListener(stationclicklistener);
                }
                linearlayout.addView((ImageView)layoutinflater.inflate(0x7f030059, null));
                i++;
            }
        }
    }

    private void getStationId(String s)
    {
        objSearch = new StationSearch(this, new jp.co.yahoo.android.apps.transit.api.ApiBase.ApiListener() {

            final StationInfoList this$0;

            public boolean onCanceled()
            {
                launch();
                return false;
            }

            public boolean onError(APIError apierror)
            {
                launch();
                return false;
            }

            public boolean onSuccess(ApiBase apibase, Object obj)
            {
                Bundle bundle = objSearch.getResults();
                if (bundle != null && bundle.containsKey("0"))
                {
                    StationData stationdata = (StationData)bundle.getSerializable("0");
                    objStation.setId(stationdata.getId());
                }
                launch();
                return false;
            }

            
            {
                this$0 = StationInfoList.this;
                super();
            }
        });
        objSearch.setExactName(s);
        objSearch.setResultCount(1);
        objSearch.setDialogDisplay(false);
        objSearch.request();
    }

    private void launch()
    {
        if (nReqCode == 0)
        {
            launchReturn();
            return;
        } else
        {
            launchNext();
            return;
        }
    }

    private void launchNext()
    {
        Resources resources = getResources();
        int i;
        Intent intent;
        if (nReqCode == resources.getInteger(0x7f0c0064))
        {
            i = resources.getInteger(0x7f0c0062);
            intent = new Intent(this, jp/co/yahoo/android/apps/transit/StationInfo);
        } else
        if (nReqCode == resources.getInteger(0x7f0c0066))
        {
            i = resources.getInteger(0x7f0c0053);
            intent = new Intent(this, jp/co/yahoo/android/apps/transit/RailDirection);
            intent.putExtra(getString(0x7f0d01df), getResources().getInteger(0x7f0c0063));
        } else
        {
            launchReturn();
            return;
        }
        intent.putExtra(getString(0x7f0d023e), objStation);
        startActivityForResult(intent, i);
    }

    private void launchReturn()
    {
        Intent intent = new Intent();
        intent.putExtra(getString(0x7f0d023e), objStation);
        setResult(-1, intent);
        finish();
    }

    private void launchReturnError()
    {
        Intent intent = new Intent();
        setResult(0, intent);
        intent.putExtra(getString(0x7f0d01cd), getString(0x7f0d012e));
        finish();
    }

    public void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setContentView(0x7f0300a9);
        Intent intent = getIntent();
        Bundle bundle1 = intent.getBundleExtra(getString(0x7f0d022c));
        String s = intent.getStringExtra(getString(0x7f0d01d3));
        nReqCode = intent.getIntExtra(getString(0x7f0d01df), 0);
        if (TransitUtil.isEmpty(s))
        {
            s = getString(0x7f0d04fb);
        }
        setTitle(s);
        if (bundle1.containsKey(getString(0x7f0d0240)))
        {
            stationList = bundle1.getBundle(getString(0x7f0d0240));
            displayList();
        } else
        {
            searchStation(bundle1);
        }
        dispatchPage(nReqCode);
    }

    public void onStop()
    {
        super.onStop();
        if (objSearch != null)
        {
            objSearch.stopRequest();
        }
    }

    protected void searchStation(Bundle bundle)
    {
        objSearch = new StationSearch(this, new jp.co.yahoo.android.apps.transit.api.ApiBase.ApiListener() {

            final StationInfoList this$0;

            public boolean onCanceled()
            {
                return false;
            }

            public boolean onError(APIError apierror)
            {
                launchReturnError();
                return false;
            }

            public boolean onSuccess(ApiBase apibase, Object obj)
            {
                stationList = objSearch.getResults();
                if (stationList == null || stationList.size() < 1)
                {
                    Intent intent = new Intent();
                    intent.putExtra(getString(0x7f0d01cd), getString(0x7f0d012e));
                    setResult(0, intent);
                    finish();
                }
                displayList();
                return false;
            }

            
            {
                this$0 = StationInfoList.this;
                super();
            }
        });
        if (bundle.containsKey(getString(0x7f0d01a3)) && bundle.containsKey(getString(0x7f0d01a3)))
        {
            objSearch.setLat(bundle.getString(getString(0x7f0d01a3)));
            objSearch.setLon(bundle.getString(getString(0x7f0d01a4)));
        } else
        if (bundle.containsKey(getString(0x7f0d0241)))
        {
            objSearch.setQuery(bundle.getString(getString(0x7f0d0241)));
            objSearch.setVI("4");
            objSearch.setResultCount(100);
        }
        if (bundle.containsKey(getString(0x7f0d024b)))
        {
            objSearch.setVI(bundle.getString(getString(0x7f0d024b)));
        }
        if (bundle.containsKey(getString(0x7f0d018e)))
        {
            objSearch.setSort(bundle.getString(getString(0x7f0d018e)));
        } else
        {
            objSearch.setSort("dist");
        }
        if (bundle.containsKey(getString(0x7f0d01a8)) && bundle.containsKey(getString(0x7f0d01d9)))
        {
            objSearch.setCPId(bundle.getString(getString(0x7f0d01a8)));
            objSearch.setRailId(bundle.getString(getString(0x7f0d01d9)));
        }
        objSearch.request();
    }



/*
    static Bundle access$002(StationInfoList stationinfolist, Bundle bundle)
    {
        stationinfolist.stationList = bundle;
        return bundle;
    }

*/






/*
    static StationData access$402(StationInfoList stationinfolist, StationData stationdata)
    {
        stationinfolist.objStation = stationdata;
        return stationdata;
    }

*/


}

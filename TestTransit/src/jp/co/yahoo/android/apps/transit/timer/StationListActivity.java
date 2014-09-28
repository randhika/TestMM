// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.timer;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import jp.co.yahoo.android.apps.transit.timer.api.StationSearch;
import jp.co.yahoo.android.apps.transit.timer.api.data.StationData;
import jp.co.yahoo.android.apps.transit.timer.util.CountdownUtil;
import jp.co.yahoo.android.yolp.common.ApiBase;
import jp.co.yahoo.android.yolp.common.YolpApiBase;

// Referenced classes of package jp.co.yahoo.android.apps.transit.timer:
//            CountdownBaseActivity, RailDirectionActivity

public class StationListActivity extends CountdownBaseActivity
{

    private int nTargetCode;
    private StationData objStation;
    private Bundle stationList;
    private int type;

    public StationListActivity()
    {
        stationList = null;
        objStation = null;
        nTargetCode = 0;
        type = 1;
    }

    private void displayList()
    {
        if (stationList == null || stationList.size() < 1)
        {
            showErrorMessageDialog(getString(0x7f0d012e), getString(0x7f0d014f));
            return;
        }
        ListView listview = (ListView)findViewById(0x7f0a0321);
        listview.setDivider(getResources().getDrawable(0x7f020165));
        ArrayAdapter arrayadapter = new ArrayAdapter(this, 0x7f030077);
        for (int i = 0; i < stationList.size(); i++)
        {
            arrayadapter.add(((StationData)stationList.getSerializable(String.valueOf(i))).getName());
        }

        listview.setAdapter(arrayadapter);
        listview.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {

            final StationListActivity this$0;

            public void onItemClick(AdapterView adapterview, View view, int j, long l)
            {
                objStation = (StationData)stationList.getSerializable(String.valueOf(j));
                if (CountdownUtil.isEmpty(objStation.getStationId()))
                {
                    getStationId(objStation.getName());
                    return;
                } else
                {
                    launchRail();
                    return;
                }
            }

            
            {
                this$0 = StationListActivity.this;
                super();
            }
        });
    }

    private void getStationId(String s)
    {
        StationSearch stationsearch = new StationSearch(this);
        stationsearch.setParam("exact_text2", s);
        stationsearch.setCount(1);
        stationsearch.executeAsync(new jp.co.yahoo.android.yolp.common.ApiBase.ApiListener() {

            final StationListActivity this$0;

            public boolean endApi(ApiBase apibase, Object obj)
            {
                Bundle bundle = (Bundle)apibase.getResult();
                if (bundle != null)
                {
                    if (bundle.containsKey("0"))
                    {
                        StationData stationdata = (StationData)bundle.getSerializable("0");
                        objStation.setStationId(stationdata.getStationId());
                    } else
                    {
                        showErrorMessageDialog(getString(0x7f0d012e), getString(0x7f0d014f));
                    }
                } else
                {
                    showErrorMessageDialog(getString(0x7f0d010a), getString(0x7f0d014f));
                }
                return false;
            }

            
            {
                this$0 = StationListActivity.this;
                super();
            }
        }, true);
    }

    private void launchBack()
    {
        Intent intent = new Intent();
        intent.putExtra(getString(0x7f0d0247), type);
        setResult(-1, intent);
        finish();
    }

    private void launchCancel()
    {
        Intent intent = new Intent();
        intent.putExtra(getString(0x7f0d0247), type);
        setResult(0, intent);
        finish();
    }

    private void launchRail()
    {
        Intent intent = new Intent(this, jp/co/yahoo/android/apps/transit/timer/RailDirectionActivity);
        intent.putExtra(getString(0x7f0d0244), nTargetCode);
        intent.putExtra(getString(0x7f0d0247), type);
        intent.putExtra(getString(0x7f0d023e), objStation);
        startActivityForResult(intent, getResources().getInteger(0x7f0c0053));
    }

    protected void onActivityResult(int i, int j, Intent intent)
    {
        super.onActivityResult(i, j, intent);
        if (j == 0)
        {
            launchCancel();
        } else
        if (-1 == j)
        {
            return;
        }
    }

    public void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setContentView(0x7f0300aa);
        setTitle(getString(0x7f0d04fb));
        Intent intent = getIntent();
        Bundle bundle1 = intent.getBundleExtra(getString(0x7f0d022c));
        stationList = intent.getBundleExtra(getString(0x7f0d0240));
        nTargetCode = intent.getIntExtra(getString(0x7f0d0244), 0);
        type = intent.getIntExtra(getString(0x7f0d0247), 1);
        if (stationList != null)
        {
            displayList();
        } else
        if (bundle1 != null)
        {
            searchStation(bundle1);
            return;
        }
    }

    public boolean onKeyDown(int i, KeyEvent keyevent)
    {
        if (i == 4)
        {
            launchBack();
        }
        return super.onKeyDown(i, keyevent);
    }

    public void onStart()
    {
        super.onStart();
        if (type == getResources().getInteger(0x7f0c0072))
        {
            dispAd(this, "2080325631", "pv");
            return;
        } else
        {
            dispAd(this, "2080325031", "pv");
            return;
        }
    }

    protected void searchStation(Bundle bundle)
    {
        StationSearch stationsearch = new StationSearch(this);
        if (bundle.containsKey(getString(0x7f0d01a3)) && bundle.containsKey(getString(0x7f0d01a3)))
        {
            stationsearch.setLat(bundle.getString(getString(0x7f0d01a3)));
            stationsearch.setLon(bundle.getString(getString(0x7f0d01a4)));
        } else
        if (bundle.containsKey(getString(0x7f0d0241)))
        {
            stationsearch.setQuerySearchMode();
            stationsearch.setQuery(bundle.getString(getString(0x7f0d0241)));
        }
        if (bundle.containsKey(getString(0x7f0d018e)))
        {
            stationsearch.setSort(bundle.getString(getString(0x7f0d018e)));
        } else
        {
            stationsearch.setSort("dist");
        }
        stationsearch.executeAsync(new jp.co.yahoo.android.yolp.common.ApiBase.ApiListener() {

            final StationListActivity this$0;

            public boolean endApi(ApiBase apibase, Object obj)
            {
                stationList = (Bundle)apibase.getResult();
                if (stationList != null)
                {
                    displayList();
                } else
                if (((YolpApiBase)apibase).getError() != null)
                {
                    showErrorMessageDialog(getString(0x7f0d010a), getString(0x7f0d014f));
                } else
                {
                    showErrorMessageDialog(getString(0x7f0d012e), getString(0x7f0d014f));
                }
                return false;
            }

            
            {
                this$0 = StationListActivity.this;
                super();
            }
        }, true);
    }

    protected void showErrorMessageDialog(String s, String s1)
    {
        showErrorMessageDialog(s, s1, new android.content.DialogInterface.OnClickListener() {

            final StationListActivity this$0;

            public void onClick(DialogInterface dialoginterface, int i)
            {
                dialoginterface.cancel();
                launchCancel();
            }

            
            {
                this$0 = StationListActivity.this;
                super();
            }
        });
    }



/*
    static Bundle access$002(StationListActivity stationlistactivity, Bundle bundle)
    {
        stationlistactivity.stationList = bundle;
        return bundle;
    }

*/




/*
    static StationData access$202(StationListActivity stationlistactivity, StationData stationdata)
    {
        stationlistactivity.objStation = stationdata;
        return stationdata;
    }

*/



}

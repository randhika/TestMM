// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.MenuItemCompat;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import jp.co.yahoo.android.apps.transit.api.ApiBase;
import jp.co.yahoo.android.apps.transit.api.NaviSearch;
import jp.co.yahoo.android.apps.transit.api.data.APIError;
import jp.co.yahoo.android.apps.transit.api.data.ConditionData;
import jp.co.yahoo.android.apps.transit.api.data.NaviSearchData;
import jp.co.yahoo.android.apps.transit.api.data.StationData;
import jp.co.yahoo.android.apps.transit.common.CreateShortcut;
import jp.co.yahoo.android.apps.transit.common.SaveCondition;
import jp.co.yahoo.android.apps.transit.common.StringUtil;
import jp.co.yahoo.android.apps.transit.common.util.TransitUtil;
import jp.co.yahoo.android.apps.transit.db.History;
import jp.co.yahoo.android.apps.transit.db.ResultDB;
import jp.co.yahoo.android.apps.transit.viewparts.PopupNew;
import jp.co.yahoo.android.apps.transit.viewparts.SearchDetailCondition;

// Referenced classes of package jp.co.yahoo.android.apps.transit:
//            TransitBaseActivity, Transit, SearchResultListView, OtherCandidatesActivity

public class SearchResultListActivity extends TransitBaseActivity
{
    private class SearchResultTabIndicator extends FrameLayout
    {

        private View indicator;
        private LayoutInflater inflater;
        final SearchResultListActivity this$0;

        public View getIndicator()
        {
            return indicator;
        }

        public SearchResultTabIndicator(Context context1)
        {
            this$0 = SearchResultListActivity.this;
            super(context1);
            inflater = LayoutInflater.from(context1);
        }

        public SearchResultTabIndicator(Context context1, int i)
        {
            this(context1);
            indicator = inflater.inflate(0x7f03009a, null);
            if (i == 0x7f020266)
            {
                indicator.setContentDescription(getString(0x7f0d02d9));
                indicator.setBackgroundResource(0x7f020273);
            } else
            if (i == 0x7f020265)
            {
                indicator.setContentDescription(getString(0x7f0d02d8));
            } else
            {
                indicator.setContentDescription(getString(0x7f0d02d7));
            }
            ((ImageView)indicator.findViewById(0x7f0a02e5)).setImageResource(i);
            indicator.setFocusable(true);
            indicator.setOnClickListener(new _cls1());
            addView(indicator);
        }
    }


    public static final int TAB_INDEX_CHEAP = 2;
    public static final int TAB_INDEX_EASY = 1;
    public static final int TAB_INDEX_FAST;
    private Button afterFinal;
    private boolean bMemo;
    private ConditionData conditionData;
    private Context context;
    private jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.NaviPointData goalCoordinates;
    private boolean goalNameCandidatesExist;
    private History history;
    private SearchResultListView lvCheap;
    private SearchResultListView lvEasy;
    private SearchResultListView lvFast;
    private PopupNew popup;
    private ConditionData researchCondData;
    private NaviSearchData results;
    private Intent returnIntent;
    private jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.NaviPointData startCoordinates;
    private boolean startNameCandidatesExist;
    private TabHost tabs;
    private SearchResultTabIndicator v1;
    private SearchResultTabIndicator v2;
    private SearchResultTabIndicator v3;
    private boolean viaStationCandidatesExist;

    public SearchResultListActivity()
    {
        bMemo = false;
        startNameCandidatesExist = false;
        goalNameCandidatesExist = false;
        viaStationCandidatesExist = false;
    }

    private StationData convertStationData2NaviPointData(jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.NaviPointData navipointdata)
    {
        StationData stationdata = new StationData();
        stationdata.setName(navipointdata.stationName);
        stationdata.setId(navipointdata.stationCode);
        stationdata.setLat(navipointdata.lat);
        stationdata.setLon(navipointdata.lon);
        stationdata.setType(navipointdata.type);
        return stationdata;
    }

    private void delNextPrevBtn()
    {
        ((ImageView)findViewById(0x7f0a016f)).setVisibility(8);
        ((ImageView)findViewById(0x7f0a0173)).setVisibility(8);
    }

    private android.view.View.OnClickListener getOnClickListenerForAfterFinal()
    {
        android.view.View.OnClickListener onclicklistener;
        try
        {
            onclicklistener = new android.view.View.OnClickListener() {

                final SearchResultListActivity this$0;

                public void onClick(View view)
                {
                    touchTapRD(getString(0x7f0d03c2));
                    researchCondData = (ConditionData)conditionData.clone();
                    researchCondData.mtf = -1;
                    HashMap hashmap = results.points;
                    ArrayList arraylist = results.routes;
                    ArrayList arraylist1 = ((jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.NaviRouteData)arraylist.get(0)).edges;
                    jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.NaviPointData navipointdata = (jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.NaviPointData)hashmap.get(((jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.Edge)arraylist1.get(0)).startPointTarget);
                    researchCondData.startName = navipointdata.stationName;
                    researchCondData.startCode = "";
                    jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.NaviPointData navipointdata1 = (jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.NaviPointData)hashmap.get(((jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.Edge)arraylist1.get(-1 + arraylist1.size())).startPointTarget);
                    researchCondData.goalName = navipointdata1.stationName;
                    researchCondData.goalCode = "";
                    String s = ((jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.NaviRouteData)arraylist.get(0)).startTime;
                    int i = Integer.parseInt(s.substring(0, 4));
                    int j = Integer.parseInt(s.substring(4, 6));
                    int k = Integer.parseInt(s.substring(6, 8));
                    int l = Integer.parseInt(s.substring(8, 10));
                    int i1 = Integer.parseInt(s.substring(10));
                    GregorianCalendar gregoriancalendar = new GregorianCalendar(i, j - 1, k, l, i1);
                    gregoriancalendar.add(12, 1);
                    researchCondData.year = gregoriancalendar.get(1);
                    researchCondData.month = 1 + gregoriancalendar.get(2);
                    researchCondData.day = gregoriancalendar.get(5);
                    researchCondData.hour = gregoriancalendar.get(11);
                    researchCondData.minite = gregoriancalendar.get(12);
                    if (startCoordinates != null)
                    {
                        researchCondData.startLat = startCoordinates.lat;
                        researchCondData.startLon = startCoordinates.lon;
                    }
                    if (goalCoordinates != null)
                    {
                        researchCondData.goalLat = goalCoordinates.lat;
                        researchCondData.goalLon = goalCoordinates.lon;
                    }
                    researchCondData.type = getResources().getInteger(0x7f0c0070);
                    researchCondData.afterFinal = true;
                    researchCondData.midnightBus = true;
                    search();
                }

            
            {
                this$0 = SearchResultListActivity.this;
                super();
            }
            };
        }
        catch (Exception exception)
        {
            return null;
        }
        return onclicklistener;
    }

    private void notabClear()
    {
        ((FrameLayout)findViewById(0x7f0a0175)).removeAllViews();
    }

    private void setAfterFinalButton(ConditionData conditiondata)
    {
        if (conditiondata.type == getResources().getInteger(0x7f0c006f))
        {
            android.view.View.OnClickListener onclicklistener = getOnClickListenerForAfterFinal();
            if (onclicklistener != null)
            {
                afterFinal.setOnClickListener(onclicklistener);
                afterFinal.setVisibility(0);
            }
            return;
        } else
        {
            afterFinal.setVisibility(8);
            return;
        }
    }

    private void setListTitle(NaviSearchData navisearchdata)
    {
        if (navisearchdata == null || navisearchdata.routes == null || navisearchdata.routes.size() <= 0) goto _L2; else goto _L1
_L1:
        String s6;
        Resources resources;
        int i;
        String s7;
        TextView textview = (TextView)findViewById(0x7f0a016d);
        String s1 = conditionData.startName;
        String s2 = conditionData.goalName;
        String s3 = TransitUtil.join(conditionData.viaName, ",");
        String s4 = (new StringBuilder()).append(s1).append(getString(0x7f0d0276)).toString();
        if (s3 != null && s3.length() > 0)
        {
            s4 = (new StringBuilder()).append(s4).append(s3).append(getString(0x7f0d0276)).toString();
        }
        textview.setText((new StringBuilder()).append(s4).append(s2).toString());
        TextView textview1 = (TextView)findViewById(0x7f0a0171);
        String s5 = Transit.getDayOfWeekJP(conditionData.year, conditionData.month, conditionData.day);
        Object aobj[] = new Object[4];
        aobj[0] = Integer.valueOf(conditionData.year);
        aobj[1] = Integer.valueOf(conditionData.month);
        aobj[2] = Integer.valueOf(conditionData.day);
        aobj[3] = s5;
        textview1.setText(String.format("%d\u5E74%d\u6708%d\u65E5(%s)", aobj));
        Object aobj1[] = new Object[2];
        aobj1[0] = Integer.valueOf(conditionData.hour);
        aobj1[1] = Integer.valueOf(conditionData.minite);
        s6 = String.format("%02d:%02d", aobj1);
        resources = getResources();
        i = conditionData.type;
        s7 = "";
        if (i != resources.getInteger(0x7f0c006b)) goto _L4; else goto _L3
_L3:
        s7 = (new StringBuilder()).append(s6).append(getString(0x7f0d02f3)).toString();
_L7:
        ((TextView)findViewById(0x7f0a0172)).setText(s7);
        ArrayList arraylist = ((jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.NaviRouteData)navisearchdata.routes.get(0)).edges;
        HashMap hashmap = navisearchdata.points;
        int j = arraylist.size();
        if (j > 1)
        {
            int k = 0;
            do
            {
                if (k >= j)
                {
                    break;
                }
                jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.NaviPointData navipointdata = (jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.NaviPointData)hashmap.get(((jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.Edge)arraylist.get(k)).startPointTarget);
                if (k == 0)
                {
                    startCoordinates = navipointdata;
                } else
                if (k == j - 1)
                {
                    goalCoordinates = navipointdata;
                }
                k++;
            } while (true);
        }
          goto _L5
_L4:
        if (i == resources.getInteger(0x7f0c0070))
        {
            s7 = (new StringBuilder()).append(s6).append(getString(0x7f0d02f3)).toString();
        } else
        if (i == resources.getInteger(0x7f0c006e))
        {
            s7 = (new StringBuilder()).append(s6).append(getString(0x7f0d02f1)).toString();
        } else
        if (i == resources.getInteger(0x7f0c006d))
        {
            s7 = getString(0x7f0d02f0);
        } else
        if (i == resources.getInteger(0x7f0c006f))
        {
            s7 = getString(0x7f0d02f2);
        } else
        if (i == resources.getInteger(0x7f0c006a))
        {
            s7 = getString(0x7f0d02ee);
        }
        continue; /* Loop/switch isn't completed */
_L2:
        String s = getString(0x7f0d0108);
        returnIntent.putExtra(getString(0x7f0d01b9), s);
        returnIntent.putExtra(getString(0x7f0d022c), conditionData);
        setResult(-1, returnIntent);
        finish();
_L5:
        return;
        if (true) goto _L7; else goto _L6
_L6:
    }

    private void setSearchConditions()
    {
        SearchDetailCondition searchdetailcondition = new SearchDetailCondition(this);
        searchdetailcondition.setCondition(conditionData, getResources(), this);
        searchdetailcondition.searchNowButtonOff();
        LinearLayout linearlayout = (LinearLayout)findViewById(0x7f0a0177);
        linearlayout.removeAllViews();
        linearlayout.addView(searchdetailcondition);
    }

    private void setTabContent()
    {
        int i;
        if (conditionData.type == getResources().getInteger(0x7f0c006a))
        {
            delNextPrevBtn();
        }
        tabs = (TabHost)findViewById(0x7f0a0174);
        tabs.setup();
        v1 = new SearchResultTabIndicator(this, 0x7f020266);
        v2 = new SearchResultTabIndicator(this, 0x7f020265);
        v3 = new SearchResultTabIndicator(this, 0x7f020264);
        lvFast = new SearchResultListView(this);
        lvFast.setValues(conditionData);
        lvFast.setId(0x7f0a002f);
        lvEasy = new SearchResultListView(this);
        lvEasy.setValues(conditionData);
        lvEasy.setId(0x7f0a002e);
        lvCheap = new SearchResultListView(this);
        lvCheap.setValues(conditionData);
        lvCheap.setId(0x7f0a002d);
        android.widget.TabHost.TabSpec tabspec = tabs.newTabSpec("tab1").setIndicator(v1).setContent(new android.widget.TabHost.TabContentFactory() {

            final SearchResultListActivity this$0;

            public View createTabContent(String s)
            {
                return lvFast;
            }

            
            {
                this$0 = SearchResultListActivity.this;
                super();
            }
        });
        tabs.addTab(tabspec);
        android.widget.TabHost.TabSpec tabspec1 = tabs.newTabSpec("tab2").setIndicator(v2).setContent(new android.widget.TabHost.TabContentFactory() {

            final SearchResultListActivity this$0;

            public View createTabContent(String s)
            {
                return lvEasy;
            }

            
            {
                this$0 = SearchResultListActivity.this;
                super();
            }
        });
        tabs.addTab(tabspec1);
        android.widget.TabHost.TabSpec tabspec2 = tabs.newTabSpec("tab3").setIndicator(v3).setContent(new android.widget.TabHost.TabContentFactory() {

            final SearchResultListActivity this$0;

            public View createTabContent(String s)
            {
                return lvCheap;
            }

            
            {
                this$0 = SearchResultListActivity.this;
                super();
            }
        });
        tabs.addTab(tabspec2);
        setResult(0, returnIntent);
        i = conditionData.sort;
        if (i != getResources().getInteger(0x7f0c002f)) goto _L2; else goto _L1
_L1:
        int k = 1;
_L4:
        if (i == getResources().getInteger(0x7f0c0030))
        {
            tabs.setCurrentTab(1);
        }
        tabs.setOnTabChangedListener(new android.widget.TabHost.OnTabChangeListener() {

            final SearchResultListActivity this$0;

            public void onTabChanged(String s)
            {
                SearchResultListView searchresultlistview = (SearchResultListView)tabs.getCurrentView();
                int l = getResources().getInteger(0x7f0c0026);
                if (searchresultlistview.getId() == 0x7f0a002f)
                {
                    l = getResources().getInteger(0x7f0c0030);
                    lvFast.setVisibility(0);
                    lvEasy.setVisibility(8);
                    lvCheap.setVisibility(8);
                } else
                if (searchresultlistview.getId() == 0x7f0a002e)
                {
                    l = getResources().getInteger(0x7f0c002f);
                    lvFast.setVisibility(8);
                    lvEasy.setVisibility(0);
                    lvCheap.setVisibility(8);
                } else
                if (searchresultlistview.getId() == 0x7f0a002d)
                {
                    l = getResources().getInteger(0x7f0c002e);
                    lvFast.setVisibility(8);
                    lvEasy.setVisibility(8);
                    lvCheap.setVisibility(0);
                }
                if (!searchresultlistview.isDataFilled())
                {
                    if (conditionData.sort != l)
                    {
                        researchCondData = (ConditionData)conditionData.clone();
                        researchCondData.sort = l;
                        search();
                    }
                    return;
                } else
                {
                    conditionData.sort = l;
                    return;
                }
            }

            
            {
                this$0 = SearchResultListActivity.this;
                super();
            }
        });
        tabs.setCurrentTab(k);
        return;
_L2:
        int j = getResources().getInteger(0x7f0c002e);
        k = 0;
        if (i == j)
        {
            k = 2;
        }
        if (true) goto _L4; else goto _L3
_L3:
    }

    private void setnoTabContent(ConditionData conditiondata, NaviSearchData navisearchdata)
    {
        FrameLayout framelayout = (FrameLayout)findViewById(0x7f0a0175);
        SearchResultListView searchresultlistview = new SearchResultListView(context);
        searchresultlistview.setValues(conditiondata);
        searchresultlistview.setChildren(this, navisearchdata);
        searchresultlistview.setDataFilled(true);
        searchresultlistview.setData(navisearchdata);
        framelayout.addView(searchresultlistview);
        results = navisearchdata;
        ((TabHost)findViewById(0x7f0a0174)).setVisibility(8);
        setAfterFinalButton(conditiondata);
        LinearLayout linearlayout = (LinearLayout)findViewById(0x7f0a016c);
        ImageView imageview = (ImageView)findViewById(0x7f0a016e);
        LinearLayout linearlayout1 = (LinearLayout)findViewById(0x7f0a016b);
        linearlayout.setVisibility(8);
        imageview.setVisibility(8);
        linearlayout1.setClickable(false);
        delNextPrevBtn();
    }

    private void showResultList(NaviSearchData navisearchdata)
    {
        if (navisearchdata == null || navisearchdata.routes == null || navisearchdata.routes.size() <= 0) goto _L2; else goto _L1
_L1:
        if (!bMemo)
        {
            (new ResultDB(this)).addSearchResultsHistory(conditionData, navisearchdata, -1);
        }
        SearchResultListView searchresultlistview = lvFast;
        ArrayList arraylist;
        ArrayList arraylist1;
        ArrayList arraylist2;
        int i;
        int j;
        int k;
        int l;
        if (getResources().getInteger(0x7f0c002f) == conditionData.sort)
        {
            searchresultlistview = lvEasy;
        } else
        if (getResources().getInteger(0x7f0c002e) == conditionData.sort)
        {
            searchresultlistview = lvCheap;
        }
        if (searchresultlistview != null);
        searchresultlistview.setValues(conditionData);
        searchresultlistview.setChildren(this, navisearchdata);
        searchresultlistview.setDataFilled(true);
        searchresultlistview.setData(navisearchdata);
        results = navisearchdata;
        String s;
        LinearLayout linearlayout;
        ImageView imageview;
        LinearLayout linearlayout1;
        try
        {
            if (navisearchdata.startStationList != null && navisearchdata.startStationList.size() > 0)
            {
                history.addHistory(convertStationData2NaviPointData((jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.NaviPointData)navisearchdata.startStationList.get(0)));
            }
            if (navisearchdata.goalStationList != null && navisearchdata.goalStationList.size() > 0)
            {
                history.addHistory(convertStationData2NaviPointData((jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.NaviPointData)navisearchdata.goalStationList.get(0)));
            }
            if (navisearchdata.viaStationList != null && navisearchdata.viaStationList.size() > 0)
            {
                history.addHistory(convertStationData2NaviPointData((jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.NaviPointData)navisearchdata.viaStationList.get(0)));
            }
        }
        catch (Exception exception) { }
        startNameCandidatesExist = false;
        goalNameCandidatesExist = false;
        viaStationCandidatesExist = false;
        arraylist = navisearchdata.startStationList;
        arraylist1 = navisearchdata.goalStationList;
        arraylist2 = navisearchdata.viaStationList;
        if (arraylist != null && arraylist.size() > 1)
        {
            startNameCandidatesExist = true;
        }
        if (arraylist1 != null && arraylist1.size() > 1)
        {
            goalNameCandidatesExist = true;
        }
        if (arraylist2 == null || arraylist2.size() <= 1) goto _L4; else goto _L3
_L3:
        i = 0;
        j = 0;
        k = 0;
        l = 0;
_L6:
        while (l < arraylist2.size()) 
        {
            if (((jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.NaviPointData)arraylist2.get(l)).position == 0 && i == 0)
            {
                history.addHistory(convertStationData2NaviPointData((jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.NaviPointData)navisearchdata.viaStationList.get(l)));
                i++;
            } else
            if (((jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.NaviPointData)arraylist2.get(l)).position == 0)
            {
                i++;
            }
            if (((jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.NaviPointData)arraylist2.get(l)).position == 1 && j == 0)
            {
                history.addHistory(convertStationData2NaviPointData((jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.NaviPointData)navisearchdata.viaStationList.get(l)));
                j++;
            } else
            if (((jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.NaviPointData)arraylist2.get(l)).position == 1)
            {
                j++;
            }
            if (((jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.NaviPointData)arraylist2.get(l)).position == 2 && k == 0)
            {
                history.addHistory(convertStationData2NaviPointData((jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.NaviPointData)navisearchdata.viaStationList.get(l)));
                k++;
            } else
            if (((jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.NaviPointData)arraylist2.get(l)).position == 2)
            {
                k++;
            }
            l++;
        }
        if (i > 1 || j > 1 || k > 1)
        {
            viaStationCandidatesExist = true;
        }
_L4:
        linearlayout = (LinearLayout)findViewById(0x7f0a016c);
        imageview = (ImageView)findViewById(0x7f0a016e);
        linearlayout1 = (LinearLayout)findViewById(0x7f0a016b);
        if (startNameCandidatesExist || goalNameCandidatesExist || viaStationCandidatesExist)
        {
            linearlayout.setVisibility(0);
            imageview.setVisibility(0);
            linearlayout1.setClickable(true);
            linearlayout1.setOnClickListener(new android.view.View.OnClickListener() {

                final SearchResultListActivity this$0;

                public void onClick(View view)
                {
                    showCandidates();
                }

            
            {
                this$0 = SearchResultListActivity.this;
                super();
            }
            });
            return;
        } else
        {
            linearlayout.setVisibility(8);
            imageview.setVisibility(8);
            linearlayout1.setClickable(false);
            return;
        }
_L2:
        s = getString(0x7f0d0108);
        returnIntent.putExtra(getString(0x7f0d01b9), s);
        returnIntent.putExtra(getString(0x7f0d022c), conditionData);
        setResult(-1, returnIntent);
        finish();
        return;
        if (true) goto _L6; else goto _L5
_L5:
    }

    public void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setContentView(0x7f030032);
        setTitle(getString(0x7f0d04ab));
        context = this;
        returnIntent = new Intent();
        bMemo = getIntent().getBooleanExtra(getString(0x7f0d01bd), false);
        conditionData = (ConditionData)getIntent().getSerializableExtra(getString(0x7f0d022c));
        results = (NaviSearchData)getIntent().getSerializableExtra(getString(0x7f0d0232));
        setSearchConditions();
        setListTitle(results);
        afterFinal = (Button)findViewById(0x7f0a0176);
        history = new History(this);
        boolean flag;
        if (conditionData.afterFinal)
        {
            setnoTabContent(conditionData, results);
        } else
        {
            setTabContent();
            showResultList(results);
            setAfterFinalButton(conditionData);
        }
        flag = conditionData.keyCurrent;
        conditionData.keyCurrent = false;
        if (flag)
        {
            dispAd(this, "2080157056", "pv");
            return;
        } else
        {
            dispAd(this, "2080157055", "pv");
            return;
        }
    }

    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuItemCompat.setShowAsAction(menu.add(0, 0, 0, getString(0x7f0d02e5)).setIcon(0x7f0200bd), 1);
        MenuItemCompat.setShowAsAction(menu.add(0, 1, 1, getString(0x7f0d0297)).setIcon(0x7f020113), 1);
        if (PopupNew.isShow(this, getString(0x7f0d03a4), -1))
        {
            (new Handler()).post(new Runnable() {

                final SearchResultListActivity this$0;

                public void run()
                {
                    View view = findViewById(0);
                    if (view != null)
                    {
                        float f = view.getWidth();
                        float f1 = TransitUtil.dpToPx(SearchResultListActivity.this, 141F);
                        float f2 = TransitUtil.dpToPx(SearchResultListActivity.this, 7F);
                        float f3 = (f - f1) / 2.0F - f2;
                        popup = new PopupNew(SearchResultListActivity.this, view);
                        popup.display((int)f3, 0, -1, getString(0x7f0d03a4), 0x7f02029d, false);
                    }
                }

            
            {
                this$0 = SearchResultListActivity.this;
                super();
            }
            });
        }
        return true;
    }

    protected void onDestroy()
    {
        super.onDestroy();
    }

    protected void onOpenSlideMenu()
    {
        super.onOpenSlideMenu();
        if (popup != null)
        {
            popup.dismiss();
        }
    }

    public boolean onOptionsItemSelected(MenuItem menuitem)
    {
        super.onOptionsItemSelected(menuitem);
        menuitem.getItemId();
        JVM INSTR tableswitch 0 1: default 36
    //                   0 38
    //                   1 82;
           goto _L1 _L2 _L3
_L1:
        return true;
_L2:
        touchTapRD(getString(0x7f0d03f9));
        if (popup != null)
        {
            popup.dismiss();
        }
        registMyroute(new ResultDB(this), conditionData);
        continue; /* Loop/switch isn't completed */
_L3:
        touchTapRD(getString(0x7f0d0419));
        (new CreateShortcut(this)).showShortcutDialog(conditionData);
        if (true) goto _L1; else goto _L4
_L4:
    }

    protected void onPause()
    {
        super.onPause();
        if (popup != null)
        {
            popup.dismiss();
        }
    }

    protected void onRestoreInstanceState(Bundle bundle)
    {
        super.onRestoreInstanceState(bundle);
        conditionData = (ConditionData)bundle.getSerializable(getString(0x7f0d022c));
        results = (NaviSearchData)bundle.getSerializable(getString(0x7f0d0232));
        if (conditionData == null || results == null) goto _L2; else goto _L1
_L1:
        if (!conditionData.afterFinal) goto _L4; else goto _L3
_L3:
        setnoTabContent(conditionData, results);
_L6:
        setSearchConditions();
        setListTitle(results);
_L2:
        return;
_L4:
        int i;
        int k;
        showResultList(results);
        i = conditionData.sort;
        if (i != getResources().getInteger(0x7f0c002f))
        {
            break; /* Loop/switch isn't completed */
        }
        k = 1;
_L7:
        tabs.setCurrentTab(k);
        if (true) goto _L6; else goto _L5
_L5:
        int j = getResources().getInteger(0x7f0c002e);
        k = 0;
        if (i == j)
        {
            k = 2;
        }
          goto _L7
        if (true) goto _L6; else goto _L8
_L8:
    }

    protected void onResume()
    {
        super.onResume();
    }

    protected void onSaveInstanceState(Bundle bundle)
    {
        super.onSaveInstanceState(bundle);
        bundle.putSerializable(getString(0x7f0d022c), conditionData);
        bundle.putSerializable(getString(0x7f0d0232), results);
    }

    protected void onStop()
    {
        super.onStop();
    }

    public void search()
    {
        if (researchCondData.ticket == null)
        {
            ConditionData conditiondata = (new SaveCondition(this)).getCond();
            if (conditiondata != null)
            {
                researchCondData.ticket = conditiondata.ticket;
            }
        }
        NaviSearch navisearch = new NaviSearch(context, new jp.co.yahoo.android.apps.transit.api.ApiBase.ApiListener() {

            final SearchResultListActivity this$0;

            public boolean onCanceled()
            {
                return false;
            }

            public boolean onError(APIError apierror)
            {
                String s = apierror.getMessage();
                if (StringUtil.isEmpty(s))
                {
                    s = getString(0x7f0d0108);
                }
                showSimpleErrorMessageDialog(s);
                return false;
            }

            public boolean onSuccess(ApiBase apibase, Object obj)
            {
                NaviSearchData navisearchdata = NaviSearch.m_routeList;
                conditionData = researchCondData;
                if (conditionData.afterFinal)
                {
                    setnoTabContent(conditionData, navisearchdata);
                } else
                {
                    showResultList(navisearchdata);
                }
                setSearchConditions();
                setListTitle(navisearchdata);
                return false;
            }

            
            {
                this$0 = SearchResultListActivity.this;
                super();
            }
        });
        navisearch.setCondition(researchCondData);
        navisearch.exec();
    }

    public void searchAfter(View view)
    {
        touchTapRD(getString(0x7f0d03fc));
        researchCondData = (ConditionData)conditionData.clone();
        lvFast.setDataFilled(false);
        lvEasy.setDataFilled(false);
        lvCheap.setDataFilled(false);
        String s = ((jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.NaviRouteData)results.routes.get(0)).startTime;
        int i = Integer.parseInt(s.substring(0, 4));
        int j = Integer.parseInt(s.substring(4, 6));
        int k = Integer.parseInt(s.substring(6, 8));
        int l = Integer.parseInt(s.substring(8, 10));
        int i1 = Integer.parseInt(s.substring(10));
        GregorianCalendar gregoriancalendar = new GregorianCalendar(i, j - 1, k, l, i1);
        gregoriancalendar.add(12, 1);
        researchCondData.year = gregoriancalendar.get(1);
        researchCondData.month = 1 + gregoriancalendar.get(2);
        researchCondData.day = gregoriancalendar.get(5);
        researchCondData.hour = gregoriancalendar.get(11);
        researchCondData.minite = gregoriancalendar.get(12);
        researchCondData.type = getResources().getInteger(0x7f0c0070);
        search();
    }

    public void searchBefore(View view)
    {
        touchTapRD(getString(0x7f0d0403));
        researchCondData = (ConditionData)conditionData.clone();
        lvFast.setDataFilled(false);
        lvEasy.setDataFilled(false);
        lvCheap.setDataFilled(false);
        String s = ((jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.NaviRouteData)results.routes.get(0)).goalTime;
        int i = Integer.parseInt(s.substring(0, 4));
        int j = Integer.parseInt(s.substring(4, 6));
        int k = Integer.parseInt(s.substring(6, 8));
        int l = Integer.parseInt(s.substring(8, 10));
        int i1 = Integer.parseInt(s.substring(10));
        GregorianCalendar gregoriancalendar = new GregorianCalendar(i, j - 1, k, l, i1);
        gregoriancalendar.add(12, -1);
        researchCondData.year = gregoriancalendar.get(1);
        researchCondData.month = 1 + gregoriancalendar.get(2);
        researchCondData.day = gregoriancalendar.get(5);
        researchCondData.hour = gregoriancalendar.get(11);
        researchCondData.minite = gregoriancalendar.get(12);
        researchCondData.type = getResources().getInteger(0x7f0c006e);
        search();
    }

    public void showCandidates()
    {
        touchTapRD(getString(0x7f0d03ec));
        Intent intent = new Intent(this, jp/co/yahoo/android/apps/transit/OtherCandidatesActivity);
        intent.putExtra(getString(0x7f0d0232), results);
        intent.putExtra(getString(0x7f0d022c), conditionData);
        startActivityForResult(intent, getResources().getInteger(0x7f0c004d));
    }











/*
    static PopupNew access$1602(SearchResultListActivity searchresultlistactivity, PopupNew popupnew)
    {
        searchresultlistactivity.popup = popupnew;
        return popupnew;
    }

*/





/*
    static ConditionData access$402(SearchResultListActivity searchresultlistactivity, ConditionData conditiondata)
    {
        searchresultlistactivity.conditionData = conditiondata;
        return conditiondata;
    }

*/



/*
    static ConditionData access$502(SearchResultListActivity searchresultlistactivity, ConditionData conditiondata)
    {
        searchresultlistactivity.researchCondData = conditiondata;
        return conditiondata;
    }

*/





    // Unreferenced inner class jp/co/yahoo/android/apps/transit/SearchResultListActivity$SearchResultTabIndicator$1

/* anonymous class */
    class SearchResultTabIndicator._cls1
        implements android.view.View.OnClickListener
    {

        final SearchResultTabIndicator this$1;
        final SearchResultListActivity val$this$0;

        public void onClick(View view)
        {
            if (view == v1.getIndicator())
            {
                touchTapRD(getString(0x7f0d03d8));
                tabs.setCurrentTab(0);
            } else
            {
                if (view == v2.getIndicator())
                {
                    touchTapRD(getString(0x7f0d03d9));
                    tabs.setCurrentTab(1);
                    return;
                }
                if (view == v3.getIndicator())
                {
                    touchTapRD(getString(0x7f0d03d0));
                    tabs.setCurrentTab(2);
                    return;
                }
            }
        }

            
            {
                this$1 = final_searchresulttabindicator;
                this$0 = SearchResultListActivity.this;
                super();
            }
    }

}

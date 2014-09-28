// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
import jp.co.yahoo.android.apps.transit.api.ApiBase;
import jp.co.yahoo.android.apps.transit.api.NaviSearch;
import jp.co.yahoo.android.apps.transit.api.data.APIError;
import jp.co.yahoo.android.apps.transit.api.data.ConditionData;
import jp.co.yahoo.android.apps.transit.api.data.NaviSearchData;
import jp.co.yahoo.android.apps.transit.common.SaveCondition;
import jp.co.yahoo.android.apps.transit.common.StringUtil;
import jp.co.yahoo.android.apps.transit.common.util.TransitUtil;

// Referenced classes of package jp.co.yahoo.android.apps.transit:
//            TransitBaseActivity, Transit, SearchResultActivity, SearchResultListActivity

public class OtherCandidatesActivity extends TransitBaseActivity
{
    public class OtherCandidateAdapter extends ArrayAdapter
    {

        private LayoutInflater inflater;
        private List items;
        final OtherCandidatesActivity this$0;

        private View getViewItem(int i)
        {
            jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.NaviPointData navipointdata = (jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.NaviPointData)items.get(i);
            String s = navipointdata.stationName;
            String s1 = navipointdata.shortAddress;
            View view;
            TextView textview;
            if (TransitUtil.isEmpty(s1))
            {
                view = inflater.inflate(0x7f030077, null);
                textview = (TextView)view;
            } else
            {
                view = inflater.inflate(0x7f030083, null);
                ((TextView)view.findViewById(0x7f0a0281)).setText(s1);
                textview = (TextView)view.findViewById(0x7f0a0063);
            }
            textview.setText(s);
            return view;
        }

        public View getDropDownView(int i, View view, ViewGroup viewgroup)
        {
            return getViewItem(i);
        }

        public View getView(int i, View view, ViewGroup viewgroup)
        {
            View view1 = getViewItem(i);
            view1.setBackgroundColor(getResources().getColor(0x7f09006e));
            return view1;
        }

        public OtherCandidateAdapter(Context context1, int i, ArrayList arraylist)
        {
            this$0 = OtherCandidatesActivity.this;
            super(context1, i, arraylist);
            items = arraylist;
            inflater = (LayoutInflater)context1.getSystemService("layout_inflater");
        }
    }


    private ConditionData conditionData;
    private Context context;
    private Spinner goalSpnr;
    private TextView goalText;
    private NaviSearchData results;
    private Intent returnIntent;
    private Spinner startSpnr;
    private TextView startText;
    private LinearLayout viaLayout;
    private LinearLayout viaLayout2;
    private LinearLayout viaLayout3;
    private Spinner viaSpnr;
    private Spinner viaSpnr2;
    private Spinner viaSpnr3;
    private TextView viaText;
    private TextView viaText2;
    private TextView viaText3;

    public OtherCandidatesActivity()
    {
    }

    private void save()
    {
        if (startSpnr.getVisibility() == 0)
        {
            jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.NaviPointData navipointdata4 = (jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.NaviPointData)startSpnr.getSelectedItem();
            conditionData.startName = navipointdata4.stationName;
            conditionData.startLat = navipointdata4.lat;
            conditionData.startLon = navipointdata4.lon;
            conditionData.startCode = navipointdata4.stationCode;
        }
        if (goalSpnr.getVisibility() == 0)
        {
            jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.NaviPointData navipointdata3 = (jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.NaviPointData)goalSpnr.getSelectedItem();
            conditionData.goalName = navipointdata3.stationName;
            conditionData.goalLat = navipointdata3.lat;
            conditionData.goalLon = navipointdata3.lon;
            conditionData.goalCode = navipointdata3.stationCode;
        }
        if (viaSpnr.getVisibility() == 0)
        {
            jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.NaviPointData navipointdata2 = (jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.NaviPointData)viaSpnr.getSelectedItem();
            conditionData.viaName.set(0, navipointdata2.stationName);
            conditionData.viaCode.set(0, navipointdata2.stationCode);
        }
        if (viaSpnr2.getVisibility() == 0)
        {
            jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.NaviPointData navipointdata1 = (jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.NaviPointData)viaSpnr2.getSelectedItem();
            conditionData.viaName.set(1, navipointdata1.stationName);
            conditionData.viaCode.set(1, navipointdata1.stationCode);
        }
        if (viaSpnr3.getVisibility() == 0)
        {
            jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.NaviPointData navipointdata = (jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.NaviPointData)viaSpnr3.getSelectedItem();
            conditionData.viaName.set(2, navipointdata.stationName);
            conditionData.viaCode.set(2, navipointdata.stationCode);
        }
        if (conditionData.ticket == null)
        {
            ConditionData conditiondata = (new SaveCondition(context)).getCond();
            if (conditiondata != null)
            {
                conditionData.ticket = conditiondata.ticket;
            }
        }
    }

    private void showCandidates()
    {
        ArrayList arraylist = results.startStationList;
        ArrayList arraylist1;
        ArrayList arraylist2;
        ArrayList arraylist3;
        ArrayList arraylist4;
        if (1 < arraylist.size())
        {
            OtherCandidateAdapter othercandidateadapter = new OtherCandidateAdapter(this, 0x1090008, arraylist);
            startSpnr.setAdapter(othercandidateadapter);
            startSpnr.setSelected(true);
            startSpnr.setVisibility(0);
            startText.setVisibility(8);
        } else
        {
            if (1 == arraylist.size())
            {
                startText.setText(((jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.NaviPointData)arraylist.get(0)).stationName);
            }
            startSpnr.setVisibility(8);
            startText.setVisibility(0);
        }
        arraylist1 = results.goalStationList;
        if (1 < arraylist1.size())
        {
            OtherCandidateAdapter othercandidateadapter1 = new OtherCandidateAdapter(this, 0x1090008, arraylist1);
            goalSpnr.setAdapter(othercandidateadapter1);
            goalSpnr.setVisibility(0);
            goalText.setVisibility(8);
        } else
        {
            if (1 == arraylist1.size())
            {
                goalText.setText(((jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.NaviPointData)arraylist1.get(0)).stationName);
            }
            goalSpnr.setVisibility(8);
            goalText.setVisibility(0);
        }
        arraylist2 = new ArrayList();
        arraylist3 = new ArrayList();
        arraylist4 = new ArrayList();
        for (int i = 0; i < results.viaStationList.size(); i++)
        {
            jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.NaviPointData navipointdata = (jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.NaviPointData)results.viaStationList.get(i);
            if (navipointdata.position == 0)
            {
                arraylist2.add(navipointdata);
            }
            if (navipointdata.position == 1)
            {
                arraylist3.add(navipointdata);
            }
            if (navipointdata.position == 2)
            {
                arraylist4.add(navipointdata);
            }
        }

        viaLayout = (LinearLayout)findViewById(0x7f0a00f5);
        viaSpnr = (Spinner)findViewById(0x7f0a00f6);
        viaText = (TextView)findViewById(0x7f0a00f7);
        viaspiner(arraylist2, viaLayout, viaSpnr, viaText);
        viaLayout2 = (LinearLayout)findViewById(0x7f0a00f8);
        viaSpnr2 = (Spinner)findViewById(0x7f0a00f9);
        viaText2 = (TextView)findViewById(0x7f0a00fa);
        viaspiner(arraylist3, viaLayout2, viaSpnr2, viaText2);
        viaLayout3 = (LinearLayout)findViewById(0x7f0a00fb);
        viaSpnr3 = (Spinner)findViewById(0x7f0a00fc);
        viaText3 = (TextView)findViewById(0x7f0a00fd);
        viaspiner(arraylist4, viaLayout3, viaSpnr3, viaText3);
        if (arraylist2.size() == 0)
        {
            viaLayout.setVisibility(8);
            return;
        }
        if (1 < arraylist2.size())
        {
            OtherCandidateAdapter othercandidateadapter2 = new OtherCandidateAdapter(this, 0x1090008, arraylist2);
            viaSpnr.setAdapter(othercandidateadapter2);
            viaSpnr.setVisibility(0);
            viaText.setVisibility(8);
            viaLayout.setVisibility(0);
            return;
        }
        if (1 == arraylist2.size())
        {
            viaText.setText(((jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.NaviPointData)arraylist2.get(0)).stationName);
        }
        viaSpnr.setVisibility(8);
        viaText.setVisibility(0);
        viaLayout.setVisibility(0);
    }

    private void viaspiner(ArrayList arraylist, LinearLayout linearlayout, Spinner spinner, TextView textview)
    {
        if (arraylist.size() == 0)
        {
            return;
        }
        if (1 < arraylist.size())
        {
            spinner.setAdapter(new OtherCandidateAdapter(this, 0x1090008, arraylist));
            spinner.setVisibility(0);
            textview.setVisibility(8);
            linearlayout.setVisibility(0);
            return;
        }
        if (1 == arraylist.size())
        {
            textview.setText(((jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.NaviPointData)arraylist.get(0)).stationName);
        }
        spinner.setVisibility(8);
        textview.setVisibility(0);
        linearlayout.setVisibility(0);
    }

    public void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setContentView(0x7f030024);
        setTitle(getString(0x7f0d02a5));
        returnIntent = new Intent();
        setResult(0, returnIntent);
        context = this;
        Intent intent = getIntent();
        conditionData = (ConditionData)intent.getSerializableExtra(getString(0x7f0d022c));
        results = (NaviSearchData)intent.getSerializableExtra(getString(0x7f0d0232));
        startSpnr = (Spinner)findViewById(0x7f0a00f0);
        startText = (TextView)findViewById(0x7f0a00f1);
        goalSpnr = (Spinner)findViewById(0x7f0a00f3);
        goalText = (TextView)findViewById(0x7f0a00f4);
        showCandidates();
        dispAd(this, "2080288757", "pv");
    }

    public void saveAndFinish(View view)
    {
        touchTapRD(getString(0x7f0d03cf));
        save();
        ConditionData conditiondata = conditionData;
        conditiondata.afterFinal = false;
        conditiondata.midnightBus = false;
        Intent intent = new Intent(context, jp/co/yahoo/android/apps/transit/Transit);
        intent.putExtra(getString(0x7f0d0181), getResources().getInteger(0x7f0c0020));
        intent.putExtra(getString(0x7f0d022c), conditiondata);
        ((TransitBaseActivity)context).startActivityInCurrentMenu(intent);
    }

    public void searchWithOtherCandidate(View view)
    {
        touchTapRD(getString(0x7f0d0411));
        save();
        NaviSearch navisearch = new NaviSearch(context, new jp.co.yahoo.android.apps.transit.api.ApiBase.ApiListener() {

            final OtherCandidatesActivity this$0;

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
                if (conditionData.mtf != -1)
                {
                    Intent intent = new Intent(OtherCandidatesActivity.this, jp/co/yahoo/android/apps/transit/SearchResultActivity);
                    intent.putExtra(getString(0x7f0d0231), -1 + conditionData.mtf);
                    intent.putExtra(getString(0x7f0d0232), navisearchdata);
                    intent.putExtra(getString(0x7f0d022c), conditionData);
                    startActivityForResult(intent, getResources().getInteger(0x7f0c0058));
                } else
                {
                    Intent intent1 = new Intent(OtherCandidatesActivity.this, jp/co/yahoo/android/apps/transit/SearchResultListActivity);
                    intent1.putExtra(getString(0x7f0d0232), navisearchdata);
                    intent1.putExtra(getString(0x7f0d022c), conditionData);
                    startActivityForResult(intent1, getResources().getInteger(0x7f0c0059));
                }
                return false;
            }

            
            {
                this$0 = OtherCandidatesActivity.this;
                super();
            }
        });
        navisearch.setCondition(conditionData);
        navisearch.exec();
    }

}

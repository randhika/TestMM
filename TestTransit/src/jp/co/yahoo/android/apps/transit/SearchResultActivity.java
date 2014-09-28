// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBar;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import jp.co.yahoo.android.apps.transit.api.ApiBase;
import jp.co.yahoo.android.apps.transit.api.NaviSearch;
import jp.co.yahoo.android.apps.transit.api.data.APIError;
import jp.co.yahoo.android.apps.transit.api.data.ConditionData;
import jp.co.yahoo.android.apps.transit.api.data.NaviSearchData;
import jp.co.yahoo.android.apps.transit.common.RequestRecommend;
import jp.co.yahoo.android.apps.transit.common.SaveCondition;
import jp.co.yahoo.android.apps.transit.common.StringUtil;
import jp.co.yahoo.android.apps.transit.common.util.TransitUtil;
import jp.co.yahoo.android.apps.transit.db.ResultDB;
import jp.co.yahoo.android.apps.transit.viewparts.SearchDetailCondition;
import jp.co.yahoo.android.apps.transit.viewparts.SearchResultFragmentView;

// Referenced classes of package jp.co.yahoo.android.apps.transit:
//            SearchResultBaseActivity, TransitBaseActivity, AlarmSet, RailmapActivity, 
//            SearchResultListActivity

public class SearchResultActivity extends SearchResultBaseActivity
    implements android.view.GestureDetector.OnGestureListener
{
    public static class MainTabListener
        implements android.support.v7.app.ActionBar.TabListener
    {

        private final SearchResultActivity activity;
        private final Class cls;
        private Fragment fragment;
        private final String tag;

        public void onTabReselected(android.support.v7.app.ActionBar.Tab tab, FragmentTransaction fragmenttransaction)
        {
        }

        public void onTabSelected(android.support.v7.app.ActionBar.Tab tab, FragmentTransaction fragmenttransaction)
        {
            int i = tab.getPosition();
            if (activity.bFirst && i == 0 && activity.result_id != 0)
            {
                activity.bFirst = false;
                return;
            }
            activity.bFirst = false;
            if (i > activity.result_id)
            {
                if (activity.result_id == 0 && i > 1)
                {
                    activity.bNext = false;
                } else
                {
                    activity.bNext = true;
                }
            } else
            if (i == 0 && activity.result_id > 1)
            {
                activity.bNext = true;
            } else
            {
                activity.bNext = false;
            }
            fragmenttransaction.setTransition(4097);
            if (fragment == null)
            {
                fragment = Fragment.instantiate(activity, cls.getName());
                Bundle bundle = new Bundle();
                bundle.putInt("section_number", tab.getPosition());
                fragment.setArguments(bundle);
                fragmenttransaction.add(0x1020002, fragment, tag);
                return;
            } else
            {
                fragmenttransaction.attach(fragment);
                return;
            }
        }

        public void onTabUnselected(android.support.v7.app.ActionBar.Tab tab, FragmentTransaction fragmenttransaction)
        {
            if (fragment != null)
            {
                fragmenttransaction.detach(fragment);
            }
        }

        public MainTabListener(SearchResultActivity searchresultactivity, String s, Class class1)
        {
            activity = searchresultactivity;
            tag = s;
            cls = class1;
        }
    }

    public static class SearchResultFragment extends Fragment
    {

        public static final String ARG_SECTION_NUMBER = "section_number";
        private SearchResultActivity activity;
        public LinearLayout convertView;

        private void setSearchConditions()
        {
            SearchDetailCondition searchdetailcondition = new SearchDetailCondition(activity.context);
            searchdetailcondition.setCondition(activity.conditionData, activity.context.getResources(), activity.context);
            LinearLayout linearlayout = (LinearLayout)convertView.findViewById(0x7f0a0177);
            linearlayout.removeAllViews();
            linearlayout.addView(searchdetailcondition);
        }

        public void onAttach(Activity activity1)
        {
            if (activity1 instanceof SearchResultActivity)
            {
                activity = (SearchResultActivity)activity1;
            }
            super.onAttach(activity1);
        }

        public Animation onCreateAnimation(int i, boolean flag, int j)
        {
            if (i == 4097)
            {
                if (flag)
                {
                    if (activity.bNext)
                    {
                        return AnimationUtils.loadAnimation(getActivity(), 0x7f040012);
                    } else
                    {
                        return AnimationUtils.loadAnimation(getActivity(), 0x7f040010);
                    }
                }
                if (activity.bNext)
                {
                    return AnimationUtils.loadAnimation(getActivity(), 0x7f040015);
                } else
                {
                    return AnimationUtils.loadAnimation(getActivity(), 0x7f040017);
                }
            } else
            {
                return super.onCreateAnimation(i, flag, j);
            }
        }

        public View onCreateView(LayoutInflater layoutinflater, ViewGroup viewgroup, Bundle bundle)
        {
            Bundle bundle1 = getArguments();
            LayoutInflater layoutinflater1 = LayoutInflater.from(activity.context);
            activity.result_id = bundle1.getInt("section_number");
            if (!activity.bFromMemo)
            {
                activity.conditionData.mtf = 1 + activity.result_id;
            }
            ((TransitBaseActivity)activity.context).dispAd(activity.context, "2080078813", "pv");
            convertView = (LinearLayout)layoutinflater1.inflate(0x7f030098, null);
            activity.objSearchView = new SearchResultFragmentView(activity.context);
            activity.objSearchView.setRouteID(bundle1.getInt("section_number"));
            activity.objSearchView.setRoute(activity.results);
            activity.objSearchView.setView(convertView);
            activity.objSearchView.setPoint(activity.points);
            activity.objSearchView.setCond(activity.conditionData);
            activity.objSearchView.createSearchResultView();
            setSearchConditions();
            activity.gestureDetector = new GestureDetector(activity.context, (android.view.GestureDetector.OnGestureListener)activity.context);
            ((ScrollView)convertView.findViewById(0x7f0a00fe)).setOnTouchListener(new android.view.View.OnTouchListener() {

                final SearchResultFragment this$0;

                public boolean onTouch(View view, MotionEvent motionevent)
                {
                    activity.scrolling = false;
                    return activity.gestureDetector.onTouchEvent(motionevent);
                }

            
            {
                this$0 = SearchResultFragment.this;
                super();
            }
            });
            return activity.objSearchView.getRouteView();
        }

        public void onPause()
        {
            super.onPause();
            if (activity.objSearchView != null)
            {
                activity.objSearchView.pauseImakokoUpdate();
            }
        }

        public void onResume()
        {
            super.onResume();
            if (activity.objSearchView != null)
            {
                activity.objSearchView.setImakokoUpdate(activity.isImakoko);
            }
        }


        public SearchResultFragment()
        {
        }
    }


    private ActionBar actionBar;
    private boolean bFirst;
    private boolean bNext;
    private Context context;
    private GestureDetector gestureDetector;
    private SearchResultFragmentView objSearchView;
    private HashMap points;
    public int routeSize;
    private boolean scrolling;

    public SearchResultActivity()
    {
        scrolling = false;
        bFirst = true;
        bNext = true;
    }

    private void addtabs(int i)
    {
        SearchResultFragment searchresultfragment = new SearchResultFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("section_number", i);
        searchresultfragment.setArguments(bundle);
        actionBar.addTab(actionBar.newTab().setText((new StringBuilder()).append("\u30EB\u30FC\u30C8").append(i + 1).toString()).setTabListener(new MainTabListener(this, "f2", searchresultfragment.getClass())));
    }

    protected void onActivityResult(int i, int j, Intent intent)
    {
        super.onActivityResult(i, j, intent);
    }

    public void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setContentView(0x7f030022);
        bFirst = true;
        context = this;
        routeSize = results.routes.size();
        points = results.points;
        actionBar = getSupportActionBar();
        actionBar.setNavigationMode(2);
        for (int i = 0; i < routeSize; i++)
        {
            addtabs(i);
        }

        actionBar.getTabAt(result_id).select();
        if (getIntent().getBooleanExtra(getString(0x7f0d01bf), false))
        {
            (new RequestRecommend(context)).request();
        }
    }

    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuItemCompat.setShowAsAction(menu.add(0, 0, 0, getString(0x7f0d0298)).setIcon(0x7f0200e6), 1);
        MenuItemCompat.setShowAsAction(menu.add(0, 1, 1, getString(0x7f0d0299)).setIcon(0x7f020112), 1);
        return true;
    }

    public boolean onDown(MotionEvent motionevent)
    {
        return false;
    }

    public boolean onFling(MotionEvent motionevent, MotionEvent motionevent1, float f, float f1)
    {
        if (1 >= featureCount)
        {
            break MISSING_BLOCK_LABEL_92;
        }
        float f3;
        float f2 = motionevent1.getY() - motionevent.getY();
        f3 = motionevent1.getX() - motionevent.getX();
        if (Math.abs(f3) <= Math.abs(f2) || Math.abs(f3) <= (float)100 || Math.abs(f) <= (float)100)
        {
            break MISSING_BLOCK_LABEL_92;
        }
        if (f3 > 0.0F)
        {
            try
            {
                showRoutePrev(null);
            }
            catch (Exception exception) { }
            break MISSING_BLOCK_LABEL_92;
        }
        showRouteNext(null);
        return false;
    }

    public void onLongPress(MotionEvent motionevent)
    {
    }

    public boolean onOptionsItemSelected(MenuItem menuitem)
    {
        super.onOptionsItemSelected(menuitem);
        menuitem.getItemId();
        JVM INSTR tableswitch 0 1: default 36
    //                   0 38
    //                   1 45;
           goto _L1 _L2 _L3
_L1:
        return true;
_L2:
        saveResults();
        continue; /* Loop/switch isn't completed */
_L3:
        showShareMenu();
        if (true) goto _L1; else goto _L4
_L4:
    }

    protected void onRestoreInstanceState(Bundle bundle)
    {
        super.onRestoreInstanceState(bundle);
        int i = bundle.getInt(getString(0x7f0d0231), -1);
        if (i != -1 && actionBar != null)
        {
            result_id = i;
            actionBar.setSelectedNavigationItem(i);
        }
    }

    protected void onSaveInstanceState(Bundle bundle)
    {
        super.onSaveInstanceState(bundle);
        bundle.putInt(getString(0x7f0d0231), result_id);
    }

    public boolean onScroll(MotionEvent motionevent, MotionEvent motionevent1, float f, float f1)
    {
        scrolling = true;
        return false;
    }

    public void onShowPress(MotionEvent motionevent)
    {
    }

    public boolean onSingleTapUp(MotionEvent motionevent)
    {
        return false;
    }

    public void registMyroute(View view)
    {
        touchTapRD(context.getString(0x7f0d03fa));
        registMyroute(new ResultDB(this), conditionData);
    }

    public void researchByCurrentTime(View view)
    {
        ((TransitBaseActivity)context).touchTapRD(getString(0x7f0d0411));
        final ConditionData conditionData = (ConditionData)this.conditionData.clone();
        conditionData.afterFinal = false;
        Calendar calendar = Calendar.getInstance();
        conditionData.year = calendar.get(1);
        conditionData.month = 1 + calendar.get(2);
        conditionData.day = calendar.get(5);
        conditionData.hour = calendar.get(11);
        conditionData.minite = calendar.get(12);
        conditionData.type = res.getInteger(0x7f0c006b);
        conditionData.resultId = -1;
        conditionData.mtf = -1;
        if (conditionData.ticket == null)
        {
            ConditionData conditiondata = (new SaveCondition(context)).getCond();
            if (conditiondata != null)
            {
                conditionData.ticket = conditiondata.ticket;
            }
        }
        NaviSearch navisearch = new NaviSearch(context, new jp.co.yahoo.android.apps.transit.api.ApiBase.ApiListener() {

            final SearchResultActivity this$0;
            final ConditionData val$conditionData;

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
                Intent intent = new Intent(context, jp/co/yahoo/android/apps/transit/SearchResultListActivity);
                intent.putExtra(getString(0x7f0d0232), navisearchdata);
                intent.putExtra(getString(0x7f0d022c), conditionData);
                startActivityForResult(intent, getResources().getInteger(0x7f0c0059));
                return false;
            }

            
            {
                this$0 = SearchResultActivity.this;
                conditionData = conditiondata;
                super();
            }
        });
        navisearch.setCondition(conditionData);
        navisearch.exec();
    }

    public void researchFromNear(View view)
    {
        touchRD((new StringBuilder()).append(getString(0x7f0d0560)).append(getString(0x7f0d0446)).append("/").append(getString(0x7f0d0449)).append("/").append(getString(0x7f0d0411)).append("/").append(getString(0x7f0d0561)).toString());
        researchFromNear();
    }

    public void sendCal(View view)
    {
        sendCalender();
    }

    public void setMemo(View view)
    {
        saveResults();
    }

    public void setSearchInfo(View view)
    {
        ((TransitBaseActivity)context).touchTapRD(getString(0x7f0d03e0));
        try
        {
            TransitUtil.openURL(this, (new StringBuilder()).append(getString(0x7f0d056e)).append("?url=").append(URLEncoder.encode(results.url, "UTF-8")).append("&from=").append(URLEncoder.encode(conditionData.startName, "UTF-8")).append("&to=").append(URLEncoder.encode(conditionData.goalName, "UTF-8")).append("&no=").append(1 + result_id).append("&viewType=4").toString());
            return;
        }
        catch (UnsupportedEncodingException unsupportedencodingexception)
        {
            return;
        }
    }

    protected void showAlarm()
    {
        ((TransitBaseActivity)context).touchTapRD(context.getString(0x7f0d03c4));
        if (TransitUtil.timeStringToCalendar(((jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.NaviRouteData)results.routes.get(result_id)).goalTime).before(Calendar.getInstance()))
        {
            ((TransitBaseActivity)context).showSimpleErrorMessageDialog(context.getString(0x7f0d0158));
            return;
        } else
        {
            Intent intent = new Intent(context, jp/co/yahoo/android/apps/transit/AlarmSet);
            NaviSearchData navisearchdata = ((SearchResultBaseActivity)context).getSaveData(result_id);
            intent.putExtra(context.getString(0x7f0d0232), navisearchdata);
            intent.putExtra(context.getString(0x7f0d022c), conditionData);
            ((TransitBaseActivity)context).startActivityForResult(intent, getResources().getInteger(0x7f0c0039));
            return;
        }
    }

    public void showAlarm(View view)
    {
        showAlarm();
    }

    public void showMap(View view)
    {
        touchTapRD(context.getString(0x7f0d0433));
        Intent intent = new Intent(context, jp/co/yahoo/android/apps/transit/RailmapActivity);
        ConditionData conditiondata = (ConditionData)conditionData.clone();
        conditiondata.resultId = objSearchView.result_id;
        intent.putExtra(context.getString(0x7f0d022c), conditiondata);
        intent.putExtra(getString(0x7f0d023c), objSearchView.route.startTime);
        intent.putExtra(getString(0x7f0d01c2), objSearchView.route.goalTime);
        startActivityForResult(intent, getResources().getInteger(0x7f0c0057));
    }

    public void showRouteNext(View view)
    {
        int i = 1 + result_id;
        if (featureCount <= i)
        {
            i -= featureCount;
        }
        actionBar.setSelectedNavigationItem(i);
    }

    public void showRoutePrev(View view)
    {
        int i = -1 + result_id;
        if (i < 0)
        {
            i += featureCount;
        }
        actionBar.setSelectedNavigationItem(i);
    }

    public void showShareMenu(View view)
    {
        showShareMenu();
    }




/*
    static SearchResultFragmentView access$102(SearchResultActivity searchresultactivity, SearchResultFragmentView searchresultfragmentview)
    {
        searchresultactivity.objSearchView = searchresultfragmentview;
        return searchresultfragmentview;
    }

*/




/*
    static GestureDetector access$302(SearchResultActivity searchresultactivity, GestureDetector gesturedetector)
    {
        searchresultactivity.gestureDetector = gesturedetector;
        return gesturedetector;
    }

*/


/*
    static boolean access$502(SearchResultActivity searchresultactivity, boolean flag)
    {
        searchresultactivity.scrolling = flag;
        return flag;
    }

*/



/*
    static boolean access$602(SearchResultActivity searchresultactivity, boolean flag)
    {
        searchresultactivity.bNext = flag;
        return flag;
    }

*/



/*
    static boolean access$702(SearchResultActivity searchresultactivity, boolean flag)
    {
        searchresultactivity.bFirst = flag;
        return flag;
    }

*/
}

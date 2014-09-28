// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.text.SpannableString;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import jp.co.yahoo.android.apps.transit.api.data.ConditionData;
import jp.co.yahoo.android.apps.transit.api.data.NaviSearchData;
import jp.co.yahoo.android.apps.transit.api.data.TimeTableData;
import jp.co.yahoo.android.apps.transit.common.AlignImageSpan;
import jp.co.yahoo.android.apps.transit.common.FixedHeaderScrollView;
import jp.co.yahoo.android.apps.transit.common.SaveCondition;
import jp.co.yahoo.android.apps.transit.common.SetSharedPreferences;
import jp.co.yahoo.android.apps.transit.common.util.TransitUtil;
import jp.co.yahoo.android.apps.transit.db.ResultDB;
import jp.co.yahoo.android.apps.transit.viewparts.MemoListView;
import jp.co.yahoo.android.apps.transit.viewparts.TransitDialogBuilder;

// Referenced classes of package jp.co.yahoo.android.apps.transit:
//            TransitBaseActivity, Transit, SearchResultListActivity, SearchResultActivity, 
//            TimeTableActivity, OthersAdressSearchActivity

public class MyPageActivity extends TransitBaseActivity
{

    public static final int MEMO_HISTORY = 2;
    public static final int MEMO_SEARCH = 0;
    public static final int MEMO_TIMETABLE = 1;
    public static final int MY_ROUTE = 257;
    public static final int TYPE_EDIT = 1;
    public static final int TYPE_NORMAL;
    private final String FORMAT_SEARCH_START_DATE = "M\u6708d\u65E5(E) HH:mm";
    private final String FORMAT_SEARCH_START_DAY = "M\u6708d\u65E5(E)";
    private final String FORMAT_TIMETABLE_SAVE_DATE = "%d\u5E74%d\u6708%d\u65E5 %s:%s \u4FDD\u5B58";
    private android.view.animation.Animation.AnimationListener animListenerMemoIn;
    private android.view.animation.Animation.AnimationListener animListenerMyrouteIn;
    private jp.co.yahoo.android.apps.transit.common.FixedHeaderScrollView.BaseScrollView baseScrollView;
    private ImageView btnChangeType;
    private android.view.View.OnClickListener changeRouteListener;
    private ConditionData conditionData;
    private LinearLayout deleteBtnLayout;
    private android.view.View.OnClickListener deleteListener;
    private int dispType;
    private boolean enableHome;
    private boolean enableOffice;
    private boolean enableOther;
    private FixedHeaderScrollView fixedScrollView;
    private jp.co.yahoo.android.apps.transit.common.FixedHeaderScrollView.HeaderLinearLayout headerLayoutMemo;
    private jp.co.yahoo.android.apps.transit.common.FixedHeaderScrollView.HeaderLinearLayout headerLayoutMyroute;
    private jp.co.yahoo.android.apps.transit.viewparts.MemoListView.OnItemClickChangeListener historyListener;
    private String latestSearchMemoId;
    private String latestTimetableMemoId;
    private LinearLayout listFavorite;
    private LinearLayout listHistory;
    private LinearLayout listMoney;
    private LinearLayout listMyRoute;
    private LinearLayout listNormal;
    private LinearLayout listSearch;
    private LinearLayout listTimetable;
    private int listType;
    private View memoLayout;
    private int minHeightMemo;
    private int minHeightMyroute;
    private View myrouteLayout;
    private jp.co.yahoo.android.apps.transit.viewparts.MemoListView.OnItemClickChangeListener myrouteListener;
    private boolean needSaveType;
    private int normalScrollY;
    private ImageView oneTapHome;
    private ImageView oneTapOffice;
    private ImageView oneTapOther;
    private android.view.View.OnClickListener registMyrouteListener;
    private int reqCode;
    private jp.co.yahoo.android.apps.transit.viewparts.MemoListView.OnItemClickChangeListener searchMemoListener;
    private int selectTab;
    private ResultDB sql;
    private TabHost tabsHeader;
    private TabHost tabsScroll;
    private TextView textEditMemo;
    private TextView textHeaderMyroute;
    private TextView textMyrouteDesc;
    private TextView textNoFavorite;
    private TextView textNoMemo;
    private TextView textNoMoney;
    private TextView textNumHistory;
    private TextView textNumHistoryHeader;
    private TextView textNumSearch;
    private TextView textNumSearchHeader;
    private TextView textNumTimetable;
    private TextView textNumTimetableHeader;
    private TextView textScrollMyroute;
    private jp.co.yahoo.android.apps.transit.viewparts.MemoListView.OnItemClickChangeListener timetableListener;
    private LinearLayout topBtnLayout;
    private MemoListView viewFavorite;
    private MemoListView viewHistory;
    private MemoListView viewMoney;
    private MemoListView viewMyRoute;
    private MemoListView viewNormal;
    private MemoListView viewTimetable;

    public MyPageActivity()
    {
        reqCode = 0;
        selectTab = -1;
        listType = -1;
        dispType = -1;
        normalScrollY = -1;
        needSaveType = true;
        latestSearchMemoId = "";
        latestTimetableMemoId = "";
        searchMemoListener = new jp.co.yahoo.android.apps.transit.viewparts.MemoListView.OnItemClickChangeListener() {

            final MyPageActivity this$0;

            public void onClicked(Bundle bundle)
            {
                if (dispType == 0)
                {
                    touchTapRD((new StringBuilder()).append(getString(0x7f0d0441)).append("/").append(getString(0x7f0d0438)).toString());
                    launchSearchMemo(bundle);
                }
            }

            public void onLongClicked(Bundle bundle)
            {
                if (dispType == 1)
                {
                    showCategorySelectDialog(bundle);
                }
            }

            
            {
                this$0 = MyPageActivity.this;
                super();
            }
        };
        historyListener = new jp.co.yahoo.android.apps.transit.viewparts.MemoListView.OnItemClickChangeListener() {

            final MyPageActivity this$0;

            public void onClicked(Bundle bundle)
            {
                touchTapRD((new StringBuilder()).append(getString(0x7f0d0440)).append("/").append(getString(0x7f0d0438)).toString());
                String s = bundle.getString("id");
                launchSearchHistory(sql.getSearchResultHistory(s));
            }

            public void onLongClicked(Bundle bundle)
            {
            }

            
            {
                this$0 = MyPageActivity.this;
                super();
            }
        };
        registMyrouteListener = new android.view.View.OnClickListener() {

            final MyPageActivity this$0;

            public void onClick(View view)
            {
                touchTapRD((new StringBuilder()).append(getString(0x7f0d0440)).append("/").append(getString(0x7f0d03f9)).toString());
                LinearLayout linearlayout = (LinearLayout)view.getParent();
                int i = sql.countMyroute();
                ConditionData conditiondata = (ConditionData)((Bundle)linearlayout.getTag()).getSerializable(getString(0x7f0d022c));
                registMyroute(sql, conditiondata);
                if (i == 0 && sql.countMyroute() > 0)
                {
                    btnChangeType.setImageResource(0x7f0200f4);
                    btnChangeType.setContentDescription(getString(0x7f0d0356));
                }
            }

            
            {
                this$0 = MyPageActivity.this;
                super();
            }
        };
        timetableListener = new jp.co.yahoo.android.apps.transit.viewparts.MemoListView.OnItemClickChangeListener() {

            final MyPageActivity this$0;

            public void onClicked(Bundle bundle)
            {
                touchTapRD((new StringBuilder()).append(getString(0x7f0d0444)).append("/").append(getString(0x7f0d0438)).toString());
                launchTimetable(bundle);
            }

            public void onLongClicked(Bundle bundle)
            {
            }

            
            {
                this$0 = MyPageActivity.this;
                super();
            }
        };
        myrouteListener = new jp.co.yahoo.android.apps.transit.viewparts.MemoListView.OnItemClickChangeListener() {

            final MyPageActivity this$0;

            public void onClicked(Bundle bundle)
            {
                touchTapRD((new StringBuilder()).append(getString(0x7f0d0442)).append("/").append(getString(0x7f0d0438)).toString());
                launchMyrouteSearchResult(bundle);
            }

            public void onLongClicked(Bundle bundle)
            {
                touchTapRD((new StringBuilder()).append(getString(0x7f0d0442)).append("/").append(getString(0x7f0d0439)).toString());
                launchMyrouteTop(bundle);
            }

            
            {
                this$0 = MyPageActivity.this;
                super();
            }
        };
        changeRouteListener = new android.view.View.OnClickListener() {

            final MyPageActivity this$0;

            public void onClick(View view)
            {
                touchTapRD((new StringBuilder()).append(getString(0x7f0d0442)).append("/").append(getString(0x7f0d03db)).toString());
                LinearLayout linearlayout = (LinearLayout)view.getParent();
                Bundle bundle = (Bundle)linearlayout.getTag();
                String s = bundle.getString("id");
                ConditionData conditiondata = (ConditionData)bundle.getSerializable(getString(0x7f0d022c));
                String s1 = conditiondata.startName;
                String s2 = conditiondata.startLon;
                String s3 = conditiondata.startLat;
                String s4 = conditiondata.startCode;
                conditiondata.startName = conditiondata.goalName;
                conditiondata.startLon = conditiondata.goalLon;
                conditiondata.startLat = conditiondata.goalLat;
                conditiondata.startCode = conditiondata.goalCode;
                conditiondata.goalName = s1;
                conditiondata.goalLon = s2;
                conditiondata.goalLat = s3;
                conditiondata.goalCode = s4;
                if (conditiondata.viaName != null && conditiondata.viaName.size() > 0)
                {
                    Collections.reverse(conditiondata.viaName);
                    Collections.reverse(conditiondata.viaCode);
                }
                sql.updateMyrouteQuery(s, conditiondata);
                setMyrouteItemText(conditiondata, linearlayout);
            }

            
            {
                this$0 = MyPageActivity.this;
                super();
            }
        };
        animListenerMemoIn = new android.view.animation.Animation.AnimationListener() {

            final MyPageActivity this$0;

            public void onAnimationEnd(Animation animation)
            {
                fixedScrollView.setHeaderView(tabsScroll, headerLayoutMemo);
                myrouteLayout.setVisibility(8);
            }

            public void onAnimationRepeat(Animation animation)
            {
            }

            public void onAnimationStart(Animation animation)
            {
                memoLayout.setVisibility(0);
            }

            
            {
                this$0 = MyPageActivity.this;
                super();
            }
        };
        animListenerMyrouteIn = new android.view.animation.Animation.AnimationListener() {

            final MyPageActivity this$0;

            public void onAnimationEnd(Animation animation)
            {
                fixedScrollView.setHeaderView(textScrollMyroute, headerLayoutMyroute);
                memoLayout.setVisibility(8);
            }

            public void onAnimationRepeat(Animation animation)
            {
            }

            public void onAnimationStart(Animation animation)
            {
                myrouteLayout.setVisibility(0);
            }

            
            {
                this$0 = MyPageActivity.this;
                super();
            }
        };
        deleteListener = new android.view.View.OnClickListener() ;
    }

    private LinearLayout createMyrouteItem(Bundle bundle)
    {
        LinearLayout linearlayout = (LinearLayout)getLayoutInflater().inflate(0x7f03006d, null);
        setMyrouteItemText((ConditionData)bundle.getSerializable(getString(0x7f0d022c)), linearlayout);
        ((ImageView)linearlayout.findViewById(0x7f0a021c)).setOnClickListener(changeRouteListener);
        return linearlayout;
    }

    private LinearLayout createSearchHistoryItem(Bundle bundle)
    {
        LinearLayout linearlayout;
        TextView textview;
        TextView textview1;
        linearlayout = (LinearLayout)getLayoutInflater().inflate(0x7f03006b, null);
        textview = (TextView)linearlayout.findViewById(0x7f0a020f);
        textview1 = (TextView)linearlayout.findViewById(0x7f0a021b);
        ConditionData conditiondata = (ConditionData)bundle.getSerializable(getString(0x7f0d022c));
        if (conditiondata.type != getResources().getInteger(0x7f0c006a)) goto _L2; else goto _L1
_L1:
        getString(0x7f0d048b);
_L5:
        textview.setText((new StringBuilder()).append(conditiondata.startName).append("\u2192").append(conditiondata.goalName).toString());
_L6:
        ((ImageView)linearlayout.findViewById(0x7f0a021c)).setOnClickListener(registMyrouteListener);
        return linearlayout;
_L2:
        Calendar calendar;
        calendar = Calendar.getInstance();
        calendar.set(conditiondata.year, -1 + conditiondata.month, conditiondata.day, conditiondata.hour, conditiondata.minite);
        if (conditiondata.type != getResources().getInteger(0x7f0c0070)) goto _L4; else goto _L3
_L3:
        String s;
        SimpleDateFormat simpledateformat = new SimpleDateFormat("M\u6708d\u65E5(E) HH:mm", Locale.JAPANESE);
        s = (new StringBuilder()).append(simpledateformat.format(calendar.getTime())).append(getString(0x7f0d007d)).toString();
_L7:
        textview1.setText(s);
          goto _L5
        Exception exception;
        exception;
        textview.setText("-");
        textview1.setText("-");
          goto _L6
_L4:
label0:
        {
            if (conditiondata.type != getResources().getInteger(0x7f0c006e))
            {
                break label0;
            }
            SimpleDateFormat simpledateformat1 = new SimpleDateFormat("M\u6708d\u65E5(E) HH:mm", Locale.JAPANESE);
            s = (new StringBuilder()).append(simpledateformat1.format(calendar.getTime())).append(getString(0x7f0d007b)).toString();
        }
          goto _L7
label1:
        {
            if (conditiondata.type != getResources().getInteger(0x7f0c006d))
            {
                break label1;
            }
            SimpleDateFormat simpledateformat2 = new SimpleDateFormat("M\u6708d\u65E5(E)", Locale.JAPANESE);
            s = (new StringBuilder()).append(simpledateformat2.format(calendar.getTime())).append(getString(0x7f0d007a)).toString();
        }
          goto _L7
label2:
        {
            if (conditiondata.type != getResources().getInteger(0x7f0c006f))
            {
                break label2;
            }
            SimpleDateFormat simpledateformat3 = new SimpleDateFormat("M\u6708d\u65E5(E)", Locale.JAPANESE);
            s = (new StringBuilder()).append(simpledateformat3.format(calendar.getTime())).append(getString(0x7f0d007c)).toString();
        }
          goto _L7
        String s1;
        SimpleDateFormat simpledateformat4 = new SimpleDateFormat("M\u6708d\u65E5(E) HH:mm", Locale.JAPANESE);
        s1 = (new StringBuilder()).append(simpledateformat4.format(calendar.getTime())).append(getString(0x7f0d007d)).toString();
        s = s1;
          goto _L7
    }

    private LinearLayout createSearchMemoItem(Bundle bundle, int i)
    {
        LinearLayout linearlayout;
        TextView textview;
        TextView textview1;
        ImageView imageview;
        TextView textview2;
        TextView textview3;
        linearlayout = (LinearLayout)getLayoutInflater().inflate(0x7f03006a, null);
        textview = (TextView)linearlayout.findViewById(0x7f0a020f);
        textview1 = (TextView)linearlayout.findViewById(0x7f0a0218);
        imageview = (ImageView)linearlayout.findViewById(0x7f0a0219);
        textview2 = (TextView)linearlayout.findViewById(0x7f0a021a);
        textview3 = (TextView)linearlayout.findViewById(0x7f0a021b);
        ConditionData conditiondata;
        jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.NaviRouteData naviroutedata;
        conditiondata = (ConditionData)bundle.getSerializable(getString(0x7f0d022c));
        naviroutedata = (jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.NaviRouteData)((NaviSearchData)bundle.getSerializable(getString(0x7f0d0232))).routes.get(0);
        if (conditiondata.type != getResources().getInteger(0x7f0c006a)) goto _L2; else goto _L1
_L1:
        String s;
        s = getString(0x7f0d048b);
        imageview.setVisibility(8);
        textview2.setVisibility(8);
_L9:
        String s1;
        textview1.setText(s);
        s1 = (new StringBuilder()).append(conditiondata.startName).append("\u2192").append(conditiondata.goalName).toString();
        if (i != 0) goto _L4; else goto _L3
_L3:
        String s2 = bundle.getString("id");
        if (TextUtils.isEmpty(s2) || !s2.equals(latestSearchMemoId)) goto _L6; else goto _L5
_L5:
        SpannableString spannablestring = new SpannableString((new StringBuilder()).append(s1).append("  ").toString());
        spannablestring.setSpan(new AlignImageSpan(this, 0x7f0201ca), -1 + spannablestring.length(), spannablestring.length(), 33);
        textview.setText(spannablestring);
_L10:
        if (conditiondata.ticket != null) goto _L8; else goto _L7
_L7:
        String s3 = getString(0x7f0d02a3);
_L11:
        textview3.setText(getString(0x7f0d0499, new Object[] {
            s3, naviroutedata.totalPrice, String.valueOf((double)naviroutedata.totaldistance / 10D)
        }));
        return linearlayout;
_L2:
        try
        {
            Calendar calendar = TransitUtil.timeStringToCalendar(naviroutedata.startTime);
            s = (new SimpleDateFormat("M\u6708d\u65E5(E) HH:mm", Locale.JAPANESE)).format(calendar.getTime());
            textview2.setText((new StringBuilder()).append(naviroutedata.goalTime.substring(8, 10)).append(":").append(naviroutedata.goalTime.substring(10, 12)).toString());
        }
        catch (Exception exception)
        {
            textview.setText("-");
            textview1.setText("-");
            imageview.setVisibility(8);
            textview2.setVisibility(8);
            if (i == 0)
            {
                textview3.setText("-");
                return linearlayout;
            }
            break MISSING_BLOCK_LABEL_563;
        }
          goto _L9
_L6:
        textview.setText(s1);
          goto _L10
_L8:
label0:
        {
            if (!conditiondata.ticket.equals(getString(0x7f0d0582)))
            {
                break label0;
            }
            s3 = getString(0x7f0d02e9);
        }
          goto _L11
        s3 = getString(0x7f0d02ea);
          goto _L11
_L4:
        textview.setText(s1);
        textview3.setVisibility(8);
        return linearlayout;
        textview3.setVisibility(8);
        return linearlayout;
          goto _L9
    }

    private void dispAd()
    {
        if (dispType != 0) goto _L2; else goto _L1
_L1:
        String s;
        int i;
        i = listType;
        s = null;
        i;
        JVM INSTR lookupswitch 4: default 56
    //                   0: 70
    //                   1: 77
    //                   2: 84
    //                   257: 91;
           goto _L3 _L4 _L5 _L6 _L7
_L3:
        if (s != null)
        {
            dispAd(((android.content.Context) (this)), s, "pv");
        }
        return;
_L4:
        s = "2080302597";
        continue; /* Loop/switch isn't completed */
_L5:
        s = "2080302605";
        continue; /* Loop/switch isn't completed */
_L6:
        s = "2080306475";
        continue; /* Loop/switch isn't completed */
_L7:
        s = "2080302600";
        if (true) goto _L3; else goto _L2
_L2:
        switch (listType)
        {
        default:
            s = null;
            break;

        case 0: // '\0'
            s = "2080302598";
            break;

        case 1: // '\001'
            s = "2080302606";
            break;

        case 2: // '\002'
            s = "2080306476";
            break;

        case 257: 
            s = "2080302602";
            break;
        }
        if (true) goto _L3; else goto _L8
_L8:
    }

    private String getMemoName()
    {
        switch (listType)
        {
        default:
            return "";

        case 0: // '\0'
            return getString(0x7f0d049d);

        case 1: // '\001'
            return getString(0x7f0d049e);

        case 2: // '\002'
            return getString(0x7f0d0497);

        case 257: 
            return getString(0x7f0d0360);
        }
    }

    private void initMemoView()
    {
        memoLayout.setVisibility(4);
        LayoutInflater layoutinflater = getLayoutInflater();
        int i = sql.countSearchResultsMemo();
        int j = sql.countTimetable();
        int k = sql.countSearchResultsHistory();
        tabsScroll = (TabHost)memoLayout.findViewById(0x7f0a00da);
        tabsScroll.setup();
        View view = layoutinflater.inflate(0x7f03008b, null);
        view.setBackgroundResource(0x7f020273);
        ((TextView)view.findViewById(0x7f0a029f)).setText(0x7f0d049d);
        textNumSearch = (TextView)view.findViewById(0x7f0a02a0);
        TextView textview = textNumSearch;
        Object aobj[] = new Object[2];
        aobj[0] = Integer.valueOf(i);
        aobj[1] = sql.getMaxSearchResultsMemo();
        textview.setText(getString(0x7f0d0357, aobj));
        view.setFocusable(true);
        View view1 = layoutinflater.inflate(0x7f03008b, null);
        ((TextView)view1.findViewById(0x7f0a029f)).setText(0x7f0d049e);
        textNumTimetable = (TextView)view1.findViewById(0x7f0a02a0);
        TextView textview1 = textNumTimetable;
        Object aobj1[] = new Object[2];
        aobj1[0] = Integer.valueOf(j);
        aobj1[1] = sql.getMaxTimetable();
        textview1.setText(getString(0x7f0d0357, aobj1));
        view1.setFocusable(true);
        View view2 = layoutinflater.inflate(0x7f03008b, null);
        ((TextView)view2.findViewById(0x7f0a029f)).setText(0x7f0d0497);
        textNumHistory = (TextView)view2.findViewById(0x7f0a02a0);
        TextView textview2 = textNumHistory;
        Object aobj2[] = new Object[2];
        aobj2[0] = Integer.valueOf(k);
        aobj2[1] = sql.getMaxSearchResultsHistory();
        textview2.setText(getString(0x7f0d0357, aobj2));
        view2.setFocusable(true);
        android.widget.TabHost.TabSpec tabspec = tabsScroll.newTabSpec(String.valueOf(0)).setIndicator(view).setContent(0x7f0a029c);
        tabsScroll.addTab(tabspec);
        android.widget.TabHost.TabSpec tabspec1 = tabsScroll.newTabSpec(String.valueOf(1)).setIndicator(view1).setContent(0x7f0a029d);
        tabsScroll.addTab(tabspec1);
        android.widget.TabHost.TabSpec tabspec2 = tabsScroll.newTabSpec(String.valueOf(2)).setIndicator(view2).setContent(0x7f0a029e);
        tabsScroll.addTab(tabspec2);
        TabHost tabhost = tabsScroll;
        android.widget.TabHost.OnTabChangeListener ontabchangelistener = new android.widget.TabHost.OnTabChangeListener() {

            final MyPageActivity this$0;

            public void onTabChanged(String s)
            {
                int l = tabsScroll.getCurrentTab();
                if (headerLayoutMemo.getVisibility() == 8 && l != tabsHeader.getCurrentTab())
                {
                    tabsHeader.setCurrentTab(l);
                }
                if (s.equals(String.valueOf(0)))
                {
                    touchTapRD((new StringBuilder()).append(getString(0x7f0d0441)).append("/").append(getString(0x7f0d041e)).toString());
                } else
                if (s.equals(String.valueOf(1)))
                {
                    touchTapRD((new StringBuilder()).append(getString(0x7f0d0444)).append("/").append(getString(0x7f0d041e)).toString());
                } else
                if (s.equals(String.valueOf(2)))
                {
                    touchTapRD((new StringBuilder()).append(getString(0x7f0d0440)).append("/").append(getString(0x7f0d041e)).toString());
                }
                if (needSaveType)
                {
                    android.content.SharedPreferences.Editor editor = getSharedPreferences(getString(0x7f0d04e1), 0).edit();
                    editor.putInt(getString(0x7f0d03a2), Integer.valueOf(s).intValue());
                    editor.putInt(getString(0x7f0d03a1), Integer.valueOf(s).intValue());
                    editor.commit();
                } else
                {
                    needSaveType = true;
                }
                setMemoListView(l);
                dispAd();
            }

            
            {
                this$0 = MyPageActivity.this;
                super();
            }
        };
        tabhost.setOnTabChangedListener(ontabchangelistener);
        headerLayoutMemo = (jp.co.yahoo.android.apps.transit.common.FixedHeaderScrollView.HeaderLinearLayout)fixedScrollView.findViewById(0x7f0a00e9);
        tabsHeader = (TabHost)headerLayoutMemo.findViewById(0x7f0a00ea);
        tabsHeader.setup();
        View view3 = layoutinflater.inflate(0x7f03008b, null);
        view3.setBackgroundResource(0x7f020273);
        ((TextView)view3.findViewById(0x7f0a029f)).setText(0x7f0d049d);
        textNumSearchHeader = (TextView)view3.findViewById(0x7f0a02a0);
        TextView textview3 = textNumSearchHeader;
        Object aobj3[] = new Object[2];
        aobj3[0] = Integer.valueOf(i);
        aobj3[1] = sql.getMaxSearchResultsMemo();
        textview3.setText(getString(0x7f0d0357, aobj3));
        view3.setFocusable(true);
        View view4 = layoutinflater.inflate(0x7f03008b, null);
        ((TextView)view4.findViewById(0x7f0a029f)).setText(0x7f0d049e);
        textNumTimetableHeader = (TextView)view4.findViewById(0x7f0a02a0);
        TextView textview4 = textNumTimetableHeader;
        Object aobj4[] = new Object[2];
        aobj4[0] = Integer.valueOf(i);
        aobj4[1] = sql.getMaxSearchResultsMemo();
        textview4.setText(getString(0x7f0d0357, aobj4));
        view4.setFocusable(true);
        View view5 = layoutinflater.inflate(0x7f03008b, null);
        ((TextView)view5.findViewById(0x7f0a029f)).setText(0x7f0d0497);
        textNumHistoryHeader = (TextView)view5.findViewById(0x7f0a02a0);
        TextView textview5 = textNumHistoryHeader;
        Object aobj5[] = new Object[2];
        aobj5[0] = Integer.valueOf(i);
        aobj5[1] = sql.getMaxSearchResultsMemo();
        textview5.setText(getString(0x7f0d0357, aobj5));
        view5.setFocusable(true);
        android.widget.TabHost.TabSpec tabspec3 = tabsHeader.newTabSpec(String.valueOf(0)).setIndicator(view3).setContent(0x7f0a029c);
        tabsHeader.addTab(tabspec3);
        android.widget.TabHost.TabSpec tabspec4 = tabsHeader.newTabSpec(String.valueOf(1)).setIndicator(view4).setContent(0x7f0a029d);
        tabsHeader.addTab(tabspec4);
        android.widget.TabHost.TabSpec tabspec5 = tabsHeader.newTabSpec(String.valueOf(2)).setIndicator(view5).setContent(0x7f0a029e);
        tabsHeader.addTab(tabspec5);
        TabHost tabhost1 = tabsHeader;
        android.widget.TabHost.OnTabChangeListener ontabchangelistener1 = new android.widget.TabHost.OnTabChangeListener() {

            final MyPageActivity this$0;

            public void onTabChanged(String s)
            {
                if (headerLayoutMemo.getVisibility() == 0 && tabsScroll.getCurrentTab() != tabsHeader.getCurrentTab())
                {
                    tabsScroll.setCurrentTab(tabsHeader.getCurrentTab());
                }
            }

            
            {
                this$0 = MyPageActivity.this;
                super();
            }
        };
        tabhost1.setOnTabChangedListener(ontabchangelistener1);
        textEditMemo = (TextView)memoLayout.findViewById(0x7f0a00db);
        textNoMemo = (TextView)memoLayout.findViewById(0x7f0a00dc);
        listSearch = (LinearLayout)memoLayout.findViewById(0x7f0a00dd);
        listTimetable = (LinearLayout)memoLayout.findViewById(0x7f0a00e3);
        listHistory = (LinearLayout)memoLayout.findViewById(0x7f0a00e4);
        fixedScrollView.setHeaderView(tabsScroll, headerLayoutMemo);
        ViewTreeObserver viewtreeobserver = tabsScroll.getViewTreeObserver();
        android.view.ViewTreeObserver.OnGlobalLayoutListener ongloballayoutlistener = new android.view.ViewTreeObserver.OnGlobalLayoutListener() {

            final MyPageActivity this$0;

            public void onGlobalLayout()
            {
                minHeightMemo = fixedScrollView.getHeight() - tabsScroll.getHeight();
                textNoMemo.setMinHeight(minHeightMemo);
                listSearch.setMinimumHeight(minHeightMemo);
                listTimetable.setMinimumHeight(minHeightMemo);
                listHistory.setMinimumHeight(minHeightMemo);
                try
                {
                    if (android.os.Build.VERSION.SDK_INT < 16)
                    {
                        TransitUtil.removeGlobalOnLayoutListener(tabsScroll.getViewTreeObserver(), this);
                        return;
                    }
                }
                catch (Exception exception)
                {
                    return;
                }
                TransitUtil.removeOnGlobalLayoutListener(tabsScroll.getViewTreeObserver(), this);
                return;
            }

            
            {
                this$0 = MyPageActivity.this;
                super();
            }
        };
        viewtreeobserver.addOnGlobalLayoutListener(ongloballayoutlistener);
    }

    private void initMyrouteView()
    {
        myrouteLayout.setVisibility(4);
        int i = sql.countMyroute();
        Object aobj[] = new Object[2];
        aobj[0] = Integer.valueOf(i);
        aobj[1] = sql.getMaxMyroute();
        String s = getString(0x7f0d0357, aobj);
        textScrollMyroute = (TextView)myrouteLayout.findViewById(0x7f0a00e6);
        textScrollMyroute.setText(s);
        headerLayoutMyroute = (jp.co.yahoo.android.apps.transit.common.FixedHeaderScrollView.HeaderLinearLayout)fixedScrollView.findViewById(0x7f0a00eb);
        textHeaderMyroute = (TextView)headerLayoutMyroute.findViewById(0x7f0a00ec);
        textHeaderMyroute.setText(s);
        listMyRoute = (LinearLayout)myrouteLayout.findViewById(0x7f0a00e7);
        textMyrouteDesc = (TextView)myrouteLayout.findViewById(0x7f0a00e8);
        fixedScrollView.setHeaderView(textScrollMyroute, headerLayoutMyroute);
        textScrollMyroute.getViewTreeObserver().addOnGlobalLayoutListener(new android.view.ViewTreeObserver.OnGlobalLayoutListener() {

            final MyPageActivity this$0;

            public void onGlobalLayout()
            {
                minHeightMyroute = fixedScrollView.getHeight() - textScrollMyroute.getHeight();
                listMyRoute.setMinimumHeight(minHeightMyroute);
                try
                {
                    if (android.os.Build.VERSION.SDK_INT < 16)
                    {
                        TransitUtil.removeGlobalOnLayoutListener(textScrollMyroute.getViewTreeObserver(), this);
                        return;
                    }
                }
                catch (Exception exception)
                {
                    return;
                }
                TransitUtil.removeOnGlobalLayoutListener(textScrollMyroute.getViewTreeObserver(), this);
                return;
            }

            
            {
                this$0 = MyPageActivity.this;
                super();
            }
        });
    }

    private void initView(int i)
    {
        deleteBtnLayout = (LinearLayout)findViewById(0x7f0a00ed);
        ((Button)findViewById(0x7f0a00ee)).setOnClickListener(deleteListener);
        fixedScrollView = (FixedHeaderScrollView)findViewById(0x7f0a00d0);
        baseScrollView = (jp.co.yahoo.android.apps.transit.common.FixedHeaderScrollView.BaseScrollView)fixedScrollView.findViewById(0x7f0a00d1);
        View view = baseScrollView.findViewById(0x7f0a00d8);
        memoLayout = view.findViewById(0x7f0a00d9);
        myrouteLayout = view.findViewById(0x7f0a00e5);
        fixedScrollView.init(baseScrollView, view);
        topBtnLayout = (LinearLayout)baseScrollView.findViewById(0x7f0a00d2);
        btnChangeType = (ImageView)topBtnLayout.findViewById(0x7f0a00d3);
        oneTapHome = (ImageView)topBtnLayout.findViewById(0x7f0a00d5);
        oneTapOffice = (ImageView)topBtnLayout.findViewById(0x7f0a00d6);
        oneTapOther = (ImageView)topBtnLayout.findViewById(0x7f0a00d7);
        if (i == 257)
        {
            initMyrouteView();
            setMyrouteListView(i);
            myrouteLayout.setVisibility(0);
            return;
        } else
        {
            initMemoView();
            setMemoListView(i);
            memoLayout.setVisibility(0);
            return;
        }
    }

    private void launchMyrouteSearchResult(Bundle bundle)
    {
        if (dispType != 0)
        {
            return;
        }
        ConditionData conditiondata = (ConditionData)bundle.getSerializable(getString(0x7f0d022c));
        String s = bundle.getString("id");
        if (s != null)
        {
            sql.updateMyrouteRefer(s);
        }
        Intent intent = new Intent(this, jp/co/yahoo/android/apps/transit/Transit);
        intent.setAction("android.intent.action.VIEW");
        intent.putExtra(getString(0x7f0d01df), getResources().getInteger(0x7f0c004c));
        intent.putExtra(getString(0x7f0d022c), conditiondata);
        startActivityForResult(intent, getResources().getInteger(0x7f0c004c));
    }

    private void launchMyrouteTop(Bundle bundle)
    {
        if (dispType != 0)
        {
            return;
        }
        ConditionData conditiondata = (ConditionData)bundle.getSerializable(getString(0x7f0d022c));
        String s = bundle.getString("id");
        if (s != null)
        {
            sql.updateMyrouteRefer(s);
        }
        Intent intent = new Intent(this, jp/co/yahoo/android/apps/transit/Transit);
        intent.setAction("android.intent.action.EDIT");
        intent.putExtra(getString(0x7f0d01df), getResources().getInteger(0x7f0c004c));
        intent.putExtra(getString(0x7f0d022c), conditiondata);
        startActivityForResult(intent, getResources().getInteger(0x7f0c004c));
    }

    private void launchSearchHistory(Bundle bundle)
    {
        NaviSearchData navisearchdata = (NaviSearchData)bundle.getSerializable(getString(0x7f0d0232));
        ConditionData conditiondata = (ConditionData)bundle.getSerializable(getString(0x7f0d022c));
        String s = bundle.getString("id");
        if (s != null)
        {
            sql.updateSearchHistoryRefer(s);
        }
        Intent intent = new Intent(this, jp/co/yahoo/android/apps/transit/SearchResultListActivity);
        intent.putExtra(getString(0x7f0d022c), conditiondata);
        intent.putExtra(getString(0x7f0d0232), navisearchdata);
        intent.putExtra(getString(0x7f0d01bd), true);
        startActivityForResult(intent, getResources().getInteger(0x7f0c0059));
    }

    private void launchSearchMemo(Bundle bundle)
    {
        NaviSearchData navisearchdata = (NaviSearchData)bundle.getSerializable(getString(0x7f0d0232));
        ConditionData conditiondata = (ConditionData)bundle.getSerializable(getString(0x7f0d022c));
        String s = bundle.getString("id");
        if (s != null)
        {
            sql.updateSearchMemoRefer(s);
        }
        Intent intent = new Intent(this, jp/co/yahoo/android/apps/transit/SearchResultActivity);
        intent.putExtra(getString(0x7f0d0231), 0);
        intent.putExtra(getString(0x7f0d0232), navisearchdata);
        intent.putExtra(getString(0x7f0d022c), conditiondata);
        intent.putExtra(getString(0x7f0d01bd), true);
        startActivityForResult(intent, getResources().getInteger(0x7f0c0058));
    }

    private void launchTimetable(Bundle bundle)
    {
        Bundle bundle1;
        String s;
        String s1;
        TimeTableData timetabledata;
        bundle1 = bundle.getBundle(getString(0x7f0d0232));
        s = bundle.getString(getString(0x7f0d01dd));
        s1 = bundle.getString("id");
        timetabledata = (TimeTableData)bundle1.getSerializable(getString(0x7f0d0232));
        if (timetabledata.departure != null) goto _L2; else goto _L1
_L1:
        timetabledata.setMemoResult(bundle1, this);
        Bundle bundle2 = new Bundle();
        bundle2.putSerializable(getString(0x7f0d0232), timetabledata);
        bundle.putBundle(getString(0x7f0d0232), bundle2);
        if (s1 != null)
        {
            String s3 = sql.updateTimetableResult(s1, new Bundle(), bundle2);
            bundle.putString(getString(0x7f0d01dd), s3);
        }
_L4:
        Intent intent = new Intent(this, jp/co/yahoo/android/apps/transit/TimeTableActivity);
        intent.putExtra(getString(0x7f0d0232), bundle1);
        intent.putExtra(getString(0x7f0d01c5), s1);
        intent.putExtra(getString(0x7f0d01dd), s);
        startActivityForResult(intent, getResources().getInteger(0x7f0c0065));
        return;
_L2:
        if (s1 != null)
        {
            String s2 = sql.updateTimetableRefer(s1);
            bundle.putString(getString(0x7f0d01dd), s2);
        }
        if (true) goto _L4; else goto _L3
_L3:
    }

    private void searchOneTap(int i)
    {
        ConditionData conditiondata;
        conditiondata = (new SaveCondition(this)).getCond();
        if (conditiondata == null)
        {
            conditiondata = new ConditionData();
        }
        i;
        JVM INSTR tableswitch 0 1: default 48
    //                   0 117
    //                   1 136;
           goto _L1 _L2 _L3
_L1:
        conditiondata.searchType = 6;
        conditiondata.goalName = getString(0x7f0d02a4);
_L5:
        conditiondata.startName = getString(0x7f0d0289);
        Intent intent = new Intent(this, jp/co/yahoo/android/apps/transit/Transit);
        intent.putExtra(getString(0x7f0d022c), conditiondata);
        startActivityForResult(intent, getResources().getInteger(0x7f0c004b));
        return;
_L2:
        conditiondata.searchType = 4;
        conditiondata.goalName = getString(0x7f0d028b);
        continue; /* Loop/switch isn't completed */
_L3:
        conditiondata.searchType = 5;
        conditiondata.goalName = getString(0x7f0d0338);
        if (true) goto _L5; else goto _L4
_L4:
    }

    private void setChangeTypeButton()
    {
        if (listType == 257)
        {
            btnChangeType.setImageResource(0x7f020103);
            btnChangeType.setContentDescription(getString(0x7f0d0355));
            return;
        }
        if (sql.countMyroute() < 1)
        {
            btnChangeType.setImageResource(0x7f0200f3);
        } else
        {
            btnChangeType.setImageResource(0x7f0200f4);
        }
        btnChangeType.setContentDescription(getString(0x7f0d0356));
    }

    private void setDisplayType(int i)
    {
        if (listType == 257)
        {
            setDisplayTypeMyroute(i);
            return;
        } else
        {
            setDisplayTypeMemo(i);
            return;
        }
    }

    private void setDisplayTypeMemo(int i)
    {
        if (dispType == i)
        {
            return;
        }
        if (i == 0)
        {
            textNoMemo.setMinHeight(minHeightMemo);
            listSearch.setMinimumHeight(minHeightMemo);
            listTimetable.setMinimumHeight(minHeightMemo);
            listHistory.setMinimumHeight(minHeightMemo);
            deleteBtnLayout.setVisibility(8);
            topBtnLayout.setVisibility(0);
            ((View)textNumSearch.getParent()).setEnabled(true);
            ((View)textNumTimetable.getParent()).setEnabled(true);
            ((View)textNumHistory.getParent()).setEnabled(true);
            ((View)textNumSearchHeader.getParent()).setEnabled(true);
            ((View)textNumTimetableHeader.getParent()).setEnabled(true);
            ((View)textNumHistoryHeader.getParent()).setEnabled(true);
            tabsScroll.findViewById(0x7f0a029b).setBackgroundColor(getResources().getColor(0x7f090062));
            if (normalScrollY != -1)
            {
                int j = topBtnLayout.getHeight();
                if (normalScrollY >= j)
                {
                    baseScrollView.scrollTo(baseScrollView.getScrollX(), j);
                } else
                {
                    baseScrollView.scrollTo(baseScrollView.getScrollX(), 0);
                }
                normalScrollY = -1;
            }
        } else
        {
            textNoMemo.setMinHeight(0);
            listSearch.setMinimumHeight(0);
            listTimetable.setMinimumHeight(0);
            listHistory.setMinimumHeight(0);
            normalScrollY = baseScrollView.getScrollY();
            deleteBtnLayout.setVisibility(0);
            topBtnLayout.setVisibility(8);
            ((View)textNumSearch.getParent()).setEnabled(false);
            ((View)textNumTimetable.getParent()).setEnabled(false);
            ((View)textNumHistory.getParent()).setEnabled(false);
            ((View)textNumSearchHeader.getParent()).setEnabled(false);
            ((View)textNumTimetableHeader.getParent()).setEnabled(false);
            ((View)textNumHistoryHeader.getParent()).setEnabled(false);
            tabsScroll.findViewById(0x7f0a029b).setBackgroundColor(getResources().getColor(0x7f090063));
            baseScrollView.scrollTo(baseScrollView.getScrollX(), 0);
        }
        selectTab;
        JVM INSTR tableswitch 0 2: default 256
    //                   0 469
    //                   1 667
    //                   2 704;
           goto _L1 _L2 _L3 _L4
_L1:
        dispType = i;
        supportInvalidateOptionsMenu();
        return;
_L2:
        if (i == 0)
        {
            textEditMemo.setVisibility(8);
        } else
        {
            textEditMemo.setVisibility(0);
        }
        if (listNormal != null && listNormal.getVisibility() == 0 && viewNormal != null)
        {
            viewNormal.setType(i);
        }
        if (listFavorite != null && listFavorite.getVisibility() == 0 && viewFavorite != null)
        {
            viewFavorite.setType(i);
        }
        if (listMoney != null && listMoney.getVisibility() == 0 && viewMoney != null)
        {
            viewMoney.setType(i);
        }
        if (i == 0)
        {
            if (textNoFavorite != null)
            {
                textNoFavorite.setText(0x7f0d0496);
            }
            if (textNoMoney != null)
            {
                textNoMoney.setText(0x7f0d049b);
            }
        } else
        {
            if (textNoFavorite != null)
            {
                textNoFavorite.setText(0x7f0d0493);
            }
            if (textNoMoney != null)
            {
                textNoMoney.setText(0x7f0d0493);
            }
        }
        continue; /* Loop/switch isn't completed */
_L3:
        textEditMemo.setVisibility(8);
        if (viewTimetable != null && viewTimetable.getVisibility() == 0)
        {
            viewTimetable.setType(i);
        }
        continue; /* Loop/switch isn't completed */
_L4:
        textEditMemo.setVisibility(8);
        if (viewHistory != null && viewHistory.getVisibility() == 0)
        {
            viewHistory.setType(i);
        }
        if (true) goto _L1; else goto _L5
_L5:
    }

    private void setDisplayTypeMyroute(int i)
    {
        if (dispType == i)
        {
            return;
        }
        if (i == 0)
        {
            listMyRoute.setMinimumHeight(minHeightMyroute);
            deleteBtnLayout.setVisibility(8);
            topBtnLayout.setVisibility(0);
            textMyrouteDesc.setVisibility(0);
            if (normalScrollY != -1)
            {
                int j = topBtnLayout.getHeight();
                if (normalScrollY >= j)
                {
                    baseScrollView.scrollTo(baseScrollView.getScrollX(), j);
                } else
                {
                    baseScrollView.scrollTo(baseScrollView.getScrollX(), 0);
                }
                normalScrollY = -1;
            }
        } else
        {
            listMyRoute.setMinimumHeight(0);
            normalScrollY = baseScrollView.getScrollY();
            deleteBtnLayout.setVisibility(0);
            topBtnLayout.setVisibility(8);
            textMyrouteDesc.setVisibility(8);
            baseScrollView.post(new Runnable() {

                final MyPageActivity this$0;

                public void run()
                {
                    baseScrollView.scrollTo(baseScrollView.getScrollX(), 0);
                }

            
            {
                this$0 = MyPageActivity.this;
                super();
            }
            });
        }
        if (viewMyRoute != null && viewMyRoute.getVisibility() == 0)
        {
            viewMyRoute.setType(i);
        }
        dispType = i;
        supportInvalidateOptionsMenu();
    }

    private void setMemoListView(int i)
    {
        int j;
        tabsScroll.setCurrentTab(i);
        tabsHeader.setCurrentTab(i);
        j = baseScrollView.getScrollY();
        if (selectTab == i) goto _L2; else goto _L1
_L1:
        selectTab;
        JVM INSTR tableswitch 0 2: default 64
    //                   0 136
    //                   1 148
    //                   2 160;
           goto _L2 _L3 _L4 _L5
_L2:
        selectTab = i;
        listType = i;
        i;
        JVM INSTR tableswitch 0 2: default 100
    //                   0 172
    //                   1 179
    //                   2 186;
           goto _L6 _L7 _L8 _L9
_L6:
        if (dispType == 0)
        {
            int k = topBtnLayout.getHeight();
            if (j < k)
            {
                break; /* Loop/switch isn't completed */
            }
            baseScrollView.scrollTo(baseScrollView.getScrollX(), k);
        }
        return;
_L3:
        listSearch.setVisibility(8);
        continue; /* Loop/switch isn't completed */
_L4:
        listTimetable.setVisibility(8);
        continue; /* Loop/switch isn't completed */
_L5:
        listHistory.setVisibility(8);
        continue; /* Loop/switch isn't completed */
_L7:
        showSearchMemoList();
        continue; /* Loop/switch isn't completed */
_L8:
        showTimetableMemoList();
        continue; /* Loop/switch isn't completed */
_L9:
        showHistoryMemoList();
        if (true) goto _L6; else goto _L10
_L10:
        baseScrollView.scrollTo(baseScrollView.getScrollX(), 0);
        return;
        if (true) goto _L2; else goto _L11
_L11:
    }

    private void setMyrouteItemText(ConditionData conditiondata, LinearLayout linearlayout)
    {
        TextView textview1;
        TextView textview = (TextView)linearlayout.findViewById(0x7f0a020f);
        textview1 = (TextView)linearlayout.findViewById(0x7f0a021b);
        if (conditiondata == null)
        {
            try
            {
                textview.setText("-");
                textview1.setText("-");
                return;
            }
            catch (Exception exception)
            {
                textview.setText("-");
            }
            break MISSING_BLOCK_LABEL_112;
        }
        textview.setText((new StringBuilder()).append(conditiondata.startName).append("\u2192").append(conditiondata.goalName).toString());
        if (conditiondata.viaName == null || conditiondata.viaName.size() < 1)
        {
            textview1.setVisibility(8);
            return;
        }
        break MISSING_BLOCK_LABEL_121;
        textview1.setText("-");
        return;
        StringBuilder stringbuilder = new StringBuilder(getString(0x7f0d0327));
        int i = 0;
_L2:
        if (i < conditiondata.viaName.size())
        {
            stringbuilder.append((String)conditiondata.viaName.get(i));
            if (i < -1 + conditiondata.viaName.size())
            {
                stringbuilder.append("\u2192");
            }
            break MISSING_BLOCK_LABEL_204;
        }
        textview1.setText(stringbuilder);
        return;
        i++;
        if (true) goto _L2; else goto _L1
_L1:
    }

    private void setMyrouteListView(int i)
    {
        listType = i;
        showMyrouteList();
    }

    private void setOneTapSearchView()
    {
        if (!TransitUtil.isEmpty((new SetSharedPreferences(this, getString(0x7f0d00c3))).getStringSharedPreferenceData(getString(0x7f0d01a2))))
        {
            enableHome = true;
            oneTapHome.setImageResource(0x7f0200ee);
        } else
        {
            enableHome = false;
            oneTapHome.setImageResource(0x7f0200ed);
        }
        if (!TransitUtil.isEmpty((new SetSharedPreferences(this, getString(0x7f0d00c5))).getStringSharedPreferenceData(getString(0x7f0d01a2))))
        {
            enableOffice = true;
            oneTapOffice.setImageResource(0x7f0200fa);
        } else
        {
            enableOffice = false;
            oneTapOffice.setImageResource(0x7f0200f9);
        }
        if (!TransitUtil.isEmpty((new SetSharedPreferences(this, getString(0x7f0d00c4))).getStringSharedPreferenceData(getString(0x7f0d01a2))))
        {
            enableOther = true;
            oneTapOther.setImageResource(0x7f020100);
            return;
        } else
        {
            enableOther = false;
            oneTapOther.setImageResource(0x7f0200ff);
            return;
        }
    }

    private void showCategorySelectDialog(final Bundle item)
    {
        String s = item.getString(getString(0x7f0d0247));
        final int positionOld;
        if (s.equals(getString(0x7f0d048d)))
        {
            positionOld = 1;
        } else
        if (s.equals(getString(0x7f0d048e)))
        {
            positionOld = 2;
        } else
        {
            positionOld = 0;
        }
        showSingleChoiceListDialog(getString(0x7f0d0490), null, 0x7f07000a, positionOld, new android.content.DialogInterface.OnClickListener() {

            final MyPageActivity this$0;
            final Bundle val$item;
            final int val$positionOld;

            public void onClick(DialogInterface dialoginterface, int i)
            {
                if (positionOld == i) goto _L2; else goto _L1
_L1:
                i;
                JVM INSTR tableswitch 1 2: default 32
            //                           1 101
            //                           2 114;
                   goto _L3 _L4 _L5
_L3:
                String s1 = getString(0x7f0d048f);
_L7:
                String s2 = item.getString("id");
                sql.updateSearchMemoCategory(s2, s1);
                setMemoListView(selectTab);
                Toast.makeText(MyPageActivity.this, getString(0x7f0d048c), 1).show();
_L2:
                return;
_L4:
                s1 = getString(0x7f0d048d);
                continue; /* Loop/switch isn't completed */
_L5:
                s1 = getString(0x7f0d048e);
                if (true) goto _L7; else goto _L6
_L6:
            }

            
            {
                this$0 = MyPageActivity.this;
                positionOld = i;
                item = bundle;
                super();
            }
        }, null);
    }

    private void showHistoryMemoList()
    {
        ArrayList arraylist = (ArrayList)sql.getSearchHistoryCondition();
        Object aobj[] = new Object[2];
        aobj[0] = Integer.valueOf(arraylist.size());
        aobj[1] = sql.getMaxSearchResultsHistory();
        String s = getString(0x7f0d0357, aobj);
        textNumHistory.setText(s);
        textNumHistoryHeader.setText(s);
        if (viewHistory != null)
        {
            viewHistory.removeAllViews();
        }
        if (arraylist.size() < 1)
        {
            textNoMemo.setText(0x7f0d0498);
            textNoMemo.setVisibility(0);
            listHistory.setVisibility(8);
            return;
        }
        textNoMemo.setVisibility(8);
        listHistory.setVisibility(0);
        if (viewHistory == null)
        {
            viewHistory = new MemoListView(this);
            viewHistory.setOnItemClickChangeListener(historyListener);
            listHistory.addView(viewHistory);
        }
        ArrayList arraylist1 = new ArrayList();
        for (Iterator iterator = arraylist.iterator(); iterator.hasNext(); arraylist1.add(createSearchHistoryItem((Bundle)iterator.next()))) { }
        viewHistory.showView(arraylist, arraylist1, false, dispType);
    }

    private void showMyrouteList()
    {
        ArrayList arraylist = (ArrayList)sql.getMyroute();
        StringBuilder stringbuilder = (new StringBuilder()).append(getString(0x7f0d035d));
        Object aobj[] = new Object[2];
        aobj[0] = Integer.valueOf(arraylist.size());
        aobj[1] = sql.getMaxMyroute();
        String s = stringbuilder.append(getString(0x7f0d0357, aobj)).toString();
        textScrollMyroute.setText(s);
        textHeaderMyroute.setText(s);
        if (viewMyRoute != null)
        {
            viewMyRoute.removeAllViews();
        }
        if (arraylist.size() < 1)
        {
            if (listType == 257 && dispType == 1)
            {
                setDisplayTypeMyroute(0);
                changeMypageType(null);
            }
            return;
        }
        if (viewMyRoute == null)
        {
            viewMyRoute = new MemoListView(this);
            viewMyRoute.setOnItemClickChangeListener(myrouteListener);
            listMyRoute.addView(viewMyRoute, 0);
        }
        ArrayList arraylist1 = new ArrayList();
        for (Iterator iterator = arraylist.iterator(); iterator.hasNext(); arraylist1.add(createMyrouteItem((Bundle)iterator.next()))) { }
        viewMyRoute.showView(arraylist, arraylist1, true, dispType);
    }

    private void showOneTapDisableMessage(int i)
    {
        if (isFinishing())
        {
            return;
        }
        i;
        JVM INSTR tableswitch 0 1: default 32
    //                   0 105
    //                   1 123;
           goto _L1 _L2 _L3
_L1:
        int j;
        final int nReqId;
        j = 0x7f0d035a;
        nReqId = getResources().getInteger(0x7f0c0051);
_L5:
        (new TransitDialogBuilder(this)).setMessage(getString(j)).setPositiveButton(getString(0x7f0d007e), new android.content.DialogInterface.OnClickListener() {

            final MyPageActivity this$0;
            final int val$nReqId;

            public void onClick(DialogInterface dialoginterface, int k)
            {
                Intent intent = new Intent(MyPageActivity.this, jp/co/yahoo/android/apps/transit/OthersAdressSearchActivity);
                intent.putExtra(getString(0x7f0d01df), nReqId);
                startActivityForResult(intent, getResources().getInteger(0x7f0c004f));
            }

            
            {
                this$0 = MyPageActivity.this;
                nReqId = i;
                super();
            }
        }).setNegativeButton(getString(0x7f0d0071), new android.content.DialogInterface.OnClickListener() {

            final MyPageActivity this$0;

            public void onClick(DialogInterface dialoginterface, int k)
            {
            }

            
            {
                this$0 = MyPageActivity.this;
                super();
            }
        }).show();
        return;
_L2:
        j = 0x7f0d0358;
        nReqId = getResources().getInteger(0x7f0c0050);
        continue; /* Loop/switch isn't completed */
_L3:
        j = 0x7f0d0359;
        nReqId = getResources().getInteger(0x7f0c0052);
        if (true) goto _L5; else goto _L4
_L4:
    }

    private void showSearchMemoList()
    {
        HashMap hashmap = sql.getSearchResultsMemo();
        ArrayList arraylist = (ArrayList)hashmap.get(getString(0x7f0d048f));
        ArrayList arraylist1 = (ArrayList)hashmap.get(getString(0x7f0d048d));
        ArrayList arraylist2 = (ArrayList)hashmap.get(getString(0x7f0d048e));
        int i = 0;
        if (arraylist != null)
        {
            i = 0 + arraylist.size();
        }
        if (arraylist1 != null)
        {
            i += arraylist1.size();
        }
        if (arraylist2 != null)
        {
            i += arraylist2.size();
        }
        Object aobj[] = new Object[2];
        aobj[0] = Integer.valueOf(i);
        aobj[1] = sql.getMaxSearchResultsMemo();
        String s = getString(0x7f0d0357, aobj);
        textNumSearch.setText(s);
        textNumSearchHeader.setText(s);
        if (viewNormal != null)
        {
            viewNormal.removeAllViews();
        }
        if (viewFavorite != null)
        {
            viewFavorite.removeAllViews();
        }
        if (viewMoney != null)
        {
            viewMoney.removeAllViews();
        }
        if (i < 1)
        {
            textNoMemo.setText(0x7f0d049c);
            textNoMemo.setVisibility(0);
            listSearch.setVisibility(8);
            return;
        }
        textNoMemo.setVisibility(8);
        listSearch.setVisibility(0);
        latestSearchMemoId = getSharedPreferences(getString(0x7f0d04e1), 0).getString(getString(0x7f0d039f), "");
        if (listNormal == null)
        {
            listNormal = (LinearLayout)listSearch.findViewById(0x7f0a00de);
        }
        if (listFavorite == null)
        {
            listFavorite = (LinearLayout)listSearch.findViewById(0x7f0a00e0);
        }
        if (listMoney == null)
        {
            listMoney = (LinearLayout)listSearch.findViewById(0x7f0a00e2);
        }
        if (textNoFavorite == null)
        {
            textNoFavorite = (TextView)listSearch.findViewById(0x7f0a00df);
        }
        if (textNoMoney == null)
        {
            textNoMoney = (TextView)listSearch.findViewById(0x7f0a00e1);
        }
        if (arraylist != null && arraylist.size() > 0)
        {
            listNormal.setVisibility(0);
            if (viewNormal == null)
            {
                viewNormal = new MemoListView(this);
                viewNormal.setOnItemClickChangeListener(searchMemoListener);
                listNormal.addView(viewNormal);
            }
            ArrayList arraylist5 = new ArrayList();
            for (Iterator iterator2 = arraylist.iterator(); iterator2.hasNext(); arraylist5.add(createSearchMemoItem((Bundle)iterator2.next(), 0))) { }
            viewNormal.showView(arraylist, arraylist5, true, dispType);
        } else
        {
            listNormal.setVisibility(8);
        }
        if (arraylist1 != null && arraylist1.size() > 0)
        {
            textNoFavorite.setVisibility(8);
            listFavorite.setVisibility(0);
            if (viewFavorite == null)
            {
                viewFavorite = new MemoListView(this);
                viewFavorite.setOnItemClickChangeListener(searchMemoListener);
                listFavorite.addView(viewFavorite);
            }
            ArrayList arraylist4 = new ArrayList();
            for (Iterator iterator1 = arraylist1.iterator(); iterator1.hasNext(); arraylist4.add(createSearchMemoItem((Bundle)iterator1.next(), 0))) { }
            viewFavorite.showView(arraylist1, arraylist4, true, dispType);
        } else
        {
            textNoFavorite.setVisibility(0);
            listFavorite.setVisibility(8);
        }
        if (arraylist2 != null && arraylist2.size() > 0)
        {
            textNoMoney.setVisibility(8);
            listMoney.setVisibility(0);
            if (viewMoney == null)
            {
                viewMoney = new MemoListView(this);
                viewMoney.setOnItemClickChangeListener(searchMemoListener);
                listMoney.addView(viewMoney);
            }
            ArrayList arraylist3 = new ArrayList();
            for (Iterator iterator = arraylist2.iterator(); iterator.hasNext(); arraylist3.add(createSearchMemoItem((Bundle)iterator.next(), 0))) { }
            viewMoney.showView(arraylist2, arraylist3, true, dispType);
            return;
        } else
        {
            textNoMoney.setVisibility(0);
            listMoney.setVisibility(8);
            return;
        }
    }

    private void showTimetableMemoList()
    {
        ArrayList arraylist;
        ArrayList arraylist1;
        Iterator iterator;
        arraylist = (ArrayList)sql.getTimetable();
        Object aobj[] = new Object[2];
        aobj[0] = Integer.valueOf(arraylist.size());
        aobj[1] = sql.getMaxTimetable();
        String s = getString(0x7f0d0357, aobj);
        textNumTimetable.setText(s);
        textNumTimetableHeader.setText(s);
        if (viewTimetable != null)
        {
            viewTimetable.removeAllViews();
        }
        if (arraylist.size() < 1)
        {
            textNoMemo.setText(0x7f0d04a0);
            textNoMemo.setVisibility(0);
            listTimetable.setVisibility(8);
            return;
        }
        textNoMemo.setVisibility(8);
        listTimetable.setVisibility(0);
        latestTimetableMemoId = getSharedPreferences(getString(0x7f0d04e1), 0).getString(getString(0x7f0d03a0), "");
        if (viewTimetable == null)
        {
            MemoListView memolistview = new MemoListView(this);
            viewTimetable = memolistview;
            viewTimetable.setOnItemClickChangeListener(timetableListener);
            listTimetable.addView(viewTimetable);
        }
        arraylist1 = new ArrayList();
        iterator = arraylist.iterator();
_L13:
        Bundle bundle;
        LinearLayout linearlayout;
        TextView textview;
        TextView textview1;
        TextView textview2;
        LinearLayout linearlayout1;
        TextView textview3;
        if (!iterator.hasNext())
        {
            break MISSING_BLOCK_LABEL_971;
        }
        bundle = (Bundle)iterator.next();
        linearlayout = (LinearLayout)getLayoutInflater().inflate(0x7f03006c, null);
        textview = (TextView)linearlayout.findViewById(0x7f0a020f);
        textview1 = (TextView)linearlayout.findViewById(0x7f0a0218);
        textview2 = (TextView)linearlayout.findViewById(0x7f0a021a);
        linearlayout1 = (LinearLayout)linearlayout.findViewById(0x7f0a017e);
        textview3 = (TextView)linearlayout.findViewById(0x7f0a021b);
        Bundle bundle1;
        Bundle bundle2;
        bundle1 = bundle.getBundle(getString(0x7f0d022c));
        bundle2 = bundle.getBundle(getString(0x7f0d0232));
        if (bundle1 == null) goto _L2; else goto _L1
_L1:
        if (bundle1.isEmpty()) goto _L2; else goto _L3
_L3:
        TimeTableData timetabledata;
        timetabledata = new TimeTableData();
        timetabledata.setMemoCondition(bundle1, this);
        bundle2.putSerializable(getString(0x7f0d0232), timetabledata);
_L8:
        String s1 = bundle.getString("id");
        if (TextUtils.isEmpty(s1) || !s1.equals(latestTimetableMemoId)) goto _L5; else goto _L4
_L4:
        SpannableString spannablestring1 = new SpannableString((new StringBuilder()).append(timetabledata.name).append("  ").toString());
        AlignImageSpan alignimagespan1 = new AlignImageSpan(this, 0x7f0201ca);
        spannablestring1.setSpan(alignimagespan1, -1 + spannablestring1.length(), spannablestring1.length(), 33);
        textview.setText(spannablestring1);
_L9:
        textview1.setText(timetabledata.railName);
        if (TextUtils.isEmpty(timetabledata.date)) goto _L7; else goto _L6
_L6:
        textview2.setText((new StringBuilder()).append("\u3010").append(timetabledata.direction).append(getString(0x7f0d0275)).append("\u3011").toString());
        linearlayout1.setVisibility(0);
        ((TextView)linearlayout1.findViewById(0x7f0a0180)).setText(timetabledata.getDisplayDateString());
_L11:
        String s2 = bundle.getString(getString(0x7f0d0249));
        int k = Integer.parseInt(s2.substring(0, 4));
        int l = Integer.parseInt(s2.substring(5, 7));
        int i1 = Integer.parseInt(s2.substring(8, 10));
        String s3 = s2.substring(11, 13);
        String s4 = s2.substring(14, 16);
        Locale locale = Locale.JAPANESE;
        Object aobj1[] = new Object[5];
        aobj1[0] = Integer.valueOf(k);
        aobj1[1] = Integer.valueOf(l);
        aobj1[2] = Integer.valueOf(i1);
        aobj1[3] = s3;
        aobj1[4] = s4;
        textview3.setText(String.format(locale, "%d\u5E74%d\u6708%d\u65E5 %s:%s \u4FDD\u5B58", aobj1));
_L10:
        arraylist1.add(linearlayout);
        continue; /* Loop/switch isn't completed */
_L2:
        timetabledata = (TimeTableData)bundle2.getSerializable(getString(0x7f0d0232));
          goto _L8
_L5:
        textview.setText(timetabledata.name);
          goto _L9
        Exception exception;
        exception;
        textview.setText("-");
        textview1.setText("-");
        textview2.setText("-");
        textview3.setText("-");
          goto _L10
_L7:
        SpannableString spannablestring;
        int i;
        spannablestring = new SpannableString((new StringBuilder()).append("\u3010").append(timetabledata.direction).append(getString(0x7f0d0275)).append("\u3011  ").toString());
        i = Integer.parseInt(timetabledata.kind);
        int j;
        Drawable drawable;
        AlignImageSpan alignimagespan;
        if (i == 2)
        {
            j = 0x7f0201d4;
        } else
        if (i == 4)
        {
            j = 0x7f0201d7;
        } else
        {
            j = 0x7f0201ec;
        }
        drawable = getResources().getDrawable(j);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        alignimagespan = new AlignImageSpan(this, j);
        spannablestring.setSpan(alignimagespan, -1 + spannablestring.length(), spannablestring.length(), 33);
        textview2.setText(spannablestring);
          goto _L11
        viewTimetable.showView(arraylist, arraylist1, false, dispType);
        return;
        if (true) goto _L13; else goto _L12
_L12:
    }

    public void changeMypageType(View view)
    {
        touchTapRD(getString(0x7f0d0442));
        android.content.SharedPreferences.Editor editor;
        if (listType == 257)
        {
            if (textNumSearch == null)
            {
                initMemoView();
            }
            if (selectTab == -1)
            {
                selectTab = getSharedPreferences(getString(0x7f0d04e1), 0).getInt(getString(0x7f0d03a1), 0);
            }
            setMemoListView(selectTab);
            Animation animation2 = AnimationUtils.loadAnimation(this, 0x7f040010);
            animation2.setAnimationListener(animListenerMemoIn);
            memoLayout.startAnimation(animation2);
            Animation animation3 = AnimationUtils.loadAnimation(this, 0x7f040017);
            myrouteLayout.startAnimation(animation3);
        } else
        {
            if (sql.countMyroute() < 1)
            {
                showMessageDialog(getString(0x7f0d0125), getString(0x7f0d0124), getString(0x7f0d0074));
                btnChangeType.setImageResource(0x7f0200f3);
                btnChangeType.setContentDescription(getString(0x7f0d0356));
                return;
            }
            if (textScrollMyroute == null)
            {
                initMyrouteView();
            }
            setMyrouteListView(257);
            Animation animation = AnimationUtils.loadAnimation(this, 0x7f040012);
            animation.setAnimationListener(animListenerMyrouteIn);
            myrouteLayout.startAnimation(animation);
            Animation animation1 = AnimationUtils.loadAnimation(this, 0x7f040015);
            memoLayout.startAnimation(animation1);
        }
        setChangeTypeButton();
        editor = getSharedPreferences(getString(0x7f0d04e1), 0).edit();
        editor.putInt(getString(0x7f0d03a2), listType);
        if (selectTab != -1)
        {
            editor.putInt(getString(0x7f0d03a1), selectTab);
        }
        editor.commit();
        dispAd();
    }

    protected void delClickListener()
    {
        int i;
        ArrayList arraylist;
        i = listType;
        arraylist = null;
        i;
        JVM INSTR lookupswitch 4: default 52
    //                   0: 132
    //                   1: 273
    //                   2: 327
    //                   257: 381;
           goto _L1 _L2 _L3 _L4 _L5
_L1:
        if (reqCode != 0)
        {
            Intent intent = new Intent();
            intent.putExtra(getString(0x7f0d01e4), arraylist.size());
            if (listType == 257 && dispType == 1)
            {
                intent.putExtra(getString(0x7f0d022c), conditionData);
            }
            setResult(-1, intent);
            finish();
            return;
        }
        break; /* Loop/switch isn't completed */
_L2:
        arraylist = new ArrayList();
        if (listNormal.getVisibility() == 0 && viewNormal != null)
        {
            arraylist.addAll(viewNormal.getCheckItems());
        }
        if (listFavorite.getVisibility() == 0 && viewFavorite != null)
        {
            arraylist.addAll(viewFavorite.getCheckItems());
        }
        if (listMoney.getVisibility() == 0 && viewMoney != null)
        {
            arraylist.addAll(viewMoney.getCheckItems());
        }
        Iterator iterator2 = arraylist.iterator();
        while (iterator2.hasNext()) 
        {
            String s2 = ((Bundle)iterator2.next()).getString("id");
            sql.deleteResultsMemo(s2);
        }
        continue; /* Loop/switch isn't completed */
_L3:
        arraylist = viewTimetable.getCheckItems();
        Iterator iterator1 = arraylist.iterator();
        while (iterator1.hasNext()) 
        {
            String s1 = ((Bundle)iterator1.next()).getString("id");
            sql.deleteTimetable(s1);
        }
        continue; /* Loop/switch isn't completed */
_L4:
        arraylist = viewHistory.getCheckItems();
        Iterator iterator = arraylist.iterator();
        while (iterator.hasNext()) 
        {
            String s = ((Bundle)iterator.next()).getString("id");
            sql.deleteResultsHistory(s);
        }
        continue; /* Loop/switch isn't completed */
_L5:
        touchTapRD((new StringBuilder()).append(getString(0x7f0d0442)).append("/").append(getString(0x7f0d03d5)).toString());
        arraylist = viewMyRoute.getCheckItems();
        String as[] = new String[arraylist.size()];
        for (int j = 0; j < arraylist.size(); j++)
        {
            as[j] = ((Bundle)arraylist.get(j)).getString("id");
        }

        sql.deleteMyroute(as);
        if (true) goto _L1; else goto _L6
_L6:
        if (listType == 257)
        {
            setMyrouteListView(listType);
            return;
        } else
        {
            setMemoListView(listType);
            return;
        }
    }

    protected void onActivityResult(int i, int j, Intent intent)
    {
        super.onActivityResult(i, j, intent);
        if (getResources().getInteger(0x7f0c004f) == i)
        {
            setOneTapSearchView();
        }
    }

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setContentView(0x7f030023);
        setTitle(getString(0x7f0d035c));
        Intent intent = getIntent();
        int i = intent.getIntExtra(getString(0x7f0d01a5), -1);
        int j = intent.getIntExtra(getString(0x7f0d0247), 0);
        reqCode = intent.getIntExtra(getString(0x7f0d01df), 0);
        if (i == 257 && j == 1 && reqCode != 0)
        {
            conditionData = (ConditionData)intent.getSerializableExtra(getString(0x7f0d022c));
        }
        if (i == -1)
        {
            i = getSharedPreferences(getString(0x7f0d04e1), 0).getInt(getString(0x7f0d03a2), 0);
        } else
        {
            needSaveType = false;
        }
        sql = new ResultDB(this);
        initView(i);
        setDisplayType(j);
        setOneTapSearchView();
        setChangeTypeButton();
        dispAd();
    }

    public boolean onCreateOptionsMenu(Menu menu)
    {
        if (dispType == 0)
        {
            MenuItemCompat.setShowAsAction(menu.add(0, 0, 0, getString(0x7f0d0492)).setIcon(0x7f0200d3), 1);
        }
        return true;
    }

    public boolean onKeyDown(int i, KeyEvent keyevent)
    {
        if (i == 4)
        {
            if (reqCode != 0)
            {
                return super.onKeyDown(i, keyevent);
            }
            if (dispType == 1)
            {
                setDisplayType(0);
                dispAd();
                return true;
            }
        }
        return super.onKeyDown(i, keyevent);
    }

    public boolean onOptionsItemSelected(MenuItem menuitem)
    {
        super.onOptionsItemSelected(menuitem);
        switch (menuitem.getItemId())
        {
        default:
            return true;

        case 0: // '\0'
            break;
        }
        if (listType == 0)
        {
            touchTapRD((new StringBuilder()).append(getString(0x7f0d0441)).append("/").append(getString(0x7f0d03da)).toString());
        } else
        if (listType == 1)
        {
            touchTapRD((new StringBuilder()).append(getString(0x7f0d0444)).append("/").append(getString(0x7f0d03da)).toString());
        } else
        if (listType == 2)
        {
            touchTapRD((new StringBuilder()).append(getString(0x7f0d0440)).append("/").append(getString(0x7f0d03da)).toString());
        }
        if (listType != 257 && textNoMemo.getVisibility() == 0)
        {
            Object aobj[] = new Object[1];
            aobj[0] = getMemoName();
            showErrorMessageDialog(getString(0x7f0d0129, aobj), getString(0x7f0d0150));
            return true;
        } else
        {
            setDisplayType(1);
            dispAd();
            return true;
        }
    }

    public void searchToHome(View view)
    {
        touchTapRD((new StringBuilder()).append(getString(0x7f0d0443)).append("/").append(getString(0x7f0d03ea)).toString());
        if (enableHome)
        {
            searchOneTap(0);
            return;
        } else
        {
            showOneTapDisableMessage(0);
            return;
        }
    }

    public void searchToOffice(View view)
    {
        touchTapRD((new StringBuilder()).append(getString(0x7f0d0443)).append("/").append(getString(0x7f0d043b)).toString());
        if (enableOffice)
        {
            searchOneTap(1);
            return;
        } else
        {
            showOneTapDisableMessage(1);
            return;
        }
    }

    public void searchToOther(View view)
    {
        touchTapRD((new StringBuilder()).append(getString(0x7f0d0443)).append("/").append(getString(0x7f0d0401)).toString());
        if (enableOther)
        {
            searchOneTap(2);
            return;
        } else
        {
            showOneTapDisableMessage(2);
            return;
        }
    }
















/*
    static int access$2002(MyPageActivity mypageactivity, int i)
    {
        mypageactivity.minHeightMyroute = i;
        return i;
    }

*/













/*
    static boolean access$302(MyPageActivity mypageactivity, boolean flag)
    {
        mypageactivity.needSaveType = flag;
        return flag;
    }

*/
















/*
    static int access$602(MyPageActivity mypageactivity, int i)
    {
        mypageactivity.minHeightMemo = i;
        return i;
    }

*/



}

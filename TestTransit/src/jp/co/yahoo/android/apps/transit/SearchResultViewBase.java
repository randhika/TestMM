// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Calendar;
import jp.co.yahoo.android.apps.transit.api.ApiBase;
import jp.co.yahoo.android.apps.transit.api.NaviSearch;
import jp.co.yahoo.android.apps.transit.api.TimeTableSearchFlat;
import jp.co.yahoo.android.apps.transit.api.data.APIError;
import jp.co.yahoo.android.apps.transit.api.data.ConditionData;
import jp.co.yahoo.android.apps.transit.api.data.StationData;
import jp.co.yahoo.android.apps.transit.api.data.TimeTableItemData;
import jp.co.yahoo.android.apps.transit.common.SaveCondition;
import jp.co.yahoo.android.apps.transit.common.StringUtil;
import jp.co.yahoo.android.apps.transit.common.util.TransitUtil;
import jp.co.yahoo.android.apps.transit.viewparts.TransitDialogBuilder;

// Referenced classes of package jp.co.yahoo.android.apps.transit:
//            TransitBaseActivity, MapDisplay, StationInfo, SearchResultBaseActivity, 
//            SearchResultListActivity

public class SearchResultViewBase
    implements android.view.View.OnClickListener
{

    protected static final String MAP_APP_PKG_NAME = "jp.co.yahoo.android.apps.map";
    protected TimeTableItemData centerTime;
    protected ConditionData conditionData;
    protected Bundle conditions;
    protected Context context;
    private AlertDialog dialog;
    protected Handler mainHandler;
    protected AlertDialog mapappDialog;
    private TimeTableSearchFlat objSearch;
    protected Resources res;

    public SearchResultViewBase(Context context1)
    {
        context = context1;
    }

    public SearchResultViewBase(Context context1, Handler handler)
    {
        context = context1;
        mainHandler = handler;
    }

    protected static boolean isAppInstalled(Context context1)
    {
        PackageManager packagemanager = context1.getPackageManager();
        try
        {
            packagemanager.getApplicationInfo("jp.co.yahoo.android.apps.map", 0);
        }
        catch (android.content.pm.PackageManager.NameNotFoundException namenotfoundexception)
        {
            return false;
        }
        return true;
    }

    protected boolean mapAppInstalled()
    {
        return isAppInstalled(context);
    }

    public void onClick(View view)
    {
        int i = view.getId();
        if (i == 0x7f0a0260)
        {
            ((TransitBaseActivity)context).touchTapRD(context.getString(0x7f0d043a));
            jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.NaviPointData anavipointdata[] = (jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.NaviPointData[])(jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.NaviPointData[])view.getTag();
            jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.NaviPointData navipointdata1 = anavipointdata[0];
            jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.NaviPointData navipointdata2 = anavipointdata[1];
            StationData stationdata2 = new StationData();
            StationData stationdata3 = new StationData();
            Intent intent2;
            if (navipointdata1.nearStLat != null && navipointdata1.nearStLon != null)
            {
                stationdata3.setLat(navipointdata1.nearStLat);
                stationdata3.setLon(navipointdata1.nearStLon);
                stationdata2.setLat(navipointdata1.lat);
                stationdata2.setLon(navipointdata1.lon);
            } else
            if (navipointdata2.nearStLat != null && navipointdata2.nearStLon != null)
            {
                stationdata3.setLat(navipointdata2.lat);
                stationdata3.setLon(navipointdata2.lon);
                stationdata2.setLat(navipointdata2.nearStLat);
                stationdata2.setLon(navipointdata2.nearStLon);
            } else
            {
                stationdata2.setLat(navipointdata1.lat);
                stationdata2.setLon(navipointdata1.lon);
                stationdata3.setLat(navipointdata2.lat);
                stationdata3.setLon(navipointdata2.lon);
            }
            intent2 = new Intent(context, jp/co/yahoo/android/apps/transit/MapDisplay);
            intent2.putExtra(context.getString(0x7f0d023a), stationdata2);
            intent2.putExtra(context.getString(0x7f0d01c1), stationdata3);
            ((TransitBaseActivity)context).startActivityInCurrentMenu(intent2);
        } else
        {
            if (i == 0x7f0a0244)
            {
                ((TransitBaseActivity)context).touchTapRD(context.getString(0x7f0d03f6));
                jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.NaviPointData navipointdata = (jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.NaviPointData)view.getTag();
                StationData stationdata1 = new StationData();
                stationdata1.setLat(navipointdata.lat);
                stationdata1.setLon(navipointdata.lon);
                stationdata1.setName(navipointdata.stationName);
                Intent intent1 = new Intent(context, jp/co/yahoo/android/apps/transit/MapDisplay);
                intent1.putExtra(context.getString(0x7f0d023e), stationdata1);
                ((TransitBaseActivity)context).startActivityInCurrentMenu(intent1);
                return;
            }
            if (i == 0x7f0a0245)
            {
                startBrowser((String)view.getTag());
                return;
            }
            if (i == 0x7f0a0243)
            {
                ((TransitBaseActivity)context).touchTapRD(context.getString(0x7f0d041b));
                StationData stationdata = (StationData)view.getTag();
                Intent intent = new Intent(context, jp/co/yahoo/android/apps/transit/StationInfo);
                intent.putExtra(res.getString(0x7f0d023e), stationdata);
                ((TransitBaseActivity)context).startActivityInCurrentMenu(intent);
                return;
            }
        }
    }

    protected void research(TimeTableItemData timetableitemdata)
    {
        final ConditionData conditionData = (ConditionData)this.conditionData.clone();
        int i = Integer.parseInt(timetableitemdata.getHourMin().substring(0, 2));
        int j = Integer.parseInt(timetableitemdata.getHourMin().substring(2, 4));
        String s = objSearch.getDate();
        Calendar calendar = Calendar.getInstance();
        calendar.set(1, Integer.parseInt(s.substring(0, 4)));
        calendar.set(2, -1 + Integer.parseInt(s.substring(4, 6)));
        calendar.set(5, Integer.parseInt(s.substring(6, 8)));
        calendar.set(11, i);
        calendar.set(12, j);
        conditionData.year = calendar.get(1);
        conditionData.month = 1 + calendar.get(2);
        conditionData.day = calendar.get(5);
        conditionData.hour = calendar.get(11);
        conditionData.minite = calendar.get(12);
        conditionData.startName = objSearch.getName();
        conditionData.startCode = objSearch.getCode();
        conditionData.viaName = null;
        conditionData.viaCode = null;
        conditionData.type = res.getInteger(0x7f0c006b);
        conditionData.resultId = -1;
        conditionData.mtf = -1;
        conditionData.afterFinal = false;
        conditionData.midnightBus = false;
        if (conditionData.ticket == null)
        {
            ConditionData conditiondata = (new SaveCondition(context)).getCond();
            if (conditiondata != null)
            {
                conditionData.ticket = conditiondata.ticket;
            }
        }
        dialog.dismiss();
        NaviSearch navisearch = new NaviSearch(context, new jp.co.yahoo.android.apps.transit.api.ApiBase.ApiListener() {

            final SearchResultViewBase this$0;
            final ConditionData val$conditionData;

            public boolean onCanceled()
            {
                return false;
            }

            public boolean onError(APIError apierror)
            {
                String s1 = apierror.getMessage();
                if (StringUtil.isEmpty(s1))
                {
                    s1 = context.getString(0x7f0d0108);
                }
                ((TransitBaseActivity)context).showSimpleErrorMessageDialog(s1);
                return false;
            }

            public boolean onSuccess(ApiBase apibase, Object obj)
            {
                jp.co.yahoo.android.apps.transit.api.data.NaviSearchData navisearchdata = NaviSearch.m_routeList;
                Intent intent = new Intent(context, jp/co/yahoo/android/apps/transit/SearchResultListActivity);
                intent.putExtra(context.getString(0x7f0d0232), navisearchdata);
                intent.putExtra(context.getString(0x7f0d022c), conditionData);
                ((TransitBaseActivity)context).startActivityForResult(intent, context.getResources().getInteger(0x7f0c0059));
                return false;
            }

            
            {
                this$0 = SearchResultViewBase.this;
                conditionData = conditiondata;
                super();
            }
        });
        navisearch.setCondition(conditionData);
        navisearch.exec();
    }

    protected void setTimeTable(final String clickTime, String s, String s1, String s2, String s3)
    {
        mapappDialog = (new TransitDialogBuilder(context)).setNegativeButton(res.getString(0x7f0d033b), new android.content.DialogInterface.OnClickListener() {

            final SearchResultViewBase this$0;

            public void onClick(DialogInterface dialoginterface, int i)
            {
                ((TransitBaseActivity)context).touchTapRD((new StringBuilder()).append(context.getString(0x7f0d03f6)).append("/").append(context.getString(0x7f0d03cd)).toString());
                dialoginterface.cancel();
            }

            
            {
                this$0 = SearchResultViewBase.this;
                super();
            }
        }).setView(((LayoutInflater)context.getSystemService("layout_inflater")).inflate(0x7f030086, null)).create();
        objSearch = new TimeTableSearchFlat(context, new jp.co.yahoo.android.apps.transit.api.ApiBase.ApiListener() {

            final SearchResultViewBase this$0;
            final String val$clickTime;

            public boolean onCanceled()
            {
                return false;
            }

            public boolean onError(APIError apierror)
            {
                Toast.makeText(context, context.getString(0x7f0d010b), 0).show();
                return false;
            }

            public boolean onSuccess(ApiBase apibase, Object obj)
            {
                showTimeTableDialog(objSearch.getResults(), clickTime);
                return false;
            }

            
            {
                this$0 = SearchResultViewBase.this;
                clickTime = s;
                super();
            }
        });
        objSearch.setName(s1);
        objSearch.setCode(s);
        objSearch.setTid(s2);
        objSearch.setDate(s3);
        objSearch.setFunc("station_grp");
        objSearch.request();
    }

    protected void setTimetableTime(LinearLayout linearlayout, TimeTableItemData timetableitemdata, Bundle bundle, Bundle bundle1, boolean flag)
    {
        int i = res.getDimensionPixelSize(0x7f0b003d);
        LinearLayout linearlayout1 = new LinearLayout(context);
        linearlayout1.setOrientation(0);
        TextView textview = new TextView(context);
        textview.setTypeface(null, 1);
        textview.setTextSize(17F);
        textview.setPadding(i, i, i, i);
        if (flag)
        {
            textview.setTextColor(res.getColor(0x7f090051));
        }
        String s = timetableitemdata.getHourMin().substring(0, 2);
        if (Integer.parseInt(s) >= 24)
        {
            s = Integer.toString(-24 + Integer.parseInt(s));
        }
        textview.setText((new StringBuilder()).append(s).append(":").append(timetableitemdata.getHourMin().substring(2, 4)).toString());
        linearlayout1.addView(textview);
        TextView textview1 = new TextView(context);
        textview1.setTypeface(null, 1);
        textview1.setTextSize(17F);
        textview1.setPadding(i, i, i, i);
        textview1.setText((new StringBuilder()).append(bundle.getString("info")).append("  [").append(bundle1.getString("info")).append("]").toString());
        linearlayout1.addView(textview1);
        linearlayout1.setBackgroundResource(0x7f02021b);
        linearlayout1.setTag(timetableitemdata);
        linearlayout1.setOnClickListener(new android.view.View.OnClickListener() {

            final SearchResultViewBase this$0;

            public void onClick(View view)
            {
                research((TimeTableItemData)view.getTag());
            }

            
            {
                this$0 = SearchResultViewBase.this;
                super();
            }
        });
        ImageView imageview = (ImageView)((SearchResultBaseActivity)context).getLayoutInflater().inflate(0x7f030059, null);
        imageview.setLayoutParams(new android.view.ViewGroup.LayoutParams(-1, 2));
        linearlayout.addView(imageview);
        linearlayout.addView(linearlayout1);
    }

    protected void showTimeTableDialog(Bundle bundle, String s)
    {
        LinearLayout linearlayout;
        LinearLayout linearlayout1;
        linearlayout = (LinearLayout)LayoutInflater.from(context).inflate(0x7f03009c, null);
        linearlayout1 = new LinearLayout(context);
        linearlayout1.setOrientation(1);
        if (bundle == null || bundle.isEmpty()) goto _L2; else goto _L1
_L1:
        String as[];
        Bundle bundle1;
        Bundle bundle3;
        Bundle bundle4;
        int i;
        as = s.split(":");
        bundle1 = bundle.getBundle("timetable");
        Bundle bundle2 = bundle.getBundle("mark");
        bundle3 = bundle2.getBundle("dest");
        bundle4 = bundle2.getBundle("kind");
        i = 0;
_L8:
        int j;
        int k;
        boolean flag;
        int l;
        j = bundle1.size();
        k = i;
        flag = false;
        l = 0;
        if (k >= j) goto _L4; else goto _L3
_L3:
        TimeTableItemData timetableitemdata4;
        String s1;
        timetableitemdata4 = (TimeTableItemData)bundle1.getSerializable(String.valueOf(i));
        s1 = as[0];
        if (Integer.parseInt(as[0]) <= 3)
        {
            s1 = String.valueOf(24 + Integer.parseInt(as[0]));
        }
        if (!timetableitemdata4.getHourMin().equals((new StringBuilder()).append(s1).append(as[1]).toString())) goto _L6; else goto _L5
_L5:
        flag = true;
        l = i;
_L4:
        if (flag)
        {
            centerTime = (TimeTableItemData)bundle1.getSerializable(String.valueOf(l));
            if ((TimeTableItemData)bundle1.getSerializable(String.valueOf(l - 2)) != null)
            {
                TimeTableItemData timetableitemdata3 = (TimeTableItemData)bundle1.getSerializable(String.valueOf(l - 2));
                setTimetableTime(linearlayout1, timetableitemdata3, bundle3.getBundle(timetableitemdata3.getDesttype()), bundle4.getBundle(timetableitemdata3.getTraintype()), false);
            }
            if ((TimeTableItemData)bundle1.getSerializable(String.valueOf(l - 1)) != null)
            {
                TimeTableItemData timetableitemdata2 = (TimeTableItemData)bundle1.getSerializable(String.valueOf(l - 1));
                setTimetableTime(linearlayout1, timetableitemdata2, bundle3.getBundle(timetableitemdata2.getDesttype()), bundle4.getBundle(timetableitemdata2.getTraintype()), false);
            }
            setTimetableTime(linearlayout1, centerTime, bundle3.getBundle(centerTime.getDesttype()), bundle4.getBundle(centerTime.getTraintype()), true);
            if ((TimeTableItemData)bundle1.getSerializable(String.valueOf(l + 1)) != null)
            {
                TimeTableItemData timetableitemdata1 = (TimeTableItemData)bundle1.getSerializable(String.valueOf(l + 1));
                setTimetableTime(linearlayout1, timetableitemdata1, bundle3.getBundle(timetableitemdata1.getDesttype()), bundle4.getBundle(timetableitemdata1.getTraintype()), false);
            }
            if ((TimeTableItemData)bundle1.getSerializable(String.valueOf(l + 2)) != null)
            {
                TimeTableItemData timetableitemdata = (TimeTableItemData)bundle1.getSerializable(String.valueOf(l + 2));
                setTimetableTime(linearlayout1, timetableitemdata, bundle3.getBundle(timetableitemdata.getDesttype()), bundle4.getBundle(timetableitemdata.getTraintype()), false);
            }
        } else
        {
            Toast.makeText(context, "\u6642\u523B\u8868\u306E\u60C5\u5831\u3092\u53D6\u5F97\u3067\u304D\u307E\u305B\u3093\u3067\u3057\u305F\u3002", 0).show();
        }
_L2:
        ((ScrollView)linearlayout.findViewById(0x7f0a02e6)).addView(linearlayout1);
        dialog = (new TransitDialogBuilder(context)).setTitle(res.getString(0x7f0d02ec)).setView(linearlayout).setNegativeButton("\u30AD\u30E3\u30F3\u30BB\u30EB", new android.content.DialogInterface.OnClickListener() {

            final SearchResultViewBase this$0;

            public void onClick(DialogInterface dialoginterface, int i1)
            {
                dialoginterface.cancel();
            }

            
            {
                this$0 = SearchResultViewBase.this;
                super();
            }
        }).show();
        return;
_L6:
        i++;
        if (true) goto _L8; else goto _L7
_L7:
    }

    protected void startBrowser(String s)
    {
        TransitUtil.openURL(context, s);
    }

}

// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.timer.common;

import android.content.Context;
import android.content.SharedPreferences;
import java.text.SimpleDateFormat;
import java.util.Date;
import jp.co.yahoo.android.apps.transit.timer.api.HolidaySearch;
import jp.co.yahoo.android.apps.transit.timer.util.CountdownUtil;
import jp.co.yahoo.android.yolp.common.ApiBase;

public class Holiday
{

    private static final String PREF_HOLIDAY = "holiday";
    private static final String PREF_HOLIDAY_DATE = "holiday_date";
    private static final String PREF_HOLIDAY_EXPIRED = "holiday_expired";
    private Context context;
    private HolidaySearch objSearch;
    private SharedPreferences pref;
    private String sDates;
    private String sExpired;

    public Holiday(Context context1)
    {
        pref = null;
        objSearch = null;
        sDates = "";
        sExpired = "";
        context = context1;
        pref = context1.getSharedPreferences("holiday", 0);
    }

    private String getData()
    {
        objSearch = new HolidaySearch(context);
        objSearch.executeAsync(new jp.co.yahoo.android.yolp.common.ApiBase.ApiListener() {

            final Holiday this$0;

            public boolean endApi(ApiBase apibase, Object obj)
            {
                android.content.SharedPreferences.Editor editor = pref.edit();
                editor.putString("holiday_expired", objSearch.getExpired());
                editor.putString("holiday_date", objSearch.getHoliday());
                editor.commit();
                return false;
            }

            
            {
                this$0 = Holiday.this;
                super();
            }
        }, false);
        return "";
    }

    private boolean isNew()
    {
        sExpired = pref.getString("holiday_expired", null);
        String s = (new SimpleDateFormat("yyyyMMdd")).format(new Date());
        if (!CountdownUtil.isEmpty(s) && !CountdownUtil.isEmpty(sExpired))
        {
            int i;
            int j;
            try
            {
                i = Integer.parseInt(s);
                j = Integer.parseInt(sExpired);
            }
            catch (Exception exception)
            {
                return false;
            }
            if (i <= j)
            {
                return true;
            }
        }
        return false;
    }

    public boolean isHoliday(String s)
    {
        for (sDates = pref.getString("holiday_date", null); CountdownUtil.isEmpty(sDates) || CountdownUtil.isEmpty(s) || sDates.indexOf(s) == -1;)
        {
            return false;
        }

        return true;
    }

    public void update()
    {
        if (!isNew())
        {
            getData();
        }
    }


}

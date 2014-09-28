// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.common;

import android.text.format.Time;

public class YTime extends Time
{

    public static final int APRIL = 3;
    public static final int AUGUST = 7;
    public static final int DECEMBER = 11;
    public static final int FEBRUARY = 1;
    public static final int JANUARY = 0;
    public static final int JULY = 6;
    public static final int JUNE = 5;
    public static final int MARCH = 2;
    public static final int MAY = 4;
    public static final int NOVEMBER = 10;
    public static final int OCTOBER = 9;
    public static final int SEPTEMBER = 8;
    public static final long TIME1970;
    public static final long TIME2037;
    private static final YTime _today = new YTime();

    public YTime()
    {
    }

    public YTime(Time time)
    {
        super(time);
    }

    public YTime(String s)
    {
        super(s);
    }

    public static YTime getNow()
    {
        YTime ytime = new YTime();
        ytime.setToNow();
        return ytime;
    }

    public void clearTimeInfo()
    {
        hour = 0;
        minute = 0;
        second = 0;
        normalize();
    }

    public boolean equalDate(YTime ytime)
    {
        return year == ytime.year && month == ytime.month && monthDay == ytime.monthDay;
    }

    public boolean equalMonth(YTime ytime)
    {
        return year == ytime.year && month == ytime.month;
    }

    public boolean equalWeek(YTime ytime)
    {
        YTime ytime1 = new YTime(this);
        if (ytime1.weekDay == 0)
        {
            ytime1.monthDay = 1 + ytime1.monthDay;
            ytime1.normalize();
        }
        YTime ytime2 = new YTime(ytime);
        if (ytime2.weekDay == 0)
        {
            ytime2.monthDay = 1 + ytime2.monthDay;
            ytime2.normalize();
        }
        return ytime1.year == ytime2.year && ytime1.getWeekNumber() == ytime2.getWeekNumber();
    }

    public boolean isToday()
    {
        _today.setToNow();
        return equalDate(_today);
    }

    public long normalize()
    {
        if (allDay)
        {
            hour = 0;
            minute = 0;
            second = 0;
        }
        return super.normalize(false);
    }

    public void set(int i, int j, int k, int l, int i1)
    {
        super.set(0, i, j, k, l, i1);
    }

    public long toMillis()
    {
        return super.toMillis(false);
    }

    static 
    {
        YTime ytime = new YTime();
        ytime.set(0, 0, 0, 1, 0, 2037);
        TIME2037 = ytime.toMillis();
    }
}

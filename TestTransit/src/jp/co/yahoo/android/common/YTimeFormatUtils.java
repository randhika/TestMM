// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.common;

import android.content.res.Resources;

// Referenced classes of package jp.co.yahoo.android.common:
//            YApplicationBase, YTime

public class YTimeFormatUtils
{

    public static final String WEEKDAY_EN_SHORT[];
    public static final String WEEKDAY_EN_SHORT_CAPITAL[];
    public static final String WEEKDAY_JP_LONG[];
    public static final String WEEKDAY_JP_SHORT[];

    public YTimeFormatUtils()
    {
    }

    public static String DW1(YTime ytime)
    {
        return (new StringBuilder()).append(ytime.monthDay).append("\u65E5").append(W(ytime)).toString();
    }

    public static String MD1(YTime ytime)
    {
        return (new StringBuilder()).append(1 + ytime.month).append("\u6708").append(ytime.monthDay).append("\u65E5").toString();
    }

    public static String MD2(YTime ytime)
    {
        return (new StringBuilder()).append(1 + ytime.month).append("/").append(ytime.monthDay).toString();
    }

    public static String MDD1(YTime ytime)
    {
        return (new StringBuilder()).append(1 + ytime.month).append(ytime.format("\u6708%d\u65E5")).toString();
    }

    public static String MDD2(YTime ytime)
    {
        return (new StringBuilder()).append(Integer.toString(1 + ytime.month)).append(ytime.format("/%d")).toString();
    }

    public static String MDDW1(YTime ytime)
    {
        return (new StringBuilder()).append(Integer.toString(1 + ytime.month)).append("\u6708").append(Integer.toString(ytime.monthDay)).append("\u65E5").append(W(ytime)).toString();
    }

    public static String MDW1(YTime ytime)
    {
        return (new StringBuilder()).append(1 + ytime.month).append("\u6708").append(ytime.monthDay).append("\u65E5").append(W(ytime)).toString();
    }

    public static String MDW2(YTime ytime)
    {
        return (new StringBuilder()).append(1 + ytime.month).append("/").append(ytime.monthDay).append(W(ytime)).toString();
    }

    public static String W(YTime ytime)
    {
        return (new StringBuilder()).append("(").append(WEEKDAY_JP_SHORT[ytime.weekDay]).append(")").toString();
    }

    public static String YYMMDD2(YTime ytime)
    {
        return (new StringBuilder()).append(ytime.year % 100).append(ytime.format("/%m/%d")).toString();
    }

    public static String YYYYMD1(YTime ytime)
    {
        return (new StringBuilder()).append(ytime.year).append("\u5E74").append(1 + ytime.month).append("\u6708").append(ytime.monthDay).append("\u65E5").toString();
    }

    public static String YYYYMD2(YTime ytime)
    {
        return (new StringBuilder()).append(ytime.year).append("/").append(1 + ytime.month).append("/").append(ytime.monthDay).toString();
    }

    public static String YYYYMDW1(YTime ytime)
    {
        return (new StringBuilder()).append(ytime.year).append("\u5E74").append(1 + ytime.month).append("\u6708").append(ytime.monthDay).append("\u65E5").append(W(ytime)).toString();
    }

    public static String YYYYMDW2(YTime ytime)
    {
        return (new StringBuilder()).append(ytime.year).append("/").append(1 + ytime.month).append("/").append(ytime.monthDay).append(W(ytime)).toString();
    }

    public static String YYYYMMDD1(YTime ytime)
    {
        return ytime.format("%Y\u5E74%m\u6708%d\u65E5");
    }

    public static String YYYYMMDDW1(YTime ytime)
    {
        return (new StringBuilder()).append(ytime.format("%Y\u5E74%m\u6708%d\u65E5")).append(W(ytime)).toString();
    }

    public static String YYYYMMDDW2(YTime ytime)
    {
        return (new StringBuilder()).append(ytime.format("%Y/%m/%d")).append(W(ytime)).toString();
    }

    public static String hmm(YTime ytime)
    {
        return (new StringBuilder()).append(ytime.hour).append(":").append(ytime.format("%M")).toString();
    }

    static 
    {
        Resources resources = YApplicationBase.getInstance().getResources();
        WEEKDAY_JP_LONG = resources.getStringArray(R.array.weekday);
        WEEKDAY_JP_SHORT = resources.getStringArray(R.array.weekday_short);
        WEEKDAY_EN_SHORT = resources.getStringArray(R.array.weekday_eng_short);
        WEEKDAY_EN_SHORT_CAPITAL = resources.getStringArray(R.array.weekday_eng_short_capital);
    }
}

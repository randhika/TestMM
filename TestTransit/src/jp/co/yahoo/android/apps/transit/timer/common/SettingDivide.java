// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.timer.common;

import android.content.Context;
import android.content.SharedPreferences;
import jp.co.yahoo.android.apps.transit.timer.api.data.DivideData;
import jp.co.yahoo.android.apps.transit.timer.util.CountdownUtil;

public class SettingDivide
{

    private static final String PREF_CONDITION = "setting";
    private static final String PREF_CONDITION_KEY = "setting_divide";
    private DivideData objDivide;
    private SharedPreferences pref;

    public SettingDivide(Context context)
    {
        pref = null;
        objDivide = null;
        pref = context.getSharedPreferences("setting", 0);
    }

    public DivideData getDivide()
    {
        String s;
        s = pref.getString("setting_divide", null);
        objDivide = new DivideData();
        if (s != null && s.length() >= 5) goto _L2; else goto _L1
_L1:
        return objDivide;
_L2:
        String s1;
        String s2;
        String s3;
        s1 = s.substring(0, 1);
        s2 = s.substring(1, 3);
        s3 = s.substring(3, 5);
        objDivide.setDivideHour(Integer.parseInt(s2));
        objDivide.setDivideMin(Integer.parseInt(s3));
        if (s1.equals("-"))
        {
            objDivide.setReverse(true);
            continue; /* Loop/switch isn't completed */
        }
        try
        {
            objDivide.setReverse(false);
        }
        catch (Exception exception) { }
        if (true) goto _L1; else goto _L3
_L3:
    }

    public void setDivide(DivideData dividedata)
    {
        objDivide = dividedata;
        String s2;
        android.content.SharedPreferences.Editor editor;
        if (dividedata == null)
        {
            s2 = "+1400";
        } else
        {
            String s;
            String s1;
            if (dividedata.isReverse())
            {
                s = (new StringBuilder()).append("").append("-").toString();
            } else
            {
                s = (new StringBuilder()).append("").append("+").toString();
            }
            s1 = (new StringBuilder()).append(s).append(CountdownUtil.getZeroNum(dividedata.getDivideHour())).toString();
            s2 = (new StringBuilder()).append(s1).append(CountdownUtil.getZeroNum(dividedata.getDivideMin())).toString();
        }
        editor = pref.edit();
        editor.putString("setting_divide", s2);
        editor.commit();
    }
}

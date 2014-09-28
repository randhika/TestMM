// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.common;

import android.text.TextUtils;

// Referenced classes of package jp.co.yahoo.android.common:
//            YLog

public class YNumberUtils
{

    private static final String TAG = jp/co/yahoo/android/common/YNumberUtils.getSimpleName();

    private YNumberUtils()
    {
    }

    public static float parseFloat(String s, float f)
    {
        if (TextUtils.isEmpty(s))
        {
            return f;
        }
        float f1;
        try
        {
            f1 = Float.parseFloat(s);
        }
        catch (NumberFormatException numberformatexception)
        {
            YLog.w(TAG, (new StringBuilder()).append(numberformatexception.toString()).append(" : ").append(s).toString());
            numberformatexception.printStackTrace();
            return f;
        }
        catch (OutOfMemoryError outofmemoryerror)
        {
            YLog.w(TAG, (new StringBuilder()).append(outofmemoryerror.toString()).append(" : ").append(s).toString());
            outofmemoryerror.printStackTrace();
            return f;
        }
        return f1;
    }

    public static int parseInt(String s, int i)
    {
        if (TextUtils.isEmpty(s))
        {
            return i;
        }
        int j;
        try
        {
            j = Integer.parseInt(s);
        }
        catch (NumberFormatException numberformatexception)
        {
            YLog.w(TAG, (new StringBuilder()).append(numberformatexception.toString()).append(" : ").append(s).toString());
            numberformatexception.printStackTrace();
            return i;
        }
        return j;
    }

    public static long parseLong(String s, long l)
    {
        if (TextUtils.isEmpty(s))
        {
            return l;
        }
        long l1;
        try
        {
            l1 = Long.parseLong(s);
        }
        catch (NumberFormatException numberformatexception)
        {
            YLog.w(TAG, (new StringBuilder()).append(numberformatexception.toString()).append(" : ").append(s).toString());
            numberformatexception.printStackTrace();
            return l;
        }
        return l1;
    }

}

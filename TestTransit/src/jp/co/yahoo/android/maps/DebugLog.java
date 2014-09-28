// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.maps;

import android.util.Log;

public final class DebugLog
{

    private static boolean mDebug = false;

    private DebugLog()
    {
    }

    public static int d(String s, String s1)
    {
        boolean flag = mDebug;
        int j = 0;
        if (flag)
        {
            j = Log.d(s, s1);
        }
        return j;
    }

    public static int d(String s, String s1, Throwable throwable)
    {
        boolean flag = mDebug;
        int j = 0;
        if (flag)
        {
            j = Log.d(s, s1, throwable);
        }
        return j;
    }

    public static int e(String s, String s1)
    {
        boolean flag = mDebug;
        int j = 0;
        if (flag)
        {
            j = Log.e(s, s1);
        }
        return j;
    }

    public static int e(String s, String s1, Throwable throwable)
    {
        boolean flag = mDebug;
        int j = 0;
        if (flag)
        {
            j = Log.e(s, s1, throwable);
        }
        return j;
    }

    public static int i(String s, String s1)
    {
        boolean flag = mDebug;
        int j = 0;
        if (flag)
        {
            j = Log.i(s, s1);
        }
        return j;
    }

    public static int i(String s, String s1, Throwable throwable)
    {
        boolean flag = mDebug;
        int j = 0;
        if (flag)
        {
            j = Log.i(s, s1, throwable);
        }
        return j;
    }

    public static void setDebug(boolean flag)
    {
    }

    public static int v(String s, String s1)
    {
        boolean flag = mDebug;
        int j = 0;
        if (flag)
        {
            j = Log.v(s, s1);
        }
        return j;
    }

    public static int v(String s, String s1, Throwable throwable)
    {
        boolean flag = mDebug;
        int j = 0;
        if (flag)
        {
            j = Log.v(s, s1, throwable);
        }
        return j;
    }

    public static int w(String s, String s1)
    {
        boolean flag = mDebug;
        int j = 0;
        if (flag)
        {
            j = Log.w(s, s1);
        }
        return j;
    }

    public static int w(String s, String s1, Throwable throwable)
    {
        boolean flag = mDebug;
        int j = 0;
        if (flag)
        {
            j = Log.w(s, s1, throwable);
        }
        return j;
    }

    public static int w(String s, Throwable throwable)
    {
        boolean flag = mDebug;
        int j = 0;
        if (flag)
        {
            j = Log.w(s, throwable);
        }
        return j;
    }

}

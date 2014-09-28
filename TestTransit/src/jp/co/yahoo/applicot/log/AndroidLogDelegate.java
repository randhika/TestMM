// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.applicot.log;

import android.util.Log;

// Referenced classes of package jp.co.yahoo.applicot.log:
//            ApplicotLog

public final class AndroidLogDelegate
    implements ApplicotLog
{

    public AndroidLogDelegate()
    {
    }

    public int d(String s, String s1)
    {
        return Log.d(s, s1);
    }

    public int d(String s, String s1, Throwable throwable)
    {
        return Log.d(s, s1, throwable);
    }

    public int e(String s, String s1)
    {
        return Log.e(s, s1);
    }

    public int e(String s, String s1, Throwable throwable)
    {
        return Log.e(s, s1, throwable);
    }

    public String getStackTraceString(Throwable throwable)
    {
        return Log.getStackTraceString(throwable);
    }

    public int i(String s, String s1)
    {
        return Log.i(s, s1);
    }

    public int i(String s, String s1, Throwable throwable)
    {
        return Log.i(s, s1, throwable);
    }

    public int v(String s, String s1)
    {
        return Log.v(s, s1);
    }

    public int v(String s, String s1, Throwable throwable)
    {
        return Log.v(s, s1, throwable);
    }

    public int w(String s, String s1)
    {
        return Log.w(s, s1);
    }

    public int w(String s, String s1, Throwable throwable)
    {
        return Log.w(s, s1, throwable);
    }

    public int w(String s, Throwable throwable)
    {
        return Log.w(s, throwable);
    }
}

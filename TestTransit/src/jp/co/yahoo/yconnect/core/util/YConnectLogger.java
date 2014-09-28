// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.yconnect.core.util;

import android.util.Log;

public class YConnectLogger
{

    public static final YConnectLogger DEBUG = new YConnectLogger(1);
    public static final YConnectLogger ERROR = new YConnectLogger(4);
    public static final YConnectLogger INFO;
    private static YConnectLogger LOG_LEVEL;
    public static final YConnectLogger VERBOSE = new YConnectLogger(0);
    public static final YConnectLogger WARNING = new YConnectLogger(3);
    private int value;

    private YConnectLogger(int i)
    {
        value = i;
    }

    public static void debug(String s, String s1)
    {
        if (LOG_LEVEL.value <= DEBUG.value)
        {
            Log.d(s, s1);
        }
    }

    public static void error(String s, String s1)
    {
        if (LOG_LEVEL.value <= ERROR.value)
        {
            Log.e(s, s1);
        }
    }

    public static void info(String s, String s1)
    {
        if (LOG_LEVEL.value <= INFO.value)
        {
            Log.i(s, s1);
        }
    }

    public static void setLogLevel(YConnectLogger yconnectlogger)
    {
        LOG_LEVEL = yconnectlogger;
    }

    public static void verbose(String s, String s1)
    {
        if (LOG_LEVEL.value <= VERBOSE.value)
        {
            Log.v(s, s1);
        }
    }

    public static void warn(String s, String s1)
    {
        if (LOG_LEVEL.value <= WARNING.value)
        {
            Log.w(s, s1);
        }
    }

    static 
    {
        INFO = new YConnectLogger(2);
        LOG_LEVEL = INFO;
    }
}

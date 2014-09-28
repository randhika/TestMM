// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.common;

import android.os.Process;
import android.util.Log;
import java.util.Formatter;

// Referenced classes of package jp.co.yahoo.android.common:
//            YApplicationBase

public class YLog
{

    public static final int DEBUG = 3;
    public static final int ERROR = 6;
    public static final int INFO = 4;
    public static final int VERBOSE = 2;
    public static final int WARN = 5;

    public YLog()
    {
    }

    public static void critical(String s, String s1)
    {
        log(6, s, s1);
    }

    public static transient void critical(String s, String s1, Object aobj[])
    {
        if (!isLoggable())
        {
            return;
        } else
        {
            StringBuilder stringbuilder = new StringBuilder();
            (new Formatter(stringbuilder)).format(s1, aobj);
            log(6, s, stringbuilder.toString());
            return;
        }
    }

    public static void d(String s, String s1)
    {
        if (!isLoggable())
        {
            return;
        } else
        {
            log(3, s, s1);
            return;
        }
    }

    public static transient void d(String s, String s1, Object aobj[])
    {
        if (!isLoggable())
        {
            return;
        } else
        {
            StringBuilder stringbuilder = new StringBuilder();
            (new Formatter(stringbuilder)).format(s1, aobj);
            log(3, s, stringbuilder.toString());
            return;
        }
    }

    public static void e(String s, String s1)
    {
        if (!isLoggable())
        {
            return;
        } else
        {
            log(6, s, s1);
            return;
        }
    }

    public static transient void e(String s, String s1, Object aobj[])
    {
        if (!isLoggable())
        {
            return;
        } else
        {
            StringBuilder stringbuilder = new StringBuilder();
            (new Formatter(stringbuilder)).format(s1, aobj);
            log(6, s, stringbuilder.toString());
            return;
        }
    }

    public static void i(String s, String s1)
    {
        if (!isLoggable())
        {
            return;
        } else
        {
            log(4, s, s1);
            return;
        }
    }

    public static transient void i(String s, String s1, Object aobj[])
    {
        if (!isLoggable())
        {
            return;
        } else
        {
            StringBuilder stringbuilder = new StringBuilder();
            (new Formatter(stringbuilder)).format(s1, aobj);
            log(4, s, stringbuilder.toString());
            return;
        }
    }

    public static boolean isLoggable()
    {
        return YApplicationBase.isDebugSignature();
    }

    private static void log(int j, String s, String s1)
    {
        StackTraceElement stacktraceelement = (new Exception()).getStackTrace()[2];
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("(");
        stringbuilder.append(Process.myTid());
        stringbuilder.append(")");
        stringbuilder.append("[");
        stringbuilder.append(stacktraceelement.getFileName());
        stringbuilder.append(":");
        stringbuilder.append(stacktraceelement.getLineNumber());
        stringbuilder.append("] ");
        stringbuilder.append(s1);
        Log.println(j, s, stringbuilder.toString());
    }

    public static void println(int j, String s, String s1)
    {
        if (!isLoggable())
        {
            return;
        } else
        {
            log(j, s, s1);
            return;
        }
    }

    public static void v(String s, String s1)
    {
        if (!isLoggable())
        {
            return;
        } else
        {
            log(2, s, s1);
            return;
        }
    }

    public static transient void v(String s, String s1, Object aobj[])
    {
        if (!isLoggable())
        {
            return;
        } else
        {
            StringBuilder stringbuilder = new StringBuilder();
            (new Formatter(stringbuilder)).format(s1, aobj);
            log(2, s, stringbuilder.toString());
            return;
        }
    }

    public static void w(String s, String s1)
    {
        if (!isLoggable())
        {
            return;
        } else
        {
            log(5, s, s1);
            return;
        }
    }

    public static transient void w(String s, String s1, Object aobj[])
    {
        if (!isLoggable())
        {
            return;
        } else
        {
            StringBuilder stringbuilder = new StringBuilder();
            (new Formatter(stringbuilder)).format(s1, aobj);
            log(5, s, stringbuilder.toString());
            return;
        }
    }
}

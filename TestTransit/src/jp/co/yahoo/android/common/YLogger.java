// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.common;

import android.content.Context;
import android.util.Log;

public class YLogger
{

    private static final String YAHOO = "yahoo";

    public YLogger()
    {
    }

    public static void alert(Context context, String s)
    {
        StackTraceElement stacktraceelement = (new Exception()).getStackTrace()[1];
        String s1 = (new StringBuilder()).append("").append(stacktraceelement.getFileName()).toString();
        String s2 = (new StringBuilder()).append(s1).append(":").toString();
        String s3 = (new StringBuilder()).append(s2).append(stacktraceelement.getLineNumber()).toString();
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(context);
        builder.setTitle(s3);
        builder.setMessage(s);
        builder.setNegativeButton("OK", null);
        builder.show();
    }

    public static void d(String s)
    {
        log(3, "yahoo", s);
    }

    public static void d(String s, String s1)
    {
        log(3, s, s1);
    }

    public static void e(String s)
    {
        log(6, "yahoo", s);
    }

    public static void e(String s, String s1)
    {
        log(6, s, s1);
    }

    public static void i(String s)
    {
        log(4, "yahoo", s);
    }

    public static void i(String s, String s1)
    {
        log(4, s, s1);
    }

    private static void log(int j, String s, String s1)
    {
        StackTraceElement stacktraceelement = (new Exception()).getStackTrace()[2];
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("[");
        stringbuilder.append(stacktraceelement.getFileName());
        stringbuilder.append(":");
        stringbuilder.append(stacktraceelement.getLineNumber());
        stringbuilder.append("] ");
        stringbuilder.append(s1);
        Log.println(j, s, stringbuilder.toString());
    }

    public static void log(String s)
    {
        StackTraceElement stacktraceelement = (new Exception()).getStackTrace()[1];
        String s1 = (new StringBuilder()).append("").append("[").toString();
        String s2 = (new StringBuilder()).append(s1).append(stacktraceelement.getFileName()).toString();
        String s3 = (new StringBuilder()).append(s2).append(":").toString();
        String s4 = (new StringBuilder()).append(s3).append(stacktraceelement.getLineNumber()).toString();
        String s5 = (new StringBuilder()).append(s4).append("] ").toString();
        Log.v("yahoo", (new StringBuilder()).append(s5).append(s).toString());
    }

    public static void log(String s, String s1)
    {
        StackTraceElement stacktraceelement = (new Exception()).getStackTrace()[1];
        String s2 = (new StringBuilder()).append("").append("[").toString();
        String s3 = (new StringBuilder()).append(s2).append(stacktraceelement.getFileName()).toString();
        String s4 = (new StringBuilder()).append(s3).append(":").toString();
        String s5 = (new StringBuilder()).append(s4).append(stacktraceelement.getLineNumber()).toString();
        String s6 = (new StringBuilder()).append(s5).append("] ").toString();
        Log.v(s, (new StringBuilder()).append(s6).append(s1).toString());
    }

    public static void v(String s)
    {
        log(2, "yahoo", s);
    }

    public static void v(String s, String s1)
    {
        log(2, s, s1);
    }

    public static void w(String s)
    {
        log(5, "yahoo", s);
    }

    public static void w(String s, String s1)
    {
        log(5, s, s1);
    }
}

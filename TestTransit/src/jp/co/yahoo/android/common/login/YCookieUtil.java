// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.common.login;


public class YCookieUtil
{

    public YCookieUtil()
    {
    }

    public static native String getYid(String s);

    static 
    {
        System.loadLibrary("ycookieutil");
    }
}

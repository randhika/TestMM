// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.yconnect.core.util;


public class StringUtil
{

    public StringUtil()
    {
    }

    public static String implode(String s, String as[])
    {
        String s1 = "";
        String s2 = "";
        int i = as.length;
        for (int j = 0; j < i; j++)
        {
            String s3 = as[j];
            s1 = (new StringBuilder()).append(s1).append(s2).append(s3).toString();
            s2 = s;
        }

        return s1;
    }

    public static String implode(String as[])
    {
        return implode(" ", as);
    }
}

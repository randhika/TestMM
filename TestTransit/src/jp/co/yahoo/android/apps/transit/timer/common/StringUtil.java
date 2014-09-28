// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.timer.common;

import java.util.Iterator;
import java.util.List;

public final class StringUtil
{

    private StringUtil()
    {
    }

    public static boolean isEmpty(String s)
    {
        return s == null || s.equals("") || s.length() == 0;
    }

    public static String join(List list, String s)
    {
        StringBuffer stringbuffer = new StringBuffer();
        String s1;
        for (Iterator iterator = list.iterator(); iterator.hasNext(); stringbuffer.append(s1))
        {
            s1 = (String)iterator.next();
            if (stringbuffer.length() > 0)
            {
                stringbuffer.append(s);
            }
        }

        return stringbuffer.toString();
    }

    public static String trim(String s)
    {
        int i = s.length();
        int j = 0;
        char ac[];
        for (ac = s.toCharArray(); j < i && (ac[j] <= ' ' || ac[j] == '\u3000'); j++) { }
        for (; j < i && (ac[i - 1] <= ' ' || ac[i - 1] == '\u3000'); i--) { }
        if (j > 0 || i < s.length())
        {
            s = s.substring(j, i);
        }
        return s;
    }
}

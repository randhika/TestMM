// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.yconnect.core.http;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class HttpParameters extends HashMap
{

    private static final long serialVersionUID = 1L;

    public HttpParameters()
    {
    }

    public String toQueryString()
    {
        String s = "";
        String s1 = "";
        for (Iterator iterator = keySet().iterator(); iterator.hasNext();)
        {
            String s2 = (String)iterator.next();
            s = (new StringBuilder()).append(s).append(s1).append(s2).append("=").append((String)get(s2)).toString();
            s1 = "&";
        }

        return s;
    }
}

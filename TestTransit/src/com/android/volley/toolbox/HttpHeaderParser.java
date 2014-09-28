// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.android.volley.toolbox;

import com.android.volley.NetworkResponse;
import java.util.Date;
import java.util.Map;
import org.apache.http.impl.cookie.DateParseException;
import org.apache.http.impl.cookie.DateUtils;

public class HttpHeaderParser
{

    public HttpHeaderParser()
    {
    }

    public static com.android.volley.Cache.Entry parseCacheHeaders(NetworkResponse networkresponse)
    {
        long l;
        Map map;
        long l1;
        long l2;
        long l3;
        long l4;
        String s1;
        boolean flag;
        l = System.currentTimeMillis();
        map = networkresponse.headers;
        l1 = 0L;
        l2 = 0L;
        l3 = 0L;
        l4 = 0L;
        String s = (String)map.get("Date");
        if (s != null)
        {
            l1 = parseDateAsEpoch(s);
        }
        s1 = (String)map.get("Cache-Control");
        flag = false;
        if (s1 == null) goto _L2; else goto _L1
_L1:
        String as[];
        int i;
        flag = true;
        as = s1.split(",");
        i = 0;
_L5:
        if (i < as.length) goto _L3; else goto _L2
_L2:
        String s2 = (String)map.get("Expires");
        if (s2 != null)
        {
            l2 = parseDateAsEpoch(s2);
        }
        String s3 = (String)map.get("ETag");
        com.android.volley.Cache.Entry entry;
        String s4;
        long l5;
        if (flag)
        {
            l3 = l + 1000L * l4;
        } else
        if (l1 > 0L && l2 >= l1)
        {
            l3 = l + (l2 - l1);
        }
        entry = new com.android.volley.Cache.Entry();
        entry.data = networkresponse.data;
        entry.etag = s3;
        entry.softTtl = l3;
        entry.ttl = entry.softTtl;
        entry.serverDate = l1;
        entry.responseHeaders = map;
        return entry;
_L3:
        s4 = as[i].trim();
        if (s4.equals("no-cache") || s4.equals("no-store"))
        {
            return null;
        }
        if (!s4.startsWith("max-age="))
        {
            break; /* Loop/switch isn't completed */
        }
        l5 = Long.parseLong(s4.substring(8));
        l4 = l5;
_L6:
        i++;
        if (true) goto _L5; else goto _L4
_L4:
        if (s4.equals("must-revalidate") || s4.equals("proxy-revalidate"))
        {
            l4 = 0L;
        }
          goto _L6
        Exception exception;
        exception;
          goto _L6
    }

    public static String parseCharset(Map map)
    {
        String s = (String)map.get("Content-Type");
        if (s == null) goto _L2; else goto _L1
_L1:
        String as[];
        int i;
        as = s.split(";");
        i = 1;
_L5:
        if (i < as.length) goto _L3; else goto _L2
_L2:
        return "ISO-8859-1";
_L3:
        String as1[] = as[i].trim().split("=");
        if (as1.length == 2 && as1[0].equals("charset"))
        {
            return as1[1];
        }
        i++;
        if (true) goto _L5; else goto _L4
_L4:
    }

    public static long parseDateAsEpoch(String s)
    {
        long l;
        try
        {
            l = DateUtils.parseDate(s).getTime();
        }
        catch (DateParseException dateparseexception)
        {
            return 0L;
        }
        return l;
    }
}

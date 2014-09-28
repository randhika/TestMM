// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

// Referenced classes of package com.google.android.gms.internal:
//            eu, eo, du

public final class ea
{

    private int mOrientation;
    private String qG;
    private String qH;
    private String qI;
    private List qJ;
    private String qK;
    private String qL;
    private List qM;
    private long qN;
    private boolean qO;
    private final long qP = -1L;
    private List qQ;
    private long qR;

    public ea()
    {
        qN = -1L;
        qO = false;
        qR = -1L;
        mOrientation = -1;
    }

    private static String a(Map map, String s)
    {
        List list = (List)map.get(s);
        if (list != null && !list.isEmpty())
        {
            return (String)list.get(0);
        } else
        {
            return null;
        }
    }

    private static long b(Map map, String s)
    {
        String s1;
        List list = (List)map.get(s);
        if (list == null || list.isEmpty())
        {
            break MISSING_BLOCK_LABEL_81;
        }
        s1 = (String)list.get(0);
        float f1 = Float.parseFloat(s1);
        return (long)(f1 * 1000F);
        NumberFormatException numberformatexception;
        numberformatexception;
        eu.D((new StringBuilder()).append("Could not parse float from ").append(s).append(" header: ").append(s1).toString());
        return -1L;
    }

    private static List c(Map map, String s)
    {
        List list = (List)map.get(s);
        if (list != null && !list.isEmpty())
        {
            String s1 = (String)list.get(0);
            if (s1 != null)
            {
                return Arrays.asList(s1.trim().split("\\s+"));
            }
        }
        return null;
    }

    private void e(Map map)
    {
        qG = a(map, "X-Afma-Ad-Size");
    }

    private void f(Map map)
    {
        List list = c(map, "X-Afma-Click-Tracking-Urls");
        if (list != null)
        {
            qJ = list;
        }
    }

    private void g(Map map)
    {
        List list = (List)map.get("X-Afma-Debug-Dialog");
        if (list != null && !list.isEmpty())
        {
            qK = (String)list.get(0);
        }
    }

    private void h(Map map)
    {
        List list = c(map, "X-Afma-Tracking-Urls");
        if (list != null)
        {
            qM = list;
        }
    }

    private void i(Map map)
    {
        long l1 = b(map, "X-Afma-Interstitial-Timeout");
        if (l1 != -1L)
        {
            qN = l1;
        }
    }

    private void j(Map map)
    {
        qL = a(map, "X-Afma-ActiveView");
    }

    private void k(Map map)
    {
        List list = (List)map.get("X-Afma-Mediation");
        if (list != null && !list.isEmpty())
        {
            qO = Boolean.valueOf((String)list.get(0)).booleanValue();
        }
    }

    private void l(Map map)
    {
        List list = c(map, "X-Afma-Manual-Tracking-Urls");
        if (list != null)
        {
            qQ = list;
        }
    }

    private void m(Map map)
    {
        long l1 = b(map, "X-Afma-Refresh-Rate");
        if (l1 != -1L)
        {
            qR = l1;
        }
    }

    private void n(Map map)
    {
        List list = (List)map.get("X-Afma-Orientation");
        if (list != null && !list.isEmpty())
        {
            String s = (String)list.get(0);
            if ("portrait".equalsIgnoreCase(s))
            {
                mOrientation = eo.bS();
            } else
            if ("landscape".equalsIgnoreCase(s))
            {
                mOrientation = eo.bR();
                return;
            }
        }
    }

    public void a(String s, Map map, String s1)
    {
        qH = s;
        qI = s1;
        d(map);
    }

    public void d(Map map)
    {
        e(map);
        f(map);
        g(map);
        h(map);
        i(map);
        k(map);
        l(map);
        m(map);
        n(map);
        j(map);
    }

    public du i(long l1)
    {
        return new du(qH, qI, qJ, qM, qN, qO, -1L, qQ, qR, mOrientation, qG, l1, qK, qL);
    }
}

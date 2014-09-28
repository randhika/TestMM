// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.a;
import com.google.android.gms.internal.b;
import java.util.Map;

// Referenced classes of package com.google.android.gms.tagmanager:
//            aj, dh, dg

class ce extends aj
{

    private static final String ID;
    private static final String agF;
    private static final String agG;

    public ce()
    {
        super(ID, new String[0]);
    }

    public boolean lh()
    {
        return false;
    }

    public com.google.android.gms.internal.d.a w(Map map)
    {
        com.google.android.gms.internal.d.a a1;
        com.google.android.gms.internal.d.a a2;
        a1 = (com.google.android.gms.internal.d.a)map.get(agF);
        a2 = (com.google.android.gms.internal.d.a)map.get(agG);
        if (a1 == null || a1 == dh.nd() || a2 == null || a2 == dh.nd()) goto _L2; else goto _L1
_L1:
        dg dg1;
        dg dg2;
        dg1 = dh.k(a1);
        dg2 = dh.k(a2);
        if (dg1 == dh.nb() || dg2 == dh.nb()) goto _L2; else goto _L3
_L3:
        double d;
        double d2;
        d2 = dg1.doubleValue();
        d = dg2.doubleValue();
        if (d2 > d) goto _L2; else goto _L4
_L4:
        double d1 = d2;
_L6:
        return dh.r(Long.valueOf(Math.round(d1 + Math.random() * (d - d1))));
_L2:
        d = 2147483647D;
        d1 = 0.0D;
        if (true) goto _L6; else goto _L5
_L5:
    }

    static 
    {
        ID = a.O.toString();
        agF = b.da.toString();
        agG = b.cZ.toString();
    }
}

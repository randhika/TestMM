// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.tagmanager;

import java.util.Map;

// Referenced classes of package com.google.android.gms.tagmanager:
//            cc, dh, dg

abstract class bx extends cc
{

    public bx(String s)
    {
        super(s);
    }

    protected boolean a(com.google.android.gms.internal.d.a a1, com.google.android.gms.internal.d.a a2, Map map)
    {
        dg dg = dh.k(a1);
        dg dg1 = dh.k(a2);
        if (dg == dh.nb() || dg1 == dh.nb())
        {
            return false;
        } else
        {
            return a(dg, dg1, map);
        }
    }

    protected abstract boolean a(dg dg, dg dg1, Map map);
}

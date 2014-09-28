// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.tagmanager;

import java.util.Map;

// Referenced classes of package com.google.android.gms.tagmanager:
//            cc, dh

abstract class dc extends cc
{

    public dc(String s)
    {
        super(s);
    }

    protected boolean a(com.google.android.gms.internal.d.a a1, com.google.android.gms.internal.d.a a2, Map map)
    {
        String s = dh.j(a1);
        String s1 = dh.j(a2);
        if (s == dh.nc() || s1 == dh.nc())
        {
            return false;
        } else
        {
            return a(s, s1, map);
        }
    }

    protected abstract boolean a(String s, String s1, Map map);
}

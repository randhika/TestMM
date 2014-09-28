// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import java.util.ArrayList;
import java.util.List;

// Referenced classes of package com.google.android.gms.internal:
//            hm, hk

public static final class <init>
{

    private final List GJ;
    private final Object GK;

    public <init> a(String s, Object obj)
    {
        GJ.add((new StringBuilder()).append((String)hm.f(s)).append("=").append(String.valueOf(obj)).toString());
        return this;
    }

    public String toString()
    {
        StringBuilder stringbuilder = (new StringBuilder(100)).append(GK.getClass().getSimpleName()).append('{');
        int i = GJ.size();
        for (int j = 0; j < i; j++)
        {
            stringbuilder.append((String)GJ.get(j));
            if (j < i - 1)
            {
                stringbuilder.append(", ");
            }
        }

        return stringbuilder.append('}').toString();
    }

    private (Object obj)
    {
        GK = hm.f(obj);
        GJ = new ArrayList();
    }

    GJ(Object obj, GJ gj)
    {
        this(obj);
    }
}

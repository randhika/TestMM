// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import java.util.Map;

// Referenced classes of package com.google.android.gms.internal:
//            bc, bb, ex, k, 
//            g, eu

static final class 
    implements bc
{

    public void b(ex ex1, Map map)
    {
        String s = (String)map.get("tx");
        String s1 = (String)map.get("ty");
        String s2 = (String)map.get("td");
        int i;
        int j;
        int l;
        k k1;
        try
        {
            i = Integer.parseInt(s);
            j = Integer.parseInt(s1);
            l = Integer.parseInt(s2);
            k1 = ex1.cc();
        }
        catch (NumberFormatException numberformatexception)
        {
            eu.D("Could not parse touch parameters from gmsg.");
            return;
        }
        if (k1 == null)
        {
            break MISSING_BLOCK_LABEL_85;
        }
        k1.z().a(i, j, l);
    }

    ()
    {
    }
}

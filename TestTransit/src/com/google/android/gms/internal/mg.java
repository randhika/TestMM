// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import java.util.Arrays;

public final class mg
{

    final byte anc[];
    final int tag;

    mg(int i, byte abyte0[])
    {
        tag = i;
        anc = abyte0;
    }

    public boolean equals(Object obj)
    {
        if (obj != this)
        {
            if (!(obj instanceof mg))
            {
                return false;
            }
            mg mg1 = (mg)obj;
            if (tag != mg1.tag || !Arrays.equals(anc, mg1.anc))
            {
                return false;
            }
        }
        return true;
    }

    public int hashCode()
    {
        return 31 * (527 + tag) + Arrays.hashCode(anc);
    }
}

// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;

// Referenced classes of package com.google.android.gms.internal:
//            fp, fm

public static final class yk
{

    private final String mName;
    private String yi;
    private boolean yj;
    private int yk;
    private boolean yl;
    private String ym;
    private final List yn = new ArrayList();
    private BitSet yo;
    private String yp;

    public yk I(int i)
    {
        if (yo == null)
        {
            yo = new BitSet();
        }
        yo.set(i);
        return this;
    }

    public yo Z(String s)
    {
        yi = s;
        return this;
    }

    public yi aa(String s)
    {
        yp = s;
        return this;
    }

    public fp dQ()
    {
        int i = 0;
        BitSet bitset = yo;
        int ai[] = null;
        if (bitset != null)
        {
            ai = new int[yo.cardinality()];
            for (int j = yo.nextSetBit(0); j >= 0;)
            {
                int k = i + 1;
                ai[i] = j;
                j = yo.nextSetBit(j + 1);
                i = k;
            }

        }
        return new fp(mName, yi, yj, yk, yl, ym, (fm[])yn.toArray(new fm[yn.size()]), ai, yp);
    }

    public yp w(boolean flag)
    {
        yj = flag;
        return this;
    }

    public yj x(boolean flag)
    {
        yl = flag;
        return this;
    }

    public (String s)
    {
        mName = s;
        yk = 1;
    }
}

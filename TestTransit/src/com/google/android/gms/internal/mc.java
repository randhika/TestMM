// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import java.util.Arrays;

public final class mc
{

    public static final Object ana = new Object();

    public static boolean equals(float af[], float af1[])
    {
        if (af == null || af.length == 0)
        {
            return af1 == null || af1.length == 0;
        } else
        {
            return Arrays.equals(af, af1);
        }
    }

    public static boolean equals(int ai[], int ai1[])
    {
        if (ai == null || ai.length == 0)
        {
            return ai1 == null || ai1.length == 0;
        } else
        {
            return Arrays.equals(ai, ai1);
        }
    }

    public static boolean equals(long al[], long al1[])
    {
        if (al == null || al.length == 0)
        {
            return al1 == null || al1.length == 0;
        } else
        {
            return Arrays.equals(al, al1);
        }
    }

    public static boolean equals(Object aobj[], Object aobj1[])
    {
        int i;
        int j;
        int k;
        if (aobj == null)
        {
            i = 0;
        } else
        {
            i = aobj.length;
        }
        if (aobj1 == null)
        {
            j = 0;
        } else
        {
            j = aobj1.length;
        }
        k = 0;
_L7:
        int l;
        for (l = 0; l < i && aobj[l] == null; l++) { }
        break MISSING_BLOCK_LABEL_164;
        i1 = k;
        for (; i1 < j && aobj1[i1] == null; i1++) { }
        if (l >= i)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (i1 >= j)
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        if (!flag || !flag1) goto _L2; else goto _L1
_L1:
        flag2 = true;
_L4:
        return flag2;
_L2:
        flag2 = false;
        if (flag != flag1) goto _L4; else goto _L3
_L3:
        flag3 = aobj[l].equals(aobj1[i1]);
        flag2 = false;
        if (!flag3) goto _L4; else goto _L5
_L5:
        j1 = l + 1;
        k = i1 + 1;
        l = j1;
        if (true) goto _L7; else goto _L6
_L6:
    }

    public static int hashCode(float af[])
    {
        if (af == null || af.length == 0)
        {
            return 0;
        } else
        {
            return Arrays.hashCode(af);
        }
    }

    public static int hashCode(int ai[])
    {
        if (ai == null || ai.length == 0)
        {
            return 0;
        } else
        {
            return Arrays.hashCode(ai);
        }
    }

    public static int hashCode(long al[])
    {
        if (al == null || al.length == 0)
        {
            return 0;
        } else
        {
            return Arrays.hashCode(al);
        }
    }

    public static int hashCode(Object aobj[])
    {
        int i = 0;
        int j;
        int k;
        if (aobj == null)
        {
            j = 0;
        } else
        {
            j = aobj.length;
        }
        for (k = 0; k < j; k++)
        {
            Object obj = aobj[k];
            if (obj != null)
            {
                i = i * 31 + obj.hashCode();
            }
        }

        return i;
    }

}

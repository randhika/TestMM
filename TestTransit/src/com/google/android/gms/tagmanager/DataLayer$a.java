// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.tagmanager;

import java.util.Arrays;

// Referenced classes of package com.google.android.gms.tagmanager:
//            DataLayer

static final class afh
{

    public final String JL;
    public final Object afh;

    public boolean equals(Object obj)
    {
        afh afh1;
        if (obj instanceof afh)
        {
            if (JL.equals((afh1 = (JL)obj).JL) && afh.equals(afh1.afh))
            {
                return true;
            }
        }
        return false;
    }

    public int hashCode()
    {
        Integer ainteger[] = new Integer[2];
        ainteger[0] = Integer.valueOf(JL.hashCode());
        ainteger[1] = Integer.valueOf(afh.hashCode());
        return Arrays.hashCode(ainteger);
    }

    public String toString()
    {
        return (new StringBuilder()).append("Key: ").append(JL).append(" value: ").append(afh.toString()).toString();
    }

    (String s, Object obj)
    {
        JL = s;
        afh = obj;
    }
}

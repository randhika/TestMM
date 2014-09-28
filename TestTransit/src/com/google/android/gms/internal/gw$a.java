// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;


// Referenced classes of package com.google.android.gms.internal:
//            hk, gw

public static final class FT
{

    public final int FS;
    public final int FT;

    public boolean equals(Object obj)
    {
        boolean flag = true;
        if (!(obj instanceof FT))
        {
            flag = false;
        } else
        if (this != obj)
        {
            FT ft = (FT)obj;
            if (ft.FS != FS || ft.FT != FT)
            {
                return false;
            }
        }
        return flag;
    }

    public int hashCode()
    {
        Object aobj[] = new Object[2];
        aobj[0] = Integer.valueOf(FS);
        aobj[1] = Integer.valueOf(FT);
        return hk.hashCode(aobj);
    }

    public (int i, int j)
    {
        FS = i;
        FT = j;
    }
}

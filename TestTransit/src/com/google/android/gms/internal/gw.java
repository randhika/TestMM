// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;


// Referenced classes of package com.google.android.gms.internal:
//            hq, hk

public final class gw extends hq
{
    public static final class a
    {

        public final int FS;
        public final int FT;

        public boolean equals(Object obj)
        {
            boolean flag = true;
            if (!(obj instanceof a))
            {
                flag = false;
            } else
            if (this != obj)
            {
                a a1 = (a)obj;
                if (a1.FS != FS || a1.FT != FT)
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

        public a(int i, int j)
        {
            FS = i;
            FT = j;
        }
    }


    public gw()
    {
        super(10);
    }
}

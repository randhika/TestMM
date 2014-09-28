// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.wallet.wobs;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;

// Referenced classes of package com.google.android.gms.wallet.wobs:
//            g

public class h
    implements android.os.Parcelable.Creator
{

    public h()
    {
    }

    static void a(g g1, Parcel parcel, int i)
    {
        int j = b.C(parcel);
        b.c(parcel, 1, g1.getVersionCode());
        b.c(parcel, 2, g1.akT);
        b.a(parcel, 3, g1.akU, false);
        b.a(parcel, 4, g1.akV);
        b.a(parcel, 5, g1.akW, false);
        b.a(parcel, 6, g1.akX);
        b.c(parcel, 7, g1.akY);
        b.G(parcel, j);
    }

    public g cm(Parcel parcel)
    {
        String s = null;
        int i = 0;
        int j = com.google.android.gms.common.internal.safeparcel.a.B(parcel);
        double d = 0.0D;
        long l = 0L;
        int k = -1;
        String s1 = null;
        int i1 = 0;
        do
        {
            if (parcel.dataPosition() < j)
            {
                int j1 = com.google.android.gms.common.internal.safeparcel.a.A(parcel);
                switch (com.google.android.gms.common.internal.safeparcel.a.ar(j1))
                {
                default:
                    com.google.android.gms.common.internal.safeparcel.a.b(parcel, j1);
                    break;

                case 1: // '\001'
                    i1 = com.google.android.gms.common.internal.safeparcel.a.g(parcel, j1);
                    break;

                case 2: // '\002'
                    i = com.google.android.gms.common.internal.safeparcel.a.g(parcel, j1);
                    break;

                case 3: // '\003'
                    s1 = com.google.android.gms.common.internal.safeparcel.a.o(parcel, j1);
                    break;

                case 4: // '\004'
                    d = com.google.android.gms.common.internal.safeparcel.a.m(parcel, j1);
                    break;

                case 5: // '\005'
                    s = com.google.android.gms.common.internal.safeparcel.a.o(parcel, j1);
                    break;

                case 6: // '\006'
                    l = com.google.android.gms.common.internal.safeparcel.a.i(parcel, j1);
                    break;

                case 7: // '\007'
                    k = com.google.android.gms.common.internal.safeparcel.a.g(parcel, j1);
                    break;
                }
            } else
            if (parcel.dataPosition() != j)
            {
                throw new com.google.android.gms.common.internal.safeparcel.a.a((new StringBuilder()).append("Overread allowed size end=").append(j).toString(), parcel);
            } else
            {
                return new g(i1, i, s1, d, s, l, k);
            }
        } while (true);
    }

    public Object createFromParcel(Parcel parcel)
    {
        return cm(parcel);
    }

    public g[] dU(int i)
    {
        return new g[i];
    }

    public Object[] newArray(int i)
    {
        return dU(i);
    }
}

// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;

public class hl
    implements android.os.Parcelable.Creator
{

    public hl()
    {
    }

    static void a(gy.a a1, Parcel parcel, int i)
    {
        int j = b.C(parcel);
        b.a(parcel, 1, a1.getAccountName(), false);
        b.c(parcel, 1000, a1.getVersionCode());
        b.a(parcel, 2, a1.fl(), false);
        b.c(parcel, 3, a1.fk());
        b.a(parcel, 4, a1.fn(), false);
        b.G(parcel, j);
    }

    public gy.a[] aq(int i)
    {
        return new gy.a[i];
    }

    public Object createFromParcel(Parcel parcel)
    {
        return z(parcel);
    }

    public Object[] newArray(int i)
    {
        return aq(i);
    }

    public gy.a z(Parcel parcel)
    {
        int i = 0;
        String s = null;
        int j = com.google.android.gms.common.internal.safeparcel.a.B(parcel);
        java.util.ArrayList arraylist = null;
        String s1 = null;
        int k = 0;
        do
        {
            if (parcel.dataPosition() < j)
            {
                int l = com.google.android.gms.common.internal.safeparcel.a.A(parcel);
                switch (com.google.android.gms.common.internal.safeparcel.a.ar(l))
                {
                default:
                    com.google.android.gms.common.internal.safeparcel.a.b(parcel, l);
                    break;

                case 1: // '\001'
                    s1 = com.google.android.gms.common.internal.safeparcel.a.o(parcel, l);
                    break;

                case 1000: 
                    k = com.google.android.gms.common.internal.safeparcel.a.g(parcel, l);
                    break;

                case 2: // '\002'
                    arraylist = com.google.android.gms.common.internal.safeparcel.a.B(parcel, l);
                    break;

                case 3: // '\003'
                    i = com.google.android.gms.common.internal.safeparcel.a.g(parcel, l);
                    break;

                case 4: // '\004'
                    s = com.google.android.gms.common.internal.safeparcel.a.o(parcel, l);
                    break;
                }
            } else
            if (parcel.dataPosition() != j)
            {
                throw new com.google.android.gms.common.internal.safeparcel.a.a((new StringBuilder()).append("Overread allowed size end=").append(j).toString(), parcel);
            } else
            {
                return new gy.a(k, s1, arraylist, i, s);
            }
        } while (true);
    }
}

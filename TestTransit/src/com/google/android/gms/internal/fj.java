// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;

// Referenced classes of package com.google.android.gms.internal:
//            fi

public class fj
    implements android.os.Parcelable.Creator
{

    public fj()
    {
    }

    static void a(fi fi1, Parcel parcel, int i)
    {
        int j = b.C(parcel);
        b.a(parcel, 1, fi1.xQ, false);
        b.c(parcel, 1000, fi1.xM);
        b.a(parcel, 2, fi1.xR, false);
        b.a(parcel, 3, fi1.xS, false);
        b.G(parcel, j);
    }

    public fi[] E(int i)
    {
        return new fi[i];
    }

    public Object createFromParcel(Parcel parcel)
    {
        return m(parcel);
    }

    public fi m(Parcel parcel)
    {
        String s = null;
        int i = com.google.android.gms.common.internal.safeparcel.a.B(parcel);
        int j = 0;
        String s1 = null;
        String s2 = null;
        do
        {
            if (parcel.dataPosition() < i)
            {
                int k = com.google.android.gms.common.internal.safeparcel.a.A(parcel);
                switch (com.google.android.gms.common.internal.safeparcel.a.ar(k))
                {
                default:
                    com.google.android.gms.common.internal.safeparcel.a.b(parcel, k);
                    break;

                case 1: // '\001'
                    s2 = com.google.android.gms.common.internal.safeparcel.a.o(parcel, k);
                    break;

                case 1000: 
                    j = com.google.android.gms.common.internal.safeparcel.a.g(parcel, k);
                    break;

                case 2: // '\002'
                    s1 = com.google.android.gms.common.internal.safeparcel.a.o(parcel, k);
                    break;

                case 3: // '\003'
                    s = com.google.android.gms.common.internal.safeparcel.a.o(parcel, k);
                    break;
                }
            } else
            if (parcel.dataPosition() != i)
            {
                throw new com.google.android.gms.common.internal.safeparcel.a.a((new StringBuilder()).append("Overread allowed size end=").append(i).toString(), parcel);
            } else
            {
                return new fi(j, s2, s1, s);
            }
        } while (true);
    }

    public Object[] newArray(int i)
    {
        return E(i);
    }
}

// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;

// Referenced classes of package com.google.android.gms.internal:
//            jw

public class jx
    implements android.os.Parcelable.Creator
{

    public jx()
    {
    }

    static void a(jw jw1, Parcel parcel, int i)
    {
        int j = b.C(parcel);
        b.a(parcel, 1, jw1.qX, false);
        b.c(parcel, 1000, jw1.xM);
        b.c(parcel, 2, jw1.YS);
        b.G(parcel, j);
    }

    public jw bA(Parcel parcel)
    {
        int i = 0;
        int j = com.google.android.gms.common.internal.safeparcel.a.B(parcel);
        String s = null;
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
                    s = com.google.android.gms.common.internal.safeparcel.a.o(parcel, l);
                    break;

                case 1000: 
                    k = com.google.android.gms.common.internal.safeparcel.a.g(parcel, l);
                    break;

                case 2: // '\002'
                    i = com.google.android.gms.common.internal.safeparcel.a.g(parcel, l);
                    break;
                }
            } else
            if (parcel.dataPosition() != j)
            {
                throw new com.google.android.gms.common.internal.safeparcel.a.a((new StringBuilder()).append("Overread allowed size end=").append(j).toString(), parcel);
            } else
            {
                return new jw(k, s, i);
            }
        } while (true);
    }

    public jw[] cV(int i)
    {
        return new jw[i];
    }

    public Object createFromParcel(Parcel parcel)
    {
        return bA(parcel);
    }

    public Object[] newArray(int i)
    {
        return cV(i);
    }
}

// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.location;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;

// Referenced classes of package com.google.android.gms.location:
//            b

public class c
    implements android.os.Parcelable.Creator
{

    public c()
    {
    }

    static void a(com.google.android.gms.location.b b1, Parcel parcel, int i)
    {
        int j = b.C(parcel);
        b.c(parcel, 1, b1.Vq);
        b.c(parcel, 1000, b1.getVersionCode());
        b.c(parcel, 2, b1.Vr);
        b.a(parcel, 3, b1.Vs);
        b.G(parcel, j);
    }

    public com.google.android.gms.location.b bs(Parcel parcel)
    {
        int i = 1;
        int j = com.google.android.gms.common.internal.safeparcel.a.B(parcel);
        int k = 0;
        long l = 0L;
        int i1 = i;
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

                case 1000: 
                    k = com.google.android.gms.common.internal.safeparcel.a.g(parcel, j1);
                    break;

                case 2: // '\002'
                    i = com.google.android.gms.common.internal.safeparcel.a.g(parcel, j1);
                    break;

                case 3: // '\003'
                    l = com.google.android.gms.common.internal.safeparcel.a.i(parcel, j1);
                    break;
                }
            } else
            if (parcel.dataPosition() != j)
            {
                throw new com.google.android.gms.common.internal.safeparcel.a.a((new StringBuilder()).append("Overread allowed size end=").append(j).toString(), parcel);
            } else
            {
                return new com.google.android.gms.location.b(k, i1, i, l);
            }
        } while (true);
    }

    public com.google.android.gms.location.b[] cL(int i)
    {
        return new com.google.android.gms.location.b[i];
    }

    public Object createFromParcel(Parcel parcel)
    {
        return bs(parcel);
    }

    public Object[] newArray(int i)
    {
        return cL(i);
    }
}

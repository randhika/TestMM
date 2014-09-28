// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.plus.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;

// Referenced classes of package com.google.android.gms.plus.internal:
//            PlusCommonExtras

public class f
    implements android.os.Parcelable.Creator
{

    public f()
    {
    }

    static void a(PlusCommonExtras pluscommonextras, Parcel parcel, int i)
    {
        int j = b.C(parcel);
        b.a(parcel, 1, pluscommonextras.jX(), false);
        b.c(parcel, 1000, pluscommonextras.getVersionCode());
        b.a(parcel, 2, pluscommonextras.jY(), false);
        b.G(parcel, j);
    }

    public PlusCommonExtras bC(Parcel parcel)
    {
        String s = null;
        int i = com.google.android.gms.common.internal.safeparcel.a.B(parcel);
        int j = 0;
        String s1 = null;
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
                    s1 = com.google.android.gms.common.internal.safeparcel.a.o(parcel, k);
                    break;

                case 1000: 
                    j = com.google.android.gms.common.internal.safeparcel.a.g(parcel, k);
                    break;

                case 2: // '\002'
                    s = com.google.android.gms.common.internal.safeparcel.a.o(parcel, k);
                    break;
                }
            } else
            if (parcel.dataPosition() != i)
            {
                throw new com.google.android.gms.common.internal.safeparcel.a.a((new StringBuilder()).append("Overread allowed size end=").append(i).toString(), parcel);
            } else
            {
                return new PlusCommonExtras(j, s1, s);
            }
        } while (true);
    }

    public PlusCommonExtras[] cZ(int i)
    {
        return new PlusCommonExtras[i];
    }

    public Object createFromParcel(Parcel parcel)
    {
        return bC(parcel);
    }

    public Object[] newArray(int i)
    {
        return cZ(i);
    }
}

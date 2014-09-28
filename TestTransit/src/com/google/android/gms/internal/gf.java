// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;

// Referenced classes of package com.google.android.gms.internal:
//            ge

public class gf
    implements android.os.Parcelable.Creator
{

    public gf()
    {
    }

    static void a(ge ge1, Parcel parcel, int i)
    {
        int j = b.C(parcel);
        b.c(parcel, 1, ge1.getVersionCode());
        b.a(parcel, 2, ge1.ec(), false);
        b.G(parcel, j);
    }

    public ge[] S(int i)
    {
        return new ge[i];
    }

    public Object createFromParcel(Parcel parcel)
    {
        return u(parcel);
    }

    public Object[] newArray(int i)
    {
        return S(i);
    }

    public ge u(Parcel parcel)
    {
        int i = com.google.android.gms.common.internal.safeparcel.a.B(parcel);
        int j = 0;
        String s = null;
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
                return new ge(j, s);
            }
        } while (true);
    }
}

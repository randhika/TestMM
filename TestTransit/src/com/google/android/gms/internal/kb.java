// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;

// Referenced classes of package com.google.android.gms.internal:
//            ka

public class kb
    implements android.os.Parcelable.Creator
{

    public kb()
    {
    }

    static void a(ka ka1, Parcel parcel, int i)
    {
        int j = b.C(parcel);
        b.a(parcel, 1, ka1.YV, false);
        b.c(parcel, 1000, ka1.versionCode);
        b.a(parcel, 2, ka1.YW, false);
        b.G(parcel, j);
    }

    public ka bB(Parcel parcel)
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
                return new ka(j, s1, s);
            }
        } while (true);
    }

    public ka[] cW(int i)
    {
        return new ka[i];
    }

    public Object createFromParcel(Parcel parcel)
    {
        return bB(parcel);
    }

    public Object[] newArray(int i)
    {
        return cW(i);
    }
}

// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.wearable.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;

// Referenced classes of package com.google.android.gms.wearable.internal:
//            aq

public class ar
    implements android.os.Parcelable.Creator
{

    public ar()
    {
    }

    static void a(aq aq1, Parcel parcel, int i)
    {
        int j = b.C(parcel);
        b.c(parcel, 1, aq1.versionCode);
        b.c(parcel, 2, aq1.statusCode);
        b.c(parcel, 3, aq1.amc);
        b.G(parcel, j);
    }

    public aq cJ(Parcel parcel)
    {
        int i = 0;
        int j = com.google.android.gms.common.internal.safeparcel.a.B(parcel);
        int k = 0;
        int l = 0;
        do
        {
            if (parcel.dataPosition() < j)
            {
                int i1 = com.google.android.gms.common.internal.safeparcel.a.A(parcel);
                switch (com.google.android.gms.common.internal.safeparcel.a.ar(i1))
                {
                default:
                    com.google.android.gms.common.internal.safeparcel.a.b(parcel, i1);
                    break;

                case 1: // '\001'
                    l = com.google.android.gms.common.internal.safeparcel.a.g(parcel, i1);
                    break;

                case 2: // '\002'
                    k = com.google.android.gms.common.internal.safeparcel.a.g(parcel, i1);
                    break;

                case 3: // '\003'
                    i = com.google.android.gms.common.internal.safeparcel.a.g(parcel, i1);
                    break;
                }
            } else
            if (parcel.dataPosition() != j)
            {
                throw new com.google.android.gms.common.internal.safeparcel.a.a((new StringBuilder()).append("Overread allowed size end=").append(j).toString(), parcel);
            } else
            {
                return new aq(l, k, i);
            }
        } while (true);
    }

    public Object createFromParcel(Parcel parcel)
    {
        return cJ(parcel);
    }

    public aq[] es(int i)
    {
        return new aq[i];
    }

    public Object[] newArray(int i)
    {
        return es(i);
    }
}

// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.wallet;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;

// Referenced classes of package com.google.android.gms.wallet:
//            ProxyCard

public class o
    implements android.os.Parcelable.Creator
{

    public o()
    {
    }

    static void a(ProxyCard proxycard, Parcel parcel, int i)
    {
        int j = b.C(parcel);
        b.c(parcel, 1, proxycard.getVersionCode());
        b.a(parcel, 2, proxycard.ajV, false);
        b.a(parcel, 3, proxycard.ajW, false);
        b.c(parcel, 4, proxycard.ajX);
        b.c(parcel, 5, proxycard.ajY);
        b.G(parcel, j);
    }

    public ProxyCard cd(Parcel parcel)
    {
        String s = null;
        int i = 0;
        int j = com.google.android.gms.common.internal.safeparcel.a.B(parcel);
        int k = 0;
        String s1 = null;
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
                    s1 = com.google.android.gms.common.internal.safeparcel.a.o(parcel, i1);
                    break;

                case 3: // '\003'
                    s = com.google.android.gms.common.internal.safeparcel.a.o(parcel, i1);
                    break;

                case 4: // '\004'
                    k = com.google.android.gms.common.internal.safeparcel.a.g(parcel, i1);
                    break;

                case 5: // '\005'
                    i = com.google.android.gms.common.internal.safeparcel.a.g(parcel, i1);
                    break;
                }
            } else
            if (parcel.dataPosition() != j)
            {
                throw new com.google.android.gms.common.internal.safeparcel.a.a((new StringBuilder()).append("Overread allowed size end=").append(j).toString(), parcel);
            } else
            {
                return new ProxyCard(l, s1, s, k, i);
            }
        } while (true);
    }

    public Object createFromParcel(Parcel parcel)
    {
        return cd(parcel);
    }

    public ProxyCard[] dJ(int i)
    {
        return new ProxyCard[i];
    }

    public Object[] newArray(int i)
    {
        return dJ(i);
    }
}

// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.wallet.fragment;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;

// Referenced classes of package com.google.android.gms.wallet.fragment:
//            WalletFragmentStyle

public class c
    implements android.os.Parcelable.Creator
{

    public c()
    {
    }

    static void a(WalletFragmentStyle walletfragmentstyle, Parcel parcel, int i)
    {
        int j = b.C(parcel);
        b.c(parcel, 1, walletfragmentstyle.xM);
        b.a(parcel, 2, walletfragmentstyle.akE, false);
        b.c(parcel, 3, walletfragmentstyle.akF);
        b.G(parcel, j);
    }

    public WalletFragmentStyle ci(Parcel parcel)
    {
        int i = 0;
        int j = com.google.android.gms.common.internal.safeparcel.a.B(parcel);
        android.os.Bundle bundle = null;
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
                    k = com.google.android.gms.common.internal.safeparcel.a.g(parcel, l);
                    break;

                case 2: // '\002'
                    bundle = com.google.android.gms.common.internal.safeparcel.a.q(parcel, l);
                    break;

                case 3: // '\003'
                    i = com.google.android.gms.common.internal.safeparcel.a.g(parcel, l);
                    break;
                }
            } else
            if (parcel.dataPosition() != j)
            {
                throw new com.google.android.gms.common.internal.safeparcel.a.a((new StringBuilder()).append("Overread allowed size end=").append(j).toString(), parcel);
            } else
            {
                return new WalletFragmentStyle(k, bundle, i);
            }
        } while (true);
    }

    public Object createFromParcel(Parcel parcel)
    {
        return ci(parcel);
    }

    public WalletFragmentStyle[] dP(int i)
    {
        return new WalletFragmentStyle[i];
    }

    public Object[] newArray(int i)
    {
        return dP(i);
    }
}

// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.wallet;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;

// Referenced classes of package com.google.android.gms.wallet:
//            InstrumentInfo

public class h
    implements android.os.Parcelable.Creator
{

    public h()
    {
    }

    static void a(InstrumentInfo instrumentinfo, Parcel parcel, int i)
    {
        int j = b.C(parcel);
        b.c(parcel, 1, instrumentinfo.getVersionCode());
        b.a(parcel, 2, instrumentinfo.getInstrumentType(), false);
        b.a(parcel, 3, instrumentinfo.getInstrumentDetails(), false);
        b.G(parcel, j);
    }

    public InstrumentInfo bW(Parcel parcel)
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
                return new InstrumentInfo(j, s1, s);
            }
        } while (true);
    }

    public Object createFromParcel(Parcel parcel)
    {
        return bW(parcel);
    }

    public InstrumentInfo[] dC(int i)
    {
        return new InstrumentInfo[i];
    }

    public Object[] newArray(int i)
    {
        return dC(i);
    }
}

// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.wallet;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.b;

// Referenced classes of package com.google.android.gms.wallet:
//            Address

public class a
    implements android.os.Parcelable.Creator
{

    public a()
    {
    }

    static void a(Address address, Parcel parcel, int i)
    {
        int j = b.C(parcel);
        b.c(parcel, 1, address.getVersionCode());
        b.a(parcel, 2, address.name, false);
        b.a(parcel, 3, address.UH, false);
        b.a(parcel, 4, address.UI, false);
        b.a(parcel, 5, address.UJ, false);
        b.a(parcel, 6, address.rf, false);
        b.a(parcel, 7, address.aiI, false);
        b.a(parcel, 8, address.aiJ, false);
        b.a(parcel, 9, address.UO, false);
        b.a(parcel, 10, address.UQ, false);
        b.a(parcel, 11, address.UR);
        b.a(parcel, 12, address.US, false);
        b.G(parcel, j);
    }

    public Address bQ(Parcel parcel)
    {
        int i = com.google.android.gms.common.internal.safeparcel.a.B(parcel);
        int j = 0;
        String s = null;
        String s1 = null;
        String s2 = null;
        String s3 = null;
        String s4 = null;
        String s5 = null;
        String s6 = null;
        String s7 = null;
        String s8 = null;
        boolean flag = false;
        String s9 = null;
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

                case 3: // '\003'
                    s1 = com.google.android.gms.common.internal.safeparcel.a.o(parcel, k);
                    break;

                case 4: // '\004'
                    s2 = com.google.android.gms.common.internal.safeparcel.a.o(parcel, k);
                    break;

                case 5: // '\005'
                    s3 = com.google.android.gms.common.internal.safeparcel.a.o(parcel, k);
                    break;

                case 6: // '\006'
                    s4 = com.google.android.gms.common.internal.safeparcel.a.o(parcel, k);
                    break;

                case 7: // '\007'
                    s5 = com.google.android.gms.common.internal.safeparcel.a.o(parcel, k);
                    break;

                case 8: // '\b'
                    s6 = com.google.android.gms.common.internal.safeparcel.a.o(parcel, k);
                    break;

                case 9: // '\t'
                    s7 = com.google.android.gms.common.internal.safeparcel.a.o(parcel, k);
                    break;

                case 10: // '\n'
                    s8 = com.google.android.gms.common.internal.safeparcel.a.o(parcel, k);
                    break;

                case 11: // '\013'
                    flag = com.google.android.gms.common.internal.safeparcel.a.c(parcel, k);
                    break;

                case 12: // '\f'
                    s9 = com.google.android.gms.common.internal.safeparcel.a.o(parcel, k);
                    break;
                }
            } else
            if (parcel.dataPosition() != i)
            {
                throw new com.google.android.gms.common.internal.safeparcel.a((new StringBuilder()).append("Overread allowed size end=").append(i).toString(), parcel);
            } else
            {
                return new Address(j, s, s1, s2, s3, s4, s5, s6, s7, s8, flag, s9);
            }
        } while (true);
    }

    public Object createFromParcel(Parcel parcel)
    {
        return bQ(parcel);
    }

    public Address[] dw(int i)
    {
        return new Address[i];
    }

    public Object[] newArray(int i)
    {
        return dw(i);
    }
}

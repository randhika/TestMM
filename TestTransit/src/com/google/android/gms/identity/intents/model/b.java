// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.identity.intents.model;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.a;

// Referenced classes of package com.google.android.gms.identity.intents.model:
//            UserAddress

public class b
    implements android.os.Parcelable.Creator
{

    public b()
    {
    }

    static void a(UserAddress useraddress, Parcel parcel, int i)
    {
        int j = com.google.android.gms.common.internal.safeparcel.b.C(parcel);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 1, useraddress.getVersionCode());
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 2, useraddress.name, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 3, useraddress.UH, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 4, useraddress.UI, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 5, useraddress.UJ, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 6, useraddress.UK, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 7, useraddress.UL, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 8, useraddress.UM, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 9, useraddress.UN, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 10, useraddress.rf, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 11, useraddress.UO, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 12, useraddress.UP, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 13, useraddress.UQ, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 14, useraddress.UR);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 15, useraddress.US, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 16, useraddress.UT, false);
        com.google.android.gms.common.internal.safeparcel.b.G(parcel, j);
    }

    public UserAddress br(Parcel parcel)
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
        String s9 = null;
        String s10 = null;
        String s11 = null;
        boolean flag = false;
        String s12 = null;
        String s13 = null;
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
                    s9 = com.google.android.gms.common.internal.safeparcel.a.o(parcel, k);
                    break;

                case 12: // '\f'
                    s10 = com.google.android.gms.common.internal.safeparcel.a.o(parcel, k);
                    break;

                case 13: // '\r'
                    s11 = com.google.android.gms.common.internal.safeparcel.a.o(parcel, k);
                    break;

                case 14: // '\016'
                    flag = com.google.android.gms.common.internal.safeparcel.a.c(parcel, k);
                    break;

                case 15: // '\017'
                    s12 = com.google.android.gms.common.internal.safeparcel.a.o(parcel, k);
                    break;

                case 16: // '\020'
                    s13 = com.google.android.gms.common.internal.safeparcel.a.o(parcel, k);
                    break;
                }
            } else
            if (parcel.dataPosition() != i)
            {
                throw new com.google.android.gms.common.internal.safeparcel.a.a((new StringBuilder()).append("Overread allowed size end=").append(i).toString(), parcel);
            } else
            {
                return new UserAddress(j, s, s1, s2, s3, s4, s5, s6, s7, s8, s9, s10, s11, flag, s12, s13);
            }
        } while (true);
    }

    public UserAddress[] cE(int i)
    {
        return new UserAddress[i];
    }

    public Object createFromParcel(Parcel parcel)
    {
        return br(parcel);
    }

    public Object[] newArray(int i)
    {
        return cE(i);
    }
}

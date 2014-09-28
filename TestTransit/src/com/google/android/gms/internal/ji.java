// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;

// Referenced classes of package com.google.android.gms.internal:
//            jh

public class ji
    implements android.os.Parcelable.Creator
{

    public ji()
    {
    }

    static void a(jh jh1, Parcel parcel, int i)
    {
        int j = b.C(parcel);
        b.a(parcel, 1, jh1.getRequestId(), false);
        b.c(parcel, 1000, jh1.getVersionCode());
        b.a(parcel, 2, jh1.getExpirationTime());
        b.a(parcel, 3, jh1.ja());
        b.a(parcel, 4, jh1.getLatitude());
        b.a(parcel, 5, jh1.getLongitude());
        b.a(parcel, 6, jh1.jb());
        b.c(parcel, 7, jh1.jc());
        b.c(parcel, 8, jh1.getNotificationResponsiveness());
        b.c(parcel, 9, jh1.jd());
        b.G(parcel, j);
    }

    public jh bt(Parcel parcel)
    {
        int i = com.google.android.gms.common.internal.safeparcel.a.B(parcel);
        int j = 0;
        String s = null;
        int k = 0;
        short word0 = 0;
        double d = 0.0D;
        double d1 = 0.0D;
        float f = 0.0F;
        long l = 0L;
        int i1 = 0;
        int j1 = -1;
        do
        {
            if (parcel.dataPosition() < i)
            {
                int k1 = com.google.android.gms.common.internal.safeparcel.a.A(parcel);
                switch (com.google.android.gms.common.internal.safeparcel.a.ar(k1))
                {
                default:
                    com.google.android.gms.common.internal.safeparcel.a.b(parcel, k1);
                    break;

                case 1: // '\001'
                    s = com.google.android.gms.common.internal.safeparcel.a.o(parcel, k1);
                    break;

                case 1000: 
                    j = com.google.android.gms.common.internal.safeparcel.a.g(parcel, k1);
                    break;

                case 2: // '\002'
                    l = com.google.android.gms.common.internal.safeparcel.a.i(parcel, k1);
                    break;

                case 3: // '\003'
                    word0 = com.google.android.gms.common.internal.safeparcel.a.f(parcel, k1);
                    break;

                case 4: // '\004'
                    d = com.google.android.gms.common.internal.safeparcel.a.m(parcel, k1);
                    break;

                case 5: // '\005'
                    d1 = com.google.android.gms.common.internal.safeparcel.a.m(parcel, k1);
                    break;

                case 6: // '\006'
                    f = com.google.android.gms.common.internal.safeparcel.a.l(parcel, k1);
                    break;

                case 7: // '\007'
                    k = com.google.android.gms.common.internal.safeparcel.a.g(parcel, k1);
                    break;

                case 8: // '\b'
                    i1 = com.google.android.gms.common.internal.safeparcel.a.g(parcel, k1);
                    break;

                case 9: // '\t'
                    j1 = com.google.android.gms.common.internal.safeparcel.a.g(parcel, k1);
                    break;
                }
            } else
            if (parcel.dataPosition() != i)
            {
                throw new com.google.android.gms.common.internal.safeparcel.a.a((new StringBuilder()).append("Overread allowed size end=").append(i).toString(), parcel);
            } else
            {
                return new jh(j, s, k, word0, d, d1, f, l, i1, j1);
            }
        } while (true);
    }

    public jh[] cO(int i)
    {
        return new jh[i];
    }

    public Object createFromParcel(Parcel parcel)
    {
        return bt(parcel);
    }

    public Object[] newArray(int i)
    {
        return cO(i);
    }
}

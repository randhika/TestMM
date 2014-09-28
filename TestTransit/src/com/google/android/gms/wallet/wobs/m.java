// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.wallet.wobs;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;

// Referenced classes of package com.google.android.gms.wallet.wobs:
//            l

public class m
    implements android.os.Parcelable.Creator
{

    public m()
    {
    }

    static void a(l l1, Parcel parcel, int i)
    {
        int j = b.C(parcel);
        b.c(parcel, 1, l1.getVersionCode());
        b.a(parcel, 2, l1.ala);
        b.a(parcel, 3, l1.alb);
        b.G(parcel, j);
    }

    public l cp(Parcel parcel)
    {
        long l1 = 0L;
        int i = com.google.android.gms.common.internal.safeparcel.a.B(parcel);
        int j = 0;
        long l2 = l1;
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
                    l2 = com.google.android.gms.common.internal.safeparcel.a.i(parcel, k);
                    break;

                case 3: // '\003'
                    l1 = com.google.android.gms.common.internal.safeparcel.a.i(parcel, k);
                    break;
                }
            } else
            if (parcel.dataPosition() != i)
            {
                throw new com.google.android.gms.common.internal.safeparcel.a.a((new StringBuilder()).append("Overread allowed size end=").append(i).toString(), parcel);
            } else
            {
                return new l(j, l2, l1);
            }
        } while (true);
    }

    public Object createFromParcel(Parcel parcel)
    {
        return cp(parcel);
    }

    public l[] dX(int i)
    {
        return new l[i];
    }

    public Object[] newArray(int i)
    {
        return dX(i);
    }
}

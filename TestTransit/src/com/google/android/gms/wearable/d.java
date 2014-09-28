// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.wearable;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;

// Referenced classes of package com.google.android.gms.wearable:
//            c

public class d
    implements android.os.Parcelable.Creator
{

    public d()
    {
    }

    static void a(c c1, Parcel parcel, int i)
    {
        int j = b.C(parcel);
        b.c(parcel, 1, c1.xM);
        b.a(parcel, 2, c1.getName(), false);
        b.a(parcel, 3, c1.getAddress(), false);
        b.c(parcel, 4, c1.getType());
        b.c(parcel, 5, c1.getRole());
        b.a(parcel, 6, c1.isEnabled());
        b.G(parcel, j);
    }

    public Object createFromParcel(Parcel parcel)
    {
        return ct(parcel);
    }

    public c ct(Parcel parcel)
    {
        String s = null;
        boolean flag = false;
        int i = com.google.android.gms.common.internal.safeparcel.a.B(parcel);
        int j = 0;
        int k = 0;
        String s1 = null;
        int l = 0;
        do
        {
            if (parcel.dataPosition() < i)
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
                    j = com.google.android.gms.common.internal.safeparcel.a.g(parcel, i1);
                    break;

                case 6: // '\006'
                    flag = com.google.android.gms.common.internal.safeparcel.a.c(parcel, i1);
                    break;
                }
            } else
            if (parcel.dataPosition() != i)
            {
                throw new com.google.android.gms.common.internal.safeparcel.a.a((new StringBuilder()).append("Overread allowed size end=").append(i).toString(), parcel);
            } else
            {
                return new c(l, s1, s, k, j, flag);
            }
        } while (true);
    }

    public c[] eb(int i)
    {
        return new c[i];
    }

    public Object[] newArray(int i)
    {
        return eb(i);
    }
}

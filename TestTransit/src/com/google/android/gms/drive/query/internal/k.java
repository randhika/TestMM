// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.drive.query.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;

// Referenced classes of package com.google.android.gms.drive.query.internal:
//            Operator

public class k
    implements android.os.Parcelable.Creator
{

    public k()
    {
    }

    static void a(Operator operator, Parcel parcel, int i)
    {
        int j = b.C(parcel);
        b.c(parcel, 1000, operator.xM);
        b.a(parcel, 1, operator.mTag, false);
        b.G(parcel, j);
    }

    public Operator aO(Parcel parcel)
    {
        int i = com.google.android.gms.common.internal.safeparcel.a.B(parcel);
        int j = 0;
        String s = null;
        do
        {
            if (parcel.dataPosition() < i)
            {
                int l = com.google.android.gms.common.internal.safeparcel.a.A(parcel);
                switch (com.google.android.gms.common.internal.safeparcel.a.ar(l))
                {
                default:
                    com.google.android.gms.common.internal.safeparcel.a.b(parcel, l);
                    break;

                case 1000: 
                    j = com.google.android.gms.common.internal.safeparcel.a.g(parcel, l);
                    break;

                case 1: // '\001'
                    s = com.google.android.gms.common.internal.safeparcel.a.o(parcel, l);
                    break;
                }
            } else
            if (parcel.dataPosition() != i)
            {
                throw new com.google.android.gms.common.internal.safeparcel.a.a((new StringBuilder()).append("Overread allowed size end=").append(i).toString(), parcel);
            } else
            {
                return new Operator(j, s);
            }
        } while (true);
    }

    public Operator[] bK(int i)
    {
        return new Operator[i];
    }

    public Object createFromParcel(Parcel parcel)
    {
        return aO(parcel);
    }

    public Object[] newArray(int i)
    {
        return bK(i);
    }
}

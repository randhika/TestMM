// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.drive.query.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;

// Referenced classes of package com.google.android.gms.drive.query.internal:
//            FieldWithSortOrder

public class c
    implements android.os.Parcelable.Creator
{

    public c()
    {
    }

    static void a(FieldWithSortOrder fieldwithsortorder, Parcel parcel, int i)
    {
        int j = b.C(parcel);
        b.c(parcel, 1000, fieldwithsortorder.xM);
        b.a(parcel, 1, fieldwithsortorder.JH, false);
        b.a(parcel, 2, fieldwithsortorder.KO);
        b.G(parcel, j);
    }

    public FieldWithSortOrder aH(Parcel parcel)
    {
        boolean flag = false;
        int i = com.google.android.gms.common.internal.safeparcel.a.B(parcel);
        String s = null;
        int j = 0;
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

                case 1000: 
                    j = com.google.android.gms.common.internal.safeparcel.a.g(parcel, k);
                    break;

                case 1: // '\001'
                    s = com.google.android.gms.common.internal.safeparcel.a.o(parcel, k);
                    break;

                case 2: // '\002'
                    flag = com.google.android.gms.common.internal.safeparcel.a.c(parcel, k);
                    break;
                }
            } else
            if (parcel.dataPosition() != i)
            {
                throw new com.google.android.gms.common.internal.safeparcel.a.a((new StringBuilder()).append("Overread allowed size end=").append(i).toString(), parcel);
            } else
            {
                return new FieldWithSortOrder(j, s, flag);
            }
        } while (true);
    }

    public FieldWithSortOrder[] bD(int i)
    {
        return new FieldWithSortOrder[i];
    }

    public Object createFromParcel(Parcel parcel)
    {
        return aH(parcel);
    }

    public Object[] newArray(int i)
    {
        return bD(i);
    }
}

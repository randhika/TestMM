// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.drive.query;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.drive.query.internal.FieldWithSortOrder;

// Referenced classes of package com.google.android.gms.drive.query:
//            SortOrder

public class b
    implements android.os.Parcelable.Creator
{

    public b()
    {
    }

    static void a(SortOrder sortorder, Parcel parcel, int i)
    {
        int j = com.google.android.gms.common.internal.safeparcel.b.C(parcel);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 1000, sortorder.xM);
        com.google.android.gms.common.internal.safeparcel.b.b(parcel, 1, sortorder.KK, false);
        com.google.android.gms.common.internal.safeparcel.b.G(parcel, j);
    }

    public SortOrder aE(Parcel parcel)
    {
        int i = com.google.android.gms.common.internal.safeparcel.a.B(parcel);
        int j = 0;
        java.util.ArrayList arraylist = null;
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
                    arraylist = com.google.android.gms.common.internal.safeparcel.a.c(parcel, k, FieldWithSortOrder.CREATOR);
                    break;
                }
            } else
            if (parcel.dataPosition() != i)
            {
                throw new com.google.android.gms.common.internal.safeparcel.a.a((new StringBuilder()).append("Overread allowed size end=").append(i).toString(), parcel);
            } else
            {
                return new SortOrder(j, arraylist);
            }
        } while (true);
    }

    public SortOrder[] bA(int i)
    {
        return new SortOrder[i];
    }

    public Object createFromParcel(Parcel parcel)
    {
        return aE(parcel);
    }

    public Object[] newArray(int i)
    {
        return bA(i);
    }
}

// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.drive.realtime.internal.event;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;

// Referenced classes of package com.google.android.gms.drive.realtime.internal.event:
//            TextInsertedDetails

public class f
    implements android.os.Parcelable.Creator
{

    public f()
    {
    }

    static void a(TextInsertedDetails textinserteddetails, Parcel parcel, int i)
    {
        int j = b.C(parcel);
        b.c(parcel, 1, textinserteddetails.xM);
        b.c(parcel, 2, textinserteddetails.mIndex);
        b.c(parcel, 3, textinserteddetails.LL);
        b.G(parcel, j);
    }

    public TextInsertedDetails aY(Parcel parcel)
    {
        int i = 0;
        int j = com.google.android.gms.common.internal.safeparcel.a.B(parcel);
        int k = 0;
        int l = 0;
        do
        {
            if (parcel.dataPosition() < j)
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
                    k = com.google.android.gms.common.internal.safeparcel.a.g(parcel, i1);
                    break;

                case 3: // '\003'
                    i = com.google.android.gms.common.internal.safeparcel.a.g(parcel, i1);
                    break;
                }
            } else
            if (parcel.dataPosition() != j)
            {
                throw new com.google.android.gms.common.internal.safeparcel.a.a((new StringBuilder()).append("Overread allowed size end=").append(j).toString(), parcel);
            } else
            {
                return new TextInsertedDetails(l, k, i);
            }
        } while (true);
    }

    public TextInsertedDetails[] bV(int i)
    {
        return new TextInsertedDetails[i];
    }

    public Object createFromParcel(Parcel parcel)
    {
        return aY(parcel);
    }

    public Object[] newArray(int i)
    {
        return bV(i);
    }
}

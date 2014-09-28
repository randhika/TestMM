// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.drive.realtime.internal.event;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;

// Referenced classes of package com.google.android.gms.drive.realtime.internal.event:
//            ValuesRemovedDetails

public class i
    implements android.os.Parcelable.Creator
{

    public i()
    {
    }

    static void a(ValuesRemovedDetails valuesremoveddetails, Parcel parcel, int j)
    {
        int k = b.C(parcel);
        b.c(parcel, 1, valuesremoveddetails.xM);
        b.c(parcel, 2, valuesremoveddetails.mIndex);
        b.c(parcel, 3, valuesremoveddetails.LF);
        b.c(parcel, 4, valuesremoveddetails.LG);
        b.a(parcel, 5, valuesremoveddetails.LP, false);
        b.c(parcel, 6, valuesremoveddetails.LQ);
        b.G(parcel, k);
    }

    public ValuesRemovedDetails[] bY(int j)
    {
        return new ValuesRemovedDetails[j];
    }

    public ValuesRemovedDetails bb(Parcel parcel)
    {
        int j = 0;
        int k = com.google.android.gms.common.internal.safeparcel.a.B(parcel);
        String s = null;
        int l = 0;
        int i1 = 0;
        int j1 = 0;
        int k1 = 0;
        do
        {
            if (parcel.dataPosition() < k)
            {
                int l1 = com.google.android.gms.common.internal.safeparcel.a.A(parcel);
                switch (com.google.android.gms.common.internal.safeparcel.a.ar(l1))
                {
                default:
                    com.google.android.gms.common.internal.safeparcel.a.b(parcel, l1);
                    break;

                case 1: // '\001'
                    k1 = com.google.android.gms.common.internal.safeparcel.a.g(parcel, l1);
                    break;

                case 2: // '\002'
                    j1 = com.google.android.gms.common.internal.safeparcel.a.g(parcel, l1);
                    break;

                case 3: // '\003'
                    i1 = com.google.android.gms.common.internal.safeparcel.a.g(parcel, l1);
                    break;

                case 4: // '\004'
                    l = com.google.android.gms.common.internal.safeparcel.a.g(parcel, l1);
                    break;

                case 5: // '\005'
                    s = com.google.android.gms.common.internal.safeparcel.a.o(parcel, l1);
                    break;

                case 6: // '\006'
                    j = com.google.android.gms.common.internal.safeparcel.a.g(parcel, l1);
                    break;
                }
            } else
            if (parcel.dataPosition() != k)
            {
                throw new com.google.android.gms.common.internal.safeparcel.a.a((new StringBuilder()).append("Overread allowed size end=").append(k).toString(), parcel);
            } else
            {
                return new ValuesRemovedDetails(k1, j1, i1, l, s, j);
            }
        } while (true);
    }

    public Object createFromParcel(Parcel parcel)
    {
        return bb(parcel);
    }

    public Object[] newArray(int j)
    {
        return bY(j);
    }
}

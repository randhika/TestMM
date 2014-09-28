// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.location;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;

// Referenced classes of package com.google.android.gms.location:
//            LocationRequest

public class LocationRequestCreator
    implements android.os.Parcelable.Creator
{

    public static final int CONTENT_DESCRIPTION;

    public LocationRequestCreator()
    {
    }

    static void a(LocationRequest locationrequest, Parcel parcel, int i)
    {
        int j = b.C(parcel);
        b.c(parcel, 1, locationrequest.mPriority);
        b.c(parcel, 1000, locationrequest.getVersionCode());
        b.a(parcel, 2, locationrequest.Vl);
        b.a(parcel, 3, locationrequest.Vm);
        b.a(parcel, 4, locationrequest.Vn);
        b.a(parcel, 5, locationrequest.Vb);
        b.c(parcel, 6, locationrequest.Vo);
        b.a(parcel, 7, locationrequest.Vp);
        b.G(parcel, j);
    }

    public LocationRequest createFromParcel(Parcel parcel)
    {
        boolean flag = false;
        int i = com.google.android.gms.common.internal.safeparcel.a.B(parcel);
        int j = 102;
        long l = 0x36ee80L;
        long l1 = 0x927c0L;
        long l2 = 0x7fffffffffffffffL;
        int k = 0x7fffffff;
        float f = 0.0F;
        int i1 = 0;
        do
        {
            if (parcel.dataPosition() < i)
            {
                int j1 = com.google.android.gms.common.internal.safeparcel.a.A(parcel);
                switch (com.google.android.gms.common.internal.safeparcel.a.ar(j1))
                {
                default:
                    com.google.android.gms.common.internal.safeparcel.a.b(parcel, j1);
                    break;

                case 1: // '\001'
                    j = com.google.android.gms.common.internal.safeparcel.a.g(parcel, j1);
                    break;

                case 1000: 
                    i1 = com.google.android.gms.common.internal.safeparcel.a.g(parcel, j1);
                    break;

                case 2: // '\002'
                    l = com.google.android.gms.common.internal.safeparcel.a.i(parcel, j1);
                    break;

                case 3: // '\003'
                    l1 = com.google.android.gms.common.internal.safeparcel.a.i(parcel, j1);
                    break;

                case 4: // '\004'
                    flag = com.google.android.gms.common.internal.safeparcel.a.c(parcel, j1);
                    break;

                case 5: // '\005'
                    l2 = com.google.android.gms.common.internal.safeparcel.a.i(parcel, j1);
                    break;

                case 6: // '\006'
                    k = com.google.android.gms.common.internal.safeparcel.a.g(parcel, j1);
                    break;

                case 7: // '\007'
                    f = com.google.android.gms.common.internal.safeparcel.a.l(parcel, j1);
                    break;
                }
            } else
            if (parcel.dataPosition() != i)
            {
                throw new com.google.android.gms.common.internal.safeparcel.a.a((new StringBuilder()).append("Overread allowed size end=").append(i).toString(), parcel);
            } else
            {
                return new LocationRequest(i1, j, l, l1, flag, l2, k, f);
            }
        } while (true);
    }

    public volatile Object createFromParcel(Parcel parcel)
    {
        return createFromParcel(parcel);
    }

    public LocationRequest[] newArray(int i)
    {
        return new LocationRequest[i];
    }

    public volatile Object[] newArray(int i)
    {
        return newArray(i);
    }
}

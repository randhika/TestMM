// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.drive.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.drive.StorageStats;

// Referenced classes of package com.google.android.gms.drive.internal:
//            OnStorageStatsResponse

public class ap
    implements android.os.Parcelable.Creator
{

    public ap()
    {
    }

    static void a(OnStorageStatsResponse onstoragestatsresponse, Parcel parcel, int i)
    {
        int j = b.C(parcel);
        b.c(parcel, 1, onstoragestatsresponse.xM);
        b.a(parcel, 2, onstoragestatsresponse.JD, i, false);
        b.G(parcel, j);
    }

    public OnStorageStatsResponse aq(Parcel parcel)
    {
        int i = com.google.android.gms.common.internal.safeparcel.a.B(parcel);
        int j = 0;
        StorageStats storagestats = null;
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
                    storagestats = (StorageStats)com.google.android.gms.common.internal.safeparcel.a.a(parcel, k, StorageStats.CREATOR);
                    break;
                }
            } else
            if (parcel.dataPosition() != i)
            {
                throw new com.google.android.gms.common.internal.safeparcel.a.a((new StringBuilder()).append("Overread allowed size end=").append(i).toString(), parcel);
            } else
            {
                return new OnStorageStatsResponse(j, storagestats);
            }
        } while (true);
    }

    public OnStorageStatsResponse[] bm(int i)
    {
        return new OnStorageStatsResponse[i];
    }

    public Object createFromParcel(Parcel parcel)
    {
        return aq(parcel);
    }

    public Object[] newArray(int i)
    {
        return bm(i);
    }
}

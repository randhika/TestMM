// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.drive.realtime.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.a;

// Referenced classes of package com.google.android.gms.drive.realtime.internal:
//            EndCompoundOperationRequest

public class b
    implements android.os.Parcelable.Creator
{

    public b()
    {
    }

    static void a(EndCompoundOperationRequest endcompoundoperationrequest, Parcel parcel, int i)
    {
        int j = com.google.android.gms.common.internal.safeparcel.b.C(parcel);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 1, endcompoundoperationrequest.xM);
        com.google.android.gms.common.internal.safeparcel.b.G(parcel, j);
    }

    public EndCompoundOperationRequest aQ(Parcel parcel)
    {
        int i = com.google.android.gms.common.internal.safeparcel.a.B(parcel);
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

                case 1: // '\001'
                    j = com.google.android.gms.common.internal.safeparcel.a.g(parcel, k);
                    break;
                }
            } else
            if (parcel.dataPosition() != i)
            {
                throw new com.google.android.gms.common.internal.safeparcel.a.a((new StringBuilder()).append("Overread allowed size end=").append(i).toString(), parcel);
            } else
            {
                return new EndCompoundOperationRequest(j);
            }
        } while (true);
    }

    public EndCompoundOperationRequest[] bM(int i)
    {
        return new EndCompoundOperationRequest[i];
    }

    public Object createFromParcel(Parcel parcel)
    {
        return aQ(parcel);
    }

    public Object[] newArray(int i)
    {
        return bM(i);
    }
}

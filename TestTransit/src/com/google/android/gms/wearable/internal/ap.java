// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.wearable.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;

// Referenced classes of package com.google.android.gms.wearable.internal:
//            ao

public class ap
    implements android.os.Parcelable.Creator
{

    public ap()
    {
    }

    static void a(ao ao1, Parcel parcel, int i)
    {
        int j = b.C(parcel);
        b.c(parcel, 1, ao1.xM);
        b.a(parcel, 2, ao1.no(), false);
        b.G(parcel, j);
    }

    public ao cI(Parcel parcel)
    {
        int i = com.google.android.gms.common.internal.safeparcel.a.B(parcel);
        int j = 0;
        android.os.IBinder ibinder = null;
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
                    ibinder = com.google.android.gms.common.internal.safeparcel.a.p(parcel, k);
                    break;
                }
            } else
            if (parcel.dataPosition() != i)
            {
                throw new com.google.android.gms.common.internal.safeparcel.a.a((new StringBuilder()).append("Overread allowed size end=").append(i).toString(), parcel);
            } else
            {
                return new ao(j, ibinder);
            }
        } while (true);
    }

    public Object createFromParcel(Parcel parcel)
    {
        return cI(parcel);
    }

    public ao[] er(int i)
    {
        return new ao[i];
    }

    public Object[] newArray(int i)
    {
        return er(i);
    }
}

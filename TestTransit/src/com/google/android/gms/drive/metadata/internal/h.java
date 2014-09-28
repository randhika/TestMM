// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.drive.metadata.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;

// Referenced classes of package com.google.android.gms.drive.metadata.internal:
//            MetadataBundle

public class h
    implements android.os.Parcelable.Creator
{

    public h()
    {
    }

    static void a(MetadataBundle metadatabundle, Parcel parcel, int i)
    {
        int j = b.C(parcel);
        b.c(parcel, 1, metadatabundle.xM);
        b.a(parcel, 2, metadatabundle.JS, false);
        b.G(parcel, j);
    }

    public MetadataBundle aC(Parcel parcel)
    {
        int i = com.google.android.gms.common.internal.safeparcel.a.B(parcel);
        int j = 0;
        android.os.Bundle bundle = null;
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
                    bundle = com.google.android.gms.common.internal.safeparcel.a.q(parcel, k);
                    break;
                }
            } else
            if (parcel.dataPosition() != i)
            {
                throw new com.google.android.gms.common.internal.safeparcel.a.a((new StringBuilder()).append("Overread allowed size end=").append(i).toString(), parcel);
            } else
            {
                return new MetadataBundle(j, bundle);
            }
        } while (true);
    }

    public MetadataBundle[] by(int i)
    {
        return new MetadataBundle[i];
    }

    public Object createFromParcel(Parcel parcel)
    {
        return aC(parcel);
    }

    public Object[] newArray(int i)
    {
        return by(i);
    }
}

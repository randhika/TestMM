// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.maps.model;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;

// Referenced classes of package com.google.android.gms.maps.model:
//            StreetViewPanoramaCamera

public class StreetViewPanoramaCameraCreator
    implements android.os.Parcelable.Creator
{

    public static final int CONTENT_DESCRIPTION;

    public StreetViewPanoramaCameraCreator()
    {
    }

    static void a(StreetViewPanoramaCamera streetviewpanoramacamera, Parcel parcel, int i)
    {
        int j = b.C(parcel);
        b.c(parcel, 1, streetviewpanoramacamera.getVersionCode());
        b.a(parcel, 2, streetviewpanoramacamera.zoom);
        b.a(parcel, 3, streetviewpanoramacamera.tilt);
        b.a(parcel, 4, streetviewpanoramacamera.bearing);
        b.G(parcel, j);
    }

    public StreetViewPanoramaCamera createFromParcel(Parcel parcel)
    {
        float f = 0.0F;
        int i = com.google.android.gms.common.internal.safeparcel.a.B(parcel);
        float f1 = 0.0F;
        int j = 0;
        float f2 = 0.0F;
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
                    f1 = com.google.android.gms.common.internal.safeparcel.a.l(parcel, k);
                    break;

                case 3: // '\003'
                    f2 = com.google.android.gms.common.internal.safeparcel.a.l(parcel, k);
                    break;

                case 4: // '\004'
                    f = com.google.android.gms.common.internal.safeparcel.a.l(parcel, k);
                    break;
                }
            } else
            if (parcel.dataPosition() != i)
            {
                throw new com.google.android.gms.common.internal.safeparcel.a.a((new StringBuilder()).append("Overread allowed size end=").append(i).toString(), parcel);
            } else
            {
                return new StreetViewPanoramaCamera(j, f1, f2, f);
            }
        } while (true);
    }

    public volatile Object createFromParcel(Parcel parcel)
    {
        return createFromParcel(parcel);
    }

    public StreetViewPanoramaCamera[] newArray(int i)
    {
        return new StreetViewPanoramaCamera[i];
    }

    public volatile Object[] newArray(int i)
    {
        return newArray(i);
    }
}

// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.maps.model;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;

// Referenced classes of package com.google.android.gms.maps.model:
//            TileOverlayOptions

public class TileOverlayOptionsCreator
    implements android.os.Parcelable.Creator
{

    public static final int CONTENT_DESCRIPTION;

    public TileOverlayOptionsCreator()
    {
    }

    static void a(TileOverlayOptions tileoverlayoptions, Parcel parcel, int i)
    {
        int j = b.C(parcel);
        b.c(parcel, 1, tileoverlayoptions.getVersionCode());
        b.a(parcel, 2, tileoverlayoptions.jQ(), false);
        b.a(parcel, 3, tileoverlayoptions.isVisible());
        b.a(parcel, 4, tileoverlayoptions.getZIndex());
        b.a(parcel, 5, tileoverlayoptions.getFadeIn());
        b.G(parcel, j);
    }

    public TileOverlayOptions createFromParcel(Parcel parcel)
    {
        boolean flag = false;
        int i = com.google.android.gms.common.internal.safeparcel.a.B(parcel);
        android.os.IBinder ibinder = null;
        float f = 0.0F;
        boolean flag1 = true;
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

                case 2: // '\002'
                    ibinder = com.google.android.gms.common.internal.safeparcel.a.p(parcel, k);
                    break;

                case 3: // '\003'
                    flag = com.google.android.gms.common.internal.safeparcel.a.c(parcel, k);
                    break;

                case 4: // '\004'
                    f = com.google.android.gms.common.internal.safeparcel.a.l(parcel, k);
                    break;

                case 5: // '\005'
                    flag1 = com.google.android.gms.common.internal.safeparcel.a.c(parcel, k);
                    break;
                }
            } else
            if (parcel.dataPosition() != i)
            {
                throw new com.google.android.gms.common.internal.safeparcel.a.a((new StringBuilder()).append("Overread allowed size end=").append(i).toString(), parcel);
            } else
            {
                return new TileOverlayOptions(j, ibinder, flag, f, flag1);
            }
        } while (true);
    }

    public volatile Object createFromParcel(Parcel parcel)
    {
        return createFromParcel(parcel);
    }

    public TileOverlayOptions[] newArray(int i)
    {
        return new TileOverlayOptions[i];
    }

    public volatile Object[] newArray(int i)
    {
        return newArray(i);
    }
}

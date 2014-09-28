// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.maps.model;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;

// Referenced classes of package com.google.android.gms.maps.model:
//            LatLngBounds, LatLng

public class LatLngBoundsCreator
    implements android.os.Parcelable.Creator
{

    public static final int CONTENT_DESCRIPTION;

    public LatLngBoundsCreator()
    {
    }

    static void a(LatLngBounds latlngbounds, Parcel parcel, int i)
    {
        int j = b.C(parcel);
        b.c(parcel, 1, latlngbounds.getVersionCode());
        b.a(parcel, 2, latlngbounds.southwest, i, false);
        b.a(parcel, 3, latlngbounds.northeast, i, false);
        b.G(parcel, j);
    }

    public LatLngBounds createFromParcel(Parcel parcel)
    {
        LatLng latlng;
        int i;
        int j;
        LatLng latlng1;
        latlng = null;
        i = com.google.android.gms.common.internal.safeparcel.a.B(parcel);
        j = 0;
        latlng1 = null;
_L6:
        int k;
        if (parcel.dataPosition() >= i)
        {
            break MISSING_BLOCK_LABEL_170;
        }
        k = com.google.android.gms.common.internal.safeparcel.a.A(parcel);
        com.google.android.gms.common.internal.safeparcel.a.ar(k);
        JVM INSTR tableswitch 1 3: default 60
    //                   1 91
    //                   2 117
    //                   3 145;
           goto _L1 _L2 _L3 _L4
_L4:
        break MISSING_BLOCK_LABEL_145;
_L2:
        break; /* Loop/switch isn't completed */
_L1:
        LatLng latlng2;
        LatLng latlng3;
        int l;
        com.google.android.gms.common.internal.safeparcel.a.b(parcel, k);
        latlng2 = latlng;
        latlng3 = latlng1;
        l = j;
_L7:
        j = l;
        latlng1 = latlng3;
        latlng = latlng2;
        if (true) goto _L6; else goto _L5
_L5:
        int i1 = com.google.android.gms.common.internal.safeparcel.a.g(parcel, k);
        LatLng latlng5 = latlng;
        latlng3 = latlng1;
        l = i1;
        latlng2 = latlng5;
          goto _L7
_L3:
        LatLng latlng4 = (LatLng)com.google.android.gms.common.internal.safeparcel.a.a(parcel, k, LatLng.CREATOR);
        l = j;
        latlng2 = latlng;
        latlng3 = latlng4;
          goto _L7
        latlng2 = (LatLng)com.google.android.gms.common.internal.safeparcel.a.a(parcel, k, LatLng.CREATOR);
        latlng3 = latlng1;
        l = j;
          goto _L7
        if (parcel.dataPosition() != i)
        {
            throw new com.google.android.gms.common.internal.safeparcel.a.a((new StringBuilder()).append("Overread allowed size end=").append(i).toString(), parcel);
        } else
        {
            return new LatLngBounds(j, latlng1, latlng);
        }
    }

    public volatile Object createFromParcel(Parcel parcel)
    {
        return createFromParcel(parcel);
    }

    public LatLngBounds[] newArray(int i)
    {
        return new LatLngBounds[i];
    }

    public volatile Object[] newArray(int i)
    {
        return newArray(i);
    }
}

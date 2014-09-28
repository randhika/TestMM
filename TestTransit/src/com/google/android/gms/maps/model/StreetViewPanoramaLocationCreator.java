// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.maps.model;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;

// Referenced classes of package com.google.android.gms.maps.model:
//            StreetViewPanoramaLocation, StreetViewPanoramaLink, LatLng

public class StreetViewPanoramaLocationCreator
    implements android.os.Parcelable.Creator
{

    public static final int CONTENT_DESCRIPTION;

    public StreetViewPanoramaLocationCreator()
    {
    }

    static void a(StreetViewPanoramaLocation streetviewpanoramalocation, Parcel parcel, int i)
    {
        int j = b.C(parcel);
        b.c(parcel, 1, streetviewpanoramalocation.getVersionCode());
        b.a(parcel, 2, streetviewpanoramalocation.links, i, false);
        b.a(parcel, 3, streetviewpanoramalocation.position, i, false);
        b.a(parcel, 4, streetviewpanoramalocation.panoId, false);
        b.G(parcel, j);
    }

    public StreetViewPanoramaLocation createFromParcel(Parcel parcel)
    {
        String s;
        int i;
        int j;
        LatLng latlng;
        StreetViewPanoramaLink astreetviewpanoramalink[];
        s = null;
        i = com.google.android.gms.common.internal.safeparcel.a.B(parcel);
        j = 0;
        latlng = null;
        astreetviewpanoramalink = null;
_L7:
        int k;
        if (parcel.dataPosition() >= i)
        {
            break MISSING_BLOCK_LABEL_228;
        }
        k = com.google.android.gms.common.internal.safeparcel.a.A(parcel);
        com.google.android.gms.common.internal.safeparcel.a.ar(k);
        JVM INSTR tableswitch 1 4: default 64
    //                   1 103
    //                   2 133
    //                   3 169
    //                   4 205;
           goto _L1 _L2 _L3 _L4 _L5
_L5:
        break MISSING_BLOCK_LABEL_205;
_L2:
        break; /* Loop/switch isn't completed */
_L1:
        String s1;
        LatLng latlng1;
        StreetViewPanoramaLink astreetviewpanoramalink1[];
        int l;
        com.google.android.gms.common.internal.safeparcel.a.b(parcel, k);
        s1 = s;
        latlng1 = latlng;
        astreetviewpanoramalink1 = astreetviewpanoramalink;
        l = j;
_L8:
        j = l;
        astreetviewpanoramalink = astreetviewpanoramalink1;
        latlng = latlng1;
        s = s1;
        if (true) goto _L7; else goto _L6
_L6:
        int i1 = com.google.android.gms.common.internal.safeparcel.a.g(parcel, k);
        String s3 = s;
        latlng1 = latlng;
        astreetviewpanoramalink1 = astreetviewpanoramalink;
        l = i1;
        s1 = s3;
          goto _L8
_L3:
        StreetViewPanoramaLink astreetviewpanoramalink2[] = (StreetViewPanoramaLink[])com.google.android.gms.common.internal.safeparcel.a.b(parcel, k, StreetViewPanoramaLink.CREATOR);
        l = j;
        LatLng latlng3 = latlng;
        astreetviewpanoramalink1 = astreetviewpanoramalink2;
        s1 = s;
        latlng1 = latlng3;
          goto _L8
_L4:
        LatLng latlng2 = (LatLng)com.google.android.gms.common.internal.safeparcel.a.a(parcel, k, LatLng.CREATOR);
        astreetviewpanoramalink1 = astreetviewpanoramalink;
        l = j;
        String s2 = s;
        latlng1 = latlng2;
        s1 = s2;
          goto _L8
        s1 = com.google.android.gms.common.internal.safeparcel.a.o(parcel, k);
        latlng1 = latlng;
        astreetviewpanoramalink1 = astreetviewpanoramalink;
        l = j;
          goto _L8
        if (parcel.dataPosition() != i)
        {
            throw new com.google.android.gms.common.internal.safeparcel.a.a((new StringBuilder()).append("Overread allowed size end=").append(i).toString(), parcel);
        } else
        {
            return new StreetViewPanoramaLocation(j, astreetviewpanoramalink, latlng, s);
        }
    }

    public volatile Object createFromParcel(Parcel parcel)
    {
        return createFromParcel(parcel);
    }

    public StreetViewPanoramaLocation[] newArray(int i)
    {
        return new StreetViewPanoramaLocation[i];
    }

    public volatile Object[] newArray(int i)
    {
        return newArray(i);
    }
}

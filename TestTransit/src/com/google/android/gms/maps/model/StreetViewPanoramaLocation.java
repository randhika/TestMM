// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.maps.model;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.hk;

// Referenced classes of package com.google.android.gms.maps.model:
//            StreetViewPanoramaLocationCreator, LatLng, StreetViewPanoramaLink

public class StreetViewPanoramaLocation
    implements SafeParcelable
{

    public static final StreetViewPanoramaLocationCreator CREATOR = new StreetViewPanoramaLocationCreator();
    public final StreetViewPanoramaLink links[];
    public final String panoId;
    public final LatLng position;
    private final int xM;

    StreetViewPanoramaLocation(int i, StreetViewPanoramaLink astreetviewpanoramalink[], LatLng latlng, String s)
    {
        xM = i;
        links = astreetviewpanoramalink;
        position = latlng;
        panoId = s;
    }

    public StreetViewPanoramaLocation(StreetViewPanoramaLink astreetviewpanoramalink[], LatLng latlng, String s)
    {
        this(1, astreetviewpanoramalink, latlng, s);
    }

    public int describeContents()
    {
        return 0;
    }

    public boolean equals(Object obj)
    {
        if (this != obj)
        {
            if (!(obj instanceof StreetViewPanoramaLocation))
            {
                return false;
            }
            StreetViewPanoramaLocation streetviewpanoramalocation = (StreetViewPanoramaLocation)obj;
            if (!panoId.equals(streetviewpanoramalocation.panoId) || !position.equals(streetviewpanoramalocation.position))
            {
                return false;
            }
        }
        return true;
    }

    int getVersionCode()
    {
        return xM;
    }

    public int hashCode()
    {
        Object aobj[] = new Object[2];
        aobj[0] = position;
        aobj[1] = panoId;
        return hk.hashCode(aobj);
    }

    public String toString()
    {
        return hk.e(this).a("panoId", panoId).a("position", position.toString()).toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        StreetViewPanoramaLocationCreator.a(this, parcel, i);
    }

}

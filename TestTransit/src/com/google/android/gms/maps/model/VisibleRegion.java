// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.maps.model;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.hk;
import com.google.android.gms.maps.internal.v;

// Referenced classes of package com.google.android.gms.maps.model:
//            VisibleRegionCreator, LatLng, LatLngBounds, k

public final class VisibleRegion
    implements SafeParcelable
{

    public static final VisibleRegionCreator CREATOR = new VisibleRegionCreator();
    public final LatLng farLeft;
    public final LatLng farRight;
    public final LatLngBounds latLngBounds;
    public final LatLng nearLeft;
    public final LatLng nearRight;
    private final int xM;

    VisibleRegion(int i, LatLng latlng, LatLng latlng1, LatLng latlng2, LatLng latlng3, LatLngBounds latlngbounds)
    {
        xM = i;
        nearLeft = latlng;
        nearRight = latlng1;
        farLeft = latlng2;
        farRight = latlng3;
        latLngBounds = latlngbounds;
    }

    public VisibleRegion(LatLng latlng, LatLng latlng1, LatLng latlng2, LatLng latlng3, LatLngBounds latlngbounds)
    {
        this(1, latlng, latlng1, latlng2, latlng3, latlngbounds);
    }

    public int describeContents()
    {
        return 0;
    }

    public boolean equals(Object obj)
    {
        if (this != obj)
        {
            if (!(obj instanceof VisibleRegion))
            {
                return false;
            }
            VisibleRegion visibleregion = (VisibleRegion)obj;
            if (!nearLeft.equals(visibleregion.nearLeft) || !nearRight.equals(visibleregion.nearRight) || !farLeft.equals(visibleregion.farLeft) || !farRight.equals(visibleregion.farRight) || !latLngBounds.equals(visibleregion.latLngBounds))
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
        Object aobj[] = new Object[5];
        aobj[0] = nearLeft;
        aobj[1] = nearRight;
        aobj[2] = farLeft;
        aobj[3] = farRight;
        aobj[4] = latLngBounds;
        return hk.hashCode(aobj);
    }

    public String toString()
    {
        return hk.e(this).a("nearLeft", nearLeft).a("nearRight", nearRight).a("farLeft", farLeft).a("farRight", farRight).a("latLngBounds", latLngBounds).toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        if (v.jL())
        {
            k.a(this, parcel, i);
            return;
        } else
        {
            VisibleRegionCreator.a(this, parcel, i);
            return;
        }
    }

}

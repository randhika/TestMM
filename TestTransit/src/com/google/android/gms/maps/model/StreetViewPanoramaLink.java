// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.maps.model;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.hk;

// Referenced classes of package com.google.android.gms.maps.model:
//            StreetViewPanoramaLinkCreator

public class StreetViewPanoramaLink
    implements SafeParcelable
{

    public static final StreetViewPanoramaLinkCreator CREATOR = new StreetViewPanoramaLinkCreator();
    public final float bearing;
    public final String panoId;
    private final int xM;

    StreetViewPanoramaLink(int i, String s, float f)
    {
        xM = i;
        panoId = s;
        if ((double)f <= 0.0D)
        {
            f = 360F + f % 360F;
        }
        bearing = f % 360F;
    }

    public int describeContents()
    {
        return 0;
    }

    public boolean equals(Object obj)
    {
        if (this != obj)
        {
            if (!(obj instanceof StreetViewPanoramaLink))
            {
                return false;
            }
            StreetViewPanoramaLink streetviewpanoramalink = (StreetViewPanoramaLink)obj;
            if (!panoId.equals(streetviewpanoramalink.panoId) || Float.floatToIntBits(bearing) != Float.floatToIntBits(streetviewpanoramalink.bearing))
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
        aobj[0] = panoId;
        aobj[1] = Float.valueOf(bearing);
        return hk.hashCode(aobj);
    }

    public String toString()
    {
        return hk.e(this).a("panoId", panoId).a("bearing", Float.valueOf(bearing)).toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        StreetViewPanoramaLinkCreator.a(this, parcel, i);
    }

}

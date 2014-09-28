// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.maps.model;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.hk;
import com.google.android.gms.internal.hm;

// Referenced classes of package com.google.android.gms.maps.model:
//            StreetViewPanoramaOrientationCreator

public class StreetViewPanoramaOrientation
    implements SafeParcelable
{
    public static final class Builder
    {

        public float bearing;
        public float tilt;

        public Builder bearing(float f)
        {
            bearing = f;
            return this;
        }

        public StreetViewPanoramaOrientation build()
        {
            return new StreetViewPanoramaOrientation(tilt, bearing);
        }

        public Builder tilt(float f)
        {
            tilt = f;
            return this;
        }

        public Builder()
        {
        }

        public Builder(StreetViewPanoramaOrientation streetviewpanoramaorientation)
        {
            bearing = streetviewpanoramaorientation.bearing;
            tilt = streetviewpanoramaorientation.tilt;
        }
    }


    public static final StreetViewPanoramaOrientationCreator CREATOR = new StreetViewPanoramaOrientationCreator();
    public final float bearing;
    public final float tilt;
    private final int xM;

    public StreetViewPanoramaOrientation(float f, float f1)
    {
        this(1, f, f1);
    }

    StreetViewPanoramaOrientation(int i, float f, float f1)
    {
        boolean flag;
        if (-90F <= f && f <= 90F)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        hm.b(flag, "Tilt needs to be between -90 and 90 inclusive");
        xM = i;
        tilt = 0.0F + f;
        if ((double)f1 <= 0.0D)
        {
            f1 = 360F + f1 % 360F;
        }
        bearing = f1 % 360F;
    }

    public static Builder builder()
    {
        return new Builder();
    }

    public static Builder builder(StreetViewPanoramaOrientation streetviewpanoramaorientation)
    {
        return new Builder(streetviewpanoramaorientation);
    }

    public int describeContents()
    {
        return 0;
    }

    public boolean equals(Object obj)
    {
        if (this != obj)
        {
            if (!(obj instanceof StreetViewPanoramaOrientation))
            {
                return false;
            }
            StreetViewPanoramaOrientation streetviewpanoramaorientation = (StreetViewPanoramaOrientation)obj;
            if (Float.floatToIntBits(tilt) != Float.floatToIntBits(streetviewpanoramaorientation.tilt) || Float.floatToIntBits(bearing) != Float.floatToIntBits(streetviewpanoramaorientation.bearing))
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
        aobj[0] = Float.valueOf(tilt);
        aobj[1] = Float.valueOf(bearing);
        return hk.hashCode(aobj);
    }

    public String toString()
    {
        return hk.e(this).a("tilt", Float.valueOf(tilt)).a("bearing", Float.valueOf(bearing)).toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        StreetViewPanoramaOrientationCreator.a(this, parcel, i);
    }

}

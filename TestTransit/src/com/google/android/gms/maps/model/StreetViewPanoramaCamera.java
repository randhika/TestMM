// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.maps.model;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.hk;
import com.google.android.gms.internal.hm;

// Referenced classes of package com.google.android.gms.maps.model:
//            StreetViewPanoramaCameraCreator, StreetViewPanoramaOrientation

public class StreetViewPanoramaCamera
    implements SafeParcelable
{
    public static final class Builder
    {

        public float bearing;
        public float tilt;
        public float zoom;

        public Builder bearing(float f)
        {
            bearing = f;
            return this;
        }

        public StreetViewPanoramaCamera build()
        {
            return new StreetViewPanoramaCamera(zoom, tilt, bearing);
        }

        public Builder orientation(StreetViewPanoramaOrientation streetviewpanoramaorientation)
        {
            tilt = streetviewpanoramaorientation.tilt;
            bearing = streetviewpanoramaorientation.bearing;
            return this;
        }

        public Builder tilt(float f)
        {
            tilt = f;
            return this;
        }

        public Builder zoom(float f)
        {
            zoom = f;
            return this;
        }

        public Builder()
        {
        }

        public Builder(StreetViewPanoramaCamera streetviewpanoramacamera)
        {
            zoom = streetviewpanoramacamera.zoom;
            bearing = streetviewpanoramacamera.bearing;
            tilt = streetviewpanoramacamera.tilt;
        }
    }


    public static final StreetViewPanoramaCameraCreator CREATOR = new StreetViewPanoramaCameraCreator();
    private StreetViewPanoramaOrientation abb;
    public final float bearing;
    public final float tilt;
    private final int xM;
    public final float zoom;

    public StreetViewPanoramaCamera(float f, float f1, float f2)
    {
        this(1, f, f1, f2);
    }

    StreetViewPanoramaCamera(int i, float f, float f1, float f2)
    {
        boolean flag;
        float f3;
        if (-90F <= f1 && f1 <= 90F)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        hm.b(flag, "Tilt needs to be between -90 and 90 inclusive");
        xM = i;
        zoom = f;
        tilt = 0.0F + f1;
        if ((double)f2 <= 0.0D)
        {
            f3 = 360F + f2 % 360F;
        } else
        {
            f3 = f2;
        }
        bearing = f3 % 360F;
        abb = (new StreetViewPanoramaOrientation.Builder()).tilt(f1).bearing(f2).build();
    }

    public static Builder builder()
    {
        return new Builder();
    }

    public static Builder builder(StreetViewPanoramaCamera streetviewpanoramacamera)
    {
        return new Builder(streetviewpanoramacamera);
    }

    public int describeContents()
    {
        return 0;
    }

    public boolean equals(Object obj)
    {
        if (this != obj)
        {
            if (!(obj instanceof StreetViewPanoramaCamera))
            {
                return false;
            }
            StreetViewPanoramaCamera streetviewpanoramacamera = (StreetViewPanoramaCamera)obj;
            if (Float.floatToIntBits(zoom) != Float.floatToIntBits(streetviewpanoramacamera.zoom) || Float.floatToIntBits(tilt) != Float.floatToIntBits(streetviewpanoramacamera.tilt) || Float.floatToIntBits(bearing) != Float.floatToIntBits(streetviewpanoramacamera.bearing))
            {
                return false;
            }
        }
        return true;
    }

    public StreetViewPanoramaOrientation getOrientation()
    {
        return abb;
    }

    int getVersionCode()
    {
        return xM;
    }

    public int hashCode()
    {
        Object aobj[] = new Object[3];
        aobj[0] = Float.valueOf(zoom);
        aobj[1] = Float.valueOf(tilt);
        aobj[2] = Float.valueOf(bearing);
        return hk.hashCode(aobj);
    }

    public String toString()
    {
        return hk.e(this).a("zoom", Float.valueOf(zoom)).a("tilt", Float.valueOf(tilt)).a("bearing", Float.valueOf(bearing)).toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        StreetViewPanoramaCameraCreator.a(this, parcel, i);
    }

}

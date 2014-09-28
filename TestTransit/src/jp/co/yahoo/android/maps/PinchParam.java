// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.maps;


// Referenced classes of package jp.co.yahoo.android.maps:
//            GeoPoint, DoublePoint

public class PinchParam
{

    private GeoPoint mGeoPoint;
    private DoublePoint mPos;
    private float mfactor;

    public PinchParam(DoublePoint doublepoint, float f, GeoPoint geopoint)
    {
        mfactor = f;
        mPos = doublepoint;
        mGeoPoint = geopoint;
    }

    public float getFactor()
    {
        return mfactor;
    }

    public GeoPoint getGeoPoint()
    {
        return mGeoPoint;
    }

    public DoublePoint getPos()
    {
        return mPos;
    }
}

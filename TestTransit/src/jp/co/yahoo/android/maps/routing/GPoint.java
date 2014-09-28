// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.maps.routing;

import java.io.Serializable;
import jp.co.yahoo.android.maps.GeoPoint;
import jp.co.yahoo.android.maps.viewlayer.Coordinate;
import jp.co.yahoo.android.maps.viewlayer.LLCalculation;

public class GPoint
    implements Serializable
{

    private static final long serialVersionUID = 1L;
    public int l_no;
    public int p_no;
    public double x;
    public double y;
    public double z;

    public GPoint()
    {
    }

    public GPoint(double d, double d1)
    {
        x = d;
        y = d1;
        z = 0.0D;
    }

    public GPoint(double d, double d1, double d2)
    {
        x = d;
        y = d1;
        z = d2;
    }

    public GPoint(int i, int j)
    {
        p_no = i;
        l_no = j;
        z = 0.0D;
    }

    public double distance(GPoint gpoint)
    {
        return Math.sqrt(Coordinate.pow(x - gpoint.x, 2D) + Coordinate.pow(y - gpoint.y, 2D));
    }

    public double distanceM(GPoint gpoint)
    {
        return LLCalculation.distance(y, x, gpoint.y, gpoint.x);
    }

    public Coordinate getCoordinate()
    {
        Coordinate coordinate = new Coordinate();
        coordinate.lon = x;
        coordinate.lat = y;
        return coordinate;
    }

    public GeoPoint getGeoPoint()
    {
        return new GeoPoint((int)(1000000D * y), (int)(1000000D * x));
    }

    public GeoPoint getGeoPointint()
    {
        return new GeoPoint(l_no, p_no);
    }
}

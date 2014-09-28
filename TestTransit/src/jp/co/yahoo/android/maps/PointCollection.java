// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.maps;

import android.graphics.Point;
import jp.co.yahoo.android.maps.viewlayer.Coordinate;

// Referenced classes of package jp.co.yahoo.android.maps:
//            GeoPoint, MapView, Projection

public class PointCollection
{

    private double cmprad;

    public PointCollection(double d)
    {
        cmprad = 0.0D;
        cmprad = Math.toRadians(180D - d);
    }

    private double angle(GeoPoint geopoint, GeoPoint geopoint1, GeoPoint geopoint2)
    {
        double d = geopoint1.getLongitudeE6() - geopoint.getLongitudeE6();
        double d1 = geopoint1.getLatitudeE6() - geopoint.getLatitudeE6();
        double d2 = geopoint1.getLongitudeE6() - geopoint2.getLongitudeE6();
        double d3 = geopoint1.getLatitudeE6() - geopoint2.getLatitudeE6();
        return Coordinate.acos((d * d2 + d1 * d3) / Math.sqrt((Coordinate.pow(d, 2D) + Coordinate.pow(d1, 2D)) * (Coordinate.pow(d2, 2D) + Coordinate.pow(d3, 2D))));
    }

    private boolean matchConfig(GeoPoint geopoint, GeoPoint geopoint1, GeoPoint geopoint2)
    {
        double d = angle(geopoint, geopoint1, geopoint2);
        if (d == (0.0D / 0.0D))
        {
            return false;
        }
        double d1 = cmprad;
        if (d > d1)
        {
            return true;
        }
        break MISSING_BLOCK_LABEL_38;
        Exception exception;
        exception;
        return false;
    }

    public Point[] thinningOut(MapView mapview, GeoPoint ageopoint[])
    {
        Projection projection;
        int i;
        int j;
        boolean aflag[];
        int k;
        projection = mapview.getProjection();
        i = ageopoint.length;
        if (i < 3)
        {
            Point apoint1[] = new Point[i];
            int i2 = 0;
            do
            {
                if (i2 >= ageopoint.length)
                {
                    return apoint1;
                }
                apoint1[i2] = projection.toWorldPixels(ageopoint[i2], null);
                i2++;
            } while (true);
        }
        j = 0;
        aflag = new boolean[i];
        k = 0;
_L5:
        if (k < i) goto _L2; else goto _L1
_L1:
        int l;
        int i1;
        int j1;
        l = 0;
        i1 = 1;
        j1 = 2;
_L6:
        if (j1 < i) goto _L4; else goto _L3
_L3:
        Point apoint[];
        int k1;
        int l1;
        apoint = new Point[i - j];
        k1 = 0;
        l1 = 0;
_L7:
        if (l1 >= i)
        {
            return apoint;
        }
        break MISSING_BLOCK_LABEL_180;
_L2:
        aflag[k] = false;
        k++;
          goto _L5
_L4:
        if (matchConfig(ageopoint[l], ageopoint[i1], ageopoint[j1]))
        {
            aflag[i1] = true;
            j++;
            i1++;
        } else
        {
            l = i1;
            i1++;
        }
        j1 = i1 + 1;
          goto _L6
        if (!aflag[l1])
        {
            apoint[k1] = projection.toWorldPixels(ageopoint[l1], null);
            k1++;
        }
        l1++;
          goto _L7
    }
}

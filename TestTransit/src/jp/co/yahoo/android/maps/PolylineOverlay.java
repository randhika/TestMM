// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.maps;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.graphics.Point;
import android.graphics.PointF;

// Referenced classes of package jp.co.yahoo.android.maps:
//            Overlay, MapView, Projection, PointCollection, 
//            GeoPoint

public class PolylineOverlay extends Overlay
{

    private int color;
    private GeoPoint geoPoints[];
    private int mMapType;
    private double mZoomLevel;
    private PathEffect m_pathEffect;
    private float width;
    private Point worldPoints[];

    public PolylineOverlay(GeoPoint ageopoint[])
    {
        mZoomLevel = -100D;
        mMapType = -1;
        m_pathEffect = null;
        geoPoints = ageopoint;
        color = 0xff0000ff;
        width = 3F;
    }

    static boolean pntInside(Point point, Point point1, Point point2, double d)
    {
        double d1 = Math.atan2(point2.x - point1.x, point2.y - point1.y);
        PointF pointf = rotate(point1, d1);
        PointF pointf1 = rotate(point2, d1);
        PointF pointf2 = rotate(point, d1);
        return (double)pointf2.x >= (double)pointf.x - d && (double)pointf2.x <= d + (double)pointf1.x && (double)pointf2.y >= (double)pointf.y - d && (double)pointf2.y <= d + (double)pointf1.y;
    }

    private static PointF rotate(Point point, double d)
    {
        double d1 = (double)point.x * Math.cos(d) - (double)point.y * Math.sin(d);
        double d2 = (double)point.x * Math.sin(d) + (double)point.y * Math.cos(d);
        return new PointF((float)d1, (float)d2);
    }

    private void thinningOut(MapView mapview)
    {
        Projection projection = mapview.getProjection();
        if (mapview.getZoomLevel() >= 2) goto _L2; else goto _L1
_L1:
        worldPoints = null;
        worldPoints = new Point[geoPoints.length];
        int j = 0;
_L3:
        if (j >= geoPoints.length)
        {
            return;
        }
        Point point = projection.toWorldPixels(geoPoints[j], null);
        worldPoints[j] = point;
        j++;
        if (true) goto _L3; else goto _L2
_L2:
        if (mapview.getZoomLevel() >= 4) goto _L5; else goto _L4
_L4:
        double d = 1.5D;
_L7:
        try
        {
            worldPoints = null;
            worldPoints = (new PointCollection(d)).thinningOut(mapview, geoPoints);
            return;
        }
        catch (Exception exception)
        {
            worldPoints = null;
        }
        return;
_L5:
        if (mapview.getZoomLevel() < 5)
        {
            d = 2.5D;
            continue; /* Loop/switch isn't completed */
        }
        if (mapview.getZoomLevel() < 6)
        {
            d = 5D;
            continue; /* Loop/switch isn't completed */
        }
        if (mapview.getZoomLevel() < 7)
        {
            d = 10D;
            continue; /* Loop/switch isn't completed */
        }
        if (mapview.getZoomLevel() < 10)
        {
            d = 40D;
            continue; /* Loop/switch isn't completed */
        }
        if (mapview.getZoomLevel() < 15)
        {
            d = 400D;
            continue; /* Loop/switch isn't completed */
        }
        int i = mapview.getZoomLevel();
        if (i < 18)
        {
            d = 400D;
        } else
        {
            d = 400D;
        }
        if (true) goto _L7; else goto _L6
_L6:
    }

    public void draw(Canvas canvas, MapView mapview, boolean flag)
    {
        int i;
        int j;
        super.draw(canvas, mapview, flag);
        i = 0;
        j = 0;
        if (flag) goto _L2; else goto _L1
_L1:
        Projection projection;
        Path path;
        int k;
        Point point;
        Paint paint;
        int l;
        int i1;
        projection = mapview.getProjection();
        path = null;
        double d = mZoomLevel;
        k = mapview.getMapType();
        point = null;
        paint = new Paint(1);
        paint.setStyle(android.graphics.Paint.Style.STROKE);
        paint.setStrokeWidth(width);
        paint.setColor(color);
        if (m_pathEffect != null)
        {
            paint.setPathEffect(m_pathEffect);
        }
        l = projection.getLeftWorldPixel();
        i1 = projection.getTopWorldPixel();
        if (mMapType != k || d != (double)mapview.getZoomLevel())
        {
            thinningOut(mapview);
        }
        if (worldPoints != null) goto _L3; else goto _L2
_L2:
        return;
_L3:
        int j1 = 0;
_L7:
        if (j1 >= worldPoints.length)
        {
            if (path != null)
            {
                Point point4 = worldPoints[j];
                path.lineTo(projection.toPixelXFromWP(point4.x, point4.y), projection.toPixelYFromWP(point4.x, point4.y));
                canvas.drawPath(path, paint);
            }
            mZoomLevel = mapview.getZoomLevel();
            mMapType = k;
            return;
        }
        if (point != null) goto _L5; else goto _L4
_L4:
        point = worldPoints[j1];
        if (point == null) goto _L2; else goto _L6
_L6:
        j = j1;
_L9:
        j1++;
          goto _L7
_L5:
        Point point1;
        point1 = worldPoints[j1];
        i++;
        if (point1 == null) goto _L2; else goto _L8
_L8:
        int k1;
        int l1;
        int i2;
        int j2;
        if (point.x > point1.x)
        {
            l1 = point1.x;
            k1 = point.x;
        } else
        {
            k1 = point1.x;
            l1 = point.x;
        }
        if (point.y > point1.y)
        {
            j2 = point1.y;
            i2 = point.y;
        } else
        {
            i2 = point1.y;
            j2 = point.y;
        }
        if (l <= k1 && l1 <= l + canvas.getWidth() && i1 <= i2 && j2 <= i1 + canvas.getHeight())
        {
            Point point3 = worldPoints[j1 - 1];
            float f2 = projection.toPixelXFromWP(point3.x, point3.y);
            float f3 = projection.toPixelYFromWP(point3.x, point3.y);
            if (path == null)
            {
                path = new Path();
                path.moveTo(f2, f3);
            } else
            {
                path.lineTo(f2, f3);
            }
        } else
        if (path != null)
        {
            Point point2 = worldPoints[j1 - 1];
            float f = projection.toPixelXFromWP(point2.x, point2.y);
            float f1 = projection.toPixelYFromWP(point2.x, point2.y);
            path.lineTo(f, f1);
            path.lineTo(f, f1);
            canvas.drawPath(path, paint);
            path = null;
        }
        point = point1;
        j = j1;
          goto _L9
    }

    public GeoPoint getCenter()
    {
        if (geoPoints == null || geoPoints.length == 0)
        {
            return null;
        }
        int i = 0;
        int j = 0;
        int k = 0;
        int l = 0;
        int i1 = 0;
        do
        {
            if (i1 >= geoPoints.length)
            {
                return new GeoPoint((i + j) / 2, (k + l) / 2);
            }
            GeoPoint geopoint = geoPoints[i1];
            if (i1 == 0)
            {
                j = geopoint.getLatitudeE6();
                i = j;
                l = geopoint.getLongitudeE6();
                k = l;
            } else
            {
                i = Math.min(i, geopoint.getLatitudeE6());
                j = Math.max(j, geopoint.getLatitudeE6());
                k = Math.min(k, geopoint.getLongitudeE6());
                l = Math.max(l, geopoint.getLongitudeE6());
            }
            i1++;
        } while (true);
    }

    public int getLatSpanE6()
    {
        if (geoPoints == null || geoPoints.length == 0)
        {
            return 0;
        }
        int i = geoPoints[0].getLatitudeE6();
        int j = geoPoints[0].getLatitudeE6();
        int k = 1;
        do
        {
            if (k >= geoPoints.length)
            {
                return j - i;
            }
            i = Math.min(i, geoPoints[k].getLatitudeE6());
            j = Math.max(j, geoPoints[k].getLatitudeE6());
            k++;
        } while (true);
    }

    public int getLonSpanE6()
    {
        if (geoPoints == null || geoPoints.length == 0)
        {
            return 0;
        }
        int i = geoPoints[0].getLongitudeE6();
        int j = geoPoints[0].getLongitudeE6();
        int k = 1;
        do
        {
            if (k >= geoPoints.length)
            {
                return j - i;
            }
            i = Math.min(i, geoPoints[k].getLongitudeE6());
            j = Math.max(j, geoPoints[k].getLongitudeE6());
            k++;
        } while (true);
    }

    protected boolean onTap()
    {
        return false;
    }

    public boolean onTap(GeoPoint geopoint, MapView mapview)
    {
        Point point = mapview.getProjection().toWorldPixels(geopoint, null);
        if (worldPoints != null)
        {
            int i = 0;
            while (i < -1 + worldPoints.length) 
            {
                if (pntInside(point, worldPoints[i], worldPoints[i + 1], 20D))
                {
                    return onTap();
                }
                i++;
            }
        }
        return false;
    }

    public void setColor(int i)
    {
        color = i;
    }

    public void setPathEffect(PathEffect patheffect)
    {
        m_pathEffect = patheffect;
    }

    public void setWidth(float f)
    {
        width = f;
    }
}

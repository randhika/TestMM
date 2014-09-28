// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.maps;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.Point;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

// Referenced classes of package jp.co.yahoo.android.maps:
//            Overlay, GeoPoint, MapView, Projection, 
//            PolylineOverlay

public class PolygonOverlay extends Overlay
{

    private static final GeoPoint ToArray_GeoPoint[] = new GeoPoint[0];
    private int fillColor;
    private GeoPoint geoPoints[];
    private boolean needsMeasure;
    private int strokeColor;
    private float strokeWidth;
    private final Paint wkpaint = new Paint(1);
    private final Path wkpath = new Path();
    private final PathMeasure wkpm;
    private float wkpos[];
    private float wktan[];

    public PolygonOverlay(GeoPoint ageopoint[])
    {
        wkpos = new float[2];
        wktan = new float[2];
        wkpm = new PathMeasure(wkpath, true);
        geoPoints = ageopoint;
        strokeColor = 0xff0000ff;
        fillColor = 0;
        strokeWidth = 3F;
        needsMeasure = true;
    }

    private static boolean pntInside(Point point, Point apoint[])
    {
        int i;
        int j;
        if (!apoint[0].equals(apoint[-1 + apoint.length]))
        {
            return false;
        }
        i = 0;
        j = 1;
_L2:
        double d;
        double d1;
        double d2;
        double d3;
        if (j >= apoint.length)
        {
            if (i == 0)
            {
                return false;
            }
            break MISSING_BLOCK_LABEL_375;
        }
        d = apoint[j - 1].x - point.x;
        d1 = apoint[j - 1].y - point.y;
        d2 = apoint[j].x - point.x;
        d3 = apoint[j].y - point.y;
        if ((d >= 0.0D || d2 >= 0.0D) && (d1 >= 0.0D || d3 >= 0.0D) && (0.0D >= d1 || 0.0D >= d3))
        {
            break; /* Loop/switch isn't completed */
        }
_L3:
        j++;
        if (true) goto _L2; else goto _L1
_L1:
        if (0.0D < d && 0.0D < d2)
        {
            i += t_pas(d1, d3);
        } else
        if (d2 != d || d3 != d1)
        {
            if (d == 0.0D && d1 == 0.0D)
            {
                return true;
            }
            if (d2 == 0.0D && d3 == 0.0D)
            {
                return true;
            }
            if (d1 == 0.0D && d3 == 0.0D)
            {
                if (d * d2 <= 0.0D)
                {
                    return true;
                }
            } else
            if (d == 0.0D && d2 == 0.0D)
            {
                if (d1 * d3 <= 0.0D)
                {
                    return true;
                }
            } else
            if (d == 0.0D && 0.0D <= d2 || d2 == 0.0D && 0.0D <= d)
            {
                i += t_pas(d1, d3);
            } else
            {
                double d4 = d * d3 - d1 * d2;
                if (d4 == 0.0D)
                {
                    continue; /* Loop/switch isn't completed */
                }
                int k = t_pas(d1, d3);
                if (0.0D < d4 * (double)k)
                {
                    i += k;
                }
            }
        }
          goto _L3
        continue; /* Loop/switch isn't completed */
        if (d * d2 + d1 * d3 >= 0.0D) goto _L3; else goto _L4
_L4:
        return true;
        return i == 2 || i == -2;
        if (true) goto _L2; else goto _L5
_L5:
    }

    private static int t_pas(double d, double d1)
    {
        byte byte0 = 1;
        if (d < 0.0D && 0.0D < d1)
        {
            byte0 = 2;
        } else
        {
            if (d1 < 0.0D && 0.0D < d)
            {
                return -2;
            }
            if ((d != 0.0D || 0.0D >= d1) && (d >= 0.0D || 0.0D != d1))
            {
                if (d1 == 0.0D && 0.0D < d)
                {
                    return -1;
                }
                return d1 >= 0.0D || 0.0D != d ? 0 : -1;
            }
        }
        return byte0;
    }

    public void draw(Canvas canvas, MapView mapview, boolean flag)
    {
        super.draw(canvas, mapview, flag);
        wkpath.reset();
        wkpath.close();
        if (flag) goto _L2; else goto _L1
_L1:
        Projection projection = mapview.getProjection();
_L10:
        int i = 0;
_L7:
        if (i < geoPoints.length) goto _L4; else goto _L3
_L3:
        if (needsMeasure) goto _L6; else goto _L5
_L5:
        wkpath.close();
        float f;
        float f1;
        List list;
        if (fillColor == strokeColor)
        {
            wkpaint.setStyle(android.graphics.Paint.Style.FILL_AND_STROKE);
        } else
        {
            wkpaint.setStyle(android.graphics.Paint.Style.FILL);
            wkpaint.setColor(fillColor);
            canvas.drawPath(wkpath, wkpaint);
            wkpaint.setStyle(android.graphics.Paint.Style.STROKE);
        }
        wkpaint.setStrokeWidth(strokeWidth);
        wkpaint.setColor(strokeColor);
        canvas.drawPath(wkpath, wkpaint);
_L2:
        return;
_L4:
        f = projection.toPixelXFromLL(geoPoints[i].getLatitude(), geoPoints[i].getLongitude());
        f1 = projection.toPixelYFromLL(geoPoints[i].getLatitude(), geoPoints[i].getLongitude());
        if (i == 0)
        {
            wkpath.moveTo(f, f1);
        } else
        {
            wkpath.lineTo(f, f1);
        }
        i++;
          goto _L7
_L6:
        needsMeasure = false;
        if (!wkpm.getPosTan(wkpm.getLength(), wkpos, wktan) || Math.atan2(wktan[1], wktan[0]) >= 0.0D) goto _L5; else goto _L8
_L8:
        list = Arrays.asList(geoPoints);
        Collections.reverse(list);
        geoPoints = (GeoPoint[])list.toArray(ToArray_GeoPoint);
        wkpath.reset();
        if (true) goto _L10; else goto _L9
_L9:
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
        Projection projection;
        Point apoint[];
        int i;
        Point point;
        int j;
        projection = mapview.getProjection();
        if (geoPoints[0].equals(geoPoints[-1 + geoPoints.length]))
        {
            apoint = new Point[geoPoints.length];
        } else
        {
            apoint = new Point[1 + geoPoints.length];
            apoint[geoPoints.length] = projection.toPixels(geoPoints[0], null);
        }
        i = 0;
_L10:
        if (i < geoPoints.length) goto _L2; else goto _L1
_L1:
        point = projection.toPixels(geopoint, null);
        if (fillColor != 0) goto _L4; else goto _L3
_L3:
        j = 0;
_L7:
        if (j < -1 + apoint.length) goto _L6; else goto _L5
_L5:
        return false;
_L2:
        apoint[i] = projection.toPixels(geoPoints[i], null);
        i++;
        continue; /* Loop/switch isn't completed */
_L6:
        if (PolylineOverlay.pntInside(point, apoint[j], apoint[j + 1], 20D))
        {
            return onTap();
        }
        j++;
          goto _L7
_L4:
        if (!pntInside(point, apoint)) goto _L5; else goto _L8
_L8:
        return onTap();
        if (true) goto _L10; else goto _L9
_L9:
    }

    public void setFillColor(int i)
    {
        fillColor = i;
    }

    public void setStrokeColor(int i)
    {
        strokeColor = i;
    }

    public void setStrokeWidth(float f)
    {
        strokeWidth = f;
    }

}

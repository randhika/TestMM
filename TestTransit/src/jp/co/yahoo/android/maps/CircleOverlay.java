// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.maps;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.RectF;

// Referenced classes of package jp.co.yahoo.android.maps:
//            Overlay, MapView, Projection, GeoPoint

public class CircleOverlay extends Overlay
{

    private int fillColor;
    private GeoPoint geoCenter;
    private int meterRadius[];
    private int strokeColor;
    private float strokeWidth;

    public CircleOverlay(GeoPoint geopoint, int i)
    {
        geoCenter = geopoint;
        meterRadius = (new int[] {
            i
        });
        strokeColor = 0xff0000ff;
        fillColor = 0;
        strokeWidth = 3F;
    }

    public CircleOverlay(GeoPoint geopoint, int i, int j)
    {
        geoCenter = geopoint;
        meterRadius = (new int[] {
            i, j
        });
        strokeColor = 0xff0000ff;
        fillColor = 0;
        strokeWidth = 3F;
    }

    public void draw(Canvas canvas, MapView mapview, boolean flag)
    {
        super.draw(canvas, mapview, flag);
        if (flag) goto _L2; else goto _L1
_L1:
        Projection projection;
        float f;
        float f1;
        Paint paint;
        projection = mapview.getProjection();
        Point point = projection.toPixels(geoCenter, null);
        f = point.x;
        f1 = point.y;
        paint = new Paint(1);
        if (fillColor != strokeColor) goto _L4; else goto _L3
_L3:
        paint.setStyle(android.graphics.Paint.Style.FILL_AND_STROKE);
_L12:
        paint.setStrokeWidth(strokeWidth);
        paint.setColor(strokeColor);
        if (meterRadius.length != 1) goto _L6; else goto _L5
_L5:
        canvas.drawCircle(f, f1, projection.metersToEquatorPixels(meterRadius[0]), paint);
_L2:
        return;
_L4:
        paint.setStyle(android.graphics.Paint.Style.FILL);
        paint.setColor(fillColor);
        if (meterRadius.length != 1) goto _L8; else goto _L7
_L7:
        canvas.drawCircle(f, f1, projection.metersToEquatorPixels(meterRadius[0]), paint);
_L9:
        paint.setStyle(android.graphics.Paint.Style.STROKE);
        continue; /* Loop/switch isn't completed */
_L8:
        if (meterRadius.length == 2)
        {
            float f2 = projection.metersToEquatorPixels(meterRadius[0]);
            float f3 = projection.metersToEquatorPixels(meterRadius[1]);
            canvas.drawOval(new RectF(f - f2, f1 - f3, f + f2, f1 + f3), paint);
        }
        if (true) goto _L9; else goto _L6
_L6:
        if (meterRadius.length != 2) goto _L2; else goto _L10
_L10:
        float f4 = projection.metersToEquatorPixels(meterRadius[0]);
        float f5 = projection.metersToEquatorPixels(meterRadius[1]);
        canvas.drawOval(new RectF(f - f4, f1 - f5, f + f4, f1 + f5), paint);
        return;
        if (true) goto _L12; else goto _L11
_L11:
    }

    public GeoPoint getCenter()
    {
        return geoCenter;
    }

    public int getLatSpanE6()
    {
        return (int)(1000000D * (2D * ((double)meterRadius[-1 + meterRadius.length] / 111111D)));
    }

    public int getLonSpanE6()
    {
        return (int)(1000000D * (2D * ((double)meterRadius[0] / 111111D)));
    }

    protected boolean onTap()
    {
        return false;
    }

    public boolean onTap(GeoPoint geopoint, MapView mapview)
    {
        Projection projection = mapview.getProjection();
        Point point = projection.toPixels(geopoint, null);
        Point point1 = projection.toPixels(geoCenter, null);
        if (meterRadius.length == 1)
        {
            float f2 = projection.metersToEquatorPixels(meterRadius[0]);
            double d3 = Math.sqrt(Math.pow(point.x - point1.x, 2D) + Math.pow(point.y - point1.y, 2D));
            if (fillColor == 0)
            {
                if (d3 > (double)(f2 - 20F) && d3 < (double)(20F + f2))
                {
                    return onTap();
                }
            } else
            if (d3 <= (double)f2)
            {
                return onTap();
            }
        } else
        if (meterRadius.length == 2)
        {
            float f = projection.metersToEquatorPixels(meterRadius[0]);
            float f1 = projection.metersToEquatorPixels(meterRadius[1]);
            double d = Math.atan2(point.y - point1.y, point.x - point1.x);
            double d1 = Math.sqrt(Math.pow((double)f * Math.cos(d), 2D) + Math.pow((double)f1 * Math.sin(d), 2D));
            double d2 = Math.sqrt(Math.pow(point.x - point1.x, 2D) + Math.pow(point.y - point1.y, 2D));
            if (fillColor == 0)
            {
                if (d2 > d1 - 20D && d2 < 20D + d1)
                {
                    return onTap();
                }
            } else
            if (d2 <= d1)
            {
                return onTap();
            }
        }
        return false;
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

// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.maps;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.view.KeyEvent;
import android.view.MotionEvent;

// Referenced classes of package jp.co.yahoo.android.maps:
//            MapView, GeoPoint

public abstract class Overlay
{
    public static interface Snappable
    {

        public abstract boolean onSnapToItem(int i, int j, Point point, MapView mapview);
    }


    protected static final float SHADOW_X_SKEW = -0.9F;
    protected static final float SHADOW_Y_SCALE = 0.5F;
    public int displayZoomLevel;
    protected MapView mMapView;

    public Overlay()
    {
        displayZoomLevel = 20;
    }

    protected static void drawAt(Canvas canvas, Bitmap bitmap, int i, int j, boolean flag)
    {
        Paint paint = new Paint();
        canvas.save();
        canvas.translate(i, j);
        canvas.drawBitmap(bitmap, i, j, paint);
        canvas.restore();
    }

    protected static void drawAt(Canvas canvas, Drawable drawable, int i, int j, Drawable drawable1, int k, int l, boolean flag)
    {
        canvas.save();
        canvas.translate(i, j);
        drawable.draw(canvas);
        canvas.translate(k, l);
        drawable1.draw(canvas);
        canvas.restore();
    }

    protected static void drawAt(Canvas canvas, Drawable drawable, int i, int j, boolean flag)
    {
        canvas.save();
        canvas.translate(i, j);
        drawable.draw(canvas);
        canvas.restore();
    }

    protected static void drawAt(Canvas canvas, Drawable drawable, int i, int j, boolean flag, int k)
    {
        canvas.save();
        canvas.rotate(k, i, j);
        canvas.translate(i, j);
        drawable.draw(canvas);
        canvas.restore();
    }

    public void draw(Canvas canvas, MapView mapview, boolean flag)
    {
    }

    public boolean draw(Canvas canvas, MapView mapview, boolean flag, long l)
    {
        draw(canvas, mapview, flag);
        return false;
    }

    public GeoPoint getCenter()
    {
        return null;
    }

    public int getLatSpanE6()
    {
        return 0;
    }

    public int getLonSpanE6()
    {
        return 0;
    }

    public boolean onKeyDown(int i, KeyEvent keyevent, MapView mapview)
    {
        return false;
    }

    public boolean onKeyUp(int i, KeyEvent keyevent, MapView mapview)
    {
        return false;
    }

    public boolean onPinch()
    {
        return false;
    }

    public boolean onRemove(MapView mapview)
    {
        return false;
    }

    public boolean onTap(GeoPoint geopoint, MapView mapview)
    {
        return false;
    }

    public boolean onTimer(MapView mapview)
    {
        return false;
    }

    public boolean onTouchEvent(MotionEvent motionevent, MapView mapview)
    {
        return false;
    }

    public boolean onTrackballEvent(MotionEvent motionevent, MapView mapview)
    {
        return false;
    }

    public boolean onTwoTap()
    {
        return false;
    }

    public void setMapView(MapView mapview)
    {
        mMapView = mapview;
    }
}

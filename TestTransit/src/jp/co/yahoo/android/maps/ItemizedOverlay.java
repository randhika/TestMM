// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.maps;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.KeyEvent;
import android.view.MotionEvent;
import java.util.ArrayList;
import java.util.List;

// Referenced classes of package jp.co.yahoo.android.maps:
//            Overlay, MapView, OverlayItem, GeoPoint, 
//            Projection

public abstract class ItemizedOverlay extends Overlay
    implements Overlay.Snappable
{
    public static interface OnFocusChangeListener
    {

        public abstract void onFocusChanged(ItemizedOverlay itemizedoverlay, OverlayItem overlayitem);
    }


    private int angle;
    protected boolean animeFlag;
    private final Drawable defaultMarker;
    private boolean drawFocusedItem;
    private OverlayItem focusedItem;
    private final List items = new ArrayList();
    private int lastFocusedIndex;
    private int mMapType;
    private Drawable mShadowMarker;
    private double mZoomLevel;
    private OnFocusChangeListener onFocusChangeListener;

    public ItemizedOverlay(Drawable drawable)
    {
        focusedItem = null;
        lastFocusedIndex = 0;
        drawFocusedItem = true;
        onFocusChangeListener = null;
        mZoomLevel = -100D;
        mMapType = 0;
        angle = -1;
        animeFlag = false;
        mShadowMarker = null;
        defaultMarker = drawable;
    }

    public static Drawable bitmap2drawable(Bitmap bitmap)
    {
        return (new BitmapDrawable(bitmap)).getCurrent();
    }

    protected static Drawable boundCenter(Drawable drawable)
    {
        int i = drawable.getIntrinsicWidth();
        int j = drawable.getIntrinsicHeight();
        if (i > 0 && j > 0)
        {
            drawable.setBounds(-i / 2, -j / 2, i / 2, j / 2);
        }
        return drawable;
    }

    protected static Drawable boundCenterBottom(Drawable drawable)
    {
        int i = drawable.getIntrinsicWidth();
        int j = drawable.getIntrinsicHeight();
        if (i > 0 && j > 0)
        {
            drawable.setBounds(-i / 2, -j, i / 2, 0);
        }
        return drawable;
    }

    public static Bitmap drawable2bitmap(Drawable drawable, int i, int j)
    {
        return drawable2bitmap(drawable, i, j, android.graphics.Bitmap.Config.RGB_565);
    }

    public static Bitmap drawable2bitmap(Drawable drawable, int i, int j, android.graphics.Bitmap.Config config)
    {
        Bitmap bitmap = Bitmap.createBitmap(i, j, config);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, i, j);
        drawable.draw(canvas);
        return bitmap;
    }

    public static Bitmap rotateBitmap(Bitmap bitmap, int i)
    {
        int j = bitmap.getWidth();
        int k = bitmap.getHeight();
        Bitmap bitmap1 = Bitmap.createBitmap(k, j, android.graphics.Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap1);
        Paint paint = new Paint();
        paint.setColor(Color.argb(0, 0, 0, 0));
        paint.setStyle(android.graphics.Paint.Style.FILL);
        canvas.drawRect(new Rect(0, 0, k, j), paint);
        canvas.rotate(i, j / 2, k / 2);
        canvas.drawBitmap(bitmap, (j - k) / 2, (j - k) / 2, null);
        return bitmap1;
    }

    protected abstract OverlayItem createItem(int i);

    public void draw(Canvas canvas, MapView mapview, boolean flag)
    {
        Projection projection;
        double d;
        int i;
        projection = mapview.getProjection();
        d = mZoomLevel;
        i = mapview.getMapType();
        if (mShadowMarker == null) goto _L2; else goto _L1
_L1:
        int j2 = 0;
_L6:
        int k2 = items.size();
        if (j2 < k2) goto _L4; else goto _L3
_L3:
        mZoomLevel = mapview.getZoomLevel();
        mMapType = i;
_L2:
        int j;
        j = 0;
        break MISSING_BLOCK_LABEL_64;
_L4:
        OverlayItem overlayitem1 = getItem(getIndexToDraw(j2));
        if (overlayitem1 != null && (drawFocusedItem || overlayitem1 != focusedItem))
        {
            Drawable drawable1 = mShadowMarker;
            if (mMapType == i || d != (double)mapview.getZoomLevel() || overlayitem1.getWorldPoint() == null)
            {
                double d1 = overlayitem1.getPoint().getLatitude();
                double d2 = overlayitem1.getPoint().getLongitude();
                overlayitem1.setWorldPoint(projection.toWorldPixelX(d1, d2), projection.toWorldPixelY(d1, d2));
            }
            Point point1 = overlayitem1.getWorldPoint();
            if (point1 != null && (angle == -1 || angle == 0) && overlayitem1.getAnimationCount() != 500)
            {
                int l2 = projection.getTopWorldPixel();
                int i3 = projection.getLeftWorldPixel();
                Rect rect1 = drawable1.getBounds();
                int j3 = point1.x + rect1.left;
                int k3 = point1.y + rect1.top;
                int l3 = rect1.right - rect1.left;
                int i4 = rect1.bottom - rect1.top;
                if (l2 <= j3 + l3 && j3 <= l2 + canvas.getWidth() && i3 <= k3 + i4 && k3 <= i3 + canvas.getHeight())
                {
                    int j4 = projection.toPixelXFromWP(point1.x, point1.y);
                    int k4 = projection.toPixelYFromWP(point1.x, point1.y);
                    drawAt(canvas, drawable1, j4 + overlayitem1.getAnimationCount(), k4 - overlayitem1.getAnimationCount(), flag);
                }
            }
        }
        j2++;
        continue; /* Loop/switch isn't completed */
        do
        {
            int k = items.size();
            if (j >= k)
            {
                mZoomLevel = mapview.getZoomLevel();
                mMapType = i;
                return;
            }
            OverlayItem overlayitem = getItem(getIndexToDraw(j));
            if (overlayitem != null && (drawFocusedItem || overlayitem != focusedItem))
            {
                Drawable drawable = getMarker(overlayitem);
                if (mMapType == i || d != (double)mapview.getZoomLevel() || overlayitem.getWorldPoint() == null)
                {
                    GeoPoint geopoint = overlayitem.getPoint();
                    overlayitem.setWorldPoint(projection.toWorldPixelX(geopoint.getLatitude(), geopoint.getLongitude()), projection.toWorldPixelY(geopoint.getLatitude(), geopoint.getLongitude()));
                }
                Point point = overlayitem.getWorldPoint();
                if (point != null)
                {
                    if (angle == -1 || angle == 0)
                    {
                        if (overlayitem.getAnimationCount() != 500)
                        {
                            int l = projection.getTopWorldPixel();
                            int i1 = projection.getLeftWorldPixel();
                            Rect rect = drawable.getBounds();
                            int j1 = point.x + rect.left;
                            int k1 = point.y + rect.top;
                            int l1 = rect.right - rect.left;
                            int i2 = rect.bottom - rect.top;
                            if (i1 <= j1 + l1 && j1 <= i1 + canvas.getWidth() && l <= k1 + i2 && k1 <= l + canvas.getHeight())
                            {
                                drawAt(canvas, drawable, projection.toPixelXFromWP(point.x, point.y), projection.toPixelYFromWP(point.x, point.y) - overlayitem.getAnimationCount(), flag);
                            }
                        }
                    } else
                    {
                        drawAt(canvas, drawable, projection.toPixelXFromWP(point.x, point.y), projection.toPixelYFromWP(point.x, point.y), flag, angle);
                    }
                }
            }
            j++;
        } while (true);
        if (true) goto _L6; else goto _L5
_L5:
    }

    public GeoPoint getCenter()
    {
        if (items.size() == 0)
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
            if (i1 >= items.size())
            {
                return new GeoPoint((i + j) / 2, (k + l) / 2);
            }
            GeoPoint geopoint = getItem(i1).getPoint();
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

    public OverlayItem getFocus()
    {
        return focusedItem;
    }

    protected int getIndexToDraw(int i)
    {
        return i;
    }

    public final OverlayItem getItem(int i)
    {
        if (i < 0 || i >= items.size())
        {
            return null;
        } else
        {
            return (OverlayItem)items.get(i);
        }
    }

    public final int getLastFocusedIndex()
    {
        return lastFocusedIndex;
    }

    public int getLatSpanE6()
    {
        int i;
        int j;
        int k;
        i = 0;
        j = 0;
        k = 0;
_L2:
        GeoPoint geopoint;
        if (k >= items.size())
        {
            return j - i;
        }
        geopoint = getItem(k).getPoint();
        if (k != 0)
        {
            break; /* Loop/switch isn't completed */
        }
        j = geopoint.getLatitudeE6();
        i = j;
_L3:
        k++;
        if (true) goto _L2; else goto _L1
_L1:
        if (geopoint.getLatitudeE6() < i)
        {
            i = geopoint.getLatitudeE6();
        }
        if (geopoint.getLatitudeE6() > j)
        {
            j = geopoint.getLatitudeE6();
        }
          goto _L3
        if (true) goto _L2; else goto _L4
_L4:
    }

    public int getLonSpanE6()
    {
        int i;
        int j;
        int k;
        i = 0;
        j = 0;
        k = 0;
_L2:
        GeoPoint geopoint;
        if (k >= items.size())
        {
            return j - i;
        }
        geopoint = getItem(k).getPoint();
        if (k != 0)
        {
            break; /* Loop/switch isn't completed */
        }
        j = geopoint.getLongitudeE6();
        i = j;
_L3:
        k++;
        if (true) goto _L2; else goto _L1
_L1:
        if (geopoint.getLongitudeE6() < i)
        {
            i = geopoint.getLongitudeE6();
        }
        if (geopoint.getLongitudeE6() > j)
        {
            j = geopoint.getLongitudeE6();
        }
          goto _L3
        if (true) goto _L2; else goto _L4
_L4:
    }

    Drawable getMarker(OverlayItem overlayitem)
    {
        Drawable drawable = overlayitem.getMarker(0);
        if (drawable == null)
        {
            drawable = defaultMarker;
        }
        return drawable;
    }

    protected boolean hitTest(OverlayItem overlayitem, Drawable drawable, int i, int j)
    {
        return drawable.copyBounds().contains(i, j);
    }

    public OverlayItem nextFocus(boolean flag)
    {
        if (flag)
        {
            if (lastFocusedIndex < -1 + items.size())
            {
                lastFocusedIndex = 1 + lastFocusedIndex;
            }
        } else
        if (lastFocusedIndex <= 0)
        {
            lastFocusedIndex = 0;
        } else
        {
            lastFocusedIndex = -1 + lastFocusedIndex;
        }
        return getItem(lastFocusedIndex);
    }

    public boolean onKeyUp(int i, KeyEvent keyevent, MapView mapview)
    {
        return false;
    }

    public boolean onSnapToItem(int i, int j, Point point, MapView mapview)
    {
        return false;
    }

    protected boolean onTap(int i)
    {
        return false;
    }

    public boolean onTap(GeoPoint geopoint, MapView mapview)
    {
        Projection projection;
        Point point;
        int i;
        projection = mapview.getProjection();
        point = projection.toPixels(geopoint, null);
        i = -1 + items.size();
_L2:
        OverlayItem overlayitem;
        if (i < 0)
        {
            setFocus(null);
            return false;
        }
        overlayitem = getItem(i);
        if (overlayitem != null)
        {
            break; /* Loop/switch isn't completed */
        }
_L3:
        i--;
        if (true) goto _L2; else goto _L1
_L1:
        Rect rect = getMarker(overlayitem).copyBounds();
        Point point1 = projection.toPixels(overlayitem.getPoint(), null);
        rect.offset(point1.x, point1.y);
        rect.inset(-20, -20);
        if (rect.contains(point.x, point.y))
        {
            setFocus(getItem(i));
            return onTap(i);
        }
          goto _L3
        if (true) goto _L2; else goto _L4
_L4:
    }

    public boolean onTimer(MapView mapview)
    {
        if (!animeFlag) goto _L2; else goto _L1
_L1:
        boolean flag;
        int i;
        int j;
        int k;
        flag = false;
        i = 1 + items.size() / 5;
        j = 0;
        k = -1 + items.size();
_L7:
        if (k >= 0) goto _L4; else goto _L3
_L3:
        if (flag && mMapView != null)
        {
            mMapView.reDraw();
        }
_L2:
        return true;
_L4:
        OverlayItem overlayitem;
        int i1;
        overlayitem = getItem(k);
        int l = overlayitem.getAnimationCount();
        if (l <= 0)
        {
            break; /* Loop/switch isn't completed */
        }
        flag = true;
        i1 = l - 120;
        if (i1 < 0)
        {
            i1 = 0;
        }
        if (overlayitem.getAnimationCount() != 500)
        {
            break; /* Loop/switch isn't completed */
        }
        overlayitem.setAnimationCount(i1);
        if (++j >= i) goto _L3; else goto _L5
_L5:
        k--;
        if (true) goto _L7; else goto _L6
_L6:
        overlayitem.setAnimationCount(i1);
          goto _L5
        if (true) goto _L7; else goto _L8
_L8:
    }

    public boolean onTouchEvent(MotionEvent motionevent, MapView mapview)
    {
        return false;
    }

    public boolean onTrackballEvent(MotionEvent motionevent, MapView mapview)
    {
        return false;
    }

    protected final void populate()
    {
        items.clear();
        angle = setangle();
        int i = 0;
        do
        {
            if (i >= size())
            {
                if (mMapView != null)
                {
                    mMapView.reDraw();
                }
                return;
            }
            items.add(createItem(i));
            i++;
        } while (true);
    }

    public void setAnimation(boolean flag)
    {
        animeFlag = flag;
        if (animeFlag) goto _L2; else goto _L1
_L1:
        int i = -1 + items.size();
_L5:
        if (i >= 0) goto _L3; else goto _L2
_L2:
        return;
_L3:
        getItem(i).setAnimationCount(0);
        i--;
        if (true) goto _L5; else goto _L4
_L4:
    }

    public void setDrawFocusedItem(boolean flag)
    {
        drawFocusedItem = flag;
    }

    public void setFocus(OverlayItem overlayitem)
    {
        if (overlayitem != null) goto _L2; else goto _L1
_L1:
        focusedItem = null;
_L6:
        if (onFocusChangeListener != null)
        {
            onFocusChangeListener.onFocusChanged(this, overlayitem);
        }
_L4:
        return;
_L2:
        int i = items.indexOf(overlayitem);
        if (i < 0) goto _L4; else goto _L3
_L3:
        focusedItem = overlayitem;
        lastFocusedIndex = i;
        if (true) goto _L6; else goto _L5
_L5:
    }

    protected void setLastFocusedIndex(int i)
    {
        lastFocusedIndex = i;
    }

    public void setOnFocusChangeListener(OnFocusChangeListener onfocuschangelistener)
    {
        onFocusChangeListener = onfocuschangelistener;
    }

    public void setPinShadow(Drawable drawable)
    {
        mShadowMarker = drawable;
    }

    public abstract int setangle();

    public abstract int size();
}

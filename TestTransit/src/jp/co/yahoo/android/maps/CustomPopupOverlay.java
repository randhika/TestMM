// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.maps;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.view.MotionEvent;
import java.util.ArrayList;

// Referenced classes of package jp.co.yahoo.android.maps:
//            PopupOverlay, MapView, OverlayItem, Projection, 
//            Overlay, GeoPoint, ItemizedOverlay

public class CustomPopupOverlay extends PopupOverlay
{
    static class PopItem
    {

        public int event;
        public float height;
        public Drawable off_image;
        public Paint off_paint;
        public Drawable on_image;
        public Paint on_paint;
        public boolean select;
        public String str;
        public float width;
        public int x;
        public int y;

        public void draw(Canvas canvas, int i, int j)
        {
            if (off_image == null) goto _L2; else goto _L1
_L1:
            if (!select || on_image == null) goto _L4; else goto _L3
_L3:
            Overlay.drawAt(canvas, on_image, i + x, j + y, false);
_L6:
            return;
_L4:
            Overlay.drawAt(canvas, off_image, i + x, j + y, false);
            return;
_L2:
            if (str != null)
            {
                if (select && on_paint != null)
                {
                    android.graphics.Paint.FontMetrics fontmetrics1 = on_paint.getFontMetrics();
                    canvas.drawText(str, i + x, (float)(j + y) - fontmetrics1.top, on_paint);
                    return;
                } else
                {
                    android.graphics.Paint.FontMetrics fontmetrics = off_paint.getFontMetrics();
                    canvas.drawText(str, i + x, (float)(j + y) - fontmetrics.top, off_paint);
                    return;
                }
            }
            if (true) goto _L6; else goto _L5
_L5:
        }

        PopItem()
        {
            select = false;
            str = null;
            on_image = null;
            off_image = null;
            event = 0;
            x = 0;
            y = 0;
            width = 0.0F;
            height = 0.0F;
            on_paint = null;
            off_paint = null;
        }
    }


    private final int billSize = 20;
    private int color;
    private Drawable m_backgroundImage;
    private float m_height;
    private int m_left;
    private ArrayList m_popItemList;
    private int m_top;
    private float m_width;
    private final int roundSize = 10;
    private final int textSize = 28;

    public CustomPopupOverlay()
    {
        m_popItemList = new ArrayList();
        color = Color.argb(128, 0, 0, 0);
        m_top = 0;
        m_left = 0;
        m_width = 0.0F;
        m_height = 0.0F;
        m_backgroundImage = null;
    }

    public Rect addImage(Drawable drawable, int i, int j, int k)
    {
        return addImage(drawable, null, i, j, k);
    }

    public Rect addImage(Drawable drawable, Drawable drawable1, int i, int j, int k)
    {
        Rect rect = new Rect();
        PopItem popitem = new PopItem();
        Drawable drawable2 = boundTopLeftBottom(drawable);
        popitem.event = k;
        popitem.x = i;
        popitem.y = j;
        popitem.width = drawable2.getIntrinsicWidth();
        popitem.height = drawable2.getIntrinsicHeight();
        popitem.off_image = drawable2;
        if (drawable1 != null)
        {
            popitem.on_image = boundTopLeftBottom(drawable1);
        }
        m_popItemList.add(popitem);
        rect.left = i;
        rect.top = j;
        rect.bottom = (int)((float)j + popitem.height);
        rect.right = (int)((float)i + popitem.width);
        return rect;
    }

    public Rect addText(String s, int i, int j, Paint paint, int k)
    {
        return addText(s, i, j, paint, null, k);
    }

    public Rect addText(String s, int i, int j, Paint paint, Paint paint1, int k)
    {
        Rect rect = new Rect();
        PopItem popitem = new PopItem();
        android.graphics.Paint.FontMetrics fontmetrics = paint.getFontMetrics();
        popitem.str = s;
        popitem.on_paint = paint1;
        popitem.off_paint = paint;
        popitem.event = k;
        popitem.x = i;
        popitem.y = j;
        popitem.width = paint.measureText(s);
        popitem.height = fontmetrics.bottom - fontmetrics.ascent;
        m_popItemList.add(popitem);
        rect.left = i;
        rect.top = j;
        rect.bottom = (int)((float)j + popitem.height);
        rect.right = (int)((float)i + popitem.width);
        return rect;
    }

    public void clear()
    {
        m_popItemList.clear();
        m_width = 0.0F;
        m_height = 0.0F;
    }

    public void draw(Canvas canvas, MapView mapview, boolean flag)
    {
        if (baseItem != null) goto _L2; else goto _L1
_L1:
        return;
_L2:
        Point point;
        int i;
        point = mapview.getProjection().toPixels(baseItem.getPoint(), null);
        if (baseMarker != null)
        {
            Rect rect = baseMarker.copyBounds();
            rect.offset(point.x, point.y);
            point.x = (rect.left + rect.right) / 2;
            point.y = rect.top;
        }
        i = 0;
_L4:
label0:
        {
            if (i < m_popItemList.size())
            {
                break label0;
            }
            PopItem popitem;
            if (m_backgroundImage != null)
            {
                int k = point.x - m_backgroundImage.getIntrinsicWidth() / 2;
                int l = point.y - m_backgroundImage.getIntrinsicHeight();
                Overlay.drawAt(canvas, m_backgroundImage, k, l, false);
                m_left = k;
                m_top = l;
                m_width = m_backgroundImage.getIntrinsicWidth();
                m_height = m_backgroundImage.getIntrinsicHeight();
                int i1 = 0;
                while (i1 < m_popItemList.size()) 
                {
                    ((PopItem)m_popItemList.get(i1)).draw(canvas, (int)((float)point.x - m_width / 2.0F), (int)((float)point.y - m_height));
                    i1++;
                }
            } else
            {
                Paint paint = new Paint(1);
                paint.setStyle(android.graphics.Paint.Style.FILL);
                paint.setColor(color);
                paint.setTypeface(Typeface.create(Typeface.SANS_SERIF, 0));
                paint.setTextSize(28F);
                paint.setShadowLayer(10F, 5F, 5F, Color.argb(96, 0, 0, 0));
                canvas.drawRoundRect(new RectF((float)point.x - m_width / 2.0F - 10F, (float)point.y - m_height - 20F - 10F, 10F + (((float)point.x - m_width / 2.0F) + m_width), 10F + (((float)point.y - m_height - 20F) + m_height)), 10F, 10F, paint);
                Path path = new Path();
                path.moveTo(-10 + point.x, -20 + point.y);
                path.lineTo(10 + point.x, -20 + point.y);
                path.lineTo(point.x, point.y);
                path.close();
                canvas.drawPath(path, paint);
                m_left = (int)((float)point.x - m_width / 2.0F);
                m_top = (int)((float)point.y - m_height - 20F);
                int j = 0;
                while (j < m_popItemList.size()) 
                {
                    ((PopItem)m_popItemList.get(j)).draw(canvas, (int)((float)point.x - m_width / 2.0F), (int)((float)point.y - m_height - 20F));
                    j++;
                }
            }
        }
        if (true) goto _L1; else goto _L3
_L3:
        popitem = (PopItem)m_popItemList.get(i);
        if (m_width < (float)popitem.x + popitem.width)
        {
            m_width = (float)popitem.x + popitem.width;
        }
        if (m_height < (float)popitem.y + popitem.height)
        {
            m_height = (float)popitem.y + popitem.height;
        }
        i++;
          goto _L4
    }

    public boolean onTap(GeoPoint geopoint, MapView mapview)
    {
        Point point = mapview.getProjection().toPixels(geopoint, null);
        if (baseItem != null)
        {
            int i = -1 + m_popItemList.size();
            while (i >= 0) 
            {
                PopItem popitem = (PopItem)m_popItemList.get(i);
                if (popitem.event != 0 && popitem != null)
                {
                    RectF rectf = new RectF(popitem.x, popitem.y, (float)popitem.x + popitem.width, (float)popitem.y + popitem.height);
                    rectf.offset(m_left, m_top);
                    if (rectf.contains(point.x, point.y))
                    {
                        onTap(baseItem, popitem.event);
                        return true;
                    }
                }
                i--;
            }
        }
        return false;
    }

    public boolean onTouchEvent(MotionEvent motionevent, MapView mapview)
    {
        Point point;
        point = new Point((int)motionevent.getX(), (int)motionevent.getY());
        if (!(new RectF(m_left, m_top, (int)((float)m_left + m_width), (int)((float)m_top + m_height))).contains(point.x, point.y))
        {
            return false;
        }
        motionevent.getAction();
        JVM INSTR tableswitch 0 1: default 104
    //                   0 106
    //                   1 248;
           goto _L1 _L2 _L3
_L1:
        return true;
_L2:
        int j = -1 + m_popItemList.size();
        while (j >= 0) 
        {
            PopItem popitem = (PopItem)m_popItemList.get(j);
            if (popitem.event != 0 && popitem != null)
            {
                RectF rectf = new RectF(popitem.x, popitem.y, (float)popitem.x + popitem.width, (float)popitem.y + popitem.height);
                rectf.offset(m_left, m_top);
                if (rectf.contains(point.x, point.y))
                {
                    popitem.select = true;
                } else
                {
                    popitem.select = false;
                }
            }
            j--;
        }
        continue; /* Loop/switch isn't completed */
_L3:
        int i = -1 + m_popItemList.size();
        while (i >= 0) 
        {
            ((PopItem)m_popItemList.get(i)).select = false;
            i--;
        }
        if (true) goto _L1; else goto _L4
_L4:
    }

    public void openPopup(ItemizedOverlay itemizedoverlay, OverlayItem overlayitem)
    {
        super.openPopup(itemizedoverlay, overlayitem);
    }

    public void setBackgroundColor(int i)
    {
        color = i;
    }

    public void setBackgroundImage(Drawable drawable)
    {
        m_backgroundImage = boundTopLeftBottom(drawable);
    }
}

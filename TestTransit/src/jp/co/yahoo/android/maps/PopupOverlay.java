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

// Referenced classes of package jp.co.yahoo.android.maps:
//            Overlay, GeoPoint, MapView, OverlayItem, 
//            Projection, PinOverlay, ItemizedOverlay

public class PopupOverlay extends Overlay
    implements ItemizedOverlay.OnFocusChangeListener
{

    protected OverlayItem baseItem;
    protected Drawable baseMarker;
    private final int billSize;
    private int color;
    private int mbSize;
    private Drawable poppic[];
    private Drawable poppic2;
    private int roundSize;
    private int takasa;
    private GeoPoint tapPint2[];
    private GeoPoint tapPoint;
    private RectF tapRegion;
    private RectF tapRegion2[];
    public int tapRegionType;
    private final int textSize;
    private int wkw;
    private float xx;
    private float yy;

    public PopupOverlay()
    {
        baseItem = null;
        baseMarker = null;
        textSize = 28;
        roundSize = 10;
        billSize = 20;
        takasa = 60;
        color = Color.argb(128, 0, 0, 0);
        mbSize = 32;
        poppic = null;
        poppic2 = null;
        yy = 0.0F;
        xx = 0.0F;
        tapPoint = null;
        tapPint2 = new GeoPoint[10];
        tapRegion = null;
        tapRegion2 = new RectF[10];
        tapRegionType = 0;
        wkw = 0;
    }

    public PopupOverlay(Drawable drawable)
    {
        baseItem = null;
        baseMarker = null;
        textSize = 28;
        roundSize = 10;
        billSize = 20;
        takasa = 60;
        color = Color.argb(128, 0, 0, 0);
        mbSize = 32;
        poppic = null;
        poppic2 = null;
        yy = 0.0F;
        xx = 0.0F;
        tapPoint = null;
        tapPint2 = new GeoPoint[10];
        tapRegion = null;
        tapRegion2 = new RectF[10];
        tapRegionType = 0;
        wkw = 0;
        poppic2 = drawable;
        poppic2 = boundCenterBottom(poppic2);
    }

    public PopupOverlay(Drawable adrawable[])
    {
        baseItem = null;
        baseMarker = null;
        textSize = 28;
        roundSize = 10;
        billSize = 20;
        takasa = 60;
        color = Color.argb(128, 0, 0, 0);
        mbSize = 32;
        poppic = null;
        poppic2 = null;
        yy = 0.0F;
        xx = 0.0F;
        tapPoint = null;
        tapPint2 = new GeoPoint[10];
        tapRegion = null;
        tapRegion2 = new RectF[10];
        tapRegionType = 0;
        wkw = 0;
        poppic = adrawable;
        int i = 0;
        do
        {
            if (i >= poppic.length)
            {
                return;
            }
            poppic[i] = boundCenterBottom(poppic[i]);
            i++;
        } while (true);
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

    protected static Drawable boundTopLeftBottom(Drawable drawable)
    {
        int i = drawable.getIntrinsicWidth();
        int j = drawable.getIntrinsicHeight();
        if (i > 0 && j > 0)
        {
            drawable.setBounds(0, 0, i, j);
        }
        return drawable;
    }

    public void closePopup()
    {
        int i;
        baseItem = null;
        baseMarker = null;
        tapPoint = null;
        tapRegion = null;
        if (poppic == null)
        {
            break MISSING_BLOCK_LABEL_73;
        }
        i = 0;
_L3:
        if (i < poppic.length) goto _L2; else goto _L1
_L1:
        if (mMapView != null)
        {
            mMapView.reDraw();
        }
        return;
_L2:
        tapRegion2[i] = null;
        tapPint2[i] = null;
        i++;
          goto _L3
        if (poppic2 != null)
        {
            tapRegion2[0] = null;
            tapPint2[0] = null;
        }
          goto _L1
    }

    public void draw(Canvas canvas, MapView mapview, boolean flag)
    {
        super.draw(canvas, mapview, flag);
        if (baseItem != null) goto _L2; else goto _L1
_L1:
        String s;
        return;
_L2:
        if ((s = baseItem.getTitle()) == null || s.equals("")) goto _L1; else goto _L3
_L3:
        int i;
        i = mbSize;
        if (baseItem.getSnippet() == null || baseItem.getSnippet().equals(""))
        {
            i = 0;
        }
        if (flag) goto _L5; else goto _L4
_L4:
        Projection projection;
        Point point1;
        Paint paint1;
        android.graphics.Paint.FontMetrics fontmetrics1;
        String as[];
        String s3;
        float f3;
        int l;
        int i1;
        String as1[];
        int j1;
        int k1;
        int l1;
        float f5;
        RectF rectf1;
        int i2;
        float f6;
        int j2;
        int k2;
        int l2;
        int i3;
        int j3;
        int l3;
        projection = mapview.getProjection();
        point1 = projection.toPixels(baseItem.getPoint(), null);
        if (baseMarker != null)
        {
            Rect rect1 = baseMarker.copyBounds();
            rect1.offset(point1.x, point1.y);
            point1.x = (rect1.left + rect1.right) / 2;
            point1.y = rect1.top;
        }
        float f2 = (float)(0.90000000000000002D * (double)mapview.getWidth());
        paint1 = new Paint(1);
        paint1.setStyle(android.graphics.Paint.Style.FILL);
        paint1.setColor(color);
        paint1.setTypeface(Typeface.create(Typeface.SANS_SERIF, 0));
        paint1.setTextSize(28F);
        paint1.setShadowLayer(10F, 5F, 5F, Color.argb(96, 0, 0, 0));
        fontmetrics1 = paint1.getFontMetrics();
        StringBuffer stringbuffer1 = new StringBuffer(s);
        Point point;
        android.graphics.Paint.FontMetrics fontmetrics;
        String s1;
        float f1;
        RectF rectf;
        Rect rect;
        int j;
        int k;
        float f4;
        String s4;
        Path path;
        RectF rectf6;
        float f14;
        int k3;
        float f15;
        float f16;
        if (s.indexOf("\n") == -1)
        {
            while (stringbuffer1.length() > 0 && paint1.measureText(stringbuffer1.toString()) + (float)roundSize + (float)i >= f2) 
            {
                int j4 = -2 + stringbuffer1.length();
                int k4 = stringbuffer1.length();
                stringbuffer1.delete(j4, k4);
                stringbuffer1.append("\u2026");
            }
        }
        if (poppic != null && baseItem.getFlg() != 1 && i != 0)
        {
            String s5 = (new StringBuilder(String.valueOf(s))).append("\n").append(" ").toString();
            stringbuffer1 = new StringBuffer(s5);
        } else
        if (baseItem.getMessage() != null)
        {
            String s2 = (new StringBuilder(String.valueOf(s))).append("\n").append(baseItem.getMessage()).toString();
            stringbuffer1 = new StringBuffer(s2);
        }
        as = stringbuffer1.toString().split("\n");
        j = as.length;
        k = 0;
        if (j > 1)
        {
            stringbuffer1 = new StringBuffer(s);
            k = (int)((float)(-1 + as.length) * -fontmetrics1.top + fontmetrics1.bottom);
        }
        s3 = stringbuffer1.toString();
        f3 = paint1.measureText(s3) + (float)roundSize + (float)i;
        l = 0;
        i1 = 0;
        if (as == null) goto _L7; else goto _L6
_L6:
        l3 = 0;
_L21:
        if (l3 < as.length) goto _L8; else goto _L7
_L7:
        if (l != 0)
        {
            f3 = paint1.measureText(as[i1]) + (float)roundSize + (float)i;
        }
        as1 = new String[as.length];
        j1 = 0;
        k1 = -1 + as.length;
_L22:
        if (k1 >= 0) goto _L10; else goto _L9
_L9:
        f4 = paint1.measureText(s3) + (float)roundSize;
        s4 = baseItem.getMessage();
        l1 = 0;
        if (s4 != null)
        {
            k3 = baseItem.getFlg();
            l1 = 0;
            if (k3 == 3)
            {
                l1 = 15;
                f15 = paint1.measureText(as[0]) + (float)roundSize;
                f16 = paint1.measureText(as[1]) + (float)roundSize + (float)i;
                f4 = f15;
                if (f15 < f16)
                {
                    f4 = f16;
                }
                f3 = f4;
            }
        }
        f5 = -fontmetrics1.top + fontmetrics1.bottom + (float)k;
        if (baseItem.getMessage() != null && baseItem.getFlg() == 3)
        {
            f5 = 10F + (-fontmetrics1.top + fontmetrics1.bottom + (float)k);
        }
        if (poppic == null || baseItem.getFlg() == 1 || i == 0 || baseItem.getFlg() == 3) goto _L12; else goto _L11
_L11:
        i3 = 0;
        j3 = 0;
_L23:
        if (j3 < poppic.length) goto _L14; else goto _L13
_L13:
        f5 += i3;
_L12:
        if (poppic2 != null && baseItem.getFlg() != 1 && i != 0 && baseItem.getFlg() != 3)
        {
            f14 = paint1.measureText(as[0]) + (float)roundSize + (float)i + (float)poppic2.getIntrinsicWidth();
            if (f3 < f14)
            {
                f3 = f14;
            }
            l1 = 90;
            f5 += 15 + poppic2.getIntrinsicHeight();
        }
        rectf1 = new RectF((float)point1.x - f3 / 2.0F - (float)roundSize, (float)(-20 + point1.y) - f5 - (float)(2 * roundSize), (float)point1.x + f3 / 2.0F + (float)roundSize, -20 + point1.y);
        canvas.drawRoundRect(rectf1, roundSize, roundSize, paint1);
        path = new Path();
        path.moveTo(-10 + point1.x, -20 + point1.y);
        path.lineTo(10 + point1.x, -20 + point1.y);
        path.lineTo(point1.x, point1.y);
        path.close();
        xx = point1.x;
        yy = point1.y;
        canvas.drawPath(path, paint1);
        paint1.setShadowLayer(0.0F, 0.0F, 0.0F, 0);
        paint1.setColor(-1);
        rectf1.centerX() - f4 / 2.0F;
        i2 = 0;
        f6 = 0.0F;
        if ((as == null || poppic != null) && poppic2 == null) goto _L16; else goto _L15
_L15:
        l2 = 0;
_L24:
        if (l2 < as.length) goto _L18; else goto _L17
_L17:
        if (poppic2 != null && baseItem.getFlg() != 1 && i != 0 && baseItem.getFlg() != 3)
        {
            paint1.setStrokeWidth(3F);
            paint1.setStyle(android.graphics.Paint.Style.STROKE);
            paint1.setColor(-1);
            canvas.drawLine((float)point1.x - f3 / 2.0F, rectf1.centerY(), f3 + ((float)point1.x - f3 / 2.0F), rectf1.centerY(), paint1);
        } else
        if (baseItem.getMessage() != null && baseItem.getFlg() == 3)
        {
            paint1.setStrokeWidth(3F);
            paint1.setStyle(android.graphics.Paint.Style.STROKE);
            paint1.setColor(-1);
            canvas.drawLine((float)point1.x - f3 / 2.0F, rectf1.centerY() - 5F, f3 + ((float)point1.x - f3 / 2.0F), rectf1.centerY() - 5F, paint1);
        } else
        if (poppic != null && baseItem.getFlg() != 1 && i != 0)
        {
            paint1.setStrokeWidth(3F);
            paint1.setStyle(android.graphics.Paint.Style.STROKE);
            paint1.setColor(-1);
            canvas.drawLine((float)point1.x - f3 / 2.0F, 35F + (20F + rectf1.top + (float)roundSize + (float)l1), f3 + ((float)point1.x - f3 / 2.0F), 35F + (20F + rectf1.top + (float)roundSize + (float)l1), paint1);
        }
        if (i == 0) goto _L1; else goto _L19
_L19:
        if (poppic == null || baseItem.getFlg() == 1 || baseItem.getFlg() == 3)
        {
            break MISSING_BLOCK_LABEL_3301;
        }
        if (tapRegionType == 1)
        {
            tapPoint = projection.fromPixels(point1.x, (int)((float)((-20 + point1.y) - roundSize) - f5 / 2.0F));
            tapRegion = new RectF(-f3 / 2.0F - (float)roundSize - 20F, -f5 / 2.0F - (float)roundSize - 20F, 20F + (f3 / 2.0F + (float)roundSize), 20F + (f5 / 2.0F + (float)roundSize));
        }
        j2 = 40;
        rectf6 = new RectF(rectf1.right - (float)roundSize - (float)mbSize, 5F + (20F + rectf1.top + (float)roundSize + (float)l1), rectf1.right - (float)roundSize, 5F + (20F + rectf1.top + (float)roundSize + (float)l1));
        Overlay.drawAt(canvas, PinOverlay.moreIcon, (int)rectf6.centerX(), (int)rectf6.centerY(), false);
        if (tapRegionType == 0)
        {
            tapPoint = projection.fromPixels((int)rectf6.centerX(), (int)rectf6.centerY());
            tapRegion = new RectF(-20 + -mbSize / 2, -20 + -mbSize / 2, 20 + mbSize / 2, 20 + mbSize / 2);
        }
        k2 = 0;
_L25:
        if (k2 < poppic.length) goto _L20; else goto _L5
_L5:
        if (baseItem.getMessage() != null && baseItem.getFlg() != 3 && poppic2 == null && poppic == null && !flag)
        {
            point = mapview.getProjection().toPixels(baseItem.getPoint(), null);
            if (baseMarker != null)
            {
                rect = baseMarker.copyBounds();
                rect.offset(point.x, point.y);
                point.x = (rect.left + rect.right) / 2;
                point.y = rect.top;
            }
            float f = (float)(0.90000000000000002D * (double)mapview.getWidth());
            Paint paint = new Paint(1);
            paint.setStyle(android.graphics.Paint.Style.FILL);
            paint.setColor(Color.argb(127, 0, 63, 255));
            paint.setTypeface(Typeface.create(Typeface.SANS_SERIF, 0));
            paint.setTextSize(28F);
            paint.setShadowLayer(10F, 5F, 5F, Color.argb(96, 0, 0, 0));
            fontmetrics = paint.getFontMetrics();
            StringBuffer stringbuffer = new StringBuffer(baseItem.getMessage());
            RectF rectf2;
            RectF rectf3;
            RectF rectf4;
            RectF rectf5;
            RectF rectf7;
            float f7;
            float f8;
            float f9;
            float f10;
            float f11;
            float f12;
            float f13;
            int i4;
            while (stringbuffer.length() > 0 && paint.measureText(stringbuffer.toString()) + (float)roundSize + (float)0 >= f) 
            {
                stringbuffer.delete(-2 + stringbuffer.length(), stringbuffer.length());
                stringbuffer.append("\u2026");
            }
            s1 = stringbuffer.toString();
            f1 = paint.measureText(s1) + (float)roundSize + (float)0;
            rectf = new RectF((float)point.x - f1 / 2.0F - (float)roundSize, (float)(-20 + point.y) - -fontmetrics.top - fontmetrics.bottom - (float)(2 * roundSize) - (float)takasa, (float)point.x + f1 / 2.0F + (float)roundSize, (-20 + point.y) - takasa);
            canvas.drawRoundRect(rectf, roundSize, roundSize, paint);
            paint.setShadowLayer(0.0F, 0.0F, 0.0F, 0);
            paint.setColor(-1);
            canvas.drawText(s1, (float)point.x - f1 / 2.0F, (float)((-20 + point.y) - roundSize) - fontmetrics.bottom - (float)takasa, paint);
            if (true)
            {
                return;
            }
        }
          goto _L1
_L8:
        if (as[l3] != null)
        {
            if (l3 == 0)
            {
                l = as[l3].length();
            }
            i4 = as[l3].length();
            if (l < i4)
            {
                l = as[l3].length();
                i1 = l3;
            }
        }
        l3++;
          goto _L21
_L10:
        as1[j1] = as[k1];
        j1++;
        k1--;
          goto _L22
_L14:
        i3 += poppic[j3].getIntrinsicHeight();
        if (f3 < (float)poppic[j3].getIntrinsicWidth())
        {
            f3 = poppic[j3].getIntrinsicWidth();
        }
        j3++;
          goto _L23
_L18:
        if (l2 == 0)
        {
            f12 = paint1.measureText(as1[l2]) + (float)roundSize;
            f13 = rectf1.centerX() - (float)(mbSize / 2) - f12 / 2.0F;
            if (poppic2 != null && baseItem.getFlg() != 1 && i != 0 && baseItem.getFlg() != 3)
            {
                f6 = 45F + rectf1.centerY();
                canvas.drawText(as1[l2], f13, f6, paint1);
            } else
            if (baseItem.getMessage() != null && baseItem.getFlg() == 3)
            {
                canvas.drawText(as1[l2], f13, (float)((-20 + point1.y) - roundSize) - fontmetrics1.bottom, paint1);
            } else
            {
                canvas.drawText(as1[l2], (float)point1.x - f3 / 2.0F, (float)((-20 + point1.y) - roundSize) - fontmetrics1.bottom, paint1);
            }
        } else
        {
            f8 = paint1.measureText(as1[l2]) + (float)roundSize;
            f9 = rectf1.centerX() - f8 / 2.0F;
            i2 = l1 + (int)((float)l2 * -fontmetrics1.top + fontmetrics1.bottom);
            if (poppic2 != null && baseItem.getFlg() != 1 && i != 0 && baseItem.getFlg() != 3)
            {
                f10 = paint1.measureText(as1[l2]) + (float)roundSize;
                f11 = rectf1.centerX() - (float)(poppic2.getIntrinsicWidth() / 2) - f10 / 2.0F;
                i2 = (int)((float)(-20 + point1.y) - f5 - (float)(2 * roundSize) - ((fontmetrics1.top + fontmetrics1.bottom) - 30F));
                canvas.drawText(as1[l2], f11, i2, paint1);
            } else
            if (baseItem.getMessage() != null && baseItem.getFlg() == 3)
            {
                canvas.drawText(as1[l2], f9, (float)((-20 + point1.y) - roundSize) - (fontmetrics1.bottom + (float)i2), paint1);
            } else
            {
                canvas.drawText(as1[l2], (float)point1.x - f3 / 2.0F, (float)((-20 + point1.y) - roundSize) - (fontmetrics1.bottom + (float)i2), paint1);
            }
        }
        l2++;
          goto _L24
_L16:
        if (baseItem.getFlg() != 1 && i != 0 && baseItem.getFlg() != 3)
        {
            f7 = paint1.measureText(s3) + (float)roundSize;
            canvas.drawText(s3, rectf1.centerX() - (float)(mbSize / 2) - f7 / 2.0F, 15F + (20F + rectf1.top + (float)roundSize + (float)l1), paint1);
            f6 = 0.0F;
            i2 = 0;
        } else
        {
            canvas.drawText(as1[0], (float)point1.x - f3 / 2.0F, (float)((-20 + point1.y) - roundSize) - fontmetrics1.bottom, paint1);
            f6 = 0.0F;
            i2 = 0;
        }
          goto _L17
_L20:
        j2 += 5 + poppic[k2].getIntrinsicHeight();
        rectf7 = new RectF((float)point1.x - f3 / 2.0F, 20F + rectf1.top + (float)roundSize + (float)l1 + (float)j2, f3 + ((float)point1.x - f3 / 2.0F), 20F + rectf1.top + (float)roundSize + (float)l1 + (float)j2);
        Overlay.drawAt(canvas, poppic[k2], (int)rectf7.centerX(), (int)rectf7.centerY(), false);
        tapPint2[k2] = projection.fromPixels((int)rectf7.centerX(), (int)rectf7.centerY());
        tapRegion2[k2] = new RectF(-poppic[k2].getIntrinsicWidth() / 2, -poppic[k2].getIntrinsicHeight() / 2, poppic[k2].getIntrinsicWidth() / 2, poppic[k2].getIntrinsicHeight() / 2);
        k2++;
          goto _L25
        if (poppic2 != null && baseItem.getFlg() != 1 && baseItem.getFlg() != 3)
        {
            rectf4 = new RectF(rectf1.right - (float)roundSize - (float)mbSize, f6 - 8F, rectf1.right - (float)roundSize, f6 - 8F);
            if (!baseItem.getSnippet().equals("1"))
            {
                Overlay.drawAt(canvas, PinOverlay.moreIcon, (int)rectf4.centerX(), (int)rectf4.centerY(), false);
            }
            (int)(rectf4.centerY() + (float)mbSize + (float)poppic2.getIntrinsicHeight());
            rectf5 = new RectF(rectf1.right - (float)roundSize - (float)poppic2.getIntrinsicWidth(), i2 + 12, rectf1.right - (float)roundSize, i2 + 12);
            Overlay.drawAt(canvas, poppic2, (int)rectf5.centerX(), (int)rectf5.centerY(), false);
            tapPint2[0] = projection.fromPixels((int)rectf5.centerX(), (int)rectf5.centerY());
            tapRegion2[0] = new RectF(-poppic2.getIntrinsicWidth() / 2, -poppic2.getIntrinsicHeight() / 2, poppic2.getIntrinsicWidth() / 2, poppic2.getIntrinsicHeight() / 2);
        } else
        if (baseItem.getMessage() != null && baseItem.getFlg() == 3)
        {
            if (tapRegionType == 1)
            {
                tapPoint = projection.fromPixels(point1.x, (int)((float)((-20 + point1.y) - roundSize) - f5 / 2.0F));
                tapRegion = new RectF(-f3 / 2.0F - (float)roundSize - 20F, -f5 / 2.0F - (float)roundSize - 20F, 20F + (f3 / 2.0F + (float)roundSize), 20F + (f5 / 2.0F + (float)roundSize));
            }
            rectf3 = new RectF(rectf1.right - (float)roundSize - (float)mbSize, rectf1.centerY() - (float)(mbSize / 2), rectf1.right - (float)roundSize, rectf1.centerY() + (float)(mbSize / 2));
            Overlay.drawAt(canvas, PinOverlay.moreIcon, (int)rectf3.centerX(), 10 + (int)rectf3.bottom, false);
            if (tapRegionType == 0)
            {
                tapPoint = projection.fromPixels((int)rectf3.centerX(), (int)rectf3.centerY());
                tapRegion = new RectF(-20 + -mbSize / 2, -20 + -mbSize / 2, 20 + mbSize / 2, 20 + mbSize / 2);
            }
        } else
        {
            if (tapRegionType == 1)
            {
                tapPoint = projection.fromPixels(point1.x, (int)((float)((-20 + point1.y) - roundSize) - f5 / 2.0F));
                tapRegion = new RectF(-f3 / 2.0F - (float)roundSize - 20F, -f5 / 2.0F - (float)roundSize - 20F, 20F + (f3 / 2.0F + (float)roundSize), 20F + (f5 / 2.0F + (float)roundSize));
            }
            rectf2 = new RectF(rectf1.right - (float)roundSize - (float)mbSize - (float)wkw, rectf1.centerY() - (float)(mbSize / 2) - (float)wkw, rectf1.right - (float)roundSize, rectf1.centerY() + (float)(mbSize / 2));
            Overlay.drawAt(canvas, PinOverlay.moreIcon, (int)rectf2.centerX(), (int)rectf2.centerY(), false);
            if (tapRegionType == 0)
            {
                tapPoint = projection.fromPixels((int)rectf2.centerX(), (int)rectf2.centerY());
                tapRegion = new RectF(-20 + -mbSize / 2, -20 + -mbSize / 2, 20 + mbSize / 2, 20 + mbSize / 2);
            }
        }
          goto _L5
    }

    public OverlayItem getBaseItem()
    {
        return baseItem;
    }

    public float getX()
    {
        return xx;
    }

    public float getY()
    {
        return yy;
    }

    public void onFocusChanged(ItemizedOverlay itemizedoverlay, OverlayItem overlayitem)
    {
        if (overlayitem != null)
        {
            openPopup(itemizedoverlay, overlayitem);
            return;
        } else
        {
            closePopup();
            return;
        }
    }

    public void onTap(OverlayItem overlayitem)
    {
    }

    public void onTap(OverlayItem overlayitem, int i)
    {
    }

    public boolean onTap(GeoPoint geopoint, MapView mapview)
    {
        if (poppic == null && poppic2 == null || tapPint2 == null && tapRegion2 == null) goto _L2; else goto _L1
_L1:
        Projection projection;
        Point point;
        projection = mapview.getProjection();
        point = projection.toPixels(geopoint, null);
        if (poppic == null) goto _L4; else goto _L3
_L3:
        int i = 0;
_L8:
        if (i < poppic.length) goto _L5; else goto _L2
_L2:
        if (tapPoint == null || tapRegion == null)
        {
            return false;
        }
        break; /* Loop/switch isn't completed */
_L5:
        if (tapPint2[i] != null)
        {
            Point point4 = projection.toPixels(tapPint2[i], null);
            RectF rectf2 = new RectF(tapRegion2[i]);
            rectf2.offset(point4.x, point4.y);
            if (rectf2.contains(point.x, point.y))
            {
                onTap(baseItem, i);
                return true;
            }
        }
        i++;
        continue; /* Loop/switch isn't completed */
_L4:
        if (tapPint2[0] != null)
        {
            Point point1 = projection.toPixels(tapPint2[0], null);
            RectF rectf = new RectF(tapRegion2[0]);
            rectf.offset(point1.x, point1.y);
            if (rectf.contains(point.x, point.y))
            {
                onTap(baseItem, 0);
                return true;
            }
        }
        if (true) goto _L2; else goto _L6
_L6:
        Projection projection1 = mapview.getProjection();
        Point point2 = projection1.toPixels(geopoint, null);
        Point point3 = projection1.toPixels(tapPoint, null);
        RectF rectf1 = new RectF(tapRegion);
        rectf1.offset(point3.x, point3.y);
        if (rectf1.contains(point2.x, point2.y))
        {
            onTap(baseItem);
            return true;
        }
        return false;
        if (true) goto _L8; else goto _L7
_L7:
    }

    public boolean onTouchEvent(MotionEvent motionevent, MapView mapview)
    {
        return false;
    }

    public void openPopup(ItemizedOverlay itemizedoverlay, OverlayItem overlayitem)
    {
        baseItem = overlayitem;
        baseMarker = itemizedoverlay.getMarker(overlayitem);
        int i = PinOverlay.moreIcon.getIntrinsicWidth();
        wkw = i - mbSize;
        if (wkw > 0)
        {
            mbSize = i / 2;
            roundSize = i / 2;
        } else
        {
            wkw = 0;
        }
        if (mMapView != null)
        {
            mMapView.reDraw();
        }
    }
}

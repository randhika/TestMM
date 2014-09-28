// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.maps;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import java.util.ArrayList;
import java.util.List;

// Referenced classes of package jp.co.yahoo.android.maps:
//            ItemizedOverlay, OverlayItem, GeoPoint

public class PinOverlay extends ItemizedOverlay
{
    static class PinItem
    {

        Drawable marker;
        final Object mdata;
        final String mess;
        final int mflg;
        final GeoPoint point;
        final String snippet;
        final String title;

        PinItem(GeoPoint geopoint, String s, String s1, String s2, int i, Object obj)
        {
            point = geopoint;
            title = s;
            snippet = s1;
            mess = s2;
            mflg = i;
            mdata = obj;
        }
    }


    public static final int PIN_GREEN = 0;
    public static final int PIN_RED = 1;
    public static final int PIN_VIOLET = 2;
    private static Drawable builtinIcon[];
    static Bitmap more;
    static Drawable moreIcon;
    public int angle;
    private List points;

    public PinOverlay(int i)
    {
        super(boundCenterBottom(builtinIcon[i]));
        points = new ArrayList();
        angle = 0;
    }

    public PinOverlay(Drawable drawable)
    {
        super(boundCenterBottom(drawable));
        points = new ArrayList();
        angle = 0;
    }

    public PinOverlay(Drawable drawable, int i, int j)
    {
        super(boundXYPixel(drawable, i, j));
        points = new ArrayList();
        angle = 0;
    }

    static Drawable boundXYPixel(Drawable drawable, int i, int j)
    {
        int k = drawable.getIntrinsicWidth();
        int l = drawable.getIntrinsicHeight();
        if (k > 0 && l > 0)
        {
            drawable.setBounds(-i, -j, k - i, l - j);
        }
        return drawable;
    }

    static void createBuiltinIcon(Context context)
    {
        builtinIcon = new Drawable[3];
        String s = context.getPackageName();
        int i = context.getResources().getIdentifier("pin_green_l", "drawable", s);
        int j = context.getResources().getIdentifier("pin_red_l", "drawable", s);
        int k = context.getResources().getIdentifier("pin_violet_l", "drawable", s);
        int l = context.getResources().getIdentifier("moreicon32", "drawable", s);
        builtinIcon[0] = context.getResources().getDrawable(i);
        builtinIcon[1] = context.getResources().getDrawable(j);
        builtinIcon[2] = context.getResources().getDrawable(k);
        moreIcon = boundXYPixel(context.getResources().getDrawable(l), 16, 16);
        more = BitmapFactory.decodeResource(context.getResources(), l);
        more.setDensity(160);
    }

    public Object addPoint(GeoPoint geopoint, String s)
    {
        return addPoint(geopoint, s, "");
    }

    public Object addPoint(GeoPoint geopoint, String s, String s1)
    {
        PinItem pinitem = new PinItem(geopoint, s, s1, null, 0, null);
        points.add(pinitem);
        populate();
        return pinitem;
    }

    public Object addPoint(GeoPoint geopoint, String s, String s1, String s2, int i)
    {
        PinItem pinitem = new PinItem(geopoint, s, s1, s2, i, null);
        points.add(pinitem);
        populate();
        return pinitem;
    }

    public Object addPoint(GeoPoint geopoint, String s, String s1, String s2, int i, Object obj)
    {
        PinItem pinitem = new PinItem(geopoint, s, s1, s2, i, obj);
        points.add(pinitem);
        populate();
        return pinitem;
    }

    public void clearPoint()
    {
        points.clear();
        populate();
    }

    protected OverlayItem createItem(int i)
    {
        PinItem pinitem = (PinItem)points.get(i);
        OverlayItem overlayitem = new OverlayItem(pinitem.point, pinitem.title, pinitem.snippet, pinitem.mess, pinitem.mflg, pinitem.mdata);
        if (animeFlag)
        {
            overlayitem.setAnimationCount(500);
        }
        if (pinitem.marker != null)
        {
            overlayitem.setMarker(pinitem.marker);
        }
        return overlayitem;
    }

    protected void onTap(Object obj)
    {
    }

    protected boolean onTap(int i)
    {
        onTap(points.get(i));
        return true;
    }

    public void removePoint(Object obj)
    {
        points.remove((PinItem)obj);
        populate();
    }

    public void setPinShadow(Drawable drawable, int i, int j)
    {
        setPinShadow(boundXYPixel(drawable, i, j));
    }

    public int setangle()
    {
        return angle;
    }

    public int size()
    {
        return points.size();
    }
}

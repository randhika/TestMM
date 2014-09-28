// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.maps;

import android.graphics.Point;
import android.graphics.drawable.Drawable;

// Referenced classes of package jp.co.yahoo.android.maps:
//            GeoPoint

public class OverlayItem
{

    public static final int ITEM_STATE_FOCUSED_MASK = 4;
    public static final int ITEM_STATE_PRESSED_MASK = 1;
    public static final int ITEM_STATE_SELECTED_MASK = 2;
    private int mAnimationCount;
    protected Drawable mMarker;
    protected final GeoPoint mPoint;
    protected final String mSnippet;
    protected final String mTitle;
    protected Point mWorldPoint;
    protected Object mdata;
    protected final String mess;
    protected final int mflg;

    public OverlayItem(GeoPoint geopoint, String s, String s1, String s2, int i, Object obj)
    {
        mdata = null;
        mAnimationCount = 0;
        mPoint = geopoint;
        mTitle = s;
        mSnippet = s1;
        mess = s2;
        mflg = i;
        mdata = obj;
    }

    public static void setState(Drawable drawable, int i)
    {
        int ai[] = new int[3];
        if ((i & 1) != 0)
        {
            ai[0] = 0x10100a7;
        }
        if ((i & 2) != 0)
        {
            ai[1] = 0x10100a1;
        }
        if ((i & 4) != 0)
        {
            ai[2] = 0x101009c;
        }
        drawable.setState(ai);
    }

    public int getAnimationCount()
    {
        return mAnimationCount;
    }

    public Object getData()
    {
        return mdata;
    }

    public int getFlg()
    {
        return mflg;
    }

    public Drawable getMarker(int i)
    {
        if (mMarker != null)
        {
            setState(mMarker, i);
        }
        return mMarker;
    }

    public String getMessage()
    {
        return mess;
    }

    public GeoPoint getPoint()
    {
        return mPoint;
    }

    public String getSnippet()
    {
        return mSnippet;
    }

    public String getTitle()
    {
        return mTitle;
    }

    public Point getWorldPoint()
    {
        return mWorldPoint;
    }

    public String routableAddress()
    {
        GeoPoint geopoint = getPoint();
        double d = (double)geopoint.getLatitudeE6() / 1000000D;
        double d1 = (double)geopoint.getLongitudeE6() / 1000000D;
        return (new StringBuilder()).append(d).append(",").append(d1).toString();
    }

    public void setAnimationCount(int i)
    {
        mAnimationCount = i;
    }

    public void setMarker(Drawable drawable)
    {
        mMarker = drawable;
    }

    public void setWorldPoint(int i, int j)
    {
        if (mWorldPoint == null)
        {
            mWorldPoint = new Point();
        }
        mWorldPoint.x = i;
        mWorldPoint.y = j;
    }

    public void setWorldPoint(Point point)
    {
        mWorldPoint = point;
    }
}

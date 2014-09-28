// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.maps.ar;

import android.app.Activity;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.MotionEvent;
import android.view.View;
import java.util.ArrayList;

// Referenced classes of package jp.co.yahoo.android.maps.ar:
//            NavigationMgr, POI, POIViewListener, Vector2D

public class POIView extends View
{

    private float VIEWPORT_WIDTH_DEGREE;
    private POIViewListener listener;
    private NavigationMgr naviMgr;

    public POIView(Activity activity, NavigationMgr navigationmgr, POIViewListener poiviewlistener)
    {
        super(activity);
        VIEWPORT_WIDTH_DEGREE = 90F;
        setFocusable(true);
        naviMgr = navigationmgr;
        listener = poiviewlistener;
    }

    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
        int i = 0;
        do
        {
            if (i >= naviMgr.getPoiList().size())
            {
                return;
            }
            POI poi = (POI)naviMgr.getPoiList().get(i);
            double d = poi.getIcon().getIntrinsicWidth();
            double d1 = poi.getIcon().getIntrinsicHeight();
            poi.getIcon().setBounds((int)(poi.getLogX() - (double)poi.getHotX()), (int)(d1 + (poi.getLogY() - (double)poi.getHotY())), (int)(poi.getLogX() + (d - (double)poi.getHotX())), (int)(d1 + (poi.getLogY() + (d1 - (double)poi.getHotY()))));
            poi.getIcon().draw(canvas);
            i++;
        } while (true);
    }

    public boolean onTouchEvent(MotionEvent motionevent)
    {
        0xff & motionevent.getAction();
        JVM INSTR tableswitch 1 1: default 28
    //                   1 30;
           goto _L1 _L2
_L1:
        return true;
_L2:
        int i;
        int j;
        int k;
        i = (int)motionevent.getX();
        j = (int)motionevent.getY();
        k = 0;
_L6:
        if (k >= naviMgr.getPoiList().size()) goto _L1; else goto _L3
_L3:
        if (!((POI)naviMgr.getPoiList().get(k)).getIcon().getBounds().contains(i, j)) goto _L5; else goto _L4
_L4:
        listener.POIViewListenerOnPick(k);
          goto _L1
_L5:
        k++;
          goto _L6
    }

    protected Vector2D pointInView(double d, double d1)
    {
        Vector2D vector2d = new Vector2D();
        double d2 = (double)naviMgr.getAzimuth() - (double)VIEWPORT_WIDTH_DEGREE / 2D;
        if (d2 < 0.0D)
        {
            d2 += 360D;
        }
        if (d1 < d2)
        {
            vector2d.mX = (float)(((d1 + (360D - d2)) / (double)VIEWPORT_WIDTH_DEGREE) * (double)getWidth());
        } else
        {
            vector2d.mX = (float)(((d1 - d2) / (double)VIEWPORT_WIDTH_DEGREE) * (double)getWidth());
        }
        vector2d.mY = 0.0F;
        return vector2d;
    }

    public void updateLocation()
    {
        int i = 0;
        do
        {
            if (i >= naviMgr.getPoiList().size())
            {
                invalidate();
                return;
            }
            POI poi = (POI)naviMgr.getPoiList().get(i);
            double d = naviMgr.calcDist(poi.getLat(), poi.getLon());
            Vector2D vector2d = pointInView(d, naviMgr.calcAngle(poi.getLat(), poi.getLon(), d));
            if (i == naviMgr.getDestination())
            {
                vector2d.mY = 30F + vector2d.mY;
            }
            poi.setLogPoint(vector2d.mX, vector2d.mY);
            i++;
        } while (true);
    }
}

// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.maps.routing;

import android.content.Context;
import android.content.res.Resources;
import android.os.AsyncTask;
import java.util.ArrayList;
import jp.co.yahoo.android.maps.GeoPoint;
import jp.co.yahoo.android.maps.MapView;
import jp.co.yahoo.android.maps.PopupOverlay;

// Referenced classes of package jp.co.yahoo.android.maps.routing:
//            RouteSearch, RouteOverlay, RouteControl, RLine, 
//            GPoint, RouteNodeInfo

private class <init> extends AsyncTask
{

    final RouteOverlay this$0;

    protected volatile transient Object doInBackground(Object aobj[])
    {
        return doInBackground((String[])aobj);
    }

    protected transient ArrayList doInBackground(String as[])
    {
        RouteSearch routesearch;
        String s;
        routesearch = new RouteSearch();
        s = "car";
        RouteOverlay.access$0(RouteOverlay.this);
        JVM INSTR tableswitch 0 2: default 44
    //                   0 191
    //                   1 44
    //                   2 197;
           goto _L1 _L2 _L1 _L3
_L1:
        break; /* Loop/switch isn't completed */
_L3:
        break MISSING_BLOCK_LABEL_197;
_L4:
        if (RouteOverlay.access$1(RouteOverlay.this))
        {
            routesearch.setTollway(2);
        } else
        {
            routesearch.setTollway(1);
        }
        routesearch.setTraffic(s);
        routesearch.setGuide(3);
        routesearch.setStartPos((double)RouteOverlay.access$2(RouteOverlay.this).getLatitudeE6() / 1000000D, (double)RouteOverlay.access$2(RouteOverlay.this).getLongitudeE6() / 1000000D);
        routesearch.setGoalPos((double)RouteOverlay.access$3(RouteOverlay.this).getLatitudeE6() / 1000000D, (double)RouteOverlay.access$3(RouteOverlay.this).getLongitudeE6() / 1000000D);
        routesearch.setAPPID(RouteOverlay.access$4(RouteOverlay.this));
        RouteOverlay.access$5(RouteOverlay.this, new RouteControl());
        routesearch.setRouteCtl(RouteOverlay.access$6(RouteOverlay.this));
        routesearch.setAPPID(RouteOverlay.access$4(RouteOverlay.this));
        routesearch.search();
        return null;
_L2:
        s = "car";
          goto _L4
        s = "walk2";
          goto _L4
    }

    protected volatile void onPostExecute(Object obj)
    {
        onPostExecute((ArrayList)obj);
    }

    protected void onPostExecute(ArrayList arraylist)
    {
        RouteOverlay.access$7(RouteOverlay.this).clear();
        RouteOverlay.access$8(RouteOverlay.this).clear();
        RouteOverlay.access$9(RouteOverlay.this).clear();
        if (RouteOverlay.access$6(RouteOverlay.this).count() != 0) goto _L2; else goto _L1
_L1:
        RouteOverlay.access$10(RouteOverlay.this, null);
        if (RouteOverlay.access$11(RouteOverlay.this) != null)
        {
            RouteOverlay.access$11(RouteOverlay.this).reDraw();
        }
        RouteOverlay.access$12(RouteOverlay.this).errorRouteSearch(RouteOverlay.this, 1);
_L5:
        return;
_L2:
        PopupOverlay popupoverlay;
        String s;
        android.graphics.drawable.Drawable drawable;
        int j;
        int k;
        popupoverlay = new PopupOverlay();
        popupoverlay.tapRegionType = 1;
        s = RouteOverlay.access$13(RouteOverlay.this).getPackageName();
        int i = RouteOverlay.access$13(RouteOverlay.this).getResources().getIdentifier("pin_route", "drawable", s);
        drawable = RouteOverlay.access$13(RouteOverlay.this).getResources().getDrawable(i);
        j = 0;
        k = 0;
_L6:
        if (k < -1 + RouteOverlay.access$6(RouteOverlay.this).count()) goto _L4; else goto _L3
_L3:
        int l;
        GeoPoint ageopoint[] = RouteOverlay.access$6(RouteOverlay.this).getGeoPoints();
         1 = new (RouteOverlay.this, ageopoint);
        1.setWidth(10F);
        1.setColor(0xa00000ff);
        RouteOverlay.access$7(RouteOverlay.this).add(1);
        l = 0;
_L7:
        if (l < RouteOverlay.access$9(RouteOverlay.this).size())
        {
            break MISSING_BLOCK_LABEL_771;
        }
        int i1 = RouteOverlay.access$13(RouteOverlay.this).getResources().getIdentifier("pin_start", "drawable", s);
        android.graphics.drawable.Drawable drawable1 = RouteOverlay.access$13(RouteOverlay.this).getResources().getDrawable(i1);
         3 = new <init>(RouteOverlay.this, drawable1);
        3.setOnFocusChangeListener(popupoverlay);
        RouteOverlay.access$7(RouteOverlay.this).add(3);
        3.addPoint(RouteOverlay.access$2(RouteOverlay.this), RouteOverlay.access$15(RouteOverlay.this), null, null, 1);
        RouteOverlay.access$16(RouteOverlay.this, 3);
        RouteOverlay.access$17(RouteOverlay.this).visible = RouteOverlay.access$18(RouteOverlay.this);
        int j1 = RouteOverlay.access$13(RouteOverlay.this).getResources().getIdentifier("pin_goal", "drawable", s);
        android.graphics.drawable.Drawable drawable2 = RouteOverlay.access$13(RouteOverlay.this).getResources().getDrawable(j1);
         4 = new <init>(RouteOverlay.this, drawable2);
        4.setOnFocusChangeListener(popupoverlay);
        RouteOverlay.access$7(RouteOverlay.this).add(4);
        4.addPoint(RouteOverlay.access$3(RouteOverlay.this), RouteOverlay.access$19(RouteOverlay.this), null, null, 1);
        RouteOverlay.access$20(RouteOverlay.this, 4);
        RouteOverlay.access$21(RouteOverlay.this).visible = RouteOverlay.access$22(RouteOverlay.this);
        RouteOverlay.access$7(RouteOverlay.this).add(popupoverlay);
        if (RouteOverlay.access$12(RouteOverlay.this) != null)
        {
            RouteOverlay.access$12(RouteOverlay.this).finishRouteSearch(RouteOverlay.this);
        }
        RouteOverlay.access$10(RouteOverlay.this, null);
        if (RouteOverlay.access$11(RouteOverlay.this) != null)
        {
            RouteOverlay.access$11(RouteOverlay.this).reDraw();
            return;
        }
          goto _L5
_L4:
        RLine rline = RouteOverlay.access$6(RouteOverlay.this).getRLine(k);
        if (k != 0 && rline != null && RouteOverlay.access$6(RouteOverlay.this).getGuideMesage(rline) != null)
        {
              = new <init>(RouteOverlay.this, drawable);
            .setOnFocusChangeListener(popupoverlay);
            .z = rline.getHierarchy();
            .visible = RouteOverlay.access$14(RouteOverlay.this);
            String.valueOf(j);
            if (k + 1 < RouteOverlay.access$6(RouteOverlay.this).count())
            {
                RouteOverlay.access$6(RouteOverlay.this).getRLine(k + 1);
            }
            if (k - 1 >= 0)
            {
                RouteOverlay.access$6(RouteOverlay.this).getRLine(k - 1);
            }
            .addPoint(rline.getBeginPoint().getGeoPoint(), RouteOverlay.access$6(RouteOverlay.this).getGuideMesage(rline));
            j++;
            RouteNodeInfo routenodeinfo = new RouteNodeInfo(rline);
            RouteOverlay.access$8(RouteOverlay.this).add(routenodeinfo);
            RouteOverlay.access$9(RouteOverlay.this).add();
        }
        k++;
          goto _L6
         2 = (this._cls0)RouteOverlay.access$9(RouteOverlay.this).get(l);
        RouteOverlay.access$7(RouteOverlay.this).add(2);
        l++;
          goto _L7
    }

    private ()
    {
        this$0 = RouteOverlay.this;
        super();
    }

    this._cls0(this._cls0 _pcls0)
    {
        this();
    }
}

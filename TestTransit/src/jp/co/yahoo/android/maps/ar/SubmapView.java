// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.maps.ar;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import java.util.List;
import jp.co.yahoo.android.maps.CircleOverlay;
import jp.co.yahoo.android.maps.GeoPoint;
import jp.co.yahoo.android.maps.MapController;
import jp.co.yahoo.android.maps.MapView;
import jp.co.yahoo.android.maps.MapViewCtrl;
import jp.co.yahoo.android.maps.PinOverlay;
import jp.co.yahoo.android.maps.PolylineOverlay;

// Referenced classes of package jp.co.yahoo.android.maps.ar:
//            NavigationMgr

public class SubmapView extends MapView
{

    private CircleOverlay circleOverlay;
    private int nCenterSet;
    private NavigationMgr naviMgr;
    private PinOverlay pinOverlay;

    public SubmapView(Activity activity, String s, NavigationMgr navigationmgr)
    {
        super(activity, s);
        naviMgr = navigationmgr;
        nCenterSet = 0;
        String s1 = activity.getPackageName();
        int i = activity.getResources().getIdentifier("compass48", "drawable", s1);
        pinOverlay = new PinOverlay((BitmapDrawable)getResources().getDrawable(i), 16, 24);
        getOverlays().add(pinOverlay);
        MapController mapcontroller = getMapController();
        ((MapViewCtrl)mapcontroller).setCpOn(false);
        mapcontroller.setZoom(4);
    }

    public void setRoute(GeoPoint ageopoint[])
    {
        PolylineOverlay polylineoverlay = new PolylineOverlay(ageopoint) {

            final SubmapView this$0;

            protected boolean onTap()
            {
                return true;
            }

            
            {
                this$0 = SubmapView.this;
                super(ageopoint);
            }
        };
        polylineoverlay.setWidth(10F);
        polylineoverlay.setColor(Color.argb(128, 0, 0, 255));
        getOverlays().add(polylineoverlay);
    }

    public void updateLocation()
    {
        MapController mapcontroller = getMapController();
        GeoPoint geopoint = new GeoPoint((int)Math.round(1000000D * naviMgr.getLat()), (int)Math.round(1000000D * naviMgr.getLon()));
        if (nCenterSet < 10)
        {
            mapcontroller.setCenter(geopoint);
            nCenterSet = 1 + nCenterSet;
        } else
        {
            mapcontroller.animateTo(geopoint);
        }
        pinOverlay.clearPoint();
        pinOverlay.angle = (int)naviMgr.getAzimuth();
        pinOverlay.addPoint(geopoint, null);
        if (circleOverlay != null)
        {
            getOverlays().remove(circleOverlay);
        }
        circleOverlay = new CircleOverlay(geopoint, Math.round(naviMgr.getAccuracy()));
        circleOverlay.setFillColor(Color.argb(30, 96, 79, 247));
        circleOverlay.setStrokeColor(Color.argb(150, 96, 79, 247));
        getOverlays().add(circleOverlay);
    }
}

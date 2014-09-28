// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import jp.co.yahoo.android.maps.GeoPoint;
import jp.co.yahoo.android.maps.MapController;
import jp.co.yahoo.android.maps.MapView;
import jp.co.yahoo.android.maps.MyLocationOverlay;

// Referenced classes of package jp.co.yahoo.android.apps.transit:
//            MapDisplay

public class _activity extends MyLocationOverlay
{

    Activity _activity;
    MapView _mapView;
    final MapDisplay this$0;

    public void onLocationChanged(Location location)
    {
        super.onLocationChanged(location);
        if (_mapView.getMapController() != null)
        {
            GeoPoint geopoint = new GeoPoint((int)(1000000D * location.getLatitude()), (int)(1000000D * location.getLongitude()));
            _mapView.getMapController().animateTo(geopoint);
            _mapView.invalidate();
        }
    }

    public (Context context, MapView mapview, Activity activity)
    {
        this$0 = MapDisplay.this;
        super(context, mapview);
        _mapView = null;
        _activity = null;
        _mapView = mapview;
        _activity = activity;
    }
}

// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.maps;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Point;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.MotionEvent;
import java.util.List;
import jp.co.yahoo.android.maps.navi.CompassOverlay;

// Referenced classes of package jp.co.yahoo.android.maps:
//            Overlay, GeoPoint, MapView, PinOverlay

public class MyLocationOverlay extends Overlay
    implements LocationListener, MapView.MapTouchListener
{

    private float _accuracy;
    private CompassOverlay _compassLocationOverlay;
    private Context _context;
    private boolean _enableMyLocation;
    private LocationListener _gpsLocationListener;
    private String _last_location_provider;
    private long _last_location_time;
    private Location _location;
    private LocationManager _locationManager;
    private GeoPoint _location_point;
    private MapView _mapView;
    private LocationListener _networkLocationListener;
    private Runnable _runOnFirstFix;

    public MyLocationOverlay(Context context, MapView mapview)
    {
        _locationManager = null;
        _gpsLocationListener = null;
        _networkLocationListener = null;
        _location = null;
        _location_point = null;
        _last_location_time = 0L;
        _last_location_provider = null;
        _compassLocationOverlay = null;
        _enableMyLocation = false;
        _runOnFirstFix = null;
        _context = context;
        _mapView = mapview;
    }

    private void updateLocation(Location location)
    {
        if (_last_location_provider == null || location.getProvider().equals("gps") || location.getProvider().equals("network") && location.getTime() - _last_location_time > 30000L)
        {
            _location = location;
            _location_point = new GeoPoint((int)(1000000D * location.getLatitude()), (int)(1000000D * location.getLongitude()));
            _accuracy = location.getAccuracy();
            _last_location_provider = location.getProvider();
            _last_location_time = location.getTime();
            if (_runOnFirstFix != null)
            {
                _runOnFirstFix.run();
                _runOnFirstFix = null;
            }
            _mapView.reDraw();
            onLocationChanged(location);
        }
    }

    public void disableMyLocation()
    {
        if (_locationManager != null)
        {
            _locationManager.removeUpdates(_gpsLocationListener);
            _locationManager.removeUpdates(_networkLocationListener);
            _locationManager = null;
            _gpsLocationListener = null;
            _networkLocationListener = null;
        }
        if (_compassLocationOverlay != null)
        {
            _compassLocationOverlay.stopCompass();
            _compassLocationOverlay = null;
        }
        _enableMyLocation = false;
    }

    protected boolean dispatchTap()
    {
        return false;
    }

    public void draw(Canvas canvas, MapView mapview, boolean flag)
    {
        super.draw(canvas, mapview, flag);
        if (isMyLocationEnabled() && _location_point != null)
        {
            drawMyLocation(canvas, mapview, _location, _location_point);
        }
    }

    protected void drawMyLocation(Canvas canvas, MapView mapview, Location location, GeoPoint geopoint)
    {
        if (_compassLocationOverlay == null)
        {
            _compassLocationOverlay = new CompassOverlay(_context, _mapView);
        }
        _compassLocationOverlay.setpos(geopoint, (int)location.getAccuracy());
        _mapView.getOverlays().remove(_compassLocationOverlay);
        _mapView.getOverlays().add(_compassLocationOverlay);
        _mapView.reDraw();
    }

    public boolean enableMyLocation()
    {
        return enableMyLocation(5000);
    }

    public boolean enableMyLocation(int i)
    {
        if (_locationManager == null)
        {
            _gpsLocationListener = new LocationListener() {

                final MyLocationOverlay this$0;

                public void onLocationChanged(Location location)
                {
                    updateLocation(location);
                }

                public void onProviderDisabled(String s)
                {
                }

                public void onProviderEnabled(String s)
                {
                }

                public void onStatusChanged(String s, int j, Bundle bundle)
                {
                }

            
            {
                this$0 = MyLocationOverlay.this;
                super();
            }
            };
            _networkLocationListener = new LocationListener() {

                final MyLocationOverlay this$0;

                public void onLocationChanged(Location location)
                {
                    updateLocation(location);
                }

                public void onProviderDisabled(String s)
                {
                }

                public void onProviderEnabled(String s)
                {
                }

                public void onStatusChanged(String s, int j, Bundle bundle)
                {
                }

            
            {
                this$0 = MyLocationOverlay.this;
                super();
            }
            };
            _locationManager = (LocationManager)_context.getSystemService("location");
            _locationManager.requestLocationUpdates("gps", i, 0.0F, _gpsLocationListener);
            _locationManager.requestLocationUpdates("network", i, 0.0F, _networkLocationListener);
        }
        _enableMyLocation = true;
        return _locationManager.isProviderEnabled("gps") || _locationManager.isProviderEnabled("network");
    }

    public Location getLastFix()
    {
        return _location;
    }

    public GeoPoint getMyLocation()
    {
        return _location_point;
    }

    public float getOrientation()
    {
        return 0.0F;
    }

    public boolean isMyLocationEnabled()
    {
        return _enableMyLocation;
    }

    public void onAccuracyChanged(int i, int j)
    {
    }

    public void onLocationChanged(Location location)
    {
    }

    public boolean onLongPress(MapView mapview, Object obj, PinOverlay pinoverlay, GeoPoint geopoint)
    {
        return false;
    }

    public boolean onPinchIn(MapView mapview)
    {
        return false;
    }

    public boolean onPinchOut(MapView mapview)
    {
        return false;
    }

    public void onProviderDisabled(String s)
    {
    }

    public void onProviderEnabled(String s)
    {
    }

    public void onSensorChanged(int i, float af[])
    {
    }

    public boolean onSnapToItem(int i, int j, Point point, MapView mapview)
    {
        return false;
    }

    public void onStatusChanged(String s, int i, Bundle bundle)
    {
    }

    public boolean onTap(GeoPoint geopoint, MapView mapview)
    {
        return false;
    }

    public boolean onTouch(MapView mapview, MotionEvent motionevent)
    {
        return false;
    }

    public boolean runOnFirstFix(Runnable runnable)
    {
        if (runnable != null)
        {
            _runOnFirstFix = runnable;
            if (_location_point != null)
            {
                _runOnFirstFix.run();
                _runOnFirstFix = null;
                return true;
            }
        }
        return false;
    }

}

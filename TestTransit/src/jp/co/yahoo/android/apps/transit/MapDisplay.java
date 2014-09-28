// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import java.util.List;
import jp.co.yahoo.android.apps.transit.api.data.StationData;
import jp.co.yahoo.android.apps.transit.common.util.TransitUtil;
import jp.co.yahoo.android.apps.transit.viewparts.TransitDialogBuilder;
import jp.co.yahoo.android.maps.GeoPoint;
import jp.co.yahoo.android.maps.MapController;
import jp.co.yahoo.android.maps.MapView;
import jp.co.yahoo.android.maps.MyLocationOverlay;
import jp.co.yahoo.android.maps.routing.RouteOverlay;

// Referenced classes of package jp.co.yahoo.android.apps.transit:
//            TransitBaseActivity

public class MapDisplay extends TransitBaseActivity
    implements jp.co.yahoo.android.maps.routing.RouteOverlay.RouteOverlayListener
{
    public class TransitLocationOverlay extends MyLocationOverlay
    {

        Activity _activity;
        MapView _mapView;
        final MapDisplay this$0;

        public void onLocationChanged(Location location1)
        {
            super.onLocationChanged(location1);
            if (_mapView.getMapController() != null)
            {
                GeoPoint geopoint = new GeoPoint((int)(1000000D * location1.getLatitude()), (int)(1000000D * location1.getLongitude()));
                _mapView.getMapController().animateTo(geopoint);
                _mapView.invalidate();
            }
        }

        public TransitLocationOverlay(Context context, MapView mapview, Activity activity)
        {
            this$0 = MapDisplay.this;
            super(context, mapview);
            _mapView = null;
            _activity = null;
            _mapView = mapview;
            _activity = activity;
        }
    }


    private TransitLocationOverlay _overlay;
    private ImageView btnScope;
    private StationData goal;
    private LocationManager location;
    private MapController mapControl;
    private MapView mapView;
    private AlertDialog mapappDialog;
    private GeoPoint pinPoint;
    private Resources res;
    private StationData start;
    private StationData station;

    public MapDisplay()
    {
    }

    public boolean errorRouteSearch(RouteOverlay routeoverlay, int i)
    {
        return false;
    }

    public boolean finishRouteSearch(RouteOverlay routeoverlay)
    {
        GeoPoint geopoint = new GeoPoint(TransitUtil.getLatLngInt(start.getLat()), TransitUtil.getLatLngInt(start.getLon()));
        GeoPoint geopoint1 = new GeoPoint(TransitUtil.getLatLngInt(goal.getLat()), TransitUtil.getLatLngInt(goal.getLon()));
        GeoPoint geopoint2 = new GeoPoint((geopoint.getLatitudeE6() + geopoint1.getLatitudeE6()) / 2, (geopoint.getLongitudeE6() + geopoint1.getLongitudeE6()) / 2);
        int i = Math.abs(geopoint1.getLatitudeE6() - geopoint.getLatitudeE6());
        int j = Math.abs(geopoint1.getLongitudeE6() - geopoint.getLongitudeE6());
        mapControl.zoomToSpan(i, j);
        mapControl.setCenter(geopoint2);
        mapControl.zoomOut();
        return false;
    }

    public void leadToMapAppOnMarket(View view)
    {
        leadToAppOnMarket(getString(0x7f0d0059));
        if (mapappDialog != null && mapappDialog.isShowing())
        {
            mapappDialog.dismiss();
        }
    }

    public void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setContentView(0x7f030033);
        Intent intent = getIntent();
        station = (StationData)intent.getSerializableExtra(getString(0x7f0d023e));
        LinearLayout linearlayout;
        if (station == null)
        {
            start = (StationData)intent.getSerializableExtra(getString(0x7f0d023a));
            goal = (StationData)intent.getSerializableExtra(getString(0x7f0d01c1));
            setTitle(getString(0x7f0d033e));
        } else
        {
            setTitle(getString(0x7f0d033c));
        }
        btnScope = (ImageView)findViewById(0x7f0a00bb);
        linearlayout = (LinearLayout)findViewById(0x7f0a0178);
        mapView = new MapView(this, getString(0x7f0d033f));
        mapView.setScalebar(false);
        mapControl = mapView.getMapController();
        _overlay = new TransitLocationOverlay(getApplicationContext(), mapView, this);
        mapView.getOverlays().add(_overlay);
        linearlayout.addView(mapView);
        location = (LocationManager)getSystemService("location");
        if (station != null)
        {
            dispAd(this, "2080283086", "pv");
            int i = TransitUtil.getLatLngInt(station.getLat());
            int j = TransitUtil.getLatLngInt(station.getLon());
            mapControl.setCenter(new GeoPoint(i, j));
            return;
        } else
        {
            dispAd(this, "2080283087", "pv");
            setRoute();
            return;
        }
    }

    protected void onDestroy()
    {
        super.onDestroy();
        mapView.onDestroy();
    }

    public void onLocationStart()
    {
        if (!location.isProviderEnabled("network") && !location.isProviderEnabled("gps"))
        {
            showSimpleErrorMessageDialog(getString(0x7f0d054a));
            return;
        } else
        {
            btnScope.setSelected(true);
            _overlay.enableMyLocation();
            mapView.invalidate();
            return;
        }
    }

    public void onLocationStop()
    {
        btnScope.setSelected(false);
        _overlay.disableMyLocation();
        mapView.invalidate();
    }

    public void onMapAppStart(View view)
    {
        if (TransitUtil.isAppInstalled(this, getString(0x7f0d0059)))
        {
            String s;
            Intent intent;
            if (station != null)
            {
                s = getResources().getString(0x7f0d0567) + "lat=" + station.getLat() + "&lon=" + station.getLon();
            } else
            {
                s = getResources().getString(0x7f0d0567) + "lat0=" + start.getLat() + "&lon0=" + start.getLon() + "&lat1=" + goal.getLat() + "&lon1=" + goal.getLon() + (new StringBuilder()).append("&start=").append(start.getName()).toString() + (new StringBuilder()).append("&goal=").append(goal.getName()).toString();
            }
            intent = new Intent();
            intent.setPackage(getString(0x7f0d0059));
            intent.setAction("android.intent.action.VIEW");
            intent.setData(Uri.parse(s));
            try
            {
                view.getContext().startActivity(intent);
                return;
            }
            catch (Exception exception)
            {
                return;
            }
        } else
        {
            (new TransitDialogBuilder(this)).setNegativeButton(getString(0x7f0d033b), new android.content.DialogInterface.OnClickListener() {

                final MapDisplay this$0;

                public void onClick(DialogInterface dialoginterface, int i)
                {
                    touchTapRD((new StringBuilder()).append(getString(0x7f0d03f6)).append("/").append(getString(0x7f0d03cd)).toString());
                    dialoginterface.cancel();
                }

            
            {
                this$0 = MapDisplay.this;
                super();
            }
            }).setView(((LayoutInflater)getSystemService("layout_inflater")).inflate(0x7f030086, null)).create().show();
            return;
        }
    }

    public void onMapClose(View view)
    {
        setResult(-1);
        finish();
    }

    protected void onPause()
    {
        super.onPause();
        mapView.onPause();
    }

    protected void onResume()
    {
        super.onResume();
        mapView.onResume();
    }

    protected void setRoute()
    {
        RouteOverlay routeoverlay = new RouteOverlay(this, getString(0x7f0d003b));
        routeoverlay.setStartTitle(start.getName());
        routeoverlay.setGoalTitle(goal.getName());
        routeoverlay.setRoutePos(new GeoPoint(TransitUtil.getLatLngInt(start.getLat()), TransitUtil.getLatLngInt(start.getLon())), new GeoPoint(TransitUtil.getLatLngInt(goal.getLat()), TransitUtil.getLatLngInt(goal.getLon())), 2);
        routeoverlay.setRoutePinVisible(false);
        routeoverlay.setRouteOverlayListener(this);
        routeoverlay.search();
        mapView.getOverlays().add(routeoverlay);
    }

    public void toggleScope(View view)
    {
        if (btnScope.isSelected())
        {
            onLocationStop();
            return;
        } else
        {
            onLocationStart();
            return;
        }
    }
}

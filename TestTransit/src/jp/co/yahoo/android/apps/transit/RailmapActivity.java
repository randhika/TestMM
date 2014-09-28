// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import jp.co.yahoo.android.apps.transit.api.ApiBase;
import jp.co.yahoo.android.apps.transit.api.LocationSearch;
import jp.co.yahoo.android.apps.transit.api.NaviRenderingSearch;
import jp.co.yahoo.android.apps.transit.api.NaviSearch;
import jp.co.yahoo.android.apps.transit.api.RevGeocoder;
import jp.co.yahoo.android.apps.transit.api.StationSearch;
import jp.co.yahoo.android.apps.transit.api.data.APIError;
import jp.co.yahoo.android.apps.transit.api.data.ConditionData;
import jp.co.yahoo.android.apps.transit.api.data.StationData;
import jp.co.yahoo.android.apps.transit.common.SaveCondition;
import jp.co.yahoo.android.apps.transit.common.util.TransitUtil;
import jp.co.yahoo.android.apps.transit.viewparts.TransitDialogBuilder;
import jp.co.yahoo.android.maps.CustomPopupOverlay;
import jp.co.yahoo.android.maps.GeoPoint;
import jp.co.yahoo.android.maps.ItemizedOverlay;
import jp.co.yahoo.android.maps.MapController;
import jp.co.yahoo.android.maps.MapView;
import jp.co.yahoo.android.maps.MyLocationOverlay;
import jp.co.yahoo.android.maps.OverlayItem;
import jp.co.yahoo.android.maps.PinOverlay;
import jp.co.yahoo.android.maps.PolylineOverlay;
import jp.co.yahoo.android.maps.weather.WeatherOverlay;

// Referenced classes of package jp.co.yahoo.android.apps.transit:
//            TransitBaseActivity, InputSearch, SearchResultListActivity

public class RailmapActivity extends TransitBaseActivity
    implements jp.co.yahoo.android.apps.transit.api.LocationSearch.LocationSearchlListener
{
    public class SubMyLocationOverlay extends MyLocationOverlay
    {

        Activity _activity;
        MapView _mapView;
        final RailmapActivity this$0;

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

        public SubMyLocationOverlay(Context context, MapView mapview, Activity activity)
        {
            this$0 = RailmapActivity.this;
            super(context, mapview);
            _mapView = null;
            _activity = null;
            _mapView = mapview;
            _activity = activity;
        }
    }


    private static final String MAP_INFO_GOAL = "goal";
    private static final String MAP_INFO_START = "start";
    private static final int MAP_POPUPBTN_DELETE = 3;
    private static final int MAP_POPUPBTN_GOAL = 2;
    private static final int MAP_POPUPBTN_START = 1;
    private static final int MAP_SELECTED_GOAL = 1;
    private static final int MAP_SELECTED_START = 0;
    private static final int MAP_SELECT_MODE_BUSSTOP = 1;
    private static final int MAP_SELECT_MODE_NOT = 3;
    private static final int MAP_SELECT_MODE_PIN = 2;
    private static final int MAP_SELECT_MODE_STATION;
    protected jp.co.yahoo.android.apps.transit.api.ApiBase.ApiListener apiListener;
    private boolean bFirstGps;
    private boolean bFirstSearch;
    private boolean bOpenPopup;
    private boolean bRouteFinish;
    private Drawable btnDelete_off;
    private Drawable btnDelete_on;
    private Drawable btnGoal_off;
    private Drawable btnGoal_on;
    private ImageButton btnResearch;
    private Button btnRoute;
    private Button btnSearch;
    private Drawable btnStart_off;
    private Drawable btnStart_on;
    private ImageButton btnWeather;
    private CheckBox chkBusstop;
    private CheckBox chkStation;
    private ConditionData condition;
    StationData goal;
    private SubMyLocationOverlay gpsOverlay;
    private boolean isWeatherDisp;
    private boolean isWeatherUpdate;
    private RelativeLayout layoutDesc;
    private LocationSearch location;
    private RelativeLayout lyRouteSearch;
    private LinearLayout lyWeather;
    private MapController mapControl;
    private MapView mapView;
    private int nGoalTime;
    private int nSearchMode;
    private int nStartTime;
    private StationSearch objBsSearch;
    private RevGeocoder objGeoSearch;
    StationData objPopup;
    private StationSearch objStSearch;
    private Drawable pinBusstop;
    private PinOverlay pinBusstopOverlay;
    private PinOverlay pinGoalOverlay;
    private GeoPoint pinPoint;
    private Drawable pinSpot;
    private PinOverlay pinSpotOverlay;
    private PinOverlay pinStartOverlay;
    private Drawable pinStation;
    private PinOverlay pinStationOverlay;
    private PolylineOverlay polylineOverlay;
    private CustomPopupOverlay popupOverlay;
    private Resources res;
    private SeekBar seekWeather;
    StationData start;
    private TextView txtSelectedGoal;
    private TextView txtSelectedStart;
    private WeatherOverlay weatherOverlay;

    public RailmapActivity()
    {
        nSearchMode = 0;
        gpsOverlay = null;
        bOpenPopup = false;
        isWeatherUpdate = true;
        isWeatherDisp = false;
        nStartTime = -1;
        nGoalTime = -1;
        bRouteFinish = false;
        bFirstGps = true;
        bFirstSearch = false;
        apiListener = new jp.co.yahoo.android.apps.transit.api.ApiBase.ApiListener() {

            final RailmapActivity this$0;

            public boolean onCanceled()
            {
                checkBoxEnable(true);
                return false;
            }

            public boolean onError(APIError apierror)
            {
                checkBoxEnable(true);
                showErrorMessageDialog(getString(0x7f0d010c), getString(0x7f0d014f));
                return false;
            }

            public boolean onSuccess(ApiBase apibase, Object obj)
            {
                checkBoxEnable(true);
                StationSearch stationsearch = (StationSearch)apibase;
                Bundle bundle = stationsearch.getResults();
                if (bundle == null)
                {
                    return true;
                }
                closePopup(stationsearch.getMode());
                if (stationsearch.getMode() == 2)
                {
                    int j;
                    if (pinBusstopOverlay == null)
                    {
                        pinBusstopOverlay = new PinOverlay(pinBusstop);
                        pinBusstopOverlay.setOnFocusChangeListener(popupOverlay);
                        mapView.getOverlays().add(pinBusstopOverlay);
                    } else
                    {
                        pinBusstopOverlay.clearPoint();
                    }
                    for (j = 0; j < bundle.size(); j++)
                    {
                        StationData stationdata1 = (StationData)bundle.getSerializable(String.valueOf(j));
                        pinBusstopOverlay.addPoint(stationdata1.getGeopoint(), stationdata1.getName(), null, null, 0, stationdata1);
                    }

                } else
                {
                    int i;
                    if (pinStationOverlay == null)
                    {
                        pinStationOverlay = new PinOverlay(pinStation);
                        pinStationOverlay.setOnFocusChangeListener(popupOverlay);
                        mapView.getOverlays().add(pinStationOverlay);
                    } else
                    {
                        pinStationOverlay.clearPoint();
                    }
                    for (i = 0; i < bundle.size(); i++)
                    {
                        StationData stationdata = (StationData)bundle.getSerializable(String.valueOf(i));
                        pinStationOverlay.addPoint(stationdata.getGeopoint(), stationdata.getName(), null, null, 0, stationdata);
                    }

                }
                mapView.getOverlays().add(popupOverlay);
                return false;
            }

            
            {
                this$0 = RailmapActivity.this;
                super();
            }
        };
    }

    private void addStartGoalPin(String s, StationData stationdata)
    {
        if (stationdata == null)
        {
            return;
        }
        if (s.equals("start"))
        {
            pinStartOverlay.clearPoint();
            pinStartOverlay.addPoint(stationdata.getGeopoint(), stationdata.getName(), s, null, 0, stationdata);
            mapView.getOverlays().add(pinStartOverlay);
            return;
        } else
        {
            pinGoalOverlay.clearPoint();
            pinGoalOverlay.addPoint(stationdata.getGeopoint(), stationdata.getName(), s, null, 0, stationdata);
            mapView.getOverlays().add(pinGoalOverlay);
            return;
        }
    }

    private void changeBtnSelect()
    {
        if (nSearchMode == 1)
        {
            chkStation.setSelected(false);
            chkBusstop.setSelected(true);
            return;
        }
        if (nSearchMode == 0)
        {
            chkStation.setSelected(true);
            chkBusstop.setSelected(false);
            return;
        }
        if (nSearchMode == 2)
        {
            chkStation.setSelected(false);
            chkBusstop.setSelected(false);
            return;
        } else
        {
            chkStation.setSelected(false);
            chkBusstop.setSelected(false);
            return;
        }
    }

    private void changeInfoSelect(int i, boolean flag, String s)
    {
        if (i == 0)
        {
            txtSelectedStart.setText(s);
        } else
        if (i == 1)
        {
            txtSelectedGoal.setText(s);
            return;
        }
    }

    private void searchAddress(String s, String s1)
    {
        objGeoSearch = new RevGeocoder(this, new jp.co.yahoo.android.apps.transit.api.ApiBase.ApiListener() {

            final RailmapActivity this$0;

            public boolean onCanceled()
            {
                return false;
            }

            public boolean onError(APIError apierror)
            {
                showErrorMessageDialog(getString(0x7f0d0117), getString(0x7f0d014f));
                return false;
            }

            public boolean onSuccess(ApiBase apibase, Object obj)
            {
                Bundle bundle = objGeoSearch.getResults();
                if (bundle == null)
                {
                    showErrorMessageDialog(getString(0x7f0d0117), getString(0x7f0d014f));
                } else
                if (pinPoint != null)
                {
                    String as[] = bundle.getStringArray(getString(0x7f0d01a2));
                    if (as.length > 0 && !TransitUtil.isEmpty(as[0]))
                    {
                        String s2 = as[0];
                        StationData stationdata = new StationData();
                        stationdata.setName(s2);
                        stationdata.setAddress(s2);
                        stationdata.setLat(TransitUtil.getLatLngString(pinPoint.getLatitudeE6()));
                        stationdata.setLon(TransitUtil.getLatLngString(pinPoint.getLongitudeE6()));
                        stationdata.setType(3);
                        stationdata.setnNaviType(128);
                        addAddressPin(stationdata);
                        return false;
                    }
                }
                return false;
            }

            
            {
                this$0 = RailmapActivity.this;
                super();
            }
        });
        objGeoSearch.setLat(s);
        objGeoSearch.setLon(s1);
        objGeoSearch.setResultCount(1);
        objGeoSearch.setDialogDisplay(false);
        objGeoSearch.request();
    }

    private void setCondition(StationData stationdata, int i, boolean flag)
    {
        if (i == 1)
        {
            start = stationdata;
            changeInfoSelect(0, true, stationdata.getName());
            addStartGoalPin("start", stationdata);
            txtSelectedStart.setEnabled(true);
        } else
        if (i == 2)
        {
            goal = stationdata;
            changeInfoSelect(1, true, stationdata.getName());
            addStartGoalPin("goal", stationdata);
            txtSelectedGoal.setEnabled(true);
        } else
        if (stationdata.equals(start))
        {
            changeInfoSelect(0, false, getString(0x7f0d02bb));
            pinStartOverlay.clearPoint();
            start = null;
            txtSelectedStart.setEnabled(false);
        } else
        if (stationdata.equals(goal))
        {
            changeInfoSelect(1, false, getString(0x7f0d02bb));
            pinGoalOverlay.clearPoint();
            goal = null;
            txtSelectedGoal.setEnabled(false);
        }
        if (start == null && goal == null)
        {
            lyRouteSearch.setVisibility(8);
            btnRoute.setVisibility(8);
            btnSearch.setVisibility(8);
        } else
        if (start != null && goal != null)
        {
            if (flag)
            {
                lyRouteSearch.setVisibility(8);
                btnRoute.setVisibility(8);
                btnSearch.setVisibility(0);
            } else
            {
                lyRouteSearch.setVisibility(0);
                btnRoute.setVisibility(0);
                btnSearch.setVisibility(8);
                bRouteFinish = false;
            }
        } else
        {
            lyRouteSearch.setVisibility(0);
            btnRoute.setVisibility(8);
            btnSearch.setVisibility(8);
        }
        if (polylineOverlay != null && !flag)
        {
            mapView.getOverlays().remove(polylineOverlay);
        }
        mapView.getOverlays().add(popupOverlay);
    }

    private void setViewEvent()
    {
        btnStart_on = res.getDrawable(0x7f020268);
        btnStart_off = res.getDrawable(0x7f020267);
        btnGoal_on = res.getDrawable(0x7f02022e);
        btnGoal_off = res.getDrawable(0x7f02022d);
        btnDelete_on = res.getDrawable(0x7f020210);
        btnDelete_off = res.getDrawable(0x7f02020f);
        pinBusstop = res.getDrawable(0x7f020243);
        pinStation = res.getDrawable(0x7f02024b);
        pinSpot = res.getDrawable(0x7f020249);
        mapView.setMapTouchListener(new jp.co.yahoo.android.maps.MapView.MapTouchListener() {

            private float old_x;
            private float old_y;
            final RailmapActivity this$0;

            public boolean onLongPress(MapView mapview, Object obj, PinOverlay pinoverlay, GeoPoint geopoint)
            {
                mapView.getOverlays().remove(pinoverlay);
                pinPoint = geopoint;
                searchAddress(TransitUtil.getLatLngString(pinPoint.getLatitudeE6()), TransitUtil.getLatLngString(pinPoint.getLongitudeE6()));
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

            public boolean onTouch(MapView mapview, MotionEvent motionevent)
            {
                if (motionevent.getAction() == 1)
                {
                    if (!bOpenPopup && (nSearchMode == 0 || nSearchMode == 1))
                    {
                        double d = Math.sqrt(Math.pow(motionevent.getX() - old_x, 2D) + Math.pow(motionevent.getY() - old_y, 2D));
                        if ((double)(mapView.getWidth() / 2) < d && (chkBusstop.isChecked() || chkStation.isChecked()))
                        {
                            togglePopup(true);
                            return false;
                        }
                    }
                } else
                if (motionevent.getAction() == 0)
                {
                    old_x = motionevent.getX();
                    old_y = motionevent.getY();
                    return false;
                }
                return false;
            }

            
            {
                this$0 = RailmapActivity.this;
                super();
                old_x = 0.0F;
                old_y = 0.0F;
            }
        });
        ((LinearLayout)findViewById(0x7f0a0150)).setOnClickListener(new android.view.View.OnClickListener() {

            final RailmapActivity this$0;

            public void onClick(View view)
            {
                showAreaSelect();
            }

            
            {
                this$0 = RailmapActivity.this;
                super();
            }
        });
        btnRoute = (Button)findViewById(0x7f0a014b);
        btnRoute.setOnClickListener(new android.view.View.OnClickListener() {

            final RailmapActivity this$0;

            public void onClick(View view)
            {
                if (!bRouteFinish)
                {
                    searchRoute();
                }
            }

            
            {
                this$0 = RailmapActivity.this;
                super();
            }
        });
        btnSearch = (Button)findViewById(0x7f0a014c);
        btnSearch.setOnClickListener(new android.view.View.OnClickListener() {

            final RailmapActivity this$0;

            public void onClick(View view)
            {
                if (bRouteFinish)
                {
                    launchSearchResult();
                }
            }

            
            {
                this$0 = RailmapActivity.this;
                super();
            }
        });
        chkStation = (CheckBox)findViewById(0x7f0a014e);
        chkStation.setOnCheckedChangeListener(new android.widget.CompoundButton.OnCheckedChangeListener() {

            final RailmapActivity this$0;

            public void onCheckedChanged(CompoundButton compoundbutton, boolean flag)
            {
                if (flag)
                {
                    search(1);
                    return;
                }
                if (pinStationOverlay != null)
                {
                    pinStationOverlay.clearPoint();
                }
                closePopup(1);
                togglePopup(false);
            }

            
            {
                this$0 = RailmapActivity.this;
                super();
            }
        });
        chkBusstop = (CheckBox)findViewById(0x7f0a014f);
        chkBusstop.setOnCheckedChangeListener(new android.widget.CompoundButton.OnCheckedChangeListener() {

            final RailmapActivity this$0;

            public void onCheckedChanged(CompoundButton compoundbutton, boolean flag)
            {
                if (flag)
                {
                    search(2);
                    return;
                }
                if (pinBusstopOverlay != null)
                {
                    pinBusstopOverlay.clearPoint();
                }
                closePopup(2);
                togglePopup(false);
            }

            
            {
                this$0 = RailmapActivity.this;
                super();
            }
        });
        ((ImageButton)findViewById(0x7f0a0144)).setOnClickListener(new android.view.View.OnClickListener() {

            final RailmapActivity this$0;

            public void onClick(View view)
            {
                if (view.isSelected())
                {
                    view.setSelected(false);
                    gpsOverlay.disableMyLocation();
                    mapView.invalidate();
                    return;
                }
                if (!location.isLocationEnabled())
                {
                    showSimpleErrorMessageDialog(getString(0x7f0d054a));
                    return;
                } else
                {
                    view.setSelected(true);
                    gpsOverlay.enableMyLocation();
                    mapView.invalidate();
                    return;
                }
            }

            
            {
                this$0 = RailmapActivity.this;
                super();
            }
        });
        btnResearch.setOnClickListener(new android.view.View.OnClickListener() {

            final RailmapActivity this$0;

            public void onClick(View view)
            {
                search();
            }

            
            {
                this$0 = RailmapActivity.this;
                super();
            }
        });
        popupOverlay = new CustomPopupOverlay() {

            final RailmapActivity this$0;

            public void closePopup()
            {
                super.closePopup();
                bOpenPopup = false;
                objPopup = null;
            }

            public void onTap(OverlayItem overlayitem, int i)
            {
                if (overlayitem == null)
                {
                    return;
                } else
                {
                    StationData stationdata = (StationData)overlayitem.getData();
                    popupOverlay.closePopup();
                    setCondition(stationdata, i, false);
                    return;
                }
            }

            public void openPopup(ItemizedOverlay itemizedoverlay, OverlayItem overlayitem)
            {
                super.openPopup(itemizedoverlay, overlayitem);
                mapControl.animateTo(overlayitem.getPoint());
                bOpenPopup = true;
                StationData stationdata = (StationData)overlayitem.getData();
                clear();
                objPopup = stationdata;
                Paint paint = new Paint(1);
                paint.setStyle(android.graphics.Paint.Style.FILL);
                paint.setTypeface(Typeface.create(Typeface.DEFAULT, 0));
                paint.setTextSize(getResources().getDimension(0x7f0b0048));
                paint.setShadowLayer(0.0F, 0.0F, 0.0F, 0);
                paint.setTextAlign(android.graphics.Paint.Align.CENTER);
                String s = overlayitem.getSnippet();
                int i = (int)getResources().getDimension(0x7f0b0033);
                int j = (int)getResources().getDimension(0x7f0b002e);
                if (stationdata == null)
                {
                    Drawable drawable2 = res.getDrawable(0x7f020180);
                    int l1 = drawable2.getIntrinsicWidth();
                    setBackgroundImage(drawable2);
                    addText(getString(0x7f0d0117), l1 / 2, i, paint, 0);
                    return;
                }
                if (TransitUtil.isEmpty(s))
                {
                    Drawable drawable1 = res.getDrawable(0x7f020180);
                    int i1 = drawable1.getIntrinsicWidth();
                    setBackgroundImage(drawable1);
                    Rect rect1 = addText(TransitUtil.getEllipsisString(stationdata.getName(), i1 - i * 2, paint.getTextSize()), i1 / 2, i, paint, 0);
                    int j1 = i1 / 2 - btnStart_on.getIntrinsicWidth() - j / 2;
                    int k1 = i1 / 2 + j / 2;
                    Rect rect2 = addImage(btnStart_off, btnStart_on, j1, i + rect1.bottom, 1);
                    addImage(btnGoal_off, btnGoal_on, k1, rect2.top, 2);
                    return;
                } else
                {
                    Drawable drawable = res.getDrawable(0x7f020181);
                    int k = drawable.getIntrinsicWidth();
                    String s1 = TransitUtil.getEllipsisString(stationdata.getName(), k - i * 2, paint.getTextSize());
                    setBackgroundImage(drawable);
                    Rect rect = addText(s1, k / 2, i, paint, 0);
                    int l = (k - btnDelete_on.getIntrinsicWidth()) / 2;
                    addImage(btnDelete_off, btnDelete_on, l, i + rect.bottom, 3);
                    return;
                }
            }

            
            {
                this$0 = RailmapActivity.this;
                super();
            }
        };
        mapView.getOverlays().add(popupOverlay);
        pinStartOverlay = new PinOverlay(res.getDrawable(0x7f02024a));
        pinStartOverlay.setOnFocusChangeListener(popupOverlay);
        pinGoalOverlay = new PinOverlay(res.getDrawable(0x7f020244));
        pinGoalOverlay.setOnFocusChangeListener(popupOverlay);
    }

    private void togglePopup(boolean flag)
    {
        if (flag && layoutDesc.getVisibility() == 8)
        {
            AlphaAnimation alphaanimation1 = new AlphaAnimation(0.0F, 1.0F);
            alphaanimation1.setDuration(3000L);
            layoutDesc.setAnimation(alphaanimation1);
            layoutDesc.setVisibility(0);
        } else
        if (!flag && layoutDesc.getVisibility() == 0)
        {
            AlphaAnimation alphaanimation = new AlphaAnimation(1.0F, 0.0F);
            alphaanimation.setDuration(3000L);
            layoutDesc.setAnimation(alphaanimation);
            layoutDesc.setVisibility(8);
            return;
        }
    }

    protected void addAddressPin(StationData stationdata)
    {
        mapView.getOverlays().remove(pinSpotOverlay);
        mapView.getOverlays().remove(popupOverlay);
        popupOverlay.closePopup();
        pinSpotOverlay = new PinOverlay(pinSpot);
        pinSpotOverlay.setOnFocusChangeListener(popupOverlay);
        mapView.getOverlays().add(pinSpotOverlay);
        mapView.getOverlays().add(popupOverlay);
        pinSpotOverlay.addPoint(pinPoint, stationdata.getName(), null, null, 0, stationdata);
    }

    protected void checkBoxEnable(boolean flag)
    {
        chkStation.setEnabled(flag);
        chkBusstop.setEnabled(flag);
    }

    protected void closePopup(int i)
    {
        while (objPopup == null || popupOverlay == null || objPopup.getType() != i) 
        {
            return;
        }
        popupOverlay.closePopup();
    }

    protected void initWeather()
    {
        WeatherOverlay weatheroverlay = new WeatherOverlay(this);
        weatherOverlay = weatheroverlay;
        WeatherOverlay weatheroverlay1 = weatherOverlay;
        jp.co.yahoo.android.maps.weather.WeatherOverlay.WeatherOverlayListener weatheroverlaylistener = new jp.co.yahoo.android.maps.weather.WeatherOverlay.WeatherOverlayListener() {

            final RailmapActivity this$0;

            public void errorUpdateWeather(WeatherOverlay weatheroverlay2, int i2)
            {
                isWeatherUpdate = true;
            }

            public void finishUpdateWeather(WeatherOverlay weatheroverlay2)
            {
                isWeatherUpdate = true;
            }

            
            {
                this$0 = RailmapActivity.this;
                super();
            }
        };
        weatheroverlay1.setWeatherOverlayListener(weatheroverlaylistener);
        seekWeather.setMax(10);
        seekWeather.setProgress(0);
        SeekBar seekbar = seekWeather;
        android.widget.SeekBar.OnSeekBarChangeListener onseekbarchangelistener = new android.widget.SeekBar.OnSeekBarChangeListener() {

            final RailmapActivity this$0;

            public void onProgressChanged(SeekBar seekbar1, int i2, boolean flag)
            {
                if (isWeatherUpdate)
                {
                    isWeatherUpdate = false;
                    weatherOverlay.updateWeather(i2 * 5);
                }
            }

            public void onStartTrackingTouch(SeekBar seekbar1)
            {
            }

            public void onStopTrackingTouch(SeekBar seekbar1)
            {
                weatherOverlay.updateWeather(5 * seekbar1.getProgress());
            }

            
            {
                this$0 = RailmapActivity.this;
                super();
            }
        };
        seekbar.setOnSeekBarChangeListener(onseekbarchangelistener);
        int i = ((RelativeLayout)findViewById(0x7f0a0154)).getWidth();
        int j;
        byte byte0;
        SimpleDateFormat simpledateformat;
        Calendar calendar;
        java.util.Date date;
        int k;
        java.util.Date date1;
        int l;
        ImageView imageview;
        ImageView imageview1;
        if (android.os.Build.VERSION.SDK_INT >= 11)
        {
            j = i - 80;
            byte0 = 10;
        } else
        {
            j = i - 20;
            byte0 = 0;
        }
        simpledateformat = new SimpleDateFormat("HH':'mm");
        calendar = Calendar.getInstance();
        calendar.add(12, 0 - calendar.get(12) % 5);
        date = calendar.getTime();
        k = (int)(calendar.getTimeInMillis() / 1000L / 60L);
        ((TextView)findViewById(0x7f0a0153)).setText(simpledateformat.format(date));
        calendar.add(12, 50);
        date1 = calendar.getTime();
        l = (int)(calendar.getTimeInMillis() / 1000L / 60L);
        ((TextView)findViewById(0x7f0a0158)).setText(simpledateformat.format(date1));
        imageview = (ImageView)findViewById(0x7f0a0156);
        if (k <= nStartTime && l >= nStartTime)
        {
            int l1 = byte0 + ((nStartTime - k) / 5) * (j / 10);
            android.view.ViewGroup.MarginLayoutParams marginlayoutparams1 = (android.view.ViewGroup.MarginLayoutParams)imageview.getLayoutParams();
            marginlayoutparams1.setMargins(l1, 0, 0, 0);
            imageview.setLayoutParams(marginlayoutparams1);
        } else
        {
            imageview.setVisibility(8);
        }
        imageview1 = (ImageView)findViewById(0x7f0a0157);
        if (k <= nGoalTime && l >= nGoalTime)
        {
            int i1 = nGoalTime - k;
            int j1;
            int k1;
            android.view.ViewGroup.MarginLayoutParams marginlayoutparams;
            if (i1 % 5 > 0)
            {
                j1 = 1 + i1 / 5;
            } else
            {
                j1 = i1 / 5;
            }
            k1 = byte0 + j1 * (j / 10);
            marginlayoutparams = (android.view.ViewGroup.MarginLayoutParams)imageview1.getLayoutParams();
            marginlayoutparams.setMargins(k1, 0, 0, 0);
            imageview1.setLayoutParams(marginlayoutparams);
            return;
        } else
        {
            imageview1.setVisibility(8);
            return;
        }
    }

    protected void launchSearchResult()
    {
        NaviSearch navisearch = new NaviSearch(this, new jp.co.yahoo.android.apps.transit.api.ApiBase.ApiListener() {

            final RailmapActivity this$0;

            public boolean onCanceled()
            {
                return false;
            }

            public boolean onError(APIError apierror)
            {
                return false;
            }

            public boolean onSuccess(ApiBase apibase, Object obj)
            {
                Intent intent = new Intent(RailmapActivity.this, jp/co/yahoo/android/apps/transit/SearchResultListActivity);
                String s = getString(0x7f0d0232);
                NaviSearch _tmp = (NaviSearch)apibase;
                intent.putExtra(s, NaviSearch.m_routeList);
                intent.putExtra(getString(0x7f0d022c), condition);
                startActivityForResult(intent, getResources().getInteger(0x7f0c0059));
                return false;
            }

            
            {
                this$0 = RailmapActivity.this;
                super();
            }
        });
        navisearch.setCondition(condition);
        navisearch.request();
    }

    protected void onActivityResult(int i, int j, Intent intent)
    {
        super.onActivityResult(i, j, intent);
        StationData stationdata = (StationData)intent.getSerializableExtra(getString(0x7f0d023e));
        if (stationdata == null)
        {
            return;
        } else
        {
            pinPoint = new GeoPoint(TransitUtil.getLatLngInt(stationdata.getLat()), TransitUtil.getLatLngInt(stationdata.getLon()));
            mapControl.setCenter(pinPoint);
            location.stopRequest();
            addAddressPin(stationdata);
            return;
        }
    }

    public void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setContentView(0x7f030030);
        res = getResources();
        Intent intent = getIntent();
        int i = TransitUtil.getLatLngInt(intent.getStringExtra(getString(0x7f0d01a3)));
        int j = TransitUtil.getLatLngInt(intent.getStringExtra(getString(0x7f0d01a4)));
        Calendar calendar = TransitUtil.timeStringToCalendar(intent.getStringExtra(getString(0x7f0d023c)));
        if (calendar != null)
        {
            nStartTime = (int)(calendar.getTimeInMillis() / 1000L / 60L);
        }
        Calendar calendar1 = TransitUtil.timeStringToCalendar(intent.getStringExtra(getString(0x7f0d01c2)));
        if (calendar1 != null)
        {
            nGoalTime = (int)(calendar1.getTimeInMillis() / 1000L / 60L);
        }
        dispAd(this, "2080124751", "pv");
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayOptions(0x10 | actionbar.getDisplayOptions());
        actionbar.setCustomView(0x7f030088);
        EditText edittext = (EditText)((LinearLayout)actionbar.getCustomView()).findViewById(0x7f0a0298);
        edittext.setFocusable(false);
        android.view.View.OnClickListener onclicklistener = new android.view.View.OnClickListener() {

            final RailmapActivity this$0;

            public void onClick(View view)
            {
                Intent intent1 = new Intent(RailmapActivity.this, jp/co/yahoo/android/apps/transit/InputSearch);
                intent1.putExtra(getString(0x7f0d01c3), true);
                intent1.putExtra(getString(0x7f0d01bc), true);
                intent1.putExtra(getString(0x7f0d022d), getString(0x7f0d0481));
                startActivityForResult(intent1, getResources().getInteger(0x7f0c0049));
            }

            
            {
                this$0 = RailmapActivity.this;
                super();
            }
        };
        edittext.setOnClickListener(onclicklistener);
        LinearLayout linearlayout = (LinearLayout)findViewById(0x7f0a013e);
        MapView mapview = new MapView(this, getString(0x7f0d033f));
        mapView = mapview;
        mapControl = mapView.getMapController();
        mapControl.setCenter(new GeoPoint(0x2203739, 0x854203e));
        mapControl.setZoom(5);
        mapView.setMapType(MapView.MapTypeStyle, "base:railway");
        linearlayout.addView(mapView);
        mapView.setLongPress(true);
        SubMyLocationOverlay submylocationoverlay = new SubMyLocationOverlay(getApplicationContext(), mapView, this);
        gpsOverlay = submylocationoverlay;
        mapView.getOverlays().add(gpsOverlay);
        layoutDesc = (RelativeLayout)findViewById(0x7f0a0142);
        btnResearch = (ImageButton)findViewById(0x7f0a0145);
        lyRouteSearch = (RelativeLayout)findViewById(0x7f0a0146);
        txtSelectedStart = (TextView)findViewById(0x7f0a0148);
        txtSelectedGoal = (TextView)findViewById(0x7f0a014a);
        LocationSearch locationsearch = new LocationSearch(this, this);
        location = locationsearch;
        seekWeather = (SeekBar)findViewById(0x7f0a0155);
        btnWeather = (ImageButton)findViewById(0x7f0a0159);
        lyWeather = (LinearLayout)findViewById(0x7f0a0143);
        ViewTreeObserver viewtreeobserver = ((RelativeLayout)findViewById(0x7f0a0154)).getViewTreeObserver();
        android.view.ViewTreeObserver.OnGlobalLayoutListener ongloballayoutlistener = new android.view.ViewTreeObserver.OnGlobalLayoutListener() {

            final RailmapActivity this$0;

            public void onGlobalLayout()
            {
                if (weatherOverlay == null)
                {
                    initWeather();
                    lyWeather.setVisibility(8);
                }
            }

            
            {
                this$0 = RailmapActivity.this;
                super();
            }
        };
        viewtreeobserver.addOnGlobalLayoutListener(ongloballayoutlistener);
        condition = (ConditionData)intent.getSerializableExtra(getString(0x7f0d022c));
        if (condition != null && condition.ticket == null)
        {
            ConditionData conditiondata = (new SaveCondition(this)).getCond();
            if (conditiondata != null)
            {
                condition.ticket = conditiondata.ticket;
            }
        }
        if (condition != null)
        {
            bFirstSearch = true;
            searchRoute();
        } else
        {
            condition = (new SaveCondition(this)).getCond();
            if (condition == null)
            {
                condition = new ConditionData();
            }
            if (i != 0 && j != 0)
            {
                MapController mapcontroller = mapControl;
                GeoPoint geopoint = new GeoPoint(i, j);
                mapcontroller.setCenter(geopoint);
            } else
            {
                location.getCurrentPlace();
            }
        }
        setViewEvent();
    }

    protected void onDestroy()
    {
        super.onDestroy();
        location.setLocationSearchlListener(null);
        mapView.onDestroy();
        chkStation = null;
        chkBusstop = null;
        btnStart_on = null;
        btnStart_off = null;
        btnGoal_on = null;
        btnGoal_off = null;
        btnDelete_on = null;
        btnDelete_off = null;
        txtSelectedStart = null;
        txtSelectedGoal = null;
        pinStation = null;
        pinBusstop = null;
        pinSpot = null;
        pinStationOverlay = null;
        pinSpotOverlay = null;
        pinStartOverlay = null;
        pinGoalOverlay = null;
        popupOverlay = null;
        mapView = null;
        pinPoint = null;
        condition = null;
        mapControl = null;
        res = null;
        layoutDesc = null;
        weatherOverlay = null;
        seekWeather = null;
        lyWeather = null;
        btnWeather = null;
    }

    public void onError(String s, String s1)
    {
        showErrorMessageDialog(s1, getString(0x7f0d014f));
    }

    protected void onPause()
    {
        super.onPause();
        mapView.onPause();
    }

    protected void onRestoreInstanceState(Bundle bundle)
    {
        super.onRestoreInstanceState(bundle);
        start = (StationData)bundle.getSerializable(getString(0x7f0d023a));
        goal = (StationData)bundle.getSerializable(getString(0x7f0d01c1));
        if (start != null)
        {
            setCondition(start, 1, false);
        }
        if (goal != null)
        {
            setCondition(goal, 2, false);
        }
    }

    protected void onResume()
    {
        super.onResume();
        mapView.onResume();
    }

    protected void onSaveInstanceState(Bundle bundle)
    {
        super.onSaveInstanceState(bundle);
        bundle.putSerializable(getString(0x7f0d023a), start);
        bundle.putSerializable(getString(0x7f0d01c1), goal);
    }

    public void onStart()
    {
        super.onStart();
        RelativeLayout relativelayout = (RelativeLayout)findViewById(0x7f0a0140);
        AlphaAnimation alphaanimation = new AlphaAnimation(1.0F, 0.0F);
        alphaanimation.setDuration(3000L);
        relativelayout.setAnimation(alphaanimation);
        relativelayout.setVisibility(8);
    }

    public void onStop()
    {
        super.onStop();
        if (objGeoSearch != null)
        {
            objGeoSearch.stopRequest();
        }
        if (objStSearch != null)
        {
            objStSearch.stopRequest();
        }
    }

    public void onSuccess(String s, Bundle bundle)
    {
        if (mapControl == null)
        {
            return;
        } else
        {
            String s1 = bundle.getString(getString(0x7f0d01a3));
            String s2 = bundle.getString(getString(0x7f0d01a4));
            int i = TransitUtil.getLatLngInt(s1);
            int j = TransitUtil.getLatLngInt(s2);
            mapControl.setCenter(new GeoPoint(i, j));
            return;
        }
    }

    public void onTimeout(String s, String s1)
    {
        showErrorMessageDialog(s1, getString(0x7f0d014f));
    }

    public void onWeather(View view)
    {
        isWeatherDisp = true;
        if (lyWeather.getVisibility() == 8)
        {
            btnWeather.setSelected(true);
            lyWeather.setVisibility(0);
            mapView.getOverlays().add(weatherOverlay);
            return;
        } else
        {
            btnWeather.setSelected(false);
            lyWeather.setVisibility(8);
            mapView.getOverlays().remove(weatherOverlay);
            return;
        }
    }

    protected void search()
    {
        checkBoxEnable(false);
        if (chkBusstop.isChecked())
        {
            search(2);
        }
        if (chkStation.isChecked())
        {
            search(1);
        }
    }

    protected void search(int i)
    {
        GeoPoint geopoint = mapView.getMapCenter();
        search(String.valueOf((double)geopoint.getLatitudeE6() / 1000000D), String.valueOf((double)geopoint.getLongitudeE6() / 1000000D), i);
    }

    protected void search(String s, String s1, int i)
    {
        if (i == 2)
        {
            objBsSearch = new StationSearch(this, apiListener);
            objBsSearch.setLat(s);
            objBsSearch.setLon(s1);
            objBsSearch.setSort("dist");
            objBsSearch.setResultCount(20);
            objBsSearch.setMode(2);
            objBsSearch.request();
        } else
        {
            objStSearch = new StationSearch(this, apiListener);
            objStSearch.setLat(s);
            objStSearch.setLon(s1);
            objStSearch.setSort("dist");
            objStSearch.setResultCount(20);
            objStSearch.setMode(1);
            objStSearch.request();
        }
        togglePopup(false);
    }

    protected void searchRoute()
    {
        NaviRenderingSearch navirenderingsearch = new NaviRenderingSearch(this, new jp.co.yahoo.android.apps.transit.api.ApiBase.ApiListener() {

            final RailmapActivity this$0;

            public boolean onCanceled()
            {
                return false;
            }

            public boolean onError(APIError apierror)
            {
                return false;
            }

            public boolean onSuccess(ApiBase apibase, Object obj)
            {
                if (mapView == null)
                {
                    return false;
                }
                if (polylineOverlay != null)
                {
                    mapView.getOverlays().remove(polylineOverlay);
                }
                GeoPoint ageopoint[] = ((NaviRenderingSearch)apibase).getRoute();
                polylineOverlay = new PolylineOverlay(ageopoint);
                polylineOverlay.setWidth(10F);
                mapView.getOverlays().add(polylineOverlay);
                mapView.getOverlays().remove(popupOverlay);
                mapView.getOverlays().add(popupOverlay);
                bRouteFinish = true;
                btnRoute.setVisibility(8);
                btnSearch.setVisibility(0);
                GeoPoint geopoint = ageopoint[0];
                GeoPoint geopoint1 = ageopoint[-1 + ageopoint.length];
                if (start == null)
                {
                    start = new StationData();
                    start.setName(condition.startName);
                    start.setLat(TransitUtil.getLatLngString(geopoint.getLatitudeE6()));
                    start.setLon(TransitUtil.getLatLngString(geopoint.getLongitudeE6()));
                    start.setId(condition.startCode);
                    setCondition(start, 1, true);
                }
                if (goal == null)
                {
                    goal = new StationData();
                    goal.setName(condition.goalName);
                    goal.setLat(TransitUtil.getLatLngString(geopoint1.getLatitudeE6()));
                    goal.setLon(TransitUtil.getLatLngString(geopoint1.getLongitudeE6()));
                    goal.setId(condition.goalCode);
                    setCondition(goal, 2, true);
                }
                GeoPoint geopoint2 = new GeoPoint((geopoint.getLatitudeE6() + geopoint1.getLatitudeE6()) / 2, (geopoint.getLongitudeE6() + geopoint1.getLongitudeE6()) / 2);
                int i = Math.abs(geopoint1.getLatitudeE6() - geopoint.getLatitudeE6());
                int j = Math.abs(geopoint1.getLongitudeE6() - geopoint.getLongitudeE6());
                mapControl.zoomToSpan(i, j);
                mapControl.setCenter(geopoint2);
                mapControl.zoomOut();
                return false;
            }

            
            {
                this$0 = RailmapActivity.this;
                super();
            }
        });
        if (start != null)
        {
            if (start.getType() != 3)
            {
                condition.startName = start.getName();
                condition.startCode = start.getId();
            } else
            {
                condition.startCode = null;
                condition.startName = start.getName();
                condition.startLat = start.getLat();
                condition.startLon = start.getLon();
            }
        }
        if (goal != null)
        {
            if (goal.getType() != 3)
            {
                condition.goalName = goal.getName();
                condition.goalCode = goal.getId();
            } else
            {
                condition.goalCode = null;
                condition.goalName = goal.getName();
                condition.goalLat = goal.getLat();
                condition.goalLon = goal.getLon();
            }
        }
        if (!bFirstSearch || condition.year == -1)
        {
            Calendar calendar = Calendar.getInstance();
            condition.year = calendar.get(1);
            condition.month = 1 + calendar.get(2);
            condition.day = calendar.get(5);
            condition.hour = calendar.get(11);
            condition.minite = calendar.get(12);
        }
        navirenderingsearch.setCondition(condition);
        navirenderingsearch.request();
        bFirstSearch = false;
    }

    protected void showAreaSelect()
    {
        String as[] = getResources().getStringArray(0x7f070005);
        (new TransitDialogBuilder(this)).setTitle(getString(0x7f0d047f)).setItems(as, new android.content.DialogInterface.OnClickListener() {

            final RailmapActivity this$0;

            public void onClick(DialogInterface dialoginterface, int i)
            {
                String as1[] = getResources().getStringArray(0x7f070006);
                String as2[] = getResources().getStringArray(0x7f070007);
                int j = TransitUtil.getLatLngInt(as1[i]);
                int k = TransitUtil.getLatLngInt(as2[i]);
                mapControl.setCenter(new GeoPoint(j, k));
                if (chkBusstop.isChecked())
                {
                    search(as1[i], as2[i], 2);
                }
                if (chkStation.isChecked())
                {
                    search(as1[i], as2[i], 1);
                }
            }

            
            {
                this$0 = RailmapActivity.this;
                super();
            }
        }).show();
    }





/*
    static boolean access$1002(RailmapActivity railmapactivity, boolean flag)
    {
        railmapactivity.bRouteFinish = flag;
        return flag;
    }

*/



/*
    static PinOverlay access$1102(RailmapActivity railmapactivity, PinOverlay pinoverlay)
    {
        railmapactivity.pinStationOverlay = pinoverlay;
        return pinoverlay;
    }

*/



/*
    static PinOverlay access$1202(RailmapActivity railmapactivity, PinOverlay pinoverlay)
    {
        railmapactivity.pinBusstopOverlay = pinoverlay;
        return pinoverlay;
    }

*/

















/*
    static PolylineOverlay access$2602(RailmapActivity railmapactivity, PolylineOverlay polylineoverlay)
    {
        railmapactivity.polylineOverlay = polylineoverlay;
        return polylineoverlay;
    }

*/







/*
    static GeoPoint access$302(RailmapActivity railmapactivity, GeoPoint geopoint)
    {
        railmapactivity.pinPoint = geopoint;
        return geopoint;
    }

*/




/*
    static boolean access$3202(RailmapActivity railmapactivity, boolean flag)
    {
        railmapactivity.isWeatherUpdate = flag;
        return flag;
    }

*/




/*
    static boolean access$502(RailmapActivity railmapactivity, boolean flag)
    {
        railmapactivity.bOpenPopup = flag;
        return flag;
    }

*/




}

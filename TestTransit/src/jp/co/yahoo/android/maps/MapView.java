// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.maps;

import android.app.Activity;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import java.util.Iterator;
import java.util.List;
import jp.co.yahoo.android.maps.indoormap.IndoorDataSet;
import jp.co.yahoo.android.maps.indoormap.IndoormapOverlay;
import jp.co.yahoo.android.maps.viewlayer.BaseView;
import jp.co.yahoo.android.maps.viewlayer.BaseViewCtrl;
import jp.co.yahoo.android.maps.viewlayer.LayerInfo;
import jp.co.yahoo.android.maps.viewlayer.MapYml;

// Referenced classes of package jp.co.yahoo.android.maps:
//            PinOverlay, MapViewCtrl, MapActivity, Projection, 
//            Overlay, MapViewListener, GeoPoint, MapController, 
//            MapLayer

public class MapView extends FrameLayout
    implements android.view.View.OnTouchListener, android.view.View.OnClickListener
{
    public static interface MapTouchListener
    {

        public abstract boolean onLongPress(MapView mapview, Object obj, PinOverlay pinoverlay, GeoPoint geopoint);

        public abstract boolean onPinchIn(MapView mapview);

        public abstract boolean onPinchOut(MapView mapview);

        public abstract boolean onTouch(MapView mapview, MotionEvent motionevent);
    }


    public static int MapExistNo = 2;
    public static int MapExistUncertainty = 0;
    public static int MapExistYes = 1;
    public static int MapTypeHybrid = 8;
    public static int MapTypeIndoor = 9;
    public static int MapTypeMetroB1 = 4;
    public static int MapTypeMetroB2 = 5;
    public static int MapTypeMetroB3 = 6;
    public static int MapTypeOSM = 7;
    public static int MapTypeSatellite = 1;
    public static int MapTypeStandard = 0;
    public static int MapTypeStyle = 3;
    public static int MapTypeUnderground = 2;
    public static String TYPE_MAP = "map";
    public static String TYPE_PHOTO = "photo";
    public static String TYPE_UNDERGROUND = "b1";
    public boolean AD_TEST_MODE;
    private final int FP = -1;
    public boolean isAttached;
    public double mDtapX;
    public double mDtapY;
    private GestureDetector mGestureDetector;
    private IndoormapOverlay mIndoormapOverlay;
    private MapViewCtrl mMapViewCtrl;
    ScaleGestureDetector mScaleGestureDetector;
    private String mSpaceid;
    private Button mZoomInBtn;
    private LinearLayout mZoomInOutLayout;
    private Button mZoomOutBtn;

    public MapView(Activity activity, String s)
    {
        super(activity);
        isAttached = false;
        AD_TEST_MODE = false;
        mZoomOutBtn = null;
        mZoomInBtn = null;
        mZoomInOutLayout = null;
        mSpaceid = null;
        mIndoormapOverlay = null;
        setFocusable(true);
        setBackgroundColor(Color.rgb(192, 191, 187));
        PinOverlay.createBuiltinIcon(activity);
        mMapViewCtrl = new MapViewCtrl(activity, this, s);
        BaseView baseview = mMapViewCtrl.getBaseView();
        baseview.setLayoutParams(new android.widget.RelativeLayout.LayoutParams(-1, -1));
        addView(baseview);
        setOnTouchListener(this);
        setOnClickListener(this);
        mGestureDetector = new GestureDetector(activity, mMapViewCtrl);
        mScaleGestureDetector = new ScaleGestureDetector(activity, mMapViewCtrl);
        if (activity instanceof MapActivity)
        {
            ((MapActivity)activity).setMapView(this);
        }
    }

    private void _addIndoormapOverlay(Overlay overlay)
    {
        if (overlay != null && (overlay instanceof IndoormapOverlay))
        {
            mIndoormapOverlay = (IndoormapOverlay)overlay;
        }
    }

    private void _removeIndoormapOverlay(Overlay overlay)
    {
        if (overlay != null && (overlay instanceof IndoormapOverlay))
        {
            IndoormapOverlay indoormapoverlay = (IndoormapOverlay)overlay;
            if (indoormapoverlay != mIndoormapOverlay)
            {
                indoormapoverlay.dispose();
            }
            if (mIndoormapOverlay != null)
            {
                mIndoormapOverlay.dispose();
                mIndoormapOverlay = null;
            }
        }
    }

    private IndoormapOverlay createIndoormapOverlay(Activity activity)
    {
        return new IndoormapOverlay(activity, this, mMapViewCtrl.getBaseView().getBaseViewCtrl());
    }

    private IndoormapOverlay getFirstIndoormapOverlay()
    {
        return mIndoormapOverlay;
    }

    private int getMaximumScaleZoomLevel(int i, boolean flag)
    {
        return mMapViewCtrl.getMaximumScaleZoomLevel(i, flag);
    }

    private boolean isExistIndoormapOverlay()
    {
        return mIndoormapOverlay != null;
    }

    private void updateIndoormapOverlay(Boolean boolean1, IndoormapOverlay indoormapoverlay)
    {
        if (boolean1 == null) goto _L2; else goto _L1
_L1:
        boolean flag1 = boolean1.booleanValue();
_L9:
        if (indoormapoverlay != null)
        {
            break MISSING_BLOCK_LABEL_19;
        }
        indoormapoverlay = getFirstIndoormapOverlay();
        if (!flag1) goto _L4; else goto _L3
_L3:
        if (indoormapoverlay == null) goto _L6; else goto _L5
_L5:
        boolean flag2;
        if (indoormapoverlay.getVisible())
        {
            flag2 = true;
            break MISSING_BLOCK_LABEL_38;
        }
          goto _L6
_L2:
        flag = isExistIndoormapOverlay();
        flag1 = flag;
        continue; /* Loop/switch isn't completed */
_L6:
        flag2 = false;
          goto _L7
_L4:
        flag2 = false;
_L7:
        boolean flag;
        try
        {
            updateIndoormapOverlay(flag2);
            return;
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
        }
        return;
        if (true) goto _L9; else goto _L8
_L8:
    }

    private void updateIndoormapOverlayWithSetMapType(int i)
    {
        try
        {
            updateIndoormapOverlay(null, null);
            int j = mMapViewCtrl.getMaximumScaleZoomLevel(i, isVisibleIndoormap());
            if (getZoomLevel() < j)
            {
                mMapViewCtrl.setZoom(j);
            }
            return;
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
        }
    }

    public boolean addIndoormapOverlay(int i, IndoormapOverlay indoormapoverlay)
    {
        if (indoormapoverlay != null)
        {
            _addIndoormapOverlay(indoormapoverlay);
            updateIndoormapOverlay(Boolean.valueOf(true), indoormapoverlay);
            mMapViewCtrl.getOverlays().add(i, indoormapoverlay);
            return true;
        } else
        {
            return false;
        }
    }

    public boolean addIndoormapOverlay(IndoormapOverlay indoormapoverlay)
    {
        if (indoormapoverlay != null)
        {
            _addIndoormapOverlay(indoormapoverlay);
            updateIndoormapOverlay(Boolean.valueOf(true), indoormapoverlay);
            return mMapViewCtrl.getOverlays().add(indoormapoverlay);
        } else
        {
            return false;
        }
    }

    public void addMapViewListener(MapViewListener mapviewlistener)
    {
        mMapViewCtrl.getBaseView().addMapViewListener(mapviewlistener);
    }

    public boolean canCoverCenter()
    {
        return false;
    }

    protected boolean checkLayoutParams(android.widget.FrameLayout.LayoutParams layoutparams)
    {
        return false;
    }

    public void computeScroll()
    {
    }

    public void displayZoomControls(boolean flag)
    {
    }

    public void forceRedrawIndoormapOverlay()
    {
        if (mIndoormapOverlay != null)
        {
            mIndoormapOverlay.updateIndoormap();
        }
    }

    protected android.widget.FrameLayout.LayoutParams generateDefaultLayoutParams()
    {
        return null;
    }

    public android.widget.FrameLayout.LayoutParams generateLayoutParams(AttributeSet attributeset)
    {
        return null;
    }

    protected android.widget.FrameLayout.LayoutParams generateLayoutParams(android.widget.FrameLayout.LayoutParams layoutparams)
    {
        return null;
    }

    public IndoorDataSet getIndoorDataSet(GeoPoint geopoint)
    {
        MapYml mapyml;
        mapyml = new MapYml(mMapViewCtrl.getBaseView(), mMapViewCtrl.getBaseView().getBaseViewCtrl().getAppid());
        mapyml.setAttestation(mMapViewCtrl.getBaseView().getBaseViewCtrl().getAttestation());
        if (geopoint != null) goto _L2; else goto _L1
_L1:
        if (mapyml.getYml(getMapCenter())) goto _L4; else goto _L3
_L3:
        return null;
_L4:
        return mapyml.getIndoorDataSet();
_L2:
        if (mapyml.getYml(geopoint))
        {
            return mapyml.getIndoorDataSet();
        }
        if (true) goto _L3; else goto _L5
_L5:
    }

    public IndoorDataSet getIndoorDataSet(GeoPoint geopoint, GeoPoint geopoint1)
    {
        MapYml mapyml;
        mapyml = new MapYml(mMapViewCtrl.getBaseView(), mMapViewCtrl.getBaseView().getBaseViewCtrl().getAppid());
        mapyml.setAttestation(mMapViewCtrl.getBaseView().getBaseViewCtrl().getAttestation());
        if (geopoint != null && geopoint1 != null) goto _L2; else goto _L1
_L1:
        Projection projection = getProjection();
        if (mapyml.getYml(projection.fromPixels(0, 0), projection.fromPixels(getWidth(), getHeight()))) goto _L4; else goto _L3
_L3:
        return null;
_L4:
        return mapyml.getIndoorDataSet();
_L2:
        if (mapyml.getYml(geopoint, geopoint1))
        {
            return mapyml.getIndoorDataSet();
        }
        if (true) goto _L3; else goto _L5
_L5:
    }

    public IndoorDataSet getIndoorDataSet(GeoPoint geopoint, GeoPoint geopoint1, GeoPoint geopoint2)
    {
        MapYml mapyml = new MapYml(mMapViewCtrl.getBaseView(), mMapViewCtrl.getBaseView().getBaseViewCtrl().getAppid());
        mapyml.setAttestation(mMapViewCtrl.getBaseView().getBaseViewCtrl().getAttestation());
        if (geopoint == null)
        {
            geopoint = getMapCenter();
        }
        if (geopoint1 == null || geopoint2 == null)
        {
            Projection projection = getProjection();
            geopoint1 = projection.fromPixels(0, 0);
            geopoint2 = projection.fromPixels(getWidth(), getHeight());
        }
        if (mapyml.getYml(geopoint, geopoint1, geopoint2))
        {
            return mapyml.getIndoorDataSet();
        } else
        {
            return null;
        }
    }

    public IndoormapOverlay getIndoormapOverlay()
    {
        return getFirstIndoormapOverlay();
    }

    public int getLatitudeSpan()
    {
        return 0;
    }

    public int getLongitudeSpan()
    {
        return 0;
    }

    public GeoPoint getMapCenter()
    {
        return mMapViewCtrl.getMapCenter();
    }

    public MapController getMapController()
    {
        return mMapViewCtrl;
    }

    public MapLayer getMapLayer(GeoPoint geopoint)
    {
        MapYml mapyml;
        mapyml = new MapYml(mMapViewCtrl.getBaseView(), mMapViewCtrl.getBaseView().getBaseViewCtrl().getAppid());
        mapyml.setAttestation(mMapViewCtrl.getBaseView().getBaseViewCtrl().getAttestation());
        if (geopoint != null) goto _L2; else goto _L1
_L1:
        MapYml mapyml1 = mMapViewCtrl.getNowMapYml();
        if (mapyml1 == null) goto _L4; else goto _L3
_L3:
        mapyml = mapyml1;
_L6:
        return mapyml;
_L4:
        if (!mapyml.getYml(getMapCenter()))
        {
            return null;
        }
        continue; /* Loop/switch isn't completed */
_L2:
        if (!mapyml.getYml(geopoint))
        {
            return null;
        }
        if (true) goto _L6; else goto _L5
_L5:
    }

    public int getMapType()
    {
        if (mMapViewCtrl == null)
        {
            return 0;
        } else
        {
            return mMapViewCtrl.getMaptype();
        }
    }

    public List getOverlays()
    {
        return mMapViewCtrl.getOverlays();
    }

    public Projection getProjection()
    {
        return mMapViewCtrl.getProjection();
    }

    public double getTileMagnification()
    {
        return ((BaseViewCtrl)mMapViewCtrl.getProjection()).getMagnification();
    }

    public int getTileSize()
    {
        return ((BaseViewCtrl)mMapViewCtrl.getProjection()).getTileSize();
    }

    public boolean getUnderground()
    {
        return mMapViewCtrl.getUnderground();
    }

    public View getZoomControls()
    {
        return null;
    }

    public int getZoomLevel()
    {
        return mMapViewCtrl.getNowLayer().level;
    }

    public void invalidate()
    {
        if (mMapViewCtrl != null)
        {
            mMapViewCtrl.reDraw();
        }
        super.invalidate();
    }

    public boolean isMapVisible()
    {
        return mMapViewCtrl.isMapVisible();
    }

    public boolean isSatellite()
    {
        return false;
    }

    public boolean isTraffic()
    {
        return false;
    }

    public boolean isVisibleIndoormap()
    {
        IndoormapOverlay indoormapoverlay = getFirstIndoormapOverlay();
        return indoormapoverlay != null && indoormapoverlay.getVisible();
    }

    public void onClick(View view)
    {
        if (view != mZoomInBtn) goto _L2; else goto _L1
_L1:
        mMapViewCtrl.zoomIn();
_L4:
        mMapViewCtrl.onClick(view);
        return;
_L2:
        if (view == mZoomOutBtn)
        {
            mMapViewCtrl.zoomOut();
        }
        if (true) goto _L4; else goto _L3
_L3:
    }

    public void onDestroy()
    {
        if (mMapViewCtrl != null)
        {
            mMapViewCtrl.onDestroy();
        }
    }

    protected void onDetachedFromWindow()
    {
    }

    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
        if (mMapViewCtrl != null)
        {
            mMapViewCtrl.doZoomToSpan();
        }
    }

    public void onFocusChanged()
    {
    }

    public boolean onKeyDown(int i, KeyEvent keyevent)
    {
        boolean flag = super.onKeyDown(i, keyevent);
        if (!flag)
        {
            flag = mMapViewCtrl.onKeyDown(i, keyevent);
        }
        return flag;
    }

    public boolean onKeyUp(int i, KeyEvent keyevent)
    {
        boolean flag = super.onKeyUp(i, keyevent);
        if (!flag)
        {
            flag = mMapViewCtrl.onKeyUp(i, keyevent);
        }
        return flag;
    }

    protected void onLayout(boolean flag, int i, int j, int k, int l)
    {
        super.onLayout(flag, i, j, k, l);
    }

    protected void onMeasure(int i, int j)
    {
        super.onMeasure(i, j);
    }

    public void onPause()
    {
        if (mMapViewCtrl != null)
        {
            mMapViewCtrl.onPause();
        }
    }

    public void onRestoreInstanceState(Bundle bundle)
    {
    }

    public void onResume()
    {
    }

    public void onSaveInstanceState(Bundle bundle)
    {
    }

    protected void onSizeChanged(int i, int j, int k, int l)
    {
        mMapViewCtrl.setWidth(i);
        mMapViewCtrl.setHeight(j);
        mMapViewCtrl.doRestMap();
    }

    public boolean onTouch(View view, MotionEvent motionevent)
    {
        mGestureDetector.onTouchEvent(motionevent);
        mScaleGestureDetector.onTouchEvent(motionevent);
        mMapViewCtrl.onTouch(view, motionevent);
        return false;
    }

    public boolean onTrackballEvent(MotionEvent motionevent)
    {
        return super.onTrackballEvent(motionevent);
    }

    public void onWindowFocusChanged(boolean flag)
    {
    }

    public void preLoad()
    {
    }

    public void reDraw()
    {
        mMapViewCtrl.reDraw();
    }

    public boolean removeIndoormapOverlay(IndoormapOverlay indoormapoverlay)
    {
        boolean flag = false;
        if (indoormapoverlay != null)
        {
            _removeIndoormapOverlay(indoormapoverlay);
            updateIndoormapOverlay(Boolean.valueOf(false), null);
            flag = mMapViewCtrl.getOverlays().remove(indoormapoverlay);
        }
        return flag;
    }

    public void removeMapViewListener(MapViewListener mapviewlistener)
    {
        mMapViewCtrl.getBaseView().removeMapViewListener(mapviewlistener);
    }

    public void removeOverlayAll()
    {
        Iterator iterator = mMapViewCtrl.getOverlays().iterator();
        do
        {
            if (!iterator.hasNext())
            {
                updateIndoormapOverlay(Boolean.valueOf(false), null);
                mMapViewCtrl.getOverlays().clear();
                return;
            }
            _removeIndoormapOverlay((Overlay)iterator.next());
        } while (true);
    }

    public void setBuiltInZoomControls(boolean flag)
    {
        if (mZoomInOutLayout == null)
        {
            mZoomInOutLayout = new LinearLayout(getContext());
            addView(mZoomInOutLayout, new android.view.ViewGroup.LayoutParams(-1, -1));
        }
        if (flag)
        {
            if (mZoomOutBtn == null)
            {
                mZoomOutBtn = new Button(getContext());
                mZoomOutBtn.setOnClickListener(this);
                mZoomOutBtn.setText("-");
                mZoomOutBtn.setLayoutParams(new android.widget.LinearLayout.LayoutParams(70, 70));
            }
            mZoomInOutLayout.addView(mZoomOutBtn);
            if (mZoomInBtn == null)
            {
                mZoomInBtn = new Button(getContext());
                mZoomInBtn.setOnClickListener(this);
                mZoomInBtn.setText("+");
                mZoomInBtn.setLayoutParams(new android.widget.LinearLayout.LayoutParams(70, 70));
            }
            mZoomInOutLayout.addView(mZoomInBtn);
        } else
        {
            if (mZoomOutBtn != null)
            {
                mZoomInOutLayout.removeView(mZoomOutBtn);
            }
            if (mZoomInBtn != null)
            {
                mZoomInOutLayout.removeView(mZoomInBtn);
                return;
            }
        }
    }

    public void setCopyrightValign(int i)
    {
        mMapViewCtrl.setCopyrightValign(i);
    }

    public void setLongPress(boolean flag)
    {
        mMapViewCtrl.setLongPress(flag);
    }

    public void setMapTouchListener(MapTouchListener maptouchlistener)
    {
        if (mMapViewCtrl != null)
        {
            mMapViewCtrl.setMapTouchListener(maptouchlistener);
        }
    }

    public void setMapType(int i)
    {
        mMapViewCtrl.setMapType(i);
        updateIndoormapOverlayWithSetMapType(i);
    }

    public void setMapType(int i, int j)
    {
        mMapViewCtrl.setMapType(i, j);
        updateIndoormapOverlayWithSetMapType(i);
    }

    public void setMapType(int i, String s)
    {
        mMapViewCtrl.setMaptype(i, s);
        updateIndoormapOverlayWithSetMapType(i);
    }

    public void setMapType(int i, String s, List list)
    {
        mMapViewCtrl.setMaptype(i, s, list);
        updateIndoormapOverlayWithSetMapType(i);
    }

    public void setMapVisible(boolean flag)
    {
        mMapViewCtrl.setMapVisible(flag);
    }

    public void setMinimumZoomLevel(int i)
    {
        mMapViewCtrl.setMinimumZoomLevel(i);
    }

    public void setSatellite(boolean flag)
    {
        if (flag)
        {
            mMapViewCtrl.setMapType(MapTypeSatellite);
            return;
        } else
        {
            mMapViewCtrl.setMapType(MapTypeStandard);
            return;
        }
    }

    public void setScalebar(boolean flag)
    {
        mMapViewCtrl.setScalebar(flag);
    }

    public void setSpaceId(String s)
    {
        mSpaceid = s;
    }

    public void setStreetView(boolean flag)
    {
    }

    public void setTraffic(boolean flag)
    {
    }

    public void updateIndoormapOverlay(boolean flag)
    {
        try
        {
            int i = getMaximumScaleZoomLevel(getMapType(), flag);
            setMinimumZoomLevel(i);
            if (getZoomLevel() < i)
            {
                mMapViewCtrl.setZoom(i);
            }
            return;
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
        }
    }

}

// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.maps;

import android.app.Activity;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Message;
import android.util.FloatMath;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import java.util.List;
import jp.co.yahoo.android.maps.viewlayer.BaseView;
import jp.co.yahoo.android.maps.viewlayer.BaseViewCtrl;
import jp.co.yahoo.android.maps.viewlayer.Coordinate;
import jp.co.yahoo.android.maps.viewlayer.LayerInfo;
import jp.co.yahoo.android.maps.viewlayer.MapLayerManager;
import jp.co.yahoo.android.maps.viewlayer.MapYml;
import jp.co.yahoo.android.maps.viewlayer.ViewLayer;
import jp.co.yahoo.android.maps.viewlayer.etc.ScalebarAndCopyrightLayer;
import jp.co.yahoo.android.maps.viewlayer.overlay.OverlayLayer;
import jp.co.yahoo.android.maps.viewlayer.tile.TileBaseLayer;

// Referenced classes of package jp.co.yahoo.android.maps:
//            MapController, DoublePoint, MultiTouch, PinOverlay, 
//            MapView, Overlay, GeoPoint, Projection

public class MapViewCtrl extends android.view.ScaleGestureDetector.SimpleOnScaleGestureListener
    implements MapController, android.view.GestureDetector.OnGestureListener, android.view.GestureDetector.OnDoubleTapListener
{

    public static String LAYER_OVERLAY_NAME = "LAYER_OVERLAY";
    public static String LAYER_SCALEBARANDCOPYRIGHT_NAME = "LAYER_SCALEBARANDCOPYRIGHT";
    public static String LAYER_TILE_NAME = "LAYER_TILE";
    private BaseView mBaseView;
    public DoublePoint mDtap;
    private boolean mFling;
    private boolean mLongPressFunction;
    private MapView mMapView;
    private DoublePoint mPinchCenterPoint;
    private boolean mPinchMode;
    private float mScaleFactor;
    private ScalebarAndCopyrightLayer mScalebarAndCopyrightLayer;
    private MapView.MapTouchListener mTouchListener;
    private double mTowTouchDist;
    private boolean mapVisible;
    private int mlatSpanE6;
    private int mlonSpanE6;
    private int moveXstabilization;
    private int moveYstabilization;

    public MapViewCtrl(Activity activity, MapView mapview, String s)
    {
        mDtap = new DoublePoint(0.0D, 0.0D);
        mPinchCenterPoint = null;
        mapVisible = true;
        mScalebarAndCopyrightLayer = null;
        mScaleFactor = 1.0F;
        mPinchMode = false;
        mFling = false;
        mTowTouchDist = -1D;
        mlatSpanE6 = 0;
        mlonSpanE6 = 0;
        mBaseView = new BaseView(s, activity, mapview);
        mMapView = mapview;
        TileBaseLayer tilebaselayer = new TileBaseLayer(mBaseView);
        tilebaselayer.setName(LAYER_TILE_NAME);
        mBaseView.addLayer(tilebaselayer);
        OverlayLayer overlaylayer = new OverlayLayer(mapview);
        overlaylayer.setName(LAYER_OVERLAY_NAME);
        mBaseView.addLayer(overlaylayer);
        mScalebarAndCopyrightLayer = new ScalebarAndCopyrightLayer(mBaseView);
        mScalebarAndCopyrightLayer.setName(LAYER_SCALEBARANDCOPYRIGHT_NAME);
        mBaseView.addLayer(mScalebarAndCopyrightLayer);
        mLongPressFunction = false;
        moveXstabilization = 0;
        moveYstabilization = 0;
        mPinchCenterPoint = new DoublePoint();
        mBaseView.getBaseViewCtrl().startAttestation();
    }

    private float getLength(MultiTouch multitouch)
    {
        boolean flag = multitouch.checkVersion();
        float f = 0.0F;
        float f1 = 0.0F;
        if (flag)
        {
            int i = multitouch.getPointerCount();
            f = 0.0F;
            f1 = 0.0F;
            if (i > 1)
            {
                f = multitouch.wgetX(1) - multitouch.wgetX(0);
                f1 = multitouch.wgetY(1) - multitouch.wgetY(0);
            }
        }
        return FloatMath.sqrt(f * f + f1 * f1);
    }

    private void moveTouch(double d, double d1)
    {
        double d2 = d;
        double d3 = d1;
        double d4 = Math.abs(d2);
        double d5 = Math.abs(d3);
        if (d4 + d5 < 4D)
        {
            if (d2 < 0.0D)
            {
                if (moveXstabilization >= 0)
                {
                    d2 = 0.0D;
                    moveXstabilization = -1 + moveXstabilization;
                }
            } else
            if (d2 > 0.0D && moveXstabilization <= 0)
            {
                d2 = 0.0D;
                moveXstabilization = 1 + moveXstabilization;
            }
            if (d3 < 0.0D)
            {
                if (moveYstabilization >= 0)
                {
                    d3 = 0.0D;
                    moveYstabilization = -1 + moveYstabilization;
                }
            } else
            if (d3 > 0.0D && moveYstabilization <= 0)
            {
                d3 = 0.0D;
                moveYstabilization = 1 + moveYstabilization;
            }
            d3 *= 0.69999999999999996D;
            d2 *= 0.69999999999999996D;
        }
        if (d4 + d5 > 0.0D)
        {
            mBaseView.doEvent(MapCtrlEvent.EventType.SET_MOVE, d2, d3);
        }
    }

    public boolean addLayer(ViewLayer viewlayer)
    {
        return mBaseView.addLayer(viewlayer);
    }

    public boolean addOverlayLayer(Overlay overlay)
    {
        return ((OverlayLayer)mBaseView.getLayerByName(LAYER_OVERLAY_NAME)).getOverlayList().add(overlay);
    }

    public void animateTo(GeoPoint geopoint)
    {
        mBaseView.getMapController().animateTo(geopoint);
    }

    public void animateTo(GeoPoint geopoint, Message message)
    {
    }

    public void animateTo(GeoPoint geopoint, Runnable runnable)
    {
    }

    public void clearOverlayLayer()
    {
        ((OverlayLayer)mBaseView.getLayerByName(LAYER_OVERLAY_NAME)).getOverlayList().clear();
    }

    public void doRestMap()
    {
        mBaseView.doEvent(MapCtrlEvent.EventType.RESET_MAP, null);
    }

    public void doZoomToSpan()
    {
        if (mlatSpanE6 != 0 && mlonSpanE6 != 0)
        {
            zoomToSpan(mlatSpanE6, mlonSpanE6);
        }
    }

    public BaseView getBaseView()
    {
        return mBaseView;
    }

    public Coordinate getCenterPos()
    {
        return mBaseView.getCenterPos();
    }

    public int[] getCenterScale()
    {
        return mBaseView.getMapController().getCenterScale();
    }

    public final int getHeight()
    {
        return mBaseView.getHeight();
    }

    public GeoPoint getMapCenter()
    {
        return mBaseView.getBaseViewCtrl().getMapCenter();
    }

    public MapLayerManager getMapLayerManager()
    {
        return mBaseView.getMapLayerManager();
    }

    public MapView getMapView()
    {
        return mMapView;
    }

    public int getMaptype()
    {
        return mBaseView.getMapController().getMaptype();
    }

    public int getMaximumScaleZoomLevel(int i, boolean flag)
    {
        return mBaseView.getMaximumScaleZoomLevel(i, flag);
    }

    public LayerInfo getNowLayer()
    {
        return mBaseView.getNowLayer();
    }

    public MapYml getNowMapYml()
    {
        if (mScalebarAndCopyrightLayer != null)
        {
            return mScalebarAndCopyrightLayer.getMapYml();
        } else
        {
            return null;
        }
    }

    public List getOverlays()
    {
        return ((OverlayLayer)mBaseView.getLayerByName(LAYER_OVERLAY_NAME)).getOverlayList();
    }

    public Projection getProjection()
    {
        return mBaseView.getProjection();
    }

    public boolean getUnderground()
    {
        return mScalebarAndCopyrightLayer.getUnderground();
    }

    public final int getWidth()
    {
        return mBaseView.getWidth();
    }

    public boolean isMapVisible()
    {
        return mapVisible;
    }

    public void onClick(View view)
    {
    }

    public void onDestroy()
    {
        mBaseView.doEvent(MapCtrlEvent.EventType.ON_DESTROY, null);
    }

    public boolean onDoubleTap(MotionEvent motionevent)
    {
        mDtap.x = motionevent.getX();
        mDtap.y = motionevent.getY();
        mBaseView.doEvent(MapCtrlEvent.EventType.ON_DOUBLETAP, new DoublePoint(motionevent.getX(), motionevent.getY()));
        return true;
    }

    public boolean onDoubleTapEvent(MotionEvent motionevent)
    {
        return false;
    }

    public boolean onDown(MotionEvent motionevent)
    {
        return false;
    }

    public void onDraw(Canvas canvas)
    {
    }

    public boolean onFling(MotionEvent motionevent, MotionEvent motionevent1, float f, float f1)
    {
        if (!mPinchMode)
        {
            double d = -f / 60F;
            double d1 = -f1 / 60F;
            if (Math.abs(d) > 4D && Math.abs(d1) > 4D)
            {
                mBaseView.doEvent(MapCtrlEvent.EventType.SET_FLICK, -f / 60F, -f1 / 60F);
                mFling = true;
            }
        }
        return true;
    }

    public boolean onKey(View view, int i, KeyEvent keyevent)
    {
        return false;
    }

    public boolean onKeyDown(int i, KeyEvent keyevent)
    {
        mBaseView.doEvent(MapCtrlEvent.EventType.KEY_DOWN, i, keyevent);
        return true;
    }

    public boolean onKeyUp(int i, KeyEvent keyevent)
    {
        mBaseView.doEvent(MapCtrlEvent.EventType.KEY_UP, i, keyevent);
        return true;
    }

    public void onLongPress(MotionEvent motionevent)
    {
        if (mLongPressFunction)
        {
            GeoPoint geopoint = mBaseView.fromPixels((int)motionevent.getX(), (int)motionevent.getY());
            PinOverlay pinoverlay = new PinOverlay(0);
            Boolean boolean1 = Boolean.valueOf(getOverlays().add(pinoverlay));
            pinoverlay.addPoint(geopoint, "");
            if (mTouchListener != null)
            {
                mTouchListener.onLongPress(getMapView(), boolean1, pinoverlay, geopoint);
                return;
            }
        }
    }

    public void onPause()
    {
    }

    public boolean onScale(ScaleGestureDetector scalegesturedetector)
    {
        mScaleFactor = mScaleFactor * scalegesturedetector.getScaleFactor();
        mBaseView.doEvent(MapCtrlEvent.EventType.ON_PINCH, mPinchCenterPoint.x, mPinchCenterPoint.y, mScaleFactor);
        return true;
    }

    public boolean onScaleBegin(ScaleGestureDetector scalegesturedetector)
    {
        mScaleFactor = 1.0F;
        mPinchMode = true;
        mPinchCenterPoint.x = getWidth() / 2;
        mPinchCenterPoint.y = getHeight() / 2;
        mBaseView.doEvent(MapCtrlEvent.EventType.ON_PINCH, mPinchCenterPoint.x, mPinchCenterPoint.y, 1.0F);
        return super.onScaleBegin(scalegesturedetector);
    }

    public void onScaleEnd(ScaleGestureDetector scalegesturedetector)
    {
        mScaleFactor = mScaleFactor * scalegesturedetector.getScaleFactor();
        mBaseView.doEvent(MapCtrlEvent.EventType.ON_PINCH_FINISH, mPinchCenterPoint.x, mPinchCenterPoint.y, mScaleFactor);
        if (1.02D >= (double)mScaleFactor && 0.97999999999999998D <= (double)mScaleFactor || mTouchListener == null) goto _L2; else goto _L1
_L1:
        if (mScaleFactor <= 1.0F) goto _L4; else goto _L3
_L3:
        mTouchListener.onPinchOut(getMapView());
_L2:
        mScaleFactor = 1.0F;
        super.onScaleEnd(scalegesturedetector);
        mPinchMode = false;
        return;
_L4:
        if (mScaleFactor < 1.0F)
        {
            mTouchListener.onPinchIn(getMapView());
        }
        if (true) goto _L2; else goto _L5
_L5:
    }

    public boolean onScroll(MotionEvent motionevent, MotionEvent motionevent1, float f, float f1)
    {
        if (!mPinchMode)
        {
            moveTouch(f, f1);
        }
        return false;
    }

    public void onShowPress(MotionEvent motionevent)
    {
    }

    public boolean onSingleTapConfirmed(MotionEvent motionevent)
    {
        return false;
    }

    public boolean onSingleTapUp(MotionEvent motionevent)
    {
        if (motionevent.getPointerCount() < 2)
        {
            onTap((int)motionevent.getX(), (int)motionevent.getY());
        }
        return false;
    }

    public void onSizeChanged(int i, int j, int k, int l)
    {
        Rect rect = new Rect(0, 0, i, j);
        mBaseView.doEvent(MapCtrlEvent.EventType.ON_SIZECHANGED, rect);
    }

    public boolean onTap(double d, double d1)
    {
        GeoPoint geopoint = mBaseView.fromPixels((int)d, (int)d1);
        mBaseView.doEvent(MapCtrlEvent.EventType.ON_TAP, geopoint);
        return true;
    }

    public boolean onTouch(View view, MotionEvent motionevent)
    {
        mBaseView.doTouch(motionevent);
        reDraw();
        if (mTouchListener != null)
        {
            mTouchListener.onTouch(getMapView(), motionevent);
        }
        0xff & motionevent.getAction();
        JVM INSTR tableswitch 0 6: default 84
    //                   0 86
    //                   1 171
    //                   2 84
    //                   3 84
    //                   4 84
    //                   5 94
    //                   6 196;
           goto _L1 _L2 _L3 _L1 _L1 _L1 _L4 _L5
_L1:
        return false;
_L2:
        mFling = false;
        continue; /* Loop/switch isn't completed */
_L4:
        if (motionevent != null && motionevent.getPointerCount() > 1)
        {
            double d4 = motionevent.getX(0);
            double d5 = motionevent.getY(0);
            double d6 = motionevent.getX(1);
            double d7 = motionevent.getY(1);
            mTowTouchDist = Math.sqrt((d4 - d6) * (d4 - d6) + (d5 - d7) * (d5 - d7));
        }
        continue; /* Loop/switch isn't completed */
_L3:
        if (!mFling)
        {
            mBaseView.onChangeMap();
        }
        mTowTouchDist = -1D;
        continue; /* Loop/switch isn't completed */
_L5:
        if (motionevent != null && motionevent.getPointerCount() > 1 && mTowTouchDist != -1D)
        {
            double d = motionevent.getX(0);
            double d1 = motionevent.getY(0);
            double d2 = motionevent.getX(1);
            double d3 = motionevent.getY(1);
            if (Math.abs(Math.sqrt((d - d2) * (d - d2) + (d1 - d3) * (d1 - d3)) - mTowTouchDist) < 10D)
            {
                mDtap.x = getWidth() / 2;
                mDtap.y = getHeight() / 2;
                mBaseView.doEvent(MapCtrlEvent.EventType.ON_TWOTAP, mDtap);
            }
        }
        mTowTouchDist = -1D;
        if (true) goto _L1; else goto _L6
_L6:
    }

    public boolean onTrackballEvent(MotionEvent motionevent)
    {
        mBaseView.doEvent(MapCtrlEvent.EventType.ON_TRACKBALLEVENT, motionevent);
        return true;
    }

    public void reDraw()
    {
        mBaseView.reDraw();
    }

    public boolean removeLayer(ViewLayer viewlayer)
    {
        return mBaseView.removeLayer(viewlayer);
    }

    public boolean removeOverlayLayer(Overlay overlay)
    {
        return ((OverlayLayer)mBaseView.getLayerByName(LAYER_OVERLAY_NAME)).getOverlayList().remove(overlay);
    }

    public void scrollBy(int i, int j)
    {
        mBaseView.getMapController().scrollBy(i, j);
    }

    public void setCenter(GeoPoint geopoint)
    {
        if (geopoint != null)
        {
            mBaseView.doEvent(MapCtrlEvent.EventType.SET_CENTER, geopoint);
        }
    }

    public void setCopyright(String s)
    {
        mBaseView.doEvent(MapCtrlEvent.EventType.SET_COPYRIGHT, s);
    }

    public void setCopyrightValign(int i)
    {
        mScalebarAndCopyrightLayer.setCopyrightValign(i);
    }

    public void setCpOn(boolean flag)
    {
        mScalebarAndCopyrightLayer.setCpOn(flag);
    }

    public final void setHeight(int i)
    {
        mBaseView.setHeight(i);
    }

    public void setLongPress(boolean flag)
    {
        mLongPressFunction = flag;
    }

    public void setMapTouchListener(MapView.MapTouchListener maptouchlistener)
    {
        mTouchListener = maptouchlistener;
    }

    public void setMapType(int i)
    {
        mBaseView.getMapController().setMapType(i);
    }

    public void setMapType(int i, int j)
    {
        ((BaseViewCtrl)mBaseView.getMapController()).setMapType(i, j);
    }

    public void setMapVisible(boolean flag)
    {
        mapVisible = flag;
    }

    public void setMaptype(int i, String s)
    {
        mBaseView.getMapController().setMaptype(i, s);
    }

    public void setMaptype(int i, String s, List list)
    {
        mBaseView.getMapController().setMaptype(i, s, list);
    }

    public void setMinimumZoomLevel(int i)
    {
        mBaseView.setMinimumZoomLevel(i);
    }

    public void setScalebar(boolean flag)
    {
        mScalebarAndCopyrightLayer.setScalebar(flag);
    }

    public final void setWidth(int i)
    {
        mBaseView.setWidth(i);
    }

    public int setZoom(int i)
    {
        mBaseView.doEvent(MapCtrlEvent.EventType.SET_ZOOM, Integer.valueOf(i));
        return 0;
    }

    public void stopAnimation(boolean flag)
    {
        mBaseView.getMapController().stopAnimation(flag);
    }

    public void stopPanning()
    {
    }

    public boolean zoomIn()
    {
        mBaseView.doEvent(MapCtrlEvent.EventType.ON_DOUBLETAP, new DoublePoint(getWidth() / 2, getHeight() / 2));
        return true;
    }

    public boolean zoomInFixing(int i, int j)
    {
        return false;
    }

    public boolean zoomOut()
    {
        mBaseView.doEvent(MapCtrlEvent.EventType.ON_TWOTAP, new DoublePoint(getWidth() / 2, getHeight() / 2));
        return true;
    }

    public boolean zoomOutFixing(int i, int j)
    {
        return false;
    }

    public void zoomToSpan(int i, int j)
    {
        if (getWidth() == 0 && getHeight() == 0)
        {
            mlatSpanE6 = i;
            mlonSpanE6 = j;
            return;
        }
        mlatSpanE6 = 0;
        mlonSpanE6 = 0;
        Coordinate coordinate = getCenterPos();
        Coordinate coordinate1 = getCenterPos();
        coordinate1.lat = (double)i / 1000000D;
        coordinate1.lon = (double)j / 1000000D;
        Coordinate coordinate2 = new Coordinate();
        Coordinate coordinate3 = new Coordinate();
        coordinate2.lat = coordinate.lat - coordinate1.lat;
        coordinate2.lon = coordinate.lon + coordinate1.lon;
        coordinate3.lat = coordinate.lat + coordinate1.lat;
        coordinate3.lon = coordinate.lon - coordinate1.lon;
        Coordinate coordinate4 = new Coordinate();
        coordinate4.lat = coordinate2.lat;
        coordinate4.lon = coordinate3.lon;
        double d = coordinate4.distance(coordinate2);
        double d1 = coordinate4.distance(coordinate3);
        double d2 = 1000D * (10D * (d / (double)getWidth()));
        double d3 = 1000D * (10D * (d1 / (double)getHeight()));
        int k;
        if (d2 <= d3)
        {
            d2 = d3;
        }
        k = (int)d2;
        setZoom(mBaseView.getMapLayerManager().getApproximateZoomLevel(k).level);
    }

}

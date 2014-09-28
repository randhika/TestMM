// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.maps.viewlayer.overlay;

import android.graphics.Canvas;
import android.os.Handler;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import java.util.List;
import jp.co.yahoo.android.maps.GeoPoint;
import jp.co.yahoo.android.maps.MapCtrlEvent;
import jp.co.yahoo.android.maps.MapView;
import jp.co.yahoo.android.maps.Overlay;
import jp.co.yahoo.android.maps.PinOverlay;
import jp.co.yahoo.android.maps.PinchParam;
import jp.co.yahoo.android.maps.PopupOverlay;
import jp.co.yahoo.android.maps.indoormap.data.TileManager;
import jp.co.yahoo.android.maps.viewlayer.BaseViewCtrl;
import jp.co.yahoo.android.maps.viewlayer.ViewLayer;

// Referenced classes of package jp.co.yahoo.android.maps.viewlayer.overlay:
//            OverlayList

public class OverlayLayer extends ViewLayer
{

    private static int $SWITCH_TABLE$jp$co$yahoo$android$maps$MapCtrlEvent$EventType[];
    private static int ST_AUTOZOOM = 4;
    private static int ST_FLICK = 1;
    private static int ST_IDOL = 0;
    private static int ST_ZOOM = 2;
    private Handler handler;
    private MapView mMapView;
    private BaseViewCtrl mMapctrl;
    public List mOverlayList;
    private int mState;
    private TileManager mTileManager;

    static int[] $SWITCH_TABLE$jp$co$yahoo$android$maps$MapCtrlEvent$EventType()
    {
        int ai[] = $SWITCH_TABLE$jp$co$yahoo$android$maps$MapCtrlEvent$EventType;
        if (ai != null)
        {
            return ai;
        }
        int ai1[] = new int[jp.co.yahoo.android.maps.MapCtrlEvent.EventType.values().length];
        try
        {
            ai1[jp.co.yahoo.android.maps.MapCtrlEvent.EventType.EVENT_MAX.ordinal()] = 23;
        }
        catch (NoSuchFieldError nosuchfielderror) { }
        try
        {
            ai1[jp.co.yahoo.android.maps.MapCtrlEvent.EventType.KEY_DOWN.ordinal()] = 2;
        }
        catch (NoSuchFieldError nosuchfielderror1) { }
        try
        {
            ai1[jp.co.yahoo.android.maps.MapCtrlEvent.EventType.KEY_UP.ordinal()] = 1;
        }
        catch (NoSuchFieldError nosuchfielderror2) { }
        try
        {
            ai1[jp.co.yahoo.android.maps.MapCtrlEvent.EventType.ON_DESTROY.ordinal()] = 22;
        }
        catch (NoSuchFieldError nosuchfielderror3) { }
        try
        {
            ai1[jp.co.yahoo.android.maps.MapCtrlEvent.EventType.ON_DOUBLETAP.ordinal()] = 7;
        }
        catch (NoSuchFieldError nosuchfielderror4) { }
        try
        {
            ai1[jp.co.yahoo.android.maps.MapCtrlEvent.EventType.ON_PINCH.ordinal()] = 11;
        }
        catch (NoSuchFieldError nosuchfielderror5) { }
        try
        {
            ai1[jp.co.yahoo.android.maps.MapCtrlEvent.EventType.ON_PINCH_FINISH.ordinal()] = 12;
        }
        catch (NoSuchFieldError nosuchfielderror6) { }
        try
        {
            ai1[jp.co.yahoo.android.maps.MapCtrlEvent.EventType.ON_SINGLETAPCONFIRMED.ordinal()] = 5;
        }
        catch (NoSuchFieldError nosuchfielderror7) { }
        try
        {
            ai1[jp.co.yahoo.android.maps.MapCtrlEvent.EventType.ON_SIZECHANGED.ordinal()] = 10;
        }
        catch (NoSuchFieldError nosuchfielderror8) { }
        try
        {
            ai1[jp.co.yahoo.android.maps.MapCtrlEvent.EventType.ON_TAP.ordinal()] = 6;
        }
        catch (NoSuchFieldError nosuchfielderror9) { }
        try
        {
            ai1[jp.co.yahoo.android.maps.MapCtrlEvent.EventType.ON_TIMER.ordinal()] = 9;
        }
        catch (NoSuchFieldError nosuchfielderror10) { }
        try
        {
            ai1[jp.co.yahoo.android.maps.MapCtrlEvent.EventType.ON_TOUCH.ordinal()] = 3;
        }
        catch (NoSuchFieldError nosuchfielderror11) { }
        try
        {
            ai1[jp.co.yahoo.android.maps.MapCtrlEvent.EventType.ON_TRACKBALLEVENT.ordinal()] = 4;
        }
        catch (NoSuchFieldError nosuchfielderror12) { }
        try
        {
            ai1[jp.co.yahoo.android.maps.MapCtrlEvent.EventType.ON_TWOTAP.ordinal()] = 8;
        }
        catch (NoSuchFieldError nosuchfielderror13) { }
        try
        {
            ai1[jp.co.yahoo.android.maps.MapCtrlEvent.EventType.RESET_MAP.ordinal()] = 21;
        }
        catch (NoSuchFieldError nosuchfielderror14) { }
        try
        {
            ai1[jp.co.yahoo.android.maps.MapCtrlEvent.EventType.SET_ANIMETETO.ordinal()] = 19;
        }
        catch (NoSuchFieldError nosuchfielderror15) { }
        try
        {
            ai1[jp.co.yahoo.android.maps.MapCtrlEvent.EventType.SET_ATTESTATION.ordinal()] = 18;
        }
        catch (NoSuchFieldError nosuchfielderror16) { }
        try
        {
            ai1[jp.co.yahoo.android.maps.MapCtrlEvent.EventType.SET_CENTER.ordinal()] = 13;
        }
        catch (NoSuchFieldError nosuchfielderror17) { }
        try
        {
            ai1[jp.co.yahoo.android.maps.MapCtrlEvent.EventType.SET_COPYRIGHT.ordinal()] = 17;
        }
        catch (NoSuchFieldError nosuchfielderror18) { }
        try
        {
            ai1[jp.co.yahoo.android.maps.MapCtrlEvent.EventType.SET_FLICK.ordinal()] = 16;
        }
        catch (NoSuchFieldError nosuchfielderror19) { }
        try
        {
            ai1[jp.co.yahoo.android.maps.MapCtrlEvent.EventType.SET_MOVE.ordinal()] = 15;
        }
        catch (NoSuchFieldError nosuchfielderror20) { }
        try
        {
            ai1[jp.co.yahoo.android.maps.MapCtrlEvent.EventType.SET_ZOOM.ordinal()] = 14;
        }
        catch (NoSuchFieldError nosuchfielderror21) { }
        try
        {
            ai1[jp.co.yahoo.android.maps.MapCtrlEvent.EventType.UPDATE_YML.ordinal()] = 20;
        }
        catch (NoSuchFieldError nosuchfielderror22) { }
        $SWITCH_TABLE$jp$co$yahoo$android$maps$MapCtrlEvent$EventType = ai1;
        return ai1;
    }

    public OverlayLayer(MapView mapview)
    {
        mOverlayList = null;
        mTileManager = null;
        mState = ST_IDOL;
        handler = new Handler();
        mMapView = mapview;
        mOverlayList = new OverlayList(mapview);
    }

    private boolean onTwoTap()
    {
        if (mOverlayList.size() <= 0) goto _L2; else goto _L1
_L1:
        int i = -1 + mOverlayList.size();
_L5:
        if (i >= 0) goto _L3; else goto _L2
_L2:
        return true;
_L3:
        Overlay overlay = (Overlay)mOverlayList.get(i);
        if (overlay.displayZoomLevel >= mMapView.getZoomLevel() && (mState & ST_AUTOZOOM) != ST_AUTOZOOM)
        {
            mMapctrl.setFactor(1.0F);
            overlay.onTwoTap();
        }
        i--;
        if (true) goto _L5; else goto _L4
_L4:
    }

    public void doEvent(MapCtrlEvent mapctrlevent, BaseViewCtrl baseviewctrl)
    {
        mMapctrl = baseviewctrl;
        $SWITCH_TABLE$jp$co$yahoo$android$maps$MapCtrlEvent$EventType()[mapctrlevent.getEventType().ordinal()];
        JVM INSTR tableswitch 1 11: default 76
    //                   1 95
    //                   2 77
    //                   3 140
    //                   4 76
    //                   5 76
    //                   6 113
    //                   7 76
    //                   8 76
    //                   9 130
    //                   10 76
    //                   11 154;
           goto _L1 _L2 _L3 _L4 _L1 _L1 _L5 _L1 _L1 _L6 _L1 _L7
_L1:
        return;
_L3:
        onKeyDown(mapctrlevent.keyCode, mapctrlevent.keyevent, baseviewctrl.getMapView());
        return;
_L2:
        onKeyUp(mapctrlevent.keyCode, mapctrlevent.keyevent, baseviewctrl.getMapView());
        return;
_L5:
        onTap((GeoPoint)mapctrlevent.arg1, baseviewctrl.getMapView());
        return;
_L6:
        onTimer(baseviewctrl.getMapView());
        return;
_L4:
        onTouch(baseviewctrl.getMapView(), mapctrlevent.motionevent);
        return;
_L7:
        Log.d("ysugisak", "ON_PINCH");
        (PinchParam)mapctrlevent.arg1;
        baseviewctrl.setFactor(mapctrlevent.factor);
        if ((mState & ST_ZOOM) != ST_ZOOM)
        {
            mState = mState | ST_ZOOM;
            int i = 0;
            while (i < mOverlayList.size()) 
            {
                Overlay overlay = (Overlay)mOverlayList.get(i);
                if (overlay.displayZoomLevel >= baseviewctrl.getMapView().getZoomLevel())
                {
                    overlay.onPinch();
                }
                i++;
            }
        }
        if (true) goto _L1; else goto _L8
_L8:
    }

    public void draw(Canvas canvas, MapView mapview, boolean flag)
    {
        List list = mOverlayList;
        list;
        JVM INSTR monitorenter ;
        int i = 0;
_L2:
        if (i < mOverlayList.size())
        {
            break MISSING_BLOCK_LABEL_30;
        }
        list;
        JVM INSTR monitorexit ;
        return;
        Overlay overlay = (Overlay)mOverlayList.get(i);
        if (overlay.displayZoomLevel >= mapview.getZoomLevel())
        {
            overlay.draw(canvas, mapview, false);
        }
        break MISSING_BLOCK_LABEL_77;
        Exception exception;
        exception;
        list;
        JVM INSTR monitorexit ;
        throw exception;
        i++;
        if (true) goto _L2; else goto _L1
_L1:
    }

    public List getOverlayList()
    {
        return mOverlayList;
    }

    public boolean onKeyDown(int i, KeyEvent keyevent, MapView mapview)
    {
        int j = 0;
        do
        {
            if (j >= mOverlayList.size())
            {
                return false;
            }
            Overlay overlay = (Overlay)mOverlayList.get(j);
            if (overlay.displayZoomLevel >= mapview.getZoomLevel() && overlay.onKeyDown(i, keyevent, mapview))
            {
                return true;
            }
            j++;
        } while (true);
    }

    public boolean onKeyUp(int i, KeyEvent keyevent, MapView mapview)
    {
        int j = 0;
        do
        {
            if (j >= mOverlayList.size())
            {
                return false;
            }
            Overlay overlay = (Overlay)mOverlayList.get(j);
            if (overlay.displayZoomLevel >= mapview.getZoomLevel() && overlay.onKeyUp(i, keyevent, mapview))
            {
                return true;
            }
            j++;
        } while (true);
    }

    public boolean onTap(final GeoPoint p, final MapView mapView)
    {
        if (mOverlayList.size() > 0)
        {
            handler.post(new Runnable() {

                final OverlayLayer this$0;
                private final MapView val$mapView;
                private final GeoPoint val$p;

                public void run()
                {
                    boolean flag;
                    int i;
                    flag = false;
                    i = -1 + mOverlayList.size();
_L6:
                    if (i >= 0) goto _L2; else goto _L1
_L1:
                    if (mMapView != null)
                    {
                        mMapView.reDraw();
                    }
_L4:
                    return;
_L2:
                    Overlay overlay;
                    overlay = (Overlay)mOverlayList.get(i);
                    if (overlay.displayZoomLevel < mapView.getZoomLevel())
                    {
                        break MISSING_BLOCK_LABEL_101;
                    }
                    if (flag)
                    {
                        break; /* Loop/switch isn't completed */
                    }
                    if (!overlay.onTap(p, mapView))
                    {
                        break MISSING_BLOCK_LABEL_101;
                    }
                    if (overlay instanceof PopupOverlay) goto _L4; else goto _L3
_L3:
                    flag = true;
_L7:
                    i--;
                    if (true) goto _L6; else goto _L5
_L5:
                    overlay instanceof PinOverlay;
                      goto _L7
                    if (true) goto _L6; else goto _L8
_L8:
                }

            
            {
                this$0 = OverlayLayer.this;
                mapView = mapview;
                p = geopoint;
                super();
            }
            });
        }
        return true;
    }

    public boolean onTimer(MapView mapview)
    {
        int i = -1 + mOverlayList.size();
        do
        {
            if (i < 0)
            {
                return false;
            }
            ((Overlay)mOverlayList.get(i)).onTimer(mapview);
            i--;
        } while (true);
    }

    public boolean onTouch(MapView mapview, MotionEvent motionevent)
    {
        int i = 0;
        do
        {
            if (i >= mOverlayList.size())
            {
                return false;
            }
            Overlay overlay = (Overlay)mOverlayList.get(i);
            if (overlay.displayZoomLevel >= mapview.getZoomLevel())
            {
                overlay.onTouchEvent(motionevent, mapview);
            }
            i++;
        } while (true);
    }

    public boolean onTrackballEvent(MapView mapview, MotionEvent motionevent)
    {
        int i = 0;
        do
        {
            if (i >= mOverlayList.size())
            {
                return false;
            }
            Overlay overlay = (Overlay)mOverlayList.get(i);
            if (overlay.displayZoomLevel >= mapview.getZoomLevel() && overlay.onTrackballEvent(motionevent, mapview))
            {
                return true;
            }
            i++;
        } while (true);
    }


}

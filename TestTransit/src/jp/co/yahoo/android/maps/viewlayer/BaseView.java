// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.maps.viewlayer;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;
import jp.co.yahoo.android.maps.GeoPoint;
import jp.co.yahoo.android.maps.MapController;
import jp.co.yahoo.android.maps.MapCtrlEvent;
import jp.co.yahoo.android.maps.MapCtrlEventsPool;
import jp.co.yahoo.android.maps.MapView;
import jp.co.yahoo.android.maps.MapViewCtrl;
import jp.co.yahoo.android.maps.MapViewListener;
import jp.co.yahoo.android.maps.Projection;
import jp.co.yahoo.android.maps.indoormap.IndoormapOverlay;
import jp.co.yahoo.android.maps.viewlayer.tile.TileBaseLayer;

// Referenced classes of package jp.co.yahoo.android.maps.viewlayer:
//            BaseViewCtrl, ViewLayer, Coordinate, MapLayerManager, 
//            LayerInfo

public class BaseView extends SurfaceView
    implements android.view.SurfaceHolder.Callback, jp.co.yahoo.android.maps.viewlayer.tile.TileBaseLayer.TileBaseLayerListener
{
    class BaseViewThread extends Thread
    {

        private static int $SWITCH_TABLE$jp$co$yahoo$android$maps$MapCtrlEvent$EventType[];
        public static final int STATUS_BASEVIEW_THRED_PO = 2;
        public static final int STATUS_BASEVIEW_THRED_RUN = 1;
        private Handler mHandler;
        private boolean mReDraw;
        private int oldZoomLevel;
        final BaseView this$0;

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

        private void doDraw(Canvas canvas)
        {
            if (!mMapView.isMapVisible())
            {
                canvas.save();
                canvas.drawColor(0, android.graphics.PorterDuff.Mode.CLEAR);
                canvas.restore();
                return;
            }
            List list = mLayerList;
            list;
            JVM INSTR monitorenter ;
            int i = 0;
_L2:
            if (i < mLayerList.size())
            {
                break MISSING_BLOCK_LABEL_69;
            }
            list;
            JVM INSTR monitorexit ;
            return;
            Exception exception;
            exception;
            list;
            JVM INSTR monitorexit ;
            throw exception;
            ((ViewLayer)mLayerList.get(i)).onDraw(canvas, mBaseViewCtrl.getMapView());
            i++;
            if (true) goto _L2; else goto _L1
_L1:
        }

        private void printEventCount(Map map)
        {
            if (map != null)
            {
                String s = "";
                Iterator iterator = map.keySet().iterator();
                do
                {
                    if (!iterator.hasNext())
                    {
                        continue;
                    }
                    jp.co.yahoo.android.maps.MapCtrlEvent.EventType eventtype = (jp.co.yahoo.android.maps.MapCtrlEvent.EventType)iterator.next();
                    Integer integer = (Integer)map.get(eventtype);
                    switch ($SWITCH_TABLE$jp$co$yahoo$android$maps$MapCtrlEvent$EventType()[eventtype.ordinal()])
                    {
                    case 1: // '\001'
                        s = (new StringBuilder(String.valueOf(s))).append(" KEY_UP:").append(integer.toString()).toString();
                        break;

                    case 2: // '\002'
                        s = (new StringBuilder(String.valueOf(s))).append(" KEY_DOWN:").append(integer.toString()).toString();
                        break;

                    case 3: // '\003'
                        s = (new StringBuilder(String.valueOf(s))).append(" ON_TOUCH:").append(integer.toString()).toString();
                        break;

                    case 4: // '\004'
                        s = (new StringBuilder(String.valueOf(s))).append(" ON_TRACKBALLEVENT:").append(integer.toString()).toString();
                        break;

                    case 5: // '\005'
                        s = (new StringBuilder(String.valueOf(s))).append(" ON_SINGLETAPCONFIRMED:").append(integer.toString()).toString();
                        break;

                    case 6: // '\006'
                        s = (new StringBuilder(String.valueOf(s))).append(" ON_TAP:").append(integer.toString()).toString();
                        break;

                    case 9: // '\t'
                        s = (new StringBuilder(String.valueOf(s))).append(" ON_TIMER:").append(integer.toString()).toString();
                        break;

                    case 10: // '\n'
                        s = (new StringBuilder(String.valueOf(s))).append(" ON_SIZECHANGED:").append(integer.toString()).toString();
                        break;

                    case 11: // '\013'
                        s = (new StringBuilder(String.valueOf(s))).append(" SET_FACTOR:").append(integer.toString()).toString();
                        break;

                    case 13: // '\r'
                        s = (new StringBuilder(String.valueOf(s))).append(" SET_CENTER:").append(integer.toString()).toString();
                        break;

                    case 14: // '\016'
                        s = (new StringBuilder(String.valueOf(s))).append(" SET_ZOOM:").append(integer.toString()).toString();
                        break;

                    case 15: // '\017'
                        s = (new StringBuilder(String.valueOf(s))).append(" SET_MOVE:").append(integer.toString()).toString();
                        break;

                    case 17: // '\021'
                        s = (new StringBuilder(String.valueOf(s))).append(" SET_COPYRIGHT:").append(integer.toString()).toString();
                        break;

                    case 21: // '\025'
                        s = (new StringBuilder(String.valueOf(s))).append(" RESET_MAP:").append(integer.toString()).toString();
                        break;

                    case 23: // '\027'
                        s = (new StringBuilder(String.valueOf(s))).append(" EVENT_MAX:").append(integer.toString()).toString();
                        break;
                    }
                } while (true);
            }
            break MISSING_BLOCK_LABEL_638;
            while (true) 
            {
                return;
            }
        }

        public void reDraw()
        {
            mReDraw = true;
            restart();
        }

        public void restart()
        {
            Object obj = lock;
            obj;
            JVM INSTR monitorenter ;
            if (getState() != Thread.State.NEW)
            {
                break MISSING_BLOCK_LABEL_27;
            }
            start();
_L2:
            return;
            lock.notify();
            if (true) goto _L2; else goto _L1
_L1:
            Exception exception;
            exception;
            obj;
            JVM INSTR monitorexit ;
            throw exception;
        }

        public void run()
        {
            long l = System.currentTimeMillis();
_L9:
            Object obj = lock;
            obj;
            JVM INSTR monitorenter ;
            boolean flag1;
            mWkEvents.clear();
            mEventsPool.pollReadyEvents(mWkEvents);
            if (mWkEvents.size() != 0)
            {
                break MISSING_BLOCK_LABEL_75;
            }
            flag1 = mReDraw;
            if (flag1)
            {
                break MISSING_BLOCK_LABEL_75;
            }
            lock.wait();
_L1:
            obj;
            JVM INSTR monitorexit ;
            boolean flag;
            int i;
            flag = false;
            i = 0;
_L6:
            Canvas canvas;
            if (i < mWkEvents.size())
            {
                break MISSING_BLOCK_LABEL_306;
            }
            if (!mReDraw)
            {
                break MISSING_BLOCK_LABEL_173;
            }
            mReDraw = false;
            canvas = null;
            canvas = mHolder.lockCanvas(null);
            if (canvas == null)
            {
                break MISSING_BLOCK_LABEL_154;
            }
            synchronized (mHolder)
            {
                doDraw(canvas);
            }
            if (canvas != null)
            {
                mHolder.unlockCanvasAndPost(canvas);
            }
            int k = getMapLayerManager().getNowZoomLevel();
            if (oldZoomLevel != -99 && oldZoomLevel != k && m_mapViewListenerList.size() > 0)
            {
                mHandler.post(new Runnable() {

                    final BaseViewThread this$1;

                    public void run()
                    {
                        Iterator iterator = m_mapViewListenerList.iterator();
                        do
                        {
                            MapViewListener mapviewlistener;
                            do
                            {
                                if (!iterator.hasNext())
                                {
                                    return;
                                }
                                mapviewlistener = (MapViewListener)iterator.next();
                            } while (mapviewlistener == null);
                            mapviewlistener.onChangeScale(mMapView);
                        } while (true);
                    }

            
            {
                this$1 = BaseViewThread.this;
                super();
            }
                });
            }
            oldZoomLevel = k;
            long l1 = System.currentTimeMillis() - l;
            Exception exception;
            MapCtrlEvent mapctrlevent;
            List list;
            int j;
            Exception exception1;
            long l2;
            Exception exception2;
            InterruptedException interruptedexception1;
            if (16L - l1 > 0L)
            {
                l2 = 16L - l1;
            } else
            {
                l2 = 0L;
            }
            if (l2 > 0L)
            {
                try
                {
                    Thread.sleep(l2);
                }
                catch (InterruptedException interruptedexception)
                {
                    interruptedexception.printStackTrace();
                }
            }
            continue; /* Loop/switch isn't completed */
            interruptedexception1;
            interruptedexception1.printStackTrace();
              goto _L1
            exception;
            obj;
            JVM INSTR monitorexit ;
            throw exception;
            mapctrlevent = (MapCtrlEvent)mWkEvents.get(i);
            if (mapctrlevent.getEventType() != jp.co.yahoo.android.maps.MapCtrlEvent.EventType.ON_TIMER) goto _L3; else goto _L2
_L2:
            if (!flag) goto _L5; else goto _L4
_L4:
            i++;
              goto _L6
_L5:
            flag = true;
_L3:
            list = mLayerList;
            list;
            JVM INSTR monitorenter ;
            j = 0;
_L7:
            if (j < mLayerList.size())
            {
                break MISSING_BLOCK_LABEL_414;
            }
            if (mapctrlevent.getEventType() == jp.co.yahoo.android.maps.MapCtrlEvent.EventType.ON_TOUCH)
            {
                mapctrlevent.motionevent.recycle();
            }
            list;
            JVM INSTR monitorexit ;
            if (mapctrlevent.getEventType() == jp.co.yahoo.android.maps.MapCtrlEvent.EventType.ON_DESTROY)
            {
                return;
            }
            break MISSING_BLOCK_LABEL_457;
            ((ViewLayer)mLayerList.get(j)).doEvent(mapctrlevent, mBaseViewCtrl);
            j++;
              goto _L7
            exception1;
            list;
            JVM INSTR monitorexit ;
            throw exception1;
            mEventsPool.releaseFinishedEvent(mapctrlevent);
              goto _L4
            exception3;
            surfaceholder;
            JVM INSTR monitorexit ;
            throw exception3;
            exception2;
            if (canvas != null)
            {
                mHolder.unlockCanvasAndPost(canvas);
            }
            throw exception2;
            if (true) goto _L9; else goto _L8
_L8:
        }


        public BaseViewThread()
        {
            this$0 = BaseView.this;
            super("BaseViewThread");
            mReDraw = false;
            mHandler = new Handler();
            oldZoomLevel = -99;
        }
    }


    static final long FPS = 60L;
    static final long FRAME_TIME = 16L;
    private long adtime;
    private boolean isAddedMapViewListener;
    private Object lock;
    private BaseViewCtrl mBaseViewCtrl;
    private Context mContext;
    protected MapCtrlEventsPool mEventsPool;
    private Handler mHandler;
    private SurfaceHolder mHolder;
    private List mLayerList;
    private MapView mMapView;
    private BaseViewThread mThread;
    private Vector mWkEvents;
    private ArrayList m_mapViewListenerList;

    public BaseView(String s, Activity activity, MapView mapview)
    {
        super(activity);
        mEventsPool = new MapCtrlEventsPool();
        m_mapViewListenerList = new ArrayList();
        isAddedMapViewListener = false;
        mWkEvents = new Vector(100);
        mHandler = new Handler();
        adtime = 0L;
        lock = new Object();
        setFocusable(true);
        requestFocus();
        mContext = activity;
        mLayerList = new ArrayList();
        mBaseViewCtrl = new BaseViewCtrl(s, activity, mapview, this);
        mHolder = getHolder();
        mHolder.addCallback(this);
        mHolder.setFormat(-2);
        mMapView = mapview;
        mThread = new BaseViewThread();
    }

    public boolean addLayer(ViewLayer viewlayer)
    {
        return mLayerList.add(viewlayer);
    }

    public void addMapViewListener(MapViewListener mapviewlistener)
    {
        this;
        JVM INSTR monitorenter ;
        m_mapViewListenerList.add(mapviewlistener);
        if (!isAddedMapViewListener)
        {
            isAddedMapViewListener = true;
            ((TileBaseLayer)getLayerByName(MapViewCtrl.LAYER_TILE_NAME)).setTileBaseLayerListener(this);
        }
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public void doEvent(jp.co.yahoo.android.maps.MapCtrlEvent.EventType eventtype, double d, double d1)
    {
        mEventsPool.push(eventtype, d, d1);
        restartThread();
    }

    public void doEvent(jp.co.yahoo.android.maps.MapCtrlEvent.EventType eventtype, double d, double d1, float f)
    {
        mEventsPool.push(eventtype, d, d1, f);
        restartThread();
    }

    public void doEvent(jp.co.yahoo.android.maps.MapCtrlEvent.EventType eventtype, int i, KeyEvent keyevent)
    {
        mEventsPool.push(eventtype, i, keyevent);
        restartThread();
    }

    public void doEvent(jp.co.yahoo.android.maps.MapCtrlEvent.EventType eventtype, MotionEvent motionevent)
    {
        mEventsPool.push(eventtype, motionevent);
        restartThread();
    }

    public void doEvent(jp.co.yahoo.android.maps.MapCtrlEvent.EventType eventtype, Object obj)
    {
        mEventsPool.push(eventtype, obj);
        restartThread();
    }

    public boolean doKeyDown(int i, KeyEvent keyevent)
    {
        mEventsPool.push(jp.co.yahoo.android.maps.MapCtrlEvent.EventType.KEY_DOWN, i, keyevent);
        restartThread();
        return true;
    }

    public boolean doKeyUp(int i, KeyEvent keyevent)
    {
        mEventsPool.push(jp.co.yahoo.android.maps.MapCtrlEvent.EventType.KEY_UP, i, keyevent);
        restartThread();
        return true;
    }

    public boolean doTap(GeoPoint geopoint)
    {
        mEventsPool.push(jp.co.yahoo.android.maps.MapCtrlEvent.EventType.ON_TAP, geopoint);
        restartThread();
        return true;
    }

    public boolean doTouch(MotionEvent motionevent)
    {
        mEventsPool.push(jp.co.yahoo.android.maps.MapCtrlEvent.EventType.ON_TOUCH, MotionEvent.obtain(motionevent));
        restartThread();
        return true;
    }

    public boolean doTrackballEvent(MotionEvent motionevent)
    {
        mEventsPool.push(jp.co.yahoo.android.maps.MapCtrlEvent.EventType.ON_TRACKBALLEVENT, motionevent);
        restartThread();
        return true;
    }

    public GeoPoint fromPixels(int i, int j)
    {
        return mBaseViewCtrl.fromPixels(i, j);
    }

    public BaseViewCtrl getBaseViewCtrl()
    {
        return mBaseViewCtrl;
    }

    public Coordinate getCenterPos()
    {
        return mBaseViewCtrl.getCenterPos();
    }

    public float getFactor()
    {
        return mBaseViewCtrl.getFactor();
    }

    public IndoormapOverlay getIndoormapOverlay()
    {
        return mMapView.getIndoormapOverlay();
    }

    public ViewLayer getLayerByName(String s)
    {
        int i = 0;
_L6:
        if (i < mLayerList.size()) goto _L2; else goto _L1
_L1:
        ViewLayer viewlayer = null;
_L4:
        return viewlayer;
_L2:
        viewlayer = (ViewLayer)mLayerList.get(i);
        if (viewlayer.getName() == s) goto _L4; else goto _L3
_L3:
        i++;
        if (true) goto _L6; else goto _L5
_L5:
    }

    public MapController getMapController()
    {
        return mBaseViewCtrl;
    }

    public MapLayerManager getMapLayerManager()
    {
        return mBaseViewCtrl.getMapLayerManager();
    }

    public int getMaximumScaleZoomLevel(int i, boolean flag)
    {
        return mBaseViewCtrl.getMaximumScaleZoomLevel(i, flag);
    }

    public LayerInfo getNowLayer()
    {
        return mBaseViewCtrl.getNowLayer();
    }

    public Projection getProjection()
    {
        return mBaseViewCtrl.getProjection();
    }

    public boolean isVisibleIndoormap()
    {
        return mMapView.isVisibleIndoormap();
    }

    public boolean onChangeMap()
    {
        this;
        JVM INSTR monitorenter ;
        if (m_mapViewListenerList.size() > 0)
        {
            mHandler.post(new Runnable() {

                final BaseView this$0;

                public void run()
                {
                    Iterator iterator = m_mapViewListenerList.iterator();
                    do
                    {
                        MapViewListener mapviewlistener;
                        do
                        {
                            if (!iterator.hasNext())
                            {
                                return;
                            }
                            mapviewlistener = (MapViewListener)iterator.next();
                        } while (mapviewlistener == null);
                        mapviewlistener.onChangeMap(mMapView);
                    } while (true);
                }

            
            {
                this$0 = BaseView.this;
                super();
            }
            });
        }
        this;
        JVM INSTR monitorexit ;
        return false;
        Exception exception;
        exception;
        throw exception;
    }

    public boolean onFinishAnimation()
    {
        mMapView.forceRedrawIndoormapOverlay();
        return false;
    }

    public boolean onSendAd()
    {
        this;
        JVM INSTR monitorenter ;
label0:
        {
            long l = System.currentTimeMillis();
            if (l > 5000L + adtime || adtime == 0L)
            {
                if (m_mapViewListenerList.size() <= 0)
                {
                    break label0;
                }
                mHandler.post(new Runnable() {

                    final BaseView this$0;

                    public void run()
                    {
                        Iterator iterator = m_mapViewListenerList.iterator();
                        do
                        {
                            MapViewListener mapviewlistener;
                            do
                            {
                                if (!iterator.hasNext())
                                {
                                    return;
                                }
                                mapviewlistener = (MapViewListener)iterator.next();
                            } while (mapviewlistener == null);
                            mapviewlistener.onSendAd();
                        } while (true);
                    }

            
            {
                this$0 = BaseView.this;
                super();
            }
                });
            }
            adtime = l;
        }
        this;
        JVM INSTR monitorexit ;
        return false;
        Exception exception;
        exception;
        throw exception;
    }

    public void reDraw()
    {
        mThread.reDraw();
    }

    public void removeAllMapViewListener()
    {
        m_mapViewListenerList.clear();
    }

    public boolean removeLayer(ViewLayer viewlayer)
    {
        return mLayerList.remove(viewlayer);
    }

    public void removeMapViewListener(MapViewListener mapviewlistener)
    {
        m_mapViewListenerList.remove(mapviewlistener);
    }

    public void restartThread()
    {
        if (mThread != null)
        {
            mThread.restart();
        }
    }

    public final void setHeight(int i)
    {
        mBaseViewCtrl.setHeight(i);
    }

    public void setMinimumZoomLevel(int i)
    {
        mBaseViewCtrl.setMinimumZoomLevel(i);
    }

    public void setWidth(int i)
    {
        mBaseViewCtrl.setWidth(i);
    }

    public void surfaceChanged(SurfaceHolder surfaceholder, int i, int j, int k)
    {
    }

    public void surfaceCreated(SurfaceHolder surfaceholder)
    {
        mThread.restart();
        reDraw();
        if (adtime == 0L)
        {
            onSendAd();
        }
    }

    public void surfaceDestroyed(SurfaceHolder surfaceholder)
    {
    }







}

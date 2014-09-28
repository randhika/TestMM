// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.maps.viewlayer.tile;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.MotionEvent;
import java.util.List;
import java.util.Vector;
import jp.co.yahoo.android.maps.DoublePoint;
import jp.co.yahoo.android.maps.GeoPoint;
import jp.co.yahoo.android.maps.MapCtrlEvent;
import jp.co.yahoo.android.maps.MapView;
import jp.co.yahoo.android.maps.indoormap.IndoormapOverlay;
import jp.co.yahoo.android.maps.viewlayer.BaseView;
import jp.co.yahoo.android.maps.viewlayer.BaseViewCtrl;
import jp.co.yahoo.android.maps.viewlayer.LayerInfo;
import jp.co.yahoo.android.maps.viewlayer.MapLayerManager;
import jp.co.yahoo.android.maps.viewlayer.MapYml;
import jp.co.yahoo.android.maps.viewlayer.ScaleUtils;
import jp.co.yahoo.android.maps.viewlayer.ViewLayer;
import jp.co.yahoo.android.maps.viewlayer.overlay.OverlayList;

// Referenced classes of package jp.co.yahoo.android.maps.viewlayer.tile:
//            TileManager, TileRequestManager, MapAnimation, Tile, 
//            TileRequest

public class TileBaseLayer extends ViewLayer
    implements TileManager.TileManagerListener, TileRequestManager.TileRequestManagerListener
{
    public static interface TileBaseLayerListener
    {

        public abstract boolean onChangeMap();

        public abstract boolean onFinishAnimation();
    }

    public class ZoomParam
    {

        private boolean mCanceled;
        private int mCount;
        private GeoPoint mGeoPoint;
        private boolean mJumpToFinish;
        private int mMaxCount;
        private int mNextLevel;
        private DoublePoint mPos;
        private float mfactor;
        final TileBaseLayer this$0;

        public void cancelMove(boolean flag)
        {
            mCanceled = true;
            mJumpToFinish = flag;
        }

        public boolean countUp()
        {
            if (mMaxCount <= mCount)
            {
                return false;
            } else
            {
                mCount = 1 + mCount;
                return true;
            }
        }

        public float getFactor()
        {
            return mfactor;
        }

        public GeoPoint getGeoPoint()
        {
            return mGeoPoint;
        }

        public DoublePoint getMovePos()
        {
            return new DoublePoint((mPos.x - (double)(mBaseViewCtrl.getWidth() / 2)) / (double)mMaxCount, (mPos.y - (double)(mBaseViewCtrl.getHeight() / 2)) / (double)mMaxCount);
        }

        public int getNextLevel()
        {
            return mNextLevel;
        }

        public float getNowFactor()
        {
            return 1.0F + mfactor * (float)mCount;
        }

        public DoublePoint getPos()
        {
            return mPos;
        }



        public ZoomParam(float f, int i, int j, DoublePoint doublepoint, GeoPoint geopoint)
        {
            this$0 = TileBaseLayer.this;
            super();
            mfactor = f / (float)i;
            mMaxCount = i;
            mCount = 0;
            mNextLevel = j;
            mPos = doublepoint;
            mGeoPoint = geopoint;
            mCanceled = false;
            mJumpToFinish = false;
        }
    }


    private static int $SWITCH_TABLE$jp$co$yahoo$android$maps$MapCtrlEvent$EventType[];
    private static int ST_AUTOZOOM = 4;
    private static int ST_FLICK = 1;
    private static int ST_IDOL = 0;
    private static int ST_ZOOM = 2;
    private boolean double_tap_flag;
    private BaseView mBaseView;
    private BaseViewCtrl mBaseViewCtrl;
    private DoublePoint mMovePos;
    public List mOverlayList;
    private int mState;
    private TileBaseLayerListener mTileBaseLayerListener;
    private TileManager mTileManager;
    private TileRequestManager mTileRequestManager;
    private Bitmap mWhiteBitmap;
    private ZoomParam mZoomParam;
    private MapView maptestView;

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

    public TileBaseLayer(BaseView baseview)
    {
        mTileManager = null;
        mTileRequestManager = null;
        mBaseViewCtrl = null;
        mMovePos = new DoublePoint();
        mZoomParam = null;
        maptestView = null;
        mState = ST_IDOL;
        mOverlayList = null;
        mTileBaseLayerListener = null;
        double_tap_flag = false;
        mWhiteBitmap = createWhiteBitmap(baseview.getBaseViewCtrl().getTileSize(), baseview.getBaseViewCtrl().getTileSize());
        mBaseView = baseview;
        mBaseViewCtrl = baseview.getBaseViewCtrl();
        mTileManager = new TileManager(mBaseViewCtrl, this);
        mTileRequestManager = new TileRequestManager(mBaseViewCtrl, this);
        Context context = mBaseViewCtrl.getContext();
        String s = context.getPackageName();
        int i = context.getResources().getIdentifier("back", "drawable", s);
        try
        {
            Resources resources = context.getResources();
            mTileManager.setMaploadImage(BitmapFactory.decodeResource(resources, i));
        }
        catch (IllegalArgumentException illegalargumentexception) { }
        mBaseView.reDraw();
    }

    private static Bitmap createWhiteBitmap(int i, int j)
    {
        Bitmap bitmap = Bitmap.createBitmap(i, j, android.graphics.Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint();
        paint.setColor(-1);
        canvas.drawRect(0.0F, 0.0F, i, j, paint);
        return bitmap;
    }

    private void fireFinishAnimation()
    {
        if (mTileBaseLayerListener != null)
        {
            mTileBaseLayerListener.onFinishAnimation();
        }
    }

    private boolean isVisibleIndoormapOverlay()
    {
        int i = mBaseViewCtrl.getMapView().getZoomLevel();
        if (i <= ScaleUtils.getIndoormapMinimumScaleZoomLevel() && i >= ScaleUtils.getIndoormapMaximumScaleZoomLevel())
        {
            IndoormapOverlay indoormapoverlay = mBaseView.getIndoormapOverlay();
            if (indoormapoverlay != null && mBaseView.isVisibleIndoormap() && !indoormapoverlay.empty())
            {
                return true;
            }
        }
        return false;
    }

    public void autoMove(int i, GeoPoint geopoint, DoublePoint doublepoint)
    {
        if (mBaseViewCtrl.getWidth() == 0 || mBaseViewCtrl.getHeight() == 0)
        {
            mBaseView.doEvent(jp.co.yahoo.android.maps.MapCtrlEvent.EventType.SET_CENTER, geopoint);
            return;
        }
        DoublePoint doublepoint1 = doublepoint;
        if (doublepoint1 == null)
        {
            Point point = mBaseViewCtrl.toPixels(geopoint, null);
            doublepoint1 = new DoublePoint(point.x, point.y);
        }
        mBaseViewCtrl.setFactor(1.0F);
        mZoomParam = new ZoomParam(mBaseViewCtrl.getFactorToNowScale(i) - 1.0F, 10, i, doublepoint1, geopoint);
        mState = mState | ST_AUTOZOOM;
        mBaseView.doEvent(jp.co.yahoo.android.maps.MapCtrlEvent.EventType.ON_TIMER, null);
    }

    public void autoZoom(int i, DoublePoint doublepoint)
    {
        mBaseViewCtrl.setFactor(1.0F);
        float f = mBaseViewCtrl.getFactorToNowScale(i);
        GeoPoint geopoint = mBaseViewCtrl.fromPixels((int)doublepoint.x, (int)doublepoint.y);
        mZoomParam = new ZoomParam(f - 1.0F, 10, i, doublepoint, geopoint);
        mState = mState | ST_AUTOZOOM;
        mTileManager.createBackImage();
        mBaseView.doEvent(jp.co.yahoo.android.maps.MapCtrlEvent.EventType.ON_TIMER, null);
    }

    public void cancelMove(boolean flag)
    {
        if (mZoomParam != null)
        {
            mZoomParam.cancelMove(flag);
        }
    }

    public void doEvent(MapCtrlEvent mapctrlevent, BaseViewCtrl baseviewctrl)
    {
        $SWITCH_TABLE$jp$co$yahoo$android$maps$MapCtrlEvent$EventType()[mapctrlevent.getEventType().ordinal()];
        JVM INSTR tableswitch 1 22: default 112
    //                   1 112
    //                   2 112
    //                   3 754
    //                   4 112
    //                   5 112
    //                   6 112
    //                   7 1247
    //                   8 1168
    //                   9 113
    //                   10 1038
    //                   11 887
    //                   12 954
    //                   13 1099
    //                   14 867
    //                   15 798
    //                   16 1115
    //                   17 1354
    //                   18 112
    //                   19 1303
    //                   20 1453
    //                   21 1012
    //                   22 1458;
           goto _L1 _L1 _L1 _L2 _L1 _L1 _L1 _L3 _L4 _L5 _L6 _L7 _L8 _L9 _L10 _L11 _L12 _L13 _L1 _L14 _L15 _L16 _L17
_L1:
        break; /* Loop/switch isn't completed */
_L17:
        break MISSING_BLOCK_LABEL_1458;
_L18:
        return;
_L5:
        if ((mState & ST_FLICK) == ST_FLICK)
        {
            double d = mMovePos.x / 1.0900000000000001D;
            double d1 = mMovePos.y / 1.0900000000000001D;
            mMovePos.x = d;
            mMovePos.y = d1;
            if (baseviewctrl.move(d, d1))
            {
                mTileManager.changeTilePos(d, d1);
            } else
            {
                mTileManager.changeTilePos(d, 0.0D);
            }
            if (Math.abs(mMovePos.y) < 3D && Math.abs(mMovePos.x) < 3D)
            {
                mState = mState ^ ST_FLICK;
                mBaseView.doEvent(jp.co.yahoo.android.maps.MapCtrlEvent.EventType.UPDATE_YML, null);
            } else
            {
                mBaseView.doEvent(jp.co.yahoo.android.maps.MapCtrlEvent.EventType.ON_TIMER, null);
            }
            mBaseView.reDraw();
        }
        if ((mState & ST_AUTOZOOM) == ST_AUTOZOOM)
        {
            if (mZoomParam != null)
            {
                boolean flag = false;
                if (mZoomParam.mCanceled)
                {
                    if (mZoomParam.mJumpToFinish)
                    {
                        mBaseViewCtrl.setCenter(mZoomParam.getGeoPoint());
                        resetMap();
                    }
                    mBaseView.doEvent(jp.co.yahoo.android.maps.MapCtrlEvent.EventType.UPDATE_YML, null);
                    mBaseView.reDraw();
                    mState = mState ^ ST_AUTOZOOM;
                    mZoomParam = null;
                } else
                if (!mZoomParam.countUp())
                {
                    flag = true;
                    if (mZoomParam.getFactor() == 0.0F)
                    {
                        mTileManager.setBackImagePos((int)mZoomParam.getPos().x, (int)mZoomParam.getPos().y, mZoomParam.getNowFactor());
                    } else
                    {
                        baseviewctrl.setFactor(mZoomParam.getNowFactor());
                        DoublePoint doublepoint2 = mZoomParam.getMovePos();
                        baseviewctrl.move(doublepoint2.getX(), doublepoint2.getY());
                        mTileManager.setBackImagePos((int)mZoomParam.getPos().x, (int)mZoomParam.getPos().y, mZoomParam.getNowFactor());
                        mBaseViewCtrl.setZoom(mZoomParam.getNextLevel());
                        if (mZoomParam.getGeoPoint() != null)
                        {
                            mBaseViewCtrl.setCenterNoReset(mZoomParam.getGeoPoint());
                        }
                        resetMap();
                    }
                    mBaseView.doEvent(jp.co.yahoo.android.maps.MapCtrlEvent.EventType.UPDATE_YML, null);
                    mBaseView.reDraw();
                    mState = mState ^ ST_AUTOZOOM;
                    mZoomParam = null;
                    double_tap_flag = false;
                } else
                {
                    baseviewctrl.setFactor(mZoomParam.getNowFactor());
                    mTileManager.setBackImagePos((int)mZoomParam.getPos().x, (int)mZoomParam.getPos().y, mZoomParam.getNowFactor());
                    DoublePoint doublepoint1 = mZoomParam.getMovePos();
                    baseviewctrl.move(doublepoint1.getX(), doublepoint1.getY());
                    if (!double_tap_flag)
                    {
                        mTileManager.changeTilePos(doublepoint1.getX(), doublepoint1.getY());
                    }
                    mBaseView.doEvent(jp.co.yahoo.android.maps.MapCtrlEvent.EventType.ON_TIMER, null);
                    flag = false;
                }
                mBaseView.reDraw();
                if (flag)
                {
                    fireFinishAnimation();
                    return;
                }
            } else
            {
                mState = mState ^ ST_AUTOZOOM;
                mBaseView.reDraw();
                return;
            }
        }
        continue; /* Loop/switch isn't completed */
_L2:
        switch (0xff & mapctrlevent.motionevent.getAction())
        {
        default:
            return;

        case 0: // '\0'
            mState = mState ^ ST_FLICK;
            break;
        }
        return;
_L11:
        if ((mState & ST_ZOOM) != ST_ZOOM)
        {
            if (baseviewctrl.move(mapctrlevent.point_x, mapctrlevent.point_y))
            {
                mTileManager.changeTilePos(mapctrlevent.point_x, mapctrlevent.point_y);
            } else
            {
                mTileManager.changeTilePos(mapctrlevent.point_x, 0.0D);
            }
            mBaseView.reDraw();
            return;
        }
        if (true) goto _L18; else goto _L10
_L10:
        baseviewctrl.setZoom(((Integer)mapctrlevent.arg1).intValue());
        resetMap();
        return;
_L7:
        baseviewctrl.setFactor(mapctrlevent.factor);
        if ((mState & ST_ZOOM) != ST_ZOOM)
        {
            mState = mState | ST_ZOOM;
            mTileManager.createBackImage();
        }
        mTileManager.setBackImagePos(mBaseViewCtrl.getWidth() / 2, mBaseViewCtrl.getHeight() / 2);
        return;
_L8:
        if ((mState & ST_ZOOM) == ST_ZOOM)
        {
            float f = baseviewctrl.getFactor();
            zoomForFactor(mBaseViewCtrl.getWidth() / 2, mBaseViewCtrl.getHeight() / 2, null, f);
            mState = mState ^ ST_ZOOM;
            return;
        }
        if (true)
        {
            continue; /* Loop/switch isn't completed */
        }
_L16:
        baseviewctrl.resetCenter();
        mTileManager.clearTiles();
        mTileManager.initTiles();
        mBaseView.reDraw();
        return;
_L6:
        Rect rect = (Rect)mapctrlevent.arg1;
        baseviewctrl.setHeight(rect.bottom - rect.top);
        baseviewctrl.setWidth(rect.right - rect.left);
        mTileManager.clearTiles();
        mTileManager.initTiles();
        mBaseView.reDraw();
        return;
_L9:
        baseviewctrl.setCenter((GeoPoint)mapctrlevent.arg1);
        resetMap();
        return;
_L12:
        mState = mState | ST_FLICK;
        mMovePos.x = mapctrlevent.point_x;
        mMovePos.y = mapctrlevent.point_y;
        mBaseView.doEvent(jp.co.yahoo.android.maps.MapCtrlEvent.EventType.ON_TIMER, mapctrlevent.point_x, mapctrlevent.point_y);
        return;
_L4:
        if ((mState & ST_AUTOZOOM) != ST_AUTOZOOM)
        {
            baseviewctrl.setFactor(1.0F);
            mOverlayList = new OverlayList(mBaseViewCtrl.getMapView());
            LayerInfo layerinfo2 = baseviewctrl.getOneUpLayerInfo();
            if (layerinfo2 != null)
            {
                autoZoom(layerinfo2.level, new DoublePoint(baseviewctrl.getWidth() / 2, baseviewctrl.getHeight() / 2));
                return;
            }
        }
        continue; /* Loop/switch isn't completed */
_L3:
        if ((mState & ST_AUTOZOOM) != ST_AUTOZOOM)
        {
            double_tap_flag = true;
            DoublePoint doublepoint = (DoublePoint)mapctrlevent.arg1;
            baseviewctrl.setFactor(1.0F);
            LayerInfo layerinfo1 = baseviewctrl.getOneDownLayerInfo();
            if (layerinfo1 != null)
            {
                autoZoom(layerinfo1.level, doublepoint);
                return;
            }
        }
        continue; /* Loop/switch isn't completed */
_L14:
        if ((mState & ST_AUTOZOOM) != ST_AUTOZOOM)
        {
            mTileManager.clearBackImage();
            MapAnimation mapanimation = (MapAnimation)mapctrlevent.arg1;
            baseviewctrl.setFactor(1.0F);
            autoMove(mapanimation.getToZLevel(), mapanimation.getToGeoPoint(), null);
            return;
        }
        if (true) goto _L18; else goto _L13
_L13:
        MapLayerManager maplayermanager = baseviewctrl.getMapLayerManager();
        LayerInfo layerinfo = baseviewctrl.getNowLayer();
        MapYml mapyml = (MapYml)mapctrlevent.arg1;
        String s = "off";
        if (!isVisibleIndoormapOverlay() && !mapyml.checkTile(maplayermanager.getNowMapType(), layerinfo.scale))
        {
            if (maplayermanager.getNowMapType() == MapView.MapTypeStandard)
            {
                s = "gws";
            } else
            if (maplayermanager.getNowMapType() == MapView.MapTypeSatellite)
            {
                s = "gws_aero";
            }
        }
        if (mTileManager.setSeamless(s))
        {
            resetMap();
            return;
        }
        if (true) goto _L18; else goto _L15
_L15:
        setMapViewUpdate();
        return;
        mTileRequestManager.stopThreadAll();
        if (mWhiteBitmap != null)
        {
            mWhiteBitmap.recycle();
            mWhiteBitmap = null;
        }
        mTileManager.clearTiles();
        mTileManager.clearBackImage();
        return;
    }

    public void draw(Canvas canvas, MapView mapview, boolean flag)
    {
        canvas.drawColor(Color.rgb(192, 191, 187));
        maptestView = mapview;
        if (!mTileManager.draw(canvas, mapview, flag))
        {
            mBaseView.reDraw();
        }
    }

    public void endLoadImage(Bitmap bitmap, TileRequest tilerequest)
    {
        mTileManager.setImage(tilerequest, bitmap);
        mBaseView.reDraw();
    }

    protected void finalize()
        throws Throwable
    {
        try
        {
            if (mWhiteBitmap != null)
            {
                mWhiteBitmap.recycle();
                mWhiteBitmap = null;
            }
            mTileManager.clearTiles();
            mTileManager.clearBackImage();
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
        }
        super.finalize();
    }

    public TileManager getTileManager()
    {
        return mTileManager;
    }

    public void removeTile(Tile tile)
    {
    }

    public void requestNewTiles(Vector vector)
    {
        if (mBaseViewCtrl.getNowLayer().doReqMap != 0)
        {
            mTileRequestManager.addRequestTileList(vector);
            return;
        }
        int i = 0;
        do
        {
            if (i >= vector.size())
            {
                mBaseView.reDraw();
                return;
            }
            Tile tile = (Tile)vector.get(i);
            String s = (new StringBuilder(String.valueOf(tile.getTileX()))).append("-").append(tile.getTileY()).toString();
            TileRequest tilerequest = new TileRequest(tile.getTileX(), tile.getTileY(), tile.getTieZ(), tile.getMapType(), tile.getSize(), tile.getStyle(), tile.getIndoorLevel(), s, tile.getSeamless());
            mTileManager.setImage(tilerequest, mWhiteBitmap);
            i++;
        } while (true);
    }

    public void resetMap()
    {
        mTileRequestManager.removeReqest();
        mBaseViewCtrl.resetCenter();
        mTileManager.initTiles();
        mBaseView.doEvent(jp.co.yahoo.android.maps.MapCtrlEvent.EventType.UPDATE_YML, null);
        mBaseView.reDraw();
    }

    public void setMapViewUpdate()
    {
        if (mTileBaseLayerListener != null)
        {
            mTileBaseLayerListener.onChangeMap();
        }
    }

    public void setTileBaseLayerListener(TileBaseLayerListener tilebaselayerlistener)
    {
        mTileBaseLayerListener = tilebaselayerlistener;
    }

    public void zoomForFactor(int i, int j, GeoPoint geopoint, float f)
    {
        mBaseViewCtrl.fromPixels(i - mBaseViewCtrl.getWidth() / 2, j - mBaseViewCtrl.getHeight() / 2);
        if (f == 1.0F)
        {
            mBaseViewCtrl.setFactor(1.0F);
        } else
        {
            if (f > 1.0F)
            {
                LayerInfo layerinfo1 = mBaseViewCtrl.getFitScaleForFactor(2);
                mTileManager.setBackImagePos(i, j, mBaseViewCtrl.getFactorToNowScale(layerinfo1.level));
                mBaseViewCtrl.setZoom(layerinfo1.level);
                if (geopoint != null)
                {
                    mBaseViewCtrl.setCenterNoReset(geopoint);
                }
                resetMap();
                return;
            }
            if (f < 1.0F)
            {
                LayerInfo layerinfo = mBaseViewCtrl.getFitScaleForFactor(1);
                mTileManager.setBackImagePos(i, j, mBaseViewCtrl.getFactorToNowScale(layerinfo.level));
                mBaseViewCtrl.setZoom(layerinfo.level);
                if (geopoint != null)
                {
                    mBaseViewCtrl.setCenterNoReset(geopoint);
                }
                resetMap();
                return;
            }
        }
    }


}

// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.maps.viewlayer;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.os.Message;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import java.math.BigDecimal;
import java.util.Enumeration;
import java.util.List;
import java.util.Vector;
import jp.co.yahoo.android.maps.DoublePoint;
import jp.co.yahoo.android.maps.GeoPoint;
import jp.co.yahoo.android.maps.MapController;
import jp.co.yahoo.android.maps.MapView;
import jp.co.yahoo.android.maps.MapViewCtrl;
import jp.co.yahoo.android.maps.Projection;
import jp.co.yahoo.android.maps.viewlayer.tile.MapAnimation;
import jp.co.yahoo.android.maps.viewlayer.tile.Tile;
import jp.co.yahoo.android.maps.viewlayer.tile.TileBaseLayer;
import jp.co.yahoo.android.maps.viewlayer.tile.TileManager;

// Referenced classes of package jp.co.yahoo.android.maps.viewlayer:
//            Coordinate, MapLayerManager, LayerInfo, LLCalculation, 
//            BaseView, Attestation

public class BaseViewCtrl
    implements Projection, MapController, Attestation.AttestationListener
{

    private String mAppid;
    private Attestation mAttestation;
    private BaseView mBaseView;
    private Coordinate mCenterPos;
    private Context mContext;
    public float mFactor;
    private double mMagnification;
    private int mMapHeight;
    private MapLayerManager mMapLayerManager;
    private MapView mMapView;
    private int mMapWidth;
    private int mTileSize;
    private DoublePoint mTopLeft;
    private double tmp_pixceldistance;
    private int tmp_scale;

    public BaseViewCtrl(String s, Activity activity, MapView mapview, BaseView baseview)
    {
        mMapWidth = 0;
        mMapHeight = 0;
        mTopLeft = new DoublePoint(0.0D, 0.0D);
        mFactor = 1.0F;
        mCenterPos = new Coordinate();
        mAppid = null;
        mAttestation = null;
        mMapLayerManager = null;
        mTileSize = 256;
        tmp_scale = 0;
        tmp_pixceldistance = 0.0D;
        mMagnification = 1.0D;
        mContext = activity;
        mAppid = s;
        mMapView = mapview;
        mBaseView = baseview;
        DisplayMetrics displaymetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        if (displaymetrics.densityDpi < 181)
        {
            mMagnification = 1.0D;
        } else
        if (displaymetrics.densityDpi < 256)
        {
            mMagnification = 1.4140625D;
        } else
        {
            mMagnification = 2D;
        }
        mTileSize = (int)((double)mTileSize * mMagnification);
        mMapLayerManager = new MapLayerManager();
        mMapLayerManager.setMagnification(mMagnification);
        mMapLayerManager.setMapType(MapView.MapTypeStandard);
        mMapLayerManager.setZoomLevel(3);
        setCenterNoReset(new GeoPoint(0x2203739, 0x854203e));
    }

    private double getPixelDistance()
    {
        if (tmp_scale != getNowLayer().scale || tmp_scale == 0 || tmp_pixceldistance == 0.0D)
        {
            double d = mTopLeft.x + (double)(mMapWidth / 2);
            double d1 = mTopLeft.y + (double)(mMapHeight / 2);
            double d2 = getNowLayer().log2Lat(d, d1);
            double d3 = getNowLayer().log2Lon(d, d1);
            double d4 = 10D + mTopLeft.x + (double)(mMapWidth / 2);
            double d5 = mTopLeft.y + (double)(mMapHeight / 2);
            double d6 = LLCalculation.distance(d2, d3, getNowLayer().log2Lat(d4, d5), getNowLayer().log2Lon(d4, d5)) / 10D;
            if (d6 == 0.0D)
            {
                return tmp_pixceldistance;
            }
            tmp_pixceldistance = d6;
            tmp_scale = getNowLayer().scale;
        }
        return tmp_pixceldistance;
    }

    private void updateCenterPos()
    {
        double d = mTopLeft.x + (double)(mMapWidth / 2);
        double d1 = mTopLeft.y + (double)(mMapHeight / 2);
        mCenterPos.lat = mMapLayerManager.log2Lat(d, d1);
        mCenterPos.lon = mMapLayerManager.log2Lon(d, d1);
    }

    public void animateTo(GeoPoint geopoint)
    {
        MapAnimation mapanimation = new MapAnimation(new GeoPoint(geopoint.getLatitudeE6(), geopoint.getLongitudeE6()), mMapLayerManager.getNowZoomLevel());
        mBaseView.doEvent(jp.co.yahoo.android.maps.MapCtrlEvent.EventType.SET_ANIMETETO, mapanimation);
    }

    public void animateTo(GeoPoint geopoint, Message message)
    {
    }

    public void animateTo(GeoPoint geopoint, Runnable runnable)
    {
    }

    public boolean endAttestation(Attestation attestation)
    {
        mBaseView.doEvent(jp.co.yahoo.android.maps.MapCtrlEvent.EventType.SET_ATTESTATION, attestation);
        return false;
    }

    public GeoPoint fromPixels(int i, int j)
    {
        GeoPoint geopoint = new GeoPoint();
        double d = getNowLayer().log2Lat(mTopLeft.x + (double)i, mTopLeft.y + (double)j);
        double d1 = getNowLayer().log2Lon(mTopLeft.x + (double)i, mTopLeft.y + (double)j);
        double d2 = d * 1000000D;
        double d3 = d1 * 1000000D;
        int k = (int)Math.round(d2);
        int l = (int)Math.round(d3);
        geopoint.setLatitudeE6(k);
        geopoint.setLongitudeE6(l);
        return geopoint;
    }

    public String getAppid()
    {
        return mAppid;
    }

    public Attestation getAttestation()
    {
        return mAttestation;
    }

    public Coordinate getCenterPos()
    {
        return mMapLayerManager.log2LatLon(mTopLeft.x + (double)(mMapWidth / 2), mTopLeft.y + (double)(mMapHeight / 2));
    }

    public int[] getCenterScale()
    {
        return null;
    }

    public int getCenterWorldPixelX()
    {
        return (int)mTopLeft.x + mMapWidth / 2;
    }

    public int getCenterWorldPixelY()
    {
        return (int)mTopLeft.y + mMapHeight / 2;
    }

    public Context getContext()
    {
        return mContext;
    }

    public float getFactor()
    {
        return mFactor;
    }

    public float getFactorToNowScale(int i)
    {
        return mMapLayerManager.getFactorToNowScale(i);
    }

    public LayerInfo getFitScaleForFactor(int i)
    {
        return mMapLayerManager.getFitScaleForFactor(mFactor, i);
    }

    public final int getHeight()
    {
        return mMapHeight;
    }

    public int getLeftWorldPixel()
    {
        return (int)mTopLeft.x;
    }

    public double getMagnification()
    {
        return mMagnification;
    }

    public GeoPoint getMapCenter()
    {
        Coordinate coordinate = mMapLayerManager.log2LatLon(mTopLeft.x + (double)(mMapWidth / 2), mTopLeft.y + (double)(mMapHeight / 2));
        return new GeoPoint((int)(1000000D * coordinate.lat), (int)(1000000D * coordinate.lon));
    }

    public MapLayerManager getMapLayerManager()
    {
        return mMapLayerManager;
    }

    public String getMapType(String s)
    {
        LayerInfo layerinfo = getNowLayer();
        if (layerinfo != null)
        {
            return layerinfo.type;
        } else
        {
            return null;
        }
    }

    public MapView getMapView()
    {
        return mMapView;
    }

    public int getMaptype()
    {
        return mMapLayerManager.getNowMapType();
    }

    public int getMaximumScaleZoomLevel(int i, boolean flag)
    {
        return mMapLayerManager.getMaximumScaleZoomLevel(i, flag);
    }

    public LayerInfo getNowLayer()
    {
        return mMapLayerManager.getNowLayer();
    }

    public LayerInfo getOneDownLayerInfo()
    {
        return mMapLayerManager.getOneDownLayerInfo();
    }

    public LayerInfo getOneUpLayerInfo()
    {
        return mMapLayerManager.getOneUpLayerInfo();
    }

    public Projection getProjection()
    {
        return this;
    }

    public int[] getTileIdPos(int i, int j)
    {
        Enumeration enumeration = ((TileBaseLayer)mBaseView.getLayerByName(MapViewCtrl.LAYER_TILE_NAME)).getTileManager().getTiles().elements();
_L2:
        boolean flag = enumeration.hasMoreElements();
        int k = 0;
        int l = 0;
        int i1 = 0;
        int j1 = 0;
        if (flag)
        {
            Tile tile = (Tile)enumeration.nextElement();
            int k1 = (int)((double)tile.getX() - mTopLeft.x);
            int l1 = (int)((double)tile.getY() - mTopLeft.y);
            if (k1 > i || k1 + mTileSize < i || l1 > j || l1 + mTileSize < j)
            {
                continue; /* Loop/switch isn't completed */
            }
            i1 = tile.getTileX();
            j1 = tile.getTileY();
            k = i - (int)((double)tile.getX() - mTopLeft.x);
            l = j - (int)((double)tile.getY() - mTopLeft.y);
        }
        return (new int[] {
            i1, j1, mMapLayerManager.getNowLayer().level, k, l
        });
        if (true) goto _L2; else goto _L1
_L1:
    }

    public int getTileSize()
    {
        return mTileSize;
    }

    public DoublePoint getTopLeftPos()
    {
        return mTopLeft;
    }

    public Point getTopLeftWorldPixels()
    {
        Point point = new Point();
        point.x = (int)mTopLeft.x;
        point.y = (int)mTopLeft.y;
        return point;
    }

    public int getTopWorldPixel()
    {
        return (int)mTopLeft.y;
    }

    public final int getWidth()
    {
        return mMapWidth;
    }

    public void initCacheFile()
    {
    }

    public void initMapCtl()
    {
        initCacheFile();
    }

    public float metersToEquatorPixels(float f)
    {
        double d = getPixelDistance();
        if (d == 0.0D)
        {
            return 0.0F;
        } else
        {
            return f / (new BigDecimal(d)).setScale(2, 5).floatValue();
        }
    }

    public boolean move(double d, double d1)
    {
        boolean flag = true;
        LayerInfo layerinfo = mMapLayerManager.getNowLayer();
        if (d1 + mTopLeft.y < layerinfo.topRng && layerinfo.topRng != 0.0D && d1 < 0.0D || d1 + mTopLeft.y + (double)mMapHeight > layerinfo.bottomRng && layerinfo.bottomRng != 0.0D && d1 > 0.0D)
        {
            d1 = 0.0D;
            flag = false;
        }
        DoublePoint doublepoint = mTopLeft;
        doublepoint.x = d + doublepoint.x;
        DoublePoint doublepoint1 = mTopLeft;
        doublepoint1.y = d1 + doublepoint1.y;
        updateCenterPos();
        mBaseView.onSendAd();
        return flag;
    }

    public void onDestroy()
    {
    }

    public boolean onKey(View view, int i, KeyEvent keyevent)
    {
        return false;
    }

    public void onPause()
    {
    }

    public void resetCenter()
    {
        DoublePoint doublepoint = mMapLayerManager.latLon2Log(mCenterPos);
        mTopLeft.x = doublepoint.x - (double)mMapWidth / 2D;
        mTopLeft.y = doublepoint.y - (double)mMapHeight / 2D;
        mBaseView.onSendAd();
    }

    public void scrollBy(int i, int j)
    {
        mBaseView.doEvent(jp.co.yahoo.android.maps.MapCtrlEvent.EventType.SET_MOVE, i, j);
    }

    public void setCenter(GeoPoint geopoint)
    {
        mCenterPos.lat = geopoint.getLatitude();
        mCenterPos.lon = geopoint.getLongitude();
        resetCenter();
    }

    public void setCenterNoReset(GeoPoint geopoint)
    {
        mCenterPos.lat = geopoint.getLatitude();
        mCenterPos.lon = geopoint.getLongitude();
    }

    public void setFactor(float f)
    {
        mFactor = f;
    }

    public final void setHeight(int i)
    {
        mMapHeight = i;
    }

    public void setMapType(int i)
    {
        setMaptype(i, null, null, 0);
    }

    public void setMapType(int i, int j)
    {
        setMaptype(i, null, null, j);
    }

    public void setMaptype(int i, String s)
    {
        setMaptype(i, s, null, 0);
    }

    public void setMaptype(int i, String s, List list)
    {
        setMaptype(i, s, list, 0);
    }

    public void setMaptype(int i, String s, List list, int j)
    {
        String s1;
        if (s == null)
        {
            s1 = null;
        } else
        {
            s1 = s;
            if (list != null)
            {
                int k = 0;
                int l = 0;
                int i1 = 0;
                while (i1 < list.size()) 
                {
                    String s2 = (String)list.get(i1);
                    if (s2.indexOf("on:") != -1)
                    {
                        l = 0;
                        if (k == 0)
                        {
                            s1 = (new StringBuilder(String.valueOf(s1))).append("|").append(s2).toString();
                        } else
                        {
                            String as1[] = s2.split(":");
                            s1 = (new StringBuilder(String.valueOf(s1))).append(",").append(as1[1]).toString();
                        }
                        k++;
                    } else
                    if (s2.indexOf("off:") != -1)
                    {
                        if (l == 0)
                        {
                            s1 = (new StringBuilder(String.valueOf(s1))).append("|").append(s2).toString();
                        } else
                        {
                            String as[] = s2.split(":");
                            s1 = (new StringBuilder(String.valueOf(s1))).append(",").append(as[1]).toString();
                        }
                        l++;
                        k = 0;
                    }
                    i1++;
                }
            }
        }
        if (mMapLayerManager.setMapType(i, j, s1))
        {
            ((TileBaseLayer)mBaseView.getLayerByName(MapViewCtrl.LAYER_TILE_NAME)).resetMap();
        }
    }

    public void setMinimumZoomLevel(int i)
    {
        mMapLayerManager.setMinimumZoomLevel(i);
    }

    public final void setWidth(int i)
    {
        mMapWidth = i;
    }

    public int setZoom(int i)
    {
        mMapLayerManager.setZoomLevel(i);
        mFactor = 1.0F;
        return 0;
    }

    public void startAttestation()
    {
        mAttestation = new Attestation(mAppid);
        mAttestation.setMapTouchListener(this);
        mAttestation.start();
    }

    public boolean startZoomin()
    {
        return false;
    }

    public boolean startZoomin(int i, int j)
    {
        return startZoomin();
    }

    public void stopAnimation(boolean flag)
    {
        ((TileBaseLayer)mBaseView.getLayerByName(MapViewCtrl.LAYER_TILE_NAME)).cancelMove(flag);
    }

    public void stopPanning()
    {
    }

    public int toPixelXFromLL(double d, double d1)
    {
        double d2;
        if (mFactor != 1.0F)
        {
            double d3 = mMapWidth / 2;
            d2 = (d3 + (mMapLayerManager.latLon2X(d, d1) - mTopLeft.x - d3) * (double)mFactor) - (d3 - (double)(mMapWidth / 2)) * (double)(mFactor - 1.0F);
        } else
        {
            d2 = getNowLayer().latLon2X(d, d1) - mTopLeft.x;
        }
        return (int)Math.round(d2);
    }

    public int toPixelXFromWP(int i, int j)
    {
        double d = i;
        double d1;
        if (mFactor != 1.0F)
        {
            d1 = (d - mTopLeft.x - (double)(mMapWidth / 2)) * (double)mFactor + (double)(mMapWidth / 2);
        } else
        {
            d1 = d - mTopLeft.x;
        }
        return (int)(d1 + 0.5D);
    }

    public int toPixelYFromLL(double d, double d1)
    {
        double d2;
        if (mFactor != 1.0F)
        {
            double d3 = mMapHeight / 2;
            d2 = (d3 + (mMapLayerManager.latLon2X(d, d1) - mTopLeft.y - d3) * (double)mFactor) - (d3 - (double)(mMapHeight / 2)) * (double)(mFactor - 1.0F);
        } else
        {
            d2 = getNowLayer().latLon2Y(d, d1) - mTopLeft.y;
        }
        return (int)Math.round(d2);
    }

    public int toPixelYFromWP(int i, int j)
    {
        double d = j;
        double d1;
        if (mFactor != 1.0F)
        {
            d1 = (d - mTopLeft.y - (double)(mMapHeight / 2)) * (double)mFactor + (double)(mMapHeight / 2);
        } else
        {
            d1 = d - mTopLeft.y;
        }
        return (int)(d1 + 0.5D);
    }

    public Point toPixels(Point point, Point point1)
    {
        Point point2;
        double d;
        double d1;
        double d2;
        double d3;
        if (point1 != null)
        {
            point2 = point1;
        } else
        {
            point2 = new Point();
        }
        d = point.x;
        d1 = point.y;
        if (mFactor != 1.0F)
        {
            double d4 = d - mTopLeft.x;
            double d5 = d1 - mTopLeft.y;
            d2 = (d4 - (double)(mMapWidth / 2)) * (double)mFactor + (double)(mMapWidth / 2);
            d3 = (d5 - (double)(mMapHeight / 2)) * (double)mFactor + (double)(mMapHeight / 2);
        } else
        {
            d2 = d - mTopLeft.x;
            d3 = d1 - mTopLeft.y;
        }
        point2.x = (int)(d2 + 0.5D);
        point2.y = (int)(d3 + 0.5D);
        return point2;
    }

    public Point toPixels(GeoPoint geopoint, Point point)
    {
        if (point != null)
        {
            return null;
        }
        Coordinate coordinate = new Coordinate();
        coordinate.lat = (double)geopoint.getLatitudeE6() / 1000000D;
        coordinate.lon = (double)geopoint.getLongitudeE6() / 1000000D;
        Point point1 = new Point();
        double d;
        double d1;
        double d2;
        double d3;
        if (mFactor != 1.0F)
        {
            double d4 = mMapWidth / 2;
            double d5 = mMapHeight / 2;
            DoublePoint doublepoint1 = mMapLayerManager.latLon2Log(coordinate);
            double d6 = doublepoint1.x - mTopLeft.x;
            double d7 = doublepoint1.y - mTopLeft.y;
            d = (d4 + (d6 - d4) * (double)mFactor) - (d4 - (double)(mMapWidth / 2)) * (double)(mFactor - 1.0F);
            d1 = (d5 + (d7 - d5) * (double)mFactor) - (d5 - (double)(mMapHeight / 2)) * (double)(mFactor - 1.0F);
        } else
        {
            DoublePoint doublepoint = new DoublePoint();
            doublepoint.x = getNowLayer().latLon2X(coordinate.lat, coordinate.lon);
            doublepoint.y = getNowLayer().latLon2Y(coordinate.lat, coordinate.lon);
            d = doublepoint.x - mTopLeft.x;
            d1 = doublepoint.y - mTopLeft.y;
        }
        d2 = (new BigDecimal(String.valueOf(d))).setScale(0, 4).doubleValue();
        d3 = (new BigDecimal(String.valueOf(d1))).setScale(0, 4).doubleValue();
        point1.x = Double.valueOf(d2).intValue();
        point1.y = Double.valueOf(d3).intValue();
        return point1;
    }

    public int toWorldPixelX(double d, double d1)
    {
        return (int)(0.5D + mMapLayerManager.latLon2X(d, d1));
    }

    public int toWorldPixelY(double d, double d1)
    {
        return (int)(0.5D + mMapLayerManager.latLon2Y(d, d1));
    }

    public Point toWorldPixels(GeoPoint geopoint, Point point)
    {
        Point point1;
        Coordinate coordinate;
        DoublePoint doublepoint;
        if (point != null)
        {
            point1 = point;
        } else
        {
            point1 = new Point();
        }
        coordinate = new Coordinate();
        coordinate.lat = geopoint.getLatitude();
        coordinate.lon = geopoint.getLongitude();
        doublepoint = mMapLayerManager.latLon2Log(coordinate);
        doublepoint.x = 0.5D + doublepoint.x;
        point1.x = (int)doublepoint.x;
        doublepoint.y = 0.5D + doublepoint.y;
        point1.y = (int)doublepoint.y;
        return point1;
    }

    public boolean zoomIn()
    {
        return false;
    }

    public boolean zoomInFixing(int i, int j)
    {
        return false;
    }

    public boolean zoomOut()
    {
        return false;
    }

    public boolean zoomOutFixing(int i, int j)
    {
        return false;
    }

    public void zoomToSpan(int i, int j)
    {
    }
}

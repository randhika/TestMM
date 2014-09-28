// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.maps.viewlayer;

import jp.co.yahoo.android.maps.DoublePoint;
import jp.co.yahoo.android.maps.MapView;

// Referenced classes of package jp.co.yahoo.android.maps.viewlayer:
//            LayerInfo, Coordinate

public class MapLayerManager
{

    private static String mMode[] = {
        "map", "photo", "map-b1", "map", "metro-b1", "metro-b2", "metro-b3", "osm", "hybrid", "indoor-iks"
    };
    private LayerInfo mLayers[];
    private double mMagnification;
    private int mNowIndoorLevel;
    private int mNowLayerNo;
    private int mNowMapType;
    private String mNowStyle;
    private int minimumZoomLevel;

    public MapLayerManager()
    {
        mLayers = null;
        mNowLayerNo = 0;
        mNowMapType = MapView.MapTypeStandard;
        mNowStyle = null;
        mNowIndoorLevel = 0;
        mMagnification = 1.0D;
        int i = MapView.MapTypeStandard;
        setMapType(i);
        minimumZoomLevel = getMaximumScaleZoomLevel(i, false);
    }

    public static String mapTypeToMode(int i)
    {
        return mMode[i];
    }

    public static String mapTypeToMode(int i, int j)
    {
        if (i == MapView.MapTypeIndoor)
        {
            int k = j;
            if (k == 0)
            {
                k = 1;
            }
            if (k > 0)
            {
                return (new StringBuilder(String.valueOf(mMode[i]))).append("-").append(k).append("f").toString();
            } else
            {
                int l = k * -1;
                return (new StringBuilder(String.valueOf(mMode[i]))).append("-b").append(l).toString();
            }
        } else
        {
            return mMode[i];
        }
    }

    public LayerInfo getApproximateZoomLevel(int i)
    {
        int j = 0;
        do
        {
            if (j >= mLayers.length)
            {
                return mLayers[-1 + mLayers.length];
            }
            if (mLayers[j].scale > i)
            {
                return mLayers[j];
            }
            j++;
        } while (true);
    }

    public float getFactorToNowScale(int i)
    {
        LayerInfo layerinfo = getNowLayer();
        LayerInfo layerinfo1 = getLayer(i);
        return (float)layerinfo.scale / (float)layerinfo1.scale;
    }

    public LayerInfo getFitScaleForFactor(float f, int i)
    {
        int j;
        int l;
        j = (int)(0.5D + (double)((float)getNowScale() / f));
        if (i != 1)
        {
            break MISSING_BLOCK_LABEL_74;
        }
        l = 0;
_L3:
        if (l < mLayers.length) goto _L2; else goto _L1
_L1:
        return null;
_L2:
        if (mLayers[l].scale >= j || l == -1 + mLayers.length)
        {
            return mLayers[l];
        }
        l++;
          goto _L3
        if (i == 2)
        {
            int k = -1 + mLayers.length;
            while (k >= 0) 
            {
                if (mLayers[k].scale <= j || k == 0 || mLayers[k].level == minimumZoomLevel)
                {
                    return mLayers[k];
                }
                k--;
            }
        }
          goto _L1
    }

    public LayerInfo getLayer(int i)
    {
        int j = 0;
        do
        {
            if (j >= mLayers.length)
            {
                return mLayers[0];
            }
            if (mLayers[j].level == i)
            {
                return mLayers[j];
            }
            j++;
        } while (true);
    }

    public LayerInfo[] getLayerList()
    {
        return mLayers;
    }

    public double getMagnification()
    {
        return mMagnification;
    }

    public int getMaximumScaleZoomLevel(int i, boolean flag)
    {
        int j;
        LayerInfo alayerinfo[];
        int k;
        int l;
        int i1;
        j = 0;
        alayerinfo = LayerInfo.getLayers(mapTypeToMode(i), mMagnification);
        if (alayerinfo == null || alayerinfo.length < 1)
        {
            throw new RuntimeException((new StringBuilder("Illegal MapType: ")).append(i).toString());
        }
        k = 0x5d21d586;
        l = alayerinfo.length;
        i1 = 0;
_L3:
        if (i1 < l) goto _L2; else goto _L1
_L1:
        int j1 = alayerinfo.length;
_L4:
        if (j >= j1)
        {
            throw new RuntimeException((new StringBuilder("Not found maximum scale: mapType=")).append(i).toString());
        }
        break MISSING_BLOCK_LABEL_158;
_L2:
        LayerInfo layerinfo = alayerinfo[i1];
        if (flag)
        {
            k = Math.min(k, layerinfo.scale);
        } else
        if (layerinfo.doReqMap == 1)
        {
            k = Math.min(k, layerinfo.scale);
        }
        i1++;
          goto _L3
        LayerInfo layerinfo1 = alayerinfo[j];
        if (layerinfo1.scale == k)
        {
            return layerinfo1.level;
        }
        j++;
          goto _L4
    }

    public int getNowIndoorLevel()
    {
        return mNowIndoorLevel;
    }

    public LayerInfo getNowLayer()
    {
        if (mLayers == null)
        {
            return null;
        } else
        {
            return mLayers[mNowLayerNo];
        }
    }

    public String getNowMapMode()
    {
        return mapTypeToMode(mNowMapType);
    }

    public int getNowMapType()
    {
        return mNowMapType;
    }

    public int getNowScale()
    {
        return mLayers[mNowLayerNo].scale;
    }

    public int getNowScid()
    {
        return getNowLayer().scid;
    }

    public String getNowStyle()
    {
        return mNowStyle;
    }

    public int getNowZoomLevel()
    {
        return getNowLayer().level;
    }

    public LayerInfo getOneDownLayerInfo()
    {
        int i = -1 + mNowLayerNo;
        if (i < 0)
        {
            return null;
        } else
        {
            return mLayers[i];
        }
    }

    public LayerInfo getOneUpLayerInfo()
    {
        int i = 1 + mNowLayerNo;
        if (i >= mLayers.length)
        {
            return null;
        } else
        {
            return mLayers[i];
        }
    }

    public DoublePoint latLon2Log(Coordinate coordinate)
    {
        LayerInfo layerinfo = getNowLayer();
        DoublePoint doublepoint = new DoublePoint();
        doublepoint.x = layerinfo.latLon2X(coordinate.lat, coordinate.lon);
        doublepoint.y = layerinfo.latLon2Y(coordinate.lat, coordinate.lon);
        return doublepoint;
    }

    public DoublePoint latLon2Log(Coordinate coordinate, int i)
    {
        LayerInfo layerinfo = getLayer(i);
        DoublePoint doublepoint = new DoublePoint();
        doublepoint.x = layerinfo.latLon2X(coordinate.lat, coordinate.lon);
        doublepoint.y = layerinfo.latLon2Y(coordinate.lat, coordinate.lon);
        return doublepoint;
    }

    public double latLon2X(double d, double d1)
    {
        return getNowLayer().latLon2X(d, d1);
    }

    public double latLon2Y(double d, double d1)
    {
        return getNowLayer().latLon2Y(d, d1);
    }

    public double log2Lat(double d, double d1)
    {
        return getNowLayer().log2Lat(d, d1);
    }

    public Coordinate log2LatLon(double d, double d1)
    {
        return getNowLayer().log2LatLon(d, d1);
    }

    public Coordinate log2LatLon(double d, double d1, int i)
    {
        return getLayer(i).log2LatLon(d, d1);
    }

    public Coordinate log2LatLon(DoublePoint doublepoint)
    {
        return log2LatLon(doublepoint.x, doublepoint.y);
    }

    public Coordinate log2LatLon(DoublePoint doublepoint, int i)
    {
        return log2LatLon(doublepoint.x, doublepoint.y, i);
    }

    public double log2Lon(double d, double d1)
    {
        return getNowLayer().log2Lon(d, d1);
    }

    public void setMagnification(double d)
    {
        mMagnification = d;
    }

    public boolean setMapType(int i)
    {
        setMapType(i, 0, null);
        return true;
    }

    public boolean setMapType(int i, int j, String s)
    {
        getNowLayer();
        LayerInfo alayerinfo[] = LayerInfo.getLayers(mapTypeToMode(i), mMagnification);
        if (alayerinfo == null)
        {
            return false;
        }
        mLayers = alayerinfo;
        mNowMapType = i;
        mNowIndoorLevel = j;
        mNowStyle = s;
        if (mNowLayerNo >= mLayers.length)
        {
            mNowLayerNo = -1 + mLayers.length;
        }
        return true;
    }

    public boolean setMapType(int i, String s)
    {
        setMapType(i, 0, s);
        return true;
    }

    public void setMinimumZoomLevel(int i)
    {
        minimumZoomLevel = i;
    }

    public boolean setZoomLevel(int i)
    {
        if (i >= minimumZoomLevel)
        {
            int j = 0;
            while (j < mLayers.length) 
            {
                if (mLayers[j].level == i)
                {
                    mNowLayerNo = j;
                    return true;
                }
                j++;
            }
        }
        return false;
    }

}

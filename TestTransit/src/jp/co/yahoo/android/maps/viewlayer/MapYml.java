// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.maps.viewlayer;

import android.util.Xml;
import java.io.InputStream;
import java.util.HashMap;
import jp.co.yahoo.android.maps.GeoPoint;
import jp.co.yahoo.android.maps.HttpClient;
import jp.co.yahoo.android.maps.MapLayer;
import jp.co.yahoo.android.maps.MapView;
import jp.co.yahoo.android.maps.Projection;
import jp.co.yahoo.android.maps.indoormap.IndoorDataSet;
import jp.co.yahoo.android.maps.indoormap.IndoormapDataParser;
import org.xmlpull.v1.XmlPullParser;

// Referenced classes of package jp.co.yahoo.android.maps.viewlayer:
//            BaseView, Coordinate, BaseViewCtrl, MapYmlPackage, 
//            MapLayerManager, MapYmlLayer, Attestation, LayerInfo

public class MapYml
    implements Runnable, MapLayer
{
    public static interface MapYmlListener
    {

        public abstract boolean endYmlHttpLoader(MapYml mapyml);
    }


    public static String COPPYLIGHT_INDOOR = "(C)Yahoo Japan";
    public static String COPPYLIGHT_OSM = "(C)OpenStreetMap contributors,ODbL";
    private static final int YML_MODE_NONE = 0;
    private static final int YML_MODE_POINT = 1;
    private static final int YML_MODE_POINT_AND_RECT = 3;
    private static final int YML_MODE_RECT = 2;
    private String mAppid;
    private Attestation mAttestation;
    private BaseView mBaseView;
    private BaseViewCtrl mBaseViewCtrl;
    private Coordinate mCenter;
    private boolean mCenterEnableB;
    private int mCenterScaleList[];
    private IndoorDataSet mIndoorDataSet;
    private String mLastLat;
    private String mLastLon;
    private Coordinate mLeftUpPoint;
    private MapYmlListener mMapYmlListener;
    private HashMap mMapYmlPackageList;
    private Coordinate mRightBottomPoint;
    private String mSub_url;
    private boolean mThreadRunCheck;
    final Runnable mUpdateResults = new Runnable() {

        final MapYml this$0;

        public void run()
        {
        }

            
            {
                this$0 = MapYml.this;
                super();
            }
    };
    private boolean mUpdateYml;
    private boolean mWeatherFlag;
    private int mYmlMode;

    public MapYml(BaseView baseview, String s)
    {
        mMapYmlPackageList = new HashMap();
        mBaseView = null;
        mBaseViewCtrl = null;
        mAppid = null;
        mYmlMode = 0;
        mCenter = null;
        mLeftUpPoint = null;
        mRightBottomPoint = null;
        mAttestation = null;
        mSub_url = null;
        mThreadRunCheck = false;
        mWeatherFlag = false;
        mLastLat = new String();
        mLastLon = new String();
        mUpdateYml = false;
        mMapYmlListener = null;
        mIndoorDataSet = new IndoorDataSet();
        mCenterEnableB = false;
        mBaseView = baseview;
        mBaseViewCtrl = mBaseView.getBaseViewCtrl();
        mAppid = s;
    }

    private boolean checkMapTypeIndoor(int i)
    {
        Coordinate coordinate;
        if (mCenter != null)
        {
            coordinate = mCenter;
        } else
        {
            coordinate = new Coordinate(mBaseViewCtrl.getCenterPos());
        }
        return coordinate.lat > 35.632050999999997D && coordinate.lat < 35.635935000000003D && coordinate.lon > 139.88383899999999D && coordinate.lon < 139.887968D && (i >= -1 || i <= 4);
    }

    private MapYmlLayer getMapYmlLayer(String s, int i)
    {
        MapYmlPackage mapymlpackage = (MapYmlPackage)mMapYmlPackageList.get(s);
        if (mapymlpackage != null)
        {
            return mapymlpackage.getMapYmlLayerByScale(i);
        } else
        {
            return null;
        }
    }

    private void parseIndoormapYml(InputStream inputstream)
    {
        XmlPullParser xmlpullparser = Xml.newPullParser();
        String s;
        MapLayerManager maplayermanager = mBaseViewCtrl.getMapLayerManager();
        xmlpullparser.setInput(inputstream, "UTF-8");
        s = maplayermanager.getNowMapMode();
        int i;
        String s1;
        MapYmlPackage mapymlpackage;
        int j;
        if (!s.equals("b1"))
        {
            try
            {
                if (!s.equals("hybrid"));
            }
            catch (Exception exception)
            {
                exception.printStackTrace();
                return;
            }
        }
        mCenterEnableB = false;
        mMapYmlPackageList.clear();
        mIndoorDataSet = new IndoorDataSet(mCenter, mLeftUpPoint, mRightBottomPoint);
        i = xmlpullparser.getEventType();
          goto _L1
_L13:
        if (i != 2) goto _L3; else goto _L2
_L2:
        if (!xmlpullparser.getName().equals("mappackage")) goto _L3; else goto _L4
_L4:
        s1 = xmlpullparser.getAttributeValue(null, "id");
        mapymlpackage = new MapYmlPackage(s1);
        xmlpullparser.next();
        j = xmlpullparser.getEventType();
          goto _L5
_L14:
        i = xmlpullparser.next();
        continue; /* Loop/switch isn't completed */
_L15:
        if (j != 2) goto _L7; else goto _L6
_L6:
        if (!xmlpullparser.getName().equals("layer")) goto _L7; else goto _L8
_L8:
        String s2 = xmlpullparser.getAttributeValue(null, "code");
        String s3 = xmlpullparser.getAttributeValue(null, "copyright");
        String s4 = xmlpullparser.nextText();
        mapymlpackage.addMapYmlLayer(new MapYmlLayer(Integer.parseInt(s2), s3, Integer.parseInt(s4)));
_L10:
        j = xmlpullparser.next();
        continue; /* Loop/switch isn't completed */
_L7:
        if (j != 3) goto _L10; else goto _L9
_L9:
        if (!xmlpullparser.getName().equals("mappackage")) goto _L10; else goto _L11
_L11:
        mMapYmlPackageList.put(s1, mapymlpackage);
        break; /* Loop/switch isn't completed */
_L3:
        if (i != 2)
        {
            break; /* Loop/switch isn't completed */
        }
        if (!xmlpullparser.getName().equals("indoormap"))
        {
            break; /* Loop/switch isn't completed */
        }
        jp.co.yahoo.android.maps.indoormap.IndoorData indoordata = IndoormapDataParser.parseIndoorData(xmlpullparser);
        mIndoorDataSet.addIndoorData(indoordata);
        break; /* Loop/switch isn't completed */
_L1:
        if (i != 1) goto _L13; else goto _L12
_L12:
        return;
_L5:
        if (j != 1) goto _L15; else goto _L14
    }

    private void parseYml(InputStream inputstream)
    {
        XmlPullParser xmlpullparser = Xml.newPullParser();
        String s;
        MapLayerManager maplayermanager = mBaseViewCtrl.getMapLayerManager();
        xmlpullparser.setInput(inputstream, "UTF-8");
        new String[1000];
        new String[1000];
        s = maplayermanager.getNowMapMode();
        String.valueOf(maplayermanager.getNowScale());
        int i;
        String s1;
        MapYmlPackage mapymlpackage;
        int j;
        if (!s.equals("b1"))
        {
            try
            {
                if (!s.equals("hybrid"));
            }
            catch (Exception exception)
            {
                exception.printStackTrace();
                return;
            }
        }
        mCenterEnableB = false;
        mMapYmlPackageList.clear();
        i = xmlpullparser.getEventType();
          goto _L1
_L13:
        if (i != 2) goto _L3; else goto _L2
_L2:
        if (!xmlpullparser.getName().equals("mappackage")) goto _L3; else goto _L4
_L4:
        s1 = xmlpullparser.getAttributeValue(null, "id");
        mapymlpackage = new MapYmlPackage(s1);
        xmlpullparser.next();
        j = xmlpullparser.getEventType();
          goto _L5
_L3:
        i = xmlpullparser.next();
        continue; /* Loop/switch isn't completed */
_L14:
        if (j != 2) goto _L7; else goto _L6
_L6:
        if (!xmlpullparser.getName().equals("layer")) goto _L7; else goto _L8
_L8:
        String s2 = xmlpullparser.getAttributeValue(null, "code");
        String s3 = xmlpullparser.getAttributeValue(null, "copyright");
        String s4 = xmlpullparser.nextText();
        mapymlpackage.addMapYmlLayer(new MapYmlLayer(Integer.parseInt(s2), s3, Integer.parseInt(s4)));
_L10:
        j = xmlpullparser.next();
        continue; /* Loop/switch isn't completed */
_L7:
        if (j != 3) goto _L10; else goto _L9
_L9:
        if (!xmlpullparser.getName().equals("mappackage")) goto _L10; else goto _L11
_L11:
        mMapYmlPackageList.put(s1, mapymlpackage);
        break; /* Loop/switch isn't completed */
_L1:
        if (i != 1) goto _L13; else goto _L12
_L12:
        return;
_L5:
        if (j != 1) goto _L14; else goto _L3
    }

    public boolean ThredYmlGet()
    {
        while ((mAttestation == null || !mAttestation.success) && mSub_url == null || mThreadRunCheck) 
        {
            return false;
        }
        mYmlMode = 1;
        (new Thread(this)).start();
        return true;
    }

    public boolean checkTile(int i, int j)
    {
        if (MapView.MapTypeStandard != i) goto _L2; else goto _L1
_L1:
        if (getMapYmlLayer("map", j) == null) goto _L4; else goto _L3
_L3:
        return true;
_L2:
        if (MapView.MapTypeUnderground != i) goto _L6; else goto _L5
_L5:
        if (getMapYmlLayer("map-b1", j) != null) goto _L3; else goto _L4
_L4:
        return false;
_L6:
        if (MapView.MapTypeSatellite != i)
        {
            break; /* Loop/switch isn't completed */
        }
        if (getMapYmlLayer("photo", j) != null)
        {
            return true;
        }
        if (true) goto _L4; else goto _L7
_L7:
        if (MapView.MapTypeStyle != i)
        {
            continue; /* Loop/switch isn't completed */
        }
        if (getMapYmlLayer("map", j) == null) goto _L4; else goto _L8
_L8:
        return true;
        if (MapView.MapTypeOSM == i) goto _L3; else goto _L9
_L9:
        if (MapView.MapTypeHybrid != i)
        {
            continue; /* Loop/switch isn't completed */
        }
        if (getMapYmlLayer("hybrid", j) == null) goto _L4; else goto _L10
_L10:
        return true;
        if (MapView.MapTypeIndoor != i) goto _L4; else goto _L11
_L11:
        return true;
    }

    public boolean exists(int i, int j)
    {
        return exists(i, 1, j);
    }

    public boolean exists(int i, int j, int k)
    {
        MapLayerManager maplayermanager = mBaseViewCtrl.getMapLayerManager();
        if (mThreadRunCheck) goto _L2; else goto _L1
_L1:
        if (i != MapView.MapTypeStandard && i != MapView.MapTypeStyle) goto _L4; else goto _L3
_L3:
        LayerInfo alayerinfo[] = LayerInfo.getLayers("map", maplayermanager.getMagnification());
        if (k <= alayerinfo.length && k > 0) goto _L5; else goto _L2
_L2:
        return false;
_L5:
        LayerInfo layerinfo;
        MapYmlPackage mapymlpackage;
        layerinfo = alayerinfo[k - 1];
        mapymlpackage = (MapYmlPackage)mMapYmlPackageList.get("map");
_L7:
        if (mapymlpackage != null && mapymlpackage.getMapYmlLayerByScale(layerinfo.scale) != null)
        {
            return true;
        }
        continue; /* Loop/switch isn't completed */
_L4:
        if (i == MapView.MapTypeSatellite)
        {
            LayerInfo alayerinfo3[] = LayerInfo.getLayers("photo", maplayermanager.getMagnification());
            if (k > alayerinfo3.length || k <= 0)
            {
                continue; /* Loop/switch isn't completed */
            }
            layerinfo = alayerinfo3[k - 1];
            mapymlpackage = (MapYmlPackage)mMapYmlPackageList.get("photo");
            continue; /* Loop/switch isn't completed */
        }
        if (i == MapView.MapTypeUnderground)
        {
            LayerInfo alayerinfo2[] = LayerInfo.getLayers("b1", maplayermanager.getMagnification());
            if (k > alayerinfo2.length || k <= 0)
            {
                continue; /* Loop/switch isn't completed */
            }
            layerinfo = alayerinfo2[k - 1];
            mapymlpackage = (MapYmlPackage)mMapYmlPackageList.get("map-b1");
            continue; /* Loop/switch isn't completed */
        }
        if (i == MapView.MapTypeMetroB1 || i == MapView.MapTypeMetroB2 || i == MapView.MapTypeMetroB3)
        {
            Coordinate coordinate = mBaseViewCtrl.getCenterPos();
            if (coordinate.lat > 35.661759444444002D && coordinate.lat < 35.666222222221997D && coordinate.lon > 139.71038805556D && coordinate.lon < 139.71588138889001D)
            {
                return true;
            }
            if (coordinate.lat > 35.670684999999999D && coordinate.lat < 35.675147500000001D && coordinate.lon > 139.75982666666999D && coordinate.lon < 139.76806638888999D)
            {
                return true;
            }
            continue; /* Loop/switch isn't completed */
        }
        if (i == MapView.MapTypeOSM)
        {
            if (k <= LayerInfo.getLayers("osm", maplayermanager.getMagnification()).length && k > 0)
            {
                return true;
            }
            continue; /* Loop/switch isn't completed */
        }
        if (i != MapView.MapTypeHybrid)
        {
            break; /* Loop/switch isn't completed */
        }
        LayerInfo alayerinfo1[] = LayerInfo.getLayers("hybrid", mBaseViewCtrl.getMagnification());
        if (k > alayerinfo1.length || k <= 0)
        {
            continue; /* Loop/switch isn't completed */
        }
        layerinfo = alayerinfo1[k - 1];
        mapymlpackage = (MapYmlPackage)mMapYmlPackageList.get("map-b1");
        if (true) goto _L7; else goto _L6
_L6:
        int l;
        l = MapView.MapTypeIndoor;
        mapymlpackage = null;
        layerinfo = null;
        if (i != l) goto _L7; else goto _L8
_L8:
        boolean flag;
        flag = checkMapTypeIndoor(j);
        mapymlpackage = null;
        layerinfo = null;
        if (!flag) goto _L7; else goto _L9
_L9:
        return true;
        if (true) goto _L2; else goto _L10
_L10:
    }

    public boolean getCenterYml(boolean flag)
    {
        mYmlMode;
        JVM INSTR tableswitch 1 3: default 32
    //                   1 34
    //                   2 344
    //                   3 539;
           goto _L1 _L2 _L3 _L4
_L1:
        return false;
_L2:
        String s;
        Coordinate coordinate5 = mCenter;
        if (coordinate5 == null)
        {
            coordinate5 = mBaseViewCtrl.getCenterPos();
        }
        double d4 = coordinate5.getLatitude();
        double d5 = coordinate5.getLongitude();
        String s3 = String.valueOf(d4);
        String s4 = String.valueOf(d5);
        if (flag && s3.equals(mLastLat) && s4.equals(mLastLon))
        {
            return false;
        }
        String s5 = (new StringBuilder(String.valueOf(s3))).append(",").append(s4).toString();
        s = (new StringBuilder("c=")).append(s5).toString();
_L13:
        int i;
        String s1;
        MapLayerManager maplayermanager = mBaseViewCtrl.getMapLayerManager();
        i = 1 + mBaseViewCtrl.getNowLayer().id;
        s1 = maplayermanager.getNowMapMode();
        if (mAttestation != null && mAttestation.success) goto _L6; else goto _L5
_L5:
        String s2 = (new StringBuilder(String.valueOf(mSub_url))).append("?").append(s).append("&sc=").append(i).append("&mode=").append(s1).append("&appid=").append(mAppid).append("&device=android&v=4").toString();
_L11:
        if (mWeatherFlag)
        {
            s2 = (new StringBuilder(String.valueOf(s2))).append("&mode=rainfall").toString();
            mWeatherFlag = false;
        }
        if (mUpdateYml) goto _L8; else goto _L7
_L7:
        InputStream inputstream;
        mUpdateYml = true;
        inputstream = HttpClient.httprun(s2);
        if (inputstream != null) goto _L10; else goto _L9
_L9:
        mUpdateYml = false;
        return false;
_L3:
        Coordinate coordinate3 = mLeftUpPoint;
        Coordinate coordinate4 = mRightBottomPoint;
        if (mLeftUpPoint == null || mRightBottomPoint == null)
        {
            Projection projection1 = mBaseViewCtrl.getProjection();
            GeoPoint geopoint2 = projection1.fromPixels(0, 0);
            GeoPoint geopoint3 = projection1.fromPixels(mBaseViewCtrl.getWidth(), mBaseViewCtrl.getHeight());
            coordinate3 = new Coordinate(geopoint2.getLatitude(), geopoint2.getLongitude());
            double d2 = geopoint3.getLatitude();
            double d3 = geopoint3.getLongitude();
            coordinate4 = new Coordinate(d2, d3);
        }
        s = (new StringBuilder("r=")).append(String.valueOf(coordinate3.getLatitude())).append(",").append(String.valueOf(coordinate3.getLongitude())).append(",").append(String.valueOf(coordinate4.getLatitude())).append(",").append(String.valueOf(coordinate4.getLongitude())).toString();
        continue; /* Loop/switch isn't completed */
_L4:
        Coordinate coordinate = mCenter;
        Coordinate coordinate1 = mLeftUpPoint;
        Coordinate coordinate2 = mRightBottomPoint;
        if (coordinate == null)
        {
            coordinate = mBaseViewCtrl.getCenterPos();
        }
        if (mLeftUpPoint == null || mRightBottomPoint == null)
        {
            Projection projection = mBaseViewCtrl.getProjection();
            GeoPoint geopoint = projection.fromPixels(0, 0);
            GeoPoint geopoint1 = projection.fromPixels(mBaseViewCtrl.getWidth(), mBaseViewCtrl.getHeight());
            coordinate1 = new Coordinate(geopoint.getLatitude(), geopoint.getLongitude());
            double d = geopoint1.getLatitude();
            double d1 = geopoint1.getLongitude();
            coordinate2 = new Coordinate(d, d1);
        }
        s = (new StringBuilder("c=")).append(String.valueOf(coordinate.getLatitude())).append(",").append(String.valueOf(coordinate.getLongitude())).append("&r=").append(String.valueOf(coordinate1.getLatitude())).append(",").append(String.valueOf(coordinate1.getLongitude())).append(",").append(String.valueOf(coordinate2.getLatitude())).append(",").append(String.valueOf(coordinate2.getLongitude())).toString();
        continue; /* Loop/switch isn't completed */
_L6:
        s2 = (new StringBuilder(String.valueOf(mAttestation.getYmlUrl()))).append("?").append(s).append("&sc=").append(i).append("&mode=").append(s1).append("&appid=").append(mAppid).append("&device=android&v=4").toString();
          goto _L11
_L10:
        parseIndoormapYml(inputstream);
        inputstream.close();
        mUpdateYml = false;
_L8:
        return true;
        Exception exception;
        exception;
        exception.printStackTrace();
        return false;
        if (true) goto _L13; else goto _L12
_L12:
    }

    public String getCopyright(int i, int j)
    {
        if (MapView.MapTypeStandard == i)
        {
            MapYmlLayer mapymllayer6 = getMapYmlLayer("map", j);
            if (mapymllayer6 != null)
            {
                return mapymllayer6.getCopyright();
            }
            MapYmlLayer mapymllayer7 = getMapYmlLayer("gws", j);
            if (mapymllayer7 != null)
            {
                String s2 = mapymllayer7.getCopyright();
                if (s2 != null)
                {
                    return s2.replaceAll("&copy;", "\251").replaceAll("&#xA;", " ");
                }
            }
        } else
        if (MapView.MapTypeUnderground == i)
        {
            MapYmlLayer mapymllayer5 = getMapYmlLayer("map-b1", j);
            if (mapymllayer5 != null)
            {
                return mapymllayer5.getCopyright();
            }
        } else
        if (MapView.MapTypeSatellite == i)
        {
            MapYmlLayer mapymllayer3 = getMapYmlLayer("photo", j);
            if (mapymllayer3 != null)
            {
                return mapymllayer3.getCopyright();
            }
            MapYmlLayer mapymllayer4 = getMapYmlLayer("gws_photo", j);
            if (mapymllayer4 != null)
            {
                String s1 = mapymllayer4.getCopyright();
                if (s1 != null)
                {
                    return s1.replaceAll("&copy;", "\251").replaceAll("&#xA;", " ");
                }
            }
        } else
        if (MapView.MapTypeStyle == i)
        {
            MapYmlLayer mapymllayer1 = getMapYmlLayer("map", j);
            if (mapymllayer1 != null)
            {
                return mapymllayer1.getCopyright();
            }
            MapYmlLayer mapymllayer2 = getMapYmlLayer("gws", j);
            if (mapymllayer2 != null)
            {
                String s = mapymllayer2.getCopyright();
                if (s != null)
                {
                    return s.replaceAll("&copy;", "\251").replaceAll("&#xA;", " ");
                }
            }
        } else
        {
            if (MapView.MapTypeOSM == i)
            {
                return COPPYLIGHT_OSM;
            }
            if (MapView.MapTypeHybrid == i)
            {
                MapYmlLayer mapymllayer = getMapYmlLayer("hybrid", j);
                if (mapymllayer != null)
                {
                    return mapymllayer.getCopyright();
                }
            } else
            if (MapView.MapTypeIndoor == i)
            {
                return COPPYLIGHT_INDOOR;
            }
        }
        return COPPYLIGHT_INDOOR;
    }

    public IndoorDataSet getIndoorDataSet()
    {
        return mIndoorDataSet;
    }

    public int getMaxZoomLevel(int i)
    {
        MapLayerManager maplayermanager = mBaseViewCtrl.getMapLayerManager();
        if (mThreadRunCheck) goto _L2; else goto _L1
_L1:
        if (i != MapView.MapTypeStandard && i != MapView.MapTypeStyle) goto _L4; else goto _L3
_L3:
        MapYmlPackage mapymlpackage;
        LayerInfo alayerinfo[];
        mapymlpackage = (MapYmlPackage)mMapYmlPackageList.get("map");
        alayerinfo = LayerInfo.getLayers("map", maplayermanager.getMagnification());
_L11:
        if (mapymlpackage != null && mapymlpackage.getMapYmlLayerMin() != null) goto _L5; else goto _L2
_L2:
        return -99;
_L4:
        if (i == MapView.MapTypeSatellite)
        {
            mapymlpackage = (MapYmlPackage)mMapYmlPackageList.get("photo");
            alayerinfo = LayerInfo.getLayers("photo", maplayermanager.getMagnification());
            continue; /* Loop/switch isn't completed */
        }
        if (i == MapView.MapTypeUnderground)
        {
            mapymlpackage = (MapYmlPackage)mMapYmlPackageList.get("map-b1");
            alayerinfo = LayerInfo.getLayers("b1", maplayermanager.getMagnification());
            continue; /* Loop/switch isn't completed */
        }
        if (i == MapView.MapTypeMetroB1 || i == MapView.MapTypeMetroB2)
        {
            continue; /* Loop/switch isn't completed */
        }
        int i1 = MapView.MapTypeMetroB3;
        mapymlpackage = null;
        alayerinfo = null;
        if (i != i1)
        {
            continue; /* Loop/switch isn't completed */
        }
        if (!exists(i, 0)) goto _L2; else goto _L6
_L6:
        return 5;
_L5:
        int j = mapymlpackage.getMapYmlLayerMax().getScale();
        int k = 0;
_L8:
        int l;
        if (k >= alayerinfo.length)
        {
            break; /* Loop/switch isn't completed */
        }
        if (alayerinfo[k].scale != j)
        {
            break MISSING_BLOCK_LABEL_229;
        }
        l = alayerinfo[k].id;
        return l + 1;
        k++;
        if (true) goto _L8; else goto _L7
_L7:
        if (true) goto _L2; else goto _L9
_L9:
        Exception exception;
        exception;
        return -99;
        if (true) goto _L11; else goto _L10
_L10:
    }

    public int getMinZoomLevel(int i)
    {
        MapLayerManager maplayermanager = mBaseViewCtrl.getMapLayerManager();
        if (mThreadRunCheck) goto _L2; else goto _L1
_L1:
        if (i != MapView.MapTypeStandard && i != MapView.MapTypeStyle) goto _L4; else goto _L3
_L3:
        MapYmlPackage mapymlpackage;
        LayerInfo alayerinfo[];
        mapymlpackage = (MapYmlPackage)mMapYmlPackageList.get("map");
        alayerinfo = LayerInfo.getLayers("map", maplayermanager.getMagnification());
_L11:
        if (mapymlpackage != null && mapymlpackage.getMapYmlLayerMin() != null) goto _L5; else goto _L2
_L2:
        return -99;
_L4:
        if (i == MapView.MapTypeSatellite)
        {
            mapymlpackage = (MapYmlPackage)mMapYmlPackageList.get("photo");
            alayerinfo = LayerInfo.getLayers("photo", maplayermanager.getMagnification());
            continue; /* Loop/switch isn't completed */
        }
        if (i == MapView.MapTypeUnderground)
        {
            mapymlpackage = (MapYmlPackage)mMapYmlPackageList.get("map-b1");
            alayerinfo = LayerInfo.getLayers("b1", maplayermanager.getMagnification());
            continue; /* Loop/switch isn't completed */
        }
        if (i == MapView.MapTypeMetroB1 || i == MapView.MapTypeMetroB2)
        {
            continue; /* Loop/switch isn't completed */
        }
        int i1 = MapView.MapTypeMetroB3;
        mapymlpackage = null;
        alayerinfo = null;
        if (i != i1)
        {
            continue; /* Loop/switch isn't completed */
        }
        if (!exists(i, 0)) goto _L2; else goto _L6
_L6:
        return 1;
_L5:
        int j = mapymlpackage.getMapYmlLayerMin().getScale();
        int k = 0;
_L8:
        int l;
        if (k >= alayerinfo.length)
        {
            break; /* Loop/switch isn't completed */
        }
        if (alayerinfo[k].scale != j)
        {
            break MISSING_BLOCK_LABEL_229;
        }
        l = alayerinfo[k].id;
        return l + 1;
        k++;
        if (true) goto _L8; else goto _L7
_L7:
        if (true) goto _L2; else goto _L9
_L9:
        Exception exception;
        exception;
        return -99;
        if (true) goto _L11; else goto _L10
_L10:
    }

    public boolean getUnderground()
    {
        MapYmlPackage mapymlpackage = (MapYmlPackage)mMapYmlPackageList.get("map-b1");
        return mapymlpackage != null && mapymlpackage.getMapYmlLayerMin() != null;
    }

    public boolean getYml(GeoPoint geopoint)
    {
        mYmlMode = 1;
        mCenter = new Coordinate();
        mCenter.lat = (double)geopoint.getLatitudeE6() / 1000000D;
        mCenter.lon = (double)geopoint.getLongitudeE6() / 1000000D;
        mSub_url = "http://layer.map.yahoo.co.jp/ml";
        return getCenterYml(false);
    }

    public boolean getYml(GeoPoint geopoint, GeoPoint geopoint1)
    {
        mYmlMode = 2;
        mLeftUpPoint = new Coordinate();
        mLeftUpPoint.lat = (double)geopoint.getLatitudeE6() / 1000000D;
        mLeftUpPoint.lon = (double)geopoint.getLongitudeE6() / 1000000D;
        mRightBottomPoint = new Coordinate();
        mRightBottomPoint.lat = (double)geopoint1.getLatitudeE6() / 1000000D;
        mRightBottomPoint.lon = (double)geopoint1.getLongitudeE6() / 1000000D;
        mSub_url = "http://layer.map.yahoo.co.jp/ml";
        return getCenterYml(false);
    }

    public boolean getYml(GeoPoint geopoint, GeoPoint geopoint1, GeoPoint geopoint2)
    {
        mYmlMode = 3;
        mCenter = new Coordinate();
        mCenter.lat = (double)geopoint.getLatitudeE6() / 1000000D;
        mCenter.lon = (double)geopoint.getLongitudeE6() / 1000000D;
        mLeftUpPoint = new Coordinate();
        mLeftUpPoint.lat = (double)geopoint1.getLatitudeE6() / 1000000D;
        mLeftUpPoint.lon = (double)geopoint1.getLongitudeE6() / 1000000D;
        mRightBottomPoint = new Coordinate();
        mRightBottomPoint.lat = (double)geopoint2.getLatitudeE6() / 1000000D;
        mRightBottomPoint.lon = (double)geopoint2.getLongitudeE6() / 1000000D;
        mSub_url = "http://layer.map.yahoo.co.jp/ml";
        return getCenterYml(false);
    }

    public void run()
    {
        mThreadRunCheck = true;
        if (getCenterYml(false) && mBaseView != null)
        {
            mBaseView.doEvent(jp.co.yahoo.android.maps.MapCtrlEvent.EventType.SET_COPYRIGHT, this);
        }
        mThreadRunCheck = false;
        if (mMapYmlListener != null)
        {
            mMapYmlListener.endYmlHttpLoader(this);
        }
    }

    public void setAttestation(Attestation attestation)
    {
        mAttestation = attestation;
    }

    public void setMapYmlListener(MapYmlListener mapymllistener)
    {
        mMapYmlListener = mapymllistener;
    }

    public void setWeatherFlag(boolean flag)
    {
        mWeatherFlag = flag;
    }

}

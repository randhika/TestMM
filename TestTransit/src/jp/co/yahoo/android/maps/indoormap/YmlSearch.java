// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.maps.indoormap;

import android.os.AsyncTask;
import java.util.ArrayList;
import jp.co.yahoo.android.maps.GeoPoint;
import jp.co.yahoo.android.maps.MapView;

// Referenced classes of package jp.co.yahoo.android.maps.indoormap:
//            IndoorDataSet

public class YmlSearch extends AsyncTask
{
    public static interface YmlSearchListener
    {

        public abstract boolean endYmlSearch(IndoorDataSet indoordataset);
    }


    private static final int YML_MODE_POINT = 1;
    private static final int YML_MODE_POINT_AND_RECT = 3;
    private static final int YML_MODE_RECT = 2;
    private IndoorDataSet idset;
    private final GeoPoint leftUpPoint;
    private YmlSearchListener m_ymlSearchListener;
    private final MapView mapView;
    private final int mode;
    private final GeoPoint point;
    private final GeoPoint rightBottomPoint;

    public YmlSearch(MapView mapview, GeoPoint geopoint)
    {
        idset = null;
        m_ymlSearchListener = null;
        mapView = mapview;
        mode = 1;
        point = geopoint;
        leftUpPoint = null;
        rightBottomPoint = null;
    }

    public YmlSearch(MapView mapview, GeoPoint geopoint, GeoPoint geopoint1)
    {
        idset = null;
        m_ymlSearchListener = null;
        mapView = mapview;
        mode = 2;
        point = null;
        leftUpPoint = geopoint;
        rightBottomPoint = geopoint1;
    }

    public YmlSearch(MapView mapview, GeoPoint geopoint, GeoPoint geopoint1, GeoPoint geopoint2)
    {
        idset = null;
        m_ymlSearchListener = null;
        mapView = mapview;
        mode = 3;
        point = geopoint;
        leftUpPoint = geopoint1;
        rightBottomPoint = geopoint2;
    }

    protected volatile transient Object doInBackground(Object aobj[])
    {
        return doInBackground((String[])aobj);
    }

    protected transient ArrayList doInBackground(String as[])
    {
        idset = null;
        if (mapView == null) goto _L2; else goto _L1
_L1:
        mode;
        JVM INSTR tableswitch 1 3: default 105
    //                   1 44
    //                   2 61
    //                   3 82;
           goto _L3 _L4 _L5 _L6
_L3:
        break; /* Loop/switch isn't completed */
_L4:
        idset = mapView.getIndoorDataSet(point);
        return null;
_L5:
        try
        {
            idset = mapView.getIndoorDataSet(leftUpPoint, rightBottomPoint);
        }
        catch (Exception exception)
        {
            return null;
        }
        return null;
_L6:
        idset = mapView.getIndoorDataSet(point, leftUpPoint, rightBottomPoint);
_L2:
        return null;
    }

    protected volatile void onPostExecute(Object obj)
    {
        onPostExecute((ArrayList)obj);
    }

    protected void onPostExecute(ArrayList arraylist)
    {
        if (m_ymlSearchListener != null)
        {
            m_ymlSearchListener.endYmlSearch(idset);
        }
    }

    public void setYmlSearchListener(YmlSearchListener ymlsearchlistener)
    {
        m_ymlSearchListener = ymlsearchlistener;
    }
}

// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.maps.ar;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import java.util.ArrayList;

// Referenced classes of package jp.co.yahoo.android.maps.ar:
//            DeviceStateListener, DeviceState, NavigationMgrListener, POI, 
//            ShapeUtil

public class NavigationMgr
    implements DeviceStateListener
{

    private float acc;
    private double alt;
    double angle;
    private int destIndex;
    double destLat;
    double destLon;
    double dist;
    private DeviceState ds;
    private double lat;
    private NavigationMgrListener listener;
    private double lon;
    private ArrayList poiList;

    public NavigationMgr(Activity activity, NavigationMgrListener navigationmgrlistener)
    {
        lat = 35D;
        lon = 135D;
        alt = 0.0D;
        acc = 0.0F;
        poiList = new ArrayList();
        ds = new DeviceState(activity, this);
        listener = navigationmgrlistener;
        calcDist();
        calcAngle(dist);
    }

    private void calcAngle(double d)
    {
        angle = calcAngle(destLat, destLon, d);
    }

    private void calcDist()
    {
        dist = calcDist(destLat, destLon);
    }

    public void StateChanged()
    {
        listener.NavigationMgrUpdated();
    }

    public int addPOI(double d, double d1, Drawable drawable, int i, int j)
    {
        POI poi = new POI(d, d1, drawable, i, j);
        poiList.add(poi);
        StateChanged();
        return -1 + poiList.size();
    }

    public double calcAngle(double d, double d1, double d2)
    {
        return ShapeUtil.calcAngle(lat, lon, d, d1, d2);
    }

    public double calcDist(double d, double d1)
    {
        return ShapeUtil.calcDistance(lat, lon, d, d1);
    }

    public void clearPOI()
    {
        poiList.clear();
        StateChanged();
    }

    public float getAccuracy()
    {
        return acc;
    }

    public double getAlt()
    {
        return alt;
    }

    public double getAngle()
    {
        return angle;
    }

    public float getAzimuth()
    {
        return ds.getAzimuth();
    }

    public int getDestination()
    {
        return destIndex;
    }

    public double getDist()
    {
        return dist;
    }

    public float getInclination()
    {
        return ds.getInclination();
    }

    public double getLat()
    {
        return lat;
    }

    public double getLon()
    {
        return lon;
    }

    public ArrayList getPoiList()
    {
        return poiList;
    }

    public void onPause()
    {
        ds.unregistSensor();
    }

    public void onResume()
    {
        ds.registSensor();
    }

    public void removePOI(int i)
    {
        poiList.remove(i);
        StateChanged();
    }

    public void setDestination(int i)
    {
        destIndex = i;
        if (poiList.size() <= destIndex)
        {
            return;
        } else
        {
            destLat = ((POI)poiList.get(destIndex)).getLat();
            destLon = ((POI)poiList.get(destIndex)).getLon();
            calcDist();
            calcAngle(dist);
            StateChanged();
            return;
        }
    }

    public void setGpsValue(double d, double d1, double d2, float f)
    {
        lat = d;
        lon = d1;
        alt = d2;
        acc = f;
        calcDist();
        calcAngle(dist);
        StateChanged();
    }
}

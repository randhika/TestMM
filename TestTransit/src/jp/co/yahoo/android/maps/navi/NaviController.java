// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.maps.navi;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.location.Location;
import java.util.List;
import jp.co.yahoo.android.maps.GeoPoint;
import jp.co.yahoo.android.maps.MapView;
import jp.co.yahoo.android.maps.ar.ARController;
import jp.co.yahoo.android.maps.asyncgetindoorlocation.IndoorLocation;
import jp.co.yahoo.android.maps.routing.GPoint;
import jp.co.yahoo.android.maps.routing.LocationControl;
import jp.co.yahoo.android.maps.routing.RLine;
import jp.co.yahoo.android.maps.routing.RouteControl;
import jp.co.yahoo.android.maps.routing.RouteOverlay;
import jp.co.yahoo.android.maps.viewlayer.Coordinate;
import jp.co.yahoo.android.maps.viewlayer.LLCalculation;

// Referenced classes of package jp.co.yahoo.android.maps.navi:
//            CompassOverlay

public class NaviController
    implements jp.co.yahoo.android.maps.routing.LocationControl.LocationControlListener
{
    public static interface NaviControllerListener
    {

        public abstract boolean onGoal(NaviController navicontroller);

        public abstract boolean onLocationAccuracyBad(NaviController navicontroller);

        public abstract boolean onLocationChanged(NaviController navicontroller);

        public abstract boolean onLocationTimeOver(NaviController navicontroller);

        public abstract boolean onRouteOut(NaviController navicontroller);
    }


    private static int GOAL_DIST_M = 10;
    private static int GUIDE_NEAR_M = 10;
    private static int NAVI_DEBG = 0;
    private static int ROUTEOUT_CNT = 30;
    private static int ROUTE_DIST_M = 30;
    private static int WALK_MAX_KM = 20;
    private ARController m_arController;
    private Drawable m_arkeiyuImage;
    private CompassOverlay m_compassOrverlay;
    private Context m_context;
    private boolean m_goalFlag;
    private LocationControl m_locationControl;
    private MapView m_mapView;
    private NaviControllerListener m_naviControlListener;
    private boolean m_navi_go;
    private Location m_nowLocation;
    private double m_oldRemainderDistance;
    private int m_pincnt;
    private RouteControl m_routeControl;
    private int m_routeOutCnt;
    private RouteOverlay m_routeOverlay;
    private long m_speed;
    private long m_startGpsTime;
    private double m_startRemainderDistance;

    public NaviController(Context context, RouteOverlay routeoverlay)
    {
        m_routeOverlay = null;
        m_context = null;
        m_mapView = null;
        m_arController = null;
        m_naviControlListener = null;
        m_locationControl = null;
        m_routeControl = null;
        m_nowLocation = null;
        m_goalFlag = false;
        m_routeOutCnt = 0;
        m_startGpsTime = 0L;
        m_oldRemainderDistance = 0.0D;
        m_startRemainderDistance = 0.0D;
        m_speed = 0L;
        m_navi_go = false;
        m_compassOrverlay = null;
        m_pincnt = 0;
        m_arkeiyuImage = null;
        m_context = context;
        m_routeOverlay = routeoverlay;
        m_routeControl = (RouteControl)m_routeOverlay.getObject();
    }

    private GPoint getGuidePoint()
    {
        if (m_routeControl == null || m_nowLocation == null)
        {
            return null;
        }
        RLine rline = m_routeControl.getNowRLine();
        int i = rline.lno;
        RLine rline1;
        do
        {
            if (i >= m_routeControl.count())
            {
                return rline.getLastPoint();
            }
            rline1 = m_routeControl.getRLine(i);
            if (rline1.direction != null)
            {
                int j = (int)Long.parseLong(rline1.direction);
                if (j != 0 && j != 1 && j != 11)
                {
                    break;
                }
            }
            i++;
        } while (true);
        return rline1.getLastPoint();
    }

    private Coordinate getNowPointByCoordinate()
    {
        if (m_routeControl == null || m_nowLocation == null)
        {
            return null;
        }
        GPoint gpoint = new GPoint(m_nowLocation.getLongitude(), m_nowLocation.getLatitude());
        GPoint gpoint1 = m_routeControl.getNearPoint(gpoint);
        Coordinate coordinate = new Coordinate();
        if (gpoint1 == null)
        {
            return null;
        } else
        {
            coordinate.lat = gpoint1.y;
            coordinate.lon = gpoint1.x;
            return coordinate;
        }
    }

    private boolean isNearGuidePoint()
    {
        if (m_routeControl == null || m_nowLocation == null)
        {
            return false;
        }
        GPoint gpoint = getGuidePoint();
        if (gpoint == null)
        {
            return false;
        }
        Coordinate coordinate = gpoint.getCoordinate();
        Coordinate coordinate1 = getNowPointByCoordinate();
        if (coordinate1 == null)
        {
            return false;
        }
        return LLCalculation.distance(coordinate1.lat, coordinate1.lon, coordinate.lat, coordinate.lon) <= (double)GUIDE_NEAR_M;
    }

    public void checkSpeed()
    {
        if (m_startGpsTime == 0L)
        {
            m_startGpsTime = System.currentTimeMillis();
            m_startRemainderDistance = getDistanceOfRemainder();
            m_oldRemainderDistance = m_startRemainderDistance;
        } else
        {
            long l = System.currentTimeMillis();
            double d = getDistanceOfRemainder();
            if (m_oldRemainderDistance - d > 10D)
            {
                m_speed = (long)(60000D * ((m_startRemainderDistance - d) / (double)(l - m_startGpsTime)));
                m_oldRemainderDistance = d;
                return;
            }
        }
    }

    public double getDistanceOfRemainder()
    {
        if (m_routeControl != null)
        {
            if (m_nowLocation == null)
            {
                return getTotalDistance();
            }
            if (!m_navi_go)
            {
                return getTotalDistance();
            }
            if (!m_goalFlag)
            {
                GPoint gpoint = new GPoint(m_nowLocation.getLongitude(), m_nowLocation.getLatitude());
                RLine rline = m_routeControl.getNowRLine();
                if (rline == null)
                {
                    return getTotalDistance();
                } else
                {
                    return m_routeControl.getMDistanceNearPointToPoint(gpoint, rline.getLastPoint()) + m_routeControl.getMTotalDistanceByLNo(1 + rline.lno);
                }
            }
        }
        return 0.0D;
    }

    public double getDistanceToNextDirection()
    {
        int i;
        int j;
        if (m_routeControl == null)
        {
            return 0.0D;
        }
        RLine rline = m_routeControl.getNowRLine();
        i = (int)rline.distance;
        j = 1 + rline.lno;
_L5:
        if (j < m_routeControl.count()) goto _L2; else goto _L1
_L1:
        return (double)i;
_L2:
        RLine rline1 = m_routeControl.getRLine(j);
        if (rline1.direction == null)
        {
            break; /* Loop/switch isn't completed */
        }
        int k = (int)Long.parseLong(rline1.direction);
        if (k != 0 && k != 1 && k != 11)
        {
            continue; /* Loop/switch isn't completed */
        }
        i += (int)rline1.distance;
        break; /* Loop/switch isn't completed */
        if (true) goto _L1; else goto _L3
_L3:
        j++;
        if (true) goto _L5; else goto _L4
_L4:
    }

    public Location getLocation()
    {
        return m_nowLocation;
    }

    public boolean getNaviEnabled()
    {
        return m_locationControl != null;
    }

    public int getNextDirection()
    {
        int k;
        if (m_routeControl != null && m_nowLocation != null)
        {
            RLine rline = m_routeControl.getNextRoute();
            int i = (int)rline.distance;
            int j = rline.lno;
            while (j < m_routeControl.count()) 
            {
label0:
                {
                    RLine rline1 = m_routeControl.getRLine(j);
                    if (rline1.direction != null)
                    {
                        k = (int)Long.parseLong(rline1.direction);
                        if (k != 0 && k != 1 && k != 11)
                        {
                            break label0;
                        }
                        i += (int)rline1.distance;
                    }
                    j++;
                }
            }
        }
        return 0;
        return k;
    }

    public double getTimeOfRemainder()
    {
        double d = getDistanceOfRemainder();
        if (m_speed == 0L)
        {
            return (double)m_routeControl.defTotalTime;
        }
        long l = (long)(0.5D + d / (double)m_speed);
        if (m_goalFlag)
        {
            return 0.0D;
        }
        if (l == 0L)
        {
            return 1.0D;
        } else
        {
            return (double)l;
        }
    }

    public double getTotalDistance()
    {
        return m_routeControl.getMTotalDistance();
    }

    public double getTotalTime()
    {
        return (double)m_routeControl.defTotalTime;
    }

    public void onYIndoorLocationChanged(LocationControl locationcontrol, IndoorLocation indoorlocation)
    {
    }

    public void onYLocationChanged(LocationControl locationcontrol)
    {
        if (m_routeControl == null) goto _L2; else goto _L1
_L1:
        GPoint gpoint;
        double d;
        m_nowLocation = locationcontrol.getLocation();
        gpoint = new GPoint(m_nowLocation.getLongitude(), m_nowLocation.getLatitude());
        m_routeControl.cmpLineAndPoint(gpoint);
        d = m_routeControl.getMDistanceToNearPoint(gpoint);
        if (m_naviControlListener == null) goto _L4; else goto _L3
_L3:
        Coordinate coordinate;
        double d1;
        double d2;
        coordinate = gpoint.getCoordinate();
        Coordinate coordinate1 = getNowPointByCoordinate();
        GPoint gpoint1 = m_routeControl.getLastPoint();
        Coordinate coordinate2 = new Coordinate();
        coordinate2.lat = gpoint1.y;
        coordinate2.lon = gpoint1.x;
        d1 = LLCalculation.distance(coordinate.lat, coordinate.lon, coordinate2.lat, coordinate2.lon);
        d2 = LLCalculation.distance(coordinate1.lat, coordinate1.lon, coordinate2.lat, coordinate2.lon);
        if (d < (double)ROUTE_DIST_M) goto _L6; else goto _L5
_L5:
        float f = m_nowLocation.getAccuracy();
        if (f <= (float)ROUTE_DIST_M) goto _L8; else goto _L7
_L7:
        m_naviControlListener.onLocationAccuracyBad(this);
        if (m_compassOrverlay != null)
        {
            m_compassOrverlay.setpos(new GeoPoint((int)(1000000D * m_nowLocation.getLatitude()), (int)(1000000D * m_nowLocation.getLongitude())), (int)f);
        }
_L2:
        return;
_L8:
        if (m_routeOutCnt > ROUTEOUT_CNT)
        {
            m_routeOutCnt = 0;
            m_naviControlListener.onRouteOut(this);
        } else
        {
            checkSpeed();
            m_naviControlListener.onLocationChanged(this);
            m_routeOutCnt = 1 + m_routeOutCnt;
        }
_L4:
        if (m_compassOrverlay != null)
        {
            m_compassOrverlay.setpos(new GeoPoint((int)(1000000D * m_nowLocation.getLatitude()), (int)(1000000D * m_nowLocation.getLongitude())));
            return;
        }
          goto _L9
_L6:
        if (d1 <= (double)GOAL_DIST_M || d2 <= (double)GOAL_DIST_M)
        {
            locationcontrol.setLocationControlListener(null);
            locationcontrol.stopLocation();
            if (m_arController != null)
            {
                m_arController.setArrowColor(0.3F, 1.0F, 0.0F, 0.0F);
            }
            m_naviControlListener.onLocationChanged(this);
            m_goalFlag = true;
            m_naviControlListener.onGoal(this);
        } else
        {
            checkSpeed();
            m_routeOutCnt = 0;
            if (m_arController != null)
            {
                m_arController.setCurrentPos(coordinate.getLatitude(), coordinate.getLongitude(), 0.0D, 0.0F);
                if (getNextDirection() == 10)
                {
                    if (m_pincnt == 1)
                    {
                        m_arController.removePOI(1);
                    }
                    m_arController.setDestination(0);
                    m_pincnt = 0;
                } else
                {
                    if (m_pincnt == 1)
                    {
                        m_arController.removePOI(1);
                    }
                    GPoint gpoint2 = getGuidePoint();
                    m_arController.addPOI(gpoint2.y, gpoint2.x, m_arkeiyuImage, 41, 38);
                    m_arController.setDestination(1);
                    m_pincnt = 1;
                }
            }
            m_naviControlListener.onLocationChanged(this);
        }
        if (true) goto _L4; else goto _L10
_L10:
_L9:
        if (true) goto _L2; else goto _L11
_L11:
    }

    public void onYLocationError(LocationControl locationcontrol)
    {
        m_naviControlListener.onLocationTimeOver(this);
    }

    public void setARController(ARController arcontroller)
    {
        m_arController = arcontroller;
        if (m_arController != null)
        {
            String s = m_context.getPackageName();
            int i = m_context.getResources().getIdentifier("argoal", "drawable", s);
            Drawable drawable = m_context.getResources().getDrawable(i);
            GPoint gpoint = m_routeControl.getLastPoint();
            m_arController.addPOI(gpoint.y, gpoint.x, drawable, 31, 30);
            int j = m_context.getResources().getIdentifier("arkeiyu", "drawable", s);
            Drawable drawable1 = m_context.getResources().getDrawable(j);
            m_arkeiyuImage = drawable1;
            GPoint gpoint1 = getGuidePoint();
            if (gpoint1 == null)
            {
                gpoint1 = m_routeControl.getBeginPoint();
            }
            m_arController.addPOI(gpoint1.y, gpoint1.x, drawable1, 41, 38);
            m_arController.setDestination(1);
            m_pincnt = 1;
            m_arController.onResume();
            GeoPoint ageopoint[] = m_routeControl.getGeoPoints();
            m_arController.setRoute(ageopoint);
            if (m_nowLocation == null)
            {
                m_arController.setCurrentPos((double)m_routeOverlay.getStartPos().getLatitudeE6() / 1000000D, (double)m_routeOverlay.getStartPos().getLongitudeE6() / 1000000D, 0.0D, 0.0F);
            } else
            {
                m_arController.setCurrentPos(m_nowLocation.getLatitude(), m_nowLocation.getLongitude(), 0.0D, 0.0F);
            }
            if (isNearGuidePoint())
            {
                m_arController.setArrowColor(0.3F, 1.0F, 0.0F, 0.0F);
            }
        }
    }

    public void setMapView(MapView mapview)
    {
        if (mapview == null && m_mapView != null && m_compassOrverlay != null)
        {
            m_compassOrverlay.stopCompass();
            m_mapView.getOverlays().remove(m_compassOrverlay);
        }
        m_mapView = mapview;
    }

    public void setNaviControlListener(NaviControllerListener navicontrollerlistener)
    {
        m_naviControlListener = navicontrollerlistener;
    }

    public boolean start()
    {
        if (m_locationControl != null)
        {
            return false;
        }
        m_routeOutCnt = 0;
        m_locationControl = new LocationControl(m_context, this);
        if (NAVI_DEBG == 1)
        {
            m_locationControl.setDebgData(m_routeControl.getCoordinates());
        }
        m_locationControl.setTimer(0x1d4c0);
        m_locationControl.startLocation(true);
        m_navi_go = true;
        if (m_mapView != null)
        {
            m_compassOrverlay = new CompassOverlay(m_context, m_mapView);
            m_mapView.getOverlays().add(m_compassOrverlay);
            if (m_routeOverlay != null)
            {
                GeoPoint geopoint = m_routeOverlay.getStartPos();
                if (geopoint != null)
                {
                    m_compassOrverlay.setpos(geopoint, 0);
                }
            }
        }
        return true;
    }

    public boolean stop()
    {
        if (m_locationControl != null)
        {
            m_locationControl.stopLocation();
            m_locationControl = null;
        }
        if (m_mapView != null)
        {
            m_mapView.getOverlays().remove(m_compassOrverlay);
        }
        if (m_compassOrverlay != null)
        {
            m_compassOrverlay.stopCompass();
            m_compassOrverlay = null;
        }
        m_startGpsTime = 0L;
        return true;
    }

}

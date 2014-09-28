// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.maps.routing;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import java.util.ArrayList;
import jp.co.yahoo.android.maps.GeoPoint;
import jp.co.yahoo.android.maps.MapView;
import jp.co.yahoo.android.maps.Overlay;
import jp.co.yahoo.android.maps.PinOverlay;
import jp.co.yahoo.android.maps.PolylineOverlay;
import jp.co.yahoo.android.maps.PopupOverlay;

// Referenced classes of package jp.co.yahoo.android.maps.routing:
//            RouteNodeInfo, RouteControl, RouteSearch, RLine, 
//            GPoint

public class RouteOverlay extends Overlay
{
    public static interface RouteOverlayListener
    {

        public abstract boolean errorRouteSearch(RouteOverlay routeoverlay, int i);

        public abstract boolean finishRouteSearch(RouteOverlay routeoverlay);
    }

    private class YRouteLineOverlay extends PolylineOverlay
    {

        final RouteOverlay this$0;
        public boolean visible;
        public int z;

        public YRouteLineOverlay(GeoPoint ageopoint[])
        {
            this$0 = RouteOverlay.this;
            super(ageopoint);
            z = 0;
            visible = true;
        }
    }

    private class YRoutePinOverlay extends PinOverlay
    {

        public RouteNodeInfo routePtInfo;
        final RouteOverlay this$0;
        public boolean visible;
        public int z;

        public YRoutePinOverlay(int i)
        {
            this$0 = RouteOverlay.this;
            super(i);
            z = 0;
            visible = true;
        }

        public YRoutePinOverlay(int i, RouteNodeInfo routenodeinfo)
        {
            this$0 = RouteOverlay.this;
            super(i);
            z = 0;
            visible = true;
            routePtInfo = routenodeinfo;
        }

        public YRoutePinOverlay(Drawable drawable)
        {
            this$0 = RouteOverlay.this;
            super(drawable);
            z = 0;
            visible = true;
        }

        public YRoutePinOverlay(Drawable drawable, RouteNodeInfo routenodeinfo)
        {
            this$0 = RouteOverlay.this;
            super(drawable);
            z = 0;
            visible = true;
            routePtInfo = routenodeinfo;
        }
    }

    private class YRouteSearchTask extends AsyncTask
    {

        final RouteOverlay this$0;

        protected volatile transient Object doInBackground(Object aobj[])
        {
            return doInBackground((String[])aobj);
        }

        protected transient ArrayList doInBackground(String as[])
        {
            RouteSearch routesearch;
            String s;
            routesearch = new RouteSearch();
            s = "car";
            m_traffic;
            JVM INSTR tableswitch 0 2: default 44
        //                       0 191
        //                       1 44
        //                       2 197;
               goto _L1 _L2 _L1 _L3
_L1:
            break; /* Loop/switch isn't completed */
_L3:
            break MISSING_BLOCK_LABEL_197;
_L4:
            if (mTollway)
            {
                routesearch.setTollway(2);
            } else
            {
                routesearch.setTollway(1);
            }
            routesearch.setTraffic(s);
            routesearch.setGuide(3);
            routesearch.setStartPos((double)m_sp.getLatitudeE6() / 1000000D, (double)m_sp.getLongitudeE6() / 1000000D);
            routesearch.setGoalPos((double)m_gp.getLatitudeE6() / 1000000D, (double)m_gp.getLongitudeE6() / 1000000D);
            routesearch.setAPPID(m_appid);
            m_routeCtl = new RouteControl();
            routesearch.setRouteCtl(m_routeCtl);
            routesearch.setAPPID(m_appid);
            routesearch.search();
            return null;
_L2:
            s = "car";
              goto _L4
            s = "walk2";
              goto _L4
        }

        protected volatile void onPostExecute(Object obj)
        {
            onPostExecute((ArrayList)obj);
        }

        protected void onPostExecute(ArrayList arraylist)
        {
            m_overlays.clear();
            m_routeNodeInfoList.clear();
            m_routePinOverlayList.clear();
            if (m_routeCtl.count() != 0) goto _L2; else goto _L1
_L1:
            m_routeSearchTask = null;
            if (m_mapView != null)
            {
                m_mapView.reDraw();
            }
            m_routeOverlayListener.errorRouteSearch(RouteOverlay.this, 1);
_L5:
            return;
_L2:
            PopupOverlay popupoverlay;
            String s;
            Drawable drawable;
            int j;
            int k;
            popupoverlay = new PopupOverlay();
            popupoverlay.tapRegionType = 1;
            s = m_context.getPackageName();
            int i = m_context.getResources().getIdentifier("pin_route", "drawable", s);
            drawable = m_context.getResources().getDrawable(i);
            j = 0;
            k = 0;
_L6:
            if (k < -1 + m_routeCtl.count()) goto _L4; else goto _L3
_L3:
            int l;
            GeoPoint ageopoint[] = m_routeCtl.getGeoPoints();
            YRouteLineOverlay yroutelineoverlay = new YRouteLineOverlay(ageopoint);
            yroutelineoverlay.setWidth(10F);
            yroutelineoverlay.setColor(0xa00000ff);
            m_overlays.add(yroutelineoverlay);
            l = 0;
_L7:
            if (l < m_routePinOverlayList.size())
            {
                break MISSING_BLOCK_LABEL_771;
            }
            int i1 = m_context.getResources().getIdentifier("pin_start", "drawable", s);
            Drawable drawable1 = m_context.getResources().getDrawable(i1);
            YRoutePinOverlay yroutepinoverlay2 = new YRoutePinOverlay(drawable1);
            yroutepinoverlay2.setOnFocusChangeListener(popupoverlay);
            m_overlays.add(yroutepinoverlay2);
            yroutepinoverlay2.addPoint(m_sp, m_startTitle, null, null, 1);
            m_startPinOverlay = yroutepinoverlay2;
            m_startPinOverlay.visible = m_startPinVisible;
            int j1 = m_context.getResources().getIdentifier("pin_goal", "drawable", s);
            Drawable drawable2 = m_context.getResources().getDrawable(j1);
            YRoutePinOverlay yroutepinoverlay3 = new YRoutePinOverlay(drawable2);
            yroutepinoverlay3.setOnFocusChangeListener(popupoverlay);
            m_overlays.add(yroutepinoverlay3);
            yroutepinoverlay3.addPoint(m_gp, m_goalTitle, null, null, 1);
            m_goalPinOverlay = yroutepinoverlay3;
            m_goalPinOverlay.visible = m_goalPinVisible;
            m_overlays.add(popupoverlay);
            if (m_routeOverlayListener != null)
            {
                m_routeOverlayListener.finishRouteSearch(RouteOverlay.this);
            }
            m_routeSearchTask = null;
            if (m_mapView != null)
            {
                m_mapView.reDraw();
                return;
            }
              goto _L5
_L4:
            RLine rline = m_routeCtl.getRLine(k);
            if (k != 0 && rline != null && m_routeCtl.getGuideMesage(rline) != null)
            {
                YRoutePinOverlay yroutepinoverlay = new YRoutePinOverlay(drawable);
                yroutepinoverlay.setOnFocusChangeListener(popupoverlay);
                yroutepinoverlay.z = rline.getHierarchy();
                yroutepinoverlay.visible = m_routePinVisible;
                String.valueOf(j);
                if (k + 1 < m_routeCtl.count())
                {
                    m_routeCtl.getRLine(k + 1);
                }
                if (k - 1 >= 0)
                {
                    m_routeCtl.getRLine(k - 1);
                }
                yroutepinoverlay.addPoint(rline.getBeginPoint().getGeoPoint(), m_routeCtl.getGuideMesage(rline));
                j++;
                RouteNodeInfo routenodeinfo = new RouteNodeInfo(rline);
                m_routeNodeInfoList.add(routenodeinfo);
                m_routePinOverlayList.add(yroutepinoverlay);
            }
            k++;
              goto _L6
            YRoutePinOverlay yroutepinoverlay1 = (YRoutePinOverlay)m_routePinOverlayList.get(l);
            m_overlays.add(yroutepinoverlay1);
            l++;
              goto _L7
        }

        private YRouteSearchTask()
        {
            this$0 = RouteOverlay.this;
            super();
        }

        YRouteSearchTask(YRouteSearchTask yroutesearchtask)
        {
            this();
        }
    }


    public static final int ROUTINGERROR_NOROUTE = 1;
    public static final int TRAFFIC_CAR = 0;
    public static final int TRAFFIC_WALK = 2;
    private boolean mTollway;
    private String m_appid;
    private Context m_context;
    private YRoutePinOverlay m_goalPinOverlay;
    private boolean m_goalPinVisible;
    private String m_goalTitle;
    private GeoPoint m_gp;
    private MapView m_mapView;
    private ArrayList m_overlays;
    private RouteControl m_routeCtl;
    private ArrayList m_routeNodeInfoList;
    private RouteOverlayListener m_routeOverlayListener;
    private ArrayList m_routePinOverlayList;
    private boolean m_routePinVisible;
    private YRouteSearchTask m_routeSearchTask;
    private GeoPoint m_sp;
    private YRoutePinOverlay m_startPinOverlay;
    private boolean m_startPinVisible;
    private String m_startTitle;
    private int m_traffic;

    public RouteOverlay(Context context, String s)
    {
        m_context = null;
        m_appid = "";
        m_startTitle = "\u51FA\u767A\u5730";
        m_goalTitle = "\u76EE\u7684\u5730";
        m_sp = null;
        m_gp = null;
        m_traffic = 0;
        m_routeOverlayListener = null;
        m_startPinVisible = true;
        m_goalPinVisible = true;
        m_routePinVisible = true;
        m_overlays = new ArrayList();
        m_routeNodeInfoList = new ArrayList();
        m_startPinOverlay = null;
        m_goalPinOverlay = null;
        m_routePinOverlayList = new ArrayList();
        m_routeSearchTask = null;
        m_mapView = null;
        m_routeCtl = null;
        mTollway = true;
        m_context = context;
        m_appid = s;
    }

    public void cancel()
    {
        if (m_routeSearchTask != null)
        {
            m_routeSearchTask.cancel(false);
            m_routeSearchTask = null;
        }
    }

    public void draw(Canvas canvas, MapView mapview, boolean flag)
    {
        m_mapView = mapview;
        if (m_routeSearchTask == null)
        {
            int i = 0;
            while (i < m_overlays.size()) 
            {
                Overlay overlay = (Overlay)m_overlays.get(i);
                if (overlay instanceof YRoutePinOverlay)
                {
                    if (((YRoutePinOverlay)overlay).visible)
                    {
                        overlay.draw(canvas, m_mapView, flag);
                    }
                } else
                {
                    overlay.draw(canvas, m_mapView, flag);
                }
                i++;
            }
        }
    }

    public RouteNodeInfo[] getAllRouteNodeInfo()
    {
        if (m_routeNodeInfoList != null)
        {
            return (RouteNodeInfo[])m_routeNodeInfoList.toArray(new RouteNodeInfo[0]);
        } else
        {
            return null;
        }
    }

    public GeoPoint getCenter()
    {
        int i;
        int j;
        int k;
        int l;
        int i1;
        if (m_routeSearchTask != null)
        {
            return null;
        }
        i = 0;
        j = 0;
        k = 0;
        l = 0;
        i1 = 0;
_L2:
        Overlay overlay;
        GeoPoint geopoint;
        if (i1 >= m_overlays.size())
        {
            return new GeoPoint((i + j) / 2, (k + l) / 2);
        }
        overlay = (Overlay)m_overlays.get(i1);
        geopoint = overlay.getCenter();
        if (geopoint != null)
        {
            break; /* Loop/switch isn't completed */
        }
_L3:
        i1++;
        if (true) goto _L2; else goto _L1
_L1:
        int j1 = overlay.getLatSpanE6();
        int k1 = overlay.getLonSpanE6();
        if (i1 == 0)
        {
            i = geopoint.getLatitudeE6() - j1 / 2;
            j = geopoint.getLatitudeE6() + j1 / 2;
            k = geopoint.getLongitudeE6() - k1 / 2;
            l = geopoint.getLongitudeE6() + k1 / 2;
        } else
        {
            i = Math.min(i, geopoint.getLatitudeE6() - j1 / 2);
            j = Math.max(j, geopoint.getLatitudeE6() + j1 / 2);
            k = Math.min(k, geopoint.getLongitudeE6() - k1 / 2);
            l = Math.max(l, geopoint.getLongitudeE6() + k1 / 2);
        }
          goto _L3
        if (true) goto _L2; else goto _L4
_L4:
    }

    public double getDistance()
    {
        if (m_routeCtl != null)
        {
            return m_routeCtl.getMTotalDistance();
        } else
        {
            return 0.0D;
        }
    }

    public GeoPoint getGoalPos()
    {
        return m_gp;
    }

    public int getLatSpanE6()
    {
        int i = 0;
        if (m_routeSearchTask != null)
        {
            return 0;
        }
        int j = 0;
        do
        {
            if (j >= m_overlays.size())
            {
                return i;
            }
            i = Math.max(i, ((Overlay)m_overlays.get(j)).getLatSpanE6());
            j++;
        } while (true);
    }

    public int getLonSpanE6()
    {
        int i = 0;
        if (m_routeSearchTask != null)
        {
            return 0;
        }
        int j = 0;
        do
        {
            if (j >= m_overlays.size())
            {
                return i;
            }
            i = Math.max(i, ((Overlay)m_overlays.get(j)).getLonSpanE6());
            j++;
        } while (true);
    }

    public Object getObject()
    {
        return m_routeCtl;
    }

    public RouteNodeInfo getRouteNodeInfo(int i)
    {
        if (m_routeNodeInfoList != null && m_routeNodeInfoList.size() > i)
        {
            return (RouteNodeInfo)m_routeNodeInfoList.get(i);
        } else
        {
            return null;
        }
    }

    public GeoPoint getStartPos()
    {
        return m_sp;
    }

    public double getTime()
    {
        if (m_routeCtl != null)
        {
            return (double)m_routeCtl.defTotalTime;
        } else
        {
            return 0.0D;
        }
    }

    public boolean onTap(GeoPoint geopoint, MapView mapview)
    {
        if (m_routeSearchTask != null)
        {
            return false;
        }
        int i = -1 + m_overlays.size();
        do
        {
            if (i < 0)
            {
                mapview.invalidate();
                return false;
            }
            Overlay overlay = (Overlay)m_overlays.get(i);
            if (overlay instanceof YRoutePinOverlay)
            {
                if (((YRoutePinOverlay)overlay).visible && overlay.onTap(geopoint, mapview))
                {
                    mapview.invalidate();
                    return true;
                }
            } else
            if (overlay.onTap(geopoint, mapview))
            {
                mapview.invalidate();
                return true;
            }
            i--;
        } while (true);
    }

    public boolean search()
    {
        cancel();
        m_routeSearchTask = new YRouteSearchTask(null);
        m_routeSearchTask.execute(new String[] {
            ""
        });
        return false;
    }

    public void setGoalPinVisible(boolean flag)
    {
        m_goalPinVisible = flag;
        if (m_goalPinOverlay != null)
        {
            m_goalPinOverlay.visible = m_goalPinVisible;
        }
    }

    public void setGoalTitle(String s)
    {
        m_goalTitle = s;
    }

    public void setRouteOverlayListener(RouteOverlayListener routeoverlaylistener)
    {
        m_routeOverlayListener = routeoverlaylistener;
    }

    public void setRoutePinVisible(boolean flag)
    {
        m_routePinVisible = flag;
        if (m_routePinOverlayList == null) goto _L2; else goto _L1
_L1:
        int i = 0;
_L5:
        if (i < m_routePinOverlayList.size()) goto _L3; else goto _L2
_L2:
        return;
_L3:
        ((YRoutePinOverlay)m_routePinOverlayList.get(i)).visible = m_routePinVisible;
        i++;
        if (true) goto _L5; else goto _L4
_L4:
    }

    public void setRoutePos(GeoPoint geopoint, GeoPoint geopoint1, int i)
    {
        m_sp = geopoint;
        m_gp = geopoint1;
        m_traffic = i;
    }

    public void setStartPinVisible(boolean flag)
    {
        m_startPinVisible = flag;
        if (m_startPinOverlay != null)
        {
            m_startPinOverlay.visible = m_startPinVisible;
        }
    }

    public void setStartTitle(String s)
    {
        m_startTitle = s;
    }

    public void setTollway(boolean flag)
    {
        mTollway = flag;
    }























}

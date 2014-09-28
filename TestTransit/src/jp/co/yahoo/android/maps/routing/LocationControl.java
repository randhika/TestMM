// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.maps.routing;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import jp.co.yahoo.android.maps.asyncgetindoorlocation.IndoorLocation;
import jp.co.yahoo.android.maps.locationprovider.IndoorLocationManager;
import jp.co.yahoo.android.maps.locationprovider.LocationResultFilter;
import jp.co.yahoo.android.maps.viewlayer.Coordinate;

public class LocationControl
{
    public static interface LocationControlListener
    {

        public abstract void onYIndoorLocationChanged(LocationControl locationcontrol, IndoorLocation indoorlocation);

        public abstract void onYLocationChanged(LocationControl locationcontrol);

        public abstract void onYLocationError(LocationControl locationcontrol);
    }


    private static final int INTONET_MINMETER = 5;
    private int GPS_NG_TIME;
    private final LocationListener cellLocationListener = new LocationListener() {

        final LocationControl this$0;

        public void onLocationChanged(Location location)
        {
            this;
            JVM INSTR monitorenter ;
            if (location != null) goto _L2; else goto _L1
_L1:
            this;
            JVM INSTR monitorexit ;
            return;
_L2:
            startTimer(m_timeout);
            m_loca = location;
            m_LocationControlListener.onYLocationChanged(LocationControl.this);
            if (true) goto _L1; else goto _L3
_L3:
            Exception exception;
            exception;
            throw exception;
        }

        public void onProviderDisabled(String s)
        {
        }

        public void onProviderEnabled(String s)
        {
        }

        public void onStatusChanged(String s, int i, Bundle bundle)
        {
        }

            
            {
                this$0 = LocationControl.this;
                super();
            }
    };
    private int cheak_interval_time;
    private Coordinate dbg_points[];
    private final LocationResultFilter filter;
    private final LocationListener filteredListener = new LocationListener() {

        final LocationControl this$0;

        public void onLocationChanged(Location location)
        {
            this;
            JVM INSTR monitorenter ;
            if (location != null) goto _L2; else goto _L1
_L1:
            this;
            JVM INSTR monitorexit ;
            return;
_L2:
            if (!"cell".equals(location.getProvider()) && use_cellprovider)
            {
                indoorLocationManager.removeUpdates(cellLocationListener);
                use_cellprovider = false;
            }
            if (!"network".equals(location.getProvider()) || last_provider == null || !last_provider.equals("indoorWifi") || location.distanceTo(m_loca) <= 5F)
            {
                last_provider = location.getProvider();
                startTimer(m_timeout);
                m_loca = location;
                if (m_LocationControlListener != null)
                {
                    if ("indoorWifi".equals(location.getProvider()))
                    {
                        Bundle bundle = location.getExtras();
                        Object aobj[] = new Object[1];
                        aobj[0] = Double.valueOf(location.getLatitude());
                        String s = String.format("%.6f", aobj);
                        Object aobj1[] = new Object[1];
                        aobj1[0] = Double.valueOf(location.getLongitude());
                        IndoorLocation indoorlocation = new IndoorLocation(s, String.format("%.6f", aobj1), (new StringBuilder()).append(bundle.getInt("yindoor-floorid")).toString(), (new StringBuilder()).append(bundle.getInt("yindoor-indoorid")).toString());
                        m_LocationControlListener.onYIndoorLocationChanged(LocationControl.this, indoorlocation);
                    }
                    m_LocationControlListener.onYLocationChanged(LocationControl.this);
                }
            }
            if (true) goto _L1; else goto _L3
_L3:
            Exception exception;
            exception;
            throw exception;
        }

        public void onProviderDisabled(String s)
        {
        }

        public void onProviderEnabled(String s)
        {
            if ("gps".equals(s))
            {
                indoorLocationManager.requestLocationUpdates("gps", 500L, 0.0F, this);
            } else
            if ("network".equals(s))
            {
                indoorLocationManager.requestLocationUpdates("network", 1000L, 0.0F, this);
                return;
            }
        }

        public void onStatusChanged(String s, int i, Bundle bundle)
        {
        }

            
            {
                this$0 = LocationControl.this;
                super();
            }
    };
    private final IndoorLocationManager indoorLocationManager;
    private String last_provider;
    private Handler mHandler;
    private LocationControlListener m_LocationControlListener;
    private boolean m_gps_flag;
    private Location m_loca;
    private int m_mode;
    private int m_timeout;
    private final LocationControlListener nullLocationControlListener = new LocationControlListener() {

        final LocationControl this$0;

        public void onYIndoorLocationChanged(LocationControl locationcontrol, IndoorLocation indoorlocation)
        {
        }

        public void onYLocationChanged(LocationControl locationcontrol)
        {
        }

        public void onYLocationError(LocationControl locationcontrol)
        {
        }

            
            {
                this$0 = LocationControl.this;
                super();
            }
    };
    private int priority_time;
    private int significant_Time;
    private boolean startingLocation;
    private boolean use_cellprovider;

    public LocationControl(Context context, LocationControlListener locationcontrollistener)
    {
        cheak_interval_time = 3000;
        priority_time = 0;
        significant_Time = 12000;
        use_cellprovider = false;
        last_provider = null;
        filter = new LocationResultFilter(filteredListener);
        m_loca = null;
        m_LocationControlListener = null;
        startingLocation = false;
        m_gps_flag = false;
        mHandler = new Handler();
        GPS_NG_TIME = 180;
        m_mode = 0;
        m_timeout = 30000;
        dbg_points = null;
        indoorLocationManager = new IndoorLocationManager(context);
        filter.setIndoorWiFiPrioritizedMillis(priority_time);
        filter.setSignificantTimeDeltaMillis(significant_Time);
        filter.setGpsPrioritizedMillis(5000);
        filter.reset();
        use_cellprovider = true;
        setLocationControlListener(locationcontrollistener);
    }

    private void startTimer(int i)
    {
        if (mHandler != null)
        {
            mHandler.removeMessages(0);
            mHandler = null;
            m_mode = 0;
        }
        m_timeout = i;
        if (i == 0)
        {
            return;
        }
        if (dbg_points != null)
        {
            m_timeout = 2000;
        }
        mHandler = new Handler() {

            final LocationControl this$0;

            public void handleMessage(Message message)
            {
                if (mHandler == null)
                {
                    return;
                }
                if (m_LocationControlListener != null)
                {
                    if (m_gps_flag)
                    {
                        m_LocationControlListener.onYLocationError(LocationControl.this);
                    } else
                    if (m_mode == 0)
                    {
                        m_mode = 1;
                    } else
                    {
                        m_LocationControlListener.onYLocationError(LocationControl.this);
                    }
                }
                sendEmptyMessageDelayed(0, m_timeout);
            }

            
            {
                this$0 = LocationControl.this;
                super();
            }
        };
        mHandler.sendEmptyMessageDelayed(0, m_timeout);
    }

    public int getCheak_interval_time()
    {
        return cheak_interval_time;
    }

    public Location getLocation()
    {
        return m_loca;
    }

    public int getPriority_time()
    {
        return priority_time;
    }

    public int getSignificant_Time()
    {
        return significant_Time;
    }

    public boolean isStartLocation()
    {
        this;
        JVM INSTR monitorenter ;
        boolean flag = startingLocation;
        this;
        JVM INSTR monitorexit ;
        return flag;
        Exception exception;
        exception;
        throw exception;
    }

    public void setCheak_interval_time(int i)
    {
        cheak_interval_time = i;
    }

    public void setDebgData(Coordinate acoordinate[])
    {
        int i = 0;
        do
        {
            if (i >= acoordinate.length)
            {
                dbg_points = acoordinate;
                return;
            }
            Coordinate coordinate = acoordinate[i];
            coordinate.lon = 0.00029999999999999997D + coordinate.lon;
            i++;
        } while (true);
    }

    public void setLocationControlListener(LocationControlListener locationcontrollistener)
    {
        if (locationcontrollistener != null)
        {
            m_LocationControlListener = locationcontrollistener;
            return;
        } else
        {
            m_LocationControlListener = nullLocationControlListener;
            return;
        }
    }

    public void setPriority_time(int i)
    {
        priority_time = i;
    }

    public void setSignificant_Time(int i)
    {
        significant_Time = i;
    }

    public void setTimer(int i)
    {
        m_timeout = i;
    }

    public void startLocation(boolean flag)
    {
        this;
        JVM INSTR monitorenter ;
        m_gps_flag = flag;
        startTimer(m_timeout);
        filter.reset();
        indoorLocationManager.removeUpdates(filter);
        if (!flag)
        {
            break MISSING_BLOCK_LABEL_62;
        }
        indoorLocationManager.requestLocationUpdates("gps", 500L, 0.0F, filter);
_L1:
        startingLocation = true;
        this;
        JVM INSTR monitorexit ;
        return;
        indoorLocationManager.requestLocationUpdates("gps", 1000L, 0.0F, filter);
        indoorLocationManager.requestLocationUpdates("network", 500L, 0.0F, filter);
        indoorLocationManager.requestLocationUpdates("cell", 500L, 0.0F, cellLocationListener);
        indoorLocationManager.requestLocationUpdates("indoorWifi", cheak_interval_time, 0.0F, filter);
          goto _L1
        Exception exception;
        exception;
        throw exception;
    }

    public void stopLocation()
    {
        this;
        JVM INSTR monitorenter ;
        if (mHandler != null)
        {
            mHandler.removeMessages(0);
            mHandler = null;
        }
        indoorLocationManager.removeUpdates(filter);
        indoorLocationManager.removeUpdates(cellLocationListener);
        startingLocation = false;
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }















}

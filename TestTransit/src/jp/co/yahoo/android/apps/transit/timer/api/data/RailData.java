// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.timer.api.data;

import java.io.Serializable;
import java.util.ArrayList;

// Referenced classes of package jp.co.yahoo.android.apps.transit.timer.api.data:
//            StationData

public class RailData
    implements Serializable
{
    public static class RailDirectionData
        implements Serializable
    {

        private static final long serialVersionUID = 0xb98ce82791f771c7L;
        private String direction;
        private String drivedayKind;
        private String groupid;
        private String source;

        public String getDirection()
        {
            return direction;
        }

        public String getDrivedayKind()
        {
            return drivedayKind;
        }

        public String getGroupid()
        {
            return groupid;
        }

        public String getSource()
        {
            return source;
        }

        public void setDirection(String s)
        {
            direction = s;
        }

        public void setDrivedayKind(String s)
        {
            drivedayKind = s;
        }

        public void setGroupid(String s)
        {
            groupid = s;
        }

        public void setSource(String s)
        {
            source = s;
        }

        public String toString()
        {
            return (new StringBuilder()).append(direction).append("\u65B9\u9762").toString();
        }

        public RailDirectionData()
        {
        }
    }


    private static final long serialVersionUID = 0x81ad4998a21c1932L;
    private ArrayList railDirection;
    private StationData station;

    public RailData()
    {
    }

    public ArrayList getRailDirection()
    {
        return railDirection;
    }

    public StationData getStation()
    {
        return station;
    }

    public void setRailDirection(ArrayList arraylist)
    {
        railDirection = arraylist;
    }

    public void setStation(StationData stationdata)
    {
        station = stationdata;
    }
}

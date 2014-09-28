// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.maps.routing;


// Referenced classes of package jp.co.yahoo.android.maps.routing:
//            RLine

public class RouteNodeInfo
{

    private int m_direction;
    private double m_distance;
    private int m_floorLevel;
    private double m_time;

    public RouteNodeInfo(Object obj)
    {
        m_direction = 0;
        m_distance = 0.0D;
        m_time = 0.0D;
        m_floorLevel = 0;
        RLine rline = (RLine)obj;
        m_floorLevel = rline.floorLevel;
        m_time = rline.time;
        m_direction = Integer.parseInt(rline.direction);
        m_distance = rline.distance;
    }

    public int getDirection()
    {
        return m_direction;
    }

    public double getDistance()
    {
        return m_distance;
    }

    public int getFloorLevel()
    {
        return m_floorLevel;
    }

    public double getTime()
    {
        return m_time;
    }
}

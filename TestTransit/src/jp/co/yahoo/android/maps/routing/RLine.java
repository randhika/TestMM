// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.maps.routing;

import java.util.ArrayList;
import jp.co.yahoo.android.maps.GeoPoint;

// Referenced classes of package jp.co.yahoo.android.maps.routing:
//            YRouteNodeInfo, GPoint

public class RLine
    implements YRouteNodeInfo
{

    public String attributeKey;
    public RLine backRLine;
    public String direction;
    public double distance;
    public String floor;
    public int floorLevel;
    public int lno;
    public ArrayList m_points;
    public RLine nextRLine;
    public String roadName;
    public int roadType;
    public double time;
    public int type;
    public int updownType;

    public RLine()
    {
        roadName = "";
        distance = 0.0D;
        time = 0.0D;
        floorLevel = 0;
        floor = "";
        m_points = new ArrayList();
        lno = 0;
        attributeKey = "";
        nextRLine = null;
        backRLine = null;
    }

    public void addPoint(GPoint gpoint)
    {
        m_points.add(gpoint);
    }

    public int count()
    {
        return m_points.size();
    }

    public String getAttributeKey()
    {
        return attributeKey;
    }

    public int getBackHierarchy()
    {
        if (backRLine == null)
        {
            return floorLevel;
        } else
        {
            return backRLine.floorLevel;
        }
    }

    public GPoint getBeginPoint()
    {
        if (m_points.size() > 0)
        {
            return (GPoint)m_points.get(0);
        } else
        {
            return null;
        }
    }

    public int getCrossKind()
    {
        return 0;
    }

    public GeoPoint getGeoPoint()
    {
        GPoint gpoint = getBeginPoint();
        if (gpoint == null)
        {
            return null;
        } else
        {
            return gpoint.getGeoPoint();
        }
    }

    public GeoPoint[] getGeoPoints()
    {
        GeoPoint ageopoint[] = new GeoPoint[m_points.size()];
        int i = 0;
        do
        {
            if (i >= ageopoint.length)
            {
                return ageopoint;
            }
            ageopoint[i] = ((GPoint)m_points.get(i)).getGeoPoint();
            i++;
        } while (true);
    }

    public String getGuideInfo()
    {
        switch (getPassType())
        {
        default:
            return "";

        case 0: // '\0'
            return getNextGuideInfo();

        case 1: // '\001'
            return (new StringBuilder("\u968E\u6BB5\u3092")).append(getUpdownString()).toString();

        case 2: // '\002'
            return "\u30A8\u30B9\u30AB\u30EC\u30FC\u30BF\u30FC\u306B\u4E57\u308B";

        case 3: // '\003'
            return "\u30A8\u30EC\u30D9\u30FC\u30BF\u30FC\u306B\u4E57\u308B";

        case 4: // '\004'
            return "\u30B9\u30ED\u30FC\u30D7\u306B\u4E57\u308B";

        case 5: // '\005'
            return "\u52D5\u304F\u6B69\u9053\u306B\u4E57\u308B";

        case 6: // '\006'
            return "\u6BB5\u5DEE\u304C\u3042\u308B";

        case 7: // '\007'
            return "\u6539\u672D\u3092\u901A\u308B";

        case -1: 
            return "\u76EE\u7684\u5730\u5468\u8FBA";
        }
    }

    public int getHierarchy()
    {
        return floorLevel;
    }

    public GPoint getLastPoint()
    {
        if (m_points.size() > 0)
        {
            return (GPoint)m_points.get(-1 + m_points.size());
        } else
        {
            return null;
        }
    }

    public double getLinkDistance()
    {
        return distance;
    }

    public String getNextGuideInfo()
    {
        String s;
        s = "";
        if (nextRLine == null)
        {
            return (new StringBuilder("\u76EE\u7684\u5730\u307E\u3067")).append((int)distance).append("m").toString();
        }
        nextRLine.getPassType();
        JVM INSTR tableswitch -1 7: default 92
    //                   -1 233
    //                   0 120
    //                   1 191
    //                   2 197
    //                   3 203
    //                   4 209
    //                   5 215
    //                   6 221
    //                   7 227;
           goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8 _L9 _L10
_L1:
        return (new StringBuilder(String.valueOf(s))).append((int)distance).append("m").toString();
_L3:
        if (nextRLine.getTernString().equals(""))
        {
            s = (new StringBuilder(String.valueOf(s))).append("\u76EE\u7684\u5730\u307E\u3067").toString();
        } else
        {
            s = (new StringBuilder(String.valueOf(s))).append(nextRLine.getTernString()).append("\u307E\u3067").toString();
        }
        continue; /* Loop/switch isn't completed */
_L4:
        s = "\u6B21\u306E\u968E\u6BB5\u307E\u3067";
        continue; /* Loop/switch isn't completed */
_L5:
        s = "\u6B21\u306E\u30A8\u30B9\u30AB\u30EC\u30FC\u30BF\u30FC\u307E\u3067";
        continue; /* Loop/switch isn't completed */
_L6:
        s = "\u6B21\u306E\u30A8\u30EC\u30D9\u30FC\u30BF\u30FC\u307E\u3067";
        continue; /* Loop/switch isn't completed */
_L7:
        s = "\u6B21\u306E\u30B9\u30ED\u30FC\u30D7\u307E\u3067";
        continue; /* Loop/switch isn't completed */
_L8:
        s = "\u6B21\u306E\u52D5\u304F\u6B69\u9053\u307E\u3067";
        continue; /* Loop/switch isn't completed */
_L9:
        s = "\u6B21\u306E\u6BB5\u5DEE\u307E\u3067";
        continue; /* Loop/switch isn't completed */
_L10:
        s = "\u6B21\u306E\u6539\u672D\u307E\u3067";
        continue; /* Loop/switch isn't completed */
_L2:
        s = "\u76EE\u7684\u5730\u307E\u3067";
        if (true) goto _L1; else goto _L11
_L11:
    }

    public int getNextHierarchy()
    {
        if (nextRLine == null)
        {
            return floorLevel;
        } else
        {
            return nextRLine.floorLevel;
        }
    }

    public int getNodeType()
    {
        return type;
    }

    public int getPassType()
    {
        return roadType;
    }

    public GPoint getPoint(int i)
    {
        return (GPoint)m_points.get(i);
    }

    public void getPoints(ArrayList arraylist)
    {
        int i = 0;
        do
        {
            if (i >= m_points.size())
            {
                return;
            }
            arraylist.add((GPoint)m_points.get(i));
            i++;
        } while (true);
    }

    public String getTernString()
    {
        switch (Integer.parseInt(direction))
        {
        default:
            return "";

        case 0: // '\0'
            return "\u901A\u8DEF";

        case 1: // '\001'
            return "\u76F4\u9032 ";

        case 2: // '\002'
            return "\u53F3\u6298";

        case 3: // '\003'
            return "\u5DE6\u6298";
        }
    }

    public String getUpdownString()
    {
        switch (updownType)
        {
        default:
            return "";

        case 1: // '\001'
            return "\u4E0A\u304C\u308B";

        case 2: // '\002'
            return "\u9032\u3080";

        case 3: // '\003'
            return "\u4E0B\u304C\u308B";
        }
    }
}

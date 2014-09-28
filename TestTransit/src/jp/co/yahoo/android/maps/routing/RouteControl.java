// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.maps.routing;

import java.util.ArrayList;
import jp.co.yahoo.android.maps.GeoPoint;
import jp.co.yahoo.android.maps.viewlayer.Coordinate;
import jp.co.yahoo.android.maps.viewlayer.LLCalculation;

// Referenced classes of package jp.co.yahoo.android.maps.routing:
//            GPoint, RLine

public class RouteControl
{

    public long defTotalTime;
    public long errorCode;
    ArrayList m_rlines;
    private GPoint m_select_line[];

    public RouteControl()
    {
        m_rlines = new ArrayList();
        m_select_line = new GPoint[2];
        errorCode = 0L;
    }

    public static double getIntersectingDist(double d, double d1, double d2, double d3, 
            double d4, double d5)
    {
        return (new GPoint(d, d1)).distance(getIntersectingPoint(d, d1, d2, d3, d4, d5));
    }

    public static double getIntersectingDist(GPoint gpoint, GPoint gpoint1, GPoint gpoint2)
    {
        return getIntersectingDist(gpoint.x, gpoint.y, gpoint1.x, gpoint1.y, gpoint2.x, gpoint2.y);
    }

    public static GPoint getIntersectingPoint(double d, double d1, double d2, double d3, 
            double d4, double d5)
    {
        GPoint gpoint;
        GPoint gpoint3;
label0:
        {
            gpoint = new GPoint();
            GPoint gpoint1 = new GPoint();
            GPoint gpoint2 = new GPoint();
            gpoint3 = new GPoint();
            GPoint gpoint4 = new GPoint();
            GPoint gpoint5 = new GPoint();
            gpoint.x = d2;
            gpoint.y = d3;
            gpoint5.x = d4;
            gpoint5.y = d5;
            gpoint1.x = d4 - d2;
            gpoint1.y = d5 - d3;
            double d6 = Math.sqrt(gpoint1.x * gpoint1.x + gpoint1.y * gpoint1.y);
            gpoint1.x = gpoint1.x / d6;
            gpoint1.y = gpoint1.y / d6;
            gpoint2.x = d;
            gpoint2.y = d1;
            gpoint4.x = gpoint2.x - gpoint.x;
            gpoint4.y = gpoint2.y - gpoint.y;
            double d7 = gpoint1.x * gpoint4.x + gpoint1.y * gpoint4.y;
            gpoint3.x = gpoint.x + d7 * gpoint1.x;
            gpoint3.y = gpoint.y + d7 * gpoint1.y;
            double d8 = gpoint3.x - gpoint2.x;
            double d9 = gpoint3.y - gpoint2.y;
            double d10 = -d9;
            double d11 = -(d8 * gpoint2.y - d9 * gpoint2.x);
            double d12;
            double d13;
            GPoint gpoint6;
            if (d8 > 0.0D)
            {
                d12 = 1.0D + gpoint3.x;
            } else
            {
                d12 = gpoint3.x - 1.0D;
            }
            d13 = (d11 + d10 * d12) / -d8;
            gpoint6 = new GPoint(d12, d13);
            if (!intersection(gpoint2, gpoint6, gpoint, gpoint5))
            {
                if (gpoint2.distance(gpoint) <= gpoint2.distance(gpoint5))
                {
                    break label0;
                }
                gpoint3.x = gpoint5.x;
                gpoint3.y = gpoint5.y;
            }
            return gpoint3;
        }
        gpoint3.x = gpoint.x;
        gpoint3.y = gpoint.y;
        return gpoint3;
    }

    public static GPoint getIntersectingPoint(GPoint gpoint, GPoint gpoint1, GPoint gpoint2)
    {
        return getIntersectingPoint(gpoint.x, gpoint.y, gpoint1.x, gpoint1.y, gpoint2.x, gpoint2.y);
    }

    public static boolean intersection(GPoint gpoint, GPoint gpoint1, GPoint gpoint2, GPoint gpoint3)
    {
        return ((gpoint.x - gpoint1.x) * (gpoint2.y - gpoint.y) + (gpoint.y - gpoint1.y) * (gpoint.x - gpoint2.x)) * ((gpoint.x - gpoint1.x) * (gpoint3.y - gpoint.y) + (gpoint.y - gpoint1.y) * (gpoint.x - gpoint3.x)) < 0.0D && ((gpoint2.x - gpoint3.x) * (gpoint.y - gpoint2.y) + (gpoint2.y - gpoint3.y) * (gpoint2.x - gpoint.x)) * ((gpoint2.x - gpoint3.x) * (gpoint1.y - gpoint2.y) + (gpoint2.y - gpoint3.y) * (gpoint2.x - gpoint1.x)) < 0.0D;
    }

    public static boolean intersectionScreen(GPoint gpoint, GPoint gpoint1, GPoint gpoint2, int i, int j)
    {
        GPoint gpoint3;
        GPoint gpoint4;
        GPoint gpoint5;
        GPoint gpoint6;
        gpoint3 = new GPoint();
        gpoint4 = new GPoint();
        gpoint5 = new GPoint();
        gpoint6 = new GPoint();
        gpoint3.x = gpoint.x;
        gpoint3.y = gpoint.y;
        gpoint4.x = gpoint1.x;
        gpoint4.y = gpoint1.y;
        gpoint5.x = gpoint2.x;
        gpoint5.y = gpoint2.y;
        gpoint6.x = gpoint5.x;
        gpoint6.y = gpoint5.y + (double)j;
        break MISSING_BLOCK_LABEL_114;
        if ((gpoint5.x > gpoint3.x || gpoint5.x + (double)i < gpoint3.x || gpoint5.y > gpoint3.y || gpoint5.y + (double)j < gpoint3.y) && (gpoint5.x > gpoint4.x || gpoint5.x + (double)i < gpoint4.x || gpoint5.y > gpoint4.y || gpoint5.y + (double)j < gpoint4.y) && !intersection(gpoint3, gpoint4, gpoint5, gpoint6))
        {
            gpoint6.x = gpoint5.x + (double)i;
            gpoint6.y = gpoint5.y;
            if (!intersection(gpoint3, gpoint4, gpoint5, gpoint6))
            {
                gpoint5.x = gpoint2.x + (double)i;
                gpoint5.y = gpoint2.y;
                gpoint6.x = gpoint5.x;
                gpoint6.y = gpoint5.y + (double)j;
                if (!intersection(gpoint3, gpoint4, gpoint5, gpoint6))
                {
                    gpoint5.x = gpoint2.x;
                    gpoint5.y = gpoint2.y + (double)j;
                    gpoint6.x = gpoint5.x + (double)i;
                    gpoint6.y = gpoint5.y + (double)j;
                    if (!intersection(gpoint3, gpoint4, gpoint5, gpoint6))
                    {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public void addRLine(RLine rline)
    {
        m_rlines.add(rline);
    }

    public double cmpLineAndPoint(GPoint gpoint)
    {
        GPoint gpoint1 = null;
        GPoint gpoint2 = null;
        double d = 999999999D;
        ArrayList arraylist = new ArrayList();
        getPoints(arraylist);
        int i = 1;
        do
        {
            if (i >= arraylist.size())
            {
                m_select_line[0] = gpoint1;
                m_select_line[1] = gpoint2;
                return d;
            }
            GPoint gpoint3 = (GPoint)arraylist.get(i - 1);
            GPoint gpoint4 = (GPoint)arraylist.get(i);
            double d1 = getIntersectingDist(gpoint, gpoint3, gpoint4);
            if (d1 <= d)
            {
                d = d1;
                gpoint1 = gpoint3;
                gpoint2 = gpoint4;
            }
            i++;
        } while (true);
    }

    public int count()
    {
        return m_rlines.size();
    }

    public GPoint getBeginPoint()
    {
        if (m_rlines.size() > 0)
        {
            RLine rline = (RLine)m_rlines.get(0);
            if (rline != null)
            {
                return rline.getBeginPoint();
            }
        }
        return null;
    }

    public Coordinate[] getCoordinates()
    {
        ArrayList arraylist = new ArrayList();
        getPoints(arraylist);
        Coordinate acoordinate[] = new Coordinate[arraylist.size()];
        int i = 0;
        do
        {
            if (i >= acoordinate.length)
            {
                return acoordinate;
            }
            acoordinate[i] = ((GPoint)arraylist.get(i)).getCoordinate();
            i++;
        } while (true);
    }

    public GeoPoint[] getGeoPoints()
    {
        ArrayList arraylist = new ArrayList();
        getPoints(arraylist);
        GeoPoint ageopoint[] = new GeoPoint[arraylist.size()];
        int i = 0;
        do
        {
            if (i >= ageopoint.length)
            {
                return ageopoint;
            }
            ageopoint[i] = ((GPoint)arraylist.get(i)).getGeoPoint();
            i++;
        } while (true);
    }

    public String getGuideMesage(RLine rline)
    {
        int i;
        int k;
        int l;
        if (rline == null)
        {
            return "";
        }
        i = -1;
        int j = (int)Long.parseLong(rline.direction);
        if (j == 0 || j == 1 || j == 11)
        {
            return null;
        }
        k = (int)rline.distance;
        l = 1 + rline.lno;
_L26:
        if (l < count()) goto _L2; else goto _L1
_L1:
        if (i == -1)
        {
            return null;
        }
        break; /* Loop/switch isn't completed */
_L2:
        int i1;
label0:
        {
            RLine rline1 = getRLine(l);
            if (rline1.direction != null)
            {
                i1 = (int)Long.parseLong(rline1.direction);
                if (i1 != 0 && i1 != 1 && i1 != 11)
                {
                    break label0;
                }
                k += (int)rline1.distance;
            }
            l++;
            continue; /* Loop/switch isn't completed */
        }
        i = i1;
        if (true) goto _L1; else goto _L3
_L3:
        i;
        JVM INSTR tableswitch 0 19: default 228
    //                   0 251
    //                   1 274
    //                   2 297
    //                   3 383
    //                   4 408
    //                   5 433
    //                   6 458
    //                   7 483
    //                   8 228
    //                   9 508
    //                   10 533
    //                   11 228
    //                   12 558
    //                   13 583
    //                   14 608
    //                   15 633
    //                   16 658
    //                   17 683
    //                   18 708
    //                   19 733;
           goto _L4 _L5 _L6 _L7 _L8 _L9 _L10 _L11 _L12 _L4 _L13 _L14 _L4 _L15 _L16 _L17 _L18 _L19 _L20 _L21 _L22
_L4:
        (new StringBuilder(String.valueOf(""))).append("\u6B21\u306E\u30DD\u30A4\u30F3\u30C8\u307E\u3067").toString();
        return null;
_L5:
        (new StringBuilder(String.valueOf(""))).append("\u6B21\u306E\u30DD\u30A4\u30F3\u30C8\u307E\u3067").toString();
        return null;
_L6:
        (new StringBuilder(String.valueOf(""))).append("\u6B21\u306E\u30DD\u30A4\u30F3\u30C8\u307E\u3067").toString();
        return null;
_L7:
        String s = (new StringBuilder(String.valueOf(""))).append("\u6B21\u306E\u53F3\u6298\u307E\u3067").toString();
_L24:
        if ((double)k > 1000D)
        {
            double d = (double)k / 1000D;
            StringBuilder stringbuilder = new StringBuilder(String.valueOf(s));
            Object aobj[] = new Object[1];
            aobj[0] = Double.valueOf(d);
            return stringbuilder.append(String.format("%.1fkm", aobj)).toString();
        }
        break; /* Loop/switch isn't completed */
_L8:
        s = (new StringBuilder(String.valueOf(""))).append("\u6B21\u306E\u5DE6\u6298\u307E\u3067").toString();
        continue; /* Loop/switch isn't completed */
_L9:
        s = (new StringBuilder(String.valueOf(""))).append("\u6B21\u306E\u659C\u3081\u524D\u53F3\u6298\u307E\u3067").toString();
        continue; /* Loop/switch isn't completed */
_L10:
        s = (new StringBuilder(String.valueOf(""))).append("\u6B21\u306E\u659C\u3081\u524D\u5DE6\u6298\u307E\u3067").toString();
        continue; /* Loop/switch isn't completed */
_L11:
        s = (new StringBuilder(String.valueOf(""))).append("\u6B21\u306E\u659C\u3081\u5F8C\u308D\u53F3\u6298\u307E\u3067").toString();
        continue; /* Loop/switch isn't completed */
_L12:
        s = (new StringBuilder(String.valueOf(""))).append("\u6B21\u306E\u659C\u3081\u5F8C\u308D\u5DE6\u6298\u307E\u3067").toString();
        continue; /* Loop/switch isn't completed */
_L13:
        s = (new StringBuilder(String.valueOf(""))).append("\u51FA\u767A\u5730 ").toString();
        continue; /* Loop/switch isn't completed */
_L14:
        s = (new StringBuilder(String.valueOf(""))).append("\u76EE\u7684\u5730\u307E\u3067").toString();
        continue; /* Loop/switch isn't completed */
_L15:
        s = (new StringBuilder(String.valueOf(""))).append("\u6B21\u306E\u6A2A\u65AD\u6B69\u9053\u307E\u3067").toString();
        continue; /* Loop/switch isn't completed */
_L16:
        s = (new StringBuilder(String.valueOf(""))).append("\u6B21\u306E\u9053\u8DEF\u6A2A\u65AD\u307E\u3067").toString();
        continue; /* Loop/switch isn't completed */
_L17:
        s = (new StringBuilder(String.valueOf(""))).append("\u6B21\u306E\u6B69\u9053\u6A4B\u307E\u3067").toString();
        continue; /* Loop/switch isn't completed */
_L18:
        s = (new StringBuilder(String.valueOf(""))).append("\u6B21\u306E\u8E0F\u5207\u307E\u3067").toString();
        continue; /* Loop/switch isn't completed */
_L19:
        s = (new StringBuilder(String.valueOf(""))).append("\u6B21\u306E\u9023\u7D61\u9053\u8DEF\u5165\u53E3\u307E\u3067").toString();
        continue; /* Loop/switch isn't completed */
_L20:
        s = (new StringBuilder(String.valueOf(""))).append("\u6B21\u306E\u5C4B\u5185\u9053\u8DEF\u5165\u53E3\u307E\u3067").toString();
        continue; /* Loop/switch isn't completed */
_L21:
        s = (new StringBuilder(String.valueOf(""))).append("\u6B21\u306E\u6577\u5730\u5185\u9053\u8DEF\u5165\u53E3\u307E\u3067").toString();
        continue; /* Loop/switch isn't completed */
_L22:
        s = (new StringBuilder(String.valueOf(""))).append("\u6B21\u306E\u6B69\u9053\u5165\u53E3\u307E\u3067").toString();
        if (true) goto _L24; else goto _L23
_L23:
        return (new StringBuilder(String.valueOf(s))).append(k).append("m").toString();
        if (true) goto _L26; else goto _L25
_L25:
    }

    public GPoint getLastPoint()
    {
        if (m_rlines.size() > 0)
        {
            RLine rline = (RLine)m_rlines.get(-1 + m_rlines.size());
            if (rline != null)
            {
                if (rline.count() == 0)
                {
                    rline = (RLine)m_rlines.get(-2 + m_rlines.size());
                }
                return rline.getLastPoint();
            }
        }
        return null;
    }

    public double getMDistanceNearPointToPoint(GPoint gpoint, GPoint gpoint1)
    {
        GPoint gpoint2 = getNearPoint(gpoint);
        GPoint gpoint3 = getPoint(gpoint2.l_no, gpoint2.p_no);
        return gpoint2.distanceM(gpoint3) + getMDistancePointToPoint(gpoint3, gpoint1);
    }

    public double getMDistancePointToPoint(GPoint gpoint, GPoint gpoint1)
    {
        if (gpoint1.p_no != gpoint.p_no || gpoint1.l_no != gpoint.l_no) goto _L2; else goto _L1
_L1:
        double d = 0.0D;
_L9:
        return d;
_L2:
        ArrayList arraylist;
        int i;
        int j;
        arraylist = new ArrayList();
        getPoints(arraylist);
        i = -1;
        j = 0;
_L7:
        if (j < arraylist.size()) goto _L4; else goto _L3
_L3:
        if (i == -1)
        {
            return -1D;
        }
        break; /* Loop/switch isn't completed */
_L4:
        GPoint gpoint2 = (GPoint)arraylist.get(j);
        if (gpoint2.p_no != gpoint.p_no || gpoint2.l_no != gpoint.l_no)
        {
            break; /* Loop/switch isn't completed */
        }
        i = j;
        if (true) goto _L3; else goto _L5
_L5:
        j++;
        if (true) goto _L7; else goto _L6
_L6:
        int k;
        d = 0.0D;
        k = i + 1;
_L11:
        if (k >= arraylist.size()) goto _L9; else goto _L8
_L8:
        GPoint gpoint4;
        GPoint gpoint3 = (GPoint)arraylist.get(k - 1);
        gpoint4 = (GPoint)arraylist.get(k);
        d += gpoint3.distanceM(gpoint4);
        if (gpoint4.p_no == gpoint1.p_no && gpoint4.l_no == gpoint1.l_no) goto _L9; else goto _L10
_L10:
        k++;
          goto _L11
    }

    public double getMDistanceToNearPoint(GPoint gpoint)
    {
        GPoint gpoint1 = getNearPoint(gpoint);
        if (gpoint1 != null)
        {
            return LLCalculation.distance(gpoint1.y, gpoint1.x, gpoint.y, gpoint.x);
        } else
        {
            return -1D;
        }
    }

    public double getMTotalDistance()
    {
        long l = 0L;
        int i = 0;
        do
        {
            if (i >= m_rlines.size())
            {
                return (double)l;
            }
            RLine rline = (RLine)m_rlines.get(i);
            l = (long)((double)l + rline.distance);
            i++;
        } while (true);
    }

    public double getMTotalDistanceByLNo(int i)
    {
        long l = 0L;
        int j = i;
        do
        {
            if (j >= m_rlines.size())
            {
                return (double)l;
            }
            RLine rline = (RLine)m_rlines.get(j);
            l = (long)((double)l + rline.distance);
            j++;
        } while (true);
    }

    public long getMTotalTime()
    {
        double d = 0.0D;
        int i = 0;
        do
        {
            if (i >= m_rlines.size())
            {
                return (long)d;
            }
            d += ((RLine)m_rlines.get(i)).time;
            i++;
        } while (true);
    }

    public long getMTotalTimeByLNo(int i)
    {
        long l = 0L;
        int j = i;
        do
        {
            if (j >= m_rlines.size())
            {
                return l;
            }
            RLine rline = (RLine)m_rlines.get(j);
            l = (long)((double)l + rline.time);
            j++;
        } while (true);
    }

    public GPoint getNearPoint(GPoint gpoint)
    {
        if (m_select_line[0] != null && m_select_line[1] != null)
        {
            GPoint gpoint1 = getIntersectingPoint(gpoint, m_select_line[0], m_select_line[1]);
            if (gpoint1 == null)
            {
                return null;
            } else
            {
                gpoint1.l_no = m_select_line[1].l_no;
                gpoint1.p_no = m_select_line[1].p_no;
                return gpoint1;
            }
        } else
        {
            return null;
        }
    }

    public RLine getNextRoute()
    {
        if (m_select_line[0] != null && m_select_line[1] != null)
        {
            return (RLine)m_rlines.get(1 + m_select_line[0].l_no);
        } else
        {
            return null;
        }
    }

    public RLine getNowRLine()
    {
        if (m_select_line[0] != null && m_select_line[1] != null)
        {
            return (RLine)m_rlines.get(m_select_line[0].l_no);
        }
        if (m_rlines.size() > 0)
        {
            return (RLine)m_rlines.get(0);
        } else
        {
            return null;
        }
    }

    public GPoint getPoint(int i, int j)
    {
        RLine rline = getRLine(i);
        if (rline != null)
        {
            return rline.getPoint(j);
        } else
        {
            return null;
        }
    }

    public void getPoints(ArrayList arraylist)
    {
        int i = 0;
        do
        {
            if (i >= m_rlines.size())
            {
                return;
            }
            ((RLine)m_rlines.get(i)).getPoints(arraylist);
            i++;
        } while (true);
    }

    public RLine getRLine(int i)
    {
        return (RLine)m_rlines.get(i);
    }

    public GPoint[] getSelectLine()
    {
        if (m_select_line[0] != null && m_select_line[1] != null)
        {
            return m_select_line;
        } else
        {
            return null;
        }
    }
}

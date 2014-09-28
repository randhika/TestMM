// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.maps.indoormap;

import java.util.ArrayList;
import jp.co.yahoo.android.maps.GeoPoint;

public class IndoorData
{
    public static class FloorArea
    {

        public String areaName;
        public int floorId;

        public String toString()
        {
            StringBuffer stringbuffer = new StringBuffer();
            stringbuffer.append("FloorArea={");
            stringbuffer.append("\n");
            stringbuffer.append((new StringBuilder("floorId=")).append(floorId).toString());
            stringbuffer.append("\n");
            stringbuffer.append((new StringBuilder("areaName=")).append(areaName).toString());
            stringbuffer.append("}");
            return stringbuffer.toString();
        }

        public FloorArea()
        {
        }
    }

    public static class FloorConnection
    {

        public int connectionFloorId;
        public int connectionIndoorId;
        public int floorId;

        public String toString()
        {
            StringBuffer stringbuffer = new StringBuffer();
            stringbuffer.append("FloorConnection={");
            stringbuffer.append("\n");
            stringbuffer.append((new StringBuilder("floorId=")).append(floorId).toString());
            stringbuffer.append("\n");
            stringbuffer.append((new StringBuilder("connectionIndoorId=")).append(connectionIndoorId).toString());
            stringbuffer.append("\n");
            stringbuffer.append((new StringBuilder("connectionFloorId=")).append(connectionFloorId).toString());
            stringbuffer.append("}");
            return stringbuffer.toString();
        }

        public FloorConnection()
        {
        }
    }

    public static class FloorShape
    {

        public ArrayList floorAreas;
        public int floorIds[];
        public ArrayList shapes;
        public String type;

        public String toString()
        {
            StringBuffer stringbuffer;
            int i;
            stringbuffer = new StringBuffer();
            stringbuffer.append("FloorShape={");
            stringbuffer.append("\n");
            stringbuffer.append((new StringBuilder("type=")).append(type).toString());
            stringbuffer.append("\n");
            stringbuffer.append((new StringBuilder("floorIds=")).append(IndoorData.toSeparatedString(floorIds, ",")).toString());
            stringbuffer.append("\n");
            stringbuffer.append((new StringBuilder("ArrayList<Shape>.size()=")).append(shapes.size()).toString());
            stringbuffer.append("\n");
            stringbuffer.append("ArrayList<Shape>={");
            stringbuffer.append("\n");
            i = 0;
_L3:
            if (i < shapes.size()) goto _L2; else goto _L1
_L1:
            int j;
            stringbuffer.append("}");
            stringbuffer.append("\n");
            stringbuffer.append((new StringBuilder("ArrayList<FloorArea>.size()=")).append(floorAreas.size()).toString());
            stringbuffer.append("\n");
            stringbuffer.append("ArrayList<FloorArea>={");
            stringbuffer.append("\n");
            j = 0;
_L4:
            if (j >= floorAreas.size())
            {
                stringbuffer.append("}");
                stringbuffer.append("\n");
                stringbuffer.append("}");
                return stringbuffer.toString();
            }
            break MISSING_BLOCK_LABEL_302;
_L2:
            Shape shape = (Shape)shapes.get(i);
            if (shape != null)
            {
                if (i != 0)
                {
                    stringbuffer.append("\n");
                }
                stringbuffer.append(shape.toString());
            }
            i++;
              goto _L3
            FloorArea floorarea = (FloorArea)floorAreas.get(j);
            if (floorarea != null)
            {
                if (j != 0)
                {
                    stringbuffer.append("\n");
                }
                stringbuffer.append(floorarea.toString());
            }
            j++;
              goto _L4
        }

        public FloorShape()
        {
            shapes = new ArrayList();
            floorAreas = new ArrayList();
        }
    }

    public static class Shape
    {

        public GeoPoint geoPoints[];

        public String toString()
        {
            StringBuffer stringbuffer;
            int i;
            stringbuffer = new StringBuffer();
            stringbuffer.append("Shape={");
            stringbuffer.append("\n");
            if (geoPoints == null)
            {
                break MISSING_BLOCK_LABEL_179;
            }
            stringbuffer.append((new StringBuilder("GeoPoint[].length=")).append(geoPoints.length).toString());
            stringbuffer.append("\n");
            stringbuffer.append("GeoPoint[]={");
            stringbuffer.append("\n");
            i = 0;
_L3:
            if (i < geoPoints.length) goto _L2; else goto _L1
_L1:
            stringbuffer.append("}");
            stringbuffer.append("\n");
_L4:
            stringbuffer.append("}");
            return stringbuffer.toString();
_L2:
            if (geoPoints[i] != null)
            {
                if (i != 0)
                {
                    stringbuffer.append(",");
                }
                stringbuffer.append(geoPoints[i].getLatitudeE6());
                stringbuffer.append(",");
                stringbuffer.append(geoPoints[i].getLongitudeE6());
            }
            i++;
              goto _L3
            stringbuffer.append("GeoPoint[]=null");
            stringbuffer.append("\n");
              goto _L4
        }

        public Shape()
        {
        }
    }


    public String copyright;
    public int defaultFloorId;
    public ArrayList floorConnections;
    public int floorIds[];
    public String floorLevels[];
    public ArrayList floorShapes;
    public int indoorId;
    public int layers[];

    public IndoorData()
    {
        floorShapes = new ArrayList();
        floorConnections = new ArrayList();
    }

    private static String toSeparatedString(int ai[], String s)
    {
        StringBuffer stringbuffer;
        int i;
        stringbuffer = new StringBuffer();
        if (ai == null)
        {
            break MISSING_BLOCK_LABEL_53;
        }
        i = 0;
_L3:
        if (i < ai.length) goto _L2; else goto _L1
_L1:
        return stringbuffer.toString();
_L2:
        if (i != 0)
        {
            stringbuffer.append(s);
        }
        stringbuffer.append(ai[i]);
        i++;
          goto _L3
        stringbuffer.append("null");
          goto _L1
    }

    private static String toSeparatedString(Object aobj[], String s)
    {
        StringBuffer stringbuffer;
        int i;
        stringbuffer = new StringBuffer();
        if (aobj == null)
        {
            break MISSING_BLOCK_LABEL_70;
        }
        i = 0;
_L3:
        if (i < aobj.length) goto _L2; else goto _L1
_L1:
        return stringbuffer.toString();
_L2:
        if (i != 0)
        {
            stringbuffer.append(s);
        }
        if (aobj[i] != null)
        {
            stringbuffer.append(aobj[i]);
        } else
        {
            stringbuffer.append("null");
        }
        i++;
          goto _L3
        stringbuffer.append("null");
          goto _L1
    }

    public String toString()
    {
        StringBuffer stringbuffer;
        int i;
        stringbuffer = new StringBuffer();
        stringbuffer.append("IndoorData={");
        stringbuffer.append((new StringBuilder("indoorId=")).append(indoorId).toString());
        stringbuffer.append("\n");
        stringbuffer.append((new StringBuilder("copyright=")).append(copyright).toString());
        stringbuffer.append("\n");
        stringbuffer.append((new StringBuilder("layers=")).append(toSeparatedString(layers, ",")).toString());
        stringbuffer.append("\n");
        stringbuffer.append((new StringBuilder("floorIds=")).append(toSeparatedString(floorIds, ",")).toString());
        stringbuffer.append("\n");
        stringbuffer.append((new StringBuilder("floorLevels=")).append(toSeparatedString(floorLevels, ",")).toString());
        stringbuffer.append("\n");
        i = 0;
_L3:
        if (i < floorShapes.size()) goto _L2; else goto _L1
_L1:
        int j = 0;
_L4:
        if (j >= floorConnections.size())
        {
            stringbuffer.append("}");
            return stringbuffer.toString();
        }
        break MISSING_BLOCK_LABEL_252;
_L2:
        stringbuffer.append(floorShapes.toString());
        stringbuffer.append("\n");
        i++;
          goto _L3
        stringbuffer.append(((FloorConnection)floorConnections.get(j)).toString());
        stringbuffer.append("\n");
        j++;
          goto _L4
    }

}

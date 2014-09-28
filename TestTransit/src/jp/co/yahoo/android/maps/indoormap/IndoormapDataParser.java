// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.maps.indoormap;

import android.util.Xml;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import jp.co.yahoo.android.maps.GeoPoint;
import jp.co.yahoo.android.maps.HttpClient;
import jp.co.yahoo.android.maps.viewlayer.MapYmlLayer;
import jp.co.yahoo.android.maps.viewlayer.MapYmlPackage;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

// Referenced classes of package jp.co.yahoo.android.maps.indoormap:
//            IndoorData

public class IndoormapDataParser
{
    static class Result
    {

        ArrayList indoormapDataList;
        HashMap mapYmlPackageList;

        Result()
        {
            mapYmlPackageList = new HashMap();
            indoormapDataList = new ArrayList();
        }
    }


    public IndoormapDataParser()
    {
    }

    private Result getDataSet(GeoPoint geopoint, String s, String s1)
    {
        double d = geopoint.getLatitude();
        double d1 = geopoint.getLongitude();
        String s2 = String.valueOf(d);
        String s3 = String.valueOf(d1);
        InputStream inputstream;
        Result result;
        try
        {
            String s4 = (new StringBuilder(String.valueOf(s2))).append(",").append(s3).toString();
            inputstream = HttpClient.httprun((new StringBuilder(String.valueOf(s))).append("?c=").append(s4).append("&appid=").append(s1).append("&device=android&v=4").toString());
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
            return null;
        }
        if (inputstream == null)
        {
            return null;
        }
        result = parseIndoormapYml(inputstream);
        inputstream.close();
        return result;
    }

    public static IndoorData parseIndoorData(XmlPullParser xmlpullparser)
        throws XmlPullParserException, IOException
    {
        IndoorData indoordata;
        int i;
        indoordata = new IndoorData();
        indoordata.indoorId = Integer.parseInt(xmlpullparser.getAttributeValue(null, "id"));
        indoordata.copyright = xmlpullparser.getAttributeValue(null, "copyright");
        indoordata.defaultFloorId = Integer.parseInt(xmlpullparser.getAttributeValue(null, "defaultfloorid"));
        xmlpullparser.next();
        i = xmlpullparser.getEventType();
_L2:
        if (i == 1)
        {
            return indoordata;
        }
        if (i != 2 || !xmlpullparser.getName().equals("layers"))
        {
            break; /* Loop/switch isn't completed */
        }
        indoordata.layers = toIntegerList(xmlpullparser.nextText());
_L3:
        i = xmlpullparser.next();
        if (true) goto _L2; else goto _L1
_L1:
        if (i == 2 && xmlpullparser.getName().equals("floorids"))
        {
            indoordata.floorIds = toIntegerList(xmlpullparser.nextText());
        } else
        {
label0:
            {
                if (i != 2 || !xmlpullparser.getName().equals("floorlevels"))
                {
                    break label0;
                }
                indoordata.floorLevels = xmlpullparser.nextText().split(",");
            }
        }
          goto _L3
        continue; /* Loop/switch isn't completed */
        if (i != 2 || !xmlpullparser.getName().equals("floorshape")) goto _L5; else goto _L4
_L4:
        IndoorData.FloorShape floorshape;
        int i1;
        floorshape = new IndoorData.FloorShape();
        indoordata.floorShapes.add(floorshape);
        floorshape.type = xmlpullparser.getAttributeValue(null, "type");
        xmlpullparser.next();
        i1 = xmlpullparser.getEventType();
_L7:
        if (i1 == 1) goto _L3; else goto _L6
_L6:
        if (i1 == 2 && xmlpullparser.getName().equals("floorids"))
        {
            floorshape.floorIds = toIntegerList(xmlpullparser.nextText());
        } else
        {
label1:
            {
                if (i1 != 2 || !xmlpullparser.getName().equals("shape"))
                {
                    break label1;
                }
                IndoorData.Shape shape = new IndoorData.Shape();
                shape.geoPoints = toGeoPointList(xmlpullparser.nextText());
                floorshape.shapes.add(shape);
            }
        }
_L9:
        i1 = xmlpullparser.next();
          goto _L7
        int j1;
        if (i1 != 2 || !xmlpullparser.getName().equals("floorareas"))
        {
            continue; /* Loop/switch isn't completed */
        }
        xmlpullparser.next();
        j1 = xmlpullparser.getEventType();
_L14:
        if (j1 == 1) goto _L9; else goto _L8
_L8:
        if (j1 != 2 || !xmlpullparser.getName().equals("floorarea")) goto _L11; else goto _L10
_L10:
        IndoorData.FloorArea floorarea;
        int k1;
        floorarea = new IndoorData.FloorArea();
        floorshape.floorAreas.add(floorarea);
        xmlpullparser.next();
        k1 = xmlpullparser.getEventType();
_L15:
        if (k1 != 1) goto _L13; else goto _L12
_L12:
        j1 = xmlpullparser.next();
          goto _L14
_L13:
        if (k1 == 2 && xmlpullparser.getName().equals("floorid"))
        {
            floorarea.floorId = Integer.parseInt(xmlpullparser.nextText());
        } else
        {
            if (k1 != 2 || !xmlpullparser.getName().equals("areaname"))
            {
                continue; /* Loop/switch isn't completed */
            }
            floorarea.areaName = xmlpullparser.nextText();
        }
_L16:
        k1 = xmlpullparser.next();
          goto _L15
        if (k1 != 3 || !xmlpullparser.getName().equals("floorarea")) goto _L16; else goto _L12
_L11:
        if (j1 != 3 || !xmlpullparser.getName().equals("floorareas")) goto _L12; else goto _L9
        if (i1 != 3 || !xmlpullparser.getName().equals("floorshape")) goto _L9; else goto _L3
_L5:
        int j;
        int k;
        if (i != 2 || !xmlpullparser.getName().equals("floorconnection"))
        {
            continue; /* Loop/switch isn't completed */
        }
        j = Integer.parseInt(xmlpullparser.getAttributeValue(null, "floorid"));
        xmlpullparser.next();
        k = xmlpullparser.getEventType();
_L22:
        if (k == 1) goto _L3; else goto _L17
_L17:
        if (k != 2 || !xmlpullparser.getName().equals("connection")) goto _L19; else goto _L18
_L18:
        IndoorData.FloorConnection floorconnection;
        int l;
        floorconnection = new IndoorData.FloorConnection();
        indoordata.floorConnections.add(floorconnection);
        floorconnection.floorId = j;
        xmlpullparser.next();
        l = xmlpullparser.getEventType();
_L23:
        if (l != 1) goto _L21; else goto _L20
_L20:
        k = xmlpullparser.next();
          goto _L22
_L21:
        if (l == 2 && xmlpullparser.getName().equals("indoorid"))
        {
            floorconnection.connectionIndoorId = Integer.parseInt(xmlpullparser.nextText());
        } else
        {
            if (l != 2 || !xmlpullparser.getName().equals("floorid"))
            {
                continue; /* Loop/switch isn't completed */
            }
            floorconnection.connectionFloorId = Integer.parseInt(xmlpullparser.nextText());
        }
_L24:
        l = xmlpullparser.next();
          goto _L23
        if (l != 3 || !xmlpullparser.getName().equals("connection")) goto _L24; else goto _L20
_L19:
        if (k != 3 || !xmlpullparser.getName().equals("floorconnection")) goto _L20; else goto _L3
        if (i != 3 || !xmlpullparser.getName().equals("indoormap")) goto _L3; else goto _L25
_L25:
        return indoordata;
        if (true) goto _L2; else goto _L26
_L26:
    }

    private static Result parseIndoormapYml(InputStream inputstream)
    {
        XmlPullParser xmlpullparser;
        Result result;
        int i;
        int j;
        xmlpullparser = Xml.newPullParser();
        String s;
        MapYmlPackage mapymlpackage;
        String s1;
        String s2;
        String s3;
        try
        {
            xmlpullparser.setInput(inputstream, "UTF-8");
            result = new Result();
            i = xmlpullparser.getEventType();
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
            return null;
        }
          goto _L1
_L13:
        if (i != 2) goto _L3; else goto _L2
_L2:
        if (!xmlpullparser.getName().equals("mappackage")) goto _L3; else goto _L4
_L4:
        s = xmlpullparser.getAttributeValue(null, "id");
        mapymlpackage = new MapYmlPackage(s);
        xmlpullparser.next();
        j = xmlpullparser.getEventType();
          goto _L5
_L14:
        i = xmlpullparser.next();
        continue; /* Loop/switch isn't completed */
_L15:
        if (j != 2) goto _L7; else goto _L6
_L6:
        if (!xmlpullparser.getName().equals("layer")) goto _L7; else goto _L8
_L8:
        s1 = xmlpullparser.getAttributeValue(null, "code");
        s2 = xmlpullparser.getAttributeValue(null, "copyright");
        s3 = xmlpullparser.nextText();
        mapymlpackage.addMapYmlLayer(new MapYmlLayer(Integer.parseInt(s1), s2, Integer.parseInt(s3)));
_L10:
        j = xmlpullparser.next();
        continue; /* Loop/switch isn't completed */
_L7:
        if (j != 3) goto _L10; else goto _L9
_L9:
        if (!xmlpullparser.getName().equals("mappackage")) goto _L10; else goto _L11
_L11:
        result.mapYmlPackageList.put(s, mapymlpackage);
        break; /* Loop/switch isn't completed */
_L3:
        if (i != 2)
        {
            break; /* Loop/switch isn't completed */
        }
        if (!xmlpullparser.getName().equals("indoormap"))
        {
            break; /* Loop/switch isn't completed */
        }
        IndoorData indoordata = parseIndoorData(xmlpullparser);
        result.indoormapDataList.add(indoordata);
        break; /* Loop/switch isn't completed */
_L1:
        if (i != 1) goto _L13; else goto _L12
_L12:
        return result;
_L5:
        if (j != 1) goto _L15; else goto _L14
    }

    private static GeoPoint[] toGeoPointList(String s)
    {
        String as[] = s.split(",");
        GeoPoint ageopoint[] = new GeoPoint[as.length / 2];
        int i = 0;
        do
        {
            if (i >= as.length / 2)
            {
                return ageopoint;
            }
            ageopoint[i] = new GeoPoint((int)(1000000D * Double.parseDouble(as[i * 2])), (int)(1000000D * Double.parseDouble(as[1 + i * 2])));
            i++;
        } while (true);
    }

    private static int[] toIntegerList(String s)
    {
        String as[] = s.split(",");
        int ai[] = new int[as.length];
        int i = 0;
        do
        {
            if (i >= as.length)
            {
                return ai;
            }
            ai[i] = Integer.parseInt(as[i]);
            i++;
        } while (true);
    }
}

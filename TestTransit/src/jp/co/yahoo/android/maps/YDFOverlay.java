// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.maps;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

// Referenced classes of package jp.co.yahoo.android.maps:
//            Overlay, PinOverlay, PopupOverlay, PolylineOverlay, 
//            PolygonOverlay, CircleOverlay, GeoPoint, MapView

public class YDFOverlay extends Overlay
{
    static class Geometry
    {

        GeoPoint points[];
        int radius[];
        String type;

        Geometry()
        {
        }
    }


    private final Overlay overlays[];

    public YDFOverlay(String s)
        throws IOException, SAXException, ParserConfigurationException, JSONException
    {
        if (s.trim().startsWith("<"))
        {
            DocumentBuilder documentbuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            StringReader stringreader = new StringReader(s);
            Document document = documentbuilder.parse(new InputSource(stringreader));
            stringreader.close();
            overlays = parseXML(document);
            return;
        }
        if (s.trim().startsWith("{"))
        {
            overlays = parseJSON(new JSONObject(s));
            return;
        } else
        {
            throw new RuntimeException("Unknown format.");
        }
    }

    public YDFOverlay(JSONObject jsonobject)
    {
        overlays = parseJSON(jsonobject);
    }

    public YDFOverlay(Document document)
    {
        overlays = parseXML(document);
    }

    private static String getAttribute(Node node, String s)
    {
        String s1 = ((Element)node).getAttribute(s);
        if (s1 == null)
        {
            return null;
        } else
        {
            return s1.trim();
        }
    }

    private static String getContent(Node node)
    {
        Node node1;
        String s;
        if (node != null)
        {
            if ((node1 = node.getFirstChild()) != null && (s = node1.getNodeValue()) != null)
            {
                return s.trim();
            }
        }
        return null;
    }

    private static Node getNode(Node node, String s)
    {
        NodeList nodelist = getNodeList(node, s);
        if (nodelist == null || nodelist.getLength() == 0)
        {
            return null;
        } else
        {
            return nodelist.item(0);
        }
    }

    private static String getNodeContent(Node node, String s)
    {
        Node node1 = getNode(node, s);
        if (node1 == null)
        {
            return null;
        } else
        {
            return getContent(node1);
        }
    }

    private static NodeList getNodeList(Node node, String s)
    {
        if (node == null || s == null || s.equals(""))
        {
            return null;
        } else
        {
            return ((Element)node).getElementsByTagName(s);
        }
    }

    private static Overlay[] parseJSON(JSONObject jsonobject)
    {
        ArrayList arraylist;
        PinOverlay pinoverlay;
        HashMap hashmap;
        JSONObject jsonobject1;
        arraylist = new ArrayList();
        pinoverlay = new PinOverlay(0);
        hashmap = new HashMap();
        jsonobject1 = jsonobject.optJSONObject("Dictionary");
        if (jsonobject1 == null || jsonobject1.equals(JSONObject.NULL)) goto _L2; else goto _L1
_L1:
        Iterator iterator1 = jsonobject1.keys();
_L5:
        if (iterator1.hasNext()) goto _L3; else goto _L2
_L2:
        JSONArray jsonarray;
        jsonarray = jsonobject.optJSONArray("Feature");
        if (jsonarray == null || jsonarray.equals(JSONObject.NULL))
        {
            return new Overlay[0];
        }
        break; /* Loop/switch isn't completed */
_L3:
        JSONArray jsonarray1 = (JSONArray)iterator1.next();
        int l6 = 0;
        do
        {
            int i7 = jsonarray1.length();
            if (l6 >= i7)
            {
                break;
            }
            JSONObject jsonobject5 = jsonarray1.optJSONObject(l6);
            String s8 = jsonobject5.optString("Id", null);
            if (s8 != null && !s8.equals(JSONObject.NULL.toString()))
            {
                hashmap.put(s8, jsonobject5);
            }
            l6++;
        } while (true);
        if (true) goto _L5; else goto _L4
_L4:
        int i = 0;
_L7:
        JSONObject jsonobject2;
        String s;
        JSONObject jsonobject3;
        int j = jsonarray.length();
        if (i >= j)
        {
            if (pinoverlay.size() > 0)
            {
                pinoverlay.populate();
                arraylist.add(pinoverlay);
                PopupOverlay popupoverlay = new PopupOverlay();
                pinoverlay.setOnFocusChangeListener(popupoverlay);
                arraylist.add(popupoverlay);
            }
            return (Overlay[])arraylist.toArray(new Overlay[0]);
        }
        jsonobject2 = jsonarray.optJSONObject(i);
        s = jsonobject2.optString("Name", null);
        jsonobject3 = jsonobject2.optJSONObject("Geometry");
        if (jsonobject3 != null && !jsonobject3.equals(JSONObject.NULL))
        {
            break; /* Loop/switch isn't completed */
        }
_L8:
        i++;
        if (true) goto _L7; else goto _L6
_L6:
        List list = parseJSON_geometry(jsonobject3, hashmap);
        JSONObject jsonobject4 = jsonobject2.optJSONObject("Style");
        if (jsonobject4 != null)
        {
            Object obj1 = JSONObject.NULL;
            if (!jsonobject4.equals(obj1))
            {
                String s7 = jsonobject4.optString("Target", null);
                if (s7 != null && !s7.equals("") && !s7.equals(JSONObject.NULL.toString()) && hashmap.containsKey(s7))
                {
                    jsonobject4 = ((JSONObject)hashmap.get(s7)).optJSONObject("Style");
                }
            }
        }
        int k = 0xff0000ff;
        int l = 3;
        Drawable drawable = null;
        String s1 = null;
        if (jsonobject4 != null)
        {
            Object obj = JSONObject.NULL;
            boolean flag = jsonobject4.equals(obj);
            drawable = null;
            s1 = null;
            if (!flag)
            {
                s1 = jsonobject4.optString("Type", null);
                String s2 = jsonobject4.optString("Image", null);
                drawable = null;
                if (s2 != null)
                {
                    boolean flag1 = s2.equals("");
                    drawable = null;
                    if (!flag1)
                    {
                        boolean flag2 = s2.equals(JSONObject.NULL.toString());
                        drawable = null;
                        if (!flag2)
                        {
                            Iterator iterator;
                            Geometry geometry;
                            PolylineOverlay polylineoverlay;
                            PolygonOverlay polygonoverlay;
                            CircleOverlay circleoverlay;
                            CircleOverlay circleoverlay1;
                            PinOverlay.PinItem pinitem;
                            String s3;
                            String s4;
                            String s5;
                            String s6;
                            int i1;
                            int j1;
                            int k1;
                            int l1;
                            int i2;
                            String as[];
                            int j2;
                            int k2;
                            Rect rect;
                            int l2;
                            int i3;
                            int j3;
                            int k3;
                            String as1[];
                            int l3;
                            int i4;
                            int j4;
                            int k4;
                            int l4;
                            int i5;
                            try
                            {
                                URL url = new URL(s2);
                                InputStream inputstream = (InputStream)url.getContent();
                                drawable = Drawable.createFromStream(inputstream, null);
                                inputstream.close();
                            }
                            catch (IOException ioexception) { }
                            if (drawable != null)
                            {
                                int j5 = drawable.getIntrinsicWidth();
                                int k5 = drawable.getIntrinsicHeight();
                                if (j5 > 0 && k5 > 0)
                                {
                                    int l5 = -j5 / 2;
                                    int i6 = -k5 / 2;
                                    int j6 = j5 / 2;
                                    int k6 = k5 / 2;
                                    drawable.setBounds(l5, i6, j6, k6);
                                }
                            }
                        }
                    }
                }
                s3 = jsonobject4.optString("Size", null);
                if (s3 != null && !s3.equals("") && !s3.equals(JSONObject.NULL.toString()))
                {
                    if (s3.contains(","))
                    {
                        as1 = s3.split(",");
                        l3 = Integer.parseInt(as1[0]);
                        i4 = Integer.parseInt(as1[1]);
                        if (drawable != null)
                        {
                            j4 = -l3 / 2;
                            k4 = -i4 / 2;
                            l4 = l3 / 2;
                            i5 = i4 / 2;
                            drawable.setBounds(j4, k4, l4, i5);
                        }
                    } else
                    {
                        l = Integer.parseInt(s3);
                    }
                }
                s4 = jsonobject4.optString("Anchor", null);
                if (s4 != null && s4.contains(","))
                {
                    as = s4.split(",");
                    j2 = Integer.parseInt(as[0]);
                    k2 = Integer.parseInt(as[1]);
                    if (drawable != null)
                    {
                        rect = drawable.getBounds();
                        l2 = -j2;
                        i3 = -k2;
                        j3 = rect.width() - j2;
                        k3 = rect.height() - k2;
                        drawable.setBounds(l2, i3, j3, k3);
                    }
                }
                s5 = jsonobject4.optString("Color", null);
                if (s5 != null && !s5.equals("") && !s5.equals(JSONObject.NULL.toString()))
                {
                    j1 = Color.parseColor((new StringBuilder("#")).append(s5).toString());
                    k1 = Color.red(j1);
                    l1 = Color.green(j1);
                    i2 = Color.blue(j1);
                    k = Color.argb(Color.alpha(j1), k1, l1, i2);
                }
                s6 = jsonobject4.optString("Opacity", null);
                if (s6 != null && !s6.equals("") && !s6.equals(JSONObject.NULL.toString()))
                {
                    i1 = (255 * Integer.parseInt(s6)) / 100;
                    k = Color.argb(i1, Color.red(k), Color.green(k), Color.blue(k));
                    if (drawable != null)
                    {
                        drawable.setAlpha(i1);
                    }
                }
            }
        }
        iterator = list.iterator();
        while (iterator.hasNext()) 
        {
            geometry = (Geometry)iterator.next();
            if (geometry.points != null && geometry.points.length != 0)
            {
                if ("point".equals(geometry.type))
                {
                    pinitem = (PinOverlay.PinItem)pinoverlay.addPoint(geometry.points[0], s);
                    if (drawable != null)
                    {
                        pinitem.marker = drawable;
                    }
                } else
                if ("linestring".equals(geometry.type))
                {
                    polylineoverlay = new PolylineOverlay(geometry.points);
                    polylineoverlay.setColor(k);
                    polylineoverlay.setWidth(l);
                    arraylist.add(polylineoverlay);
                } else
                if ("polygon".equals(geometry.type))
                {
                    polygonoverlay = new PolygonOverlay(geometry.points);
                    polygonoverlay.setStrokeColor(k);
                    if ("line".equals(s1))
                    {
                        polygonoverlay.setStrokeWidth(l);
                        polygonoverlay.setFillColor(0);
                    } else
                    if ("fill".equals(s1))
                    {
                        polygonoverlay.setStrokeColor(0);
                        polygonoverlay.setStrokeWidth(0.0F);
                        polygonoverlay.setFillColor(k);
                    }
                    arraylist.add(polygonoverlay);
                } else
                if ("circle".equals(geometry.type))
                {
                    if (geometry.radius != null && geometry.radius.length > 0)
                    {
                        circleoverlay1 = new CircleOverlay(geometry.points[0], geometry.radius[0]);
                        circleoverlay1.setStrokeColor(k);
                        circleoverlay1.setStrokeWidth(l);
                        if ("line".equals(s1))
                        {
                            circleoverlay1.setFillColor(0);
                        } else
                        if ("fill".equals(s1))
                        {
                            circleoverlay1.setFillColor(k);
                        }
                        arraylist.add(circleoverlay1);
                    }
                } else
                if ("ellipse".equals(geometry.type) && geometry.radius != null && geometry.radius.length > 1)
                {
                    circleoverlay = new CircleOverlay(geometry.points[0], geometry.radius[0], geometry.radius[1]);
                    circleoverlay.setStrokeColor(k);
                    circleoverlay.setStrokeWidth(l);
                    if ("line".equals(s1))
                    {
                        circleoverlay.setFillColor(0);
                    } else
                    if ("fill".equals(s1))
                    {
                        circleoverlay.setFillColor(k);
                    }
                    arraylist.add(circleoverlay);
                }
            }
        }
          goto _L8
        if (true) goto _L7; else goto _L9
_L9:
    }

    private static List parseJSON_geometry(JSONObject jsonobject, Map map)
    {
        ArrayList arraylist;
        String s;
        arraylist = new ArrayList();
        s = jsonobject.optString("Target", null);
        if (s == null || s.equals("") || s.equals(JSONObject.NULL.toString())) goto _L2; else goto _L1
_L1:
        if (map.containsKey(s))
        {
            JSONObject jsonobject2 = ((JSONObject)map.get(s)).optJSONObject("Geometry");
            if (jsonobject2 != null && !jsonobject2.equals(JSONObject.NULL))
            {
                arraylist.addAll(parseJSON_geometry(jsonobject2, map));
            }
        }
_L4:
        return arraylist;
_L2:
        String s1;
        s1 = jsonobject.optString("Type", null);
        if (!"multigeometry".equals(s1))
        {
            break; /* Loop/switch isn't completed */
        }
        JSONArray jsonarray = jsonobject.optJSONArray("Geometry");
        int l = 0;
        while (l < jsonarray.length()) 
        {
            arraylist.addAll(parseJSON_geometry(jsonarray.optJSONObject(l), map));
            l++;
        }
        if (true) goto _L4; else goto _L3
_L3:
        Geometry geometry;
        String s2;
        String s3;
        geometry = new Geometry();
        geometry.type = s1;
        s2 = jsonobject.optString("Coordinates", null);
        s3 = null;
        if (s2 == null)
        {
            JSONObject jsonobject1 = jsonobject.optJSONObject("Coordinates");
            s3 = null;
            if (jsonobject1 != null)
            {
                s3 = jsonobject1.optString("-format", null);
                s2 = jsonobject1.optString("#text", null);
            }
        }
        if (s3 == null || s3.equals("") || s3.equals(JSONObject.NULL.toString()))
        {
            s3 = "lon,lat";
        }
        if (s2 == null || s2.equals("") || s2.equals(JSONObject.NULL.toString())) goto _L6; else goto _L5
_L5:
        ArrayList arraylist1;
        String as1[];
        String as2[];
        int j;
        arraylist1 = new ArrayList();
        as1 = s3.split(",");
        as2 = s2.split("\\s");
        j = 0;
_L11:
        if (j < as2.length) goto _L8; else goto _L7
_L7:
        geometry.points = (GeoPoint[])arraylist1.toArray(new GeoPoint[0]);
_L6:
        String s4 = jsonobject.optString("Radius", null);
        if (s4 == null || s4.equals("") || s4.equals(JSONObject.NULL.toString())) goto _L10; else goto _L9
_L9:
        String as[];
        int i;
        as = s4.split(",");
        geometry.radius = new int[as.length];
        i = 0;
_L13:
        if (i < as.length)
        {
            break MISSING_BLOCK_LABEL_569;
        }
_L10:
        arraylist.add(geometry);
        return arraylist;
_L8:
        String as3[];
        double d;
        double d1;
        int k;
        as3 = as2[j].split(",");
        d = 0.0D;
        d1 = 0.0D;
        k = 0;
_L12:
label0:
        {
            if (k < Math.min(as1.length, as3.length))
            {
                break label0;
            }
            arraylist1.add(new GeoPoint((int)(1000000D * d), (int)(1000000D * d1)));
            j++;
        }
          goto _L11
        if ("lat".equals(as1[k]))
        {
            d = Double.parseDouble(as3[k]);
        } else
        if ("lon".equals(as1[k]))
        {
            d1 = Double.parseDouble(as3[k]);
        }
        k++;
          goto _L12
        geometry.radius[i] = Integer.parseInt(as[i]);
        i++;
          goto _L13
    }

    private static Overlay[] parseXML(Document document)
    {
        ArrayList arraylist;
        PinOverlay pinoverlay;
        HashMap hashmap;
        Node node;
        arraylist = new ArrayList();
        pinoverlay = new PinOverlay(0);
        hashmap = new HashMap();
        node = getNode(document.getDocumentElement(), "Dictionary");
        if (node == null) goto _L2; else goto _L1
_L1:
        NodeList nodelist1;
        int l6;
        nodelist1 = node.getChildNodes();
        l6 = 0;
_L5:
        int i7 = nodelist1.getLength();
        if (l6 < i7) goto _L3; else goto _L2
_L2:
        NodeList nodelist;
        nodelist = getNodeList(document.getDocumentElement(), "Feature");
        if (nodelist == null)
        {
            return new Overlay[0];
        }
        break; /* Loop/switch isn't completed */
_L3:
        Node node4;
        node4 = nodelist1.item(l6);
        if (node4.getNodeType() == 1)
        {
            break; /* Loop/switch isn't completed */
        }
_L6:
        l6++;
        if (true) goto _L5; else goto _L4
_L4:
        String s8 = getNodeContent(node4, "Id");
        if (s8 != null && !s8.equals(""))
        {
            hashmap.put(s8, node4);
        }
          goto _L6
        if (true) goto _L5; else goto _L7
_L7:
        int i = 0;
_L9:
        Node node1;
        String s;
        Node node2;
        int j = nodelist.getLength();
        if (i >= j)
        {
            if (pinoverlay.size() > 0)
            {
                pinoverlay.populate();
                arraylist.add(pinoverlay);
                PopupOverlay popupoverlay = new PopupOverlay();
                pinoverlay.setOnFocusChangeListener(popupoverlay);
                arraylist.add(popupoverlay);
            }
            return (Overlay[])arraylist.toArray(new Overlay[0]);
        }
        node1 = nodelist.item(i);
        s = getNodeContent(node1, "Name");
        node2 = getNode(node1, "Geometry");
        if (node2 != null)
        {
            break; /* Loop/switch isn't completed */
        }
_L10:
        i++;
        if (true) goto _L9; else goto _L8
_L8:
        List list = parseXML_geometry(node2, hashmap);
        Node node3 = getNode(node1, "Style");
        if (node3 != null)
        {
            String s7 = getNodeContent(node3, "Target");
            if (s7 != null && !s7.equals("") && hashmap.containsKey(s7))
            {
                node3 = getNode((Node)hashmap.get(s7), "Style");
            }
        }
        int k = 0xff0000ff;
        int l = 3;
        Drawable drawable = null;
        String s1 = null;
        if (node3 != null)
        {
            s1 = getNodeContent(node3, "Type");
            String s2 = getNodeContent(node3, "Image");
            drawable = null;
            if (s2 != null)
            {
                boolean flag = s2.equals("");
                drawable = null;
                if (!flag)
                {
                    Iterator iterator;
                    Geometry geometry;
                    PolylineOverlay polylineoverlay;
                    PolygonOverlay polygonoverlay;
                    CircleOverlay circleoverlay;
                    CircleOverlay circleoverlay1;
                    PinOverlay.PinItem pinitem;
                    String s3;
                    String s4;
                    String s5;
                    String s6;
                    int i1;
                    int j1;
                    int k1;
                    int l1;
                    int i2;
                    String as[];
                    int j2;
                    int k2;
                    Rect rect;
                    int l2;
                    int i3;
                    int j3;
                    int k3;
                    String as1[];
                    int l3;
                    int i4;
                    int j4;
                    int k4;
                    int l4;
                    int i5;
                    try
                    {
                        URL url = new URL(s2);
                        InputStream inputstream = (InputStream)url.getContent();
                        drawable = Drawable.createFromStream(inputstream, null);
                        inputstream.close();
                    }
                    catch (IOException ioexception) { }
                    if (drawable != null)
                    {
                        int j5 = drawable.getIntrinsicWidth();
                        int k5 = drawable.getIntrinsicHeight();
                        if (j5 > 0 && k5 > 0)
                        {
                            int l5 = -j5 / 2;
                            int i6 = -k5 / 2;
                            int j6 = j5 / 2;
                            int k6 = k5 / 2;
                            drawable.setBounds(l5, i6, j6, k6);
                        }
                    }
                }
            }
            s3 = getNodeContent(node3, "Size");
            if (s3 != null && !s3.equals(""))
            {
                if (s3.contains(","))
                {
                    as1 = s3.split(",");
                    l3 = Integer.parseInt(as1[0]);
                    i4 = Integer.parseInt(as1[1]);
                    if (drawable != null)
                    {
                        j4 = -l3 / 2;
                        k4 = -i4 / 2;
                        l4 = l3 / 2;
                        i5 = i4 / 2;
                        drawable.setBounds(j4, k4, l4, i5);
                    }
                } else
                {
                    l = Integer.parseInt(s3);
                }
            }
            s4 = getNodeContent(node3, "Anchor");
            if (s4 != null && s4.contains(","))
            {
                as = s4.split(",");
                j2 = Integer.parseInt(as[0]);
                k2 = Integer.parseInt(as[1]);
                if (drawable != null)
                {
                    rect = drawable.getBounds();
                    l2 = -j2;
                    i3 = -k2;
                    j3 = rect.width() - j2;
                    k3 = rect.height() - k2;
                    drawable.setBounds(l2, i3, j3, k3);
                }
            }
            s5 = getNodeContent(node3, "Color");
            if (s5 != null && !s5.equals(""))
            {
                j1 = Color.parseColor((new StringBuilder("#")).append(s5).toString());
                k1 = Color.red(j1);
                l1 = Color.green(j1);
                i2 = Color.blue(j1);
                k = Color.argb(Color.alpha(j1), k1, l1, i2);
            }
            s6 = getNodeContent(node3, "Opacity");
            if (s6 != null && !s6.equals(""))
            {
                i1 = (255 * Integer.parseInt(s6)) / 100;
                k = Color.argb(i1, Color.red(k), Color.green(k), Color.blue(k));
                if (drawable != null)
                {
                    drawable.setAlpha(i1);
                }
            }
        }
        iterator = list.iterator();
        while (iterator.hasNext()) 
        {
            geometry = (Geometry)iterator.next();
            if (geometry.points != null && geometry.points.length != 0)
            {
                if ("point".equals(geometry.type))
                {
                    pinitem = (PinOverlay.PinItem)pinoverlay.addPoint(geometry.points[0], s);
                    if (drawable != null)
                    {
                        pinitem.marker = drawable;
                    }
                } else
                if ("linestring".equals(geometry.type))
                {
                    polylineoverlay = new PolylineOverlay(geometry.points);
                    polylineoverlay.setColor(k);
                    polylineoverlay.setWidth(l);
                    arraylist.add(polylineoverlay);
                } else
                if ("polygon".equals(geometry.type))
                {
                    polygonoverlay = new PolygonOverlay(geometry.points);
                    polygonoverlay.setStrokeColor(k);
                    polygonoverlay.setStrokeWidth(l);
                    if ("line".equals(s1))
                    {
                        polygonoverlay.setFillColor(0);
                    } else
                    if ("fill".equals(s1))
                    {
                        polygonoverlay.setFillColor(k);
                    }
                    arraylist.add(polygonoverlay);
                } else
                if ("circle".equals(geometry.type))
                {
                    if (geometry.radius != null && geometry.radius.length > 0)
                    {
                        circleoverlay1 = new CircleOverlay(geometry.points[0], geometry.radius[0]);
                        circleoverlay1.setStrokeColor(k);
                        circleoverlay1.setStrokeWidth(l);
                        if ("line".equals(s1))
                        {
                            circleoverlay1.setFillColor(0);
                        } else
                        if ("fill".equals(s1))
                        {
                            circleoverlay1.setFillColor(k);
                        }
                        arraylist.add(circleoverlay1);
                    }
                } else
                if ("ellipse".equals(geometry.type) && geometry.radius != null && geometry.radius.length > 1)
                {
                    circleoverlay = new CircleOverlay(geometry.points[0], geometry.radius[0], geometry.radius[1]);
                    circleoverlay.setStrokeColor(k);
                    circleoverlay.setStrokeWidth(l);
                    if ("line".equals(s1))
                    {
                        circleoverlay.setFillColor(0);
                    } else
                    if ("fill".equals(s1))
                    {
                        circleoverlay.setFillColor(k);
                    }
                    arraylist.add(circleoverlay);
                }
            }
        }
          goto _L10
        if (true) goto _L9; else goto _L11
_L11:
    }

    private static List parseXML_geometry(Node node, Map map)
    {
        ArrayList arraylist;
        String s;
        arraylist = new ArrayList();
        s = getNodeContent(node, "Target");
        if (s == null || s.equals("")) goto _L2; else goto _L1
_L1:
        if (map.containsKey(s))
        {
            Node node2 = getNode((Node)map.get(s), "Geometry");
            if (node2 != null)
            {
                arraylist.addAll(parseXML_geometry(node2, map));
            }
        }
_L4:
        return arraylist;
_L2:
        String s1;
        s1 = getNodeContent(node, "Type");
        if (!"multigeometry".equals(s1))
        {
            break; /* Loop/switch isn't completed */
        }
        NodeList nodelist = getNodeList(node, "Geometry");
        if (nodelist != null)
        {
            int l = 0;
            while (l < nodelist.getLength()) 
            {
                arraylist.addAll(parseXML_geometry(nodelist.item(l), map));
                l++;
            }
        }
        if (true) goto _L4; else goto _L3
_L3:
        Geometry geometry;
        String s2;
        String s3;
        geometry = new Geometry();
        geometry.type = s1;
        Node node1 = getNode(node, "Coordinates");
        s2 = getAttribute(node1, "format");
        if (s2 == null || s2.equals(""))
        {
            s2 = "lon,lat";
        }
        s3 = getContent(node1);
        if (s3 == null || s3.equals("")) goto _L6; else goto _L5
_L5:
        ArrayList arraylist1;
        String as1[];
        String as2[];
        int j;
        arraylist1 = new ArrayList();
        as1 = s2.split(",");
        as2 = s3.split("\\s");
        j = 0;
_L11:
        if (j < as2.length) goto _L8; else goto _L7
_L7:
        geometry.points = (GeoPoint[])arraylist1.toArray(new GeoPoint[0]);
_L6:
        String s4 = getNodeContent(node, "Radius");
        if (s4 == null || s4.equals("")) goto _L10; else goto _L9
_L9:
        String as[];
        int i;
        as = s4.split(",");
        geometry.radius = new int[as.length];
        i = 0;
_L13:
        if (i < as.length)
        {
            break MISSING_BLOCK_LABEL_478;
        }
_L10:
        arraylist.add(geometry);
        return arraylist;
_L8:
        String as3[];
        double d;
        double d1;
        int k;
        as3 = as2[j].split(",");
        d = 0.0D;
        d1 = 0.0D;
        k = 0;
_L12:
label0:
        {
            if (k < Math.min(as1.length, as3.length))
            {
                break label0;
            }
            arraylist1.add(new GeoPoint((int)(1000000D * d), (int)(1000000D * d1)));
            j++;
        }
          goto _L11
        if ("lat".equals(as1[k]))
        {
            d = Double.parseDouble(as3[k]);
        } else
        if ("lon".equals(as1[k]))
        {
            d1 = Double.parseDouble(as3[k]);
        }
        k++;
          goto _L12
        geometry.radius[i] = Integer.parseInt(as[i]);
        i++;
          goto _L13
    }

    public void draw(Canvas canvas, MapView mapview, boolean flag)
    {
        int i = 0;
        do
        {
            if (i >= overlays.length)
            {
                return;
            }
            overlays[i].draw(canvas, mapview, flag);
            i++;
        } while (true);
    }

    public GeoPoint getCenter()
    {
        int i;
        int j;
        int k;
        int l;
        int i1;
        i = 0;
        j = 0;
        k = 0;
        l = 0;
        i1 = 0;
_L2:
        GeoPoint geopoint;
        if (i1 >= overlays.length)
        {
            return new GeoPoint((i + j) / 2, (k + l) / 2);
        }
        geopoint = overlays[i1].getCenter();
        if (geopoint != null)
        {
            break; /* Loop/switch isn't completed */
        }
_L3:
        i1++;
        if (true) goto _L2; else goto _L1
_L1:
        int j1 = overlays[i1].getLatSpanE6();
        int k1 = overlays[i1].getLonSpanE6();
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

    public int getLatSpanE6()
    {
        int i = 0;
        int j = 0;
        do
        {
            if (j >= overlays.length)
            {
                return i;
            }
            i = Math.max(i, overlays[j].getLatSpanE6());
            j++;
        } while (true);
    }

    public int getLonSpanE6()
    {
        int i = 0;
        int j = 0;
        do
        {
            if (j >= overlays.length)
            {
                return i;
            }
            i = Math.max(i, overlays[j].getLonSpanE6());
            j++;
        } while (true);
    }

    public Overlay[] getOverlays()
    {
        return overlays;
    }

    public boolean onTap(GeoPoint geopoint, MapView mapview)
    {
        boolean flag = false;
        int i = 0;
        do
        {
            if (i >= overlays.length)
            {
                return flag;
            }
            if (overlays[i] instanceof PinOverlay)
            {
                flag = overlays[i].onTap(geopoint, mapview);
            }
            i++;
        } while (true);
    }
}

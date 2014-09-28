// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.maps.routing;

import android.os.AsyncTask;
import java.io.IOException;
import java.util.ArrayList;
import jp.co.yahoo.android.maps.HttpClient;
import jp.co.yahoo.android.maps.viewlayer.Coordinate;
import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonToken;
import org.json.JSONArray;
import org.json.JSONObject;

// Referenced classes of package jp.co.yahoo.android.maps.routing:
//            RouteControl, RLine, GPoint

public class RouteSearch extends AsyncTask
{
    public static interface RouteSearchListener
    {

        public abstract boolean endRouteSearch(RouteSearch routesearch);
    }


    private static String ROUTE_API = "http://navi.olp.yahooapis.jp/OpenLocalPlatform/V1/routeSearch";
    private String APPID;
    public int error;
    private int mTollway;
    private String m_bfree;
    private String m_date;
    private int m_goalFloorLevel;
    private Coordinate m_goalPos;
    private int m_guide;
    private RLine m_oldLine;
    private RouteControl m_routeCtl;
    private RouteSearchListener m_routeSearchListener;
    private int m_startFloorLevel;
    private Coordinate m_startPos;
    private String m_traffic;
    private String m_url;
    public int tollway;

    public RouteSearch()
    {
        m_url = "";
        m_startPos = null;
        m_goalPos = null;
        m_startFloorLevel = 0;
        m_goalFloorLevel = 0;
        tollway = 0;
        m_traffic = "walk";
        error = 0;
        m_routeCtl = null;
        m_routeSearchListener = null;
        m_guide = 2;
        m_bfree = "1";
        m_oldLine = null;
        m_date = null;
        mTollway = 1;
        APPID = "";
        m_url = ROUTE_API;
    }

    private String floorLevelToString(int i)
    {
        if (i >= 0)
        {
            return (new StringBuilder(String.valueOf(i + 1))).append("F").toString();
        } else
        {
            int j = i * -1;
            return (new StringBuilder("B")).append(j).append("F").toString();
        }
    }

    private boolean jsonpuser(JsonParser jsonparser)
    {
        if (jsonparser.nextToken() != JsonToken.START_OBJECT) goto _L2; else goto _L1
_L1:
        if (jsonparser.nextToken() == JsonToken.END_OBJECT)
        {
            return false;
        }
        if (jsonparser.getCurrentToken() != JsonToken.START_OBJECT)
        {
            break; /* Loop/switch isn't completed */
        }
        Exception exception;
        if ("ResultInfo".equals(jsonparser.getCurrentName()))
        {
            puserResultInfo(jsonparser);
            continue; /* Loop/switch isn't completed */
        }
        try
        {
            jsonparser.skipChildren();
        }
        // Misplaced declaration of an exception variable
        catch (Exception exception)
        {
            error = 1;
            return false;
        }
        if (true) goto _L1; else goto _L3
_L3:
        if (jsonparser.getCurrentToken() != JsonToken.START_ARRAY || !"Feature".equals(jsonparser.getCurrentName())) goto _L1; else goto _L4
_L4:
        boolean flag = puserFeature(jsonparser);
        if (flag) goto _L1; else goto _L2
_L2:
        return false;
    }

    private boolean jsonpuser(JSONObject jsonobject)
    {
        if (jsonobject != null) goto _L2; else goto _L1
_L1:
        return false;
_L2:
        JSONArray jsonarray;
        try
        {
            jsonarray = jsonobject.optJSONArray("Feature");
        }
        catch (Exception exception)
        {
            error = 1;
            return false;
        }
        if (jsonarray != null)
        {
            break MISSING_BLOCK_LABEL_32;
        }
        error = 1;
        return false;
        int i = 0;
_L4:
        JSONObject jsonobject1;
        if (i < jsonarray.length())
        {
            break MISSING_BLOCK_LABEL_116;
        }
        m_routeCtl.getRLine(-1 + m_routeCtl.count()).direction = "10";
        jsonobject1 = jsonobject.optJSONObject("ResultInfo");
        m_routeCtl.defTotalTime = 0L;
        if (jsonobject1 == null)
        {
            break MISSING_BLOCK_LABEL_139;
        }
        String s = jsonobject1.optJSONObject("Description").optString("TotalTime");
        m_routeCtl.defTotalTime = Integer.parseInt(s);
        break MISSING_BLOCK_LABEL_139;
        boolean flag = jsonpuserFeature(jsonarray.optJSONObject(i));
        if (!flag)
        {
            break; /* Loop/switch isn't completed */
        }
        i++;
        if (true) goto _L4; else goto _L3
_L3:
        if (true) goto _L1; else goto _L5
_L5:
        return true;
    }

    private boolean jsonpuserFeature(JSONObject jsonobject)
    {
        RLine rline = new RLine();
        rline.lno = m_routeCtl.count();
        JSONObject jsonobject1 = jsonobject.optJSONObject("Geometry");
        if (jsonobject1 == null || jsonobject1.equals(JSONObject.NULL))
        {
            error = 1;
            return false;
        }
        jsonpuserGeometry(jsonobject1, rline);
        JSONObject jsonobject2 = jsonobject.optJSONObject("RouteInfo");
        if (jsonobject2 == null || jsonobject2.equals(JSONObject.NULL))
        {
            rline.direction = "0";
            rline.time = 0.0D;
            rline.distance = 0.0D;
            m_routeCtl.addRLine(rline);
            return true;
        }
        jsonpuserRouteInfo(jsonobject2, rline);
        if (rline.lno == 0 && rline.distance == 0.0D)
        {
            error = 2;
            return false;
        } else
        {
            m_routeCtl.addRLine(rline);
            return true;
        }
    }

    private boolean jsonpuserGeometry(JSONObject jsonobject, RLine rline)
    {
        String s = jsonobject.optString("Coordinates", null);
        if (s == null)
        {
            return false;
        }
        String as[] = s.split("\\s");
        int i = 0;
        do
        {
            if (i >= as.length)
            {
                return true;
            }
            String as1[] = as[i].split(",");
            if (as1.length == 2)
            {
                GPoint gpoint = new GPoint(Double.parseDouble(as1[0]), Double.parseDouble(as1[1]));
                gpoint.l_no = rline.lno;
                gpoint.p_no = i;
                rline.addPoint(gpoint);
            }
            i++;
        } while (true);
    }

    private boolean jsonpuserRouteInfo(JSONObject jsonobject, RLine rline)
    {
        JSONObject jsonobject1 = jsonobject.optJSONObject("Edge");
        JSONObject jsonobject2;
        JSONObject jsonobject3;
        if (jsonobject1 != null)
        {
            if ((jsonobject2 = jsonobject1.optJSONObject("GuideInfo")) != null && (jsonobject3 = jsonobject2.optJSONObject("Guide")) != null)
            {
                String s = jsonobject3.optString("FloorLevel", null);
                if (s != null)
                {
                    rline.floorLevel = Integer.parseInt(s);
                }
                rline.direction = jsonobject3.optString("Direction", null);
                String s1 = jsonobject3.optString("Time", null);
                if (s1 != null)
                {
                    rline.time = Double.parseDouble(s1);
                }
                String s2 = jsonobject3.optString("Distance", null);
                if (s2 != null)
                {
                    rline.distance = Double.parseDouble(s2);
                }
                return true;
            }
        }
        return false;
    }

    private boolean puserError(JsonParser jsonparser)
        throws JsonParseException, IOException
    {
        do
        {
            if (jsonparser.nextToken() == JsonToken.END_OBJECT)
            {
                return false;
            }
            String s = jsonparser.getCurrentName();
            jsonparser.nextToken();
            if ("Message".equals(s))
            {
                while (jsonparser.nextToken() != JsonToken.END_OBJECT) 
                {
                    String s1 = jsonparser.getCurrentName();
                    jsonparser.nextToken();
                    if ("Detail".equals(s1))
                    {
                        while (jsonparser.nextToken() != JsonToken.END_OBJECT) 
                        {
                            if ("Code".equals(jsonparser.getCurrentName()))
                            {
                                m_routeCtl.errorCode = jsonparser.getLongValue();
                            } else
                            {
                                jsonparser.skipChildren();
                            }
                        }
                    } else
                    {
                        jsonparser.skipChildren();
                    }
                }
            } else
            {
                jsonparser.skipChildren();
            }
        } while (true);
    }

    private boolean puserFeature(JsonParser jsonparser)
        throws JsonParseException, IOException
    {
_L2:
        do
        {
            if (jsonparser.nextToken() == JsonToken.END_ARRAY)
            {
                return true;
            }
        } while (jsonparser.getCurrentToken() != JsonToken.START_OBJECT);
        RLine rline = new RLine();
        rline.lno = m_routeCtl.count();
        do
        {
label0:
            {
                if (jsonparser.nextToken() != JsonToken.END_OBJECT)
                {
                    break label0;
                }
                rline.lno;
                if (m_oldLine != null)
                {
                    m_oldLine.nextRLine = rline;
                    rline.backRLine = m_oldLine;
                }
                m_oldLine = rline;
                m_routeCtl.addRLine(rline);
            }
            if (true)
            {
                continue;
            }
            if (jsonparser.getCurrentToken() == JsonToken.START_OBJECT)
            {
                String s = jsonparser.getCurrentName();
                jsonparser.nextToken();
                if ("Geometry".equals(s))
                {
                    puserGeometry(jsonparser, rline);
                } else
                if ("RouteInfo".equals(s))
                {
                    puserRouteInfo(jsonparser, rline);
                } else
                {
                    jsonparser.skipChildren();
                }
            }
        } while (true);
        if (true) goto _L2; else goto _L1
_L1:
    }

    private boolean puserGeometry(JsonParser jsonparser, RLine rline)
        throws JsonParseException, IOException
    {
_L6:
        String as[];
        int i;
        if (jsonparser.nextToken() == JsonToken.END_OBJECT)
        {
            return true;
        }
        if (!"Coordinates".equals(jsonparser.getCurrentName()))
        {
            break MISSING_BLOCK_LABEL_195;
        }
        jsonparser.nextValue();
        String s = jsonparser.getText();
        if (s == null)
        {
            return false;
        }
        as = s.split("\\s");
        i = 0;
_L2:
        String as1[];
        if (i >= as.length)
        {
            continue; /* Loop/switch isn't completed */
        }
        as1 = as[i].split(",");
        if (as1.length != 2)
        {
            break; /* Loop/switch isn't completed */
        }
        GPoint gpoint = new GPoint(Double.parseDouble(as1[0]), Double.parseDouble(as1[1]));
        gpoint.l_no = rline.lno;
        gpoint.p_no = i;
        rline.addPoint(gpoint);
_L4:
        i++;
        if (true) goto _L2; else goto _L1
_L1:
        if (as1.length != 3) goto _L4; else goto _L3
_L3:
        GPoint gpoint1 = new GPoint(Double.parseDouble(as1[0]), Double.parseDouble(as1[1]), Double.parseDouble(as1[2]));
        gpoint1.l_no = rline.lno;
        gpoint1.p_no = i;
        rline.addPoint(gpoint1);
          goto _L4
        jsonparser.skipChildren();
        if (true) goto _L6; else goto _L5
_L5:
    }

    private boolean puserGuide(JsonParser jsonparser, RLine rline)
        throws JsonParseException, IOException
    {
        do
        {
            if (jsonparser.nextToken() == JsonToken.END_OBJECT)
            {
                return true;
            }
            String s = jsonparser.getCurrentName();
            jsonparser.nextToken();
            if ("FloorLevel".equals(s))
            {
                rline.floor = jsonparser.getText();
            } else
            if ("Time".equals(s))
            {
                rline.time = jsonparser.getLongValue();
            } else
            if ("Direction".equals(s))
            {
                rline.direction = jsonparser.getText();
            } else
            if ("Distance".equals(s))
            {
                rline.distance = jsonparser.getLongValue();
            } else
            if ("Type".equals(s))
            {
                rline.type = (int)jsonparser.getLongValue();
            } else
            if ("AttributeKey".equals(s))
            {
                rline.attributeKey = jsonparser.getText();
            } else
            if ("RoadType".equals(s))
            {
                rline.roadType = (int)jsonparser.getLongValue();
            } else
            if ("UpdownType".equals(s))
            {
                rline.updownType = (int)jsonparser.getLongValue();
            } else
            if ("Floor".equals(s))
            {
                rline.floorLevel = Integer.parseInt(jsonparser.getText());
            } else
            {
                jsonparser.skipChildren();
            }
        } while (true);
    }

    private boolean puserResultInfo(JsonParser jsonparser)
        throws JsonParseException, IOException
    {
        do
        {
            if (jsonparser.nextToken() == JsonToken.END_OBJECT)
            {
                return false;
            }
            String s = jsonparser.getCurrentName();
            jsonparser.nextToken();
            if ("Description".equals(s))
            {
                while (jsonparser.nextToken() != JsonToken.END_OBJECT) 
                {
                    String s1 = jsonparser.getCurrentName();
                    jsonparser.nextToken();
                    if ("TotalTime".equals(s1))
                    {
                        m_routeCtl.defTotalTime = jsonparser.getLongValue();
                    } else
                    {
                        jsonparser.skipChildren();
                    }
                }
            } else
            {
                jsonparser.skipChildren();
            }
        } while (true);
    }

    private boolean puserRouteInfo(JsonParser jsonparser, RLine rline)
        throws JsonParseException, IOException
    {
        do
        {
            String s;
            do
            {
                do
                {
                    if (jsonparser.nextToken() == JsonToken.END_OBJECT)
                    {
                        return true;
                    }
                } while (jsonparser.getCurrentToken() != JsonToken.START_OBJECT);
                s = jsonparser.getCurrentName();
                jsonparser.nextToken();
            } while (!"Edge".equals(s));
            while (jsonparser.nextToken() != JsonToken.END_OBJECT) 
            {
                if ("GuideInfo".equals(jsonparser.getCurrentName()))
                {
                    jsonparser.nextToken();
                    while (jsonparser.nextToken() != JsonToken.END_OBJECT) 
                    {
                        String s1 = jsonparser.getCurrentName();
                        jsonparser.nextToken();
                        if ("Guide".equals(s1))
                        {
                            puserGuide(jsonparser, rline);
                        }
                    }
                } else
                {
                    jsonparser.skipChildren();
                }
            }
        } while (true);
    }

    protected volatile transient Object doInBackground(Object aobj[])
    {
        return doInBackground((String[])aobj);
    }

    protected transient ArrayList doInBackground(String as[])
    {
        search();
        return null;
    }

    protected volatile void onPostExecute(Object obj)
    {
        onPostExecute((ArrayList)obj);
    }

    protected void onPostExecute(ArrayList arraylist)
    {
        if (m_routeSearchListener != null)
        {
            m_routeSearchListener.endRouteSearch(this);
        }
    }

    public boolean search()
    {
        String s;
        java.io.InputStream inputstream;
        JsonFactory jsonfactory;
        if (m_traffic.equals("metro"))
        {
            s = (new StringBuilder(String.valueOf(m_url))).append("?output=json&guide=").append(m_guide).append("&appid=").append(APPID).append("&coordinates=").append(m_startPos.lon).append(",").append(m_startPos.lat).append(",").append(floorLevelToString(m_startFloorLevel)).append(",").append(m_goalPos.lon).append(",").append(m_goalPos.lat).append(",").append(floorLevelToString(m_goalFloorLevel)).append("&traffic=").append(m_traffic).append("&cformat=1&guidelevel=0&restrict=0&deform=0&bfree=").append(m_bfree).toString();
        } else
        {
            s = (new StringBuilder(String.valueOf(m_url))).append("?output=json&guide=").append(m_guide).append("&appid=").append(APPID).append("&coordinates=").append(m_startPos.lon).append(",").append(m_startPos.lat).append(",").append(m_goalPos.lon).append(",").append(m_goalPos.lat).append("&traffic=").append(m_traffic).append("&guidelevel=0&restrict=0&deform=0").toString();
        }
        if (m_traffic.equals("car"))
        {
            s = (new StringBuilder(String.valueOf(s))).append("&tollway=").append(mTollway).toString();
        }
        if (m_date != null && !m_date.equals(""))
        {
            s = (new StringBuilder(String.valueOf(s))).append("&date=").append(m_date).toString();
        }
        inputstream = HttpClient.httprun(s);
        jsonfactory = new JsonFactory();
        try
        {
            jsonpuser(jsonfactory.createJsonParser(inputstream));
        }
        catch (Exception exception)
        {
            error = 1;
            return false;
        }
        if (m_routeCtl.count() == 0)
        {
            error = 2;
            return false;
        }
        RLine rline = m_routeCtl.getRLine(0);
        if (rline == null || rline.distance == 0.0D)
        {
            error = 2;
            return false;
        } else
        {
            return true;
        }
    }

    public void setAPPID(String s)
    {
        APPID = s;
    }

    public void setBfree(String s)
    {
        m_bfree = s;
    }

    public void setDate(String s)
    {
        m_date = s;
    }

    public void setGoalPos(double d, double d1)
    {
        m_goalPos = new Coordinate(d, d1);
    }

    public void setGoalPos(double d, double d1, int i)
    {
        m_goalPos = new Coordinate(d, d1);
        m_goalFloorLevel = i;
    }

    public void setGuide(int i)
    {
        m_guide = i;
    }

    public void setRouteCtl(RouteControl routecontrol)
    {
        m_routeCtl = routecontrol;
    }

    public void setRouteSearchListener(RouteSearchListener routesearchlistener)
    {
        m_routeSearchListener = routesearchlistener;
    }

    public void setStartPos(double d, double d1)
    {
        m_startPos = new Coordinate(d, d1);
    }

    public void setStartPos(double d, double d1, int i)
    {
        m_startPos = new Coordinate(d, d1);
        m_startFloorLevel = i;
    }

    public void setTollway(int i)
    {
        mTollway = i;
    }

    public void setTraffic(String s)
    {
        m_traffic = s;
    }

}

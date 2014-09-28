// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.api;

import android.content.Context;
import android.content.res.Resources;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import jp.co.yahoo.android.apps.transit.api.data.APIError;
import jp.co.yahoo.android.apps.transit.api.data.ConditionData;
import jp.co.yahoo.android.apps.transit.api.data.NaviSearchData;
import jp.co.yahoo.android.apps.transit.common.StringUtil;
import jp.co.yahoo.android.apps.transit.common.util.TransitUtil;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

// Referenced classes of package jp.co.yahoo.android.apps.transit.api:
//            YolpApiBase

public class NaviSearch extends YolpApiBase
{

    public static String NAVI_APPID;
    public static String NAVI_URL;
    public static NaviSearchData m_routeList = null;
    private Resources res;

    public NaviSearch(Context context, ApiBase.ApiListener apilistener)
    {
        super(context, apilistener);
        res = context.getResources();
        NAVI_URL = res.getString(0x7f0d0042);
        NAVI_APPID = res.getString(0x7f0d003b);
        setAppid(NAVI_APPID);
        setUri(NAVI_URL);
        m_routeList = new NaviSearchData();
        setResultCount(6);
        setDeital("full");
        setWeburl("1");
    }

    private ArrayList getRidingPosition(JSONObject jsonobject)
    {
        Object obj;
        ArrayList arraylist;
        JSONArray jsonarray;
        int j;
        jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.RidingPosition ridingposition;
        JSONArray jsonarray1;
        int k;
        int l;
        jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.RidingPositionCar ridingpositioncar;
        JSONArray jsonarray2;
        int i1;
        int j1;
        try
        {
            obj = jsonobject.opt("RidingPosition");
        }
        catch (JSONException jsonexception)
        {
            jsonexception.printStackTrace();
            return null;
        }
        if (obj == null) goto _L2; else goto _L1
_L1:
        if (!(obj instanceof JSONArray)) goto _L2; else goto _L3
_L3:
        jsonarray = jsonobject.optJSONArray("RidingPosition");
        int i = jsonarray.length();
        arraylist = new ArrayList(i);
        j = 0;
_L9:
        if (j >= jsonarray.length())
        {
            break; /* Loop/switch isn't completed */
        }
        ridingposition = new jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.RidingPosition();
        JSONObject jsonobject1 = jsonarray.getJSONObject(j);
        if (!TransitUtil.isEmpty(jsonobject1.optString("Direction")))
        {
            ridingposition.direction = jsonobject1.optString("Direction");
        }
        ridingposition.isFrontFirstCar = jsonobject1.optInt("IsFrontFirstCar");
        jsonarray1 = jsonobject1.getJSONArray("Car");
        k = jsonarray1.length();
        ArrayList arraylist1 = new ArrayList(k);
        ridingposition.Cars = arraylist1;
        l = 0;
_L7:
        if (l >= k)
        {
            break; /* Loop/switch isn't completed */
        }
        ridingpositioncar = new jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.RidingPositionCar();
        JSONObject jsonobject2 = jsonarray1.getJSONObject(l);
        ridingpositioncar.allOutflowsText = jsonobject2.optString("AllOutflowsText");
        ridingpositioncar.numOfCar = jsonobject2.optString("NumOfCar");
        jsonarray2 = jsonobject2.getJSONArray("Outflow");
        i1 = jsonarray2.length();
        ArrayList arraylist2 = new ArrayList(i1);
        ridingpositioncar.outflow = arraylist2;
        j1 = 0;
_L5:
        if (j1 >= i1)
        {
            break; /* Loop/switch isn't completed */
        }
        jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.RidingPositionOutflow ridingpositionoutflow = new jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.RidingPositionOutflow();
        JSONObject jsonobject3 = jsonarray2.getJSONObject(j1);
        ridingpositionoutflow.carNo = jsonobject3.getString("CarNo");
        ridingpositionoutflow.Means = jsonobject3.getString("Means");
        ridingpositioncar.outflow.add(ridingpositionoutflow);
        j1++;
        if (true) goto _L5; else goto _L4
_L4:
        ridingposition.Cars.add(ridingpositioncar);
        l++;
        if (true) goto _L7; else goto _L6
_L6:
        arraylist.add(ridingposition);
        j++;
        if (true) goto _L9; else goto _L8
_L2:
        arraylist = null;
_L8:
        return arraylist;
    }

    private ArrayList getStopStation(JSONObject jsonobject)
    {
        JSONArray jsonarray;
        ArrayList arraylist;
        jsonarray = jsonobject.optJSONArray("StopStation");
        arraylist = null;
        if (jsonarray == null) goto _L2; else goto _L1
_L1:
        JSONArray jsonarray1;
        int i;
        jsonarray1 = jsonobject.optJSONArray("StopStation");
        arraylist = new ArrayList(jsonarray1.length());
        i = 0;
_L3:
        if (i >= jsonarray1.length())
        {
            break; /* Loop/switch isn't completed */
        }
        JSONObject jsonobject1;
        jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.StopStation stopstation;
        String s3;
        int l;
        jsonobject1 = jsonarray1.getJSONObject(i);
        stopstation = new jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.StopStation();
        stopstation.code = jsonobject1.optString("Code");
        stopstation.name = jsonobject1.optString("Name");
        if (jsonobject1.optString("DepartureTime") == null || jsonobject1.optString("DepartureTime") == "")
        {
            break MISSING_BLOCK_LABEL_260;
        }
        String s2 = jsonobject1.optString("DepartureTime");
        Object aobj1[] = new Object[1];
        aobj1[0] = Integer.valueOf(Integer.parseInt(s2));
        s3 = String.format("%04d", aobj1);
        l = Integer.parseInt(s3.substring(0, 2));
        int i1;
        if (l < 24)
        {
            break MISSING_BLOCK_LABEL_205;
        }
        i1 = l - 24;
        s3 = (new StringBuilder()).append("0").append(i1).append(s3.substring(2, 4)).toString();
        stopstation.departureTime = (new StringBuilder()).append(s3.substring(0, 2)).append(":").append(s3.substring(2, 4)).toString();
        stopstation.departureUnixTimestamp = Long.valueOf(jsonobject1.optLong("DepartureUnixTimestamp"));
        String s1;
        int j;
        if (jsonobject1.optString("ArraivalTime") == null || jsonobject1.optString("ArraivalTime") == "")
        {
            break MISSING_BLOCK_LABEL_432;
        }
        String s = jsonobject1.optString("ArraivalTime");
        Object aobj[] = new Object[1];
        aobj[0] = Integer.valueOf(Integer.parseInt(s));
        s1 = String.format("%04d", aobj);
        j = Integer.parseInt(s1.substring(0, 2));
        int k;
        if (j < 24)
        {
            break MISSING_BLOCK_LABEL_376;
        }
        k = j - 24;
        s1 = (new StringBuilder()).append("0").append(k).append(s1.substring(2, 4)).toString();
        stopstation.arraivalTime = (new StringBuilder()).append(s1.substring(0, 2)).append(":").append(s1.substring(2, 4)).toString();
        stopstation.arrivalUnixTimestamp = Long.valueOf(jsonobject1.optLong("ArrivalUnixTimestamp"));
        arraylist.add(stopstation);
_L4:
        i++;
        if (true) goto _L3; else goto _L2
        JSONException jsonexception;
        jsonexception;
        jsonexception.printStackTrace();
          goto _L4
_L2:
        return arraylist;
    }

    private jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.Edge parseEdge(JSONObject jsonobject, HashMap hashmap, HashMap hashmap1)
    {
        JSONObject jsonobject1;
        JSONArray jsonarray;
        if (jsonobject != null)
        {
            if ((jsonobject1 = jsonobject.optJSONObject("Property")) != null && ((jsonarray = jsonobject.optJSONArray("Vertex")) != null && jsonarray.length() >= 2))
            {
                jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.Edge edge = new jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.Edge();
                int i = 0;
                while (i < jsonarray.length()) 
                {
                    JSONObject jsonobject4 = jsonarray.optJSONObject(i);
                    if (jsonobject4.optString("Type", "").equals("Start"))
                    {
                        edge.startPointTarget = jsonobject4.optJSONObject("Property").optJSONObject("Station").optString("Target");
                    } else
                    if (jsonobject4.optString("Type", "").equals("End"))
                    {
                        edge.goalPointTarget = jsonobject4.optJSONObject("Property").optJSONObject("Station").optString("Target");
                    }
                    i++;
                }
                edge.edgeid = jsonobject1.optInt("EdgeId", 0);
                edge.passStCount = jsonobject1.optInt("StopStationCount", 0);
                edge.time = jsonobject1.optInt("TimeOnBoard", 0);
                edge.traffic = jsonobject1.optInt("Traffic", 0);
                edge.fromState = jsonobject1.optInt("FromState", 0);
                edge.toState = jsonobject1.optInt("ToState", 0);
                edge.color = jsonobject1.optInt("Color", 0);
                edge.distance = jsonobject1.optInt("Distance");
                edge.railname = jsonobject1.optString("RailName");
                edge.railDispName = jsonobject1.optString("RailDisplayName");
                edge.timeType = jsonobject1.optString("TimeType");
                edge.arrivalDatetime = jsonobject1.optString("ArrivalDatetime");
                edge.departureDatetime = jsonobject1.optString("DepartureDatetime");
                edge.arrivalTrackNumber = jsonobject1.optString("ArrivalTrackNumber");
                edge.departureTrackNumber = jsonobject1.optString("DepartureTrackNumber");
                edge.trainId = jsonobject1.optString("TrainId");
                edge.AirportTicketLinkSP = jsonobject1.optString("AirportTicketLinkSP");
                edge.AirportDPLinkSP = jsonobject1.optString("AirportDPLinkSP");
                edge.AirportDPLinkSPDisp = jsonobject1.optString("AirportDPLinkSPDisp");
                JSONObject jsonobject2 = jsonobject1.optJSONObject("TimeAttention");
                if (jsonobject2 != null)
                {
                    edge.attentionId = jsonobject2.optInt("Id");
                    edge.attentionText = jsonobject2.optString("Text");
                }
                edge.StopStations = getStopStation(jsonobject1);
                edge.RidingPosition = getRidingPosition(jsonobject1);
                JSONArray jsonarray1 = jsonobject1.optJSONArray("CarTypeList");
                if (jsonarray1 != null)
                {
                    ArrayList arraylist = new ArrayList(jsonarray1.length());
                    for (int j = 0; j < jsonarray1.length(); j++)
                    {
                        JSONObject jsonobject3 = jsonarray1.optJSONObject(j);
                        if (jsonobject3 != null)
                        {
                            arraylist.add(jsonobject3.optString("name"));
                        }
                    }

                    edge.carType = arraylist;
                }
                return setDispName(edge);
            }
        }
        return null;
    }

    private ArrayList parsePrice(JSONArray jsonarray)
    {
        ArrayList arraylist;
        if (jsonarray == null || jsonarray.length() < 1)
        {
            arraylist = null;
        } else
        {
            arraylist = new ArrayList(jsonarray.length());
            int i = 0;
            while (i < jsonarray.length()) 
            {
                JSONObject jsonobject = jsonarray.optJSONObject(i);
                String s = jsonobject.optString("Value");
                jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.Price price = new jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.Price();
                price.value = s;
                price.edgeFrom = jsonobject.optInt("EdgeFrom", 0);
                price.edgeTo = jsonobject.optInt("EdgeTo", 0);
                price.type = null;
                if (jsonobject.optString("Type") != null)
                {
                    price.type = jsonobject.optString("Type");
                }
                if (!jsonobject.isNull("TicketType"))
                {
                    price.ticketType = jsonobject.optString("TicketType");
                }
                price.previousTaxFare = parseStringToBoolean(jsonobject, "PreviousTaxFare");
                arraylist.add(price);
                i++;
            }
        }
        return arraylist;
    }

    private jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.NaviPointData parseStation(JSONObject jsonobject)
    {
        jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.NaviPointData navipointdata = new jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.NaviPointData();
        if (jsonobject != null) goto _L2; else goto _L1
_L1:
        navipointdata = null;
_L4:
        return navipointdata;
_L2:
        String s;
        String as[];
        String as1[];
        try
        {
            s = jsonobject.optString("Id");
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
            return null;
        }
        if (s == null) goto _L4; else goto _L3
_L3:
        if (s.length() <= 0) goto _L4; else goto _L5
_L5:
        navipointdata.target = s;
        navipointdata.stationName = jsonobject.optString("Name");
        navipointdata.stationCode = jsonobject.optString("StationCode");
        navipointdata.areaCode = jsonobject.optInt("AreaCode");
        navipointdata.shortAddress = jsonobject.optString("ShortAddress");
        navipointdata.type = jsonobject.optInt("Type", 1);
        if (navipointdata.type != 128)
        {
            break MISSING_BLOCK_LABEL_152;
        }
        as1 = jsonobject.optString("NearStationCoordinates").split(",");
        if (as1 == null)
        {
            break MISSING_BLOCK_LABEL_152;
        }
        if (as1.length > 1)
        {
            navipointdata.nearStLon = as1[0];
            navipointdata.nearStLat = as1[1];
        }
        as = jsonobject.optJSONObject("Geometry").optString("Coordinates").split(",");
        if (as == null) goto _L4; else goto _L6
_L6:
        if (as.length <= 1) goto _L4; else goto _L7
_L7:
        navipointdata.lon = as[0];
        navipointdata.lat = as[1];
        return navipointdata;
    }

    private boolean parseStringToBoolean(JSONObject jsonobject, String s)
    {
        return jsonobject.optString(s).equals("True");
    }

    private jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.Edge setDispName(jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.Edge edge)
    {
        int i;
        int j;
        i = ((jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.NaviPointData)m_routeList.points.get(edge.startPointTarget)).type;
        j = ((jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.NaviPointData)m_routeList.points.get(edge.goalPointTarget)).type;
        if (i != 128 && j != 128) goto _L2; else goto _L1
_L1:
        if (edge.railname.equals("\u5F92\u6B69") || edge.railname.equals("") || edge.railname.equals("\u51FA\u53E3")) goto _L4; else goto _L3
_L3:
        edge.railDispName = (new StringBuilder()).append("\u51FA\u53E3\uFF1A").append(edge.railname).toString();
_L6:
        return edge;
_L4:
        edge.railDispName = "";
        return edge;
_L2:
        if (edge.railDispName == null || edge.railDispName == "")
        {
            edge.railDispName = edge.railname;
        }
        if (edge.carType != null)
        {
            edge.railDispName = (new StringBuilder()).append(edge.railDispName).append("(").toString();
            for (int k = 0; k < edge.carType.size(); k++)
            {
                if (k > 0)
                {
                    edge.railDispName = (new StringBuilder()).append(edge.railDispName).append("\uFF0B").toString();
                }
                edge.railDispName = (new StringBuilder()).append(edge.railDispName).append((String)edge.carType.get(k)).toString();
            }

            edge.railDispName = (new StringBuilder()).append(edge.railDispName).append(")").toString();
            return edge;
        }
        if (true) goto _L6; else goto _L5
_L5:
    }

    protected void analyzeDictionaly(JSONObject jsonobject)
    {
        HashMap hashmap;
        JSONObject jsonobject1;
        JSONArray jsonarray = jsonobject.optJSONArray("Station");
        hashmap = new HashMap(jsonarray.length());
        int i = 0;
        while (i < jsonarray.length()) 
        {
            jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.NaviPointData navipointdata1 = parseStation(jsonarray.optJSONObject(i));
            if (navipointdata1 != null)
            {
                hashmap.put(navipointdata1.target, navipointdata1);
            }
            i++;
        }
        m_routeList.points = hashmap;
        jsonobject1 = getResultInfo();
        JSONArray jsonarray1;
        ArrayList arraylist;
        ArrayList arraylist1;
        ArrayList arraylist2;
        jsonarray1 = jsonobject1.getJSONObject("QueryInfo").getJSONArray("Feature");
        arraylist = new ArrayList();
        arraylist1 = new ArrayList();
        arraylist2 = new ArrayList();
        int j;
        int k;
        int l;
        int i1;
        j = 0;
        k = 0;
        l = 0;
        i1 = 0;
_L2:
        String s;
        jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.NaviPointData navipointdata;
        if (i1 >= jsonarray1.length())
        {
            break MISSING_BLOCK_LABEL_305;
        }
        s = jsonarray1.getJSONObject(i1).getString("Type");
        navipointdata = (jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.NaviPointData)hashmap.get(jsonarray1.getJSONObject(i1).getJSONObject("Station").getString("Target"));
        if (!s.equals("from"))
        {
            break MISSING_BLOCK_LABEL_217;
        }
        arraylist.add(j, navipointdata);
        j++;
        break MISSING_BLOCK_LABEL_352;
        if (!s.equals("to"))
        {
            break MISSING_BLOCK_LABEL_243;
        }
        arraylist1.add(k, navipointdata);
        k++;
        break MISSING_BLOCK_LABEL_352;
        String s1;
        if (!s.equals("via"))
        {
            break MISSING_BLOCK_LABEL_352;
        }
        s1 = jsonarray1.getJSONObject(i1).getString("Position");
        navipointdata.position = -1;
        if (s1 == null)
        {
            break MISSING_BLOCK_LABEL_290;
        }
        navipointdata.position = Integer.parseInt(s1);
        arraylist2.add(l, navipointdata);
        l++;
        break MISSING_BLOCK_LABEL_352;
        try
        {
            m_routeList.startStationList = arraylist;
            m_routeList.goalStationList = arraylist1;
            m_routeList.viaStationList = arraylist2;
            m_routeList.webUrl = jsonobject1.optString("WebUrl");
            return;
        }
        catch (JSONException jsonexception)
        {
            jsonexception.printStackTrace();
        }
        return;
        i1++;
        if (true) goto _L2; else goto _L1
_L1:
    }

    protected void analyzeFeature(JSONArray jsonarray)
    {
        ArrayList arraylist;
        int i;
        JSONObject jsonobject;
        jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.NaviRouteData naviroutedata;
        JSONObject jsonobject1;
        ArrayList arraylist1;
        int j;
        JSONArray jsonarray1;
        JSONArray jsonarray2;
        JSONObject jsonobject2;
        JSONObject jsonobject3;
        JSONObject jsonobject4;
        JSONArray jsonarray3;
        JSONObject jsonobject6;
        jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.TeikiDetail teikidetail;
        try
        {
            arraylist = new ArrayList(jsonarray.length());
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
            return;
        }
        i = 0;
_L18:
        if (i >= jsonarray.length()) goto _L2; else goto _L1
_L1:
        jsonobject = jsonarray.getJSONObject(i).getJSONObject("RouteInfo");
        naviroutedata = new jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.NaviRouteData();
        jsonobject1 = jsonobject.optJSONObject("Property");
        if (jsonobject1 == null) goto _L4; else goto _L3
_L3:
        jsonarray1 = jsonobject1.optJSONArray("Price");
        if (jsonarray1 == null)
        {
            break MISSING_BLOCK_LABEL_93;
        }
        if (jsonarray1.length() > 0)
        {
            naviroutedata.edgePrice = parsePrice(jsonarray1);
        }
        jsonarray2 = jsonobject1.optJSONArray("ExpPrice");
        if (jsonarray2 == null)
        {
            break MISSING_BLOCK_LABEL_127;
        }
        if (jsonarray2.length() > 0)
        {
            naviroutedata.edgeExpPrice = parsePrice(jsonarray2);
        }
        jsonobject2 = jsonobject1.getJSONObject("TotalPrice");
        if (jsonobject2 == null) goto _L6; else goto _L5
_L5:
        naviroutedata.totalPrice = jsonobject2.optString("TotalPrice");
        naviroutedata.totalExpPrice = jsonobject2.optString("TotalExpPrice");
        naviroutedata.farePrice = jsonobject2.optString("FarePrice");
        naviroutedata.totalPreviousTaxFare = parseStringToBoolean(jsonobject2, "TotalPreviousTaxFare");
        jsonobject3 = jsonobject2.optJSONObject("Teiki");
        if (jsonobject3 == null)
        {
            break MISSING_BLOCK_LABEL_249;
        }
        naviroutedata.Teiki1 = jsonobject3.optString("Teiki1");
        naviroutedata.Teiki3 = jsonobject3.optString("Teiki3");
        naviroutedata.Teiki6 = jsonobject3.optString("Teiki6");
        jsonobject4 = jsonobject2.optJSONObject("TeikiPreviousTaxFare");
        if (jsonobject4 == null)
        {
            break MISSING_BLOCK_LABEL_306;
        }
        naviroutedata.previousTaxFareTeiki1 = parseStringToBoolean(jsonobject4, "Teiki1");
        naviroutedata.previousTaxFareTeiki3 = parseStringToBoolean(jsonobject4, "Teiki3");
        naviroutedata.previousTaxFareTeiki6 = parseStringToBoolean(jsonobject4, "Teiki6");
        jsonarray3 = jsonobject2.optJSONArray("TeikiDetail");
        if (jsonarray3 == null) goto _L6; else goto _L7
_L7:
        if (jsonarray3.length() <= 0) goto _L6; else goto _L8
_L8:
        arraylist1 = new ArrayList(jsonarray3.length());
        j = 0;
_L19:
        if (j >= jsonarray3.length()) goto _L10; else goto _L9
_L9:
        jsonobject6 = jsonarray3.getJSONObject(j);
        if (jsonobject6 != null) goto _L12; else goto _L11
_L12:
        teikidetail = new jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.TeikiDetail();
        teikidetail.edgeFrom = jsonobject6.optInt("EdgeFrom");
        teikidetail.edgeTo = jsonobject6.optInt("EdgeTo");
        teikidetail.previous = parseStringToBoolean(jsonobject6, "Previous");
        teikidetail.teiki1 = jsonobject6.optString("Teiki1");
        teikidetail.teiki3 = jsonobject6.optString("Teiki3");
        teikidetail.teiki6 = jsonobject6.optString("Teiki6");
        arraylist1.add(teikidetail);
          goto _L11
_L10:
        naviroutedata.teikiDetials = arraylist1;
_L6:
        JSONObject jsonobject5 = jsonobject1.getJSONObject("Sort");
        boolean flag;
        boolean flag1;
        boolean flag2;
        JSONArray jsonarray4;
        ArrayList arraylist2;
        ArrayList arraylist3;
        int k;
        int l;
        jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.Edge edge;
        Iterator iterator;
        boolean flag3;
        boolean flag4;
        if (jsonobject5.optInt("Fast") <= 0)
        {
            flag = false;
        } else
        {
            flag = true;
        }
        naviroutedata.fast = flag;
        if (jsonobject5.optInt("Cheap") <= 0)
        {
            flag1 = false;
        } else
        {
            flag1 = true;
        }
        naviroutedata.cheap = flag1;
        if (jsonobject5.optInt("Easy") <= 0)
        {
            flag2 = false;
        } else
        {
            flag2 = true;
        }
        naviroutedata.easy = flag2;
_L4:
        naviroutedata.transfercount = jsonobject1.optInt("TransferCount", 0);
        naviroutedata.totaltime = jsonobject1.optInt("TimeOnBoard", 0) + jsonobject1.optInt("TimeOther", 0) + jsonobject1.optInt("TimeWalk", 0);
        naviroutedata.totaldistance = jsonobject1.optInt("Distance", 0);
        jsonarray4 = jsonobject.optJSONArray("Edge");
        arraylist2 = new ArrayList(jsonarray4.length());
        arraylist3 = new ArrayList(jsonarray4.length());
        k = jsonarray4.length();
        if (jsonarray4.length() <= 0) goto _L14; else goto _L13
_L13:
        l = 0;
_L20:
        if (l >= k) goto _L14; else goto _L15
_L15:
        edge = parseEdge(jsonarray4.optJSONObject(l), null, null);
        arraylist2.add(edge);
        iterator = arraylist3.iterator();
_L17:
        flag3 = iterator.hasNext();
        flag4 = false;
        if (!flag3)
        {
            break MISSING_BLOCK_LABEL_756;
        }
        if (((Integer)iterator.next()).intValue() != edge.traffic) goto _L17; else goto _L16
_L16:
        flag4 = true;
        if (flag4)
        {
            break MISSING_BLOCK_LABEL_775;
        }
        arraylist3.add(Integer.valueOf(edge.traffic));
        if (l != 0)
        {
            break MISSING_BLOCK_LABEL_790;
        }
        naviroutedata.startTime = edge.departureDatetime;
        int i1 = k - 1;
        if (l != i1)
        {
            break MISSING_BLOCK_LABEL_911;
        }
        jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.Edge edge1 = new jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.Edge();
        edge1.fromState = edge.toState;
        edge1.startPointTarget = edge.goalPointTarget;
        arraylist2.add(edge1);
        naviroutedata.goalTime = edge.arrivalDatetime;
        break MISSING_BLOCK_LABEL_911;
_L14:
        naviroutedata.edges = arraylist2;
        naviroutedata.types = arraylist3;
        arraylist.add(naviroutedata);
        i++;
          goto _L18
_L2:
        m_routeList.routes = arraylist;
        m_routeList.url = getUrl();
        setResult(m_routeList);
        return;
_L11:
        j++;
          goto _L19
        l++;
          goto _L20
    }

    protected void analyzeYolpError(JSONObject jsonobject)
    {
        if (jsonobject == null || jsonobject.isNull("Code")) goto _L2; else goto _L1
_L1:
        APIError apierror = new APIError();
        int j = Integer.parseInt(jsonobject.optString("Code"));
        int i = j;
_L20:
        i;
        JVM INSTR tableswitch 4001 4030: default 172
    //                   4001 217
    //                   4002 217
    //                   4003 217
    //                   4004 217
    //                   4005 172
    //                   4006 172
    //                   4007 172
    //                   4008 172
    //                   4009 172
    //                   4010 172
    //                   4011 232
    //                   4012 247
    //                   4013 262
    //                   4014 277
    //                   4015 292
    //                   4016 307
    //                   4017 322
    //                   4018 337
    //                   4019 352
    //                   4020 367
    //                   4021 382
    //                   4022 172
    //                   4023 172
    //                   4024 172
    //                   4025 172
    //                   4026 172
    //                   4027 172
    //                   4028 172
    //                   4029 172
    //                   4030 397;
           goto _L3 _L4 _L4 _L4 _L4 _L3 _L3 _L3 _L3 _L3 _L3 _L5 _L6 _L7 _L8 _L9 _L10 _L11 _L12 _L13 _L14 _L15 _L3 _L3 _L3 _L3 _L3 _L3 _L3 _L3 _L16
_L3:
        String s = getContext().getString(0x7f0d0140);
_L18:
        apierror.setCode(jsonobject.optString("Code"));
        apierror.setApiMessage(jsonobject.optString("Message"));
        apierror.setMessage(s);
        setError(apierror);
_L2:
        return;
_L4:
        s = getContext().getString(0x7f0d0140);
        continue; /* Loop/switch isn't completed */
_L5:
        s = getContext().getString(0x7f0d0146);
        continue; /* Loop/switch isn't completed */
_L6:
        s = getContext().getString(0x7f0d0141);
        continue; /* Loop/switch isn't completed */
_L7:
        s = getContext().getString(0x7f0d014b);
        continue; /* Loop/switch isn't completed */
_L8:
        s = getContext().getString(0x7f0d0147);
        continue; /* Loop/switch isn't completed */
_L9:
        s = getContext().getString(0x7f0d0144);
        continue; /* Loop/switch isn't completed */
_L10:
        s = getContext().getString(0x7f0d0143);
        continue; /* Loop/switch isn't completed */
_L11:
        s = getContext().getString(0x7f0d0145);
        continue; /* Loop/switch isn't completed */
_L12:
        s = getContext().getString(0x7f0d014c);
        continue; /* Loop/switch isn't completed */
_L13:
        s = getContext().getString(0x7f0d0149);
        continue; /* Loop/switch isn't completed */
_L14:
        s = getContext().getString(0x7f0d0142);
        continue; /* Loop/switch isn't completed */
_L15:
        s = getContext().getString(0x7f0d0148);
        continue; /* Loop/switch isn't completed */
_L16:
        s = getContext().getString(0x7f0d014a);
        if (true) goto _L18; else goto _L17
_L17:
        Exception exception;
        exception;
        i = 0;
        if (true) goto _L20; else goto _L19
_L19:
    }

    public void exec()
    {
        request();
    }

    public void setCondition(ConditionData conditiondata)
    {
        String s7;
        String s;
        String s1;
        String s2;
        String s3;
        String s4;
        String s5;
        StringBuffer stringbuffer;
        Object aobj[];
        StringBuffer stringbuffer1;
        Object aobj1[];
        StringBuffer stringbuffer2;
        Object aobj2[];
        StringBuffer stringbuffer3;
        Object aobj3[];
        String s8;
        ArrayList arraylist1;
        String s9;
        String s10;
        try
        {
            s = conditiondata.startCode;
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
            return;
        }
        if (s == "" || s == null)
        {
            break MISSING_BLOCK_LABEL_27;
        }
        param.put("fcode", s);
        s1 = conditiondata.startLon;
        s2 = conditiondata.startLat;
        if (s1 == "" || s1 == null || s2 == "" || s2 == null) goto _L2; else goto _L1
_L1:
        s10 = (new StringBuilder()).append(StringUtil.trim(conditiondata.startName)).append(",").append(s1).append(",").append(s2).toString();
        param.put("from", URLEncoder.encode(s10, "UTF-8"));
_L9:
        s3 = conditiondata.goalCode;
        if (s3 == "" || s3 == null)
        {
            break MISSING_BLOCK_LABEL_157;
        }
        param.put("tcode", s3);
        s4 = conditiondata.goalLon;
        s5 = conditiondata.goalLat;
        if (s4 == "" || s4 == null || s5 == "" || s5 == null) goto _L4; else goto _L3
_L3:
        s9 = (new StringBuilder()).append(StringUtil.trim(conditiondata.goalName)).append(",").append(s4).append(",").append(s5).toString();
        param.put("to", URLEncoder.encode(s9, "UTF-8"));
_L10:
        if (conditiondata.viaCode != null || conditiondata.viaName != null) goto _L6; else goto _L5
_L5:
        if (res.getInteger(0x7f0c006b) != conditiondata.type) goto _L8; else goto _L7
_L7:
        s7 = Integer.toString(res.getInteger(0x7f0c0070));
_L14:
        stringbuffer = (new StringBuffer()).append(conditiondata.year);
        aobj = new Object[1];
        aobj[0] = Integer.valueOf(conditiondata.month);
        stringbuffer1 = stringbuffer.append(String.format("%02d", aobj));
        aobj1 = new Object[1];
        aobj1[0] = Integer.valueOf(conditiondata.day);
        stringbuffer2 = stringbuffer1.append(String.format("%02d", aobj1));
        aobj2 = new Object[1];
        aobj2[0] = Integer.valueOf(conditiondata.hour);
        stringbuffer3 = stringbuffer2.append(String.format("%02d", aobj2));
        aobj3 = new Object[1];
        aobj3[0] = Integer.valueOf(conditiondata.minite);
        s8 = stringbuffer3.append(String.format("%02d", aobj3)).toString();
        param.put("date", s8);
        param.put("type", s7);
        param.put("s", Integer.toString(conditiondata.sort));
        param.put("ws", Integer.toString(conditiondata.walkSpeed));
        param.put("expkind", Integer.toString(conditiondata.seatKind));
        if (!TransitUtil.isEmpty(conditiondata.ticket))
        {
            param.put("ticket", conditiondata.ticket);
        }
        if (!TransitUtil.isEmpty(conditiondata.passtype))
        {
            param.put("passtype", conditiondata.passtype);
        }
        arraylist1 = new ArrayList();
        if (conditiondata.superExpress)
        {
            arraylist1.add(res.getString(0x7f0d0367));
        }
        if (conditiondata.express)
        {
            arraylist1.add(res.getString(0x7f0d0362));
        }
        if (conditiondata.airplane)
        {
            arraylist1.add(res.getString(0x7f0d0361));
        }
        if (conditiondata.highwayBus)
        {
            arraylist1.add(res.getString(0x7f0d0363));
        }
        if (conditiondata.localBus)
        {
            arraylist1.add(res.getString(0x7f0d0366));
        }
        if (conditiondata.ferry)
        {
            arraylist1.add(res.getString(0x7f0d0364));
        }
        if (conditiondata.midnightBus)
        {
            arraylist1.add(res.getString(0x7f0d0365));
        }
        param.put("ptn", StringUtil.join(arraylist1, ","));
        param.put("qp", "1");
        return;
_L2:
        param.put("from", URLEncoder.encode(StringUtil.trim(conditiondata.startName), "UTF-8"));
          goto _L9
_L4:
        param.put("to", URLEncoder.encode(StringUtil.trim(conditiondata.goalName), "UTF-8"));
          goto _L10
_L6:
        if (conditiondata.viaCode == null || conditiondata.viaCode.size() != 1 || TransitUtil.isEmpty((String)conditiondata.viaCode.get(0))) goto _L12; else goto _L11
_L11:
        param.put("viacode", conditiondata.viaCode.get(0));
          goto _L5
_L12:
        if (TransitUtil.isEmptyArry(conditiondata.viaName) && TransitUtil.isEmptyArry(conditiondata.viaCode)) goto _L5; else goto _L13
_L13:
        ArrayList arraylist = new ArrayList();
        int i = 0;
_L15:
        if (i >= conditiondata.viaName.size())
        {
            break MISSING_BLOCK_LABEL_1018;
        }
        if (conditiondata.viaCode != null && !TransitUtil.isEmpty((String)conditiondata.viaCode.get(i)))
        {
            arraylist.add(conditiondata.viaCode.get(i));
            break MISSING_BLOCK_LABEL_1062;
        }
        arraylist.add(conditiondata.viaName.get(i));
        break MISSING_BLOCK_LABEL_1062;
        param.put("via", URLEncoder.encode(TransitUtil.join(arraylist, ","), "UTF-8"));
          goto _L5
_L8:
        String s6 = Integer.toString(conditiondata.type);
        s7 = s6;
          goto _L14
        i++;
          goto _L15
    }

    public void setDate(String s)
    {
        param.put("date", s);
    }

    public void setDeital(String s)
    {
        param.put("detail", s);
    }

    public void setEngine(String s)
    {
        param.put("engine", s);
    }

    public void setFrom(String s)
    {
        try
        {
            param.put("from", URLEncoder.encode(s, "UTF-8"));
            return;
        }
        catch (UnsupportedEncodingException unsupportedencodingexception)
        {
            return;
        }
    }

    public void setMode(String s)
    {
        param.put("mode", s);
    }

    public void setPri(String s)
    {
        param.put("pri", s);
    }

    public void setPs(String s)
    {
        param.put("ps", s);
    }

    public void setPtn(String s)
    {
        param.put("ptn", s);
    }

    public void setSort(String s)
    {
        param.put("s", s);
    }

    public void setTo(String s)
    {
        try
        {
            param.put("to", URLEncoder.encode(s, "UTF-8"));
            return;
        }
        catch (UnsupportedEncodingException unsupportedencodingexception)
        {
            return;
        }
    }

    public void setType(String s)
    {
        if (!TransitUtil.isEmpty(s) && s.equals("99"))
        {
            s = "1";
        }
        param.put("type", s);
    }

    public void setVia(String s)
    {
        try
        {
            param.put("via", URLEncoder.encode(s, "UTF-8"));
            return;
        }
        catch (UnsupportedEncodingException unsupportedencodingexception)
        {
            return;
        }
    }

    public void setWeburl(String s)
    {
        param.put("weburl", s);
    }

}

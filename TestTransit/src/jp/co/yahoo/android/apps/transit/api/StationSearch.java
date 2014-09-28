// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.api;

import android.content.Context;
import android.os.Bundle;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import jp.co.yahoo.android.apps.transit.api.data.DiainfoData;
import jp.co.yahoo.android.apps.transit.api.data.RailDirectionData;
import jp.co.yahoo.android.apps.transit.api.data.StationData;
import jp.co.yahoo.android.apps.transit.common.util.TransitUtil;
import org.json.JSONArray;
import org.json.JSONObject;

// Referenced classes of package jp.co.yahoo.android.apps.transit.api:
//            YolpLocalSearch

public class StationSearch extends YolpLocalSearch
{

    private int nMode;
    private Bundle stationList;

    public StationSearch(Context context, ApiBase.ApiListener apilistener)
    {
        super(context, apilistener);
        nMode = 1;
        setCid(getContext().getString(0x7f0d05a2));
        setDetail("full");
        setDistinct("true");
        setGroup("gid");
    }

    protected void analyzeFeature(JSONArray jsonarray)
    {
        stationList = new Bundle();
        if (jsonarray == null) goto _L2; else goto _L1
_L1:
        int i = 0;
_L15:
        if (i >= jsonarray.length()) goto _L2; else goto _L3
_L3:
        StationData stationdata = new StationData();
        if (nMode != 1) goto _L5; else goto _L4
_L4:
        stationdata.setType(1);
        stationdata.setnNaviType(1);
_L12:
        DiainfoData diainfodata;
        JSONObject jsonobject1;
        stationList.putSerializable(String.valueOf(i), stationdata);
        diainfodata = new DiainfoData();
        JSONObject jsonobject = jsonarray.optJSONObject(i);
        jsonobject1 = jsonobject.optJSONObject("Property");
        JSONObject jsonobject2 = jsonobject1.optJSONObject("Detail");
        stationdata.setId(jsonobject2.optString("StationId"));
        stationdata.setUid(jsonobject1.optString("Uid"));
        stationdata.setName(jsonobject.optString("Name"));
        stationdata.setAddress(jsonobject1.optString("Address"));
        stationdata.setGovernmentCode(jsonobject1.optString("GovernmentCode"));
        if (jsonobject2.has("RailSubName"))
        {
            diainfodata.setRailName(jsonobject2.optString("RailSubName"));
        }
        if (jsonobject2.has("CompanyName"))
        {
            diainfodata.setRailCompanyName(jsonobject2.optString("CompanyName"));
        }
        stationdata.setDiainfo(diainfodata);
        String as[] = jsonobject.optJSONObject("Geometry").optString("Coordinates").split(",");
        stationdata.setLon(as[0]);
        stationdata.setLat(as[1]);
        stationdata.setType(nMode);
        if (!jsonobject1.has("StationInfo")) goto _L7; else goto _L6
_L6:
        JSONObject jsonobject3;
        JSONObject jsonobject4;
        jsonobject3 = jsonobject1.optJSONObject("StationInfo");
        jsonobject4 = jsonobject3.optJSONObject("DiaInfo");
        if (jsonobject4 != null)
        {
            break MISSING_BLOCK_LABEL_291;
        }
        jsonobject4 = jsonobject3.optJSONArray("DiaInfo").optJSONObject(0);
        JSONArray jsonarray1 = jsonobject3.optJSONArray("RailGroup");
        if (jsonobject4 == null)
        {
            break MISSING_BLOCK_LABEL_365;
        }
        diainfodata.setRailName(jsonobject4.optString("RailName"));
        diainfodata.setRailcode(jsonobject4.optString("RailCode"));
        diainfodata.setCpId(jsonobject4.optString("ContentProviderId"));
        diainfodata.setRailAreaCode(jsonobject4.optString("RailAreaCode"));
        diainfodata.setRailRangeCode(jsonobject4.optString("RailRangeCode"));
        ArrayList arraylist = new ArrayList(jsonarray1.length());
        int j = 0;
_L13:
        if (j >= jsonarray1.length()) goto _L9; else goto _L8
_L8:
        JSONObject jsonobject5;
        RailDirectionData raildirectiondata;
        jsonobject5 = jsonarray1.optJSONObject(j);
        raildirectiondata = new RailDirectionData();
        if (!TransitUtil.isEmpty(jsonobject5.optString("Direction"))) goto _L11; else goto _L10
_L5:
        stationdata.setType(2);
        stationdata.setnNaviType(32);
          goto _L12
_L11:
        raildirectiondata.setDirection(jsonobject5.optString("Direction"));
        raildirectiondata.setGroupid(jsonobject5.optString("RailId"));
        raildirectiondata.setSource(jsonobject5.optString("Source"));
        raildirectiondata.setDrivedayKind(jsonobject5.optString("ServiceDayCode"));
        raildirectiondata.setRailTartget(jsonobject5.optInt("RailTarget", 0));
        arraylist.add(raildirectiondata);
          goto _L10
_L9:
        JSONArray jsonarray2 = jsonobject3.optJSONArray("Exit");
        if (jsonarray2 == null)
        {
            break MISSING_BLOCK_LABEL_556;
        }
        if (jsonarray2.length() > 0 && !TransitUtil.isEmpty(jsonarray2.optJSONObject(0).optString("Name")))
        {
            stationdata.setExit(true);
        }
        JSONArray jsonarray3 = jsonobject3.optJSONArray("Facility");
        if (jsonarray3 == null)
        {
            break MISSING_BLOCK_LABEL_600;
        }
        if (jsonarray3.length() > 0 && !TransitUtil.isEmpty(jsonarray3.optJSONObject(0).optString("Name")))
        {
            stationdata.setFacility(true);
        }
        stationdata.setRailDirection(arraylist);
          goto _L7
_L2:
        return;
        Exception exception1;
        exception1;
          goto _L7
_L10:
        j++;
          goto _L13
        Exception exception;
        exception;
_L7:
        i++;
        if (true) goto _L15; else goto _L14
_L14:
    }

    public int getMode()
    {
        return nMode;
    }

    public Bundle getResults()
    {
        return stationList;
    }

    public void setCPId(String s)
    {
        param.put("exact_custom3", s);
    }

    public void setExactName(String s)
    {
        if (TransitUtil.isEmpty(s))
        {
            break MISSING_BLOCK_LABEL_25;
        }
        param.put("exact_text2", URLEncoder.encode(s, "UTF-8"));
        return;
        UnsupportedEncodingException unsupportedencodingexception;
        unsupportedencodingexception;
        unsupportedencodingexception.printStackTrace();
        return;
    }

    public void setLocoMode(String s)
    {
        param.put("loco_mode", s);
    }

    public void setMode(int i)
    {
        nMode = i;
        if (i == 2)
        {
            setCid(getContext().getString(0x7f0d05a1));
            return;
        } else
        {
            setCid(getContext().getString(0x7f0d05a2));
            return;
        }
    }

    public void setRailId(String s)
    {
        param.put("exact_custom4", s);
    }

    public void setUMARailName(String s)
    {
        if (TransitUtil.isEmpty(s))
        {
            break MISSING_BLOCK_LABEL_25;
        }
        param.put("exact_text17", URLEncoder.encode(s, "UTF-8"));
        return;
        UnsupportedEncodingException unsupportedencodingexception;
        unsupportedencodingexception;
        unsupportedencodingexception.printStackTrace();
        return;
    }
}

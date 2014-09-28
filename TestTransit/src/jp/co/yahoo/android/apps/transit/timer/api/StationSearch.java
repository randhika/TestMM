// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.timer.api;

import android.content.Context;
import android.os.Bundle;
import java.util.ArrayList;
import java.util.HashMap;
import jp.co.yahoo.android.apps.transit.timer.api.data.RailDirectionData;
import jp.co.yahoo.android.apps.transit.timer.api.data.StationData;
import jp.co.yahoo.android.apps.transit.timer.util.CountdownUtil;
import jp.co.yahoo.android.yolp.common.LocalSearch;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class StationSearch extends LocalSearch
{

    private int nMode;

    public StationSearch(Context context)
    {
        super(context);
        nMode = 1;
        setCid(context.getString(0x7f0d05a2));
        setAppid(context.getString(0x7f0d003a));
        param.put("detail", "full");
        param.put("distinct", "true");
        param.put("group", "gid");
        param.put("loco_mode", "false");
    }

    protected void analyze()
    {
        Bundle bundle;
        JSONArray jsonarray;
        bundle = new Bundle();
        jsonarray = getFeature();
        if (jsonarray == null) goto _L2; else goto _L1
_L1:
        int i = 0;
_L9:
        if (i >= jsonarray.length()) goto _L4; else goto _L3
_L3:
        StationData stationdata;
        JSONObject jsonobject1;
        stationdata = new StationData();
        String s = String.valueOf(i);
        bundle.putSerializable(s, stationdata);
        JSONObject jsonobject = jsonarray.getJSONObject(i);
        jsonobject1 = jsonobject.getJSONObject("Property");
        JSONObject jsonobject2 = jsonobject1.getJSONObject("Detail");
        stationdata.setStationId(jsonobject2.optString("StationId"));
        stationdata.setName(jsonobject.getString("Name"));
        stationdata.setAddress(jsonobject1.getString("Address"));
        stationdata.setGovernmentCode(jsonobject1.getString("GovernmentCode"));
        if (jsonobject2.has("RailSubName"))
        {
            stationdata.setRailname(jsonobject2.getString("RailSubName"));
        }
        String as[] = jsonobject.getJSONObject("Geometry").getString("Coordinates").split(",");
        stationdata.setLon(as[0]);
        stationdata.setLat(as[1]);
        stationdata.setType(nMode);
        if (!jsonobject1.has("StationInfo")) goto _L6; else goto _L5
_L5:
        JSONArray jsonarray1;
        ArrayList arraylist;
        JSONObject jsonobject3 = jsonobject1.optJSONObject("StationInfo");
        if (jsonobject3.optJSONObject("DiaInfo") == null)
        {
            jsonobject3.optJSONArray("DiaInfo").optJSONObject(0);
        }
        jsonarray1 = jsonobject3.optJSONArray("RailGroup");
        arraylist = new ArrayList(jsonarray1.length());
        int j = 0;
_L10:
        if (j >= jsonarray1.length()) goto _L8; else goto _L7
_L7:
        JSONObject jsonobject4 = jsonarray1.optJSONObject(j);
        RailDirectionData raildirectiondata = new RailDirectionData();
        if (CountdownUtil.isEmpty(jsonobject4.optString("Direction")))
        {
            break MISSING_BLOCK_LABEL_404;
        }
        try
        {
            raildirectiondata.setDirection(jsonobject4.optString("Direction"));
            raildirectiondata.setGroupid(jsonobject4.optString("RailId"));
            raildirectiondata.setSource(jsonobject4.optString("Source"));
            raildirectiondata.setDrivedayKind(jsonobject4.optString("ServiceDayCode"));
            raildirectiondata.setRailName(jsonobject4.optString("RailName"));
            arraylist.add(raildirectiondata);
            break MISSING_BLOCK_LABEL_404;
        }
        catch (JSONException jsonexception)
        {
            jsonexception.printStackTrace();
        }
_L4:
        setResult(bundle);
        return;
_L8:
        stationdata.setRailDirection(arraylist);
_L6:
        i++;
          goto _L9
_L2:
        bundle = null;
          goto _L4
        j++;
          goto _L10
    }

    public void setCode(String s)
    {
        param.put("code", s);
    }

    public void setMode(int i)
    {
        nMode = i;
        if (i == 2)
        {
            setCid(context.getString(0x7f0d05a1));
            return;
        } else
        {
            setCid(context.getString(0x7f0d05a2));
            return;
        }
    }

    public void setQuerySearchMode()
    {
        param.put("vi", "4");
    }
}

// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.timer.api;

import android.content.Context;
import android.os.Bundle;
import java.util.ArrayList;
import java.util.HashMap;
import jp.co.yahoo.android.apps.transit.timer.api.data.RailData;
import jp.co.yahoo.android.apps.transit.timer.api.data.StationData;
import jp.co.yahoo.android.yolp.common.YolpApiBase;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DirectionSearch extends YolpApiBase
{

    public DirectionSearch(Context context)
    {
        super(context);
        setAppid(super.context.getString(0x7f0d003b));
        setUri(super.context.getString(0x7f0d003d));
    }

    protected void analyze()
    {
        JSONArray jsonarray;
        Bundle bundle;
        int i;
        jsonarray = getFeature();
        bundle = new Bundle();
        if (jsonarray == null)
        {
            break MISSING_BLOCK_LABEL_250;
        }
        i = 0;
_L3:
        RailData raildata;
        JSONArray jsonarray1;
        ArrayList arraylist;
        if (i >= jsonarray.length())
        {
            break MISSING_BLOCK_LABEL_250;
        }
        raildata = new RailData();
        StationData stationdata = new StationData();
        JSONObject jsonobject = jsonarray.getJSONObject(i);
        jsonarray1 = jsonobject.getJSONObject("RouteInfo").getJSONArray("RailGroup");
        raildata.setStation(stationdata);
        stationdata.setName(jsonobject.getString("Name"));
        stationdata.setStationId((String)param.get("code"));
        stationdata.setRailname(jsonarray1.getJSONObject(0).getString("RailName"));
        arraylist = new ArrayList();
        int j = 0;
_L2:
        if (j >= jsonarray1.length())
        {
            break; /* Loop/switch isn't completed */
        }
        jp.co.yahoo.android.apps.transit.timer.api.data.RailData.RailDirectionData raildirectiondata = new jp.co.yahoo.android.apps.transit.timer.api.data.RailData.RailDirectionData();
        JSONObject jsonobject1 = jsonarray1.getJSONObject(j);
        raildirectiondata.setDirection(jsonobject1.getString("Direction"));
        raildirectiondata.setGroupid(jsonobject1.getString("GroupId"));
        raildirectiondata.setSource(jsonobject1.getString("Source"));
        raildirectiondata.setDrivedayKind(jsonobject1.getString("DrivedayKind"));
        arraylist.add(raildirectiondata);
        j++;
        if (true) goto _L2; else goto _L1
_L1:
        raildata.setRailDirection(arraylist);
        bundle.putSerializable(String.valueOf(i), raildata);
        i++;
          goto _L3
        JSONException jsonexception;
        jsonexception;
        jsonexception.printStackTrace();
        setResult(bundle);
        return;
    }

    public void executeThread()
    {
    }

    public void setCode(String s)
    {
        param.put("code", s);
    }

    public void setDate(String s)
    {
        param.put("date", s);
    }

    public void setGroup(String s)
    {
        param.put("grouping", s);
    }
}

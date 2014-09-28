// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.api;

import android.content.Context;
import android.os.Bundle;
import java.util.ArrayList;
import java.util.HashMap;
import jp.co.yahoo.android.apps.transit.api.data.DiainfoData;
import jp.co.yahoo.android.apps.transit.api.data.RailDirectionData;
import jp.co.yahoo.android.apps.transit.api.data.StationData;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

// Referenced classes of package jp.co.yahoo.android.apps.transit.api:
//            NaviApiBase

public class DirectionSearch extends NaviApiBase
{

    private Bundle railList;

    public DirectionSearch(Context context, ApiBase.ApiListener apilistener)
    {
        super(context, apilistener);
        setMsg(context.getString(0x7f0d04a3));
        setMsgErr(context.getString(0x7f0d0106));
        setUri(getContext().getString(0x7f0d003d));
    }

    protected void analyzeFeature(JSONArray jsonarray)
    {
        int i;
        railList = new Bundle();
        if (jsonarray == null)
        {
            break MISSING_BLOCK_LABEL_243;
        }
        i = 0;
_L3:
        StationData stationdata;
        JSONArray jsonarray1;
        ArrayList arraylist;
        if (i >= jsonarray.length())
        {
            break MISSING_BLOCK_LABEL_243;
        }
        stationdata = new StationData();
        JSONObject jsonobject = jsonarray.getJSONObject(i);
        jsonarray1 = jsonobject.getJSONObject("RouteInfo").getJSONArray("RailGroup");
        stationdata.setName(jsonobject.getString("Name"));
        stationdata.setId(getParameter("code"));
        DiainfoData diainfodata = new DiainfoData();
        diainfodata.setRailName(jsonarray1.getJSONObject(0).getString("RailName"));
        stationdata.setDiainfo(diainfodata);
        arraylist = new ArrayList();
        int j = 0;
_L2:
        if (j >= jsonarray1.length())
        {
            break; /* Loop/switch isn't completed */
        }
        RailDirectionData raildirectiondata = new RailDirectionData();
        JSONObject jsonobject1 = jsonarray1.getJSONObject(j);
        raildirectiondata.setDirection(jsonobject1.getString("Direction"));
        raildirectiondata.setGroupid(jsonobject1.getString("GroupId"));
        raildirectiondata.setSource(jsonobject1.getString("Source"));
        raildirectiondata.setDrivedayKind(jsonobject1.getString("DrivedayKind"));
        arraylist.add(raildirectiondata);
        j++;
        if (true) goto _L2; else goto _L1
_L1:
        stationdata.setRailDirection(arraylist);
        railList.putSerializable(String.valueOf(i), stationdata);
        i++;
          goto _L3
        JSONException jsonexception;
        jsonexception;
        jsonexception.printStackTrace();
    }

    public Bundle getResults()
    {
        return railList;
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

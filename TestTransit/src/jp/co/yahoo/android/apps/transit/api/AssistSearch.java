// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.api;

import android.content.Context;
import com.android.volley.VolleyError;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import jp.co.yahoo.android.apps.transit.api.data.StationData;
import jp.co.yahoo.android.apps.transit.common.util.TransitUtil;
import org.json.JSONArray;
import org.json.JSONObject;

// Referenced classes of package jp.co.yahoo.android.apps.transit.api:
//            ApiBase

public class AssistSearch extends ApiBase
{

    private List suggestList;

    public AssistSearch(Context context)
    {
        super(context, null);
        suggestList = new ArrayList();
        param.put("src", "rosen");
        setUri(getContext().getString(0x7f0d0035));
        param.put("appid", getContext().getString(0x7f0d0036));
    }

    protected boolean analyze(JSONObject jsonobject)
    {
        boolean flag = true;
        if (jsonobject != null) goto _L2; else goto _L1
_L1:
        flag = false;
_L4:
        return flag;
_L2:
        JSONArray jsonarray;
        jsonarray = jsonobject.optJSONArray("Result");
        if (jsonarray == null && jsonobject.has("Result"))
        {
            jsonarray = new JSONArray();
            jsonarray.put(jsonobject.optJSONObject("Result"));
        }
        if (jsonarray == null || jsonarray.length() < flag) goto _L4; else goto _L3
_L3:
        int i = 0;
_L10:
        if (i >= jsonarray.length()) goto _L4; else goto _L5
_L5:
        JSONObject jsonobject1;
        StationData stationdata;
        String s;
        jsonobject1 = jsonarray.optJSONObject(i);
        stationdata = new StationData();
        stationdata.setName(jsonobject1.optString("Suggest"));
        stationdata.setKananame(jsonobject1.optString("Yomi"));
        stationdata.setLat(jsonobject1.optString("Lat"));
        stationdata.setLon(jsonobject1.optString("Lon"));
        stationdata.setAddress(jsonobject1.optString("Address"));
        s = jsonobject1.optString("Id");
        if (!TransitUtil.isEmpty(s) && !s.equals("st")) goto _L7; else goto _L6
_L6:
        stationdata.setId(jsonobject1.optString("Code"));
        stationdata.setnNaviType(1);
_L9:
        suggestList.add(stationdata);
        break MISSING_BLOCK_LABEL_263;
_L7:
        if (s.equals("bu"))
        {
            stationdata.setId(jsonobject1.optString("Code"));
            stationdata.setnNaviType(32);
            continue; /* Loop/switch isn't completed */
        }
        if (s.equals("la"))
        {
            stationdata.setnNaviType(128);
        }
        if (true) goto _L9; else goto _L8
_L8:
        i++;
          goto _L10
          goto _L4
        Exception exception;
        exception;
          goto _L8
    }

    protected void analyzeError(VolleyError volleyerror)
    {
    }

    public List getSuggest()
    {
        return suggestList;
    }

    public void setQ(String s)
    {
        param.put("query", s);
    }

    public void setResults(int i)
    {
        param.put("results", Integer.toString(i));
    }
}

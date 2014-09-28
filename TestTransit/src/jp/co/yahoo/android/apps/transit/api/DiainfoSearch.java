// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.api;

import android.content.Context;
import android.os.Bundle;
import com.android.volley.VolleyError;
import java.util.ArrayList;
import java.util.HashMap;
import jp.co.yahoo.android.apps.transit.api.data.APIError;
import jp.co.yahoo.android.apps.transit.api.data.DiainfoData;
import jp.co.yahoo.android.apps.transit.common.util.TransitUtil;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

// Referenced classes of package jp.co.yahoo.android.apps.transit.api:
//            NaviApiBase

public class DiainfoSearch extends NaviApiBase
{

    private String area;
    private String detail;
    private String diainfo;
    private boolean isDetailAnalyze;
    private String method;
    private Context objContext;
    private String rail;
    private String range;
    private Bundle results;
    private String type;

    public DiainfoSearch(Context context, ApiBase.ApiListener apilistener)
    {
        super(context, apilistener);
        isDetailAnalyze = false;
        objContext = context;
        setMsg(context.getString(0x7f0d04a2));
        setMsgErr(context.getString(0x7f0d0105));
        setUri(getContext().getString(0x7f0d003c));
    }

    protected void analyzeError(VolleyError volleyerror)
    {
        APIError apierror = new APIError();
        apierror.setMessage(sMsgErr);
        apierror.setCode("400");
        setError(apierror);
    }

    protected void analyzeFeature(JSONArray jsonarray)
    {
        if (jsonarray != null) goto _L2; else goto _L1
_L1:
        return;
_L2:
        String s;
        int i;
        results = new Bundle();
        s = getParameter(objContext.getString(0x7f0d01cb));
        i = 0;
_L18:
        if (i >= jsonarray.length())
        {
            continue; /* Loop/switch isn't completed */
        }
        DiainfoData diainfodata;
        diainfodata = new DiainfoData();
        if (TransitUtil.isEmpty(s) || !s.equals("check"))
        {
            break MISSING_BLOCK_LABEL_167;
        }
        JSONObject jsonobject2 = jsonarray.optJSONObject(i);
        diainfodata.setRailAreaCode(jsonobject2.optString("RailAreaCode"));
        diainfodata.setRailAreaName(jsonobject2.optString("RailAreaName"));
        diainfodata.setRailName(jsonobject2.optString("RailName"));
        diainfodata.setRailcode(jsonobject2.optString("RailCode"));
        if (jsonobject2.getJSONObject("Diainfo").optInt("ServiceCondition") != 1)
        {
            break MISSING_BLOCK_LABEL_158;
        }
        diainfodata.setCondition(true);
_L3:
        results.putSerializable(Integer.toString(i), diainfodata);
        break MISSING_BLOCK_LABEL_714;
        diainfodata.setCondition(false);
          goto _L3
        JSONObject jsonobject;
        String s1;
        jsonobject = jsonarray.getJSONObject(i).getJSONObject("RouteInfo").getJSONObject("Property");
        s1 = jsonobject.getString("RailwayTypeCode");
        if (results.containsKey(s1)) goto _L5; else goto _L4
_L4:
        Bundle bundle = new Bundle();
        Bundle bundle1;
        String s2;
        String s3;
        ArrayList arraylist;
        JSONArray jsonarray1;
        int j;
        JSONObject jsonobject1;
        jp.co.yahoo.android.apps.transit.api.data.DiainfoData.DiainfoDataDetail diainfodatadetail;
        Bundle bundle2;
        Bundle bundle3;
        String s4;
        Bundle bundle4;
        Bundle bundle5;
        String s5;
        try
        {
            results.putBundle(s1, bundle);
        }
        catch (JSONException jsonexception1)
        {
            break MISSING_BLOCK_LABEL_714;
        }
        bundle1 = bundle;
        s2 = jsonobject.getString("RailAreaCode");
        if (!s1.equals(objContext.getString(0x7f0d0573))) goto _L7; else goto _L6
_L6:
        if (bundle1.containsKey(s2)) goto _L9; else goto _L8
_L8:
        bundle2 = new Bundle();
        try
        {
            bundle1.putBundle(s2, bundle2);
        }
        catch (JSONException jsonexception2)
        {
            break MISSING_BLOCK_LABEL_714;
        }
        bundle3 = bundle2;
        s4 = jsonobject.getString("CompanyCode");
        if (bundle3.containsKey(s4)) goto _L11; else goto _L10
_L10:
        bundle4 = new Bundle();
        bundle3.putBundle(s4, bundle4);
        bundle5 = bundle4;
_L14:
        s5 = String.valueOf(bundle5.size());
        bundle5.putSerializable(s5, diainfodata);
_L15:
        diainfodata.setRailAreaCode(s2);
        diainfodata.setRailTypeCode(s1);
        diainfodata.setRailAreaName(jsonobject.getString("RailAreaName"));
        diainfodata.setRailTypeName(jsonobject.getString("RailwayTypeName"));
        diainfodata.setRailcode(jsonobject.getString("RailCode"));
        diainfodata.setRailName(jsonobject.getString("DisplayRailName"));
        diainfodata.setRailCompanyCode(jsonobject.getString("CompanyCode"));
        diainfodata.setRailCompanyName(jsonobject.getString("CompanyName"));
        diainfodata.setCpId(jsonobject.getString("ContentProviderId"));
        diainfodata.setRailRangeCode(jsonobject.getString("RailRangeCode"));
        if (jsonobject.isNull("Diainfo") || TransitUtil.isEmpty(jsonobject.getString("Diainfo")))
        {
            break MISSING_BLOCK_LABEL_690;
        }
        if (!isDetailAnalyze)
        {
            break MISSING_BLOCK_LABEL_665;
        }
        arraylist = new ArrayList();
        jsonarray1 = jsonobject.getJSONArray("Diainfo");
        j = 0;
_L13:
        if (j >= jsonarray1.length())
        {
            break; /* Loop/switch isn't completed */
        }
        jsonobject1 = jsonarray1.getJSONObject(j);
        diainfodatadetail = new jp.co.yahoo.android.apps.transit.api.data.DiainfoData.DiainfoDataDetail();
        arraylist.add(diainfodatadetail);
        diainfodatadetail.setStatus(jsonobject1.getString("Status"));
        diainfodatadetail.setStatusCode(jsonobject1.getString("StatusCode"));
        diainfodatadetail.setMessage(jsonobject1.getString("Message"));
        diainfodatadetail.setUpdateDate(jsonobject1.getString("UpdateDate"));
        j++;
        if (true) goto _L13; else goto _L12
_L5:
        bundle1 = results.getBundle(s1);
        break MISSING_BLOCK_LABEL_229;
_L9:
        bundle3 = bundle1.getBundle(s2);
        break MISSING_BLOCK_LABEL_287;
_L11:
        bundle5 = bundle3.getBundle(s4);
          goto _L14
_L7:
        s3 = String.valueOf(bundle1.size());
        bundle1.putSerializable(s3, diainfodata);
          goto _L15
_L12:
        diainfodata.setDetailinfo(arraylist);
        diainfodata.setDetail(true);
_L16:
        JSONException jsonexception3;
        if (i == 0)
        {
            try
            {
                results.putSerializable("0", diainfodata);
            }
            catch (JSONException jsonexception) { }
        }
        break MISSING_BLOCK_LABEL_714;
        diainfodata.setDetail(false);
          goto _L16
        jsonexception3;
        i++;
        if (true) goto _L18; else goto _L17
_L17:
        if (true) goto _L1; else goto _L19
_L19:
    }

    public Bundle getResult()
    {
        return results;
    }

    public volatile Object getResult()
    {
        return getResult();
    }

    public boolean isDetailAnalyze()
    {
        return isDetailAnalyze;
    }

    public void setArea(String s)
    {
        param.put(objContext.getString(0x7f0d01d6), s);
    }

    public void setDetail(String s)
    {
        param.put(objContext.getString(0x7f0d01a9), s);
    }

    public void setDetailAnalyze(boolean flag)
    {
        isDetailAnalyze = flag;
    }

    public void setDiainfo(String s)
    {
        param.put(objContext.getString(0x7f0d01ad), s);
    }

    public void setMethod(String s)
    {
        param.put(objContext.getString(0x7f0d01cb), s);
    }

    public void setRail(String s)
    {
        param.put(objContext.getString(0x7f0d01d9), s);
    }

    public void setRange(String s)
    {
        param.put(objContext.getString(0x7f0d01dc), s);
    }

    public void setType(String s)
    {
        param.put(objContext.getString(0x7f0d01da), s);
    }
}

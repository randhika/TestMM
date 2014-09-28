// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.api;

import android.content.Context;
import com.android.volley.NetworkResponse;
import com.android.volley.VolleyError;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import jp.co.yahoo.android.apps.transit.api.data.APIError;
import jp.co.yahoo.android.apps.transit.common.util.TransitUtil;
import org.json.JSONArray;
import org.json.JSONObject;

// Referenced classes of package jp.co.yahoo.android.apps.transit.api:
//            ApiBase

public class YolpApiBase extends ApiBase
{

    private int count;
    private JSONObject dictionary;
    private JSONArray feature;
    private Object objResult;
    private JSONObject resultInfo;
    private int total;

    public YolpApiBase(Context context, ApiBase.ApiListener apilistener)
    {
        super(context, apilistener);
        count = 0;
        total = 0;
        param.put("output", "json");
        sMsgErr = context.getString(0x7f0d0102);
        sMsg = context.getString(0x7f0d04a1);
    }

    protected boolean analyze(JSONObject jsonobject)
    {
        return analyzeYDF(jsonobject);
    }

    protected void analyzeDictionaly(JSONObject jsonobject)
    {
    }

    protected void analyzeError(VolleyError volleyerror)
    {
        APIError apierror = new APIError();
        if (volleyerror != null)
        {
            if (volleyerror.networkResponse != null)
            {
                apierror.setCode(Integer.toString(volleyerror.networkResponse.statusCode));
            }
            apierror.setApiMessage(volleyerror.getMessage());
        }
        apierror.setMessage(getContext().getString(0x7f0d0102));
        setError(apierror);
    }

    protected void analyzeFeature(JSONArray jsonarray)
    {
    }

    protected boolean analyzeYDF(JSONObject jsonobject)
    {
        if (jsonobject == null)
        {
            APIError apierror = new APIError();
            apierror.setCode("500");
            apierror.setMessage(getContext().getString(0x7f0d0102));
            setError(apierror);
            return false;
        }
        resultInfo = jsonobject.optJSONObject("ResultInfo");
        if (resultInfo == null)
        {
            analyzeYolpError(jsonobject);
            return false;
        }
        setCount(resultInfo.optInt("Count"));
        setTotal(resultInfo.optInt("Total"));
        feature = jsonobject.optJSONArray("Feature");
        if (feature == null && jsonobject.has("Feature"))
        {
            feature = new JSONArray();
            feature.put(jsonobject.optJSONObject("Feature"));
        }
        if (feature == null)
        {
            return true;
        } else
        {
            dictionary = jsonobject.optJSONObject("Dictionary");
            analyzeDictionaly(dictionary);
            analyzeFeature(feature);
            return true;
        }
    }

    protected void analyzeYolpError(JSONObject jsonobject)
    {
        if (jsonobject != null && !jsonobject.isNull("Code"))
        {
            APIError apierror = new APIError();
            apierror.setCode(jsonobject.optString("Code"));
            apierror.setApiMessage(jsonobject.optString("Message"));
            apierror.setMessage(sMsgErr);
            setError(apierror);
        }
    }

    public int getCount()
    {
        return count;
    }

    public JSONObject getDictionary()
    {
        return dictionary;
    }

    public JSONArray getFeature()
    {
        return feature;
    }

    public Object getResult()
    {
        return objResult;
    }

    public JSONObject getResultInfo()
    {
        return resultInfo;
    }

    public int getTotal()
    {
        return total;
    }

    public void setAppid(String s)
    {
        param.put("appid", s);
    }

    public void setCount(int i)
    {
        count = i;
    }

    public void setDatum(String s)
    {
        param.put("datum", s);
    }

    public void setDictionary(JSONObject jsonobject)
    {
        dictionary = jsonobject;
    }

    public void setFeature(JSONArray jsonarray)
    {
        feature = jsonarray;
    }

    public void setLat(String s)
    {
        param.put("lat", s);
    }

    public void setLon(String s)
    {
        param.put("lon", s);
    }

    public void setQuery(String s)
    {
        if (TransitUtil.isEmpty(s))
        {
            break MISSING_BLOCK_LABEL_23;
        }
        param.put("query", URLEncoder.encode(s, "UTF-8"));
        return;
        UnsupportedEncodingException unsupportedencodingexception;
        unsupportedencodingexception;
        unsupportedencodingexception.printStackTrace();
        return;
    }

    public void setResult(Object obj)
    {
        objResult = obj;
    }

    public void setResultCount(int i)
    {
        param.put("results", Integer.toString(i));
    }

    public void setResultInfo(JSONObject jsonobject)
    {
        resultInfo = jsonobject;
    }

    public void setStart(int i)
    {
        param.put("start", Integer.toString(i));
    }

    public void setTotal(int i)
    {
        total = i;
    }
}

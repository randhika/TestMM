// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.yolp.common;

import android.content.Context;
import java.util.HashMap;
import org.json.JSONArray;
import org.json.JSONObject;

// Referenced classes of package jp.co.yahoo.android.yolp.common:
//            ApiBase, YolpError

public class YolpApiBase extends ApiBase
{

    protected JSONObject dictionary;
    private YolpError error;
    protected JSONArray feature;
    private JSONObject resultInfo;

    public YolpApiBase(Context context)
    {
        super(context);
    }

    protected void analyze()
    {
    }

    protected void analyzeError(JSONObject jsonobject)
    {
        if (jsonobject != null && !jsonobject.isNull("Code"))
        {
            YolpError yolperror = new YolpError();
            yolperror.setCode(jsonobject.optString("Code"));
            yolperror.setMessage(jsonobject.optString("Message"));
            setError(yolperror);
        }
    }

    protected void analyzeYDF(String s)
    {
        JSONObject jsonobject;
        try
        {
            jsonobject = new JSONObject(s);
        }
        catch (Exception exception)
        {
            YolpError yolperror = new YolpError();
            yolperror.setCode("500");
            yolperror.setMessage("\u4E88\u671F\u305B\u306C\u30A8\u30E9\u30FC\u3067\u3059");
            setError(yolperror);
            return;
        }
        resultInfo = jsonobject.optJSONObject("ResultInfo");
        if (resultInfo == null)
        {
            analyzeError(jsonobject);
            return;
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
            return;
        } else
        {
            dictionary = jsonobject.optJSONObject("Dictionary");
            return;
        }
    }

    public JSONObject getDictionary()
    {
        return dictionary;
    }

    public YolpError getError()
    {
        return error;
    }

    public JSONArray getFeature()
    {
        return feature;
    }

    public JSONObject getResultInfo()
    {
        return resultInfo;
    }

    public String request()
    {
        String s = super.request();
        analyzeYDF(s);
        analyze();
        return s;
    }

    public void setAppid(String s)
    {
        param.put("appid", s);
    }

    public void setDictionary(JSONObject jsonobject)
    {
        dictionary = jsonobject;
    }

    public void setError(YolpError yolperror)
    {
        error = yolperror;
    }

    public void setFeature(JSONArray jsonarray)
    {
        feature = jsonarray;
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
}

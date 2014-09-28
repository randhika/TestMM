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
import org.json.JSONObject;

// Referenced classes of package jp.co.yahoo.android.apps.transit.api:
//            ApiBase

public class YjpnShortUrl extends ApiBase
{

    private String longUrl;
    private String shortUrl;

    public YjpnShortUrl(Context context, ApiBase.ApiListener apilistener)
    {
        super(context, apilistener);
        setUri(getContext().getString(0x7f0d004d));
        param.put("appid", getContext().getString(0x7f0d004e));
        param.put("output", "json");
        sMsgErr = context.getString(0x7f0d0109);
        sMsg = context.getString(0x7f0d04a1);
    }

    private void analyzeError(JSONObject jsonobject)
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

    private boolean analyzeResponse(JSONObject jsonobject)
    {
        if (jsonobject == null || jsonobject.optJSONObject("ResultSet") == null)
        {
            APIError apierror = new APIError();
            apierror.setCode("500");
            apierror.setMessage(sMsgErr);
            setError(apierror);
            return false;
        }
        JSONObject jsonobject1 = jsonobject.optJSONObject("ResultSet").optJSONObject("Result");
        if (jsonobject1 == null)
        {
            analyzeError(jsonobject);
            return false;
        } else
        {
            shortUrl = jsonobject1.optString("ShortUrl");
            longUrl = jsonobject1.optString("LongUrl");
            return true;
        }
    }

    protected boolean analyze(JSONObject jsonobject)
    {
        return analyzeResponse(jsonobject);
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
        apierror.setMessage(sMsgErr);
        setError(apierror);
    }

    public String getLongUrl()
    {
        return longUrl;
    }

    public String getShortUrl()
    {
        return shortUrl;
    }

    public void setUrl(String s)
    {
        if (TransitUtil.isEmpty(s))
        {
            break MISSING_BLOCK_LABEL_23;
        }
        param.put("url", URLEncoder.encode(s, "UTF-8"));
        return;
        UnsupportedEncodingException unsupportedencodingexception;
        unsupportedencodingexception;
    }
}

// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.yconnect.core.oidc;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import jp.co.yahoo.yconnect.core.http.HttpHeaders;
import jp.co.yahoo.yconnect.core.http.HttpParameters;
import jp.co.yahoo.yconnect.core.http.YHttpClient;
import jp.co.yahoo.yconnect.core.util.YConnectLogger;
import org.json.JSONException;
import org.json.JSONObject;

// Referenced classes of package jp.co.yahoo.yconnect.core.oidc:
//            AbstractCommonClient, CheckIdException, IdTokenObject

public class CheckIdClient extends AbstractCommonClient
{

    private static final String TAG = jp/co/yahoo/yconnect/core/oidc/CheckIdClient.getSimpleName();
    private YHttpClient client;
    private IdTokenObject idTokenObject;
    private String idTokenString;
    private long responseTime;

    public CheckIdClient(String s, String s1)
    {
        super(s);
        responseTime = 0L;
        idTokenString = s1;
    }

    protected void checkErrorResponse(int i, Map map)
        throws CheckIdException
    {
        String s = (String)map.get("WWW-Authenticate");
        if (i != 200)
        {
            if (s != null)
            {
                YConnectLogger.debug(TAG, s);
                HashMap hashmap = extractWWWAuthHeader(s);
                YConnectLogger.debug(TAG, hashmap.toString());
                String s1 = (String)hashmap.get("error");
                String s2 = (String)hashmap.get("error_description");
                throw new CheckIdException(s1, (new StringBuilder()).append(s2).append(" [be thrown by ").append(TAG).append("]").toString());
            } else
            {
                throw new CheckIdException("Failed Request.", "");
            }
        } else
        {
            return;
        }
    }

    public void fetch()
        throws CheckIdException, JSONException
    {
        HttpParameters httpparameters = new HttpParameters();
        httpparameters.put("id_token", idTokenString);
        client = new YHttpClient();
        client.requestGet(endpointUrl, httpparameters, null);
        YConnectLogger.debug(TAG, client.getResponseHeaders().toString());
        YConnectLogger.debug(TAG, client.getResponseBody().toString());
        int i = client.getStatusCode();
        HttpHeaders httpheaders = client.getResponseHeaders();
        checkErrorResponse(i, httpheaders);
        if (httpheaders.get("Date") != null)
        {
            Date date = new Date((String)httpheaders.get("Date"));
            responseTime = date.getTime() / 1000L;
            YConnectLogger.debug(TAG, (new StringBuilder()).append("Response Date: ").append(responseTime).toString());
        }
        JSONObject jsonobject = new JSONObject(client.getResponseBody());
        idTokenObject = new IdTokenObject(jsonobject.optString("iss"), jsonobject.optString("user_id"), jsonobject.optString("aud"), jsonobject.optString("nonce"), jsonobject.optLong("exp"), jsonobject.optLong("iat"));
    }

    public IdTokenObject getIdTokenObject()
    {
        return idTokenObject;
    }

    public long getResposeTime()
    {
        return responseTime;
    }

}

// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.yconnect.core.oauth2;

import jp.co.yahoo.yconnect.core.http.HttpHeaders;
import jp.co.yahoo.yconnect.core.http.HttpParameters;
import jp.co.yahoo.yconnect.core.http.YHttpClient;
import jp.co.yahoo.yconnect.core.util.YConnectLogger;
import org.json.JSONObject;

// Referenced classes of package jp.co.yahoo.yconnect.core.oauth2:
//            AbstractTokenClient, TokenException, BearerToken

public class RefreshTokenClient extends AbstractTokenClient
{

    private static final String TAG = jp/co/yahoo/yconnect/core/oauth2/RefreshTokenClient.getSimpleName();
    private BearerToken accessToken;
    private YHttpClient client;
    private String refreshToken;
    private String responseTime;

    public RefreshTokenClient(String s, String s1, String s2)
    {
        super(s, s2);
        refreshToken = s1;
    }

    public void fetch()
        throws TokenException, Exception
    {
        HttpParameters httpparameters = new HttpParameters();
        httpparameters.put("grant_type", "refresh_token");
        httpparameters.put("refresh_token", refreshToken);
        httpparameters.put("client_id", clientId);
        HttpHeaders httpheaders = new HttpHeaders();
        httpheaders.put("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
        client = new YHttpClient();
        client.requestPost(endpointUrl, httpparameters, httpheaders);
        responseTime = (String)client.getResponseHeaders().get("Date");
        YConnectLogger.debug(TAG, client.getResponseHeaders().toString());
        YConnectLogger.debug(TAG, client.getResponseBody().toString());
        JSONObject jsonobject = new JSONObject(client.getResponseBody());
        checkErrorResponse(client.getStatusCode(), jsonobject);
        accessToken = new BearerToken(jsonobject.optString("access_token"), Long.parseLong(jsonobject.optString("expires_in")), jsonobject.optString("refresh_token"));
    }

    public BearerToken getAccessToken()
    {
        return accessToken;
    }

    public String getResponseTime()
    {
        return responseTime;
    }

}

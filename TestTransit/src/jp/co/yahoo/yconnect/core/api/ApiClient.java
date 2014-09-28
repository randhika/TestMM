// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.yconnect.core.api;

import java.util.HashMap;
import jp.co.yahoo.yconnect.core.http.HttpHeaders;
import jp.co.yahoo.yconnect.core.http.HttpParameters;
import jp.co.yahoo.yconnect.core.http.YHttpClient;
import jp.co.yahoo.yconnect.core.oauth2.BearerToken;
import jp.co.yahoo.yconnect.core.util.YConnectLogger;

// Referenced classes of package jp.co.yahoo.yconnect.core.api:
//            ApiClientException

public class ApiClient
{

    public static final String DELETE_METHOD = "DELETE";
    public static final String GET_METHOD = "GET";
    public static final String POST_METHOD = "POST";
    public static final String PUT_METHOD = "PUT";
    private static final String TAG = jp/co/yahoo/yconnect/core/api/ApiClient.getSimpleName();
    private String charset;
    private String contentType;
    private HttpParameters parameters;
    private String postBody;
    private HttpHeaders requestHeaders;
    private String responseBody;
    private int responseCode;
    private HttpHeaders responseHeaders;
    private String responseMessage;

    public ApiClient()
    {
        postBody = null;
        contentType = "application/x-www-form-urlencoded";
        charset = "ISO-8859-1";
        parameters = new HttpParameters();
        requestHeaders = new HttpHeaders();
    }

    public ApiClient(String s)
    {
        postBody = null;
        contentType = "application/x-www-form-urlencoded";
        charset = "ISO-8859-1";
        parameters = new HttpParameters();
        requestHeaders = new HttpHeaders();
        setHeader("Authorization", (new StringBuilder()).append("Bearer ").append(s).toString());
    }

    public ApiClient(BearerToken bearertoken)
    {
        postBody = null;
        contentType = "application/x-www-form-urlencoded";
        charset = "ISO-8859-1";
        parameters = new HttpParameters();
        requestHeaders = new HttpHeaders();
        setHeader("Authorization", (new StringBuilder()).append("Bearer ").append(bearertoken.toAuthorizationHeader()).toString());
    }

    private void checkErrorResponse()
        throws ApiClientException
    {
        String s = (String)responseHeaders.get("WWW-Authenticate");
        if (responseCode != 200)
        {
            if (s != null)
            {
                YConnectLogger.debug(TAG, s);
                HashMap hashmap = extractWWWAuthHeader(s);
                YConnectLogger.debug(TAG, hashmap.toString());
                String s1 = (String)hashmap.get("error");
                String s2 = (String)hashmap.get("error_description");
                throw new ApiClientException(s1, (new StringBuilder()).append(s2).append(" [be thrown by ").append(TAG).append("]").toString());
            } else
            {
                throw new ApiClientException((new StringBuilder()).append("Failed Request.(status code: ").append(responseCode).append(" status message: ").append(responseMessage).append(")").toString(), responseHeaders.toString());
            }
        } else
        {
            return;
        }
    }

    private static HashMap extractWWWAuthHeader(String s)
    {
        HashMap hashmap = new HashMap();
        String as[] = s.split(",");
        int i = as.length;
        for (int j = 0; j < i; j++)
        {
            String as1[] = as[j].replaceAll("\"", "").split("=");
            hashmap.put(as1[0].trim(), as1[1].trim());
        }

        return hashmap;
    }

    public void fetchResouce(String s, String s1)
        throws ApiClientException
    {
        YConnectLogger.debug(TAG, (new StringBuilder()).append("request parameters: ").append(parameters.toQueryString()).toString());
        YConnectLogger.debug(TAG, (new StringBuilder()).append("request headers: ").append(requestHeaders.toHeaderString()).toString());
        YHttpClient yhttpclient;
        if ("POST".equalsIgnoreCase(s1) && postBody != null)
        {
            yhttpclient = new YHttpClient();
            yhttpclient.requestPost(s, postBody, requestHeaders, contentType, charset);
        } else
        if ("POST".equalsIgnoreCase(s1))
        {
            yhttpclient = new YHttpClient();
            yhttpclient.requestPost(s, parameters, requestHeaders);
        } else
        if ("GET".equalsIgnoreCase(s1))
        {
            yhttpclient = new YHttpClient();
            yhttpclient.requestGet(s, parameters, requestHeaders);
        } else
        if ("PUT".equalsIgnoreCase(s1) && postBody != null)
        {
            yhttpclient = new YHttpClient();
            yhttpclient.requestPost(s, postBody, requestHeaders, contentType, charset);
        } else
        if ("PUT".equalsIgnoreCase(s1))
        {
            yhttpclient = new YHttpClient();
            yhttpclient.requestPut(s, parameters, requestHeaders);
        } else
        if ("DELETE".equalsIgnoreCase(s1) && postBody != null)
        {
            yhttpclient = new YHttpClient();
            yhttpclient.requestDelete(s, postBody, requestHeaders, contentType, charset);
        } else
        if ("DELETE".equalsIgnoreCase(s1))
        {
            yhttpclient = new YHttpClient();
            yhttpclient.requestDelete(s, parameters, requestHeaders);
        } else
        {
            throw new ApiClientException("Undefined Http method.", (new StringBuilder()).append(s1).append(" [be thrown by ").append(TAG).append("]").toString());
        }
        responseCode = yhttpclient.getStatusCode();
        responseMessage = yhttpclient.getStatusMessage();
        responseHeaders = yhttpclient.getResponseHeaders();
        responseBody = yhttpclient.getResponseBody();
        checkErrorResponse();
    }

    public HttpHeaders getHeaders()
    {
        return responseHeaders;
    }

    public String getResponse()
    {
        return responseBody;
    }

    public int getStatusCode()
    {
        return responseCode;
    }

    public String getStatusMessage()
    {
        return responseMessage;
    }

    public void setAccessToken(BearerToken bearertoken)
    {
        setParameter("access_token", bearertoken.getAccessToken());
    }

    public void setCharset(String s)
    {
        charset = s;
    }

    public void setContentType(String s)
    {
        contentType = s;
    }

    public void setHeader(String s, String s1)
    {
        String s2 = s.replace(":", "").trim();
        requestHeaders.put(s2, s1);
    }

    public void setParameter(String s, String s1)
    {
        parameters.put(s, s1);
    }

    public void setPostBody(String s)
    {
        postBody = s;
    }

}

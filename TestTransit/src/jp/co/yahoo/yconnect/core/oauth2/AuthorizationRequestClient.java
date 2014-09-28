// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.yconnect.core.oauth2;

import android.net.Uri;
import jp.co.yahoo.yconnect.core.util.YConnectLogger;

public class AuthorizationRequestClient
{

    private static final String TAG = jp/co/yahoo/yconnect/core/oauth2/AuthorizationRequestClient.getSimpleName();
    private String clientId;
    private String endpointUri;
    private String redirectUri;
    private android.net.Uri.Builder requestUri;
    private String responseType;
    private String state;

    public AuthorizationRequestClient(String s, String s1)
    {
        endpointUri = s;
        clientId = s1;
        requestUri = new android.net.Uri.Builder();
    }

    public Uri generateAuthorizationUri()
    {
        Uri uri = Uri.parse(endpointUri);
        requestUri.scheme(uri.getScheme());
        requestUri.authority(uri.getAuthority());
        requestUri.path(uri.getPath());
        requestUri.appendQueryParameter("client_id", clientId);
        requestUri.appendQueryParameter("response_type", responseType);
        requestUri.appendQueryParameter("state", state);
        requestUri.appendQueryParameter("redirect_uri", redirectUri);
        YConnectLogger.info(TAG, requestUri.build().toString());
        return requestUri.build();
    }

    public void setParameter(String s, String s1)
    {
        if (requestUri.build().getQueryParameter(s) == null)
        {
            requestUri.appendQueryParameter(s, s1);
        }
    }

    public void setRedirectUri(String s)
    {
        redirectUri = s;
    }

    public void setResponseType(String s)
    {
        responseType = s;
    }

    public void setState(String s)
    {
        state = s;
    }

}

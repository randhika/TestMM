// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.yconnect;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import jp.co.yahoo.yconnect.core.api.ApiClientException;
import jp.co.yahoo.yconnect.core.http.YHttpClient;
import jp.co.yahoo.yconnect.core.oauth2.AuthorizationException;
import jp.co.yahoo.yconnect.core.oauth2.AuthorizationRequestClient;
import jp.co.yahoo.yconnect.core.oauth2.BearerToken;
import jp.co.yahoo.yconnect.core.oauth2.ExplicitCallbackUriParser;
import jp.co.yahoo.yconnect.core.oauth2.RefreshTokenClient;
import jp.co.yahoo.yconnect.core.oauth2.TokenClient;
import jp.co.yahoo.yconnect.core.oauth2.TokenException;
import jp.co.yahoo.yconnect.core.oidc.CheckIdClient;
import jp.co.yahoo.yconnect.core.oidc.CheckIdException;
import jp.co.yahoo.yconnect.core.oidc.IdTokenObject;
import jp.co.yahoo.yconnect.core.oidc.IdTokenVerification;
import jp.co.yahoo.yconnect.core.oidc.UserInfoClient;
import jp.co.yahoo.yconnect.core.oidc.UserInfoObject;
import jp.co.yahoo.yconnect.core.util.StringUtil;

public class YConnectExplicit
{

    private static final String AUTHORIZATION_ENDPOINT_URL = "https://auth.login.yahoo.co.jp/yconnect/v1/authorization";
    private static final String CHECK_ID_ENDPOINT_URL = "https://auth.login.yahoo.co.jp/yconnect/v1/checktoken";
    private static final String ISSUER = "https://auth.login.yahoo.co.jp";
    private static final String TOKEN_ENDPOINT_URL = "https://auth.login.yahoo.co.jp/yconnect/v1/token";
    private static final String USERINFO_ENDPOINT_URL = "https://userinfo.yahooapis.jp/yconnect/v1/attribute";
    private static YConnectExplicit instance;
    protected BearerToken accessToken;
    private String code;
    protected String display;
    private String idToken;
    private IdTokenObject idTokenObject;
    protected String nonce;
    protected String prompt;
    protected AuthorizationRequestClient requestClient;
    private ExplicitCallbackUriParser responsePaser;
    private String responseTime;
    private String responseType;
    protected String scope;
    private UserInfoObject userInfoObject;

    protected YConnectExplicit()
    {
        responseType = "code id_token";
        display = "touch";
        prompt = "login";
        scope = null;
        nonce = null;
    }

    public static void disableSSLCheck()
    {
        YHttpClient.disableSSLCheck();
    }

    public static void enableSSLCheck()
    {
        YHttpClient.enableSSLCheck();
    }

    public static YConnectExplicit getInstance()
    {
        if (instance == null)
        {
            instance = new YConnectExplicit();
        }
        return instance;
    }

    public Uri generateAuthorizationUri()
    {
        requestClient.setResponseType(responseType);
        requestClient.setParameter("display", display);
        requestClient.setParameter("prompt", prompt);
        if (scope != null)
        {
            requestClient.setParameter("scope", scope);
        }
        if (nonce != null)
        {
            requestClient.setParameter("nonce", nonce);
        }
        return requestClient.generateAuthorizationUri();
    }

    public String getAccessToken()
    {
        return accessToken.getAccessToken();
    }

    public long getAccessTokenExpiration()
    {
        return accessToken.getExpiration();
    }

    public String getAuthorizationCode()
    {
        return code;
    }

    public String getDisplay()
    {
        return display;
    }

    public String getIdToken()
    {
        return idToken;
    }

    public IdTokenObject getIdTokenObject()
    {
        return idTokenObject;
    }

    public String getRefreshToken()
    {
        return accessToken.getRefreshToken();
    }

    public String getResponseTime()
    {
        return responseTime;
    }

    public UserInfoObject getUserInfoObject()
    {
        return userInfoObject;
    }

    public void init(String s, String s1, String s2)
    {
        requestClient = new AuthorizationRequestClient("https://auth.login.yahoo.co.jp/yconnect/v1/authorization", s);
        requestClient.setRedirectUri(s1);
        requestClient.setState(s2);
    }

    public void init(String s, String s1, String s2, String s3, String as[], String as1[], String s4)
    {
        requestClient = new AuthorizationRequestClient("https://auth.login.yahoo.co.jp/yconnect/v1/authorization", s);
        requestClient.setRedirectUri(s1);
        requestClient.setState(s2);
        display = s3;
        prompt = StringUtil.implode(as);
        scope = StringUtil.implode(as1);
        nonce = s4;
    }

    public void parseAuthorizationResponse(Uri uri, String s, String s1)
        throws AuthorizationException
    {
        responsePaser = new ExplicitCallbackUriParser(uri, s);
        code = responsePaser.getAuthorizationCode(s1);
    }

    public void refreshToken(String s, String s1)
        throws TokenException, Exception
    {
        RefreshTokenClient refreshtokenclient = new RefreshTokenClient("https://auth.login.yahoo.co.jp/yconnect/v1/token", s, s1);
        refreshtokenclient.fetch();
        accessToken = refreshtokenclient.getAccessToken();
        responseTime = refreshtokenclient.getResponseTime();
    }

    public void requestAuthorization(Activity activity)
    {
        activity.startActivity(new Intent("android.intent.action.VIEW", generateAuthorizationUri()));
        activity.finish();
    }

    public boolean requestCheckToken(String s, String s1, String s2)
        throws CheckIdException, Exception
    {
        CheckIdClient checkidclient = new CheckIdClient("https://auth.login.yahoo.co.jp/yconnect/v1/checktoken", s);
        checkidclient.fetch();
        idTokenObject = checkidclient.getIdTokenObject();
        long l = checkidclient.getResposeTime();
        return IdTokenVerification.check("https://auth.login.yahoo.co.jp", s1, s2, idTokenObject, l);
    }

    public void requestToken(String s, String s1, String s2)
        throws TokenException, Exception
    {
        TokenClient tokenclient = new TokenClient("https://auth.login.yahoo.co.jp/yconnect/v1/token", s, s1, s2);
        tokenclient.fetch();
        accessToken = tokenclient.getAccessToken();
        idToken = tokenclient.getIdToken();
    }

    public void requestUserInfo(String s)
        throws ApiClientException, Exception
    {
        UserInfoClient userinfoclient = new UserInfoClient(s);
        userinfoclient.fetchResouce("https://userinfo.yahooapis.jp/yconnect/v1/attribute", "GET");
        userInfoObject = userinfoclient.getUserInfoObject();
    }

    public void setDisplay(String s)
    {
        display = s;
    }

    public void setNonce(String s)
    {
        nonce = s;
    }

    public void setPrompt(String as[])
    {
        prompt = StringUtil.implode(as);
    }

    public void setResponseType(String s)
    {
        responseType = s;
    }

    public void setScope(String as[])
    {
        scope = StringUtil.implode(as);
    }
}

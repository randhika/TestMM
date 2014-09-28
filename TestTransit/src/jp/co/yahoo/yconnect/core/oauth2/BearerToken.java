// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.yconnect.core.oauth2;


public class BearerToken
{

    private String accessToken;
    private long expiration;
    private String refreshToken;
    private String scope;

    public BearerToken(String s, long l)
    {
        accessToken = "";
        expiration = 0L;
        refreshToken = null;
        scope = null;
        accessToken = s;
        expiration = l;
    }

    public BearerToken(String s, long l, String s1)
    {
        accessToken = "";
        expiration = 0L;
        refreshToken = null;
        scope = null;
        accessToken = s;
        expiration = l;
        refreshToken = s1;
    }

    public BearerToken(String s, long l, String s1, String s2)
    {
        accessToken = "";
        expiration = 0L;
        refreshToken = null;
        scope = null;
        accessToken = s;
        expiration = l;
        refreshToken = s1;
        scope = s2;
    }

    public String getAccessToken()
    {
        return accessToken;
    }

    public long getExpiration()
    {
        return expiration;
    }

    public String getRefreshToken()
    {
        return refreshToken;
    }

    public String getScope()
    {
        return scope;
    }

    public String toAuthorizationHeader()
    {
        return getAccessToken();
    }

    public String toQueryString()
    {
        return (new StringBuilder()).append("access_token=").append(accessToken).toString();
    }

    public String toString()
    {
        String s = (new StringBuilder()).append("{access_token: ").append(accessToken).append(", ").append("expidation: ").append(Long.toString(expiration)).toString();
        if (refreshToken != null)
        {
            s = (new StringBuilder()).append(s).append(", refresh_token: ").append(refreshToken).toString();
        }
        if (scope != null)
        {
            s = (new StringBuilder()).append(s).append(", scope: ").append(scope).toString();
        }
        return (new StringBuilder()).append(s).append("}").toString();
    }
}

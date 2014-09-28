// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.yconnect.core.oauth2;

import jp.co.yahoo.yconnect.core.util.YConnectLogger;
import org.json.JSONObject;

// Referenced classes of package jp.co.yahoo.yconnect.core.oauth2:
//            TokenException, BearerToken

abstract class AbstractTokenClient
{

    private static final String TAG = jp/co/yahoo/yconnect/core/oauth2/AbstractTokenClient.getSimpleName();
    protected BearerToken accessToken;
    protected String clientId;
    protected String endpointUrl;

    public AbstractTokenClient(String s, String s1)
    {
        endpointUrl = s;
        clientId = s1;
    }

    protected void checkErrorResponse(int i, JSONObject jsonobject)
        throws TokenException
    {
        if (i >= 400)
        {
            String s = jsonobject.optString("error");
            if (s != null)
            {
                String s1 = jsonobject.optString("error_description");
                String s2 = jsonobject.optString("error_code");
                YConnectLogger.error(TAG, (new StringBuilder()).append(s).append(" / ").append(s1).append(" / ").append(s2).toString());
                throw new TokenException(s, (new StringBuilder()).append(s1).append(" [be thrown by ").append(TAG).append("]").toString(), s2);
            } else
            {
                YConnectLogger.error(TAG, "An unexpected error has occurred.");
                throw new TokenException("An unexpected error has occurred.", (new StringBuilder()).append("[be thrown by ").append(TAG).append("]").toString(), "");
            }
        }
        if (i == 200)
        {
            return;
        } else
        {
            YConnectLogger.error(TAG, "An unexpected error has occurred.");
            throw new TokenException("An unexpected error has occurred.", "", "");
        }
    }

    abstract void fetch()
        throws TokenException, Exception;

    public BearerToken getAccessToken()
    {
        return accessToken;
    }

}

// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.yconnect.core.oauth2;

import android.net.Uri;
import jp.co.yahoo.yconnect.core.http.HttpParameters;
import jp.co.yahoo.yconnect.core.util.YConnectLogger;

// Referenced classes of package jp.co.yahoo.yconnect.core.oauth2:
//            AuthorizationCallbackUriParser, AuthorizationException, BearerToken

public class ImplicitCallbackUriParser extends AuthorizationCallbackUriParser
{

    private static final String TAG = jp/co/yahoo/yconnect/core/oauth2/ImplicitCallbackUriParser.getSimpleName();

    public ImplicitCallbackUriParser(Uri uri, String s)
        throws AuthorizationException
    {
        super(uri, s);
    }

    public BearerToken getAccessToken(String s)
        throws AuthorizationException
    {
        YConnectLogger.debug(TAG, (new StringBuilder()).append("Response state=").append((String)parameters.get("state")).append(", Input state=").append(s).toString());
        if (s.equals(parameters.get("state")))
        {
            String s1 = (String)parameters.get("access_token");
            String s2 = (String)parameters.get("expires_in");
            if (s1 != null && s2 != null)
            {
                return new BearerToken(s1, Long.parseLong(s2));
            } else
            {
                YConnectLogger.error(TAG, "No access_token or expires_in parameters.");
                throw new AuthorizationException("No access_token or expires_in parameters.", (new StringBuilder()).append("[be thrown by ").append(TAG).append("]").toString());
            }
        } else
        {
            YConnectLogger.error(TAG, "Not Match State.");
            throw new AuthorizationException("Not Match State.", (new StringBuilder()).append("[be thrown by ").append(TAG).append("]").toString());
        }
    }

    public String getIdToken()
    {
        String s = (String)parameters.get("id_token");
        if (s != null)
        {
            return s;
        } else
        {
            YConnectLogger.info(TAG, "Not found id_token parameter.");
            return null;
        }
    }

}

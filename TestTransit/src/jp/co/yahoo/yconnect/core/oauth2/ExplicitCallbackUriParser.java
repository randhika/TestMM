// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.yconnect.core.oauth2;

import android.net.Uri;
import jp.co.yahoo.yconnect.core.http.HttpParameters;
import jp.co.yahoo.yconnect.core.util.YConnectLogger;

// Referenced classes of package jp.co.yahoo.yconnect.core.oauth2:
//            AuthorizationCallbackUriParser, AuthorizationException

public class ExplicitCallbackUriParser extends AuthorizationCallbackUriParser
{

    private static final String TAG = jp/co/yahoo/yconnect/core/oauth2/ExplicitCallbackUriParser.getSimpleName();

    public ExplicitCallbackUriParser(Uri uri, String s)
        throws AuthorizationException
    {
        super(uri, s);
    }

    public String getAuthorizationCode(String s)
        throws AuthorizationException
    {
        YConnectLogger.debug(TAG, (new StringBuilder()).append("Response state=").append((String)parameters.get("state")).append(", Input state=").append(s).toString());
        if (s.equals(parameters.get("state")))
        {
            String s1 = (String)parameters.get("code");
            if (s1 != null)
            {
                return s1;
            } else
            {
                YConnectLogger.error(TAG, "No authorization code parameters.");
                throw new AuthorizationException("No authorization code parameters.", (new StringBuilder()).append("[be thrown by ").append(TAG).append("]").toString());
            }
        } else
        {
            YConnectLogger.error(TAG, "Not Match State.");
            throw new AuthorizationException("Not Match State.", "");
        }
    }

}

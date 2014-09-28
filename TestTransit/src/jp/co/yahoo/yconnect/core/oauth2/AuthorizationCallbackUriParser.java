// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.yconnect.core.oauth2;

import android.net.Uri;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import jp.co.yahoo.yconnect.core.http.HttpParameters;
import jp.co.yahoo.yconnect.core.util.YConnectLogger;

// Referenced classes of package jp.co.yahoo.yconnect.core.oauth2:
//            AuthorizationException

public class AuthorizationCallbackUriParser
{

    private static final String TAG = jp/co/yahoo/yconnect/core/oauth2/AuthorizationCallbackUriParser.getSimpleName();
    private String callBackUrl;
    protected HttpParameters parameters;

    public AuthorizationCallbackUriParser(Uri uri, String s)
        throws AuthorizationException
    {
        parameters = new HttpParameters();
        callBackUrl = s;
        parseUri(uri);
    }

    private void parseUri(Uri uri)
        throws AuthorizationException
    {
        if (uri == null)
        {
            YConnectLogger.error(TAG, "Not Found Callback URI.");
            throw new AuthorizationException("Not Found Callback URI.", (new StringBuilder()).append("[be thrown by ").append(TAG).append("]").toString());
        }
        YConnectLogger.debug(TAG, (new StringBuilder()).append("Response Uri: ").append(uri).toString());
        if (uri.toString().startsWith(callBackUrl))
        {
            extractParameters(uri.getEncodedQuery());
            extractParameters(uri.getEncodedFragment());
            if (parameters.get("error") != null)
            {
                String s = (String)parameters.get("error");
                String s1 = (String)parameters.get("error_description");
                YConnectLogger.error(TAG, (new StringBuilder()).append("error=").append(s).append(", error_description=").append(s1).toString());
                throw new AuthorizationException(s, s1);
            }
            if (parameters.isEmpty())
            {
                YConnectLogger.error(TAG, "Not Found Authorization Parameters.");
                throw new AuthorizationException("Not Found Authorization Parameters.", "");
            } else
            {
                YConnectLogger.debug(TAG, (new StringBuilder()).append("Finished Parsing: ").append(parameters.toString()).toString());
                return;
            }
        } else
        {
            YConnectLogger.error(TAG, "Invalid Callback URI.");
            throw new AuthorizationException("Invalid Callback URI.", uri.toString());
        }
    }

    public void extractParameters(String s)
    {
        int i = 0;
        if (s != null) goto _L2; else goto _L1
_L1:
        return;
_L2:
        String as[];
        int j;
        as = s.trim().split("&");
        j = as.length;
_L4:
        String as1[];
        if (i >= j)
        {
            continue; /* Loop/switch isn't completed */
        }
        as1 = as[i].split("=");
        String s1 = URLDecoder.decode(as1[0].trim(), "UTF-8");
        String s2 = "";
        try
        {
            if (as1.length == 2)
            {
                s2 = URLDecoder.decode(as1[1].trim(), "UTF-8");
            }
            parameters.put(s1, s2);
        }
        catch (UnsupportedEncodingException unsupportedencodingexception)
        {
            unsupportedencodingexception.printStackTrace();
        }
        i++;
        if (true) goto _L4; else goto _L3
_L3:
        if (true) goto _L1; else goto _L5
_L5:
    }

}

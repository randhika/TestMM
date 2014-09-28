// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.yconnect.core.http;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.net.ssl.SSLException;
import jp.co.yahoo.yconnect.core.util.YConnectLogger;
import org.apache.http.conn.ssl.AbstractVerifier;

public class CustomAbstractVerifier extends AbstractVerifier
{

    private static final String TAG = jp/co/yahoo/yconnect/core/http/CustomAbstractVerifier.getSimpleName();

    public CustomAbstractVerifier()
    {
    }

    public void verify(String s, String as[], String as1[])
        throws SSLException
    {
        YConnectLogger.debug(TAG, (new StringBuilder()).append("host : ").append(s).toString());
        YConnectLogger.debug(TAG, (new StringBuilder()).append("cns : ").append(Arrays.asList(as)).toString());
        YConnectLogger.debug(TAG, (new StringBuilder()).append("subjectAlts : ").append(Arrays.asList(as1)).toString());
        int i = as.length;
        for (int j = 0; j < i; j++)
        {
            String s1 = as[j];
            if (!s1.startsWith("*."))
            {
                continue;
            }
            Matcher matcher = Pattern.compile(s1.substring(2)).matcher(s);
            if (!matcher.find())
            {
                continue;
            }
            String as2[] = s.substring(0, matcher.start()).split("\\.");
            YConnectLogger.debug(TAG, (new StringBuilder()).append("sub domains : ").append(Arrays.asList(as2)).toString());
            if (as2.length > 1)
            {
                throw new SSLException((new StringBuilder()).append("SSL certificates is a wildcard, and there is more than one subdomain. [be thrown by ").append(TAG).append("]").toString());
            }
        }

    }

}

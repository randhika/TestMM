// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.yconnect.core.oidc;

import java.util.HashMap;
import jp.co.yahoo.yconnect.core.util.YConnectLogger;

// Referenced classes of package jp.co.yahoo.yconnect.core.oidc:
//            CheckIdException

abstract class AbstractCommonClient
{

    private static final String TAG = jp/co/yahoo/yconnect/core/oidc/AbstractCommonClient.getSimpleName();
    protected String endpointUrl;

    public AbstractCommonClient(String s)
    {
        endpointUrl = s;
    }

    protected static HashMap extractWWWAuthHeader(String s)
    {
        HashMap hashmap = new HashMap();
        String as[] = s.split(",");
        int i = as.length;
        for (int j = 0; j < i; j++)
        {
            String as1[] = as[j].replaceAll("\"", "").split("=");
            hashmap.put(as1[0].trim(), as1[1].trim());
            YConnectLogger.debug(TAG, (new StringBuilder()).append(as1[0].trim()).append(" / ").append(as1[1].trim()).toString());
        }

        return hashmap;
    }

    abstract void fetch()
        throws CheckIdException, Exception;

}

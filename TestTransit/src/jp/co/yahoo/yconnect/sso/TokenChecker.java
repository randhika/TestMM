// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.yconnect.sso;

import android.content.Context;
import java.util.Date;
import jp.co.yahoo.yconnect.core.oidc.IdTokenObject;
import jp.co.yahoo.yconnect.core.util.YConnectLogger;
import jp.co.yahoo.yconnect.data.DataManager;

public class TokenChecker
{

    private static final String TAG = jp/co/yahoo/yconnect/sso/TokenChecker.getSimpleName();

    public TokenChecker()
    {
    }

    public static boolean chkIdTokenExp(Context context, long l)
        throws Exception
    {
        long l1 = getIdTokenExpiration(context).longValue();
        long l2 = l1 - l;
        YConnectLogger.debug(TAG, (new StringBuilder()).append("idToken_expiredTime : ").append(l1).toString());
        YConnectLogger.debug(TAG, (new StringBuilder()).append("respnseTime : ").append(l).toString());
        if (l2 >= 0L)
        {
            YConnectLogger.info(TAG, "checked IdToken.");
            YConnectLogger.verbose(TAG, (new StringBuilder()).append("diff days : ").append((float)l2 / 86400F).toString());
            return true;
        } else
        {
            YConnectLogger.info(TAG, "IdToken is expired.");
            return false;
        }
    }

    public static boolean chkIdTokenExp(Context context, String s)
        throws Exception
    {
        return chkIdTokenExp(context, (new Date(s)).getTime() / 1000L);
    }

    private static Long getIdTokenExpiration(Context context)
        throws Exception
    {
        return Long.valueOf((new DataManager(context, DataManager.loadSecretKey(context, "default_yid"), "default_yid")).loadIdToken().getExp());
    }

}

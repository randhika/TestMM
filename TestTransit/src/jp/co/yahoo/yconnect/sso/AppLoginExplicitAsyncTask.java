// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.yconnect.sso;

import android.support.v4.app.FragmentActivity;
import android.support.v4.content.AsyncTaskLoader;
import jp.co.yahoo.yconnect.AppLoginExplicit;
import jp.co.yahoo.yconnect.core.oauth2.BearerToken;
import jp.co.yahoo.yconnect.core.oauth2.TokenException;
import jp.co.yahoo.yconnect.core.oidc.CheckIdException;
import jp.co.yahoo.yconnect.core.util.YConnectLogger;
import jp.co.yahoo.yconnect.data.DataManager;

public class AppLoginExplicitAsyncTask extends AsyncTaskLoader
{

    private static final String TAG = jp/co/yahoo/yconnect/sso/AppLoginExplicitAsyncTask.getSimpleName();
    private FragmentActivity activity;
    private String clientId;
    private String code;
    private String customUriScheme;
    private String idTokenString;
    private String nonce;
    private AppLoginExplicit yconnect;

    public AppLoginExplicitAsyncTask(FragmentActivity fragmentactivity, String s, String s1, String s2, String s3)
    {
        super(fragmentactivity);
        activity = fragmentactivity;
        clientId = s2;
        code = s;
        nonce = s1;
        customUriScheme = s3;
    }

    public volatile Object loadInBackground()
    {
        return loadInBackground();
    }

    public String loadInBackground()
    {
        YConnectLogger.info(TAG, "Request Access Token and Refresh Token.");
        yconnect = AppLoginExplicit.getInstance();
        String s2;
        yconnect.requestToken(code, customUriScheme, clientId);
        String s = yconnect.getAccessToken();
        long l = yconnect.getAccessTokenExpiration();
        String s1 = yconnect.getRefreshToken();
        idTokenString = yconnect.getIdToken();
        byte abyte0[] = DataManager.loadSecretKey(activity, "default_yid");
        DataManager datamanager = new DataManager(activity, abyte0, "default_yid");
        datamanager.saveAccessToken(new BearerToken(s, l, s1));
        YConnectLogger.info(TAG, "Request CheckToken.");
        yconnect.requestCheckToken(idTokenString, nonce, clientId);
        datamanager.saveIdToken(yconnect.getIdTokenObject());
        s2 = idTokenString;
        return s2;
        TokenException tokenexception;
        tokenexception;
        YConnectLogger.error(TAG, (new StringBuilder()).append("error=").append(tokenexception.getError()).append(", error_description=").append(tokenexception.getErrorDescription()).toString());
_L2:
        return "";
        CheckIdException checkidexception;
        checkidexception;
        YConnectLogger.error(TAG, (new StringBuilder()).append("error=").append(checkidexception.getError()).append(", error_description=").append(checkidexception.getErrorDescription()).toString());
        continue; /* Loop/switch isn't completed */
        Exception exception;
        exception;
        YConnectLogger.error(TAG, (new StringBuilder()).append("error=").append(exception.getMessage()).toString());
        if (true) goto _L2; else goto _L1
_L1:
    }

    protected void onStartLoading()
    {
        forceLoad();
    }

}

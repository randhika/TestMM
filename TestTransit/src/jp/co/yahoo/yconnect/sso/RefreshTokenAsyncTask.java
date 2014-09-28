// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.yconnect.sso;

import android.support.v4.app.FragmentActivity;
import android.support.v4.content.AsyncTaskLoader;
import jp.co.yahoo.yconnect.AppLoginExplicit;
import jp.co.yahoo.yconnect.core.oauth2.BearerToken;
import jp.co.yahoo.yconnect.core.oauth2.TokenException;
import jp.co.yahoo.yconnect.core.util.YConnectLogger;
import jp.co.yahoo.yconnect.data.DataManager;

// Referenced classes of package jp.co.yahoo.yconnect.sso:
//            TokenChecker

public class RefreshTokenAsyncTask extends AsyncTaskLoader
{

    private static final String TAG = jp/co/yahoo/yconnect/sso/RefreshTokenAsyncTask.getSimpleName();
    public static final String exception = "999";
    public static final String expired = "expired";
    public static final String success = "0";
    private FragmentActivity activity;
    private DataManager manager;
    private String refreshToken;
    private AppLoginExplicit yconnect;

    public RefreshTokenAsyncTask(FragmentActivity fragmentactivity)
    {
        super(fragmentactivity);
        activity = fragmentactivity;
    }

    public volatile Object loadInBackground()
    {
        return loadInBackground();
    }

    public String loadInBackground()
    {
        try
        {
            YConnectLogger.info(TAG, "Refreshing AccessToken and checking token expiration.");
            byte abyte0[] = DataManager.loadSecretKey(activity, "default_yid");
            manager = new DataManager(activity, abyte0, "default_yid");
            refreshToken = manager.loadAccessToken().getRefreshToken();
            yconnect = AppLoginExplicit.getInstance();
            yconnect.refreshToken(refreshToken, yconnect.clientId);
            if (!TokenChecker.chkIdTokenExp(activity, yconnect.getResponseTime()))
            {
                YConnectLogger.info(TAG, "error=expired_idToke, error_description=IdToken is expired.");
                throw new TokenException("expired_idToken", (new StringBuilder()).append("IdToken is expired.[be thrown by ").append(TAG).append("]").toString(), "702");
            }
        }
        catch (TokenException tokenexception)
        {
            YConnectLogger.error(TAG, (new StringBuilder()).append("error=").append(tokenexception.getError()).append(", error_description=").append(tokenexception.getErrorDescription()).append(", error_code=").append(tokenexception.getErrorCode()).toString());
            if (tokenexception.isInvalidGrant() || tokenexception.isExpiredIdtoken())
            {
                return "expired";
            } else
            {
                return tokenexception.getErrorCode();
            }
        }
        catch (Exception exception1)
        {
            YConnectLogger.error(TAG, (new StringBuilder()).append("error=").append(exception1.getMessage()).toString());
            return "999";
        }
        manager.saveAccessToken(new BearerToken(yconnect.getAccessToken(), yconnect.getAccessTokenExpiration(), refreshToken));
        return "0";
    }

    protected void onStartLoading()
    {
        forceLoad();
    }

}

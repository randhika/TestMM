// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.yconnect;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import java.util.Date;
import java.util.UUID;
import jp.co.yahoo.yconnect.core.oauth2.AuthorizationRequestClient;
import jp.co.yahoo.yconnect.core.oauth2.BearerToken;
import jp.co.yahoo.yconnect.core.oauth2.TokenException;
import jp.co.yahoo.yconnect.core.util.YConnectLogger;
import jp.co.yahoo.yconnect.data.DataManager;
import jp.co.yahoo.yconnect.data.util.RandomStringUtil;
import jp.co.yahoo.yconnect.sso.AppLogin;
import jp.co.yahoo.yconnect.sso.AppLoginRefreshTokenActivity;
import jp.co.yahoo.yconnect.sso.LoginInvisibleActivity;
import jp.co.yahoo.yconnect.sso.LogoutInvisibleActivity;
import jp.co.yahoo.yconnect.sso.RefreshTokenInvisibleActivity;
import jp.co.yahoo.yconnect.sso.ShowLoginViewActivity;
import jp.co.yahoo.yconnect.sso.TokenChecker;
import jp.co.yahoo.yconnect.sso.YTcookieChecker;

// Referenced classes of package jp.co.yahoo.yconnect:
//            YConnectExplicit

public class AppLoginExplicit extends YConnectExplicit
    implements AppLogin
{

    public static final String TAG = jp/co/yahoo/yconnect/AppLoginExplicit.getSimpleName();
    private static AppLoginExplicit instance;
    static final String version = "3.1.1";
    public Intent authzIntent;
    public String clientId;
    public String customUriScheme;
    public String loginType;
    public boolean sloginSkip;
    public String snonce;

    protected AppLoginExplicit()
    {
        loginType = "simple";
        String as[] = {
            "recognize"
        };
        String as1[] = {
            "openid", "profile", "email", "address"
        };
        setDisplay("inapp");
        setScope(as1);
        setPrompt(as);
        setSloginSkip(false);
    }

    public static AppLoginExplicit getInstance()
    {
        if (instance == null)
        {
            instance = new AppLoginExplicit();
        }
        return instance;
    }

    public static boolean isLogin(Context context)
    {
        jp.co.yahoo.yconnect.core.oidc.IdTokenObject idtokenobject;
        long l;
        try
        {
            idtokenobject = (new DataManager(context, DataManager.loadSecretKey(context, "default_yid"), "default_yid")).loadIdToken();
            l = (new Date()).getTime() / 1000L;
        }
        catch (Exception exception)
        {
            YConnectLogger.info(TAG, "Status is Exception.");
            exception.printStackTrace();
            return false;
        }
        if (idtokenobject == null)
        {
            break MISSING_BLOCK_LABEL_63;
        }
        if (!TokenChecker.chkIdTokenExp(context, l) || !YTcookieChecker.chkYTcookie())
        {
            break MISSING_BLOCK_LABEL_63;
        }
        YConnectLogger.info(TAG, "Status is Login.");
        return true;
        YConnectLogger.info(TAG, "Status is Logout.");
        return false;
    }

    public Uri generateAuthorizationUri()
    {
        snonce = generateSnonce();
        requestClient.setParameter("login_type", loginType);
        requestClient.setParameter("snonce", snonce);
        requestClient.setParameter("sdk", (new StringBuilder()).append(getVersion()).append("a").toString());
        return super.generateAuthorizationUri();
    }

    public String generateSnonce()
    {
        return UUID.randomUUID().toString();
    }

    public String getVersion()
    {
        return "3.1.1";
    }

    public void init(String s, String s1, Context context)
        throws Exception
    {
        RandomStringUtil randomstringutil = new RandomStringUtil();
        String s2 = randomstringutil.generateState();
        String s3 = randomstringutil.generateNonce();
        byte abyte0[] = DataManager.loadSecretKey(context, "default_yid");
        if (abyte0 == null)
        {
            abyte0 = DataManager.generateSecretKey();
        }
        DataManager datamanager = new DataManager(context, abyte0, "default_yid");
        datamanager.saveState(s2);
        datamanager.saveNonce(s3);
        setNonce(s3);
        init(s, s1, s2);
    }

    public void issueRefreshToken(Activity activity, int i)
    {
        activity.startActivityForResult(new Intent(activity, jp/co/yahoo/yconnect/sso/AppLoginRefreshTokenActivity), i);
    }

    public void login(Activity activity, int i)
    {
        activity.startActivityForResult(new Intent(activity, jp/co/yahoo/yconnect/sso/LoginInvisibleActivity), i);
    }

    public void logout(Activity activity, int i)
    {
        activity.startActivityForResult(new Intent(activity, jp/co/yahoo/yconnect/sso/LogoutInvisibleActivity), i);
    }

    public void refreshToken(Activity activity, int i)
    {
        activity.startActivityForResult(new Intent(activity, jp/co/yahoo/yconnect/sso/RefreshTokenInvisibleActivity), i);
    }

    public void refreshToken(Context context)
        throws TokenException, Exception
    {
        YConnectLogger.info(TAG, "Refreshing AccessToken and checking token expiration.");
        DataManager datamanager = new DataManager(context, DataManager.loadSecretKey(context, "default_yid"), "default_yid");
        String s = datamanager.loadAccessToken().getRefreshToken();
        super.refreshToken(s, clientId);
        if (!TokenChecker.chkIdTokenExp(context, getResponseTime()))
        {
            YConnectLogger.info(TAG, "error=expired_idToke, error_description=IdToken is expired.");
            throw new TokenException("expired_idToken", (new StringBuilder()).append("IdToken is expired. [be thrown by ").append(TAG).append("]").toString(), "702");
        } else
        {
            datamanager.saveAccessToken(new BearerToken(getAccessToken(), getAccessTokenExpiration(), s));
            return;
        }
    }

    public void requestAuthorization(Activity activity)
    {
        if (authzIntent == null)
        {
            YConnectLogger.debug(TAG, "Generate new intent.");
            activity.startActivity(new Intent("android.intent.action.VIEW", generateAuthorizationUri()));
        } else
        {
            YConnectLogger.debug(TAG, "setData to authzIntent.");
            authzIntent.setData(generateAuthorizationUri());
            activity.startActivity(authzIntent);
        }
        activity.finish();
    }

    public void setAuthzIntent(Intent intent)
    {
        authzIntent = intent;
    }

    public void setClientId(String s)
    {
        clientId = s;
    }

    public void setCustomUriScheme(String s)
    {
        customUriScheme = s;
    }

    public void setSloginSkip(boolean flag)
    {
        sloginSkip = flag;
    }

    public void showLoginView(Activity activity, int i)
    {
        activity.startActivityForResult(new Intent(activity, jp/co/yahoo/yconnect/sso/ShowLoginViewActivity), i);
    }

}

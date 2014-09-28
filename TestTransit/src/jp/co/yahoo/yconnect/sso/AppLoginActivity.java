// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.yconnect.sso;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import jp.co.yahoo.yconnect.AppLoginExplicit;
import jp.co.yahoo.yconnect.core.oauth2.AuthorizationException;
import jp.co.yahoo.yconnect.core.util.YConnectLogger;
import jp.co.yahoo.yconnect.data.DataManager;

// Referenced classes of package jp.co.yahoo.yconnect.sso:
//            SSODialogFragment, AppLoginExplicitAsyncTask, SloginClient

public class AppLoginActivity extends FragmentActivity
    implements android.support.v4.app.LoaderManager.LoaderCallbacks
{

    public static final String TAG = jp/co/yahoo/yconnect/sso/AppLoginActivity.getSimpleName();
    private String clientId;
    private String customUriScheme;
    private SSODialogFragment fragment;
    private Intent intent;
    private AppLoginExplicit yconnect;

    public AppLoginActivity()
    {
        clientId = null;
        customUriScheme = null;
    }

    public void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        YConnectLogger.debug(TAG, "onCreate AppLoginActivity");
        intent = getIntent();
        yconnect = AppLoginExplicit.getInstance();
        clientId = yconnect.clientId;
        customUriScheme = yconnect.customUriScheme;
        setContentView(jp.co.yahoo.yconnect.sdk.R.layout.appsso_webview_app_login);
        if ("android.intent.action.VIEW".equals(intent.getAction()))
        {
            reqAppLoginExplicitAsyncTask(yconnect, intent);
            return;
        } else
        {
            requAuthzEndpoint(yconnect);
            return;
        }
    }

    public Loader onCreateLoader(int i, Bundle bundle)
    {
        YConnectLogger.verbose(TAG, "onCreateLoader");
        Bundle bundle1 = new Bundle();
        bundle1.putString("Message", "\u8AAD\u307F\u8FBC\u307F\u4E2D...");
        fragment = SSODialogFragment.newInstance();
        fragment.setArguments(bundle1);
        fragment.show(getSupportFragmentManager(), "progress");
        return new AppLoginExplicitAsyncTask(this, bundle.getString("code"), bundle.getString("nonce"), clientId, customUriScheme);
    }

    public volatile void onLoadFinished(Loader loader, Object obj)
    {
        onLoadFinished(loader, (String)obj);
    }

    public void onLoadFinished(Loader loader, String s)
    {
        if (s != null && !s.equals("") && !yconnect.sloginSkip)
        {
            YConnectLogger.info(TAG, "Request sloginActivity.");
            (new SloginClient(s, yconnect.snonce, yconnect.loginType, clientId)).reqLoginClient(this);
            return;
        } else
        {
            YConnectLogger.info(TAG, "Not Request sloginActivity.");
            finish();
            return;
        }
    }

    public void onLoaderReset(Loader loader)
    {
    }

    public void reqAppLoginExplicitAsyncTask(AppLoginExplicit apploginexplicit, Intent intent1)
    {
        try
        {
            YConnectLogger.info(TAG, "Get callback uri and parse it.");
            DataManager datamanager = new DataManager(this, DataManager.loadSecretKey(this, "default_yid"), "default_yid");
            String s = datamanager.loadState();
            String s1 = datamanager.loadNonce();
            apploginexplicit.parseAuthorizationResponse(intent1.getData(), customUriScheme, s);
            String s2 = apploginexplicit.getAuthorizationCode();
            Bundle bundle = new Bundle();
            bundle.putString("code", s2);
            bundle.putString("nonce", s1);
            getSupportLoaderManager().initLoader(0, bundle, this);
            return;
        }
        catch (AuthorizationException authorizationexception)
        {
            YConnectLogger.error(TAG, (new StringBuilder()).append("error=").append(authorizationexception.getError()).toString());
            authorizationexception.printStackTrace();
            finish();
            return;
        }
        catch (Exception exception)
        {
            YConnectLogger.error(TAG, (new StringBuilder()).append("error=").append(exception.getMessage()).toString());
            exception.printStackTrace();
            finish();
            return;
        }
    }

    public void requAuthzEndpoint(AppLoginExplicit apploginexplicit)
    {
        try
        {
            YConnectLogger.info(TAG, "Request authorization.");
            apploginexplicit.init(clientId, customUriScheme, this);
            apploginexplicit.requestAuthorization(this);
            return;
        }
        catch (Exception exception)
        {
            YConnectLogger.error(TAG, (new StringBuilder()).append("error=").append(exception.getMessage()).toString());
            exception.printStackTrace();
            finish();
            return;
        }
    }

}

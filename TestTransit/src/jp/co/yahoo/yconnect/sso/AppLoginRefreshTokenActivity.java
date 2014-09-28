// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.yconnect.sso;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import jp.co.yahoo.yconnect.AppLoginExplicit;
import jp.co.yahoo.yconnect.core.util.YConnectLogger;
import jp.co.yahoo.yconnect.data.DataManager;

// Referenced classes of package jp.co.yahoo.yconnect.sso:
//            SSODialogFragment, AppLoginExplicitAsyncTask, SloginClient

public class AppLoginRefreshTokenActivity extends FragmentActivity
    implements android.support.v4.app.LoaderManager.LoaderCallbacks
{

    public static final String TAG = jp/co/yahoo/yconnect/sso/AppLoginRefreshTokenActivity.getSimpleName();
    private String GRANT_ENDPOINT_URL;
    private String clientId;
    private String customUriScheme;
    private SSODialogFragment fragment;
    private AppLoginExplicit yconnect;

    public AppLoginRefreshTokenActivity()
    {
        clientId = null;
        customUriScheme = null;
        GRANT_ENDPOINT_URL = "https://auth.login.yahoo.co.jp/yconnect/v1/grant";
    }

    public void deleteToken()
    {
        try
        {
            DataManager datamanager = new DataManager(this, DataManager.loadSecretKey(this, "default_yid"), "default_yid");
            datamanager.deleteAccessToken();
            datamanager.deleteIdToken();
            return;
        }
        catch (Exception exception)
        {
            YConnectLogger.error(TAG, (new StringBuilder()).append("error=").append(exception.getMessage()).toString());
        }
        finish();
    }

    public Uri genCallbackUri(String s)
    {
        Uri uri = Uri.parse(s);
        android.net.Uri.Builder builder = new android.net.Uri.Builder();
        builder.scheme(uri.getScheme());
        builder.authority(uri.getAuthority());
        builder.path(uri.getPath());
        builder.appendQueryParameter("code", uri.getQueryParameter("code"));
        builder.appendQueryParameter("state", uri.getQueryParameter("state"));
        return builder.build();
    }

    public String getAuthzEndpointUri(AppLoginExplicit apploginexplicit)
    {
        String s;
        try
        {
            YConnectLogger.info(TAG, "Request authorization.");
            clientId = apploginexplicit.clientId;
            customUriScheme = apploginexplicit.customUriScheme;
            apploginexplicit.setPrompt(new String[] {
                "login"
            });
            apploginexplicit.init(clientId, customUriScheme, this);
            s = apploginexplicit.generateAuthorizationUri().toString();
        }
        catch (Exception exception)
        {
            YConnectLogger.error(TAG, (new StringBuilder()).append("error=").append(exception.getMessage()).toString());
            finish();
            return null;
        }
        return s;
    }

    public void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        YConnectLogger.debug(TAG, "onCreate YConnectExplicitWebViewActivity");
        deleteToken();
        yconnect = AppLoginExplicit.getInstance();
        String s = getAuthzEndpointUri(yconnect);
        YConnectLogger.error(TAG, (new StringBuilder()).append("authzUri").append(s).toString());
        reqAuthzEndpointWebview(yconnect, s);
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

    public void reqAppLoginExplicitAsyncTask(String s)
    {
        try
        {
            YConnectLogger.info(TAG, "Get callback uri and parse it.");
            DataManager datamanager = new DataManager(this, DataManager.loadSecretKey(this, "default_yid"), "default_yid");
            String s1 = datamanager.loadState();
            String s2 = datamanager.loadNonce();
            yconnect.parseAuthorizationResponse(genCallbackUri(s), customUriScheme, s1);
            String s3 = yconnect.getAuthorizationCode();
            Bundle bundle = new Bundle();
            bundle.putString("code", s3);
            bundle.putString("nonce", s2);
            getSupportLoaderManager().initLoader(0, bundle, this);
            return;
        }
        catch (Exception exception)
        {
            YConnectLogger.error(TAG, (new StringBuilder()).append("error=").append(exception.getMessage()).toString());
        }
        finish();
    }

    public void reqAuthzEndpointWebview(AppLoginExplicit apploginexplicit, String s)
    {
        try
        {
            WebViewClient webviewclient = new WebViewClient() {

                final AppLoginRefreshTokenActivity this$0;

                public void onPageFinished(WebView webview1, String s1)
                {
                    if (s1.startsWith(GRANT_ENDPOINT_URL))
                    {
                        webview1.stopLoading();
                    }
                }

                public void onPageStarted(WebView webview1, String s1, Bitmap bitmap)
                {
                    YConnectLogger.verbose(AppLoginRefreshTokenActivity.TAG, (new StringBuilder()).append("url=").append(s1).toString());
                    if (s1.startsWith(customUriScheme))
                    {
                        YConnectLogger.info(AppLoginRefreshTokenActivity.TAG, "url is customUriScheme.");
                        reqAppLoginExplicitAsyncTask(s1);
                    }
                }

            
            {
                this$0 = AppLoginRefreshTokenActivity.this;
                super();
            }
            };
            setContentView(jp.co.yahoo.yconnect.sdk.R.layout.appsso_webview_refresh_token);
            WebView webview = (WebView)findViewById(jp.co.yahoo.yconnect.sdk.R.id.webview_refresh_token);
            webview.clearCache(true);
            webview.setWebViewClient(webviewclient);
            webview.setWebChromeClient(new WebChromeClient());
            webview.getSettings().setJavaScriptEnabled(true);
            webview.loadUrl(s);
            YConnectLogger.info(TAG, "reqAuthzEndpointWebview");
            return;
        }
        catch (Exception exception)
        {
            YConnectLogger.error(TAG, (new StringBuilder()).append("error=").append(exception.getMessage()).toString());
        }
        finish();
    }



}

// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.yconnect.sso;

import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import jp.co.yahoo.yconnect.core.util.YConnectLogger;

// Referenced classes of package jp.co.yahoo.yconnect.sso:
//            SSODialogFragment

public class AppLogoutWebviewClient extends FragmentActivity
{

    private static final String DONE = "https://auth.login.yahoo.co.jp/yconnect/v1/logout_complete";
    private static final String LOGIN = "https://login.yahoo.co.jp/";
    private static final String TAG = jp/co/yahoo/yconnect/sso/AppLogoutWebviewClient.getSimpleName();
    private static final String delimiter = ";";
    private static final String domain = "login.yahoo.co.jp";
    private static final String path = "config/login";
    private static final String src = "www";
    private FragmentActivity activity;
    private SSODialogFragment fragment;
    private String loginCookie;
    private android.net.Uri.Builder logoutReq;

    public AppLogoutWebviewClient(FragmentActivity fragmentactivity)
    {
        logoutReq = new android.net.Uri.Builder();
        activity = fragmentactivity;
    }

    private void activityFinish()
    {
        YConnectLogger.verbose(TAG, "finished logoutWebviewClient.");
        fragment = (SSODialogFragment)getSupportFragmentManager().findFragmentByTag("progress");
        if (fragment != null && (fragment instanceof SSODialogFragment))
        {
            fragment.dismissAllowingStateLoss();
        }
        activity.finish();
    }

    private boolean chkTcookie(WebView webview, String s)
    {
        String as[] = s.split(";");
        int i = as.length;
        for (int j = 0; j < i; j++)
        {
            if (as[j].trim().startsWith("T="))
            {
                return true;
            }
        }

        YConnectLogger.info(TAG, "Deleted TCookie.");
        return false;
    }

    private String genLogoutUri()
    {
        Uri uri = Uri.parse("https://login.yahoo.co.jp/");
        logoutReq.scheme(uri.getScheme());
        logoutReq.authority(uri.getAuthority());
        logoutReq.path("config/login");
        logoutReq.appendQueryParameter(".src", "www");
        logoutReq.appendQueryParameter("logout", "1");
        logoutReq.appendQueryParameter(".direct", "1");
        logoutReq.appendQueryParameter(".done", "https://auth.login.yahoo.co.jp/yconnect/v1/logout_complete");
        return logoutReq.build().toString();
    }

    private String getLoginCookie()
    {
        CookieSyncManager.createInstance(activity);
        CookieSyncManager.getInstance().sync();
        CookieManager cookiemanager = CookieManager.getInstance();
        cookiemanager.setAcceptCookie(true);
        return cookiemanager.getCookie("login.yahoo.co.jp");
    }

    public void logoutWebviewClient()
    {
        try
        {
            YConnectLogger.info(TAG, "Request delete cookie.");
            activity.setContentView(jp.co.yahoo.yconnect.sdk.R.layout.appsso_webview_app_logout);
            WebViewClient webviewclient = new WebViewClient() {

                final AppLogoutWebviewClient this$0;

                public void onPageFinished(WebView webview1, String s)
                {
                    if (loginCookie != null)
                    {
                        chkTcookie(webview1, loginCookie);
                    }
                    YConnectLogger.info(AppLogoutWebviewClient.TAG, "has already logouted.");
                    webview1.stopLoading();
                    activityFinish();
                }

                public void onPageStarted(WebView webview1, String s, Bitmap bitmap)
                {
                    YConnectLogger.debug(AppLogoutWebviewClient.TAG, (new StringBuilder()).append("URL: ").append(s).toString());
                    loginCookie = getLoginCookie();
                    YConnectLogger.debug(AppLogoutWebviewClient.TAG, "url: login.yahoo.co.jp");
                    YConnectLogger.debug(AppLogoutWebviewClient.TAG, (new StringBuilder()).append("cookie: ").append(loginCookie).toString());
                }

            
            {
                this$0 = AppLogoutWebviewClient.this;
                super();
            }
            };
            WebView webview = (WebView)activity.findViewById(jp.co.yahoo.yconnect.sdk.R.id.webview_app_logout);
            webview.setWebViewClient(webviewclient);
            webview.clearCache(true);
            webview.setWebChromeClient(new WebChromeClient());
            webview.getSettings().setJavaScriptEnabled(true);
            webview.loadUrl(genLogoutUri());
            return;
        }
        catch (Exception exception)
        {
            YConnectLogger.error(TAG, (new StringBuilder()).append("error=").append(exception.getMessage()).toString());
        }
        activityFinish();
    }





/*
    static String access$102(AppLogoutWebviewClient applogoutwebviewclient, String s)
    {
        applogoutwebviewclient.loginCookie = s;
        return s;
    }

*/



}

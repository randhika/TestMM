// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.yconnect.sso;

import android.app.Activity;
import android.net.Uri;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import jp.co.yahoo.yconnect.core.util.YConnectLogger;

// Referenced classes of package jp.co.yahoo.yconnect.sso:
//            SSODialogFragment, SloginWebViewClient

public class SloginClient extends FragmentActivity
{

    public static final String SLOGIN = "https://auth.login.yahoo.co.jp/yconnect/v1/slogin";
    public static final String SLOGIN_COMPLETE = "https://auth.login.yahoo.co.jp/yconnect/v1/slogin_complete";
    public static final String SLOGIN_ERROR = "https://auth.login.yahoo.co.jp/yconnect/v1/slogin_error";
    private static final String TAG = jp/co/yahoo/yconnect/sso/SloginClient.getSimpleName();
    private Activity activity;
    private String clientId;
    private SSODialogFragment fragment;
    private String idToken;
    private String loginType;
    public android.net.Uri.Builder sloginReq;
    private String snonce;
    private WebView webView;

    public SloginClient(String s, String s1, String s2, String s3)
    {
        sloginReq = new android.net.Uri.Builder();
        idToken = s;
        snonce = s1;
        loginType = s2;
        clientId = s3;
    }

    public void finish()
    {
        YConnectLogger.verbose(TAG, "finished SloginClient.");
        fragment = (SSODialogFragment)getSupportFragmentManager().findFragmentByTag("progress");
        if (fragment != null && (fragment instanceof SSODialogFragment))
        {
            fragment.dismissAllowingStateLoss();
        }
        activity.finish();
    }

    public String generateSloginUri()
    {
        Uri uri = Uri.parse("https://auth.login.yahoo.co.jp/yconnect/v1/slogin");
        sloginReq.scheme(uri.getScheme());
        sloginReq.authority(uri.getAuthority());
        sloginReq.path(uri.getPath());
        sloginReq.appendQueryParameter("token", idToken);
        sloginReq.appendQueryParameter("snonce", snonce);
        sloginReq.appendQueryParameter("login_type", loginType);
        sloginReq.appendQueryParameter("client_id", clientId);
        return sloginReq.build().toString();
    }

    public void reqLoginClient(Activity activity1)
    {
        try
        {
            YConnectLogger.info(TAG, "Request slogin and relogin.");
            activity = activity1;
            activity1.setContentView(jp.co.yahoo.yconnect.sdk.R.layout.appsso_webview_slogin);
            SloginWebViewClient sloginwebviewclient = new SloginWebViewClient(this);
            String s = generateSloginUri();
            webView = (WebView)activity1.findViewById(jp.co.yahoo.yconnect.sdk.R.id.webview_slogin);
            webView.clearCache(true);
            webView.setWebViewClient(sloginwebviewclient);
            webView.setWebChromeClient(new WebChromeClient());
            webView.getSettings().setJavaScriptEnabled(true);
            webView.loadUrl(s);
            return;
        }
        catch (Exception exception)
        {
            YConnectLogger.error(TAG, (new StringBuilder()).append("error=").append(exception.getMessage()).toString());
        }
        finish();
    }

}

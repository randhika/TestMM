// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.yconnect.sso;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import jp.co.yahoo.yconnect.AppLoginExplicit;
import jp.co.yahoo.yconnect.core.util.YConnectLogger;

public class ShowLoginViewActivity extends Activity
{

    private static final String TAG = jp/co/yahoo/yconnect/sso/ShowLoginViewActivity.getSimpleName();
    private String REGISTRATION;
    private String last;
    private String src;
    private String template;
    private WebView webView;

    public ShowLoginViewActivity()
    {
        REGISTRATION = "https://account.edit.yahoo.co.jp/registration";
        src = "yconnect";
        last = "";
    }

    public void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        YConnectLogger.debug(TAG, "onCreate ShowLoginViewActivity");
        setContentView(jp.co.yahoo.yconnect.sdk.R.layout.appsso_webview_show_login_view);
        StringBuilder stringbuilder;
        BufferedReader bufferedreader;
        YConnectLogger.info(TAG, "Request show login view.");
        java.io.InputStream inputstream = getResources().openRawResource(jp.co.yahoo.yconnect.sdk.R.raw.appsso_login_view);
        stringbuilder = new StringBuilder();
        bufferedreader = new BufferedReader(new InputStreamReader(inputstream));
_L1:
        String s = bufferedreader.readLine();
label0:
        {
            if (s == null)
            {
                break label0;
            }
            try
            {
                stringbuilder.append((new StringBuilder()).append(s).append("\n").toString());
            }
            catch (IOException ioexception)
            {
                YConnectLogger.error(TAG, (new StringBuilder()).append("error=").append(ioexception.getMessage()).toString());
                ioexception.printStackTrace();
                return;
            }
        }
          goto _L1
        template = stringbuilder.toString();
        reqLoginView();
        return;
    }

    public void reqLogin()
    {
        YConnectLogger.verbose(TAG, "Request login activity.");
        AppLoginExplicit.getInstance().login(this, 100);
        finish();
    }

    public void reqLoginView()
    {
        YConnectLogger.verbose(TAG, "Request loginView.");
        WebViewClient webviewclient = new WebViewClient();
        webView = (WebView)findViewById(jp.co.yahoo.yconnect.sdk.R.id.webview_show_login_view);
        webView.clearCache(true);
        webView.setWebViewClient(webviewclient);
        webView.setWebChromeClient(new WebChromeClient() {

            final ShowLoginViewActivity this$0;

            public boolean onJsAlert(WebView webview, String s, String s1, JsResult jsresult)
            {
                YConnectLogger.debug(ShowLoginViewActivity.TAG, (new StringBuilder()).append("onjsAlert:").append(s1).toString());
                if (!s1.equals("login")) goto _L2; else goto _L1
_L1:
                reqLogin();
_L4:
                jsresult.confirm();
                return true;
_L2:
                if (s1.equals("registration"))
                {
                    reqRegistrationYID();
                }
                if (true) goto _L4; else goto _L3
_L3:
            }

            
            {
                this$0 = ShowLoginViewActivity.this;
                super();
            }
        });
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadDataWithBaseURL("file:///android_asset/", template, "text/html", "utf-8", null);
    }

    public void reqRegistrationYID()
    {
        YConnectLogger.info(TAG, "Request account registration.");
        try
        {
            AppLoginExplicit apploginexplicit = AppLoginExplicit.getInstance();
            apploginexplicit.init(apploginexplicit.clientId, apploginexplicit.customUriScheme, this);
            String s = apploginexplicit.getDisplay();
            last = apploginexplicit.generateAuthorizationUri().toString();
            android.net.Uri.Builder builder = new android.net.Uri.Builder();
            Uri uri = Uri.parse(REGISTRATION);
            builder.scheme(uri.getScheme());
            builder.authority(uri.getAuthority());
            builder.path(uri.getPath());
            builder.appendQueryParameter(".src", src);
            builder.appendQueryParameter(".last", last);
            builder.appendQueryParameter(".display", s);
            builder.appendQueryParameter("ckey", apploginexplicit.clientId);
            YConnectLogger.debug(TAG, builder.build().toString());
            startActivity(new Intent("android.intent.action.VIEW", builder.build()));
            finish();
            return;
        }
        catch (Exception exception)
        {
            YConnectLogger.error(TAG, (new StringBuilder()).append("error=").append(exception.getMessage()).toString());
        }
    }


}

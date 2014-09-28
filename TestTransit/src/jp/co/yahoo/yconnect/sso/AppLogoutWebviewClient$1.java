// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.yconnect.sso;

import android.graphics.Bitmap;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import jp.co.yahoo.yconnect.core.util.YConnectLogger;

// Referenced classes of package jp.co.yahoo.yconnect.sso:
//            AppLogoutWebviewClient

class this._cls0 extends WebViewClient
{

    final AppLogoutWebviewClient this$0;

    public void onPageFinished(WebView webview, String s)
    {
        if (AppLogoutWebviewClient.access$100(AppLogoutWebviewClient.this) != null)
        {
            AppLogoutWebviewClient.access$300(AppLogoutWebviewClient.this, webview, AppLogoutWebviewClient.access$100(AppLogoutWebviewClient.this));
        }
        YConnectLogger.info(AppLogoutWebviewClient.access$000(), "has already logouted.");
        webview.stopLoading();
        AppLogoutWebviewClient.access$400(AppLogoutWebviewClient.this);
    }

    public void onPageStarted(WebView webview, String s, Bitmap bitmap)
    {
        YConnectLogger.debug(AppLogoutWebviewClient.access$000(), (new StringBuilder()).append("URL: ").append(s).toString());
        AppLogoutWebviewClient.access$102(AppLogoutWebviewClient.this, AppLogoutWebviewClient.access$200(AppLogoutWebviewClient.this));
        YConnectLogger.debug(AppLogoutWebviewClient.access$000(), "url: login.yahoo.co.jp");
        YConnectLogger.debug(AppLogoutWebviewClient.access$000(), (new StringBuilder()).append("cookie: ").append(AppLogoutWebviewClient.access$100(AppLogoutWebviewClient.this)).toString());
    }

    ()
    {
        this$0 = AppLogoutWebviewClient.this;
        super();
    }
}

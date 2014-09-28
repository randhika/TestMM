// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.yconnect.sso;

import android.graphics.Bitmap;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import jp.co.yahoo.yconnect.core.util.YConnectLogger;

// Referenced classes of package jp.co.yahoo.yconnect.sso:
//            AppLoginRefreshTokenActivity

class this._cls0 extends WebViewClient
{

    final AppLoginRefreshTokenActivity this$0;

    public void onPageFinished(WebView webview, String s)
    {
        if (s.startsWith(AppLoginRefreshTokenActivity.access$000(AppLoginRefreshTokenActivity.this)))
        {
            webview.stopLoading();
        }
    }

    public void onPageStarted(WebView webview, String s, Bitmap bitmap)
    {
        YConnectLogger.verbose(AppLoginRefreshTokenActivity.TAG, (new StringBuilder()).append("url=").append(s).toString());
        if (s.startsWith(AppLoginRefreshTokenActivity.access$100(AppLoginRefreshTokenActivity.this)))
        {
            YConnectLogger.info(AppLoginRefreshTokenActivity.TAG, "url is customUriScheme.");
            reqAppLoginExplicitAsyncTask(s);
        }
    }

    ()
    {
        this$0 = AppLoginRefreshTokenActivity.this;
        super();
    }
}

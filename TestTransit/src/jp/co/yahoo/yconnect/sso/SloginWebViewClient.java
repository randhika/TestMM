// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.yconnect.sso;

import android.graphics.Bitmap;
import android.net.Uri;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import jp.co.yahoo.yconnect.core.util.YConnectLogger;

// Referenced classes of package jp.co.yahoo.yconnect.sso:
//            YTcookieChecker, SloginClient, SloginErrorType

public class SloginWebViewClient extends WebViewClient
{

    private static final String TAG = jp/co/yahoo/yconnect/sso/SloginWebViewClient.getSimpleName();
    private SloginClient sloginClient;

    public SloginWebViewClient(SloginClient sloginclient)
    {
        sloginClient = sloginclient;
    }

    public void handleSloginComplete(WebView webview, String s)
    {
        if (YTcookieChecker.chkYTcookie())
        {
            YConnectLogger.info(TAG, "Y/Tcookie is exists.");
        } else
        {
            YConnectLogger.error(TAG, "Y/Tcookie is not exists.");
        }
        sloginClient.finish();
    }

    public void handleSloginError(WebView webview, String s)
    {
        String s1 = Uri.parse(s).getQueryParameter("code");
        YConnectLogger.error(TAG, (new StringBuilder()).append("error=").append(SloginErrorType.getError(s1)).append(" error_description=").append(SloginErrorType.getDescription(s1)).toString());
        sloginClient.finish();
    }

    public void onPageFinished(WebView webview, String s)
    {
        YConnectLogger.verbose(TAG, "onPageFinished ShowLoginViewActivity.");
        sloginClient.finish();
    }

    public void onPageStarted(WebView webview, String s, Bitmap bitmap)
    {
        if (s.startsWith("https://auth.login.yahoo.co.jp/yconnect/v1/slogin_complete"))
        {
            handleSloginComplete(webview, s);
        } else
        if (s.startsWith("https://auth.login.yahoo.co.jp/yconnect/v1/slogin_error"))
        {
            handleSloginError(webview, s);
            return;
        }
    }

}

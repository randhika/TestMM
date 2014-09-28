// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.yconnect.sso;

import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import jp.co.yahoo.yconnect.core.util.YConnectLogger;

// Referenced classes of package jp.co.yahoo.yconnect.sso:
//            ShowLoginViewActivity

class this._cls0 extends WebChromeClient
{

    final ShowLoginViewActivity this$0;

    public boolean onJsAlert(WebView webview, String s, String s1, JsResult jsresult)
    {
        YConnectLogger.debug(ShowLoginViewActivity.access$000(), (new StringBuilder()).append("onjsAlert:").append(s1).toString());
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

    ()
    {
        this$0 = ShowLoginViewActivity.this;
        super();
    }
}

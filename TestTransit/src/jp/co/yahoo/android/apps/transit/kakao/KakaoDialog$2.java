// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.kakao;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

// Referenced classes of package jp.co.yahoo.android.apps.transit.kakao:
//            KakaoDialog

class this._cls0 extends WebChromeClient
{

    final KakaoDialog this$0;

    public boolean onJsAlert(WebView webview, String s, String s1, final JsResult result)
    {
        (new android.app.ilder(getContext())).setMessage(s1).setPositiveButton(0x104000a, new android.content.DialogInterface.OnClickListener() {

            final KakaoDialog._cls2 this$1;
            final JsResult val$result;

            public void onClick(DialogInterface dialoginterface, int i)
            {
                result.confirm();
            }

            
            {
                this$1 = KakaoDialog._cls2.this;
                result = jsresult;
                super();
            }
        }).setCancelable(false).create().show();
        return true;
    }

    _cls1.val.result()
    {
        this$0 = KakaoDialog.this;
        super();
    }
}

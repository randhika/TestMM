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

class val.result
    implements android.content.OnClickListener
{

    final val.result this$1;
    final JsResult val$result;

    public void onClick(DialogInterface dialoginterface, int i)
    {
        val$result.confirm();
    }

    is._cls0()
    {
        this$1 = final__pcls0;
        val$result = JsResult.this;
        super();
    }

    // Unreferenced inner class jp/co/yahoo/android/apps/transit/kakao/KakaoDialog$2

/* anonymous class */
    class KakaoDialog._cls2 extends WebChromeClient
    {

        final KakaoDialog this$0;

        public boolean onJsAlert(WebView webview, String s, String s1, JsResult jsresult)
        {
            (new android.app.AlertDialog.Builder(getContext())).setMessage(s1).setPositiveButton(0x104000a, jsresult. new KakaoDialog._cls2._cls1()).setCancelable(false).create().show();
            return true;
        }

            
            {
                this$0 = KakaoDialog.this;
                super();
            }
    }

}

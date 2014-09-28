// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.kakao;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Iterator;
import java.util.List;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.json.JSONException;
import org.json.JSONObject;

// Referenced classes of package jp.co.yahoo.android.apps.transit.kakao:
//            Kakao

public class KakaoDialog extends Dialog
{

    private Kakao kakao;
    private Kakao.KakaoListener listener;
    private ProgressDialog mProgressDialog;
    private String mUrl;
    private WebView mWebView;
    private Kakao.KakaoTokenListener tokenListener;

    public KakaoDialog(Context context, String s, Kakao kakao1, Kakao.KakaoListener kakaolistener, Kakao.KakaoTokenListener kakaotokenlistener)
    {
        super(context, 0x1030009);
        mUrl = s;
        listener = kakaolistener;
        tokenListener = kakaotokenlistener;
        kakao = kakao1;
    }

    private void webViewRemoveJavascriptInterface()
    {
        mWebView.removeJavascriptInterface("searchBoxJavaBridge_");
    }

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        mWebView = new WebView(getContext());
        mWebView.setWebViewClient(new WebViewClient() {

            final KakaoDialog this$0;

            public void onPageFinished(WebView webview, String s)
            {
                super.onPageFinished(webview, s);
                if (mProgressDialog != null)
                {
                    mProgressDialog.dismiss();
                }
            }

            public void onPageStarted(WebView webview, String s, Bitmap bitmap)
            {
                super.onPageStarted(webview, s, bitmap);
                mProgressDialog = ProgressDialog.show(getContext(), null, "Loading...");
            }

            public void onReceivedError(WebView webview, int i, String s, String s1)
            {
                super.onReceivedError(webview, i, s, s1);
                if (mProgressDialog != null)
                {
                    mProgressDialog.dismiss();
                }
            }

            public boolean shouldOverrideUrlLoading(WebView webview, String s)
            {
                URI uri = new URI(s);
                URI uri1 = uri;
_L9:
                if (uri1 == null || !uri1.getHost().equals("appauth")) goto _L2; else goto _L1
_L1:
                JSONObject jsonobject;
                String s1;
                String s2;
                Iterator iterator;
                List list = URLEncodedUtils.parse(uri1, "UTF-8");
                jsonobject = new JSONObject();
                s1 = null;
                s2 = null;
                iterator = list.iterator();
_L4:
                NameValuePair namevaluepair;
                if (!iterator.hasNext())
                {
                    break; /* Loop/switch isn't completed */
                }
                namevaluepair = (NameValuePair)iterator.next();
                String s3;
                String s4;
                s3 = namevaluepair.getName();
                s4 = namevaluepair.getValue();
                jsonobject.put(s3, s4);
                if ("access_token".equals(s3))
                {
                    s1 = s4;
                    continue; /* Loop/switch isn't completed */
                }
                boolean flag = "refresh_token".equals(s3);
                if (flag)
                {
                    s2 = s4;
                }
                continue; /* Loop/switch isn't completed */
                JSONException jsonexception;
                jsonexception;
                Log.w("kakao-android-sdk", jsonexception);
                if (true) goto _L4; else goto _L3
_L3:
                kakao.setTokens(s1, s2);
                if (tokenListener == null) goto _L6; else goto _L5
_L5:
                tokenListener.onSetTokens(s1, s2);
_L7:
                if (listener != null)
                {
                    listener.onResult(Kakao.OK, jsonobject);
                }
                dismiss();
                return true;
_L6:
                if (Kakao.isLogging)
                {
                    Log.e("kakao-android-sdk", "KakaoTokenListener is not set. see Kakao.setTokenListener().");
                }
                if (true) goto _L7; else goto _L2
_L2:
                return super.shouldOverrideUrlLoading(webview, s);
                URISyntaxException urisyntaxexception;
                urisyntaxexception;
                uri1 = null;
                if (true) goto _L9; else goto _L8
_L8:
            }

            
            {
                this$0 = KakaoDialog.this;
                super();
            }
        });
        mWebView.setWebChromeClient(new WebChromeClient() {

            final KakaoDialog this$0;

            public boolean onJsAlert(WebView webview, String s, String s1, JsResult jsresult)
            {
                (new android.app.AlertDialog.Builder(getContext())).setMessage(s1).setPositiveButton(0x104000a, jsresult. new android.content.DialogInterface.OnClickListener() {

                    final _cls2 this$1;
                    final JsResult val$result;

                    public void onClick(DialogInterface dialoginterface, int i)
                    {
                        result.confirm();
                    }

            
            {
                this$1 = final__pcls2;
                result = JsResult.this;
                super();
            }
                }).setCancelable(false).create().show();
                return true;
            }

            
            {
                this$0 = KakaoDialog.this;
                super();
            }
        });
        mWebView.getSettings().setJavaScriptEnabled(true);
        if (android.os.Build.VERSION.SDK_INT >= 11 && android.os.Build.VERSION.SDK_INT <= 16)
        {
            webViewRemoveJavascriptInterface();
        }
        mWebView.loadUrl(mUrl);
        mWebView.setLayoutParams(new android.widget.FrameLayout.LayoutParams(-1, -1));
        addContentView(mWebView, new android.view.ViewGroup.LayoutParams(-1, -1));
    }

    public boolean onKeyDown(int i, KeyEvent keyevent)
    {
        if (i == 4)
        {
            if (mWebView.canGoBack())
            {
                mWebView.goBack();
                return true;
            }
            listener.onResult(new Kakao.Status(false, "user canceled", null), null);
        }
        return super.onKeyDown(i, keyevent);
    }






/*
    static ProgressDialog access$302(KakaoDialog kakaodialog, ProgressDialog progressdialog)
    {
        kakaodialog.mProgressDialog = progressdialog;
        return progressdialog;
    }

*/
}

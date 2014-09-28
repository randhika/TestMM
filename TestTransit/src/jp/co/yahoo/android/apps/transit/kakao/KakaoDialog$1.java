// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.kakao;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.util.Log;
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
//            KakaoDialog, Kakao

class this._cls0 extends WebViewClient
{

    final KakaoDialog this$0;

    public void onPageFinished(WebView webview, String s)
    {
        super.onPageFinished(webview, s);
        if (KakaoDialog.access$300(KakaoDialog.this) != null)
        {
            KakaoDialog.access$300(KakaoDialog.this).dismiss();
        }
    }

    public void onPageStarted(WebView webview, String s, Bitmap bitmap)
    {
        super.onPageStarted(webview, s, bitmap);
        KakaoDialog.access$302(KakaoDialog.this, ProgressDialog.show(getContext(), null, "Loading..."));
    }

    public void onReceivedError(WebView webview, int i, String s, String s1)
    {
        super.onReceivedError(webview, i, s, s1);
        if (KakaoDialog.access$300(KakaoDialog.this) != null)
        {
            KakaoDialog.access$300(KakaoDialog.this).dismiss();
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
        KakaoDialog.access$000(KakaoDialog.this).setTokens(s1, s2);
        if (KakaoDialog.access$100(KakaoDialog.this) == null) goto _L6; else goto _L5
_L5:
        KakaoDialog.access$100(KakaoDialog.this).onSetTokens(s1, s2);
_L7:
        if (KakaoDialog.access$200(KakaoDialog.this) != null)
        {
            KakaoDialog.access$200(KakaoDialog.this).onResult(Kakao.OK, jsonobject);
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

    tener()
    {
        this$0 = KakaoDialog.this;
        super();
    }
}

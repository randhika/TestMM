// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.ads;

import android.webkit.WebSettings;
import android.webkit.WebView;
import java.util.concurrent.atomic.AtomicBoolean;

// Referenced classes of package jp.co.yahoo.android.ads:
//            CreateAdLayoutDisplay, AdViewForInstance, r, AdContainer, 
//            a, h

class a
    implements Runnable
{

    final CreateAdLayoutDisplay a;

    public void run()
    {
        CreateAdLayoutDisplay.c(a, new WebView(CreateAdLayoutDisplay.c(a)));
        AdViewForInstance.callWebViewResumeTimers(CreateAdLayoutDisplay.k(a), AdViewForInstance.getNeedWebViewResumeTimers());
        WebSettings websettings = CreateAdLayoutDisplay.k(a).getSettings();
        websettings.setJavaScriptEnabled(true);
        websettings.setCacheMode(1);
        (new r()).a(websettings);
        CreateAdLayoutDisplay.k(a).setFocusable(false);
        CreateAdLayoutDisplay.k(a).setVerticalScrollbarOverlay(true);
        CreateAdLayoutDisplay.k(a).setVerticalScrollBarEnabled(false);
        CreateAdLayoutDisplay.k(a).setHorizontalScrollbarOverlay(true);
        CreateAdLayoutDisplay.k(a).setHorizontalScrollBarEnabled(false);
        android.widget.ams ams = new android.widget.ams(-1, (int)CreateAdLayoutDisplay.o(a));
        ams.addRule(3, 25);
        CreateAdLayoutDisplay.k(a).setLayoutParams(ams);
        CreateAdLayoutDisplay.k(a).setInitialScale((int)(100D * CreateAdLayoutDisplay.e(a).getAdRatio()));
        StringBuffer stringbuffer = new StringBuffer();
        if (!h.d(CreateAdLayoutDisplay.b(a).h()[0]))
        {
            stringbuffer.append((new StringBuilder()).append("clickTAG=").append(h.b(CreateAdLayoutDisplay.b(a).h()[0], "utf-8")).append("&amp;targetTAG=_top").toString());
        }
        if (!h.d(CreateAdLayoutDisplay.b(a).h()[1]))
        {
            stringbuffer.append((new StringBuilder()).append("&amp;clickTAG2=").append(h.b(CreateAdLayoutDisplay.b(a).h()[1], "utf-8")).append("&amp;targetTAG2=_top").toString());
        }
        if (!h.d(CreateAdLayoutDisplay.b(a).h()[2]))
        {
            stringbuffer.append((new StringBuilder()).append("&amp;clickTAG3=").append(h.b(CreateAdLayoutDisplay.b(a).h()[2], "utf-8")).append("&amp;targetTAG3=_top").toString());
        }
        if (!h.d(CreateAdLayoutDisplay.b(a).h()[3]))
        {
            stringbuffer.append((new StringBuilder()).append("&amp;clickTAG4=").append(h.b(CreateAdLayoutDisplay.b(a).h()[3], "utf-8")).append("&amp;targetTAG4=_top").toString());
        }
        if (!h.d(CreateAdLayoutDisplay.b(a).h()[4]))
        {
            stringbuffer.append((new StringBuilder()).append("&amp;clickTAG5=").append(h.b(CreateAdLayoutDisplay.b(a).h()[4], "utf-8")).append("&amp;targetTAG5=_top").toString());
        }
        StringBuffer stringbuffer1 = new StringBuffer();
        stringbuffer1.append("<div id=\"yahoo_rma_expand_flash_wrapper\">");
        stringbuffer1.append("<object id=\"yahoo_rma_expand_flash\" classid=\"clsid:D27CDB6E-AE6D-11cf-96B8-444553540000\" style=\"width:100%;height:100%;\">");
        if (stringbuffer.length() != 0)
        {
            stringbuffer1.append((new StringBuilder()).append("<param name=\"FlashVars\" value=\"").append(stringbuffer.toString()).append("\">").toString());
        }
        stringbuffer1.append((new StringBuilder()).append("<param name=\"movie\" value=\"").append(CreateAdLayoutDisplay.b(a).p()).append("\">").toString());
        stringbuffer1.append("<param name=\"loop\" value=\"true\">");
        stringbuffer1.append("<param name=\"quality\" value=\"high\">");
        stringbuffer1.append("<param name=\"allowScriptAccess\" value=\"always\">");
        stringbuffer1.append("<param name=\"wmode\" value=\"transparent\">");
        stringbuffer1.append((new StringBuilder()).append("<embed id=\"yahoo_rma_expand_img\" style=\"width:100%;height:100%;\" src=\"").append(CreateAdLayoutDisplay.b(a).p()).append("\" loop=\"true\" quality=\"high\" type=\"application/x-shockwave-flash\" ").append("allowscriptaccess=\"always\" swliveconnect=\"false\" wmode=\"transparent\"").toString());
        String s;
        if (stringbuffer.length() != 0)
        {
            stringbuffer1.append((new StringBuilder()).append(" flashvars=\"").append(stringbuffer.toString()).append("\">").toString());
        } else
        {
            stringbuffer1.append(">");
        }
        stringbuffer1.append("</object></div>");
        s = "<!DOCTYPE html><html><head><meta http-equiv=\"content-type\" content=\"text/html; charset=UTF-8\"><meta name=\"viewport\" content=\"width=device-width,initial-scale=1.0,minimum-scale=1.0,maximum-scale=1.0,user-scalable=0\"/><style type=\"text/css\">body {margin:0px;}</style><script language=\"JavaScript\">window.onload = function(){var swf = document.getElementById(\"yahoo_rma_expand_flash\"); if(swf){ swf.focus(); }};</script></head><body>#contents#</body></html>".replaceAll("#contents#", stringbuffer1.toString());
        h.a(3, (new StringBuilder()).append("flash html : ").append(s).toString());
        CreateAdLayoutDisplay.k(a).loadDataWithBaseURL(null, s, "text/html", "utf-8", null);
        CreateAdLayoutDisplay.k(a).setId(21);
        CreateAdLayoutDisplay.p(a).set(true);
    }

    (CreateAdLayoutDisplay createadlayoutdisplay)
    {
        a = createadlayoutdisplay;
        super();
    }
}

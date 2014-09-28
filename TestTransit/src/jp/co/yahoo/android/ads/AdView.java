// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.ads;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import java.util.Map;

// Referenced classes of package jp.co.yahoo.android.ads:
//            i, h, e, AdViewListener, 
//            AdResponse

public class AdView extends i
{
    class a
        implements AdViewListener
    {

        final AdView a;

        public void onAdViewActivityEnd(Map map)
        {
            h.a(3, "Adview program end listener");
            AdResponse adresponse = (AdResponse)map.get(a.n);
            h.a(3, (new StringBuilder()).append("[").append(a.n).append(" RESPONSE]").toString());
            h.a(3, (new StringBuilder()).append("Adview response. code : ").append(adresponse.getCode()).append(" message : ").append(adresponse.getMessage()).toString());
            if (adresponse.getAdlayout() != null)
            {
                a.addView(adresponse.getAdlayout());
                a.setVisibility(0);
            }
        }

        public void onAdViewActivityStart()
        {
        }

        a()
        {
            a = AdView.this;
            super();
        }
    }


    private String n;

    public AdView(Context context)
    {
        this(context, null, 0);
        if (Log.isLoggable("YJAdsSDK", 3))
        {
            Log.d("YJAdsSDK", "Not from a layout XML file");
        }
    }

    public AdView(Context context, AttributeSet attributeset)
    {
        this(context, attributeset, 0);
        if (Log.isLoggable("YJAdsSDK", 3))
        {
            Log.d("YJAdsSDK", "View from a layout XML file");
        }
    }

    public AdView(Context context, AttributeSet attributeset, int j)
    {
        super(context, attributeset, j);
        h.a(3, "View from a layout XML file defStyle");
        if (attributeset != null)
        {
            String s = (new StringBuilder()).append("http://schemas.android.com/apk/res/").append(context.getPackageName()).toString();
            h.a(3, (new StringBuilder()).append("Namespace :").append(s).toString());
            h.a(3, (new StringBuilder()).append("AttributeSet arrts :").append(attributeset).toString());
            e.a(attributeset.getAttributeBooleanValue(s, "testing", false));
            a = attributeset.getAttributeValue(s, "appli_id");
            String s1 = attributeset.getAttributeValue(s, "apos");
            b = s1;
            n = s1;
            c = attributeset.getAttributeValue(s, "spaceid");
            if (b.indexOf(",") != -1)
            {
                h.a(5, (new StringBuilder()).append("adViewForXML not ads lookup : ").append(b).toString());
                b = "";
                n = "";
            }
            d = false;
        }
        super.f = new a();
        requestFreshAd();
    }

}

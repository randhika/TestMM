// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.ads;

import java.util.Map;

// Referenced classes of package jp.co.yahoo.android.ads:
//            AdViewListener, h, AdView, AdResponse

class a
    implements AdViewListener
{

    final AdView a;

    public void onAdViewActivityEnd(Map map)
    {
        h.a(3, "Adview program end listener");
        AdResponse adresponse = (AdResponse)map.get(AdView.access$000(a));
        h.a(3, (new StringBuilder()).append("[").append(AdView.access$000(a)).append(" RESPONSE]").toString());
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

    e(AdView adview)
    {
        a = adview;
        super();
    }
}

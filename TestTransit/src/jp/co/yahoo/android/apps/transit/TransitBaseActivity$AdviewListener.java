// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit;

import android.widget.RelativeLayout;
import java.util.Map;
import jp.co.yahoo.android.ads.AdResponse;
import jp.co.yahoo.android.ads.AdViewListener;

// Referenced classes of package jp.co.yahoo.android.apps.transit:
//            TransitBaseActivity

class this._cls0
    implements AdViewListener
{

    final TransitBaseActivity this$0;

    private void sedAdLayout(AdResponse adresponse, RelativeLayout relativelayout)
    {
        if (adresponse != null && relativelayout != null && adresponse.getCode() == "200")
        {
            relativelayout.removeAllViews();
            relativelayout.setPadding(0, 2, 0, 2);
            relativelayout.addView(adresponse.getAdlayout());
        }
    }

    public void onAdViewActivityEnd(Map map)
    {
        if (map.containsKey("pv"))
        {
            sedAdLayout((AdResponse)map.get("pv"), (RelativeLayout)findViewById(0x7f0a006f));
        }
        if (map.containsKey("bottom"))
        {
            sedAdLayout((AdResponse)map.get("bottom"), (RelativeLayout)findViewById(0x7f0a0070));
        }
        if (map.containsKey("top"))
        {
            sedAdLayout((AdResponse)map.get("top"), (RelativeLayout)findViewById(0x7f0a0060));
        }
    }

    public void onAdViewActivityStart()
    {
    }

    I()
    {
        this$0 = TransitBaseActivity.this;
        super();
    }
}

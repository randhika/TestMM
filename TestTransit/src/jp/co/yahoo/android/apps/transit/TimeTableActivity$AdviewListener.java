// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit;

import android.widget.RelativeLayout;
import java.util.Map;
import jp.co.yahoo.android.ads.AdResponse;
import jp.co.yahoo.android.ads.AdViewListener;
import jp.co.yahoo.android.apps.transit.viewparts.TimeTableBaseListView;

// Referenced classes of package jp.co.yahoo.android.apps.transit:
//            TimeTableActivity

class this._cls0
    implements AdViewListener
{

    final TimeTableActivity this$0;

    private void setAdLayout(AdResponse adresponse, RelativeLayout relativelayout)
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
        TimeTableActivity.access$2802(TimeTableActivity.this, map);
        if (map.containsKey("pv"))
        {
            AdResponse adresponse2 = (AdResponse)map.get("pv");
            if (TimeTableActivity.access$2900(TimeTableActivity.this) != null)
            {
                setAdLayout(adresponse2, (RelativeLayout)TimeTableActivity.access$2900(TimeTableActivity.this).findViewById(0x7f0a006f));
            }
        }
        if (map.containsKey("bottom"))
        {
            AdResponse adresponse1 = (AdResponse)map.get("bottom");
            if (TimeTableActivity.access$2900(TimeTableActivity.this) != null)
            {
                setAdLayout(adresponse1, (RelativeLayout)TimeTableActivity.access$2900(TimeTableActivity.this).findViewById(0x7f0a0070));
            }
        }
        if (map.containsKey("top"))
        {
            AdResponse adresponse = (AdResponse)map.get("top");
            if (TimeTableActivity.access$2900(TimeTableActivity.this) != null)
            {
                setAdLayout(adresponse, (RelativeLayout)TimeTableActivity.access$2900(TimeTableActivity.this).findViewById(0x7f0a0060));
            }
        }
    }

    public void onAdViewActivityStart()
    {
    }

    ()
    {
        this$0 = TimeTableActivity.this;
        super();
    }
}

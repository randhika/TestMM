// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit;

import android.view.View;
import jp.co.yahoo.android.apps.transit.viewparts.RidingPositionResultView;

// Referenced classes of package jp.co.yahoo.android.apps.transit:
//            RidingPositionActivity

class this._cls0
    implements android.widget.
{

    final RidingPositionActivity this$0;

    public View createTabContent(String s)
    {
        RidingPositionActivity.access$002(RidingPositionActivity.this, new RidingPositionResultView(RidingPositionActivity.access$100(RidingPositionActivity.this)));
        RidingPositionActivity.access$000(RidingPositionActivity.this).setData(RidingPositionActivity.access$200(RidingPositionActivity.this), RidingPositionActivity.access$300(RidingPositionActivity.this), RidingPositionActivity.access$400(RidingPositionActivity.this), RidingPositionActivity.access$500(RidingPositionActivity.this));
        RidingPositionActivity.access$000(RidingPositionActivity.this).setTabView(Integer.valueOf(s).intValue());
        return RidingPositionActivity.access$000(RidingPositionActivity.this);
    }

    esultView()
    {
        this$0 = RidingPositionActivity.this;
        super();
    }
}

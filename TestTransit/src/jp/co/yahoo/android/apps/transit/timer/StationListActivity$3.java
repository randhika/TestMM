// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.timer;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import jp.co.yahoo.android.apps.transit.timer.api.data.StationData;
import jp.co.yahoo.android.apps.transit.timer.util.CountdownUtil;

// Referenced classes of package jp.co.yahoo.android.apps.transit.timer:
//            StationListActivity

class this._cls0
    implements android.widget.kListener
{

    final StationListActivity this$0;

    public void onItemClick(AdapterView adapterview, View view, int i, long l)
    {
        StationListActivity.access$202(StationListActivity.this, (StationData)StationListActivity.access$000(StationListActivity.this).getSerializable(String.valueOf(i)));
        if (CountdownUtil.isEmpty(StationListActivity.access$200(StationListActivity.this).getStationId()))
        {
            StationListActivity.access$300(StationListActivity.this, StationListActivity.access$200(StationListActivity.this).getName());
            return;
        } else
        {
            StationListActivity.access$400(StationListActivity.this);
            return;
        }
    }

    ()
    {
        this$0 = StationListActivity.this;
        super();
    }
}

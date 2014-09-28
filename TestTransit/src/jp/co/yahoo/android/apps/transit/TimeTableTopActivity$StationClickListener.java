// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit;

import android.view.View;
import jp.co.yahoo.android.apps.transit.api.data.StationData;
import jp.co.yahoo.android.apps.transit.common.util.TransitUtil;

// Referenced classes of package jp.co.yahoo.android.apps.transit:
//            TimeTableTopActivity

class type
    implements android.view.tationClickListener
{

    final TimeTableTopActivity this$0;
    int type;

    public void onClick(View view)
    {
        if (type == 0)
        {
            touchTapRD(getString(0x7f0d040d));
        } else
        if (type == 1)
        {
            touchTapRD(getString(0x7f0d03e8));
        }
        TimeTableTopActivity.access$702(TimeTableTopActivity.this, (StationData)view.getTag());
        if (TransitUtil.isEmpty(TimeTableTopActivity.access$700(TimeTableTopActivity.this).getId()))
        {
            TimeTableTopActivity.access$800(TimeTableTopActivity.this, TimeTableTopActivity.access$700(TimeTableTopActivity.this).getName());
            return;
        } else
        {
            TimeTableTopActivity.access$900(TimeTableTopActivity.this, TimeTableTopActivity.access$700(TimeTableTopActivity.this));
            return;
        }
    }

    public (int i)
    {
        this$0 = TimeTableTopActivity.this;
        super();
        type = i;
    }
}

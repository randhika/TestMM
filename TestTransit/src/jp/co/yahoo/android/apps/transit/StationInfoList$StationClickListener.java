// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit;

import android.view.View;
import jp.co.yahoo.android.apps.transit.api.data.StationData;
import jp.co.yahoo.android.apps.transit.common.util.TransitUtil;

// Referenced classes of package jp.co.yahoo.android.apps.transit:
//            StationInfoList

class this._cls0
    implements android.view.tationClickListener
{

    final StationInfoList this$0;

    public void onClick(View view)
    {
        StationInfoList.access$402(StationInfoList.this, (StationData)view.getTag());
        if (TransitUtil.isEmpty(StationInfoList.access$400(StationInfoList.this).getId()))
        {
            StationInfoList.access$600(StationInfoList.this, StationInfoList.access$400(StationInfoList.this).getName());
            return;
        } else
        {
            StationInfoList.access$500(StationInfoList.this);
            return;
        }
    }

    ()
    {
        this$0 = StationInfoList.this;
        super();
    }
}

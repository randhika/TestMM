// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.timer;

import android.os.Bundle;
import jp.co.yahoo.android.apps.transit.timer.api.data.StationData;
import jp.co.yahoo.android.yolp.common.ApiBase;

// Referenced classes of package jp.co.yahoo.android.apps.transit.timer:
//            StationListActivity

class this._cls0
    implements jp.co.yahoo.android.yolp.common.vity._cls2
{

    final StationListActivity this$0;

    public boolean endApi(ApiBase apibase, Object obj)
    {
        Bundle bundle = (Bundle)apibase.getResult();
        if (bundle != null)
        {
            if (bundle.containsKey("0"))
            {
                StationData stationdata = (StationData)bundle.getSerializable("0");
                StationListActivity.access$200(StationListActivity.this).setStationId(stationdata.getStationId());
            } else
            {
                showErrorMessageDialog(getString(0x7f0d012e), getString(0x7f0d014f));
            }
        } else
        {
            showErrorMessageDialog(getString(0x7f0d010a), getString(0x7f0d014f));
        }
        return false;
    }

    ()
    {
        this$0 = StationListActivity.this;
        super();
    }
}

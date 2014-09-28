// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit;

import android.os.Bundle;
import jp.co.yahoo.android.apps.transit.api.ApiBase;
import jp.co.yahoo.android.apps.transit.api.StationSearch;
import jp.co.yahoo.android.apps.transit.api.data.APIError;
import jp.co.yahoo.android.apps.transit.api.data.StationData;

// Referenced classes of package jp.co.yahoo.android.apps.transit:
//            TimeTableTopActivity

class this._cls0
    implements jp.co.yahoo.android.apps.transit.api.._cls0
{

    final TimeTableTopActivity this$0;

    public boolean onCanceled()
    {
        TimeTableTopActivity.access$900(TimeTableTopActivity.this, TimeTableTopActivity.access$700(TimeTableTopActivity.this));
        return false;
    }

    public boolean onError(APIError apierror)
    {
        TimeTableTopActivity.access$900(TimeTableTopActivity.this, TimeTableTopActivity.access$700(TimeTableTopActivity.this));
        return false;
    }

    public boolean onSuccess(ApiBase apibase, Object obj)
    {
        Bundle bundle = TimeTableTopActivity.access$1000(TimeTableTopActivity.this).getResults();
        if (bundle != null && bundle.containsKey("0"))
        {
            StationData stationdata = (StationData)bundle.getSerializable("0");
            TimeTableTopActivity.access$700(TimeTableTopActivity.this).setId(stationdata.getId());
        }
        TimeTableTopActivity.access$900(TimeTableTopActivity.this, TimeTableTopActivity.access$700(TimeTableTopActivity.this));
        return false;
    }

    ()
    {
        this$0 = TimeTableTopActivity.this;
        super();
    }
}

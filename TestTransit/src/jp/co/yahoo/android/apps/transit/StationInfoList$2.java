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
//            StationInfoList

class this._cls0
    implements jp.co.yahoo.android.apps.transit.api.r
{

    final StationInfoList this$0;

    public boolean onCanceled()
    {
        StationInfoList.access$500(StationInfoList.this);
        return false;
    }

    public boolean onError(APIError apierror)
    {
        StationInfoList.access$500(StationInfoList.this);
        return false;
    }

    public boolean onSuccess(ApiBase apibase, Object obj)
    {
        Bundle bundle = StationInfoList.access$100(StationInfoList.this).getResults();
        if (bundle != null && bundle.containsKey("0"))
        {
            StationData stationdata = (StationData)bundle.getSerializable("0");
            StationInfoList.access$400(StationInfoList.this).setId(stationdata.getId());
        }
        StationInfoList.access$500(StationInfoList.this);
        return false;
    }

    ta()
    {
        this$0 = StationInfoList.this;
        super();
    }
}

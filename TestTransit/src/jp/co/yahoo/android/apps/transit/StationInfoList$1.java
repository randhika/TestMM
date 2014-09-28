// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit;

import android.content.Intent;
import android.os.Bundle;
import jp.co.yahoo.android.apps.transit.api.ApiBase;
import jp.co.yahoo.android.apps.transit.api.StationSearch;
import jp.co.yahoo.android.apps.transit.api.data.APIError;

// Referenced classes of package jp.co.yahoo.android.apps.transit:
//            StationInfoList

class this._cls0
    implements jp.co.yahoo.android.apps.transit.api.r
{

    final StationInfoList this$0;

    public boolean onCanceled()
    {
        return false;
    }

    public boolean onError(APIError apierror)
    {
        StationInfoList.access$300(StationInfoList.this);
        return false;
    }

    public boolean onSuccess(ApiBase apibase, Object obj)
    {
        StationInfoList.access$002(StationInfoList.this, StationInfoList.access$100(StationInfoList.this).getResults());
        if (StationInfoList.access$000(StationInfoList.this) == null || StationInfoList.access$000(StationInfoList.this).size() < 1)
        {
            Intent intent = new Intent();
            intent.putExtra(getString(0x7f0d01cd), getString(0x7f0d012e));
            setResult(0, intent);
            finish();
        }
        StationInfoList.access$200(StationInfoList.this);
        return false;
    }

    tener()
    {
        this$0 = StationInfoList.this;
        super();
    }
}

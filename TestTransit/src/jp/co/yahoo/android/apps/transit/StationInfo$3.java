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
//            StationInfo

class this._cls0
    implements jp.co.yahoo.android.apps.transit.api.tener
{

    final StationInfo this$0;

    public boolean onCanceled()
    {
        StationInfo.access$300(StationInfo.this);
        return false;
    }

    public boolean onError(APIError apierror)
    {
        StationInfo.access$300(StationInfo.this);
        return false;
    }

    public boolean onSuccess(ApiBase apibase, Object obj)
    {
        Bundle bundle = StationInfo.access$100(StationInfo.this).getResults();
        if (bundle != null && bundle.size() > 0)
        {
            StationInfo.access$202(StationInfo.this, (StationData)bundle.getSerializable("0"));
        }
        StationInfo.access$300(StationInfo.this);
        return false;
    }

    ror()
    {
        this$0 = StationInfo.this;
        super();
    }
}

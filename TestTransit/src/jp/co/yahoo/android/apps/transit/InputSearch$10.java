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
//            InputSearch

class this._cls0
    implements jp.co.yahoo.android.apps.transit.api.ener
{

    final InputSearch this$0;

    public boolean onCanceled()
    {
        InputSearch.access$400(InputSearch.this);
        return false;
    }

    public boolean onError(APIError apierror)
    {
        InputSearch.access$400(InputSearch.this);
        return false;
    }

    public boolean onSuccess(ApiBase apibase, Object obj)
    {
        Bundle bundle = InputSearch.access$2200(InputSearch.this).getResults();
        if (bundle != null && bundle.containsKey("0"))
        {
            StationData stationdata = (StationData)bundle.getSerializable("0");
            InputSearch.access$200(InputSearch.this).setId(stationdata.getId());
            InputSearch.access$200(InputSearch.this).setLat(stationdata.getLat());
            InputSearch.access$200(InputSearch.this).setLon(stationdata.getLon());
        }
        InputSearch.access$400(InputSearch.this);
        return false;
    }

    or()
    {
        this$0 = InputSearch.this;
        super();
    }
}

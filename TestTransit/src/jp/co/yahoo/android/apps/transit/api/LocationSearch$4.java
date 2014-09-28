// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.api;

import android.app.ProgressDialog;
import android.os.Bundle;
import jp.co.yahoo.android.apps.transit.api.data.APIError;

// Referenced classes of package jp.co.yahoo.android.apps.transit.api:
//            LocationSearch, StationSearch, ApiBase

class this._cls0
    implements er
{

    final LocationSearch this$0;

    public boolean onCanceled()
    {
        return false;
    }

    public boolean onError(APIError apierror)
    {
        if (LocationSearch.access$400(LocationSearch.this) != null && LocationSearch.access$400(LocationSearch.this).isShowing())
        {
            LocationSearch.access$400(LocationSearch.this).dismiss();
        }
        if (bCanceled || bTimeout)
        {
            return false;
        } else
        {
            LocationSearch.this.onError(LocationSearch.access$700(LocationSearch.this, 0x7f0d01d1), LocationSearch.access$700(LocationSearch.this, 0x7f0d010a));
            return false;
        }
    }

    public boolean onSuccess(ApiBase apibase, Object obj)
    {
        if (LocationSearch.access$400(LocationSearch.this) != null && LocationSearch.access$400(LocationSearch.this).isShowing())
        {
            LocationSearch.access$400(LocationSearch.this).dismiss();
        }
        if (!bCanceled && !bTimeout)
        {
            Bundle bundle = LocationSearch.access$500(LocationSearch.this).getResults();
            if (LocationSearch.access$900(LocationSearch.this) != null)
            {
                Bundle bundle1 = new Bundle();
                bundle1.putBundle(LocationSearch.access$700(LocationSearch.this, 0x7f0d0240), bundle);
                LocationSearch.access$900(LocationSearch.this).onSuccess(LocationSearch.access$700(LocationSearch.this, 0x7f0d01d1), bundle1);
                return false;
            }
        }
        return false;
    }

    cationSearchlListener()
    {
        this$0 = LocationSearch.this;
        super();
    }
}

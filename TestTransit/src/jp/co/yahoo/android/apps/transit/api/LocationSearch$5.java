// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.api;

import android.app.ProgressDialog;
import android.os.Bundle;
import jp.co.yahoo.android.apps.transit.api.data.APIError;

// Referenced classes of package jp.co.yahoo.android.apps.transit.api:
//            LocationSearch, RevGeocoder, ApiBase

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
            LocationSearch.this.onError(LocationSearch.access$700(LocationSearch.this, 0x7f0d01d0), LocationSearch.access$700(LocationSearch.this, 0x7f0d00b2));
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
            Bundle bundle = LocationSearch.access$600(LocationSearch.this).getResults();
            if (bundle == null || !bundle.containsKey(LocationSearch.access$700(LocationSearch.this, 0x7f0d01a2)))
            {
                LocationSearch.this.onError(LocationSearch.access$700(LocationSearch.this, 0x7f0d01d0), LocationSearch.access$700(LocationSearch.this, 0x7f0d00b2));
            }
            String as[] = bundle.getStringArray(LocationSearch.access$700(LocationSearch.this, 0x7f0d01a2));
            if (as == null || as.length < 1)
            {
                LocationSearch.this.onError(LocationSearch.access$700(LocationSearch.this, 0x7f0d01d0), LocationSearch.access$700(LocationSearch.this, 0x7f0d00b2));
            }
            if (LocationSearch.access$900(LocationSearch.this) != null)
            {
                Bundle bundle1 = new Bundle();
                bundle1.putInt(LocationSearch.access$700(LocationSearch.this, 0x7f0d01ce), 0x7f0c0078);
                bundle1.putString(LocationSearch.access$700(LocationSearch.this, 0x7f0d01cf), LocationSearch.access$700(LocationSearch.this, 0x7f0d01d0));
                bundle1.putString(LocationSearch.access$700(LocationSearch.this, 0x7f0d01a2), as[0]);
                bundle1.putString(LocationSearch.access$700(LocationSearch.this, 0x7f0d01a4), LocationSearch.access$600(LocationSearch.this).getParameter("lon"));
                bundle1.putString(LocationSearch.access$700(LocationSearch.this, 0x7f0d01a3), LocationSearch.access$600(LocationSearch.this).getParameter("lat"));
                LocationSearch.access$900(LocationSearch.this).onSuccess(LocationSearch.access$700(LocationSearch.this, 0x7f0d01d0), bundle1);
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

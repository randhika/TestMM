// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit;

import android.os.Bundle;
import jp.co.yahoo.android.apps.transit.api.data.APIError;

// Referenced classes of package jp.co.yahoo.android.apps.transit:
//            TransitStationSearchActivity

class this._cls0
    implements jp.co.yahoo.android.apps.transit.api.._cls0
{

    final TransitStationSearchActivity this$0;

    public boolean onCanceled()
    {
        return false;
    }

    public boolean onError(APIError apierror)
    {
        showErrorMessageDialog(apierror.getMessage(), getString(0x7f0d014f));
        return false;
    }

    public boolean onSuccess(Bundle bundle)
    {
        if (bundle == null || bundle.size() < 1)
        {
            showErrorMessageDialog(getString(0x7f0d0128), getString(0x7f0d014f));
            return false;
        } else
        {
            TransitStationSearchActivity.access$100(TransitStationSearchActivity.this, bundle, null, TransitStationSearchActivity.access$000(TransitStationSearchActivity.this));
            return false;
        }
    }

    public volatile boolean onSuccess(Object obj)
    {
        return onSuccess((Bundle)obj);
    }

    ()
    {
        this$0 = TransitStationSearchActivity.this;
        super();
    }
}

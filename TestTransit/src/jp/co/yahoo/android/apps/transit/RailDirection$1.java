// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit;

import android.os.Bundle;
import jp.co.yahoo.android.apps.transit.api.ApiBase;
import jp.co.yahoo.android.apps.transit.api.DirectionSearch;
import jp.co.yahoo.android.apps.transit.api.data.APIError;

// Referenced classes of package jp.co.yahoo.android.apps.transit:
//            RailDirection

class this._cls0
    implements jp.co.yahoo.android.apps.transit.api.ner
{

    final RailDirection this$0;

    public boolean onCanceled()
    {
        return false;
    }

    public boolean onError(APIError apierror)
    {
        showErrorMessageDialog(getString(0x7f0d0106), getString(0x7f0d014f));
        return false;
    }

    public boolean onSuccess(ApiBase apibase, Object obj)
    {
        Bundle bundle = ((DirectionSearch)apibase).getResults();
        if (bundle == null || bundle.size() < 1)
        {
            showErrorMessageDialog(getString(0x7f0d0130), getString(0x7f0d014f));
        } else
        {
            RailDirection.access$000(RailDirection.this, bundle);
        }
        return false;
    }

    r()
    {
        this$0 = RailDirection.this;
        super();
    }
}

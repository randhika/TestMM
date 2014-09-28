// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit;

import jp.co.yahoo.android.apps.transit.api.ApiBase;
import jp.co.yahoo.android.apps.transit.api.DiainfoSearch;
import jp.co.yahoo.android.apps.transit.api.data.APIError;

// Referenced classes of package jp.co.yahoo.android.apps.transit:
//            DiainfoAreaListActivity

class this._cls0
    implements jp.co.yahoo.android.apps.transit.api.._cls0
{

    final DiainfoAreaListActivity this$0;

    public boolean onCanceled()
    {
        return false;
    }

    public boolean onError(APIError apierror)
    {
        showErrorMessageDialog(apierror.getMessage(), getString(0x7f0d014f));
        return false;
    }

    public boolean onSuccess(ApiBase apibase, Object obj)
    {
        showAreaList(DiainfoAreaListActivity.access$000(DiainfoAreaListActivity.this).getResult());
        return false;
    }

    ()
    {
        this$0 = DiainfoAreaListActivity.this;
        super();
    }
}

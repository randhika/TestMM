// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit;

import jp.co.yahoo.android.apps.transit.api.ApiBase;
import jp.co.yahoo.android.apps.transit.api.DiainfoSearch;
import jp.co.yahoo.android.apps.transit.api.data.APIError;

// Referenced classes of package jp.co.yahoo.android.apps.transit:
//            TransitBaseActivity

class this._cls0
    implements jp.co.yahoo.android.apps.transit.api.._cls0
{

    final TransitBaseActivity this$0;

    public boolean onCanceled()
    {
        setDiainfo(false);
        return false;
    }

    public boolean onError(APIError apierror)
    {
        setDiainfo(false);
        return false;
    }

    public boolean onSuccess(ApiBase apibase, Object obj)
    {
        chkDiainfo = ((DiainfoSearch)apibase).getResult();
        if (chkDiainfo != null && chkRegistRail != null)
        {
            chkDiainfoDetail();
            setHumbergarIcon();
        }
        return false;
    }

    ()
    {
        this$0 = TransitBaseActivity.this;
        super();
    }
}

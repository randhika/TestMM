// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit;

import jp.co.yahoo.android.apps.transit.api.ApiBase;
import jp.co.yahoo.android.apps.transit.api.DiainfoSearch;
import jp.co.yahoo.android.apps.transit.api.data.APIError;

// Referenced classes of package jp.co.yahoo.android.apps.transit:
//            DiainfoActivity

class this._cls0
    implements jp.co.yahoo.android.apps.transit.api.r
{

    final DiainfoActivity this$0;

    public boolean onCanceled()
    {
        DiainfoActivity.access$002(DiainfoActivity.this, true);
        setCompleted();
        return false;
    }

    public boolean onError(APIError apierror)
    {
        DiainfoActivity.access$002(DiainfoActivity.this, true);
        DiainfoActivity.access$202(DiainfoActivity.this, true);
        setCompleted();
        return false;
    }

    public boolean onSuccess(ApiBase apibase, Object obj)
    {
        DiainfoActivity.access$002(DiainfoActivity.this, true);
        diainfo = DiainfoActivity.access$100(DiainfoActivity.this).getResult();
        showDiainfoList(diainfo);
        setCompleted();
        return false;
    }

    tener()
    {
        this$0 = DiainfoActivity.this;
        super();
    }
}

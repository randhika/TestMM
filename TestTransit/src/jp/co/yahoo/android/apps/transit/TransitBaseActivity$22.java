// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit;

import android.os.Bundle;
import jp.co.yahoo.android.apps.transit.api.data.APIError;

// Referenced classes of package jp.co.yahoo.android.apps.transit:
//            TransitBaseActivity

class this._cls0
    implements jp.co.yahoo.android.apps.transit.api.ener
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

    public boolean onSuccess(Bundle bundle)
    {
        chkRegistRail = bundle;
        if (chkDiainfo != null && chkRegistRail != null)
        {
            chkDiainfoDetail();
            setHumbergarIcon();
        }
        return false;
    }

    public volatile boolean onSuccess(Object obj)
    {
        return onSuccess((Bundle)obj);
    }

    Listener()
    {
        this$0 = TransitBaseActivity.this;
        super();
    }
}

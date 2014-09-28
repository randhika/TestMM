// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit;

import android.os.Bundle;
import jp.co.yahoo.android.apps.transit.api.AuthApiBase;
import jp.co.yahoo.android.apps.transit.api.data.APIError;

// Referenced classes of package jp.co.yahoo.android.apps.transit:
//            InputSearch

class this._cls0
    implements jp.co.yahoo.android.apps.transit.api.thApiListener
{

    final InputSearch this$0;

    public boolean onCanceled()
    {
        InputSearch.access$1100(InputSearch.this);
        return false;
    }

    public boolean onError(APIError apierror)
    {
        if (apierror.getCode().equals(AuthApiBase.ERR_CODE_EXPIRED_TOKEN))
        {
            InputSearch.access$1002(InputSearch.this, null);
        }
        InputSearch.access$1100(InputSearch.this);
        return false;
    }

    public boolean onSuccess(Bundle bundle)
    {
        InputSearch.access$902(InputSearch.this, bundle);
        InputSearch.access$1100(InputSearch.this);
        return false;
    }

    public volatile boolean onSuccess(Object obj)
    {
        return onSuccess((Bundle)obj);
    }

    ror()
    {
        this$0 = InputSearch.this;
        super();
    }
}

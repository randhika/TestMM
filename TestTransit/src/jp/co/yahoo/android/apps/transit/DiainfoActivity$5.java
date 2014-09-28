// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit;

import android.os.Bundle;
import jp.co.yahoo.android.apps.transit.api.data.APIError;

// Referenced classes of package jp.co.yahoo.android.apps.transit:
//            DiainfoActivity

class this._cls0
    implements jp.co.yahoo.android.apps.transit.api.iListener
{

    final DiainfoActivity this$0;

    public boolean onCanceled()
    {
        DiainfoActivity.access$702(DiainfoActivity.this, true);
        setCompleted();
        return false;
    }

    public boolean onError(APIError apierror)
    {
        DiainfoActivity.access$702(DiainfoActivity.this, true);
        setCompleted();
        return false;
    }

    public boolean onSuccess(Bundle bundle)
    {
        pushSettings = bundle;
        DiainfoActivity.access$702(DiainfoActivity.this, true);
        setCompleted();
        return false;
    }

    public volatile boolean onSuccess(Object obj)
    {
        return onSuccess((Bundle)obj);
    }

    thApiListener()
    {
        this$0 = DiainfoActivity.this;
        super();
    }
}

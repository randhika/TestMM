// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.common;

import android.os.Bundle;
import java.util.HashMap;
import jp.co.yahoo.android.apps.transit.api.data.APIError;
import jp.co.yahoo.android.apps.transit.db.PushSubscriptionDB;

// Referenced classes of package jp.co.yahoo.android.apps.transit.common:
//            PushDiainfoManager

class er
    implements jp.co.yahoo.android.apps.transit.api.tener
{

    final PushDiainfoManager this$0;
    final jp.co.yahoo.android.apps.transit.api.tener val$listener;
    final boolean val$saveDb;

    public boolean onCanceled()
    {
        if (val$listener != null)
        {
            val$listener.onCanceled();
        }
        return false;
    }

    public boolean onError(APIError apierror)
    {
        if (val$listener != null)
        {
            val$listener.onError(apierror);
        }
        return false;
    }

    public boolean onSuccess(Bundle bundle)
    {
        if (val$saveDb && bundle != null && bundle.containsKey("result"))
        {
            HashMap hashmap = (HashMap)bundle.getSerializable("result");
            (new PushSubscriptionDB(PushDiainfoManager.access$200(PushDiainfoManager.this))).updateDiainfo(hashmap);
        }
        if (val$listener != null)
        {
            val$listener.onSuccess(bundle);
        }
        return false;
    }

    public volatile boolean onSuccess(Object obj)
    {
        return onSuccess((Bundle)obj);
    }

    er()
    {
        this$0 = final_pushdiainfomanager;
        val$saveDb = flag;
        val$listener = jp.co.yahoo.android.apps.transit.api.tener.this;
        super();
    }
}

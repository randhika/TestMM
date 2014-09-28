// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.ResultCallback;

// Referenced classes of package jp.co.yahoo.android.apps.transit:
//            WearUpdateService

class this._cls0
    implements ResultCallback
{

    final WearUpdateService this$0;

    public volatile void onResult(Result result)
    {
        onResult((com.google.android.gms.wearable.lt)result);
    }

    public void onResult(com.google.android.gms.wearable.lt lt)
    {
    }

    t()
    {
        this$0 = WearUpdateService.this;
        super();
    }
}

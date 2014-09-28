// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.timer.common;

import android.os.Handler;
import android.os.Message;

// Referenced classes of package jp.co.yahoo.android.apps.transit.timer.common:
//            CountdownManager

class this._cls0 extends Handler
{

    final CountdownManager this$0;

    public void handleMessage(Message message)
    {
        updateTime();
        if (handler != null)
        {
            handler.sendMessageDelayed(obtainMessage(), 1000L);
        }
    }

    ()
    {
        this$0 = CountdownManager.this;
        super();
    }
}

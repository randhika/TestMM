// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.common;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

// Referenced classes of package jp.co.yahoo.android.common:
//            YHttpRequest

class this._cls0 extends Handler
{

    final YHttpRequest this$0;

    public void handleMessage(Message message)
    {
        String s = message.getData().getString("Topic");
        if (s.equals("timeout"))
        {
            onTimeout();
        } else
        if (s.equals("complete"))
        {
            onComplete();
            return;
        }
    }

    (Looper looper)
    {
        this$0 = YHttpRequest.this;
        super(looper);
    }
}

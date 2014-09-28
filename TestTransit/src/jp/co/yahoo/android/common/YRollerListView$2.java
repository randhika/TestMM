// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.common;

import android.os.Handler;
import android.os.Message;

// Referenced classes of package jp.co.yahoo.android.common:
//            YRollerListView

class this._cls0 extends Handler
{

    private int _scrollY;
    final YRollerListView this$0;

    public void handleMessage(Message message)
    {
        message.what;
        JVM INSTR tableswitch 1 2: default 28
    //                   1 29
    //                   2 59;
           goto _L1 _L2 _L3
_L1:
        return;
_L2:
        _scrollY = getScrollY();
        Message message1 = Message.obtain();
        message1.what = 2;
        sendMessageDelayed(message1, 10L);
        return;
_L3:
        if (getScrollY() - _scrollY == 0)
        {
            removeMessages(1);
            removeMessages(2);
            onScrollStateChanged(YRollerListView.this, 0);
            return;
        }
        if (true) goto _L1; else goto _L4
_L4:
    }

    ()
    {
        this$0 = YRollerListView.this;
        super();
    }
}

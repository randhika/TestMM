// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.common;

import android.os.Handler;
import android.os.Message;

// Referenced classes of package jp.co.yahoo.android.common:
//            YExtendedGestureDetector16

private class this._cls0 extends Handler
{

    final YExtendedGestureDetector16 this$0;

    public void handleMessage(Message message)
    {
        message.what;
        JVM INSTR tableswitch 1 4: default 36
    //                   1 63
    //                   2 83
    //                   3 145
    //                   4 91;
           goto _L1 _L2 _L3 _L4 _L5
_L1:
        throw new RuntimeException((new StringBuilder()).append("Unknown message ").append(message).toString());
_L2:
        YExtendedGestureDetector16.access$100(YExtendedGestureDetector16.this).onShowPress(YExtendedGestureDetector16.access$000(YExtendedGestureDetector16.this));
_L7:
        return;
_L3:
        YExtendedGestureDetector16.access$200(YExtendedGestureDetector16.this);
        return;
_L5:
        YExtendedGestureDetector16.access$100(YExtendedGestureDetector16.this).onHoldAfterMove(YExtendedGestureDetector16.access$300(YExtendedGestureDetector16.this));
        if (!YExtendedGestureDetector16.access$400(YExtendedGestureDetector16.this).hasMessages(4))
        {
            YExtendedGestureDetector16.access$400(YExtendedGestureDetector16.this).sendEmptyMessageDelayed(4, YExtendedGestureDetector16.access$500(YExtendedGestureDetector16.this));
            return;
        }
        continue; /* Loop/switch isn't completed */
_L4:
        if (YExtendedGestureDetector16.access$600(YExtendedGestureDetector16.this) != null && !YExtendedGestureDetector16.access$700(YExtendedGestureDetector16.this))
        {
            YExtendedGestureDetector16.access$600(YExtendedGestureDetector16.this).onSingleTapConfirmed(YExtendedGestureDetector16.access$000(YExtendedGestureDetector16.this));
            return;
        }
        if (true) goto _L7; else goto _L6
_L6:
    }

    ener16()
    {
        this$0 = YExtendedGestureDetector16.this;
        super();
    }

    this._cls0(Handler handler)
    {
        this$0 = YExtendedGestureDetector16.this;
        super(handler.getLooper());
    }
}

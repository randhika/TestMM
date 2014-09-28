// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.timer.common;


// Referenced classes of package jp.co.yahoo.android.apps.transit.timer.common:
//            RingAlerm

class this._cls0
    implements Runnable
{

    final RingAlerm this$0;

    public void run()
    {
        stopAlerm();
        if (listener != null)
        {
            listener.onStop();
        }
    }

    ermListener()
    {
        this$0 = RingAlerm.this;
        super();
    }
}

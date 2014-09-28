// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit;

import android.os.Handler;
import android.os.Message;

// Referenced classes of package jp.co.yahoo.android.apps.transit:
//            TransitBaseActivity

class this._cls0
    implements Runnable
{

    final TransitBaseActivity this$0;

    public void run()
    {
        Message message = Message.obtain(TransitBaseActivity.access$100(TransitBaseActivity.this), 1);
        TransitBaseActivity.access$100(TransitBaseActivity.this).sendMessage(message);
    }

    ()
    {
        this$0 = TransitBaseActivity.this;
        super();
    }
}

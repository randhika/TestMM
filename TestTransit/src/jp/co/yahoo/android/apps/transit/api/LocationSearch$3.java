// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.api;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import java.util.TimerTask;

// Referenced classes of package jp.co.yahoo.android.apps.transit.api:
//            LocationSearch

class this._cls0 extends TimerTask
{

    final LocationSearch this$0;

    public void run()
    {
        bTimeout = true;
        Bundle bundle = new Bundle();
        bundle.putInt(LocationSearch.access$700(LocationSearch.this, 0x7f0d01ce), 0x7f0c0079);
        Message message = LocationSearch.access$800(LocationSearch.this).obtainMessage();
        message.obj = bundle;
        LocationSearch.access$800(LocationSearch.this).sendMessage(message);
    }

    ()
    {
        this$0 = LocationSearch.this;
        super();
    }
}

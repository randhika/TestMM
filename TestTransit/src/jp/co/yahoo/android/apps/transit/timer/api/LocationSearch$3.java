// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.timer.api;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import java.util.TimerTask;

// Referenced classes of package jp.co.yahoo.android.apps.transit.timer.api:
//            LocationSearch

class this._cls0 extends TimerTask
{

    final LocationSearch this$0;

    public void run()
    {
        Bundle bundle = new Bundle();
        bundle.putInt(LocationSearch.access$100(LocationSearch.this, 0x7f0d01ce), 0x7f0c0079);
        bundle.putString(LocationSearch.access$100(LocationSearch.this, 0x7f0d01cf), LocationSearch.access$100(LocationSearch.this, 0x7f0d01d0));
        bundle.putString(LocationSearch.access$100(LocationSearch.this, 0x7f0d01b9), LocationSearch.access$100(LocationSearch.this, 0x7f0d010c));
        bundle.putString(LocationSearch.access$100(LocationSearch.this, 0x7f0d01ba), LocationSearch.access$100(LocationSearch.this, 0x7f0d01ba));
        Message message = LocationSearch.access$500(LocationSearch.this).obtainMessage();
        message.obj = bundle;
        LocationSearch.access$500(LocationSearch.this).sendMessage(message);
    }

    A()
    {
        this$0 = LocationSearch.this;
        super();
    }
}

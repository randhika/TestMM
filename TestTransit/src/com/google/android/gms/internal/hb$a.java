// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;

// Referenced classes of package com.google.android.gms.internal:
//            hb, hc

final class Gi extends Handler
{

    final hb Gi;

    public void handleMessage(Message message)
    {
        if (message.what == 1 && !Gi.isConnecting())
        {
            ionResult ionresult1 = (Connecting)message.obj;
            ionresult1.fu();
            ionresult1.unregister();
            return;
        }
        if (message.what == 3)
        {
            hb.a(Gi).a(new ConnectionResult(((Integer)message.obj).intValue(), null));
            return;
        }
        if (message.what == 4)
        {
            hb.a(Gi, 1);
            hb.a(Gi, null);
            hb.a(Gi).ao(((Integer)message.obj).intValue());
            return;
        }
        if (message.what == 2 && !Gi.isConnected())
        {
            ionResult ionresult = (Connected)message.obj;
            ionresult.fu();
            ionresult.unregister();
            return;
        }
        if (message.what == 2 || message.what == 1)
        {
            ((unregister)message.obj).fv();
            return;
        } else
        {
            Log.wtf("GmsClient", "Don't know how to handle this message.");
            return;
        }
    }

    public ionResult(hb hb1, Looper looper)
    {
        Gi = hb1;
        super(looper);
    }
}

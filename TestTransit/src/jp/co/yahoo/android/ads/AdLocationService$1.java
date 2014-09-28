// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.ads;

import android.os.Handler;
import java.util.TimerTask;

// Referenced classes of package jp.co.yahoo.android.ads:
//            AdLocationService, h

class a extends TimerTask
{

    final Handler a;
    final AdLocationService b;

    public void run()
    {
        a.post(new Runnable() {

            final AdLocationService._cls1 a;

            public void run()
            {
                a.b.stopSelf();
                h.a(3, "[ALService] Time out");
            }

            
            {
                a = AdLocationService._cls1.this;
                super();
            }
        });
    }

    _cls1.a(AdLocationService adlocationservice, Handler handler)
    {
        b = adlocationservice;
        a = handler;
        super();
    }
}

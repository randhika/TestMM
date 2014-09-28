// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.ads;

import android.os.Handler;
import java.util.TimerTask;

// Referenced classes of package jp.co.yahoo.android.ads:
//            AdLocationService, h

class a
    implements Runnable
{

    final Self a;

    public void run()
    {
        a.a.stopSelf();
        h.a(3, "[ALService] Time out");
    }

    ( )
    {
        a = ;
        super();
    }

    // Unreferenced inner class jp/co/yahoo/android/ads/AdLocationService$1

/* anonymous class */
    class AdLocationService._cls1 extends TimerTask
    {

        final Handler a;
        final AdLocationService b;

        public void run()
        {
            a.post(new AdLocationService._cls1._cls1(this));
        }

            
            {
                b = adlocationservice;
                a = handler;
                super();
            }
    }

}

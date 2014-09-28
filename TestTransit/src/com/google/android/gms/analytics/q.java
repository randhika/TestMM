// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.analytics;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

// Referenced classes of package com.google.android.gms.analytics:
//            af

class q extends BroadcastReceiver
{

    static final String tR = com/google/android/gms/analytics/q.getName();
    private final af tS;

    q(af af1)
    {
        tS = af1;
    }

    public static void w(Context context)
    {
        Intent intent = new Intent("com.google.analytics.RADIO_POWERED");
        intent.addCategory(context.getPackageName());
        intent.putExtra(tR, true);
        context.sendBroadcast(intent);
    }

    public void onReceive(Context context, Intent intent)
    {
        String s = intent.getAction();
        if ("android.net.conn.CONNECTIVITY_CHANGE".equals(s))
        {
            boolean flag = intent.getBooleanExtra("noConnectivity", false);
            af af1 = tS;
            boolean flag1 = false;
            if (!flag)
            {
                flag1 = true;
            }
            af1.t(flag1);
        } else
        if ("com.google.analytics.RADIO_POWERED".equals(s) && !intent.hasExtra(tR))
        {
            tS.cI();
            return;
        }
    }

    public void v(Context context)
    {
        IntentFilter intentfilter = new IntentFilter();
        intentfilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        context.registerReceiver(this, intentfilter);
        IntentFilter intentfilter1 = new IntentFilter();
        intentfilter1.addAction("com.google.analytics.RADIO_POWERED");
        intentfilter1.addCategory(context.getPackageName());
        context.registerReceiver(this, intentfilter1);
    }

}

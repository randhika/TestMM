// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.timer.widget;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

public class CountdownWidgetReceiver extends BroadcastReceiver
{
    public static interface CountdownWidgetReceiverListener
    {

        public abstract void onConfigurationChanged();

        public abstract void onScreenOff();

        public abstract void onScreenOn();

        public abstract void onTimeChanged();
    }


    protected CountdownWidgetReceiverListener listener;

    public CountdownWidgetReceiver()
    {
        listener = null;
    }

    public void onReceive(Context context, Intent intent)
    {
        if (listener != null)
        {
            String s = intent.getAction();
            if ("android.intent.action.SCREEN_ON".equals(s))
            {
                listener.onScreenOn();
                return;
            }
            if ("android.intent.action.REBOOT".equals(s) || "android.intent.action.ACTION_SHUTDOWN".equals(s) || "android.intent.action.SCREEN_OFF".equals(s))
            {
                listener.onScreenOff();
                return;
            }
            if ("android.intent.action.TIME_SET".equals(s))
            {
                listener.onTimeChanged();
                return;
            }
            if ("android.intent.action.CONFIGURATION_CHANGED".equals(s))
            {
                listener.onConfigurationChanged();
                return;
            }
        }
    }

    public void registerReceiver(Context context, CountdownWidgetReceiverListener countdownwidgetreceiverlistener)
    {
        listener = countdownwidgetreceiverlistener;
        IntentFilter intentfilter = new IntentFilter();
        intentfilter.addAction("android.intent.action.SCREEN_ON");
        intentfilter.addAction("android.intent.action.REBOOT");
        intentfilter.addAction("android.intent.action.ACTION_SHUTDOWN");
        intentfilter.addAction("android.intent.action.SCREEN_OFF");
        intentfilter.addAction("android.intent.action.TIME_SET");
        intentfilter.addAction("android.intent.action.CONFIGURATION_CHANGED");
        context.registerReceiver(this, intentfilter);
    }

    public void unregisterReceiver(Context context)
    {
        context.unregisterReceiver(this);
    }
}

// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.widget;

import android.app.PendingIntent;
import android.app.Service;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.widget.RemoteViews;
import jp.co.yahoo.android.apps.transit.InputSearch;
import jp.co.yahoo.android.apps.transit.Transit;

// Referenced classes of package jp.co.yahoo.android.apps.transit.widget:
//            WidgetSearchActivity, SearchWidget

public class SearchWidgetService extends Service
{

    private final String HOME_BUTTON_ACTION = "HOME_BUTTON_ACTION";
    private final String TO_BUTTON_ACTION = "TO_BUTTON_ACTION";
    private final String TRANSIT_BUTTON_ACTION = "TRANSIT_BUTTON_ACTION";
    private final String VOICE_BUTTON_ACTION = "VOICE_BUTTON_ACTION";

    public SearchWidgetService()
    {
    }

    public IBinder onBind(Intent intent)
    {
        return null;
    }

    public int onStartCommand(Intent intent, int i, int j)
    {
        super.onStart(intent, j);
        Context context = getApplicationContext();
        RemoteViews remoteviews = new RemoteViews(getPackageName(), 0x7f03009d);
        String s = "";
        if (intent != null)
        {
            s = intent.getAction();
        }
        Intent intent1 = new Intent();
        intent1.setAction("HOME_BUTTON_ACTION");
        remoteviews.setOnClickPendingIntent(0x7f0a02ea, PendingIntent.getService(context, 0, intent1, 0x8000000));
        if ("HOME_BUTTON_ACTION".equals(s))
        {
            Intent intent2 = new Intent(context, jp/co/yahoo/android/apps/transit/widget/WidgetSearchActivity);
            intent2.putExtra(getString(0x7f0d024d), "home");
            intent2.setFlags(0x14000000);
            getApplicationContext().startActivity(intent2);
        }
        Intent intent3 = new Intent();
        intent3.setAction("TRANSIT_BUTTON_ACTION");
        remoteviews.setOnClickPendingIntent(0x7f0a02e7, PendingIntent.getService(context, 0, intent3, 0x8000000));
        if ("TRANSIT_BUTTON_ACTION".equals(s))
        {
            Intent intent4 = new Intent(context, jp/co/yahoo/android/apps/transit/Transit);
            intent4.setFlags(0x14000000);
            getApplicationContext().startActivity(intent4);
        }
        Intent intent5 = new Intent();
        intent5.setAction("TO_BUTTON_ACTION");
        remoteviews.setOnClickPendingIntent(0x7f0a02e8, PendingIntent.getService(context, 0, intent5, 0x8000000));
        if ("TO_BUTTON_ACTION".equals(s))
        {
            Intent intent6 = new Intent(context, jp/co/yahoo/android/apps/transit/InputSearch);
            intent6.putExtra(getString(0x7f0d022d), getString(0x7f0d0284));
            intent6.putExtra(getString(0x7f0d01a1), true);
            intent6.putExtra(getString(0x7f0d01c3), true);
            intent6.putExtra(getString(0x7f0d0233), 3);
            intent6.setFlags(0x14000000);
            getApplicationContext().startActivity(intent6);
        }
        Intent intent7 = new Intent();
        intent7.setAction("VOICE_BUTTON_ACTION");
        remoteviews.setOnClickPendingIntent(0x7f0a02e9, PendingIntent.getService(context, 0, intent7, 0x8000000));
        if ("VOICE_BUTTON_ACTION".equals(s))
        {
            Intent intent8 = new Intent(context, jp/co/yahoo/android/apps/transit/widget/WidgetSearchActivity);
            intent8.putExtra(getString(0x7f0d024d), "voice");
            intent8.setFlags(0x14000000);
            getApplicationContext().startActivity(intent8);
        }
        ComponentName componentname = new ComponentName(context, jp/co/yahoo/android/apps/transit/widget/SearchWidget);
        AppWidgetManager.getInstance(context).updateAppWidget(componentname, remoteviews);
        return 0;
    }
}

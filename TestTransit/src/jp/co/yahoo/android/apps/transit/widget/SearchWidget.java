// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.widget;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import jp.co.yahoo.android.apps.transit.common.util.TransitUtil;

// Referenced classes of package jp.co.yahoo.android.apps.transit.widget:
//            SearchWidgetService

public class SearchWidget extends AppWidgetProvider
{

    public Context context;

    public SearchWidget()
    {
    }

    public void onDeleted(Context context1, int ai[])
    {
        context1.stopService(new Intent(context1, jp/co/yahoo/android/apps/transit/widget/SearchWidgetService));
        super.onDeleted(context1, ai);
    }

    public void onDisabled(Context context1)
    {
        context1.stopService(new Intent(context1, jp/co/yahoo/android/apps/transit/widget/SearchWidgetService));
        super.onDisabled(context1);
    }

    public void onEnabled(Context context1)
    {
        context = context1;
        TransitUtil.touchRD(context1.getString(0x7f0d0566));
        super.onEnabled(context1);
    }

    public void onReceive(Context context1, Intent intent)
    {
        super.onReceive(context1, intent);
    }

    public void onUpdate(Context context1, AppWidgetManager appwidgetmanager, int ai[])
    {
        context1.startService(new Intent(context1, jp/co/yahoo/android/apps/transit/widget/SearchWidgetService));
        super.onUpdate(context1, appwidgetmanager, ai);
    }
}

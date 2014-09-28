// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.timer.widget;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import jp.co.yahoo.android.apps.transit.common.util.TransitUtil;

// Referenced classes of package jp.co.yahoo.android.apps.transit.timer.widget:
//            CountdownWidgetService

public class CountdownWidgetProvider extends AppWidgetProvider
{

    public CountdownWidgetProvider()
    {
    }

    private Class getServiceClass()
    {
        return jp/co/yahoo/android/apps/transit/timer/widget/CountdownWidgetService;
    }

    private boolean isExistWidget(Context context)
    {
        ComponentName componentname = new ComponentName(context, getClass());
        int ai[] = AppWidgetManager.getInstance(context).getAppWidgetIds(componentname);
        return ai != null && ai.length > 0;
    }

    public void onDeleted(Context context, int ai[])
    {
        Intent intent = new Intent(context.getApplicationContext(), getServiceClass());
        intent.setAction("jp.co.yahoo.android.apps.transit.timer.ACTION_DELETE");
        intent.putExtra(context.getString(0x7f0d017f), ai);
        context.getApplicationContext().startService(intent);
        super.onDeleted(context, ai);
    }

    public void onDisabled(Context context)
    {
        Intent intent = new Intent(context.getApplicationContext(), getServiceClass());
        context.getApplicationContext().stopService(intent);
        super.onDisabled(context);
    }

    public void onEnabled(Context context)
    {
        TransitUtil.touchRD(context.getString(0x7f0d0563));
        super.onEnabled(context);
    }

    public void onReceive(Context context, Intent intent)
    {
        String s = intent.getAction();
        if (!"jp.co.yahoo.android.apps.transit.timer.ACTION_DATA_CHANGED".equals(s)) goto _L2; else goto _L1
_L1:
        if (isExistWidget(context))
        {
            Intent intent2 = new Intent(context.getApplicationContext(), getServiceClass());
            intent2.setAction(s);
            intent2.putExtra(context.getString(0x7f0d0247), intent.getIntExtra(context.getString(0x7f0d0247), -1));
            intent2.putStringArrayListExtra(s, intent.getStringArrayListExtra(s));
            context.getApplicationContext().startService(intent2);
        }
_L4:
        super.onReceive(context, intent);
        return;
_L2:
        if ("android.intent.action.USER_PRESENT".equals(s) && isExistWidget(context))
        {
            Intent intent1 = new Intent(context.getApplicationContext(), getServiceClass());
            intent1.setAction("jp.co.yahoo.android.apps.transit.timer.ACTION_RESTART");
            context.getApplicationContext().startService(intent1);
        }
        if (true) goto _L4; else goto _L3
_L3:
    }

    public void onUpdate(Context context, AppWidgetManager appwidgetmanager, int ai[])
    {
        super.onUpdate(context, appwidgetmanager, ai);
        Intent intent = new Intent(context.getApplicationContext(), getServiceClass());
        intent.setAction("jp.co.yahoo.android.apps.transit.timer.ACTION_UPDATE");
        intent.putExtra(context.getString(0x7f0d017f), ai);
        context.getApplicationContext().startService(intent);
    }
}

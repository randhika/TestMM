// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.timer.widget;

import android.content.Intent;
import java.util.Queue;

// Referenced classes of package jp.co.yahoo.android.apps.transit.timer.widget:
//            CountdownWidgetService

class this._cls0
    implements Runnable
{

    final CountdownWidgetService this$0;

    public void run()
    {
        Intent intent = (Intent)CountdownWidgetService.access$000(CountdownWidgetService.this).poll();
        if (intent != null)
        {
            CountdownWidgetService.access$100(CountdownWidgetService.this);
            String s = intent.getAction();
            if ("jp.co.yahoo.android.apps.transit.timer.ACTION_UPDATE".equals(s))
            {
                CountdownWidgetService.access$200(CountdownWidgetService.this, intent);
                return;
            }
            if ("jp.co.yahoo.android.apps.transit.timer.ACTION_DELETE".equals(s))
            {
                CountdownWidgetService.access$300(CountdownWidgetService.this, intent);
                return;
            }
            if ("jp.co.yahoo.android.apps.transit.timer.ACTION_BUTTON_LAUNCH".equals(s))
            {
                CountdownWidgetService.access$400(CountdownWidgetService.this, intent);
                return;
            }
            if ("jp.co.yahoo.android.apps.transit.timer.ACTION_BUTTON_CHANGE".equals(s))
            {
                CountdownWidgetService.access$500(CountdownWidgetService.this, intent);
                return;
            }
            if ("jp.co.yahoo.android.apps.transit.timer.ACTION_DATA_CHANGED".equals(s))
            {
                CountdownWidgetService.access$600(CountdownWidgetService.this, intent);
                return;
            }
            if ("jp.co.yahoo.android.apps.transit.timer.ACTION_RESTART".equals(s))
            {
                CountdownWidgetService.access$700(CountdownWidgetService.this);
                return;
            }
            if ("jp.co.yahoo.android.apps.transit.timer.ACTION_RESTART_PROCESS".equals(s))
            {
                CountdownWidgetService.access$800(CountdownWidgetService.this);
                return;
            }
            if ("jp.co.yahoo.android.apps.transit.timer.ACTION_CHECK_START".equals(s))
            {
                CountdownWidgetService.access$800(CountdownWidgetService.this);
                return;
            }
        }
    }

    ()
    {
        this$0 = CountdownWidgetService.this;
        super();
    }
}

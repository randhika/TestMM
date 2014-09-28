// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.timer.widget;

import android.content.res.Resources;

// Referenced classes of package jp.co.yahoo.android.apps.transit.timer.widget:
//            CountdownWidgetService

class this._cls0
    implements Runnable
{

    final CountdownWidgetService this$0;

    public void run()
    {
        int i = 0;
        cancelTimer();
        CountdownWidgetService.access$3002(CountdownWidgetService.this, -1);
        android.content.Context context = getApplicationContext();
        if (CountdownWidgetService.access$1000(CountdownWidgetService.this) != null && CountdownWidgetService.access$1000(CountdownWidgetService.this).length > 0)
        {
            int i1 = getResources().getInteger(0x7f0c0074);
            int ai1[] = CountdownWidgetService.access$1000(CountdownWidgetService.this);
            int j1 = ai1.length;
            for (int k1 = 0; k1 < j1; k1++)
            {
                int l1 = ai1[k1];
                CountdownWidgetService.access$1300(CountdownWidgetService.this, context, l1, i1);
            }

        }
        if (CountdownWidgetService.access$2300(CountdownWidgetService.this) != null && CountdownWidgetService.access$2300(CountdownWidgetService.this).length > 0)
        {
            int j = getResources().getInteger(0x7f0c0073);
            int ai[] = CountdownWidgetService.access$2300(CountdownWidgetService.this);
            for (int k = ai.length; i < k; i++)
            {
                int l = ai[i];
                CountdownWidgetService.access$1300(CountdownWidgetService.this, context, l, j);
            }

        }
        if (CountdownWidgetService.access$1500(CountdownWidgetService.this) != null || CountdownWidgetService.access$2600(CountdownWidgetService.this) != null)
        {
            startTimer();
        }
    }

    ()
    {
        this$0 = CountdownWidgetService.this;
        super();
    }
}

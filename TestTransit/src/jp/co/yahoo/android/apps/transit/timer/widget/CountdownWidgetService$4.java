// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.timer.widget;

import android.content.res.Resources;
import jp.co.yahoo.android.apps.transit.timer.api.data.StationData;
import jp.co.yahoo.android.apps.transit.timer.common.CountdownManager;

// Referenced classes of package jp.co.yahoo.android.apps.transit.timer.widget:
//            CountdownWidgetService

class this._cls0
    implements Runnable
{

    final CountdownWidgetService this$0;

    public void run()
    {
        cancelTimer();
        if (CountdownWidgetService.access$1000(CountdownWidgetService.this) != null && CountdownWidgetService.access$1000(CountdownWidgetService.this).length > 0)
        {
            CountdownWidgetService.access$1102(CountdownWidgetService.this, -1);
            CountdownWidgetService.access$1202(CountdownWidgetService.this, null);
            if (CountdownWidgetService.access$1500(CountdownWidgetService.this) != null)
            {
                CountdownWidgetService.access$900(CountdownWidgetService.this).setWeek(-1);
                CountdownWidgetService.access$900(CountdownWidgetService.this).setTime(null);
                CountdownWidgetService.access$900(CountdownWidgetService.this).resetCountDown(CountdownWidgetService.access$1500(CountdownWidgetService.this).getTimetable());
                CountdownWidgetService.access$1102(CountdownWidgetService.this, CountdownWidgetService.access$900(CountdownWidgetService.this).updateTime());
                CountdownWidgetService.access$1202(CountdownWidgetService.this, CountdownWidgetService.access$900(CountdownWidgetService.this).getTargetTime());
            }
            CountdownWidgetService.access$2900(CountdownWidgetService.this, getApplicationContext(), CountdownWidgetService.access$1000(CountdownWidgetService.this), getResources().getInteger(0x7f0c0074));
        }
        if (CountdownWidgetService.access$2300(CountdownWidgetService.this) != null && CountdownWidgetService.access$2300(CountdownWidgetService.this).length > 0)
        {
            CountdownWidgetService.access$2402(CountdownWidgetService.this, -1);
            CountdownWidgetService.access$2502(CountdownWidgetService.this, null);
            if (CountdownWidgetService.access$2600(CountdownWidgetService.this) != null)
            {
                CountdownWidgetService.access$2200(CountdownWidgetService.this).setWeek(-1);
                CountdownWidgetService.access$2200(CountdownWidgetService.this).setTime(null);
                CountdownWidgetService.access$2200(CountdownWidgetService.this).resetCountDown(CountdownWidgetService.access$2600(CountdownWidgetService.this).getTimetable());
                CountdownWidgetService.access$2402(CountdownWidgetService.this, CountdownWidgetService.access$2200(CountdownWidgetService.this).updateTime());
                CountdownWidgetService.access$2502(CountdownWidgetService.this, CountdownWidgetService.access$2200(CountdownWidgetService.this).getTargetTime());
            }
            CountdownWidgetService.access$2900(CountdownWidgetService.this, getApplicationContext(), CountdownWidgetService.access$2300(CountdownWidgetService.this), getResources().getInteger(0x7f0c0073));
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

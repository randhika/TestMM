// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.timer.widget;

import android.os.Handler;
import java.util.TimerTask;

// Referenced classes of package jp.co.yahoo.android.apps.transit.timer.widget:
//            CountdownWidgetService

class this._cls0 extends TimerTask
{

    final CountdownWidgetService this$0;

    public void run()
    {
        CountdownWidgetService.access$2800(CountdownWidgetService.this).post(CountdownWidgetService.access$2700(CountdownWidgetService.this));
    }

    ()
    {
        this$0 = CountdownWidgetService.this;
        super();
    }
}

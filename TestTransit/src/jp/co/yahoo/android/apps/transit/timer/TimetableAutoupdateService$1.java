// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.timer;

import android.content.Intent;
import java.util.Queue;

// Referenced classes of package jp.co.yahoo.android.apps.transit.timer:
//            TimetableAutoupdateService

class this._cls0
    implements Runnable
{

    final TimetableAutoupdateService this$0;

    public void run()
    {
        if ((Intent)TimetableAutoupdateService.access$000(TimetableAutoupdateService.this).poll() == null)
        {
            return;
        } else
        {
            updateAllbyDay();
            return;
        }
    }

    I()
    {
        this$0 = TimetableAutoupdateService.this;
        super();
    }
}

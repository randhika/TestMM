// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit;

import android.content.Intent;
import java.util.Queue;

// Referenced classes of package jp.co.yahoo.android.apps.transit:
//            PushDiainfoService

class this._cls0
    implements Runnable
{

    final PushDiainfoService this$0;

    public void run()
    {
        Intent intent = (Intent)PushDiainfoService.access$000(PushDiainfoService.this).poll();
        if (intent != null)
        {
            String s = intent.getAction();
            if ("jp.co.yahoo.android.apps.transit.PushDiainfoService.ACTION_ON_APP_START".equals(s))
            {
                PushDiainfoService.access$100(PushDiainfoService.this);
                return;
            }
            if ("jp.co.yahoo.android.apps.transit.PushDiainfoService.ACTION_ON_GET_RAIL".equals(s))
            {
                PushDiainfoService.access$200(PushDiainfoService.this, intent);
                return;
            }
        }
    }

    ()
    {
        this$0 = PushDiainfoService.this;
        super();
    }
}

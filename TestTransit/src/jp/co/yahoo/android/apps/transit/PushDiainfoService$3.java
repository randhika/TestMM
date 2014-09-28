// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit;

import java.util.ArrayList;

// Referenced classes of package jp.co.yahoo.android.apps.transit:
//            PushDiainfoService

class this._cls0
    implements jp.co.yahoo.android.apps.transit.common.shManagerListener
{

    final PushDiainfoService this$0;

    public void onCanceled()
    {
        PushDiainfoService.access$300(PushDiainfoService.this).remove("jp.co.yahoo.android.apps.transit.PushDiainfoService.ACTION_ON_GET_RAIL");
        if (PushDiainfoService.access$300(PushDiainfoService.this).isEmpty())
        {
            stopSelf();
        }
    }

    public void onError(int i, String s, String s1, String s2)
    {
        PushDiainfoService.access$300(PushDiainfoService.this).remove("jp.co.yahoo.android.apps.transit.PushDiainfoService.ACTION_ON_GET_RAIL");
        if (PushDiainfoService.access$300(PushDiainfoService.this).isEmpty())
        {
            stopSelf();
        }
    }

    public void onSuccess(String s, String s1)
    {
        PushDiainfoService.access$300(PushDiainfoService.this).remove("jp.co.yahoo.android.apps.transit.PushDiainfoService.ACTION_ON_GET_RAIL");
        if (PushDiainfoService.access$300(PushDiainfoService.this).isEmpty())
        {
            stopSelf();
        }
    }

    ager.PushManagerListener()
    {
        this$0 = PushDiainfoService.this;
        super();
    }
}

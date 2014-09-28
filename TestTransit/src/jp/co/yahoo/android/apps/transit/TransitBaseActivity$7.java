// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit;

import java.util.TimerTask;
import jp.co.yahoo.android.apps.transit.common.SingletonMap;

// Referenced classes of package jp.co.yahoo.android.apps.transit:
//            TransitBaseActivity

class this._cls0 extends TimerTask
{

    final TransitBaseActivity this$0;

    public void run()
    {
        if (SingletonMap.getInstance().containsKey((new StringBuilder()).append(getLocalClassName()).append(getRouteIdString()).toString()) && !((Boolean)SingletonMap.getInstance().get((new StringBuilder()).append(getLocalClassName()).append(getRouteIdString()).toString())).booleanValue())
        {
            SingletonMap.getInstance().remove((new StringBuilder()).append(getLocalClassName()).append(getRouteIdString()).toString());
        }
    }

    ()
    {
        this$0 = TransitBaseActivity.this;
        super();
    }
}

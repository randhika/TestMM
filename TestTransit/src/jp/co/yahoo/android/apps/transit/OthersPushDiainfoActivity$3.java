// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit;

import jp.co.yahoo.android.apps.transit.common.util.TransitUtil;

// Referenced classes of package jp.co.yahoo.android.apps.transit:
//            OthersPushDiainfoActivity

class this._cls0
    implements Runnable
{

    final OthersPushDiainfoActivity this$0;

    public void run()
    {
        if (OthersPushDiainfoActivity.access$500(OthersPushDiainfoActivity.this))
        {
            OthersPushDiainfoActivity.access$502(OthersPushDiainfoActivity.this, false);
            return;
        } else
        {
            TransitUtil.login(OthersPushDiainfoActivity.this);
            return;
        }
    }

    ()
    {
        this$0 = OthersPushDiainfoActivity.this;
        super();
    }
}

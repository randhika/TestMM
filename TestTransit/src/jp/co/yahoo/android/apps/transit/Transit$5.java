// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit;

import android.view.View;
import jp.co.yahoo.android.apps.transit.api.data.ConditionData;
import jp.co.yahoo.android.apps.transit.common.SaveCondition;

// Referenced classes of package jp.co.yahoo.android.apps.transit:
//            Transit

class this._cls0
    implements android.view.ckListener
{

    final Transit this$0;

    public void onClick(View view)
    {
        SaveCondition savecondition = new SaveCondition(Transit.this);
        Transit.access$300(Transit.this).ticket = savecondition.getCond().ticket;
        Transit.access$400(Transit.this);
    }

    onditionData()
    {
        this$0 = Transit.this;
        super();
    }
}

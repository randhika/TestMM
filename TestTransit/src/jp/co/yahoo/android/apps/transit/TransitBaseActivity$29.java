// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit;

import android.content.DialogInterface;
import jp.co.yahoo.android.apps.transit.api.data.ConditionData;
import jp.co.yahoo.android.apps.transit.db.ResultDB;

// Referenced classes of package jp.co.yahoo.android.apps.transit:
//            TransitBaseActivity

class val.condition
    implements android.content.Listener
{

    final TransitBaseActivity this$0;
    final ConditionData val$condition;
    final ResultDB val$sql;

    public void onClick(DialogInterface dialoginterface, int i)
    {
        StringBuilder stringbuilder = new StringBuilder(getString(0x7f0d0560));
        stringbuilder.append(getString(0x7f0d043f)).append("/");
        stringbuilder.append(getString(0x7f0d0442)).append("/");
        if (i == 0)
        {
            stringbuilder.append(getString(0x7f0d03e6));
            touchRD(stringbuilder.toString());
            TransitBaseActivity.access$1100(TransitBaseActivity.this, val$sql, val$condition, 1, 0);
            return;
        } else
        {
            stringbuilder.append(getString(0x7f0d03e5));
            touchRD(stringbuilder.toString());
            TransitBaseActivity.access$1200(TransitBaseActivity.this, val$condition);
            return;
        }
    }

    ()
    {
        this$0 = final_transitbaseactivity;
        val$sql = resultdb;
        val$condition = ConditionData.this;
        super();
    }
}

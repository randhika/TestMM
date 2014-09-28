// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import jp.co.yahoo.android.apps.transit.api.data.ConditionData;
import jp.co.yahoo.android.apps.transit.api.data.NaviSearchData;
import jp.co.yahoo.android.apps.transit.common.Alarm;

// Referenced classes of package jp.co.yahoo.android.apps.transit:
//            AlarmDialog

public class AlarmReceiver extends BroadcastReceiver
{

    public AlarmReceiver()
    {
    }

    private void setAlarmAgain(Context context)
    {
        (new Alarm(context)).setAlarmAgain();
    }

    private void showAlertDialog(Context context, Intent intent)
    {
        ConditionData conditiondata = (ConditionData)intent.getSerializableExtra(context.getString(0x7f0d022c));
        NaviSearchData navisearchdata = (NaviSearchData)intent.getSerializableExtra(context.getString(0x7f0d0232));
        android.os.Bundle bundle = intent.getBundleExtra(context.getString(0x7f0d0234));
        Intent intent1 = new Intent(context, jp/co/yahoo/android/apps/transit/AlarmDialog);
        intent1.addFlags(0x10000000);
        intent1.putExtra(context.getString(0x7f0d022c), conditiondata);
        intent1.putExtra(context.getString(0x7f0d0232), navisearchdata);
        intent1.putExtra(context.getString(0x7f0d0234), bundle);
        context.startActivity(intent1);
    }

    public void onReceive(Context context, Intent intent)
    {
        String s = intent.getAction();
        if (s != null && s.equals("android.intent.action.BOOT_COMPLETED"))
        {
            setAlarmAgain(context);
            return;
        } else
        {
            showAlertDialog(context, intent);
            return;
        }
    }
}

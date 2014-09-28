// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.timer;

import android.content.DialogInterface;
import android.widget.TextView;
import jp.co.yahoo.android.apps.transit.timer.api.data.AlermData;

// Referenced classes of package jp.co.yahoo.android.apps.transit.timer:
//            SettingStartDetailActivity

class val.items
    implements android.content.r
{

    final SettingStartDetailActivity this$0;
    final String val$items[];

    public void onClick(DialogInterface dialoginterface, int i)
    {
        String s = val$items[i];
        SettingStartDetailActivity.access$000(SettingStartDetailActivity.this).setAlermLength(Integer.parseInt(s));
        SettingStartDetailActivity.access$900(SettingStartDetailActivity.this).setText((new StringBuilder()).append(s).append(getString(0x7f0d02f8)).toString());
        onCheck();
    }

    ()
    {
        this$0 = final_settingstartdetailactivity;
        val$items = _5B_Ljava.lang.String_3B_.this;
        super();
    }
}

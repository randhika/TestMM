// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.timer;

import android.content.DialogInterface;
import android.content.Intent;
import android.widget.TextView;
import jp.co.yahoo.android.apps.transit.timer.api.data.AlermData;

// Referenced classes of package jp.co.yahoo.android.apps.transit.timer:
//            SettingStartDetailActivity

class this._cls0
    implements android.content.er
{

    final SettingStartDetailActivity this$0;

    public void onClick(DialogInterface dialoginterface, int i)
    {
        if (i == 0)
        {
            Intent intent = new Intent("android.intent.action.RINGTONE_PICKER");
            startActivityForResult(intent, 1);
            return;
        } else
        {
            SettingStartDetailActivity.access$000(SettingStartDetailActivity.this).setSound(null);
            SettingStartDetailActivity.access$000(SettingStartDetailActivity.this).setSoundUri(null);
            SettingStartDetailActivity.access$700(SettingStartDetailActivity.this).setText(getString(0x7f0d04d2));
            return;
        }
    }

    I()
    {
        this$0 = SettingStartDetailActivity.this;
        super();
    }
}

// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.timer;

import android.widget.TextView;
import android.widget.TimePicker;
import jp.co.yahoo.android.apps.transit.timer.api.data.AlermData;

// Referenced classes of package jp.co.yahoo.android.apps.transit.timer:
//            SettingStartDetailActivity

class this._cls0
    implements android.app.tener
{

    final SettingStartDetailActivity this$0;

    public void onTimeSet(TimePicker timepicker, int i, int j)
    {
        SettingStartDetailActivity.access$000(SettingStartDetailActivity.this).setStartTime(60 * (i * 60) + j * 60);
        Object aobj[] = new Object[2];
        aobj[0] = Integer.valueOf(i);
        aobj[1] = Integer.valueOf(j);
        String s = String.format("%02d:%02d", aobj);
        SettingStartDetailActivity.access$300(SettingStartDetailActivity.this).setText(s);
        onCheck();
    }

    I()
    {
        this$0 = SettingStartDetailActivity.this;
        super();
    }
}

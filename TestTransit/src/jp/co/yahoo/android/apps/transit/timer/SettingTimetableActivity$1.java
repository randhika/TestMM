// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.timer;

import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.TimePicker;
import jp.co.yahoo.android.apps.transit.timer.api.data.AlermData;
import jp.co.yahoo.android.apps.transit.timer.util.CountdownUtil;

// Referenced classes of package jp.co.yahoo.android.apps.transit.timer:
//            SettingTimetableActivity

class this._cls0
    implements android.app.istener
{

    final SettingTimetableActivity this$0;

    public void onTimeSet(TimePicker timepicker, int i, int j)
    {
        SettingTimetableActivity.access$000(SettingTimetableActivity.this).setStartTime(60 * (i * 60) + j * 60);
        String s = getString(0x7f0d015f);
        int k = SettingTimetableActivity.access$000(SettingTimetableActivity.this).getStartTime() / 3600;
        int l = (SettingTimetableActivity.access$000(SettingTimetableActivity.this).getStartTime() % 3600) / 60;
        String s1 = (new StringBuilder()).append(s).append(CountdownUtil.getZeroNum(k)).append(":").append(CountdownUtil.getZeroNum(l)).toString();
        SettingTimetableActivity.access$100(SettingTimetableActivity.this).setText(s1);
        SettingTimetableActivity.access$000(SettingTimetableActivity.this).setSetting(true);
        SettingTimetableActivity.access$200(SettingTimetableActivity.this).setChecked(true);
        SettingTimetableActivity.access$300(SettingTimetableActivity.this);
    }

    ()
    {
        this$0 = SettingTimetableActivity.this;
        super();
    }
}

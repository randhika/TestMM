// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.timer;

import android.widget.TimePicker;
import jp.co.yahoo.android.apps.transit.timer.api.data.DivideData;

// Referenced classes of package jp.co.yahoo.android.apps.transit.timer:
//            SettingDivideActivity

class this._cls0
    implements android.app.etListener
{

    final SettingDivideActivity this$0;

    public void onTimeSet(TimePicker timepicker, int i, int j)
    {
        SettingDivideActivity.access$000(SettingDivideActivity.this).setDivideHour(i);
        SettingDivideActivity.access$000(SettingDivideActivity.this).setDivideMin(j);
        SettingDivideActivity.access$100(SettingDivideActivity.this);
    }

    ()
    {
        this$0 = SettingDivideActivity.this;
        super();
    }
}

// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.timer;

import android.content.DialogInterface;
import android.content.res.Resources;
import android.text.format.Time;
import jp.co.yahoo.android.apps.transit.timer.api.data.AlermData;
import jp.co.yahoo.android.apps.transit.timer.api.data.StationData;
import jp.co.yahoo.android.apps.transit.timer.api.data.TimeTableItemData;
import jp.co.yahoo.android.apps.transit.timer.common.Alerm;
import jp.co.yahoo.android.apps.transit.timer.common.CountdownPanelManager;
import jp.co.yahoo.android.apps.transit.timer.db.SettingDb;
import jp.co.yahoo.android.apps.transit.timer.viewparts.CountdownPanelView;

// Referenced classes of package jp.co.yahoo.android.apps.transit.timer:
//            CountdownActivity

class val.items
    implements android.content.ckListener
{

    final CountdownActivity this$0;
    final String val$items[];

    public void onClick(DialogInterface dialoginterface, int i)
    {
        String s = val$items[i];
        TimeTableItemData timetableitemdata = CountdownActivity.access$300(CountdownActivity.this).getTargetTime();
        int j = (60 * (60 * timetableitemdata.getHour()) + 60 * timetableitemdata.getMinute()) - 60 * Integer.parseInt(s);
        Time time = new Time("Asia/Tokyo");
        time.setToNow();
        int k = time.hour;
        if (k <= 3)
        {
            k += 24;
        }
        if (j <= 60 * (k * 60) + 60 * time.minute)
        {
            showErrorMessageDialog(getString(0x7f0d0101), getString(0x7f0d015e));
            return;
        }
        CountdownActivity.access$1800(CountdownActivity.this).delAlarmByTime(AlermData.TYPE_ALERT);
        if (CountdownActivity.access$200(CountdownActivity.this).getSettingType() == res.getInteger(0x7f0c0075) || CountdownActivity.access$200(CountdownActivity.this).getSettingType() == res.getInteger(0x7f0c0072))
        {
            CountdownActivity.access$200(CountdownActivity.this).setId(Integer.toString(CountdownActivity.access$500(CountdownActivity.this).addTempSetting(CountdownActivity.access$200(CountdownActivity.this))));
        }
        AlermData alermdata = new AlermData();
        String s1 = CountdownActivity.access$200(CountdownActivity.this).getRailname();
        String s2 = CountdownActivity.access$200(CountdownActivity.this).getDirname();
        String s3 = CountdownActivity.access$200(CountdownActivity.this).getName();
        if (s2 != null && !s2.equals(""))
        {
            s1 = (new StringBuilder()).append(s1).append(" ").append(s2).append(getString(0x7f0d0275)).append(" ").toString();
        }
        if (s3 != null && !s3.equals(""))
        {
            s1 = (new StringBuilder()).append(s1).append(" ").append(s3).append(getString(0x7f0d0304)).toString();
        }
        alermdata.setStartTime(60 * (60 * timetableitemdata.getHour()) + 60 * timetableitemdata.getMinute());
        alermdata.setTimetableId(Integer.parseInt(CountdownActivity.access$200(CountdownActivity.this).getId()));
        alermdata.setRepeat("0");
        alermdata.setSetting(true);
        alermdata.setType(AlermData.TYPE_ALERT);
        alermdata.setLine(s1);
        alermdata.setCountdownTime(Integer.parseInt(s));
        CountdownActivity.access$1800(CountdownActivity.this).setAlerm(alermdata, false);
        CountdownActivity.access$2002(CountdownActivity.this, CountdownActivity.access$1800(CountdownActivity.this).getCountdownAlerm());
        if (CountdownActivity.access$300(CountdownActivity.this).getNowPanel() != null)
        {
            CountdownActivity.access$300(CountdownActivity.this).getNowPanel().setAlertLabel(true);
        }
        CountdownActivity.access$300(CountdownActivity.this).setAlermTime(alermdata.getStartTime());
        CountdownActivity.access$1900(CountdownActivity.this);
    }

    nelView()
    {
        this$0 = final_countdownactivity;
        val$items = _5B_Ljava.lang.String_3B_.this;
        super();
    }
}

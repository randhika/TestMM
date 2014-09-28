// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.timer;

import android.content.DialogInterface;
import android.os.Bundle;
import jp.co.yahoo.android.apps.transit.timer.api.data.AlermData;
import jp.co.yahoo.android.apps.transit.timer.api.data.StationData;
import jp.co.yahoo.android.apps.transit.timer.common.Alerm;
import jp.co.yahoo.android.apps.transit.timer.db.SettingDb;

// Referenced classes of package jp.co.yahoo.android.apps.transit.timer:
//            CountdownActivity

class val.sFilter
    implements android.content.ckListener
{

    final CountdownActivity this$0;
    final AlermData val$alermdata;
    final boolean val$bDest;
    final String val$sFilter;

    public void onClick(DialogInterface dialoginterface, int i)
    {
        CountdownActivity.access$1800(CountdownActivity.this).delAlarm(val$alermdata);
        CountdownActivity.access$1900(CountdownActivity.this);
        Bundle bundle = CountdownActivity.access$200(CountdownActivity.this).getTimetable();
        if (val$bDest)
        {
            bundle.putString("filter_dest", val$sFilter);
        } else
        {
            bundle.putString("filter_kind", val$sFilter);
        }
        CountdownActivity.access$200(CountdownActivity.this).setTimetable(bundle);
        CountdownActivity.access$500(CountdownActivity.this).updateTimetable(CountdownActivity.access$200(CountdownActivity.this), bundle);
        showCountdown();
    }

    A()
    {
        this$0 = final_countdownactivity;
        val$alermdata = alermdata1;
        val$bDest = flag;
        val$sFilter = String.this;
        super();
    }
}

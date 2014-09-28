// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit;

import android.os.Bundle;
import jp.co.yahoo.android.apps.transit.timer.api.data.StationData;
import jp.co.yahoo.android.apps.transit.timer.api.data.TimeTableItemData;
import jp.co.yahoo.android.apps.transit.timer.common.CountdownManager;

// Referenced classes of package jp.co.yahoo.android.apps.transit:
//            WearUpdateService

class onData
    implements jp.co.yahoo.android.apps.transit.timer.common.ntdownListener
{

    final WearUpdateService this$0;
    final CountdownManager val$countdown;
    final String val$path;
    final StationData val$station;

    public boolean changeWeek(int i)
    {
        return false;
    }

    public boolean end(TimeTableItemData timetableitemdata)
    {
        return false;
    }

    public boolean onAllFiltered()
    {
        Bundle bundle = new Bundle();
        WearUpdateService.access$000(WearUpdateService.this, val$path, val$countdown.getWeek(), val$countdown.getIsTomorrow(), bundle, val$station);
        return false;
    }

    public boolean onNoTimetable()
    {
        WearUpdateService.access$000(WearUpdateService.this, val$path, val$countdown.getWeek(), val$countdown.getIsTomorrow(), null, val$station);
        return false;
    }

    public void updateTarget(TimeTableItemData timetableitemdata)
    {
    }

    public void updateTime(int i)
    {
    }

    onData()
    {
        this$0 = final_wearupdateservice;
        val$path = s;
        val$countdown = countdownmanager;
        val$station = StationData.this;
        super();
    }
}

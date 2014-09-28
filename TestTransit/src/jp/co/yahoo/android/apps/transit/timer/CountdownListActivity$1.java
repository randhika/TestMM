// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.timer;

import jp.co.yahoo.android.apps.transit.timer.api.data.TimeTableItemData;

// Referenced classes of package jp.co.yahoo.android.apps.transit.timer:
//            CountdownListActivity

class this._cls0
    implements jp.co.yahoo.android.apps.transit.timer.common.wnListener
{

    final CountdownListActivity this$0;

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
        return false;
    }

    public boolean onNoTimetable()
    {
        return false;
    }

    public void updateTarget(TimeTableItemData timetableitemdata)
    {
    }

    public void updateTime(int i)
    {
    }

    ta()
    {
        this$0 = CountdownListActivity.this;
        super();
    }
}

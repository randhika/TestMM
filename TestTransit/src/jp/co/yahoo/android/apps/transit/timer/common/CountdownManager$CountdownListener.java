// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.timer.common;

import jp.co.yahoo.android.apps.transit.timer.api.data.TimeTableItemData;

// Referenced classes of package jp.co.yahoo.android.apps.transit.timer.common:
//            CountdownManager

public static interface 
{

    public abstract boolean changeWeek(int i);

    public abstract boolean end(TimeTableItemData timetableitemdata);

    public abstract boolean onAllFiltered();

    public abstract boolean onNoTimetable();

    public abstract void updateTarget(TimeTableItemData timetableitemdata);

    public abstract void updateTime(int i);
}

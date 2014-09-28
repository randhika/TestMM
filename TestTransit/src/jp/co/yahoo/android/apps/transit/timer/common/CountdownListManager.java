// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.timer.common;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import java.util.ArrayList;
import jp.co.yahoo.android.apps.transit.timer.api.data.TimeTableItemData;
import jp.co.yahoo.android.apps.transit.timer.util.CountdownUtil;
import jp.co.yahoo.android.apps.transit.timer.viewparts.CountdownListView;

// Referenced classes of package jp.co.yahoo.android.apps.transit.timer.common:
//            CountdownManager

public class CountdownListManager extends CountdownManager
{

    ArrayList ListViews;
    LinearLayout panelList;
    private int sameTrainNum;
    private int type;

    public CountdownListManager(Context context, LinearLayout linearlayout, CountdownManager.CountdownListener countdownlistener)
    {
        super(context);
        type = 1;
        panelList = linearlayout;
    }

    private void updateListAllData()
    {
        panelList.removeAllViews();
        sameTrainNum = isSameTrain();
        TimeTableItemData timetableitemdata = (TimeTableItemData)objNowTimetable.getSerializable(Integer.toString(nCurrentIndex));
        int i = 60 * (60 * timetableitemdata.getHour()) + 60 * timetableitemdata.getMinute();
        int j = CountdownUtil.getNowTimeSec();
        if (bTommorow && j > i)
        {
            j -= 0x15180;
        }
        int k = 10;
        int l = nMaxCount - nCurrentIndex;
        if (l < k)
        {
            k = l;
        }
        int i1 = 0;
        while (i1 < k) 
        {
            LinearLayout linearlayout;
            CountdownListView countdownlistview;
            TimeTableItemData timetableitemdata1;
            if (panelList.getChildAt(i1) == null)
            {
                linearlayout = (LinearLayout)((LayoutInflater)context.getSystemService("layout_inflater")).inflate(0x7f030063, null);
            } else
            {
                linearlayout = (LinearLayout)panelList.getChildAt(i1);
            }
            countdownlistview = new CountdownListView(context, i1 + nCurrentIndex, type, nWeek, linearlayout);
            timetableitemdata1 = (TimeTableItemData)objNowTimetable.getSerializable(Integer.toString(i1 + nCurrentIndex));
            countdownlistview.setNumber(i1, sameTrainNum);
            countdownlistview.setTimetable(timetableitemdata1);
            countdownlistview.update(j);
            panelList.addView(countdownlistview.getList(), i1);
            i1++;
        }
    }

    public void changeTarget(int i)
    {
    }

    public void setCountDown(Bundle bundle)
    {
        super.setCountDown(bundle);
        updateListAllData();
    }

    public void setType(int i)
    {
        type = i;
    }

    public void updateTime(int i)
    {
        sameTrainNum = isSameTrain();
        if (i % 60 == 0)
        {
            updateListAllData();
        } else
        {
            int j = 0;
            while (j < sameTrainNum) 
            {
                android.view.View view = panelList.getChildAt(j);
                if (view != null)
                {
                    CountdownListView countdownlistview = new CountdownListView(context, j + nCurrentIndex, type, nWeek, (LinearLayout)view);
                    TimeTableItemData timetableitemdata = (TimeTableItemData)objNowTimetable.getSerializable(Integer.toString(j + nCurrentIndex));
                    countdownlistview.setNumber(j, sameTrainNum);
                    countdownlistview.setTimetable(timetableitemdata);
                    countdownlistview.update(i);
                    panelList.removeViewAt(j);
                    panelList.addView(countdownlistview.getList(), j);
                }
                j++;
            }
        }
    }
}

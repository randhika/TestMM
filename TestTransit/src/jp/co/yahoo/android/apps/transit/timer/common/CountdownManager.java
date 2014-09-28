// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.timer.common;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.format.Time;
import jp.co.yahoo.android.apps.transit.timer.api.data.TimeTableItemData;
import jp.co.yahoo.android.apps.transit.timer.util.CountdownUtil;

public class CountdownManager
{
    public static interface CountdownListener
    {

        public abstract boolean changeWeek(int i);

        public abstract boolean end(TimeTableItemData timetableitemdata);

        public abstract boolean onAllFiltered();

        public abstract boolean onNoTimetable();

        public abstract void updateTarget(TimeTableItemData timetableitemdata);

        public abstract void updateTime(int i);
    }


    protected boolean bTommorow;
    protected boolean bWeek;
    protected Context context;
    protected Handler handler;
    protected CountdownListener listener;
    protected int nAlerm;
    protected int nCurrentIndex;
    protected int nMaxCount;
    protected int nPreviousTime;
    protected int nWeek;
    protected TimeTableItemData objCurrentTime;
    protected Bundle objNowTimetable;
    protected Time objTarget;
    protected Bundle objTimetables;

    public CountdownManager(Context context1)
    {
        context = null;
        objTimetables = null;
        objNowTimetable = null;
        nWeek = 1;
        bWeek = false;
        bTommorow = false;
        nCurrentIndex = 0;
        nAlerm = -1;
        nPreviousTime = 0;
        objCurrentTime = null;
        objTarget = null;
        nMaxCount = 0;
        listener = null;
        context = context1;
    }

    public CountdownManager(Context context1, CountdownListener countdownlistener)
    {
        context = null;
        objTimetables = null;
        objNowTimetable = null;
        nWeek = 1;
        bWeek = false;
        bTommorow = false;
        nCurrentIndex = 0;
        nAlerm = -1;
        nPreviousTime = 0;
        objCurrentTime = null;
        objTarget = null;
        nMaxCount = 0;
        listener = null;
        context = context1;
        listener = countdownlistener;
    }

    private int getIndexNum(Time time)
    {
        byte byte0;
        if (objNowTimetable == null)
        {
            byte0 = -1;
        } else
        {
            int i = time.hour;
            if (i <= 3)
            {
                i += 24;
            }
            int j = i * 60 + time.minute;
            byte0 = -1;
            int k = 0;
            while (k < objNowTimetable.size()) 
            {
                TimeTableItemData timetableitemdata = (TimeTableItemData)objNowTimetable.getSerializable(Integer.toString(k));
                if (60 * timetableitemdata.getHour() + timetableitemdata.getMinute() > j)
                {
                    return k;
                }
                k++;
            }
        }
        return byte0;
    }

    private void getNowTimetable()
    {
        if (objTimetables == null || objTimetables.size() <= 0) goto _L2; else goto _L1
_L1:
        if (nWeek < 0)
        {
            nWeek = CountdownUtil.getNowWeek(context);
        }
        objNowTimetable = objTimetables.getBundle(Integer.toString(nWeek)).getBundle("timetable");
        if (objNowTimetable != null && objNowTimetable.size() >= 1) goto _L4; else goto _L3
_L3:
        nMaxCount = 1;
        if (listener != null)
        {
            listener.onNoTimetable();
        }
_L2:
        return;
_L4:
        String s;
        String s1;
        s = objTimetables.getString("filter_dest");
        s1 = objTimetables.getString("filter_kind");
        objNowTimetable = CountdownUtil.filter(objNowTimetable, s, s1);
        if (objNowTimetable != null && objNowTimetable.size() >= 1)
        {
            break; /* Loop/switch isn't completed */
        }
        nMaxCount = 1;
        if (listener != null)
        {
            listener.onAllFiltered();
            return;
        }
        if (true) goto _L2; else goto _L5
_L5:
        if (objTarget == null)
        {
            objTarget = new Time("Asia/Tokyo");
            objTarget.setToNow();
        }
        nCurrentIndex = getIndexNum(objTarget);
        if (bWeek || nCurrentIndex != -1)
        {
            break; /* Loop/switch isn't completed */
        }
label0:
        {
            int i = CountdownUtil.getNowWeek(1, true, context);
            if (i == nWeek)
            {
                break label0;
            }
            nWeek = i;
            objNowTimetable = objTimetables.getBundle(Integer.toString(nWeek)).getBundle("timetable");
            if (objNowTimetable == null || objNowTimetable.size() < 1)
            {
                nMaxCount = 1;
                if (listener != null)
                {
                    listener.onNoTimetable();
                    return;
                }
            } else
            {
                objNowTimetable = CountdownUtil.filter(objNowTimetable, s, s1);
                if (objNowTimetable != null && objNowTimetable.size() >= 1)
                {
                    break label0;
                }
                nMaxCount = 1;
                if (listener != null)
                {
                    listener.onAllFiltered();
                    return;
                }
            }
        }
        if (true) goto _L2; else goto _L6
        bTommorow = true;
        nCurrentIndex = 0;
_L8:
        nMaxCount = objNowTimetable.size();
        objCurrentTime = (TimeTableItemData)objNowTimetable.getSerializable(Integer.toString(nCurrentIndex));
        return;
_L6:
        if (nCurrentIndex == -1)
        {
            bTommorow = true;
            nCurrentIndex = 0;
        }
        if (true) goto _L8; else goto _L7
_L7:
    }

    public void changeEnd()
    {
        changeTargetIndex(-1 + nMaxCount);
    }

    public void changeNext()
    {
        changeTargetIndex(1 + nCurrentIndex);
    }

    public void changeNow()
    {
        Time time = new Time("Asia/Tokyo");
        time.setToNow();
        int i = getIndexNum(time);
        if (i != -1)
        {
            changeTargetIndex(i);
        }
    }

    public void changePrev()
    {
        changeTargetIndex(-1 + nCurrentIndex);
    }

    public void changeTarget(int i)
    {
    }

    public void changeTargetIndex(int i)
    {
        TimeTableItemData timetableitemdata;
        if (objNowTimetable != null)
        {
            if ((timetableitemdata = (TimeTableItemData)objNowTimetable.getSerializable(Integer.toString(i))) != null)
            {
                nCurrentIndex = i;
                objCurrentTime = timetableitemdata;
                if (listener != null)
                {
                    listener.updateTarget(objCurrentTime);
                }
                changeTarget(nCurrentIndex);
                return;
            }
        }
    }

    public boolean getIsTomorrow()
    {
        return bTommorow;
    }

    public int getMaxCount()
    {
        return nMaxCount;
    }

    public Bundle getNowTimetables()
    {
        return objNowTimetable;
    }

    public TimeTableItemData getTargetTime()
    {
        return objCurrentTime;
    }

    public Bundle getTodayTimetables()
    {
        return objTimetables.getBundle(Integer.toString(nWeek));
    }

    public int getWeek()
    {
        return nWeek;
    }

    public int isSameTrain()
    {
        int i = 1;
        do
        {
            if (i >= 6)
            {
                break;
            }
            TimeTableItemData timetableitemdata = (TimeTableItemData)objNowTimetable.getSerializable(Integer.toString(i + nCurrentIndex));
            if (timetableitemdata != null && (timetableitemdata.getMinute() != objCurrentTime.getMinute() || timetableitemdata.getHour() != objCurrentTime.getHour()))
            {
                break;
            }
            i++;
        } while (true);
        return i;
    }

    public void resetCountDown(Bundle bundle)
    {
        objNowTimetable = null;
        bTommorow = false;
        nCurrentIndex = 0;
        nAlerm = -1;
        nPreviousTime = 0;
        objCurrentTime = null;
        nMaxCount = 0;
        setCountDown(bundle);
    }

    public void setAlermTime(int i)
    {
        nAlerm = i;
    }

    public void setCountDown(Bundle bundle)
    {
        objTimetables = bundle;
        getNowTimetable();
    }

    public void setIsTomorrow(boolean flag)
    {
        bTommorow = flag;
    }

    public void setListener(CountdownListener countdownlistener)
    {
        listener = countdownlistener;
    }

    public void setTime(Time time)
    {
        objTarget = time;
    }

    public void setWeek(int i)
    {
        if (i >= 0)
        {
            bWeek = true;
        }
        nWeek = i;
    }

    public void startCountDown()
    {
        if (objCurrentTime == null)
        {
            nCurrentIndex = 0;
            if (objNowTimetable != null && objNowTimetable.size() > 0)
            {
                objCurrentTime = (TimeTableItemData)objNowTimetable.getSerializable(Integer.toString(0));
            }
        }
        changeTarget(nCurrentIndex);
        updateTime();
        handler = new Handler() {

            final CountdownManager this$0;

            public void handleMessage(Message message1)
            {
                updateTime();
                if (handler != null)
                {
                    handler.sendMessageDelayed(obtainMessage(), 1000L);
                }
            }

            
            {
                this$0 = CountdownManager.this;
                super();
            }
        };
        Message message = new Message();
        message.what = 100;
        handler.sendMessageDelayed(message, 1000L);
        if (listener != null)
        {
            listener.updateTarget(objCurrentTime);
        }
    }

    public void stop()
    {
        handler = null;
    }

    public int updateTime()
    {
        int i = CountdownUtil.getNowTimeSec();
        if (objCurrentTime != null)
        {
            int j = 60 * (60 * objCurrentTime.getHour()) + 60 * objCurrentTime.getMinute();
            if (i == j)
            {
                int k = isSameTrain();
                if (k > 1)
                {
                    changeTargetIndex(k + nCurrentIndex);
                } else
                {
                    changeNext();
                }
            }
            if (bTommorow && i > j)
            {
                i -= 0x15180;
            }
            updateTime(i);
        }
        if (listener != null)
        {
            listener.updateTime(i);
        }
        return i;
    }

    public void updateTime(int i)
    {
    }

    public int updateTimeEx()
    {
        int i = CountdownUtil.getNowTimeSec();
        int j = i;
        if (objCurrentTime != null)
        {
            int l = 60 * (60 * objCurrentTime.getHour()) + 60 * objCurrentTime.getMinute();
            if (i == l)
            {
                int i1 = isSameTrain();
                int k;
                if (i1 > 1)
                {
                    changeTargetIndex(i1 + nCurrentIndex);
                } else
                {
                    changeNext();
                }
                if (l == 60 * (60 * objCurrentTime.getHour()) + 60 * objCurrentTime.getMinute())
                {
                    objCurrentTime = null;
                }
            }
            if (bTommorow)
            {
                if (i > l)
                {
                    i -= 0x15180;
                }
                if (nPreviousTime < i && i >= 14400)
                {
                    objCurrentTime = null;
                }
            }
            if (i > l)
            {
                objCurrentTime = null;
            }
        }
        if (objCurrentTime == null)
        {
            nWeek = -1;
            objTarget = null;
            bTommorow = false;
            getNowTimetable();
        }
        if (objCurrentTime != null)
        {
            k = 60 * (60 * objCurrentTime.getHour()) + 60 * objCurrentTime.getMinute();
            i = j;
            if (bTommorow && i > k)
            {
                i -= 0x15180;
            }
            updateTime(i);
        }
        if (listener != null)
        {
            listener.updateTime(i);
        }
        nPreviousTime = i;
        return i;
    }
}

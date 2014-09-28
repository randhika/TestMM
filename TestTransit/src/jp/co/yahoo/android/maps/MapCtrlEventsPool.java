// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.maps;

import android.view.KeyEvent;
import android.view.MotionEvent;
import java.util.Vector;

// Referenced classes of package jp.co.yahoo.android.maps:
//            MapCtrlEvent

public class MapCtrlEventsPool
{

    private static final int INIT_POOLING_COUNT = 20;
    private MapCtrlEvent.EventType b_type;
    private final Vector mEmptyEvents = new Vector();
    private final Vector mReadyEvents = new Vector();
    private final Vector mRunningEvents = new Vector();

    public MapCtrlEventsPool()
    {
        int i = 0;
        do
        {
            if (i >= 20)
            {
                return;
            }
            mEmptyEvents.add(new MapCtrlEvent());
            i++;
        } while (true);
    }

    private MapCtrlEvent pollEmptyEvent()
    {
        Vector vector = mEmptyEvents;
        vector;
        JVM INSTR monitorenter ;
        int i = mEmptyEvents.size();
        MapCtrlEvent mapctrlevent;
        mapctrlevent = null;
        if (i <= 0)
        {
            break MISSING_BLOCK_LABEL_35;
        }
        mapctrlevent = (MapCtrlEvent)mEmptyEvents.remove(0);
        vector;
        JVM INSTR monitorexit ;
        if (mapctrlevent == null)
        {
            mapctrlevent = new MapCtrlEvent();
        }
        return mapctrlevent;
        Exception exception;
        exception;
        vector;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public void pollReadyEvents(Vector vector)
    {
        Vector vector1 = mReadyEvents;
        vector1;
        JVM INSTR monitorenter ;
        int i = 0;
_L2:
        if (i < mReadyEvents.size())
        {
            break MISSING_BLOCK_LABEL_23;
        }
        vector1;
        JVM INSTR monitorexit ;
        return;
        MapCtrlEvent mapctrlevent = (MapCtrlEvent)mReadyEvents.remove(0);
        vector.add(mapctrlevent);
        mRunningEvents.add(mapctrlevent);
        i++;
        if (true) goto _L2; else goto _L1
_L1:
        Exception exception;
        exception;
        vector1;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public void push(MapCtrlEvent.EventType eventtype, double d, double d1)
    {
        MapCtrlEvent mapctrlevent = pollEmptyEvent();
        mapctrlevent.type = eventtype;
        mapctrlevent.point_x = d;
        mapctrlevent.point_y = d1;
        mapctrlevent.eventTime = System.currentTimeMillis();
        mReadyEvents.add(mapctrlevent);
    }

    public void push(MapCtrlEvent.EventType eventtype, double d, double d1, float f)
    {
        MapCtrlEvent mapctrlevent = pollEmptyEvent();
        mapctrlevent.type = eventtype;
        mapctrlevent.point_x = d;
        mapctrlevent.point_y = d1;
        mapctrlevent.factor = f;
        mapctrlevent.eventTime = System.currentTimeMillis();
        mReadyEvents.add(mapctrlevent);
    }

    public void push(MapCtrlEvent.EventType eventtype, int i, KeyEvent keyevent)
    {
        MapCtrlEvent mapctrlevent = pollEmptyEvent();
        mapctrlevent.keyCode = i;
        mapctrlevent.keyevent = keyevent;
        mapctrlevent.type = eventtype;
        mapctrlevent.eventTime = System.currentTimeMillis();
        mReadyEvents.add(mapctrlevent);
    }

    public void push(MapCtrlEvent.EventType eventtype, MotionEvent motionevent)
    {
        MapCtrlEvent mapctrlevent = pollEmptyEvent();
        mapctrlevent.type = eventtype;
        mapctrlevent.motionevent = motionevent;
        mapctrlevent.eventTime = System.currentTimeMillis();
        mReadyEvents.add(mapctrlevent);
    }

    public void push(MapCtrlEvent.EventType eventtype, Object obj)
    {
        b_type = eventtype;
        MapCtrlEvent mapctrlevent = pollEmptyEvent();
        mapctrlevent.type = eventtype;
        mapctrlevent.arg1 = obj;
        mapctrlevent.eventTime = System.currentTimeMillis();
        mReadyEvents.add(mapctrlevent);
    }

    public void releaseFinishedEvent(MapCtrlEvent mapctrlevent)
    {
        if (mRunningEvents.remove(mapctrlevent))
        {
            mapctrlevent.clear();
            mEmptyEvents.add(mapctrlevent);
        }
    }
}

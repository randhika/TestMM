// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.maps;

import android.view.KeyEvent;
import android.view.MotionEvent;

public class MapCtrlEvent
{
    public static final class EventType extends Enum
    {

        private static final EventType ENUM$VALUES[];
        public static final EventType EVENT_MAX;
        public static final EventType KEY_DOWN;
        public static final EventType KEY_UP;
        public static final EventType ON_DESTROY;
        public static final EventType ON_DOUBLETAP;
        public static final EventType ON_PINCH;
        public static final EventType ON_PINCH_FINISH;
        public static final EventType ON_SINGLETAPCONFIRMED;
        public static final EventType ON_SIZECHANGED;
        public static final EventType ON_TAP;
        public static final EventType ON_TIMER;
        public static final EventType ON_TOUCH;
        public static final EventType ON_TRACKBALLEVENT;
        public static final EventType ON_TWOTAP;
        public static final EventType RESET_MAP;
        public static final EventType SET_ANIMETETO;
        public static final EventType SET_ATTESTATION;
        public static final EventType SET_CENTER;
        public static final EventType SET_COPYRIGHT;
        public static final EventType SET_FLICK;
        public static final EventType SET_MOVE;
        public static final EventType SET_ZOOM;
        public static final EventType UPDATE_YML;

        public static EventType valueOf(String s)
        {
            return (EventType)Enum.valueOf(jp/co/yahoo/android/maps/MapCtrlEvent$EventType, s);
        }

        public static EventType[] values()
        {
            EventType aeventtype[] = ENUM$VALUES;
            int i = aeventtype.length;
            EventType aeventtype1[] = new EventType[i];
            System.arraycopy(aeventtype, 0, aeventtype1, 0, i);
            return aeventtype1;
        }

        static 
        {
            KEY_UP = new EventType("KEY_UP", 0);
            KEY_DOWN = new EventType("KEY_DOWN", 1);
            ON_TOUCH = new EventType("ON_TOUCH", 2);
            ON_TRACKBALLEVENT = new EventType("ON_TRACKBALLEVENT", 3);
            ON_SINGLETAPCONFIRMED = new EventType("ON_SINGLETAPCONFIRMED", 4);
            ON_TAP = new EventType("ON_TAP", 5);
            ON_DOUBLETAP = new EventType("ON_DOUBLETAP", 6);
            ON_TWOTAP = new EventType("ON_TWOTAP", 7);
            ON_TIMER = new EventType("ON_TIMER", 8);
            ON_SIZECHANGED = new EventType("ON_SIZECHANGED", 9);
            ON_PINCH = new EventType("ON_PINCH", 10);
            ON_PINCH_FINISH = new EventType("ON_PINCH_FINISH", 11);
            SET_CENTER = new EventType("SET_CENTER", 12);
            SET_ZOOM = new EventType("SET_ZOOM", 13);
            SET_MOVE = new EventType("SET_MOVE", 14);
            SET_FLICK = new EventType("SET_FLICK", 15);
            SET_COPYRIGHT = new EventType("SET_COPYRIGHT", 16);
            SET_ATTESTATION = new EventType("SET_ATTESTATION", 17);
            SET_ANIMETETO = new EventType("SET_ANIMETETO", 18);
            UPDATE_YML = new EventType("UPDATE_YML", 19);
            RESET_MAP = new EventType("RESET_MAP", 20);
            ON_DESTROY = new EventType("ON_DESTROY", 21);
            EVENT_MAX = new EventType("EVENT_MAX", 22);
            EventType aeventtype[] = new EventType[23];
            aeventtype[0] = KEY_UP;
            aeventtype[1] = KEY_DOWN;
            aeventtype[2] = ON_TOUCH;
            aeventtype[3] = ON_TRACKBALLEVENT;
            aeventtype[4] = ON_SINGLETAPCONFIRMED;
            aeventtype[5] = ON_TAP;
            aeventtype[6] = ON_DOUBLETAP;
            aeventtype[7] = ON_TWOTAP;
            aeventtype[8] = ON_TIMER;
            aeventtype[9] = ON_SIZECHANGED;
            aeventtype[10] = ON_PINCH;
            aeventtype[11] = ON_PINCH_FINISH;
            aeventtype[12] = SET_CENTER;
            aeventtype[13] = SET_ZOOM;
            aeventtype[14] = SET_MOVE;
            aeventtype[15] = SET_FLICK;
            aeventtype[16] = SET_COPYRIGHT;
            aeventtype[17] = SET_ATTESTATION;
            aeventtype[18] = SET_ANIMETETO;
            aeventtype[19] = UPDATE_YML;
            aeventtype[20] = RESET_MAP;
            aeventtype[21] = ON_DESTROY;
            aeventtype[22] = EVENT_MAX;
            ENUM$VALUES = aeventtype;
        }

        private EventType(String s, int i)
        {
            super(s, i);
        }
    }


    public Object arg1;
    long eventTime;
    public float factor;
    public int keyCode;
    public KeyEvent keyevent;
    public MotionEvent motionevent;
    public double point_x;
    public double point_y;
    public EventType type;

    public MapCtrlEvent()
    {
        arg1 = null;
        factor = 0.0F;
        point_x = 0.0D;
        point_y = 0.0D;
        clear();
    }

    public void clear()
    {
        keyCode = 0;
        keyevent = null;
        type = null;
        motionevent = null;
        arg1 = null;
        eventTime = 0L;
        factor = 0.0F;
        point_x = 0.0D;
        point_y = 0.0D;
    }

    public EventType getEventType()
    {
        return type;
    }
}

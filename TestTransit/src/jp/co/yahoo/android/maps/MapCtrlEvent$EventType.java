// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.maps;


// Referenced classes of package jp.co.yahoo.android.maps:
//            MapCtrlEvent

public static final class  extends Enum
{

    private static final ENUM.VALUES ENUM$VALUES[];
    public static final ENUM.VALUES EVENT_MAX;
    public static final ENUM.VALUES KEY_DOWN;
    public static final ENUM.VALUES KEY_UP;
    public static final ENUM.VALUES ON_DESTROY;
    public static final ENUM.VALUES ON_DOUBLETAP;
    public static final ENUM.VALUES ON_PINCH;
    public static final ENUM.VALUES ON_PINCH_FINISH;
    public static final ENUM.VALUES ON_SINGLETAPCONFIRMED;
    public static final ENUM.VALUES ON_SIZECHANGED;
    public static final ENUM.VALUES ON_TAP;
    public static final ENUM.VALUES ON_TIMER;
    public static final ENUM.VALUES ON_TOUCH;
    public static final ENUM.VALUES ON_TRACKBALLEVENT;
    public static final ENUM.VALUES ON_TWOTAP;
    public static final ENUM.VALUES RESET_MAP;
    public static final ENUM.VALUES SET_ANIMETETO;
    public static final ENUM.VALUES SET_ATTESTATION;
    public static final ENUM.VALUES SET_CENTER;
    public static final ENUM.VALUES SET_COPYRIGHT;
    public static final ENUM.VALUES SET_FLICK;
    public static final ENUM.VALUES SET_MOVE;
    public static final ENUM.VALUES SET_ZOOM;
    public static final ENUM.VALUES UPDATE_YML;

    public static  valueOf(String s)
    {
        return ()Enum.valueOf(jp/co/yahoo/android/maps/MapCtrlEvent$EventType, s);
    }

    public static [] values()
    {
         a[] = ENUM$VALUES;
        int i = a.length;
         a1[] = new ENUM.VALUES[i];
        System.arraycopy(a, 0, a1, 0, i);
        return a1;
    }

    static 
    {
        KEY_UP = new <init>("KEY_UP", 0);
        KEY_DOWN = new <init>("KEY_DOWN", 1);
        ON_TOUCH = new <init>("ON_TOUCH", 2);
        ON_TRACKBALLEVENT = new <init>("ON_TRACKBALLEVENT", 3);
        ON_SINGLETAPCONFIRMED = new <init>("ON_SINGLETAPCONFIRMED", 4);
        ON_TAP = new <init>("ON_TAP", 5);
        ON_DOUBLETAP = new <init>("ON_DOUBLETAP", 6);
        ON_TWOTAP = new <init>("ON_TWOTAP", 7);
        ON_TIMER = new <init>("ON_TIMER", 8);
        ON_SIZECHANGED = new <init>("ON_SIZECHANGED", 9);
        ON_PINCH = new <init>("ON_PINCH", 10);
        ON_PINCH_FINISH = new <init>("ON_PINCH_FINISH", 11);
        SET_CENTER = new <init>("SET_CENTER", 12);
        SET_ZOOM = new <init>("SET_ZOOM", 13);
        SET_MOVE = new <init>("SET_MOVE", 14);
        SET_FLICK = new <init>("SET_FLICK", 15);
        SET_COPYRIGHT = new <init>("SET_COPYRIGHT", 16);
        SET_ATTESTATION = new <init>("SET_ATTESTATION", 17);
        SET_ANIMETETO = new <init>("SET_ANIMETETO", 18);
        UPDATE_YML = new <init>("UPDATE_YML", 19);
        RESET_MAP = new <init>("RESET_MAP", 20);
        ON_DESTROY = new <init>("ON_DESTROY", 21);
        EVENT_MAX = new <init>("EVENT_MAX", 22);
        ENUM.VALUES avalues[] = new <init>[23];
        avalues[0] = KEY_UP;
        avalues[1] = KEY_DOWN;
        avalues[2] = ON_TOUCH;
        avalues[3] = ON_TRACKBALLEVENT;
        avalues[4] = ON_SINGLETAPCONFIRMED;
        avalues[5] = ON_TAP;
        avalues[6] = ON_DOUBLETAP;
        avalues[7] = ON_TWOTAP;
        avalues[8] = ON_TIMER;
        avalues[9] = ON_SIZECHANGED;
        avalues[10] = ON_PINCH;
        avalues[11] = ON_PINCH_FINISH;
        avalues[12] = SET_CENTER;
        avalues[13] = SET_ZOOM;
        avalues[14] = SET_MOVE;
        avalues[15] = SET_FLICK;
        avalues[16] = SET_COPYRIGHT;
        avalues[17] = SET_ATTESTATION;
        avalues[18] = SET_ANIMETETO;
        avalues[19] = UPDATE_YML;
        avalues[20] = RESET_MAP;
        avalues[21] = ON_DESTROY;
        avalues[22] = EVENT_MAX;
        ENUM$VALUES = avalues;
    }

    private (String s, int i)
    {
        super(s, i);
    }
}

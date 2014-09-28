// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.yjvoice;


public final class YJVO_STATE extends Enum
{

    private static final YJVO_STATE $VALUES[];
    public static final YJVO_STATE PHRASE_FINISH;
    public static final YJVO_STATE PHRASE_START;
    public static final YJVO_STATE RECOGNIZE_CANCEL;
    public static final YJVO_STATE RECOGNIZE_ERROR;
    public static final YJVO_STATE RECOGNIZE_FINISH;
    public static final YJVO_STATE VOICE_TOO_LONG;

    private YJVO_STATE(String s, int i)
    {
        super(s, i);
    }

    public static YJVO_STATE valueOf(String s)
    {
        return (YJVO_STATE)Enum.valueOf(jp/co/yahoo/android/yjvoice/YJVO_STATE, s);
    }

    public static YJVO_STATE[] values()
    {
        return (YJVO_STATE[])$VALUES.clone();
    }

    static 
    {
        PHRASE_START = new YJVO_STATE("PHRASE_START", 0);
        PHRASE_FINISH = new YJVO_STATE("PHRASE_FINISH", 1);
        RECOGNIZE_FINISH = new YJVO_STATE("RECOGNIZE_FINISH", 2);
        RECOGNIZE_CANCEL = new YJVO_STATE("RECOGNIZE_CANCEL", 3);
        RECOGNIZE_ERROR = new YJVO_STATE("RECOGNIZE_ERROR", 4);
        VOICE_TOO_LONG = new YJVO_STATE("VOICE_TOO_LONG", 5);
        YJVO_STATE ayjvo_state[] = new YJVO_STATE[6];
        ayjvo_state[0] = PHRASE_START;
        ayjvo_state[1] = PHRASE_FINISH;
        ayjvo_state[2] = RECOGNIZE_FINISH;
        ayjvo_state[3] = RECOGNIZE_CANCEL;
        ayjvo_state[4] = RECOGNIZE_ERROR;
        ayjvo_state[5] = VOICE_TOO_LONG;
        $VALUES = ayjvo_state;
    }
}

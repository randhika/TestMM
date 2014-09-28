// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.yjvoice;


public final class YJVO_SAMPLERATE extends Enum
{

    private static final YJVO_SAMPLERATE $VALUES[];
    public static final YJVO_SAMPLERATE SAMPLERATE_11025;
    public static final YJVO_SAMPLERATE SAMPLERATE_16000;
    public static final YJVO_SAMPLERATE SAMPLERATE_22050;
    public static final YJVO_SAMPLERATE SAMPLERATE_32000;
    public static final YJVO_SAMPLERATE SAMPLERATE_44100;
    public static final YJVO_SAMPLERATE SAMPLERATE_48000;
    public static final YJVO_SAMPLERATE SAMPLERATE_8000;
    public static final YJVO_SAMPLERATE SAMPLERATE_88200;
    public static final YJVO_SAMPLERATE SAMPLERATE_96000;

    private YJVO_SAMPLERATE(String s, int i)
    {
        super(s, i);
    }

    public static YJVO_SAMPLERATE valueOf(String s)
    {
        return (YJVO_SAMPLERATE)Enum.valueOf(jp/co/yahoo/android/yjvoice/YJVO_SAMPLERATE, s);
    }

    public static YJVO_SAMPLERATE[] values()
    {
        return (YJVO_SAMPLERATE[])$VALUES.clone();
    }

    static 
    {
        SAMPLERATE_8000 = new YJVO_SAMPLERATE("SAMPLERATE_8000", 0);
        SAMPLERATE_11025 = new YJVO_SAMPLERATE("SAMPLERATE_11025", 1);
        SAMPLERATE_16000 = new YJVO_SAMPLERATE("SAMPLERATE_16000", 2);
        SAMPLERATE_22050 = new YJVO_SAMPLERATE("SAMPLERATE_22050", 3);
        SAMPLERATE_32000 = new YJVO_SAMPLERATE("SAMPLERATE_32000", 4);
        SAMPLERATE_44100 = new YJVO_SAMPLERATE("SAMPLERATE_44100", 5);
        SAMPLERATE_48000 = new YJVO_SAMPLERATE("SAMPLERATE_48000", 6);
        SAMPLERATE_88200 = new YJVO_SAMPLERATE("SAMPLERATE_88200", 7);
        SAMPLERATE_96000 = new YJVO_SAMPLERATE("SAMPLERATE_96000", 8);
        YJVO_SAMPLERATE ayjvo_samplerate[] = new YJVO_SAMPLERATE[9];
        ayjvo_samplerate[0] = SAMPLERATE_8000;
        ayjvo_samplerate[1] = SAMPLERATE_11025;
        ayjvo_samplerate[2] = SAMPLERATE_16000;
        ayjvo_samplerate[3] = SAMPLERATE_22050;
        ayjvo_samplerate[4] = SAMPLERATE_32000;
        ayjvo_samplerate[5] = SAMPLERATE_44100;
        ayjvo_samplerate[6] = SAMPLERATE_48000;
        ayjvo_samplerate[7] = SAMPLERATE_88200;
        ayjvo_samplerate[8] = SAMPLERATE_96000;
        $VALUES = ayjvo_samplerate;
    }
}

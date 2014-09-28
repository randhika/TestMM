// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.yjvoice;


public final class YJVO_MODE extends Enum
{

    private static final YJVO_MODE $VALUES[];
    public static final YJVO_MODE CONTINUOUS;
    public static final YJVO_MODE PHRASE;

    private YJVO_MODE(String s, int i)
    {
        super(s, i);
    }

    public static YJVO_MODE valueOf(String s)
    {
        return (YJVO_MODE)Enum.valueOf(jp/co/yahoo/android/yjvoice/YJVO_MODE, s);
    }

    public static YJVO_MODE[] values()
    {
        return (YJVO_MODE[])$VALUES.clone();
    }

    static 
    {
        PHRASE = new YJVO_MODE("PHRASE", 0);
        CONTINUOUS = new YJVO_MODE("CONTINUOUS", 1);
        YJVO_MODE ayjvo_mode[] = new YJVO_MODE[2];
        ayjvo_mode[0] = PHRASE;
        ayjvo_mode[1] = CONTINUOUS;
        $VALUES = ayjvo_mode;
    }
}

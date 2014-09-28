// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.yjvoice;


public final class YJVO_FILTER extends Enum
{

    private static final YJVO_FILTER $VALUES[];
    public static final YJVO_FILTER NORMAL;
    public static final YJVO_FILTER NUMBER;
    public static final YJVO_FILTER SENTENCE;

    private YJVO_FILTER(String s, int i)
    {
        super(s, i);
    }

    public static YJVO_FILTER valueOf(String s)
    {
        return (YJVO_FILTER)Enum.valueOf(jp/co/yahoo/android/yjvoice/YJVO_FILTER, s);
    }

    public static YJVO_FILTER[] values()
    {
        return (YJVO_FILTER[])$VALUES.clone();
    }

    static 
    {
        NORMAL = new YJVO_FILTER("NORMAL", 0);
        NUMBER = new YJVO_FILTER("NUMBER", 1);
        SENTENCE = new YJVO_FILTER("SENTENCE", 2);
        YJVO_FILTER ayjvo_filter[] = new YJVO_FILTER[3];
        ayjvo_filter[0] = NORMAL;
        ayjvo_filter[1] = NUMBER;
        ayjvo_filter[2] = SENTENCE;
        $VALUES = ayjvo_filter;
    }
}

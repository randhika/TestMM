// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.yjvoice;


public final class YJVO_ACCEPT extends Enum
{

    private static final YJVO_ACCEPT $VALUES[];
    public static final YJVO_ACCEPT ACCEPT_ACCEPT;
    public static final YJVO_ACCEPT ACCEPT_NEGATIVE;
    public static final YJVO_ACCEPT ACCEPT_NEITHER;
    public static final YJVO_ACCEPT ACCEPT_POSITIVE;
    public static final YJVO_ACCEPT ACCEPT_REJECT;

    private YJVO_ACCEPT(String s, int i)
    {
        super(s, i);
    }

    public static YJVO_ACCEPT valueOf(String s)
    {
        return (YJVO_ACCEPT)Enum.valueOf(jp/co/yahoo/android/yjvoice/YJVO_ACCEPT, s);
    }

    public static YJVO_ACCEPT[] values()
    {
        return (YJVO_ACCEPT[])$VALUES.clone();
    }

    static 
    {
        ACCEPT_ACCEPT = new YJVO_ACCEPT("ACCEPT_ACCEPT", 0);
        ACCEPT_POSITIVE = new YJVO_ACCEPT("ACCEPT_POSITIVE", 1);
        ACCEPT_NEITHER = new YJVO_ACCEPT("ACCEPT_NEITHER", 2);
        ACCEPT_NEGATIVE = new YJVO_ACCEPT("ACCEPT_NEGATIVE", 3);
        ACCEPT_REJECT = new YJVO_ACCEPT("ACCEPT_REJECT", 4);
        YJVO_ACCEPT ayjvo_accept[] = new YJVO_ACCEPT[5];
        ayjvo_accept[0] = ACCEPT_ACCEPT;
        ayjvo_accept[1] = ACCEPT_POSITIVE;
        ayjvo_accept[2] = ACCEPT_NEITHER;
        ayjvo_accept[3] = ACCEPT_NEGATIVE;
        ayjvo_accept[4] = ACCEPT_REJECT;
        $VALUES = ayjvo_accept;
    }
}

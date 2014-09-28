// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.yjvoice;


public final class YJVO_DOMAIN extends Enum
{

    private static final YJVO_DOMAIN $VALUES[];
    public static final YJVO_DOMAIN DICTATION;
    public static final YJVO_DOMAIN DICTATION_16K;
    public static final YJVO_DOMAIN DICTATION_8K;
    public static final YJVO_DOMAIN SEARCH;
    public static final YJVO_DOMAIN SEARCH_16K;
    public static final YJVO_DOMAIN SEARCH_8K;
    public static final YJVO_DOMAIN TALK;
    public static final YJVO_DOMAIN TALK_16K;
    public static final YJVO_DOMAIN TALK_8K;

    private YJVO_DOMAIN(String s, int i)
    {
        super(s, i);
    }

    public static YJVO_DOMAIN valueOf(String s)
    {
        return (YJVO_DOMAIN)Enum.valueOf(jp/co/yahoo/android/yjvoice/YJVO_DOMAIN, s);
    }

    public static YJVO_DOMAIN[] values()
    {
        return (YJVO_DOMAIN[])$VALUES.clone();
    }

    static 
    {
        SEARCH = new YJVO_DOMAIN("SEARCH", 0);
        TALK = new YJVO_DOMAIN("TALK", 1);
        DICTATION = new YJVO_DOMAIN("DICTATION", 2);
        SEARCH_16K = new YJVO_DOMAIN("SEARCH_16K", 3);
        TALK_16K = new YJVO_DOMAIN("TALK_16K", 4);
        DICTATION_16K = new YJVO_DOMAIN("DICTATION_16K", 5);
        SEARCH_8K = new YJVO_DOMAIN("SEARCH_8K", 6);
        TALK_8K = new YJVO_DOMAIN("TALK_8K", 7);
        DICTATION_8K = new YJVO_DOMAIN("DICTATION_8K", 8);
        YJVO_DOMAIN ayjvo_domain[] = new YJVO_DOMAIN[9];
        ayjvo_domain[0] = SEARCH;
        ayjvo_domain[1] = TALK;
        ayjvo_domain[2] = DICTATION;
        ayjvo_domain[3] = SEARCH_16K;
        ayjvo_domain[4] = TALK_16K;
        ayjvo_domain[5] = DICTATION_16K;
        ayjvo_domain[6] = SEARCH_8K;
        ayjvo_domain[7] = TALK_8K;
        ayjvo_domain[8] = DICTATION_8K;
        $VALUES = ayjvo_domain;
    }
}

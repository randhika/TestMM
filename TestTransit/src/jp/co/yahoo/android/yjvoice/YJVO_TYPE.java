// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.yjvoice;


public final class YJVO_TYPE extends Enum
{

    private static final YJVO_TYPE $VALUES[];
    public static final YJVO_TYPE LATTICE;
    public static final YJVO_TYPE NBEST;

    private YJVO_TYPE(String s, int i)
    {
        super(s, i);
    }

    public static YJVO_TYPE valueOf(String s)
    {
        return (YJVO_TYPE)Enum.valueOf(jp/co/yahoo/android/yjvoice/YJVO_TYPE, s);
    }

    public static YJVO_TYPE[] values()
    {
        return (YJVO_TYPE[])$VALUES.clone();
    }

    static 
    {
        NBEST = new YJVO_TYPE("NBEST", 0);
        LATTICE = new YJVO_TYPE("LATTICE", 1);
        YJVO_TYPE ayjvo_type[] = new YJVO_TYPE[2];
        ayjvo_type[0] = NBEST;
        ayjvo_type[1] = LATTICE;
        $VALUES = ayjvo_type;
    }
}

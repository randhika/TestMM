// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.yjvoice;


// Referenced classes of package jp.co.yahoo.android.yjvoice:
//            YJVONbestResult

public static final class  extends Enum
{

    private static final .VALUES $VALUES[];
    public static final .VALUES RESULT_STATE_EOD;
    public static final .VALUES RESULT_STATE_NORMAL;
    public static final .VALUES RESULT_STATE_PARTIAL;
    public static final .VALUES RESULT_STATE_TIMEOUT;

    public static  valueOf(String s)
    {
        return ()Enum.valueOf(jp/co/yahoo/android/yjvoice/YJVONbestResult$YJVO_STATE_RESULT, s);
    }

    public static [] values()
    {
        return ([])$VALUES.clone();
    }

    static 
    {
        RESULT_STATE_NORMAL = new <init>("RESULT_STATE_NORMAL", 0);
        RESULT_STATE_EOD = new <init>("RESULT_STATE_EOD", 1);
        RESULT_STATE_TIMEOUT = new <init>("RESULT_STATE_TIMEOUT", 2);
        RESULT_STATE_PARTIAL = new <init>("RESULT_STATE_PARTIAL", 3);
        T_3B_.clone aclone[] = new <init>[4];
        aclone[0] = RESULT_STATE_NORMAL;
        aclone[1] = RESULT_STATE_EOD;
        aclone[2] = RESULT_STATE_TIMEOUT;
        aclone[3] = RESULT_STATE_PARTIAL;
        $VALUES = aclone;
    }

    private (String s, int i)
    {
        super(s, i);
    }
}

// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.common;


// Referenced classes of package jp.co.yahoo.android.apps.transit.common:
//            AsyncTask

public static final class  extends Enum
{

    private static final .VALUES $VALUES[];
    public static final .VALUES FINISHED;
    public static final .VALUES PENDING;
    public static final .VALUES RUNNING;

    public static  valueOf(String s)
    {
        return ()Enum.valueOf(jp/co/yahoo/android/apps/transit/common/AsyncTask$Status, s);
    }

    public static [] values()
    {
        return ([])$VALUES.clone();
    }

    static 
    {
        PENDING = new <init>("PENDING", 0);
        RUNNING = new <init>("RUNNING", 1);
        FINISHED = new <init>("FINISHED", 2);
        s_3B_.clone aclone[] = new <init>[3];
        aclone[0] = PENDING;
        aclone[1] = RUNNING;
        aclone[2] = FINISHED;
        $VALUES = aclone;
    }

    private (String s, int i)
    {
        super(s, i);
    }
}

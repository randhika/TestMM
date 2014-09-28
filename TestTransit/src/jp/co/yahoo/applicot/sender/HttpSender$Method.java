// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.applicot.sender;


// Referenced classes of package jp.co.yahoo.applicot.sender:
//            HttpSender

public static final class  extends Enum
{

    private static final .VALUES $VALUES[];
    public static final .VALUES POST;
    public static final .VALUES PUT;

    public static  valueOf(String s)
    {
        return ()Enum.valueOf(jp/co/yahoo/applicot/sender/HttpSender$Method, s);
    }

    public static [] values()
    {
        return ([])$VALUES.clone();
    }

    static 
    {
        POST = new <init>("POST", 0);
        PUT = new <init>("PUT", 1);
        d_3B_.clone aclone[] = new <init>[2];
        aclone[0] = POST;
        aclone[1] = PUT;
        $VALUES = aclone;
    }

    private (String s, int i)
    {
        super(s, i);
    }
}

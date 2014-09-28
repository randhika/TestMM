// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.common;


// Referenced classes of package jp.co.yahoo.android.common:
//            YGeocoder

public static final class  extends Enum
{

    private static final .VALUES $VALUES[];
    public static final .VALUES Contents;
    public static final .VALUES Geo;

    public static  valueOf(String s)
    {
        return ()Enum.valueOf(jp/co/yahoo/android/common/YGeocoder$SearchType, s);
    }

    public static [] values()
    {
        return ([])$VALUES.clone();
    }

    static 
    {
        Contents = new <init>("Contents", 0);
        Geo = new <init>("Geo", 1);
        e_3B_.clone aclone[] = new <init>[2];
        aclone[0] = Contents;
        aclone[1] = Geo;
        $VALUES = aclone;
    }

    private (String s, int i)
    {
        super(s, i);
    }
}

// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.ads;


// Referenced classes of package com.google.ads:
//            AdRequest

public static final class  extends Enum
{

    public static final b FEMALE;
    public static final b MALE;
    public static final b UNKNOWN;
    private static final b b[];

    public static  valueOf(String s)
    {
        return ()Enum.valueOf(com/google/ads/AdRequest$Gender, s);
    }

    public static [] values()
    {
        return ([])b.clone();
    }

    static 
    {
        UNKNOWN = new <init>("UNKNOWN", 0);
        MALE = new <init>("MALE", 1);
        FEMALE = new <init>("FEMALE", 2);
        r_3B_.clone aclone[] = new <init>[3];
        aclone[0] = UNKNOWN;
        aclone[1] = MALE;
        aclone[2] = FEMALE;
        b = aclone;
    }

    private (String s, int i)
    {
        super(s, i);
    }
}

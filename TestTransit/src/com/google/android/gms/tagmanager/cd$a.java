// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.tagmanager;


// Referenced classes of package com.google.android.gms.tagmanager:
//            cd

static final class  extends Enum
{

    public static final agE agB;
    public static final agE agC;
    public static final agE agD;
    private static final agE agE[];

    public static  valueOf(String s)
    {
        return ()Enum.valueOf(com/google/android/gms/tagmanager/cd$a, s);
    }

    public static [] values()
    {
        return ([])agE.clone();
    }

    static 
    {
        agB = new <init>("NONE", 0);
        agC = new <init>("CONTAINER", 1);
        agD = new <init>("CONTAINER_DEBUG", 2);
        one aone[] = new <init>[3];
        aone[0] = agB;
        aone[1] = agC;
        aone[2] = agD;
        agE = aone;
    }

    private (String s, int i)
    {
        super(s, i);
    }
}

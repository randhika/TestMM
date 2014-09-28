// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.tagmanager;


// Referenced classes of package com.google.android.gms.tagmanager:
//            az

private static final class  extends Enum
{

    public static final agb afY;
    public static final agb afZ;
    public static final agb aga;
    private static final agb agb[];

    public static  valueOf(String s)
    {
        return ()Enum.valueOf(com/google/android/gms/tagmanager/az$a, s);
    }

    public static [] values()
    {
        return ([])agb.clone();
    }

    static 
    {
        afY = new <init>("NONE", 0);
        afZ = new <init>("URL", 1);
        aga = new <init>("BACKSLASH", 2);
        one aone[] = new <init>[3];
        aone[0] = afY;
        aone[1] = afZ;
        aone[2] = aga;
        agb = aone;
    }

    private (String s, int i)
    {
        super(s, i);
    }
}

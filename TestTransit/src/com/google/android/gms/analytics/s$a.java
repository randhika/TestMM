// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.analytics;


// Referenced classes of package com.google.android.gms.analytics:
//            s

private static final class  extends Enum
{

    public static final uF uA;
    public static final uF uB;
    public static final uF uC;
    public static final uF uD;
    public static final uF uE;
    private static final uF uF[];
    public static final uF uy;
    public static final uF uz;

    public static  valueOf(String s1)
    {
        return ()Enum.valueOf(com/google/android/gms/analytics/s$a, s1);
    }

    public static [] values()
    {
        return ([])uF.clone();
    }

    static 
    {
        uy = new <init>("CONNECTING", 0);
        uz = new <init>("CONNECTED_SERVICE", 1);
        uA = new <init>("CONNECTED_LOCAL", 2);
        uB = new <init>("BLOCKED", 3);
        uC = new <init>("PENDING_CONNECTION", 4);
        uD = new <init>("PENDING_DISCONNECT", 5);
        uE = new <init>("DISCONNECTED", 6);
        lone alone[] = new <init>[7];
        alone[0] = uy;
        alone[1] = uz;
        alone[2] = uA;
        alone[3] = uB;
        alone[4] = uC;
        alone[5] = uD;
        alone[6] = uE;
        uF = alone;
    }

    private (String s1, int i)
    {
        super(s1, i);
    }
}

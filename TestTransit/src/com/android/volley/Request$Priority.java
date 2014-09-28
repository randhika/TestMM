// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.android.volley;


// Referenced classes of package com.android.volley:
//            Request

public static final class  extends Enum
{

    private static final ENUM.VALUES ENUM$VALUES[];
    public static final ENUM.VALUES HIGH;
    public static final ENUM.VALUES IMMEDIATE;
    public static final ENUM.VALUES LOW;
    public static final ENUM.VALUES NORMAL;

    public static  valueOf(String s)
    {
        return ()Enum.valueOf(com/android/volley/Request$Priority, s);
    }

    public static [] values()
    {
         a[] = ENUM$VALUES;
        int i = a.length;
         a1[] = new ENUM.VALUES[i];
        System.arraycopy(a, 0, a1, 0, i);
        return a1;
    }

    static 
    {
        LOW = new <init>("LOW", 0);
        NORMAL = new <init>("NORMAL", 1);
        HIGH = new <init>("HIGH", 2);
        IMMEDIATE = new <init>("IMMEDIATE", 3);
        ENUM.VALUES avalues[] = new <init>[4];
        avalues[0] = LOW;
        avalues[1] = NORMAL;
        avalues[2] = HIGH;
        avalues[3] = IMMEDIATE;
        ENUM$VALUES = avalues;
    }

    private (String s, int i)
    {
        super(s, i);
    }
}

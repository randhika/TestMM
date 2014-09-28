// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson;


// Referenced classes of package org.codehaus.jackson:
//            JsonParser

public static final class  extends Enum
{

    private static final .VALUES $VALUES[];
    public static final .VALUES BIG_DECIMAL;
    public static final .VALUES BIG_INTEGER;
    public static final .VALUES DOUBLE;
    public static final .VALUES FLOAT;
    public static final .VALUES INT;
    public static final .VALUES LONG;

    public static  valueOf(String s)
    {
        return ()Enum.valueOf(org/codehaus/jackson/JsonParser$NumberType, s);
    }

    public static [] values()
    {
        return ([])$VALUES.clone();
    }

    static 
    {
        INT = new <init>("INT", 0);
        LONG = new <init>("LONG", 1);
        BIG_INTEGER = new <init>("BIG_INTEGER", 2);
        FLOAT = new <init>("FLOAT", 3);
        DOUBLE = new <init>("DOUBLE", 4);
        BIG_DECIMAL = new <init>("BIG_DECIMAL", 5);
        e_3B_.clone aclone[] = new <init>[6];
        aclone[0] = INT;
        aclone[1] = LONG;
        aclone[2] = BIG_INTEGER;
        aclone[3] = FLOAT;
        aclone[4] = DOUBLE;
        aclone[5] = BIG_DECIMAL;
        $VALUES = aclone;
    }

    private (String s, int i)
    {
        super(s, i);
    }
}

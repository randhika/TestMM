// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.annotate;


// Referenced classes of package org.codehaus.jackson.annotate:
//            JsonTypeInfo

public static final class  extends Enum
{

    private static final .VALUES $VALUES[];
    public static final .VALUES PROPERTY;
    public static final .VALUES WRAPPER_ARRAY;
    public static final .VALUES WRAPPER_OBJECT;

    public static  valueOf(String s)
    {
        return ()Enum.valueOf(org/codehaus/jackson/annotate/JsonTypeInfo$As, s);
    }

    public static [] values()
    {
        return ([])$VALUES.clone();
    }

    static 
    {
        PROPERTY = new <init>("PROPERTY", 0);
        WRAPPER_OBJECT = new <init>("WRAPPER_OBJECT", 1);
        WRAPPER_ARRAY = new <init>("WRAPPER_ARRAY", 2);
        s_3B_.clone aclone[] = new <init>[3];
        aclone[0] = PROPERTY;
        aclone[1] = WRAPPER_OBJECT;
        aclone[2] = WRAPPER_ARRAY;
        $VALUES = aclone;
    }

    private (String s, int i)
    {
        super(s, i);
    }
}

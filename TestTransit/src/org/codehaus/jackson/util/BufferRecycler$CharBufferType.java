// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.util;


// Referenced classes of package org.codehaus.jackson.util:
//            BufferRecycler

public static final class size extends Enum
{

    private static final .VALUES $VALUES[];
    public static final .VALUES CONCAT_BUFFER;
    public static final .VALUES NAME_COPY_BUFFER;
    public static final .VALUES TEXT_BUFFER;
    public static final .VALUES TOKEN_BUFFER;
    private final int size;

    public static size valueOf(String s)
    {
        return (size)Enum.valueOf(org/codehaus/jackson/util/BufferRecycler$CharBufferType, s);
    }

    public static size[] values()
    {
        return (size[])$VALUES.clone();
    }

    static 
    {
        TOKEN_BUFFER = new <init>("TOKEN_BUFFER", 0, 2000);
        CONCAT_BUFFER = new <init>("CONCAT_BUFFER", 1, 2000);
        TEXT_BUFFER = new <init>("TEXT_BUFFER", 2, 200);
        NAME_COPY_BUFFER = new <init>("NAME_COPY_BUFFER", 3, 200);
        e_3B_.clone aclone[] = new <init>[4];
        aclone[0] = TOKEN_BUFFER;
        aclone[1] = CONCAT_BUFFER;
        aclone[2] = TEXT_BUFFER;
        aclone[3] = NAME_COPY_BUFFER;
        $VALUES = aclone;
    }


    private (String s, int i, int j)
    {
        super(s, i);
        size = j;
    }
}

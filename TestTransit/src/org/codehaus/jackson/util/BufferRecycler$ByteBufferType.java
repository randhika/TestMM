// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.util;


// Referenced classes of package org.codehaus.jackson.util:
//            BufferRecycler

public static final class size extends Enum
{

    private static final .VALUES $VALUES[];
    public static final .VALUES READ_IO_BUFFER;
    public static final .VALUES WRITE_CONCAT_BUFFER;
    public static final .VALUES WRITE_ENCODING_BUFFER;
    private final int size;

    public static size valueOf(String s)
    {
        return (size)Enum.valueOf(org/codehaus/jackson/util/BufferRecycler$ByteBufferType, s);
    }

    public static size[] values()
    {
        return (size[])$VALUES.clone();
    }

    static 
    {
        READ_IO_BUFFER = new <init>("READ_IO_BUFFER", 0, 4000);
        WRITE_ENCODING_BUFFER = new <init>("WRITE_ENCODING_BUFFER", 1, 4000);
        WRITE_CONCAT_BUFFER = new <init>("WRITE_CONCAT_BUFFER", 2, 2000);
        e_3B_.clone aclone[] = new <init>[3];
        aclone[0] = READ_IO_BUFFER;
        aclone[1] = WRITE_ENCODING_BUFFER;
        aclone[2] = WRITE_CONCAT_BUFFER;
        $VALUES = aclone;
    }


    private (String s, int i, int j)
    {
        super(s, i);
        size = j;
    }
}

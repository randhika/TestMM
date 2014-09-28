// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.util;


public class BufferRecycler
{
    public static final class ByteBufferType extends Enum
    {

        private static final ByteBufferType $VALUES[];
        public static final ByteBufferType READ_IO_BUFFER;
        public static final ByteBufferType WRITE_CONCAT_BUFFER;
        public static final ByteBufferType WRITE_ENCODING_BUFFER;
        private final int size;

        public static ByteBufferType valueOf(String s)
        {
            return (ByteBufferType)Enum.valueOf(org/codehaus/jackson/util/BufferRecycler$ByteBufferType, s);
        }

        public static ByteBufferType[] values()
        {
            return (ByteBufferType[])$VALUES.clone();
        }

        static 
        {
            READ_IO_BUFFER = new ByteBufferType("READ_IO_BUFFER", 0, 4000);
            WRITE_ENCODING_BUFFER = new ByteBufferType("WRITE_ENCODING_BUFFER", 1, 4000);
            WRITE_CONCAT_BUFFER = new ByteBufferType("WRITE_CONCAT_BUFFER", 2, 2000);
            ByteBufferType abytebuffertype[] = new ByteBufferType[3];
            abytebuffertype[0] = READ_IO_BUFFER;
            abytebuffertype[1] = WRITE_ENCODING_BUFFER;
            abytebuffertype[2] = WRITE_CONCAT_BUFFER;
            $VALUES = abytebuffertype;
        }


        private ByteBufferType(String s, int i, int j)
        {
            super(s, i);
            size = j;
        }
    }

    public static final class CharBufferType extends Enum
    {

        private static final CharBufferType $VALUES[];
        public static final CharBufferType CONCAT_BUFFER;
        public static final CharBufferType NAME_COPY_BUFFER;
        public static final CharBufferType TEXT_BUFFER;
        public static final CharBufferType TOKEN_BUFFER;
        private final int size;

        public static CharBufferType valueOf(String s)
        {
            return (CharBufferType)Enum.valueOf(org/codehaus/jackson/util/BufferRecycler$CharBufferType, s);
        }

        public static CharBufferType[] values()
        {
            return (CharBufferType[])$VALUES.clone();
        }

        static 
        {
            TOKEN_BUFFER = new CharBufferType("TOKEN_BUFFER", 0, 2000);
            CONCAT_BUFFER = new CharBufferType("CONCAT_BUFFER", 1, 2000);
            TEXT_BUFFER = new CharBufferType("TEXT_BUFFER", 2, 200);
            NAME_COPY_BUFFER = new CharBufferType("NAME_COPY_BUFFER", 3, 200);
            CharBufferType acharbuffertype[] = new CharBufferType[4];
            acharbuffertype[0] = TOKEN_BUFFER;
            acharbuffertype[1] = CONCAT_BUFFER;
            acharbuffertype[2] = TEXT_BUFFER;
            acharbuffertype[3] = NAME_COPY_BUFFER;
            $VALUES = acharbuffertype;
        }


        private CharBufferType(String s, int i, int j)
        {
            super(s, i);
            size = j;
        }
    }


    public static final int DEFAULT_WRITE_CONCAT_BUFFER_LEN = 2000;
    protected final byte _byteBuffers[][] = new byte[ByteBufferType.values().length][];
    protected final char _charBuffers[][] = new char[CharBufferType.values().length][];

    public BufferRecycler()
    {
    }

    private final byte[] balloc(int i)
    {
        return new byte[i];
    }

    private final char[] calloc(int i)
    {
        return new char[i];
    }

    public final byte[] allocByteBuffer(ByteBufferType bytebuffertype)
    {
        int i = bytebuffertype.ordinal();
        byte abyte0[] = _byteBuffers[i];
        if (abyte0 == null)
        {
            return balloc(bytebuffertype.size);
        } else
        {
            _byteBuffers[i] = null;
            return abyte0;
        }
    }

    public final char[] allocCharBuffer(CharBufferType charbuffertype)
    {
        return allocCharBuffer(charbuffertype, 0);
    }

    public final char[] allocCharBuffer(CharBufferType charbuffertype, int i)
    {
        if (charbuffertype.size > i)
        {
            i = charbuffertype.size;
        }
        int j = charbuffertype.ordinal();
        char ac[] = _charBuffers[j];
        if (ac == null || ac.length < i)
        {
            return calloc(i);
        } else
        {
            _charBuffers[j] = null;
            return ac;
        }
    }

    public final void releaseByteBuffer(ByteBufferType bytebuffertype, byte abyte0[])
    {
        _byteBuffers[bytebuffertype.ordinal()] = abyte0;
    }

    public final void releaseCharBuffer(CharBufferType charbuffertype, char ac[])
    {
        _charBuffers[charbuffertype.ordinal()] = ac;
    }
}

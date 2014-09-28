// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.smile;


// Referenced classes of package org.codehaus.jackson.smile:
//            SmileGenerator

public static final class _defaultState extends Enum
{

    private static final .VALUES $VALUES[];
    public static final .VALUES CHECK_SHARED_NAMES;
    public static final .VALUES CHECK_SHARED_STRING_VALUES;
    public static final .VALUES ENCODE_BINARY_AS_7BIT;
    public static final .VALUES WRITE_END_MARKER;
    public static final .VALUES WRITE_HEADER;
    protected final boolean _defaultState;
    protected final int _mask = 1 << ordinal();

    public static int collectDefaults()
    {
        int i = 0;
        _defaultState a_ldefaultstate[] = values();
        int j = a_ldefaultstate.length;
        for (int k = 0; k < j; k++)
        {
            _defaultState _ldefaultstate = a_ldefaultstate[k];
            if (_ldefaultstate.enabledByDefault())
            {
                i |= _ldefaultstate.getMask();
            }
        }

        return i;
    }

    public static getMask valueOf(String s)
    {
        return (getMask)Enum.valueOf(org/codehaus/jackson/smile/SmileGenerator$Feature, s);
    }

    public static getMask[] values()
    {
        return (getMask[])$VALUES.clone();
    }

    public boolean enabledByDefault()
    {
        return _defaultState;
    }

    public int getMask()
    {
        return _mask;
    }

    static 
    {
        WRITE_HEADER = new <init>("WRITE_HEADER", 0, true);
        WRITE_END_MARKER = new <init>("WRITE_END_MARKER", 1, false);
        ENCODE_BINARY_AS_7BIT = new <init>("ENCODE_BINARY_AS_7BIT", 2, true);
        CHECK_SHARED_NAMES = new <init>("CHECK_SHARED_NAMES", 3, true);
        CHECK_SHARED_STRING_VALUES = new <init>("CHECK_SHARED_STRING_VALUES", 4, false);
        _mask a_lmask[] = new <init>[5];
        a_lmask[0] = WRITE_HEADER;
        a_lmask[1] = WRITE_END_MARKER;
        a_lmask[2] = ENCODE_BINARY_AS_7BIT;
        a_lmask[3] = CHECK_SHARED_NAMES;
        a_lmask[4] = CHECK_SHARED_STRING_VALUES;
        $VALUES = a_lmask;
    }

    private (String s, int i, boolean flag)
    {
        super(s, i);
        _defaultState = flag;
    }
}

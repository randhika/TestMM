// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson;


// Referenced classes of package org.codehaus.jackson:
//            JsonGenerator

public static final class _defaultState extends Enum
{

    private static final .VALUES $VALUES[];
    public static final .VALUES AUTO_CLOSE_JSON_CONTENT;
    public static final .VALUES AUTO_CLOSE_TARGET;
    public static final .VALUES ESCAPE_NON_ASCII;
    public static final .VALUES FLUSH_PASSED_TO_STREAM;
    public static final .VALUES QUOTE_FIELD_NAMES;
    public static final .VALUES QUOTE_NON_NUMERIC_NUMBERS;
    public static final .VALUES WRITE_NUMBERS_AS_STRINGS;
    final boolean _defaultState;
    final int _mask = 1 << ordinal();

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
        return (getMask)Enum.valueOf(org/codehaus/jackson/JsonGenerator$Feature, s);
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
        AUTO_CLOSE_TARGET = new <init>("AUTO_CLOSE_TARGET", 0, true);
        AUTO_CLOSE_JSON_CONTENT = new <init>("AUTO_CLOSE_JSON_CONTENT", 1, true);
        QUOTE_FIELD_NAMES = new <init>("QUOTE_FIELD_NAMES", 2, true);
        QUOTE_NON_NUMERIC_NUMBERS = new <init>("QUOTE_NON_NUMERIC_NUMBERS", 3, true);
        WRITE_NUMBERS_AS_STRINGS = new <init>("WRITE_NUMBERS_AS_STRINGS", 4, false);
        FLUSH_PASSED_TO_STREAM = new <init>("FLUSH_PASSED_TO_STREAM", 5, true);
        ESCAPE_NON_ASCII = new <init>("ESCAPE_NON_ASCII", 6, false);
        _mask a_lmask[] = new <init>[7];
        a_lmask[0] = AUTO_CLOSE_TARGET;
        a_lmask[1] = AUTO_CLOSE_JSON_CONTENT;
        a_lmask[2] = QUOTE_FIELD_NAMES;
        a_lmask[3] = QUOTE_NON_NUMERIC_NUMBERS;
        a_lmask[4] = WRITE_NUMBERS_AS_STRINGS;
        a_lmask[5] = FLUSH_PASSED_TO_STREAM;
        a_lmask[6] = ESCAPE_NON_ASCII;
        $VALUES = a_lmask;
    }

    private (String s, int i, boolean flag)
    {
        super(s, i);
        _defaultState = flag;
    }
}

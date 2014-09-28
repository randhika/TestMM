// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson;


// Referenced classes of package org.codehaus.jackson:
//            JsonParser

public static final class _defaultState extends Enum
{

    private static final .VALUES $VALUES[];
    public static final .VALUES ALLOW_BACKSLASH_ESCAPING_ANY_CHARACTER;
    public static final .VALUES ALLOW_COMMENTS;
    public static final .VALUES ALLOW_NON_NUMERIC_NUMBERS;
    public static final .VALUES ALLOW_NUMERIC_LEADING_ZEROS;
    public static final .VALUES ALLOW_SINGLE_QUOTES;
    public static final .VALUES ALLOW_UNQUOTED_CONTROL_CHARS;
    public static final .VALUES ALLOW_UNQUOTED_FIELD_NAMES;
    public static final .VALUES AUTO_CLOSE_SOURCE;
    public static final .VALUES CANONICALIZE_FIELD_NAMES;
    public static final .VALUES INTERN_FIELD_NAMES;
    final boolean _defaultState;

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
        return (getMask)Enum.valueOf(org/codehaus/jackson/JsonParser$Feature, s);
    }

    public static getMask[] values()
    {
        return (getMask[])$VALUES.clone();
    }

    public boolean enabledByDefault()
    {
        return _defaultState;
    }

    public boolean enabledIn(int i)
    {
        return (i & getMask()) != 0;
    }

    public int getMask()
    {
        return 1 << ordinal();
    }

    static 
    {
        AUTO_CLOSE_SOURCE = new <init>("AUTO_CLOSE_SOURCE", 0, true);
        ALLOW_COMMENTS = new <init>("ALLOW_COMMENTS", 1, false);
        ALLOW_UNQUOTED_FIELD_NAMES = new <init>("ALLOW_UNQUOTED_FIELD_NAMES", 2, false);
        ALLOW_SINGLE_QUOTES = new <init>("ALLOW_SINGLE_QUOTES", 3, false);
        ALLOW_UNQUOTED_CONTROL_CHARS = new <init>("ALLOW_UNQUOTED_CONTROL_CHARS", 4, false);
        ALLOW_BACKSLASH_ESCAPING_ANY_CHARACTER = new <init>("ALLOW_BACKSLASH_ESCAPING_ANY_CHARACTER", 5, false);
        ALLOW_NUMERIC_LEADING_ZEROS = new <init>("ALLOW_NUMERIC_LEADING_ZEROS", 6, false);
        ALLOW_NON_NUMERIC_NUMBERS = new <init>("ALLOW_NON_NUMERIC_NUMBERS", 7, false);
        INTERN_FIELD_NAMES = new <init>("INTERN_FIELD_NAMES", 8, true);
        CANONICALIZE_FIELD_NAMES = new <init>("CANONICALIZE_FIELD_NAMES", 9, true);
        ordinal aordinal[] = new <init>[10];
        aordinal[0] = AUTO_CLOSE_SOURCE;
        aordinal[1] = ALLOW_COMMENTS;
        aordinal[2] = ALLOW_UNQUOTED_FIELD_NAMES;
        aordinal[3] = ALLOW_SINGLE_QUOTES;
        aordinal[4] = ALLOW_UNQUOTED_CONTROL_CHARS;
        aordinal[5] = ALLOW_BACKSLASH_ESCAPING_ANY_CHARACTER;
        aordinal[6] = ALLOW_NUMERIC_LEADING_ZEROS;
        aordinal[7] = ALLOW_NON_NUMERIC_NUMBERS;
        aordinal[8] = INTERN_FIELD_NAMES;
        aordinal[9] = CANONICALIZE_FIELD_NAMES;
        $VALUES = aordinal;
    }

    private (String s, int i, boolean flag)
    {
        super(s, i);
        _defaultState = flag;
    }
}

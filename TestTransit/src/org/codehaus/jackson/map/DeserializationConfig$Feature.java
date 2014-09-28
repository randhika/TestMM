// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.map;


// Referenced classes of package org.codehaus.jackson.map:
//            DeserializationConfig

public static final class _defaultState extends Enum
{

    private static final .VALUES $VALUES[];
    public static final .VALUES ACCEPT_EMPTY_STRING_AS_NULL_OBJECT;
    public static final .VALUES ACCEPT_SINGLE_VALUE_AS_ARRAY;
    public static final .VALUES AUTO_DETECT_CREATORS;
    public static final .VALUES AUTO_DETECT_FIELDS;
    public static final .VALUES AUTO_DETECT_SETTERS;
    public static final .VALUES CAN_OVERRIDE_ACCESS_MODIFIERS;
    public static final .VALUES FAIL_ON_NULL_FOR_PRIMITIVES;
    public static final .VALUES FAIL_ON_NUMBERS_FOR_ENUMS;
    public static final .VALUES FAIL_ON_UNKNOWN_PROPERTIES;
    public static final .VALUES READ_ENUMS_USING_TO_STRING;
    public static final .VALUES USE_ANNOTATIONS;
    public static final .VALUES USE_BIG_DECIMAL_FOR_FLOATS;
    public static final .VALUES USE_BIG_INTEGER_FOR_INTS;
    public static final .VALUES USE_GETTERS_AS_SETTERS;
    public static final .VALUES WRAP_EXCEPTIONS;
    public static final .VALUES WRAP_ROOT_VALUE;
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
        return (getMask)Enum.valueOf(org/codehaus/jackson/map/DeserializationConfig$Feature, s);
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
        return 1 << ordinal();
    }

    static 
    {
        USE_ANNOTATIONS = new <init>("USE_ANNOTATIONS", 0, true);
        AUTO_DETECT_SETTERS = new <init>("AUTO_DETECT_SETTERS", 1, true);
        AUTO_DETECT_CREATORS = new <init>("AUTO_DETECT_CREATORS", 2, true);
        AUTO_DETECT_FIELDS = new <init>("AUTO_DETECT_FIELDS", 3, true);
        USE_GETTERS_AS_SETTERS = new <init>("USE_GETTERS_AS_SETTERS", 4, true);
        CAN_OVERRIDE_ACCESS_MODIFIERS = new <init>("CAN_OVERRIDE_ACCESS_MODIFIERS", 5, true);
        USE_BIG_DECIMAL_FOR_FLOATS = new <init>("USE_BIG_DECIMAL_FOR_FLOATS", 6, false);
        USE_BIG_INTEGER_FOR_INTS = new <init>("USE_BIG_INTEGER_FOR_INTS", 7, false);
        READ_ENUMS_USING_TO_STRING = new <init>("READ_ENUMS_USING_TO_STRING", 8, false);
        FAIL_ON_UNKNOWN_PROPERTIES = new <init>("FAIL_ON_UNKNOWN_PROPERTIES", 9, true);
        FAIL_ON_NULL_FOR_PRIMITIVES = new <init>("FAIL_ON_NULL_FOR_PRIMITIVES", 10, false);
        FAIL_ON_NUMBERS_FOR_ENUMS = new <init>("FAIL_ON_NUMBERS_FOR_ENUMS", 11, false);
        WRAP_EXCEPTIONS = new <init>("WRAP_EXCEPTIONS", 12, true);
        WRAP_ROOT_VALUE = new <init>("WRAP_ROOT_VALUE", 13, false);
        ACCEPT_EMPTY_STRING_AS_NULL_OBJECT = new <init>("ACCEPT_EMPTY_STRING_AS_NULL_OBJECT", 14, false);
        ACCEPT_SINGLE_VALUE_AS_ARRAY = new <init>("ACCEPT_SINGLE_VALUE_AS_ARRAY", 15, false);
        ordinal aordinal[] = new <init>[16];
        aordinal[0] = USE_ANNOTATIONS;
        aordinal[1] = AUTO_DETECT_SETTERS;
        aordinal[2] = AUTO_DETECT_CREATORS;
        aordinal[3] = AUTO_DETECT_FIELDS;
        aordinal[4] = USE_GETTERS_AS_SETTERS;
        aordinal[5] = CAN_OVERRIDE_ACCESS_MODIFIERS;
        aordinal[6] = USE_BIG_DECIMAL_FOR_FLOATS;
        aordinal[7] = USE_BIG_INTEGER_FOR_INTS;
        aordinal[8] = READ_ENUMS_USING_TO_STRING;
        aordinal[9] = FAIL_ON_UNKNOWN_PROPERTIES;
        aordinal[10] = FAIL_ON_NULL_FOR_PRIMITIVES;
        aordinal[11] = FAIL_ON_NUMBERS_FOR_ENUMS;
        aordinal[12] = WRAP_EXCEPTIONS;
        aordinal[13] = WRAP_ROOT_VALUE;
        aordinal[14] = ACCEPT_EMPTY_STRING_AS_NULL_OBJECT;
        aordinal[15] = ACCEPT_SINGLE_VALUE_AS_ARRAY;
        $VALUES = aordinal;
    }

    private (String s, int i, boolean flag)
    {
        super(s, i);
        _defaultState = flag;
    }
}

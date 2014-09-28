// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.map;


// Referenced classes of package org.codehaus.jackson.map:
//            SerializationConfig

public static final class _defaultState extends Enum
{

    private static final .VALUES $VALUES[];
    public static final .VALUES AUTO_DETECT_FIELDS;
    public static final .VALUES AUTO_DETECT_GETTERS;
    public static final .VALUES AUTO_DETECT_IS_GETTERS;
    public static final .VALUES CAN_OVERRIDE_ACCESS_MODIFIERS;
    public static final .VALUES CLOSE_CLOSEABLE;
    public static final .VALUES DEFAULT_VIEW_INCLUSION;
    public static final .VALUES FAIL_ON_EMPTY_BEANS;
    public static final .VALUES FLUSH_AFTER_WRITE_VALUE;
    public static final .VALUES INDENT_OUTPUT;
    public static final .VALUES SORT_PROPERTIES_ALPHABETICALLY;
    public static final .VALUES USE_ANNOTATIONS;
    public static final .VALUES USE_STATIC_TYPING;
    public static final .VALUES WRAP_EXCEPTIONS;
    public static final .VALUES WRAP_ROOT_VALUE;
    public static final .VALUES WRITE_CHAR_ARRAYS_AS_JSON_ARRAYS;
    public static final .VALUES WRITE_DATES_AS_TIMESTAMPS;
    public static final .VALUES WRITE_ENUMS_USING_TO_STRING;
    public static final .VALUES WRITE_NULL_MAP_VALUES;
    public static final .VALUES WRITE_NULL_PROPERTIES;
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
        return (getMask)Enum.valueOf(org/codehaus/jackson/map/SerializationConfig$Feature, s);
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
        AUTO_DETECT_GETTERS = new <init>("AUTO_DETECT_GETTERS", 1, true);
        AUTO_DETECT_IS_GETTERS = new <init>("AUTO_DETECT_IS_GETTERS", 2, true);
        AUTO_DETECT_FIELDS = new <init>("AUTO_DETECT_FIELDS", 3, true);
        CAN_OVERRIDE_ACCESS_MODIFIERS = new <init>("CAN_OVERRIDE_ACCESS_MODIFIERS", 4, true);
        WRITE_NULL_PROPERTIES = new <init>("WRITE_NULL_PROPERTIES", 5, true);
        USE_STATIC_TYPING = new <init>("USE_STATIC_TYPING", 6, false);
        DEFAULT_VIEW_INCLUSION = new <init>("DEFAULT_VIEW_INCLUSION", 7, true);
        WRAP_ROOT_VALUE = new <init>("WRAP_ROOT_VALUE", 8, false);
        INDENT_OUTPUT = new <init>("INDENT_OUTPUT", 9, false);
        SORT_PROPERTIES_ALPHABETICALLY = new <init>("SORT_PROPERTIES_ALPHABETICALLY", 10, false);
        FAIL_ON_EMPTY_BEANS = new <init>("FAIL_ON_EMPTY_BEANS", 11, true);
        WRAP_EXCEPTIONS = new <init>("WRAP_EXCEPTIONS", 12, true);
        CLOSE_CLOSEABLE = new <init>("CLOSE_CLOSEABLE", 13, false);
        FLUSH_AFTER_WRITE_VALUE = new <init>("FLUSH_AFTER_WRITE_VALUE", 14, true);
        WRITE_DATES_AS_TIMESTAMPS = new <init>("WRITE_DATES_AS_TIMESTAMPS", 15, true);
        WRITE_CHAR_ARRAYS_AS_JSON_ARRAYS = new <init>("WRITE_CHAR_ARRAYS_AS_JSON_ARRAYS", 16, false);
        WRITE_ENUMS_USING_TO_STRING = new <init>("WRITE_ENUMS_USING_TO_STRING", 17, false);
        WRITE_NULL_MAP_VALUES = new <init>("WRITE_NULL_MAP_VALUES", 18, true);
        ordinal aordinal[] = new <init>[19];
        aordinal[0] = USE_ANNOTATIONS;
        aordinal[1] = AUTO_DETECT_GETTERS;
        aordinal[2] = AUTO_DETECT_IS_GETTERS;
        aordinal[3] = AUTO_DETECT_FIELDS;
        aordinal[4] = CAN_OVERRIDE_ACCESS_MODIFIERS;
        aordinal[5] = WRITE_NULL_PROPERTIES;
        aordinal[6] = USE_STATIC_TYPING;
        aordinal[7] = DEFAULT_VIEW_INCLUSION;
        aordinal[8] = WRAP_ROOT_VALUE;
        aordinal[9] = INDENT_OUTPUT;
        aordinal[10] = SORT_PROPERTIES_ALPHABETICALLY;
        aordinal[11] = FAIL_ON_EMPTY_BEANS;
        aordinal[12] = WRAP_EXCEPTIONS;
        aordinal[13] = CLOSE_CLOSEABLE;
        aordinal[14] = FLUSH_AFTER_WRITE_VALUE;
        aordinal[15] = WRITE_DATES_AS_TIMESTAMPS;
        aordinal[16] = WRITE_CHAR_ARRAYS_AS_JSON_ARRAYS;
        aordinal[17] = WRITE_ENUMS_USING_TO_STRING;
        aordinal[18] = WRITE_NULL_MAP_VALUES;
        $VALUES = aordinal;
    }

    private (String s, int i, boolean flag)
    {
        super(s, i);
        _defaultState = flag;
    }
}

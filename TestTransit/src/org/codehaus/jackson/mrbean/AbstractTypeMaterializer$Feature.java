// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.mrbean;


// Referenced classes of package org.codehaus.jackson.mrbean:
//            AbstractTypeMaterializer

public static final class _defaultState extends Enum
{

    private static final .VALUES $VALUES[];
    public static final .VALUES FAIL_ON_UNMATERIALIZED_METHOD;
    final boolean _defaultState;

    protected static int collectDefaults()
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
        return (getMask)Enum.valueOf(org/codehaus/jackson/mrbean/AbstractTypeMaterializer$Feature, s);
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
        FAIL_ON_UNMATERIALIZED_METHOD = new <init>("FAIL_ON_UNMATERIALIZED_METHOD", 0, false);
        ordinal aordinal[] = new <init>[1];
        aordinal[0] = FAIL_ON_UNMATERIALIZED_METHOD;
        $VALUES = aordinal;
    }

    private (String s, int i, boolean flag)
    {
        super(s, i);
        _defaultState = flag;
    }
}

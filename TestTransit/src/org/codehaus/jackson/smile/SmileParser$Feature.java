// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.smile;


// Referenced classes of package org.codehaus.jackson.smile:
//            SmileParser

public static final class _defaultState extends Enum
{

    private static final .VALUES $VALUES[];
    public static final .VALUES REQUIRE_HEADER;
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
        return (getMask)Enum.valueOf(org/codehaus/jackson/smile/SmileParser$Feature, s);
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
        REQUIRE_HEADER = new <init>("REQUIRE_HEADER", 0, true);
        _mask a_lmask[] = new <init>[1];
        a_lmask[0] = REQUIRE_HEADER;
        $VALUES = a_lmask;
    }

    private (String s, int i, boolean flag)
    {
        super(s, i);
        _defaultState = flag;
    }
}

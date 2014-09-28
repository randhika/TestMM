// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.map;


// Referenced classes of package org.codehaus.jackson.map:
//            ObjectMapper

public static final class _cls9 extends Enum
{

    private static final .VALUES $VALUES[];
    public static final .VALUES JAVA_LANG_OBJECT;
    public static final .VALUES NON_CONCRETE_AND_ARRAYS;
    public static final .VALUES NON_FINAL;
    public static final .VALUES OBJECT_AND_NON_CONCRETE;

    public static _cls9 valueOf(String s)
    {
        return (_cls9)Enum.valueOf(org/codehaus/jackson/map/ObjectMapper$DefaultTyping, s);
    }

    public static _cls9[] values()
    {
        return (_cls9[])$VALUES.clone();
    }

    static 
    {
        JAVA_LANG_OBJECT = new <init>("JAVA_LANG_OBJECT", 0);
        OBJECT_AND_NON_CONCRETE = new <init>("OBJECT_AND_NON_CONCRETE", 1);
        NON_CONCRETE_AND_ARRAYS = new <init>("NON_CONCRETE_AND_ARRAYS", 2);
        NON_FINAL = new <init>("NON_FINAL", 3);
        g_3B_.clone aclone[] = new <init>[4];
        aclone[0] = JAVA_LANG_OBJECT;
        aclone[1] = OBJECT_AND_NON_CONCRETE;
        aclone[2] = NON_CONCRETE_AND_ARRAYS;
        aclone[3] = NON_FINAL;
        $VALUES = aclone;
    }

    private _cls9(String s, int i)
    {
        super(s, i);
    }
}

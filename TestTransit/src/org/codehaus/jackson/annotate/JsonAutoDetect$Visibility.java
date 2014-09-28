// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.annotate;

import java.lang.reflect.Member;
import java.lang.reflect.Modifier;

// Referenced classes of package org.codehaus.jackson.annotate:
//            JsonAutoDetect

public static final class  extends Enum
{

    private static final .VALUES $VALUES[];
    public static final .VALUES ANY;
    public static final .VALUES DEFAULT;
    public static final .VALUES NONE;
    public static final .VALUES NON_PRIVATE;
    public static final .VALUES PROTECTED_AND_PUBLIC;
    public static final .VALUES PUBLIC_ONLY;

    public static  valueOf(String s)
    {
        return ()Enum.valueOf(org/codehaus/jackson/annotate/JsonAutoDetect$Visibility, s);
    }

    public static [] values()
    {
        return ([])$VALUES.clone();
    }

    public boolean isVisible(Member member)
    {
        boolean flag = true;
        p.org.codehaus.jackson.annotate.JsonAutoDetect.Visibility[ordinal()];
        JVM INSTR tableswitch 1 5: default 44
    //                   1 46
    //                   2 48
    //                   3 50
    //                   4 64
    //                   5 76;
           goto _L1 _L2 _L3 _L4 _L5 _L6
_L2:
        break MISSING_BLOCK_LABEL_46;
_L1:
        flag = false;
_L8:
        return flag;
_L3:
        return false;
_L4:
        if (!Modifier.isPrivate(member.getModifiers())) goto _L8; else goto _L7
_L7:
        return false;
_L5:
        if (Modifier.isProtected(member.getModifiers())) goto _L8; else goto _L6
_L6:
        return Modifier.isPublic(member.getModifiers());
    }

    static 
    {
        ANY = new <init>("ANY", 0);
        NON_PRIVATE = new <init>("NON_PRIVATE", 1);
        PROTECTED_AND_PUBLIC = new <init>("PROTECTED_AND_PUBLIC", 2);
        PUBLIC_ONLY = new <init>("PUBLIC_ONLY", 3);
        NONE = new <init>("NONE", 4);
        DEFAULT = new <init>("DEFAULT", 5);
        ordinal aordinal[] = new <init>[6];
        aordinal[0] = ANY;
        aordinal[1] = NON_PRIVATE;
        aordinal[2] = PROTECTED_AND_PUBLIC;
        aordinal[3] = PUBLIC_ONLY;
        aordinal[4] = NONE;
        aordinal[5] = DEFAULT;
        $VALUES = aordinal;
    }

    private (String s, int i)
    {
        super(s, i);
    }
}

// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.mrbean;

import java.lang.reflect.Member;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class BeanUtil
{

    public BeanUtil()
    {
    }

    private static void _addSuperTypes(Class class1, Class class2, Collection collection, boolean flag)
    {
        if (class1 != class2 && class1 != null && class1 != java/lang/Object) goto _L2; else goto _L1
_L1:
        return;
_L2:
        if (!flag)
        {
            break; /* Loop/switch isn't completed */
        }
        if (collection.contains(class1))
        {
            continue; /* Loop/switch isn't completed */
        }
        collection.add(class1);
        break; /* Loop/switch isn't completed */
        if (true) goto _L1; else goto _L3
_L3:
        Class aclass[] = class1.getInterfaces();
        int i = aclass.length;
        for (int j = 0; j < i; j++)
        {
            _addSuperTypes(aclass[j], class2, collection, true);
        }

        _addSuperTypes(class1.getSuperclass(), class2, collection, true);
        return;
    }

    public static List findSuperTypes(Class class1, Class class2)
    {
        return findSuperTypes(class1, class2, ((List) (new ArrayList())));
    }

    public static List findSuperTypes(Class class1, Class class2, List list)
    {
        _addSuperTypes(class1, class2, list, false);
        return list;
    }

    protected static boolean isConcrete(Member member)
    {
        return (0x600 & member.getModifiers()) == 0;
    }
}

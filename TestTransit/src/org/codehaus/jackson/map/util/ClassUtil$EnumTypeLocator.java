// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.map.util;

import java.lang.reflect.Field;
import java.util.EnumMap;
import java.util.EnumSet;

// Referenced classes of package org.codehaus.jackson.map.util:
//            ClassUtil

private static class 
{

    static final enumSetTypeField instance = new <init>();
    private final Field enumMapTypeField = locateField(java/util/EnumMap, "elementType", java/lang/Class);
    private final Field enumSetTypeField = locateField(java/util/EnumSet, "elementType", java/lang/Class);

    private Object get(Object obj, Field field)
    {
        Object obj1;
        try
        {
            obj1 = field.get(obj);
        }
        catch (Exception exception)
        {
            throw new IllegalArgumentException(exception);
        }
        return obj1;
    }

    private static Field locateField(Class class1, String s, Class class2)
    {
        Field afield[];
        int i;
        int j;
        afield = class1.getDeclaredFields();
        i = afield.length;
        j = 0;
_L2:
        Field field;
label0:
        {
            field = null;
            if (j < i)
            {
                Field field2 = afield[j];
                if (!s.equals(field2.getName()) || field2.getType() != class2)
                {
                    break MISSING_BLOCK_LABEL_94;
                }
                field = field2;
            }
            if (field != null)
            {
                break label0;
            }
            int k = afield.length;
            for (int l = 0; l < k; l++)
            {
                Field field1 = afield[l];
                if (field1.getType() != class2)
                {
                    continue;
                }
                if (field != null)
                {
                    return null;
                }
                field = field1;
            }

            break label0;
        }
        j++;
        continue; /* Loop/switch isn't completed */
        if (field != null)
        {
            try
            {
                field.setAccessible(true);
            }
            catch (Throwable throwable) { }
        }
        return field;
        if (true) goto _L2; else goto _L1
_L1:
    }

    public Class enumTypeFor(EnumMap enummap)
    {
        if (enumMapTypeField != null)
        {
            return (Class)get(enummap, enumMapTypeField);
        } else
        {
            throw new IllegalStateException("Can not figure out type for EnumMap (odd JDK platform?)");
        }
    }

    public Class enumTypeFor(EnumSet enumset)
    {
        if (enumSetTypeField != null)
        {
            return (Class)get(enumset, enumSetTypeField);
        } else
        {
            throw new IllegalStateException("Can not figure out type for EnumSet (odd JDK platform?)");
        }
    }


    private ()
    {
    }
}

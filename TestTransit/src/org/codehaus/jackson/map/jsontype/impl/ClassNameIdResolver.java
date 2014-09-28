// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.map.jsontype.impl;

import java.util.EnumMap;
import java.util.EnumSet;
import org.codehaus.jackson.map.type.CollectionType;
import org.codehaus.jackson.map.type.MapType;
import org.codehaus.jackson.map.type.TypeFactory;
import org.codehaus.jackson.map.util.ClassUtil;
import org.codehaus.jackson.type.JavaType;

// Referenced classes of package org.codehaus.jackson.map.jsontype.impl:
//            TypeIdResolverBase

public class ClassNameIdResolver extends TypeIdResolverBase
{

    public ClassNameIdResolver(JavaType javatype, TypeFactory typefactory)
    {
        super(javatype, typefactory);
    }

    protected final String _idFrom(Object obj, Class class1)
    {
        if (java/lang/Enum.isAssignableFrom(class1) && !class1.isEnum())
        {
            class1 = class1.getSuperclass();
        }
        String s = class1.getName();
        if (s.startsWith("java.util"))
        {
            if (obj instanceof EnumSet)
            {
                Class class3 = ClassUtil.findEnumType((EnumSet)obj);
                s = TypeFactory.defaultInstance().constructCollectionType(java/util/EnumSet, class3).toCanonical();
            } else
            {
                if (obj instanceof EnumMap)
                {
                    Class class2 = ClassUtil.findEnumType((EnumMap)obj);
                    return TypeFactory.defaultInstance().constructMapType(java/util/EnumMap, class2, java/lang/Object).toCanonical();
                }
                String s1 = s.substring(9);
                if ((s1.startsWith(".Arrays$") || s1.startsWith(".Collections$")) && s.indexOf("List") >= 0)
                {
                    return "java.util.ArrayList";
                }
            }
        }
        return s;
    }

    public org.codehaus.jackson.annotate.JsonTypeInfo.Id getMechanism()
    {
        return org.codehaus.jackson.annotate.JsonTypeInfo.Id.CLASS;
    }

    public String idFromValue(Object obj)
    {
        return _idFrom(obj, obj.getClass());
    }

    public String idFromValueAndType(Object obj, Class class1)
    {
        return _idFrom(obj, class1);
    }

    public void registerSubtype(Class class1, String s)
    {
    }

    public JavaType typeFromId(String s)
    {
        if (s.indexOf('<') > 0)
        {
            return TypeFactory.fromCanonical(s);
        }
        JavaType javatype;
        try
        {
            Class class1 = Class.forName(s, true, Thread.currentThread().getContextClassLoader());
            javatype = _typeFactory.constructSpecializedType(_baseType, class1);
        }
        catch (ClassNotFoundException classnotfoundexception)
        {
            throw new IllegalArgumentException((new StringBuilder()).append("Invalid type id '").append(s).append("' (for id type 'Id.class'): no such class found").toString());
        }
        catch (Exception exception)
        {
            throw new IllegalArgumentException((new StringBuilder()).append("Invalid type id '").append(s).append("' (for id type 'Id.class'): ").append(exception.getMessage()).toString(), exception);
        }
        return javatype;
    }
}

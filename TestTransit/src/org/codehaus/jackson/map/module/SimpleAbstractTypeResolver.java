// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.map.module;

import java.lang.reflect.Modifier;
import java.util.HashMap;
import org.codehaus.jackson.map.AbstractTypeResolver;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.type.ClassKey;
import org.codehaus.jackson.type.JavaType;

public class SimpleAbstractTypeResolver extends AbstractTypeResolver
{

    protected final HashMap _mappings = new HashMap();

    public SimpleAbstractTypeResolver()
    {
    }

    public SimpleAbstractTypeResolver addMapping(Class class1, Class class2)
    {
        if (class1 == class2)
        {
            throw new IllegalArgumentException("Can not add mapping from class to itself");
        }
        if (!class1.isAssignableFrom(class2))
        {
            throw new IllegalArgumentException((new StringBuilder()).append("Can not add mapping from class ").append(class1.getName()).append(" to ").append(class2.getName()).append(", as latter is not a subtype of former").toString());
        }
        if (!Modifier.isAbstract(class1.getModifiers()))
        {
            throw new IllegalArgumentException((new StringBuilder()).append("Can not add mapping from class ").append(class1.getName()).append(" since it is not abstract").toString());
        } else
        {
            _mappings.put(new ClassKey(class1), class2);
            return this;
        }
    }

    public JavaType findTypeMapping(DeserializationConfig deserializationconfig, JavaType javatype)
    {
        Class class1 = javatype.getRawClass();
        Class class2 = (Class)_mappings.get(new ClassKey(class1));
        if (class2 == null)
        {
            return null;
        } else
        {
            return javatype.narrowBy(class2);
        }
    }

    public JavaType resolveAbstractType(DeserializationConfig deserializationconfig, JavaType javatype)
    {
        return null;
    }
}

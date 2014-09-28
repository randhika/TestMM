// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.map.jsontype.impl;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import org.codehaus.jackson.map.AnnotationIntrospector;
import org.codehaus.jackson.map.MapperConfig;
import org.codehaus.jackson.map.introspect.BasicBeanDescription;
import org.codehaus.jackson.map.jsontype.NamedType;
import org.codehaus.jackson.type.JavaType;

// Referenced classes of package org.codehaus.jackson.map.jsontype.impl:
//            TypeIdResolverBase

public class TypeNameIdResolver extends TypeIdResolverBase
{

    protected final MapperConfig _config;
    protected final HashMap _idToType;
    protected final HashMap _typeToId;

    protected TypeNameIdResolver(MapperConfig mapperconfig, JavaType javatype, HashMap hashmap, HashMap hashmap1)
    {
        super(javatype, mapperconfig.getTypeFactory());
        _config = mapperconfig;
        _typeToId = hashmap;
        _idToType = hashmap1;
    }

    protected static String _defaultTypeId(Class class1)
    {
        String s = class1.getName();
        int i = s.lastIndexOf('.');
        if (i < 0)
        {
            return s;
        } else
        {
            return s.substring(i + 1);
        }
    }

    public static TypeNameIdResolver construct(MapperConfig mapperconfig, JavaType javatype, Collection collection, boolean flag, boolean flag1)
    {
        if (flag == flag1)
        {
            throw new IllegalArgumentException();
        }
        HashMap hashmap = null;
        if (flag)
        {
            hashmap = new HashMap();
        }
        HashMap hashmap1 = null;
        if (flag1)
        {
            hashmap1 = new HashMap();
        }
        if (collection != null)
        {
            Iterator iterator = collection.iterator();
            do
            {
                if (!iterator.hasNext())
                {
                    break;
                }
                NamedType namedtype = (NamedType)iterator.next();
                Class class1 = namedtype.getType();
                String s;
                if (namedtype.hasName())
                {
                    s = namedtype.getName();
                } else
                {
                    s = _defaultTypeId(class1);
                }
                if (flag)
                {
                    hashmap.put(class1.getName(), s);
                }
                if (flag1)
                {
                    JavaType javatype1 = (JavaType)hashmap1.get(s);
                    if (javatype1 == null || !class1.isAssignableFrom(javatype1.getRawClass()))
                    {
                        hashmap1.put(s, mapperconfig.constructType(class1));
                    }
                }
            } while (true);
        }
        return new TypeNameIdResolver(mapperconfig, javatype, hashmap, hashmap1);
    }

    public org.codehaus.jackson.annotate.JsonTypeInfo.Id getMechanism()
    {
        return org.codehaus.jackson.annotate.JsonTypeInfo.Id.NAME;
    }

    public String idFromValue(Object obj)
    {
        Class class1;
        String s;
        class1 = obj.getClass();
        s = class1.getName();
        HashMap hashmap = _typeToId;
        hashmap;
        JVM INSTR monitorenter ;
        String s1 = (String)_typeToId.get(s);
        if (s1 != null)
        {
            break MISSING_BLOCK_LABEL_99;
        }
        if (_config.isAnnotationProcessingEnabled())
        {
            BasicBeanDescription basicbeandescription = (BasicBeanDescription)_config.introspectClassAnnotations(class1);
            s1 = _config.getAnnotationIntrospector().findTypeName(basicbeandescription.getClassInfo());
        }
        if (s1 != null)
        {
            break MISSING_BLOCK_LABEL_88;
        }
        s1 = _defaultTypeId(class1);
        _typeToId.put(s, s1);
        hashmap;
        JVM INSTR monitorexit ;
        return s1;
        Exception exception;
        exception;
        hashmap;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public String idFromValueAndType(Object obj, Class class1)
    {
        return idFromValue(obj);
    }

    public String toString()
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append('[').append(getClass().getName());
        stringbuilder.append("; id-to-type=").append(_idToType);
        stringbuilder.append(']');
        return stringbuilder.toString();
    }

    public JavaType typeFromId(String s)
        throws IllegalArgumentException
    {
        return (JavaType)_idToType.get(s);
    }
}

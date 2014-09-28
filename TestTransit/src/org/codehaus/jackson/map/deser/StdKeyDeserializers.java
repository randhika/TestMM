// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.map.deser;

import java.util.HashMap;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.KeyDeserializer;
import org.codehaus.jackson.map.introspect.BasicBeanDescription;
import org.codehaus.jackson.map.type.TypeFactory;
import org.codehaus.jackson.type.JavaType;

// Referenced classes of package org.codehaus.jackson.map.deser:
//            StdKeyDeserializer, EnumResolver

class StdKeyDeserializers
{

    final HashMap _keyDeserializers = new HashMap();

    private StdKeyDeserializers()
    {
        add(new StdKeyDeserializer.BoolKD());
        add(new StdKeyDeserializer.ByteKD());
        add(new StdKeyDeserializer.CharKD());
        add(new StdKeyDeserializer.ShortKD());
        add(new StdKeyDeserializer.IntKD());
        add(new StdKeyDeserializer.LongKD());
        add(new StdKeyDeserializer.FloatKD());
        add(new StdKeyDeserializer.DoubleKD());
    }

    private void add(StdKeyDeserializer stdkeydeserializer)
    {
        Class class1 = stdkeydeserializer.getKeyClass();
        _keyDeserializers.put(TypeFactory.defaultInstance().constructType(class1), stdkeydeserializer);
    }

    public static HashMap constructAll()
    {
        return (new StdKeyDeserializers())._keyDeserializers;
    }

    public static KeyDeserializer constructEnumKeyDeserializer(DeserializationConfig deserializationconfig, JavaType javatype)
    {
        return new StdKeyDeserializer.EnumKD(EnumResolver.constructUnsafe(javatype.getRawClass(), deserializationconfig.getAnnotationIntrospector()));
    }

    public static KeyDeserializer findStringBasedKeyDeserializer(DeserializationConfig deserializationconfig, JavaType javatype)
    {
        BasicBeanDescription basicbeandescription = (BasicBeanDescription)deserializationconfig.introspect(javatype);
        java.lang.reflect.Constructor constructor = basicbeandescription.findSingleArgConstructor(new Class[] {
            java/lang/String
        });
        if (constructor != null)
        {
            return new StdKeyDeserializer.StringCtorKeyDeserializer(constructor);
        }
        java.lang.reflect.Method method = basicbeandescription.findFactoryMethod(new Class[] {
            java/lang/String
        });
        if (method != null)
        {
            return new StdKeyDeserializer.StringFactoryKeyDeserializer(method);
        } else
        {
            return null;
        }
    }
}

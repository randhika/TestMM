// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.map.module;

import java.util.HashMap;
import org.codehaus.jackson.map.BeanDescription;
import org.codehaus.jackson.map.BeanProperty;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.KeyDeserializer;
import org.codehaus.jackson.map.KeyDeserializers;
import org.codehaus.jackson.map.type.ClassKey;
import org.codehaus.jackson.type.JavaType;

public class SimpleKeyDeserializers
    implements KeyDeserializers
{

    protected HashMap _classMappings;

    public SimpleKeyDeserializers()
    {
        _classMappings = null;
    }

    public SimpleKeyDeserializers addDeserializer(Class class1, KeyDeserializer keydeserializer)
    {
        if (_classMappings == null)
        {
            _classMappings = new HashMap();
        }
        _classMappings.put(new ClassKey(class1), keydeserializer);
        return this;
    }

    public KeyDeserializer findKeyDeserializer(JavaType javatype, DeserializationConfig deserializationconfig, BeanDescription beandescription, BeanProperty beanproperty)
    {
        if (_classMappings == null)
        {
            return null;
        } else
        {
            return (KeyDeserializer)_classMappings.get(new ClassKey(javatype.getRawClass()));
        }
    }
}

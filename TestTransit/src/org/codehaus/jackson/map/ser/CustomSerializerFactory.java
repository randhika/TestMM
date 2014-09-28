// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.map.ser;

import java.lang.reflect.Modifier;
import java.util.HashMap;
import org.codehaus.jackson.map.BeanProperty;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.SerializerFactory;
import org.codehaus.jackson.map.type.ClassKey;
import org.codehaus.jackson.type.JavaType;

// Referenced classes of package org.codehaus.jackson.map.ser:
//            BeanSerializerFactory

public class CustomSerializerFactory extends BeanSerializerFactory
{

    protected HashMap _directClassMappings;
    protected JsonSerializer _enumSerializerOverride;
    protected HashMap _interfaceMappings;
    protected HashMap _transitiveClassMappings;

    public CustomSerializerFactory()
    {
        this(null);
    }

    public CustomSerializerFactory(org.codehaus.jackson.map.SerializerFactory.Config config)
    {
        super(config);
        _directClassMappings = null;
        _transitiveClassMappings = null;
        _interfaceMappings = null;
    }

    protected JsonSerializer _findInterfaceMapping(Class class1, ClassKey classkey)
    {
        Class aclass[];
        int i;
        int j;
        aclass = class1.getInterfaces();
        i = aclass.length;
        j = 0;
_L5:
        Class class2;
        JsonSerializer jsonserializer;
        if (j >= i)
        {
            break; /* Loop/switch isn't completed */
        }
        class2 = aclass[j];
        classkey.reset(class2);
        jsonserializer = (JsonSerializer)_interfaceMappings.get(classkey);
        if (jsonserializer == null) goto _L2; else goto _L1
_L1:
        return jsonserializer;
_L2:
        if ((jsonserializer = _findInterfaceMapping(class2, classkey)) != null) goto _L1; else goto _L3
_L3:
        j++;
        if (true) goto _L5; else goto _L4
_L4:
        return null;
    }

    public void addGenericMapping(Class class1, JsonSerializer jsonserializer)
    {
        ClassKey classkey = new ClassKey(class1);
        if (class1.isInterface())
        {
            if (_interfaceMappings == null)
            {
                _interfaceMappings = new HashMap();
            }
            _interfaceMappings.put(classkey, jsonserializer);
            return;
        }
        if (_transitiveClassMappings == null)
        {
            _transitiveClassMappings = new HashMap();
        }
        _transitiveClassMappings.put(classkey, jsonserializer);
    }

    public void addSpecificMapping(Class class1, JsonSerializer jsonserializer)
    {
        ClassKey classkey = new ClassKey(class1);
        if (class1.isInterface())
        {
            throw new IllegalArgumentException((new StringBuilder()).append("Can not add specific mapping for an interface (").append(class1.getName()).append(")").toString());
        }
        if (Modifier.isAbstract(class1.getModifiers()))
        {
            throw new IllegalArgumentException((new StringBuilder()).append("Can not add specific mapping for an abstract class (").append(class1.getName()).append(")").toString());
        }
        if (_directClassMappings == null)
        {
            _directClassMappings = new HashMap();
        }
        _directClassMappings.put(classkey, jsonserializer);
    }

    public JsonSerializer createSerializer(SerializationConfig serializationconfig, JavaType javatype, BeanProperty beanproperty)
        throws JsonMappingException
    {
        JsonSerializer jsonserializer = findCustomSerializer(javatype.getRawClass(), serializationconfig);
        if (jsonserializer != null)
        {
            return jsonserializer;
        } else
        {
            return super.createSerializer(serializationconfig, javatype, beanproperty);
        }
    }

    protected JsonSerializer findCustomSerializer(Class class1, SerializationConfig serializationconfig)
    {
        ClassKey classkey = new ClassKey(class1);
        if (_directClassMappings != null)
        {
            JsonSerializer jsonserializer3 = (JsonSerializer)_directClassMappings.get(classkey);
            if (jsonserializer3 != null)
            {
                return jsonserializer3;
            }
        }
        if (class1.isEnum() && _enumSerializerOverride != null)
        {
            return _enumSerializerOverride;
        }
        if (_transitiveClassMappings != null)
        {
            for (Class class3 = class1; class3 != null; class3 = class3.getSuperclass())
            {
                classkey.reset(class3);
                JsonSerializer jsonserializer2 = (JsonSerializer)_transitiveClassMappings.get(classkey);
                if (jsonserializer2 != null)
                {
                    return jsonserializer2;
                }
            }

        }
        if (_interfaceMappings != null)
        {
            classkey.reset(class1);
            JsonSerializer jsonserializer = (JsonSerializer)_interfaceMappings.get(classkey);
            if (jsonserializer != null)
            {
                return jsonserializer;
            }
            for (Class class2 = class1; class2 != null; class2 = class2.getSuperclass())
            {
                JsonSerializer jsonserializer1 = _findInterfaceMapping(class2, classkey);
                if (jsonserializer1 != null)
                {
                    return jsonserializer1;
                }
            }

        }
        return null;
    }

    public void setEnumSerializer(JsonSerializer jsonserializer)
    {
        _enumSerializerOverride = jsonserializer;
    }

    public SerializerFactory withConfig(org.codehaus.jackson.map.SerializerFactory.Config config)
    {
        if (getClass() != org/codehaus/jackson/map/ser/CustomSerializerFactory)
        {
            throw new IllegalStateException((new StringBuilder()).append("Subtype of CustomSerializerFactory (").append(getClass().getName()).append(") has not properly overridden method 'withAdditionalSerializers': can not instantiate subtype with ").append("additional serializer definitions").toString());
        } else
        {
            return new CustomSerializerFactory(config);
        }
    }
}

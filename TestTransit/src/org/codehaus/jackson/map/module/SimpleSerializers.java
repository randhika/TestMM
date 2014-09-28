// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.map.module;

import java.util.HashMap;
import org.codehaus.jackson.map.BeanDescription;
import org.codehaus.jackson.map.BeanProperty;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.Serializers;
import org.codehaus.jackson.map.TypeSerializer;
import org.codehaus.jackson.map.type.ArrayType;
import org.codehaus.jackson.map.type.ClassKey;
import org.codehaus.jackson.map.type.CollectionLikeType;
import org.codehaus.jackson.map.type.CollectionType;
import org.codehaus.jackson.map.type.MapLikeType;
import org.codehaus.jackson.map.type.MapType;
import org.codehaus.jackson.type.JavaType;

public class SimpleSerializers
    implements Serializers
{

    protected HashMap _classMappings;
    protected HashMap _interfaceMappings;

    public SimpleSerializers()
    {
        _classMappings = null;
        _interfaceMappings = null;
    }

    private void _addSerializer(Class class1, JsonSerializer jsonserializer)
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
        if (_classMappings == null)
        {
            _classMappings = new HashMap();
        }
        _classMappings.put(classkey, jsonserializer);
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

    public void addSerializer(Class class1, JsonSerializer jsonserializer)
    {
        _addSerializer(class1, jsonserializer);
    }

    public void addSerializer(JsonSerializer jsonserializer)
    {
        Class class1 = jsonserializer.handledType();
        if (class1 == null || class1 == java/lang/Object)
        {
            throw new IllegalArgumentException((new StringBuilder()).append("JsonSerializer of type ").append(jsonserializer.getClass().getName()).append(" does not define valid handledType() (use alternative registration method?)").toString());
        } else
        {
            _addSerializer(class1, jsonserializer);
            return;
        }
    }

    public JsonSerializer findArraySerializer(SerializationConfig serializationconfig, ArrayType arraytype, BeanDescription beandescription, BeanProperty beanproperty, TypeSerializer typeserializer, JsonSerializer jsonserializer)
    {
        return findSerializer(serializationconfig, arraytype, beandescription, beanproperty);
    }

    public JsonSerializer findCollectionLikeSerializer(SerializationConfig serializationconfig, CollectionLikeType collectionliketype, BeanDescription beandescription, BeanProperty beanproperty, TypeSerializer typeserializer, JsonSerializer jsonserializer)
    {
        return findSerializer(serializationconfig, collectionliketype, beandescription, beanproperty);
    }

    public JsonSerializer findCollectionSerializer(SerializationConfig serializationconfig, CollectionType collectiontype, BeanDescription beandescription, BeanProperty beanproperty, TypeSerializer typeserializer, JsonSerializer jsonserializer)
    {
        return findSerializer(serializationconfig, collectiontype, beandescription, beanproperty);
    }

    public JsonSerializer findMapLikeSerializer(SerializationConfig serializationconfig, MapLikeType mapliketype, BeanDescription beandescription, BeanProperty beanproperty, JsonSerializer jsonserializer, TypeSerializer typeserializer, JsonSerializer jsonserializer1)
    {
        return findSerializer(serializationconfig, mapliketype, beandescription, beanproperty);
    }

    public JsonSerializer findMapSerializer(SerializationConfig serializationconfig, MapType maptype, BeanDescription beandescription, BeanProperty beanproperty, JsonSerializer jsonserializer, TypeSerializer typeserializer, JsonSerializer jsonserializer1)
    {
        return findSerializer(serializationconfig, maptype, beandescription, beanproperty);
    }

    public JsonSerializer findSerializer(SerializationConfig serializationconfig, JavaType javatype, BeanDescription beandescription, BeanProperty beanproperty)
    {
label0:
        {
            Class class1 = javatype.getRawClass();
            ClassKey classkey = new ClassKey(class1);
            if (class1.isInterface())
            {
                if (_interfaceMappings != null)
                {
                    JsonSerializer jsonserializer4 = (JsonSerializer)_interfaceMappings.get(classkey);
                    if (jsonserializer4 != null)
                    {
                        return jsonserializer4;
                    }
                }
            } else
            if (_classMappings != null)
            {
                JsonSerializer jsonserializer2 = (JsonSerializer)_classMappings.get(classkey);
                if (jsonserializer2 != null)
                {
                    return jsonserializer2;
                }
                for (Class class2 = class1; class2 != null; class2 = class2.getSuperclass())
                {
                    classkey.reset(class2);
                    JsonSerializer jsonserializer3 = (JsonSerializer)_classMappings.get(classkey);
                    if (jsonserializer3 != null)
                    {
                        return jsonserializer3;
                    }
                }

            }
            if (_interfaceMappings == null)
            {
                break label0;
            }
            JsonSerializer jsonserializer = _findInterfaceMapping(class1, classkey);
            if (jsonserializer != null)
            {
                return jsonserializer;
            }
            if (class1.isInterface())
            {
                break label0;
            }
            JsonSerializer jsonserializer1;
            do
            {
                class1 = class1.getSuperclass();
                if (class1 == null)
                {
                    break label0;
                }
                jsonserializer1 = _findInterfaceMapping(class1, classkey);
            } while (jsonserializer1 == null);
            return jsonserializer1;
        }
        return null;
    }
}

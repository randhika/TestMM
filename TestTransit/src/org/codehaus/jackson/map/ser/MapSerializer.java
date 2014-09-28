// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.map.ser;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.BeanProperty;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.ResolvableSerializer;
import org.codehaus.jackson.map.SerializerProvider;
import org.codehaus.jackson.map.TypeSerializer;
import org.codehaus.jackson.map.ser.impl.PropertySerializerMap;
import org.codehaus.jackson.map.type.TypeFactory;
import org.codehaus.jackson.type.JavaType;

// Referenced classes of package org.codehaus.jackson.map.ser:
//            ContainerSerializerBase

public class MapSerializer extends ContainerSerializerBase
    implements ResolvableSerializer
{

    protected static final JavaType UNSPECIFIED_TYPE = TypeFactory.unknownType();
    protected PropertySerializerMap _dynamicValueSerializers;
    protected final HashSet _ignoredEntries;
    protected JsonSerializer _keySerializer;
    protected final JavaType _keyType;
    protected final BeanProperty _property;
    protected JsonSerializer _valueSerializer;
    protected final JavaType _valueType;
    protected final boolean _valueTypeIsStatic;
    protected final TypeSerializer _valueTypeSerializer;

    protected MapSerializer()
    {
        this((HashSet)null, null, null, false, null, null, null, null);
    }

    protected MapSerializer(HashSet hashset, JavaType javatype, JavaType javatype1, boolean flag, TypeSerializer typeserializer, JsonSerializer jsonserializer, BeanProperty beanproperty)
    {
        this(hashset, javatype, javatype1, flag, typeserializer, jsonserializer, null, beanproperty);
    }

    protected MapSerializer(HashSet hashset, JavaType javatype, JavaType javatype1, boolean flag, TypeSerializer typeserializer, JsonSerializer jsonserializer, JsonSerializer jsonserializer1, 
            BeanProperty beanproperty)
    {
        super(java/util/Map, false);
        _property = beanproperty;
        _ignoredEntries = hashset;
        _keyType = javatype;
        _valueType = javatype1;
        _valueTypeIsStatic = flag;
        _valueTypeSerializer = typeserializer;
        _keySerializer = jsonserializer;
        _valueSerializer = jsonserializer1;
        _dynamicValueSerializers = PropertySerializerMap.emptyMap();
    }

    protected MapSerializer(HashSet hashset, JavaType javatype, boolean flag, TypeSerializer typeserializer)
    {
        this(hashset, UNSPECIFIED_TYPE, javatype, flag, typeserializer, null, null, null);
    }

    public static MapSerializer construct(String as[], JavaType javatype, boolean flag, TypeSerializer typeserializer, BeanProperty beanproperty)
    {
        return construct(as, javatype, flag, typeserializer, beanproperty, null, null);
    }

    public static MapSerializer construct(String as[], JavaType javatype, boolean flag, TypeSerializer typeserializer, BeanProperty beanproperty, JsonSerializer jsonserializer, JsonSerializer jsonserializer1)
    {
        HashSet hashset = toSet(as);
        JavaType javatype1;
        JavaType javatype2;
        if (javatype == null)
        {
            javatype2 = UNSPECIFIED_TYPE;
            javatype1 = javatype2;
        } else
        {
            javatype1 = javatype.getKeyType();
            javatype2 = javatype.getContentType();
        }
        if (!flag)
        {
            if (javatype2 != null && javatype2.isFinal())
            {
                flag = true;
            } else
            {
                flag = false;
            }
        }
        return new MapSerializer(hashset, javatype1, javatype2, flag, typeserializer, jsonserializer, jsonserializer1, beanproperty);
    }

    private static HashSet toSet(String as[])
    {
        HashSet hashset;
        if (as == null || as.length == 0)
        {
            hashset = null;
        } else
        {
            hashset = new HashSet(as.length);
            int i = as.length;
            int j = 0;
            while (j < i) 
            {
                hashset.add(as[j]);
                j++;
            }
        }
        return hashset;
    }

    protected final JsonSerializer _findAndAddDynamic(PropertySerializerMap propertyserializermap, Class class1, SerializerProvider serializerprovider)
        throws JsonMappingException
    {
        org.codehaus.jackson.map.ser.impl.PropertySerializerMap.SerializerAndMapResult serializerandmapresult = propertyserializermap.findAndAddSerializer(class1, serializerprovider, _property);
        if (propertyserializermap != serializerandmapresult.map)
        {
            _dynamicValueSerializers = serializerandmapresult.map;
        }
        return serializerandmapresult.serializer;
    }

    protected final JsonSerializer _findAndAddDynamic(PropertySerializerMap propertyserializermap, JavaType javatype, SerializerProvider serializerprovider)
        throws JsonMappingException
    {
        org.codehaus.jackson.map.ser.impl.PropertySerializerMap.SerializerAndMapResult serializerandmapresult = propertyserializermap.findAndAddSerializer(javatype, serializerprovider, _property);
        if (propertyserializermap != serializerandmapresult.map)
        {
            _dynamicValueSerializers = serializerandmapresult.map;
        }
        return serializerandmapresult.serializer;
    }

    public ContainerSerializerBase _withValueTypeSerializer(TypeSerializer typeserializer)
    {
        MapSerializer mapserializer = new MapSerializer(_ignoredEntries, _keyType, _valueType, _valueTypeIsStatic, typeserializer, _keySerializer, _valueSerializer, _property);
        if (_valueSerializer != null)
        {
            mapserializer._valueSerializer = _valueSerializer;
        }
        return mapserializer;
    }

    public JsonNode getSchema(SerializerProvider serializerprovider, Type type)
    {
        return createSchemaNode("object", true);
    }

    public void resolve(SerializerProvider serializerprovider)
        throws JsonMappingException
    {
        if (_valueTypeIsStatic && _valueSerializer == null)
        {
            _valueSerializer = serializerprovider.findValueSerializer(_valueType, _property);
        }
        if (_keySerializer == null)
        {
            _keySerializer = serializerprovider.findKeySerializer(_keyType, _property);
        }
    }

    public volatile void serialize(Object obj, JsonGenerator jsongenerator, SerializerProvider serializerprovider)
        throws IOException, JsonGenerationException
    {
        serialize((Map)obj, jsongenerator, serializerprovider);
    }

    public void serialize(Map map, JsonGenerator jsongenerator, SerializerProvider serializerprovider)
        throws IOException, JsonGenerationException
    {
        jsongenerator.writeStartObject();
        if (!map.isEmpty())
        {
            if (_valueSerializer != null)
            {
                serializeFieldsUsing(map, jsongenerator, serializerprovider, _valueSerializer);
            } else
            {
                serializeFields(map, jsongenerator, serializerprovider);
            }
        }
        jsongenerator.writeEndObject();
    }

    protected void serializeFields(Map map, JsonGenerator jsongenerator, SerializerProvider serializerprovider)
        throws IOException, JsonGenerationException
    {
        if (_valueTypeSerializer == null) goto _L2; else goto _L1
_L1:
        serializeTypedFields(map, jsongenerator, serializerprovider);
_L4:
        return;
_L2:
        JsonSerializer jsonserializer;
        HashSet hashset;
        boolean flag;
        PropertySerializerMap propertyserializermap;
        Object obj;
        Object obj1;
        jsonserializer = _keySerializer;
        hashset = _ignoredEntries;
        Iterator iterator;
        java.util.Map.Entry entry;
        if (!serializerprovider.isEnabled(org.codehaus.jackson.map.SerializationConfig.Feature.WRITE_NULL_MAP_VALUES))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        propertyserializermap = _dynamicValueSerializers;
        iterator = map.entrySet().iterator();
_L5:
        if (!iterator.hasNext()) goto _L4; else goto _L3
_L3:
        entry = (java.util.Map.Entry)iterator.next();
        obj = entry.getValue();
        obj1 = entry.getKey();
        if (obj1 != null)
        {
            continue; /* Loop/switch isn't completed */
        }
        serializerprovider.getNullKeySerializer().serialize(null, jsongenerator, serializerprovider);
_L7:
        if (obj == null)
        {
            serializerprovider.defaultSerializeNull(jsongenerator);
        } else
        {
            Class class1 = obj.getClass();
            JsonSerializer jsonserializer1 = propertyserializermap.serializerFor(class1);
            if (jsonserializer1 == null)
            {
                Exception exception;
                if (_valueType.hasGenericTypes())
                {
                    jsonserializer1 = _findAndAddDynamic(propertyserializermap, _valueType.forcedNarrowBy(class1), serializerprovider);
                } else
                {
                    jsonserializer1 = _findAndAddDynamic(propertyserializermap, class1, serializerprovider);
                }
                propertyserializermap = _dynamicValueSerializers;
            }
            try
            {
                jsonserializer1.serialize(obj, jsongenerator, serializerprovider);
            }
            // Misplaced declaration of an exception variable
            catch (Exception exception)
            {
                wrapAndThrow(serializerprovider, exception, map, (new StringBuilder()).append("").append(obj1).toString());
            }
        }
          goto _L5
          goto _L4
        if (flag && obj == null || hashset != null && hashset.contains(obj1)) goto _L5; else goto _L6
_L6:
        jsonserializer.serialize(obj1, jsongenerator, serializerprovider);
          goto _L7
    }

    protected void serializeFieldsUsing(Map map, JsonGenerator jsongenerator, SerializerProvider serializerprovider, JsonSerializer jsonserializer)
        throws IOException, JsonGenerationException
    {
        JsonSerializer jsonserializer1;
        HashSet hashset;
        TypeSerializer typeserializer;
        boolean flag;
        Object obj;
        Object obj1;
        jsonserializer1 = _keySerializer;
        hashset = _ignoredEntries;
        typeserializer = _valueTypeSerializer;
        Iterator iterator;
        java.util.Map.Entry entry;
        if (!serializerprovider.isEnabled(org.codehaus.jackson.map.SerializationConfig.Feature.WRITE_NULL_MAP_VALUES))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        iterator = map.entrySet().iterator();
_L2:
        if (!iterator.hasNext())
        {
            break; /* Loop/switch isn't completed */
        }
        entry = (java.util.Map.Entry)iterator.next();
        obj = entry.getValue();
        obj1 = entry.getKey();
        if (obj1 == null)
        {
            serializerprovider.getNullKeySerializer().serialize(null, jsongenerator, serializerprovider);
        } else
        {
            if (flag && obj == null || hashset != null && hashset.contains(obj1))
            {
                continue; /* Loop/switch isn't completed */
            }
            jsonserializer1.serialize(obj1, jsongenerator, serializerprovider);
        }
        if (obj == null)
        {
            serializerprovider.defaultSerializeNull(jsongenerator);
            continue; /* Loop/switch isn't completed */
        }
        if (typeserializer == null)
        {
            try
            {
                jsonserializer.serialize(obj, jsongenerator, serializerprovider);
            }
            catch (Exception exception)
            {
                wrapAndThrow(serializerprovider, exception, map, (new StringBuilder()).append("").append(obj1).toString());
            }
            continue; /* Loop/switch isn't completed */
        }
        jsonserializer.serializeWithType(obj, jsongenerator, serializerprovider, typeserializer);
        if (true) goto _L2; else goto _L1
_L1:
    }

    protected void serializeTypedFields(Map map, JsonGenerator jsongenerator, SerializerProvider serializerprovider)
        throws IOException, JsonGenerationException
    {
        JsonSerializer jsonserializer = _keySerializer;
        JsonSerializer jsonserializer1 = null;
        Class class1 = null;
        HashSet hashset = _ignoredEntries;
        boolean flag;
        Iterator iterator;
        if (!serializerprovider.isEnabled(org.codehaus.jackson.map.SerializationConfig.Feature.WRITE_NULL_MAP_VALUES))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        iterator = map.entrySet().iterator();
        do
        {
            if (iterator.hasNext())
            {
                java.util.Map.Entry entry = (java.util.Map.Entry)iterator.next();
                Object obj = entry.getValue();
                Object obj1 = entry.getKey();
                if (obj1 == null)
                {
                    serializerprovider.getNullKeySerializer().serialize(null, jsongenerator, serializerprovider);
                } else
                {
                    if (flag && obj == null || hashset != null && hashset.contains(obj1))
                    {
                        continue;
                    }
                    jsonserializer.serialize(obj1, jsongenerator, serializerprovider);
                }
                if (obj == null)
                {
                    serializerprovider.defaultSerializeNull(jsongenerator);
                } else
                {
                    Class class2 = obj.getClass();
                    JsonSerializer jsonserializer2;
                    if (class2 == class1)
                    {
                        jsonserializer2 = jsonserializer1;
                    } else
                    {
                        jsonserializer2 = serializerprovider.findValueSerializer(class2, _property);
                        jsonserializer1 = jsonserializer2;
                        class1 = class2;
                    }
                    try
                    {
                        jsonserializer2.serializeWithType(obj, jsongenerator, serializerprovider, _valueTypeSerializer);
                    }
                    catch (Exception exception)
                    {
                        wrapAndThrow(serializerprovider, exception, map, (new StringBuilder()).append("").append(obj1).toString());
                    }
                }
                continue;
            }
            return;
        } while (true);
    }

    public volatile void serializeWithType(Object obj, JsonGenerator jsongenerator, SerializerProvider serializerprovider, TypeSerializer typeserializer)
        throws IOException, JsonProcessingException
    {
        serializeWithType((Map)obj, jsongenerator, serializerprovider, typeserializer);
    }

    public void serializeWithType(Map map, JsonGenerator jsongenerator, SerializerProvider serializerprovider, TypeSerializer typeserializer)
        throws IOException, JsonGenerationException
    {
        typeserializer.writeTypePrefixForObject(map, jsongenerator);
        if (!map.isEmpty())
        {
            if (_valueSerializer != null)
            {
                serializeFieldsUsing(map, jsongenerator, serializerprovider, _valueSerializer);
            } else
            {
                serializeFields(map, jsongenerator, serializerprovider);
            }
        }
        typeserializer.writeTypeSuffixForObject(map, jsongenerator);
    }

}

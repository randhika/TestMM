// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.map.ser;

import java.io.IOException;
import java.lang.reflect.Type;
import java.text.DateFormat;
import java.util.Date;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.BeanProperty;
import org.codehaus.jackson.map.ContextualSerializer;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.SerializerFactory;
import org.codehaus.jackson.map.SerializerProvider;
import org.codehaus.jackson.map.TypeSerializer;
import org.codehaus.jackson.map.ser.impl.ReadOnlyClassToSerializerMap;
import org.codehaus.jackson.map.util.ClassUtil;
import org.codehaus.jackson.map.util.RootNameLookup;
import org.codehaus.jackson.node.ObjectNode;
import org.codehaus.jackson.schema.JsonSchema;
import org.codehaus.jackson.schema.SchemaAware;
import org.codehaus.jackson.type.JavaType;

// Referenced classes of package org.codehaus.jackson.map.ser:
//            FailingSerializer, StdKeySerializer, NullSerializer, SerializerCache, 
//            SerializerBase

public class StdSerializerProvider extends SerializerProvider
{
    private static final class WrappedSerializer extends JsonSerializer
    {

        protected final JsonSerializer _serializer;
        protected final TypeSerializer _typeSerializer;

        public Class handledType()
        {
            return java/lang/Object;
        }

        public void serialize(Object obj, JsonGenerator jsongenerator, SerializerProvider serializerprovider)
            throws IOException, JsonProcessingException
        {
            _serializer.serializeWithType(obj, jsongenerator, serializerprovider, _typeSerializer);
        }

        public void serializeWithType(Object obj, JsonGenerator jsongenerator, SerializerProvider serializerprovider, TypeSerializer typeserializer)
            throws IOException, JsonProcessingException
        {
            _serializer.serializeWithType(obj, jsongenerator, serializerprovider, typeserializer);
        }

        public WrappedSerializer(TypeSerializer typeserializer, JsonSerializer jsonserializer)
        {
            _typeSerializer = typeserializer;
            _serializer = jsonserializer;
        }
    }


    static final boolean CACHE_UNKNOWN_MAPPINGS;
    public static final JsonSerializer DEFAULT_KEY_SERIALIZER = new StdKeySerializer();
    public static final JsonSerializer DEFAULT_NULL_KEY_SERIALIZER = new FailingSerializer("Null key for a Map not allowed in JSON (use a converting NullKeySerializer?)");
    public static final JsonSerializer DEFAULT_UNKNOWN_SERIALIZER = new SerializerBase(java/lang/Object) {

        protected void failForEmpty(Object obj)
            throws JsonMappingException
        {
            throw new JsonMappingException((new StringBuilder()).append("No serializer found for class ").append(obj.getClass().getName()).append(" and no properties discovered to create BeanSerializer (to avoid exception, disable SerializationConfig.Feature.FAIL_ON_EMPTY_BEANS) )").toString());
        }

        public JsonNode getSchema(SerializerProvider serializerprovider, Type type)
            throws JsonMappingException
        {
            return null;
        }

        public void serialize(Object obj, JsonGenerator jsongenerator, SerializerProvider serializerprovider)
            throws IOException, JsonMappingException
        {
            if (serializerprovider.isEnabled(org.codehaus.jackson.map.SerializationConfig.Feature.FAIL_ON_EMPTY_BEANS))
            {
                failForEmpty(obj);
            }
            jsongenerator.writeStartObject();
            jsongenerator.writeEndObject();
        }

        public final void serializeWithType(Object obj, JsonGenerator jsongenerator, SerializerProvider serializerprovider, TypeSerializer typeserializer)
            throws IOException, JsonGenerationException
        {
            if (serializerprovider.isEnabled(org.codehaus.jackson.map.SerializationConfig.Feature.FAIL_ON_EMPTY_BEANS))
            {
                failForEmpty(obj);
            }
            typeserializer.writeTypePrefixForObject(obj, jsongenerator);
            typeserializer.writeTypeSuffixForObject(obj, jsongenerator);
        }

    };
    protected DateFormat _dateFormat;
    protected JsonSerializer _keySerializer;
    protected final ReadOnlyClassToSerializerMap _knownSerializers;
    protected JsonSerializer _nullKeySerializer;
    protected JsonSerializer _nullValueSerializer;
    protected final RootNameLookup _rootNames;
    protected final SerializerCache _serializerCache;
    protected final SerializerFactory _serializerFactory;
    protected JsonSerializer _unknownTypeSerializer;

    public StdSerializerProvider()
    {
        super(null);
        _unknownTypeSerializer = DEFAULT_UNKNOWN_SERIALIZER;
        _keySerializer = DEFAULT_KEY_SERIALIZER;
        _nullValueSerializer = NullSerializer.instance;
        _nullKeySerializer = DEFAULT_NULL_KEY_SERIALIZER;
        _serializerFactory = null;
        _serializerCache = new SerializerCache();
        _knownSerializers = null;
        _rootNames = new RootNameLookup();
    }

    protected StdSerializerProvider(SerializationConfig serializationconfig, StdSerializerProvider stdserializerprovider, SerializerFactory serializerfactory)
    {
        super(serializationconfig);
        _unknownTypeSerializer = DEFAULT_UNKNOWN_SERIALIZER;
        _keySerializer = DEFAULT_KEY_SERIALIZER;
        _nullValueSerializer = NullSerializer.instance;
        _nullKeySerializer = DEFAULT_NULL_KEY_SERIALIZER;
        if (serializationconfig == null)
        {
            throw new NullPointerException();
        } else
        {
            _serializerFactory = serializerfactory;
            _serializerCache = stdserializerprovider._serializerCache;
            _unknownTypeSerializer = stdserializerprovider._unknownTypeSerializer;
            _keySerializer = stdserializerprovider._keySerializer;
            _nullValueSerializer = stdserializerprovider._nullValueSerializer;
            _nullKeySerializer = stdserializerprovider._nullKeySerializer;
            _rootNames = stdserializerprovider._rootNames;
            _knownSerializers = _serializerCache.getReadOnlyLookupMap();
            return;
        }
    }

    protected JsonSerializer _createAndCacheUntypedSerializer(Class class1, BeanProperty beanproperty)
        throws JsonMappingException
    {
        JsonSerializer jsonserializer;
        try
        {
            jsonserializer = _createUntypedSerializer(_config.constructType(class1), beanproperty);
        }
        catch (IllegalArgumentException illegalargumentexception)
        {
            throw new JsonMappingException(illegalargumentexception.getMessage(), null, illegalargumentexception);
        }
        if (jsonserializer != null)
        {
            _serializerCache.addAndResolveNonTypedSerializer(class1, jsonserializer, this);
        }
        return jsonserializer;
    }

    protected JsonSerializer _createAndCacheUntypedSerializer(JavaType javatype, BeanProperty beanproperty)
        throws JsonMappingException
    {
        JsonSerializer jsonserializer;
        try
        {
            jsonserializer = _createUntypedSerializer(javatype, beanproperty);
        }
        catch (IllegalArgumentException illegalargumentexception)
        {
            throw new JsonMappingException(illegalargumentexception.getMessage(), null, illegalargumentexception);
        }
        if (jsonserializer != null)
        {
            _serializerCache.addAndResolveNonTypedSerializer(javatype, jsonserializer, this);
        }
        return jsonserializer;
    }

    protected JsonSerializer _createUntypedSerializer(JavaType javatype, BeanProperty beanproperty)
        throws JsonMappingException
    {
        return _serializerFactory.createSerializer(_config, javatype, beanproperty);
    }

    protected JsonSerializer _findExplicitUntypedSerializer(Class class1, BeanProperty beanproperty)
    {
        JsonSerializer jsonserializer = _knownSerializers.untypedValueSerializer(class1);
        if (jsonserializer != null)
        {
            return jsonserializer;
        }
        JsonSerializer jsonserializer1 = _serializerCache.untypedValueSerializer(class1);
        if (jsonserializer1 != null)
        {
            return jsonserializer1;
        }
        JsonSerializer jsonserializer2;
        try
        {
            jsonserializer2 = _createAndCacheUntypedSerializer(class1, beanproperty);
        }
        catch (Exception exception)
        {
            return null;
        }
        return jsonserializer2;
    }

    protected void _reportIncompatibleRootType(Object obj, JavaType javatype)
        throws IOException, JsonProcessingException
    {
        if (javatype.isPrimitive() && ClassUtil.wrapperType(javatype.getRawClass()).isAssignableFrom(obj.getClass()))
        {
            return;
        } else
        {
            throw new JsonMappingException((new StringBuilder()).append("Incompatible types: declared root type (").append(javatype).append(") vs ").append(obj.getClass().getName()).toString());
        }
    }

    protected void _serializeValue(JsonGenerator jsongenerator, Object obj)
        throws IOException, JsonProcessingException
    {
        if (obj != null) goto _L2; else goto _L1
_L1:
        JsonSerializer jsonserializer;
        boolean flag;
        jsonserializer = getNullValueSerializer();
        flag = false;
_L4:
        try
        {
            jsonserializer.serialize(obj, jsongenerator, this);
        }
        catch (IOException ioexception)
        {
            throw ioexception;
        }
        catch (Exception exception)
        {
            String s = exception.getMessage();
            if (s == null)
            {
                s = (new StringBuilder()).append("[no message for ").append(exception.getClass().getName()).append("]").toString();
            }
            throw new JsonMappingException(s, exception);
        }
        if (!flag)
        {
            break MISSING_BLOCK_LABEL_28;
        }
        jsongenerator.writeEndObject();
        return;
_L2:
        jsonserializer = findTypedValueSerializer(obj.getClass(), true, null);
        flag = _config.isEnabled(org.codehaus.jackson.map.SerializationConfig.Feature.WRAP_ROOT_VALUE);
        if (flag)
        {
            jsongenerator.writeStartObject();
            jsongenerator.writeFieldName(_rootNames.findRootName(obj.getClass(), _config));
        }
        if (true) goto _L4; else goto _L3
_L3:
    }

    protected void _serializeValue(JsonGenerator jsongenerator, Object obj, JavaType javatype)
        throws IOException, JsonProcessingException
    {
        if (obj != null) goto _L2; else goto _L1
_L1:
        JsonSerializer jsonserializer;
        boolean flag;
        jsonserializer = getNullValueSerializer();
        flag = false;
_L4:
        try
        {
            jsonserializer.serialize(obj, jsongenerator, this);
        }
        catch (IOException ioexception)
        {
            throw ioexception;
        }
        catch (Exception exception)
        {
            String s = exception.getMessage();
            if (s == null)
            {
                s = (new StringBuilder()).append("[no message for ").append(exception.getClass().getName()).append("]").toString();
            }
            throw new JsonMappingException(s, exception);
        }
        if (!flag)
        {
            break MISSING_BLOCK_LABEL_30;
        }
        jsongenerator.writeEndObject();
        return;
_L2:
        if (!javatype.getRawClass().isAssignableFrom(obj.getClass()))
        {
            _reportIncompatibleRootType(obj, javatype);
        }
        jsonserializer = findTypedValueSerializer(javatype, true, null);
        flag = _config.isEnabled(org.codehaus.jackson.map.SerializationConfig.Feature.WRAP_ROOT_VALUE);
        if (flag)
        {
            jsongenerator.writeStartObject();
            jsongenerator.writeFieldName(_rootNames.findRootName(javatype, _config));
        }
        if (true) goto _L4; else goto _L3
_L3:
    }

    public int cachedSerializersCount()
    {
        return _serializerCache.size();
    }

    protected StdSerializerProvider createInstance(SerializationConfig serializationconfig, SerializerFactory serializerfactory)
    {
        return new StdSerializerProvider(serializationconfig, this, serializerfactory);
    }

    public final void defaultSerializeDateValue(long l, JsonGenerator jsongenerator)
        throws IOException, JsonProcessingException
    {
        if (isEnabled(org.codehaus.jackson.map.SerializationConfig.Feature.WRITE_DATES_AS_TIMESTAMPS))
        {
            jsongenerator.writeNumber(l);
            return;
        }
        if (_dateFormat == null)
        {
            _dateFormat = (DateFormat)_config.getDateFormat().clone();
        }
        jsongenerator.writeString(_dateFormat.format(new Date(l)));
    }

    public final void defaultSerializeDateValue(Date date, JsonGenerator jsongenerator)
        throws IOException, JsonProcessingException
    {
        if (isEnabled(org.codehaus.jackson.map.SerializationConfig.Feature.WRITE_DATES_AS_TIMESTAMPS))
        {
            jsongenerator.writeNumber(date.getTime());
            return;
        }
        if (_dateFormat == null)
        {
            _dateFormat = (DateFormat)_config.getDateFormat().clone();
        }
        jsongenerator.writeString(_dateFormat.format(date));
    }

    public JsonSerializer findKeySerializer(JavaType javatype, BeanProperty beanproperty)
        throws JsonMappingException
    {
        JsonSerializer jsonserializer = _serializerFactory.createKeySerializer(_config, javatype, beanproperty);
        if (jsonserializer == null)
        {
            jsonserializer = _keySerializer;
        }
        if (jsonserializer instanceof ContextualSerializer)
        {
            jsonserializer = ((ContextualSerializer)jsonserializer).createContextual(_config, beanproperty);
        }
        return jsonserializer;
    }

    public JsonSerializer findTypedValueSerializer(Class class1, boolean flag, BeanProperty beanproperty)
        throws JsonMappingException
    {
        JsonSerializer jsonserializer = _knownSerializers.typedValueSerializer(class1);
        if (jsonserializer != null)
        {
            return jsonserializer;
        }
        JsonSerializer jsonserializer1 = _serializerCache.typedValueSerializer(class1);
        if (jsonserializer1 != null)
        {
            return jsonserializer1;
        }
        Object obj = findValueSerializer(class1, beanproperty);
        TypeSerializer typeserializer = _serializerFactory.createTypeSerializer(_config, _config.constructType(class1), beanproperty);
        if (typeserializer != null)
        {
            obj = new WrappedSerializer(typeserializer, ((JsonSerializer) (obj)));
        }
        if (flag)
        {
            _serializerCache.addTypedSerializer(class1, ((JsonSerializer) (obj)));
        }
        return ((JsonSerializer) (obj));
    }

    public JsonSerializer findTypedValueSerializer(JavaType javatype, boolean flag, BeanProperty beanproperty)
        throws JsonMappingException
    {
        JsonSerializer jsonserializer = _knownSerializers.typedValueSerializer(javatype);
        if (jsonserializer != null)
        {
            return jsonserializer;
        }
        JsonSerializer jsonserializer1 = _serializerCache.typedValueSerializer(javatype);
        if (jsonserializer1 != null)
        {
            return jsonserializer1;
        }
        Object obj = findValueSerializer(javatype, beanproperty);
        TypeSerializer typeserializer = _serializerFactory.createTypeSerializer(_config, javatype, beanproperty);
        if (typeserializer != null)
        {
            obj = new WrappedSerializer(typeserializer, ((JsonSerializer) (obj)));
        }
        if (flag)
        {
            _serializerCache.addTypedSerializer(javatype, ((JsonSerializer) (obj)));
        }
        return ((JsonSerializer) (obj));
    }

    public JsonSerializer findValueSerializer(Class class1, BeanProperty beanproperty)
        throws JsonMappingException
    {
        JsonSerializer jsonserializer = _knownSerializers.untypedValueSerializer(class1);
        if (jsonserializer == null)
        {
            jsonserializer = _serializerCache.untypedValueSerializer(class1);
            if (jsonserializer == null)
            {
                jsonserializer = _serializerCache.untypedValueSerializer(_config.constructType(class1));
                if (jsonserializer == null)
                {
                    jsonserializer = _createAndCacheUntypedSerializer(class1, beanproperty);
                    if (jsonserializer == null)
                    {
                        return getUnknownTypeSerializer(class1);
                    }
                }
            }
        }
        if (jsonserializer instanceof ContextualSerializer)
        {
            return ((ContextualSerializer)jsonserializer).createContextual(_config, beanproperty);
        } else
        {
            return jsonserializer;
        }
    }

    public JsonSerializer findValueSerializer(JavaType javatype, BeanProperty beanproperty)
        throws JsonMappingException
    {
        JsonSerializer jsonserializer = _knownSerializers.untypedValueSerializer(javatype);
        if (jsonserializer == null)
        {
            jsonserializer = _serializerCache.untypedValueSerializer(javatype);
            if (jsonserializer == null)
            {
                jsonserializer = _createAndCacheUntypedSerializer(javatype, beanproperty);
                if (jsonserializer == null)
                {
                    return getUnknownTypeSerializer(javatype.getRawClass());
                }
            }
        }
        if (jsonserializer instanceof ContextualSerializer)
        {
            return ((ContextualSerializer)jsonserializer).createContextual(_config, beanproperty);
        } else
        {
            return jsonserializer;
        }
    }

    public void flushCachedSerializers()
    {
        _serializerCache.flush();
    }

    public JsonSchema generateJsonSchema(Class class1, SerializationConfig serializationconfig, SerializerFactory serializerfactory)
        throws JsonMappingException
    {
        if (class1 == null)
        {
            throw new IllegalArgumentException("A class must be provided");
        }
        StdSerializerProvider stdserializerprovider = createInstance(serializationconfig, serializerfactory);
        if (stdserializerprovider.getClass() != getClass())
        {
            throw new IllegalStateException((new StringBuilder()).append("Broken serializer provider: createInstance returned instance of type ").append(stdserializerprovider.getClass()).append("; blueprint of type ").append(getClass()).toString());
        }
        JsonSerializer jsonserializer = stdserializerprovider.findValueSerializer(class1, null);
        JsonNode jsonnode;
        if (jsonserializer instanceof SchemaAware)
        {
            jsonnode = ((SchemaAware)jsonserializer).getSchema(stdserializerprovider, null);
        } else
        {
            jsonnode = JsonSchema.getDefaultSchemaNode();
        }
        if (!(jsonnode instanceof ObjectNode))
        {
            throw new IllegalArgumentException((new StringBuilder()).append("Class ").append(class1.getName()).append(" would not be serialized as a JSON object and therefore has no schema").toString());
        } else
        {
            return new JsonSchema((ObjectNode)jsonnode);
        }
    }

    public JsonSerializer getNullKeySerializer()
    {
        return _nullKeySerializer;
    }

    public JsonSerializer getNullValueSerializer()
    {
        return _nullValueSerializer;
    }

    public JsonSerializer getUnknownTypeSerializer(Class class1)
    {
        return _unknownTypeSerializer;
    }

    public boolean hasSerializerFor(SerializationConfig serializationconfig, Class class1, SerializerFactory serializerfactory)
    {
        return createInstance(serializationconfig, serializerfactory)._findExplicitUntypedSerializer(class1, null) != null;
    }

    public final void serializeValue(SerializationConfig serializationconfig, JsonGenerator jsongenerator, Object obj, SerializerFactory serializerfactory)
        throws IOException, JsonGenerationException
    {
        if (serializerfactory == null)
        {
            throw new IllegalArgumentException("Can not pass null serializerFactory");
        }
        StdSerializerProvider stdserializerprovider = createInstance(serializationconfig, serializerfactory);
        if (stdserializerprovider.getClass() != getClass())
        {
            throw new IllegalStateException((new StringBuilder()).append("Broken serializer provider: createInstance returned instance of type ").append(stdserializerprovider.getClass()).append("; blueprint of type ").append(getClass()).toString());
        } else
        {
            stdserializerprovider._serializeValue(jsongenerator, obj);
            return;
        }
    }

    public final void serializeValue(SerializationConfig serializationconfig, JsonGenerator jsongenerator, Object obj, JavaType javatype, SerializerFactory serializerfactory)
        throws IOException, JsonGenerationException
    {
        if (serializerfactory == null)
        {
            throw new IllegalArgumentException("Can not pass null serializerFactory");
        }
        StdSerializerProvider stdserializerprovider = createInstance(serializationconfig, serializerfactory);
        if (stdserializerprovider.getClass() != getClass())
        {
            throw new IllegalStateException((new StringBuilder()).append("Broken serializer provider: createInstance returned instance of type ").append(stdserializerprovider.getClass()).append("; blueprint of type ").append(getClass()).toString());
        } else
        {
            stdserializerprovider._serializeValue(jsongenerator, obj, javatype);
            return;
        }
    }

    public void setDefaultKeySerializer(JsonSerializer jsonserializer)
    {
        if (jsonserializer == null)
        {
            throw new IllegalArgumentException("Can not pass null JsonSerializer");
        } else
        {
            _keySerializer = jsonserializer;
            return;
        }
    }

    public void setNullKeySerializer(JsonSerializer jsonserializer)
    {
        if (jsonserializer == null)
        {
            throw new IllegalArgumentException("Can not pass null JsonSerializer");
        } else
        {
            _nullKeySerializer = jsonserializer;
            return;
        }
    }

    public void setNullValueSerializer(JsonSerializer jsonserializer)
    {
        if (jsonserializer == null)
        {
            throw new IllegalArgumentException("Can not pass null JsonSerializer");
        } else
        {
            _nullValueSerializer = jsonserializer;
            return;
        }
    }

}

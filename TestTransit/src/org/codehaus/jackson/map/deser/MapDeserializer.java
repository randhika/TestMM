// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.map.deser;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.JsonToken;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.DeserializerProvider;
import org.codehaus.jackson.map.JsonDeserializer;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.KeyDeserializer;
import org.codehaus.jackson.map.ResolvableDeserializer;
import org.codehaus.jackson.map.TypeDeserializer;
import org.codehaus.jackson.map.util.ArrayBuilders;
import org.codehaus.jackson.type.JavaType;

// Referenced classes of package org.codehaus.jackson.map.deser:
//            ContainerDeserializer, SettableBeanProperty, PropertyValueBuffer, CreatorContainer

public class MapDeserializer extends ContainerDeserializer
    implements ResolvableDeserializer
{

    protected final Constructor _defaultCtor;
    protected HashSet _ignorableProperties;
    protected final KeyDeserializer _keyDeserializer;
    protected final JavaType _mapType;
    protected Creator.PropertyBased _propertyBasedCreator;
    protected final JsonDeserializer _valueDeserializer;
    protected final TypeDeserializer _valueTypeDeserializer;

    public MapDeserializer(JavaType javatype, Constructor constructor, KeyDeserializer keydeserializer, JsonDeserializer jsondeserializer, TypeDeserializer typedeserializer)
    {
        super(java/util/Map);
        _mapType = javatype;
        _defaultCtor = constructor;
        _keyDeserializer = keydeserializer;
        _valueDeserializer = jsondeserializer;
        _valueTypeDeserializer = typedeserializer;
    }

    public Map _deserializeUsingCreator(JsonParser jsonparser, DeserializationContext deserializationcontext)
        throws IOException, JsonProcessingException
    {
        Creator.PropertyBased propertybased = _propertyBasedCreator;
        PropertyValueBuffer propertyvaluebuffer = propertybased.startBuilding(jsonparser, deserializationcontext);
        JsonToken jsontoken = jsonparser.getCurrentToken();
        if (jsontoken == JsonToken.START_OBJECT)
        {
            jsontoken = jsonparser.nextToken();
        }
        JsonDeserializer jsondeserializer = _valueDeserializer;
        TypeDeserializer typedeserializer = _valueTypeDeserializer;
        while (jsontoken == JsonToken.FIELD_NAME) 
        {
            String s = jsonparser.getCurrentName();
            JsonToken jsontoken1 = jsonparser.nextToken();
            if (_ignorableProperties != null && _ignorableProperties.contains(s))
            {
                jsonparser.skipChildren();
            } else
            {
                SettableBeanProperty settablebeanproperty = propertybased.findCreatorProperty(s);
                if (settablebeanproperty != null)
                {
                    Object obj2 = settablebeanproperty.deserialize(jsonparser, deserializationcontext);
                    if (propertyvaluebuffer.assignParameter(settablebeanproperty.getCreatorIndex(), obj2))
                    {
                        jsonparser.nextToken();
                        Map map1;
                        try
                        {
                            map1 = (Map)propertybased.build(propertyvaluebuffer);
                        }
                        catch (Exception exception1)
                        {
                            wrapAndThrow(exception1, _mapType.getRawClass());
                            return null;
                        }
                        _readAndBind(jsonparser, deserializationcontext, map1);
                        return map1;
                    }
                } else
                {
                    String s1 = jsonparser.getCurrentName();
                    Object obj;
                    Object obj1;
                    if (_keyDeserializer == null)
                    {
                        obj = s1;
                    } else
                    {
                        obj = _keyDeserializer.deserializeKey(s1, deserializationcontext);
                    }
                    if (jsontoken1 == JsonToken.VALUE_NULL)
                    {
                        obj1 = null;
                    } else
                    if (typedeserializer == null)
                    {
                        obj1 = jsondeserializer.deserialize(jsonparser, deserializationcontext);
                    } else
                    {
                        obj1 = jsondeserializer.deserializeWithType(jsonparser, deserializationcontext, typedeserializer);
                    }
                    propertyvaluebuffer.bufferMapProperty(obj, obj1);
                }
            }
            jsontoken = jsonparser.nextToken();
        }
        Map map;
        try
        {
            map = (Map)propertybased.build(propertyvaluebuffer);
        }
        catch (Exception exception)
        {
            wrapAndThrow(exception, _mapType.getRawClass());
            return null;
        }
        return map;
    }

    protected final void _readAndBind(JsonParser jsonparser, DeserializationContext deserializationcontext, Map map)
        throws IOException, JsonProcessingException
    {
        JsonToken jsontoken = jsonparser.getCurrentToken();
        if (jsontoken == JsonToken.START_OBJECT)
        {
            jsontoken = jsonparser.nextToken();
        }
        KeyDeserializer keydeserializer = _keyDeserializer;
        JsonDeserializer jsondeserializer = _valueDeserializer;
        TypeDeserializer typedeserializer = _valueTypeDeserializer;
        while (jsontoken == JsonToken.FIELD_NAME) 
        {
            String s = jsonparser.getCurrentName();
            Object obj;
            JsonToken jsontoken1;
            if (keydeserializer == null)
            {
                obj = s;
            } else
            {
                obj = keydeserializer.deserializeKey(s, deserializationcontext);
            }
            jsontoken1 = jsonparser.nextToken();
            if (_ignorableProperties != null && _ignorableProperties.contains(s))
            {
                jsonparser.skipChildren();
            } else
            {
                Object obj1;
                if (jsontoken1 == JsonToken.VALUE_NULL)
                {
                    obj1 = null;
                } else
                if (typedeserializer == null)
                {
                    obj1 = jsondeserializer.deserialize(jsonparser, deserializationcontext);
                } else
                {
                    obj1 = jsondeserializer.deserializeWithType(jsonparser, deserializationcontext, typedeserializer);
                }
                map.put(obj, obj1);
            }
            jsontoken = jsonparser.nextToken();
        }
    }

    public volatile Object deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext)
        throws IOException, JsonProcessingException
    {
        return deserialize(jsonparser, deserializationcontext);
    }

    public volatile Object deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext, Object obj)
        throws IOException, JsonProcessingException
    {
        return deserialize(jsonparser, deserializationcontext, (Map)obj);
    }

    public Map deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext)
        throws IOException, JsonProcessingException
    {
        JsonToken jsontoken = jsonparser.getCurrentToken();
        if (jsontoken != JsonToken.START_OBJECT && jsontoken != JsonToken.FIELD_NAME && jsontoken != JsonToken.END_OBJECT)
        {
            throw deserializationcontext.mappingException(getMapClass());
        }
        if (_propertyBasedCreator != null)
        {
            return _deserializeUsingCreator(jsonparser, deserializationcontext);
        }
        if (_defaultCtor == null)
        {
            throw deserializationcontext.instantiationException(getMapClass(), "No default constructor found");
        }
        Map map;
        try
        {
            map = (Map)_defaultCtor.newInstance(new Object[0]);
        }
        catch (Exception exception)
        {
            throw deserializationcontext.instantiationException(getMapClass(), exception);
        }
        _readAndBind(jsonparser, deserializationcontext, map);
        return map;
    }

    public Map deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext, Map map)
        throws IOException, JsonProcessingException
    {
        JsonToken jsontoken = jsonparser.getCurrentToken();
        if (jsontoken != JsonToken.START_OBJECT && jsontoken != JsonToken.FIELD_NAME)
        {
            throw deserializationcontext.mappingException(getMapClass());
        } else
        {
            _readAndBind(jsonparser, deserializationcontext, map);
            return map;
        }
    }

    public Object deserializeWithType(JsonParser jsonparser, DeserializationContext deserializationcontext, TypeDeserializer typedeserializer)
        throws IOException, JsonProcessingException
    {
        return typedeserializer.deserializeTypedFromObject(jsonparser, deserializationcontext);
    }

    public JsonDeserializer getContentDeserializer()
    {
        return _valueDeserializer;
    }

    public JavaType getContentType()
    {
        return _mapType.getContentType();
    }

    public final Class getMapClass()
    {
        return _mapType.getRawClass();
    }

    public JavaType getValueType()
    {
        return _mapType;
    }

    public void resolve(DeserializationConfig deserializationconfig, DeserializerProvider deserializerprovider)
        throws JsonMappingException
    {
        if (_propertyBasedCreator != null)
        {
            SettableBeanProperty settablebeanproperty;
            for (Iterator iterator = _propertyBasedCreator.properties().iterator(); iterator.hasNext(); settablebeanproperty.setValueDeserializer(findDeserializer(deserializationconfig, deserializerprovider, settablebeanproperty.getType(), settablebeanproperty)))
            {
                settablebeanproperty = (SettableBeanProperty)iterator.next();
            }

        }
    }

    public void setCreators(CreatorContainer creatorcontainer)
    {
        _propertyBasedCreator = creatorcontainer.propertyBasedCreator();
    }

    public void setIgnorableProperties(String as[])
    {
        HashSet hashset;
        if (as == null || as.length == 0)
        {
            hashset = null;
        } else
        {
            hashset = ArrayBuilders.arrayToSet(as);
        }
        _ignorableProperties = hashset;
    }

    protected void wrapAndThrow(Throwable throwable, Object obj)
        throws IOException
    {
        for (; (throwable instanceof InvocationTargetException) && throwable.getCause() != null; throwable = throwable.getCause()) { }
        if (throwable instanceof Error)
        {
            throw (Error)throwable;
        }
        if ((throwable instanceof IOException) && !(throwable instanceof JsonMappingException))
        {
            throw (IOException)throwable;
        } else
        {
            throw JsonMappingException.wrapWithPath(throwable, obj, null);
        }
    }
}

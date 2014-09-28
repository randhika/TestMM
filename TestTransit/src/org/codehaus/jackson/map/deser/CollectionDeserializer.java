// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.map.deser;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.util.Collection;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.JsonToken;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonDeserializer;
import org.codehaus.jackson.map.TypeDeserializer;
import org.codehaus.jackson.type.JavaType;

// Referenced classes of package org.codehaus.jackson.map.deser:
//            ContainerDeserializer

public class CollectionDeserializer extends ContainerDeserializer
{

    protected final JavaType _collectionType;
    final Constructor _defaultCtor;
    final JsonDeserializer _valueDeserializer;
    final TypeDeserializer _valueTypeDeserializer;

    public CollectionDeserializer(JavaType javatype, JsonDeserializer jsondeserializer, TypeDeserializer typedeserializer, Constructor constructor)
    {
        super(javatype.getRawClass());
        _collectionType = javatype;
        _valueDeserializer = jsondeserializer;
        _valueTypeDeserializer = typedeserializer;
        if (constructor == null)
        {
            throw new IllegalArgumentException((new StringBuilder()).append("No default constructor found for container class ").append(javatype.getRawClass().getName()).toString());
        } else
        {
            _defaultCtor = constructor;
            return;
        }
    }

    private final Collection handleNonArray(JsonParser jsonparser, DeserializationContext deserializationcontext, Collection collection)
        throws IOException, JsonProcessingException
    {
        if (!deserializationcontext.isEnabled(org.codehaus.jackson.map.DeserializationConfig.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY))
        {
            throw deserializationcontext.mappingException(_collectionType.getRawClass());
        }
        JsonDeserializer jsondeserializer = _valueDeserializer;
        TypeDeserializer typedeserializer = _valueTypeDeserializer;
        Object obj;
        if (jsonparser.getCurrentToken() == JsonToken.VALUE_NULL)
        {
            obj = null;
        } else
        if (typedeserializer == null)
        {
            obj = jsondeserializer.deserialize(jsonparser, deserializationcontext);
        } else
        {
            obj = jsondeserializer.deserializeWithType(jsonparser, deserializationcontext, typedeserializer);
        }
        collection.add(obj);
        return collection;
    }

    public volatile Object deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext)
        throws IOException, JsonProcessingException
    {
        return deserialize(jsonparser, deserializationcontext);
    }

    public volatile Object deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext, Object obj)
        throws IOException, JsonProcessingException
    {
        return deserialize(jsonparser, deserializationcontext, (Collection)obj);
    }

    public Collection deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext)
        throws IOException, JsonProcessingException
    {
        Collection collection;
        try
        {
            collection = (Collection)_defaultCtor.newInstance(new Object[0]);
        }
        catch (Exception exception)
        {
            throw deserializationcontext.instantiationException(_collectionType.getRawClass(), exception);
        }
        return deserialize(jsonparser, deserializationcontext, collection);
    }

    public Collection deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext, Collection collection)
        throws IOException, JsonProcessingException
    {
        if (jsonparser.isExpectedStartArrayToken()) goto _L2; else goto _L1
_L1:
        collection = handleNonArray(jsonparser, deserializationcontext, collection);
_L4:
        return collection;
_L2:
        JsonDeserializer jsondeserializer = _valueDeserializer;
        TypeDeserializer typedeserializer = _valueTypeDeserializer;
        do
        {
            JsonToken jsontoken = jsonparser.nextToken();
            if (jsontoken == JsonToken.END_ARRAY)
            {
                continue;
            }
            Object obj;
            if (jsontoken == JsonToken.VALUE_NULL)
            {
                obj = null;
            } else
            if (typedeserializer == null)
            {
                obj = jsondeserializer.deserialize(jsonparser, deserializationcontext);
            } else
            {
                obj = jsondeserializer.deserializeWithType(jsonparser, deserializationcontext, typedeserializer);
            }
            collection.add(obj);
        } while (true);
        if (true) goto _L4; else goto _L3
_L3:
    }

    public Object deserializeWithType(JsonParser jsonparser, DeserializationContext deserializationcontext, TypeDeserializer typedeserializer)
        throws IOException, JsonProcessingException
    {
        return typedeserializer.deserializeTypedFromArray(jsonparser, deserializationcontext);
    }

    public JsonDeserializer getContentDeserializer()
    {
        return _valueDeserializer;
    }

    public JavaType getContentType()
    {
        return _collectionType.getContentType();
    }
}

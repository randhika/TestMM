// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.map.deser.impl;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.util.Collection;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.JsonToken;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonDeserializer;
import org.codehaus.jackson.map.TypeDeserializer;
import org.codehaus.jackson.map.deser.ContainerDeserializer;
import org.codehaus.jackson.type.JavaType;

public final class StringCollectionDeserializer extends ContainerDeserializer
{

    protected final JavaType _collectionType;
    final Constructor _defaultCtor;
    protected final boolean _isDefaultDeserializer;
    protected final JsonDeserializer _valueDeserializer;

    public StringCollectionDeserializer(JavaType javatype, JsonDeserializer jsondeserializer, Constructor constructor)
    {
        super(javatype.getRawClass());
        _collectionType = javatype;
        _valueDeserializer = jsondeserializer;
        _defaultCtor = constructor;
        _isDefaultDeserializer = isDefaultSerializer(jsondeserializer);
    }

    private Collection deserializeUsingCustom(JsonParser jsonparser, DeserializationContext deserializationcontext, Collection collection)
        throws IOException, JsonProcessingException
    {
        JsonDeserializer jsondeserializer = _valueDeserializer;
        do
        {
            JsonToken jsontoken = jsonparser.nextToken();
            if (jsontoken != JsonToken.END_ARRAY)
            {
                String s;
                if (jsontoken == JsonToken.VALUE_NULL)
                {
                    s = null;
                } else
                {
                    s = (String)jsondeserializer.deserialize(jsonparser, deserializationcontext);
                }
                collection.add(s);
            } else
            {
                return collection;
            }
        } while (true);
    }

    private final Collection handleNonArray(JsonParser jsonparser, DeserializationContext deserializationcontext, Collection collection)
        throws IOException, JsonProcessingException
    {
        if (!deserializationcontext.isEnabled(org.codehaus.jackson.map.DeserializationConfig.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY))
        {
            throw deserializationcontext.mappingException(_collectionType.getRawClass());
        }
        JsonDeserializer jsondeserializer = _valueDeserializer;
        String s;
        if (jsonparser.getCurrentToken() == JsonToken.VALUE_NULL)
        {
            s = null;
        } else
        if (jsondeserializer == null)
        {
            s = jsonparser.getText();
        } else
        {
            s = (String)jsondeserializer.deserialize(jsonparser, deserializationcontext);
        }
        collection.add(s);
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
        if (!_isDefaultDeserializer)
        {
            return deserializeUsingCustom(jsonparser, deserializationcontext, collection);
        }
        do
        {
            JsonToken jsontoken = jsonparser.nextToken();
            if (jsontoken == JsonToken.END_ARRAY)
            {
                continue;
            }
            String s;
            if (jsontoken == JsonToken.VALUE_NULL)
            {
                s = null;
            } else
            {
                s = jsonparser.getText();
            }
            collection.add(s);
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

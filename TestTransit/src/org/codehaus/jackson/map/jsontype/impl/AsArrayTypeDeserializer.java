// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.map.jsontype.impl;

import java.io.IOException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.JsonToken;
import org.codehaus.jackson.map.BeanProperty;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonDeserializer;
import org.codehaus.jackson.map.jsontype.TypeIdResolver;
import org.codehaus.jackson.type.JavaType;

// Referenced classes of package org.codehaus.jackson.map.jsontype.impl:
//            TypeDeserializerBase

public class AsArrayTypeDeserializer extends TypeDeserializerBase
{

    public AsArrayTypeDeserializer(JavaType javatype, TypeIdResolver typeidresolver, BeanProperty beanproperty)
    {
        super(javatype, typeidresolver, beanproperty);
    }

    private final Object _deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext)
        throws IOException, JsonProcessingException
    {
        Object obj = _findDeserializer(deserializationcontext, _locateTypeId(jsonparser, deserializationcontext)).deserialize(jsonparser, deserializationcontext);
        if (jsonparser.nextToken() != JsonToken.END_ARRAY)
        {
            throw deserializationcontext.wrongTokenException(jsonparser, JsonToken.END_ARRAY, "expected closing END_ARRAY after type information and deserialized value");
        } else
        {
            return obj;
        }
    }

    protected final String _locateTypeId(JsonParser jsonparser, DeserializationContext deserializationcontext)
        throws IOException, JsonProcessingException
    {
        if (!jsonparser.isExpectedStartArrayToken())
        {
            throw deserializationcontext.wrongTokenException(jsonparser, JsonToken.START_ARRAY, (new StringBuilder()).append("need JSON Array to contain As.WRAPPER_ARRAY type information for class ").append(baseTypeName()).toString());
        }
        if (jsonparser.nextToken() != JsonToken.VALUE_STRING)
        {
            throw deserializationcontext.wrongTokenException(jsonparser, JsonToken.VALUE_STRING, (new StringBuilder()).append("need JSON String that contains type id (for subtype of ").append(baseTypeName()).append(")").toString());
        } else
        {
            String s = jsonparser.getText();
            jsonparser.nextToken();
            return s;
        }
    }

    public Object deserializeTypedFromAny(JsonParser jsonparser, DeserializationContext deserializationcontext)
        throws IOException, JsonProcessingException
    {
        return _deserialize(jsonparser, deserializationcontext);
    }

    public Object deserializeTypedFromArray(JsonParser jsonparser, DeserializationContext deserializationcontext)
        throws IOException, JsonProcessingException
    {
        return _deserialize(jsonparser, deserializationcontext);
    }

    public Object deserializeTypedFromObject(JsonParser jsonparser, DeserializationContext deserializationcontext)
        throws IOException, JsonProcessingException
    {
        return _deserialize(jsonparser, deserializationcontext);
    }

    public Object deserializeTypedFromScalar(JsonParser jsonparser, DeserializationContext deserializationcontext)
        throws IOException, JsonProcessingException
    {
        return _deserialize(jsonparser, deserializationcontext);
    }

    public org.codehaus.jackson.annotate.JsonTypeInfo.As getTypeInclusion()
    {
        return org.codehaus.jackson.annotate.JsonTypeInfo.As.WRAPPER_ARRAY;
    }
}

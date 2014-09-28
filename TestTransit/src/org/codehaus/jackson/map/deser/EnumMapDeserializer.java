// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.map.deser;

import java.io.IOException;
import java.util.EnumMap;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.JsonToken;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonDeserializer;
import org.codehaus.jackson.map.TypeDeserializer;

// Referenced classes of package org.codehaus.jackson.map.deser:
//            StdDeserializer, EnumResolver

public final class EnumMapDeserializer extends StdDeserializer
{

    final EnumResolver _enumResolver;
    final JsonDeserializer _valueDeserializer;

    public EnumMapDeserializer(EnumResolver enumresolver, JsonDeserializer jsondeserializer)
    {
        super(java/util/EnumMap);
        _enumResolver = enumresolver;
        _valueDeserializer = jsondeserializer;
    }

    private EnumMap constructMap()
    {
        return new EnumMap(_enumResolver.getEnumClass());
    }

    public volatile Object deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext)
        throws IOException, JsonProcessingException
    {
        return deserialize(jsonparser, deserializationcontext);
    }

    public EnumMap deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext)
        throws IOException, JsonProcessingException
    {
        if (jsonparser.getCurrentToken() != JsonToken.START_OBJECT)
        {
            throw deserializationcontext.mappingException(java/util/EnumMap);
        }
        EnumMap enummap = constructMap();
        while (jsonparser.nextToken() != JsonToken.END_OBJECT) 
        {
            String s = jsonparser.getCurrentName();
            Enum enum = _enumResolver.findEnum(s);
            if (enum == null)
            {
                throw deserializationcontext.weirdStringException(_enumResolver.getEnumClass(), "value not one of declared Enum instance names");
            }
            Object obj;
            if (jsonparser.nextToken() == JsonToken.VALUE_NULL)
            {
                obj = null;
            } else
            {
                obj = _valueDeserializer.deserialize(jsonparser, deserializationcontext);
            }
            enummap.put(enum, obj);
        }
        return enummap;
    }

    public Object deserializeWithType(JsonParser jsonparser, DeserializationContext deserializationcontext, TypeDeserializer typedeserializer)
        throws IOException, JsonProcessingException
    {
        return typedeserializer.deserializeTypedFromObject(jsonparser, deserializationcontext);
    }
}

// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.map.deser;

import java.io.IOException;
import java.util.EnumSet;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.JsonToken;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.TypeDeserializer;

// Referenced classes of package org.codehaus.jackson.map.deser:
//            StdDeserializer, EnumDeserializer, EnumResolver

public final class EnumSetDeserializer extends StdDeserializer
{

    final Class _enumClass;
    final EnumDeserializer _enumDeserializer;

    public EnumSetDeserializer(EnumResolver enumresolver)
    {
        super(java/util/EnumSet);
        _enumDeserializer = new EnumDeserializer(enumresolver);
        _enumClass = enumresolver.getEnumClass();
    }

    private EnumSet constructSet()
    {
        return EnumSet.noneOf(_enumClass);
    }

    public volatile Object deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext)
        throws IOException, JsonProcessingException
    {
        return deserialize(jsonparser, deserializationcontext);
    }

    public EnumSet deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext)
        throws IOException, JsonProcessingException
    {
        if (!jsonparser.isExpectedStartArrayToken())
        {
            throw deserializationcontext.mappingException(java/util/EnumSet);
        }
        EnumSet enumset = constructSet();
        do
        {
            JsonToken jsontoken = jsonparser.nextToken();
            if (jsontoken != JsonToken.END_ARRAY)
            {
                if (jsontoken == JsonToken.VALUE_NULL)
                {
                    throw deserializationcontext.mappingException(_enumClass);
                }
                enumset.add(_enumDeserializer.deserialize(jsonparser, deserializationcontext));
            } else
            {
                return enumset;
            }
        } while (true);
    }

    public Object deserializeWithType(JsonParser jsonparser, DeserializationContext deserializationcontext, TypeDeserializer typedeserializer)
        throws IOException, JsonProcessingException
    {
        return typedeserializer.deserializeTypedFromArray(jsonparser, deserializationcontext);
    }
}

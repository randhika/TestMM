// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.map.deser;

import java.io.IOException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.JsonToken;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.util.ArrayBuilders;

// Referenced classes of package org.codehaus.jackson.map.deser:
//            ArrayDeserializers

static final class  extends 
{

    private final byte[] handleNonArray(JsonParser jsonparser, DeserializationContext deserializationcontext)
        throws IOException, JsonProcessingException
    {
        if (!deserializationcontext.isEnabled(org.codehaus.jackson.map..ACCEPT_SINGLE_VALUE_AS_ARRAY))
        {
            throw deserializationcontext.mappingException(_valueClass);
        }
        JsonToken jsontoken = jsonparser.getCurrentToken();
        byte byte0;
        if (jsontoken == JsonToken.VALUE_NUMBER_INT || jsontoken == JsonToken.VALUE_NUMBER_FLOAT)
        {
            byte0 = jsonparser.getByteValue();
        } else
        {
            if (jsontoken != JsonToken.VALUE_NULL)
            {
                throw deserializationcontext.mappingException(_valueClass.getComponentType());
            }
            byte0 = 0;
        }
        return (new byte[] {
            byte0
        });
    }

    public volatile Object deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext)
        throws IOException, JsonProcessingException
    {
        return deserialize(jsonparser, deserializationcontext);
    }

    public byte[] deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext)
        throws IOException, JsonProcessingException
    {
        JsonToken jsontoken = jsonparser.getCurrentToken();
        if (jsontoken == JsonToken.VALUE_STRING)
        {
            return jsonparser.getBinaryValue(deserializationcontext.getBase64Variant());
        }
        if (jsontoken == JsonToken.VALUE_EMBEDDED_OBJECT)
        {
            Object obj = jsonparser.getEmbeddedObject();
            if (obj == null)
            {
                return null;
            }
            if (obj instanceof byte[])
            {
                return (byte[])(byte[])obj;
            }
        }
        if (!jsonparser.isExpectedStartArrayToken())
        {
            return handleNonArray(jsonparser, deserializationcontext);
        }
        org.codehaus.jackson.map.util.  = deserializationcontext.getArrayBuilders().getByteBuilder();
        byte abyte0[] = (byte[]).etAndStart();
        int i = 0;
        do
        {
            JsonToken jsontoken1 = jsonparser.nextToken();
            if (jsontoken1 != JsonToken.END_ARRAY)
            {
                byte byte0;
                int j;
                if (jsontoken1 == JsonToken.VALUE_NUMBER_INT || jsontoken1 == JsonToken.VALUE_NUMBER_FLOAT)
                {
                    byte0 = jsonparser.getByteValue();
                } else
                {
                    if (jsontoken1 != JsonToken.VALUE_NULL)
                    {
                        throw deserializationcontext.mappingException(_valueClass.getComponentType());
                    }
                    byte0 = 0;
                }
                if (i >= abyte0.length)
                {
                    abyte0 = (byte[]).endCompletedChunk(abyte0, i);
                    i = 0;
                }
                j = i + 1;
                abyte0[i] = byte0;
                i = j;
            } else
            {
                return (byte[]).pleteAndClearBuffer(abyte0, i);
            }
        } while (true);
    }

    public ()
    {
        super([B);
    }
}

// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.map.deser;

import java.io.IOException;
import java.math.BigDecimal;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.JsonToken;
import org.codehaus.jackson.map.DeserializationContext;

// Referenced classes of package org.codehaus.jackson.map.deser:
//            StdScalarDeserializer, StdDeserializer

public static class  extends StdScalarDeserializer
{

    public volatile Object deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext)
        throws IOException, JsonProcessingException
    {
        return deserialize(jsonparser, deserializationcontext);
    }

    public BigDecimal deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext)
        throws IOException, JsonProcessingException
    {
        JsonToken jsontoken = jsonparser.getCurrentToken();
        if (jsontoken == JsonToken.VALUE_NUMBER_INT || jsontoken == JsonToken.VALUE_NUMBER_FLOAT)
        {
            return jsonparser.getDecimalValue();
        }
        if (jsontoken == JsonToken.VALUE_STRING)
        {
            String s = jsonparser.getText().trim();
            if (s.length() == 0)
            {
                return null;
            }
            BigDecimal bigdecimal;
            try
            {
                bigdecimal = new BigDecimal(s);
            }
            catch (IllegalArgumentException illegalargumentexception)
            {
                throw deserializationcontext.weirdStringException(_valueClass, "not a valid representation");
            }
            return bigdecimal;
        } else
        {
            throw deserializationcontext.mappingException(_valueClass);
        }
    }

    public ()
    {
        super(java/math/BigDecimal);
    }
}

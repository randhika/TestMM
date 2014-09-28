// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.map.deser;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.JsonToken;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.TypeDeserializer;

// Referenced classes of package org.codehaus.jackson.map.deser:
//            StdScalarDeserializer, StdDeserializer

public static final class  extends StdScalarDeserializer
{

    public Number deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext)
        throws IOException, JsonProcessingException
    {
        String s;
label0:
        {
            JsonToken jsontoken = jsonparser.getCurrentToken();
            if (jsontoken == JsonToken.VALUE_NUMBER_INT)
            {
                if (deserializationcontext.isEnabled(org.codehaus.jackson.map.IG_INTEGER_FOR_INTS))
                {
                    return jsonparser.getBigIntegerValue();
                } else
                {
                    return jsonparser.getNumberValue();
                }
            }
            if (jsontoken == JsonToken.VALUE_NUMBER_FLOAT)
            {
                if (deserializationcontext.isEnabled(org.codehaus.jackson.map.IG_DECIMAL_FOR_FLOATS))
                {
                    return jsonparser.getDecimalValue();
                } else
                {
                    return Double.valueOf(jsonparser.getDoubleValue());
                }
            }
            if (jsontoken != JsonToken.VALUE_STRING)
            {
                break MISSING_BLOCK_LABEL_197;
            }
            s = jsonparser.getText().trim();
            BigDecimal bigdecimal;
            try
            {
                if (s.indexOf('.') < 0)
                {
                    break MISSING_BLOCK_LABEL_135;
                }
                if (!deserializationcontext.isEnabled(org.codehaus.jackson.map.IG_DECIMAL_FOR_FLOATS))
                {
                    break label0;
                }
                bigdecimal = new BigDecimal(s);
            }
            catch (IllegalArgumentException illegalargumentexception)
            {
                throw deserializationcontext.weirdStringException(_valueClass, "not a valid number");
            }
            return bigdecimal;
        }
        return new Double(s);
        long l;
        if (deserializationcontext.isEnabled(org.codehaus.jackson.map.IG_INTEGER_FOR_INTS))
        {
            return new BigInteger(s);
        }
        l = Long.parseLong(s);
        if (l > 0x7fffffffL || l < 0xffffffff80000000L)
        {
            break MISSING_BLOCK_LABEL_187;
        }
        return Integer.valueOf((int)l);
        Long long1 = Long.valueOf(l);
        return long1;
        throw deserializationcontext.mappingException(_valueClass);
    }

    public volatile Object deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext)
        throws IOException, JsonProcessingException
    {
        return deserialize(jsonparser, deserializationcontext);
    }

    public Object deserializeWithType(JsonParser jsonparser, DeserializationContext deserializationcontext, TypeDeserializer typedeserializer)
        throws IOException, JsonProcessingException
    {
        switch (dehaus.jackson.JsonToken[jsonparser.getCurrentToken().ordinal()])
        {
        default:
            return typedeserializer.deserializeTypedFromScalar(jsonparser, deserializationcontext);

        case 1: // '\001'
        case 2: // '\002'
        case 3: // '\003'
            return deserialize(jsonparser, deserializationcontext);
        }
    }

    public ()
    {
        super(java/lang/Number);
    }
}

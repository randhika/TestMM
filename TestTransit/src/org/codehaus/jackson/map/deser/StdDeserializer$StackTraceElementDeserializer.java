// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.map.deser;

import java.io.IOException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.JsonToken;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonMappingException;

// Referenced classes of package org.codehaus.jackson.map.deser:
//            StdScalarDeserializer, StdDeserializer

public static class  extends StdScalarDeserializer
{

    public volatile Object deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext)
        throws IOException, JsonProcessingException
    {
        return deserialize(jsonparser, deserializationcontext);
    }

    public StackTraceElement deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext)
        throws IOException, JsonProcessingException
    {
        if (jsonparser.getCurrentToken() == JsonToken.START_OBJECT)
        {
            String s = "";
            String s1 = "";
            String s2 = "";
            int i = -1;
            do
            {
                JsonToken jsontoken = jsonparser.nextValue();
                if (jsontoken == JsonToken.END_OBJECT)
                {
                    break;
                }
                String s3 = jsonparser.getCurrentName();
                if ("className".equals(s3))
                {
                    s = jsonparser.getText();
                } else
                if ("fileName".equals(s3))
                {
                    s2 = jsonparser.getText();
                } else
                if ("lineNumber".equals(s3))
                {
                    if (jsontoken.isNumeric())
                    {
                        i = jsonparser.getIntValue();
                    } else
                    {
                        throw JsonMappingException.from(jsonparser, (new StringBuilder()).append("Non-numeric token (").append(jsontoken).append(") for property 'lineNumber'").toString());
                    }
                } else
                if ("methodName".equals(s3))
                {
                    s1 = jsonparser.getText();
                } else
                if (!"nativeMethod".equals(s3))
                {
                    handleUnknownProperty(jsonparser, deserializationcontext, _valueClass, s3);
                }
            } while (true);
            return new StackTraceElement(s, s1, s2, i);
        } else
        {
            throw deserializationcontext.mappingException(_valueClass);
        }
    }

    public ()
    {
        super(java/lang/StackTraceElement);
    }
}

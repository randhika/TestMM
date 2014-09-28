// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.map.deser;

import java.io.IOException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.JsonToken;
import org.codehaus.jackson.map.DeserializationContext;

// Referenced classes of package org.codehaus.jackson.map.deser:
//            StdScalarDeserializer, StdDeserializer

public static final class  extends StdScalarDeserializer
{

    public Class deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext)
        throws IOException, JsonProcessingException
    {
        if (jsonparser.getCurrentToken() == JsonToken.VALUE_STRING)
        {
            String s = jsonparser.getText();
            if (s.indexOf('.') < 0)
            {
                if ("int".equals(s))
                {
                    return Integer.TYPE;
                }
                if ("long".equals(s))
                {
                    return Long.TYPE;
                }
                if ("float".equals(s))
                {
                    return Float.TYPE;
                }
                if ("double".equals(s))
                {
                    return Double.TYPE;
                }
                if ("boolean".equals(s))
                {
                    return Boolean.TYPE;
                }
                if ("byte".equals(s))
                {
                    return Byte.TYPE;
                }
                if ("char".equals(s))
                {
                    return Character.TYPE;
                }
                if ("short".equals(s))
                {
                    return Short.TYPE;
                }
                if ("void".equals(s))
                {
                    return Void.TYPE;
                }
            }
            Class class1;
            try
            {
                class1 = Class.forName(jsonparser.getText());
            }
            catch (ClassNotFoundException classnotfoundexception)
            {
                throw deserializationcontext.instantiationException(_valueClass, classnotfoundexception);
            }
            return class1;
        } else
        {
            throw deserializationcontext.mappingException(_valueClass);
        }
    }

    public volatile Object deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext)
        throws IOException, JsonProcessingException
    {
        return deserialize(jsonparser, deserializationcontext);
    }

    public ()
    {
        super(java/lang/Class);
    }
}

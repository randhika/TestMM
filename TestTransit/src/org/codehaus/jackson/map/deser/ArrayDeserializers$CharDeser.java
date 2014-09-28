// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.map.deser;

import java.io.IOException;
import org.codehaus.jackson.Base64Variant;
import org.codehaus.jackson.Base64Variants;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.JsonToken;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonMappingException;

// Referenced classes of package org.codehaus.jackson.map.deser:
//            ArrayDeserializers

static final class  extends 
{

    public volatile Object deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext)
        throws IOException, JsonProcessingException
    {
        return deserialize(jsonparser, deserializationcontext);
    }

    public char[] deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext)
        throws IOException, JsonProcessingException
    {
        JsonToken jsontoken = jsonparser.getCurrentToken();
        if (jsontoken == JsonToken.VALUE_STRING)
        {
            char ac[] = jsonparser.getTextCharacters();
            int i = jsonparser.getTextOffset();
            int j = jsonparser.getTextLength();
            char ac1[] = new char[j];
            System.arraycopy(ac, i, ac1, 0, j);
            return ac1;
        }
        if (jsonparser.isExpectedStartArrayToken())
        {
            StringBuilder stringbuilder = new StringBuilder(64);
            do
            {
                JsonToken jsontoken1 = jsonparser.nextToken();
                if (jsontoken1 != JsonToken.END_ARRAY)
                {
                    if (jsontoken1 != JsonToken.VALUE_STRING)
                    {
                        throw deserializationcontext.mappingException(Character.TYPE);
                    }
                    String s = jsonparser.getText();
                    if (s.length() != 1)
                    {
                        throw JsonMappingException.from(jsonparser, (new StringBuilder()).append("Can not convert a JSON String of length ").append(s.length()).append(" into a char element of char array").toString());
                    }
                    stringbuilder.append(s.charAt(0));
                } else
                {
                    return stringbuilder.toString().toCharArray();
                }
            } while (true);
        }
        if (jsontoken == JsonToken.VALUE_EMBEDDED_OBJECT)
        {
            Object obj = jsonparser.getEmbeddedObject();
            if (obj == null)
            {
                return null;
            }
            if (obj instanceof char[])
            {
                return (char[])(char[])obj;
            }
            if (obj instanceof String)
            {
                return ((String)obj).toCharArray();
            }
            if (obj instanceof byte[])
            {
                return Base64Variants.getDefaultVariant().encode((byte[])(byte[])obj, false).toCharArray();
            }
        }
        throw deserializationcontext.mappingException(_valueClass);
    }

    public ()
    {
        super([C);
    }
}

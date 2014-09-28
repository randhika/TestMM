// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.map.deser;

import java.io.IOException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.JsonToken;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.util.ObjectBuffer;

// Referenced classes of package org.codehaus.jackson.map.deser:
//            ArrayDeserializers

static final class init> extends init>
{

    private final String[] handleNonArray(JsonParser jsonparser, DeserializationContext deserializationcontext)
        throws IOException, JsonProcessingException
    {
        if (!deserializationcontext.isEnabled(org.codehaus.jackson.map.CCEPT_SINGLE_VALUE_AS_ARRAY))
        {
            throw deserializationcontext.mappingException(_valueClass);
        }
        String as[] = new String[1];
        String s;
        if (jsonparser.getCurrentToken() == JsonToken.VALUE_NULL)
        {
            s = null;
        } else
        {
            s = jsonparser.getText();
        }
        as[0] = s;
        return as;
    }

    public volatile Object deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext)
        throws IOException, JsonProcessingException
    {
        return deserialize(jsonparser, deserializationcontext);
    }

    public String[] deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext)
        throws IOException, JsonProcessingException
    {
        if (!jsonparser.isExpectedStartArrayToken())
        {
            return handleNonArray(jsonparser, deserializationcontext);
        }
        ObjectBuffer objectbuffer = deserializationcontext.leaseObjectBuffer();
        Object aobj[] = objectbuffer.resetAndStart();
        int i = 0;
        do
        {
            JsonToken jsontoken = jsonparser.nextToken();
            if (jsontoken != JsonToken.END_ARRAY)
            {
                Object obj;
                int j;
                if (jsontoken == JsonToken.VALUE_NULL)
                {
                    obj = null;
                } else
                {
                    obj = jsonparser.getText();
                }
                if (i >= aobj.length)
                {
                    aobj = objectbuffer.appendCompletedChunk(aobj);
                    i = 0;
                }
                j = i + 1;
                aobj[i] = obj;
                i = j;
            } else
            {
                String as[] = (String[])objectbuffer.completeAndClearBuffer(aobj, i, java/lang/String);
                deserializationcontext.returnObjectBuffer(objectbuffer);
                return as;
            }
        } while (true);
    }

    public ()
    {
        super([Ljava/lang/String;);
    }
}

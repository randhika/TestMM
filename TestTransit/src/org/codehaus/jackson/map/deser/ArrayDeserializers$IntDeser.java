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

static final class r extends r
{

    private final int[] handleNonArray(JsonParser jsonparser, DeserializationContext deserializationcontext)
        throws IOException, JsonProcessingException
    {
        if (!deserializationcontext.isEnabled(org.codehaus.jackson.map.e.ACCEPT_SINGLE_VALUE_AS_ARRAY))
        {
            throw deserializationcontext.mappingException(_valueClass);
        } else
        {
            int ai[] = new int[1];
            ai[0] = _parseIntPrimitive(jsonparser, deserializationcontext);
            return ai;
        }
    }

    public volatile Object deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext)
        throws IOException, JsonProcessingException
    {
        return deserialize(jsonparser, deserializationcontext);
    }

    public int[] deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext)
        throws IOException, JsonProcessingException
    {
        if (!jsonparser.isExpectedStartArrayToken())
        {
            return handleNonArray(jsonparser, deserializationcontext);
        }
        org.codehaus.jackson.map.util.  = deserializationcontext.getArrayBuilders().getIntBuilder();
        int ai[] = (int[]).etAndStart();
        int i;
        int k;
        for (i = 0; jsonparser.nextToken() != JsonToken.END_ARRAY; i = k)
        {
            int j = _parseIntPrimitive(jsonparser, deserializationcontext);
            if (i >= ai.length)
            {
                ai = (int[]).endCompletedChunk(ai, i);
                i = 0;
            }
            k = i + 1;
            ai[i] = j;
        }

        return (int[]).pleteAndClearBuffer(ai, i);
    }

    public r()
    {
        super([I);
    }
}

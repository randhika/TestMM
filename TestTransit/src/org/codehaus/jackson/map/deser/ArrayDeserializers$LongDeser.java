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

    private final long[] handleNonArray(JsonParser jsonparser, DeserializationContext deserializationcontext)
        throws IOException, JsonProcessingException
    {
        if (!deserializationcontext.isEnabled(org.codehaus.jackson.map..ACCEPT_SINGLE_VALUE_AS_ARRAY))
        {
            throw deserializationcontext.mappingException(_valueClass);
        } else
        {
            long al[] = new long[1];
            al[0] = _parseLongPrimitive(jsonparser, deserializationcontext);
            return al;
        }
    }

    public volatile Object deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext)
        throws IOException, JsonProcessingException
    {
        return deserialize(jsonparser, deserializationcontext);
    }

    public long[] deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext)
        throws IOException, JsonProcessingException
    {
        if (!jsonparser.isExpectedStartArrayToken())
        {
            return handleNonArray(jsonparser, deserializationcontext);
        }
        org.codehaus.jackson.map.util.  = deserializationcontext.getArrayBuilders().getLongBuilder();
        long al[] = (long[]).etAndStart();
        int i;
        int j;
        for (i = 0; jsonparser.nextToken() != JsonToken.END_ARRAY; i = j)
        {
            long l = _parseLongPrimitive(jsonparser, deserializationcontext);
            if (i >= al.length)
            {
                al = (long[]).endCompletedChunk(al, i);
                i = 0;
            }
            j = i + 1;
            al[i] = l;
        }

        return (long[]).pleteAndClearBuffer(al, i);
    }

    public ()
    {
        super([J);
    }
}

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

static final class nit> extends nit>
{

    private final boolean[] handleNonArray(JsonParser jsonparser, DeserializationContext deserializationcontext)
        throws IOException, JsonProcessingException
    {
        if (!deserializationcontext.isEnabled(org.codehaus.jackson.map.CEPT_SINGLE_VALUE_AS_ARRAY))
        {
            throw deserializationcontext.mappingException(_valueClass);
        } else
        {
            boolean aflag[] = new boolean[1];
            aflag[0] = _parseBooleanPrimitive(jsonparser, deserializationcontext);
            return aflag;
        }
    }

    public volatile Object deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext)
        throws IOException, JsonProcessingException
    {
        return deserialize(jsonparser, deserializationcontext);
    }

    public boolean[] deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext)
        throws IOException, JsonProcessingException
    {
        if (!jsonparser.isExpectedStartArrayToken())
        {
            return handleNonArray(jsonparser, deserializationcontext);
        }
        org.codehaus.jackson.map.util.  = deserializationcontext.getArrayBuilders().getBooleanBuilder();
        boolean aflag[] = (boolean[]).etAndStart();
        int i;
        int j;
        for (i = 0; jsonparser.nextToken() != JsonToken.END_ARRAY; i = j)
        {
            boolean flag = _parseBooleanPrimitive(jsonparser, deserializationcontext);
            if (i >= aflag.length)
            {
                aflag = (boolean[]).endCompletedChunk(aflag, i);
                i = 0;
            }
            j = i + 1;
            aflag[i] = flag;
        }

        return (boolean[]).pleteAndClearBuffer(aflag, i);
    }

    public ()
    {
        super([Z);
    }
}

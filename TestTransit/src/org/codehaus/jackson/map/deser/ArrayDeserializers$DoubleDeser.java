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

static final class init> extends init>
{

    private final double[] handleNonArray(JsonParser jsonparser, DeserializationContext deserializationcontext)
        throws IOException, JsonProcessingException
    {
        if (!deserializationcontext.isEnabled(org.codehaus.jackson.map.CCEPT_SINGLE_VALUE_AS_ARRAY))
        {
            throw deserializationcontext.mappingException(_valueClass);
        } else
        {
            double ad[] = new double[1];
            ad[0] = _parseDoublePrimitive(jsonparser, deserializationcontext);
            return ad;
        }
    }

    public volatile Object deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext)
        throws IOException, JsonProcessingException
    {
        return deserialize(jsonparser, deserializationcontext);
    }

    public double[] deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext)
        throws IOException, JsonProcessingException
    {
        if (!jsonparser.isExpectedStartArrayToken())
        {
            return handleNonArray(jsonparser, deserializationcontext);
        }
        org.codehaus.jackson.map.util.  = deserializationcontext.getArrayBuilders().getDoubleBuilder();
        double ad[] = (double[]).etAndStart();
        int i;
        int j;
        for (i = 0; jsonparser.nextToken() != JsonToken.END_ARRAY; i = j)
        {
            double d = _parseDoublePrimitive(jsonparser, deserializationcontext);
            if (i >= ad.length)
            {
                ad = (double[]).endCompletedChunk(ad, i);
                i = 0;
            }
            j = i + 1;
            ad[i] = d;
        }

        return (double[]).pleteAndClearBuffer(ad, i);
    }

    public ()
    {
        super([D);
    }
}

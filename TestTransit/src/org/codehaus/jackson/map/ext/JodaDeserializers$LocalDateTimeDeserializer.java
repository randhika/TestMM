// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.map.ext;

import java.io.IOException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.JsonToken;
import org.codehaus.jackson.map.DeserializationContext;
import org.joda.time.DateTime;
import org.joda.time.LocalDateTime;

// Referenced classes of package org.codehaus.jackson.map.ext:
//            JodaDeserializers

public static class  extends 
{

    public volatile Object deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext)
        throws IOException, JsonProcessingException
    {
        return deserialize(jsonparser, deserializationcontext);
    }

    public LocalDateTime deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext)
        throws IOException, JsonProcessingException
    {
        if (jsonparser.isExpectedStartArrayToken())
        {
            jsonparser.nextToken();
            int i = jsonparser.getIntValue();
            jsonparser.nextToken();
            int j = jsonparser.getIntValue();
            jsonparser.nextToken();
            int k = jsonparser.getIntValue();
            jsonparser.nextToken();
            int l = jsonparser.getIntValue();
            jsonparser.nextToken();
            int i1 = jsonparser.getIntValue();
            jsonparser.nextToken();
            int j1 = jsonparser.getIntValue();
            JsonToken jsontoken = jsonparser.nextToken();
            JsonToken jsontoken1 = JsonToken.END_ARRAY;
            int k1 = 0;
            if (jsontoken != jsontoken1)
            {
                k1 = jsonparser.getIntValue();
                jsonparser.nextToken();
            }
            if (jsonparser.getCurrentToken() != JsonToken.END_ARRAY)
            {
                throw deserializationcontext.wrongTokenException(jsonparser, JsonToken.END_ARRAY, "after LocalDateTime ints");
            } else
            {
                return new LocalDateTime(i, j, k, l, i1, j1, k1);
            }
        }
        DateTime datetime;
        switch (jackson.JsonToken[jsonparser.getCurrentToken().ordinal()])
        {
        default:
            throw deserializationcontext.wrongTokenException(jsonparser, JsonToken.START_ARRAY, "expected JSON Array or Number");

        case 1: // '\001'
            return new LocalDateTime(jsonparser.getLongValue());

        case 2: // '\002'
            datetime = parseLocal(jsonparser);
            break;
        }
        if (datetime == null)
        {
            return null;
        } else
        {
            return datetime.toLocalDateTime();
        }
    }

    public ()
    {
        super(org/joda/time/LocalDateTime);
    }
}

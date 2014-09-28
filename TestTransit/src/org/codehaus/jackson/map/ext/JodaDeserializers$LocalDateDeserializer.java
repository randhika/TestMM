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
import org.joda.time.LocalDate;

// Referenced classes of package org.codehaus.jackson.map.ext:
//            JodaDeserializers

public static class > extends >
{

    public volatile Object deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext)
        throws IOException, JsonProcessingException
    {
        return deserialize(jsonparser, deserializationcontext);
    }

    public LocalDate deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext)
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
            if (jsonparser.nextToken() != JsonToken.END_ARRAY)
            {
                throw deserializationcontext.wrongTokenException(jsonparser, JsonToken.END_ARRAY, "after LocalDate ints");
            } else
            {
                return new LocalDate(i, j, k);
            }
        }
        DateTime datetime;
        switch (aus.jackson.JsonToken[jsonparser.getCurrentToken().ordinal()])
        {
        default:
            throw deserializationcontext.wrongTokenException(jsonparser, JsonToken.START_ARRAY, "expected JSON Array, String or Number");

        case 1: // '\001'
            return new LocalDate(jsonparser.getLongValue());

        case 2: // '\002'
            datetime = parseLocal(jsonparser);
            break;
        }
        if (datetime == null)
        {
            return null;
        } else
        {
            return datetime.toLocalDate();
        }
    }

    public ()
    {
        super(org/joda/time/LocalDate);
    }
}

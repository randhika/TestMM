// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.map.ext;

import java.io.IOException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.JsonToken;
import org.codehaus.jackson.map.DeserializationContext;
import org.joda.time.DateMidnight;
import org.joda.time.DateTime;

// Referenced classes of package org.codehaus.jackson.map.ext:
//            JodaDeserializers

public static class  extends 
{

    public volatile Object deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext)
        throws IOException, JsonProcessingException
    {
        return deserialize(jsonparser, deserializationcontext);
    }

    public DateMidnight deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext)
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
                throw deserializationcontext.wrongTokenException(jsonparser, JsonToken.END_ARRAY, "after DateMidnight ints");
            } else
            {
                return new DateMidnight(i, j, k);
            }
        }
        DateTime datetime;
        switch (.jackson.JsonToken[jsonparser.getCurrentToken().ordinal()])
        {
        default:
            throw deserializationcontext.wrongTokenException(jsonparser, JsonToken.START_ARRAY, "expected JSON Array, Number or String");

        case 1: // '\001'
            return new DateMidnight(jsonparser.getLongValue());

        case 2: // '\002'
            datetime = parseLocal(jsonparser);
            break;
        }
        if (datetime == null)
        {
            return null;
        } else
        {
            return datetime.toDateMidnight();
        }
    }

    public ()
    {
        super(org/joda/time/DateMidnight);
    }
}

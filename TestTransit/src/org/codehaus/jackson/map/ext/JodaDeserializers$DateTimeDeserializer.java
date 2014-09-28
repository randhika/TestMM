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
import org.joda.time.DateTimeZone;
import org.joda.time.ReadableInstant;

// Referenced classes of package org.codehaus.jackson.map.ext:
//            JodaDeserializers

public static class t> extends t>
{

    public volatile Object deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext)
        throws IOException, JsonProcessingException
    {
        return deserialize(jsonparser, deserializationcontext);
    }

    public ReadableInstant deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext)
        throws IOException, JsonProcessingException
    {
        JsonToken jsontoken = jsonparser.getCurrentToken();
        if (jsontoken == JsonToken.VALUE_NUMBER_INT)
        {
            return new DateTime(jsonparser.getLongValue(), DateTimeZone.UTC);
        }
        if (jsontoken == JsonToken.VALUE_STRING)
        {
            String s = jsonparser.getText().trim();
            if (s.length() == 0)
            {
                return null;
            } else
            {
                return new DateTime(s, DateTimeZone.UTC);
            }
        } else
        {
            throw deserializationcontext.mappingException(getValueClass());
        }
    }

    public I(Class class1)
    {
        super(class1);
    }
}

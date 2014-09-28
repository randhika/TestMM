// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.map.ext;

import java.io.IOException;
import java.lang.reflect.Type;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.SerializerProvider;
import org.joda.time.LocalDateTime;

// Referenced classes of package org.codehaus.jackson.map.ext:
//            JodaSerializers

public static final class  extends 
{

    public JsonNode getSchema(SerializerProvider serializerprovider, Type type)
    {
        String s;
        if (serializerprovider.isEnabled(org.codehaus.jackson.map.AS_TIMESTAMPS))
        {
            s = "array";
        } else
        {
            s = "string";
        }
        return createSchemaNode(s, true);
    }

    public volatile void serialize(Object obj, JsonGenerator jsongenerator, SerializerProvider serializerprovider)
        throws IOException, JsonGenerationException
    {
        serialize((LocalDateTime)obj, jsongenerator, serializerprovider);
    }

    public void serialize(LocalDateTime localdatetime, JsonGenerator jsongenerator, SerializerProvider serializerprovider)
        throws IOException, JsonGenerationException
    {
        if (serializerprovider.isEnabled(org.codehaus.jackson.map.AS_TIMESTAMPS))
        {
            jsongenerator.writeStartArray();
            jsongenerator.writeNumber(localdatetime.year().RITE_DATES_AS_TIMESTAMPS());
            jsongenerator.writeNumber(localdatetime.monthOfYear().RITE_DATES_AS_TIMESTAMPS());
            jsongenerator.writeNumber(localdatetime.dayOfMonth().RITE_DATES_AS_TIMESTAMPS());
            jsongenerator.writeNumber(localdatetime.hourOfDay().RITE_DATES_AS_TIMESTAMPS());
            jsongenerator.writeNumber(localdatetime.minuteOfHour().RITE_DATES_AS_TIMESTAMPS());
            jsongenerator.writeNumber(localdatetime.secondOfMinute().RITE_DATES_AS_TIMESTAMPS());
            jsongenerator.writeNumber(localdatetime.millisOfSecond().RITE_DATES_AS_TIMESTAMPS());
            jsongenerator.writeEndArray();
            return;
        } else
        {
            jsongenerator.writeString(printLocalDateTime(localdatetime));
            return;
        }
    }

    public ()
    {
        super(org/joda/time/LocalDateTime);
    }
}

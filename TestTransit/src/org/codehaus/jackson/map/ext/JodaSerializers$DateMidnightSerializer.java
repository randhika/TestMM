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
import org.joda.time.DateMidnight;

// Referenced classes of package org.codehaus.jackson.map.ext:
//            JodaSerializers

public static final class I extends I
{

    public JsonNode getSchema(SerializerProvider serializerprovider, Type type)
    {
        String s;
        if (serializerprovider.isEnabled(org.codehaus.jackson.map._AS_TIMESTAMPS))
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
        serialize((DateMidnight)obj, jsongenerator, serializerprovider);
    }

    public void serialize(DateMidnight datemidnight, JsonGenerator jsongenerator, SerializerProvider serializerprovider)
        throws IOException, JsonGenerationException
    {
        if (serializerprovider.isEnabled(org.codehaus.jackson.map._AS_TIMESTAMPS))
        {
            jsongenerator.writeStartArray();
            jsongenerator.writeNumber(datemidnight.year().WRITE_DATES_AS_TIMESTAMPS());
            jsongenerator.writeNumber(datemidnight.monthOfYear().WRITE_DATES_AS_TIMESTAMPS());
            jsongenerator.writeNumber(datemidnight.dayOfMonth().WRITE_DATES_AS_TIMESTAMPS());
            jsongenerator.writeEndArray();
            return;
        } else
        {
            jsongenerator.writeString(printLocalDate(datemidnight));
            return;
        }
    }

    public I()
    {
        super(org/joda/time/DateMidnight);
    }
}

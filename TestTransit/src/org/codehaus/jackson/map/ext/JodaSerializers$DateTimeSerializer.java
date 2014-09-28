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
import org.joda.time.DateTime;

// Referenced classes of package org.codehaus.jackson.map.ext:
//            JodaSerializers

public static final class t> extends t>
{

    public JsonNode getSchema(SerializerProvider serializerprovider, Type type)
    {
        String s;
        if (serializerprovider.isEnabled(org.codehaus.jackson.map.ATES_AS_TIMESTAMPS))
        {
            s = "number";
        } else
        {
            s = "string";
        }
        return createSchemaNode(s, true);
    }

    public volatile void serialize(Object obj, JsonGenerator jsongenerator, SerializerProvider serializerprovider)
        throws IOException, JsonGenerationException
    {
        serialize((DateTime)obj, jsongenerator, serializerprovider);
    }

    public void serialize(DateTime datetime, JsonGenerator jsongenerator, SerializerProvider serializerprovider)
        throws IOException, JsonGenerationException
    {
        if (serializerprovider.isEnabled(org.codehaus.jackson.map.ATES_AS_TIMESTAMPS))
        {
            jsongenerator.writeNumber(datetime.getMillis());
            return;
        } else
        {
            jsongenerator.writeString(datetime.toString());
            return;
        }
    }

    public ()
    {
        super(org/joda/time/DateTime);
    }
}

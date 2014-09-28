// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.map.ser;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Calendar;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.SerializerProvider;

// Referenced classes of package org.codehaus.jackson.map.ser:
//            ScalarSerializerBase, StdSerializers

public static final class  extends ScalarSerializerBase
{

    public static final eValue instance = new <init>();

    public JsonNode getSchema(SerializerProvider serializerprovider, Type type)
    {
        String s;
        if (serializerprovider.isEnabled(org.codehaus.jackson.map.DATES_AS_TIMESTAMPS))
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
        serialize((Calendar)obj, jsongenerator, serializerprovider);
    }

    public void serialize(Calendar calendar, JsonGenerator jsongenerator, SerializerProvider serializerprovider)
        throws IOException, JsonGenerationException
    {
        serializerprovider.defaultSerializeDateValue(calendar.getTimeInMillis(), jsongenerator);
    }


    public ()
    {
        super(java/util/Calendar);
    }
}

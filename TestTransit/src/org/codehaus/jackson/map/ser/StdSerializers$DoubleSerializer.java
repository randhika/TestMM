// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.map.ser;

import java.io.IOException;
import java.lang.reflect.Type;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.SerializerProvider;

// Referenced classes of package org.codehaus.jackson.map.ser:
//            StdSerializers

public static final class ializer extends ializer
{

    static final serialize instance = new <init>();

    public JsonNode getSchema(SerializerProvider serializerprovider, Type type)
    {
        return createSchemaNode("number", true);
    }

    public void serialize(Double double1, JsonGenerator jsongenerator, SerializerProvider serializerprovider)
        throws IOException, JsonGenerationException
    {
        jsongenerator.writeNumber(double1.doubleValue());
    }

    public volatile void serialize(Object obj, JsonGenerator jsongenerator, SerializerProvider serializerprovider)
        throws IOException, JsonGenerationException
    {
        serialize((Double)obj, jsongenerator, serializerprovider);
    }


    public ializer()
    {
        super(java/lang/Double);
    }
}

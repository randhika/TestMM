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
import org.codehaus.jackson.map.TypeSerializer;
import org.codehaus.jackson.node.ObjectNode;

// Referenced classes of package org.codehaus.jackson.map.ser:
//            ArraySerializers, ContainerSerializerBase

public static final class it> extends it>
{

    public ContainerSerializerBase _withValueTypeSerializer(TypeSerializer typeserializer)
    {
        return new <init>(typeserializer);
    }

    public JsonNode getSchema(SerializerProvider serializerprovider, Type type)
    {
        ObjectNode objectnode = createSchemaNode("array", true);
        objectnode.put("items", createSchemaNode("integer"));
        return objectnode;
    }

    public volatile void serializeContents(Object obj, JsonGenerator jsongenerator, SerializerProvider serializerprovider)
        throws IOException, JsonGenerationException
    {
        serializeContents((short[])obj, jsongenerator, serializerprovider);
    }

    public void serializeContents(short aword0[], JsonGenerator jsongenerator, SerializerProvider serializerprovider)
        throws IOException, JsonGenerationException
    {
        int i = 0;
        for (int j = aword0.length; i < j; i++)
        {
            jsongenerator.writeNumber(aword0[i]);
        }

    }

    public ()
    {
        this(null);
    }

    public <init>(TypeSerializer typeserializer)
    {
        super([S, typeserializer, null);
    }
}

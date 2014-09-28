// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.xc;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import javax.activation.DataHandler;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.SerializerProvider;
import org.codehaus.jackson.map.ser.SerializerBase;
import org.codehaus.jackson.node.ObjectNode;

public class DataHandlerJsonSerializer extends SerializerBase
{

    public DataHandlerJsonSerializer()
    {
        super(javax/activation/DataHandler);
    }

    public JsonNode getSchema(SerializerProvider serializerprovider, Type type)
    {
        ObjectNode objectnode = createSchemaNode("array", true);
        objectnode.put("items", createSchemaNode("string"));
        return objectnode;
    }

    public volatile void serialize(Object obj, JsonGenerator jsongenerator, SerializerProvider serializerprovider)
        throws IOException, JsonGenerationException
    {
        serialize((DataHandler)obj, jsongenerator, serializerprovider);
    }

    public void serialize(DataHandler datahandler, JsonGenerator jsongenerator, SerializerProvider serializerprovider)
        throws IOException, JsonProcessingException
    {
        ByteArrayOutputStream bytearrayoutputstream = new ByteArrayOutputStream();
        byte abyte0[] = new byte[4096];
        InputStream inputstream = datahandler.getInputStream();
        for (int i = inputstream.read(abyte0); i > 0; i = inputstream.read(abyte0))
        {
            bytearrayoutputstream.write(abyte0, 0, i);
        }

        jsongenerator.writeBinary(bytearrayoutputstream.toByteArray());
    }
}

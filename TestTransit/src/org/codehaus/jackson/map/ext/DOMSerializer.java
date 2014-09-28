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
import org.codehaus.jackson.map.ser.SerializerBase;
import org.w3c.dom.Node;
import org.w3c.dom.bootstrap.DOMImplementationRegistry;
import org.w3c.dom.ls.DOMImplementationLS;
import org.w3c.dom.ls.LSSerializer;

public class DOMSerializer extends SerializerBase
{

    protected final DOMImplementationLS _domImpl;

    public DOMSerializer()
    {
        super(org/w3c/dom/Node);
        DOMImplementationRegistry domimplementationregistry;
        try
        {
            domimplementationregistry = DOMImplementationRegistry.newInstance();
        }
        catch (Exception exception)
        {
            throw new IllegalStateException((new StringBuilder()).append("Could not instantiate DOMImplementationRegistry: ").append(exception.getMessage()).toString(), exception);
        }
        _domImpl = (DOMImplementationLS)domimplementationregistry.getDOMImplementation("LS");
    }

    public JsonNode getSchema(SerializerProvider serializerprovider, Type type)
    {
        return createSchemaNode("string", true);
    }

    public volatile void serialize(Object obj, JsonGenerator jsongenerator, SerializerProvider serializerprovider)
        throws IOException, JsonGenerationException
    {
        serialize((Node)obj, jsongenerator, serializerprovider);
    }

    public void serialize(Node node, JsonGenerator jsongenerator, SerializerProvider serializerprovider)
        throws IOException, JsonGenerationException
    {
        if (_domImpl == null)
        {
            throw new IllegalStateException("Could not find DOM LS");
        } else
        {
            jsongenerator.writeString(_domImpl.createLSSerializer().writeToString(node));
            return;
        }
    }
}

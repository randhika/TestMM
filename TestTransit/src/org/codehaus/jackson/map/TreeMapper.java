// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.map;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.net.URL;
import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.node.JsonNodeFactory;
import org.codehaus.jackson.node.NullNode;

// Referenced classes of package org.codehaus.jackson.map:
//            ObjectMapper

public class TreeMapper extends JsonNodeFactory
{

    protected ObjectMapper _objectMapper;

    public TreeMapper()
    {
        this(null);
    }

    public TreeMapper(ObjectMapper objectmapper)
    {
        _objectMapper = objectmapper;
    }

    public JsonFactory getJsonFactory()
    {
        return objectMapper().getJsonFactory();
    }

    protected ObjectMapper objectMapper()
    {
        this;
        JVM INSTR monitorenter ;
        ObjectMapper objectmapper;
        if (_objectMapper == null)
        {
            _objectMapper = new ObjectMapper();
        }
        objectmapper = _objectMapper;
        this;
        JVM INSTR monitorexit ;
        return objectmapper;
        Exception exception;
        exception;
        throw exception;
    }

    public JsonNode readTree(File file)
        throws IOException, JsonParseException
    {
        Object obj = (JsonNode)objectMapper().readValue(file, org/codehaus/jackson/JsonNode);
        if (obj == null)
        {
            obj = NullNode.instance;
        }
        return ((JsonNode) (obj));
    }

    public JsonNode readTree(InputStream inputstream)
        throws IOException, JsonParseException
    {
        Object obj = (JsonNode)objectMapper().readValue(inputstream, org/codehaus/jackson/JsonNode);
        if (obj == null)
        {
            obj = NullNode.instance;
        }
        return ((JsonNode) (obj));
    }

    public JsonNode readTree(Reader reader)
        throws IOException, JsonParseException
    {
        Object obj = (JsonNode)objectMapper().readValue(reader, org/codehaus/jackson/JsonNode);
        if (obj == null)
        {
            obj = NullNode.instance;
        }
        return ((JsonNode) (obj));
    }

    public JsonNode readTree(String s)
        throws IOException, JsonParseException
    {
        Object obj = (JsonNode)objectMapper().readValue(s, org/codehaus/jackson/JsonNode);
        if (obj == null)
        {
            obj = NullNode.instance;
        }
        return ((JsonNode) (obj));
    }

    public JsonNode readTree(URL url)
        throws IOException, JsonParseException
    {
        Object obj = (JsonNode)objectMapper().readValue(url, org/codehaus/jackson/JsonNode);
        if (obj == null)
        {
            obj = NullNode.instance;
        }
        return ((JsonNode) (obj));
    }

    public JsonNode readTree(JsonParser jsonparser)
        throws IOException, JsonParseException
    {
        if (jsonparser.getCurrentToken() == null && jsonparser.nextToken() == null)
        {
            return null;
        } else
        {
            return objectMapper().readTree(jsonparser);
        }
    }

    public JsonNode readTree(byte abyte0[])
        throws IOException, JsonParseException
    {
        Object obj = (JsonNode)objectMapper().readValue(abyte0, 0, abyte0.length, org/codehaus/jackson/JsonNode);
        if (obj == null)
        {
            obj = NullNode.instance;
        }
        return ((JsonNode) (obj));
    }

    public void writeTree(JsonNode jsonnode, File file)
        throws IOException, JsonParseException
    {
        objectMapper().writeValue(file, jsonnode);
    }

    public void writeTree(JsonNode jsonnode, OutputStream outputstream)
        throws IOException, JsonParseException
    {
        objectMapper().writeValue(outputstream, jsonnode);
    }

    public void writeTree(JsonNode jsonnode, Writer writer)
        throws IOException, JsonParseException
    {
        objectMapper().writeValue(writer, jsonnode);
    }
}

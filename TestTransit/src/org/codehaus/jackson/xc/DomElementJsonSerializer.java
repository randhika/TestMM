// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.xc;

import java.io.IOException;
import java.lang.reflect.Type;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.SerializerProvider;
import org.codehaus.jackson.map.ser.SerializerBase;
import org.codehaus.jackson.node.ObjectNode;
import org.w3c.dom.Attr;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class DomElementJsonSerializer extends SerializerBase
{

    public DomElementJsonSerializer()
    {
        super(org/w3c/dom/Element);
    }

    public JsonNode getSchema(SerializerProvider serializerprovider, Type type)
        throws JsonMappingException
    {
        ObjectNode objectnode = createSchemaNode("object", true);
        objectnode.put("name", createSchemaNode("string"));
        objectnode.put("namespace", createSchemaNode("string", true));
        objectnode.put("attributes", createSchemaNode("array", true));
        objectnode.put("children", createSchemaNode("array", true));
        return objectnode;
    }

    public volatile void serialize(Object obj, JsonGenerator jsongenerator, SerializerProvider serializerprovider)
        throws IOException, JsonGenerationException
    {
        serialize((Element)obj, jsongenerator, serializerprovider);
    }

    public void serialize(Element element, JsonGenerator jsongenerator, SerializerProvider serializerprovider)
        throws IOException, JsonGenerationException
    {
        NodeList nodelist;
        int i;
        jsongenerator.writeStartObject();
        jsongenerator.writeStringField("name", element.getTagName());
        if (element.getNamespaceURI() != null)
        {
            jsongenerator.writeStringField("namespace", element.getNamespaceURI());
        }
        NamedNodeMap namednodemap = element.getAttributes();
        if (namednodemap != null && namednodemap.getLength() > 0)
        {
            jsongenerator.writeArrayFieldStart("attributes");
            for (int j = 0; j < namednodemap.getLength(); j++)
            {
                Attr attr = (Attr)namednodemap.item(j);
                jsongenerator.writeStartObject();
                jsongenerator.writeStringField("$", attr.getValue());
                jsongenerator.writeStringField("name", attr.getName());
                String s = attr.getNamespaceURI();
                if (s != null)
                {
                    jsongenerator.writeStringField("namespace", s);
                }
                jsongenerator.writeEndObject();
            }

            jsongenerator.writeEndArray();
        }
        nodelist = element.getChildNodes();
        if (nodelist == null || nodelist.getLength() <= 0)
        {
            break MISSING_BLOCK_LABEL_300;
        }
        jsongenerator.writeArrayFieldStart("children");
        i = 0;
_L2:
        Node node;
        if (i >= nodelist.getLength())
        {
            break MISSING_BLOCK_LABEL_296;
        }
        node = nodelist.item(i);
        switch (node.getNodeType())
        {
        case 2: // '\002'
        default:
            break;

        case 3: // '\003'
        case 4: // '\004'
            break; /* Loop/switch isn't completed */

        case 1: // '\001'
            break;
        }
        break MISSING_BLOCK_LABEL_282;
_L3:
        i++;
        if (true) goto _L2; else goto _L1
_L1:
        jsongenerator.writeStartObject();
        jsongenerator.writeStringField("$", node.getNodeValue());
        jsongenerator.writeEndObject();
          goto _L3
        serialize((Element)node, jsongenerator, serializerprovider);
          goto _L3
        jsongenerator.writeEndArray();
        jsongenerator.writeEndObject();
        return;
    }
}

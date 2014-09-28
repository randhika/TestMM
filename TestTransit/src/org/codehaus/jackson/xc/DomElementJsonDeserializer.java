// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.xc;

import java.io.IOException;
import java.util.Iterator;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.deser.StdDeserializer;
import org.codehaus.jackson.node.ArrayNode;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class DomElementJsonDeserializer extends StdDeserializer
{

    private final DocumentBuilder builder;

    public DomElementJsonDeserializer()
    {
        super(org/w3c/dom/Element);
        try
        {
            DocumentBuilderFactory documentbuilderfactory = DocumentBuilderFactory.newInstance();
            documentbuilderfactory.setNamespaceAware(true);
            builder = documentbuilderfactory.newDocumentBuilder();
            return;
        }
        catch (ParserConfigurationException parserconfigurationexception)
        {
            throw new RuntimeException();
        }
    }

    public DomElementJsonDeserializer(DocumentBuilder documentbuilder)
    {
        super(org/w3c/dom/Element);
        builder = documentbuilder;
    }

    public volatile Object deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext)
        throws IOException, JsonProcessingException
    {
        return deserialize(jsonparser, deserializationcontext);
    }

    public Element deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext)
        throws IOException, JsonProcessingException
    {
        return fromNode(builder.newDocument(), jsonparser.readValueAsTree());
    }

    protected Element fromNode(Document document, JsonNode jsonnode)
        throws IOException
    {
        String s;
        String s1;
        if (jsonnode.get("namespace") != null)
        {
            s = jsonnode.get("namespace").getValueAsText();
        } else
        {
            s = null;
        }
        if (jsonnode.get("name") != null)
        {
            s1 = jsonnode.get("name").getValueAsText();
        } else
        {
            s1 = null;
        }
        if (s1 == null)
        {
            throw new JsonMappingException("No name for DOM element was provided in the JSON object.");
        }
        Element element = document.createElementNS(s, s1);
        JsonNode jsonnode1 = jsonnode.get("attributes");
        if (jsonnode1 != null && (jsonnode1 instanceof ArrayNode))
        {
            Iterator iterator1 = jsonnode1.getElements();
            do
            {
                if (!iterator1.hasNext())
                {
                    break;
                }
                JsonNode jsonnode4 = (JsonNode)iterator1.next();
                String s4;
                String s5;
                String s6;
                if (jsonnode4.get("namespace") != null)
                {
                    s4 = jsonnode4.get("namespace").getValueAsText();
                } else
                {
                    s4 = null;
                }
                if (jsonnode4.get("name") != null)
                {
                    s5 = jsonnode4.get("name").getValueAsText();
                } else
                {
                    s5 = null;
                }
                if (jsonnode4.get("$") != null)
                {
                    s6 = jsonnode4.get("$").getValueAsText();
                } else
                {
                    s6 = null;
                }
                if (s5 != null)
                {
                    element.setAttributeNS(s4, s5, s6);
                }
            } while (true);
        }
        JsonNode jsonnode2 = jsonnode.get("children");
        if (jsonnode2 != null && (jsonnode2 instanceof ArrayNode))
        {
            Iterator iterator = jsonnode2.getElements();
            do
            {
                if (!iterator.hasNext())
                {
                    break;
                }
                JsonNode jsonnode3 = (JsonNode)iterator.next();
                String s2;
                String s3;
                if (jsonnode3.get("name") != null)
                {
                    s2 = jsonnode3.get("name").getValueAsText();
                } else
                {
                    s2 = null;
                }
                if (jsonnode3.get("$") != null)
                {
                    s3 = jsonnode3.get("$").getValueAsText();
                } else
                {
                    s3 = null;
                }
                if (s3 != null)
                {
                    element.appendChild(document.createTextNode(s3));
                } else
                if (s2 != null)
                {
                    element.appendChild(fromNode(document, jsonnode3));
                }
            } while (true);
        }
        return element;
    }
}

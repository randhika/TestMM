// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.map.ext;

import java.io.StringReader;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.deser.FromStringDeserializer;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;

public abstract class DOMDeserializer extends FromStringDeserializer
{
    public static class DocumentDeserializer extends DOMDeserializer
    {

        public volatile Object _deserialize(String s, DeserializationContext deserializationcontext)
        {
            return _deserialize(s, deserializationcontext);
        }

        public Document _deserialize(String s, DeserializationContext deserializationcontext)
            throws IllegalArgumentException
        {
            return parse(s);
        }

        public DocumentDeserializer()
        {
            super(org/w3c/dom/Document);
        }
    }

    public static class NodeDeserializer extends DOMDeserializer
    {

        public volatile Object _deserialize(String s, DeserializationContext deserializationcontext)
        {
            return _deserialize(s, deserializationcontext);
        }

        public Node _deserialize(String s, DeserializationContext deserializationcontext)
            throws IllegalArgumentException
        {
            return parse(s);
        }

        public NodeDeserializer()
        {
            super(org/w3c/dom/Node);
        }
    }


    static final DocumentBuilderFactory _parserFactory;

    protected DOMDeserializer(Class class1)
    {
        super(class1);
    }

    public abstract Object _deserialize(String s, DeserializationContext deserializationcontext);

    protected final Document parse(String s)
        throws IllegalArgumentException
    {
        Document document;
        try
        {
            document = _parserFactory.newDocumentBuilder().parse(new InputSource(new StringReader(s)));
        }
        catch (Exception exception)
        {
            throw new IllegalArgumentException((new StringBuilder()).append("Failed to parse JSON String as XML: ").append(exception.getMessage()).toString(), exception);
        }
        return document;
    }

    static 
    {
        _parserFactory = DocumentBuilderFactory.newInstance();
        _parserFactory.setNamespaceAware(true);
    }
}

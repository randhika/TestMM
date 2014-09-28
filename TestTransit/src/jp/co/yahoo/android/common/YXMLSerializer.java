// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.common;

import android.util.Log;
import android.util.Xml;
import java.io.IOException;
import java.io.OutputStream;
import org.w3c.dom.Attr;
import org.w3c.dom.CDATASection;
import org.w3c.dom.Comment;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xmlpull.v1.XmlSerializer;

public class YXMLSerializer
{
    static class XMLSerializerException extends Exception
    {

        private static final long serialVersionUID = 0xba95cd87dc2f2054L;

        XMLSerializerException()
        {
        }
    }


    static final String FEATURE_INDENT = "http://xmlpull.org/v1/doc/features.html#indent-output";
    private static final String TAG = "XMLSerializer";
    String encoding;
    boolean indent;
    OutputStream output;
    XmlSerializer serializer;

    public YXMLSerializer(OutputStream outputstream)
    {
        encoding = "UTF-8";
        indent = true;
        output = outputstream;
    }

    private String _getNameWithoutPrefix(Node node)
    {
        String s = node.getPrefix();
        String s1 = node.getNodeName();
        if (s == null)
        {
            return s1;
        } else
        {
            return s1.substring(1 + s.length());
        }
    }

    private void _initSerialzier()
        throws IllegalArgumentException, IllegalStateException, IOException
    {
        serializer = Xml.newSerializer();
        serializer.setOutput(output, encoding);
    }

    private void _setPrefix(Node node)
        throws IllegalArgumentException, IllegalStateException, IOException
    {
        if (node.getNamespaceURI() != null)
        {
            serializer.setPrefix(node.getPrefix(), node.getNamespaceURI());
        }
    }

    private void _writeCDATASection(CDATASection cdatasection)
        throws IllegalArgumentException, IllegalStateException, DOMException, IOException
    {
        serializer.cdsect(cdatasection.getData());
    }

    private void _writeComment(Comment comment)
        throws IllegalArgumentException, IllegalStateException, DOMException, IOException
    {
        serializer.comment(comment.getData());
    }

    private void _writeElement(Element element)
        throws IllegalArgumentException, IllegalStateException, IOException
    {
        serializer.startTag(element.getNamespaceURI(), _getNameWithoutPrefix(element));
        NamedNodeMap namednodemap = element.getAttributes();
        int i = namednodemap.getLength();
        for (int j = 0; j < i; j++)
        {
            Attr attr = (Attr)namednodemap.item(j);
            _setPrefix(attr);
            serializer.attribute(attr.getNamespaceURI(), _getNameWithoutPrefix(attr), attr.getValue());
        }

        NodeList nodelist = element.getChildNodes();
        int k = nodelist.getLength();
        for (int l = 0; l < k; l++)
        {
            _writeNode(nodelist.item(l));
        }

        serializer.endTag(element.getNamespaceURI(), _getNameWithoutPrefix(element));
    }

    private void _writeNode(Node node)
        throws IllegalArgumentException, IllegalStateException, IOException
    {
        _setPrefix(node);
        node.getNodeType();
        JVM INSTR tableswitch 1 12: default 401
    //                   1 240
    //                   2 72
    //                   3 393
    //                   4 114
    //                   5 285
    //                   6 249
    //                   7 357
    //                   8 123
    //                   9 168
    //                   10 204
    //                   11 132
    //                   12 321;
           goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8 _L9 _L10 _L11 _L12 _L13
_L3:
        XMLSerializerException xmlserializerexception;
        Log.e("XMLSerializer", (new StringBuilder()).append("Attribute node: ").append(node.toString()).toString());
        throw new XMLSerializerException();
_L5:
        try
        {
            _writeCDATASection((CDATASection)node);
            return;
        }
        // Misplaced declaration of an exception variable
        catch (XMLSerializerException xmlserializerexception)
        {
            xmlserializerexception.printStackTrace();
            return;
        }
_L9:
        _writeComment((Comment)node);
        return;
_L12:
        Log.e("XMLSerializer", (new StringBuilder()).append("Document Fragment node: ").append(node.toString()).toString());
        throw new XMLSerializerException();
_L10:
        Log.e("XMLSerializer", (new StringBuilder()).append("Document node: ").append(node.toString()).toString());
        throw new XMLSerializerException();
_L11:
        Log.e("XMLSerializer", (new StringBuilder()).append("Document Type node: ").append(node.toString()).toString());
        throw new XMLSerializerException();
_L2:
        _writeElement((Element)node);
        return;
_L7:
        Log.e("XMLSerializer", (new StringBuilder()).append("Entity node: ").append(node.toString()).toString());
        throw new XMLSerializerException();
_L6:
        Log.e("XMLSerializer", (new StringBuilder()).append("Entity Preference node: ").append(node.toString()).toString());
        throw new XMLSerializerException();
_L13:
        Log.e("XMLSerializer", (new StringBuilder()).append("Notation node: ").append(node.toString()).toString());
        throw new XMLSerializerException();
_L8:
        Log.e("XMLSerializer", (new StringBuilder()).append("Processing Instruction node: ").append(node.toString()).toString());
        throw new XMLSerializerException();
_L4:
        _writeText((Text)node);
_L1:
    }

    private void _writeText(Text text)
        throws IllegalArgumentException, IllegalStateException, DOMException, IOException
    {
        serializer.text(text.getData());
    }

    public String getEncoding()
    {
        return encoding;
    }

    public boolean getIndent()
    {
        return indent;
    }

    public void serialize(Document document)
        throws IllegalArgumentException, IllegalStateException, IOException
    {
        _initSerialzier();
        serializer.startDocument(encoding, null);
        serializer.setFeature("http://xmlpull.org/v1/doc/features.html#indent-output", indent);
        _writeNode(document.getDocumentElement());
        serializer.endDocument();
    }

    public void setEncoding(String s)
    {
        encoding = s;
    }

    public void setIndent(boolean flag)
    {
        indent = flag;
    }
}

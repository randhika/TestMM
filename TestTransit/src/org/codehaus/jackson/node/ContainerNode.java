// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.node;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonToken;

// Referenced classes of package org.codehaus.jackson.node:
//            BaseJsonNode, JsonNodeFactory, POJONode, ArrayNode, 
//            BinaryNode, BooleanNode, ObjectNode, NullNode, 
//            NumericNode, TextNode

public abstract class ContainerNode extends BaseJsonNode
{
    protected static class NoNodesIterator
        implements Iterator
    {

        static final NoNodesIterator instance = new NoNodesIterator();

        public static NoNodesIterator instance()
        {
            return instance;
        }

        public boolean hasNext()
        {
            return false;
        }

        public volatile Object next()
        {
            return next();
        }

        public JsonNode next()
        {
            throw new NoSuchElementException();
        }

        public void remove()
        {
            throw new IllegalStateException();
        }


        private NoNodesIterator()
        {
        }
    }

    protected static class NoStringsIterator
        implements Iterator
    {

        static final NoStringsIterator instance = new NoStringsIterator();

        public static NoStringsIterator instance()
        {
            return instance;
        }

        public boolean hasNext()
        {
            return false;
        }

        public volatile Object next()
        {
            return next();
        }

        public String next()
        {
            throw new NoSuchElementException();
        }

        public void remove()
        {
            throw new IllegalStateException();
        }


        private NoStringsIterator()
        {
        }
    }


    JsonNodeFactory _nodeFactory;

    protected ContainerNode(JsonNodeFactory jsonnodefactory)
    {
        _nodeFactory = jsonnodefactory;
    }

    public final POJONode POJONode(Object obj)
    {
        return _nodeFactory.POJONode(obj);
    }

    public final ArrayNode arrayNode()
    {
        return _nodeFactory.arrayNode();
    }

    public abstract JsonToken asToken();

    public final BinaryNode binaryNode(byte abyte0[])
    {
        return _nodeFactory.binaryNode(abyte0);
    }

    public final BinaryNode binaryNode(byte abyte0[], int i, int j)
    {
        return _nodeFactory.binaryNode(abyte0, i, j);
    }

    public final BooleanNode booleanNode(boolean flag)
    {
        return _nodeFactory.booleanNode(flag);
    }

    public volatile JsonNode findParent(String s)
    {
        return findParent(s);
    }

    public abstract ObjectNode findParent(String s);

    public abstract List findParents(String s, List list);

    public abstract JsonNode findValue(String s);

    public abstract List findValues(String s, List list);

    public abstract List findValuesAsText(String s, List list);

    public abstract JsonNode get(int i);

    public abstract JsonNode get(String s);

    public String getValueAsText()
    {
        return null;
    }

    public boolean isContainerNode()
    {
        return true;
    }

    public final NullNode nullNode()
    {
        return _nodeFactory.nullNode();
    }

    public final NumericNode numberNode(byte byte0)
    {
        return _nodeFactory.numberNode(byte0);
    }

    public final NumericNode numberNode(double d)
    {
        return _nodeFactory.numberNode(d);
    }

    public final NumericNode numberNode(float f)
    {
        return _nodeFactory.numberNode(f);
    }

    public final NumericNode numberNode(int i)
    {
        return _nodeFactory.numberNode(i);
    }

    public final NumericNode numberNode(long l)
    {
        return _nodeFactory.numberNode(l);
    }

    public final NumericNode numberNode(BigDecimal bigdecimal)
    {
        return _nodeFactory.numberNode(bigdecimal);
    }

    public final NumericNode numberNode(short word0)
    {
        return _nodeFactory.numberNode(word0);
    }

    public final ObjectNode objectNode()
    {
        return _nodeFactory.objectNode();
    }

    public abstract ContainerNode removeAll();

    public abstract int size();

    public final TextNode textNode(String s)
    {
        return _nodeFactory.textNode(s);
    }
}

// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.node;

import java.math.BigDecimal;
import java.math.BigInteger;

// Referenced classes of package org.codehaus.jackson.node:
//            POJONode, ArrayNode, BinaryNode, BooleanNode, 
//            NullNode, IntNode, DoubleNode, LongNode, 
//            DecimalNode, BigIntegerNode, ObjectNode, TextNode, 
//            NumericNode

public class JsonNodeFactory
{

    public static final JsonNodeFactory instance = new JsonNodeFactory();

    protected JsonNodeFactory()
    {
    }

    public POJONode POJONode(Object obj)
    {
        return new POJONode(obj);
    }

    public ArrayNode arrayNode()
    {
        return new ArrayNode(this);
    }

    public BinaryNode binaryNode(byte abyte0[])
    {
        return BinaryNode.valueOf(abyte0);
    }

    public BinaryNode binaryNode(byte abyte0[], int i, int j)
    {
        return BinaryNode.valueOf(abyte0, i, j);
    }

    public BooleanNode booleanNode(boolean flag)
    {
        if (flag)
        {
            return BooleanNode.getTrue();
        } else
        {
            return BooleanNode.getFalse();
        }
    }

    public NullNode nullNode()
    {
        return NullNode.getInstance();
    }

    public NumericNode numberNode(byte byte0)
    {
        return IntNode.valueOf(byte0);
    }

    public NumericNode numberNode(double d)
    {
        return DoubleNode.valueOf(d);
    }

    public NumericNode numberNode(float f)
    {
        return DoubleNode.valueOf(f);
    }

    public NumericNode numberNode(int i)
    {
        return IntNode.valueOf(i);
    }

    public NumericNode numberNode(long l)
    {
        return LongNode.valueOf(l);
    }

    public NumericNode numberNode(BigDecimal bigdecimal)
    {
        return DecimalNode.valueOf(bigdecimal);
    }

    public NumericNode numberNode(BigInteger biginteger)
    {
        return BigIntegerNode.valueOf(biginteger);
    }

    public NumericNode numberNode(short word0)
    {
        return IntNode.valueOf(word0);
    }

    public ObjectNode objectNode()
    {
        return new ObjectNode(this);
    }

    public TextNode textNode(String s)
    {
        return TextNode.valueOf(s);
    }

}

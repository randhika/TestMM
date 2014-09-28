// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

// Referenced classes of package org.codehaus.jackson:
//            JsonGenerationException, JsonToken, JsonParser, JsonGenerator

public abstract class JsonNode
    implements Iterable
{

    protected static final List NO_NODES = Collections.emptyList();
    protected static final List NO_STRINGS = Collections.emptyList();

    protected JsonNode()
    {
    }

    public abstract JsonToken asToken();

    public abstract boolean equals(Object obj);

    public abstract JsonNode findParent(String s);

    public final List findParents(String s)
    {
        List list = findParents(s, null);
        if (list == null)
        {
            list = Collections.emptyList();
        }
        return list;
    }

    public abstract List findParents(String s, List list);

    public abstract JsonNode findPath(String s);

    public abstract JsonNode findValue(String s);

    public final List findValues(String s)
    {
        List list = findValues(s, null);
        if (list == null)
        {
            list = Collections.emptyList();
        }
        return list;
    }

    public abstract List findValues(String s, List list);

    public final List findValuesAsText(String s)
    {
        List list = findValuesAsText(s, null);
        if (list == null)
        {
            list = Collections.emptyList();
        }
        return list;
    }

    public abstract List findValuesAsText(String s, List list);

    public JsonNode get(int i)
    {
        return null;
    }

    public JsonNode get(String s)
    {
        return null;
    }

    public BigInteger getBigIntegerValue()
    {
        return BigInteger.ZERO;
    }

    public byte[] getBinaryValue()
        throws IOException
    {
        return null;
    }

    public boolean getBooleanValue()
    {
        return false;
    }

    public BigDecimal getDecimalValue()
    {
        return BigDecimal.ZERO;
    }

    public double getDoubleValue()
    {
        return 0.0D;
    }

    public final JsonNode getElementValue(int i)
    {
        return get(i);
    }

    public Iterator getElements()
    {
        return NO_NODES.iterator();
    }

    public Iterator getFieldNames()
    {
        return NO_STRINGS.iterator();
    }

    public final JsonNode getFieldValue(String s)
    {
        return get(s);
    }

    public Iterator getFields()
    {
        return Collections.emptyList().iterator();
    }

    public int getIntValue()
    {
        return 0;
    }

    public long getLongValue()
    {
        return 0L;
    }

    public abstract JsonParser.NumberType getNumberType();

    public Number getNumberValue()
    {
        return null;
    }

    public final JsonNode getPath(int i)
    {
        return path(i);
    }

    public final JsonNode getPath(String s)
    {
        return path(s);
    }

    public String getTextValue()
    {
        return null;
    }

    public boolean getValueAsBoolean()
    {
        return getValueAsBoolean(false);
    }

    public boolean getValueAsBoolean(boolean flag)
    {
        return flag;
    }

    public double getValueAsDouble()
    {
        return getValueAsDouble(0.0D);
    }

    public double getValueAsDouble(double d)
    {
        return d;
    }

    public int getValueAsInt()
    {
        return getValueAsInt(0);
    }

    public int getValueAsInt(int i)
    {
        return i;
    }

    public long getValueAsLong()
    {
        return getValueAsLong(0L);
    }

    public long getValueAsLong(long l)
    {
        return l;
    }

    public abstract String getValueAsText();

    public boolean has(int i)
    {
        return get(i) != null;
    }

    public boolean has(String s)
    {
        return get(s) != null;
    }

    public boolean isArray()
    {
        return false;
    }

    public boolean isBigDecimal()
    {
        return false;
    }

    public boolean isBigInteger()
    {
        return false;
    }

    public boolean isBinary()
    {
        return false;
    }

    public boolean isBoolean()
    {
        return false;
    }

    public boolean isContainerNode()
    {
        return false;
    }

    public boolean isDouble()
    {
        return false;
    }

    public boolean isFloatingPointNumber()
    {
        return false;
    }

    public boolean isInt()
    {
        return false;
    }

    public boolean isIntegralNumber()
    {
        return false;
    }

    public boolean isLong()
    {
        return false;
    }

    public boolean isMissingNode()
    {
        return false;
    }

    public boolean isNull()
    {
        return false;
    }

    public boolean isNumber()
    {
        return false;
    }

    public boolean isObject()
    {
        return false;
    }

    public boolean isPojo()
    {
        return false;
    }

    public boolean isTextual()
    {
        return false;
    }

    public boolean isValueNode()
    {
        return false;
    }

    public final Iterator iterator()
    {
        return getElements();
    }

    public abstract JsonNode path(int i);

    public abstract JsonNode path(String s);

    public int size()
    {
        return 0;
    }

    public abstract String toString();

    public abstract JsonParser traverse();

    public JsonNode with(String s)
    {
        throw new UnsupportedOperationException((new StringBuilder()).append("JsonNode not of type ObjectNode (but ").append(getClass().getName()).append("), can not call with() on it").toString());
    }

    public abstract void writeTo(JsonGenerator jsongenerator)
        throws IOException, JsonGenerationException;

}

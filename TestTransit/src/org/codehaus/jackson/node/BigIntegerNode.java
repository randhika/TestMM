// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.node;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.JsonToken;
import org.codehaus.jackson.map.SerializerProvider;

// Referenced classes of package org.codehaus.jackson.node:
//            NumericNode

public final class BigIntegerNode extends NumericNode
{

    protected final BigInteger _value;

    public BigIntegerNode(BigInteger biginteger)
    {
        _value = biginteger;
    }

    public static BigIntegerNode valueOf(BigInteger biginteger)
    {
        return new BigIntegerNode(biginteger);
    }

    public JsonToken asToken()
    {
        return JsonToken.VALUE_NUMBER_INT;
    }

    public boolean equals(Object obj)
    {
        if (obj != this)
        {
            if (obj == null)
            {
                return false;
            }
            if (obj.getClass() != getClass())
            {
                return false;
            }
            if (((BigIntegerNode)obj)._value != _value)
            {
                return false;
            }
        }
        return true;
    }

    public BigInteger getBigIntegerValue()
    {
        return _value;
    }

    public BigDecimal getDecimalValue()
    {
        return new BigDecimal(_value);
    }

    public double getDoubleValue()
    {
        return _value.doubleValue();
    }

    public int getIntValue()
    {
        return _value.intValue();
    }

    public long getLongValue()
    {
        return _value.longValue();
    }

    public org.codehaus.jackson.JsonParser.NumberType getNumberType()
    {
        return org.codehaus.jackson.JsonParser.NumberType.BIG_INTEGER;
    }

    public Number getNumberValue()
    {
        return _value;
    }

    public boolean getValueAsBoolean(boolean flag)
    {
        return !BigInteger.ZERO.equals(_value);
    }

    public String getValueAsText()
    {
        return _value.toString();
    }

    public int hashCode()
    {
        return _value.hashCode();
    }

    public boolean isBigInteger()
    {
        return true;
    }

    public boolean isIntegralNumber()
    {
        return true;
    }

    public final void serialize(JsonGenerator jsongenerator, SerializerProvider serializerprovider)
        throws IOException, JsonProcessingException
    {
        jsongenerator.writeNumber(_value);
    }
}

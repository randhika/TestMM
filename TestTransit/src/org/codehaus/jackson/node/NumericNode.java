// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.node;

import java.math.BigDecimal;
import java.math.BigInteger;

// Referenced classes of package org.codehaus.jackson.node:
//            ValueNode

public abstract class NumericNode extends ValueNode
{

    protected NumericNode()
    {
    }

    public abstract BigInteger getBigIntegerValue();

    public abstract BigDecimal getDecimalValue();

    public abstract double getDoubleValue();

    public abstract int getIntValue();

    public abstract long getLongValue();

    public abstract org.codehaus.jackson.JsonParser.NumberType getNumberType();

    public abstract Number getNumberValue();

    public double getValueAsDouble()
    {
        return getDoubleValue();
    }

    public double getValueAsDouble(double d)
    {
        return getDoubleValue();
    }

    public int getValueAsInt()
    {
        return getIntValue();
    }

    public int getValueAsInt(int i)
    {
        return getIntValue();
    }

    public long getValueAsLong()
    {
        return getLongValue();
    }

    public long getValueAsLong(long l)
    {
        return getLongValue();
    }

    public abstract String getValueAsText();

    public final boolean isNumber()
    {
        return true;
    }
}

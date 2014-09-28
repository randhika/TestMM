// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.node;

import java.io.IOException;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.JsonToken;
import org.codehaus.jackson.map.SerializerProvider;

// Referenced classes of package org.codehaus.jackson.node:
//            ValueNode

public final class POJONode extends ValueNode
{

    protected final Object _value;

    public POJONode(Object obj)
    {
        _value = obj;
    }

    public JsonToken asToken()
    {
        return JsonToken.VALUE_EMBEDDED_OBJECT;
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
            POJONode pojonode = (POJONode)obj;
            if (_value == null)
            {
                if (pojonode._value != null)
                {
                    return false;
                }
            } else
            {
                return _value.equals(pojonode._value);
            }
        }
        return true;
    }

    public Object getPojo()
    {
        return _value;
    }

    public boolean getValueAsBoolean(boolean flag)
    {
        if (_value != null && (_value instanceof Boolean))
        {
            flag = ((Boolean)_value).booleanValue();
        }
        return flag;
    }

    public double getValueAsDouble(double d)
    {
        if (_value instanceof Number)
        {
            d = ((Number)_value).doubleValue();
        }
        return d;
    }

    public int getValueAsInt(int i)
    {
        if (_value instanceof Number)
        {
            i = ((Number)_value).intValue();
        }
        return i;
    }

    public long getValueAsLong(long l)
    {
        if (_value instanceof Number)
        {
            l = ((Number)_value).longValue();
        }
        return l;
    }

    public String getValueAsText()
    {
        if (_value == null)
        {
            return "null";
        } else
        {
            return _value.toString();
        }
    }

    public int hashCode()
    {
        return _value.hashCode();
    }

    public boolean isPojo()
    {
        return true;
    }

    public final void serialize(JsonGenerator jsongenerator, SerializerProvider serializerprovider)
        throws IOException, JsonProcessingException
    {
        if (_value == null)
        {
            jsongenerator.writeNull();
            return;
        } else
        {
            jsongenerator.writeObject(_value);
            return;
        }
    }

    public String toString()
    {
        return String.valueOf(_value);
    }
}

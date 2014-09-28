// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.node;

import java.io.IOException;
import org.codehaus.jackson.Base64Variant;
import org.codehaus.jackson.Base64Variants;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonLocation;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.JsonToken;
import org.codehaus.jackson.io.NumberInput;
import org.codehaus.jackson.map.SerializerProvider;
import org.codehaus.jackson.util.ByteArrayBuilder;
import org.codehaus.jackson.util.CharTypes;

// Referenced classes of package org.codehaus.jackson.node:
//            ValueNode

public final class TextNode extends ValueNode
{

    static final TextNode EMPTY_STRING_NODE = new TextNode("");
    static final int INT_SPACE = 32;
    final String _value;

    public TextNode(String s)
    {
        _value = s;
    }

    protected static void appendQuoted(StringBuilder stringbuilder, String s)
    {
        stringbuilder.append('"');
        CharTypes.appendQuoted(stringbuilder, s);
        stringbuilder.append('"');
    }

    public static TextNode valueOf(String s)
    {
        if (s == null)
        {
            return null;
        }
        if (s.length() == 0)
        {
            return EMPTY_STRING_NODE;
        } else
        {
            return new TextNode(s);
        }
    }

    protected void _reportBase64EOF()
        throws JsonParseException
    {
        throw new JsonParseException("Unexpected end-of-String when base64 content", JsonLocation.NA);
    }

    protected void _reportInvalidBase64(Base64Variant base64variant, char c, int i)
        throws JsonParseException
    {
        _reportInvalidBase64(base64variant, c, i, null);
    }

    protected void _reportInvalidBase64(Base64Variant base64variant, char c, int i, String s)
        throws JsonParseException
    {
        String s1;
        if (c <= ' ')
        {
            s1 = (new StringBuilder()).append("Illegal white space character (code 0x").append(Integer.toHexString(c)).append(") as character #").append(i + 1).append(" of 4-char base64 unit: can only used between units").toString();
        } else
        if (base64variant.usesPaddingChar(c))
        {
            s1 = (new StringBuilder()).append("Unexpected padding character ('").append(base64variant.getPaddingChar()).append("') as character #").append(i + 1).append(" of 4-char base64 unit: padding only legal as 3rd or 4th character").toString();
        } else
        if (!Character.isDefined(c) || Character.isISOControl(c))
        {
            s1 = (new StringBuilder()).append("Illegal character (code 0x").append(Integer.toHexString(c)).append(") in base64 content").toString();
        } else
        {
            s1 = (new StringBuilder()).append("Illegal character '").append(c).append("' (code 0x").append(Integer.toHexString(c)).append(") in base64 content").toString();
        }
        if (s != null)
        {
            s1 = (new StringBuilder()).append(s1).append(": ").append(s).toString();
        }
        throw new JsonParseException(s1, JsonLocation.NA);
    }

    public JsonToken asToken()
    {
        return JsonToken.VALUE_STRING;
    }

    public boolean equals(Object obj)
    {
        boolean flag;
        if (obj == this)
        {
            flag = true;
        } else
        {
            flag = false;
            if (obj != null)
            {
                Class class1 = obj.getClass();
                Class class2 = getClass();
                flag = false;
                if (class1 == class2)
                {
                    return ((TextNode)obj)._value.equals(_value);
                }
            }
        }
        return flag;
    }

    public byte[] getBinaryValue()
        throws IOException
    {
        return getBinaryValue(Base64Variants.getDefaultVariant());
    }

    public byte[] getBinaryValue(Base64Variant base64variant)
        throws IOException
    {
        ByteArrayBuilder bytearraybuilder;
        String s;
        int i;
        int j;
        bytearraybuilder = new ByteArrayBuilder(100);
        s = _value;
        i = 0;
        j = s.length();
_L3:
        if (i >= j) goto _L2; else goto _L1
_L1:
        int k;
        int i1;
        int k1;
        k = i + 1;
        char c = s.charAt(i);
        if (k < j)
        {
label0:
            {
                if (c <= ' ')
                {
                    break MISSING_BLOCK_LABEL_399;
                }
                int l = base64variant.decodeBase64Char(c);
                if (l < 0)
                {
                    _reportInvalidBase64(base64variant, c, 0);
                }
                if (k >= j)
                {
                    _reportBase64EOF();
                }
                i1 = k + 1;
                char c1 = s.charAt(k);
                int j1 = base64variant.decodeBase64Char(c1);
                if (j1 < 0)
                {
                    _reportInvalidBase64(base64variant, c1, 1);
                }
                k1 = j1 | l << 6;
                if (i1 < j)
                {
                    break MISSING_BLOCK_LABEL_170;
                }
                if (base64variant.usesPadding())
                {
                    break label0;
                }
                bytearraybuilder.append(k1 >> 4);
            }
        }
_L2:
        return bytearraybuilder.toByteArray();
        _reportBase64EOF();
        int l1;
        int i2;
label1:
        {
            l1 = i1 + 1;
            char c2 = s.charAt(i1);
            i2 = base64variant.decodeBase64Char(c2);
            if (i2 >= 0)
            {
                break label1;
            }
            if (i2 != -2)
            {
                _reportInvalidBase64(base64variant, c2, 2);
            }
            if (l1 >= j)
            {
                _reportBase64EOF();
            }
            i = l1 + 1;
            char c4 = s.charAt(l1);
            if (!base64variant.usesPaddingChar(c4))
            {
                _reportInvalidBase64(base64variant, c4, 3, (new StringBuilder()).append("expected padding character '").append(base64variant.getPaddingChar()).append("'").toString());
            }
            bytearraybuilder.append(k1 >> 4);
        }
          goto _L3
        int j2;
label2:
        {
            j2 = i2 | k1 << 6;
            if (l1 < j)
            {
                break MISSING_BLOCK_LABEL_331;
            }
            if (base64variant.usesPadding())
            {
                break label2;
            }
            bytearraybuilder.appendTwoBytes(j2 >> 2);
        }
          goto _L2
        _reportBase64EOF();
        i = l1 + 1;
        char c3 = s.charAt(l1);
        int k2 = base64variant.decodeBase64Char(c3);
        if (k2 < 0)
        {
            if (k2 != -2)
            {
                _reportInvalidBase64(base64variant, c3, 3);
            }
            bytearraybuilder.appendTwoBytes(j2 >> 2);
        } else
        {
            bytearraybuilder.appendThreeBytes(k2 | j2 << 6);
        }
          goto _L3
        i = k;
          goto _L1
    }

    public String getTextValue()
    {
        return _value;
    }

    public boolean getValueAsBoolean(boolean flag)
    {
        if (_value != null && "true".equals(_value.trim()))
        {
            flag = true;
        }
        return flag;
    }

    public double getValueAsDouble(double d)
    {
        return NumberInput.parseAsDouble(_value, d);
    }

    public int getValueAsInt(int i)
    {
        return NumberInput.parseAsInt(_value, i);
    }

    public long getValueAsLong(long l)
    {
        return NumberInput.parseAsLong(_value, l);
    }

    public String getValueAsText()
    {
        return _value;
    }

    public int hashCode()
    {
        return _value.hashCode();
    }

    public boolean isTextual()
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
            jsongenerator.writeString(_value);
            return;
        }
    }

    public String toString()
    {
        int i = _value.length();
        StringBuilder stringbuilder = new StringBuilder(i + 2 + (i >> 4));
        appendQuoted(stringbuilder, _value);
        return stringbuilder.toString();
    }

}

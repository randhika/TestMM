// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.impl;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonToken;
import org.codehaus.jackson.io.IOContext;
import org.codehaus.jackson.io.NumberInput;
import org.codehaus.jackson.util.TextBuffer;

// Referenced classes of package org.codehaus.jackson.impl:
//            JsonParserBase

public abstract class JsonNumericParserBase extends JsonParserBase
{

    static final BigDecimal BD_MAX_INT = new BigDecimal(0x7fffffffffffffffL);
    static final BigDecimal BD_MAX_LONG = new BigDecimal(0x7fffffffffffffffL);
    static final BigDecimal BD_MIN_INT = new BigDecimal(0x8000000000000000L);
    static final BigDecimal BD_MIN_LONG = new BigDecimal(0x8000000000000000L);
    protected static final char CHAR_NULL = 0;
    protected static final int INT_0 = 48;
    protected static final int INT_1 = 49;
    protected static final int INT_2 = 50;
    protected static final int INT_3 = 51;
    protected static final int INT_4 = 52;
    protected static final int INT_5 = 53;
    protected static final int INT_6 = 54;
    protected static final int INT_7 = 55;
    protected static final int INT_8 = 56;
    protected static final int INT_9 = 57;
    protected static final int INT_DECIMAL_POINT = 46;
    protected static final int INT_E = 69;
    protected static final int INT_MINUS = 45;
    protected static final int INT_PLUS = 43;
    protected static final int INT_e = 101;
    static final double MAX_INT_D = 2147483647D;
    static final long MAX_INT_L = 0x7fffffffL;
    static final double MAX_LONG_D = 9.2233720368547758E+18D;
    static final double MIN_INT_D = -2147483648D;
    static final long MIN_INT_L = 0xffffffff80000000L;
    static final double MIN_LONG_D = -9.2233720368547758E+18D;
    protected static final int NR_BIGDECIMAL = 16;
    protected static final int NR_BIGINT = 4;
    protected static final int NR_DOUBLE = 8;
    protected static final int NR_INT = 1;
    protected static final int NR_LONG = 2;
    protected static final int NR_UNKNOWN;
    protected int _expLength;
    protected int _fractLength;
    protected int _intLength;
    protected int _numTypesValid;
    protected BigDecimal _numberBigDecimal;
    protected BigInteger _numberBigInt;
    protected double _numberDouble;
    protected int _numberInt;
    protected long _numberLong;
    protected boolean _numberNegative;

    protected JsonNumericParserBase(IOContext iocontext, int i)
    {
        super(iocontext, i);
        _numTypesValid = 0;
    }

    private final void _parseSlowFloatValue(int i)
        throws IOException, JsonParseException
    {
        if (i == 16)
        {
            try
            {
                _numberBigDecimal = _textBuffer.contentsAsDecimal();
                _numTypesValid = 16;
                return;
            }
            catch (NumberFormatException numberformatexception)
            {
                _wrapError((new StringBuilder()).append("Malformed numeric value '").append(_textBuffer.contentsAsString()).append("'").toString(), numberformatexception);
            }
            break MISSING_BLOCK_LABEL_78;
        }
        _numberDouble = _textBuffer.contentsAsDouble();
        _numTypesValid = 8;
        return;
    }

    private final void _parseSlowIntValue(int i, char ac[], int j, int k)
        throws IOException, JsonParseException
    {
        String s = _textBuffer.contentsAsString();
        if (NumberInput.inLongRange(ac, j, k, _numberNegative))
        {
            _numberLong = Long.parseLong(s);
            _numTypesValid = 2;
            return;
        }
        try
        {
            _numberBigInt = new BigInteger(s);
            _numTypesValid = 4;
            return;
        }
        catch (NumberFormatException numberformatexception)
        {
            _wrapError((new StringBuilder()).append("Malformed numeric value '").append(s).append("'").toString(), numberformatexception);
        }
        return;
    }

    protected void _parseNumericValue(int i)
        throws IOException, JsonParseException
    {
        if (_currToken == JsonToken.VALUE_NUMBER_INT)
        {
            char ac[] = _textBuffer.getTextBuffer();
            int j = _textBuffer.getTextOffset();
            int k = _intLength;
            if (_numberNegative)
            {
                j++;
            }
            if (k <= 9)
            {
                int i1 = NumberInput.parseInt(ac, j, k);
                if (_numberNegative)
                {
                    i1 = -i1;
                }
                _numberInt = i1;
                _numTypesValid = 1;
                return;
            }
            if (k <= 18)
            {
                long l = NumberInput.parseLong(ac, j, k);
                if (_numberNegative)
                {
                    l = -l;
                }
                if (k == 10)
                {
                    if (_numberNegative)
                    {
                        if (l >= 0xffffffff80000000L)
                        {
                            _numberInt = (int)l;
                            _numTypesValid = 1;
                            return;
                        }
                    } else
                    if (l <= 0x7fffffffL)
                    {
                        _numberInt = (int)l;
                        _numTypesValid = 1;
                        return;
                    }
                }
                _numberLong = l;
                _numTypesValid = 2;
                return;
            } else
            {
                _parseSlowIntValue(i, ac, j, k);
                return;
            }
        }
        if (_currToken == JsonToken.VALUE_NUMBER_FLOAT)
        {
            _parseSlowFloatValue(i);
            return;
        } else
        {
            _reportError((new StringBuilder()).append("Current token (").append(_currToken).append(") not numeric, can not use numeric value accessors").toString());
            return;
        }
    }

    protected void convertNumberToBigDecimal()
        throws IOException, JsonParseException
    {
        if ((8 & _numTypesValid) != 0)
        {
            _numberBigDecimal = new BigDecimal(getText());
        } else
        if ((4 & _numTypesValid) != 0)
        {
            _numberBigDecimal = new BigDecimal(_numberBigInt);
        } else
        if ((2 & _numTypesValid) != 0)
        {
            _numberBigDecimal = BigDecimal.valueOf(_numberLong);
        } else
        if ((1 & _numTypesValid) != 0)
        {
            _numberBigDecimal = BigDecimal.valueOf(_numberInt);
        } else
        {
            _throwInternal();
        }
        _numTypesValid = 0x10 | _numTypesValid;
    }

    protected void convertNumberToBigInteger()
        throws IOException, JsonParseException
    {
        if ((0x10 & _numTypesValid) != 0)
        {
            _numberBigInt = _numberBigDecimal.toBigInteger();
        } else
        if ((2 & _numTypesValid) != 0)
        {
            _numberBigInt = BigInteger.valueOf(_numberLong);
        } else
        if ((1 & _numTypesValid) != 0)
        {
            _numberBigInt = BigInteger.valueOf(_numberInt);
        } else
        if ((8 & _numTypesValid) != 0)
        {
            _numberBigInt = BigDecimal.valueOf(_numberDouble).toBigInteger();
        } else
        {
            _throwInternal();
        }
        _numTypesValid = 4 | _numTypesValid;
    }

    protected void convertNumberToDouble()
        throws IOException, JsonParseException
    {
        if ((0x10 & _numTypesValid) != 0)
        {
            _numberDouble = _numberBigDecimal.doubleValue();
        } else
        if ((4 & _numTypesValid) != 0)
        {
            _numberDouble = _numberBigInt.doubleValue();
        } else
        if ((2 & _numTypesValid) != 0)
        {
            _numberDouble = _numberLong;
        } else
        if ((1 & _numTypesValid) != 0)
        {
            _numberDouble = _numberInt;
        } else
        {
            _throwInternal();
        }
        _numTypesValid = 8 | _numTypesValid;
    }

    protected void convertNumberToInt()
        throws IOException, JsonParseException
    {
        if ((2 & _numTypesValid) != 0)
        {
            int i = (int)_numberLong;
            if ((long)i != _numberLong)
            {
                _reportError((new StringBuilder()).append("Numeric value (").append(getText()).append(") out of range of int").toString());
            }
            _numberInt = i;
        } else
        if ((4 & _numTypesValid) != 0)
        {
            _numberInt = _numberBigInt.intValue();
        } else
        if ((8 & _numTypesValid) != 0)
        {
            if (_numberDouble < -2147483648D || _numberDouble > 2147483647D)
            {
                reportOverflowInt();
            }
            _numberInt = (int)_numberDouble;
        } else
        if ((0x10 & _numTypesValid) != 0)
        {
            if (BD_MIN_INT.compareTo(_numberBigDecimal) > 0 || BD_MAX_INT.compareTo(_numberBigDecimal) < 0)
            {
                reportOverflowInt();
            }
            _numberInt = _numberBigDecimal.intValue();
        } else
        {
            _throwInternal();
        }
        _numTypesValid = 1 | _numTypesValid;
    }

    protected void convertNumberToLong()
        throws IOException, JsonParseException
    {
        if ((1 & _numTypesValid) != 0)
        {
            _numberLong = _numberInt;
        } else
        if ((4 & _numTypesValid) != 0)
        {
            _numberLong = _numberBigInt.longValue();
        } else
        if ((8 & _numTypesValid) != 0)
        {
            if (_numberDouble < -9.2233720368547758E+18D || _numberDouble > 9.2233720368547758E+18D)
            {
                reportOverflowLong();
            }
            _numberLong = (long)_numberDouble;
        } else
        if ((0x10 & _numTypesValid) != 0)
        {
            if (BD_MIN_LONG.compareTo(_numberBigDecimal) > 0 || BD_MAX_LONG.compareTo(_numberBigDecimal) < 0)
            {
                reportOverflowLong();
            }
            _numberLong = _numberBigDecimal.longValue();
        } else
        {
            _throwInternal();
        }
        _numTypesValid = 2 | _numTypesValid;
    }

    public BigInteger getBigIntegerValue()
        throws IOException, JsonParseException
    {
        if ((4 & _numTypesValid) == 0)
        {
            if (_numTypesValid == 0)
            {
                _parseNumericValue(4);
            }
            if ((4 & _numTypesValid) == 0)
            {
                convertNumberToBigInteger();
            }
        }
        return _numberBigInt;
    }

    public BigDecimal getDecimalValue()
        throws IOException, JsonParseException
    {
        if ((0x10 & _numTypesValid) == 0)
        {
            if (_numTypesValid == 0)
            {
                _parseNumericValue(16);
            }
            if ((0x10 & _numTypesValid) == 0)
            {
                convertNumberToBigDecimal();
            }
        }
        return _numberBigDecimal;
    }

    public double getDoubleValue()
        throws IOException, JsonParseException
    {
        if ((8 & _numTypesValid) == 0)
        {
            if (_numTypesValid == 0)
            {
                _parseNumericValue(8);
            }
            if ((8 & _numTypesValid) == 0)
            {
                convertNumberToDouble();
            }
        }
        return _numberDouble;
    }

    public float getFloatValue()
        throws IOException, JsonParseException
    {
        return (float)getDoubleValue();
    }

    public int getIntValue()
        throws IOException, JsonParseException
    {
        if ((1 & _numTypesValid) == 0)
        {
            if (_numTypesValid == 0)
            {
                _parseNumericValue(1);
            }
            if ((1 & _numTypesValid) == 0)
            {
                convertNumberToInt();
            }
        }
        return _numberInt;
    }

    public long getLongValue()
        throws IOException, JsonParseException
    {
        if ((2 & _numTypesValid) == 0)
        {
            if (_numTypesValid == 0)
            {
                _parseNumericValue(2);
            }
            if ((2 & _numTypesValid) == 0)
            {
                convertNumberToLong();
            }
        }
        return _numberLong;
    }

    public org.codehaus.jackson.JsonParser.NumberType getNumberType()
        throws IOException, JsonParseException
    {
        if (_numTypesValid == 0)
        {
            _parseNumericValue(0);
        }
        if (_currToken == JsonToken.VALUE_NUMBER_INT)
        {
            if ((1 & _numTypesValid) != 0)
            {
                return org.codehaus.jackson.JsonParser.NumberType.INT;
            }
            if ((2 & _numTypesValid) != 0)
            {
                return org.codehaus.jackson.JsonParser.NumberType.LONG;
            } else
            {
                return org.codehaus.jackson.JsonParser.NumberType.BIG_INTEGER;
            }
        }
        if ((0x10 & _numTypesValid) != 0)
        {
            return org.codehaus.jackson.JsonParser.NumberType.BIG_DECIMAL;
        } else
        {
            return org.codehaus.jackson.JsonParser.NumberType.DOUBLE;
        }
    }

    public Number getNumberValue()
        throws IOException, JsonParseException
    {
        if (_numTypesValid == 0)
        {
            _parseNumericValue(0);
        }
        if (_currToken == JsonToken.VALUE_NUMBER_INT)
        {
            if ((1 & _numTypesValid) != 0)
            {
                return Integer.valueOf(_numberInt);
            }
            if ((2 & _numTypesValid) != 0)
            {
                return Long.valueOf(_numberLong);
            }
            if ((4 & _numTypesValid) != 0)
            {
                return _numberBigInt;
            } else
            {
                return _numberBigDecimal;
            }
        }
        if ((0x10 & _numTypesValid) != 0)
        {
            return _numberBigDecimal;
        }
        if ((8 & _numTypesValid) == 0)
        {
            _throwInternal();
        }
        return Double.valueOf(_numberDouble);
    }

    protected void reportInvalidNumber(String s)
        throws JsonParseException
    {
        _reportError((new StringBuilder()).append("Invalid numeric value: ").append(s).toString());
    }

    protected void reportOverflowInt()
        throws IOException, JsonParseException
    {
        _reportError((new StringBuilder()).append("Numeric value (").append(getText()).append(") out of range of int (").append(0x80000000).append(" - ").append(0x7fffffff).append(")").toString());
    }

    protected void reportOverflowLong()
        throws IOException, JsonParseException
    {
        _reportError((new StringBuilder()).append("Numeric value (").append(getText()).append(") out of range of long (").append(0x8000000000000000L).append(" - ").append(0x7fffffffffffffffL).append(")").toString());
    }

    protected void reportUnexpectedNumberChar(int i, String s)
        throws JsonParseException
    {
        String s1 = (new StringBuilder()).append("Unexpected character (").append(_getCharDesc(i)).append(") in numeric value").toString();
        if (s != null)
        {
            s1 = (new StringBuilder()).append(s1).append(": ").append(s).toString();
        }
        _reportError(s1);
    }

    protected final JsonToken reset(boolean flag, int i, int j, int k)
    {
        if (j < 1 && k < 1)
        {
            return resetInt(flag, i);
        } else
        {
            return resetFloat(flag, i, j, k);
        }
    }

    protected final JsonToken resetAsNaN(String s, double d)
    {
        _textBuffer.resetWithString(s);
        _numberDouble = d;
        _numTypesValid = 8;
        return JsonToken.VALUE_NUMBER_FLOAT;
    }

    protected final JsonToken resetFloat(boolean flag, int i, int j, int k)
    {
        _numberNegative = flag;
        _intLength = i;
        _fractLength = j;
        _expLength = k;
        _numTypesValid = 0;
        return JsonToken.VALUE_NUMBER_FLOAT;
    }

    protected final JsonToken resetInt(boolean flag, int i)
    {
        _numberNegative = flag;
        _intLength = i;
        _fractLength = 0;
        _expLength = 0;
        _numTypesValid = 0;
        return JsonToken.VALUE_NUMBER_INT;
    }

}

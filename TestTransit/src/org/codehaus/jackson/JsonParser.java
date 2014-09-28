// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson;

import java.io.Closeable;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import java.math.BigDecimal;
import java.math.BigInteger;
import org.codehaus.jackson.type.TypeReference;

// Referenced classes of package org.codehaus.jackson:
//            Versioned, JsonParseException, Base64Variants, JsonToken, 
//            JsonProcessingException, ObjectCodec, FormatSchema, Version, 
//            Base64Variant, JsonLocation, JsonStreamContext, JsonNode

public abstract class JsonParser
    implements Closeable, Versioned
{
    public static final class Feature extends Enum
    {

        private static final Feature $VALUES[];
        public static final Feature ALLOW_BACKSLASH_ESCAPING_ANY_CHARACTER;
        public static final Feature ALLOW_COMMENTS;
        public static final Feature ALLOW_NON_NUMERIC_NUMBERS;
        public static final Feature ALLOW_NUMERIC_LEADING_ZEROS;
        public static final Feature ALLOW_SINGLE_QUOTES;
        public static final Feature ALLOW_UNQUOTED_CONTROL_CHARS;
        public static final Feature ALLOW_UNQUOTED_FIELD_NAMES;
        public static final Feature AUTO_CLOSE_SOURCE;
        public static final Feature CANONICALIZE_FIELD_NAMES;
        public static final Feature INTERN_FIELD_NAMES;
        final boolean _defaultState;

        public static int collectDefaults()
        {
            int i = 0;
            Feature afeature[] = values();
            int j = afeature.length;
            for (int k = 0; k < j; k++)
            {
                Feature feature = afeature[k];
                if (feature.enabledByDefault())
                {
                    i |= feature.getMask();
                }
            }

            return i;
        }

        public static Feature valueOf(String s)
        {
            return (Feature)Enum.valueOf(org/codehaus/jackson/JsonParser$Feature, s);
        }

        public static Feature[] values()
        {
            return (Feature[])$VALUES.clone();
        }

        public boolean enabledByDefault()
        {
            return _defaultState;
        }

        public boolean enabledIn(int i)
        {
            return (i & getMask()) != 0;
        }

        public int getMask()
        {
            return 1 << ordinal();
        }

        static 
        {
            AUTO_CLOSE_SOURCE = new Feature("AUTO_CLOSE_SOURCE", 0, true);
            ALLOW_COMMENTS = new Feature("ALLOW_COMMENTS", 1, false);
            ALLOW_UNQUOTED_FIELD_NAMES = new Feature("ALLOW_UNQUOTED_FIELD_NAMES", 2, false);
            ALLOW_SINGLE_QUOTES = new Feature("ALLOW_SINGLE_QUOTES", 3, false);
            ALLOW_UNQUOTED_CONTROL_CHARS = new Feature("ALLOW_UNQUOTED_CONTROL_CHARS", 4, false);
            ALLOW_BACKSLASH_ESCAPING_ANY_CHARACTER = new Feature("ALLOW_BACKSLASH_ESCAPING_ANY_CHARACTER", 5, false);
            ALLOW_NUMERIC_LEADING_ZEROS = new Feature("ALLOW_NUMERIC_LEADING_ZEROS", 6, false);
            ALLOW_NON_NUMERIC_NUMBERS = new Feature("ALLOW_NON_NUMERIC_NUMBERS", 7, false);
            INTERN_FIELD_NAMES = new Feature("INTERN_FIELD_NAMES", 8, true);
            CANONICALIZE_FIELD_NAMES = new Feature("CANONICALIZE_FIELD_NAMES", 9, true);
            Feature afeature[] = new Feature[10];
            afeature[0] = AUTO_CLOSE_SOURCE;
            afeature[1] = ALLOW_COMMENTS;
            afeature[2] = ALLOW_UNQUOTED_FIELD_NAMES;
            afeature[3] = ALLOW_SINGLE_QUOTES;
            afeature[4] = ALLOW_UNQUOTED_CONTROL_CHARS;
            afeature[5] = ALLOW_BACKSLASH_ESCAPING_ANY_CHARACTER;
            afeature[6] = ALLOW_NUMERIC_LEADING_ZEROS;
            afeature[7] = ALLOW_NON_NUMERIC_NUMBERS;
            afeature[8] = INTERN_FIELD_NAMES;
            afeature[9] = CANONICALIZE_FIELD_NAMES;
            $VALUES = afeature;
        }

        private Feature(String s, int i, boolean flag)
        {
            super(s, i);
            _defaultState = flag;
        }
    }

    public static final class NumberType extends Enum
    {

        private static final NumberType $VALUES[];
        public static final NumberType BIG_DECIMAL;
        public static final NumberType BIG_INTEGER;
        public static final NumberType DOUBLE;
        public static final NumberType FLOAT;
        public static final NumberType INT;
        public static final NumberType LONG;

        public static NumberType valueOf(String s)
        {
            return (NumberType)Enum.valueOf(org/codehaus/jackson/JsonParser$NumberType, s);
        }

        public static NumberType[] values()
        {
            return (NumberType[])$VALUES.clone();
        }

        static 
        {
            INT = new NumberType("INT", 0);
            LONG = new NumberType("LONG", 1);
            BIG_INTEGER = new NumberType("BIG_INTEGER", 2);
            FLOAT = new NumberType("FLOAT", 3);
            DOUBLE = new NumberType("DOUBLE", 4);
            BIG_DECIMAL = new NumberType("BIG_DECIMAL", 5);
            NumberType anumbertype[] = new NumberType[6];
            anumbertype[0] = INT;
            anumbertype[1] = LONG;
            anumbertype[2] = BIG_INTEGER;
            anumbertype[3] = FLOAT;
            anumbertype[4] = DOUBLE;
            anumbertype[5] = BIG_DECIMAL;
            $VALUES = anumbertype;
        }

        private NumberType(String s, int i)
        {
            super(s, i);
        }
    }


    private static final int MAX_BYTE_I = 127;
    private static final int MAX_SHORT_I = 32767;
    private static final int MIN_BYTE_I = -128;
    private static final int MIN_SHORT_I = -32768;
    protected JsonToken _currToken;
    protected int _features;
    protected JsonToken _lastClearedToken;

    protected JsonParser()
    {
    }

    protected JsonParser(int i)
    {
        _features = i;
    }

    protected JsonParseException _constructError(String s)
    {
        return new JsonParseException(s, getCurrentLocation());
    }

    public boolean canUseSchema(FormatSchema formatschema)
    {
        return false;
    }

    public void clearCurrentToken()
    {
        if (_currToken != null)
        {
            _lastClearedToken = _currToken;
            _currToken = null;
        }
    }

    public abstract void close()
        throws IOException;

    public JsonParser configure(Feature feature, boolean flag)
    {
        if (flag)
        {
            enableFeature(feature);
            return this;
        } else
        {
            disableFeature(feature);
            return this;
        }
    }

    public JsonParser disable(Feature feature)
    {
        _features = _features & (-1 ^ feature.getMask());
        return this;
    }

    public void disableFeature(Feature feature)
    {
        disable(feature);
    }

    public JsonParser enable(Feature feature)
    {
        _features = _features | feature.getMask();
        return this;
    }

    public void enableFeature(Feature feature)
    {
        enable(feature);
    }

    public abstract BigInteger getBigIntegerValue()
        throws IOException, JsonParseException;

    public byte[] getBinaryValue()
        throws IOException, JsonParseException
    {
        return getBinaryValue(Base64Variants.getDefaultVariant());
    }

    public abstract byte[] getBinaryValue(Base64Variant base64variant)
        throws IOException, JsonParseException;

    public boolean getBooleanValue()
        throws IOException, JsonParseException
    {
        if (_currToken == JsonToken.VALUE_TRUE)
        {
            return true;
        }
        if (_currToken == JsonToken.VALUE_FALSE)
        {
            return false;
        } else
        {
            throw new JsonParseException((new StringBuilder()).append("Current token (").append(_currToken).append(") not of boolean type").toString(), getCurrentLocation());
        }
    }

    public byte getByteValue()
        throws IOException, JsonParseException
    {
        int i = getIntValue();
        if (i < -128 || i > 127)
        {
            throw _constructError((new StringBuilder()).append("Numeric value (").append(getText()).append(") out of range of Java byte").toString());
        } else
        {
            return (byte)i;
        }
    }

    public abstract ObjectCodec getCodec();

    public abstract JsonLocation getCurrentLocation();

    public abstract String getCurrentName()
        throws IOException, JsonParseException;

    public JsonToken getCurrentToken()
    {
        return _currToken;
    }

    public abstract BigDecimal getDecimalValue()
        throws IOException, JsonParseException;

    public abstract double getDoubleValue()
        throws IOException, JsonParseException;

    public Object getEmbeddedObject()
        throws IOException, JsonParseException
    {
        return null;
    }

    public abstract float getFloatValue()
        throws IOException, JsonParseException;

    public Object getInputSource()
    {
        return null;
    }

    public abstract int getIntValue()
        throws IOException, JsonParseException;

    public JsonToken getLastClearedToken()
    {
        return _lastClearedToken;
    }

    public abstract long getLongValue()
        throws IOException, JsonParseException;

    public abstract NumberType getNumberType()
        throws IOException, JsonParseException;

    public abstract Number getNumberValue()
        throws IOException, JsonParseException;

    public abstract JsonStreamContext getParsingContext();

    public short getShortValue()
        throws IOException, JsonParseException
    {
        int i = getIntValue();
        if (i < -32768 || i > 32767)
        {
            throw _constructError((new StringBuilder()).append("Numeric value (").append(getText()).append(") out of range of Java short").toString());
        } else
        {
            return (short)i;
        }
    }

    public abstract String getText()
        throws IOException, JsonParseException;

    public abstract char[] getTextCharacters()
        throws IOException, JsonParseException;

    public abstract int getTextLength()
        throws IOException, JsonParseException;

    public abstract int getTextOffset()
        throws IOException, JsonParseException;

    public abstract JsonLocation getTokenLocation();

    public boolean getValueAsBoolean()
        throws IOException, JsonParseException
    {
        return getValueAsBoolean(false);
    }

    public boolean getValueAsBoolean(boolean flag)
        throws IOException, JsonParseException
    {
        return flag;
    }

    public double getValueAsDouble()
        throws IOException, JsonParseException
    {
        return getValueAsDouble(0.0D);
    }

    public double getValueAsDouble(double d)
        throws IOException, JsonParseException
    {
        return d;
    }

    public int getValueAsInt()
        throws IOException, JsonParseException
    {
        return getValueAsInt(0);
    }

    public int getValueAsInt(int i)
        throws IOException, JsonParseException
    {
        return i;
    }

    public long getValueAsLong()
        throws IOException, JsonParseException
    {
        return (long)getValueAsInt(0);
    }

    public long getValueAsLong(long l)
        throws IOException, JsonParseException
    {
        return l;
    }

    public boolean hasCurrentToken()
    {
        return _currToken != null;
    }

    public boolean hasTextCharacters()
    {
        return false;
    }

    public abstract boolean isClosed();

    public boolean isEnabled(Feature feature)
    {
        return (_features & feature.getMask()) != 0;
    }

    public boolean isExpectedStartArrayToken()
    {
        return getCurrentToken() == JsonToken.START_ARRAY;
    }

    public final boolean isFeatureEnabled(Feature feature)
    {
        return isEnabled(feature);
    }

    public abstract JsonToken nextToken()
        throws IOException, JsonParseException;

    public JsonToken nextValue()
        throws IOException, JsonParseException
    {
        JsonToken jsontoken = nextToken();
        if (jsontoken == JsonToken.FIELD_NAME)
        {
            jsontoken = nextToken();
        }
        return jsontoken;
    }

    public Object readValueAs(Class class1)
        throws IOException, JsonProcessingException
    {
        ObjectCodec objectcodec = getCodec();
        if (objectcodec == null)
        {
            throw new IllegalStateException("No ObjectCodec defined for the parser, can not deserialize JSON into Java objects");
        } else
        {
            return objectcodec.readValue(this, class1);
        }
    }

    public Object readValueAs(TypeReference typereference)
        throws IOException, JsonProcessingException
    {
        ObjectCodec objectcodec = getCodec();
        if (objectcodec == null)
        {
            throw new IllegalStateException("No ObjectCodec defined for the parser, can not deserialize JSON into Java objects");
        } else
        {
            return objectcodec.readValue(this, typereference);
        }
    }

    public JsonNode readValueAsTree()
        throws IOException, JsonProcessingException
    {
        ObjectCodec objectcodec = getCodec();
        if (objectcodec == null)
        {
            throw new IllegalStateException("No ObjectCodec defined for the parser, can not deserialize JSON into JsonNode tree");
        } else
        {
            return objectcodec.readTree(this);
        }
    }

    public int releaseBuffered(OutputStream outputstream)
        throws IOException
    {
        return -1;
    }

    public int releaseBuffered(Writer writer)
        throws IOException
    {
        return -1;
    }

    public abstract void setCodec(ObjectCodec objectcodec);

    public void setFeature(Feature feature, boolean flag)
    {
        configure(feature, flag);
    }

    public void setSchema(FormatSchema formatschema)
    {
        throw new UnsupportedOperationException((new StringBuilder()).append("Parser of type ").append(getClass().getName()).append(" does not support schema of type '").append(formatschema.getSchemaType()).append("'").toString());
    }

    public abstract JsonParser skipChildren()
        throws IOException, JsonParseException;

    public Version version()
    {
        return Version.unknownVersion();
    }
}

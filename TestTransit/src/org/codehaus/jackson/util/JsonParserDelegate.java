// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.util;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import org.codehaus.jackson.Base64Variant;
import org.codehaus.jackson.FormatSchema;
import org.codehaus.jackson.JsonLocation;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonStreamContext;
import org.codehaus.jackson.JsonToken;
import org.codehaus.jackson.ObjectCodec;
import org.codehaus.jackson.Version;

public class JsonParserDelegate extends JsonParser
{

    protected JsonParser _flddelegate;

    public JsonParserDelegate(JsonParser jsonparser)
    {
        _flddelegate = jsonparser;
    }

    public boolean canUseSchema(FormatSchema formatschema)
    {
        return _flddelegate.canUseSchema(formatschema);
    }

    public void clearCurrentToken()
    {
        _flddelegate.clearCurrentToken();
    }

    public void close()
        throws IOException
    {
        _flddelegate.close();
    }

    public JsonParser disable(org.codehaus.jackson.JsonParser.Feature feature)
    {
        _flddelegate.disable(feature);
        return this;
    }

    public JsonParser enable(org.codehaus.jackson.JsonParser.Feature feature)
    {
        _flddelegate.enable(feature);
        return this;
    }

    public BigInteger getBigIntegerValue()
        throws IOException, JsonParseException
    {
        return _flddelegate.getBigIntegerValue();
    }

    public byte[] getBinaryValue(Base64Variant base64variant)
        throws IOException, JsonParseException
    {
        return _flddelegate.getBinaryValue(base64variant);
    }

    public byte getByteValue()
        throws IOException, JsonParseException
    {
        return _flddelegate.getByteValue();
    }

    public ObjectCodec getCodec()
    {
        return _flddelegate.getCodec();
    }

    public JsonLocation getCurrentLocation()
    {
        return _flddelegate.getCurrentLocation();
    }

    public String getCurrentName()
        throws IOException, JsonParseException
    {
        return _flddelegate.getCurrentName();
    }

    public JsonToken getCurrentToken()
    {
        return _flddelegate.getCurrentToken();
    }

    public BigDecimal getDecimalValue()
        throws IOException, JsonParseException
    {
        return _flddelegate.getDecimalValue();
    }

    public double getDoubleValue()
        throws IOException, JsonParseException
    {
        return _flddelegate.getDoubleValue();
    }

    public float getFloatValue()
        throws IOException, JsonParseException
    {
        return _flddelegate.getFloatValue();
    }

    public Object getInputSource()
    {
        return _flddelegate.getInputSource();
    }

    public int getIntValue()
        throws IOException, JsonParseException
    {
        return _flddelegate.getIntValue();
    }

    public JsonToken getLastClearedToken()
    {
        return _flddelegate.getLastClearedToken();
    }

    public long getLongValue()
        throws IOException, JsonParseException
    {
        return _flddelegate.getLongValue();
    }

    public org.codehaus.jackson.JsonParser.NumberType getNumberType()
        throws IOException, JsonParseException
    {
        return _flddelegate.getNumberType();
    }

    public Number getNumberValue()
        throws IOException, JsonParseException
    {
        return _flddelegate.getNumberValue();
    }

    public JsonStreamContext getParsingContext()
    {
        return _flddelegate.getParsingContext();
    }

    public short getShortValue()
        throws IOException, JsonParseException
    {
        return _flddelegate.getShortValue();
    }

    public String getText()
        throws IOException, JsonParseException
    {
        return _flddelegate.getText();
    }

    public char[] getTextCharacters()
        throws IOException, JsonParseException
    {
        return _flddelegate.getTextCharacters();
    }

    public int getTextLength()
        throws IOException, JsonParseException
    {
        return _flddelegate.getTextLength();
    }

    public int getTextOffset()
        throws IOException, JsonParseException
    {
        return _flddelegate.getTextOffset();
    }

    public JsonLocation getTokenLocation()
    {
        return _flddelegate.getTokenLocation();
    }

    public boolean hasCurrentToken()
    {
        return _flddelegate.hasCurrentToken();
    }

    public boolean isClosed()
    {
        return _flddelegate.isClosed();
    }

    public boolean isEnabled(org.codehaus.jackson.JsonParser.Feature feature)
    {
        return _flddelegate.isEnabled(feature);
    }

    public JsonToken nextToken()
        throws IOException, JsonParseException
    {
        return _flddelegate.nextToken();
    }

    public void setCodec(ObjectCodec objectcodec)
    {
        _flddelegate.setCodec(objectcodec);
    }

    public void setSchema(FormatSchema formatschema)
    {
        _flddelegate.setSchema(formatschema);
    }

    public JsonParser skipChildren()
        throws IOException, JsonParseException
    {
        _flddelegate.skipChildren();
        return this;
    }

    public Version version()
    {
        return _flddelegate.version();
    }
}

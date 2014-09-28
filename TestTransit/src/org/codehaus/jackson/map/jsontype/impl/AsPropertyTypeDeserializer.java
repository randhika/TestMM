// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.map.jsontype.impl;

import java.io.IOException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.JsonToken;
import org.codehaus.jackson.map.BeanProperty;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonDeserializer;
import org.codehaus.jackson.map.jsontype.TypeIdResolver;
import org.codehaus.jackson.type.JavaType;
import org.codehaus.jackson.util.JsonParserSequence;
import org.codehaus.jackson.util.TokenBuffer;

// Referenced classes of package org.codehaus.jackson.map.jsontype.impl:
//            AsArrayTypeDeserializer

public class AsPropertyTypeDeserializer extends AsArrayTypeDeserializer
{

    protected final String _typePropertyName;

    public AsPropertyTypeDeserializer(JavaType javatype, TypeIdResolver typeidresolver, BeanProperty beanproperty, String s)
    {
        super(javatype, typeidresolver, beanproperty);
        _typePropertyName = s;
    }

    public Object deserializeTypedFromAny(JsonParser jsonparser, DeserializationContext deserializationcontext)
        throws IOException, JsonProcessingException
    {
        if (jsonparser.getCurrentToken() == JsonToken.START_ARRAY)
        {
            return super.deserializeTypedFromArray(jsonparser, deserializationcontext);
        } else
        {
            return deserializeTypedFromObject(jsonparser, deserializationcontext);
        }
    }

    public Object deserializeTypedFromObject(JsonParser jsonparser, DeserializationContext deserializationcontext)
        throws IOException, JsonProcessingException
    {
        JsonToken jsontoken = jsonparser.getCurrentToken();
        TokenBuffer tokenbuffer;
        if (jsontoken == JsonToken.START_OBJECT)
        {
            jsontoken = jsonparser.nextToken();
        } else
        if (jsontoken != JsonToken.FIELD_NAME)
        {
            throw deserializationcontext.wrongTokenException(jsonparser, JsonToken.START_OBJECT, (new StringBuilder()).append("need JSON Object to contain As.PROPERTY type information (for class ").append(baseTypeName()).append(")").toString());
        }
        tokenbuffer = null;
        for (; jsontoken == JsonToken.FIELD_NAME; jsontoken = jsonparser.nextToken())
        {
            String s = jsonparser.getCurrentName();
            jsonparser.nextToken();
            if (_typePropertyName.equals(s))
            {
                JsonDeserializer jsondeserializer = _findDeserializer(deserializationcontext, jsonparser.getText());
                if (tokenbuffer != null)
                {
                    jsonparser = JsonParserSequence.createFlattened(tokenbuffer.asParser(jsonparser), jsonparser);
                }
                jsonparser.nextToken();
                return jsondeserializer.deserialize(jsonparser, deserializationcontext);
            }
            if (tokenbuffer == null)
            {
                tokenbuffer = new TokenBuffer(null);
            }
            tokenbuffer.writeFieldName(s);
            tokenbuffer.copyCurrentStructure(jsonparser);
        }

        throw deserializationcontext.wrongTokenException(jsonparser, JsonToken.FIELD_NAME, (new StringBuilder()).append("missing property '").append(_typePropertyName).append("' that is to contain type id  (for class ").append(baseTypeName()).append(")").toString());
    }

    public String getPropertyName()
    {
        return _typePropertyName;
    }

    public org.codehaus.jackson.annotate.JsonTypeInfo.As getTypeInclusion()
    {
        return org.codehaus.jackson.annotate.JsonTypeInfo.As.PROPERTY;
    }
}

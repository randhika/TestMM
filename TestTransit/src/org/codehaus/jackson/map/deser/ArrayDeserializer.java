// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.map.deser;

import java.io.IOException;
import java.lang.reflect.Array;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.JsonToken;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonDeserializer;
import org.codehaus.jackson.map.TypeDeserializer;
import org.codehaus.jackson.map.type.ArrayType;
import org.codehaus.jackson.map.util.ObjectBuffer;
import org.codehaus.jackson.type.JavaType;

// Referenced classes of package org.codehaus.jackson.map.deser:
//            ContainerDeserializer

public class ArrayDeserializer extends ContainerDeserializer
{

    protected final JavaType _arrayType;
    protected final Class _elementClass;
    protected final JsonDeserializer _elementDeserializer;
    final TypeDeserializer _elementTypeDeserializer;
    protected final boolean _untyped;

    public ArrayDeserializer(ArrayType arraytype, JsonDeserializer jsondeserializer)
    {
        this(arraytype, jsondeserializer, null);
    }

    public ArrayDeserializer(ArrayType arraytype, JsonDeserializer jsondeserializer, TypeDeserializer typedeserializer)
    {
        super([Ljava/lang/Object;);
        _arrayType = arraytype;
        _elementClass = arraytype.getContentType().getRawClass();
        boolean flag;
        if (_elementClass == java/lang/Object)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        _untyped = flag;
        _elementDeserializer = jsondeserializer;
        _elementTypeDeserializer = typedeserializer;
    }

    private final Object[] handleNonArray(JsonParser jsonparser, DeserializationContext deserializationcontext)
        throws IOException, JsonProcessingException
    {
        if (!deserializationcontext.isEnabled(org.codehaus.jackson.map.DeserializationConfig.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY))
        {
            if (jsonparser.getCurrentToken() == JsonToken.VALUE_STRING && _elementClass == java/lang/Byte)
            {
                return deserializeFromBase64(jsonparser, deserializationcontext);
            } else
            {
                throw deserializationcontext.mappingException(_arrayType.getRawClass());
            }
        }
        Object obj;
        Object aobj[];
        if (jsonparser.getCurrentToken() == JsonToken.VALUE_NULL)
        {
            obj = null;
        } else
        if (_elementTypeDeserializer == null)
        {
            obj = _elementDeserializer.deserialize(jsonparser, deserializationcontext);
        } else
        {
            obj = _elementDeserializer.deserializeWithType(jsonparser, deserializationcontext, _elementTypeDeserializer);
        }
        if (_untyped)
        {
            aobj = new Object[1];
        } else
        {
            aobj = (Object[])(Object[])Array.newInstance(_elementClass, 1);
        }
        aobj[0] = obj;
        return aobj;
    }

    public volatile Object deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext)
        throws IOException, JsonProcessingException
    {
        return ((Object) (deserialize(jsonparser, deserializationcontext)));
    }

    public Object[] deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext)
        throws IOException, JsonProcessingException
    {
        if (!jsonparser.isExpectedStartArrayToken())
        {
            return handleNonArray(jsonparser, deserializationcontext);
        }
        ObjectBuffer objectbuffer = deserializationcontext.leaseObjectBuffer();
        Object aobj[] = objectbuffer.resetAndStart();
        int i = 0;
        TypeDeserializer typedeserializer = _elementTypeDeserializer;
        do
        {
            JsonToken jsontoken = jsonparser.nextToken();
            if (jsontoken == JsonToken.END_ARRAY)
            {
                break;
            }
            Object obj;
            int j;
            if (jsontoken == JsonToken.VALUE_NULL)
            {
                obj = null;
            } else
            if (typedeserializer == null)
            {
                obj = _elementDeserializer.deserialize(jsonparser, deserializationcontext);
            } else
            {
                obj = _elementDeserializer.deserializeWithType(jsonparser, deserializationcontext, typedeserializer);
            }
            if (i >= aobj.length)
            {
                aobj = objectbuffer.appendCompletedChunk(aobj);
                i = 0;
            }
            j = i + 1;
            aobj[i] = obj;
            i = j;
        } while (true);
        Object aobj1[];
        if (_untyped)
        {
            aobj1 = objectbuffer.completeAndClearBuffer(aobj, i);
        } else
        {
            aobj1 = objectbuffer.completeAndClearBuffer(aobj, i, _elementClass);
        }
        deserializationcontext.returnObjectBuffer(objectbuffer);
        return aobj1;
    }

    protected Byte[] deserializeFromBase64(JsonParser jsonparser, DeserializationContext deserializationcontext)
        throws IOException, JsonProcessingException
    {
        byte abyte0[] = jsonparser.getBinaryValue(deserializationcontext.getBase64Variant());
        Byte abyte[] = new Byte[abyte0.length];
        int i = 0;
        for (int j = abyte0.length; i < j; i++)
        {
            abyte[i] = Byte.valueOf(abyte0[i]);
        }

        return abyte;
    }

    public volatile Object deserializeWithType(JsonParser jsonparser, DeserializationContext deserializationcontext, TypeDeserializer typedeserializer)
        throws IOException, JsonProcessingException
    {
        return ((Object) (deserializeWithType(jsonparser, deserializationcontext, typedeserializer)));
    }

    public Object[] deserializeWithType(JsonParser jsonparser, DeserializationContext deserializationcontext, TypeDeserializer typedeserializer)
        throws IOException, JsonProcessingException
    {
        return (Object[])(Object[])typedeserializer.deserializeTypedFromArray(jsonparser, deserializationcontext);
    }

    public JsonDeserializer getContentDeserializer()
    {
        return _elementDeserializer;
    }

    public JavaType getContentType()
    {
        return _arrayType.getContentType();
    }
}

// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.map;

import java.io.IOException;
import java.util.Iterator;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonStreamContext;
import org.codehaus.jackson.JsonToken;
import org.codehaus.jackson.type.JavaType;

// Referenced classes of package org.codehaus.jackson.map:
//            JsonMappingException, RuntimeJsonMappingException, JsonDeserializer, DeserializationContext

public class MappingIterator
    implements Iterator
{

    protected static final MappingIterator EMPTY_ITERATOR = new MappingIterator(null, null, null, null);
    protected final DeserializationContext _context;
    protected final JsonDeserializer _deserializer;
    protected final JsonParser _parser;
    protected final JavaType _type;

    protected MappingIterator(JavaType javatype, JsonParser jsonparser, DeserializationContext deserializationcontext, JsonDeserializer jsondeserializer)
    {
        _type = javatype;
        _parser = jsonparser;
        _context = deserializationcontext;
        _deserializer = jsondeserializer;
        if (jsonparser != null && jsonparser.getCurrentToken() == JsonToken.START_ARRAY && !jsonparser.getParsingContext().inRoot())
        {
            jsonparser.clearCurrentToken();
        }
    }

    protected static MappingIterator emptyIterator()
    {
        return EMPTY_ITERATOR;
    }

    public boolean hasNext()
    {
        boolean flag;
        try
        {
            flag = hasNextValue();
        }
        catch (JsonMappingException jsonmappingexception)
        {
            throw new RuntimeJsonMappingException(jsonmappingexception.getMessage(), jsonmappingexception);
        }
        catch (IOException ioexception)
        {
            throw new RuntimeException(ioexception.getMessage(), ioexception);
        }
        return flag;
    }

    public boolean hasNextValue()
        throws IOException
    {
        if (_parser != null) goto _L2; else goto _L1
_L1:
        return false;
_L2:
        JsonToken jsontoken;
        if (_parser.getCurrentToken() != null)
        {
            break; /* Loop/switch isn't completed */
        }
        jsontoken = _parser.nextToken();
        if (jsontoken == null)
        {
            _parser.close();
            return false;
        }
        if (jsontoken == JsonToken.END_ARRAY) goto _L1; else goto _L3
_L3:
        return true;
    }

    public Object next()
    {
        Object obj;
        try
        {
            obj = nextValue();
        }
        catch (JsonMappingException jsonmappingexception)
        {
            throw new RuntimeJsonMappingException(jsonmappingexception.getMessage(), jsonmappingexception);
        }
        catch (IOException ioexception)
        {
            throw new RuntimeException(ioexception.getMessage(), ioexception);
        }
        return obj;
    }

    public Object nextValue()
        throws IOException
    {
        Object obj = _deserializer.deserialize(_parser, _context);
        _parser.clearCurrentToken();
        return obj;
    }

    public void remove()
    {
        throw new UnsupportedOperationException();
    }

}

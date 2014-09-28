// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.map.deser;

import java.io.IOException;
import java.lang.reflect.Method;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.JsonToken;
import org.codehaus.jackson.map.BeanProperty;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonDeserializer;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.introspect.AnnotatedMethod;
import org.codehaus.jackson.type.JavaType;

public final class SettableAnyProperty
{

    protected final BeanProperty _property;
    protected final Method _setter;
    protected final JavaType _type;
    protected JsonDeserializer _valueDeserializer;

    public SettableAnyProperty(BeanProperty beanproperty, AnnotatedMethod annotatedmethod, JavaType javatype)
    {
        _property = beanproperty;
        _type = javatype;
        _setter = annotatedmethod.getAnnotated();
    }

    private String getClassName()
    {
        return _setter.getDeclaringClass().getName();
    }

    protected void _throwAsIOE(Exception exception, String s, Object obj)
        throws IOException
    {
        if (exception instanceof IllegalArgumentException)
        {
            String s1;
            StringBuilder stringbuilder;
            String s2;
            if (obj == null)
            {
                s1 = "[NULL]";
            } else
            {
                s1 = obj.getClass().getName();
            }
            stringbuilder = (new StringBuilder("Problem deserializing \"any\" property '")).append(s);
            stringbuilder.append((new StringBuilder()).append("' of class ").append(getClassName()).append(" (expected type: ").toString()).append(_type);
            stringbuilder.append("; actual type: ").append(s1).append(")");
            s2 = exception.getMessage();
            if (s2 != null)
            {
                stringbuilder.append(", problem: ").append(s2);
            } else
            {
                stringbuilder.append(" (no error message provided)");
            }
            throw new JsonMappingException(stringbuilder.toString(), null, exception);
        }
        if (exception instanceof IOException)
        {
            throw (IOException)exception;
        }
        if (exception instanceof RuntimeException)
        {
            throw (RuntimeException)exception;
        }
        Object obj1;
        for (obj1 = exception; ((Throwable) (obj1)).getCause() != null; obj1 = ((Throwable) (obj1)).getCause()) { }
        throw new JsonMappingException(((Throwable) (obj1)).getMessage(), null, ((Throwable) (obj1)));
    }

    public final Object deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext)
        throws IOException, JsonProcessingException
    {
        if (jsonparser.getCurrentToken() == JsonToken.VALUE_NULL)
        {
            return null;
        } else
        {
            return _valueDeserializer.deserialize(jsonparser, deserializationcontext);
        }
    }

    public final void deserializeAndSet(JsonParser jsonparser, DeserializationContext deserializationcontext, Object obj, String s)
        throws IOException, JsonProcessingException
    {
        set(obj, s, deserialize(jsonparser, deserializationcontext));
    }

    public BeanProperty getProperty()
    {
        return _property;
    }

    public JavaType getType()
    {
        return _type;
    }

    public boolean hasValueDeserializer()
    {
        return _valueDeserializer != null;
    }

    public final void set(Object obj, String s, Object obj1)
        throws IOException
    {
        try
        {
            _setter.invoke(obj, new Object[] {
                s, obj1
            });
            return;
        }
        catch (Exception exception)
        {
            _throwAsIOE(exception, s, obj1);
        }
    }

    public void setValueDeserializer(JsonDeserializer jsondeserializer)
    {
        if (_valueDeserializer != null)
        {
            throw new IllegalStateException("Already had assigned deserializer for SettableAnyProperty");
        } else
        {
            _valueDeserializer = jsondeserializer;
            return;
        }
    }

    public String toString()
    {
        return (new StringBuilder()).append("[any property on class ").append(getClassName()).append("]").toString();
    }
}

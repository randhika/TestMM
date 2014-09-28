// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.map.deser;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.JsonToken;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonDeserializer;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.TypeDeserializer;
import org.codehaus.jackson.map.introspect.AnnotatedMember;
import org.codehaus.jackson.map.introspect.AnnotatedMethod;
import org.codehaus.jackson.map.util.Annotations;
import org.codehaus.jackson.type.JavaType;

// Referenced classes of package org.codehaus.jackson.map.deser:
//            SettableBeanProperty

public static final class _getter extends SettableBeanProperty
{

    protected final AnnotatedMethod _annotated;
    protected final Method _getter;

    public final void deserializeAndSet(JsonParser jsonparser, DeserializationContext deserializationcontext, Object obj)
        throws IOException, JsonProcessingException
    {
        if (jsonparser.getCurrentToken() == JsonToken.VALUE_NULL)
        {
            return;
        }
        Object obj1;
        try
        {
            obj1 = _getter.invoke(obj, new Object[0]);
        }
        catch (Exception exception)
        {
            _throwAsIOE(exception);
            return;
        }
        if (obj1 == null)
        {
            throw new JsonMappingException((new StringBuilder()).append("Problem deserializing 'setterless' property '").append(getName()).append("': get method returned null").toString());
        } else
        {
            _valueDeserializer.deserialize(jsonparser, deserializationcontext, obj1);
            return;
        }
    }

    public Annotation getAnnotation(Class class1)
    {
        return _annotated.getAnnotation(class1);
    }

    public AnnotatedMember getMember()
    {
        return _annotated;
    }

    public final void set(Object obj, Object obj1)
        throws IOException
    {
        throw new UnsupportedOperationException("Should never call 'set' on setterless property");
    }

    public (String s, JavaType javatype, TypeDeserializer typedeserializer, Annotations annotations, AnnotatedMethod annotatedmethod)
    {
        super(s, javatype, typedeserializer, annotations);
        _annotated = annotatedmethod;
        _getter = annotatedmethod.getAnnotated();
    }
}

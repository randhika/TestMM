// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.map.deser;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.TypeDeserializer;
import org.codehaus.jackson.map.introspect.AnnotatedMember;
import org.codehaus.jackson.map.introspect.AnnotatedMethod;
import org.codehaus.jackson.map.util.Annotations;
import org.codehaus.jackson.type.JavaType;

// Referenced classes of package org.codehaus.jackson.map.deser:
//            SettableBeanProperty

public static final class _setter extends SettableBeanProperty
{

    protected final AnnotatedMethod _annotated;
    protected final Method _setter;

    public void deserializeAndSet(JsonParser jsonparser, DeserializationContext deserializationcontext, Object obj)
        throws IOException, JsonProcessingException
    {
        set(obj, deserialize(jsonparser, deserializationcontext));
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
        try
        {
            _setter.invoke(obj, new Object[] {
                obj1
            });
            return;
        }
        catch (Exception exception)
        {
            _throwAsIOE(exception, obj1);
        }
    }

    public (String s, JavaType javatype, TypeDeserializer typedeserializer, Annotations annotations, AnnotatedMethod annotatedmethod)
    {
        super(s, javatype, typedeserializer, annotations);
        _annotated = annotatedmethod;
        _setter = annotatedmethod.getAnnotated();
    }
}

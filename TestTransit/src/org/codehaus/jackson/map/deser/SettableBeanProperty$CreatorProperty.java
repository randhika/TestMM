// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.map.deser;

import java.io.IOException;
import java.lang.annotation.Annotation;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.TypeDeserializer;
import org.codehaus.jackson.map.introspect.AnnotatedMember;
import org.codehaus.jackson.map.introspect.AnnotatedParameter;
import org.codehaus.jackson.map.util.Annotations;
import org.codehaus.jackson.type.JavaType;

// Referenced classes of package org.codehaus.jackson.map.deser:
//            SettableBeanProperty

public static final class _index extends SettableBeanProperty
{

    protected final AnnotatedParameter _annotated;
    protected final int _index;

    public void deserializeAndSet(JsonParser jsonparser, DeserializationContext deserializationcontext, Object obj)
        throws IOException, JsonProcessingException
    {
        set(obj, deserialize(jsonparser, deserializationcontext));
    }

    public Annotation getAnnotation(Class class1)
    {
        return _annotated.getAnnotation(class1);
    }

    public int getCreatorIndex()
    {
        return _index;
    }

    public AnnotatedMember getMember()
    {
        return _annotated;
    }

    public void set(Object obj, Object obj1)
        throws IOException
    {
    }

    public I(String s, JavaType javatype, TypeDeserializer typedeserializer, Annotations annotations, AnnotatedParameter annotatedparameter, int i)
    {
        super(s, javatype, typedeserializer, annotations);
        _annotated = annotatedparameter;
        _index = i;
    }
}

// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.map.deser;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonDeserializer;
import org.codehaus.jackson.map.introspect.AnnotatedConstructor;
import org.codehaus.jackson.map.introspect.AnnotatedMember;
import org.codehaus.jackson.map.introspect.AnnotatedMethod;
import org.codehaus.jackson.map.introspect.BasicBeanDescription;
import org.codehaus.jackson.map.type.TypeBindings;
import org.codehaus.jackson.map.util.ClassUtil;
import org.codehaus.jackson.type.JavaType;

// Referenced classes of package org.codehaus.jackson.map.deser:
//            Creator

static final class d.getParameterType
{

    protected final AnnotatedMember _creator;
    protected final Constructor _ctor;
    protected JsonDeserializer _deserializer;
    protected final Method _factoryMethod;
    protected final JavaType _valueType;

    public Object deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext)
        throws IOException, JsonProcessingException
    {
        Object obj = _deserializer.deserialize(jsonparser, deserializationcontext);
        Object obj1;
        try
        {
            if (_ctor != null)
            {
                return _ctor.newInstance(new Object[] {
                    obj
                });
            }
            obj1 = _factoryMethod.invoke(null, new Object[] {
                obj
            });
        }
        catch (Exception exception)
        {
            ClassUtil.unwrapAndThrowAsIAE(exception);
            return null;
        }
        return obj1;
    }

    public AnnotatedMember getCreator()
    {
        return _creator;
    }

    public JavaType getValueType()
    {
        return _valueType;
    }

    public void setDeserializer(JsonDeserializer jsondeserializer)
    {
        _deserializer = jsondeserializer;
    }

    public d(BasicBeanDescription basicbeandescription, AnnotatedConstructor annotatedconstructor, AnnotatedMethod annotatedmethod)
    {
        TypeBindings typebindings = basicbeandescription.bindingsForBeanType();
        if (annotatedconstructor != null)
        {
            _creator = annotatedconstructor;
            _ctor = annotatedconstructor.getAnnotated();
            _factoryMethod = null;
            _valueType = typebindings.resolveType(annotatedconstructor.getParameterType(0));
            return;
        }
        if (annotatedmethod != null)
        {
            _creator = annotatedmethod;
            _ctor = null;
            _factoryMethod = annotatedmethod.getAnnotated();
            _valueType = typebindings.resolveType(annotatedmethod.getParameterType(0));
            return;
        } else
        {
            throw new IllegalArgumentException("Internal error: neither delegating constructor nor factory method passed");
        }
    }
}

// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.map.ser;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import org.codehaus.jackson.map.AnnotationIntrospector;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.TypeSerializer;
import org.codehaus.jackson.map.introspect.Annotated;
import org.codehaus.jackson.map.introspect.AnnotatedClass;
import org.codehaus.jackson.map.introspect.AnnotatedField;
import org.codehaus.jackson.map.introspect.AnnotatedMember;
import org.codehaus.jackson.map.introspect.AnnotatedMethod;
import org.codehaus.jackson.map.introspect.BasicBeanDescription;
import org.codehaus.jackson.map.util.Annotations;
import org.codehaus.jackson.type.JavaType;

// Referenced classes of package org.codehaus.jackson.map.ser:
//            BeanPropertyWriter, BeanSerializerFactory

public class PropertyBuilder
{

    protected final AnnotationIntrospector _annotationIntrospector;
    protected final BasicBeanDescription _beanDesc;
    protected final SerializationConfig _config;
    protected Object _defaultBean;
    protected final org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion _outputProps;

    public PropertyBuilder(SerializationConfig serializationconfig, BasicBeanDescription basicbeandescription)
    {
        _config = serializationconfig;
        _beanDesc = basicbeandescription;
        _outputProps = basicbeandescription.findSerializationInclusion(serializationconfig.getSerializationInclusion());
        _annotationIntrospector = _config.getAnnotationIntrospector();
    }

    protected Object _throwWrapped(Exception exception, String s, Object obj)
    {
        Object obj1;
        for (obj1 = exception; ((Throwable) (obj1)).getCause() != null; obj1 = ((Throwable) (obj1)).getCause()) { }
        if (obj1 instanceof Error)
        {
            throw (Error)obj1;
        }
        if (obj1 instanceof RuntimeException)
        {
            throw (RuntimeException)obj1;
        } else
        {
            throw new IllegalArgumentException((new StringBuilder()).append("Failed to get property '").append(s).append("' of default ").append(obj.getClass().getName()).append(" instance").toString());
        }
    }

    protected BeanPropertyWriter buildWriter(String s, JavaType javatype, JsonSerializer jsonserializer, TypeSerializer typeserializer, TypeSerializer typeserializer1, AnnotatedMember annotatedmember, boolean flag)
    {
        Method method;
        Field field;
        JavaType javatype1;
        org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion inclusion;
        boolean flag1;
        Object obj;
        if (annotatedmember instanceof AnnotatedField)
        {
            method = null;
            field = ((AnnotatedField)annotatedmember).getAnnotated();
        } else
        {
            method = ((AnnotatedMethod)annotatedmember).getAnnotated();
            field = null;
        }
        javatype1 = findSerializationType(annotatedmember, flag, javatype);
        if (typeserializer1 != null)
        {
            if (javatype1 == null)
            {
                javatype1 = javatype;
            }
            if (javatype1.getContentType() == null)
            {
                throw new IllegalStateException((new StringBuilder()).append("Problem trying to create BeanPropertyWriter for property '").append(s).append("' (of type ").append(_beanDesc.getType()).append("); serialization type ").append(javatype1).append(" has no content").toString());
            }
            javatype1 = javatype1.withContentTypeHandler(typeserializer1);
            javatype1.getContentType();
        }
        inclusion = _annotationIntrospector.findSerializationInclusion(annotatedmember, _outputProps);
        flag1 = false;
        obj = null;
        if (inclusion == null) goto _L2; else goto _L1
_L1:
        int i;
        static class _cls1
        {

            static final int $SwitchMap$org$codehaus$jackson$map$annotate$JsonSerialize$Inclusion[];

            static 
            {
                $SwitchMap$org$codehaus$jackson$map$annotate$JsonSerialize$Inclusion = new int[org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion.values().length];
                try
                {
                    $SwitchMap$org$codehaus$jackson$map$annotate$JsonSerialize$Inclusion[org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion.NON_DEFAULT.ordinal()] = 1;
                }
                catch (NoSuchFieldError nosuchfielderror) { }
                try
                {
                    $SwitchMap$org$codehaus$jackson$map$annotate$JsonSerialize$Inclusion[org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion.NON_NULL.ordinal()] = 2;
                }
                catch (NoSuchFieldError nosuchfielderror1)
                {
                    return;
                }
            }
        }

        i = _cls1..SwitchMap.org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion[inclusion.ordinal()];
        flag1 = false;
        obj = null;
        i;
        JVM INSTR tableswitch 1 2: default 208
    //                   1 240
    //                   2 265;
           goto _L2 _L3 _L4
_L2:
        return new BeanPropertyWriter(annotatedmember, _beanDesc.getClassAnnotations(), s, javatype, jsonserializer, typeserializer, javatype1, method, field, flag1, obj);
_L3:
        obj = getDefaultValue(s, method, field);
        flag1 = false;
        if (obj == null)
        {
            flag1 = true;
        }
        continue; /* Loop/switch isn't completed */
_L4:
        flag1 = true;
        obj = null;
        if (true) goto _L2; else goto _L5
_L5:
    }

    protected JavaType findSerializationType(Annotated annotated, boolean flag, JavaType javatype)
    {
        Class class1 = _annotationIntrospector.findSerializationType(annotated);
        if (class1 != null)
        {
            Class class2 = javatype.getRawClass();
            JavaType javatype1;
            org.codehaus.jackson.map.annotate.JsonSerialize.Typing typing;
            if (class1.isAssignableFrom(class2))
            {
                javatype = javatype.widenBy(class1);
            } else
            {
                if (!class2.isAssignableFrom(class1))
                {
                    throw new IllegalArgumentException((new StringBuilder()).append("Illegal concrete-type annotation for method '").append(annotated.getName()).append("': class ").append(class1.getName()).append(" not a super-type of (declared) class ").append(class2.getName()).toString());
                }
                javatype = javatype.forcedNarrowBy(class1);
            }
            flag = true;
        }
        javatype1 = BeanSerializerFactory.modifySecondaryTypesByAnnotation(_config, annotated, javatype);
        if (javatype1 != javatype)
        {
            flag = true;
            javatype = javatype1;
        }
        if (!flag)
        {
            typing = _annotationIntrospector.findSerializationTyping(annotated);
            if (typing != null)
            {
                if (typing == org.codehaus.jackson.map.annotate.JsonSerialize.Typing.STATIC)
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
            }
        }
        if (flag)
        {
            return javatype;
        } else
        {
            return null;
        }
    }

    public Annotations getClassAnnotations()
    {
        return _beanDesc.getClassAnnotations();
    }

    protected Object getDefaultBean()
    {
        if (_defaultBean == null)
        {
            _defaultBean = _beanDesc.instantiateBean(_config.isEnabled(org.codehaus.jackson.map.SerializationConfig.Feature.CAN_OVERRIDE_ACCESS_MODIFIERS));
            if (_defaultBean == null)
            {
                Class class1 = _beanDesc.getClassInfo().getAnnotated();
                throw new IllegalArgumentException((new StringBuilder()).append("Class ").append(class1.getName()).append(" has no default constructor; can not instantiate default bean value to support 'properties=JsonSerialize.Inclusion.NON_DEFAULT' annotation").toString());
            }
        }
        return _defaultBean;
    }

    protected Object getDefaultValue(String s, Method method, Field field)
    {
        Object obj;
        obj = getDefaultBean();
        if (method == null)
        {
            break MISSING_BLOCK_LABEL_21;
        }
        return method.invoke(obj, new Object[0]);
        Object obj1 = field.get(obj);
        return obj1;
        Exception exception;
        exception;
        return _throwWrapped(exception, s, obj);
    }
}

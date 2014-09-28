// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.map.introspect;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Collection;
import java.util.Map;
import org.codehaus.jackson.map.BeanDescription;
import org.codehaus.jackson.map.ClassIntrospector;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.MapperConfig;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.util.ClassUtil;
import org.codehaus.jackson.type.JavaType;

// Referenced classes of package org.codehaus.jackson.map.introspect:
//            AnnotatedClass, BasicBeanDescription, MethodFilter

public class BasicClassIntrospector extends ClassIntrospector
{
    public static class GetterMethodFilter
        implements MethodFilter
    {

        public boolean includeMethod(Method method)
        {
            return ClassUtil.hasGetterSignature(method);
        }

        private GetterMethodFilter()
        {
        }

    }

    public static final class SetterAndGetterMethodFilter extends SetterMethodFilter
    {

        public boolean includeMethod(Method method)
        {
            if (!super.includeMethod(method))
            {
                if (!ClassUtil.hasGetterSignature(method))
                {
                    return false;
                }
                Class class1 = method.getReturnType();
                if (!java/util/Collection.isAssignableFrom(class1) && !java/util/Map.isAssignableFrom(class1))
                {
                    return false;
                }
            }
            return true;
        }

        public SetterAndGetterMethodFilter()
        {
        }
    }

    public static class SetterMethodFilter
        implements MethodFilter
    {

        public boolean includeMethod(Method method)
        {
            if (Modifier.isStatic(method.getModifiers()))
            {
                return false;
            }
            switch (method.getParameterTypes().length)
            {
            default:
                return false;

            case 1: // '\001'
                return true;

            case 2: // '\002'
                return true;
            }
        }

        public SetterMethodFilter()
        {
        }
    }


    public static final GetterMethodFilter DEFAULT_GETTER_FILTER = new GetterMethodFilter();
    public static final SetterAndGetterMethodFilter DEFAULT_SETTER_AND_GETTER_FILTER = new SetterAndGetterMethodFilter();
    public static final SetterMethodFilter DEFAULT_SETTER_FILTER = new SetterMethodFilter();
    public static final BasicClassIntrospector instance = new BasicClassIntrospector();

    public BasicClassIntrospector()
    {
    }

    public volatile BeanDescription forClassAnnotations(MapperConfig mapperconfig, Class class1, org.codehaus.jackson.map.ClassIntrospector.MixInResolver mixinresolver)
    {
        return forClassAnnotations(mapperconfig, class1, mixinresolver);
    }

    public BasicBeanDescription forClassAnnotations(MapperConfig mapperconfig, Class class1, org.codehaus.jackson.map.ClassIntrospector.MixInResolver mixinresolver)
    {
        boolean flag = mapperconfig.isAnnotationProcessingEnabled();
        org.codehaus.jackson.map.AnnotationIntrospector annotationintrospector = mapperconfig.getAnnotationIntrospector();
        AnnotatedClass annotatedclass;
        if (!flag)
        {
            annotationintrospector = null;
        }
        annotatedclass = AnnotatedClass.construct(class1, annotationintrospector, mixinresolver);
        return new BasicBeanDescription(mapperconfig, mapperconfig.constructType(class1), annotatedclass);
    }

    public volatile BeanDescription forCreation(DeserializationConfig deserializationconfig, JavaType javatype, org.codehaus.jackson.map.ClassIntrospector.MixInResolver mixinresolver)
    {
        return forCreation(deserializationconfig, javatype, mixinresolver);
    }

    public BasicBeanDescription forCreation(DeserializationConfig deserializationconfig, JavaType javatype, org.codehaus.jackson.map.ClassIntrospector.MixInResolver mixinresolver)
    {
        boolean flag = deserializationconfig.isAnnotationProcessingEnabled();
        org.codehaus.jackson.map.AnnotationIntrospector annotationintrospector = deserializationconfig.getAnnotationIntrospector();
        Class class1 = javatype.getRawClass();
        AnnotatedClass annotatedclass;
        if (!flag)
        {
            annotationintrospector = null;
        }
        annotatedclass = AnnotatedClass.construct(class1, annotationintrospector, mixinresolver);
        annotatedclass.resolveCreators(true);
        return new BasicBeanDescription(deserializationconfig, javatype, annotatedclass);
    }

    public volatile BeanDescription forDeserialization(DeserializationConfig deserializationconfig, JavaType javatype, org.codehaus.jackson.map.ClassIntrospector.MixInResolver mixinresolver)
    {
        return forDeserialization(deserializationconfig, javatype, mixinresolver);
    }

    public BasicBeanDescription forDeserialization(DeserializationConfig deserializationconfig, JavaType javatype, org.codehaus.jackson.map.ClassIntrospector.MixInResolver mixinresolver)
    {
        boolean flag = deserializationconfig.isAnnotationProcessingEnabled();
        org.codehaus.jackson.map.AnnotationIntrospector annotationintrospector = deserializationconfig.getAnnotationIntrospector();
        Class class1 = javatype.getRawClass();
        AnnotatedClass annotatedclass;
        if (!flag)
        {
            annotationintrospector = null;
        }
        annotatedclass = AnnotatedClass.construct(class1, annotationintrospector, mixinresolver);
        annotatedclass.resolveMemberMethods(getDeserializationMethodFilter(deserializationconfig), true);
        annotatedclass.resolveCreators(true);
        annotatedclass.resolveFields(true);
        return new BasicBeanDescription(deserializationconfig, javatype, annotatedclass);
    }

    public volatile BeanDescription forDirectClassAnnotations(MapperConfig mapperconfig, Class class1, org.codehaus.jackson.map.ClassIntrospector.MixInResolver mixinresolver)
    {
        return forDirectClassAnnotations(mapperconfig, class1, mixinresolver);
    }

    public BasicBeanDescription forDirectClassAnnotations(MapperConfig mapperconfig, Class class1, org.codehaus.jackson.map.ClassIntrospector.MixInResolver mixinresolver)
    {
        boolean flag = mapperconfig.isAnnotationProcessingEnabled();
        org.codehaus.jackson.map.AnnotationIntrospector annotationintrospector = mapperconfig.getAnnotationIntrospector();
        AnnotatedClass annotatedclass;
        if (!flag)
        {
            annotationintrospector = null;
        }
        annotatedclass = AnnotatedClass.constructWithoutSuperTypes(class1, annotationintrospector, mixinresolver);
        return new BasicBeanDescription(mapperconfig, mapperconfig.constructType(class1), annotatedclass);
    }

    public volatile BeanDescription forSerialization(SerializationConfig serializationconfig, JavaType javatype, org.codehaus.jackson.map.ClassIntrospector.MixInResolver mixinresolver)
    {
        return forSerialization(serializationconfig, javatype, mixinresolver);
    }

    public BasicBeanDescription forSerialization(SerializationConfig serializationconfig, JavaType javatype, org.codehaus.jackson.map.ClassIntrospector.MixInResolver mixinresolver)
    {
        org.codehaus.jackson.map.AnnotationIntrospector annotationintrospector = serializationconfig.getAnnotationIntrospector();
        AnnotatedClass annotatedclass = AnnotatedClass.construct(javatype.getRawClass(), annotationintrospector, mixinresolver);
        annotatedclass.resolveMemberMethods(getSerializationMethodFilter(serializationconfig), false);
        annotatedclass.resolveCreators(true);
        annotatedclass.resolveFields(false);
        return new BasicBeanDescription(serializationconfig, javatype, annotatedclass);
    }

    protected MethodFilter getDeserializationMethodFilter(DeserializationConfig deserializationconfig)
    {
        if (deserializationconfig.isEnabled(org.codehaus.jackson.map.DeserializationConfig.Feature.USE_GETTERS_AS_SETTERS))
        {
            return DEFAULT_SETTER_AND_GETTER_FILTER;
        } else
        {
            return DEFAULT_SETTER_FILTER;
        }
    }

    protected MethodFilter getSerializationMethodFilter(SerializationConfig serializationconfig)
    {
        return DEFAULT_GETTER_FILTER;
    }

}

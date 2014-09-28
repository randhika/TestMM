// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.map;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import org.codehaus.jackson.map.introspect.Annotated;
import org.codehaus.jackson.map.introspect.AnnotatedClass;
import org.codehaus.jackson.map.introspect.AnnotatedConstructor;
import org.codehaus.jackson.map.introspect.AnnotatedField;
import org.codehaus.jackson.map.introspect.AnnotatedMember;
import org.codehaus.jackson.map.introspect.AnnotatedMethod;
import org.codehaus.jackson.map.introspect.AnnotatedParameter;
import org.codehaus.jackson.map.introspect.NopAnnotationIntrospector;
import org.codehaus.jackson.map.introspect.VisibilityChecker;
import org.codehaus.jackson.map.jsontype.TypeResolverBuilder;
import org.codehaus.jackson.type.JavaType;

// Referenced classes of package org.codehaus.jackson.map:
//            BeanProperty, MapperConfig

public abstract class AnnotationIntrospector
{
    public static class Pair extends AnnotationIntrospector
    {

        protected final AnnotationIntrospector _primary;
        protected final AnnotationIntrospector _secondary;

        public static AnnotationIntrospector create(AnnotationIntrospector annotationintrospector, AnnotationIntrospector annotationintrospector1)
        {
            if (annotationintrospector == null)
            {
                return annotationintrospector1;
            }
            if (annotationintrospector1 == null)
            {
                return annotationintrospector;
            } else
            {
                return new Pair(annotationintrospector, annotationintrospector1);
            }
        }

        public Collection allIntrospectors()
        {
            return allIntrospectors(((Collection) (new ArrayList())));
        }

        public Collection allIntrospectors(Collection collection)
        {
            _primary.allIntrospectors(collection);
            _secondary.allIntrospectors(collection);
            return collection;
        }

        public VisibilityChecker findAutoDetectVisibility(AnnotatedClass annotatedclass, VisibilityChecker visibilitychecker)
        {
            VisibilityChecker visibilitychecker1 = _secondary.findAutoDetectVisibility(annotatedclass, visibilitychecker);
            return _primary.findAutoDetectVisibility(annotatedclass, visibilitychecker1);
        }

        public Boolean findCachability(AnnotatedClass annotatedclass)
        {
            Boolean boolean1 = _primary.findCachability(annotatedclass);
            if (boolean1 == null)
            {
                boolean1 = _secondary.findCachability(annotatedclass);
            }
            return boolean1;
        }

        public Class findContentDeserializer(Annotated annotated)
        {
            Class class1 = _primary.findContentDeserializer(annotated);
            if (class1 == null || class1 == org/codehaus/jackson/map/JsonDeserializer$None)
            {
                class1 = _secondary.findContentDeserializer(annotated);
            }
            return class1;
        }

        public Class findContentSerializer(Annotated annotated)
        {
            Class class1 = _primary.findContentSerializer(annotated);
            if (class1 == null || class1 == org/codehaus/jackson/map/JsonSerializer$None)
            {
                class1 = _secondary.findContentSerializer(annotated);
            }
            return class1;
        }

        public String findDeserializablePropertyName(AnnotatedField annotatedfield)
        {
            String s = _primary.findDeserializablePropertyName(annotatedfield);
            if (s == null)
            {
                s = _secondary.findDeserializablePropertyName(annotatedfield);
            } else
            if (s.length() == 0)
            {
                String s1 = _secondary.findDeserializablePropertyName(annotatedfield);
                if (s1 != null)
                {
                    return s1;
                }
            }
            return s;
        }

        public Class findDeserializationContentType(Annotated annotated, JavaType javatype, String s)
        {
            Class class1 = _primary.findDeserializationContentType(annotated, javatype, s);
            if (class1 == null)
            {
                class1 = _secondary.findDeserializationContentType(annotated, javatype, s);
            }
            return class1;
        }

        public Class findDeserializationKeyType(Annotated annotated, JavaType javatype, String s)
        {
            Class class1 = _primary.findDeserializationKeyType(annotated, javatype, s);
            if (class1 == null)
            {
                class1 = _secondary.findDeserializationKeyType(annotated, javatype, s);
            }
            return class1;
        }

        public Class findDeserializationType(Annotated annotated, JavaType javatype, String s)
        {
            Class class1 = _primary.findDeserializationType(annotated, javatype, s);
            if (class1 == null)
            {
                class1 = _secondary.findDeserializationType(annotated, javatype, s);
            }
            return class1;
        }

        public Object findDeserializer(Annotated annotated)
        {
            Object obj = _primary.findDeserializer(annotated);
            if (obj == null)
            {
                obj = _secondary.findDeserializer(annotated);
            }
            return obj;
        }

        public Object findDeserializer(Annotated annotated, BeanProperty beanproperty)
        {
            Object obj = _primary.findDeserializer(annotated, beanproperty);
            if (obj == null)
            {
                obj = _secondary.findDeserializer(annotated, beanproperty);
            }
            return obj;
        }

        public String findEnumValue(Enum enum)
        {
            String s = _primary.findEnumValue(enum);
            if (s == null)
            {
                s = _secondary.findEnumValue(enum);
            }
            return s;
        }

        public Object findFilterId(AnnotatedClass annotatedclass)
        {
            Object obj = _primary.findFilterId(annotatedclass);
            if (obj == null)
            {
                obj = _secondary.findFilterId(annotatedclass);
            }
            return obj;
        }

        public String findGettablePropertyName(AnnotatedMethod annotatedmethod)
        {
            String s = _primary.findGettablePropertyName(annotatedmethod);
            if (s == null)
            {
                s = _secondary.findGettablePropertyName(annotatedmethod);
            } else
            if (s.length() == 0)
            {
                String s1 = _secondary.findGettablePropertyName(annotatedmethod);
                if (s1 != null)
                {
                    return s1;
                }
            }
            return s;
        }

        public Boolean findIgnoreUnknownProperties(AnnotatedClass annotatedclass)
        {
            Boolean boolean1 = _primary.findIgnoreUnknownProperties(annotatedclass);
            if (boolean1 == null)
            {
                boolean1 = _secondary.findIgnoreUnknownProperties(annotatedclass);
            }
            return boolean1;
        }

        public Class findKeyDeserializer(Annotated annotated)
        {
            Class class1 = _primary.findKeyDeserializer(annotated);
            if (class1 == null || class1 == org/codehaus/jackson/map/KeyDeserializer$None)
            {
                class1 = _secondary.findKeyDeserializer(annotated);
            }
            return class1;
        }

        public Class findKeySerializer(Annotated annotated)
        {
            Class class1 = _primary.findKeySerializer(annotated);
            if (class1 == null || class1 == org/codehaus/jackson/map/JsonSerializer$None)
            {
                class1 = _secondary.findKeySerializer(annotated);
            }
            return class1;
        }

        public String[] findPropertiesToIgnore(AnnotatedClass annotatedclass)
        {
            String as[] = _primary.findPropertiesToIgnore(annotatedclass);
            if (as == null)
            {
                as = _secondary.findPropertiesToIgnore(annotatedclass);
            }
            return as;
        }

        public TypeResolverBuilder findPropertyContentTypeResolver(MapperConfig mapperconfig, AnnotatedMember annotatedmember, JavaType javatype)
        {
            TypeResolverBuilder typeresolverbuilder = _primary.findPropertyContentTypeResolver(mapperconfig, annotatedmember, javatype);
            if (typeresolverbuilder == null)
            {
                typeresolverbuilder = _secondary.findPropertyContentTypeResolver(mapperconfig, annotatedmember, javatype);
            }
            return typeresolverbuilder;
        }

        public String findPropertyNameForParam(AnnotatedParameter annotatedparameter)
        {
            String s = _primary.findPropertyNameForParam(annotatedparameter);
            if (s == null)
            {
                s = _secondary.findPropertyNameForParam(annotatedparameter);
            }
            return s;
        }

        public TypeResolverBuilder findPropertyTypeResolver(MapperConfig mapperconfig, AnnotatedMember annotatedmember, JavaType javatype)
        {
            TypeResolverBuilder typeresolverbuilder = _primary.findPropertyTypeResolver(mapperconfig, annotatedmember, javatype);
            if (typeresolverbuilder == null)
            {
                typeresolverbuilder = _secondary.findPropertyTypeResolver(mapperconfig, annotatedmember, javatype);
            }
            return typeresolverbuilder;
        }

        public ReferenceProperty findReferenceType(AnnotatedMember annotatedmember)
        {
            ReferenceProperty referenceproperty = _primary.findReferenceType(annotatedmember);
            if (referenceproperty == null)
            {
                referenceproperty = _secondary.findReferenceType(annotatedmember);
            }
            return referenceproperty;
        }

        public String findRootName(AnnotatedClass annotatedclass)
        {
            String s = _primary.findRootName(annotatedclass);
            if (s == null)
            {
                s = _secondary.findRootName(annotatedclass);
            } else
            if (s.length() <= 0)
            {
                String s1 = _secondary.findRootName(annotatedclass);
                if (s1 != null)
                {
                    return s1;
                }
            }
            return s;
        }

        public String findSerializablePropertyName(AnnotatedField annotatedfield)
        {
            String s = _primary.findSerializablePropertyName(annotatedfield);
            if (s == null)
            {
                s = _secondary.findSerializablePropertyName(annotatedfield);
            } else
            if (s.length() == 0)
            {
                String s1 = _secondary.findSerializablePropertyName(annotatedfield);
                if (s1 != null)
                {
                    return s1;
                }
            }
            return s;
        }

        public Class findSerializationContentType(Annotated annotated, JavaType javatype)
        {
            Class class1 = _primary.findSerializationContentType(annotated, javatype);
            if (class1 == null)
            {
                class1 = _secondary.findSerializationContentType(annotated, javatype);
            }
            return class1;
        }

        public org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion findSerializationInclusion(Annotated annotated, org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion inclusion)
        {
            org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion inclusion1 = _secondary.findSerializationInclusion(annotated, inclusion);
            return _primary.findSerializationInclusion(annotated, inclusion1);
        }

        public Class findSerializationKeyType(Annotated annotated, JavaType javatype)
        {
            Class class1 = _primary.findSerializationKeyType(annotated, javatype);
            if (class1 == null)
            {
                class1 = _secondary.findSerializationKeyType(annotated, javatype);
            }
            return class1;
        }

        public String[] findSerializationPropertyOrder(AnnotatedClass annotatedclass)
        {
            String as[] = _primary.findSerializationPropertyOrder(annotatedclass);
            if (as == null)
            {
                as = _secondary.findSerializationPropertyOrder(annotatedclass);
            }
            return as;
        }

        public Boolean findSerializationSortAlphabetically(AnnotatedClass annotatedclass)
        {
            Boolean boolean1 = _primary.findSerializationSortAlphabetically(annotatedclass);
            if (boolean1 == null)
            {
                boolean1 = _secondary.findSerializationSortAlphabetically(annotatedclass);
            }
            return boolean1;
        }

        public Class findSerializationType(Annotated annotated)
        {
            Class class1 = _primary.findSerializationType(annotated);
            if (class1 == null)
            {
                class1 = _secondary.findSerializationType(annotated);
            }
            return class1;
        }

        public org.codehaus.jackson.map.annotate.JsonSerialize.Typing findSerializationTyping(Annotated annotated)
        {
            org.codehaus.jackson.map.annotate.JsonSerialize.Typing typing = _primary.findSerializationTyping(annotated);
            if (typing == null)
            {
                typing = _secondary.findSerializationTyping(annotated);
            }
            return typing;
        }

        public Class[] findSerializationViews(Annotated annotated)
        {
            Class aclass[] = _primary.findSerializationViews(annotated);
            if (aclass == null)
            {
                aclass = _secondary.findSerializationViews(annotated);
            }
            return aclass;
        }

        public Object findSerializer(Annotated annotated)
        {
            Object obj = _primary.findSerializer(annotated);
            if (obj == null)
            {
                obj = _secondary.findSerializer(annotated);
            }
            return obj;
        }

        public Object findSerializer(Annotated annotated, BeanProperty beanproperty)
        {
            Object obj = _primary.findSerializer(annotated, beanproperty);
            if (obj == null)
            {
                obj = _secondary.findSerializer(annotated, beanproperty);
            }
            return obj;
        }

        public String findSettablePropertyName(AnnotatedMethod annotatedmethod)
        {
            String s = _primary.findSettablePropertyName(annotatedmethod);
            if (s == null)
            {
                s = _secondary.findSettablePropertyName(annotatedmethod);
            } else
            if (s.length() == 0)
            {
                String s1 = _secondary.findSettablePropertyName(annotatedmethod);
                if (s1 != null)
                {
                    return s1;
                }
            }
            return s;
        }

        public List findSubtypes(Annotated annotated)
        {
            List list = _primary.findSubtypes(annotated);
            List list1 = _secondary.findSubtypes(annotated);
            if (list == null || list.isEmpty())
            {
                return list1;
            }
            if (list1 == null || list1.isEmpty())
            {
                return list;
            } else
            {
                ArrayList arraylist = new ArrayList(list.size() + list1.size());
                arraylist.addAll(list);
                arraylist.addAll(list1);
                return arraylist;
            }
        }

        public String findTypeName(AnnotatedClass annotatedclass)
        {
            String s = _primary.findTypeName(annotatedclass);
            if (s == null || s.length() == 0)
            {
                s = _secondary.findTypeName(annotatedclass);
            }
            return s;
        }

        public TypeResolverBuilder findTypeResolver(MapperConfig mapperconfig, AnnotatedClass annotatedclass, JavaType javatype)
        {
            TypeResolverBuilder typeresolverbuilder = _primary.findTypeResolver(mapperconfig, annotatedclass, javatype);
            if (typeresolverbuilder == null)
            {
                typeresolverbuilder = _secondary.findTypeResolver(mapperconfig, annotatedclass, javatype);
            }
            return typeresolverbuilder;
        }

        public boolean hasAnyGetterAnnotation(AnnotatedMethod annotatedmethod)
        {
            return _primary.hasAnyGetterAnnotation(annotatedmethod) || _secondary.hasAnyGetterAnnotation(annotatedmethod);
        }

        public boolean hasAnySetterAnnotation(AnnotatedMethod annotatedmethod)
        {
            return _primary.hasAnySetterAnnotation(annotatedmethod) || _secondary.hasAnySetterAnnotation(annotatedmethod);
        }

        public boolean hasAsValueAnnotation(AnnotatedMethod annotatedmethod)
        {
            return _primary.hasAsValueAnnotation(annotatedmethod) || _secondary.hasAsValueAnnotation(annotatedmethod);
        }

        public boolean hasCreatorAnnotation(Annotated annotated)
        {
            return _primary.hasCreatorAnnotation(annotated) || _secondary.hasCreatorAnnotation(annotated);
        }

        public boolean isHandled(Annotation annotation)
        {
            return _primary.isHandled(annotation) || _secondary.isHandled(annotation);
        }

        public boolean isIgnorableConstructor(AnnotatedConstructor annotatedconstructor)
        {
            return _primary.isIgnorableConstructor(annotatedconstructor) || _secondary.isIgnorableConstructor(annotatedconstructor);
        }

        public boolean isIgnorableField(AnnotatedField annotatedfield)
        {
            return _primary.isIgnorableField(annotatedfield) || _secondary.isIgnorableField(annotatedfield);
        }

        public boolean isIgnorableMethod(AnnotatedMethod annotatedmethod)
        {
            return _primary.isIgnorableMethod(annotatedmethod) || _secondary.isIgnorableMethod(annotatedmethod);
        }

        public Boolean isIgnorableType(AnnotatedClass annotatedclass)
        {
            Boolean boolean1 = _primary.isIgnorableType(annotatedclass);
            if (boolean1 == null)
            {
                boolean1 = _secondary.isIgnorableType(annotatedclass);
            }
            return boolean1;
        }

        public Pair(AnnotationIntrospector annotationintrospector, AnnotationIntrospector annotationintrospector1)
        {
            _primary = annotationintrospector;
            _secondary = annotationintrospector1;
        }
    }

    public static class ReferenceProperty
    {

        private final String _name;
        private final Type _type;

        public static ReferenceProperty back(String s)
        {
            return new ReferenceProperty(Type.BACK_REFERENCE, s);
        }

        public static ReferenceProperty managed(String s)
        {
            return new ReferenceProperty(Type.MANAGED_REFERENCE, s);
        }

        public String getName()
        {
            return _name;
        }

        public Type getType()
        {
            return _type;
        }

        public boolean isBackReference()
        {
            return _type == Type.BACK_REFERENCE;
        }

        public boolean isManagedReference()
        {
            return _type == Type.MANAGED_REFERENCE;
        }

        public ReferenceProperty(Type type, String s)
        {
            _type = type;
            _name = s;
        }
    }

    public static final class ReferenceProperty.Type extends Enum
    {

        private static final ReferenceProperty.Type $VALUES[];
        public static final ReferenceProperty.Type BACK_REFERENCE;
        public static final ReferenceProperty.Type MANAGED_REFERENCE;

        public static ReferenceProperty.Type valueOf(String s)
        {
            return (ReferenceProperty.Type)Enum.valueOf(org/codehaus/jackson/map/AnnotationIntrospector$ReferenceProperty$Type, s);
        }

        public static ReferenceProperty.Type[] values()
        {
            return (ReferenceProperty.Type[])$VALUES.clone();
        }

        static 
        {
            MANAGED_REFERENCE = new ReferenceProperty.Type("MANAGED_REFERENCE", 0);
            BACK_REFERENCE = new ReferenceProperty.Type("BACK_REFERENCE", 1);
            ReferenceProperty.Type atype[] = new ReferenceProperty.Type[2];
            atype[0] = MANAGED_REFERENCE;
            atype[1] = BACK_REFERENCE;
            $VALUES = atype;
        }

        private ReferenceProperty.Type(String s, int i)
        {
            super(s, i);
        }
    }


    public AnnotationIntrospector()
    {
    }

    public static AnnotationIntrospector nopInstance()
    {
        return NopAnnotationIntrospector.instance;
    }

    public static AnnotationIntrospector pair(AnnotationIntrospector annotationintrospector, AnnotationIntrospector annotationintrospector1)
    {
        return new Pair(annotationintrospector, annotationintrospector1);
    }

    public Collection allIntrospectors()
    {
        return Collections.singletonList(this);
    }

    public Collection allIntrospectors(Collection collection)
    {
        collection.add(this);
        return collection;
    }

    public VisibilityChecker findAutoDetectVisibility(AnnotatedClass annotatedclass, VisibilityChecker visibilitychecker)
    {
        return visibilitychecker;
    }

    public abstract Boolean findCachability(AnnotatedClass annotatedclass);

    public abstract Class findContentDeserializer(Annotated annotated);

    public Class findContentSerializer(Annotated annotated)
    {
        return null;
    }

    public abstract String findDeserializablePropertyName(AnnotatedField annotatedfield);

    public abstract Class findDeserializationContentType(Annotated annotated, JavaType javatype, String s);

    public abstract Class findDeserializationKeyType(Annotated annotated, JavaType javatype, String s);

    public abstract Class findDeserializationType(Annotated annotated, JavaType javatype, String s);

    public Object findDeserializer(Annotated annotated)
    {
        return findDeserializer(annotated, null);
    }

    public Object findDeserializer(Annotated annotated, BeanProperty beanproperty)
    {
        if (beanproperty != null)
        {
            return findDeserializer(annotated);
        } else
        {
            return null;
        }
    }

    public abstract String findEnumValue(Enum enum);

    public Object findFilterId(AnnotatedClass annotatedclass)
    {
        return null;
    }

    public abstract String findGettablePropertyName(AnnotatedMethod annotatedmethod);

    public abstract Boolean findIgnoreUnknownProperties(AnnotatedClass annotatedclass);

    public abstract Class findKeyDeserializer(Annotated annotated);

    public Class findKeySerializer(Annotated annotated)
    {
        return null;
    }

    public abstract String[] findPropertiesToIgnore(AnnotatedClass annotatedclass);

    public TypeResolverBuilder findPropertyContentTypeResolver(MapperConfig mapperconfig, AnnotatedMember annotatedmember, JavaType javatype)
    {
        return null;
    }

    public abstract String findPropertyNameForParam(AnnotatedParameter annotatedparameter);

    public TypeResolverBuilder findPropertyTypeResolver(MapperConfig mapperconfig, AnnotatedMember annotatedmember, JavaType javatype)
    {
        return null;
    }

    public ReferenceProperty findReferenceType(AnnotatedMember annotatedmember)
    {
        return null;
    }

    public abstract String findRootName(AnnotatedClass annotatedclass);

    public abstract String findSerializablePropertyName(AnnotatedField annotatedfield);

    public Class findSerializationContentType(Annotated annotated, JavaType javatype)
    {
        return null;
    }

    public org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion findSerializationInclusion(Annotated annotated, org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion inclusion)
    {
        return inclusion;
    }

    public Class findSerializationKeyType(Annotated annotated, JavaType javatype)
    {
        return null;
    }

    public abstract String[] findSerializationPropertyOrder(AnnotatedClass annotatedclass);

    public abstract Boolean findSerializationSortAlphabetically(AnnotatedClass annotatedclass);

    public abstract Class findSerializationType(Annotated annotated);

    public abstract org.codehaus.jackson.map.annotate.JsonSerialize.Typing findSerializationTyping(Annotated annotated);

    public abstract Class[] findSerializationViews(Annotated annotated);

    public Object findSerializer(Annotated annotated)
    {
        return findSerializer(annotated, null);
    }

    public Object findSerializer(Annotated annotated, BeanProperty beanproperty)
    {
        if (beanproperty != null)
        {
            return findSerializer(annotated);
        } else
        {
            return null;
        }
    }

    public abstract String findSettablePropertyName(AnnotatedMethod annotatedmethod);

    public List findSubtypes(Annotated annotated)
    {
        return null;
    }

    public String findTypeName(AnnotatedClass annotatedclass)
    {
        return null;
    }

    public TypeResolverBuilder findTypeResolver(MapperConfig mapperconfig, AnnotatedClass annotatedclass, JavaType javatype)
    {
        return null;
    }

    public boolean hasAnyGetterAnnotation(AnnotatedMethod annotatedmethod)
    {
        return false;
    }

    public boolean hasAnySetterAnnotation(AnnotatedMethod annotatedmethod)
    {
        return false;
    }

    public abstract boolean hasAsValueAnnotation(AnnotatedMethod annotatedmethod);

    public boolean hasCreatorAnnotation(Annotated annotated)
    {
        return false;
    }

    public abstract boolean isHandled(Annotation annotation);

    public abstract boolean isIgnorableConstructor(AnnotatedConstructor annotatedconstructor);

    public abstract boolean isIgnorableField(AnnotatedField annotatedfield);

    public abstract boolean isIgnorableMethod(AnnotatedMethod annotatedmethod);

    public Boolean isIgnorableType(AnnotatedClass annotatedclass)
    {
        return null;
    }
}

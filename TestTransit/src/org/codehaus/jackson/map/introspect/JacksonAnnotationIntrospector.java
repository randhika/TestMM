// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.map.introspect;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;
import org.codehaus.jackson.annotate.JacksonAnnotation;
import org.codehaus.jackson.annotate.JsonAnyGetter;
import org.codehaus.jackson.annotate.JsonAnySetter;
import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonBackReference;
import org.codehaus.jackson.annotate.JsonClass;
import org.codehaus.jackson.annotate.JsonContentClass;
import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonGetter;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonIgnoreType;
import org.codehaus.jackson.annotate.JsonKeyClass;
import org.codehaus.jackson.annotate.JsonManagedReference;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonPropertyOrder;
import org.codehaus.jackson.annotate.JsonRawValue;
import org.codehaus.jackson.annotate.JsonSetter;
import org.codehaus.jackson.annotate.JsonSubTypes;
import org.codehaus.jackson.annotate.JsonTypeInfo;
import org.codehaus.jackson.annotate.JsonTypeName;
import org.codehaus.jackson.annotate.JsonValue;
import org.codehaus.jackson.annotate.JsonWriteNullProperties;
import org.codehaus.jackson.map.AnnotationIntrospector;
import org.codehaus.jackson.map.BeanProperty;
import org.codehaus.jackson.map.MapperConfig;
import org.codehaus.jackson.map.annotate.JsonCachable;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonFilter;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonTypeIdResolver;
import org.codehaus.jackson.map.annotate.JsonTypeResolver;
import org.codehaus.jackson.map.annotate.JsonView;
import org.codehaus.jackson.map.annotate.NoClass;
import org.codehaus.jackson.map.jsontype.NamedType;
import org.codehaus.jackson.map.jsontype.TypeIdResolver;
import org.codehaus.jackson.map.jsontype.TypeResolverBuilder;
import org.codehaus.jackson.map.jsontype.impl.StdTypeResolverBuilder;
import org.codehaus.jackson.map.ser.impl.RawSerializer;
import org.codehaus.jackson.type.JavaType;

// Referenced classes of package org.codehaus.jackson.map.introspect:
//            Annotated, AnnotatedClass, VisibilityChecker, AnnotatedField, 
//            AnnotatedMethod, AnnotatedParameter, AnnotatedMember, AnnotatedConstructor

public class JacksonAnnotationIntrospector extends AnnotationIntrospector
{

    public JacksonAnnotationIntrospector()
    {
    }

    protected StdTypeResolverBuilder _constructStdTypeResolverBuilder()
    {
        return new StdTypeResolverBuilder();
    }

    protected TypeResolverBuilder _findTypeResolver(MapperConfig mapperconfig, Annotated annotated, JavaType javatype)
    {
        JsonTypeInfo jsontypeinfo = (JsonTypeInfo)annotated.getAnnotation(org/codehaus/jackson/annotate/JsonTypeInfo);
        JsonTypeResolver jsontyperesolver = (JsonTypeResolver)annotated.getAnnotation(org/codehaus/jackson/map/annotate/JsonTypeResolver);
        Object obj;
        JsonTypeIdResolver jsontypeidresolver;
        TypeIdResolver typeidresolver;
        if (jsontyperesolver != null)
        {
            if (jsontypeinfo == null)
            {
                return null;
            }
            obj = mapperconfig.typeResolverBuilderInstance(annotated, jsontyperesolver.value());
        } else
        {
            if (jsontypeinfo == null || jsontypeinfo.use() == org.codehaus.jackson.annotate.JsonTypeInfo.Id.NONE)
            {
                return null;
            }
            obj = _constructStdTypeResolverBuilder();
        }
        jsontypeidresolver = (JsonTypeIdResolver)annotated.getAnnotation(org/codehaus/jackson/map/annotate/JsonTypeIdResolver);
        if (jsontypeidresolver == null)
        {
            typeidresolver = null;
        } else
        {
            typeidresolver = mapperconfig.typeIdResolverInstance(annotated, jsontypeidresolver.value());
        }
        if (typeidresolver != null)
        {
            typeidresolver.init(javatype);
        }
        return ((TypeResolverBuilder) (obj)).init(jsontypeinfo.use(), typeidresolver).inclusion(jsontypeinfo.include()).typeProperty(jsontypeinfo.property());
    }

    protected boolean _isIgnorable(Annotated annotated)
    {
        JsonIgnore jsonignore = (JsonIgnore)annotated.getAnnotation(org/codehaus/jackson/annotate/JsonIgnore);
        return jsonignore != null && jsonignore.value();
    }

    public VisibilityChecker findAutoDetectVisibility(AnnotatedClass annotatedclass, VisibilityChecker visibilitychecker)
    {
        JsonAutoDetect jsonautodetect = (JsonAutoDetect)annotatedclass.getAnnotation(org/codehaus/jackson/annotate/JsonAutoDetect);
        if (jsonautodetect == null)
        {
            return visibilitychecker;
        } else
        {
            return visibilitychecker.with(jsonautodetect);
        }
    }

    public Boolean findCachability(AnnotatedClass annotatedclass)
    {
        JsonCachable jsoncachable = (JsonCachable)annotatedclass.getAnnotation(org/codehaus/jackson/map/annotate/JsonCachable);
        if (jsoncachable == null)
        {
            return null;
        }
        if (jsoncachable.value())
        {
            return Boolean.TRUE;
        } else
        {
            return Boolean.FALSE;
        }
    }

    public Class findContentDeserializer(Annotated annotated)
    {
        JsonDeserialize jsondeserialize = (JsonDeserialize)annotated.getAnnotation(org/codehaus/jackson/map/annotate/JsonDeserialize);
        if (jsondeserialize != null)
        {
            Class class1 = jsondeserialize.contentUsing();
            if (class1 != org/codehaus/jackson/map/JsonDeserializer$None)
            {
                return class1;
            }
        }
        return null;
    }

    public Class findContentSerializer(Annotated annotated)
    {
        JsonSerialize jsonserialize = (JsonSerialize)annotated.getAnnotation(org/codehaus/jackson/map/annotate/JsonSerialize);
        if (jsonserialize != null)
        {
            Class class1 = jsonserialize.contentUsing();
            if (class1 != org/codehaus/jackson/map/JsonSerializer$None)
            {
                return class1;
            }
        }
        return null;
    }

    public String findDeserializablePropertyName(AnnotatedField annotatedfield)
    {
        JsonProperty jsonproperty = (JsonProperty)annotatedfield.getAnnotation(org/codehaus/jackson/annotate/JsonProperty);
        if (jsonproperty != null)
        {
            return jsonproperty.value();
        }
        if (annotatedfield.hasAnnotation(org/codehaus/jackson/map/annotate/JsonDeserialize) || annotatedfield.hasAnnotation(org/codehaus/jackson/map/annotate/JsonView))
        {
            return "";
        } else
        {
            return null;
        }
    }

    public Class findDeserializationContentType(Annotated annotated, JavaType javatype, String s)
    {
        JsonDeserialize jsondeserialize = (JsonDeserialize)annotated.getAnnotation(org/codehaus/jackson/map/annotate/JsonDeserialize);
        if (jsondeserialize == null) goto _L2; else goto _L1
_L1:
        Class class1 = jsondeserialize.contentAs();
        if (class1 == org/codehaus/jackson/map/annotate/NoClass) goto _L2; else goto _L3
_L3:
        return class1;
_L2:
        JsonContentClass jsoncontentclass = (JsonContentClass)annotated.getAnnotation(org/codehaus/jackson/annotate/JsonContentClass);
        if (jsoncontentclass == null)
        {
            break; /* Loop/switch isn't completed */
        }
        class1 = jsoncontentclass.value();
        if (class1 != org/codehaus/jackson/map/annotate/NoClass) goto _L3; else goto _L4
_L4:
        return null;
    }

    public Class findDeserializationKeyType(Annotated annotated, JavaType javatype, String s)
    {
        JsonDeserialize jsondeserialize = (JsonDeserialize)annotated.getAnnotation(org/codehaus/jackson/map/annotate/JsonDeserialize);
        if (jsondeserialize == null) goto _L2; else goto _L1
_L1:
        Class class1 = jsondeserialize.keyAs();
        if (class1 == org/codehaus/jackson/map/annotate/NoClass) goto _L2; else goto _L3
_L3:
        return class1;
_L2:
        JsonKeyClass jsonkeyclass = (JsonKeyClass)annotated.getAnnotation(org/codehaus/jackson/annotate/JsonKeyClass);
        if (jsonkeyclass == null)
        {
            break; /* Loop/switch isn't completed */
        }
        class1 = jsonkeyclass.value();
        if (class1 != org/codehaus/jackson/map/annotate/NoClass) goto _L3; else goto _L4
_L4:
        return null;
    }

    public Class findDeserializationType(Annotated annotated, JavaType javatype, String s)
    {
        JsonDeserialize jsondeserialize = (JsonDeserialize)annotated.getAnnotation(org/codehaus/jackson/map/annotate/JsonDeserialize);
        if (jsondeserialize == null) goto _L2; else goto _L1
_L1:
        Class class1 = jsondeserialize.as();
        if (class1 == org/codehaus/jackson/map/annotate/NoClass) goto _L2; else goto _L3
_L3:
        return class1;
_L2:
        JsonClass jsonclass = (JsonClass)annotated.getAnnotation(org/codehaus/jackson/annotate/JsonClass);
        if (jsonclass == null)
        {
            break; /* Loop/switch isn't completed */
        }
        class1 = jsonclass.value();
        if (class1 != org/codehaus/jackson/map/annotate/NoClass) goto _L3; else goto _L4
_L4:
        return null;
    }

    public Class findDeserializer(Annotated annotated, BeanProperty beanproperty)
    {
        JsonDeserialize jsondeserialize = (JsonDeserialize)annotated.getAnnotation(org/codehaus/jackson/map/annotate/JsonDeserialize);
        if (jsondeserialize != null)
        {
            Class class1 = jsondeserialize.using();
            if (class1 != org/codehaus/jackson/map/JsonDeserializer$None)
            {
                return class1;
            }
        }
        return null;
    }

    public volatile Object findDeserializer(Annotated annotated, BeanProperty beanproperty)
    {
        return findDeserializer(annotated, beanproperty);
    }

    public String findEnumValue(Enum enum)
    {
        return enum.name();
    }

    public Object findFilterId(AnnotatedClass annotatedclass)
    {
        JsonFilter jsonfilter = (JsonFilter)annotatedclass.getAnnotation(org/codehaus/jackson/map/annotate/JsonFilter);
        if (jsonfilter != null)
        {
            String s = jsonfilter.value();
            if (s.length() > 0)
            {
                return s;
            }
        }
        return null;
    }

    public String findGettablePropertyName(AnnotatedMethod annotatedmethod)
    {
        JsonProperty jsonproperty = (JsonProperty)annotatedmethod.getAnnotation(org/codehaus/jackson/annotate/JsonProperty);
        if (jsonproperty != null)
        {
            return jsonproperty.value();
        }
        JsonGetter jsongetter = (JsonGetter)annotatedmethod.getAnnotation(org/codehaus/jackson/annotate/JsonGetter);
        if (jsongetter != null)
        {
            return jsongetter.value();
        }
        if (annotatedmethod.hasAnnotation(org/codehaus/jackson/map/annotate/JsonSerialize) || annotatedmethod.hasAnnotation(org/codehaus/jackson/map/annotate/JsonView))
        {
            return "";
        } else
        {
            return null;
        }
    }

    public Boolean findIgnoreUnknownProperties(AnnotatedClass annotatedclass)
    {
        JsonIgnoreProperties jsonignoreproperties = (JsonIgnoreProperties)annotatedclass.getAnnotation(org/codehaus/jackson/annotate/JsonIgnoreProperties);
        if (jsonignoreproperties == null)
        {
            return null;
        } else
        {
            return Boolean.valueOf(jsonignoreproperties.ignoreUnknown());
        }
    }

    public Class findKeyDeserializer(Annotated annotated)
    {
        JsonDeserialize jsondeserialize = (JsonDeserialize)annotated.getAnnotation(org/codehaus/jackson/map/annotate/JsonDeserialize);
        if (jsondeserialize != null)
        {
            Class class1 = jsondeserialize.keyUsing();
            if (class1 != org/codehaus/jackson/map/KeyDeserializer$None)
            {
                return class1;
            }
        }
        return null;
    }

    public Class findKeySerializer(Annotated annotated)
    {
        JsonSerialize jsonserialize = (JsonSerialize)annotated.getAnnotation(org/codehaus/jackson/map/annotate/JsonSerialize);
        if (jsonserialize != null)
        {
            Class class1 = jsonserialize.keyUsing();
            if (class1 != org/codehaus/jackson/map/JsonSerializer$None)
            {
                return class1;
            }
        }
        return null;
    }

    public String[] findPropertiesToIgnore(AnnotatedClass annotatedclass)
    {
        JsonIgnoreProperties jsonignoreproperties = (JsonIgnoreProperties)annotatedclass.getAnnotation(org/codehaus/jackson/annotate/JsonIgnoreProperties);
        if (jsonignoreproperties == null)
        {
            return null;
        } else
        {
            return jsonignoreproperties.value();
        }
    }

    public TypeResolverBuilder findPropertyContentTypeResolver(MapperConfig mapperconfig, AnnotatedMember annotatedmember, JavaType javatype)
    {
        if (!javatype.isContainerType())
        {
            throw new IllegalArgumentException((new StringBuilder()).append("Must call method with a container type (got ").append(javatype).append(")").toString());
        } else
        {
            return _findTypeResolver(mapperconfig, annotatedmember, javatype);
        }
    }

    public String findPropertyNameForParam(AnnotatedParameter annotatedparameter)
    {
        if (annotatedparameter != null)
        {
            JsonProperty jsonproperty = (JsonProperty)annotatedparameter.getAnnotation(org/codehaus/jackson/annotate/JsonProperty);
            if (jsonproperty != null)
            {
                return jsonproperty.value();
            }
        }
        return null;
    }

    public TypeResolverBuilder findPropertyTypeResolver(MapperConfig mapperconfig, AnnotatedMember annotatedmember, JavaType javatype)
    {
        if (javatype.isContainerType())
        {
            return null;
        } else
        {
            return _findTypeResolver(mapperconfig, annotatedmember, javatype);
        }
    }

    public org.codehaus.jackson.map.AnnotationIntrospector.ReferenceProperty findReferenceType(AnnotatedMember annotatedmember)
    {
        JsonManagedReference jsonmanagedreference = (JsonManagedReference)annotatedmember.getAnnotation(org/codehaus/jackson/annotate/JsonManagedReference);
        if (jsonmanagedreference != null)
        {
            return org.codehaus.jackson.map.AnnotationIntrospector.ReferenceProperty.managed(jsonmanagedreference.value());
        }
        JsonBackReference jsonbackreference = (JsonBackReference)annotatedmember.getAnnotation(org/codehaus/jackson/annotate/JsonBackReference);
        if (jsonbackreference != null)
        {
            return org.codehaus.jackson.map.AnnotationIntrospector.ReferenceProperty.back(jsonbackreference.value());
        } else
        {
            return null;
        }
    }

    public String findRootName(AnnotatedClass annotatedclass)
    {
        return null;
    }

    public String findSerializablePropertyName(AnnotatedField annotatedfield)
    {
        JsonProperty jsonproperty = (JsonProperty)annotatedfield.getAnnotation(org/codehaus/jackson/annotate/JsonProperty);
        if (jsonproperty != null)
        {
            return jsonproperty.value();
        }
        if (annotatedfield.hasAnnotation(org/codehaus/jackson/map/annotate/JsonSerialize) || annotatedfield.hasAnnotation(org/codehaus/jackson/map/annotate/JsonView))
        {
            return "";
        } else
        {
            return null;
        }
    }

    public Class findSerializationContentType(Annotated annotated, JavaType javatype)
    {
        JsonSerialize jsonserialize = (JsonSerialize)annotated.getAnnotation(org/codehaus/jackson/map/annotate/JsonSerialize);
        if (jsonserialize != null)
        {
            Class class1 = jsonserialize.contentAs();
            if (class1 != org/codehaus/jackson/map/annotate/NoClass)
            {
                return class1;
            }
        }
        return null;
    }

    public org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion findSerializationInclusion(Annotated annotated, org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion inclusion)
    {
        JsonSerialize jsonserialize = (JsonSerialize)annotated.getAnnotation(org/codehaus/jackson/map/annotate/JsonSerialize);
        if (jsonserialize != null)
        {
            return jsonserialize.include();
        }
        JsonWriteNullProperties jsonwritenullproperties = (JsonWriteNullProperties)annotated.getAnnotation(org/codehaus/jackson/annotate/JsonWriteNullProperties);
        if (jsonwritenullproperties != null)
        {
            if (jsonwritenullproperties.value())
            {
                return org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion.ALWAYS;
            } else
            {
                return org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion.NON_NULL;
            }
        } else
        {
            return inclusion;
        }
    }

    public Class findSerializationKeyType(Annotated annotated, JavaType javatype)
    {
        JsonSerialize jsonserialize = (JsonSerialize)annotated.getAnnotation(org/codehaus/jackson/map/annotate/JsonSerialize);
        if (jsonserialize != null)
        {
            Class class1 = jsonserialize.keyAs();
            if (class1 != org/codehaus/jackson/map/annotate/NoClass)
            {
                return class1;
            }
        }
        return null;
    }

    public String[] findSerializationPropertyOrder(AnnotatedClass annotatedclass)
    {
        JsonPropertyOrder jsonpropertyorder = (JsonPropertyOrder)annotatedclass.getAnnotation(org/codehaus/jackson/annotate/JsonPropertyOrder);
        if (jsonpropertyorder == null)
        {
            return null;
        } else
        {
            return jsonpropertyorder.value();
        }
    }

    public Boolean findSerializationSortAlphabetically(AnnotatedClass annotatedclass)
    {
        JsonPropertyOrder jsonpropertyorder = (JsonPropertyOrder)annotatedclass.getAnnotation(org/codehaus/jackson/annotate/JsonPropertyOrder);
        if (jsonpropertyorder == null)
        {
            return null;
        } else
        {
            return Boolean.valueOf(jsonpropertyorder.alphabetic());
        }
    }

    public Class findSerializationType(Annotated annotated)
    {
        JsonSerialize jsonserialize = (JsonSerialize)annotated.getAnnotation(org/codehaus/jackson/map/annotate/JsonSerialize);
        if (jsonserialize != null)
        {
            Class class1 = jsonserialize.as();
            if (class1 != org/codehaus/jackson/map/annotate/NoClass)
            {
                return class1;
            }
        }
        return null;
    }

    public org.codehaus.jackson.map.annotate.JsonSerialize.Typing findSerializationTyping(Annotated annotated)
    {
        JsonSerialize jsonserialize = (JsonSerialize)annotated.getAnnotation(org/codehaus/jackson/map/annotate/JsonSerialize);
        if (jsonserialize == null)
        {
            return null;
        } else
        {
            return jsonserialize.typing();
        }
    }

    public Class[] findSerializationViews(Annotated annotated)
    {
        JsonView jsonview = (JsonView)annotated.getAnnotation(org/codehaus/jackson/map/annotate/JsonView);
        if (jsonview == null)
        {
            return null;
        } else
        {
            return jsonview.value();
        }
    }

    public Object findSerializer(Annotated annotated, BeanProperty beanproperty)
    {
        JsonSerialize jsonserialize = (JsonSerialize)annotated.getAnnotation(org/codehaus/jackson/map/annotate/JsonSerialize);
        if (jsonserialize != null)
        {
            Class class1 = jsonserialize.using();
            if (class1 != org/codehaus/jackson/map/JsonSerializer$None)
            {
                return class1;
            }
        }
        JsonRawValue jsonrawvalue = (JsonRawValue)annotated.getAnnotation(org/codehaus/jackson/annotate/JsonRawValue);
        if (jsonrawvalue != null && jsonrawvalue.value())
        {
            return new RawSerializer(annotated.getRawType());
        } else
        {
            return null;
        }
    }

    public String findSettablePropertyName(AnnotatedMethod annotatedmethod)
    {
        JsonProperty jsonproperty = (JsonProperty)annotatedmethod.getAnnotation(org/codehaus/jackson/annotate/JsonProperty);
        if (jsonproperty != null)
        {
            return jsonproperty.value();
        }
        JsonSetter jsonsetter = (JsonSetter)annotatedmethod.getAnnotation(org/codehaus/jackson/annotate/JsonSetter);
        if (jsonsetter != null)
        {
            return jsonsetter.value();
        }
        if (annotatedmethod.hasAnnotation(org/codehaus/jackson/map/annotate/JsonDeserialize) || annotatedmethod.hasAnnotation(org/codehaus/jackson/map/annotate/JsonView))
        {
            return "";
        } else
        {
            return null;
        }
    }

    public List findSubtypes(Annotated annotated)
    {
        JsonSubTypes jsonsubtypes = (JsonSubTypes)annotated.getAnnotation(org/codehaus/jackson/annotate/JsonSubTypes);
        Object obj;
        if (jsonsubtypes == null)
        {
            obj = null;
        } else
        {
            org.codehaus.jackson.annotate.JsonSubTypes.Type atype[] = jsonsubtypes.value();
            obj = new ArrayList(atype.length);
            int i = atype.length;
            int j = 0;
            while (j < i) 
            {
                org.codehaus.jackson.annotate.JsonSubTypes.Type type = atype[j];
                ((ArrayList) (obj)).add(new NamedType(type.value(), type.name()));
                j++;
            }
        }
        return ((List) (obj));
    }

    public String findTypeName(AnnotatedClass annotatedclass)
    {
        JsonTypeName jsontypename = (JsonTypeName)annotatedclass.getAnnotation(org/codehaus/jackson/annotate/JsonTypeName);
        if (jsontypename == null)
        {
            return null;
        } else
        {
            return jsontypename.value();
        }
    }

    public TypeResolverBuilder findTypeResolver(MapperConfig mapperconfig, AnnotatedClass annotatedclass, JavaType javatype)
    {
        return _findTypeResolver(mapperconfig, annotatedclass, javatype);
    }

    public boolean hasAnyGetterAnnotation(AnnotatedMethod annotatedmethod)
    {
        return annotatedmethod.hasAnnotation(org/codehaus/jackson/annotate/JsonAnyGetter);
    }

    public boolean hasAnySetterAnnotation(AnnotatedMethod annotatedmethod)
    {
        return annotatedmethod.hasAnnotation(org/codehaus/jackson/annotate/JsonAnySetter);
    }

    public boolean hasAsValueAnnotation(AnnotatedMethod annotatedmethod)
    {
        JsonValue jsonvalue = (JsonValue)annotatedmethod.getAnnotation(org/codehaus/jackson/annotate/JsonValue);
        return jsonvalue != null && jsonvalue.value();
    }

    public boolean hasCreatorAnnotation(Annotated annotated)
    {
        return annotated.hasAnnotation(org/codehaus/jackson/annotate/JsonCreator);
    }

    public boolean isHandled(Annotation annotation)
    {
        return annotation.annotationType().getAnnotation(org/codehaus/jackson/annotate/JacksonAnnotation) != null;
    }

    public boolean isIgnorableConstructor(AnnotatedConstructor annotatedconstructor)
    {
        return _isIgnorable(annotatedconstructor);
    }

    public boolean isIgnorableField(AnnotatedField annotatedfield)
    {
        return _isIgnorable(annotatedfield);
    }

    public boolean isIgnorableMethod(AnnotatedMethod annotatedmethod)
    {
        return _isIgnorable(annotatedmethod);
    }

    public Boolean isIgnorableType(AnnotatedClass annotatedclass)
    {
        JsonIgnoreType jsonignoretype = (JsonIgnoreType)annotatedclass.getAnnotation(org/codehaus/jackson/annotate/JsonIgnoreType);
        if (jsonignoretype == null)
        {
            return null;
        } else
        {
            return Boolean.valueOf(jsonignoretype.value());
        }
    }
}

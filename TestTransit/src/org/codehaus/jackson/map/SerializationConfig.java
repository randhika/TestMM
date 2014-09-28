// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.map;

import java.text.DateFormat;
import java.util.HashMap;
import org.codehaus.jackson.map.introspect.Annotated;
import org.codehaus.jackson.map.introspect.AnnotatedClass;
import org.codehaus.jackson.map.introspect.VisibilityChecker;
import org.codehaus.jackson.map.jsontype.SubtypeResolver;
import org.codehaus.jackson.map.jsontype.TypeResolverBuilder;
import org.codehaus.jackson.map.ser.FilterProvider;
import org.codehaus.jackson.map.type.TypeFactory;
import org.codehaus.jackson.map.util.ClassUtil;
import org.codehaus.jackson.type.JavaType;

// Referenced classes of package org.codehaus.jackson.map:
//            MapperConfig, AnnotationIntrospector, ClassIntrospector, HandlerInstantiator, 
//            JsonSerializer, PropertyNamingStrategy, BeanDescription

public class SerializationConfig extends MapperConfig
{
    public static final class Feature extends Enum
    {

        private static final Feature $VALUES[];
        public static final Feature AUTO_DETECT_FIELDS;
        public static final Feature AUTO_DETECT_GETTERS;
        public static final Feature AUTO_DETECT_IS_GETTERS;
        public static final Feature CAN_OVERRIDE_ACCESS_MODIFIERS;
        public static final Feature CLOSE_CLOSEABLE;
        public static final Feature DEFAULT_VIEW_INCLUSION;
        public static final Feature FAIL_ON_EMPTY_BEANS;
        public static final Feature FLUSH_AFTER_WRITE_VALUE;
        public static final Feature INDENT_OUTPUT;
        public static final Feature SORT_PROPERTIES_ALPHABETICALLY;
        public static final Feature USE_ANNOTATIONS;
        public static final Feature USE_STATIC_TYPING;
        public static final Feature WRAP_EXCEPTIONS;
        public static final Feature WRAP_ROOT_VALUE;
        public static final Feature WRITE_CHAR_ARRAYS_AS_JSON_ARRAYS;
        public static final Feature WRITE_DATES_AS_TIMESTAMPS;
        public static final Feature WRITE_ENUMS_USING_TO_STRING;
        public static final Feature WRITE_NULL_MAP_VALUES;
        public static final Feature WRITE_NULL_PROPERTIES;
        final boolean _defaultState;

        public static int collectDefaults()
        {
            int i = 0;
            Feature afeature[] = values();
            int j = afeature.length;
            for (int k = 0; k < j; k++)
            {
                Feature feature = afeature[k];
                if (feature.enabledByDefault())
                {
                    i |= feature.getMask();
                }
            }

            return i;
        }

        public static Feature valueOf(String s)
        {
            return (Feature)Enum.valueOf(org/codehaus/jackson/map/SerializationConfig$Feature, s);
        }

        public static Feature[] values()
        {
            return (Feature[])$VALUES.clone();
        }

        public boolean enabledByDefault()
        {
            return _defaultState;
        }

        public int getMask()
        {
            return 1 << ordinal();
        }

        static 
        {
            USE_ANNOTATIONS = new Feature("USE_ANNOTATIONS", 0, true);
            AUTO_DETECT_GETTERS = new Feature("AUTO_DETECT_GETTERS", 1, true);
            AUTO_DETECT_IS_GETTERS = new Feature("AUTO_DETECT_IS_GETTERS", 2, true);
            AUTO_DETECT_FIELDS = new Feature("AUTO_DETECT_FIELDS", 3, true);
            CAN_OVERRIDE_ACCESS_MODIFIERS = new Feature("CAN_OVERRIDE_ACCESS_MODIFIERS", 4, true);
            WRITE_NULL_PROPERTIES = new Feature("WRITE_NULL_PROPERTIES", 5, true);
            USE_STATIC_TYPING = new Feature("USE_STATIC_TYPING", 6, false);
            DEFAULT_VIEW_INCLUSION = new Feature("DEFAULT_VIEW_INCLUSION", 7, true);
            WRAP_ROOT_VALUE = new Feature("WRAP_ROOT_VALUE", 8, false);
            INDENT_OUTPUT = new Feature("INDENT_OUTPUT", 9, false);
            SORT_PROPERTIES_ALPHABETICALLY = new Feature("SORT_PROPERTIES_ALPHABETICALLY", 10, false);
            FAIL_ON_EMPTY_BEANS = new Feature("FAIL_ON_EMPTY_BEANS", 11, true);
            WRAP_EXCEPTIONS = new Feature("WRAP_EXCEPTIONS", 12, true);
            CLOSE_CLOSEABLE = new Feature("CLOSE_CLOSEABLE", 13, false);
            FLUSH_AFTER_WRITE_VALUE = new Feature("FLUSH_AFTER_WRITE_VALUE", 14, true);
            WRITE_DATES_AS_TIMESTAMPS = new Feature("WRITE_DATES_AS_TIMESTAMPS", 15, true);
            WRITE_CHAR_ARRAYS_AS_JSON_ARRAYS = new Feature("WRITE_CHAR_ARRAYS_AS_JSON_ARRAYS", 16, false);
            WRITE_ENUMS_USING_TO_STRING = new Feature("WRITE_ENUMS_USING_TO_STRING", 17, false);
            WRITE_NULL_MAP_VALUES = new Feature("WRITE_NULL_MAP_VALUES", 18, true);
            Feature afeature[] = new Feature[19];
            afeature[0] = USE_ANNOTATIONS;
            afeature[1] = AUTO_DETECT_GETTERS;
            afeature[2] = AUTO_DETECT_IS_GETTERS;
            afeature[3] = AUTO_DETECT_FIELDS;
            afeature[4] = CAN_OVERRIDE_ACCESS_MODIFIERS;
            afeature[5] = WRITE_NULL_PROPERTIES;
            afeature[6] = USE_STATIC_TYPING;
            afeature[7] = DEFAULT_VIEW_INCLUSION;
            afeature[8] = WRAP_ROOT_VALUE;
            afeature[9] = INDENT_OUTPUT;
            afeature[10] = SORT_PROPERTIES_ALPHABETICALLY;
            afeature[11] = FAIL_ON_EMPTY_BEANS;
            afeature[12] = WRAP_EXCEPTIONS;
            afeature[13] = CLOSE_CLOSEABLE;
            afeature[14] = FLUSH_AFTER_WRITE_VALUE;
            afeature[15] = WRITE_DATES_AS_TIMESTAMPS;
            afeature[16] = WRITE_CHAR_ARRAYS_AS_JSON_ARRAYS;
            afeature[17] = WRITE_ENUMS_USING_TO_STRING;
            afeature[18] = WRITE_NULL_MAP_VALUES;
            $VALUES = afeature;
        }

        private Feature(String s, int i, boolean flag)
        {
            super(s, i);
            _defaultState = flag;
        }
    }


    protected static final int DEFAULT_FEATURE_FLAGS = Feature.collectDefaults();
    protected int _featureFlags;
    protected FilterProvider _filterProvider;
    protected org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion _serializationInclusion;
    protected Class _serializationView;

    public SerializationConfig(ClassIntrospector classintrospector, AnnotationIntrospector annotationintrospector, VisibilityChecker visibilitychecker, SubtypeResolver subtyperesolver, PropertyNamingStrategy propertynamingstrategy, TypeFactory typefactory, HandlerInstantiator handlerinstantiator)
    {
        super(classintrospector, annotationintrospector, visibilitychecker, subtyperesolver, propertynamingstrategy, typefactory, handlerinstantiator);
        _featureFlags = DEFAULT_FEATURE_FLAGS;
        _serializationInclusion = null;
        _filterProvider = null;
    }

    protected SerializationConfig(SerializationConfig serializationconfig)
    {
        this(serializationconfig, serializationconfig._base);
    }

    protected SerializationConfig(SerializationConfig serializationconfig, Class class1)
    {
        super(serializationconfig);
        _featureFlags = DEFAULT_FEATURE_FLAGS;
        _serializationInclusion = null;
        _featureFlags = serializationconfig._featureFlags;
        _serializationInclusion = serializationconfig._serializationInclusion;
        _serializationView = class1;
        _filterProvider = serializationconfig._filterProvider;
    }

    protected SerializationConfig(SerializationConfig serializationconfig, HashMap hashmap, SubtypeResolver subtyperesolver)
    {
        this(serializationconfig, serializationconfig._base);
        _mixInAnnotations = hashmap;
        _subtypeResolver = subtyperesolver;
    }

    protected SerializationConfig(SerializationConfig serializationconfig, MapperConfig.Base base)
    {
        super(serializationconfig, base, serializationconfig._subtypeResolver);
        _featureFlags = DEFAULT_FEATURE_FLAGS;
        _serializationInclusion = null;
        _featureFlags = serializationconfig._featureFlags;
        _serializationInclusion = serializationconfig._serializationInclusion;
        _serializationView = serializationconfig._serializationView;
        _filterProvider = serializationconfig._filterProvider;
    }

    protected SerializationConfig(SerializationConfig serializationconfig, FilterProvider filterprovider)
    {
        super(serializationconfig);
        _featureFlags = DEFAULT_FEATURE_FLAGS;
        _serializationInclusion = null;
        _featureFlags = serializationconfig._featureFlags;
        _serializationInclusion = serializationconfig._serializationInclusion;
        _serializationView = serializationconfig._serializationView;
        _filterProvider = filterprovider;
    }

    public boolean canOverrideAccessModifiers()
    {
        return isEnabled(Feature.CAN_OVERRIDE_ACCESS_MODIFIERS);
    }

    public volatile MapperConfig createUnshared(SubtypeResolver subtyperesolver)
    {
        return createUnshared(subtyperesolver);
    }

    public volatile MapperConfig createUnshared(TypeResolverBuilder typeresolverbuilder, VisibilityChecker visibilitychecker, SubtypeResolver subtyperesolver)
    {
        return createUnshared(typeresolverbuilder, visibilitychecker, subtyperesolver);
    }

    public SerializationConfig createUnshared(SubtypeResolver subtyperesolver)
    {
        HashMap hashmap = _mixInAnnotations;
        _mixInAnnotationsShared = true;
        return new SerializationConfig(this, hashmap, subtyperesolver);
    }

    public SerializationConfig createUnshared(TypeResolverBuilder typeresolverbuilder, VisibilityChecker visibilitychecker, SubtypeResolver subtyperesolver)
    {
        return createUnshared(subtyperesolver).withTypeResolverBuilder(typeresolverbuilder).withVisibilityChecker(visibilitychecker);
    }

    public void disable(Feature feature)
    {
        _featureFlags = _featureFlags & (-1 ^ feature.getMask());
    }

    public void enable(Feature feature)
    {
        _featureFlags = _featureFlags | feature.getMask();
    }

    public void fromAnnotations(Class class1)
    {
        AnnotationIntrospector annotationintrospector = getAnnotationIntrospector();
        AnnotatedClass annotatedclass = AnnotatedClass.construct(class1, annotationintrospector, null);
        _base = _base.withVisibilityChecker(annotationintrospector.findAutoDetectVisibility(annotatedclass, getDefaultVisibilityChecker()));
        org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion inclusion = annotationintrospector.findSerializationInclusion(annotatedclass, null);
        if (inclusion != _serializationInclusion)
        {
            setSerializationInclusion(inclusion);
        }
        org.codehaus.jackson.map.annotate.JsonSerialize.Typing typing = annotationintrospector.findSerializationTyping(annotatedclass);
        if (typing != null)
        {
            Feature feature = Feature.USE_STATIC_TYPING;
            boolean flag;
            if (typing == org.codehaus.jackson.map.annotate.JsonSerialize.Typing.STATIC)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            set(feature, flag);
        }
    }

    public AnnotationIntrospector getAnnotationIntrospector()
    {
        if (isEnabled(Feature.USE_ANNOTATIONS))
        {
            return super.getAnnotationIntrospector();
        } else
        {
            return AnnotationIntrospector.nopInstance();
        }
    }

    public FilterProvider getFilterProvider()
    {
        return _filterProvider;
    }

    public org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion getSerializationInclusion()
    {
        if (_serializationInclusion != null)
        {
            return _serializationInclusion;
        }
        if (isEnabled(Feature.WRITE_NULL_PROPERTIES))
        {
            return org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion.ALWAYS;
        } else
        {
            return org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion.NON_NULL;
        }
    }

    public Class getSerializationView()
    {
        return _serializationView;
    }

    public BeanDescription introspect(JavaType javatype)
    {
        return getClassIntrospector().forSerialization(this, javatype, this);
    }

    public BeanDescription introspectClassAnnotations(Class class1)
    {
        return getClassIntrospector().forClassAnnotations(this, class1, this);
    }

    public BeanDescription introspectDirectClassAnnotations(Class class1)
    {
        return getClassIntrospector().forDirectClassAnnotations(this, class1, this);
    }

    public boolean isAnnotationProcessingEnabled()
    {
        return isEnabled(Feature.USE_ANNOTATIONS);
    }

    public final boolean isEnabled(Feature feature)
    {
        return (_featureFlags & feature.getMask()) != 0;
    }

    public JsonSerializer serializerInstance(Annotated annotated, Class class1)
    {
        HandlerInstantiator handlerinstantiator = getHandlerInstantiator();
        if (handlerinstantiator != null)
        {
            JsonSerializer jsonserializer = handlerinstantiator.serializerInstance(this, annotated, class1);
            if (jsonserializer != null)
            {
                return jsonserializer;
            }
        }
        return (JsonSerializer)ClassUtil.createInstance(class1, canOverrideAccessModifiers());
    }

    public void set(Feature feature, boolean flag)
    {
        if (flag)
        {
            enable(feature);
            return;
        } else
        {
            disable(feature);
            return;
        }
    }

    public final void setDateFormat(DateFormat dateformat)
    {
        super.setDateFormat(dateformat);
        Feature feature = Feature.WRITE_DATES_AS_TIMESTAMPS;
        boolean flag;
        if (dateformat == null)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        set(feature, flag);
    }

    public void setSerializationInclusion(org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion inclusion)
    {
        _serializationInclusion = inclusion;
        if (inclusion == org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion.NON_NULL)
        {
            disable(Feature.WRITE_NULL_PROPERTIES);
            return;
        } else
        {
            enable(Feature.WRITE_NULL_PROPERTIES);
            return;
        }
    }

    public void setSerializationView(Class class1)
    {
        _serializationView = class1;
    }

    public String toString()
    {
        return (new StringBuilder()).append("[SerializationConfig: flags=0x").append(Integer.toHexString(_featureFlags)).append("]").toString();
    }

    public volatile MapperConfig withAnnotationIntrospector(AnnotationIntrospector annotationintrospector)
    {
        return withAnnotationIntrospector(annotationintrospector);
    }

    public SerializationConfig withAnnotationIntrospector(AnnotationIntrospector annotationintrospector)
    {
        return new SerializationConfig(this, _base.withAnnotationIntrospector(annotationintrospector));
    }

    public volatile MapperConfig withClassIntrospector(ClassIntrospector classintrospector)
    {
        return withClassIntrospector(classintrospector);
    }

    public SerializationConfig withClassIntrospector(ClassIntrospector classintrospector)
    {
        return new SerializationConfig(this, _base.withClassIntrospector(classintrospector));
    }

    public volatile MapperConfig withDateFormat(DateFormat dateformat)
    {
        return withDateFormat(dateformat);
    }

    public SerializationConfig withDateFormat(DateFormat dateformat)
    {
        SerializationConfig serializationconfig = new SerializationConfig(this, _base.withDateFormat(dateformat));
        Feature feature = Feature.WRITE_DATES_AS_TIMESTAMPS;
        boolean flag;
        if (dateformat == null)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        serializationconfig.set(feature, flag);
        return serializationconfig;
    }

    public SerializationConfig withFilters(FilterProvider filterprovider)
    {
        return new SerializationConfig(this, filterprovider);
    }

    public volatile MapperConfig withHandlerInstantiator(HandlerInstantiator handlerinstantiator)
    {
        return withHandlerInstantiator(handlerinstantiator);
    }

    public SerializationConfig withHandlerInstantiator(HandlerInstantiator handlerinstantiator)
    {
        return new SerializationConfig(this, _base.withHandlerInstantiator(handlerinstantiator));
    }

    public volatile MapperConfig withPropertyNamingStrategy(PropertyNamingStrategy propertynamingstrategy)
    {
        return withPropertyNamingStrategy(propertynamingstrategy);
    }

    public SerializationConfig withPropertyNamingStrategy(PropertyNamingStrategy propertynamingstrategy)
    {
        return new SerializationConfig(this, _base.withPropertyNamingStrategy(propertynamingstrategy));
    }

    public volatile MapperConfig withSubtypeResolver(SubtypeResolver subtyperesolver)
    {
        return withSubtypeResolver(subtyperesolver);
    }

    public SerializationConfig withSubtypeResolver(SubtypeResolver subtyperesolver)
    {
        SerializationConfig serializationconfig = new SerializationConfig(this);
        serializationconfig._subtypeResolver = subtyperesolver;
        return serializationconfig;
    }

    public volatile MapperConfig withTypeFactory(TypeFactory typefactory)
    {
        return withTypeFactory(typefactory);
    }

    public SerializationConfig withTypeFactory(TypeFactory typefactory)
    {
        return new SerializationConfig(this, _base.withTypeFactory(typefactory));
    }

    public volatile MapperConfig withTypeResolverBuilder(TypeResolverBuilder typeresolverbuilder)
    {
        return withTypeResolverBuilder(typeresolverbuilder);
    }

    public SerializationConfig withTypeResolverBuilder(TypeResolverBuilder typeresolverbuilder)
    {
        return new SerializationConfig(this, _base.withTypeResolverBuilder(typeresolverbuilder));
    }

    public SerializationConfig withView(Class class1)
    {
        return new SerializationConfig(this, class1);
    }

    public volatile MapperConfig withVisibilityChecker(VisibilityChecker visibilitychecker)
    {
        return withVisibilityChecker(visibilitychecker);
    }

    public SerializationConfig withVisibilityChecker(VisibilityChecker visibilitychecker)
    {
        return new SerializationConfig(this, _base.withVisibilityChecker(visibilitychecker));
    }

}

// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.map;

import java.text.DateFormat;
import java.util.HashMap;
import org.codehaus.jackson.Base64Variant;
import org.codehaus.jackson.Base64Variants;
import org.codehaus.jackson.map.introspect.Annotated;
import org.codehaus.jackson.map.introspect.AnnotatedClass;
import org.codehaus.jackson.map.introspect.NopAnnotationIntrospector;
import org.codehaus.jackson.map.introspect.VisibilityChecker;
import org.codehaus.jackson.map.jsontype.SubtypeResolver;
import org.codehaus.jackson.map.jsontype.TypeResolverBuilder;
import org.codehaus.jackson.map.type.TypeFactory;
import org.codehaus.jackson.map.util.ClassUtil;
import org.codehaus.jackson.map.util.LinkedNode;
import org.codehaus.jackson.node.JsonNodeFactory;
import org.codehaus.jackson.type.JavaType;

// Referenced classes of package org.codehaus.jackson.map:
//            MapperConfig, HandlerInstantiator, JsonDeserializer, AnnotationIntrospector, 
//            ClassIntrospector, KeyDeserializer, AbstractTypeResolver, PropertyNamingStrategy, 
//            DeserializationProblemHandler, BeanDescription

public class DeserializationConfig extends MapperConfig
{
    public static final class Feature extends Enum
    {

        private static final Feature $VALUES[];
        public static final Feature ACCEPT_EMPTY_STRING_AS_NULL_OBJECT;
        public static final Feature ACCEPT_SINGLE_VALUE_AS_ARRAY;
        public static final Feature AUTO_DETECT_CREATORS;
        public static final Feature AUTO_DETECT_FIELDS;
        public static final Feature AUTO_DETECT_SETTERS;
        public static final Feature CAN_OVERRIDE_ACCESS_MODIFIERS;
        public static final Feature FAIL_ON_NULL_FOR_PRIMITIVES;
        public static final Feature FAIL_ON_NUMBERS_FOR_ENUMS;
        public static final Feature FAIL_ON_UNKNOWN_PROPERTIES;
        public static final Feature READ_ENUMS_USING_TO_STRING;
        public static final Feature USE_ANNOTATIONS;
        public static final Feature USE_BIG_DECIMAL_FOR_FLOATS;
        public static final Feature USE_BIG_INTEGER_FOR_INTS;
        public static final Feature USE_GETTERS_AS_SETTERS;
        public static final Feature WRAP_EXCEPTIONS;
        public static final Feature WRAP_ROOT_VALUE;
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
            return (Feature)Enum.valueOf(org/codehaus/jackson/map/DeserializationConfig$Feature, s);
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
            AUTO_DETECT_SETTERS = new Feature("AUTO_DETECT_SETTERS", 1, true);
            AUTO_DETECT_CREATORS = new Feature("AUTO_DETECT_CREATORS", 2, true);
            AUTO_DETECT_FIELDS = new Feature("AUTO_DETECT_FIELDS", 3, true);
            USE_GETTERS_AS_SETTERS = new Feature("USE_GETTERS_AS_SETTERS", 4, true);
            CAN_OVERRIDE_ACCESS_MODIFIERS = new Feature("CAN_OVERRIDE_ACCESS_MODIFIERS", 5, true);
            USE_BIG_DECIMAL_FOR_FLOATS = new Feature("USE_BIG_DECIMAL_FOR_FLOATS", 6, false);
            USE_BIG_INTEGER_FOR_INTS = new Feature("USE_BIG_INTEGER_FOR_INTS", 7, false);
            READ_ENUMS_USING_TO_STRING = new Feature("READ_ENUMS_USING_TO_STRING", 8, false);
            FAIL_ON_UNKNOWN_PROPERTIES = new Feature("FAIL_ON_UNKNOWN_PROPERTIES", 9, true);
            FAIL_ON_NULL_FOR_PRIMITIVES = new Feature("FAIL_ON_NULL_FOR_PRIMITIVES", 10, false);
            FAIL_ON_NUMBERS_FOR_ENUMS = new Feature("FAIL_ON_NUMBERS_FOR_ENUMS", 11, false);
            WRAP_EXCEPTIONS = new Feature("WRAP_EXCEPTIONS", 12, true);
            WRAP_ROOT_VALUE = new Feature("WRAP_ROOT_VALUE", 13, false);
            ACCEPT_EMPTY_STRING_AS_NULL_OBJECT = new Feature("ACCEPT_EMPTY_STRING_AS_NULL_OBJECT", 14, false);
            ACCEPT_SINGLE_VALUE_AS_ARRAY = new Feature("ACCEPT_SINGLE_VALUE_AS_ARRAY", 15, false);
            Feature afeature[] = new Feature[16];
            afeature[0] = USE_ANNOTATIONS;
            afeature[1] = AUTO_DETECT_SETTERS;
            afeature[2] = AUTO_DETECT_CREATORS;
            afeature[3] = AUTO_DETECT_FIELDS;
            afeature[4] = USE_GETTERS_AS_SETTERS;
            afeature[5] = CAN_OVERRIDE_ACCESS_MODIFIERS;
            afeature[6] = USE_BIG_DECIMAL_FOR_FLOATS;
            afeature[7] = USE_BIG_INTEGER_FOR_INTS;
            afeature[8] = READ_ENUMS_USING_TO_STRING;
            afeature[9] = FAIL_ON_UNKNOWN_PROPERTIES;
            afeature[10] = FAIL_ON_NULL_FOR_PRIMITIVES;
            afeature[11] = FAIL_ON_NUMBERS_FOR_ENUMS;
            afeature[12] = WRAP_EXCEPTIONS;
            afeature[13] = WRAP_ROOT_VALUE;
            afeature[14] = ACCEPT_EMPTY_STRING_AS_NULL_OBJECT;
            afeature[15] = ACCEPT_SINGLE_VALUE_AS_ARRAY;
            $VALUES = afeature;
        }

        private Feature(String s, int i, boolean flag)
        {
            super(s, i);
            _defaultState = flag;
        }
    }


    protected static final int DEFAULT_FEATURE_FLAGS = Feature.collectDefaults();
    protected AbstractTypeResolver _abstractTypeResolver;
    protected int _featureFlags;
    protected JsonNodeFactory _nodeFactory;
    protected LinkedNode _problemHandlers;

    public DeserializationConfig(ClassIntrospector classintrospector, AnnotationIntrospector annotationintrospector, VisibilityChecker visibilitychecker, SubtypeResolver subtyperesolver, PropertyNamingStrategy propertynamingstrategy, TypeFactory typefactory, HandlerInstantiator handlerinstantiator)
    {
        super(classintrospector, annotationintrospector, visibilitychecker, subtyperesolver, propertynamingstrategy, typefactory, handlerinstantiator);
        _featureFlags = DEFAULT_FEATURE_FLAGS;
        _nodeFactory = JsonNodeFactory.instance;
    }

    protected DeserializationConfig(DeserializationConfig deserializationconfig)
    {
        this(deserializationconfig, deserializationconfig._base);
    }

    private DeserializationConfig(DeserializationConfig deserializationconfig, HashMap hashmap, SubtypeResolver subtyperesolver)
    {
        this(deserializationconfig, deserializationconfig._base);
        _mixInAnnotations = hashmap;
        _subtypeResolver = subtyperesolver;
    }

    protected DeserializationConfig(DeserializationConfig deserializationconfig, MapperConfig.Base base)
    {
        super(deserializationconfig, base, deserializationconfig._subtypeResolver);
        _featureFlags = DEFAULT_FEATURE_FLAGS;
        _featureFlags = deserializationconfig._featureFlags;
        _abstractTypeResolver = deserializationconfig._abstractTypeResolver;
        _problemHandlers = deserializationconfig._problemHandlers;
        _nodeFactory = deserializationconfig._nodeFactory;
    }

    protected DeserializationConfig(DeserializationConfig deserializationconfig, JsonNodeFactory jsonnodefactory)
    {
        super(deserializationconfig);
        _featureFlags = DEFAULT_FEATURE_FLAGS;
        _featureFlags = deserializationconfig._featureFlags;
        _abstractTypeResolver = deserializationconfig._abstractTypeResolver;
        _problemHandlers = deserializationconfig._problemHandlers;
        _nodeFactory = jsonnodefactory;
    }

    public void addHandler(DeserializationProblemHandler deserializationproblemhandler)
    {
        if (!LinkedNode.contains(_problemHandlers, deserializationproblemhandler))
        {
            _problemHandlers = new LinkedNode(deserializationproblemhandler, _problemHandlers);
        }
    }

    public boolean canOverrideAccessModifiers()
    {
        return isEnabled(Feature.CAN_OVERRIDE_ACCESS_MODIFIERS);
    }

    public void clearHandlers()
    {
        _problemHandlers = null;
    }

    public DeserializationConfig createUnshared(SubtypeResolver subtyperesolver)
    {
        HashMap hashmap = _mixInAnnotations;
        _mixInAnnotationsShared = true;
        return new DeserializationConfig(this, hashmap, subtyperesolver);
    }

    public DeserializationConfig createUnshared(TypeResolverBuilder typeresolverbuilder, VisibilityChecker visibilitychecker, SubtypeResolver subtyperesolver)
    {
        return createUnshared(subtyperesolver).withTypeResolverBuilder(typeresolverbuilder).withVisibilityChecker(visibilitychecker);
    }

    public volatile MapperConfig createUnshared(SubtypeResolver subtyperesolver)
    {
        return createUnshared(subtyperesolver);
    }

    public volatile MapperConfig createUnshared(TypeResolverBuilder typeresolverbuilder, VisibilityChecker visibilitychecker, SubtypeResolver subtyperesolver)
    {
        return createUnshared(typeresolverbuilder, visibilitychecker, subtyperesolver);
    }

    public JsonDeserializer deserializerInstance(Annotated annotated, Class class1)
    {
        HandlerInstantiator handlerinstantiator = getHandlerInstantiator();
        if (handlerinstantiator != null)
        {
            JsonDeserializer jsondeserializer = handlerinstantiator.deserializerInstance(this, annotated, class1);
            if (jsondeserializer != null)
            {
                return jsondeserializer;
            }
        }
        return (JsonDeserializer)ClassUtil.createInstance(class1, canOverrideAccessModifiers());
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
        VisibilityChecker visibilitychecker = getDefaultVisibilityChecker();
        _base = _base.withVisibilityChecker(annotationintrospector.findAutoDetectVisibility(annotatedclass, visibilitychecker));
    }

    public AbstractTypeResolver getAbstractTypeResolver()
    {
        return _abstractTypeResolver;
    }

    public AnnotationIntrospector getAnnotationIntrospector()
    {
        if (isEnabled(Feature.USE_ANNOTATIONS))
        {
            return super.getAnnotationIntrospector();
        } else
        {
            return NopAnnotationIntrospector.instance;
        }
    }

    public Base64Variant getBase64Variant()
    {
        return Base64Variants.getDefaultVariant();
    }

    public final JsonNodeFactory getNodeFactory()
    {
        return _nodeFactory;
    }

    public LinkedNode getProblemHandlers()
    {
        return _problemHandlers;
    }

    public BeanDescription introspect(JavaType javatype)
    {
        return getClassIntrospector().forDeserialization(this, javatype, this);
    }

    public BeanDescription introspectClassAnnotations(Class class1)
    {
        return getClassIntrospector().forClassAnnotations(this, class1, this);
    }

    public BeanDescription introspectDirectClassAnnotations(Class class1)
    {
        return getClassIntrospector().forDirectClassAnnotations(this, class1, this);
    }

    public BeanDescription introspectForCreation(JavaType javatype)
    {
        return getClassIntrospector().forCreation(this, javatype, this);
    }

    public boolean isAnnotationProcessingEnabled()
    {
        return isEnabled(Feature.USE_ANNOTATIONS);
    }

    public final boolean isEnabled(Feature feature)
    {
        return (_featureFlags & feature.getMask()) != 0;
    }

    public KeyDeserializer keyDeserializerInstance(Annotated annotated, Class class1)
    {
        HandlerInstantiator handlerinstantiator = getHandlerInstantiator();
        if (handlerinstantiator != null)
        {
            KeyDeserializer keydeserializer = handlerinstantiator.keyDeserializerInstance(this, annotated, class1);
            if (keydeserializer != null)
            {
                return keydeserializer;
            }
        }
        return (KeyDeserializer)ClassUtil.createInstance(class1, canOverrideAccessModifiers());
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

    public void setAbstractTypeResolver(AbstractTypeResolver abstracttyperesolver)
    {
        _abstractTypeResolver = abstracttyperesolver;
    }

    public void setNodeFactory(JsonNodeFactory jsonnodefactory)
    {
        _nodeFactory = jsonnodefactory;
    }

    public DeserializationConfig withAnnotationIntrospector(AnnotationIntrospector annotationintrospector)
    {
        return new DeserializationConfig(this, _base.withAnnotationIntrospector(annotationintrospector));
    }

    public volatile MapperConfig withAnnotationIntrospector(AnnotationIntrospector annotationintrospector)
    {
        return withAnnotationIntrospector(annotationintrospector);
    }

    public DeserializationConfig withClassIntrospector(ClassIntrospector classintrospector)
    {
        return new DeserializationConfig(this, _base.withClassIntrospector(classintrospector));
    }

    public volatile MapperConfig withClassIntrospector(ClassIntrospector classintrospector)
    {
        return withClassIntrospector(classintrospector);
    }

    public DeserializationConfig withDateFormat(DateFormat dateformat)
    {
        if (dateformat == _base.getDateFormat())
        {
            return this;
        } else
        {
            return new DeserializationConfig(this, _base.withDateFormat(dateformat));
        }
    }

    public volatile MapperConfig withDateFormat(DateFormat dateformat)
    {
        return withDateFormat(dateformat);
    }

    public DeserializationConfig withHandlerInstantiator(HandlerInstantiator handlerinstantiator)
    {
        if (handlerinstantiator == _base.getHandlerInstantiator())
        {
            return this;
        } else
        {
            return new DeserializationConfig(this, _base.withHandlerInstantiator(handlerinstantiator));
        }
    }

    public volatile MapperConfig withHandlerInstantiator(HandlerInstantiator handlerinstantiator)
    {
        return withHandlerInstantiator(handlerinstantiator);
    }

    public DeserializationConfig withNodeFactory(JsonNodeFactory jsonnodefactory)
    {
        return new DeserializationConfig(this, jsonnodefactory);
    }

    public DeserializationConfig withPropertyNamingStrategy(PropertyNamingStrategy propertynamingstrategy)
    {
        return new DeserializationConfig(this, _base.withPropertyNamingStrategy(propertynamingstrategy));
    }

    public volatile MapperConfig withPropertyNamingStrategy(PropertyNamingStrategy propertynamingstrategy)
    {
        return withPropertyNamingStrategy(propertynamingstrategy);
    }

    public DeserializationConfig withSubtypeResolver(SubtypeResolver subtyperesolver)
    {
        DeserializationConfig deserializationconfig = new DeserializationConfig(this);
        deserializationconfig._subtypeResolver = subtyperesolver;
        return deserializationconfig;
    }

    public volatile MapperConfig withSubtypeResolver(SubtypeResolver subtyperesolver)
    {
        return withSubtypeResolver(subtyperesolver);
    }

    public DeserializationConfig withTypeFactory(TypeFactory typefactory)
    {
        if (typefactory == _base.getTypeFactory())
        {
            return this;
        } else
        {
            return new DeserializationConfig(this, _base.withTypeFactory(typefactory));
        }
    }

    public volatile MapperConfig withTypeFactory(TypeFactory typefactory)
    {
        return withTypeFactory(typefactory);
    }

    public DeserializationConfig withTypeResolverBuilder(TypeResolverBuilder typeresolverbuilder)
    {
        return new DeserializationConfig(this, _base.withTypeResolverBuilder(typeresolverbuilder));
    }

    public volatile MapperConfig withTypeResolverBuilder(TypeResolverBuilder typeresolverbuilder)
    {
        return withTypeResolverBuilder(typeresolverbuilder);
    }

    public DeserializationConfig withVisibilityChecker(VisibilityChecker visibilitychecker)
    {
        return new DeserializationConfig(this, _base.withVisibilityChecker(visibilitychecker));
    }

    public volatile MapperConfig withVisibilityChecker(VisibilityChecker visibilitychecker)
    {
        return withVisibilityChecker(visibilitychecker);
    }

}

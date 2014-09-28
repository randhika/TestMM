// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.map;

import java.text.DateFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.codehaus.jackson.map.introspect.Annotated;
import org.codehaus.jackson.map.introspect.VisibilityChecker;
import org.codehaus.jackson.map.jsontype.SubtypeResolver;
import org.codehaus.jackson.map.jsontype.TypeIdResolver;
import org.codehaus.jackson.map.jsontype.TypeResolverBuilder;
import org.codehaus.jackson.map.jsontype.impl.StdSubtypeResolver;
import org.codehaus.jackson.map.type.ClassKey;
import org.codehaus.jackson.map.type.TypeFactory;
import org.codehaus.jackson.map.util.ClassUtil;
import org.codehaus.jackson.map.util.StdDateFormat;
import org.codehaus.jackson.type.JavaType;

// Referenced classes of package org.codehaus.jackson.map:
//            HandlerInstantiator, ClassIntrospector, AnnotationIntrospector, PropertyNamingStrategy, 
//            BeanDescription

public abstract class MapperConfig
    implements ClassIntrospector.MixInResolver
{
    public static class Base
    {

        protected final AnnotationIntrospector _annotationIntrospector;
        protected final ClassIntrospector _classIntrospector;
        protected final DateFormat _dateFormat;
        protected final HandlerInstantiator _handlerInstantiator;
        protected final PropertyNamingStrategy _propertyNamingStrategy;
        protected final TypeFactory _typeFactory;
        protected final TypeResolverBuilder _typeResolverBuilder;
        protected final VisibilityChecker _visibilityChecker;

        public AnnotationIntrospector getAnnotationIntrospector()
        {
            return _annotationIntrospector;
        }

        public ClassIntrospector getClassIntrospector()
        {
            return _classIntrospector;
        }

        public DateFormat getDateFormat()
        {
            return _dateFormat;
        }

        public HandlerInstantiator getHandlerInstantiator()
        {
            return _handlerInstantiator;
        }

        public PropertyNamingStrategy getPropertyNamingStrategy()
        {
            return _propertyNamingStrategy;
        }

        public TypeFactory getTypeFactory()
        {
            return _typeFactory;
        }

        public TypeResolverBuilder getTypeResolverBuilder()
        {
            return _typeResolverBuilder;
        }

        public VisibilityChecker getVisibilityChecker()
        {
            return _visibilityChecker;
        }

        public Base withAnnotationIntrospector(AnnotationIntrospector annotationintrospector)
        {
            return new Base(_classIntrospector, annotationintrospector, _visibilityChecker, _propertyNamingStrategy, _typeFactory, _typeResolverBuilder, _dateFormat, _handlerInstantiator);
        }

        public Base withClassIntrospector(ClassIntrospector classintrospector)
        {
            return new Base(classintrospector, _annotationIntrospector, _visibilityChecker, _propertyNamingStrategy, _typeFactory, _typeResolverBuilder, _dateFormat, _handlerInstantiator);
        }

        public Base withDateFormat(DateFormat dateformat)
        {
            return new Base(_classIntrospector, _annotationIntrospector, _visibilityChecker, _propertyNamingStrategy, _typeFactory, _typeResolverBuilder, dateformat, _handlerInstantiator);
        }

        public Base withHandlerInstantiator(HandlerInstantiator handlerinstantiator)
        {
            return new Base(_classIntrospector, _annotationIntrospector, _visibilityChecker, _propertyNamingStrategy, _typeFactory, _typeResolverBuilder, _dateFormat, handlerinstantiator);
        }

        public Base withPropertyNamingStrategy(PropertyNamingStrategy propertynamingstrategy)
        {
            return new Base(_classIntrospector, _annotationIntrospector, _visibilityChecker, propertynamingstrategy, _typeFactory, _typeResolverBuilder, _dateFormat, _handlerInstantiator);
        }

        public Base withTypeFactory(TypeFactory typefactory)
        {
            return new Base(_classIntrospector, _annotationIntrospector, _visibilityChecker, _propertyNamingStrategy, typefactory, _typeResolverBuilder, _dateFormat, _handlerInstantiator);
        }

        public Base withTypeResolverBuilder(TypeResolverBuilder typeresolverbuilder)
        {
            return new Base(_classIntrospector, _annotationIntrospector, _visibilityChecker, _propertyNamingStrategy, _typeFactory, typeresolverbuilder, _dateFormat, _handlerInstantiator);
        }

        public Base withVisibilityChecker(VisibilityChecker visibilitychecker)
        {
            return new Base(_classIntrospector, _annotationIntrospector, visibilitychecker, _propertyNamingStrategy, _typeFactory, _typeResolverBuilder, _dateFormat, _handlerInstantiator);
        }

        public Base(ClassIntrospector classintrospector, AnnotationIntrospector annotationintrospector, VisibilityChecker visibilitychecker, PropertyNamingStrategy propertynamingstrategy, TypeFactory typefactory, TypeResolverBuilder typeresolverbuilder, DateFormat dateformat, 
                HandlerInstantiator handlerinstantiator)
        {
            _classIntrospector = classintrospector;
            _annotationIntrospector = annotationintrospector;
            _visibilityChecker = visibilitychecker;
            _propertyNamingStrategy = propertynamingstrategy;
            _typeFactory = typefactory;
            _typeResolverBuilder = typeresolverbuilder;
            _dateFormat = dateformat;
            _handlerInstantiator = handlerinstantiator;
        }
    }


    protected static final DateFormat DEFAULT_DATE_FORMAT;
    protected Base _base;
    protected HashMap _mixInAnnotations;
    protected boolean _mixInAnnotationsShared;
    protected SubtypeResolver _subtypeResolver;

    protected MapperConfig(ClassIntrospector classintrospector, AnnotationIntrospector annotationintrospector, VisibilityChecker visibilitychecker, SubtypeResolver subtyperesolver, PropertyNamingStrategy propertynamingstrategy, TypeFactory typefactory, HandlerInstantiator handlerinstantiator)
    {
        _base = new Base(classintrospector, annotationintrospector, visibilitychecker, propertynamingstrategy, typefactory, null, DEFAULT_DATE_FORMAT, handlerinstantiator);
        _subtypeResolver = subtyperesolver;
        _mixInAnnotationsShared = true;
    }

    protected MapperConfig(MapperConfig mapperconfig)
    {
        this(mapperconfig, mapperconfig._base, mapperconfig._subtypeResolver);
    }

    protected MapperConfig(MapperConfig mapperconfig, Base base, SubtypeResolver subtyperesolver)
    {
        _base = base;
        _subtypeResolver = subtyperesolver;
        _mixInAnnotationsShared = true;
        _mixInAnnotations = mapperconfig._mixInAnnotations;
    }

    public final void addMixInAnnotations(Class class1, Class class2)
    {
        if (_mixInAnnotations != null) goto _L2; else goto _L1
_L1:
        _mixInAnnotationsShared = false;
        _mixInAnnotations = new HashMap();
_L4:
        _mixInAnnotations.put(new ClassKey(class1), class2);
        return;
_L2:
        if (_mixInAnnotationsShared)
        {
            _mixInAnnotationsShared = false;
            _mixInAnnotations = new HashMap(_mixInAnnotations);
        }
        if (true) goto _L4; else goto _L3
_L3:
    }

    public final void appendAnnotationIntrospector(AnnotationIntrospector annotationintrospector)
    {
        _base = _base.withAnnotationIntrospector(AnnotationIntrospector.Pair.create(getAnnotationIntrospector(), annotationintrospector));
    }

    public abstract boolean canOverrideAccessModifiers();

    public final JavaType constructType(Class class1)
    {
        return getTypeFactory().constructType(class1);
    }

    public abstract MapperConfig createUnshared(SubtypeResolver subtyperesolver);

    public abstract MapperConfig createUnshared(TypeResolverBuilder typeresolverbuilder, VisibilityChecker visibilitychecker, SubtypeResolver subtyperesolver);

    public final Class findMixInClassFor(Class class1)
    {
        if (_mixInAnnotations == null)
        {
            return null;
        } else
        {
            return (Class)_mixInAnnotations.get(new ClassKey(class1));
        }
    }

    public abstract void fromAnnotations(Class class1);

    public AnnotationIntrospector getAnnotationIntrospector()
    {
        return _base.getAnnotationIntrospector();
    }

    public ClassIntrospector getClassIntrospector()
    {
        return _base.getClassIntrospector();
    }

    public final DateFormat getDateFormat()
    {
        return _base.getDateFormat();
    }

    public final TypeResolverBuilder getDefaultTyper(JavaType javatype)
    {
        return _base.getTypeResolverBuilder();
    }

    public final VisibilityChecker getDefaultVisibilityChecker()
    {
        return _base.getVisibilityChecker();
    }

    public final HandlerInstantiator getHandlerInstantiator()
    {
        return _base.getHandlerInstantiator();
    }

    public final PropertyNamingStrategy getPropertyNamingStrategy()
    {
        return _base.getPropertyNamingStrategy();
    }

    public final SubtypeResolver getSubtypeResolver()
    {
        if (_subtypeResolver == null)
        {
            _subtypeResolver = new StdSubtypeResolver();
        }
        return _subtypeResolver;
    }

    public final TypeFactory getTypeFactory()
    {
        return _base.getTypeFactory();
    }

    public final void insertAnnotationIntrospector(AnnotationIntrospector annotationintrospector)
    {
        _base = _base.withAnnotationIntrospector(AnnotationIntrospector.Pair.create(annotationintrospector, getAnnotationIntrospector()));
    }

    public abstract BeanDescription introspectClassAnnotations(Class class1);

    public abstract BeanDescription introspectDirectClassAnnotations(Class class1);

    public abstract boolean isAnnotationProcessingEnabled();

    public final int mixInCount()
    {
        if (_mixInAnnotations == null)
        {
            return 0;
        } else
        {
            return _mixInAnnotations.size();
        }
    }

    public final void setAnnotationIntrospector(AnnotationIntrospector annotationintrospector)
    {
        _base = _base.withAnnotationIntrospector(annotationintrospector);
    }

    public void setDateFormat(DateFormat dateformat)
    {
        if (dateformat == null)
        {
            dateformat = StdDateFormat.instance;
        }
        _base = _base.withDateFormat(dateformat);
    }

    public final void setIntrospector(ClassIntrospector classintrospector)
    {
        _base = _base.withClassIntrospector(classintrospector);
    }

    public final void setMixInAnnotations(Map map)
    {
        HashMap hashmap = null;
        if (map != null)
        {
            int i = map.size();
            hashmap = null;
            if (i > 0)
            {
                hashmap = new HashMap(map.size());
                java.util.Map.Entry entry;
                for (Iterator iterator = map.entrySet().iterator(); iterator.hasNext(); hashmap.put(new ClassKey((Class)entry.getKey()), entry.getValue()))
                {
                    entry = (java.util.Map.Entry)iterator.next();
                }

            }
        }
        _mixInAnnotationsShared = false;
        _mixInAnnotations = hashmap;
    }

    public final void setSubtypeResolver(SubtypeResolver subtyperesolver)
    {
        _subtypeResolver = subtyperesolver;
    }

    public TypeIdResolver typeIdResolverInstance(Annotated annotated, Class class1)
    {
        HandlerInstantiator handlerinstantiator = getHandlerInstantiator();
        if (handlerinstantiator != null)
        {
            TypeIdResolver typeidresolver = handlerinstantiator.typeIdResolverInstance(this, annotated, class1);
            if (typeidresolver != null)
            {
                return typeidresolver;
            }
        }
        return (TypeIdResolver)ClassUtil.createInstance(class1, canOverrideAccessModifiers());
    }

    public TypeResolverBuilder typeResolverBuilderInstance(Annotated annotated, Class class1)
    {
        HandlerInstantiator handlerinstantiator = getHandlerInstantiator();
        if (handlerinstantiator != null)
        {
            TypeResolverBuilder typeresolverbuilder = handlerinstantiator.typeResolverBuilderInstance(this, annotated, class1);
            if (typeresolverbuilder != null)
            {
                return typeresolverbuilder;
            }
        }
        return (TypeResolverBuilder)ClassUtil.createInstance(class1, canOverrideAccessModifiers());
    }

    public abstract MapperConfig withAnnotationIntrospector(AnnotationIntrospector annotationintrospector);

    public abstract MapperConfig withClassIntrospector(ClassIntrospector classintrospector);

    public abstract MapperConfig withDateFormat(DateFormat dateformat);

    public abstract MapperConfig withHandlerInstantiator(HandlerInstantiator handlerinstantiator);

    public abstract MapperConfig withPropertyNamingStrategy(PropertyNamingStrategy propertynamingstrategy);

    public abstract MapperConfig withSubtypeResolver(SubtypeResolver subtyperesolver);

    public abstract MapperConfig withTypeFactory(TypeFactory typefactory);

    public abstract MapperConfig withTypeResolverBuilder(TypeResolverBuilder typeresolverbuilder);

    public abstract MapperConfig withVisibilityChecker(VisibilityChecker visibilitychecker);

    static 
    {
        DEFAULT_DATE_FORMAT = StdDateFormat.instance;
    }
}

// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.jaxrs;

import java.util.ArrayList;
import org.codehaus.jackson.map.AnnotationIntrospector;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.introspect.JacksonAnnotationIntrospector;
import org.codehaus.jackson.xc.JaxbAnnotationIntrospector;

// Referenced classes of package org.codehaus.jackson.jaxrs:
//            Annotations

public class MapperConfigurator
{

    protected Annotations _defaultAnnotationsToUse[];
    protected ObjectMapper _defaultMapper;
    protected Class _jaxbIntrospectorClass;
    protected ObjectMapper _mapper;

    public MapperConfigurator(ObjectMapper objectmapper, Annotations aannotations[])
    {
        _mapper = objectmapper;
        _defaultAnnotationsToUse = aannotations;
    }

    protected AnnotationIntrospector _resolveIntrospector(Annotations annotations)
    {
        static class _cls1
        {

            static final int $SwitchMap$org$codehaus$jackson$jaxrs$Annotations[];

            static 
            {
                $SwitchMap$org$codehaus$jackson$jaxrs$Annotations = new int[Annotations.values().length];
                try
                {
                    $SwitchMap$org$codehaus$jackson$jaxrs$Annotations[Annotations.JACKSON.ordinal()] = 1;
                }
                catch (NoSuchFieldError nosuchfielderror) { }
                try
                {
                    $SwitchMap$org$codehaus$jackson$jaxrs$Annotations[Annotations.JAXB.ordinal()] = 2;
                }
                catch (NoSuchFieldError nosuchfielderror1)
                {
                    return;
                }
            }
        }

        switch (_cls1..SwitchMap.org.codehaus.jackson.jaxrs.Annotations[annotations.ordinal()])
        {
        default:
            throw new IllegalStateException();

        case 1: // '\001'
            return new JacksonAnnotationIntrospector();

        case 2: // '\002'
            break;
        }
        AnnotationIntrospector annotationintrospector;
        try
        {
            if (_jaxbIntrospectorClass == null)
            {
                _jaxbIntrospectorClass = org/codehaus/jackson/xc/JaxbAnnotationIntrospector;
            }
            annotationintrospector = (AnnotationIntrospector)_jaxbIntrospectorClass.newInstance();
        }
        catch (Exception exception)
        {
            throw new IllegalStateException((new StringBuilder()).append("Failed to instantiate JaxbAnnotationIntrospector: ").append(exception.getMessage()).toString(), exception);
        }
        return annotationintrospector;
    }

    protected AnnotationIntrospector _resolveIntrospectors(Annotations aannotations[])
    {
        ArrayList arraylist = new ArrayList();
        int i = aannotations.length;
        for (int j = 0; j < i; j++)
        {
            Annotations annotations = aannotations[j];
            if (annotations != null)
            {
                arraylist.add(_resolveIntrospector(annotations));
            }
        }

        AnnotationIntrospector annotationintrospector;
        if (arraylist.size() == 0)
        {
            annotationintrospector = AnnotationIntrospector.nopInstance();
        } else
        {
            annotationintrospector = (AnnotationIntrospector)arraylist.get(0);
            int k = 1;
            int l = arraylist.size();
            while (k < l) 
            {
                annotationintrospector = AnnotationIntrospector.pair(annotationintrospector, (AnnotationIntrospector)arraylist.get(k));
                k++;
            }
        }
        return annotationintrospector;
    }

    protected void _setAnnotations(ObjectMapper objectmapper, Annotations aannotations[])
    {
        AnnotationIntrospector annotationintrospector;
        if (aannotations == null || aannotations.length == 0)
        {
            annotationintrospector = AnnotationIntrospector.nopInstance();
        } else
        {
            annotationintrospector = _resolveIntrospectors(aannotations);
        }
        objectmapper.getDeserializationConfig().setAnnotationIntrospector(annotationintrospector);
        objectmapper.getSerializationConfig().setAnnotationIntrospector(annotationintrospector);
    }

    public void configure(org.codehaus.jackson.JsonGenerator.Feature feature, boolean flag)
    {
        this;
        JVM INSTR monitorenter ;
        mapper().configure(feature, flag);
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public void configure(org.codehaus.jackson.JsonParser.Feature feature, boolean flag)
    {
        this;
        JVM INSTR monitorenter ;
        mapper().configure(feature, flag);
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public void configure(org.codehaus.jackson.map.DeserializationConfig.Feature feature, boolean flag)
    {
        this;
        JVM INSTR monitorenter ;
        mapper().configure(feature, flag);
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public void configure(org.codehaus.jackson.map.SerializationConfig.Feature feature, boolean flag)
    {
        this;
        JVM INSTR monitorenter ;
        mapper().configure(feature, flag);
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public ObjectMapper getConfiguredMapper()
    {
        this;
        JVM INSTR monitorenter ;
        ObjectMapper objectmapper = _mapper;
        this;
        JVM INSTR monitorexit ;
        return objectmapper;
        Exception exception;
        exception;
        throw exception;
    }

    public ObjectMapper getDefaultMapper()
    {
        this;
        JVM INSTR monitorenter ;
        ObjectMapper objectmapper;
        if (_defaultMapper == null)
        {
            _defaultMapper = new ObjectMapper();
            _setAnnotations(_defaultMapper, _defaultAnnotationsToUse);
        }
        objectmapper = _defaultMapper;
        this;
        JVM INSTR monitorexit ;
        return objectmapper;
        Exception exception;
        exception;
        throw exception;
    }

    protected ObjectMapper mapper()
    {
        if (_mapper == null)
        {
            _mapper = new ObjectMapper();
            _setAnnotations(_mapper, _defaultAnnotationsToUse);
        }
        return _mapper;
    }

    public void setAnnotationsToUse(Annotations aannotations[])
    {
        this;
        JVM INSTR monitorenter ;
        _setAnnotations(mapper(), aannotations);
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public void setMapper(ObjectMapper objectmapper)
    {
        this;
        JVM INSTR monitorenter ;
        _mapper = objectmapper;
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }
}

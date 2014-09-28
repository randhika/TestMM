// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.map.introspect;

import java.lang.annotation.Annotation;
import java.util.HashMap;
import org.codehaus.jackson.map.util.Annotations;

public final class AnnotationMap
    implements Annotations
{

    protected HashMap _annotations;

    public AnnotationMap()
    {
    }

    protected final void _add(Annotation annotation)
    {
        if (_annotations == null)
        {
            _annotations = new HashMap();
        }
        _annotations.put(annotation.annotationType(), annotation);
    }

    public void add(Annotation annotation)
    {
        _add(annotation);
    }

    public void addIfNotPresent(Annotation annotation)
    {
        if (_annotations == null || !_annotations.containsKey(annotation.annotationType()))
        {
            _add(annotation);
        }
    }

    public Annotation get(Class class1)
    {
        if (_annotations == null)
        {
            return null;
        } else
        {
            return (Annotation)_annotations.get(class1);
        }
    }

    public int size()
    {
        if (_annotations == null)
        {
            return 0;
        } else
        {
            return _annotations.size();
        }
    }

    public String toString()
    {
        if (_annotations == null)
        {
            return "[null]";
        } else
        {
            return _annotations.toString();
        }
    }
}

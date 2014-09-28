// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.map.jsontype.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import org.codehaus.jackson.map.AnnotationIntrospector;
import org.codehaus.jackson.map.MapperConfig;
import org.codehaus.jackson.map.introspect.AnnotatedClass;
import org.codehaus.jackson.map.introspect.AnnotatedMember;
import org.codehaus.jackson.map.jsontype.NamedType;
import org.codehaus.jackson.map.jsontype.SubtypeResolver;

public class StdSubtypeResolver extends SubtypeResolver
{

    protected LinkedHashSet _registeredSubtypes;

    public StdSubtypeResolver()
    {
    }

    protected void _collectAndResolve(AnnotatedClass annotatedclass, NamedType namedtype, MapperConfig mapperconfig, AnnotationIntrospector annotationintrospector, HashMap hashmap)
    {
        if (!namedtype.hasName())
        {
            String s = annotationintrospector.findTypeName(annotatedclass);
            if (s != null)
            {
                namedtype = new NamedType(namedtype.getType(), s);
            }
        }
        if (!hashmap.containsKey(namedtype)) goto _L2; else goto _L1
_L1:
        if (namedtype.hasName() && !((NamedType)hashmap.get(namedtype)).hasName())
        {
            hashmap.put(namedtype, namedtype);
        }
_L4:
        return;
_L2:
        hashmap.put(namedtype, namedtype);
        java.util.List list = annotationintrospector.findSubtypes(annotatedclass);
        if (list != null && !list.isEmpty())
        {
            Iterator iterator = list.iterator();
            while (iterator.hasNext()) 
            {
                NamedType namedtype1 = (NamedType)iterator.next();
                AnnotatedClass annotatedclass1 = AnnotatedClass.constructWithoutSuperTypes(namedtype1.getType(), annotationintrospector, mapperconfig);
                if (!namedtype1.hasName())
                {
                    namedtype1 = new NamedType(namedtype1.getType(), annotationintrospector.findTypeName(annotatedclass1));
                }
                _collectAndResolve(annotatedclass1, namedtype1, mapperconfig, annotationintrospector, hashmap);
            }
        }
        if (true) goto _L4; else goto _L3
_L3:
    }

    public Collection collectAndResolveSubtypes(AnnotatedClass annotatedclass, MapperConfig mapperconfig, AnnotationIntrospector annotationintrospector)
    {
        HashMap hashmap = new HashMap();
        if (_registeredSubtypes != null)
        {
            Class class1 = annotatedclass.getRawType();
            Iterator iterator = _registeredSubtypes.iterator();
            do
            {
                if (!iterator.hasNext())
                {
                    break;
                }
                NamedType namedtype = (NamedType)iterator.next();
                if (class1.isAssignableFrom(namedtype.getType()))
                {
                    _collectAndResolve(AnnotatedClass.constructWithoutSuperTypes(namedtype.getType(), annotationintrospector, mapperconfig), namedtype, mapperconfig, annotationintrospector, hashmap);
                }
            } while (true);
        }
        _collectAndResolve(annotatedclass, new NamedType(annotatedclass.getRawType(), null), mapperconfig, annotationintrospector, hashmap);
        return new ArrayList(hashmap.values());
    }

    public Collection collectAndResolveSubtypes(AnnotatedMember annotatedmember, MapperConfig mapperconfig, AnnotationIntrospector annotationintrospector)
    {
        HashMap hashmap = new HashMap();
        if (_registeredSubtypes != null)
        {
            Class class1 = annotatedmember.getRawType();
            Iterator iterator1 = _registeredSubtypes.iterator();
            do
            {
                if (!iterator1.hasNext())
                {
                    break;
                }
                NamedType namedtype2 = (NamedType)iterator1.next();
                if (class1.isAssignableFrom(namedtype2.getType()))
                {
                    _collectAndResolve(AnnotatedClass.constructWithoutSuperTypes(namedtype2.getType(), annotationintrospector, mapperconfig), namedtype2, mapperconfig, annotationintrospector, hashmap);
                }
            } while (true);
        }
        java.util.List list = annotationintrospector.findSubtypes(annotatedmember);
        if (list != null)
        {
            NamedType namedtype1;
            for (Iterator iterator = list.iterator(); iterator.hasNext(); _collectAndResolve(AnnotatedClass.constructWithoutSuperTypes(namedtype1.getType(), annotationintrospector, mapperconfig), namedtype1, mapperconfig, annotationintrospector, hashmap))
            {
                namedtype1 = (NamedType)iterator.next();
            }

        }
        NamedType namedtype = new NamedType(annotatedmember.getRawType(), null);
        _collectAndResolve(AnnotatedClass.constructWithoutSuperTypes(annotatedmember.getRawType(), annotationintrospector, mapperconfig), namedtype, mapperconfig, annotationintrospector, hashmap);
        return new ArrayList(hashmap.values());
    }

    public transient void registerSubtypes(Class aclass[])
    {
        NamedType anamedtype[] = new NamedType[aclass.length];
        int i = 0;
        for (int j = aclass.length; i < j; i++)
        {
            anamedtype[i] = new NamedType(aclass[i]);
        }

        registerSubtypes(anamedtype);
    }

    public transient void registerSubtypes(NamedType anamedtype[])
    {
        if (_registeredSubtypes == null)
        {
            _registeredSubtypes = new LinkedHashSet();
        }
        int i = anamedtype.length;
        for (int j = 0; j < i; j++)
        {
            NamedType namedtype = anamedtype[j];
            _registeredSubtypes.add(namedtype);
        }

    }
}

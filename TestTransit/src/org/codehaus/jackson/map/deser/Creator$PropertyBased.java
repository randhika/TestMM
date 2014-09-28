// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.map.deser;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashMap;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.introspect.AnnotatedConstructor;
import org.codehaus.jackson.map.introspect.AnnotatedMethod;
import org.codehaus.jackson.map.util.ClassUtil;
import org.codehaus.jackson.type.JavaType;

// Referenced classes of package org.codehaus.jackson.map.deser:
//            SettableBeanProperty, PropertyValueBuffer, PropertyValue, Creator

static final class _defaultValues
{

    protected final Constructor _ctor;
    protected final Object _defaultValues[];
    protected final Method _factoryMethod;
    protected final HashMap _properties = new HashMap();

    public Object build(PropertyValueBuffer propertyvaluebuffer)
        throws Exception
    {
        Object obj = null;
        if (_ctor == null) goto _L2; else goto _L1
_L1:
        Object obj2 = _ctor.newInstance(propertyvaluebuffer.getParameters(_defaultValues));
        obj = obj2;
_L4:
        for (PropertyValue propertyvalue = propertyvaluebuffer.buffered(); propertyvalue != null; propertyvalue = propertyvalue.next)
        {
            propertyvalue.assign(obj);
        }

        break; /* Loop/switch isn't completed */
_L2:
        Object obj1 = _factoryMethod.invoke(null, propertyvaluebuffer.getParameters(_defaultValues));
        obj = obj1;
        if (true) goto _L4; else goto _L3
        Exception exception;
        exception;
        ClassUtil.throwRootCause(exception);
_L3:
        return obj;
    }

    public SettableBeanProperty findCreatorProperty(String s)
    {
        return (SettableBeanProperty)_properties.get(s);
    }

    public Collection properties()
    {
        return _properties.values();
    }

    public PropertyValueBuffer startBuilding(JsonParser jsonparser, DeserializationContext deserializationcontext)
    {
        return new PropertyValueBuffer(jsonparser, deserializationcontext, _properties.size());
    }

    public tor(AnnotatedConstructor annotatedconstructor, SettableBeanProperty asettablebeanproperty[], AnnotatedMethod annotatedmethod, SettableBeanProperty asettablebeanproperty1[])
    {
        SettableBeanProperty asettablebeanproperty2[];
        Object aobj[];
        int i;
        if (annotatedconstructor != null)
        {
            _ctor = annotatedconstructor.getAnnotated();
            _factoryMethod = null;
            asettablebeanproperty2 = asettablebeanproperty;
        } else
        if (annotatedmethod != null)
        {
            _ctor = null;
            _factoryMethod = annotatedmethod.getAnnotated();
            asettablebeanproperty2 = asettablebeanproperty1;
        } else
        {
            throw new IllegalArgumentException("Internal error: neither delegating constructor nor factory method passed");
        }
        aobj = null;
        i = 0;
        for (int j = asettablebeanproperty2.length; i < j; i++)
        {
            SettableBeanProperty settablebeanproperty = asettablebeanproperty2[i];
            _properties.put(settablebeanproperty.getName(), settablebeanproperty);
            if (settablebeanproperty.getType().isPrimitive())
            {
                if (aobj == null)
                {
                    aobj = new Object[j];
                }
                aobj[i] = ClassUtil.defaultValue(settablebeanproperty.getType().getRawClass());
            }
        }

        _defaultValues = aobj;
    }
}

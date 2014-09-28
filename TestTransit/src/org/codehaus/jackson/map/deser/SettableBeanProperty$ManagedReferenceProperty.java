// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.map.deser;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.introspect.AnnotatedMember;
import org.codehaus.jackson.map.util.Annotations;

// Referenced classes of package org.codehaus.jackson.map.deser:
//            SettableBeanProperty

public static final class _isContainer extends SettableBeanProperty
{

    protected final SettableBeanProperty _backProperty;
    protected final boolean _isContainer;
    protected final SettableBeanProperty _managedProperty;
    protected final String _referenceName;

    public void deserializeAndSet(JsonParser jsonparser, DeserializationContext deserializationcontext, Object obj)
        throws IOException, JsonProcessingException
    {
        set(obj, _managedProperty.deserialize(jsonparser, deserializationcontext));
    }

    public Annotation getAnnotation(Class class1)
    {
        return _managedProperty.getAnnotation(class1);
    }

    public AnnotatedMember getMember()
    {
        return _managedProperty.getMember();
    }

    public final void set(Object obj, Object obj1)
        throws IOException
    {
        _managedProperty.set(obj, obj1);
        if (obj1 != null)
        {
            if (_isContainer)
            {
                if (obj1 instanceof Object[])
                {
                    Object aobj[] = (Object[])(Object[])obj1;
                    int i = aobj.length;
                    for (int j = 0; j < i; j++)
                    {
                        Object obj4 = aobj[j];
                        if (obj4 != null)
                        {
                            _backProperty.set(obj4, obj);
                        }
                    }

                } else
                if (obj1 instanceof Collection)
                {
                    Iterator iterator1 = ((Collection)obj1).iterator();
                    do
                    {
                        if (!iterator1.hasNext())
                        {
                            break;
                        }
                        Object obj3 = iterator1.next();
                        if (obj3 != null)
                        {
                            _backProperty.set(obj3, obj);
                        }
                    } while (true);
                } else
                if (obj1 instanceof Map)
                {
                    Iterator iterator = ((Map)obj1).values().iterator();
                    do
                    {
                        if (!iterator.hasNext())
                        {
                            break;
                        }
                        Object obj2 = iterator.next();
                        if (obj2 != null)
                        {
                            _backProperty.set(obj2, obj);
                        }
                    } while (true);
                } else
                {
                    throw new IllegalStateException((new StringBuilder()).append("Unsupported container type (").append(obj1.getClass().getName()).append(") when resolving reference '").append(_referenceName).append("'").toString());
                }
            } else
            {
                _backProperty.set(obj1, obj);
            }
        }
    }

    public (String s, SettableBeanProperty settablebeanproperty, SettableBeanProperty settablebeanproperty1, Annotations annotations, boolean flag)
    {
        super(settablebeanproperty.getName(), settablebeanproperty.getType(), settablebeanproperty._valueTypeDeserializer, annotations);
        _referenceName = s;
        _managedProperty = settablebeanproperty;
        _backProperty = settablebeanproperty1;
        _isContainer = flag;
    }
}

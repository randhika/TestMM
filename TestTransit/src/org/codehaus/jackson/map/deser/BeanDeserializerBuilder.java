// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.map.deser;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import org.codehaus.jackson.map.BeanProperty;
import org.codehaus.jackson.map.JsonDeserializer;
import org.codehaus.jackson.map.deser.impl.BeanPropertyMap;
import org.codehaus.jackson.map.introspect.BasicBeanDescription;

// Referenced classes of package org.codehaus.jackson.map.deser:
//            SettableBeanProperty, BeanDeserializer, SettableAnyProperty, CreatorContainer

public class BeanDeserializerBuilder
{

    protected SettableAnyProperty _anySetter;
    protected HashMap _backRefProperties;
    protected final BasicBeanDescription _beanDesc;
    protected CreatorContainer _creators;
    protected HashSet _ignorableProps;
    protected boolean _ignoreAllUnknown;
    protected final HashMap _properties = new HashMap();

    public BeanDeserializerBuilder(BasicBeanDescription basicbeandescription)
    {
        _beanDesc = basicbeandescription;
    }

    public void addBackReferenceProperty(String s, SettableBeanProperty settablebeanproperty)
    {
        if (_backRefProperties == null)
        {
            _backRefProperties = new HashMap(4);
        }
        _backRefProperties.put(s, settablebeanproperty);
    }

    public void addIgnorable(String s)
    {
        if (_ignorableProps == null)
        {
            _ignorableProps = new HashSet();
        }
        _ignorableProps.add(s);
    }

    public void addOrReplaceProperty(SettableBeanProperty settablebeanproperty, boolean flag)
    {
        _properties.put(settablebeanproperty.getName(), settablebeanproperty);
    }

    public void addProperty(SettableBeanProperty settablebeanproperty)
    {
        SettableBeanProperty settablebeanproperty1 = (SettableBeanProperty)_properties.put(settablebeanproperty.getName(), settablebeanproperty);
        if (settablebeanproperty1 != null && settablebeanproperty1 != settablebeanproperty)
        {
            throw new IllegalArgumentException((new StringBuilder()).append("Duplicate property '").append(settablebeanproperty.getName()).append("' for ").append(_beanDesc.getType()).toString());
        } else
        {
            return;
        }
    }

    public JsonDeserializer build(BeanProperty beanproperty)
    {
        BeanPropertyMap beanpropertymap = new BeanPropertyMap(_properties.values());
        beanpropertymap.assignIndexes();
        return new BeanDeserializer(_beanDesc.getClassInfo(), _beanDesc.getType(), beanproperty, _creators, beanpropertymap, _backRefProperties, _ignorableProps, _ignoreAllUnknown, _anySetter);
    }

    public Iterator getProperties()
    {
        return _properties.values().iterator();
    }

    public boolean hasProperty(String s)
    {
        return _properties.containsKey(s);
    }

    public SettableBeanProperty removeProperty(String s)
    {
        return (SettableBeanProperty)_properties.remove(s);
    }

    public void setAnySetter(SettableAnyProperty settableanyproperty)
    {
        if (_anySetter != null && settableanyproperty != null)
        {
            throw new IllegalStateException("_anySetter already set to non-null");
        } else
        {
            _anySetter = settableanyproperty;
            return;
        }
    }

    public void setCreators(CreatorContainer creatorcontainer)
    {
        _creators = creatorcontainer;
    }

    public void setIgnoreUnknownProperties(boolean flag)
    {
        _ignoreAllUnknown = flag;
    }
}

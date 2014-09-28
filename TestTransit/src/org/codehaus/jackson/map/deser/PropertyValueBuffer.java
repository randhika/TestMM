// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.map.deser;

import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.DeserializationContext;

// Referenced classes of package org.codehaus.jackson.map.deser:
//            PropertyValue, SettableAnyProperty, SettableBeanProperty

public final class PropertyValueBuffer
{

    private PropertyValue _buffered;
    final DeserializationContext _context;
    final Object _creatorParameters[];
    private int _paramsNeeded;
    final JsonParser _parser;

    public PropertyValueBuffer(JsonParser jsonparser, DeserializationContext deserializationcontext, int i)
    {
        _parser = jsonparser;
        _context = deserializationcontext;
        _paramsNeeded = i;
        _creatorParameters = new Object[i];
    }

    public boolean assignParameter(int i, Object obj)
    {
        _creatorParameters[i] = obj;
        int j = -1 + _paramsNeeded;
        _paramsNeeded = j;
        return j <= 0;
    }

    public void bufferAnyProperty(SettableAnyProperty settableanyproperty, String s, Object obj)
    {
        _buffered = new PropertyValue.Any(_buffered, obj, settableanyproperty, s);
    }

    public void bufferMapProperty(Object obj, Object obj1)
    {
        _buffered = new PropertyValue.Map(_buffered, obj1, obj);
    }

    public void bufferProperty(SettableBeanProperty settablebeanproperty, Object obj)
    {
        _buffered = new PropertyValue.Regular(_buffered, obj, settablebeanproperty);
    }

    protected PropertyValue buffered()
    {
        return _buffered;
    }

    protected final Object[] getParameters(Object aobj[])
    {
        if (aobj != null)
        {
            int i = 0;
            for (int j = _creatorParameters.length; i < j; i++)
            {
                if (_creatorParameters[i] != null)
                {
                    continue;
                }
                Object obj = aobj[i];
                if (obj != null)
                {
                    _creatorParameters[i] = obj;
                }
            }

        }
        return _creatorParameters;
    }
}

// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.map.util;

import java.util.Iterator;
import java.util.NoSuchElementException;

// Referenced classes of package org.codehaus.jackson.map.util:
//            ArrayBuilders

private static final class _index
    implements Iterator, Iterable
{

    private final Object _array[];
    private int _index;

    public boolean hasNext()
    {
        return _index < _array.length;
    }

    public Iterator iterator()
    {
        return this;
    }

    public Object next()
    {
        if (_index >= _array.length)
        {
            throw new NoSuchElementException();
        } else
        {
            Object aobj[] = _array;
            int i = _index;
            _index = i + 1;
            return aobj[i];
        }
    }

    public void remove()
    {
        throw new UnsupportedOperationException();
    }

    public (Object aobj[])
    {
        _array = aobj;
        _index = 0;
    }
}

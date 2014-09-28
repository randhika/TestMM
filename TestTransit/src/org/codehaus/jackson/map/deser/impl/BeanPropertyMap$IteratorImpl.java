// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.map.deser.impl;

import java.util.Iterator;
import java.util.NoSuchElementException;
import org.codehaus.jackson.map.deser.SettableBeanProperty;

// Referenced classes of package org.codehaus.jackson.map.deser.impl:
//            BeanPropertyMap

private static final class _nextBucketIndex
    implements Iterator
{

    private final _currentBucket _buckets[];
    private _currentBucket _currentBucket;
    private int _nextBucketIndex;

    public boolean hasNext()
    {
        return _currentBucket != null;
    }

    public volatile Object next()
    {
        return next();
    }

    public SettableBeanProperty next()
    {
        next next1 = _currentBucket;
        if (next1 == null)
        {
            throw new NoSuchElementException();
        }
        next next2;
        next anext[];
        int i;
        for (next2 = next1._currentBucket; next2 == null && _nextBucketIndex < _buckets.length; next2 = anext[i])
        {
            anext = _buckets;
            i = _nextBucketIndex;
            _nextBucketIndex = i + 1;
        }

        _currentBucket = next2;
        return next1._currentBucket;
    }

    public void remove()
    {
        throw new UnsupportedOperationException();
    }

    public ( a[])
    {
        int i;
        int j;
        _buckets = a;
        i = _buckets.length;
        j = 0;
_L3:
        int k;
         ;
        if (j >= i)
        {
            break MISSING_BLOCK_LABEL_63;
        }
         a1[] = _buckets;
        k = j + 1;
         = a1[j];
        if ( == null) goto _L2; else goto _L1
_L1:
        _currentBucket = ;
_L4:
        _nextBucketIndex = k;
        return;
_L2:
        j = k;
          goto _L3
        k = j;
          goto _L4
    }
}

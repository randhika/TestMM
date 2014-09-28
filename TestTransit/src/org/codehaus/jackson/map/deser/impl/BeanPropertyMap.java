// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.map.deser.impl;

import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import org.codehaus.jackson.map.deser.SettableBeanProperty;

public final class BeanPropertyMap
{
    private static final class Bucket
    {

        public final String key;
        public final Bucket next;
        public final SettableBeanProperty value;

        public Bucket(Bucket bucket, String s, SettableBeanProperty settablebeanproperty)
        {
            next = bucket;
            key = s;
            value = settablebeanproperty;
        }
    }

    private static final class IteratorImpl
        implements Iterator
    {

        private final Bucket _buckets[];
        private Bucket _currentBucket;
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
            Bucket bucket = _currentBucket;
            if (bucket == null)
            {
                throw new NoSuchElementException();
            }
            Bucket bucket1;
            Bucket abucket[];
            int i;
            for (bucket1 = bucket.next; bucket1 == null && _nextBucketIndex < _buckets.length; bucket1 = abucket[i])
            {
                abucket = _buckets;
                i = _nextBucketIndex;
                _nextBucketIndex = i + 1;
            }

            _currentBucket = bucket1;
            return bucket.value;
        }

        public void remove()
        {
            throw new UnsupportedOperationException();
        }

        public IteratorImpl(Bucket abucket[])
        {
            int i;
            int j;
            _buckets = abucket;
            i = _buckets.length;
            j = 0;
_L3:
            int k;
            Bucket bucket;
            if (j >= i)
            {
                break MISSING_BLOCK_LABEL_63;
            }
            Bucket abucket1[] = _buckets;
            k = j + 1;
            bucket = abucket1[j];
            if (bucket == null) goto _L2; else goto _L1
_L1:
            _currentBucket = bucket;
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


    private final Bucket _buckets[];
    private final int _hashMask;
    private final int _size;

    public BeanPropertyMap(Collection collection)
    {
        _size = collection.size();
        int i = findSize(_size);
        _hashMask = i - 1;
        Bucket abucket[] = new Bucket[i];
        for (Iterator iterator = collection.iterator(); iterator.hasNext();)
        {
            SettableBeanProperty settablebeanproperty = (SettableBeanProperty)iterator.next();
            String s = settablebeanproperty.getName();
            int j = s.hashCode() & _hashMask;
            abucket[j] = new Bucket(abucket[j], s, settablebeanproperty);
        }

        _buckets = abucket;
    }

    private SettableBeanProperty _findWithEquals(String s, int i)
    {
        for (Bucket bucket = _buckets[i]; bucket != null; bucket = bucket.next)
        {
            if (s.equals(bucket.key))
            {
                return bucket.value;
            }
        }

        return null;
    }

    private static final int findSize(int i)
    {
        int j;
        int k;
        if (i <= 32)
        {
            j = i + i;
        } else
        {
            j = i + (i >> 2);
        }
        for (k = 2; k < j; k += k) { }
        return k;
    }

    public Iterator allProperties()
    {
        return new IteratorImpl(_buckets);
    }

    public void assignIndexes()
    {
        int i = 0;
        Bucket abucket[] = _buckets;
        int j = abucket.length;
        for (int k = 0; k < j;)
        {
            Bucket bucket = abucket[k];
            int l;
            int i1;
            for (l = i; bucket != null; l = i1)
            {
                SettableBeanProperty settablebeanproperty = bucket.value;
                i1 = l + 1;
                settablebeanproperty.assignIndex(l);
                bucket = bucket.next;
            }

            k++;
            i = l;
        }

    }

    public SettableBeanProperty find(String s)
    {
        int i = s.hashCode() & _hashMask;
        Bucket bucket = _buckets[i];
        if (bucket == null)
        {
            return null;
        }
        if (bucket.key == s)
        {
            return bucket.value;
        }
        do
        {
            bucket = bucket.next;
            if (bucket != null)
            {
                if (bucket.key == s)
                {
                    return bucket.value;
                }
            } else
            {
                return _findWithEquals(s, i);
            }
        } while (true);
    }

    public void replace(SettableBeanProperty settablebeanproperty)
    {
        String s = settablebeanproperty.getName();
        int i = s.hashCode() & -1 + _buckets.length;
        boolean flag = false;
        Bucket bucket = _buckets[i];
        Bucket bucket1 = null;
        while (bucket != null) 
        {
            Bucket bucket2;
            if (!flag && bucket.key.equals(s))
            {
                flag = true;
                bucket2 = bucket1;
            } else
            {
                bucket2 = new Bucket(bucket1, bucket.key, bucket.value);
            }
            bucket = bucket.next;
            bucket1 = bucket2;
        }
        if (!flag)
        {
            throw new NoSuchElementException((new StringBuilder()).append("No entry '").append(settablebeanproperty).append("' found, can't replace").toString());
        } else
        {
            _buckets[i] = new Bucket(bucket1, s, settablebeanproperty);
            return;
        }
    }

    public int size()
    {
        return _size;
    }
}

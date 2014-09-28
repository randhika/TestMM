// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.map.ser.impl;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.codehaus.jackson.map.JsonSerializer;

public class JsonSerializerMap
{
    private static final class Bucket
    {

        public final org.codehaus.jackson.map.ser.SerializerCache.TypeKey key;
        public final Bucket next;
        public final JsonSerializer value;

        public Bucket(Bucket bucket, org.codehaus.jackson.map.ser.SerializerCache.TypeKey typekey, JsonSerializer jsonserializer)
        {
            next = bucket;
            key = typekey;
            value = jsonserializer;
        }
    }


    private final Bucket _buckets[];
    private final int _size;

    public JsonSerializerMap(Map map)
    {
        int i = findSize(map.size());
        _size = i;
        int j = i - 1;
        Bucket abucket[] = new Bucket[i];
        for (Iterator iterator = map.entrySet().iterator(); iterator.hasNext();)
        {
            java.util.Map.Entry entry = (java.util.Map.Entry)iterator.next();
            org.codehaus.jackson.map.ser.SerializerCache.TypeKey typekey = (org.codehaus.jackson.map.ser.SerializerCache.TypeKey)entry.getKey();
            int k = j & typekey.hashCode();
            abucket[k] = new Bucket(abucket[k], typekey, (JsonSerializer)entry.getValue());
        }

        _buckets = abucket;
    }

    private static final int findSize(int i)
    {
        int j;
        int k;
        if (i <= 64)
        {
            j = i + i;
        } else
        {
            j = i + (i >> 2);
        }
        for (k = 8; k < j; k += k) { }
        return k;
    }

    public JsonSerializer find(org.codehaus.jackson.map.ser.SerializerCache.TypeKey typekey)
    {
        Bucket bucket;
        int i = typekey.hashCode() & -1 + _buckets.length;
        bucket = _buckets[i];
        if (bucket != null) goto _L2; else goto _L1
_L1:
        return null;
_L2:
        if (typekey.equals(bucket.key))
        {
            return bucket.value;
        }
        do
        {
            bucket = bucket.next;
            if (bucket == null)
            {
                continue; /* Loop/switch isn't completed */
            }
            if (typekey.equals(bucket.key))
            {
                return bucket.value;
            }
        } while (true);
        if (true) goto _L1; else goto _L3
_L3:
    }

    public int size()
    {
        return _size;
    }
}

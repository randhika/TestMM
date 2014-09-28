// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.common;

import android.util.Log;
import java.io.Serializable;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

public class YSoftHashMap extends AbstractMap
    implements Serializable
{

    private static final String TAG = "YSoftHashMap";
    private final Map hash = new HashMap();
    private final ReferenceQueue queue = new ReferenceQueue();
    private final Map reverseLookup = new HashMap();

    public YSoftHashMap()
    {
    }

    private void expungeStaleEntries()
    {
        do
        {
            java.lang.ref.Reference reference = queue.poll();
            if (reference != null)
            {
                Log.i("YSoftHashMap", (new StringBuilder()).append("CACHE Expunged: ").append(hash.remove(reverseLookup.remove(reference))).toString());
            } else
            {
                return;
            }
        } while (true);
    }

    public void clear()
    {
        this;
        JVM INSTR monitorenter ;
        hash.clear();
        reverseLookup.clear();
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public Set entrySet()
    {
        this;
        JVM INSTR monitorenter ;
        LinkedHashSet linkedhashset;
        Iterator iterator;
        expungeStaleEntries();
        linkedhashset = new LinkedHashSet();
        iterator = hash.entrySet().iterator();
_L2:
        final java.util.Map.Entry entry;
        final Object value;
        do
        {
            if (!iterator.hasNext())
            {
                break MISSING_BLOCK_LABEL_96;
            }
            entry = (java.util.Map.Entry)iterator.next();
            value = ((SoftReference)entry.getValue()).get();
        } while (value == null);
        linkedhashset.add(new java.util.Map.Entry() {

            final YSoftHashMap this$0;
            final java.util.Map.Entry val$entry;
            final Object val$value;

            public Object getKey()
            {
                return entry.getKey();
            }

            public Object getValue()
            {
                return value;
            }

            public Object setValue(Object obj)
            {
                entry.setValue(new SoftReference(obj, queue));
                return value;
            }

            
            {
                this$0 = YSoftHashMap.this;
                entry = entry1;
                value = obj;
                super();
            }
        });
        if (true) goto _L2; else goto _L1
_L1:
        Exception exception;
        exception;
        throw exception;
        this;
        JVM INSTR monitorexit ;
        return linkedhashset;
    }

    public Object get(Object obj)
    {
        this;
        JVM INSTR monitorenter ;
        SoftReference softreference;
        expungeStaleEntries();
        softreference = (SoftReference)hash.get(obj);
        Object obj1;
        obj1 = null;
        if (softreference == null)
        {
            break MISSING_BLOCK_LABEL_60;
        }
        obj1 = softreference.get();
        if (obj1 != null)
        {
            break MISSING_BLOCK_LABEL_60;
        }
        hash.remove(obj);
        reverseLookup.remove(softreference);
        this;
        JVM INSTR monitorexit ;
        return obj1;
        Exception exception;
        exception;
        throw exception;
    }

    public Object put(Object obj, Object obj1)
    {
        this;
        JVM INSTR monitorenter ;
        SoftReference softreference1;
        expungeStaleEntries();
        SoftReference softreference = new SoftReference(obj1, queue);
        reverseLookup.put(softreference, obj);
        softreference1 = (SoftReference)hash.put(obj, softreference);
        if (softreference1 != null) goto _L2; else goto _L1
_L1:
        Object obj3 = null;
_L4:
        this;
        JVM INSTR monitorexit ;
        return obj3;
_L2:
        Object obj2;
        reverseLookup.remove(softreference1);
        obj2 = softreference1.get();
        obj3 = obj2;
        if (true) goto _L4; else goto _L3
_L3:
        Exception exception;
        exception;
        throw exception;
    }

    public Object remove(Object obj)
    {
        this;
        JVM INSTR monitorenter ;
        SoftReference softreference;
        expungeStaleEntries();
        softreference = (SoftReference)hash.remove(obj);
        if (softreference != null) goto _L2; else goto _L1
_L1:
        Object obj2 = null;
_L4:
        this;
        JVM INSTR monitorexit ;
        return obj2;
_L2:
        Object obj1 = softreference.get();
        obj2 = obj1;
        if (true) goto _L4; else goto _L3
_L3:
        Exception exception;
        exception;
        throw exception;
    }

    public int size()
    {
        this;
        JVM INSTR monitorenter ;
        int i;
        expungeStaleEntries();
        i = hash.size();
        this;
        JVM INSTR monitorexit ;
        return i;
        Exception exception;
        exception;
        throw exception;
    }

}

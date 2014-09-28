// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.map.ser;

import java.util.HashMap;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.ResolvableSerializer;
import org.codehaus.jackson.map.SerializerProvider;
import org.codehaus.jackson.map.ser.impl.ReadOnlyClassToSerializerMap;
import org.codehaus.jackson.type.JavaType;

public final class SerializerCache
{
    public static final class TypeKey
    {

        protected Class _class;
        protected int _hashCode;
        protected boolean _isTyped;
        protected JavaType _type;

        private static final int hash(Class class1, boolean flag)
        {
            int i = class1.getName().hashCode();
            if (flag)
            {
                i++;
            }
            return i;
        }

        private static final int hash(JavaType javatype, boolean flag)
        {
            int i = -1 + javatype.hashCode();
            if (flag)
            {
                i--;
            }
            return i;
        }

        public final boolean equals(Object obj)
        {
            if (obj != this)
            {
                TypeKey typekey = (TypeKey)obj;
                if (typekey._isTyped == _isTyped)
                {
                    if (_class != null)
                    {
                        if (typekey._class != _class)
                        {
                            return false;
                        }
                    } else
                    {
                        return _type.equals(typekey._type);
                    }
                } else
                {
                    return false;
                }
            }
            return true;
        }

        public final int hashCode()
        {
            return _hashCode;
        }

        public void resetTyped(Class class1)
        {
            _type = null;
            _class = class1;
            _isTyped = true;
            _hashCode = hash(class1, true);
        }

        public void resetTyped(JavaType javatype)
        {
            _type = javatype;
            _class = null;
            _isTyped = true;
            _hashCode = hash(javatype, true);
        }

        public void resetUntyped(Class class1)
        {
            _type = null;
            _class = class1;
            _isTyped = false;
            _hashCode = hash(class1, false);
        }

        public void resetUntyped(JavaType javatype)
        {
            _type = javatype;
            _class = null;
            _isTyped = false;
            _hashCode = hash(javatype, false);
        }

        public final String toString()
        {
            if (_class != null)
            {
                return (new StringBuilder()).append("{class: ").append(_class.getName()).append(", typed? ").append(_isTyped).append("}").toString();
            } else
            {
                return (new StringBuilder()).append("{type: ").append(_type).append(", typed? ").append(_isTyped).append("}").toString();
            }
        }

        public TypeKey(Class class1, boolean flag)
        {
            _class = class1;
            _type = null;
            _isTyped = flag;
            _hashCode = hash(class1, flag);
        }

        public TypeKey(JavaType javatype, boolean flag)
        {
            _type = javatype;
            _class = null;
            _isTyped = flag;
            _hashCode = hash(javatype, flag);
        }
    }


    private ReadOnlyClassToSerializerMap _readOnlyMap;
    private HashMap _sharedMap;

    public SerializerCache()
    {
        _sharedMap = new HashMap(64);
        _readOnlyMap = null;
    }

    public void addAndResolveNonTypedSerializer(Class class1, JsonSerializer jsonserializer, SerializerProvider serializerprovider)
        throws JsonMappingException
    {
        this;
        JVM INSTR monitorenter ;
        if (_sharedMap.put(new TypeKey(class1, false), jsonserializer) == null)
        {
            _readOnlyMap = null;
        }
        if (jsonserializer instanceof ResolvableSerializer)
        {
            ((ResolvableSerializer)jsonserializer).resolve(serializerprovider);
        }
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public void addAndResolveNonTypedSerializer(JavaType javatype, JsonSerializer jsonserializer, SerializerProvider serializerprovider)
        throws JsonMappingException
    {
        this;
        JVM INSTR monitorenter ;
        if (_sharedMap.put(new TypeKey(javatype, false), jsonserializer) == null)
        {
            _readOnlyMap = null;
        }
        if (jsonserializer instanceof ResolvableSerializer)
        {
            ((ResolvableSerializer)jsonserializer).resolve(serializerprovider);
        }
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public void addTypedSerializer(Class class1, JsonSerializer jsonserializer)
    {
        this;
        JVM INSTR monitorenter ;
        if (_sharedMap.put(new TypeKey(class1, true), jsonserializer) == null)
        {
            _readOnlyMap = null;
        }
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public void addTypedSerializer(JavaType javatype, JsonSerializer jsonserializer)
    {
        this;
        JVM INSTR monitorenter ;
        if (_sharedMap.put(new TypeKey(javatype, true), jsonserializer) == null)
        {
            _readOnlyMap = null;
        }
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public void flush()
    {
        this;
        JVM INSTR monitorenter ;
        _sharedMap.clear();
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public ReadOnlyClassToSerializerMap getReadOnlyLookupMap()
    {
        this;
        JVM INSTR monitorenter ;
        ReadOnlyClassToSerializerMap readonlyclasstoserializermap = _readOnlyMap;
        if (readonlyclasstoserializermap != null)
        {
            break MISSING_BLOCK_LABEL_24;
        }
        readonlyclasstoserializermap = ReadOnlyClassToSerializerMap.from(_sharedMap);
        _readOnlyMap = readonlyclasstoserializermap;
        this;
        JVM INSTR monitorexit ;
        return readonlyclasstoserializermap.instance();
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public int size()
    {
        this;
        JVM INSTR monitorenter ;
        int i = _sharedMap.size();
        this;
        JVM INSTR monitorexit ;
        return i;
        Exception exception;
        exception;
        throw exception;
    }

    public JsonSerializer typedValueSerializer(Class class1)
    {
        this;
        JVM INSTR monitorenter ;
        JsonSerializer jsonserializer = (JsonSerializer)_sharedMap.get(new TypeKey(class1, true));
        this;
        JVM INSTR monitorexit ;
        return jsonserializer;
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public JsonSerializer typedValueSerializer(JavaType javatype)
    {
        this;
        JVM INSTR monitorenter ;
        JsonSerializer jsonserializer = (JsonSerializer)_sharedMap.get(new TypeKey(javatype, true));
        this;
        JVM INSTR monitorexit ;
        return jsonserializer;
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public JsonSerializer untypedValueSerializer(Class class1)
    {
        this;
        JVM INSTR monitorenter ;
        JsonSerializer jsonserializer = (JsonSerializer)_sharedMap.get(new TypeKey(class1, false));
        this;
        JVM INSTR monitorexit ;
        return jsonserializer;
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public JsonSerializer untypedValueSerializer(JavaType javatype)
    {
        this;
        JVM INSTR monitorenter ;
        JsonSerializer jsonserializer = (JsonSerializer)_sharedMap.get(new TypeKey(javatype, false));
        this;
        JVM INSTR monitorexit ;
        return jsonserializer;
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
    }
}

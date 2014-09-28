// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.map.util;

import org.codehaus.jackson.io.SerializedString;
import org.codehaus.jackson.map.AnnotationIntrospector;
import org.codehaus.jackson.map.MapperConfig;
import org.codehaus.jackson.map.introspect.BasicBeanDescription;
import org.codehaus.jackson.map.type.ClassKey;
import org.codehaus.jackson.type.JavaType;

// Referenced classes of package org.codehaus.jackson.map.util:
//            LRUMap

public class RootNameLookup
{

    protected LRUMap _rootNames;

    public RootNameLookup()
    {
    }

    public SerializedString findRootName(Class class1, MapperConfig mapperconfig)
    {
        this;
        JVM INSTR monitorenter ;
        ClassKey classkey = new ClassKey(class1);
        if (_rootNames != null) goto _L2; else goto _L1
_L1:
        _rootNames = new LRUMap(20, 200);
_L4:
        String s;
        BasicBeanDescription basicbeandescription = (BasicBeanDescription)mapperconfig.introspectClassAnnotations(class1);
        s = mapperconfig.getAnnotationIntrospector().findRootName(basicbeandescription.getClassInfo());
        if (s != null)
        {
            break MISSING_BLOCK_LABEL_69;
        }
        s = class1.getSimpleName();
        SerializedString serializedstring;
        serializedstring = new SerializedString(s);
        _rootNames.put(classkey, serializedstring);
_L3:
        this;
        JVM INSTR monitorexit ;
        return serializedstring;
_L2:
        serializedstring = (SerializedString)_rootNames.get(classkey);
        if (serializedstring == null) goto _L4; else goto _L3
        Exception exception;
        exception;
        throw exception;
    }

    public SerializedString findRootName(JavaType javatype, MapperConfig mapperconfig)
    {
        return findRootName(javatype.getRawClass(), mapperconfig);
    }
}

// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.map.jsontype;

import org.codehaus.jackson.type.JavaType;

public interface TypeIdResolver
{

    public abstract org.codehaus.jackson.annotate.JsonTypeInfo.Id getMechanism();

    public abstract String idFromValue(Object obj);

    public abstract String idFromValueAndType(Object obj, Class class1);

    public abstract void init(JavaType javatype);

    public abstract JavaType typeFromId(String s);
}

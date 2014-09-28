// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.map.exc;

import org.codehaus.jackson.JsonLocation;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.JsonMappingException;

public class UnrecognizedPropertyException extends JsonMappingException
{

    private static final long serialVersionUID = 1L;
    protected final Class _referringClass;
    protected final String _unrecognizedPropertyName;

    public UnrecognizedPropertyException(String s, JsonLocation jsonlocation, Class class1, String s1)
    {
        super(s, jsonlocation);
        _referringClass = class1;
        _unrecognizedPropertyName = s1;
    }

    public static UnrecognizedPropertyException from(JsonParser jsonparser, Object obj, String s)
    {
        if (obj == null)
        {
            throw new IllegalArgumentException();
        }
        Class class1;
        UnrecognizedPropertyException unrecognizedpropertyexception;
        if (obj instanceof Class)
        {
            class1 = (Class)obj;
        } else
        {
            class1 = obj.getClass();
        }
        unrecognizedpropertyexception = new UnrecognizedPropertyException((new StringBuilder()).append("Unrecognized field \"").append(s).append("\" (Class ").append(class1.getName()).append("), not marked as ignorable").toString(), jsonparser.getCurrentLocation(), class1, s);
        unrecognizedpropertyexception.prependPath(obj, s);
        return unrecognizedpropertyexception;
    }

    public Class getReferringClass()
    {
        return _referringClass;
    }

    public String getUnrecognizedPropertyName()
    {
        return _unrecognizedPropertyName;
    }
}

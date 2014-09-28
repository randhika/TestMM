// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.ads.mediation;

import com.google.android.gms.internal.eu;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public abstract class MediationServerParameters
{
    public static final class MappingException extends Exception
    {

        public MappingException(String s)
        {
            super(s);
        }
    }

    protected static interface Parameter
        extends Annotation
    {

        public abstract String name();

        public abstract boolean required();
    }


    public MediationServerParameters()
    {
    }

    protected void a()
    {
    }

    public void load(Map map)
        throws MappingException
    {
        HashMap hashmap = new HashMap();
        Field afield[] = getClass().getFields();
        int i = afield.length;
        for (int j = 0; j < i; j++)
        {
            Field field2 = afield[j];
            Parameter parameter = (Parameter)field2.getAnnotation(com/google/ads/mediation/MediationServerParameters$Parameter);
            if (parameter != null)
            {
                hashmap.put(parameter.name(), field2);
            }
        }

        if (hashmap.isEmpty())
        {
            eu.D("No server options fields detected. To suppress this message either add a field with the @Parameter annotation, or override the load() method.");
        }
        for (Iterator iterator = map.entrySet().iterator(); iterator.hasNext();)
        {
            java.util.Map.Entry entry = (java.util.Map.Entry)iterator.next();
            Field field1 = (Field)hashmap.remove(entry.getKey());
            if (field1 != null)
            {
                try
                {
                    field1.set(this, entry.getValue());
                }
                catch (IllegalAccessException illegalaccessexception)
                {
                    eu.D((new StringBuilder()).append("Server option \"").append((String)entry.getKey()).append("\" could not be set: Illegal Access").toString());
                }
                catch (IllegalArgumentException illegalargumentexception)
                {
                    eu.D((new StringBuilder()).append("Server option \"").append((String)entry.getKey()).append("\" could not be set: Bad Type").toString());
                }
            } else
            {
                eu.z((new StringBuilder()).append("Unexpected server option: ").append((String)entry.getKey()).append(" = \"").append((String)entry.getValue()).append("\"").toString());
            }
        }

        StringBuilder stringbuilder = new StringBuilder();
        Iterator iterator1 = hashmap.values().iterator();
        do
        {
            if (!iterator1.hasNext())
            {
                break;
            }
            Field field = (Field)iterator1.next();
            if (((Parameter)field.getAnnotation(com/google/ads/mediation/MediationServerParameters$Parameter)).required())
            {
                eu.D((new StringBuilder()).append("Required server option missing: ").append(((Parameter)field.getAnnotation(com/google/ads/mediation/MediationServerParameters$Parameter)).name()).toString());
                if (stringbuilder.length() > 0)
                {
                    stringbuilder.append(", ");
                }
                stringbuilder.append(((Parameter)field.getAnnotation(com/google/ads/mediation/MediationServerParameters$Parameter)).name());
            }
        } while (true);
        if (stringbuilder.length() > 0)
        {
            throw new MappingException((new StringBuilder()).append("Required server option(s) missing: ").append(stringbuilder.toString()).toString());
        } else
        {
            a();
            return;
        }
    }
}

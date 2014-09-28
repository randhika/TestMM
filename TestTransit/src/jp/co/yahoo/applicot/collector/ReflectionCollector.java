// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.applicot.collector;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

final class ReflectionCollector
{

    ReflectionCollector()
    {
    }

    public static String collectConstants(Class class1)
    {
        return collectConstants(class1, "");
    }

    public static String collectConstants(Class class1, String s)
    {
        StringBuilder stringbuilder = new StringBuilder();
        Field afield[] = class1.getFields();
        int i = afield.length;
        int j = 0;
        while (j < i) 
        {
            Field field = afield[j];
            if (s != null && s.length() > 0)
            {
                stringbuilder.append(s).append('.');
            }
            stringbuilder.append(field.getName()).append("=");
            try
            {
                stringbuilder.append(field.get(null).toString());
            }
            catch (IllegalArgumentException illegalargumentexception)
            {
                stringbuilder.append("N/A");
            }
            catch (IllegalAccessException illegalaccessexception)
            {
                stringbuilder.append("N/A");
            }
            stringbuilder.append("\n");
            j++;
        }
        return stringbuilder.toString();
    }

    public static String collectStaticGettersResults(Class class1)
    {
        StringBuilder stringbuilder = new StringBuilder();
        Method amethod[] = class1.getMethods();
        int i = amethod.length;
        int j = 0;
        while (j < i) 
        {
            Method method = amethod[j];
            if (method.getParameterTypes().length == 0 && (method.getName().startsWith("get") || method.getName().startsWith("is")) && !method.getName().equals("getClass"))
            {
                try
                {
                    stringbuilder.append(method.getName());
                    stringbuilder.append('=');
                    stringbuilder.append(method.invoke(null, (Object[])null));
                    stringbuilder.append("\n");
                }
                catch (IllegalArgumentException illegalargumentexception) { }
                catch (IllegalAccessException illegalaccessexception) { }
                catch (InvocationTargetException invocationtargetexception) { }
            }
            j++;
        }
        return stringbuilder.toString();
    }
}

// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

// Referenced classes of package com.google.android.gms.internal:
//            me

public final class mf
{

    private static void a(String s, Object obj, StringBuffer stringbuffer, StringBuffer stringbuffer1)
        throws IllegalAccessException, InvocationTargetException
    {
        if (obj != null) goto _L2; else goto _L1
_L1:
        return;
_L2:
        if (!(obj instanceof me)) goto _L4; else goto _L3
_L3:
        int i;
        Class class1;
        Method amethod[];
        int l;
        int i1;
        i = stringbuffer.length();
        if (s != null)
        {
            stringbuffer1.append(stringbuffer).append(cA(s)).append(" <\n");
            stringbuffer.append("  ");
        }
        class1 = obj.getClass();
        Field afield[] = class1.getFields();
        int j = afield.length;
        int k = 0;
        while (k < j) 
        {
            Field field = afield[k];
            int j1 = field.getModifiers();
            String s5 = field.getName();
            if ((j1 & 1) == 1 && (j1 & 8) != 8 && !s5.startsWith("_") && !s5.endsWith("_"))
            {
                Class class2 = field.getType();
                Object obj1 = field.get(obj);
                if (class2.isArray())
                {
                    if (class2.getComponentType() == Byte.TYPE)
                    {
                        a(s5, obj1, stringbuffer, stringbuffer1);
                    } else
                    {
                        int k1;
                        int l1;
                        if (obj1 == null)
                        {
                            k1 = 0;
                        } else
                        {
                            k1 = Array.getLength(obj1);
                        }
                        l1 = 0;
                        while (l1 < k1) 
                        {
                            a(s5, Array.get(obj1, l1), stringbuffer, stringbuffer1);
                            l1++;
                        }
                    }
                } else
                {
                    a(s5, obj1, stringbuffer, stringbuffer1);
                }
            }
            k++;
        }
        amethod = class1.getMethods();
        l = amethod.length;
        i1 = 0;
_L6:
        String s4;
        if (i1 >= l)
        {
            continue; /* Loop/switch isn't completed */
        }
        String s3 = amethod[i1].getName();
        if (!s3.startsWith("set"))
        {
            break MISSING_BLOCK_LABEL_343;
        }
        s4 = s3.substring(3);
        Method method = class1.getMethod((new StringBuilder()).append("has").append(s4).toString(), new Class[0]);
        if (((Boolean)method.invoke(obj, new Object[0])).booleanValue())
        {
            break; /* Loop/switch isn't completed */
        }
_L7:
        i1++;
        if (true) goto _L6; else goto _L5
_L5:
        Method method1 = class1.getMethod((new StringBuilder()).append("get").append(s4).toString(), new Class[0]);
        a(s4, method1.invoke(obj, new Object[0]), stringbuffer, stringbuffer1);
          goto _L7
        if (s == null) goto _L1; else goto _L8
_L8:
        stringbuffer.setLength(i);
        stringbuffer1.append(stringbuffer).append(">\n");
        return;
_L4:
        String s1 = cA(s);
        stringbuffer1.append(stringbuffer).append(s1).append(": ");
        if (obj instanceof String)
        {
            String s2 = cB((String)obj);
            stringbuffer1.append("\"").append(s2).append("\"");
        } else
        if (obj instanceof byte[])
        {
            a((byte[])(byte[])obj, stringbuffer1);
        } else
        {
            stringbuffer1.append(obj);
        }
        stringbuffer1.append("\n");
        return;
        NoSuchMethodException nosuchmethodexception1;
        nosuchmethodexception1;
          goto _L7
        NoSuchMethodException nosuchmethodexception;
        nosuchmethodexception;
          goto _L7
    }

    private static void a(byte abyte0[], StringBuffer stringbuffer)
    {
        if (abyte0 == null)
        {
            stringbuffer.append("\"\"");
            return;
        }
        stringbuffer.append('"');
        int i = 0;
        while (i < abyte0.length) 
        {
            byte byte0 = abyte0[i];
            if (byte0 == 92 || byte0 == 34)
            {
                stringbuffer.append('\\').append((char)byte0);
            } else
            if (byte0 >= 32 && byte0 < 127)
            {
                stringbuffer.append((char)byte0);
            } else
            {
                Object aobj[] = new Object[1];
                aobj[0] = Integer.valueOf(byte0);
                stringbuffer.append(String.format("\\%03o", aobj));
            }
            i++;
        }
        stringbuffer.append('"');
    }

    private static String aK(String s)
    {
        int i = s.length();
        StringBuilder stringbuilder = new StringBuilder(i);
        int j = 0;
        while (j < i) 
        {
            char c = s.charAt(j);
            if (c >= ' ' && c <= '~' && c != '"' && c != '\'')
            {
                stringbuilder.append(c);
            } else
            {
                Object aobj[] = new Object[1];
                aobj[0] = Integer.valueOf(c);
                stringbuilder.append(String.format("\\u%04x", aobj));
            }
            j++;
        }
        return stringbuilder.toString();
    }

    private static String cA(String s)
    {
        StringBuffer stringbuffer = new StringBuffer();
        int i = 0;
        while (i < s.length()) 
        {
            char c = s.charAt(i);
            if (i == 0)
            {
                stringbuffer.append(Character.toLowerCase(c));
            } else
            if (Character.isUpperCase(c))
            {
                stringbuffer.append('_').append(Character.toLowerCase(c));
            } else
            {
                stringbuffer.append(c);
            }
            i++;
        }
        return stringbuffer.toString();
    }

    private static String cB(String s)
    {
        if (!s.startsWith("http") && s.length() > 200)
        {
            s = (new StringBuilder()).append(s.substring(0, 200)).append("[...]").toString();
        }
        return aK(s);
    }

    public static String e(me me1)
    {
        if (me1 == null)
        {
            return "";
        }
        StringBuffer stringbuffer = new StringBuffer();
        try
        {
            a(null, me1, new StringBuffer(), stringbuffer);
        }
        catch (IllegalAccessException illegalaccessexception)
        {
            return (new StringBuilder()).append("Error printing proto: ").append(illegalaccessexception.getMessage()).toString();
        }
        catch (InvocationTargetException invocationtargetexception)
        {
            return (new StringBuilder()).append("Error printing proto: ").append(invocationtargetexception.getMessage()).toString();
        }
        return stringbuffer.toString();
    }
}

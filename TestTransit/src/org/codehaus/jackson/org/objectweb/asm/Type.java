// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.org.objectweb.asm;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class Type
{

    public static final int ARRAY = 9;
    public static final int BOOLEAN = 1;
    public static final Type BOOLEAN_TYPE = new Type(1, null, 0x5a000501, 1);
    public static final int BYTE = 3;
    public static final Type BYTE_TYPE = new Type(3, null, 0x42000501, 1);
    public static final int CHAR = 2;
    public static final Type CHAR_TYPE = new Type(2, null, 0x43000601, 1);
    public static final int DOUBLE = 8;
    public static final Type DOUBLE_TYPE = new Type(8, null, 0x44030302, 1);
    public static final int FLOAT = 6;
    public static final Type FLOAT_TYPE = new Type(6, null, 0x46020201, 1);
    public static final int INT = 5;
    public static final Type INT_TYPE = new Type(5, null, 0x49000001, 1);
    public static final int LONG = 7;
    public static final Type LONG_TYPE = new Type(7, null, 0x4a010102, 1);
    public static final int OBJECT = 10;
    public static final int SHORT = 4;
    public static final Type SHORT_TYPE = new Type(4, null, 0x53000701, 1);
    public static final int VOID;
    public static final Type VOID_TYPE = new Type(0, null, 0x56050000, 1);
    private final int a;
    private final char b[];
    private final int c;
    private final int d;

    private Type(int i, char ac[], int j, int k)
    {
        a = i;
        b = ac;
        c = j;
        d = k;
    }

    private static Type a(char ac[], int i)
    {
        int j = 1;
        switch (ac[i])
        {
        default:
            for (; ac[i + j] != ';'; j++) { }
            break;

        case 86: // 'V'
            return VOID_TYPE;

        case 90: // 'Z'
            return BOOLEAN_TYPE;

        case 67: // 'C'
            return CHAR_TYPE;

        case 66: // 'B'
            return BYTE_TYPE;

        case 83: // 'S'
            return SHORT_TYPE;

        case 73: // 'I'
            return INT_TYPE;

        case 70: // 'F'
            return FLOAT_TYPE;

        case 74: // 'J'
            return LONG_TYPE;

        case 68: // 'D'
            return DOUBLE_TYPE;

        case 91: // '['
            for (; ac[i + j] == '['; j++) { }
            if (ac[i + j] == 'L')
            {
                for (j++; ac[i + j] != ';'; j++) { }
            }
            return new Type(9, ac, i, j + 1);
        }
        return new Type(10, ac, i + 1, j - 1);
    }

    private void a(StringBuffer stringbuffer)
    {
        if (b == null)
        {
            stringbuffer.append((char)((0xff000000 & c) >>> 24));
            return;
        }
        if (a == 9)
        {
            stringbuffer.append(b, c, d);
            return;
        } else
        {
            stringbuffer.append('L');
            stringbuffer.append(b, c, d);
            stringbuffer.append(';');
            return;
        }
    }

    private static void a(StringBuffer stringbuffer, Class class1)
    {
        do
        {
            if (class1.isPrimitive())
            {
                char c2;
                if (class1 == Integer.TYPE)
                {
                    c2 = 'I';
                } else
                if (class1 == Void.TYPE)
                {
                    c2 = 'V';
                } else
                if (class1 == Boolean.TYPE)
                {
                    c2 = 'Z';
                } else
                if (class1 == Byte.TYPE)
                {
                    c2 = 'B';
                } else
                if (class1 == Character.TYPE)
                {
                    c2 = 'C';
                } else
                if (class1 == Short.TYPE)
                {
                    c2 = 'S';
                } else
                if (class1 == Double.TYPE)
                {
                    c2 = 'D';
                } else
                if (class1 == Float.TYPE)
                {
                    c2 = 'F';
                } else
                {
                    c2 = 'J';
                }
                stringbuffer.append(c2);
                return;
            }
            if (!class1.isArray())
            {
                break;
            }
            stringbuffer.append('[');
            class1 = class1.getComponentType();
        } while (true);
        stringbuffer.append('L');
        String s = class1.getName();
        int i = s.length();
        for (int j = 0; j < i; j++)
        {
            char c1 = s.charAt(j);
            if (c1 == '.')
            {
                c1 = '/';
            }
            stringbuffer.append(c1);
        }

        stringbuffer.append(';');
    }

    public static Type[] getArgumentTypes(String s)
    {
        int i;
        char ac[];
        int j;
        int k;
        i = 1;
        ac = s.toCharArray();
        j = 0;
        k = i;
_L4:
        int l;
        char c1;
        l = k + 1;
        c1 = ac[k];
        if (c1 != ')') goto _L2; else goto _L1
_L1:
        Type atype[];
        atype = new Type[j];
        int k1 = 0;
        while (ac[i] != ')') 
        {
            atype[k1] = a(ac, i);
            int l1 = atype[k1].d;
            int i1;
            int j1;
            byte byte0;
            if (atype[k1].a == 10)
            {
                byte0 = 2;
            } else
            {
                byte0 = 0;
            }
            i += byte0 + l1;
            k1++;
        }
          goto _L3
_L2:
        if (c1 == 'L')
        {
            i1 = l;
            do
            {
                j1 = i1 + 1;
                if (ac[i1] == ';')
                {
                    break;
                }
                i1 = j1;
            } while (true);
            j++;
            k = j1;
        } else
        if (c1 != '[')
        {
            j++;
            k = l;
        } else
        {
            k = l;
        }
        if (true) goto _L4; else goto _L3
_L3:
        return atype;
    }

    public static Type[] getArgumentTypes(Method method)
    {
        Class aclass[] = method.getParameterTypes();
        Type atype[] = new Type[aclass.length];
        for (int i = -1 + aclass.length; i >= 0; i--)
        {
            atype[i] = getType(aclass[i]);
        }

        return atype;
    }

    public static int getArgumentsAndReturnSizes(String s)
    {
        byte byte0;
        int i;
        int j;
        byte0 = 1;
        i = byte0;
        j = byte0;
_L7:
        int k;
        char c1;
        k = i + 1;
        c1 = s.charAt(i);
        if (c1 != ')') goto _L2; else goto _L1
_L1:
        char c3;
        int j1;
        c3 = s.charAt(k);
        j1 = j << 2;
        if (c3 != 'V') goto _L4; else goto _L3
_L3:
        byte0 = 0;
_L5:
        return j1 | byte0;
_L4:
        if (c3 == 'D' || c3 == 'J')
        {
            byte0 = 2;
        }
        if (true) goto _L5; else goto _L2
_L2:
        if (c1 == 'L')
        {
            int l = k;
            int i1;
            do
            {
                i1 = l + 1;
                if (s.charAt(l) == ';')
                {
                    break;
                }
                l = i1;
            } while (true);
            j++;
            i = i1;
        } else
        if (c1 == '[')
        {
            char c2;
            do
            {
                c2 = s.charAt(k);
                if (c2 != '[')
                {
                    break;
                }
                k++;
            } while (true);
            if (c2 == 'D' || c2 == 'J')
            {
                j--;
                i = k;
            } else
            {
                i = k;
            }
        } else
        if (c1 == 'D' || c1 == 'J')
        {
            j += 2;
            i = k;
        } else
        {
            j++;
            i = k;
        }
        if (true) goto _L7; else goto _L6
_L6:
    }

    public static String getConstructorDescriptor(Constructor constructor)
    {
        Class aclass[] = constructor.getParameterTypes();
        StringBuffer stringbuffer = new StringBuffer();
        stringbuffer.append('(');
        for (int i = 0; i < aclass.length; i++)
        {
            a(stringbuffer, aclass[i]);
        }

        return stringbuffer.append(")V").toString();
    }

    public static String getDescriptor(Class class1)
    {
        StringBuffer stringbuffer = new StringBuffer();
        a(stringbuffer, class1);
        return stringbuffer.toString();
    }

    public static String getInternalName(Class class1)
    {
        return class1.getName().replace('.', '/');
    }

    public static String getMethodDescriptor(Method method)
    {
        Class aclass[] = method.getParameterTypes();
        StringBuffer stringbuffer = new StringBuffer();
        stringbuffer.append('(');
        for (int i = 0; i < aclass.length; i++)
        {
            a(stringbuffer, aclass[i]);
        }

        stringbuffer.append(')');
        a(stringbuffer, method.getReturnType());
        return stringbuffer.toString();
    }

    public static String getMethodDescriptor(Type type, Type atype[])
    {
        StringBuffer stringbuffer = new StringBuffer();
        stringbuffer.append('(');
        for (int i = 0; i < atype.length; i++)
        {
            atype[i].a(stringbuffer);
        }

        stringbuffer.append(')');
        type.a(stringbuffer);
        return stringbuffer.toString();
    }

    public static Type getObjectType(String s)
    {
        char ac[] = s.toCharArray();
        byte byte0;
        if (ac[0] == '[')
        {
            byte0 = 9;
        } else
        {
            byte0 = 10;
        }
        return new Type(byte0, ac, 0, ac.length);
    }

    public static Type getReturnType(String s)
    {
        return a(s.toCharArray(), 1 + s.indexOf(')'));
    }

    public static Type getReturnType(Method method)
    {
        return getType(method.getReturnType());
    }

    public static Type getType(Class class1)
    {
        if (class1.isPrimitive())
        {
            if (class1 == Integer.TYPE)
            {
                return INT_TYPE;
            }
            if (class1 == Void.TYPE)
            {
                return VOID_TYPE;
            }
            if (class1 == Boolean.TYPE)
            {
                return BOOLEAN_TYPE;
            }
            if (class1 == Byte.TYPE)
            {
                return BYTE_TYPE;
            }
            if (class1 == Character.TYPE)
            {
                return CHAR_TYPE;
            }
            if (class1 == Short.TYPE)
            {
                return SHORT_TYPE;
            }
            if (class1 == Double.TYPE)
            {
                return DOUBLE_TYPE;
            }
            if (class1 == Float.TYPE)
            {
                return FLOAT_TYPE;
            } else
            {
                return LONG_TYPE;
            }
        } else
        {
            return getType(getDescriptor(class1));
        }
    }

    public static Type getType(String s)
    {
        return a(s.toCharArray(), 0);
    }

    public boolean equals(Object obj)
    {
        if (this != obj) goto _L2; else goto _L1
_L1:
        return true;
_L2:
        if (!(obj instanceof Type))
        {
            return false;
        }
        Type type = (Type)obj;
        if (a != type.a)
        {
            return false;
        }
        if (a == 10 || a == 9)
        {
            if (d != type.d)
            {
                return false;
            }
            int i = c;
            int j = type.c;
            int k = i + d;
            while (i < k) 
            {
                if (b[i] != type.b[j])
                {
                    return false;
                }
                i++;
                j++;
            }
        }
        if (true) goto _L1; else goto _L3
_L3:
    }

    public String getClassName()
    {
        StringBuffer stringbuffer;
        switch (a)
        {
        default:
            return (new String(b, c, d)).replace('/', '.');

        case 0: // '\0'
            return "void";

        case 1: // '\001'
            return "boolean";

        case 2: // '\002'
            return "char";

        case 3: // '\003'
            return "byte";

        case 4: // '\004'
            return "short";

        case 5: // '\005'
            return "int";

        case 6: // '\006'
            return "float";

        case 7: // '\007'
            return "long";

        case 8: // '\b'
            return "double";

        case 9: // '\t'
            stringbuffer = new StringBuffer(getElementType().getClassName());
            break;
        }
        for (int i = getDimensions(); i > 0; i--)
        {
            stringbuffer.append("[]");
        }

        return stringbuffer.toString();
    }

    public String getDescriptor()
    {
        StringBuffer stringbuffer = new StringBuffer();
        a(stringbuffer);
        return stringbuffer.toString();
    }

    public int getDimensions()
    {
        int i;
        for (i = 1; b[i + c] == '['; i++) { }
        return i;
    }

    public Type getElementType()
    {
        return a(b, c + getDimensions());
    }

    public String getInternalName()
    {
        return new String(b, c, d);
    }

    public int getOpcode(int i)
    {
        int j = 4;
        if (i == 46 || i == 79)
        {
            if (b == null)
            {
                j = (0xff00 & c) >> 8;
            }
            return j + i;
        }
        if (b == null)
        {
            j = (0xff0000 & c) >> 16;
        }
        return j + i;
    }

    public int getSize()
    {
        if (b == null)
        {
            return 0xff & c;
        } else
        {
            return 1;
        }
    }

    public int getSort()
    {
        return a;
    }

    public int hashCode()
    {
        int i = 13 * a;
        if (a == 10 || a == 9)
        {
            int j = c;
            for (int k = j + d; j < k;)
            {
                int l = 17 * (i + b[j]);
                j++;
                i = l;
            }

        }
        return i;
    }

    public String toString()
    {
        return getDescriptor();
    }

}

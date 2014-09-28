// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.tagmanager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

// Referenced classes of package com.google.android.gms.tagmanager:
//            dg, bh

class dh
{

    private static final Object ain = null;
    private static Long aio = new Long(0L);
    private static Double aip = new Double(0.0D);
    private static dg aiq = dg.z(0L);
    private static String air;
    private static Boolean ais = new Boolean(false);
    private static List ait = new ArrayList(0);
    private static Map aiu = new HashMap();
    private static com.google.android.gms.internal.d.a aiv;

    public static com.google.android.gms.internal.d.a cp(String s1)
    {
        com.google.android.gms.internal.d.a a = new com.google.android.gms.internal.d.a();
        a.type = 5;
        a.fS = s1;
        return a;
    }

    private static dg cq(String s1)
    {
        dg dg1;
        try
        {
            dg1 = dg.co(s1);
        }
        catch (NumberFormatException numberformatexception)
        {
            bh.A((new StringBuilder()).append("Failed to convert '").append(s1).append("' to a number.").toString());
            return aiq;
        }
        return dg1;
    }

    private static Long cr(String s1)
    {
        dg dg1 = cq(s1);
        if (dg1 == aiq)
        {
            return aio;
        } else
        {
            return Long.valueOf(dg1.longValue());
        }
    }

    private static Double cs(String s1)
    {
        dg dg1 = cq(s1);
        if (dg1 == aiq)
        {
            return aip;
        } else
        {
            return Double.valueOf(dg1.doubleValue());
        }
    }

    private static Boolean ct(String s1)
    {
        if ("true".equalsIgnoreCase(s1))
        {
            return Boolean.TRUE;
        }
        if ("false".equalsIgnoreCase(s1))
        {
            return Boolean.FALSE;
        } else
        {
            return ais;
        }
    }

    private static double getDouble(Object obj)
    {
        if (obj instanceof Number)
        {
            return ((Number)obj).doubleValue();
        } else
        {
            bh.A("getDouble received non-Number");
            return 0.0D;
        }
    }

    public static String j(com.google.android.gms.internal.d.a a)
    {
        return m(o(a));
    }

    public static dg k(com.google.android.gms.internal.d.a a)
    {
        return n(o(a));
    }

    public static Long l(com.google.android.gms.internal.d.a a)
    {
        return o(o(a));
    }

    public static Double m(com.google.android.gms.internal.d.a a)
    {
        return p(o(a));
    }

    public static String m(Object obj)
    {
        if (obj == null)
        {
            return air;
        } else
        {
            return obj.toString();
        }
    }

    public static Object mX()
    {
        return ain;
    }

    public static Long mY()
    {
        return aio;
    }

    public static Double mZ()
    {
        return aip;
    }

    public static dg n(Object obj)
    {
        if (obj instanceof dg)
        {
            return (dg)obj;
        }
        if (t(obj))
        {
            return dg.z(u(obj));
        }
        if (s(obj))
        {
            return dg.a(Double.valueOf(getDouble(obj)));
        } else
        {
            return cq(m(obj));
        }
    }

    public static Boolean n(com.google.android.gms.internal.d.a a)
    {
        return q(o(a));
    }

    public static Boolean na()
    {
        return ais;
    }

    public static dg nb()
    {
        return aiq;
    }

    public static String nc()
    {
        return air;
    }

    public static com.google.android.gms.internal.d.a nd()
    {
        return aiv;
    }

    public static Long o(Object obj)
    {
        if (t(obj))
        {
            return Long.valueOf(u(obj));
        } else
        {
            return cr(m(obj));
        }
    }

    public static Object o(com.google.android.gms.internal.d.a a)
    {
        int i = 0;
        if (a == null)
        {
            return ain;
        }
        switch (a.type)
        {
        default:
            bh.A((new StringBuilder()).append("Failed to convert a value of type: ").append(a.type).toString());
            return ain;

        case 1: // '\001'
            return a.fN;

        case 2: // '\002'
            ArrayList arraylist = new ArrayList(a.fO.length);
            com.google.android.gms.internal.d.a aa1[] = a.fO;
            for (int j1 = aa1.length; i < j1; i++)
            {
                Object obj2 = o(aa1[i]);
                if (obj2 == ain)
                {
                    return ain;
                }
                arraylist.add(obj2);
            }

            return arraylist;

        case 3: // '\003'
            if (a.fP.length != a.fQ.length)
            {
                bh.A((new StringBuilder()).append("Converting an invalid value to object: ").append(a.toString()).toString());
                return ain;
            }
            HashMap hashmap = new HashMap(a.fQ.length);
            for (; i < a.fP.length; i++)
            {
                Object obj = o(a.fP[i]);
                Object obj1 = o(a.fQ[i]);
                if (obj == ain || obj1 == ain)
                {
                    return ain;
                }
                hashmap.put(obj, obj1);
            }

            return hashmap;

        case 4: // '\004'
            bh.A("Trying to convert a macro reference to object");
            return ain;

        case 5: // '\005'
            bh.A("Trying to convert a function id to object");
            return ain;

        case 6: // '\006'
            return Long.valueOf(a.fT);

        case 7: // '\007'
            StringBuffer stringbuffer = new StringBuffer();
            com.google.android.gms.internal.d.a aa[] = a.fV;
            for (int i1 = aa.length; i < i1; i++)
            {
                String s1 = j(aa[i]);
                if (s1 == air)
                {
                    return ain;
                }
                stringbuffer.append(s1);
            }

            return stringbuffer.toString();

        case 8: // '\b'
            return Boolean.valueOf(a.fU);
        }
    }

    public static Double p(Object obj)
    {
        if (s(obj))
        {
            return Double.valueOf(getDouble(obj));
        } else
        {
            return cs(m(obj));
        }
    }

    public static Boolean q(Object obj)
    {
        if (obj instanceof Boolean)
        {
            return (Boolean)obj;
        } else
        {
            return ct(m(obj));
        }
    }

    public static com.google.android.gms.internal.d.a r(Object obj)
    {
        boolean flag;
        com.google.android.gms.internal.d.a a;
        flag = false;
        a = new com.google.android.gms.internal.d.a();
        if (obj instanceof com.google.android.gms.internal.d.a)
        {
            return (com.google.android.gms.internal.d.a)obj;
        }
        if (!(obj instanceof String)) goto _L2; else goto _L1
_L1:
        a.type = 1;
        a.fN = (String)obj;
_L4:
        a.fX = flag;
        return a;
_L2:
        if (obj instanceof List)
        {
            a.type = 2;
            List list = (List)obj;
            ArrayList arraylist2 = new ArrayList(list.size());
            Iterator iterator1 = list.iterator();
            boolean flag3 = false;
            while (iterator1.hasNext()) 
            {
                com.google.android.gms.internal.d.a a3 = r(iterator1.next());
                if (a3 == aiv)
                {
                    return aiv;
                }
                boolean flag4;
                if (flag3 || a3.fX)
                {
                    flag4 = true;
                } else
                {
                    flag4 = false;
                }
                arraylist2.add(a3);
                flag3 = flag4;
            }
            a.fO = (com.google.android.gms.internal.d.a[])arraylist2.toArray(new com.google.android.gms.internal.d.a[0]);
            flag = flag3;
            continue; /* Loop/switch isn't completed */
        }
        if (obj instanceof Map)
        {
            a.type = 3;
            Set set = ((Map)obj).entrySet();
            ArrayList arraylist = new ArrayList(set.size());
            ArrayList arraylist1 = new ArrayList(set.size());
            Iterator iterator = set.iterator();
            boolean flag1 = false;
            while (iterator.hasNext()) 
            {
                java.util.Map.Entry entry = (java.util.Map.Entry)iterator.next();
                com.google.android.gms.internal.d.a a1 = r(entry.getKey());
                com.google.android.gms.internal.d.a a2 = r(entry.getValue());
                if (a1 == aiv || a2 == aiv)
                {
                    return aiv;
                }
                boolean flag2;
                if (flag1 || a1.fX || a2.fX)
                {
                    flag2 = true;
                } else
                {
                    flag2 = false;
                }
                arraylist.add(a1);
                arraylist1.add(a2);
                flag1 = flag2;
            }
            a.fP = (com.google.android.gms.internal.d.a[])arraylist.toArray(new com.google.android.gms.internal.d.a[0]);
            a.fQ = (com.google.android.gms.internal.d.a[])arraylist1.toArray(new com.google.android.gms.internal.d.a[0]);
            flag = flag1;
            continue; /* Loop/switch isn't completed */
        }
        if (s(obj))
        {
            a.type = 1;
            a.fN = obj.toString();
            flag = false;
            continue; /* Loop/switch isn't completed */
        }
        if (t(obj))
        {
            a.type = 6;
            a.fT = u(obj);
            flag = false;
            continue; /* Loop/switch isn't completed */
        }
        if (!(obj instanceof Boolean))
        {
            break; /* Loop/switch isn't completed */
        }
        a.type = 8;
        a.fU = ((Boolean)obj).booleanValue();
        flag = false;
        if (true) goto _L4; else goto _L3
_L3:
        StringBuilder stringbuilder = (new StringBuilder()).append("Converting to Value from unknown object type: ");
        String s1;
        if (obj == null)
        {
            s1 = "null";
        } else
        {
            s1 = obj.getClass().toString();
        }
        bh.A(stringbuilder.append(s1).toString());
        return aiv;
    }

    private static boolean s(Object obj)
    {
        return (obj instanceof Double) || (obj instanceof Float) || (obj instanceof dg) && ((dg)obj).mS();
    }

    private static boolean t(Object obj)
    {
        return (obj instanceof Byte) || (obj instanceof Short) || (obj instanceof Integer) || (obj instanceof Long) || (obj instanceof dg) && ((dg)obj).mT();
    }

    private static long u(Object obj)
    {
        if (obj instanceof Number)
        {
            return ((Number)obj).longValue();
        } else
        {
            bh.A("getInt64 received non-Number");
            return 0L;
        }
    }

    static 
    {
        air = new String("");
        aiv = r(air);
    }
}

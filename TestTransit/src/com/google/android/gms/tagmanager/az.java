// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.a;
import com.google.android.gms.internal.b;
import java.io.UnsupportedEncodingException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

// Referenced classes of package com.google.android.gms.tagmanager:
//            aj, dk, bh, dh

class az extends aj
{
    private static final class a extends Enum
    {

        public static final a afY;
        public static final a afZ;
        public static final a aga;
        private static final a agb[];

        public static a valueOf(String s)
        {
            return (a)Enum.valueOf(com/google/android/gms/tagmanager/az$a, s);
        }

        public static a[] values()
        {
            return (a[])agb.clone();
        }

        static 
        {
            afY = new a("NONE", 0);
            afZ = new a("URL", 1);
            aga = new a("BACKSLASH", 2);
            a aa[] = new a[3];
            aa[0] = afY;
            aa[1] = afZ;
            aa[2] = aga;
            agb = aa;
        }

        private a(String s, int i)
        {
            super(s, i);
        }
    }


    private static final String ID;
    private static final String afU;
    private static final String afV;
    private static final String afW;
    private static final String afy;

    public az()
    {
        String s = ID;
        String as[] = new String[1];
        as[0] = afy;
        super(s, as);
    }

    private String a(String s, a a1, Set set)
    {
        static class _cls1
        {

            static final int afX[];

            static 
            {
                afX = new int[a.values().length];
                try
                {
                    afX[a.afZ.ordinal()] = 1;
                }
                catch (NoSuchFieldError nosuchfielderror) { }
                try
                {
                    afX[a.aga.ordinal()] = 2;
                }
                catch (NoSuchFieldError nosuchfielderror1) { }
                try
                {
                    afX[a.afY.ordinal()] = 3;
                }
                catch (NoSuchFieldError nosuchfielderror2)
                {
                    return;
                }
            }
        }

        Iterator iterator;
        String s2;
        switch (_cls1.afX[a1.ordinal()])
        {
        default:
            return s;

        case 1: // '\001'
            String s4;
            try
            {
                s4 = dk.cv(s);
            }
            catch (UnsupportedEncodingException unsupportedencodingexception)
            {
                com.google.android.gms.tagmanager.bh.b("Joiner: unsupported encoding", unsupportedencodingexception);
                return s;
            }
            return s4;

        case 2: // '\002'
            String s1 = s.replace("\\", "\\\\");
            iterator = set.iterator();
            s2 = s1;
            break;
        }
        while (iterator.hasNext()) 
        {
            String s3 = ((Character)iterator.next()).toString();
            s2 = s2.replace(s3, (new StringBuilder()).append("\\").append(s3).toString());
        }
        return s2;
    }

    private void a(StringBuilder stringbuilder, String s, a a1, Set set)
    {
        stringbuilder.append(a(s, a1, set));
    }

    private void a(Set set, String s)
    {
        for (int i = 0; i < s.length(); i++)
        {
            set.add(Character.valueOf(s.charAt(i)));
        }

    }

    public boolean lh()
    {
        return true;
    }

    public com.google.android.gms.internal.d.a w(Map map)
    {
        com.google.android.gms.internal.d.a a1 = (com.google.android.gms.internal.d.a)map.get(afy);
        if (a1 == null)
        {
            return dh.nd();
        }
        com.google.android.gms.internal.d.a a2 = (com.google.android.gms.internal.d.a)map.get(afU);
        String s;
        com.google.android.gms.internal.d.a a3;
        String s1;
        a a4;
        com.google.android.gms.internal.d.a a5;
        a a6;
        Object obj;
        StringBuilder stringbuilder;
        if (a2 != null)
        {
            s = dh.j(a2);
        } else
        {
            s = "";
        }
        a3 = (com.google.android.gms.internal.d.a)map.get(afV);
        if (a3 != null)
        {
            s1 = dh.j(a3);
        } else
        {
            s1 = "=";
        }
        a4 = a.afY;
        a5 = (com.google.android.gms.internal.d.a)map.get(afW);
        int i;
        String s2;
        String s3;
        boolean flag;
        com.google.android.gms.internal.d.a aa[];
        int j;
        int k;
        com.google.android.gms.internal.d.a a7;
        if (a5 != null)
        {
            String s4 = dh.j(a5);
            if ("url".equals(s4))
            {
                a6 = a.afZ;
                obj = null;
            } else
            if ("backslash".equals(s4))
            {
                a6 = a.aga;
                obj = new HashSet();
                a(((Set) (obj)), s);
                a(((Set) (obj)), s1);
                ((Set) (obj)).remove(Character.valueOf('\\'));
            } else
            {
                bh.A((new StringBuilder()).append("Joiner: unsupported escape type: ").append(s4).toString());
                return dh.nd();
            }
        } else
        {
            a6 = a4;
            obj = null;
        }
        stringbuilder = new StringBuilder();
        a1.type;
        JVM INSTR tableswitch 2 3: default 156
    //                   2 276
    //                   3 344;
           goto _L1 _L2 _L3
_L1:
        a(stringbuilder, dh.j(a1), a6, ((Set) (obj)));
_L5:
        return dh.r(stringbuilder.toString());
_L2:
        flag = true;
        aa = a1.fO;
        j = aa.length;
        k = 0;
        while (k < j) 
        {
            a7 = aa[k];
            if (!flag)
            {
                stringbuilder.append(s);
            }
            a(stringbuilder, dh.j(a7), a6, ((Set) (obj)));
            k++;
            flag = false;
        }
        if (true)
        {
            continue; /* Loop/switch isn't completed */
        }
_L3:
        i = 0;
        while (i < a1.fP.length) 
        {
            if (i > 0)
            {
                stringbuilder.append(s);
            }
            s2 = dh.j(a1.fP[i]);
            s3 = dh.j(a1.fQ[i]);
            a(stringbuilder, s2, a6, ((Set) (obj)));
            stringbuilder.append(s1);
            a(stringbuilder, s3, a6, ((Set) (obj)));
            i++;
        }
        if (true) goto _L5; else goto _L4
_L4:
    }

    static 
    {
        ID = com.google.android.gms.internal.a.ac.toString();
        afy = b.bi.toString();
        afU = b.cL.toString();
        afV = b.cO.toString();
        afW = b.co.toString();
    }
}

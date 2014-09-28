// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.org.objectweb.asm;


// Referenced classes of package org.codehaus.jackson.org.objectweb.asm:
//            Label, ClassWriter, Item, Type

final class Frame
{

    static final int a[];
    Label b;
    int c[];
    int d[];
    private int e[];
    private int f[];
    private int g;
    private int h;
    private int i[];

    Frame()
    {
    }

    private int a()
    {
        if (g > 0)
        {
            int ai[] = f;
            int k = -1 + g;
            g = k;
            return ai[k];
        } else
        {
            Label label = b;
            int j = -1 + label.f;
            label.f = j;
            return 0x3000000 | -j;
        }
    }

    private int a(int j)
    {
        int k;
        if (e == null || j >= e.length)
        {
            k = 0x2000000 | j;
        } else
        {
            k = e[j];
            if (k == 0)
            {
                int ai[] = e;
                int l = 0x2000000 | j;
                ai[j] = l;
                return l;
            }
        }
        return k;
    }

    private int a(ClassWriter classwriter, int j)
    {
        if (j != 0x1000006) goto _L2; else goto _L1
_L1:
        int k = 0x1700000 | classwriter.c(classwriter.F);
_L5:
        int l = 0;
_L6:
        if (l < h)
        {
            int i1 = i[l];
            int j1 = 0xf0000000 & i1;
            int k1 = 0xf000000 & i1;
            if (k1 == 0x2000000)
            {
                i1 = j1 + c[i1 & 0x7fffff];
            } else
            if (k1 == 0x3000000)
            {
                i1 = j1 + d[d.length - (i1 & 0x7fffff)];
            }
            if (j != i1)
            {
                break MISSING_BLOCK_LABEL_148;
            }
            j = k;
        }
_L4:
        return j;
_L2:
        if ((0xfff00000 & j) != 0x1800000) goto _L4; else goto _L3
_L3:
        k = 0x1700000 | classwriter.c(classwriter.E[0xfffff & j].g);
          goto _L5
        l++;
          goto _L6
    }

    private void a(int j, int k)
    {
        if (e == null)
        {
            e = new int[10];
        }
        int l = e.length;
        if (j >= l)
        {
            int ai[] = new int[Math.max(j + 1, l * 2)];
            System.arraycopy(e, 0, ai, 0, l);
            e = ai;
        }
        e[j] = k;
    }

    private void a(String s)
    {
        char c1 = s.charAt(0);
        if (c1 == '(')
        {
            c(-1 + (Type.getArgumentsAndReturnSizes(s) >> 2));
            return;
        }
        if (c1 == 'J' || c1 == 'D')
        {
            c(2);
            return;
        } else
        {
            c(1);
            return;
        }
    }

    private void a(ClassWriter classwriter, String s)
    {
        int j = b(classwriter, s);
        if (j != 0)
        {
            b(j);
            if (j == 0x1000004 || j == 0x1000003)
            {
                b(0x1000000);
            }
        }
    }

    private static boolean a(ClassWriter classwriter, int j, int ai[], int k)
    {
        int l = ai[k];
        if (l == j)
        {
            return false;
        }
        int i1;
        if ((0xfffffff & j) == 0x1000005)
        {
            if (l == 0x1000005)
            {
                return false;
            }
            i1 = 0x1000005;
        } else
        {
            i1 = j;
        }
        if (l == 0)
        {
            ai[k] = i1;
            return true;
        }
        if ((l & 0xff00000) == 0x1700000 || (l & 0xf0000000) != 0)
        {
            if (i1 == 0x1000005)
            {
                return false;
            }
            if ((0xfff00000 & i1) == (0xfff00000 & l))
            {
                if ((l & 0xff00000) == 0x1700000)
                {
                    i1 = 0x1700000 | i1 & 0xf0000000 | classwriter.a(i1 & 0xfffff, 0xfffff & l);
                } else
                {
                    i1 = 0x1700000 | classwriter.c("java/lang/Object");
                }
            } else
            if ((i1 & 0xff00000) == 0x1700000 || (i1 & 0xf0000000) != 0)
            {
                i1 = 0x1700000 | classwriter.c("java/lang/Object");
            } else
            {
                i1 = 0x1000000;
            }
        } else
        if (l == 0x1000005)
        {
            if ((i1 & 0xff00000) != 0x1700000 && (i1 & 0xf0000000) == 0)
            {
                i1 = 0x1000000;
            }
        } else
        {
            i1 = 0x1000000;
        }
        if (l != i1)
        {
            ai[k] = i1;
            return true;
        } else
        {
            return false;
        }
    }

    private static int b(ClassWriter classwriter, String s)
    {
        int j;
        int k;
        int i1;
        j = 0x1000001;
        char c1;
        int l;
        if (s.charAt(0) == '(')
        {
            k = 1 + s.indexOf(')');
        } else
        {
            k = 0;
        }
        c1 = s.charAt(k);
        l = 0;
        switch (c1)
        {
        default:
            for (i1 = k + 1; s.charAt(i1) == '['; i1++) { }
            break;

        case 66: // 'B'
        case 67: // 'C'
        case 73: // 'I'
        case 83: // 'S'
        case 90: // 'Z'
            l = j;
            // fall through

        case 86: // 'V'
            return l;

        case 70: // 'F'
            return 0x1000002;

        case 74: // 'J'
            return 0x1000004;

        case 68: // 'D'
            return 0x1000003;

        case 76: // 'L'
            return 0x1700000 | classwriter.c(s.substring(k + 1, -1 + s.length()));
        }
        s.charAt(i1);
        JVM INSTR lookupswitch 8: default 268
    //                   66: 312
    //                   67: 306
    //                   68: 336
    //                   70: 324
    //                   73: 290
    //                   74: 330
    //                   83: 318
    //                   90: 300;
           goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8 _L9
_L6:
        break; /* Loop/switch isn't completed */
_L1:
        j = 0x1700000 | classwriter.c(s.substring(i1 + 1, -1 + s.length()));
_L11:
        return j | i1 - k << 28;
_L9:
        j = 0x1000009;
        continue; /* Loop/switch isn't completed */
_L3:
        j = 0x100000b;
        continue; /* Loop/switch isn't completed */
_L2:
        j = 0x100000a;
        continue; /* Loop/switch isn't completed */
_L8:
        j = 0x100000c;
        continue; /* Loop/switch isn't completed */
_L5:
        j = 0x1000002;
        continue; /* Loop/switch isn't completed */
_L7:
        j = 0x1000004;
        continue; /* Loop/switch isn't completed */
_L4:
        j = 0x1000003;
        if (true) goto _L11; else goto _L10
_L10:
    }

    private void b(int j)
    {
        if (f == null)
        {
            f = new int[10];
        }
        int k = f.length;
        if (g >= k)
        {
            int ai1[] = new int[Math.max(1 + g, k * 2)];
            System.arraycopy(f, 0, ai1, 0, k);
            f = ai1;
        }
        int ai[] = f;
        int l = g;
        g = l + 1;
        ai[l] = j;
        int i1 = b.f + g;
        if (i1 > b.g)
        {
            b.g = i1;
        }
    }

    private void c(int j)
    {
        if (g >= j)
        {
            g = g - j;
            return;
        } else
        {
            Label label = b;
            label.f = label.f - (j - g);
            g = 0;
            return;
        }
    }

    private void d(int j)
    {
        if (i == null)
        {
            i = new int[2];
        }
        int k = i.length;
        if (h >= k)
        {
            int ai1[] = new int[Math.max(1 + h, k * 2)];
            System.arraycopy(i, 0, ai1, 0, k);
            i = ai1;
        }
        int ai[] = i;
        int l = h;
        h = l + 1;
        ai[l] = j;
    }

    void a(int j, int k, ClassWriter classwriter, Item item)
    {
        j;
        JVM INSTR tableswitch 0 199: default 816
    //                   0 831
    //                   1 832
    //                   2 839
    //                   3 839
    //                   4 839
    //                   5 839
    //                   6 839
    //                   7 839
    //                   8 839
    //                   9 846
    //                   10 846
    //                   11 859
    //                   12 859
    //                   13 859
    //                   14 866
    //                   15 866
    //                   16 839
    //                   17 839
    //                   18 879
    //                   19 816
    //                   20 816
    //                   21 839
    //                   22 846
    //                   23 859
    //                   24 866
    //                   25 988
    //                   26 816
    //                   27 816
    //                   28 816
    //                   29 816
    //                   30 816
    //                   31 816
    //                   32 816
    //                   33 816
    //                   34 816
    //                   35 816
    //                   36 816
    //                   37 816
    //                   38 816
    //                   39 816
    //                   40 816
    //                   41 816
    //                   42 816
    //                   43 816
    //                   44 816
    //                   45 816
    //                   46 998
    //                   47 1010
    //                   48 1028
    //                   49 1040
    //                   50 1058
    //                   51 998
    //                   52 998
    //                   53 998
    //                   54 1075
    //                   55 1144
    //                   56 1075
    //                   57 1144
    //                   58 1075
    //                   59 816
    //                   60 816
    //                   61 816
    //                   62 816
    //                   63 816
    //                   64 816
    //                   65 816
    //                   66 816
    //                   67 816
    //                   68 816
    //                   69 816
    //                   70 816
    //                   71 816
    //                   72 816
    //                   73 816
    //                   74 816
    //                   75 816
    //                   76 816
    //                   77 816
    //                   78 816
    //                   79 1227
    //                   80 1233
    //                   81 1227
    //                   82 1233
    //                   83 1227
    //                   84 1227
    //                   85 1227
    //                   86 1227
    //                   87 1239
    //                   88 1245
    //                   89 1251
    //                   90 1270
    //                   91 1301
    //                   92 1344
    //                   93 1381
    //                   94 1430
    //                   95 1491
    //                   96 1516
    //                   97 1528
    //                   98 1546
    //                   99 1558
    //                   100 1516
    //                   101 1528
    //                   102 1546
    //                   103 1558
    //                   104 1516
    //                   105 1528
    //                   106 1546
    //                   107 1558
    //                   108 1516
    //                   109 1528
    //                   110 1546
    //                   111 1558
    //                   112 1516
    //                   113 1528
    //                   114 1546
    //                   115 1558
    //                   116 831
    //                   117 831
    //                   118 831
    //                   119 831
    //                   120 1516
    //                   121 1576
    //                   122 1516
    //                   123 1576
    //                   124 1516
    //                   125 1576
    //                   126 1516
    //                   127 1528
    //                   128 1516
    //                   129 1528
    //                   130 1516
    //                   131 1528
    //                   132 1594
    //                   133 1602
    //                   134 1620
    //                   135 1632
    //                   136 1516
    //                   137 1546
    //                   138 1040
    //                   139 1650
    //                   140 1602
    //                   141 1632
    //                   142 1516
    //                   143 1010
    //                   144 1546
    //                   145 831
    //                   146 831
    //                   147 831
    //                   148 1662
    //                   149 1516
    //                   150 1516
    //                   151 1662
    //                   152 1662
    //                   153 1239
    //                   154 1239
    //                   155 1239
    //                   156 1239
    //                   157 1239
    //                   158 1239
    //                   159 1245
    //                   160 1245
    //                   161 1245
    //                   162 1245
    //                   163 1245
    //                   164 1245
    //                   165 1245
    //                   166 1245
    //                   167 831
    //                   168 1674
    //                   169 1674
    //                   170 1239
    //                   171 1239
    //                   172 1239
    //                   173 1245
    //                   174 1239
    //                   175 1245
    //                   176 1239
    //                   177 831
    //                   178 1684
    //                   179 1695
    //                   180 1705
    //                   181 1721
    //                   182 1736
    //                   183 1736
    //                   184 1736
    //                   185 1736
    //                   186 1796
    //                   187 1816
    //                   188 1834
    //                   189 1940
    //                   190 1650
    //                   191 1239
    //                   192 2003
    //                   193 1650
    //                   194 1239
    //                   195 1239
    //                   196 816
    //                   197 816
    //                   198 1239
    //                   199 1239;
           goto _L1 _L2 _L3 _L4 _L4 _L4 _L4 _L4 _L4 _L4 _L5 _L5 _L6 _L6 _L6 _L7 _L7 _L4 _L4 _L8 _L1 _L1 _L4 _L5 _L6 _L7 _L9 _L1 _L1 _L1 _L1 _L1 _L1 _L1 _L1 _L1 _L1 _L1 _L1 _L1 _L1 _L1 _L1 _L1 _L1 _L1 _L1 _L10 _L11 _L12 _L13 _L14 _L10 _L10 _L10 _L15 _L16 _L15 _L16 _L15 _L1 _L1 _L1 _L1 _L1 _L1 _L1 _L1 _L1 _L1 _L1 _L1 _L1 _L1 _L1 _L1 _L1 _L1 _L1 _L1 _L17 _L18 _L17 _L18 _L17 _L17 _L17 _L17 _L19 _L20 _L21 _L22 _L23 _L24 _L25 _L26 _L27 _L28 _L29 _L30 _L31 _L28 _L29 _L30 _L31 _L28 _L29 _L30 _L31 _L28 _L29 _L30 _L31 _L28 _L29 _L30 _L31 _L2 _L2 _L2 _L2 _L28 _L32 _L28 _L32 _L28 _L32 _L28 _L29 _L28 _L29 _L28 _L29 _L33 _L34 _L35 _L36 _L28 _L30 _L13 _L37 _L34 _L36 _L28 _L11 _L30 _L2 _L2 _L2 _L38 _L28 _L28 _L38 _L38 _L19 _L19 _L19 _L19 _L19 _L19 _L20 _L20 _L20 _L20 _L20 _L20 _L20 _L20 _L2 _L39 _L39 _L19 _L19 _L19 _L20 _L19 _L20 _L19 _L2 _L40 _L41 _L42 _L43 _L44 _L44 _L44 _L44 _L45 _L46 _L47 _L48 _L37 _L19 _L49 _L37 _L19 _L19 _L1 _L1 _L19 _L19
_L2:
        break MISSING_BLOCK_LABEL_831;
_L1:
        c(k);
        a(classwriter, item.g);
_L51:
        return;
_L3:
        b(0x1000005);
        return;
_L4:
        b(0x1000001);
        return;
_L5:
        b(0x1000004);
        b(0x1000000);
        return;
_L6:
        b(0x1000002);
        return;
_L7:
        b(0x1000003);
        b(0x1000000);
        return;
_L8:
        switch (item.b)
        {
        default:
            b(0x1700000 | classwriter.c("java/lang/String"));
            return;

        case 3: // '\003'
            b(0x1000001);
            return;

        case 5: // '\005'
            b(0x1000004);
            b(0x1000000);
            return;

        case 4: // '\004'
            b(0x1000002);
            return;

        case 6: // '\006'
            b(0x1000003);
            b(0x1000000);
            return;

        case 7: // '\007'
            b(0x1700000 | classwriter.c("java/lang/Class"));
            break;
        }
        return;
_L9:
        b(a(k));
        return;
_L10:
        c(2);
        b(0x1000001);
        return;
_L11:
        c(2);
        b(0x1000004);
        b(0x1000000);
        return;
_L12:
        c(2);
        b(0x1000002);
        return;
_L13:
        c(2);
        b(0x1000003);
        b(0x1000000);
        return;
_L14:
        c(1);
        b(0xf0000000 + a());
        return;
_L15:
        a(k, a());
        if (k <= 0) goto _L51; else goto _L50
_L50:
        int k5;
        k5 = a(k - 1);
        if (k5 == 0x1000004 || k5 == 0x1000003)
        {
            a(k - 1, 0x1000000);
            return;
        }
        if ((0xf000000 & k5) == 0x1000000) goto _L51; else goto _L52
_L52:
        a(k - 1, k5 | 0x800000);
        return;
_L16:
        c(1);
        a(k, a());
        a(k + 1, 0x1000000);
        if (k <= 0) goto _L51; else goto _L53
_L53:
        int j5;
        j5 = a(k - 1);
        if (j5 == 0x1000004 || j5 == 0x1000003)
        {
            a(k - 1, 0x1000000);
            return;
        }
        if ((0xf000000 & j5) == 0x1000000) goto _L51; else goto _L54
_L54:
        a(k - 1, j5 | 0x800000);
        return;
_L17:
        c(3);
        return;
_L18:
        c(4);
        return;
_L19:
        c(1);
        return;
_L20:
        c(2);
        return;
_L21:
        int i5 = a();
        b(i5);
        b(i5);
        return;
_L22:
        int k4 = a();
        int l4 = a();
        b(k4);
        b(l4);
        b(k4);
        return;
_L23:
        int l3 = a();
        int i4 = a();
        int j4 = a();
        b(l3);
        b(j4);
        b(i4);
        b(l3);
        return;
_L24:
        int j3 = a();
        int k3 = a();
        b(k3);
        b(j3);
        b(k3);
        b(j3);
        return;
_L25:
        int k2 = a();
        int l2 = a();
        int i3 = a();
        b(l2);
        b(k2);
        b(i3);
        b(l2);
        b(k2);
        return;
_L26:
        int k1 = a();
        int l1 = a();
        int i2 = a();
        int j2 = a();
        b(l1);
        b(k1);
        b(j2);
        b(i2);
        b(l1);
        b(k1);
        return;
_L27:
        int i1 = a();
        int j1 = a();
        b(i1);
        b(j1);
        return;
_L28:
        c(2);
        b(0x1000001);
        return;
_L29:
        c(4);
        b(0x1000004);
        b(0x1000000);
        return;
_L30:
        c(2);
        b(0x1000002);
        return;
_L31:
        c(4);
        b(0x1000003);
        b(0x1000000);
        return;
_L32:
        c(3);
        b(0x1000004);
        b(0x1000000);
        return;
_L33:
        a(k, 0x1000001);
        return;
_L34:
        c(1);
        b(0x1000004);
        b(0x1000000);
        return;
_L35:
        c(1);
        b(0x1000002);
        return;
_L36:
        c(1);
        b(0x1000003);
        b(0x1000000);
        return;
_L37:
        c(1);
        b(0x1000001);
        return;
_L38:
        c(4);
        b(0x1000001);
        return;
_L39:
        throw new RuntimeException("JSR/RET are not supported with computeFrames option");
_L40:
        a(classwriter, item.i);
        return;
_L41:
        a(item.i);
        return;
_L42:
        c(1);
        a(classwriter, item.i);
        return;
_L43:
        a(item.i);
        a();
        return;
_L44:
        a(item.i);
        if (j != 184)
        {
            int l = a();
            if (j == 183 && item.h.charAt(0) == '<')
            {
                d(l);
            }
        }
        a(classwriter, item.i);
        return;
_L45:
        a(item.h);
        a(classwriter, item.h);
        return;
_L46:
        b(0x1800000 | classwriter.a(item.g, k));
        return;
_L47:
        a();
        switch (k)
        {
        default:
            b(0x11000004);
            return;

        case 4: // '\004'
            b(0x11000009);
            return;

        case 5: // '\005'
            b(0x1100000b);
            return;

        case 8: // '\b'
            b(0x1100000a);
            return;

        case 9: // '\t'
            b(0x1100000c);
            return;

        case 10: // '\n'
            b(0x11000001);
            return;

        case 6: // '\006'
            b(0x11000002);
            return;

        case 7: // '\007'
            b(0x11000003);
            return;
        }
_L48:
        String s1 = item.g;
        a();
        if (s1.charAt(0) == '[')
        {
            a(classwriter, '[' + s1);
            return;
        } else
        {
            b(0x11700000 | classwriter.c(s1));
            return;
        }
_L49:
        String s = item.g;
        a();
        if (s.charAt(0) == '[')
        {
            a(classwriter, s);
            return;
        } else
        {
            b(0x1700000 | classwriter.c(s));
            return;
        }
    }

    void a(ClassWriter classwriter, int j, Type atype[], int k)
    {
        int l = 1;
        int i1 = 0;
        c = new int[k];
        d = new int[0];
        if ((j & 8) == 0)
        {
            if ((0x40000 & j) == 0)
            {
                c[0] = 0x1700000 | classwriter.c(classwriter.F);
            } else
            {
                c[0] = 0x1000006;
                i1 = 0;
            }
        } else
        {
            l = 0;
            i1 = 0;
        }
        while (i1 < atype.length) 
        {
            int k1 = b(classwriter, atype[i1].getDescriptor());
            int ai1[] = c;
            int l1 = l + 1;
            ai1[l] = k1;
            int ai[];
            int j1;
            if (k1 == 0x1000004 || k1 == 0x1000003)
            {
                int ai2[] = c;
                l = l1 + 1;
                ai2[l1] = 0x1000000;
            } else
            {
                l = l1;
            }
            i1++;
        }
        for (; l < k; l = j1)
        {
            ai = c;
            j1 = l + 1;
            ai[l] = 0x1000000;
        }

    }

    boolean a(ClassWriter classwriter, Frame frame, int j)
    {
        int k = c.length;
        int l = d.length;
        int ai[] = frame.c;
        boolean flag = false;
        if (ai == null)
        {
            frame.c = new int[k];
            flag = true;
        }
        int i1 = 0;
        boolean flag1 = flag;
        while (i1 < k) 
        {
            if (e != null && i1 < e.length)
            {
                int k3 = e[i1];
                if (k3 == 0)
                {
                    k3 = c[i1];
                } else
                {
                    int l3 = 0xf0000000 & k3;
                    int i4 = 0xf000000 & k3;
                    if (i4 != 0x1000000)
                    {
                        int j4;
                        if (i4 == 0x2000000)
                        {
                            j4 = l3 + c[0x7fffff & k3];
                        } else
                        {
                            j4 = l3 + d[l - (0x7fffff & k3)];
                        }
                        int j1;
                        boolean flag2;
                        int k1;
                        boolean flag3;
                        int l1;
                        int i2;
                        int j2;
                        int k2;
                        int l2;
                        int i3;
                        int j3;
                        boolean flag4;
                        boolean flag5;
                        if ((k3 & 0x800000) != 0 && (j4 == 0x1000004 || j4 == 0x1000003))
                        {
                            k3 = 0x1000000;
                        } else
                        {
                            k3 = j4;
                        }
                    }
                }
            } else
            {
                k3 = c[i1];
            }
            if (i != null)
            {
                k3 = a(classwriter, k3);
            }
            flag1 |= a(classwriter, k3, frame.c, i1);
            i1++;
        }
        if (j > 0)
        {
            j3 = 0;
            for (flag4 = flag1; j3 < k; flag4 = flag5)
            {
                flag5 = flag4 | a(classwriter, c[j3], frame.c, j3);
                j3++;
            }

            if (frame.d == null)
            {
                frame.d = new int[1];
                flag4 = true;
            }
            flag3 = flag4 | a(classwriter, j, frame.d, 0);
        } else
        {
            j1 = d.length + b.f;
            if (frame.d == null)
            {
                frame.d = new int[j1 + g];
                flag2 = true;
            } else
            {
                flag2 = flag1;
            }
            k1 = 0;
            flag3 = flag2;
            for (; k1 < j1; k1++)
            {
                i3 = d[k1];
                if (i != null)
                {
                    i3 = a(classwriter, i3);
                }
                flag3 |= a(classwriter, i3, frame.d, k1);
            }

            l1 = 0;
            while (l1 < g) 
            {
                i2 = f[l1];
                j2 = 0xf0000000 & i2;
                k2 = 0xf000000 & i2;
                if (k2 != 0x1000000)
                {
                    if (k2 == 0x2000000)
                    {
                        l2 = j2 + c[0x7fffff & i2];
                    } else
                    {
                        l2 = j2 + d[l - (0x7fffff & i2)];
                    }
                    if ((i2 & 0x800000) != 0 && (l2 == 0x1000004 || l2 == 0x1000003))
                    {
                        i2 = 0x1000000;
                    } else
                    {
                        i2 = l2;
                    }
                }
                if (i != null)
                {
                    i2 = a(classwriter, i2);
                }
                flag3 |= a(classwriter, i2, frame.d, j1 + l1);
                l1++;
            }
        }
        return flag3;
    }

    static 
    {
        int ai[] = new int[202];
        for (int j = 0; j < ai.length; j++)
        {
            ai[j] = -69 + "EFFFFFFFFGGFFFGGFFFEEFGFGFEEEEEEEEEEEEEEEEEEEEDEDEDDDDDCDCDEEEEEEEEEEEEEEEEEEEEBABABBBBDCFFFGGGEDCDCDCDCDCDCDCDCDCDCEEEEDDDDDDDCDCDCEFEFDDEEFFDEDEEEBDDBBDDDDDDCCCCCCCCEFEDDDCDCDEEEEEEEEEEFEEEEEEDDEEDDEE".charAt(j);
        }

        a = ai;
    }
}

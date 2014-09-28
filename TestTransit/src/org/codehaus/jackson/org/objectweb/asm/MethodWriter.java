// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.org.objectweb.asm;


// Referenced classes of package org.codehaus.jackson.org.objectweb.asm:
//            MethodVisitor, ByteVector, ClassWriter, Type, 
//            Label, Item, Edge, Frame, 
//            Handler, Attribute, AnnotationWriter, ClassReader, 
//            AnnotationVisitor

class MethodWriter
    implements MethodVisitor
{

    private int A;
    private Handler B;
    private Handler C;
    private int D;
    private ByteVector E;
    private int F;
    private ByteVector G;
    private int H;
    private ByteVector I;
    private Attribute J;
    private boolean K;
    private int L;
    private final int M;
    private Label N;
    private Label O;
    private Label P;
    private int Q;
    private int R;
    private int S;
    MethodWriter a;
    final ClassWriter b;
    private int c;
    private final int d;
    private final int e;
    private final String f;
    String g;
    int h;
    int i;
    int j;
    int k[];
    private ByteVector l;
    private AnnotationWriter m;
    private AnnotationWriter n;
    private AnnotationWriter o[];
    private AnnotationWriter p[];
    private Attribute q;
    private ByteVector r;
    private int s;
    private int t;
    private int u;
    private ByteVector v;
    private int w;
    private int x[];
    private int y;
    private int z[];

    MethodWriter(ClassWriter classwriter, int i1, String s1, String s2, String s3, String as[], boolean flag, 
            boolean flag1)
    {
        r = new ByteVector();
        if (classwriter.A == null)
        {
            classwriter.A = this;
        } else
        {
            classwriter.B.a = this;
        }
        classwriter.B = this;
        b = classwriter;
        c = i1;
        d = classwriter.newUTF8(s1);
        e = classwriter.newUTF8(s2);
        f = s2;
        g = s3;
        if (as != null && as.length > 0)
        {
            j = as.length;
            k = new int[j];
            for (int l1 = 0; l1 < j; l1++)
            {
                k[l1] = classwriter.newClass(as[l1]);
            }

        }
        int j1 = 0;
        if (!flag1)
        {
            if (flag)
            {
                j1 = 1;
            } else
            {
                j1 = 2;
            }
        }
        M = j1;
        if (flag || flag1)
        {
            if (flag1 && "<init>".equals(s1))
            {
                c = 0x40000 | c;
            }
            int k1 = Type.getArgumentsAndReturnSizes(f) >> 2;
            if ((i1 & 8) != 0)
            {
                k1--;
            }
            t = k1;
            N = new Label();
            Label label = N;
            label.a = 8 | label.a;
            visitLabel(N);
        }
    }

    static int a(byte abyte0[], int i1)
    {
        return (0xff & abyte0[i1]) << 24 | (0xff & abyte0[i1 + 1]) << 16 | (0xff & abyte0[i1 + 2]) << 8 | 0xff & abyte0[i1 + 3];
    }

    static int a(int ai[], int ai1[], int i1, int j1)
    {
        int k1 = j1 - i1;
        int l1 = 0;
        while (l1 < ai.length) 
        {
            if (i1 < ai[l1] && ai[l1] <= j1)
            {
                k1 += ai1[l1];
            } else
            if (j1 < ai[l1] && ai[l1] <= i1)
            {
                k1 -= ai1[l1];
            }
            l1++;
        }
        return k1;
    }

    private void a(int i1, int j1)
    {
_L8:
        if (i1 >= j1) goto _L2; else goto _L1
_L1:
        int k1;
        int l1;
        k1 = z[i1];
        l1 = 0xf0000000 & k1;
        if (l1 != 0) goto _L4; else goto _L3
_L3:
        int k2 = k1 & 0xfffff;
        k1 & 0xff00000;
        JVM INSTR lookupswitch 2: default 60
    //                   24117248: 76
    //                   25165824: 112;
           goto _L5 _L6 _L7
_L5:
        v.putByte(k2);
_L9:
        i1++;
          goto _L8
_L6:
        v.putByte(7).putShort(b.newClass(b.E[k2].g));
          goto _L9
_L7:
        v.putByte(8).putShort(b.E[k2].c);
          goto _L9
_L4:
        StringBuffer stringbuffer;
        stringbuffer = new StringBuffer();
        int i2 = l1 >> 28;
        do
        {
            int j2 = i2 - 1;
            if (i2 <= 0)
            {
                break;
            }
            stringbuffer.append('[');
            i2 = j2;
        } while (true);
        if ((k1 & 0xff00000) != 0x1700000) goto _L11; else goto _L10
_L10:
        stringbuffer.append('L');
        stringbuffer.append(b.E[k1 & 0xfffff].g);
        stringbuffer.append(';');
_L12:
        v.putByte(7).putShort(b.newClass(stringbuffer.toString()));
          goto _L9
_L11:
        switch (k1 & 0xf)
        {
        case 4: // '\004'
        case 5: // '\005'
        case 6: // '\006'
        case 7: // '\007'
        case 8: // '\b'
        default:
            stringbuffer.append('J');
            break;

        case 1: // '\001'
            stringbuffer.append('I');
            break;

        case 2: // '\002'
            stringbuffer.append('F');
            break;

        case 3: // '\003'
            stringbuffer.append('D');
            break;

        case 9: // '\t'
            stringbuffer.append('Z');
            break;

        case 10: // '\n'
            stringbuffer.append('B');
            break;

        case 11: // '\013'
            stringbuffer.append('C');
            break;

        case 12: // '\f'
            stringbuffer.append('S');
            break;
        }
        if (true) goto _L12; else goto _L2
_L2:
    }

    private void a(int i1, int j1, int k1)
    {
        int l1 = k1 + (j1 + 3);
        if (z == null || z.length < l1)
        {
            z = new int[l1];
        }
        z[0] = i1;
        z[1] = j1;
        z[2] = k1;
        y = 3;
    }

    private void a(int i1, Label label)
    {
        Edge edge = new Edge();
        edge.a = i1;
        edge.b = label;
        edge.c = P.j;
        P.j = edge;
    }

    private void a(Object obj)
    {
        if (obj instanceof String)
        {
            v.putByte(7).putShort(b.newClass((String)obj));
            return;
        }
        if (obj instanceof Integer)
        {
            v.putByte(((Integer)obj).intValue());
            return;
        } else
        {
            v.putByte(8).putShort(((Label)obj).c);
            return;
        }
    }

    private void a(Label label, Label alabel[])
    {
        int i1 = 0;
        if (P != null)
        {
            if (M == 0)
            {
                P.h.a(171, 0, null, null);
                a(0, label);
                Label label1 = label.a();
                label1.a = 0x10 | label1.a;
                for (int j1 = 0; j1 < alabel.length; j1++)
                {
                    a(0, alabel[j1]);
                    Label label2 = alabel[j1].a();
                    label2.a = 0x10 | label2.a;
                }

            } else
            {
                Q = -1 + Q;
                a(Q, label);
                for (; i1 < alabel.length; i1++)
                {
                    a(Q, alabel[i1]);
                }

            }
            e();
        }
    }

    static void a(byte abyte0[], int i1, int j1)
    {
        abyte0[i1] = (byte)(j1 >>> 8);
        abyte0[i1 + 1] = (byte)j1;
    }

    static void a(int ai[], int ai1[], Label label)
    {
        if ((4 & label.a) == 0)
        {
            label.c = a(ai, ai1, 0, label.c);
            label.a = 4 | label.a;
        }
    }

    static short b(byte abyte0[], int i1)
    {
        return (short)((0xff & abyte0[i1]) << 8 | 0xff & abyte0[i1 + 1]);
    }

    private void b()
    {
        if (x != null)
        {
            if (v == null)
            {
                v = new ByteVector();
            }
            c();
            u = 1 + u;
        }
        x = z;
        z = null;
    }

    private void b(Frame frame)
    {
        int ai[] = frame.c;
        int ai1[] = frame.d;
        int i1 = 0;
        int j1 = 0;
        int k1 = 0;
        while (i1 < ai.length) 
        {
            int i4 = ai[i1];
            if (i4 == 0x1000000)
            {
                k1++;
            } else
            {
                j1 += k1 + 1;
                k1 = 0;
            }
            if (i4 == 0x1000004 || i4 == 0x1000003)
            {
                i1++;
            }
            i1++;
        }
        int l1 = 0;
        int i2 = 0;
        for (; l1 < ai1.length; l1++)
        {
            int l3 = ai1[l1];
            i2++;
            if (l3 == 0x1000004 || l3 == 0x1000003)
            {
                l1++;
            }
        }

        a(frame.b.c, j1, i2);
        int j2 = 0;
        int k2;
        do
        {
            k2 = 0;
            if (j1 <= 0)
            {
                break;
            }
            int j3 = ai[j2];
            int ai3[] = z;
            int k3 = y;
            y = k3 + 1;
            ai3[k3] = j3;
            if (j3 == 0x1000004 || j3 == 0x1000003)
            {
                j2++;
            }
            j2++;
            j1--;
        } while (true);
        for (; k2 < ai1.length; k2++)
        {
            int l2 = ai1[k2];
            int ai2[] = z;
            int i3 = y;
            y = i3 + 1;
            ai2[i3] = l2;
            if (l2 == 0x1000004 || l2 == 0x1000003)
            {
                k2++;
            }
        }

        b();
    }

    static int c(byte abyte0[], int i1)
    {
        return (0xff & abyte0[i1]) << 8 | 0xff & abyte0[i1 + 1];
    }

    private void c()
    {
        char c1;
        int i1;
        int j1;
        int k1;
        int l1;
        int i2;
        int j2;
        int k2;
        int l2;
        c1 = '@';
        i1 = 0;
        j1 = z[1];
        k1 = z[2];
        if ((0xffff & b.b) < 50)
        {
            v.putShort(z[0]).putShort(j1);
            a(3, j1 + 3);
            v.putShort(k1);
            a(j1 + 3, k1 + (j1 + 3));
            return;
        }
        l1 = x[1];
        if (u == 0)
        {
            i2 = z[0];
        } else
        {
            i2 = -1 + (z[0] - x[0]);
        }
        if (k1 != 0) goto _L2; else goto _L1
_L1:
        k2 = j1 - l1;
        k2;
        JVM INSTR tableswitch -3 3: default 164
    //                   -3 340
    //                   -2 340
    //                   -1 340
    //                   0 350
    //                   1 368
    //                   2 368
    //                   3 368;
           goto _L3 _L4 _L4 _L4 _L5 _L6 _L6 _L6
_L3:
        c1 = '\377';
_L15:
        j2 = l1;
_L12:
        if (c1 == '\377') goto _L8; else goto _L7
_L7:
        l2 = 3;
_L13:
        if (i1 >= j2) goto _L8; else goto _L9
_L9:
        if (z[l2] == x[l2]) goto _L11; else goto _L10
_L10:
        c1 = '\377';
          goto _L8
_L4:
        c1 = '\370';
        l1 = j1;
        continue; /* Loop/switch isn't completed */
_L5:
        if (i2 < c1)
        {
            c1 = '\0';
        } else
        {
            c1 = '\373';
        }
        continue; /* Loop/switch isn't completed */
_L6:
        c1 = '\374';
        continue; /* Loop/switch isn't completed */
_L2:
        if (j1 == l1 && k1 == 1)
        {
            if (i2 >= 63)
            {
                c1 = '\367';
            }
            j2 = l1;
            k2 = 0;
        } else
        {
            c1 = '\377';
            j2 = l1;
            k2 = 0;
        }
          goto _L12
_L11:
        l2++;
        i1++;
          goto _L13
_L8:
        switch (c1)
        {
        default:
            v.putByte(255).putShort(i2).putShort(j1);
            a(3, j1 + 3);
            v.putShort(k1);
            a(j1 + 3, k1 + (j1 + 3));
            return;

        case 0: // '\0'
            v.putByte(i2);
            return;

        case 64: // '@'
            v.putByte(i2 + 64);
            a(j1 + 3, j1 + 4);
            return;

        case 247: 
            v.putByte(247).putShort(i2);
            a(j1 + 3, j1 + 4);
            return;

        case 251: 
            v.putByte(251).putShort(i2);
            return;

        case 248: 
            v.putByte(k2 + 251).putShort(i2);
            return;

        case 252: 
            v.putByte(k2 + 251).putShort(i2);
            break;
        }
        a(j2 + 3, j1 + 3);
        return;
        if (true) goto _L15; else goto _L14
_L14:
    }

    private void d()
    {
        byte abyte0[];
        int ai[];
        int ai1[];
        boolean aflag[];
        int i1;
        abyte0 = r.a;
        ai = new int[0];
        ai1 = new int[0];
        aflag = new boolean[r.b];
        i1 = 3;
_L14:
        int j1;
        int k1;
        if (i1 == 3)
        {
            i1 = 2;
        }
        j1 = i1;
        k1 = 0;
_L11:
        int i11;
        int j11;
        if (k1 >= abyte0.length)
        {
            break MISSING_BLOCK_LABEL_631;
        }
        i11 = 0xff & abyte0[k1];
        j11 = 0;
        ClassWriter.a[i11];
        JVM INSTR tableswitch 0 16: default 156
    //                   0 231
    //                   1 604
    //                   2 613
    //                   3 604
    //                   4 231
    //                   5 613
    //                   6 613
    //                   7 622
    //                   8 240
    //                   9 386
    //                   10 604
    //                   11 613
    //                   12 613
    //                   13 395
    //                   14 488
    //                   15 156
    //                   16 570;
           goto _L1 _L2 _L3 _L4 _L3 _L2 _L4 _L4 _L5 _L6 _L7 _L3 _L4 _L4 _L8 _L9 _L1 _L10
_L1:
        k1 += 4;
_L12:
        if (j11 != 0)
        {
            int ai2[] = new int[1 + ai.length];
            int ai3[] = new int[1 + ai1.length];
            System.arraycopy(ai, 0, ai2, 0, ai.length);
            System.arraycopy(ai1, 0, ai3, 0, ai1.length);
            ai2[ai.length] = k1;
            ai3[ai1.length] = j11;
            ByteVector bytevector;
            int l1;
            Handler handler;
            int i2;
            Attribute attribute;
            Label alabel[];
            int j2;
            byte abyte1[];
            int k2;
            ByteVector bytevector1;
            byte abyte2[];
            int l2;
            int i3;
            int j3;
            int k3;
            Frame frame;
            Type atype[];
            Label label;
            int l3;
            int i4;
            int j4;
            int k4;
            int l4;
            int i5;
            int j5;
            int k5;
            int l5;
            int i6;
            int j6;
            int k6;
            int l6;
            int i7;
            int j7;
            int k7;
            int l7;
            int i8;
            int j8;
            int k8;
            int l8;
            int i9;
            int j9;
            int k9;
            int l9;
            int i10;
            int j10;
            int k10;
            int l10;
            boolean flag;
            int k11;
            boolean flag1;
            int l11;
            int i12;
            int j12;
            int k12;
            int l12;
            if (j11 > 0)
            {
                j1 = 3;
                ai1 = ai3;
                ai = ai2;
            } else
            {
                ai1 = ai3;
                ai = ai2;
            }
        }
        if (true) goto _L11; else goto _L2
_L2:
        k1++;
        j11 = 0;
          goto _L12
_L6:
        byte byte0;
        if (i11 > 201)
        {
            if (i11 < 218)
            {
                k12 = i11 - 49;
            } else
            {
                k12 = i11 - 20;
            }
            l12 = k1 + c(abyte0, k1 + 1);
            i11 = k12;
            i12 = l12;
        } else
        {
            i12 = k1 + b(abyte0, k1 + 1);
        }
        j12 = a(ai, ai1, k1, i12);
        if ((j12 < -32768 || j12 > 32767) && !aflag[k1])
        {
            if (i11 == 167 || i11 == 168)
            {
                byte0 = 2;
            } else
            {
                byte0 = 5;
            }
            aflag[k1] = true;
        } else
        {
            byte0 = 0;
        }
        k1 += 3;
        j11 = byte0;
          goto _L12
_L7:
        k1 += 5;
        j11 = 0;
          goto _L12
_L8:
        if (j1 == 1)
        {
            j11 = -(3 & a(ai, ai1, 0, k1));
        } else
        {
            flag1 = aflag[k1];
            j11 = 0;
            if (!flag1)
            {
                j11 = k1 & 3;
                aflag[k1] = true;
            }
        }
        l11 = (k1 + 4) - (k1 & 3);
        k1 = l11 + (12 + 4 * (1 + (a(abyte0, l11 + 8) - a(abyte0, l11 + 4))));
          goto _L12
_L9:
        if (j1 == 1)
        {
            j11 = -(3 & a(ai, ai1, 0, k1));
        } else
        {
            flag = aflag[k1];
            j11 = 0;
            if (!flag)
            {
                j11 = k1 & 3;
                aflag[k1] = true;
            }
        }
        k11 = (k1 + 4) - (k1 & 3);
        k1 = k11 + (8 + 8 * a(abyte0, k11 + 4));
          goto _L12
_L10:
        if ((0xff & abyte0[k1 + 1]) == 132)
        {
            k1 += 6;
            j11 = 0;
        } else
        {
            k1 += 4;
            j11 = 0;
        }
          goto _L12
_L3:
        k1 += 2;
        j11 = 0;
          goto _L12
_L4:
        k1 += 3;
        j11 = 0;
          goto _L12
_L5:
        k1 += 5;
        j11 = 0;
          goto _L12
        if (j1 < 3)
        {
            j1--;
        }
        if (j1 == 0)
        {
            bytevector = new ByteVector(r.b);
            l1 = 0;
            do
            {
                if (l1 < r.b)
                {
                    i4 = 0xff & abyte0[l1];
                    switch (ClassWriter.a[i4])
                    {
                    case 15: // '\017'
                    default:
                        bytevector.putByteArray(abyte0, l1, 4);
                        l1 += 4;
                        continue;

                    case 0: // '\0'
                    case 4: // '\004'
                        bytevector.putByte(i4);
                        l1++;
                        continue;

                    case 8: // '\b'
                        if (i4 > 201)
                        {
                            if (i4 < 218)
                            {
                                k10 = i4 - 49;
                            } else
                            {
                                k10 = i4 - 20;
                            }
                            l10 = l1 + c(abyte0, l1 + 1);
                            i4 = k10;
                            k9 = l10;
                        } else
                        {
                            k9 = l1 + b(abyte0, l1 + 1);
                        }
                        l9 = a(ai, ai1, l1, k9);
                        if (aflag[l1])
                        {
                            if (i4 == 167)
                            {
                                bytevector.putByte(200);
                                j10 = l9;
                            } else
                            if (i4 == 168)
                            {
                                bytevector.putByte(201);
                                j10 = l9;
                            } else
                            {
                                if (i4 <= 166)
                                {
                                    i10 = -1 + (1 ^ i4 + 1);
                                } else
                                {
                                    i10 = i4 ^ 1;
                                }
                                bytevector.putByte(i10);
                                bytevector.putShort(8);
                                bytevector.putByte(200);
                                j10 = l9 - 3;
                            }
                            bytevector.putInt(j10);
                        } else
                        {
                            bytevector.putByte(i4);
                            bytevector.putShort(l9);
                        }
                        l1 += 3;
                        continue;

                    case 9: // '\t'
                        j9 = a(ai, ai1, l1, l1 + a(abyte0, l1 + 1));
                        bytevector.putByte(i4);
                        bytevector.putInt(j9);
                        l1 += 5;
                        continue;

                    case 13: // '\r'
                        l6 = (l1 + 4) - (l1 & 3);
                        bytevector.putByte(170);
                        bytevector.putByteArray(null, 0, (4 - bytevector.b % 4) % 4);
                        i7 = l1 + a(abyte0, l6);
                        j7 = l6 + 4;
                        bytevector.putInt(a(ai, ai1, l1, i7));
                        k7 = a(abyte0, j7);
                        l7 = j7 + 4;
                        bytevector.putInt(k7);
                        i8 = 1 + (a(abyte0, l7) - k7);
                        j8 = l7 + 4;
                        bytevector.putInt(a(abyte0, j8 - 4));
                        k5 = j8;
                        for (k8 = i8; k8 > 0;)
                        {
                            l8 = l1 + a(abyte0, k5);
                            i9 = k5 + 4;
                            bytevector.putInt(a(ai, ai1, l1, l8));
                            k8--;
                            k5 = i9;
                        }

                        break;

                    case 14: // '\016'
                        j4 = (l1 + 4) - (l1 & 3);
                        bytevector.putByte(171);
                        bytevector.putByteArray(null, 0, (4 - bytevector.b % 4) % 4);
                        k4 = l1 + a(abyte0, j4);
                        l4 = j4 + 4;
                        bytevector.putInt(a(ai, ai1, l1, k4));
                        i5 = a(abyte0, l4);
                        j5 = l4 + 4;
                        bytevector.putInt(i5);
                        k5 = j5;
                        for (l5 = i5; l5 > 0;)
                        {
                            bytevector.putInt(a(abyte0, k5));
                            i6 = k5 + 4;
                            j6 = l1 + a(abyte0, i6);
                            k6 = i6 + 4;
                            bytevector.putInt(a(ai, ai1, l1, j6));
                            l5--;
                            k5 = k6;
                        }

                        break;

                    case 16: // '\020'
                        if ((0xff & abyte0[l1 + 1]) == 132)
                        {
                            bytevector.putByteArray(abyte0, l1, 6);
                            l1 += 6;
                        } else
                        {
                            bytevector.putByteArray(abyte0, l1, 4);
                            l1 += 4;
                        }
                        continue;

                    case 1: // '\001'
                    case 3: // '\003'
                    case 10: // '\n'
                        bytevector.putByteArray(abyte0, l1, 2);
                        l1 += 2;
                        continue;

                    case 2: // '\002'
                    case 5: // '\005'
                    case 6: // '\006'
                    case 11: // '\013'
                    case 12: // '\f'
                        bytevector.putByteArray(abyte0, l1, 3);
                        l1 += 3;
                        continue;

                    case 7: // '\007'
                        bytevector.putByteArray(abyte0, l1, 5);
                        l1 += 5;
                        continue;
                    }
                } else
                {
                    if (u > 0)
                    {
                        if (M == 0)
                        {
                            u = 0;
                            v = null;
                            x = null;
                            z = null;
                            frame = new Frame();
                            frame.b = N;
                            atype = Type.getArgumentTypes(f);
                            frame.a(b, c, atype, t);
                            b(frame);
                            for (label = N; label != null; label = label.i)
                            {
                                l3 = -3 + label.c;
                                if ((0x20 & label.a) != 0 || l3 >= 0 && aflag[l3])
                                {
                                    a(ai, ai1, label);
                                    b(label.h);
                                }
                            }

                        } else
                        {
                            b.I = true;
                        }
                    }
                    for (handler = B; handler != null; handler = handler.f)
                    {
                        a(ai, ai1, handler.a);
                        a(ai, ai1, handler.b);
                        a(ai, ai1, handler.c);
                    }

                    for (i2 = 0; i2 < 2; i2++)
                    {
                        if (i2 == 0)
                        {
                            bytevector1 = E;
                        } else
                        {
                            bytevector1 = G;
                        }
                        if (bytevector1 == null)
                        {
                            continue;
                        }
                        abyte2 = bytevector1.a;
                        for (l2 = 0; l2 < bytevector1.b; l2 += 10)
                        {
                            i3 = c(abyte2, l2);
                            j3 = a(ai, ai1, 0, i3);
                            a(abyte2, l2, j3);
                            k3 = a(ai, ai1, 0, i3 + c(abyte2, l2 + 2)) - j3;
                            a(abyte2, l2 + 2, k3);
                        }

                    }

                    if (I != null)
                    {
                        abyte1 = I.a;
                        for (k2 = 0; k2 < I.b; k2 += 4)
                        {
                            a(abyte1, k2, a(ai, ai1, 0, c(abyte1, k2)));
                        }

                    }
                    for (attribute = J; attribute != null; attribute = attribute.a)
                    {
                        alabel = attribute.getLabels();
                        if (alabel == null)
                        {
                            continue;
                        }
                        for (j2 = -1 + alabel.length; j2 >= 0; j2--)
                        {
                            a(ai, ai1, alabel[j2]);
                        }

                    }

                    r = bytevector;
                    return;
                }
                l1 = k5;
            } while (true);
        }
        i1 = j1;
        if (true) goto _L14; else goto _L13
_L13:
    }

    private void e()
    {
        if (M == 0)
        {
            Label label = new Label();
            label.h = new Frame();
            label.h.b = label;
            label.a(this, r.b, r.a);
            O.i = label;
            O = label;
        } else
        {
            P.g = R;
        }
        P = null;
    }

    final int a()
    {
        int k1;
        if (h != 0)
        {
            k1 = 6 + i;
        } else
        {
            if (K)
            {
                d();
            }
            int i1 = 8;
            if (r.b > 0)
            {
                b.newUTF8("Code");
                int l2 = i1 + (18 + r.b + 8 * A);
                if (E != null)
                {
                    b.newUTF8("LocalVariableTable");
                    l2 += 8 + E.b;
                }
                if (G != null)
                {
                    b.newUTF8("LocalVariableTypeTable");
                    l2 += 8 + G.b;
                }
                if (I != null)
                {
                    b.newUTF8("LineNumberTable");
                    l2 += 8 + I.b;
                }
                int j1;
                int l1;
                int i2;
                int j2;
                int k2;
                if (v != null)
                {
                    boolean flag;
                    ClassWriter classwriter;
                    String s1;
                    if ((0xffff & b.b) >= 50)
                    {
                        flag = true;
                    } else
                    {
                        flag = false;
                    }
                    classwriter = b;
                    if (flag)
                    {
                        s1 = "StackMapTable";
                    } else
                    {
                        s1 = "StackMap";
                    }
                    classwriter.newUTF8(s1);
                    i1 = l2 + (8 + v.b);
                } else
                {
                    i1 = l2;
                }
                if (J != null)
                {
                    i1 += J.a(b, r.a, r.b, s, t);
                }
            }
            if (j > 0)
            {
                b.newUTF8("Exceptions");
                i1 += 8 + 2 * j;
            }
            if ((0x1000 & c) != 0 && ((0xffff & b.b) < 49 || (0x40000 & c) != 0))
            {
                b.newUTF8("Synthetic");
                i1 += 6;
            }
            if ((0x20000 & c) != 0)
            {
                b.newUTF8("Deprecated");
                i1 += 6;
            }
            if (g != null)
            {
                b.newUTF8("Signature");
                b.newUTF8(g);
                i1 += 8;
            }
            if (l != null)
            {
                b.newUTF8("AnnotationDefault");
                i1 += 6 + l.b;
            }
            if (m != null)
            {
                b.newUTF8("RuntimeVisibleAnnotations");
                i1 += 8 + m.a();
            }
            if (n != null)
            {
                b.newUTF8("RuntimeInvisibleAnnotations");
                i1 += 8 + n.a();
            }
            if (o != null)
            {
                b.newUTF8("RuntimeVisibleParameterAnnotations");
                j1 = i1 + (7 + 2 * (o.length - S));
                j2 = -1 + o.length;
                while (j2 >= S) 
                {
                    if (o[j2] == null)
                    {
                        k2 = 0;
                    } else
                    {
                        k2 = o[j2].a();
                    }
                    j1 += k2;
                    j2--;
                }
            } else
            {
                j1 = i1;
            }
            if (p != null)
            {
                b.newUTF8("RuntimeInvisibleParameterAnnotations");
                j1 += 7 + 2 * (p.length - S);
                l1 = -1 + p.length;
                while (l1 >= S) 
                {
                    if (p[l1] == null)
                    {
                        i2 = 0;
                    } else
                    {
                        i2 = p[l1].a();
                    }
                    j1 += i2;
                    l1--;
                }
            }
            k1 = j1;
            if (q != null)
            {
                return k1 + q.a(b, null, 0, -1, -1);
            }
        }
        return k1;
    }

    final void a(ByteVector bytevector)
    {
        boolean flag = true;
        int i1 = 0x60000 | (0x40000 & c) / 64;
        bytevector.putShort(c & ~i1).putShort(d).putShort(e);
        if (h != 0)
        {
            bytevector.putByteArray(b.J.b, h, i);
        } else
        {
            int j1;
            if (r.b > 0)
            {
                j1 = ((flag) ? 1 : 0);
            } else
            {
                j1 = 0;
            }
            if (j > 0)
            {
                j1++;
            }
            if ((0x1000 & c) != 0 && ((0xffff & b.b) < 49 || (0x40000 & c) != 0))
            {
                j1++;
            }
            if ((0x20000 & c) != 0)
            {
                j1++;
            }
            if (g != null)
            {
                j1++;
            }
            if (l != null)
            {
                j1++;
            }
            if (m != null)
            {
                j1++;
            }
            if (n != null)
            {
                j1++;
            }
            if (o != null)
            {
                j1++;
            }
            if (p != null)
            {
                j1++;
            }
            if (q != null)
            {
                j1 += q.a();
            }
            bytevector.putShort(j1);
            if (r.b > 0)
            {
                int l1 = 12 + r.b + 8 * A;
                if (E != null)
                {
                    l1 += 8 + E.b;
                }
                if (G != null)
                {
                    l1 += 8 + G.b;
                }
                if (I != null)
                {
                    l1 += 8 + I.b;
                }
                int i2;
                int j2;
                if (v != null)
                {
                    i2 = l1 + (8 + v.b);
                } else
                {
                    i2 = l1;
                }
                if (J != null)
                {
                    i2 += J.a(b, r.a, r.b, s, t);
                }
                bytevector.putShort(b.newUTF8("Code")).putInt(i2);
                bytevector.putShort(s).putShort(t);
                bytevector.putInt(r.b).putByteArray(r.a, 0, r.b);
                bytevector.putShort(A);
                if (A > 0)
                {
                    for (Handler handler = B; handler != null; handler = handler.f)
                    {
                        bytevector.putShort(handler.a.c).putShort(handler.b.c).putShort(handler.c.c).putShort(handler.e);
                    }

                }
                if (E != null)
                {
                    j2 = ((flag) ? 1 : 0);
                } else
                {
                    j2 = 0;
                }
                if (G != null)
                {
                    j2++;
                }
                if (I != null)
                {
                    j2++;
                }
                if (v != null)
                {
                    j2++;
                }
                if (J != null)
                {
                    j2 += J.a();
                }
                bytevector.putShort(j2);
                if (E != null)
                {
                    bytevector.putShort(b.newUTF8("LocalVariableTable"));
                    bytevector.putInt(2 + E.b).putShort(D);
                    bytevector.putByteArray(E.a, 0, E.b);
                }
                if (G != null)
                {
                    bytevector.putShort(b.newUTF8("LocalVariableTypeTable"));
                    bytevector.putInt(2 + G.b).putShort(F);
                    bytevector.putByteArray(G.a, 0, G.b);
                }
                if (I != null)
                {
                    bytevector.putShort(b.newUTF8("LineNumberTable"));
                    bytevector.putInt(2 + I.b).putShort(H);
                    bytevector.putByteArray(I.a, 0, I.b);
                }
                if (v != null)
                {
                    int k1;
                    ClassWriter classwriter;
                    String s1;
                    if ((0xffff & b.b) < 50)
                    {
                        flag = false;
                    }
                    classwriter = b;
                    if (flag)
                    {
                        s1 = "StackMapTable";
                    } else
                    {
                        s1 = "StackMap";
                    }
                    bytevector.putShort(classwriter.newUTF8(s1));
                    bytevector.putInt(2 + v.b).putShort(u);
                    bytevector.putByteArray(v.a, 0, v.b);
                }
                if (J != null)
                {
                    J.a(b, r.a, r.b, t, s, bytevector);
                }
            }
            if (j > 0)
            {
                bytevector.putShort(b.newUTF8("Exceptions")).putInt(2 + 2 * j);
                bytevector.putShort(j);
                for (k1 = 0; k1 < j; k1++)
                {
                    bytevector.putShort(k[k1]);
                }

            }
            if ((0x1000 & c) != 0 && ((0xffff & b.b) < 49 || (0x40000 & c) != 0))
            {
                bytevector.putShort(b.newUTF8("Synthetic")).putInt(0);
            }
            if ((0x20000 & c) != 0)
            {
                bytevector.putShort(b.newUTF8("Deprecated")).putInt(0);
            }
            if (g != null)
            {
                bytevector.putShort(b.newUTF8("Signature")).putInt(2).putShort(b.newUTF8(g));
            }
            if (l != null)
            {
                bytevector.putShort(b.newUTF8("AnnotationDefault"));
                bytevector.putInt(l.b);
                bytevector.putByteArray(l.a, 0, l.b);
            }
            if (m != null)
            {
                bytevector.putShort(b.newUTF8("RuntimeVisibleAnnotations"));
                m.a(bytevector);
            }
            if (n != null)
            {
                bytevector.putShort(b.newUTF8("RuntimeInvisibleAnnotations"));
                n.a(bytevector);
            }
            if (o != null)
            {
                bytevector.putShort(b.newUTF8("RuntimeVisibleParameterAnnotations"));
                AnnotationWriter.a(o, S, bytevector);
            }
            if (p != null)
            {
                bytevector.putShort(b.newUTF8("RuntimeInvisibleParameterAnnotations"));
                AnnotationWriter.a(p, S, bytevector);
            }
            if (q != null)
            {
                q.a(b, null, 0, -1, -1, bytevector);
                return;
            }
        }
    }

    public AnnotationVisitor visitAnnotation(String s1, boolean flag)
    {
        ByteVector bytevector = new ByteVector();
        bytevector.putShort(b.newUTF8(s1)).putShort(0);
        AnnotationWriter annotationwriter = new AnnotationWriter(b, true, bytevector, bytevector, 2);
        if (flag)
        {
            annotationwriter.g = m;
            m = annotationwriter;
            return annotationwriter;
        } else
        {
            annotationwriter.g = n;
            n = annotationwriter;
            return annotationwriter;
        }
    }

    public AnnotationVisitor visitAnnotationDefault()
    {
        l = new ByteVector();
        return new AnnotationWriter(b, false, l, null, 0);
    }

    public void visitAttribute(Attribute attribute)
    {
        if (attribute.isCodeAttribute())
        {
            attribute.a = J;
            J = attribute;
            return;
        } else
        {
            attribute.a = q;
            q = attribute;
            return;
        }
    }

    public void visitCode()
    {
    }

    public void visitEnd()
    {
    }

    public void visitFieldInsn(int i1, String s1, String s2, String s3)
    {
        byte byte0;
        byte byte1;
        Item item;
        byte0 = 1;
        byte1 = -2;
        item = b.a(s1, s2, s3);
        if (P == null) goto _L2; else goto _L1
_L1:
        if (M != 0) goto _L4; else goto _L3
_L3:
        P.h.a(i1, 0, b, item);
_L2:
        r.b(i1, item.a);
        return;
_L4:
        char c1 = s3.charAt(0);
        i1;
        JVM INSTR tableswitch 178 180: default 104
    //                   178 159
    //                   179 192
    //                   180 232;
           goto _L5 _L6 _L7 _L8
_L8:
        break MISSING_BLOCK_LABEL_232;
_L6:
        break; /* Loop/switch isn't completed */
_L5:
        int k1;
        int j2 = Q;
        if (c1 == 'D' || c1 == 'J')
        {
            byte1 = -3;
        }
        k1 = j2 + byte1;
_L10:
        if (k1 > R)
        {
            R = k1;
        }
        Q = k1;
        if (true) goto _L2; else goto _L9
_L9:
        int i2 = Q;
        if (c1 == 'D' || c1 == 'J')
        {
            byte0 = 2;
        }
        k1 = byte0 + i2;
          goto _L10
_L7:
        int l1 = Q;
        byte byte2;
        if (c1 == 'D' || c1 == 'J')
        {
            byte2 = byte1;
        } else
        {
            byte2 = -1;
        }
        k1 = byte2 + l1;
          goto _L10
        int j1 = Q;
        if (c1 != 'D' && c1 != 'J')
        {
            byte0 = 0;
        }
        k1 = byte0 + j1;
          goto _L10
    }

    public void visitFrame(int i1, int j1, Object aobj[], int k1, Object aobj1[])
    {
        int l1 = 0;
        if (M != 0) goto _L2; else goto _L1
_L1:
        return;
_L2:
        if (i1 == -1)
        {
            a(r.b, j1, k1);
            int l2 = 0;
            int i3;
            do
            {
                i3 = 0;
                if (l2 >= j1)
                {
                    break;
                }
                if (aobj[l2] instanceof String)
                {
                    int ai5[] = z;
                    int k4 = y;
                    y = k4 + 1;
                    ai5[k4] = 0x1700000 | b.c((String)aobj[l2]);
                } else
                if (aobj[l2] instanceof Integer)
                {
                    int ai4[] = z;
                    int j4 = y;
                    y = j4 + 1;
                    ai4[j4] = ((Integer)aobj[l2]).intValue();
                } else
                {
                    int ai3[] = z;
                    int i4 = y;
                    y = i4 + 1;
                    ai3[i4] = 0x1800000 | b.a("", ((Label)aobj[l2]).c);
                }
                l2++;
            } while (true);
            while (i3 < k1) 
            {
                if (aobj1[i3] instanceof String)
                {
                    int ai2[] = z;
                    int l3 = y;
                    y = l3 + 1;
                    ai2[l3] = 0x1700000 | b.c((String)aobj1[i3]);
                } else
                if (aobj1[i3] instanceof Integer)
                {
                    int ai1[] = z;
                    int k3 = y;
                    y = k3 + 1;
                    ai1[k3] = ((Integer)aobj1[i3]).intValue();
                } else
                {
                    int ai[] = z;
                    int j3 = y;
                    y = j3 + 1;
                    ai[j3] = 0x1800000 | b.a("", ((Label)aobj1[i3]).c);
                }
                i3++;
            }
            b();
            return;
        }
        if (v != null) goto _L4; else goto _L3
_L3:
        int i2;
        v = new ByteVector();
        i2 = r.b;
_L12:
        i1;
        JVM INSTR tableswitch 0 4: default 436
    //                   0 492
    //                   1 567
    //                   2 608
    //                   3 629
    //                   4 668;
           goto _L5 _L6 _L7 _L8 _L9 _L10
_L5:
        break; /* Loop/switch isn't completed */
_L10:
        break MISSING_BLOCK_LABEL_668;
_L13:
        w = r.b;
        u = 1 + u;
        return;
_L4:
        i2 = -1 + (r.b - w);
        if (i2 >= 0) goto _L12; else goto _L11
_L11:
        if (i1 != 3)
        {
            throw new IllegalStateException();
        }
          goto _L1
_L6:
        v.putByte(255).putShort(i2).putShort(j1);
        for (int k2 = 0; k2 < j1; k2++)
        {
            a(aobj[k2]);
        }

        v.putShort(k1);
        while (l1 < k1) 
        {
            a(aobj1[l1]);
            l1++;
        }
          goto _L13
_L7:
        v.putByte(j1 + 251).putShort(i2);
        int j2 = 0;
        while (j2 < j1) 
        {
            a(aobj[j2]);
            j2++;
        }
          goto _L13
_L8:
        v.putByte(251 - j1).putShort(i2);
          goto _L13
_L9:
        if (i2 < 64)
        {
            v.putByte(i2);
        } else
        {
            v.putByte(251).putShort(i2);
        }
          goto _L13
        if (i2 < 64)
        {
            v.putByte(i2 + 64);
        } else
        {
            v.putByte(247).putShort(i2);
        }
        a(aobj1[0]);
          goto _L13
    }

    public void visitIincInsn(int i1, int j1)
    {
        if (P != null && M == 0)
        {
            P.h.a(132, i1, null, null);
        }
        if (M != 2)
        {
            int k1 = i1 + 1;
            if (k1 > t)
            {
                t = k1;
            }
        }
        if (i1 > 255 || j1 > 127 || j1 < -128)
        {
            r.putByte(196).b(132, i1).putShort(j1);
            return;
        } else
        {
            r.putByte(132).a(i1, j1);
            return;
        }
    }

    public void visitInsn(int i1)
    {
        r.putByte(i1);
        if (P != null)
        {
            if (M == 0)
            {
                P.h.a(i1, 0, null, null);
            } else
            {
                int j1 = Q + Frame.a[i1];
                if (j1 > R)
                {
                    R = j1;
                }
                Q = j1;
            }
            if (i1 >= 172 && i1 <= 177 || i1 == 191)
            {
                e();
            }
        }
    }

    public void visitIntInsn(int i1, int j1)
    {
        if (P != null)
        {
            if (M == 0)
            {
                P.h.a(i1, j1, null, null);
            } else
            if (i1 != 188)
            {
                int k1 = 1 + Q;
                if (k1 > R)
                {
                    R = k1;
                }
                Q = k1;
            }
        }
        if (i1 == 17)
        {
            r.b(i1, j1);
            return;
        } else
        {
            r.a(i1, j1);
            return;
        }
    }

    public void visitJumpInsn(int i1, Label label)
    {
        Label label1 = P;
        Label label2 = null;
        if (label1 != null)
        {
            if (M == 0)
            {
                P.h.a(i1, 0, null, null);
                Label label4 = label.a();
                label4.a = 0x10 | label4.a;
                a(0, label);
                label2 = null;
                if (i1 != 167)
                {
                    label2 = new Label();
                }
            } else
            if (i1 == 168)
            {
                if ((0x200 & label.a) == 0)
                {
                    label.a = 0x200 | label.a;
                    L = 1 + L;
                }
                Label label3 = P;
                label3.a = 0x80 | label3.a;
                a(1 + Q, label);
                label2 = new Label();
            } else
            {
                Q = Q + Frame.a[i1];
                a(Q, label);
                label2 = null;
            }
        }
        if ((2 & label.a) != 0 && label.c - r.b < -32768)
        {
            if (i1 == 167)
            {
                r.putByte(200);
            } else
            if (i1 == 168)
            {
                r.putByte(201);
            } else
            {
                if (label2 != null)
                {
                    label2.a = 0x10 | label2.a;
                }
                ByteVector bytevector = r;
                int j1;
                if (i1 <= 166)
                {
                    j1 = -1 + (1 ^ i1 + 1);
                } else
                {
                    j1 = i1 ^ 1;
                }
                bytevector.putByte(j1);
                r.putShort(8);
                r.putByte(200);
            }
            label.a(this, r, -1 + r.b, true);
        } else
        {
            r.putByte(i1);
            label.a(this, r, -1 + r.b, false);
        }
        if (P != null)
        {
            if (label2 != null)
            {
                visitLabel(label2);
            }
            if (i1 == 167)
            {
                e();
            }
        }
    }

    public void visitLabel(Label label)
    {
        K = K | label.a(this, r.b, r.a);
        if ((1 & label.a) == 0)
        {
            if (M == 0)
            {
                if (P != null)
                {
                    if (label.c == P.c)
                    {
                        Label label2 = P;
                        label2.a = label2.a | 0x10 & label.a;
                        label.h = P.h;
                        return;
                    }
                    a(0, label);
                }
                P = label;
                if (label.h == null)
                {
                    label.h = new Frame();
                    label.h.b = label;
                }
                if (O != null)
                {
                    if (label.c == O.c)
                    {
                        Label label1 = O;
                        label1.a = label1.a | 0x10 & label.a;
                        label.h = O.h;
                        P = O;
                        return;
                    }
                    O.i = label;
                }
                O = label;
                return;
            }
            if (M == 1)
            {
                if (P != null)
                {
                    P.g = R;
                    a(Q, label);
                }
                P = label;
                Q = 0;
                R = 0;
                if (O != null)
                {
                    O.i = label;
                }
                O = label;
                return;
            }
        }
    }

    public void visitLdcInsn(Object obj)
    {
        Item item = b.a(obj);
        int i1;
        if (P != null)
        {
            if (M == 0)
            {
                P.h.a(18, 0, b, item);
            } else
            {
                int j1;
                if (item.b == 5 || item.b == 6)
                {
                    j1 = 2 + Q;
                } else
                {
                    j1 = 1 + Q;
                }
                if (j1 > R)
                {
                    R = j1;
                }
                Q = j1;
            }
        }
        i1 = item.a;
        if (item.b == 5 || item.b == 6)
        {
            r.b(20, i1);
            return;
        }
        if (i1 >= 256)
        {
            r.b(19, i1);
            return;
        } else
        {
            r.a(18, i1);
            return;
        }
    }

    public void visitLineNumber(int i1, Label label)
    {
        if (I == null)
        {
            I = new ByteVector();
        }
        H = 1 + H;
        I.putShort(label.c);
        I.putShort(i1);
    }

    public void visitLocalVariable(String s1, String s2, String s3, Label label, Label label1, int i1)
    {
        byte byte0 = 2;
        if (s3 != null)
        {
            if (G == null)
            {
                G = new ByteVector();
            }
            F = 1 + F;
            G.putShort(label.c).putShort(label1.c - label.c).putShort(b.newUTF8(s1)).putShort(b.newUTF8(s3)).putShort(i1);
        }
        if (E == null)
        {
            E = new ByteVector();
        }
        D = 1 + D;
        E.putShort(label.c).putShort(label1.c - label.c).putShort(b.newUTF8(s1)).putShort(b.newUTF8(s2)).putShort(i1);
        if (M != byte0)
        {
            char c1 = s2.charAt(0);
            int j1;
            if (c1 != 'J' && c1 != 'D')
            {
                byte0 = 1;
            }
            j1 = byte0 + i1;
            if (j1 > t)
            {
                t = j1;
            }
        }
    }

    public void visitLookupSwitchInsn(Label label, int ai[], Label alabel[])
    {
        int i1 = 0;
        int j1 = r.b;
        r.putByte(171);
        r.putByteArray(null, 0, (4 - r.b % 4) % 4);
        label.a(this, r, j1, true);
        r.putInt(alabel.length);
        for (; i1 < alabel.length; i1++)
        {
            r.putInt(ai[i1]);
            alabel[i1].a(this, r, j1, true);
        }

        a(label, alabel);
    }

    public void visitMaxs(int i1, int j1)
    {
        if (M == 0)
        {
            String s1;
            for (Handler handler1 = B; handler1 != null; handler1 = handler1.f)
            {
                Label label16 = handler1.a.a();
                Label label17 = handler1.c.a();
                Label label18 = handler1.b.a();
                int l4;
                if (handler1.d == null)
                {
                    s1 = "java/lang/Throwable";
                } else
                {
                    s1 = handler1.d;
                }
                l4 = 0x1700000 | b.c(s1);
                label17.a = 0x10 | label17.a;
                for (Label label19 = label16; label19 != label18; label19 = label19.i)
                {
                    Edge edge4 = new Edge();
                    edge4.a = l4;
                    edge4.b = label17;
                    edge4.c = label19.j;
                    label19.j = edge4;
                }

            }

            Frame frame = N.h;
            Type atype[] = Type.getArgumentTypes(f);
            frame.a(b, c, atype, t);
            b(frame);
            Label label11 = N;
            int l2 = 0;
            while (label11 != null) 
            {
                Label label14 = label11.k;
                label11.k = null;
                Frame frame2 = label11.h;
                if ((0x10 & label11.a) != 0)
                {
                    label11.a = 0x20 | label11.a;
                }
                label11.a = 0x40 | label11.a;
                int k4 = frame2.d.length + label11.g;
                Edge edge3;
                if (k4 <= l2)
                {
                    k4 = l2;
                }
                edge3 = label11.j;
                while (edge3 != null) 
                {
                    Label label15 = edge3.b.a();
                    Handler handler;
                    Label label;
                    int k1;
                    Label label1;
                    int l1;
                    int i2;
                    Edge edge;
                    Edge edge1;
                    Label label2;
                    Label label3;
                    int j2;
                    Label label4;
                    int k2;
                    Label label5;
                    Label label6;
                    Label label7;
                    Label label8;
                    Label label9;
                    Label label10;
                    Edge edge2;
                    Label label12;
                    int i3;
                    Frame frame1;
                    Label label13;
                    int j3;
                    int k3;
                    int l3;
                    int i4;
                    int ai[];
                    int j4;
                    if (frame2.a(b, label15.h, edge3.a) && label15.k == null)
                    {
                        label15.k = label14;
                    } else
                    {
                        label15 = label14;
                    }
                    edge3 = edge3.c;
                    label14 = label15;
                }
                label11 = label14;
                l2 = k4;
            }
            label12 = N;
            i3 = l2;
            for (; label12 != null; label12 = label12.i)
            {
                frame1 = label12.h;
                if ((0x20 & label12.a) != 0)
                {
                    b(frame1);
                }
                if ((0x40 & label12.a) != 0)
                {
                    continue;
                }
                label13 = label12.i;
                j3 = label12.c;
                if (label13 == null)
                {
                    k3 = r.b;
                } else
                {
                    k3 = label13.c;
                }
                l3 = k3 - 1;
                if (l3 < j3)
                {
                    continue;
                }
                i3 = Math.max(i3, 1);
                for (i4 = j3; i4 < l3; i4++)
                {
                    r.a[i4] = 0;
                }

                r.a[l3] = -65;
                a(j3, 0, 1);
                ai = z;
                j4 = y;
                y = j4 + 1;
                ai[j4] = 0x1700000 | b.c("java/lang/Throwable");
                b();
            }

            s = i3;
            return;
        }
        if (M == 1)
        {
            for (handler = B; handler != null; handler = handler.f)
            {
                label8 = handler.a;
                label9 = handler.c;
                label10 = handler.b;
                while (label8 != label10) 
                {
                    edge2 = new Edge();
                    edge2.a = 0x7fffffff;
                    edge2.b = label9;
                    if ((0x80 & label8.a) == 0)
                    {
                        edge2.c = label8.j;
                        label8.j = edge2;
                    } else
                    {
                        edge2.c = label8.j.c.c;
                        label8.j.c.c = edge2;
                    }
                    label8 = label8.i;
                }
            }

            if (L > 0)
            {
                N.b(null, 1L, L);
                label4 = N;
                k2 = 0;
                for (; label4 != null; label4 = label4.i)
                {
                    if ((0x80 & label4.a) == 0)
                    {
                        continue;
                    }
                    label7 = label4.j.c.b;
                    if ((0x400 & label7.a) == 0)
                    {
                        k2++;
                        label7.b(null, (long)k2 / 32L << 32 | 1L << k2 % 32, L);
                    }
                }

                for (label5 = N; label5 != null; label5 = label5.i)
                {
                    if ((0x80 & label5.a) == 0)
                    {
                        continue;
                    }
                    for (label6 = N; label6 != null; label6 = label6.i)
                    {
                        label6.a = 0xfffff7ff & label6.a;
                    }

                    label5.j.c.b.b(label5, 0L, L);
                }

            }
            label = N;
            k1 = 0;
            while (label != null) 
            {
                label1 = label.k;
                l1 = label.f;
                i2 = l1 + label.g;
                if (i2 <= k1)
                {
                    i2 = k1;
                }
                edge = label.j;
                if ((0x80 & label.a) != 0)
                {
                    edge1 = edge.c;
                } else
                {
                    edge1 = edge;
                }
                while (edge1 != null) 
                {
                    label2 = edge1.b;
                    if ((8 & label2.a) == 0)
                    {
                        if (edge1.a == 0x7fffffff)
                        {
                            j2 = 1;
                        } else
                        {
                            j2 = l1 + edge1.a;
                        }
                        label2.f = j2;
                        label2.a = 8 | label2.a;
                        label2.k = label1;
                        label3 = label2;
                    } else
                    {
                        label3 = label1;
                    }
                    edge1 = edge1.c;
                    label1 = label3;
                }
                label = label1;
                k1 = i2;
            }
            s = k1;
            return;
        } else
        {
            s = i1;
            t = j1;
            return;
        }
    }

    public void visitMethodInsn(int i1, String s1, String s2, String s3)
    {
        boolean flag;
        Item item;
        int j1;
        if (i1 == 185)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (i1 == 186)
        {
            item = b.a(s2, s3);
        } else
        {
            item = b.a(s1, s2, s3, flag);
        }
        j1 = item.c;
        if (P != null)
        {
            if (M == 0)
            {
                P.h.a(i1, 0, b, item);
            } else
            {
                int k1;
                int l1;
                if (j1 == 0)
                {
                    k1 = Type.getArgumentsAndReturnSizes(s3);
                    item.c = k1;
                } else
                {
                    k1 = j1;
                }
                if (i1 == 184 || i1 == 186)
                {
                    l1 = 1 + ((Q - (k1 >> 2)) + (k1 & 3));
                } else
                {
                    l1 = (Q - (k1 >> 2)) + (k1 & 3);
                }
                if (l1 > R)
                {
                    R = l1;
                }
                Q = l1;
                j1 = k1;
            }
        }
        if (flag)
        {
            if (j1 == 0)
            {
                j1 = Type.getArgumentsAndReturnSizes(s3);
                item.c = j1;
            }
            r.b(185, item.a).a(j1 >> 2, 0);
        } else
        {
            r.b(i1, item.a);
            if (i1 == 186)
            {
                r.putShort(0);
                return;
            }
        }
    }

    public void visitMultiANewArrayInsn(String s1, int i1)
    {
        Item item = b.a(s1);
        if (P != null)
        {
            if (M == 0)
            {
                P.h.a(197, i1, b, item);
            } else
            {
                Q = Q + (1 - i1);
            }
        }
        r.b(197, item.a).putByte(i1);
    }

    public AnnotationVisitor visitParameterAnnotation(int i1, String s1, boolean flag)
    {
        ByteVector bytevector = new ByteVector();
        if ("Ljava/lang/Synthetic;".equals(s1))
        {
            S = Math.max(S, i1 + 1);
            return new AnnotationWriter(b, false, bytevector, null, 0);
        }
        bytevector.putShort(b.newUTF8(s1)).putShort(0);
        AnnotationWriter annotationwriter = new AnnotationWriter(b, true, bytevector, bytevector, 2);
        if (flag)
        {
            if (o == null)
            {
                o = new AnnotationWriter[Type.getArgumentTypes(f).length];
            }
            annotationwriter.g = o[i1];
            o[i1] = annotationwriter;
            return annotationwriter;
        }
        if (p == null)
        {
            p = new AnnotationWriter[Type.getArgumentTypes(f).length];
        }
        annotationwriter.g = p[i1];
        p[i1] = annotationwriter;
        return annotationwriter;
    }

    public void visitTableSwitchInsn(int i1, int j1, Label label, Label alabel[])
    {
        int k1 = 0;
        int l1 = r.b;
        r.putByte(170);
        r.putByteArray(null, 0, (4 - r.b % 4) % 4);
        label.a(this, r, l1, true);
        r.putInt(i1).putInt(j1);
        for (; k1 < alabel.length; k1++)
        {
            alabel[k1].a(this, r, l1, true);
        }

        a(label, alabel);
    }

    public void visitTryCatchBlock(Label label, Label label1, Label label2, String s1)
    {
        A = 1 + A;
        Handler handler = new Handler();
        handler.a = label;
        handler.b = label1;
        handler.c = label2;
        handler.d = s1;
        int i1;
        if (s1 != null)
        {
            i1 = b.newClass(s1);
        } else
        {
            i1 = 0;
        }
        handler.e = i1;
        if (C == null)
        {
            B = handler;
        } else
        {
            C.f = handler;
        }
        C = handler;
    }

    public void visitTypeInsn(int i1, String s1)
    {
        Item item = b.a(s1);
        if (P == null) goto _L2; else goto _L1
_L1:
        if (M != 0) goto _L4; else goto _L3
_L3:
        P.h.a(i1, r.b, b, item);
_L2:
        r.b(i1, item.a);
        return;
_L4:
        if (i1 == 187)
        {
            int j1 = 1 + Q;
            if (j1 > R)
            {
                R = j1;
            }
            Q = j1;
        }
        if (true) goto _L2; else goto _L5
_L5:
    }

    public void visitVarInsn(int i1, int j1)
    {
        if (P != null)
        {
            if (M == 0)
            {
                P.h.a(i1, j1, null, null);
            } else
            if (i1 == 169)
            {
                Label label = P;
                label.a = 0x100 | label.a;
                P.f = Q;
                e();
            } else
            {
                int i2 = Q + Frame.a[i1];
                if (i2 > R)
                {
                    R = i2;
                }
                Q = i2;
            }
        }
        if (M != 2)
        {
            int l1;
            if (i1 == 22 || i1 == 24 || i1 == 55 || i1 == 57)
            {
                l1 = j1 + 2;
            } else
            {
                l1 = j1 + 1;
            }
            if (l1 > t)
            {
                t = l1;
            }
        }
        if (j1 < 4 && i1 != 169)
        {
            int k1;
            if (i1 < 54)
            {
                k1 = j1 + (26 + (i1 - 21 << 2));
            } else
            {
                k1 = j1 + (59 + (i1 - 54 << 2));
            }
            r.putByte(k1);
        } else
        if (j1 >= 256)
        {
            r.putByte(196).b(i1, j1);
        } else
        {
            r.a(i1, j1);
        }
        if (i1 >= 54 && M == 0 && A > 0)
        {
            visitLabel(new Label());
        }
    }
}

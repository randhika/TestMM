// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.org.objectweb.asm;

import java.io.IOException;
import java.io.InputStream;

// Referenced classes of package org.codehaus.jackson.org.objectweb.asm:
//            AnnotationVisitor, Type, Opcodes, Attribute, 
//            MethodVisitor, Item, ClassWriter, ByteVector, 
//            ClassVisitor, FieldVisitor, MethodWriter, Label

public class ClassReader
{

    public static final int EXPAND_FRAMES = 8;
    public static final int SKIP_CODE = 1;
    public static final int SKIP_DEBUG = 2;
    public static final int SKIP_FRAMES = 4;
    private final int a[];
    public final byte b[];
    private final String c[];
    private final int d;
    public final int header;

    public ClassReader(InputStream inputstream)
        throws IOException
    {
        this(a(inputstream));
    }

    public ClassReader(String s)
        throws IOException
    {
        this(ClassLoader.getSystemResourceAsStream(s.replace('.', '/') + ".class"));
    }

    public ClassReader(byte abyte0[])
    {
        this(abyte0, 0, abyte0.length);
    }

    public ClassReader(byte abyte0[], int i, int j)
    {
        int k;
        int l;
        int j1;
        int k1;
        b = abyte0;
        a = new int[readUnsignedShort(i + 8)];
        k = a.length;
        c = new String[k];
        l = 0;
        int i1 = i + 10;
        j1 = 1;
        k1 = i1;
_L6:
        if (j1 >= k)
        {
            break MISSING_BLOCK_LABEL_202;
        }
        a[j1] = k1 + 1;
        abyte0[k1];
        JVM INSTR tableswitch 1 12: default 140
    //                   1 176
    //                   2 140
    //                   3 160
    //                   4 160
    //                   5 166
    //                   6 166
    //                   7 140
    //                   8 140
    //                   9 160
    //                   10 160
    //                   11 160
    //                   12 160;
           goto _L1 _L2 _L1 _L3 _L3 _L4 _L4 _L1 _L1 _L3 _L3 _L3 _L3
_L2:
        break MISSING_BLOCK_LABEL_176;
_L3:
        break; /* Loop/switch isn't completed */
_L1:
        int l1 = 3;
_L7:
        int i2 = l1 + k1;
        j1++;
        k1 = i2;
        if (true) goto _L6; else goto _L5
_L5:
        l1 = 5;
          goto _L7
_L4:
        l1 = 9;
        j1++;
          goto _L7
        l1 = 3 + readUnsignedShort(k1 + 1);
        if (l1 > l)
        {
            l = l1;
        }
          goto _L7
        d = l;
        header = k1;
        return;
    }

    private int a(int i, char ac[], String s, AnnotationVisitor annotationvisitor)
    {
        int j = 0;
        if (annotationvisitor == null)
        {
            switch (0xff & b[i])
            {
            default:
                return i + 3;

            case 101: // 'e'
                return i + 5;

            case 64: // '@'
                return a(i + 3, ac, true, null);

            case 91: // '['
                return a(i + 1, ac, false, null);
            }
        }
        byte abyte0[] = b;
        int k = i + 1;
        int l;
        int i1;
        switch (0xff & abyte0[i])
        {
        default:
            return k;

        case 68: // 'D'
        case 70: // 'F'
        case 73: // 'I'
        case 74: // 'J'
            annotationvisitor.visit(s, readConst(readUnsignedShort(k), ac));
            return k + 2;

        case 66: // 'B'
            annotationvisitor.visit(s, new Byte((byte)readInt(a[readUnsignedShort(k)])));
            return k + 2;

        case 90: // 'Z'
            Boolean boolean1;
            if (readInt(a[readUnsignedShort(k)]) == 0)
            {
                boolean1 = Boolean.FALSE;
            } else
            {
                boolean1 = Boolean.TRUE;
            }
            annotationvisitor.visit(s, boolean1);
            return k + 2;

        case 83: // 'S'
            annotationvisitor.visit(s, new Short((short)readInt(a[readUnsignedShort(k)])));
            return k + 2;

        case 67: // 'C'
            annotationvisitor.visit(s, new Character((char)readInt(a[readUnsignedShort(k)])));
            return k + 2;

        case 115: // 's'
            annotationvisitor.visit(s, readUTF8(k, ac));
            return k + 2;

        case 101: // 'e'
            annotationvisitor.visitEnum(s, readUTF8(k, ac), readUTF8(k + 2, ac));
            return k + 4;

        case 99: // 'c'
            annotationvisitor.visit(s, Type.getType(readUTF8(k, ac)));
            return k + 2;

        case 64: // '@'
            return a(k + 2, ac, true, annotationvisitor.visitAnnotation(s, readUTF8(k, ac)));

        case 91: // '['
            l = readUnsignedShort(k);
            i1 = k + 2;
            break;
        }
        if (l == 0)
        {
            return a(i1 - 2, ac, false, annotationvisitor.visitArray(s));
        }
        byte abyte1[] = b;
        int j1 = i1 + 1;
        double ad[];
        switch (0xff & abyte1[i1])
        {
        default:
            return a(j1 - 3, ac, false, annotationvisitor.visitArray(s));

        case 66: // 'B'
            byte abyte2[] = new byte[l];
            for (; j < l; j++)
            {
                abyte2[j] = (byte)readInt(a[readUnsignedShort(j1)]);
                j1 += 3;
            }

            annotationvisitor.visit(s, abyte2);
            return j1 - 1;

        case 90: // 'Z'
            boolean aflag[] = new boolean[l];
            int k1 = 0;
            int l1 = j1;
            while (k1 < l) 
            {
                boolean flag;
                if (readInt(a[readUnsignedShort(l1)]) != 0)
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                aflag[k1] = flag;
                l1 += 3;
                k1++;
            }
            annotationvisitor.visit(s, aflag);
            return l1 - 1;

        case 83: // 'S'
            short aword0[] = new short[l];
            for (; j < l; j++)
            {
                aword0[j] = (short)readInt(a[readUnsignedShort(j1)]);
                j1 += 3;
            }

            annotationvisitor.visit(s, aword0);
            return j1 - 1;

        case 67: // 'C'
            char ac1[] = new char[l];
            for (; j < l; j++)
            {
                ac1[j] = (char)readInt(a[readUnsignedShort(j1)]);
                j1 += 3;
            }

            annotationvisitor.visit(s, ac1);
            return j1 - 1;

        case 73: // 'I'
            int ai[] = new int[l];
            for (; j < l; j++)
            {
                ai[j] = readInt(a[readUnsignedShort(j1)]);
                j1 += 3;
            }

            annotationvisitor.visit(s, ai);
            return j1 - 1;

        case 74: // 'J'
            long al[] = new long[l];
            for (; j < l; j++)
            {
                al[j] = readLong(a[readUnsignedShort(j1)]);
                j1 += 3;
            }

            annotationvisitor.visit(s, al);
            return j1 - 1;

        case 70: // 'F'
            float af[] = new float[l];
            for (; j < l; j++)
            {
                af[j] = Float.intBitsToFloat(readInt(a[readUnsignedShort(j1)]));
                j1 += 3;
            }

            annotationvisitor.visit(s, af);
            return j1 - 1;

        case 68: // 'D'
            ad = new double[l];
            break;
        }
        for (; j < l; j++)
        {
            ad[j] = Double.longBitsToDouble(readLong(a[readUnsignedShort(j1)]));
            j1 += 3;
        }

        annotationvisitor.visit(s, ad);
        return j1 - 1;
    }

    private int a(int i, char ac[], boolean flag, AnnotationVisitor annotationvisitor)
    {
        int j;
        int k;
        j = readUnsignedShort(i);
        k = i + 2;
        if (!flag) goto _L2; else goto _L1
_L1:
        int l;
        l = k;
        for (int k1 = j; k1 > 0;)
        {
            int l1 = a(l + 2, ac, readUTF8(l, ac), annotationvisitor);
            k1--;
            l = l1;
        }

          goto _L3
_L5:
        int i1;
        while (i1 > 0) 
        {
            int j1 = a(l, ac, ((String) (null)), annotationvisitor);
            i1--;
            l = j1;
        }
_L3:
        if (annotationvisitor != null)
        {
            annotationvisitor.visitEnd();
        }
        return l;
_L2:
        l = k;
        i1 = j;
        if (true) goto _L5; else goto _L4
_L4:
    }

    private int a(Object aobj[], int i, int j, char ac[], Label alabel[])
    {
        byte abyte0[] = b;
        int k = j + 1;
        switch (0xff & abyte0[j])
        {
        default:
            aobj[i] = readLabel(readUnsignedShort(k), alabel);
            return k + 2;

        case 0: // '\0'
            aobj[i] = Opcodes.TOP;
            return k;

        case 1: // '\001'
            aobj[i] = Opcodes.INTEGER;
            return k;

        case 2: // '\002'
            aobj[i] = Opcodes.FLOAT;
            return k;

        case 3: // '\003'
            aobj[i] = Opcodes.DOUBLE;
            return k;

        case 4: // '\004'
            aobj[i] = Opcodes.LONG;
            return k;

        case 5: // '\005'
            aobj[i] = Opcodes.NULL;
            return k;

        case 6: // '\006'
            aobj[i] = Opcodes.UNINITIALIZED_THIS;
            return k;

        case 7: // '\007'
            aobj[i] = readClass(k, ac);
            break;
        }
        return k + 2;
    }

    private String a(int i, int j, char ac[])
    {
        int k;
        byte abyte0[];
        char c1;
        int l;
        int i1;
        k = i + j;
        abyte0 = b;
        c1 = '\0';
        l = 0;
        i1 = 0;
_L6:
        int j1;
        byte byte0;
        if (i >= k)
        {
            break MISSING_BLOCK_LABEL_218;
        }
        j1 = i + 1;
        byte0 = abyte0[i];
        l;
        JVM INSTR tableswitch 0 2: default 64
    //                   0 78
    //                   1 162
    //                   2 194;
           goto _L1 _L2 _L3 _L4
_L4:
        break MISSING_BLOCK_LABEL_194;
_L2:
        break; /* Loop/switch isn't completed */
_L1:
        int k1 = i1;
_L7:
        i1 = k1;
        i = j1;
        if (true) goto _L6; else goto _L5
_L5:
        int i2 = byte0 & 0xff;
        if (i2 < 128)
        {
            k1 = i1 + 1;
            ac[i1] = (char)i2;
        } else
        if (i2 < 224 && i2 > 191)
        {
            c1 = (char)(i2 & 0x1f);
            l = 1;
            k1 = i1;
        } else
        {
            c1 = (char)(i2 & 0xf);
            l = 2;
            k1 = i1;
        }
          goto _L7
_L3:
        int l1 = i1 + 1;
        ac[i1] = (char)(c1 << 6 | byte0 & 0x3f);
        k1 = l1;
        l = 0;
          goto _L7
        c1 = (char)(c1 << 6 | byte0 & 0x3f);
        l = 1;
        k1 = i1;
          goto _L7
        return new String(ac, 0, i1);
    }

    private Attribute a(Attribute aattribute[], String s, int i, int j, char ac[], int k, Label alabel[])
    {
        for (int l = 0; l < aattribute.length; l++)
        {
            if (aattribute[l].type.equals(s))
            {
                return aattribute[l].read(this, i, j, ac, k, alabel);
            }
        }

        return (new Attribute(s)).read(this, i, j, null, -1, null);
    }

    private void a(int i, String s, char ac[], boolean flag, MethodVisitor methodvisitor)
    {
        int j;
        int k;
        int l;
        int i1;
        byte abyte0[] = b;
        j = i + 1;
        k = 0xff & abyte0[i];
        l = Type.getArgumentTypes(s).length - k;
        for (i1 = 0; i1 < l; i1++)
        {
            AnnotationVisitor annotationvisitor1 = methodvisitor.visitParameterAnnotation(i1, "Ljava/lang/Synthetic;", false);
            if (annotationvisitor1 != null)
            {
                annotationvisitor1.visitEnd();
            }
        }

          goto _L1
_L2:
        int j1;
        j1++;
_L3:
        if (j1 < k + l)
        {
            int k1 = readUnsignedShort(j);
            j += 2;
            while (k1 > 0) 
            {
                AnnotationVisitor annotationvisitor = methodvisitor.visitParameterAnnotation(j1, readUTF8(j, ac), flag);
                j = a(j + 2, ac, true, annotationvisitor);
                k1--;
            }
        } else
        {
            return;
        }
        if (true) goto _L2; else goto _L1
_L1:
        j1 = i1;
          goto _L3
    }

    private static byte[] a(InputStream inputstream)
        throws IOException
    {
        byte abyte0[];
        int i;
        if (inputstream == null)
        {
            throw new IOException("Class not found");
        }
        abyte0 = new byte[inputstream.available()];
        i = 0;
_L6:
        int j = inputstream.read(abyte0, i, abyte0.length - i);
        if (j != -1) goto _L2; else goto _L1
_L1:
        if (i < abyte0.length)
        {
            byte abyte2[] = new byte[i];
            System.arraycopy(abyte0, 0, abyte2, 0, i);
            abyte0 = abyte2;
        }
_L4:
        return abyte0;
_L2:
        int k;
        k = j + i;
        if (k != abyte0.length)
        {
            break; /* Loop/switch isn't completed */
        }
        int l = inputstream.read();
        if (l >= 0)
        {
            byte abyte1[] = new byte[1000 + abyte0.length];
            System.arraycopy(abyte0, 0, abyte1, 0, k);
            i = k + 1;
            abyte1[k] = (byte)l;
            abyte0 = abyte1;
            continue; /* Loop/switch isn't completed */
        }
        if (true) goto _L4; else goto _L3
_L3:
        i = k;
        if (true) goto _L6; else goto _L5
_L5:
    }

    void a(ClassWriter classwriter)
    {
        char ac[];
        int i;
        Item aitem[];
        int j;
        ac = new char[d];
        i = a.length;
        aitem = new Item[i];
        j = 1;
_L10:
        int l;
        byte byte0;
        Item item;
        if (j >= i)
        {
            break MISSING_BLOCK_LABEL_417;
        }
        l = a[j];
        byte0 = b[l - 1];
        item = new Item(j);
        byte0;
        JVM INSTR tableswitch 1 12: default 124
    //                   1 346
    //                   2 124
    //                   3 233
    //                   4 251
    //                   5 303
    //                   6 323
    //                   7 124
    //                   8 124
    //                   9 181
    //                   10 181
    //                   11 181
    //                   12 272;
           goto _L1 _L2 _L1 _L3 _L4 _L5 _L6 _L1 _L1 _L7 _L7 _L7 _L8
_L2:
        break MISSING_BLOCK_LABEL_346;
_L7:
        break; /* Loop/switch isn't completed */
_L1:
        int i1;
        item.a(byte0, readUTF8(l, ac), null, null);
        i1 = j;
_L11:
        int j1 = item.j % aitem.length;
        item.k = aitem[j1];
        aitem[j1] = item;
        j = i1 + 1;
        if (true) goto _L10; else goto _L9
_L9:
        int l1 = a[readUnsignedShort(l + 2)];
        item.a(byte0, readClass(l, ac), readUTF8(l1, ac), readUTF8(l1 + 2, ac));
        i1 = j;
          goto _L11
_L3:
        item.a(readInt(l));
        i1 = j;
          goto _L11
_L4:
        item.a(Float.intBitsToFloat(readInt(l)));
        i1 = j;
          goto _L11
_L8:
        item.a(byte0, readUTF8(l, ac), readUTF8(l + 2, ac), null);
        i1 = j;
          goto _L11
_L5:
        item.a(readLong(l));
        i1 = j + 1;
          goto _L11
_L6:
        item.a(Double.longBitsToDouble(readLong(l)));
        i1 = j + 1;
          goto _L11
        String s = c[j];
        if (s == null)
        {
            int k1 = a[j];
            String as[] = c;
            s = a(k1 + 2, readUnsignedShort(k1), ac);
            as[j] = s;
        }
        item.a(byte0, s, null, null);
        i1 = j;
          goto _L11
        int k = -1 + a[1];
        classwriter.d.putByteArray(b, k, header - k);
        classwriter.e = aitem;
        classwriter.f = (int)(0.75D * (double)i);
        classwriter.c = i;
        return;
    }

    public void accept(ClassVisitor classvisitor, int i)
    {
        accept(classvisitor, new Attribute[0], i);
    }

    public void accept(ClassVisitor classvisitor, Attribute aattribute[], int i)
    {
        byte abyte0[] = b;
        char ac[] = new char[d];
        boolean flag = false;
        boolean flag1 = false;
        Attribute attribute = null;
        int j = header;
        int k = readUnsignedShort(j);
        String s = readClass(j + 2, ac);
        int l = a[readUnsignedShort(j + 4)];
        String s1;
        String as[];
        int i1;
        int j1;
        int k1;
        int l1;
        if (l == 0)
        {
            s1 = null;
        } else
        {
            s1 = readUTF8(l, ac);
        }
        as = new String[readUnsignedShort(j + 6)];
        i1 = 0;
        j1 = j + 8;
        k1 = 0;
        int l57;
        for (l1 = j1; k1 < as.length; l1 = l57)
        {
            as[k1] = readClass(l1, ac);
            l57 = l1 + 2;
            k1++;
        }

        boolean flag2;
        boolean flag3;
        boolean flag4;
        int i2;
        int j2;
        if ((i & 1) != 0)
        {
            flag2 = true;
        } else
        {
            flag2 = false;
        }
        if ((i & 2) != 0)
        {
            flag3 = true;
        } else
        {
            flag3 = false;
        }
        if ((i & 8) != 0)
        {
            flag4 = true;
        } else
        {
            flag4 = false;
        }
        i2 = readUnsignedShort(l1);
        j2 = l1 + 2;
        for (int k2 = i2; k2 > 0; k2--)
        {
            int k57 = readUnsignedShort(j2 + 6);
            j2 += 8;
            for (; k57 > 0; k57--)
            {
                j2 += 6 + readInt(j2 + 2);
            }

        }

        int l2 = readUnsignedShort(j2);
        int i3 = j2 + 2;
        for (int j3 = l2; j3 > 0; j3--)
        {
            int j57 = readUnsignedShort(i3 + 6);
            i3 += 8;
            for (; j57 > 0; j57--)
            {
                i3 += 6 + readInt(i3 + 2);
            }

        }

        String s2 = null;
        String s3 = null;
        String s4 = null;
        String s5 = null;
        String s6 = null;
        String s7 = null;
        int k3 = readUnsignedShort(i3);
        int l3 = i3 + 2;
        int i4 = k3;
        int j4 = l3;
        while (i4 > 0) 
        {
            String s27 = readUTF8(j4, ac);
            String s28;
            String s29;
            String s30;
            String s31;
            String s32;
            int l54;
            Attribute attribute15;
            int i55;
            int j55;
            int k55;
            int l55;
            if ("SourceFile".equals(s27))
            {
                String s34 = readUTF8(j4 + 6, ac);
                s29 = s6;
                s30 = s5;
                s31 = s4;
                s32 = s34;
                l54 = i1;
                attribute15 = attribute;
                s28 = s7;
                j55 = ((flag) ? 1 : 0);
                i55 = ((flag1) ? 1 : 0);
            } else
            if ("InnerClasses".equals(s27))
            {
                int i57 = j4 + 6;
                s29 = s6;
                s30 = s5;
                s31 = s4;
                s32 = s3;
                l54 = i57;
                attribute15 = attribute;
                s28 = s7;
                j55 = ((flag) ? 1 : 0);
                i55 = ((flag1) ? 1 : 0);
            } else
            if ("EnclosingMethod".equals(s27))
            {
                s30 = readClass(j4 + 6, ac);
                int l56 = readUnsignedShort(j4 + 8);
                int k4;
                int l4;
                int i5;
                int j5;
                int k5;
                int l5;
                int i6;
                int j6;
                int k6;
                String s8;
                String s9;
                String s10;
                boolean flag5;
                boolean flag6;
                int l6;
                int i7;
                int j7;
                Attribute attribute1;
                int k7;
                int l7;
                int i8;
                int j8;
                int k8;
                String as1[];
                int l8;
                int i9;
                String as2[];
                MethodVisitor methodvisitor;
                int j9;
                int k9;
                int l9;
                int i10;
                int j10;
                Label alabel[];
                int k10;
                int l10;
                int i11;
                int j11;
                int k11;
                int l11;
                int i12;
                boolean flag7;
                boolean flag8;
                Attribute attribute2;
                int j12;
                int k12;
                int l12;
                int i13;
                Object aobj[];
                byte byte0;
                Object aobj1[];
                int j13;
                Object aobj2[];
                int k13;
                int l13;
                int i14;
                int j14;
                byte byte1;
                int k14;
                int l14;
                int i15;
                Label label;
                Attribute attribute3;
                int ai[];
                int j15;
                int k15;
                int l15;
                int i16;
                int j16;
                int k16;
                String s11;
                int l16;
                int i17;
                int j17;
                int k17;
                int l17;
                int i18;
                int j18;
                Label label1;
                int k18;
                int l18;
                int i19;
                int j19;
                String s12;
                String s13;
                String s14;
                int k19;
                int l19;
                int i20;
                int ai1[];
                Label alabel1[];
                int j20;
                int k20;
                int l20;
                int i21;
                int j21;
                Label alabel2[];
                int k21;
                int l21;
                int i22;
                int j22;
                int k22;
                int l22;
                int i23;
                int j23;
                int k23;
                int l23;
                int i24;
                int j24;
                int k24;
                int l24;
                int i25;
                int j25;
                int k25;
                int l25;
                byte byte2;
                int i26;
                int j26;
                int k26;
                int l26;
                int i27;
                int j27;
                int k27;
                int l27;
                int i28;
                int j28;
                int k28;
                int l28;
                Object aobj3[];
                int i29;
                int j29;
                int k29;
                int l29;
                int i30;
                int j30;
                int k30;
                int l30;
                int i31;
                int j31;
                int k31;
                int l31;
                int i32;
                int j32;
                String s15;
                int k32;
                Attribute attribute4;
                boolean flag9;
                int l32;
                int i33;
                int j33;
                int k33;
                int l33;
                int i34;
                int j34;
                Attribute attribute5;
                int k34;
                int l34;
                int i35;
                int j35;
                int k35;
                int l35;
                Label label2;
                int i36;
                int j36;
                int k36;
                int l36;
                int i37;
                int j37;
                Label label3;
                Label label4;
                Label label5;
                Label label6;
                Label label7;
                int k37;
                int l37;
                int i38;
                int j38;
                int k38;
                int l38;
                int i39;
                int j39;
                int k39;
                int l39;
                int i40;
                int j40;
                int k40;
                Attribute attribute6;
                int l40;
                int i41;
                int j41;
                int k41;
                int l41;
                String s16;
                boolean flag10;
                int i42;
                AnnotationVisitor annotationvisitor;
                MethodWriter methodwriter;
                int j42;
                int k42;
                boolean flag11;
                boolean flag12;
                int l42;
                int i43;
                int j43;
                String s17;
                int k43;
                int l43;
                Attribute attribute7;
                int i44;
                int j44;
                int k44;
                int l44;
                int i45;
                int j45;
                Attribute attribute8;
                int k45;
                int l45;
                int i46;
                int j46;
                int k46;
                int l46;
                int i47;
                String s18;
                String s19;
                int j47;
                String s20;
                boolean flag13;
                boolean flag14;
                Attribute attribute9;
                int k47;
                int l47;
                int i48;
                Object obj;
                FieldVisitor fieldvisitor;
                int j48;
                Attribute attribute10;
                int k48;
                int l48;
                int i49;
                int j49;
                int k49;
                String s21;
                boolean flag15;
                int l49;
                String s22;
                Attribute attribute11;
                int i50;
                int j50;
                Attribute attribute12;
                int k50;
                int l50;
                int i51;
                int j51;
                int k51;
                int l51;
                int i52;
                int j52;
                int k52;
                int l52;
                String s23;
                String s24;
                String s25;
                int i53;
                Attribute attribute13;
                int j53;
                int k53;
                int l53;
                int i54;
                int j54;
                String s26;
                boolean flag16;
                int k54;
                Attribute attribute14;
                int i56;
                int j56;
                String s33;
                int k56;
                if (l56 != 0)
                {
                    s29 = readUTF8(a[l56], ac);
                    s28 = readUTF8(2 + a[l56], ac);
                } else
                {
                    s28 = s7;
                    s29 = s6;
                }
                s31 = s4;
                s32 = s3;
                l54 = i1;
                attribute15 = attribute;
                i55 = ((flag1) ? 1 : 0);
                j55 = ((flag) ? 1 : 0);
            } else
            if ("Signature".equals(s27))
            {
                s2 = readUTF8(j4 + 6, ac);
                s28 = s7;
                s29 = s6;
                s30 = s5;
                s31 = s4;
                s32 = s3;
                l54 = i1;
                attribute15 = attribute;
                i55 = ((flag1) ? 1 : 0);
                j55 = ((flag) ? 1 : 0);
            } else
            if ("RuntimeVisibleAnnotations".equals(s27))
            {
                k56 = j4 + 6;
                s29 = s6;
                s30 = s5;
                s31 = s4;
                s32 = s3;
                l54 = i1;
                attribute15 = attribute;
                j55 = k56;
                s28 = s7;
                i55 = ((flag1) ? 1 : 0);
            } else
            if ("Deprecated".equals(s27))
            {
                k |= 0x20000;
                s28 = s7;
                s29 = s6;
                s30 = s5;
                s31 = s4;
                s32 = s3;
                l54 = i1;
                attribute15 = attribute;
                i55 = ((flag1) ? 1 : 0);
                j55 = ((flag) ? 1 : 0);
            } else
            if ("Synthetic".equals(s27))
            {
                k |= 0x41000;
                s28 = s7;
                s29 = s6;
                s30 = s5;
                s31 = s4;
                s32 = s3;
                l54 = i1;
                attribute15 = attribute;
                i55 = ((flag1) ? 1 : 0);
                j55 = ((flag) ? 1 : 0);
            } else
            if ("SourceDebugExtension".equals(s27))
            {
                j56 = readInt(j4 + 2);
                s33 = a(j4 + 6, j56, new char[j56]);
                s29 = s6;
                s30 = s5;
                s31 = s33;
                s32 = s3;
                l54 = i1;
                attribute15 = attribute;
                s28 = s7;
                j55 = ((flag) ? 1 : 0);
                i55 = ((flag1) ? 1 : 0);
            } else
            if ("RuntimeInvisibleAnnotations".equals(s27))
            {
                i56 = j4 + 6;
                s29 = s6;
                s30 = s5;
                s31 = s4;
                s32 = s3;
                l54 = i1;
                attribute15 = attribute;
                j55 = ((flag) ? 1 : 0);
                s28 = s7;
                i55 = i56;
            } else
            {
                attribute14 = a(aattribute, s27, j4 + 6, readInt(j4 + 2), ac, -1, null);
                if (attribute14 != null)
                {
                    attribute14.a = attribute;
                    s29 = s6;
                    s30 = s5;
                    s31 = s4;
                    s32 = s3;
                    l54 = i1;
                    attribute15 = attribute14;
                    s28 = s7;
                    j55 = ((flag) ? 1 : 0);
                    i55 = ((flag1) ? 1 : 0);
                } else
                {
                    s28 = s7;
                    s29 = s6;
                    s30 = s5;
                    s31 = s4;
                    s32 = s3;
                    l54 = i1;
                    attribute15 = attribute;
                    i55 = ((flag1) ? 1 : 0);
                    j55 = ((flag) ? 1 : 0);
                }
            }
            k55 = j4 + (6 + readInt(j4 + 2));
            l55 = i4 - 1;
            s3 = s32;
            i4 = l55;
            i1 = l54;
            j4 = k55;
            attribute = attribute15;
            flag1 = i55;
            flag = j55;
            s4 = s31;
            s7 = s28;
            s6 = s29;
            s5 = s30;
        }
        classvisitor.visit(readInt(4), k, s, s2, s1, as);
        if (!flag3 && (s3 != null || s4 != null))
        {
            classvisitor.visitSource(s3, s4);
        }
        if (s5 != null)
        {
            classvisitor.visitOuterClass(s5, s6, s7);
        }
label0:
        for (k4 = 1; k4 >= 0; k4--)
        {
            if (k4 == 0)
            {
                j53 = ((flag1) ? 1 : 0);
            } else
            {
                j53 = ((flag) ? 1 : 0);
            }
            if (j53 == 0)
            {
                continue;
            }
            k53 = readUnsignedShort(j53);
            l53 = j53 + 2;
            i54 = k53;
            do
            {
                if (i54 <= 0)
                {
                    continue label0;
                }
                j54 = l53 + 2;
                s26 = readUTF8(l53, ac);
                if (k4 != 0)
                {
                    flag16 = true;
                } else
                {
                    flag16 = false;
                }
                k54 = a(j54, ac, true, classvisitor.visitAnnotation(s26, flag16));
                i54--;
                l53 = k54;
            } while (true);
        }

        for (; attribute != null; attribute = attribute13)
        {
            attribute13 = attribute.a;
            attribute.a = null;
            classvisitor.visitAttribute(attribute);
        }

        if (i1 != 0)
        {
            i52 = readUnsignedShort(i1);
            j52 = i1 + 2;
            k52 = i52;
            l52 = j52;
            while (k52 > 0) 
            {
                if (readUnsignedShort(l52) == 0)
                {
                    s23 = null;
                } else
                {
                    s23 = readClass(l52, ac);
                }
                if (readUnsignedShort(l52 + 2) == 0)
                {
                    s24 = null;
                } else
                {
                    s24 = readClass(l52 + 2, ac);
                }
                if (readUnsignedShort(l52 + 4) == 0)
                {
                    s25 = null;
                } else
                {
                    s25 = readUTF8(l52 + 4, ac);
                }
                classvisitor.visitInnerClass(s23, s24, s25, readUnsignedShort(l52 + 6));
                i53 = l52 + 8;
                k52--;
                l52 = i53;
            }
        }
        l4 = readUnsignedShort(l1);
        i5 = l1 + 2;
        j5 = l4;
        while (j5 > 0) 
        {
            i47 = readUnsignedShort(i5);
            s18 = readUTF8(i5 + 2, ac);
            s19 = readUTF8(i5 + 4, ac);
            j47 = 0;
            s20 = null;
            flag13 = false;
            flag14 = false;
            attribute9 = null;
            k47 = readUnsignedShort(i5 + 6);
            l47 = i5 + 8;
            i48 = k47;
            i5 = l47;
            while (i48 > 0) 
            {
                s22 = readUTF8(i5, ac);
                if ("ConstantValue".equals(s22))
                {
                    i50 = readUnsignedShort(i5 + 6);
                    j50 = i47;
                    attribute12 = attribute9;
                    k50 = ((flag14) ? 1 : 0);
                    l50 = ((flag13) ? 1 : 0);
                } else
                if ("Signature".equals(s22))
                {
                    s20 = readUTF8(i5 + 6, ac);
                    i50 = j47;
                    j50 = i47;
                    attribute12 = attribute9;
                    k50 = ((flag14) ? 1 : 0);
                    l50 = ((flag13) ? 1 : 0);
                } else
                if ("Deprecated".equals(s22))
                {
                    j50 = 0x20000 | i47;
                    attribute12 = attribute9;
                    k50 = ((flag14) ? 1 : 0);
                    l50 = ((flag13) ? 1 : 0);
                    i50 = j47;
                } else
                if ("Synthetic".equals(s22))
                {
                    j50 = 0x41000 | i47;
                    attribute12 = attribute9;
                    k50 = ((flag14) ? 1 : 0);
                    l50 = ((flag13) ? 1 : 0);
                    i50 = j47;
                } else
                if ("RuntimeVisibleAnnotations".equals(s22))
                {
                    l51 = i5 + 6;
                    j50 = i47;
                    attribute12 = attribute9;
                    k50 = ((flag14) ? 1 : 0);
                    l50 = l51;
                    i50 = j47;
                } else
                if ("RuntimeInvisibleAnnotations".equals(s22))
                {
                    k51 = i5 + 6;
                    j50 = i47;
                    attribute12 = attribute9;
                    k50 = k51;
                    l50 = ((flag13) ? 1 : 0);
                    i50 = j47;
                } else
                {
                    attribute11 = a(aattribute, s22, i5 + 6, readInt(i5 + 2), ac, -1, null);
                    if (attribute11 != null)
                    {
                        attribute11.a = attribute9;
                        j50 = i47;
                        attribute12 = attribute11;
                        k50 = ((flag14) ? 1 : 0);
                        l50 = ((flag13) ? 1 : 0);
                        i50 = j47;
                    } else
                    {
                        i50 = j47;
                        j50 = i47;
                        attribute12 = attribute9;
                        k50 = ((flag14) ? 1 : 0);
                        l50 = ((flag13) ? 1 : 0);
                    }
                }
                i51 = i5 + (6 + readInt(i5 + 2));
                j51 = i48 - 1;
                j47 = i50;
                i48 = j51;
                i47 = j50;
                i5 = i51;
                attribute9 = attribute12;
                flag14 = k50;
                flag13 = l50;
            }
            if (j47 == 0)
            {
                obj = null;
            } else
            {
                obj = readConst(j47, ac);
            }
            fieldvisitor = classvisitor.visitField(i47, s18, s19, s20, obj);
            if (fieldvisitor == null)
            {
                continue;
            }
label1:
            for (j48 = 1; j48 >= 0; j48--)
            {
                if (j48 == 0)
                {
                    k48 = ((flag14) ? 1 : 0);
                } else
                {
                    k48 = ((flag13) ? 1 : 0);
                }
                if (k48 == 0)
                {
                    continue;
                }
                l48 = readUnsignedShort(k48);
                i49 = k48 + 2;
                j49 = l48;
                do
                {
                    if (j49 <= 0)
                    {
                        continue label1;
                    }
                    k49 = i49 + 2;
                    s21 = readUTF8(i49, ac);
                    if (j48 != 0)
                    {
                        flag15 = true;
                    } else
                    {
                        flag15 = false;
                    }
                    l49 = a(k49, ac, true, fieldvisitor.visitAnnotation(s21, flag15));
                    j49--;
                    i49 = l49;
                } while (true);
            }

            for (; attribute9 != null; attribute9 = attribute10)
            {
                attribute10 = attribute9.a;
                attribute9.a = null;
                fieldvisitor.visitAttribute(attribute9);
            }

            fieldvisitor.visitEnd();
            j5--;
        }
        k5 = readUnsignedShort(i5);
        l5 = i5 + 2;
        i6 = k5;
        if (i6 <= 0) goto _L2; else goto _L1
_L1:
        j6 = l5 + 6;
        k6 = readUnsignedShort(l5);
        s8 = readUTF8(l5 + 2, ac);
        s9 = readUTF8(l5 + 4, ac);
        s10 = null;
        flag5 = false;
        flag6 = false;
        l6 = 0;
        i7 = 0;
        j7 = 0;
        attribute1 = null;
        k7 = 0;
        l7 = 0;
        i8 = readUnsignedShort(l5 + 6);
        j8 = l5 + 8;
        k8 = i8;
        l5 = j8;
_L4:
        if (k8 <= 0)
        {
            break; /* Loop/switch isn't completed */
        }
        s17 = readUTF8(l5, ac);
        k43 = readInt(l5 + 2);
        l43 = l5 + 6;
        if ("Code".equals(s17))
        {
            if (flag2)
            {
                break MISSING_BLOCK_LABEL_8391;
            }
            i44 = j7;
            j44 = i7;
            k44 = l6;
            l44 = l7;
            i45 = l43;
            j45 = k6;
            attribute8 = attribute1;
            k45 = ((flag6) ? 1 : 0);
            l45 = ((flag5) ? 1 : 0);
        } else
        if ("Exceptions".equals(s17))
        {
            i44 = j7;
            j44 = i7;
            k44 = l6;
            l44 = l43;
            i45 = k7;
            j45 = k6;
            attribute8 = attribute1;
            k45 = ((flag6) ? 1 : 0);
            l45 = ((flag5) ? 1 : 0);
        } else
        if ("Signature".equals(s17))
        {
            s10 = readUTF8(l43, ac);
            i44 = j7;
            j44 = i7;
            k44 = l6;
            l44 = l7;
            i45 = k7;
            j45 = k6;
            attribute8 = attribute1;
            k45 = ((flag6) ? 1 : 0);
            l45 = ((flag5) ? 1 : 0);
        } else
        if ("Deprecated".equals(s17))
        {
            l46 = 0x20000 | k6;
            j44 = i7;
            k44 = l6;
            l44 = l7;
            i45 = k7;
            j45 = l46;
            k45 = ((flag6) ? 1 : 0);
            l45 = ((flag5) ? 1 : 0);
            i44 = j7;
            attribute8 = attribute1;
        } else
        if ("RuntimeVisibleAnnotations".equals(s17))
        {
            i44 = j7;
            j44 = i7;
            k44 = l6;
            l44 = l7;
            i45 = k7;
            j45 = k6;
            attribute8 = attribute1;
            k45 = ((flag6) ? 1 : 0);
            l45 = l43;
        } else
        if ("AnnotationDefault".equals(s17))
        {
            i44 = j7;
            j44 = i7;
            k44 = l43;
            l44 = l7;
            i45 = k7;
            l45 = ((flag5) ? 1 : 0);
            attribute8 = attribute1;
            k45 = ((flag6) ? 1 : 0);
            j45 = k6;
        } else
        if ("Synthetic".equals(s17))
        {
            k46 = 0x41000 | k6;
            j44 = i7;
            k44 = l6;
            l44 = l7;
            i45 = k7;
            j45 = k46;
            k45 = ((flag6) ? 1 : 0);
            l45 = ((flag5) ? 1 : 0);
            i44 = j7;
            attribute8 = attribute1;
        } else
        if ("RuntimeInvisibleAnnotations".equals(s17))
        {
            i44 = j7;
            j44 = i7;
            k44 = l6;
            l44 = l7;
            i45 = k7;
            j45 = k6;
            attribute8 = attribute1;
            k45 = l43;
            l45 = ((flag5) ? 1 : 0);
        } else
        if ("RuntimeVisibleParameterAnnotations".equals(s17))
        {
            i44 = j7;
            j44 = l43;
            k44 = l6;
            l44 = l7;
            i45 = k7;
            k45 = ((flag6) ? 1 : 0);
            attribute8 = attribute1;
            j45 = k6;
            l45 = ((flag5) ? 1 : 0);
        } else
        if ("RuntimeInvisibleParameterAnnotations".equals(s17))
        {
            i44 = l43;
            j44 = i7;
            k44 = l6;
            l44 = l7;
            i45 = k7;
            attribute8 = attribute1;
            j45 = k6;
            k45 = ((flag6) ? 1 : 0);
            l45 = ((flag5) ? 1 : 0);
        } else
        {
            attribute7 = a(aattribute, s17, l43, k43, ac, -1, null);
            if (attribute7 == null)
            {
                break MISSING_BLOCK_LABEL_8391;
            }
            attribute7.a = attribute1;
            j44 = i7;
            k44 = l6;
            l44 = l7;
            i45 = k7;
            j45 = k6;
            k45 = ((flag6) ? 1 : 0);
            l45 = ((flag5) ? 1 : 0);
            j46 = j7;
            attribute8 = attribute7;
            i44 = j46;
        }
_L105:
        i46 = k43 + l43;
        k8--;
        k7 = i45;
        k6 = j45;
        l5 = i46;
        attribute1 = attribute8;
        flag6 = k45;
        flag5 = l45;
        j7 = i44;
        l7 = l44;
        i7 = j44;
        l6 = k44;
        if (true) goto _L4; else goto _L3
_L3:
        if (l7 != 0) goto _L6; else goto _L5
_L5:
        l8 = l7;
        as2 = null;
_L104:
        methodvisitor = classvisitor.visitMethod(k6, s8, s9, s10, as2);
        if (methodvisitor == null) goto _L8; else goto _L7
_L7:
        if (!(methodvisitor instanceof MethodWriter)) goto _L10; else goto _L9
_L9:
        methodwriter = (MethodWriter)methodvisitor;
        if (methodwriter.b.J != this || s10 != methodwriter.g) goto _L10; else goto _L11
_L11:
        if (as2 != null) goto _L13; else goto _L12
_L12:
        if (methodwriter.j == 0)
        {
            flag12 = true;
        } else
        {
            flag12 = false;
        }
_L102:
        if (!flag12) goto _L10; else goto _L14
_L14:
        methodwriter.h = j6;
        methodwriter.i = l5 - j6;
_L97:
        i6--;
        break MISSING_BLOCK_LABEL_2919;
_L6:
        as1 = new String[readUnsignedShort(l7)];
        l8 = l7 + 2;
        for (i9 = 0; i9 < as1.length; i9++)
        {
            as1[i9] = readClass(l8, ac);
            l8 += 2;
        }

        break; /* Loop/switch isn't completed */
_L13:
        j42 = as2.length;
        k42 = methodwriter.j;
        flag11 = false;
        if (j42 != k42) goto _L16; else goto _L15
_L15:
        flag11 = true;
        l42 = -1 + as2.length;
        i43 = l8;
        j43 = l42;
_L17:
        if (j43 < 0)
        {
            break; /* Loop/switch isn't completed */
        }
label2:
        {
            i43 -= 2;
            if (methodwriter.k[j43] == readUnsignedShort(i43))
            {
                break label2;
            }
            flag12 = false;
        }
        continue; /* Loop/switch isn't completed */
        j43--;
        if (true) goto _L17; else goto _L16
_L10:
        if (l6 != 0)
        {
            annotationvisitor = methodvisitor.visitAnnotationDefault();
            a(l6, ac, ((String) (null)), annotationvisitor);
            if (annotationvisitor != null)
            {
                annotationvisitor.visitEnd();
            }
        }
label3:
        for (k40 = 1; k40 >= 0; k40--)
        {
            if (k40 == 0)
            {
                l40 = ((flag6) ? 1 : 0);
            } else
            {
                l40 = ((flag5) ? 1 : 0);
            }
            if (l40 == 0)
            {
                continue;
            }
            i41 = readUnsignedShort(l40);
            j41 = l40 + 2;
            k41 = i41;
            do
            {
                if (k41 <= 0)
                {
                    continue label3;
                }
                l41 = j41 + 2;
                s16 = readUTF8(j41, ac);
                if (k40 != 0)
                {
                    flag10 = true;
                } else
                {
                    flag10 = false;
                }
                i42 = a(l41, ac, true, methodvisitor.visitAnnotation(s16, flag10));
                k41--;
                j41 = i42;
            } while (true);
        }

        if (i7 != 0)
        {
            a(i7, s9, ac, true, methodvisitor);
        }
        if (j7 != 0)
        {
            a(j7, s9, ac, false, methodvisitor);
        }
        for (; attribute1 != null; attribute1 = attribute6)
        {
            attribute6 = attribute1.a;
            attribute1.a = null;
            methodvisitor.visitAttribute(attribute1);
        }

_L8:
        if (methodvisitor == null || k7 == 0) goto _L19; else goto _L18
_L18:
        j9 = readUnsignedShort(k7);
        k9 = readUnsignedShort(k7 + 2);
        l9 = readInt(k7 + 4);
        i10 = k7 + 8;
        j10 = i10 + l9;
        methodvisitor.visitCode();
        alabel = new Label[l9 + 2];
        readLabel(l9 + 1, alabel);
        k10 = i10;
_L31:
        if (k10 >= j10)
        {
            break MISSING_BLOCK_LABEL_4932;
        }
        l37 = k10 - i10;
        i38 = 0xff & abyte0[k10];
        ClassWriter.a[i38];
        JVM INSTR tableswitch 0 16: default 4608
    //                   0 4621
    //                   1 4905
    //                   2 4914
    //                   3 4905
    //                   4 4621
    //                   5 4914
    //                   6 4914
    //                   7 4923
    //                   8 4630
    //                   9 4657
    //                   10 4905
    //                   11 4914
    //                   12 4914
    //                   13 4720
    //                   14 4817
    //                   15 4608
    //                   16 4684;
           goto _L20 _L21 _L22 _L23 _L22 _L21 _L23 _L23 _L24 _L25 _L26 _L22 _L23 _L23 _L27 _L28 _L20 _L29
_L24:
        break MISSING_BLOCK_LABEL_4923;
_L21:
        break; /* Loop/switch isn't completed */
_L20:
        j38 = k10 + 4;
_L32:
        k10 = j38;
        if (true) goto _L31; else goto _L30
_L30:
        j38 = k10 + 1;
          goto _L32
_L25:
        readLabel(l37 + readShort(k10 + 1), alabel);
        j38 = k10 + 3;
          goto _L32
_L26:
        readLabel(l37 + readInt(k10 + 1), alabel);
        j38 = k10 + 5;
          goto _L32
_L29:
        if ((0xff & abyte0[k10 + 1]) == 132)
        {
            j38 = k10 + 6;
        } else
        {
            j38 = k10 + 4;
        }
          goto _L32
_L27:
        k39 = (k10 + 4) - (l37 & 3);
        readLabel(l37 + readInt(k39), alabel);
        l39 = 1 + (readInt(k39 + 8) - readInt(k39 + 4));
        j38 = k39 + 12;
        i40 = l39;
        while (i40 > 0) 
        {
            readLabel(l37 + readInt(j38), alabel);
            j40 = j38 + 4;
            i40--;
            j38 = j40;
        }
          goto _L32
_L28:
        k38 = (k10 + 4) - (l37 & 3);
        readLabel(l37 + readInt(k38), alabel);
        l38 = readInt(k38 + 4);
        j38 = k38 + 8;
        i39 = l38;
        while (i39 > 0) 
        {
            readLabel(l37 + readInt(j38 + 4), alabel);
            j39 = j38 + 8;
            i39--;
            j38 = j39;
        }
          goto _L32
_L22:
        j38 = k10 + 2;
          goto _L32
_L23:
        j38 = k10 + 3;
          goto _L32
        j38 = k10 + 5;
          goto _L32
        l10 = readUnsignedShort(k10);
        i11 = k10 + 2;
        while (l10 > 0) 
        {
            label5 = readLabel(readUnsignedShort(i11), alabel);
            label6 = readLabel(readUnsignedShort(i11 + 2), alabel);
            label7 = readLabel(readUnsignedShort(i11 + 4), alabel);
            k37 = readUnsignedShort(i11 + 6);
            if (k37 == 0)
            {
                methodvisitor.visitTryCatchBlock(label5, label6, label7, null);
            } else
            {
                methodvisitor.visitTryCatchBlock(label5, label6, label7, readUTF8(a[k37], ac));
            }
            i11 += 8;
            l10--;
        }
        j11 = 0;
        k11 = 0;
        l11 = 0;
        i12 = 0;
        flag7 = false;
        flag8 = true;
        attribute2 = null;
        j12 = readUnsignedShort(i11);
        k12 = i11 + 2;
        l12 = j12;
        i13 = k12;
_L41:
        if (l12 <= 0) goto _L34; else goto _L33
_L33:
        s15 = readUTF8(i13, ac);
        if (!"LocalVariableTable".equals(s15)) goto _L36; else goto _L35
_L35:
        if (flag3) goto _L38; else goto _L37
_L36:
        if (!"LocalVariableTypeTable".equals(s15)) goto _L40; else goto _L39
_L39:
        i36 = i13 + 6;
        j33 = ((flag7) ? 1 : 0);
        l32 = i12;
        k33 = l11;
        i33 = i36;
        flag9 = flag8;
        l33 = j11;
_L47:
        i34 = i13 + (6 + readInt(i13 + 2));
        j34 = l12 - 1;
        flag8 = flag9;
        k11 = i33;
        j11 = l33;
        l12 = j34;
        i13 = i34;
        i12 = l32;
        l11 = k33;
        flag7 = j33;
          goto _L41
_L40:
        if (!"LineNumberTable".equals(s15)) goto _L43; else goto _L42
_L42:
        if (!flag3)
        {
            j35 = readUnsignedShort(i13 + 6);
            k35 = i13 + 8;
            for (; j35 > 0; j35--)
            {
                l35 = readUnsignedShort(k35);
                if (alabel[l35] == null)
                {
                    label2 = readLabel(l35, alabel);
                    label2.a = 1 | label2.a;
                }
                alabel[l35].b = readUnsignedShort(k35 + 2);
                k35 += 4;
            }

        }
          goto _L38
_L43:
        if (!"StackMapTable".equals(s15)) goto _L45; else goto _L44
_L44:
        if ((i & 4) != 0) goto _L38; else goto _L46
_L46:
        k34 = i13 + 8;
        l34 = readInt(i13 + 2);
        i35 = readUnsignedShort(i13 + 6);
        k33 = k34;
        i33 = k11;
        l33 = j11;
        l32 = l34;
        j33 = i35;
        flag9 = flag8;
          goto _L47
_L45:
        if (!"StackMap".equals(s15)) goto _L49; else goto _L48
_L48:
        if ((i & 4) != 0) goto _L38; else goto _L50
_L50:
        k33 = i13 + 8;
        l32 = readInt(i13 + 2);
        j33 = readUnsignedShort(i13 + 6);
        i33 = k11;
        l33 = j11;
        flag9 = false;
          goto _L47
_L49:
        k32 = 0;
        attribute4 = attribute2;
_L56:
        if (k32 >= aattribute.length) goto _L52; else goto _L51
_L51:
        if (!aattribute[k32].type.equals(s15)) goto _L54; else goto _L53
_L53:
        attribute5 = aattribute[k32].read(this, i13 + 6, readInt(i13 + 2), ac, i10 - 8, alabel);
        if (attribute5 == null) goto _L54; else goto _L55
_L55:
        attribute5.a = attribute4;
_L100:
        k32++;
        attribute4 = attribute5;
          goto _L56
_L34:
        if (l11 == 0) goto _L58; else goto _L57
_L57:
        aobj3 = new Object[k9];
        aobj1 = new Object[j9];
        if (!flag4) goto _L60; else goto _L59
_L59:
        if ((k6 & 8) == 0)
        {
            if ("<init>".equals(s8))
            {
                i29 = 1;
                aobj3[0] = Opcodes.UNINITIALIZED_THIS;
            } else
            {
                i29 = 1;
                aobj3[0] = readClass(2 + header, ac);
            }
        } else
        {
            i29 = 0;
        }
        l29 = 1;
        i30 = l29 + 1;
        s9.charAt(l29);
        JVM INSTR lookupswitch 10: default 5832
    //                   66: 5939
    //                   67: 5939
    //                   68: 6014
    //                   70: 5964
    //                   73: 5939
    //                   74: 5989
    //                   76: 6134
    //                   83: 5939
    //                   90: 5939
    //                   91: 6039;
           goto _L61 _L62 _L62 _L63 _L64 _L62 _L65 _L66 _L62 _L62 _L67
_L61:
        byte0 = -1;
        for (j29 = l11; j29 < -2 + (l11 + i12); j29++)
        {
            if (abyte0[j29] == 8)
            {
                k29 = readUnsignedShort(j29 + 1);
                if (k29 >= 0 && k29 < l9 && (0xff & abyte0[i10 + k29]) == 187)
                {
                    readLabel(k29, alabel);
                }
            }
        }

        break; /* Loop/switch isn't completed */
_L62:
        j32 = i29 + 1;
        aobj3[i29] = Opcodes.INTEGER;
        i29 = j32;
        l29 = i30;
        break MISSING_BLOCK_LABEL_5730;
_L64:
        i32 = i29 + 1;
        aobj3[i29] = Opcodes.FLOAT;
        i29 = i32;
        l29 = i30;
        break MISSING_BLOCK_LABEL_5730;
_L65:
        l31 = i29 + 1;
        aobj3[i29] = Opcodes.LONG;
        i29 = l31;
        l29 = i30;
        break MISSING_BLOCK_LABEL_5730;
_L63:
        k31 = i29 + 1;
        aobj3[i29] = Opcodes.DOUBLE;
        i29 = k31;
        l29 = i30;
        break MISSING_BLOCK_LABEL_5730;
_L67:
        for (; s9.charAt(i30) == '['; i30++) { }
        if (s9.charAt(i30) == 'L')
        {
            for (j31 = i30 + 1; s9.charAt(j31) != ';'; j31++) { }
            i30 = j31;
        }
        l30 = i29 + 1;
        i31 = i30 + 1;
        aobj3[i29] = s9.substring(l29, i31);
        i29 = l30;
        l29 = i31;
        break MISSING_BLOCK_LABEL_5730;
_L66:
        for (; s9.charAt(i30) != ';'; i30++) { }
        j30 = i29 + 1;
        k30 = l29 + 1;
        l29 = i30 + 1;
        aobj3[i29] = s9.substring(k30, i30);
        i29 = j30;
        break MISSING_BLOCK_LABEL_5730;
        j13 = i29;
        aobj = aobj3;
_L99:
        aobj2 = aobj;
        k13 = 0;
        l13 = 0;
        i14 = j13;
        j14 = byte0;
        byte1 = 0;
        k14 = ((flag7) ? 1 : 0);
        l14 = l11;
        i15 = i10;
_L91:
        if (i15 >= j10) goto _L69; else goto _L68
_L68:
        j18 = i15 - i10;
        label1 = alabel[j18];
        if (label1 != null)
        {
            methodvisitor.visitLabel(label1);
            if (!flag3 && label1.b > 0)
            {
                methodvisitor.visitLineNumber(label1.b, label1);
            }
        }
        k18 = k14;
_L74:
        if (aobj2 == null || j14 != j18 && j14 != -1) goto _L71; else goto _L70
_L70:
        if (!flag8 || flag4)
        {
            methodvisitor.visitFrame(-1, i14, aobj2, k13, aobj1);
        } else
        if (j14 != -1)
        {
            methodvisitor.visitFrame(byte1, l13, aobj2, k13, aobj1);
        }
        if (k18 <= 0) goto _L73; else goto _L72
_L72:
        if (flag8)
        {
            i23 = l14 + 1;
            k22 = 0xff & abyte0[l14];
            l22 = j14;
        } else
        {
            k22 = 255;
            l22 = -1;
            i23 = l14;
        }
        j23 = 0;
        if (k22 < 64)
        {
            byte2 = 3;
            k25 = 0;
            l25 = k22;
        } else
        if (k22 < 128)
        {
            l25 = k22 - 64;
            i23 = a(aobj1, 0, i23, ac, alabel);
            byte2 = 4;
            k25 = 1;
            j23 = 0;
        } else
        {
            k23 = readUnsignedShort(i23);
            i23 += 2;
            if (k22 == 247)
            {
                i23 = a(aobj1, 0, i23, ac, alabel);
                byte2 = 4;
                k25 = 1;
                l25 = k23;
                j23 = 0;
            } else
            if (k22 >= 248 && k22 < 251)
            {
                l28 = 251 - k22;
                i14 -= l28;
                byte2 = 2;
                j23 = l28;
                l25 = k23;
                k25 = 0;
            } else
            if (k22 == 251)
            {
                byte2 = 3;
                l25 = k23;
                k25 = 0;
                j23 = 0;
            } else
            {
label4:
                {
                    if (k22 >= 255)
                    {
                        break label4;
                    }
                    if (flag4)
                    {
                        i27 = i14;
                    } else
                    {
                        i27 = 0;
                    }
                    j27 = k22 - 251;
                    k27 = i23;
                    l27 = i27;
                    for (i28 = j27; i28 > 0;)
                    {
                        k28 = l27 + 1;
                        k27 = a(aobj2, l27, k27, ac, alabel);
                        i28--;
                        l27 = k28;
                    }

                    j28 = k22 - 251;
                    i14 += j28;
                    byte2 = 1;
                    i23 = k27;
                    j23 = j28;
                    l25 = k23;
                    k25 = 0;
                }
            }
        }
_L98:
        i26 = l22 + (l25 + 1);
        readLabel(i26, alabel);
        j26 = k18 - 1;
        k13 = k25;
        l13 = j23;
        byte1 = byte2;
        k18 = j26;
        j14 = i26;
        l14 = i23;
          goto _L74
_L73:
        aobj2 = null;
          goto _L74
_L71:
        l18 = 0xff & abyte0[i15];
        ClassWriter.a[l18];
        JVM INSTR tableswitch 0 16: default 6988
    //                   0 7033
    //                   1 7537
    //                   2 7562
    //                   3 7508
    //                   4 7051
    //                   5 7804
    //                   6 7651
    //                   7 7651
    //                   8 7120
    //                   9 7152
    //                   10 7588
    //                   11 7621
    //                   12 7832
    //                   13 7267
    //                   14 7387
    //                   15 6988
    //                   16 7187;
           goto _L75 _L76 _L77 _L78 _L79 _L80 _L81 _L82 _L82 _L83 _L84 _L85 _L86 _L87 _L88 _L89 _L75 _L90
_L87:
        break MISSING_BLOCK_LABEL_7832;
_L75:
        methodvisitor.visitMultiANewArrayInsn(readClass(i15 + 1, ac), 0xff & abyte0[i15 + 3]);
        i19 = i15 + 4;
_L92:
        k14 = k18;
        i15 = i19;
          goto _L91
_L76:
        methodvisitor.visitInsn(l18);
        i19 = i15 + 1;
          goto _L92
_L80:
        if (l18 > 54)
        {
            j22 = l18 - 59;
            methodvisitor.visitVarInsn(54 + (j22 >> 2), j22 & 3);
        } else
        {
            i22 = l18 - 26;
            methodvisitor.visitVarInsn(21 + (i22 >> 2), i22 & 3);
        }
        i19 = i15 + 1;
          goto _L92
_L83:
        methodvisitor.visitJumpInsn(l18, alabel[j18 + readShort(i15 + 1)]);
        i19 = i15 + 3;
          goto _L92
_L84:
        methodvisitor.visitJumpInsn(l18 - 33, alabel[j18 + readInt(i15 + 1)]);
        i19 = i15 + 5;
          goto _L92
_L90:
        l21 = 0xff & abyte0[i15 + 1];
        if (l21 == 132)
        {
            methodvisitor.visitIincInsn(readUnsignedShort(i15 + 2), readShort(i15 + 4));
            i19 = i15 + 6;
        } else
        {
            methodvisitor.visitVarInsn(l21, readUnsignedShort(i15 + 2));
            i19 = i15 + 4;
        }
          goto _L92
_L88:
        k20 = (i15 + 4) - (j18 & 3);
        l20 = j18 + readInt(k20);
        i21 = readInt(k20 + 4);
        j21 = readInt(k20 + 8);
        i19 = k20 + 12;
        alabel2 = new Label[1 + (j21 - i21)];
        for (k21 = 0; k21 < alabel2.length; k21++)
        {
            alabel2[k21] = alabel[j18 + readInt(i19)];
            i19 += 4;
        }

        methodvisitor.visitTableSwitchInsn(i21, j21, alabel[l20], alabel2);
          goto _L92
_L89:
        k19 = (i15 + 4) - (j18 & 3);
        l19 = j18 + readInt(k19);
        i20 = readInt(k19 + 4);
        i19 = k19 + 8;
        ai1 = new int[i20];
        alabel1 = new Label[i20];
        for (j20 = 0; j20 < ai1.length; j20++)
        {
            ai1[j20] = readInt(i19);
            alabel1[j20] = alabel[j18 + readInt(i19 + 4)];
            i19 += 8;
        }

        methodvisitor.visitLookupSwitchInsn(alabel[l19], ai1, alabel1);
          goto _L92
_L79:
        methodvisitor.visitVarInsn(l18, 0xff & abyte0[i15 + 1]);
        i19 = i15 + 2;
          goto _L92
_L77:
        methodvisitor.visitIntInsn(l18, abyte0[i15 + 1]);
        i19 = i15 + 2;
          goto _L92
_L78:
        methodvisitor.visitIntInsn(l18, readShort(i15 + 1));
        i19 = i15 + 3;
          goto _L92
_L85:
        methodvisitor.visitLdcInsn(readConst(0xff & abyte0[i15 + 1], ac));
        i19 = i15 + 2;
          goto _L92
_L86:
        methodvisitor.visitLdcInsn(readConst(readUnsignedShort(i15 + 1), ac));
        i19 = i15 + 3;
          goto _L92
_L82:
        j19 = a[readUnsignedShort(i15 + 1)];
        if (l18 == 186)
        {
            s12 = "java/lang/dyn/Dynamic";
        } else
        {
            s12 = readClass(j19, ac);
            j19 = a[readUnsignedShort(j19 + 2)];
        }
        s13 = readUTF8(j19, ac);
        s14 = readUTF8(j19 + 2, ac);
        if (l18 < 182)
        {
            methodvisitor.visitFieldInsn(l18, s12, s13, s14);
        } else
        {
            methodvisitor.visitMethodInsn(l18, s12, s13, s14);
        }
        if (l18 == 185 || l18 == 186)
        {
            i19 = i15 + 5;
        } else
        {
            i19 = i15 + 3;
        }
          goto _L92
_L81:
        methodvisitor.visitTypeInsn(l18, readClass(i15 + 1, ac));
        i19 = i15 + 3;
          goto _L92
        methodvisitor.visitIincInsn(0xff & abyte0[i15 + 1], abyte0[i15 + 2]);
        i19 = i15 + 3;
          goto _L92
_L69:
        label = alabel[j10 - i10];
        if (label != null)
        {
            methodvisitor.visitLabel(label);
        }
        if (flag3 || j11 == 0)
        {
            break MISSING_BLOCK_LABEL_8169;
        }
        ai = null;
        if (k11 != 0)
        {
            j17 = 3 * readUnsignedShort(k11);
            k17 = k11 + 2;
            ai = new int[j17];
            while (j17 > 0) 
            {
                l17 = j17 - 1;
                ai[l17] = k17 + 6;
                i18 = l17 - 1;
                ai[i18] = readUnsignedShort(k17 + 8);
                j17 = i18 - 1;
                ai[j17] = readUnsignedShort(k17);
                k17 += 10;
            }
        }
        j15 = readUnsignedShort(j11);
        k15 = j11 + 2;
        l15 = j15;
_L95:
        if (l15 <= 0)
        {
            break MISSING_BLOCK_LABEL_8169;
        }
        i16 = readUnsignedShort(k15);
        j16 = readUnsignedShort(k15 + 2);
        k16 = readUnsignedShort(k15 + 8);
        s11 = null;
        if (ai == null) goto _L94; else goto _L93
_L93:
        l16 = 0;
_L96:
        i17 = ai.length;
        s11 = null;
        if (l16 < i17)
        {
            if (ai[l16] != i16 || ai[l16 + 1] != k16)
            {
                break MISSING_BLOCK_LABEL_8163;
            }
            s11 = readUTF8(ai[l16 + 2], ac);
        }
_L94:
        methodvisitor.visitLocalVariable(readUTF8(k15 + 4, ac), readUTF8(k15 + 6, ac), s11, alabel[i16], alabel[i16 + j16], k16);
        k15 += 10;
        l15--;
          goto _L95
        l16 += 3;
          goto _L96
        for (; attribute2 != null; attribute2 = attribute3)
        {
            attribute3 = attribute2.a;
            attribute2.a = null;
            methodvisitor.visitAttribute(attribute2);
        }

        methodvisitor.visitMaxs(j9, k9);
_L19:
        if (methodvisitor != null)
        {
            methodvisitor.visitEnd();
        }
          goto _L97
_L2:
        classvisitor.visitEnd();
        return;
        l23 = readUnsignedShort(i23);
        i24 = i23 + 2;
        j24 = 0;
        for (k24 = l23; k24 > 0;)
        {
            l26 = j24 + 1;
            i24 = a(aobj2, j24, i24, ac, alabel);
            k24--;
            j24 = l26;
        }

        l24 = readUnsignedShort(i24);
        i23 = i24 + 2;
        i25 = 0;
        for (j25 = l24; j25 > 0;)
        {
            k26 = i25 + 1;
            i23 = a(aobj1, i25, i23, ac, alabel);
            j25--;
            i25 = k26;
        }

        k25 = l24;
        j23 = l23;
        i14 = l23;
        l25 = k23;
        byte2 = 0;
          goto _L98
_L60:
        i29 = 0;
          goto _L61
_L58:
        aobj = null;
        byte0 = 0;
        aobj1 = null;
        j13 = 0;
          goto _L99
_L38:
        flag9 = flag8;
        j33 = ((flag7) ? 1 : 0);
        l32 = i12;
        k33 = l11;
        i33 = k11;
        l33 = j11;
          goto _L47
_L37:
        j36 = i13 + 6;
        k36 = readUnsignedShort(i13 + 6);
        l36 = i13 + 8;
        for (; k36 > 0; k36--)
        {
            i37 = readUnsignedShort(l36);
            if (alabel[i37] == null)
            {
                label4 = readLabel(i37, alabel);
                label4.a = 1 | label4.a;
            }
            j37 = i37 + readUnsignedShort(l36 + 2);
            if (alabel[j37] == null)
            {
                label3 = readLabel(j37, alabel);
                label3.a = 1 | label3.a;
            }
            l36 += 10;
        }

        j33 = ((flag7) ? 1 : 0);
        l32 = i12;
        k33 = l11;
        i33 = k11;
        l33 = j36;
        flag9 = flag8;
          goto _L47
_L54:
        attribute5 = attribute4;
          goto _L100
_L52:
        flag9 = flag8;
        l32 = i12;
        attribute2 = attribute4;
        i33 = k11;
        j33 = ((flag7) ? 1 : 0);
        k33 = l11;
        l33 = j11;
          goto _L47
_L16:
        flag12 = flag11;
        if (true) goto _L102; else goto _L101
_L101:
        as2 = as1;
        if (true) goto _L104; else goto _L103
_L103:
        i44 = j7;
        j44 = i7;
        k44 = l6;
        l44 = l7;
        i45 = k7;
        j45 = k6;
        attribute8 = attribute1;
        k45 = ((flag6) ? 1 : 0);
        l45 = ((flag5) ? 1 : 0);
          goto _L105
    }

    public int getAccess()
    {
        return readUnsignedShort(header);
    }

    public String getClassName()
    {
        return readClass(2 + header, new char[d]);
    }

    public String[] getInterfaces()
    {
        int i = 6 + header;
        int j = readUnsignedShort(i);
        String as[] = new String[j];
        if (j > 0)
        {
            char ac[] = new char[d];
            for (int k = 0; k < j; k++)
            {
                i += 2;
                as[k] = readClass(i, ac);
            }

        }
        return as;
    }

    public int getItem(int i)
    {
        return a[i];
    }

    public String getSuperName()
    {
        int i = a[readUnsignedShort(4 + header)];
        if (i == 0)
        {
            return null;
        } else
        {
            return readUTF8(i, new char[d]);
        }
    }

    public int readByte(int i)
    {
        return 0xff & b[i];
    }

    public String readClass(int i, char ac[])
    {
        return readUTF8(a[readUnsignedShort(i)], ac);
    }

    public Object readConst(int i, char ac[])
    {
        int j = a[i];
        switch (b[j - 1])
        {
        default:
            return readUTF8(j, ac);

        case 3: // '\003'
            return new Integer(readInt(j));

        case 4: // '\004'
            return new Float(Float.intBitsToFloat(readInt(j)));

        case 5: // '\005'
            return new Long(readLong(j));

        case 6: // '\006'
            return new Double(Double.longBitsToDouble(readLong(j)));

        case 7: // '\007'
            return Type.getObjectType(readUTF8(j, ac));
        }
    }

    public int readInt(int i)
    {
        byte abyte0[] = b;
        return (0xff & abyte0[i]) << 24 | (0xff & abyte0[i + 1]) << 16 | (0xff & abyte0[i + 2]) << 8 | 0xff & abyte0[i + 3];
    }

    protected Label readLabel(int i, Label alabel[])
    {
        if (alabel[i] == null)
        {
            alabel[i] = new Label();
        }
        return alabel[i];
    }

    public long readLong(int i)
    {
        long l = readInt(i);
        return 0xffffffffL & (long)readInt(i + 4) | l << 32;
    }

    public short readShort(int i)
    {
        byte abyte0[] = b;
        return (short)((0xff & abyte0[i]) << 8 | 0xff & abyte0[i + 1]);
    }

    public String readUTF8(int i, char ac[])
    {
        int j = readUnsignedShort(i);
        String s = c[j];
        if (s != null)
        {
            return s;
        } else
        {
            int k = a[j];
            String as[] = c;
            String s1 = a(k + 2, readUnsignedShort(k), ac);
            as[j] = s1;
            return s1;
        }
    }

    public int readUnsignedShort(int i)
    {
        byte abyte0[] = b;
        return (0xff & abyte0[i]) << 8 | 0xff & abyte0[i + 1];
    }
}

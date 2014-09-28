// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.org.objectweb.asm;


// Referenced classes of package org.codehaus.jackson.org.objectweb.asm:
//            AnnotationVisitor, ByteVector, ClassWriter, Item, 
//            Type

final class AnnotationWriter
    implements AnnotationVisitor
{

    private final ClassWriter a;
    private int b;
    private final boolean c;
    private final ByteVector d;
    private final ByteVector e;
    private final int f;
    AnnotationWriter g;
    AnnotationWriter h;

    AnnotationWriter(ClassWriter classwriter, boolean flag, ByteVector bytevector, ByteVector bytevector1, int i)
    {
        a = classwriter;
        c = flag;
        d = bytevector;
        e = bytevector1;
        f = i;
    }

    static void a(AnnotationWriter aannotationwriter[], int i, ByteVector bytevector)
    {
        int j = 1 + 2 * (aannotationwriter.length - i);
        int k = i;
        while (k < aannotationwriter.length) 
        {
            int i1;
            if (aannotationwriter[k] == null)
            {
                i1 = 0;
            } else
            {
                i1 = aannotationwriter[k].a();
            }
            j += i1;
            k++;
        }
        bytevector.putInt(j).putByte(aannotationwriter.length - i);
        for (; i < aannotationwriter.length; i++)
        {
            AnnotationWriter annotationwriter = aannotationwriter[i];
            AnnotationWriter annotationwriter1 = null;
            int l = 0;
            while (annotationwriter != null) 
            {
                l++;
                annotationwriter.visitEnd();
                annotationwriter.h = annotationwriter1;
                AnnotationWriter annotationwriter2 = annotationwriter.g;
                AnnotationWriter annotationwriter3 = annotationwriter;
                annotationwriter = annotationwriter2;
                annotationwriter1 = annotationwriter3;
            }
            bytevector.putShort(l);
            for (; annotationwriter1 != null; annotationwriter1 = annotationwriter1.h)
            {
                bytevector.putByteArray(annotationwriter1.d.a, 0, annotationwriter1.d.b);
            }

        }

    }

    int a()
    {
        int i = 0;
        for (; this != null; this = g)
        {
            i += d.b;
        }

        return i;
    }

    void a(ByteVector bytevector)
    {
        AnnotationWriter annotationwriter = null;
        int i = 2;
        int j = 0;
        AnnotationWriter annotationwriter2;
        for (AnnotationWriter annotationwriter1 = this; annotationwriter1 != null; annotationwriter1 = annotationwriter2)
        {
            j++;
            i += annotationwriter1.d.b;
            annotationwriter1.visitEnd();
            annotationwriter1.h = annotationwriter;
            annotationwriter2 = annotationwriter1.g;
            annotationwriter = annotationwriter1;
        }

        bytevector.putInt(i);
        bytevector.putShort(j);
        for (; annotationwriter != null; annotationwriter = annotationwriter.h)
        {
            bytevector.putByteArray(annotationwriter.d.a, 0, annotationwriter.d.b);
        }

    }

    public void visit(String s, Object obj)
    {
        int i;
        int j;
        i = 1;
        j = 0;
        b = 1 + b;
        if (c)
        {
            d.putShort(a.newUTF8(s));
        }
        if (!(obj instanceof String)) goto _L2; else goto _L1
_L1:
        d.b(115, a.newUTF8((String)obj));
_L4:
        return;
_L2:
        if (obj instanceof Byte)
        {
            d.b(66, a.a(((Byte)obj).byteValue()).a);
            return;
        }
        if (obj instanceof Boolean)
        {
            if (!((Boolean)obj).booleanValue())
            {
                i = 0;
            }
            d.b(90, a.a(i).a);
            return;
        }
        if (obj instanceof Character)
        {
            d.b(67, a.a(((Character)obj).charValue()).a);
            return;
        }
        if (obj instanceof Short)
        {
            d.b(83, a.a(((Short)obj).shortValue()).a);
            return;
        }
        if (obj instanceof Type)
        {
            d.b(99, a.newUTF8(((Type)obj).getDescriptor()));
            return;
        }
        if (!(obj instanceof byte[]))
        {
            break; /* Loop/switch isn't completed */
        }
        byte abyte0[] = (byte[])(byte[])obj;
        d.b(91, abyte0.length);
        while (j < abyte0.length) 
        {
            d.b(66, a.a(abyte0[j]).a);
            j++;
        }
        if (true) goto _L4; else goto _L3
_L3:
        if (!(obj instanceof boolean[]))
        {
            break; /* Loop/switch isn't completed */
        }
        boolean aflag[] = (boolean[])(boolean[])obj;
        d.b(91, aflag.length);
        int k = 0;
        while (k < aflag.length) 
        {
            ByteVector bytevector = d;
            ClassWriter classwriter = a;
            int l;
            if (aflag[k])
            {
                l = i;
            } else
            {
                l = 0;
            }
            bytevector.b(90, classwriter.a(l).a);
            k++;
        }
        if (true) goto _L4; else goto _L5
_L5:
        if (!(obj instanceof short[]))
        {
            break; /* Loop/switch isn't completed */
        }
        short aword0[] = (short[])(short[])obj;
        d.b(91, aword0.length);
        while (j < aword0.length) 
        {
            d.b(83, a.a(aword0[j]).a);
            j++;
        }
        if (true) goto _L4; else goto _L6
_L6:
        if (!(obj instanceof char[]))
        {
            break; /* Loop/switch isn't completed */
        }
        char ac[] = (char[])(char[])obj;
        d.b(91, ac.length);
        while (j < ac.length) 
        {
            d.b(67, a.a(ac[j]).a);
            j++;
        }
        if (true) goto _L4; else goto _L7
_L7:
        if (!(obj instanceof int[]))
        {
            break; /* Loop/switch isn't completed */
        }
        int ai[] = (int[])(int[])obj;
        d.b(91, ai.length);
        while (j < ai.length) 
        {
            d.b(73, a.a(ai[j]).a);
            j++;
        }
        if (true) goto _L4; else goto _L8
_L8:
        if (!(obj instanceof long[]))
        {
            break; /* Loop/switch isn't completed */
        }
        long al[] = (long[])(long[])obj;
        d.b(91, al.length);
        while (j < al.length) 
        {
            d.b(74, a.a(al[j]).a);
            j++;
        }
        if (true) goto _L4; else goto _L9
_L9:
        if (!(obj instanceof float[]))
        {
            break; /* Loop/switch isn't completed */
        }
        float af[] = (float[])(float[])obj;
        d.b(91, af.length);
        while (j < af.length) 
        {
            d.b(70, a.a(af[j]).a);
            j++;
        }
        if (true) goto _L4; else goto _L10
_L10:
        if (obj instanceof double[])
        {
            double ad[] = (double[])(double[])obj;
            d.b(91, ad.length);
            while (j < ad.length) 
            {
                d.b(68, a.a(ad[j]).a);
                j++;
            }
        } else
        {
            Item item = a.a(obj);
            d.b(".s.IFJDCS".charAt(item.b), item.a);
            return;
        }
        if (true) goto _L4; else goto _L11
_L11:
    }

    public AnnotationVisitor visitAnnotation(String s, String s1)
    {
        b = 1 + b;
        if (c)
        {
            d.putShort(a.newUTF8(s));
        }
        d.b(64, a.newUTF8(s1)).putShort(0);
        return new AnnotationWriter(a, true, d, d, -2 + d.b);
    }

    public AnnotationVisitor visitArray(String s)
    {
        b = 1 + b;
        if (c)
        {
            d.putShort(a.newUTF8(s));
        }
        d.b(91, 0);
        return new AnnotationWriter(a, false, d, d, -2 + d.b);
    }

    public void visitEnd()
    {
        if (e != null)
        {
            byte abyte0[] = e.a;
            abyte0[f] = (byte)(b >>> 8);
            abyte0[1 + f] = (byte)b;
        }
    }

    public void visitEnum(String s, String s1, String s2)
    {
        b = 1 + b;
        if (c)
        {
            d.putShort(a.newUTF8(s));
        }
        d.b(101, a.newUTF8(s1)).putShort(a.newUTF8(s2));
    }
}

// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.org.objectweb.asm;


// Referenced classes of package org.codehaus.jackson.org.objectweb.asm:
//            FieldVisitor, ClassWriter, Item, AnnotationWriter, 
//            Attribute, ByteVector, AnnotationVisitor

final class FieldWriter
    implements FieldVisitor
{

    FieldWriter a;
    private final ClassWriter b;
    private final int c;
    private final int d;
    private final int e;
    private int f;
    private int g;
    private AnnotationWriter h;
    private AnnotationWriter i;
    private Attribute j;

    FieldWriter(ClassWriter classwriter, int k, String s, String s1, String s2, Object obj)
    {
        if (classwriter.y == null)
        {
            classwriter.y = this;
        } else
        {
            classwriter.z.a = this;
        }
        classwriter.z = this;
        b = classwriter;
        c = k;
        d = classwriter.newUTF8(s);
        e = classwriter.newUTF8(s1);
        if (s2 != null)
        {
            f = classwriter.newUTF8(s2);
        }
        if (obj != null)
        {
            g = classwriter.a(obj).a;
        }
    }

    int a()
    {
        int k = 8;
        if (g != 0)
        {
            b.newUTF8("ConstantValue");
            k = 16;
        }
        if ((0x1000 & c) != 0 && ((0xffff & b.b) < 49 || (0x40000 & c) != 0))
        {
            b.newUTF8("Synthetic");
            k += 6;
        }
        if ((0x20000 & c) != 0)
        {
            b.newUTF8("Deprecated");
            k += 6;
        }
        if (f != 0)
        {
            b.newUTF8("Signature");
            k += 8;
        }
        if (h != null)
        {
            b.newUTF8("RuntimeVisibleAnnotations");
            k += 8 + h.a();
        }
        int l;
        if (i != null)
        {
            b.newUTF8("RuntimeInvisibleAnnotations");
            l = k + (8 + i.a());
        } else
        {
            l = k;
        }
        if (j != null)
        {
            l += j.a(b, null, 0, -1, -1);
        }
        return l;
    }

    void a(ByteVector bytevector)
    {
        int k = 0x60000 | (0x40000 & c) / 64;
        bytevector.putShort(c & ~k).putShort(d).putShort(e);
        int l;
        if (g != 0)
        {
            l = 1;
        } else
        {
            l = 0;
        }
        if ((0x1000 & c) != 0 && ((0xffff & b.b) < 49 || (0x40000 & c) != 0))
        {
            l++;
        }
        if ((0x20000 & c) != 0)
        {
            l++;
        }
        if (f != 0)
        {
            l++;
        }
        if (h != null)
        {
            l++;
        }
        if (i != null)
        {
            l++;
        }
        if (j != null)
        {
            l += j.a();
        }
        bytevector.putShort(l);
        if (g != 0)
        {
            bytevector.putShort(b.newUTF8("ConstantValue"));
            bytevector.putInt(2).putShort(g);
        }
        if ((0x1000 & c) != 0 && ((0xffff & b.b) < 49 || (0x40000 & c) != 0))
        {
            bytevector.putShort(b.newUTF8("Synthetic")).putInt(0);
        }
        if ((0x20000 & c) != 0)
        {
            bytevector.putShort(b.newUTF8("Deprecated")).putInt(0);
        }
        if (f != 0)
        {
            bytevector.putShort(b.newUTF8("Signature"));
            bytevector.putInt(2).putShort(f);
        }
        if (h != null)
        {
            bytevector.putShort(b.newUTF8("RuntimeVisibleAnnotations"));
            h.a(bytevector);
        }
        if (i != null)
        {
            bytevector.putShort(b.newUTF8("RuntimeInvisibleAnnotations"));
            i.a(bytevector);
        }
        if (j != null)
        {
            j.a(b, null, 0, -1, -1, bytevector);
        }
    }

    public AnnotationVisitor visitAnnotation(String s, boolean flag)
    {
        ByteVector bytevector = new ByteVector();
        bytevector.putShort(b.newUTF8(s)).putShort(0);
        AnnotationWriter annotationwriter = new AnnotationWriter(b, true, bytevector, bytevector, 2);
        if (flag)
        {
            annotationwriter.g = h;
            h = annotationwriter;
            return annotationwriter;
        } else
        {
            annotationwriter.g = i;
            i = annotationwriter;
            return annotationwriter;
        }
    }

    public void visitAttribute(Attribute attribute)
    {
        attribute.a = j;
        j = attribute;
    }

    public void visitEnd()
    {
    }
}

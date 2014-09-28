// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.org.objectweb.asm;


// Referenced classes of package org.codehaus.jackson.org.objectweb.asm:
//            ClassVisitor, ByteVector, Item, ClassReader, 
//            Type, FieldWriter, MethodWriter, AnnotationWriter, 
//            Attribute, AnnotationVisitor, FieldVisitor, MethodVisitor

public class ClassWriter
    implements ClassVisitor
{

    public static final int COMPUTE_FRAMES = 2;
    public static final int COMPUTE_MAXS = 1;
    static final byte a[];
    MethodWriter A;
    MethodWriter B;
    private short D;
    Item E[];
    String F;
    private final boolean G;
    private final boolean H;
    boolean I;
    ClassReader J;
    int b;
    int c;
    final ByteVector d;
    Item e[];
    int f;
    final Item g;
    final Item h;
    final Item i;
    private int j;
    private int k;
    private int l;
    private int m;
    private int n;
    private int o[];
    private int p;
    private ByteVector q;
    private int r;
    private int s;
    private AnnotationWriter t;
    private AnnotationWriter u;
    private Attribute v;
    private int w;
    private ByteVector x;
    FieldWriter y;
    FieldWriter z;

    public ClassWriter(int i1)
    {
        boolean flag = true;
        super();
        c = ((flag) ? 1 : 0);
        d = new ByteVector();
        e = new Item[256];
        f = (int)(0.75D * (double)e.length);
        g = new Item();
        h = new Item();
        i = new Item();
        boolean flag1;
        if ((i1 & 1) != 0)
        {
            flag1 = flag;
        } else
        {
            flag1 = false;
        }
        H = flag1;
        if ((i1 & 2) == 0)
        {
            flag = false;
        }
        G = flag;
    }

    public ClassWriter(ClassReader classreader, int i1)
    {
        this(i1);
        classreader.a(this);
        J = classreader;
    }

    private Item a(Item item)
    {
        Item item1;
        for (item1 = e[item.j % e.length]; item1 != null && (item1.b != item.b || !item.a(item1)); item1 = item1.k) { }
        return item1;
    }

    private void a(int i1, int j1, int k1)
    {
        d.b(i1, j1).putShort(k1);
    }

    private Item b(String s1)
    {
        h.a(8, s1, null, null);
        Item item = a(h);
        if (item == null)
        {
            d.b(8, newUTF8(s1));
            int i1 = c;
            c = i1 + 1;
            item = new Item(i1, h);
            b(item);
        }
        return item;
    }

    private void b(Item item)
    {
        if (c > f)
        {
            int j1 = e.length;
            int k1 = 1 + j1 * 2;
            Item aitem[] = new Item[k1];
            for (int l1 = j1 - 1; l1 >= 0; l1--)
            {
                Item item2;
                for (Item item1 = e[l1]; item1 != null; item1 = item2)
                {
                    int i2 = item1.j % aitem.length;
                    item2 = item1.k;
                    item1.k = aitem[i2];
                    aitem[i2] = item1;
                }

            }

            e = aitem;
            f = (int)(0.75D * (double)k1);
        }
        int i1 = item.j % e.length;
        item.k = e[i1];
        e[i1] = item;
    }

    private Item c(Item item)
    {
        D = (short)(1 + D);
        Item item1 = new Item(D, g);
        b(item1);
        if (E == null)
        {
            E = new Item[16];
        }
        if (D == E.length)
        {
            Item aitem[] = new Item[2 * E.length];
            System.arraycopy(E, 0, aitem, 0, E.length);
            E = aitem;
        }
        E[D] = item1;
        return item1;
    }

    int a(int i1, int j1)
    {
        h.b = 15;
        h.d = (long)i1 | (long)j1 << 32;
        h.j = 0x7fffffff & j1 + (i1 + 15);
        Item item = a(h);
        if (item == null)
        {
            String s1 = E[i1].g;
            String s2 = E[j1].g;
            h.c = c(getCommonSuperClass(s1, s2));
            item = new Item(0, h);
            b(item);
        }
        return item.c;
    }

    int a(String s1, int i1)
    {
        g.b = 14;
        g.c = i1;
        g.g = s1;
        g.j = 0x7fffffff & i1 + (14 + s1.hashCode());
        Item item = a(g);
        if (item == null)
        {
            item = c(g);
        }
        return item.a;
    }

    Item a(double d1)
    {
        g.a(d1);
        Item item = a(g);
        if (item == null)
        {
            d.putByte(6).putLong(g.d);
            item = new Item(c, g);
            b(item);
            c = 2 + c;
        }
        return item;
    }

    Item a(float f1)
    {
        g.a(f1);
        Item item = a(g);
        if (item == null)
        {
            d.putByte(4).putInt(g.c);
            int i1 = c;
            c = i1 + 1;
            item = new Item(i1, g);
            b(item);
        }
        return item;
    }

    Item a(int i1)
    {
        g.a(i1);
        Item item = a(g);
        if (item == null)
        {
            d.putByte(3).putInt(i1);
            int j1 = c;
            c = j1 + 1;
            item = new Item(j1, g);
            b(item);
        }
        return item;
    }

    Item a(long l1)
    {
        g.a(l1);
        Item item = a(g);
        if (item == null)
        {
            d.putByte(5).putLong(l1);
            item = new Item(c, g);
            b(item);
            c = 2 + c;
        }
        return item;
    }

    Item a(Object obj)
    {
        if (obj instanceof Integer)
        {
            return a(((Integer)obj).intValue());
        }
        if (obj instanceof Byte)
        {
            return a(((Byte)obj).intValue());
        }
        if (obj instanceof Character)
        {
            return a(((Character)obj).charValue());
        }
        if (obj instanceof Short)
        {
            return a(((Short)obj).intValue());
        }
        if (obj instanceof Boolean)
        {
            int i1;
            if (((Boolean)obj).booleanValue())
            {
                i1 = 1;
            } else
            {
                i1 = 0;
            }
            return a(i1);
        }
        if (obj instanceof Float)
        {
            return a(((Float)obj).floatValue());
        }
        if (obj instanceof Long)
        {
            return a(((Long)obj).longValue());
        }
        if (obj instanceof Double)
        {
            return a(((Double)obj).doubleValue());
        }
        if (obj instanceof String)
        {
            return b((String)obj);
        }
        if (obj instanceof Type)
        {
            Type type = (Type)obj;
            String s1;
            if (type.getSort() == 10)
            {
                s1 = type.getInternalName();
            } else
            {
                s1 = type.getDescriptor();
            }
            return a(s1);
        } else
        {
            throw new IllegalArgumentException("value " + obj);
        }
    }

    Item a(String s1)
    {
        h.a(7, s1, null, null);
        Item item = a(h);
        if (item == null)
        {
            d.b(7, newUTF8(s1));
            int i1 = c;
            c = i1 + 1;
            item = new Item(i1, h);
            b(item);
        }
        return item;
    }

    Item a(String s1, String s2)
    {
        h.a(12, s1, s2, null);
        Item item = a(h);
        if (item == null)
        {
            a(12, newUTF8(s1), newUTF8(s2));
            int i1 = c;
            c = i1 + 1;
            item = new Item(i1, h);
            b(item);
        }
        return item;
    }

    Item a(String s1, String s2, String s3)
    {
        i.a(9, s1, s2, s3);
        Item item = a(i);
        if (item == null)
        {
            a(9, newClass(s1), newNameType(s2, s3));
            int i1 = c;
            c = i1 + 1;
            item = new Item(i1, i);
            b(item);
        }
        return item;
    }

    Item a(String s1, String s2, String s3, boolean flag)
    {
        byte byte0;
        Item item;
        if (flag)
        {
            byte0 = 11;
        } else
        {
            byte0 = 10;
        }
        i.a(byte0, s1, s2, s3);
        item = a(i);
        if (item == null)
        {
            a(byte0, newClass(s1), newNameType(s2, s3));
            int i1 = c;
            c = i1 + 1;
            Item item1 = new Item(i1, i);
            b(item1);
            return item1;
        } else
        {
            return item;
        }
    }

    int c(String s1)
    {
        g.a(13, s1, null, null);
        Item item = a(g);
        if (item == null)
        {
            item = c(g);
        }
        return item.a;
    }

    protected String getCommonSuperClass(String s1, String s2)
    {
        Class class1;
        Class class2;
        try
        {
            class1 = Class.forName(s1.replace('/', '.'));
            class2 = Class.forName(s2.replace('/', '.'));
        }
        catch (Exception exception)
        {
            throw new RuntimeException(exception.toString());
        }
        if (class1.isAssignableFrom(class2))
        {
            return s1;
        }
        if (class2.isAssignableFrom(class1))
        {
            return s2;
        }
        if (class1.isInterface() || class2.isInterface())
        {
            return "java/lang/Object";
        }
        do
        {
            class1 = class1.getSuperclass();
        } while (!class1.isAssignableFrom(class2));
        return class1.getName().replace('.', '/');
    }

    public int newClass(String s1)
    {
        return a(s1).a;
    }

    public int newConst(Object obj)
    {
        return a(obj).a;
    }

    public int newField(String s1, String s2, String s3)
    {
        return a(s1, s2, s3).a;
    }

    public int newMethod(String s1, String s2, String s3, boolean flag)
    {
        return a(s1, s2, s3, flag).a;
    }

    public int newNameType(String s1, String s2)
    {
        return a(s1, s2).a;
    }

    public int newUTF8(String s1)
    {
        g.a(1, s1, null, null);
        Item item = a(g);
        if (item == null)
        {
            d.putByte(1).putUTF8(s1);
            int i1 = c;
            c = i1 + 1;
            item = new Item(i1, g);
            b(item);
        }
        return item.a;
    }

    public byte[] toByteArray()
    {
        int i1 = 24 + 2 * n;
        FieldWriter fieldwriter = y;
        int j1;
        int k3;
        for (j1 = 0; fieldwriter != null; j1 = k3)
        {
            k3 = j1 + 1;
            i1 += fieldwriter.a();
            fieldwriter = fieldwriter.a;
        }

        MethodWriter methodwriter = A;
        int k1;
        int j3;
        for (k1 = 0; methodwriter != null; k1 = j3)
        {
            j3 = k1 + 1;
            i1 += methodwriter.a();
            methodwriter = methodwriter.a;
        }

        int l1;
        int i2;
        ByteVector bytevector;
        int j2;
        if (l != 0)
        {
            l1 = 1;
            i1 += 8;
            newUTF8("Signature");
        } else
        {
            l1 = 0;
        }
        if (p != 0)
        {
            l1++;
            i1 += 8;
            newUTF8("SourceFile");
        }
        if (q != null)
        {
            l1++;
            i1 += 4 + q.b;
            newUTF8("SourceDebugExtension");
        }
        if (r != 0)
        {
            l1++;
            i1 += 10;
            newUTF8("EnclosingMethod");
        }
        if ((0x20000 & j) != 0)
        {
            l1++;
            i1 += 6;
            newUTF8("Deprecated");
        }
        if ((0x1000 & j) != 0 && ((0xffff & b) < 49 || (0x40000 & j) != 0))
        {
            l1++;
            i1 += 6;
            newUTF8("Synthetic");
        }
        if (x != null)
        {
            l1++;
            i1 += 8 + x.b;
            newUTF8("InnerClasses");
        }
        if (t != null)
        {
            l1++;
            i1 += 8 + t.a();
            newUTF8("RuntimeVisibleAnnotations");
        }
        if (u != null)
        {
            l1++;
            i1 += 8 + u.a();
            newUTF8("RuntimeInvisibleAnnotations");
        }
        i2 = i1;
        if (v != null)
        {
            int i3 = l1 + v.a();
            i2 += v.a(this, null, 0, -1, -1);
            l1 = i3;
        }
        bytevector = new ByteVector(i2 + d.b);
        bytevector.putInt(0xcafebabe).putInt(b);
        bytevector.putShort(c).putByteArray(d.a, 0, d.b);
        j2 = 0x60000 | (0x40000 & j) / 64;
        bytevector.putShort(j & ~j2).putShort(k).putShort(m);
        bytevector.putShort(n);
        for (int k2 = 0; k2 < n; k2++)
        {
            bytevector.putShort(o[k2]);
        }

        bytevector.putShort(j1);
        for (FieldWriter fieldwriter1 = y; fieldwriter1 != null; fieldwriter1 = fieldwriter1.a)
        {
            fieldwriter1.a(bytevector);
        }

        bytevector.putShort(k1);
        for (MethodWriter methodwriter1 = A; methodwriter1 != null; methodwriter1 = methodwriter1.a)
        {
            methodwriter1.a(bytevector);
        }

        bytevector.putShort(l1);
        if (l != 0)
        {
            bytevector.putShort(newUTF8("Signature")).putInt(2).putShort(l);
        }
        if (p != 0)
        {
            bytevector.putShort(newUTF8("SourceFile")).putInt(2).putShort(p);
        }
        if (q != null)
        {
            int l2 = -2 + q.b;
            bytevector.putShort(newUTF8("SourceDebugExtension")).putInt(l2);
            bytevector.putByteArray(q.a, 2, l2);
        }
        if (r != 0)
        {
            bytevector.putShort(newUTF8("EnclosingMethod")).putInt(4);
            bytevector.putShort(r).putShort(s);
        }
        if ((0x20000 & j) != 0)
        {
            bytevector.putShort(newUTF8("Deprecated")).putInt(0);
        }
        if ((0x1000 & j) != 0 && ((0xffff & b) < 49 || (0x40000 & j) != 0))
        {
            bytevector.putShort(newUTF8("Synthetic")).putInt(0);
        }
        if (x != null)
        {
            bytevector.putShort(newUTF8("InnerClasses"));
            bytevector.putInt(2 + x.b).putShort(w);
            bytevector.putByteArray(x.a, 0, x.b);
        }
        if (t != null)
        {
            bytevector.putShort(newUTF8("RuntimeVisibleAnnotations"));
            t.a(bytevector);
        }
        if (u != null)
        {
            bytevector.putShort(newUTF8("RuntimeInvisibleAnnotations"));
            u.a(bytevector);
        }
        if (v != null)
        {
            v.a(this, null, 0, -1, -1, bytevector);
        }
        if (I)
        {
            ClassWriter classwriter = new ClassWriter(2);
            (new ClassReader(bytevector.a)).accept(classwriter, 4);
            return classwriter.toByteArray();
        } else
        {
            return bytevector.a;
        }
    }

    public void visit(int i1, int j1, String s1, String s2, String s3, String as[])
    {
        int k1 = 0;
        b = i1;
        j = j1;
        k = newClass(s1);
        F = s1;
        if (s2 != null)
        {
            l = newUTF8(s2);
        }
        int l1;
        if (s3 == null)
        {
            l1 = 0;
        } else
        {
            l1 = newClass(s3);
        }
        m = l1;
        if (as != null && as.length > 0)
        {
            n = as.length;
            o = new int[n];
            for (; k1 < n; k1++)
            {
                o[k1] = newClass(as[k1]);
            }

        }
    }

    public AnnotationVisitor visitAnnotation(String s1, boolean flag)
    {
        ByteVector bytevector = new ByteVector();
        bytevector.putShort(newUTF8(s1)).putShort(0);
        AnnotationWriter annotationwriter = new AnnotationWriter(this, true, bytevector, bytevector, 2);
        if (flag)
        {
            annotationwriter.g = t;
            t = annotationwriter;
            return annotationwriter;
        } else
        {
            annotationwriter.g = u;
            u = annotationwriter;
            return annotationwriter;
        }
    }

    public void visitAttribute(Attribute attribute)
    {
        attribute.a = v;
        v = attribute;
    }

    public void visitEnd()
    {
    }

    public FieldVisitor visitField(int i1, String s1, String s2, String s3, Object obj)
    {
        return new FieldWriter(this, i1, s1, s2, s3, obj);
    }

    public void visitInnerClass(String s1, String s2, String s3, int i1)
    {
        if (x == null)
        {
            x = new ByteVector();
        }
        w = 1 + w;
        ByteVector bytevector = x;
        int j1;
        ByteVector bytevector1;
        int k1;
        ByteVector bytevector2;
        int l1;
        if (s1 == null)
        {
            j1 = 0;
        } else
        {
            j1 = newClass(s1);
        }
        bytevector.putShort(j1);
        bytevector1 = x;
        if (s2 == null)
        {
            k1 = 0;
        } else
        {
            k1 = newClass(s2);
        }
        bytevector1.putShort(k1);
        bytevector2 = x;
        l1 = 0;
        if (s3 != null)
        {
            l1 = newUTF8(s3);
        }
        bytevector2.putShort(l1);
        x.putShort(i1);
    }

    public MethodVisitor visitMethod(int i1, String s1, String s2, String s3, String as[])
    {
        return new MethodWriter(this, i1, s1, s2, s3, as, H, G);
    }

    public void visitOuterClass(String s1, String s2, String s3)
    {
        r = newClass(s1);
        if (s2 != null && s3 != null)
        {
            s = newNameType(s2, s3);
        }
    }

    public void visitSource(String s1, String s2)
    {
        if (s1 != null)
        {
            p = newUTF8(s1);
        }
        if (s2 != null)
        {
            q = (new ByteVector()).putUTF8(s2);
        }
    }

    static 
    {
        byte abyte0[] = new byte[220];
        for (int i1 = 0; i1 < abyte0.length; i1++)
        {
            abyte0[i1] = (byte)(-65 + "AAAAAAAAAAAAAAAABCKLLDDDDDEEEEEEEEEEEEEEEEEEEEAAAAAAAADDDDDEEEEEEEEEEEEEEEEEEEEAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAMAAAAAAAAAAAAAAAAAAAAIIIIIIIIIIIIIIIIDNOAAAAAAGGGGGGGHHFBFAAFFAAQPIIJJIIIIIIIIIIIIIIIIII".charAt(i1));
        }

        a = abyte0;
    }
}

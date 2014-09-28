// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.org.objectweb.asm;


// Referenced classes of package org.codehaus.jackson.org.objectweb.asm:
//            ClassWriter, ByteVector, ClassReader, Label

public class Attribute
{

    Attribute a;
    byte b[];
    public final String type;

    protected Attribute(String s)
    {
        type = s;
    }

    final int a()
    {
        int i = 0;
        for (; this != null; this = a)
        {
            i++;
        }

        return i;
    }

    final int a(ClassWriter classwriter, byte abyte0[], int i, int j, int k)
    {
        int l = 0;
        for (Attribute attribute = this; attribute != null;)
        {
            classwriter.newUTF8(attribute.type);
            int i1 = l + (6 + attribute.write(classwriter, abyte0, i, j, k).b);
            attribute = attribute.a;
            l = i1;
        }

        return l;
    }

    final void a(ClassWriter classwriter, byte abyte0[], int i, int j, int k, ByteVector bytevector)
    {
        for (Attribute attribute = this; attribute != null; attribute = attribute.a)
        {
            ByteVector bytevector1 = attribute.write(classwriter, abyte0, i, j, k);
            bytevector.putShort(classwriter.newUTF8(attribute.type)).putInt(bytevector1.b);
            bytevector.putByteArray(bytevector1.a, 0, bytevector1.b);
        }

    }

    protected Label[] getLabels()
    {
        return null;
    }

    public boolean isCodeAttribute()
    {
        return false;
    }

    public boolean isUnknown()
    {
        return true;
    }

    protected Attribute read(ClassReader classreader, int i, int j, char ac[], int k, Label alabel[])
    {
        Attribute attribute = new Attribute(type);
        attribute.b = new byte[j];
        System.arraycopy(classreader.b, i, attribute.b, 0, j);
        return attribute;
    }

    protected ByteVector write(ClassWriter classwriter, byte abyte0[], int i, int j, int k)
    {
        ByteVector bytevector = new ByteVector();
        bytevector.a = b;
        bytevector.b = b.length;
        return bytevector;
    }
}

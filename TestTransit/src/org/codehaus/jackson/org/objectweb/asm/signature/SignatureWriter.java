// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.org.objectweb.asm.signature;


// Referenced classes of package org.codehaus.jackson.org.objectweb.asm.signature:
//            SignatureVisitor

public class SignatureWriter
    implements SignatureVisitor
{

    private final StringBuffer a = new StringBuffer();
    private boolean b;
    private boolean c;
    private int d;

    public SignatureWriter()
    {
    }

    private void a()
    {
        if (b)
        {
            b = false;
            a.append('>');
        }
    }

    private void b()
    {
        if (d % 2 != 0)
        {
            a.append('>');
        }
        d = d / 2;
    }

    public String toString()
    {
        return a.toString();
    }

    public SignatureVisitor visitArrayType()
    {
        a.append('[');
        return this;
    }

    public void visitBaseType(char c1)
    {
        a.append(c1);
    }

    public SignatureVisitor visitClassBound()
    {
        return this;
    }

    public void visitClassType(String s)
    {
        a.append('L');
        a.append(s);
        d = 2 * d;
    }

    public void visitEnd()
    {
        b();
        a.append(';');
    }

    public SignatureVisitor visitExceptionType()
    {
        a.append('^');
        return this;
    }

    public void visitFormalTypeParameter(String s)
    {
        if (!b)
        {
            b = true;
            a.append('<');
        }
        a.append(s);
        a.append(':');
    }

    public void visitInnerClassType(String s)
    {
        b();
        a.append('.');
        a.append(s);
        d = 2 * d;
    }

    public SignatureVisitor visitInterface()
    {
        return this;
    }

    public SignatureVisitor visitInterfaceBound()
    {
        a.append(':');
        return this;
    }

    public SignatureVisitor visitParameterType()
    {
        a();
        if (!c)
        {
            c = true;
            a.append('(');
        }
        return this;
    }

    public SignatureVisitor visitReturnType()
    {
        a();
        if (!c)
        {
            a.append('(');
        }
        a.append(')');
        return this;
    }

    public SignatureVisitor visitSuperclass()
    {
        a();
        return this;
    }

    public SignatureVisitor visitTypeArgument(char c1)
    {
        if (d % 2 == 0)
        {
            d = 1 + d;
            a.append('<');
        }
        if (c1 != '=')
        {
            a.append(c1);
        }
        return this;
    }

    public void visitTypeArgument()
    {
        if (d % 2 == 0)
        {
            d = 1 + d;
            a.append('<');
        }
        a.append('*');
    }

    public void visitTypeVariable(String s)
    {
        a.append('T');
        a.append(s);
        a.append(';');
    }
}

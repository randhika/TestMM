// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.org.objectweb.asm.signature;


public interface SignatureVisitor
{

    public static final char EXTENDS = 43;
    public static final char INSTANCEOF = 61;
    public static final char SUPER = 45;

    public abstract SignatureVisitor visitArrayType();

    public abstract void visitBaseType(char c);

    public abstract SignatureVisitor visitClassBound();

    public abstract void visitClassType(String s);

    public abstract void visitEnd();

    public abstract SignatureVisitor visitExceptionType();

    public abstract void visitFormalTypeParameter(String s);

    public abstract void visitInnerClassType(String s);

    public abstract SignatureVisitor visitInterface();

    public abstract SignatureVisitor visitInterfaceBound();

    public abstract SignatureVisitor visitParameterType();

    public abstract SignatureVisitor visitReturnType();

    public abstract SignatureVisitor visitSuperclass();

    public abstract SignatureVisitor visitTypeArgument(char c);

    public abstract void visitTypeArgument();

    public abstract void visitTypeVariable(String s);
}

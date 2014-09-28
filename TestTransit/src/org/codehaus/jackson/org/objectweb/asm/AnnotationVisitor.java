// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.org.objectweb.asm;


public interface AnnotationVisitor
{

    public abstract void visit(String s, Object obj);

    public abstract AnnotationVisitor visitAnnotation(String s, String s1);

    public abstract AnnotationVisitor visitArray(String s);

    public abstract void visitEnd();

    public abstract void visitEnum(String s, String s1, String s2);
}

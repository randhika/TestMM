// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.org.objectweb.asm;


// Referenced classes of package org.codehaus.jackson.org.objectweb.asm:
//            AnnotationVisitor, Attribute, FieldVisitor, MethodVisitor

public interface ClassVisitor
{

    public abstract void visit(int i, int j, String s, String s1, String s2, String as[]);

    public abstract AnnotationVisitor visitAnnotation(String s, boolean flag);

    public abstract void visitAttribute(Attribute attribute);

    public abstract void visitEnd();

    public abstract FieldVisitor visitField(int i, String s, String s1, String s2, Object obj);

    public abstract void visitInnerClass(String s, String s1, String s2, int i);

    public abstract MethodVisitor visitMethod(int i, String s, String s1, String s2, String as[]);

    public abstract void visitOuterClass(String s, String s1, String s2);

    public abstract void visitSource(String s, String s1);
}

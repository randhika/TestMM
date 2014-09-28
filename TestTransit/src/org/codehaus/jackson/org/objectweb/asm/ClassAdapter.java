// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.org.objectweb.asm;


// Referenced classes of package org.codehaus.jackson.org.objectweb.asm:
//            ClassVisitor, AnnotationVisitor, Attribute, FieldVisitor, 
//            MethodVisitor

public class ClassAdapter
    implements ClassVisitor
{

    protected ClassVisitor cv;

    public ClassAdapter(ClassVisitor classvisitor)
    {
        cv = classvisitor;
    }

    public void visit(int i, int j, String s, String s1, String s2, String as[])
    {
        cv.visit(i, j, s, s1, s2, as);
    }

    public AnnotationVisitor visitAnnotation(String s, boolean flag)
    {
        return cv.visitAnnotation(s, flag);
    }

    public void visitAttribute(Attribute attribute)
    {
        cv.visitAttribute(attribute);
    }

    public void visitEnd()
    {
        cv.visitEnd();
    }

    public FieldVisitor visitField(int i, String s, String s1, String s2, Object obj)
    {
        return cv.visitField(i, s, s1, s2, obj);
    }

    public void visitInnerClass(String s, String s1, String s2, int i)
    {
        cv.visitInnerClass(s, s1, s2, i);
    }

    public MethodVisitor visitMethod(int i, String s, String s1, String s2, String as[])
    {
        return cv.visitMethod(i, s, s1, s2, as);
    }

    public void visitOuterClass(String s, String s1, String s2)
    {
        cv.visitOuterClass(s, s1, s2);
    }

    public void visitSource(String s, String s1)
    {
        cv.visitSource(s, s1);
    }
}

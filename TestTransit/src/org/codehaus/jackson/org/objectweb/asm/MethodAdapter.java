// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.org.objectweb.asm;


// Referenced classes of package org.codehaus.jackson.org.objectweb.asm:
//            MethodVisitor, AnnotationVisitor, Attribute, Label

public class MethodAdapter
    implements MethodVisitor
{

    protected MethodVisitor mv;

    public MethodAdapter(MethodVisitor methodvisitor)
    {
        mv = methodvisitor;
    }

    public AnnotationVisitor visitAnnotation(String s, boolean flag)
    {
        return mv.visitAnnotation(s, flag);
    }

    public AnnotationVisitor visitAnnotationDefault()
    {
        return mv.visitAnnotationDefault();
    }

    public void visitAttribute(Attribute attribute)
    {
        mv.visitAttribute(attribute);
    }

    public void visitCode()
    {
        mv.visitCode();
    }

    public void visitEnd()
    {
        mv.visitEnd();
    }

    public void visitFieldInsn(int i, String s, String s1, String s2)
    {
        mv.visitFieldInsn(i, s, s1, s2);
    }

    public void visitFrame(int i, int j, Object aobj[], int k, Object aobj1[])
    {
        mv.visitFrame(i, j, aobj, k, aobj1);
    }

    public void visitIincInsn(int i, int j)
    {
        mv.visitIincInsn(i, j);
    }

    public void visitInsn(int i)
    {
        mv.visitInsn(i);
    }

    public void visitIntInsn(int i, int j)
    {
        mv.visitIntInsn(i, j);
    }

    public void visitJumpInsn(int i, Label label)
    {
        mv.visitJumpInsn(i, label);
    }

    public void visitLabel(Label label)
    {
        mv.visitLabel(label);
    }

    public void visitLdcInsn(Object obj)
    {
        mv.visitLdcInsn(obj);
    }

    public void visitLineNumber(int i, Label label)
    {
        mv.visitLineNumber(i, label);
    }

    public void visitLocalVariable(String s, String s1, String s2, Label label, Label label1, int i)
    {
        mv.visitLocalVariable(s, s1, s2, label, label1, i);
    }

    public void visitLookupSwitchInsn(Label label, int ai[], Label alabel[])
    {
        mv.visitLookupSwitchInsn(label, ai, alabel);
    }

    public void visitMaxs(int i, int j)
    {
        mv.visitMaxs(i, j);
    }

    public void visitMethodInsn(int i, String s, String s1, String s2)
    {
        mv.visitMethodInsn(i, s, s1, s2);
    }

    public void visitMultiANewArrayInsn(String s, int i)
    {
        mv.visitMultiANewArrayInsn(s, i);
    }

    public AnnotationVisitor visitParameterAnnotation(int i, String s, boolean flag)
    {
        return mv.visitParameterAnnotation(i, s, flag);
    }

    public void visitTableSwitchInsn(int i, int j, Label label, Label alabel[])
    {
        mv.visitTableSwitchInsn(i, j, label, alabel);
    }

    public void visitTryCatchBlock(Label label, Label label1, Label label2, String s)
    {
        mv.visitTryCatchBlock(label, label1, label2, s);
    }

    public void visitTypeInsn(int i, String s)
    {
        mv.visitTypeInsn(i, s);
    }

    public void visitVarInsn(int i, int j)
    {
        mv.visitVarInsn(i, j);
    }
}

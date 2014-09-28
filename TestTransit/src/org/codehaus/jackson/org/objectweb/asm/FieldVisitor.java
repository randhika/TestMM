// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.org.objectweb.asm;


// Referenced classes of package org.codehaus.jackson.org.objectweb.asm:
//            AnnotationVisitor, Attribute

public interface FieldVisitor
{

    public abstract AnnotationVisitor visitAnnotation(String s, boolean flag);

    public abstract void visitAttribute(Attribute attribute);

    public abstract void visitEnd();
}

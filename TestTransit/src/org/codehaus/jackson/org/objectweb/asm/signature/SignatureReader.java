// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.org.objectweb.asm.signature;


// Referenced classes of package org.codehaus.jackson.org.objectweb.asm.signature:
//            SignatureVisitor

public class SignatureReader
{

    private final String a;

    public SignatureReader(String s)
    {
        a = s;
    }

    private static int a(String s, int i, SignatureVisitor signaturevisitor)
    {
        int j;
        char c;
        j = i + 1;
        c = s.charAt(i);
        c;
        JVM INSTR tableswitch 66 91: default 132
    //                   66 203
    //                   67 203
    //                   68 203
    //                   69 132
    //                   70 203
    //                   71 132
    //                   72 132
    //                   73 203
    //                   74 203
    //                   75 132
    //                   76 132
    //                   77 132
    //                   78 132
    //                   79 132
    //                   80 132
    //                   81 132
    //                   82 132
    //                   83 203
    //                   84 225
    //                   85 132
    //                   86 203
    //                   87 132
    //                   88 132
    //                   89 132
    //                   90 203
    //                   91 213;
           goto _L1 _L2 _L2 _L2 _L1 _L2 _L1 _L1 _L2 _L2 _L1 _L1 _L1 _L1 _L1 _L1 _L1 _L1 _L2 _L3 _L1 _L2 _L1 _L1 _L1 _L2 _L4
_L1:
        boolean flag;
        int l;
        int i1;
        boolean flag1;
        flag = false;
        l = j;
        i1 = j;
        flag1 = false;
_L9:
        int j1;
        char c1;
        j1 = i1 + 1;
        c1 = s.charAt(i1);
        c1;
        JVM INSTR lookupswitch 3: default 196
    //                   46: 252
    //                   59: 252
    //                   60: 326;
           goto _L5 _L6 _L6 _L7
_L5:
        i1 = j1;
        if (true) goto _L9; else goto _L8
_L8:
_L2:
        signaturevisitor.visitBaseType(c);
        return j;
_L4:
        return a(s, j, signaturevisitor.visitArrayType());
_L3:
        int k = s.indexOf(';', j);
        signaturevisitor.visitTypeVariable(s.substring(j, k));
        return k + 1;
_L6:
        if (!flag)
        {
            String s2 = s.substring(l, j1 - 1);
            if (flag1)
            {
                signaturevisitor.visitInnerClassType(s2);
            } else
            {
                signaturevisitor.visitClassType(s2);
            }
        }
        if (c1 == ';')
        {
            signaturevisitor.visitEnd();
            return j1;
        }
        flag1 = true;
        l = j1;
        i1 = j1;
        flag = false;
          goto _L9
_L7:
        int k1;
        char c2;
        String s1 = s.substring(l, j1 - 1);
        if (flag1)
        {
            signaturevisitor.visitInnerClassType(s1);
        } else
        {
            signaturevisitor.visitClassType(s1);
        }
        k1 = j1;
_L16:
        c2 = s.charAt(k1);
        c2;
        JVM INSTR lookupswitch 4: default 408
    //                   42: 448
    //                   43: 460
    //                   45: 460
    //                   62: 438;
           goto _L10 _L11 _L12 _L12 _L13
_L10:
        k1 = a(s, k1, signaturevisitor.visitTypeArgument('='));
          goto _L14
_L13:
        i1 = k1;
        flag = true;
          goto _L15
_L11:
        k1++;
        signaturevisitor.visitTypeArgument();
          goto _L14
_L12:
        k1 = a(s, k1 + 1, signaturevisitor.visitTypeArgument(c2));
_L14:
        if (true) goto _L16; else goto _L15
_L15:
        if (true) goto _L9; else goto _L17
_L17:
    }

    public void accept(SignatureVisitor signaturevisitor)
    {
        String s = a;
        int i = s.length();
        char c = s.charAt(0);
        int j = 0;
        if (c == '<')
        {
            j = 2;
            do
            {
                int j1 = s.indexOf(':', j);
                signaturevisitor.visitFormalTypeParameter(s.substring(j - 1, j1));
                int k1 = j1 + 1;
                char c1 = s.charAt(k1);
                int k;
                int l;
                int i1;
                int l1;
                char c2;
                if (c1 == 'L' || c1 == '[' || c1 == 'T')
                {
                    l1 = a(s, k1, signaturevisitor.visitClassBound());
                } else
                {
                    l1 = k1;
                }
                do
                {
                    j = l1 + 1;
                    c2 = s.charAt(l1);
                    if (c2 != ':')
                    {
                        break;
                    }
                    l1 = a(s, j, signaturevisitor.visitInterfaceBound());
                } while (true);
            } while (c2 != '>');
        }
        if (s.charAt(j) == '(')
        {
            for (l = j + 1; s.charAt(l) != ')'; l = a(s, l, signaturevisitor.visitParameterType())) { }
            for (i1 = a(s, l + 1, signaturevisitor.visitReturnType()); i1 < i; i1 = a(s, i1 + 1, signaturevisitor.visitExceptionType())) { }
        } else
        {
            for (k = a(s, j, signaturevisitor.visitSuperclass()); k < i; k = a(s, k, signaturevisitor.visitInterface())) { }
        }
    }

    public void acceptType(SignatureVisitor signaturevisitor)
    {
        a(a, 0, signaturevisitor);
    }
}

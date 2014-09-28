// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.sym;


// Referenced classes of package org.codehaus.jackson.sym:
//            CharsToNameCanonicalizer

static final class mNext
{

    private final String _symbol;
    private final _symbol mNext;

    public String find(char ac[], int i, int j)
    {
        String s;
        mNext mnext;
        s = _symbol;
        mnext = mNext;
_L2:
        if (s.length() != j)
        {
            break MISSING_BLOCK_LABEL_61;
        }
        break MISSING_BLOCK_LABEL_21;
        for (int k = 0; s.charAt(k) == ac[i + k] && ++k < j;) { }
        if (k == j)
        {
            return s;
        }
        break MISSING_BLOCK_LABEL_61;
        if (mnext == null)
        {
            return null;
        }
        s = mnext.getSymbol();
        mnext = mnext.getNext();
        if (true) goto _L2; else goto _L1
_L1:
    }

    public getNext getNext()
    {
        return mNext;
    }

    public String getSymbol()
    {
        return _symbol;
    }

    public (String s,  )
    {
        _symbol = s;
        mNext = ;
    }
}

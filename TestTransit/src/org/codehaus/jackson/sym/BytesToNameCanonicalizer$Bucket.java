// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.sym;


// Referenced classes of package org.codehaus.jackson.sym:
//            Name, BytesToNameCanonicalizer

static final class _next
{

    protected final Name _name;
    protected final _next _next;

    public Name find(int i, int j, int k)
    {
        if (_name.hashCode() != i || !_name.equals(j, k)) goto _L2; else goto _L1
_L1:
        Name name = _name;
_L4:
        return name;
_L2:
        _next _lnext = _next;
label0:
        do
        {
label1:
            {
                if (_lnext == null)
                {
                    break label1;
                }
                name = _lnext._name;
                if (name.hashCode() == i && name.equals(j, k))
                {
                    break label0;
                }
                _lnext = _lnext._next;
            }
        } while (true);
        if (true) goto _L4; else goto _L3
_L3:
        return null;
    }

    public Name find(int i, int ai[], int j)
    {
        if (_name.hashCode() != i || !_name.equals(ai, j)) goto _L2; else goto _L1
_L1:
        Name name = _name;
_L4:
        return name;
_L2:
        _next _lnext = _next;
label0:
        do
        {
label1:
            {
                if (_lnext == null)
                {
                    break label1;
                }
                name = _lnext._name;
                if (name.hashCode() == i && name.equals(ai, j))
                {
                    break label0;
                }
                _lnext = _lnext._next;
            }
        } while (true);
        if (true) goto _L4; else goto _L3
_L3:
        return null;
    }

    public int length()
    {
        int i = 1;
        for (_next _lnext = _next; _lnext != null; _lnext = _lnext._next)
        {
            i++;
        }

        return i;
    }

    (Name name,  )
    {
        _name = name;
        _next = ;
    }
}

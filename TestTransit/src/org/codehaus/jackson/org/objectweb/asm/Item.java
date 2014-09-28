// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.org.objectweb.asm;


final class Item
{

    int a;
    int b;
    int c;
    long d;
    String g;
    String h;
    String i;
    int j;
    Item k;

    Item()
    {
    }

    Item(int l)
    {
        a = l;
    }

    Item(int l, Item item)
    {
        a = l;
        b = item.b;
        c = item.c;
        d = item.d;
        g = item.g;
        h = item.h;
        i = item.i;
        j = item.j;
    }

    void a(double d1)
    {
        b = 6;
        d = Double.doubleToRawLongBits(d1);
        j = 0x7fffffff & b + (int)d1;
    }

    void a(float f)
    {
        b = 4;
        c = Float.floatToRawIntBits(f);
        j = 0x7fffffff & b + (int)f;
    }

    void a(int l)
    {
        b = 3;
        c = l;
        j = 0x7fffffff & l + b;
    }

    void a(int l, String s, String s1, String s2)
    {
        b = l;
        g = s;
        h = s1;
        i = s2;
        switch (l)
        {
        default:
            j = 0x7fffffff & l + s.hashCode() * s1.hashCode() * s2.hashCode();
            return;

        case 1: // '\001'
        case 7: // '\007'
        case 8: // '\b'
        case 13: // '\r'
            j = 0x7fffffff & l + s.hashCode();
            return;

        case 12: // '\f'
            j = 0x7fffffff & l + s.hashCode() * s1.hashCode();
            break;
        }
    }

    void a(long l)
    {
        b = 5;
        d = l;
        j = 0x7fffffff & b + (int)l;
    }

    boolean a(Item item)
    {
        b;
        JVM INSTR tableswitch 1 15: default 80
    //                   1 124
    //                   2 80
    //                   3 150
    //                   4 150
    //                   5 136
    //                   6 136
    //                   7 124
    //                   8 124
    //                   9 80
    //                   10 80
    //                   11 80
    //                   12 190
    //                   13 124
    //                   14 163
    //                   15 136;
           goto _L1 _L2 _L1 _L3 _L3 _L4 _L4 _L2 _L2 _L1 _L1 _L1 _L5 _L2 _L6 _L4
_L1:
        if (!item.g.equals(g) || !item.h.equals(h) || !item.i.equals(i)) goto _L8; else goto _L7
_L7:
        return true;
_L2:
        return item.g.equals(g);
_L4:
        if (item.d != d)
        {
            return false;
        }
        continue; /* Loop/switch isn't completed */
_L3:
        if (item.c != c)
        {
            return false;
        }
        continue; /* Loop/switch isn't completed */
_L6:
        if (item.c != c || !item.g.equals(g))
        {
            return false;
        }
        continue; /* Loop/switch isn't completed */
_L5:
        if (!item.g.equals(g) || !item.h.equals(h))
        {
            return false;
        }
        if (true) goto _L7; else goto _L8
_L8:
        return false;
    }
}

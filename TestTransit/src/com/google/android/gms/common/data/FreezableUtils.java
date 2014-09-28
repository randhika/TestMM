// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.common.data;

import java.util.ArrayList;
import java.util.Iterator;

// Referenced classes of package com.google.android.gms.common.data:
//            Freezable

public final class FreezableUtils
{

    public FreezableUtils()
    {
    }

    public static ArrayList freeze(ArrayList arraylist)
    {
        ArrayList arraylist1 = new ArrayList(arraylist.size());
        int i = arraylist.size();
        for (int j = 0; j < i; j++)
        {
            arraylist1.add(((Freezable)arraylist.get(j)).freeze());
        }

        return arraylist1;
    }

    public static ArrayList freeze(Freezable afreezable[])
    {
        ArrayList arraylist = new ArrayList(afreezable.length);
        for (int i = 0; i < afreezable.length; i++)
        {
            arraylist.add(afreezable[i].freeze());
        }

        return arraylist;
    }

    public static ArrayList freezeIterable(Iterable iterable)
    {
        ArrayList arraylist = new ArrayList();
        for (Iterator iterator = iterable.iterator(); iterator.hasNext(); arraylist.add(((Freezable)iterator.next()).freeze())) { }
        return arraylist;
    }
}

// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.common.hamburger;

import java.util.ArrayList;
import java.util.Iterator;

// Referenced classes of package jp.co.yahoo.android.common.hamburger:
//            YHBGRecommendApps

public static class mEntries
{

    ArrayList mEntries;
    public String mLastModified;

    void addEntry(mEntries mentries)
    {
        mEntries.add(mentries);
    }

    void clear()
    {
        mLastModified = null;
        mEntries.clear();
    }

    mEntries get(int i)
    {
        return (mEntries)mEntries.get(i);
    }

    Iterator getEntries()
    {
        return mEntries.iterator();
    }

    public String toString()
    {
        StringBuilder stringbuilder = new StringBuilder(512);
        stringbuilder.append((new StringBuilder("LastModified = ")).append(mLastModified).append("\n").toString());
        stringbuilder.append("YHamburgerEntry:\n");
        Iterator iterator = getEntries();
        do
        {
            if (!iterator.hasNext())
            {
                return stringbuilder.toString();
            }
            stringbuilder.append(((getEntries)iterator.next()).toString());
        } while (true);
    }

    public ()
    {
        mEntries = new ArrayList();
    }
}

// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.common;

import android.util.SparseIntArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public abstract class SectionListBaseAdapter extends BaseAdapter
    implements SectionListView.SectionListAdapter
{

    private static int HEADER_VIEW_TYPE = 0;
    private static int ITEM_VIEW_TYPE = 0;
    private int count;
    private SparseIntArray positionSectionCache;
    private SparseIntArray sectionCache;
    private int sectionCount;
    private SparseIntArray sectionCountCache;
    private SparseIntArray sectionPositionCache;

    public SectionListBaseAdapter()
    {
        sectionCache = new SparseIntArray();
        sectionPositionCache = new SparseIntArray();
        sectionCountCache = new SparseIntArray();
        positionSectionCache = new SparseIntArray();
        count = -1;
        sectionCount = -1;
    }

    private int internalGetCountForSection(int i)
    {
        int j = sectionCountCache.get(i, -1);
        if (j != -1)
        {
            return j;
        } else
        {
            int k = getCountForSection(i);
            sectionCountCache.put(i, k);
            return k;
        }
    }

    private int internalGetSectionCount()
    {
        if (sectionCount >= 0)
        {
            return sectionCount;
        } else
        {
            sectionCount = getSectionCount();
            return sectionCount;
        }
    }

    public final int getCount()
    {
        if (count >= 0)
        {
            return count;
        }
        int i = 0;
        for (int j = 0; j < internalGetSectionCount(); j++)
        {
            i = 1 + (i + internalGetCountForSection(j));
        }

        count = i;
        return i;
    }

    public abstract int getCountForSection(int i);

    public final Object getItem(int i)
    {
        return getItem(getSectionForPosition(i), getPositionInSectionForPosition(i));
    }

    public abstract Object getItem(int i, int j);

    public final long getItemId(int i)
    {
        return getItemId(getSectionForPosition(i), getPositionInSectionForPosition(i));
    }

    public abstract long getItemId(int i, int j);

    public abstract View getItemView(int i, int j, View view, ViewGroup viewgroup);

    public final int getItemViewType(int i)
    {
        if (isSectionHeader(i))
        {
            return getItemViewTypeCount() + getSectionHeaderViewType(getSectionForPosition(i));
        } else
        {
            return getItemViewType(getSectionForPosition(i), getPositionInSectionForPosition(i));
        }
    }

    public int getItemViewType(int i, int j)
    {
        return ITEM_VIEW_TYPE;
    }

    public int getItemViewTypeCount()
    {
        return 1;
    }

    public int getPositionInSectionForPosition(int i)
    {
        int j = sectionPositionCache.get(i, -1);
        if (j != -1)
        {
            return j;
        }
        int k = 0;
        for (int l = 0; l < internalGetSectionCount(); l++)
        {
            int i1 = 1 + (k + internalGetCountForSection(l));
            if (i >= k && i < i1)
            {
                int j1 = -1 + (i - k);
                sectionPositionCache.put(i, j1);
                return j1;
            }
            k = i1;
        }

        return 0;
    }

    public final int getPositionOfSection(int i)
    {
        int j = positionSectionCache.get(i, -1);
        if (j != -1)
        {
            return j;
        }
        int k = 0;
        for (int l = 0; l < internalGetSectionCount() && l < i; l++)
        {
            k = k + 1 + internalGetCountForSection(l);
        }

        positionSectionCache.put(i, k);
        return k;
    }

    public abstract int getSectionCount();

    public final int getSectionForPosition(int i)
    {
        int j = sectionCache.get(i, -1);
        if (j != -1)
        {
            return j;
        }
        int k = 0;
        for (int l = 0; l < internalGetSectionCount(); l++)
        {
            int i1 = 1 + (k + internalGetCountForSection(l));
            if (i >= k && i < i1)
            {
                sectionCache.put(i, l);
                return l;
            }
            k = i1;
        }

        return 0;
    }

    public abstract View getSectionHeaderView(int i, View view, ViewGroup viewgroup);

    public int getSectionHeaderViewType(int i)
    {
        return HEADER_VIEW_TYPE;
    }

    public int getSectionHeaderViewTypeCount()
    {
        return 1;
    }

    public final View getView(int i, View view, ViewGroup viewgroup)
    {
        if (isSectionHeader(i))
        {
            return getSectionHeaderView(getSectionForPosition(i), view, viewgroup);
        } else
        {
            return getItemView(getSectionForPosition(i), getPositionInSectionForPosition(i), view, viewgroup);
        }
    }

    public final int getViewTypeCount()
    {
        return getItemViewTypeCount() + getSectionHeaderViewTypeCount();
    }

    public final boolean isSectionHeader(int i)
    {
        int j;
        int k;
        j = 0;
        k = 0;
_L7:
        int l;
        boolean flag;
        l = internalGetSectionCount();
        flag = false;
        if (k >= l) goto _L2; else goto _L1
_L1:
        if (i != j) goto _L4; else goto _L3
_L3:
        flag = true;
_L2:
        return flag;
_L4:
        flag = false;
        if (i < j) goto _L2; else goto _L5
_L5:
        j += 1 + internalGetCountForSection(k);
        k++;
        if (true) goto _L7; else goto _L6
_L6:
    }

    public void notifyDataSetChanged()
    {
        sectionCache.clear();
        sectionPositionCache.clear();
        sectionCountCache.clear();
        positionSectionCache.clear();
        count = -1;
        sectionCount = -1;
        super.notifyDataSetChanged();
    }

    public void notifyDataSetInvalidated()
    {
        sectionCache.clear();
        sectionPositionCache.clear();
        sectionCountCache.clear();
        positionSectionCache.clear();
        count = -1;
        sectionCount = -1;
        super.notifyDataSetInvalidated();
    }

}

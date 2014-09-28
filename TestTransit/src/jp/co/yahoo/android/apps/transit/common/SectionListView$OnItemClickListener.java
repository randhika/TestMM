// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.common;

import android.view.View;
import android.widget.AdapterView;
import android.widget.HeaderViewListAdapter;

// Referenced classes of package jp.co.yahoo.android.apps.transit.common:
//            SectionListBaseAdapter, SectionListView

public static abstract class Q
    implements android.widget.View.OnItemClickListener
{

    public abstract void onItemClick(AdapterView adapterview, View view, int i, int j, long l);

    public void onItemClick(AdapterView adapterview, View view, int i, long l)
    {
        SectionListBaseAdapter sectionlistbaseadapter;
        int j;
        int k;
        if (adapterview.getAdapter().getClass().equals(android/widget/HeaderViewListAdapter))
        {
            sectionlistbaseadapter = (SectionListBaseAdapter)((HeaderViewListAdapter)adapterview.getAdapter()).getWrappedAdapter();
        } else
        {
            sectionlistbaseadapter = (SectionListBaseAdapter)adapterview.getAdapter();
        }
        j = sectionlistbaseadapter.getSectionForPosition(i);
        k = sectionlistbaseadapter.getPositionInSectionForPosition(i);
        if (k == -1)
        {
            onSectionClick(adapterview, view, j, l);
            return;
        } else
        {
            onItemClick(adapterview, view, j, k, l);
            return;
        }
    }

    public abstract void onSectionClick(AdapterView adapterview, View view, int i, long l);

    public Q()
    {
    }
}

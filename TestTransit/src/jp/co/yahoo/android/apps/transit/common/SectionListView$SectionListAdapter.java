// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.common;

import android.view.View;
import android.view.ViewGroup;

// Referenced classes of package jp.co.yahoo.android.apps.transit.common:
//            SectionListView

public static interface 
{

    public abstract int getCount();

    public abstract int getItemViewType(int i);

    public abstract int getPositionOfSection(int i);

    public abstract int getSectionForPosition(int i);

    public abstract View getSectionHeaderView(int i, View view, ViewGroup viewgroup);

    public abstract int getSectionHeaderViewType(int i);

    public abstract boolean isSectionHeader(int i);
}

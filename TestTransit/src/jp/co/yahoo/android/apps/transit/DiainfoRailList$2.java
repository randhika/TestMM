// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit;

import android.view.View;
import android.widget.ExpandableListView;
import jp.co.yahoo.android.apps.transit.api.data.DiainfoData;
import jp.co.yahoo.android.apps.transit.viewparts.DiainfoExpandableListAdapter;

// Referenced classes of package jp.co.yahoo.android.apps.transit:
//            DiainfoRailList

class this._cls0
    implements android.widget..OnChildClickListener
{

    final DiainfoRailList this$0;

    public boolean onChildClick(ExpandableListView expandablelistview, View view, int i, int j, long l)
    {
        DiainfoData diainfodata = (DiainfoData)((DiainfoExpandableListAdapter)expandablelistview.getExpandableListAdapter()).getChild(i, j);
        if (isRegist)
        {
            postRegist(diainfodata);
        } else
        {
            lounchDiainfoDetail(diainfodata);
        }
        return false;
    }

    ta()
    {
        this$0 = DiainfoRailList.this;
        super();
    }
}

// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.internal.widget;

import android.view.View;
import android.widget.AdapterView;

// Referenced classes of package android.support.v7.internal.widget:
//            ListPopupWindow

class this._cls0
    implements android.widget.SelectedListener
{

    final ListPopupWindow this$0;

    public void onItemSelected(AdapterView adapterview, View view, int i, long l)
    {
        if (i != -1)
        {
            opDownListView opdownlistview = ListPopupWindow.access$700(ListPopupWindow.this);
            if (opdownlistview != null)
            {
                opDownListView.access._mth502(opdownlistview, false);
            }
        }
    }

    public void onNothingSelected(AdapterView adapterview)
    {
    }

    opDownListView()
    {
        this$0 = ListPopupWindow.this;
        super();
    }
}

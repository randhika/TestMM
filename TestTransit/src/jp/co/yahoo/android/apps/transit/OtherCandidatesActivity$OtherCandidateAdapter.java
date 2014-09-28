// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
import jp.co.yahoo.android.apps.transit.common.util.TransitUtil;

// Referenced classes of package jp.co.yahoo.android.apps.transit:
//            OtherCandidatesActivity

public class inflater extends ArrayAdapter
{

    private LayoutInflater inflater;
    private List items;
    final OtherCandidatesActivity this$0;

    private View getViewItem(int i)
    {
        jp.co.yahoo.android.apps.transit.api.data.ndidatesh ndidatesh = (jp.co.yahoo.android.apps.transit.api.data.ndidatesh)items.get(i);
        String s = ndidatesh.ndidatesh;
        String s1 = ndidatesh.ndidatesh;
        View view;
        TextView textview;
        if (TransitUtil.isEmpty(s1))
        {
            view = inflater.inflate(0x7f030077, null);
            textview = (TextView)view;
        } else
        {
            view = inflater.inflate(0x7f030083, null);
            ((TextView)view.findViewById(0x7f0a0281)).setText(s1);
            textview = (TextView)view.findViewById(0x7f0a0063);
        }
        textview.setText(s);
        return view;
    }

    public View getDropDownView(int i, View view, ViewGroup viewgroup)
    {
        return getViewItem(i);
    }

    public View getView(int i, View view, ViewGroup viewgroup)
    {
        View view1 = getViewItem(i);
        view1.setBackgroundColor(getResources().getColor(0x7f09006e));
        return view1;
    }

    public (Context context, int i, ArrayList arraylist)
    {
        this$0 = OtherCandidatesActivity.this;
        super(context, i, arraylist);
        items = arraylist;
        inflater = (LayoutInflater)context.getSystemService("layout_inflater");
    }
}

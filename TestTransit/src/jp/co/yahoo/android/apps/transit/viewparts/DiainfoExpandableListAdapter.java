// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.viewparts;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;
import java.util.ArrayList;
import jp.co.yahoo.android.apps.transit.api.data.DiainfoData;

public class DiainfoExpandableListAdapter extends BaseExpandableListAdapter
{

    private ArrayList children;
    private Context context;
    private ArrayList groups;
    private LayoutInflater inflater;
    private boolean isDiainfo;
    private int padding;

    public DiainfoExpandableListAdapter(Context context1, ArrayList arraylist, ArrayList arraylist1)
    {
        isDiainfo = true;
        context = context1;
        groups = arraylist;
        children = arraylist1;
        padding = context1.getResources().getDimensionPixelSize(0x7f0b0025);
        inflater = (LayoutInflater)context.getSystemService("layout_inflater");
    }

    private TextView getSimpleView(Boolean boolean1)
    {
        TextView textview = (TextView)inflater.inflate(0x7f030077, null);
        if (boolean1.booleanValue())
        {
            textview.setTextAppearance(context, 0x1030044);
            textview.setPadding(padding, 0, 0, 0);
            return textview;
        } else
        {
            textview.setPadding(padding, 0, 0, 0);
            return textview;
        }
    }

    public Object getChild(int i, int j)
    {
        return ((Bundle)children.get(i)).getSerializable(String.valueOf(j));
    }

    public long getChildId(int i, int j)
    {
        return (long)j;
    }

    public View getChildView(int i, int j, boolean flag, View view, ViewGroup viewgroup)
    {
        DiainfoData diainfodata = (DiainfoData)((Bundle)children.get(i)).getSerializable(String.valueOf(j));
        View view1 = inflater.inflate(0x7f03006e, null);
        TextView textview = (TextView)view1.findViewById(0x7f0a021d);
        textview.setPadding(padding, 0, 0, 0);
        textview.setText(diainfodata.getRailName());
        view1.setClickable(false);
        if (diainfodata.isDetail() && isDiainfo)
        {
            view1.findViewById(0x7f0a021e).setVisibility(0);
        }
        return view1;
    }

    public int getChildrenCount(int i)
    {
        return ((Bundle)children.get(i)).size();
    }

    public Object getGroup(int i)
    {
        return groups.get(i);
    }

    public int getGroupCount()
    {
        return groups.size();
    }

    public long getGroupId(int i)
    {
        return (long)i;
    }

    public View getGroupView(int i, boolean flag, View view, ViewGroup viewgroup)
    {
        View view1;
        view1 = inflater.inflate(0x7f03006e, null);
        TextView textview = (TextView)view1.findViewById(0x7f0a021d);
        textview.setPadding(padding, 0, 0, 0);
        textview.setText(getGroup(i).toString());
        view1.setClickable(false);
        if (!isDiainfo) goto _L2; else goto _L1
_L1:
        int j;
        Bundle bundle;
        j = 0;
        bundle = (Bundle)children.get(i);
_L7:
        if (!bundle.containsKey(Integer.toString(j))) goto _L2; else goto _L3
_L3:
        if (!((DiainfoData)((Bundle)children.get(i)).getSerializable(String.valueOf(j))).isDetail()) goto _L5; else goto _L4
_L4:
        view1.findViewById(0x7f0a021e).setVisibility(0);
_L2:
        return view1;
_L5:
        j++;
        if (true) goto _L7; else goto _L6
_L6:
    }

    public boolean hasStableIds()
    {
        return true;
    }

    public boolean isChildSelectable(int i, int j)
    {
        return true;
    }

    public void setDiainfoIcon(boolean flag)
    {
        isDiainfo = flag;
    }
}

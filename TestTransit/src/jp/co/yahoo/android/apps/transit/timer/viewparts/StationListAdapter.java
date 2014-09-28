// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.timer.viewparts;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;
import jp.co.yahoo.android.apps.transit.timer.api.data.StationData;

public class StationListAdapter extends ArrayAdapter
{

    private LayoutInflater mInflater;
    private Resources res;

    public StationListAdapter(Context context, List list)
    {
        super(context, 0, list);
        mInflater = (LayoutInflater)context.getSystemService("layout_inflater");
        res = context.getResources();
    }

    public View getView(int i, View view, ViewGroup viewgroup)
    {
        StationData stationdata = (StationData)getItem(i);
        if (view == null)
        {
            view = mInflater.inflate(0x7f03007c, null);
        }
        ((TextView)view.findViewById(0x7f0a0270)).setText(stationdata.getName());
        ((TextView)view.findViewById(0x7f0a0271)).setText((new StringBuilder()).append(stationdata.getRailname()).append(" ").append(stationdata.getDirname()).append(res.getString(0x7f0d0275)).toString());
        ImageView imageview = (ImageView)view.findViewById(0x7f0a0273);
        if (stationdata.getSettingType() == res.getInteger(0x7f0c0073))
        {
            imageview.setImageResource(0x7f0201c8);
            imageview.setContentDescription(res.getString(0x7f0d0316));
        } else
        {
            imageview.setImageResource(0x7f0201c7);
            imageview.setContentDescription(res.getString(0x7f0d028b));
        }
        view.setTag(stationdata);
        return view;
    }
}

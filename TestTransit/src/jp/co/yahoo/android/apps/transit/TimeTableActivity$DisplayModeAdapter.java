// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

// Referenced classes of package jp.co.yahoo.android.apps.transit:
//            TimeTableActivity

private class inflater extends ArrayAdapter
{

    private LayoutInflater inflater;
    final TimeTableActivity this$0;

    public View getDropDownView(int i, View view, ViewGroup viewgroup)
    {
        LinearLayout linearlayout;
        ImageView imageview;
        TextView textview;
        if (view == null)
        {
            linearlayout = (LinearLayout)inflater.inflate(0x7f030072, null);
        } else
        {
            linearlayout = (LinearLayout)view;
        }
        imageview = (ImageView)linearlayout.findViewById(0x7f0a0221);
        textview = (TextView)linearlayout.findViewById(0x7f0a0222);
        textview.setTextAppearance(TimeTableActivity.this, 0x7f0e00b8);
        if (i == 0)
        {
            imageview.setImageResource(0x7f020116);
            textview.setText(0x7f0d0514);
            return linearlayout;
        } else
        {
            imageview.setImageResource(0x7f02011f);
            textview.setText(0x7f0d0515);
            return linearlayout;
        }
    }

    public View getView(int i, View view, ViewGroup viewgroup)
    {
        ImageView imageview;
        if (view == null)
        {
            imageview = new ImageView(getContext());
        } else
        {
            imageview = (ImageView)view;
        }
        if (i == 0)
        {
            imageview.setImageResource(0x7f020116);
            return imageview;
        } else
        {
            imageview.setImageResource(0x7f02011f);
            return imageview;
        }
    }

    public (Context context, int i, Integer ainteger[])
    {
        this$0 = TimeTableActivity.this;
        super(context, i, ainteger);
        inflater = (LayoutInflater)context.getSystemService("layout_inflater");
    }
}

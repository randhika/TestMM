// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.viewparts;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;
import jp.co.yahoo.android.apps.transit.common.util.TransitUtil;

public class ListItemArrow extends LinearLayout
{

    public ListItemArrow(Context context)
    {
        this(context, null);
    }

    public ListItemArrow(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        setOrientation(1);
        LayoutInflater layoutinflater = (LayoutInflater)context.getSystemService("layout_inflater");
        TypedArray typedarray = context.obtainStyledAttributes(attributeset, jp.co.yahoo.android.apps.transit.R.styleable.custom_parts, 0, 0);
        layoutinflater.inflate(0x7f030066, this, true);
        String s = typedarray.getString(0);
        TextView textview = (TextView)findViewById(0x7f0a004d);
        if (!TransitUtil.isEmpty(s))
        {
            textview.setText(s);
            return;
        } else
        {
            textview.setVisibility(8);
            return;
        }
    }
}

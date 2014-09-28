// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.viewparts;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import jp.co.yahoo.android.apps.transit.common.util.TransitUtil;

public class ListItemImageText extends LinearLayout
{

    public ListItemImageText(Context context)
    {
        this(context, null);
    }

    public ListItemImageText(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        setOrientation(1);
        LayoutInflater layoutinflater = (LayoutInflater)context.getSystemService("layout_inflater");
        TypedArray typedarray = context.obtainStyledAttributes(attributeset, jp.co.yahoo.android.apps.transit.R.styleable.custom_parts, 0, 0);
        layoutinflater.inflate(0x7f030072, this, true);
        String s = typedarray.getString(0);
        TextView textview = (TextView)findViewById(0x7f0a0222);
        int i;
        int j;
        ImageView imageview;
        int k;
        ImageView imageview1;
        if (!TransitUtil.isEmpty(s))
        {
            textview.setText(s);
        } else
        {
            textview.setVisibility(8);
        }
        i = typedarray.getResourceId(5, 0);
        if (i != 0)
        {
            textview.setText(i);
        }
        j = typedarray.getResourceId(3, 0);
        imageview = (ImageView)findViewById(0x7f0a0221);
        if (j != 0)
        {
            imageview.setImageResource(j);
        } else
        {
            imageview.setVisibility(8);
        }
        k = typedarray.getResourceId(4, 0);
        imageview1 = (ImageView)findViewById(0x7f0a0223);
        if (j != 0)
        {
            imageview1.setImageResource(k);
            return;
        } else
        {
            imageview1.setVisibility(8);
            return;
        }
    }
}

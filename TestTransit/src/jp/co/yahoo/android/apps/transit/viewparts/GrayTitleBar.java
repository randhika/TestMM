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

public class GrayTitleBar extends LinearLayout
{

    private static int TITLE_TYPE_01 = 1;
    private static int TITLE_TYPE_02 = 2;
    private static int TITLE_TYPE_03 = 3;

    public GrayTitleBar(Context context)
    {
        this(context, null);
    }

    public GrayTitleBar(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        setOrientation(1);
        LayoutInflater layoutinflater = (LayoutInflater)context.getSystemService("layout_inflater");
        TypedArray typedarray = context.obtainStyledAttributes(attributeset, jp.co.yahoo.android.apps.transit.R.styleable.custom_parts, 0, 0);
        int i = typedarray.getResourceId(2, 1);
        int j;
        ImageView imageview;
        String s;
        TextView textview;
        String s1;
        TextView textview1;
        if (i == TITLE_TYPE_01)
        {
            layoutinflater.inflate(0x7f03005f, this, true);
        } else
        if (i == TITLE_TYPE_02)
        {
            layoutinflater.inflate(0x7f030060, this, true);
        } else
        {
            layoutinflater.inflate(0x7f030061, this, true);
        }
        j = typedarray.getResourceId(3, 0);
        imageview = (ImageView)findViewById(0x7f0a01fd);
        if (j != 0)
        {
            imageview.setImageResource(j);
        } else
        {
            imageview.setVisibility(8);
        }
        s = typedarray.getString(0);
        textview = (TextView)findViewById(0x7f0a01fc);
        if (!TransitUtil.isEmpty(s))
        {
            textview.setText(s);
        } else
        {
            textview.setVisibility(8);
        }
        s1 = typedarray.getString(1);
        textview1 = (TextView)findViewById(0x7f0a01a2);
        if (!TransitUtil.isEmpty(s1))
        {
            textview1.setText(s1);
            return;
        } else
        {
            textview1.setVisibility(8);
            return;
        }
    }

}

// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.viewparts;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class CustomDialogTitle extends LinearLayout
{

    private ImageView iconView;

    public CustomDialogTitle(Context context, String s, int i)
    {
        super(context);
        LinearLayout linearlayout = (LinearLayout)((LayoutInflater)context.getSystemService("layout_inflater")).inflate(0x7f030052, null);
        iconView = (ImageView)linearlayout.findViewById(0x7f0a01f2);
        if (i != 0)
        {
            iconView.setImageResource(i);
            iconView.setVisibility(0);
        }
        ((TextView)linearlayout.findViewById(0x7f0a01ed)).setText(s);
        addView(linearlayout);
    }

    public CustomDialogTitle setIconInfo()
    {
        iconView.setImageResource(0x7f020182);
        iconView.setVisibility(0);
        return this;
    }

    public CustomDialogTitle setIconWarning()
    {
        iconView.setImageResource(0x7f020183);
        iconView.setVisibility(0);
        return this;
    }
}

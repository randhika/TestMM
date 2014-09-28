// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.common;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.preference.Preference;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

public class YIconPreference extends Preference
{

    private Drawable _drawable;

    public YIconPreference(Context context)
    {
        this(context, null);
    }

    public YIconPreference(Context context, AttributeSet attributeset)
    {
        this(context, attributeset, 0x101008e);
    }

    public YIconPreference(Context context, AttributeSet attributeset, int i)
    {
        super(context, attributeset, i);
        _drawable = null;
        setLayoutResource(R.layout.common_pref_information);
    }

    protected void onBindView(View view)
    {
        super.onBindView(view);
        ImageView imageview = (ImageView)view.findViewById(R.id.icon);
        if (_drawable != null)
        {
            imageview.setImageDrawable(_drawable);
            imageview.setVisibility(0);
            return;
        } else
        {
            imageview.setVisibility(8);
            return;
        }
    }

    public void setDrawable(Drawable drawable)
    {
        _drawable = drawable;
        notifyChanged();
    }
}

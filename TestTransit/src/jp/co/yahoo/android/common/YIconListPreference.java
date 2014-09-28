// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.common;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

// Referenced classes of package jp.co.yahoo.android.common:
//            YListPreference

public class YIconListPreference extends YListPreference
{

    private Drawable _drawable;

    public YIconListPreference(Context context)
    {
        this(context, null);
    }

    public YIconListPreference(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
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

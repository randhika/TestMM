// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.common;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.preference.CheckBoxPreference;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class YIconCheckBoxPreference extends CheckBoxPreference
{

    private static final String TAG = jp/co/yahoo/android/common/YIconCheckBoxPreference.getSimpleName();
    private int mColorSummary;
    private int mColorTitle;
    private Drawable mDrawable;

    public YIconCheckBoxPreference(Context context)
    {
        this(context, null);
    }

    public YIconCheckBoxPreference(Context context, AttributeSet attributeset)
    {
        this(context, attributeset, 0x101008f);
    }

    public YIconCheckBoxPreference(Context context, AttributeSet attributeset, int i)
    {
        super(context, attributeset, i);
        mDrawable = null;
        mColorTitle = -1;
        mColorSummary = -1;
        setLayoutResource(R.layout.common_pref_information);
    }

    protected void onBindView(View view)
    {
        super.onBindView(view);
        ImageView imageview = (ImageView)view.findViewById(R.id.icon);
        if (mDrawable != null)
        {
            imageview.setImageDrawable(mDrawable);
            imageview.setVisibility(0);
        } else
        {
            imageview.setVisibility(8);
        }
        ((TextView)view.findViewById(0x1020016)).setTextColor(mColorTitle);
        ((TextView)view.findViewById(0x1020010)).setTextColor(mColorSummary);
    }

    public void setDrawable(Drawable drawable)
    {
        mDrawable = drawable;
        notifyChanged();
    }

    public void setTextColor(int i)
    {
        mColorTitle = i;
        mColorSummary = i;
    }

}

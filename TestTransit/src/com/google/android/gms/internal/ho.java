// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.widget.Button;

// Referenced classes of package com.google.android.gms.internal:
//            hm

public final class ho extends Button
{

    public ho(Context context)
    {
        this(context, null);
    }

    public ho(Context context, AttributeSet attributeset)
    {
        super(context, attributeset, 0x1010048);
    }

    private int b(int i, int j, int k)
    {
        switch (i)
        {
        default:
            throw new IllegalStateException((new StringBuilder()).append("Unknown color scheme: ").append(i).toString());

        case 1: // '\001'
            j = k;
            // fall through

        case 0: // '\0'
            return j;
        }
    }

    private void b(Resources resources, int i, int j)
    {
        int k;
        switch (i)
        {
        default:
            throw new IllegalStateException((new StringBuilder()).append("Unknown button size: ").append(i).toString());

        case 2: // '\002'
            break MISSING_BLOCK_LABEL_84;

        case 0: // '\0'
        case 1: // '\001'
            k = b(j, com.google.android.gms.R.drawable.common_signin_btn_text_dark, com.google.android.gms.R.drawable.common_signin_btn_text_light);
            break;
        }
_L1:
        if (k == -1)
        {
            throw new IllegalStateException("Could not find background resource!");
        } else
        {
            setBackgroundDrawable(resources.getDrawable(k));
            return;
        }
        k = b(j, com.google.android.gms.R.drawable.common_signin_btn_icon_dark, com.google.android.gms.R.drawable.common_signin_btn_icon_light);
          goto _L1
    }

    private void c(Resources resources)
    {
        setTypeface(Typeface.DEFAULT_BOLD);
        setTextSize(14F);
        float f = resources.getDisplayMetrics().density;
        setMinHeight((int)(0.5F + f * 48F));
        setMinWidth((int)(0.5F + f * 48F));
    }

    private void c(Resources resources, int i, int j)
    {
        setTextColor(resources.getColorStateList(b(j, com.google.android.gms.R.color.common_signin_btn_text_dark, com.google.android.gms.R.color.common_signin_btn_text_light)));
        switch (i)
        {
        default:
            throw new IllegalStateException((new StringBuilder()).append("Unknown button size: ").append(i).toString());

        case 0: // '\0'
            setText(resources.getString(com.google.android.gms.R.string.common_signin_button_text));
            return;

        case 1: // '\001'
            setText(resources.getString(com.google.android.gms.R.string.common_signin_button_text_long));
            return;

        case 2: // '\002'
            setText(null);
            break;
        }
    }

    public void a(Resources resources, int i, int j)
    {
        boolean flag;
        Object aobj[];
        boolean flag1;
        Object aobj1[];
        if (i >= 0 && i < 3)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        aobj = new Object[1];
        aobj[0] = Integer.valueOf(i);
        hm.a(flag, "Unknown button size %d", aobj);
        if (j >= 0 && j < 2)
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        aobj1 = new Object[1];
        aobj1[0] = Integer.valueOf(j);
        hm.a(flag1, "Unknown color scheme %s", aobj1);
        c(resources);
        b(resources, i, j);
        c(resources, i, j);
    }
}

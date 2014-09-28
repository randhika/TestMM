// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.ads;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import java.math.BigDecimal;
import java.util.HashMap;

// Referenced classes of package jp.co.yahoo.android.ads:
//            e, h

public class AdContainer extends RelativeLayout
{

    public HashMap a;
    private double b;

    public AdContainer(Context context)
    {
        super(context);
        a(null, 0);
    }

    public AdContainer(Context context, AttributeSet attributeset)
    {
        this(context, attributeset, 0);
    }

    public AdContainer(Context context, AttributeSet attributeset, int i)
    {
        super(context, attributeset, i);
        a(attributeset, i);
    }

    private void a(AttributeSet attributeset, int i)
    {
        a = e.h(getContext());
        b = (new BigDecimal(((Float)a.get("widthPixels")).floatValue() / 320F)).setScale(2, 4).doubleValue();
        h.a(3, (new StringBuilder()).append("adRatio = ").append(b).toString());
        setFocusable(true);
        setClickable(true);
    }

    public double getAdRatio()
    {
        return b;
    }

    public float getDisplayInfo(String s)
    {
        return ((Float)a.get(s)).floatValue();
    }
}

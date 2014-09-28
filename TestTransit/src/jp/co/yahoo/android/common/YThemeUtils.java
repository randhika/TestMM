// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.common;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;

// Referenced classes of package jp.co.yahoo.android.common:
//            YNumberUtils

public class YThemeUtils
{

    private final Context _context;

    public YThemeUtils(Context context)
    {
        _context = context;
    }

    public int getColor(int i)
    {
        return getValue(i).getColor(0, 0);
    }

    public float getDim(int i)
    {
        return getValue(i).getDimension(0, 0.0F);
    }

    public Drawable getDrawable(int i)
    {
        return getValue(i).getDrawable(0);
    }

    public float getFloat(int i)
    {
        return YNumberUtils.parseFloat(getValue(i).getText(0).toString(), 0.0F);
    }

    public int getResId(int i)
    {
        return getValue(i).getResourceId(0, 0);
    }

    public TypedArray getValue(int i)
    {
        return _context.getTheme().obtainStyledAttributes(new int[] {
            i
        });
    }
}

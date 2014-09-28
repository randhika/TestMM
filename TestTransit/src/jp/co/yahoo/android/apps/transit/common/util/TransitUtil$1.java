// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.common.util;

import android.graphics.Paint;
import android.text.Layout;
import android.widget.TextView;

// Referenced classes of package jp.co.yahoo.android.apps.transit.common.util:
//            TransitUtil

static final class val.label
    implements android.view.er.OnGlobalLayoutListener
{

    final String val$label;
    final TextView val$textView;

    public void onGlobalLayout()
    {
        Layout layout = val$textView.getLayout();
        boolean flag = false;
        if (layout != null)
        {
            int k = layout.getLineCount();
            flag = false;
            if (k > 0)
            {
                int l = layout.getEllipsisCount(k - 1);
                flag = false;
                if (l > 0)
                {
                    flag = true;
                }
            }
        }
        if (flag)
        {
            String s = val$textView.getText().toString();
            int i = val$textView.getWidth();
            float f = val$textView.getTextSize();
            Paint paint = new Paint();
            paint.setTextSize(f);
            paint.setSubpixelText(true);
            float f1 = paint.measureText(val$label);
            int j = (int)((float)i - f1);
            String s1 = TransitUtil.getEllipsisString(s.substring(0, s.length() - val$label.length()), j, paint.getTextSize());
            val$textView.setText((new StringBuilder()).append(s1).append(val$label).toString());
        }
        try
        {
            if (android.os.SDK_INT < 16)
            {
                TransitUtil.removeGlobalOnLayoutListener(val$textView.getViewTreeObserver(), this);
                return;
            }
        }
        catch (Exception exception)
        {
            return;
        }
        TransitUtil.removeOnGlobalLayoutListener(val$textView.getViewTreeObserver(), this);
        return;
    }

    (TextView textview, String s)
    {
        val$textView = textview;
        val$label = s;
        super();
    }
}

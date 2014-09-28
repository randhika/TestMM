// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit;

import android.widget.LinearLayout;

// Referenced classes of package jp.co.yahoo.android.apps.transit:
//            RailmapActivity

class this._cls0
    implements android.view.nGlobalLayoutListener
{

    final RailmapActivity this$0;

    public void onGlobalLayout()
    {
        if (RailmapActivity.access$000(RailmapActivity.this) == null)
        {
            initWeather();
            RailmapActivity.access$100(RailmapActivity.this).setVisibility(8);
        }
    }

    r()
    {
        this$0 = RailmapActivity.this;
        super();
    }
}

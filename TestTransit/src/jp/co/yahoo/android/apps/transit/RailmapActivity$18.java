// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit;

import android.widget.SeekBar;
import jp.co.yahoo.android.maps.weather.WeatherOverlay;

// Referenced classes of package jp.co.yahoo.android.apps.transit:
//            RailmapActivity

class this._cls0
    implements android.widget.angeListener
{

    final RailmapActivity this$0;

    public void onProgressChanged(SeekBar seekbar, int i, boolean flag)
    {
        if (RailmapActivity.access$3200(RailmapActivity.this))
        {
            RailmapActivity.access$3202(RailmapActivity.this, false);
            RailmapActivity.access$000(RailmapActivity.this).updateWeather(i * 5);
        }
    }

    public void onStartTrackingTouch(SeekBar seekbar)
    {
    }

    public void onStopTrackingTouch(SeekBar seekbar)
    {
        RailmapActivity.access$000(RailmapActivity.this).updateWeather(5 * seekbar.getProgress());
    }

    _cls9()
    {
        this$0 = RailmapActivity.this;
        super();
    }
}

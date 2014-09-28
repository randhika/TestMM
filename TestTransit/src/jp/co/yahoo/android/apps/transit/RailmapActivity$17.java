// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit;

import jp.co.yahoo.android.maps.weather.WeatherOverlay;

// Referenced classes of package jp.co.yahoo.android.apps.transit:
//            RailmapActivity

class this._cls0
    implements jp.co.yahoo.android.maps.weather.herOverlayListener
{

    final RailmapActivity this$0;

    public void errorUpdateWeather(WeatherOverlay weatheroverlay, int i)
    {
        RailmapActivity.access$3202(RailmapActivity.this, true);
    }

    public void finishUpdateWeather(WeatherOverlay weatheroverlay)
    {
        RailmapActivity.access$3202(RailmapActivity.this, true);
    }

    herOverlayListener()
    {
        this$0 = RailmapActivity.this;
        super();
    }
}

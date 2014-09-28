// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.maps.weather;

import java.util.TimerTask;

// Referenced classes of package jp.co.yahoo.android.maps.weather:
//            WeatherOverlay

class this._cls0 extends TimerTask
{

    final WeatherOverlay this$0;

    public void run()
    {
        updateWeather();
    }

    ()
    {
        this$0 = WeatherOverlay.this;
        super();
    }
}

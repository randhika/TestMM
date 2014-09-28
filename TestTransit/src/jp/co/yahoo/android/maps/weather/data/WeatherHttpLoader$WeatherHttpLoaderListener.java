// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.maps.weather.data;

import java.io.InputStream;

// Referenced classes of package jp.co.yahoo.android.maps.weather.data:
//            WeatherHttpLoader, WeatherRequest

public static interface 
{

    public abstract boolean endAllWeatherHttpLoader(WeatherHttpLoader weatherhttploader);

    public abstract boolean endWeatherHttpLoader(InputStream inputstream, WeatherRequest weatherrequest);

    public abstract boolean errorWeatherHttpLoader(WeatherHttpLoader weatherhttploader);
}

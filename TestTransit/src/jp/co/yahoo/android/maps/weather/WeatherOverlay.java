// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.maps.weather;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;
import jp.co.yahoo.android.maps.MapView;
import jp.co.yahoo.android.maps.weather.data.Tile;
import jp.co.yahoo.android.maps.weather.data.TileOverlay;
import jp.co.yahoo.android.maps.weather.data.WeatherHttpLoader;
import jp.co.yahoo.android.maps.weather.data.WeatherRequest;

public class WeatherOverlay extends TileOverlay
    implements jp.co.yahoo.android.maps.weather.data.WeatherHttpLoader.WeatherHttpLoaderListener
{
    public static interface WeatherOverlayListener
    {

        public abstract void errorUpdateWeather(WeatherOverlay weatheroverlay, int i);

        public abstract void finishUpdateWeather(WeatherOverlay weatheroverlay);
    }


    private Date mDate;
    private String mStrDate;
    private Timer mTimer;
    private WeatherHttpLoader mWeatherHttpLoader;
    private WeatherOverlayListener mWeatherOverlayListener;

    public WeatherOverlay(Activity activity)
    {
        super(activity);
        mWeatherHttpLoader = null;
        mStrDate = "";
        mDate = null;
        mWeatherOverlayListener = null;
        mTimer = null;
        updateWeather();
    }

    public boolean endAllWeatherHttpLoader(WeatherHttpLoader weatherhttploader)
    {
        if (mWeatherOverlayListener != null)
        {
            mWeatherOverlayListener.finishUpdateWeather(this);
        }
        return false;
    }

    public boolean endWeatherHttpLoader(InputStream inputstream, WeatherRequest weatherrequest)
    {
        if (!mStrDate.equals(weatherrequest.getDate())) goto _L2; else goto _L1
_L1:
        Drawable drawable = null;
        drawable = Drawable.createFromStream(inputstream, "");
        if (drawable == null)
        {
            break MISSING_BLOCK_LABEL_66;
        }
        int i;
        int j;
        i = weatherrequest.getTileSize();
        j = weatherrequest.getTileSize();
        if (i <= 0 || j <= 0)
        {
            break MISSING_BLOCK_LABEL_59;
        }
        drawable.setBounds(0, 0, i, j);
        drawable.setAlpha(150);
_L4:
        if (drawable != null)
        {
            setTileImage(drawable, weatherrequest.getTileX(), weatherrequest.getTileY(), weatherrequest.getTileZ());
            repaint();
        }
_L2:
        return false;
        IllegalArgumentException illegalargumentexception;
        illegalargumentexception;
        illegalargumentexception.printStackTrace();
        if (true) goto _L4; else goto _L3
_L3:
    }

    public boolean errorWeatherHttpLoader(WeatherHttpLoader weatherhttploader)
    {
        if (mWeatherOverlayListener != null)
        {
            mWeatherOverlayListener.errorUpdateWeather(this, 1);
        }
        return false;
    }

    public Date getDate()
    {
        if (mDate != null)
        {
            return new Date(mDate.getTime());
        } else
        {
            return null;
        }
    }

    public boolean onRemove(MapView mapview)
    {
        if (mWeatherHttpLoader != null)
        {
            mWeatherHttpLoader.stopThread();
            mWeatherHttpLoader = null;
        }
        return false;
    }

    public void removeTile(Tile tile)
    {
    }

    public void requestNewTiles(Vector vector)
    {
        if (vector == null) goto _L2; else goto _L1
_L1:
        int i = 0;
_L5:
        if (i < vector.size()) goto _L3; else goto _L2
_L2:
        return;
_L3:
        Tile tile = (Tile)vector.get(i);
        if (tile.getTileZ() >= 4)
        {
            WeatherRequest weatherrequest = new WeatherRequest(tile.getTileX(), tile.getTileY(), tile.getTileSid(), tile.getSize(), mStrDate);
            if (mWeatherHttpLoader == null)
            {
                mWeatherHttpLoader = new WeatherHttpLoader(this);
            }
            mWeatherHttpLoader.addTileRequest(weatherrequest);
        }
        i++;
        if (true) goto _L5; else goto _L4
_L4:
    }

    public void setWeatherOverlayListener(WeatherOverlayListener weatheroverlaylistener)
    {
        mWeatherOverlayListener = weatheroverlaylistener;
    }

    public void startAutoUpdate(int i)
    {
        if (mTimer == null)
        {
            mTimer = new Timer();
            mTimer.scheduleAtFixedRate(new TimerTask() {

                final WeatherOverlay this$0;

                public void run()
                {
                    updateWeather();
                }

            
            {
                this$0 = WeatherOverlay.this;
                super();
            }
            }, 0L, 60000 * i);
        }
    }

    public void stopAutoUpdate()
    {
        if (mTimer != null)
        {
            mTimer.cancel();
            mTimer = null;
        }
    }

    public void updateWeather()
    {
        updateWeather(0);
    }

    public void updateWeather(int i)
    {
        if (i >= -120 && i <= 60)
        {
            Date date = new Date(System.currentTimeMillis() - (long)(0xa1220 - 60000 * i));
            int j = date.getMinutes();
            if (j % 10 != 0)
            {
                date.setMinutes(5 * (j / 5));
            }
            mStrDate = (new SimpleDateFormat("yyyyMMddkkmm")).format(date);
            mDate = date;
            if (!resetTileImage())
            {
                initTiles();
                return;
            }
        }
    }
}

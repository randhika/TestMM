// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.common;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.SystemClock;
import java.util.Arrays;

// Referenced classes of package jp.co.yahoo.android.common:
//            YLog

public class YShakeDetector
    implements SensorEventListener
{
    private static class ValuesHolder
    {

        private int _position;
        private final int _size;
        private final float _values[];

        private void clear()
        {
            int i = _values.length;
            int j = 0;
            while (j < i) 
            {
                if (j % 2 == 0)
                {
                    _values[j] = 9999F;
                } else
                {
                    _values[j] = -9999F;
                }
                j++;
            }
        }

        public boolean add(float f)
        {
            _values[_position] = f;
            if (-1 + _size == _position)
            {
                _position = 0;
                return true;
            } else
            {
                _position = 1 + _position;
                return false;
            }
        }

        public float getMedian()
        {
            float af[] = (float[])_values.clone();
            Arrays.sort(af);
            return af[af.length / 2];
        }

        public ValuesHolder(int i)
        {
            _position = 0;
            _size = i;
            _values = new float[i];
            clear();
        }
    }

    public static interface YShakeListener
    {

        public abstract void onShake();
    }


    private static final float SENSOR_GAP = 25F;
    private static final String TAG = "YShakeDetector";
    private static final long TIME_GAP = 500L;
    private long _lastTime;
    private YShakeListener _listener;
    private SensorManager _sensorManager;
    private final ValuesHolder _x = new ValuesHolder(30);
    private final ValuesHolder _y = new ValuesHolder(30);
    private final ValuesHolder _z = new ValuesHolder(30);

    public YShakeDetector()
    {
        _lastTime = 0L;
    }

    private void detectedShakeAction()
    {
        long l = SystemClock.elapsedRealtime();
        if (l - _lastTime > 500L)
        {
            _lastTime = l;
            _listener.onShake();
        }
    }

    public void onAccuracyChanged(Sensor sensor, int i)
    {
    }

    public void onSensorChanged(SensorEvent sensorevent)
    {
        float f = sensorevent.values[0];
        float f1 = sensorevent.values[1];
        float f2 = sensorevent.values[2];
        _x.add(f);
        _y.add(f1);
        _z.add(f2);
        if (Math.abs(f) >= 10F || Math.abs(f1) >= 10F || Math.abs(f2) >= 10F)
        {
            float f3 = f - _x.getMedian();
            float f4 = f1 - _y.getMedian();
            float f5 = f2 - _z.getMedian();
            if (Math.abs(f3) + Math.abs(f4) + Math.abs(f5) > 25F)
            {
                detectedShakeAction();
                return;
            }
        }
    }

    public boolean start(Context context, YShakeListener yshakelistener)
    {
        _sensorManager = (SensorManager)context.getSystemService("sensor");
        Sensor sensor = _sensorManager.getDefaultSensor(1);
        if (sensor == null)
        {
            YLog.e("YShakeDetector", "it does not have an accelerometer sensor");
            return false;
        } else
        {
            _sensorManager.registerListener(this, sensor, 3);
            _listener = yshakelistener;
            return true;
        }
    }

    public void stop()
    {
        if (_sensorManager != null)
        {
            _sensorManager.unregisterListener(this);
        }
    }
}

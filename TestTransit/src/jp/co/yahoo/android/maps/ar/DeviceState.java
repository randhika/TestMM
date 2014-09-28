// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.maps.ar;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import java.util.List;

// Referenced classes of package jp.co.yahoo.android.maps.ar:
//            DeviceStateListener

public class DeviceState
    implements SensorEventListener
{

    private Sensor accelerometer;
    float accelerometerValues[];
    private float azimuth;
    float geomagneticMatrix[];
    private float inclination;
    DeviceStateListener listener;
    private Sensor magneticField;
    private SensorManager sensorManager;

    public DeviceState(Context context, DeviceStateListener devicestatelistener)
    {
        accelerometerValues = null;
        geomagneticMatrix = null;
        inclination = 0.0F;
        azimuth = 0.0F;
        listener = devicestatelistener;
        sensorManager = (SensorManager)context.getSystemService("sensor");
        if (sensorManager.getSensorList(1).size() > 0 && sensorManager.getSensorList(2).size() > 0)
        {
            accelerometer = sensorManager.getDefaultSensor(1);
            magneticField = sensorManager.getDefaultSensor(2);
            return;
        } else
        {
            accelerometer = null;
            magneticField = null;
            return;
        }
    }

    public float getAzimuth()
    {
        return (float)((180D * (double)azimuth) / 3.1415926535897931D);
    }

    public float getInclination()
    {
        return (float)((180D * (double)inclination) / 3.1415926535897931D);
    }

    public void onAccuracyChanged(Sensor sensor, int i)
    {
    }

    public void onSensorChanged(SensorEvent sensorevent)
    {
        sensorevent.sensor.getType();
        JVM INSTR tableswitch 1 2: default 28
    //                   1 220
    //                   2 237;
           goto _L1 _L2 _L3
_L1:
        if (geomagneticMatrix == null || accelerometerValues == null) goto _L5; else goto _L4
_L4:
        float af[] = new float[16];
        if (!SensorManager.getRotationMatrix(af, null, accelerometerValues, geomagneticMatrix)) goto _L5; else goto _L6
_L6:
        float af1[] = new float[16];
        SensorManager.remapCoordinateSystem(af, 131, 1, af1);
        float af2[] = new float[3];
        SensorManager.getOrientation(af1, af2);
        inclination = 0.05F * af2[1] + inclination * (1.0F - 0.05F);
        if ((double)azimuth > 3.1415926535897931D)
        {
            azimuth = (float)((double)azimuth - 6.2831853071795862D);
        }
        if ((double)Math.abs(azimuth - af2[0]) > 3.1415926535897931D)
        {
            if (af2[0] < 0.0F)
            {
                af2[0] = (float)(6.2831853071795862D + (double)af2[0]);
            } else
            {
                azimuth = (float)(6.2831853071795862D + (double)azimuth);
            }
        }
        azimuth = 0.05F * af2[0] + azimuth * (1.0F - 0.05F);
_L9:
        if (azimuth < 0.0F) goto _L8; else goto _L7
_L7:
        listener.StateChanged();
_L5:
        return;
_L2:
        accelerometerValues = (float[])sensorevent.values.clone();
          goto _L1
_L3:
        geomagneticMatrix = (float[])sensorevent.values.clone();
          goto _L1
_L8:
        azimuth = (float)(6.2831853071795862D + (double)azimuth);
          goto _L9
    }

    public void registSensor()
    {
        if (accelerometer != null && magneticField != null)
        {
            sensorManager.registerListener(this, accelerometer, 2);
            sensorManager.registerListener(this, magneticField, 2);
        }
    }

    public void unregistSensor()
    {
        if (accelerometer != null && magneticField != null)
        {
            sensorManager.unregisterListener(this, accelerometer);
            sensorManager.unregisterListener(this, magneticField);
        }
    }
}

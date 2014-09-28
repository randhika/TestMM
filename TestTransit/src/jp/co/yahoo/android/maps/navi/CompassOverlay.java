// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.maps.navi;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import jp.co.yahoo.android.maps.CircleOverlay;
import jp.co.yahoo.android.maps.GeoPoint;
import jp.co.yahoo.android.maps.MapView;
import jp.co.yahoo.android.maps.Overlay;
import jp.co.yahoo.android.maps.Projection;

public class CompassOverlay extends Overlay
    implements SensorEventListener
{

    private static final int TABLE_MAX = 20;
    private float accelerometerValues[];
    private float accels[];
    private int accelsCount;
    private float accels_X[];
    private float accels_Y[];
    private float accels_Z[];
    private float angle;
    private GeoPoint center;
    private Context cont;
    private int drowflg;
    private long frame_count;
    private Bitmap image;
    private float inR[];
    private float last_angle;
    private int last_seconds;
    private Canvas mCanvas;
    private MapView mMapView;
    private SensorManager mSensorManager;
    private CircleOverlay m_circleOverlay;
    private boolean m_pinVisible;
    private float magneticValues[];
    private float mags[];
    private int magsCount;
    private float mags_X[];
    private float mags_Y[];
    private float mags_Z[];
    private int mode;
    private float ori[];
    private float outR[];
    private int yokoState;

    public CompassOverlay(Context context, MapView mapview)
    {
        inR = new float[16];
        outR = new float[16];
        ori = new float[3];
        center = new GeoPoint(0x21175d7, 0x77ae310);
        magneticValues = new float[3];
        accelerometerValues = new float[3];
        accelsCount = 0;
        mags = new float[3];
        accels = new float[3];
        magsCount = 0;
        mags_X = new float[20];
        mags_Y = new float[20];
        mags_Z = new float[20];
        accels_X = new float[20];
        accels_Y = new float[20];
        accels_Z = new float[20];
        angle = 0.0F;
        last_angle = 0.0F;
        drowflg = 1;
        image = null;
        mode = 0;
        mCanvas = null;
        m_circleOverlay = null;
        m_pinVisible = true;
        yokoState = 0;
        frame_count = 0L;
        last_seconds = (new Date()).getSeconds();
        cont = context;
        mMapView = mapview;
        String s = context.getPackageName();
        int i = context.getResources().getIdentifier("compass48", "drawable", s);
        image = BitmapFactory.decodeResource(cont.getResources(), i);
        enableCompass();
    }

    private void setZoomlevel(int i)
    {
    }

    public void draw(Canvas canvas, MapView mapview, boolean flag)
    {
        if (drowflg == 1)
        {
            if (m_circleOverlay != null)
            {
                m_circleOverlay.draw(canvas, mapview, flag);
            }
            if (m_pinVisible)
            {
                Point point = mapview.getProjection().toPixels(center, null);
                int i = point.x;
                int j = point.y;
                int k = image.getWidth();
                int l = image.getHeight();
                canvas.save();
                Paint paint = new Paint();
                paint.setColor(Color.argb(0, 0, 0, 0));
                paint.setStyle(android.graphics.Paint.Style.FILL);
                canvas.drawRect(new Rect(i, j, l, k), paint);
                canvas.translate(i, j);
                canvas.rotate((float)((180D * (double)angle) / 3.1415926535897931D));
                canvas.drawBitmap(image, 0 - k / 2, 0 - l / 2, null);
                canvas.restore();
            }
        }
    }

    public boolean enableCompass()
    {
        mSensorManager = (SensorManager)cont.getSystemService("sensor");
        Iterator iterator = mSensorManager.getSensorList(-1).iterator();
        do
        {
            Sensor sensor;
            do
            {
                if (!iterator.hasNext())
                {
                    return false;
                }
                sensor = (Sensor)iterator.next();
                if (sensor.getType() == 2)
                {
                    mSensorManager.registerListener(this, sensor, 2);
                }
            } while (sensor.getType() != 1);
            mSensorManager.registerListener(this, sensor, 2);
        } while (true);
    }

    public void onAccuracyChanged(Sensor sensor, int i)
    {
    }

    public void onSensorChanged(SensorEvent sensorevent)
    {
        sensorevent.sensor.getType();
        JVM INSTR tableswitch 1 2: default 28
    //                   1 329
    //                   2 346;
           goto _L1 _L2 _L3
_L1:
        if (accelerometerValues == null || magneticValues == null) goto _L5; else goto _L4
_L4:
        float f;
        float f1;
        float f2;
        byte byte0;
        if (mode == 1)
        {
            if (yokoState == 0)
            {
                if (accelerometerValues[0] < -4F)
                {
                    yokoState = 1;
                }
            } else
            if (accelerometerValues[0] > 4F)
            {
                yokoState = 0;
            }
        }
        f = Math.abs(accelerometerValues[0]);
        f1 = Math.abs(accelerometerValues[1]);
        f2 = Math.abs(accelerometerValues[2]);
        if (mode == 0)
        {
            if (f2 > f1)
            {
                byte0 = 0;
            } else
            {
                byte0 = 1;
            }
        } else
        if (f2 > f)
        {
            byte0 = 0;
        } else
        if (accelerometerValues[0] > 0.0F)
        {
            byte0 = 3;
        } else
        {
            byte0 = 4;
        }
        SensorManager.getRotationMatrix(inR, null, accelerometerValues, magneticValues);
        if (mode == 0)
        {
            if (byte0 == 0)
            {
                SensorManager.remapCoordinateSystem(inR, 129, 2, outR);
            } else
            if (byte0 == 1)
            {
                SensorManager.remapCoordinateSystem(inR, 129, 131, outR);
            }
        } else
        if (byte0 == 0)
        {
            if (yokoState == 0)
            {
                SensorManager.remapCoordinateSystem(inR, 2, 1, outR);
            } else
            {
                SensorManager.remapCoordinateSystem(inR, 130, 129, outR);
            }
        } else
        if (byte0 == 3)
        {
            SensorManager.remapCoordinateSystem(inR, 131, 1, outR);
        } else
        if (byte0 == 4)
        {
            SensorManager.remapCoordinateSystem(inR, 3, 129, outR);
        }
        SensorManager.getOrientation(outR, ori);
        if ((double)angle > 3.1415926535897931D)
        {
            angle = (float)((double)angle - 6.2831853071795862D);
        }
        if ((double)Math.abs(angle - ori[0]) > 3.1415926535897931D)
        {
            if (ori[0] < 0.0F)
            {
                float af[] = ori;
                af[0] = (float)(6.2831853071795862D + (double)af[0]);
            } else
            {
                angle = (float)(6.2831853071795862D + (double)angle);
            }
        }
        angle = 0.05F * ori[0] + angle * (1.0F - 0.05F);
_L8:
        if (angle < 0.0F) goto _L7; else goto _L6
_L6:
        if ((double)Math.abs(angle - last_angle) > 0.050000000000000003D)
        {
            last_angle = angle;
            mMapView.reDraw();
        }
_L5:
        return;
_L2:
        accelerometerValues = (float[])sensorevent.values.clone();
          goto _L1
_L3:
        magneticValues = (float[])sensorevent.values.clone();
          goto _L1
_L7:
        angle = (float)(6.2831853071795862D + (double)angle);
          goto _L8
    }

    public void setPinVisible(boolean flag)
    {
        m_pinVisible = flag;
    }

    public void setkatamuki(int i)
    {
        mode = i;
    }

    public void setpos(GeoPoint geopoint)
    {
        center = geopoint;
        m_circleOverlay = null;
    }

    public void setpos(GeoPoint geopoint, int i)
    {
        center = geopoint;
        if (i > 30)
        {
            m_circleOverlay = new CircleOverlay(geopoint, i);
            m_circleOverlay.setFillColor(Color.argb(30, 96, 79, 247));
            m_circleOverlay.setStrokeColor(Color.argb(150, 96, 79, 247));
        } else
        {
            m_circleOverlay = null;
        }
        setZoomlevel(i);
    }

    public void setpos(GeoPoint geopoint, int i, int j)
    {
        center = geopoint;
        if (i >= j)
        {
            m_circleOverlay = new CircleOverlay(geopoint, i);
            m_circleOverlay.setFillColor(Color.argb(30, 96, 79, 247));
            m_circleOverlay.setStrokeColor(Color.argb(150, 96, 79, 247));
        } else
        {
            m_circleOverlay = null;
        }
        setZoomlevel(i);
    }

    public void stopCompass()
    {
        if (mSensorManager != null)
        {
            mSensorManager.unregisterListener(this);
            mSensorManager = null;
        }
        drowflg = 0;
    }
}

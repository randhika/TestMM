// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.maps.ar;

import android.content.Context;
import android.hardware.Camera;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import java.io.IOException;

public class CameraPreview extends SurfaceView
    implements android.view.SurfaceHolder.Callback, android.hardware.Camera.PictureCallback
{

    protected Camera camera;
    private SurfaceHolder holder;

    CameraPreview(Context context)
    {
        super(context);
        holder = getHolder();
        holder.addCallback(this);
        holder.setType(3);
    }

    public void onPictureTaken(byte abyte0[], Camera camera1)
    {
    }

    public boolean onTouchEvent(MotionEvent motionevent)
    {
        if (motionevent.getAction() == 0)
        {
            camera.autoFocus(null);
        }
        return true;
    }

    public void surfaceChanged(SurfaceHolder surfaceholder, int i, int j, int k)
    {
        camera.startPreview();
    }

    public void surfaceCreated(SurfaceHolder surfaceholder)
    {
        try
        {
            camera = Camera.open();
            camera.setPreviewDisplay(surfaceholder);
            return;
        }
        catch (IOException ioexception)
        {
            ioexception.printStackTrace();
        }
    }

    public void surfaceDestroyed(SurfaceHolder surfaceholder)
    {
        camera.setPreviewCallback(null);
        camera.stopPreview();
        camera.release();
        camera = null;
    }
}

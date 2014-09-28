// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.timer.viewparts;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.util.FloatMath;
import android.view.MotionEvent;
import android.view.View;

public class ScalableView extends View
{

    private static final int TOUCH_DRAG = 1;
    private static final int TOUCH_NONE = 0;
    private static final int TOUCH_ZOOM = 2;
    protected Bitmap _bmpImageFile;
    private float _fPinchMoveX;
    private float _fPinchMoveY;
    private float _fPinchScale;
    private float _fPinchStartDistance;
    private int _nTouchMode;
    private PointF _ptPinchStart;

    public ScalableView(Context context, String s)
    {
        super(context);
        _fPinchScale = 1.0F;
        _ptPinchStart = new PointF();
        _fPinchStartDistance = 0.0F;
        _fPinchMoveX = 0.0F;
        _fPinchMoveY = 0.0F;
        _nTouchMode = 0;
        _bmpImageFile = LoadImageFile(s, 1600);
    }

    private void GetCenterPoint(MotionEvent motionevent, PointF pointf)
    {
        pointf.x = 0.5F * (motionevent.getX(0) + motionevent.getX(1));
        pointf.y = 0.5F * (motionevent.getY(0) + motionevent.getY(1));
    }

    private float GetDistance(MotionEvent motionevent)
    {
        float f = motionevent.getX(0) - motionevent.getX(1);
        float f1 = motionevent.getY(0) - motionevent.getY(1);
        return FloatMath.sqrt(f * f + f1 * f1);
    }

    public Bitmap LoadImageFile(String s, int i)
    {
        android.graphics.BitmapFactory.Options options = new android.graphics.BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(s, options);
        int j = options.outWidth;
        int k = options.outHeight;
        if (j == 0 || k == 0)
        {
            return null;
        }
        if (j <= i && k <= i)
        {
            options.inJustDecodeBounds = false;
            return BitmapFactory.decodeFile(s, options);
        }
        float f = (float)j / (float)i;
        float f1 = (float)k / (float)i;
        float f2;
        int l;
        if (f > f1)
        {
            f2 = f;
        } else
        {
            f2 = f1;
        }
        for (l = 1; f2 >= (float)l; l *= 2) { }
        options.inJustDecodeBounds = false;
        options.inSampleSize = l;
        return BitmapFactory.decodeFile(s, options);
    }

    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
        if (_bmpImageFile == null)
        {
            return;
        }
        float f = 0.0F;
        int i = _bmpImageFile.getWidth();
        int j = _bmpImageFile.getHeight();
        int k = getWidth();
        int l = getHeight();
        float f1;
        float f2;
        float f3;
        float f4;
        float f5;
        float f6;
        float f7;
        Matrix matrix;
        if ((long)i * (long)l > (long)k * (long)j)
        {
            f1 = (float)k / (float)i;
            f2 = 0.5F * ((float)l - f1 * (float)j);
        } else
        {
            f1 = (float)l / (float)j;
            f = 0.5F * ((float)k - f1 * (float)i);
            f2 = 0.0F;
        }
        f3 = f1 * _fPinchScale;
        f4 = f + _fPinchMoveX;
        f5 = f2 + _fPinchMoveY;
        f6 = f4 + (_ptPinchStart.x - _ptPinchStart.x * _fPinchScale);
        f7 = f5 + (_ptPinchStart.y - _ptPinchStart.y * _fPinchScale);
        matrix = new Matrix();
        matrix.preScale(f3, f3);
        matrix.postTranslate(f6, f7);
        canvas.drawBitmap(_bmpImageFile, matrix, null);
    }

    public boolean onTouchEvent(MotionEvent motionevent)
    {
        0xff & motionevent.getAction();
        JVM INSTR tableswitch 1 6: default 48
    //                   1 214
    //                   2 130
    //                   3 48
    //                   4 48
    //                   5 86
    //                   6 214;
           goto _L1 _L2 _L3 _L1 _L1 _L4 _L2
_L1:
        0xff & motionevent.getAction();
        JVM INSTR tableswitch 0 2: default 84
    //                   0 265
    //                   1 297
    //                   2 287;
           goto _L5 _L6 _L7 _L8
_L5:
        return true;
_L4:
        if (motionevent.getPointerCount() >= 2)
        {
            _fPinchStartDistance = GetDistance(motionevent);
            if (_fPinchStartDistance > 50F)
            {
                GetCenterPoint(motionevent, _ptPinchStart);
                _nTouchMode = 2;
            }
        }
        continue; /* Loop/switch isn't completed */
_L3:
        if (_nTouchMode == 2 && _fPinchStartDistance > 0.0F)
        {
            PointF pointf = new PointF();
            GetCenterPoint(motionevent, pointf);
            _fPinchMoveX = pointf.x - _ptPinchStart.x;
            _fPinchMoveY = pointf.y - _ptPinchStart.y;
            _fPinchScale = GetDistance(motionevent) / _fPinchStartDistance;
            invalidate();
        }
        continue; /* Loop/switch isn't completed */
_L2:
        if (_nTouchMode == 2)
        {
            _nTouchMode = 0;
            _fPinchMoveX = 0.0F;
            _fPinchMoveY = 0.0F;
            _fPinchScale = 1.0F;
            _ptPinchStart.x = 0.0F;
            _ptPinchStart.y = 0.0F;
            invalidate();
        }
        continue; /* Loop/switch isn't completed */
_L6:
        if (_nTouchMode != 0 || motionevent.getPointerCount() != 1) goto _L5; else goto _L9
_L9:
        _nTouchMode = 1;
        return true;
_L8:
        if (_nTouchMode != 1) goto _L5; else goto _L10
_L10:
        return true;
_L7:
        if (_nTouchMode != 1) goto _L5; else goto _L11
_L11:
        _nTouchMode = 0;
        return true;
        if (true) goto _L1; else goto _L12
_L12:
    }
}

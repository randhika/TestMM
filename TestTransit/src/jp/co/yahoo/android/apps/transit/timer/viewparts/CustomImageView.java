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
import android.media.ExifInterface;
import android.util.AttributeSet;
import android.util.FloatMath;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import java.io.IOException;
import jp.co.yahoo.android.apps.transit.TransitBaseActivity;
import jp.co.yahoo.android.apps.transit.common.util.TransitUtil;

public class CustomImageView extends ImageView
    implements android.view.View.OnTouchListener
{

    private static final float DEFAULT_MAX_SCALE = 5F;
    private static final float DEFAULT_SCALE = 1F;
    private static final int MATRIX_VALUES_NUM = 9;
    private static final int MODE_DOWN = 3;
    private static final int MODE_DRAG = 1;
    private static final int MODE_NONE = 0;
    private static final int MODE_ZOOM = 2;
    private int actionBarHeight;
    private boolean isFitSize;
    private boolean isLock;
    private boolean isSmall;
    private android.view.View.OnClickListener listener;
    private Bitmap mBitmap;
    private String mFilePath;
    private Matrix mImageMatrix;
    private float mInitialScale;
    private float mMaxScale;
    private PointF mMidPoint;
    private int mMode;
    private PointF mMovePoint;
    private Matrix mSavedImageMatrix;
    private float mSpan;
    private int mTouchMode;
    private PointF mTouchPoint;

    public CustomImageView(Context context)
    {
        super(context);
        mFilePath = null;
        mBitmap = null;
        mTouchPoint = new PointF();
        mMovePoint = new PointF();
        mImageMatrix = new Matrix();
        mSavedImageMatrix = new Matrix();
        mSpan = 0.0F;
        mInitialScale = 1.0F;
        mMaxScale = 5F;
        mMidPoint = new PointF();
        mMode = 0;
        mTouchMode = 0;
        isLock = false;
        isFitSize = false;
        isSmall = false;
        actionBarHeight = 0;
        init(context);
    }

    public CustomImageView(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        mFilePath = null;
        mBitmap = null;
        mTouchPoint = new PointF();
        mMovePoint = new PointF();
        mImageMatrix = new Matrix();
        mSavedImageMatrix = new Matrix();
        mSpan = 0.0F;
        mInitialScale = 1.0F;
        mMaxScale = 5F;
        mMidPoint = new PointF();
        mMode = 0;
        mTouchMode = 0;
        isLock = false;
        isFitSize = false;
        isSmall = false;
        actionBarHeight = 0;
        init(context);
    }

    public CustomImageView(Context context, AttributeSet attributeset, int i)
    {
        super(context, attributeset, i);
        mFilePath = null;
        mBitmap = null;
        mTouchPoint = new PointF();
        mMovePoint = new PointF();
        mImageMatrix = new Matrix();
        mSavedImageMatrix = new Matrix();
        mSpan = 0.0F;
        mInitialScale = 1.0F;
        mMaxScale = 5F;
        mMidPoint = new PointF();
        mMode = 0;
        mTouchMode = 0;
        isLock = false;
        isFitSize = false;
        isSmall = false;
        actionBarHeight = 0;
        init(context);
    }

    private void actionDown(MotionEvent motionevent)
    {
        mMovePoint.set(motionevent.getX(), motionevent.getY());
        mImageMatrix.set(mSavedImageMatrix);
    }

    private void actionMove(MotionEvent motionevent)
    {
        if (mBitmap == null)
        {
            return;
        }
        PointF pointf = new PointF(motionevent.getX(), motionevent.getY());
        float f = pointf.x - mMovePoint.x;
        float f1 = pointf.y - mMovePoint.y;
        float af[] = new float[9];
        mImageMatrix.getValues(af);
        float f2 = super.getWidth();
        float f3 = (float)mBitmap.getWidth() * af[0];
        float f4 = super.getHeight();
        float f5 = (float)mBitmap.getHeight() * af[4];
        if (f2 >= f3)
        {
            f = 0.0F;
        }
        if (f4 >= f5)
        {
            f1 = 0.0F;
        }
        mImageMatrix.postTranslate(f, f1);
        super.setImageMatrix(mImageMatrix);
    }

    private boolean actionPointerDown(MotionEvent motionevent)
    {
        float f = getSpan(motionevent);
        if (f < 10F)
        {
            return false;
        } else
        {
            mSpan = f;
            float f1 = motionevent.getX(0) + motionevent.getX(1);
            float f2 = motionevent.getY(0) + motionevent.getY(1);
            mMidPoint.set(f1 / 2.0F, f2 / 2.0F);
            mSavedImageMatrix.set(super.getImageMatrix());
            return true;
        }
    }

    private boolean actionPointerMove(MotionEvent motionevent)
    {
        mImageMatrix.set(mSavedImageMatrix);
        float f = getMatrixScale(mImageMatrix);
        float f1 = getScale(motionevent);
        float f2 = f1 * f;
        if (f2 < mInitialScale)
        {
            f1 = mInitialScale / f;
        }
        if (f2 > mMaxScale)
        {
            f1 = mMaxScale / f;
        }
        mImageMatrix.postScale(f1, f1, mMidPoint.x, mMidPoint.y);
        super.setImageMatrix(mImageMatrix);
        return true;
    }

    private boolean chkSize(int i, int j, int k, int l)
    {
        boolean flag = true;
        if (i <= k && j <= l)
        {
            flag = false;
        }
        return flag;
    }

    private void chkXPosition(Bitmap bitmap, float f, Matrix matrix)
    {
        if (bitmap != null)
        {
            float f1 = super.getWidth();
            float f2 = f * (float)bitmap.getWidth();
            float af[] = new float[9];
            matrix.getValues(af);
            float f3 = af[2];
            if (f3 > 0.0F)
            {
                setValueToImageMatrix(2, 0.0F, matrix);
                return;
            }
            if (f2 + f3 < f1)
            {
                setValueToImageMatrix(2, af[2] + (f1 - (f2 + f3)), matrix);
                return;
            }
        }
    }

    private void chkYPosition(Bitmap bitmap, float f, Matrix matrix)
    {
        if (bitmap != null)
        {
            float f1 = super.getHeight();
            float f2 = f * (float)bitmap.getHeight();
            float af[] = new float[9];
            matrix.getValues(af);
            float f3 = af[5];
            if (f1 <= f2)
            {
                if (f3 > 0.0F)
                {
                    setValueToImageMatrix(5, 0.0F, matrix);
                    return;
                }
                if (f2 + f3 < f1)
                {
                    setValueToImageMatrix(5, af[5] + (f1 - (f2 + f3)), matrix);
                    return;
                }
            }
        }
    }

    private Bitmap decode(android.graphics.BitmapFactory.Options options, String s, boolean flag)
    {
        options.inJustDecodeBounds = flag;
        return BitmapFactory.decodeFile(s, options);
    }

    private void drawInitial()
    {
        if (mBitmap != null)
        {
            mBitmap.recycle();
        }
        mBitmap = getBitmap(mFilePath);
        if (mBitmap == null)
        {
            return;
        }
        super.setImageBitmap(mBitmap);
        float af[] = new float[9];
        mImageMatrix.getValues(af);
        if (af[0] == 1.0F)
        {
            mImageMatrix.postScale(1.0F, 1.0F);
            mSavedImageMatrix.set(mImageMatrix);
        }
        mInitialScale = getInitialScale(mBitmap);
        setInCenteringX(mBitmap, mInitialScale, mImageMatrix);
        if (!isFitSize) goto _L2; else goto _L1
_L1:
        mImageMatrix.postScale(mInitialScale, mInitialScale);
        mSavedImageMatrix.set(mImageMatrix);
_L4:
        super.setImageMatrix(mImageMatrix);
        return;
_L2:
        if (isSmall)
        {
            mImageMatrix.postScale(mInitialScale, mInitialScale);
            mSavedImageMatrix.set(mImageMatrix);
        }
        if (true) goto _L4; else goto _L3
_L3:
    }

    private Bitmap getBitmap(String s)
    {
        if (mFilePath == null)
        {
            return null;
        }
        Bitmap bitmap;
        try
        {
            bitmap = loadBitmap(mFilePath);
        }
        catch (OutOfMemoryError outofmemoryerror)
        {
            return null;
        }
        return bitmap;
    }

    private int getBmpImageScale(float f, float f1, float f2, float f3)
    {
        float f4 = 1.0F + f / f2;
        float f5 = 1.0F + f1 / f3;
        return Math.min((int)f4, (int)f5);
    }

    private float getDragSpan(MotionEvent motionevent)
    {
        PointF pointf = new PointF(motionevent.getX(), motionevent.getY());
        float f = pointf.x - mTouchPoint.x;
        float f1 = pointf.y - mTouchPoint.y;
        return FloatMath.sqrt(f * f + f1 * f1);
    }

    private float getInitialScale(Bitmap bitmap)
    {
        if (bitmap == null)
        {
            return 1.0F;
        } else
        {
            float f = super.getWidth();
            float f1 = (float)super.getHeight() + (float)actionBarHeight;
            float f2 = bitmap.getWidth();
            float f3 = bitmap.getHeight();
            return Math.max(f / f2, f1 / f3);
        }
    }

    private float getMatrixScale(Matrix matrix)
    {
        float af[] = new float[9];
        matrix.getValues(af);
        float f = af[0];
        if (f == 0.0F)
        {
            f = 1.0F;
        }
        return f;
    }

    private float getScale(MotionEvent motionevent)
    {
        return getSpan(motionevent) / mSpan;
    }

    private float getSpan(MotionEvent motionevent)
    {
        float f = motionevent.getX(0) - motionevent.getX(1);
        float f1 = motionevent.getY(0) - motionevent.getY(1);
        return FloatMath.sqrt(f * f + f1 * f1);
    }

    private void init(Context context)
    {
        super.setOnTouchListener(this);
        setScaleType(android.widget.ImageView.ScaleType.MATRIX);
        setClickable(true);
        setEnabled(true);
        setFocusable(true);
        setFocusableInTouchMode(true);
        actionBarHeight = TransitUtil.getActionBarHeight((TransitBaseActivity)context);
    }

    private Bitmap loadBitmap(String s)
    {
        int i = super.getWidth();
        int j = super.getHeight() + actionBarHeight;
        return loadImage(s, new android.graphics.BitmapFactory.Options(), i, j);
    }

    private Bitmap loadImage(String s, android.graphics.BitmapFactory.Options options, int i, int j)
    {
        int k;
        int l;
        Matrix matrix;
        decode(options, s, true);
        k = options.outWidth;
        l = options.outHeight;
        matrix = new Matrix();
        int i1 = (new ExifInterface(s)).getAttributeInt("Orientation", 1);
        i1;
        JVM INSTR tableswitch 1 8: default 96
    //                   1 176
    //                   2 176
    //                   3 206
    //                   4 206
    //                   5 218
    //                   6 182
    //                   7 182
    //                   8 218;
           goto _L1 _L2 _L2 _L3 _L3 _L4 _L5 _L5 _L4
_L4:
        break MISSING_BLOCK_LABEL_218;
_L1:
        matrix = null;
_L6:
        Bitmap bitmap;
        if (chkSize(k, l, i, j))
        {
            options.inSampleSize = getBmpImageScale(k, l, i, j);
        } else
        {
            isSmall = true;
        }
        bitmap = decode(options, s, false);
        if (bitmap != null && matrix != null)
        {
            bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        }
        return bitmap;
_L2:
        matrix = null;
          goto _L6
_L5:
        try
        {
            k = options.outHeight;
            l = options.outWidth;
            matrix.postRotate(90F);
        }
        catch (IOException ioexception)
        {
            matrix = null;
        }
          goto _L6
_L3:
        matrix.postRotate(180F);
          goto _L6
        k = options.outHeight;
        l = options.outWidth;
        matrix.postRotate(270F);
          goto _L6
    }

    private boolean onTouchDragEvent(MotionEvent motionevent, int i)
    {
        i;
        JVM INSTR tableswitch 0 2: default 28
    //                   0 30
    //                   1 77
    //                   2 42;
           goto _L1 _L2 _L3 _L4
_L1:
        return false;
_L2:
        actionDown(motionevent);
        setMode(1);
        return true;
_L4:
        if (mMode == 1)
        {
            setMode(1);
            actionMove(motionevent);
            mMovePoint.set(motionevent.getX(), motionevent.getY());
            return true;
        }
          goto _L1
_L3:
        setMode(0);
        mSavedImageMatrix.set(super.getImageMatrix());
        return true;
    }

    private boolean onTouchEvent(MotionEvent motionevent, int i)
    {
        i;
        JVM INSTR tableswitch 0 2: default 28
    //                   0 30
    //                   1 59
    //                   2 52;
           goto _L1 _L2 _L3 _L4
_L1:
        return false;
_L2:
        mTouchPoint.set(motionevent.getX(), motionevent.getY());
        setTouchMode(3);
        return false;
_L4:
        setTouchMode(1);
        return false;
_L3:
        if (mTouchMode == 3)
        {
            return true;
        }
        if (mTouchMode == 1 && getDragSpan(motionevent) < 10F)
        {
            return true;
        }
        if (true) goto _L1; else goto _L5
_L5:
    }

    private boolean onTouchPointerEvent(MotionEvent motionevent, int i)
    {
        i;
        JVM INSTR tableswitch 2 6: default 36
    //                   2 51
    //                   3 36
    //                   4 36
    //                   5 38
    //                   6 65;
           goto _L1 _L2 _L1 _L1 _L3 _L4
_L1:
        return false;
_L3:
        actionPointerDown(motionevent);
        setMode(2);
        return true;
_L2:
        if (mMode == 2)
        {
            return actionPointerMove(motionevent);
        }
          goto _L1
_L4:
        setMode(0);
        mSavedImageMatrix.set(super.getImageMatrix());
        return true;
    }

    private void setInCenteringX(Bitmap bitmap, float f, Matrix matrix)
    {
        if (bitmap != null)
        {
            float f1 = super.getWidth();
            float f2 = f * (float)bitmap.getWidth();
            matrix.getValues(new float[9]);
            float f3 = f2 - f1;
            if (f3 > 0.0F)
            {
                setValueToImageMatrix(2, 0.0F - f3 / (2.0F * f), matrix);
                return;
            }
        }
    }

    private void setMode(int i)
    {
        mMode = i;
    }

    private void setTouchMode(int i)
    {
        mTouchMode = i;
    }

    private void setValueToImageMatrix(int i, float f, Matrix matrix)
    {
        float af[] = new float[9];
        matrix.getValues(af);
        af[i] = f;
        matrix.setValues(af);
    }

    public void cleanup()
    {
        if (mBitmap != null)
        {
            mBitmap.recycle();
            mBitmap = null;
            listener = null;
        }
        super.setImageBitmap(null);
        super.setImageMatrix(null);
    }

    protected void onDraw(Canvas canvas)
    {
        if (mBitmap == null)
        {
            return;
        }
        Matrix matrix = super.getImageMatrix();
        float af[] = new float[9];
        if (mMode == 2)
        {
            super.onDraw(canvas);
            matrix = super.getImageMatrix();
            canvas.drawColor(-1, android.graphics.PorterDuff.Mode.SRC_OVER);
        }
        matrix.getValues(af);
        chkXPosition(mBitmap, af[0], matrix);
        chkYPosition(mBitmap, af[4], matrix);
        super.setImageMatrix(matrix);
        super.onDraw(canvas);
    }

    protected void onLayout(boolean flag, int i, int j, int k, int l)
    {
        super.onLayout(flag, i, j, k, l);
        while (super.getWidth() == 0 || mBitmap != null) 
        {
            return;
        }
        drawInitial();
    }

    public boolean onTouch(View view, MotionEvent motionevent)
    {
        int i = 0xff & motionevent.getAction();
        if (onTouchEvent(motionevent, i))
        {
            post(new Runnable() {

                final CustomImageView this$0;

                public void run()
                {
                    if (listener != null)
                    {
                        listener.onClick(CustomImageView.this);
                    }
                }

            
            {
                this$0 = CustomImageView.this;
                super();
            }
            });
        }
        if (isLock)
        {
            return true;
        }
        boolean flag = onTouchDragEvent(motionevent, i);
        if (!flag && !isFitSize)
        {
            flag = onTouchPointerEvent(motionevent, i);
        }
        return flag;
    }

    public void setFit(boolean flag)
    {
        isFitSize = flag;
    }

    public void setImage(String s)
    {
        if (s != null)
        {
            mFilePath = s;
            if (super.getWidth() != 0)
            {
                drawInitial();
                return;
            }
        }
    }

    public void setListener(android.view.View.OnClickListener onclicklistener)
    {
        listener = onclicklistener;
    }

    public void setLock(boolean flag)
    {
        isLock = flag;
    }

    public void setMaxScale(float f)
    {
        if (mInitialScale > f)
        {
            return;
        } else
        {
            mMaxScale = f;
            return;
        }
    }

}

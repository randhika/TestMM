// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.yjvoice.screen;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;

// Referenced classes of package jp.co.yahoo.android.yjvoice.screen:
//            ViewEventListener

class LevelMeterView extends View
{

    private final int ANIMATION_INTERVAL;
    private int mAnimation;
    private Bitmap mBitmap1;
    private Bitmap mBitmap2;
    private Bitmap mBitmap3;
    private Bitmap mBitmap4;
    private Bitmap mBitmap5;
    private Handler mHandler;
    private int mLevelNum;
    private int mMaxVal;
    private int mMinVal;
    Paint mPaint;
    private int mValue;
    private LevelMeterView mView;
    ViewEventListener mViewEventListener;

    public LevelMeterView(Context context)
    {
        super(context);
        mMinVal = 0;
        mMaxVal = 32767;
        mValue = -1;
        mLevelNum = 11;
        mAnimation = -1;
        mHandler = new Handler();
        ANIMATION_INTERVAL = 100;
        init(context);
    }

    public LevelMeterView(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        mMinVal = 0;
        mMaxVal = 32767;
        mValue = -1;
        mLevelNum = 11;
        mAnimation = -1;
        mHandler = new Handler();
        ANIMATION_INTERVAL = 100;
        init(context);
    }

    public LevelMeterView(Context context, AttributeSet attributeset, int i)
    {
        super(context, attributeset, i);
        mMinVal = 0;
        mMaxVal = 32767;
        mValue = -1;
        mLevelNum = 11;
        mAnimation = -1;
        mHandler = new Handler();
        ANIMATION_INTERVAL = 100;
        init(context);
    }

    private void drawAnimation(Canvas canvas)
    {
        Rect rect;
        int k;
        int i = getWidth();
        int j = getHeight();
        rect = new Rect(0, 0, j, j);
        k = i / mLevelNum;
        mAnimation;
        JVM INSTR tableswitch 1 4: default 64
    //                   1 115
    //                   2 121
    //                   3 127
    //                   4 133;
           goto _L1 _L2 _L3 _L4 _L5
_L5:
        break MISSING_BLOCK_LABEL_133;
_L1:
        int l = 0;
_L6:
        int i1 = 0;
        while (i1 < mLevelNum) 
        {
            if (i1 > l)
            {
                canvas.drawBitmap(mBitmap2, null, rect, mPaint);
            } else
            {
                canvas.drawBitmap(mBitmap1, null, rect, mPaint);
            }
            i1++;
            rect.offset(k, 0);
        }
        break MISSING_BLOCK_LABEL_157;
_L2:
        l = 1;
          goto _L6
_L3:
        l = 2;
          goto _L6
_L4:
        l = 1;
          goto _L6
        l = 0;
          goto _L6
    }

    private void drawLevelMeter(Canvas canvas)
    {
        int i = getWidth();
        int j = getHeight();
        Rect rect = new Rect(0, 0, j, j);
        int k = i / mLevelNum;
        int l = (int)(10D * (2D * Math.sqrt((float)(mValue - mMinVal) / (float)(mMaxVal - mMinVal))));
        int i1 = 0;
        while (i1 < mLevelNum) 
        {
            if (i1 >= l)
            {
                canvas.drawBitmap(mBitmap2, null, rect, mPaint);
            } else
            if (i1 <= 6)
            {
                canvas.drawBitmap(mBitmap3, null, rect, mPaint);
            } else
            if (i1 <= 8)
            {
                canvas.drawBitmap(mBitmap4, null, rect, mPaint);
            } else
            {
                canvas.drawBitmap(mBitmap5, null, rect, mPaint);
            }
            i1++;
            rect.offset(k, 0);
        }
    }

    private int init(Context context)
    {
        byte byte0;
label0:
        {
            mView = this;
            mPaint = new Paint();
            mPaint.setAntiAlias(true);
            android.content.res.Resources resources = context.getResources();
            mBitmap1 = BitmapFactory.decodeResource(resources, R.drawable.dot1);
            mBitmap2 = BitmapFactory.decodeResource(resources, R.drawable.dot2);
            mBitmap3 = BitmapFactory.decodeResource(resources, R.drawable.dot3);
            mBitmap4 = BitmapFactory.decodeResource(resources, R.drawable.dot4);
            mBitmap5 = BitmapFactory.decodeResource(resources, R.drawable.dot5);
            if (mBitmap1 != null && mBitmap2 != null && mBitmap3 != null && mBitmap4 != null)
            {
                Bitmap bitmap = mBitmap5;
                byte0 = 0;
                if (bitmap != null)
                {
                    break label0;
                }
            }
            byte0 = -1;
        }
        return byte0;
    }

    public void doAnimation()
    {
        mAnimation = 0;
        Runnable runnable = new Runnable() {

            final LevelMeterView this$0;

            public void run()
            {
                int i = 
// JavaClassFileOutputException: get_constant: invalid tag

            
            {
                this$0 = LevelMeterView.this;
                super();
            }
        };
        mView.invalidate();
        mHandler.postDelayed(runnable, 100L);
    }

    protected void onDraw(Canvas canvas)
    {
        onDraw(canvas);
        if (mAnimation >= 0)
        {
            drawAnimation(canvas);
            return;
        } else
        {
            drawLevelMeter(canvas);
            return;
        }
    }

    protected void onSizeChanged(int i, int j, int k, int l)
    {
        onSizeChanged(i, j, k, l);
    }

    public int setValue(int i)
    {
        mValue = i;
        return 0;
    }

    public int setValueRange(int i, int j)
    {
        if (i > j)
        {
            return -1;
        } else
        {
            mMinVal = i;
            mMaxVal = j;
            return 0;
        }
    }



/*
    static int access$002(LevelMeterView levelmeterview, int i)
    {
        levelmeterview.mAnimation = i;
        return i;
    }

*/


/*
    static int access$008(LevelMeterView levelmeterview)
    {
        int i = levelmeterview.mAnimation;
        levelmeterview.mAnimation = i + 1;
        return i;
    }

*/


}

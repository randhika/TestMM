// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.maps.indoormap.data;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import jp.co.yahoo.android.maps.DoublePoint;
import jp.co.yahoo.android.maps.viewlayer.LayerInfo;

public class Tile
{

    private static int ALPHA = 20;
    private boolean disposed;
    private int mAlpha;
    private LayerInfo mLayerInfo;
    private Bitmap mMapbitimg;
    private Bitmap mMapbitlode;
    private Bitmap mMapblode;
    private Drawable mMapimg;
    private Drawable mMaplode;
    private Paint mPaint;
    private int mSize;
    private int mTileX;
    private int mTileY;
    private int mX;
    private int mY;

    public Tile(Bitmap bitmap, LayerInfo layerinfo)
    {
        mMapimg = null;
        mMaplode = null;
        mMapbitimg = null;
        mMapbitlode = null;
        mMapblode = null;
        mPaint = new Paint();
        disposed = false;
        mAlpha = 255;
        mLayerInfo = layerinfo;
        mMapblode = bitmap;
    }

    private static void clearMapimg(Drawable drawable)
    {
        if (drawable != null && (drawable instanceof BitmapDrawable))
        {
            Bitmap bitmap = ((BitmapDrawable)drawable).getBitmap();
            if (!bitmap.isRecycled())
            {
                bitmap.recycle();
            }
        }
    }

    private static Bitmap getBitmap(Drawable drawable)
    {
        if (drawable != null && (drawable instanceof BitmapDrawable))
        {
            return ((BitmapDrawable)drawable).getBitmap();
        } else
        {
            return null;
        }
    }

    private static boolean isAvalable(Drawable drawable)
    {
        boolean flag = false;
        if (drawable != null)
        {
            boolean flag1 = drawable instanceof BitmapDrawable;
            flag = false;
            if (flag1)
            {
                boolean flag2 = ((BitmapDrawable)drawable).getBitmap().isRecycled();
                flag = false;
                if (!flag2)
                {
                    flag = true;
                }
            }
        }
        return flag;
    }

    public boolean changeTilePos(double d, double d1, int i, int j, DoublePoint doublepoint, 
            int k, int l)
    {
        if (d <= 0.0D || (double)(mX + mSize) >= doublepoint.x) goto _L2; else goto _L1
_L1:
        boolean flag;
        mX = mX + i * mSize;
        mTileX = i + mTileX;
        flag = true;
_L8:
        if (d1 <= 0.0D || (double)(mY + mSize) >= doublepoint.y) goto _L4; else goto _L3
_L3:
        mY = mY + j * mSize;
        mTileY = mTileY - j;
        flag = true;
_L6:
        return flag;
_L2:
        int i1 = d != 0.0D;
        flag = false;
        if (i1 < 0)
        {
            int j1 = (double)mX != doublepoint.x + (double)k;
            flag = false;
            if (j1 > 0)
            {
                mX = mX - i * mSize;
                mTileX = mTileX - i;
                flag = true;
            }
        }
        continue; /* Loop/switch isn't completed */
_L4:
        if (d1 >= 0.0D || (double)mY <= doublepoint.y + (double)l) goto _L6; else goto _L5
_L5:
        mY = mY - j * mSize;
        mTileY = j + mTileY;
        return true;
        if (true) goto _L8; else goto _L7
_L7:
    }

    public void clearMapImage()
    {
        this;
        JVM INSTR monitorenter ;
        clearMapimg(mMapimg);
        mMapimg = null;
        clearMapimg(mMaplode);
        mMaplode = null;
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public void dispose()
    {
        this;
        JVM INSTR monitorenter ;
        disposed = true;
        clearMapImage();
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public boolean draw(Canvas canvas, int i, int j, int k, double d, int l, 
            int i1)
    {
        this;
        JVM INSTR monitorenter ;
        int j1;
        int k1;
        Drawable drawable;
        j1 = mX - j;
        k1 = mY - i;
        drawable = mMapimg;
        if (drawable != null)
        {
            break MISSING_BLOCK_LABEL_35;
        }
        drawable = mMaplode;
        if (drawable == null) goto _L2; else goto _L1
_L1:
        if (d == 1.0D) goto _L4; else goto _L3
_L3:
        (int)(0.5D + d * (double)mSize);
        (int)(0.5D + d * (double)mSize);
        int l1 = (int)(0.5D + ((double)j1 + (double)(j1 - l / 2) * (d - 1.0D)));
        int i2 = (int)(0.5D + ((double)k1 + (double)(k1 - i1 / 2) * (d - 1.0D)));
        canvas.save();
        canvas.translate(l1, i2);
        canvas.scale((float)d, (float)d);
        mMapimg.draw(canvas);
        canvas.restore();
_L8:
        boolean flag = true;
_L6:
        this;
        JVM INSTR monitorexit ;
        return flag;
_L4:
        Exception exception;
        try
        {
            canvas.save();
            canvas.translate(j1, k1);
            if (isAvalable(drawable))
            {
                drawable.draw(canvas);
            }
            canvas.restore();
            continue; /* Loop/switch isn't completed */
        }
        catch (Exception exception1) { }
        finally
        {
            this;
        }
_L2:
        flag = false;
        if (true) goto _L6; else goto _L5
_L5:
        throw exception;
        if (true) goto _L8; else goto _L7
_L7:
    }

    public boolean drawLoadImage(Canvas canvas, int i, int j, int k, double d, int l, 
            int i1)
    {
        return false;
    }

    public boolean drawbit(Canvas canvas, int i, int j, int k, float f, int l, int i1, 
            boolean flag)
    {
        int j1 = mX - j;
        int k1 = mY - i;
        Bitmap bitmap = mMapbitimg;
        boolean flag1 = false;
        if (bitmap != null)
        {
            boolean flag2 = mMapbitimg.isRecycled();
            flag1 = false;
            if (!flag2)
            {
                if (mAlpha < 255)
                {
                    mAlpha = mAlpha + ALPHA;
                }
                if (mAlpha > 255 || !flag)
                {
                    mAlpha = 255;
                }
                mPaint.setColor(Color.argb(255, 255, 0, 0));
                canvas.drawBitmap(mMapbitimg, j1, k1, mPaint);
                flag1 = true;
            }
        }
        return flag1;
    }

    public boolean equals(Object obj)
    {
        if (this != obj)
        {
            if (obj == null)
            {
                return false;
            }
            if (getClass() != obj.getClass())
            {
                return false;
            }
            Tile tile = (Tile)obj;
            if (mSize != tile.mSize)
            {
                return false;
            }
            if (mTileX != tile.mTileX)
            {
                return false;
            }
            if (mTileY != tile.mTileY)
            {
                return false;
            }
            if (mX != tile.mX)
            {
                return false;
            }
            if (mY != tile.mY)
            {
                return false;
            }
        }
        return true;
    }

    public Drawable getMapImage()
    {
        this;
        JVM INSTR monitorenter ;
        Drawable drawable = mMapimg;
        this;
        JVM INSTR monitorexit ;
        return drawable;
        Exception exception;
        exception;
        throw exception;
    }

    public int getScale()
    {
        return mLayerInfo.scale;
    }

    public int getSize()
    {
        return mSize;
    }

    public int getTileSid()
    {
        return mLayerInfo.scid;
    }

    public int getTileX()
    {
        return mTileX;
    }

    public int getTileY()
    {
        return mTileY;
    }

    public int getTileZ()
    {
        return mLayerInfo.level;
    }

    public int getX()
    {
        return mX;
    }

    public int getY()
    {
        return mY;
    }

    public int hashCode()
    {
        return 31 * (31 * (31 * (31 * (31 + mSize) + mTileX) + mTileY) + mX) + mY;
    }

    public boolean isDisposed()
    {
        return disposed;
    }

    public void resetPos(int i, int j, int k, int l, int i1)
    {
        this;
        JVM INSTR monitorenter ;
        mTileX = k;
        mTileY = l;
        mX = i;
        mY = j;
        mSize = i1;
        mMapimg = null;
        mMaplode = null;
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public void setLayerInfo(LayerInfo layerinfo)
    {
        mLayerInfo = layerinfo;
    }

    public void setMapBitImage(Bitmap bitmap)
    {
        Bitmap _tmp = mMapbitimg;
        mMapbitimg = bitmap;
        if (bitmap != null)
        {
            mMapbitlode = bitmap;
        }
    }

    public void setMapImage(Drawable drawable)
    {
        this;
        JVM INSTR monitorenter ;
        Drawable drawable1;
        drawable1 = mMapimg;
        mMapimg = drawable;
        if (drawable == null)
        {
            break MISSING_BLOCK_LABEL_21;
        }
        mMaplode = drawable;
        clearMapimg(drawable1);
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

}

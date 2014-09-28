// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.maps.weather.data;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import jp.co.yahoo.android.maps.DoublePoint;
import jp.co.yahoo.android.maps.viewlayer.LayerInfo;

public class Tile
{

    private LayerInfo mLayerInfo;
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
        mPaint = new Paint();
        mLayerInfo = layerinfo;
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
        mMapimg = null;
        mMaplode = null;
    }

    public boolean draw(Canvas canvas, int i, int j, int k, double d, int l, 
            int i1)
    {
        int j1 = mX - j;
        int k1 = mY - i;
        Drawable drawable = mMapimg;
        if (drawable == null)
        {
            drawable = mMaplode;
        }
        if (drawable != null)
        {
            if (d == 1.0D)
            {
                canvas.save();
                canvas.translate(j1, k1);
                drawable.draw(canvas);
                canvas.restore();
            }
            return true;
        } else
        {
            return false;
        }
    }

    public boolean drawLoadImage(Canvas canvas, int i, int j, int k, double d, int l, 
            int i1)
    {
        return false;
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
        return mMapimg;
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

    public void resetPos(int i, int j, int k, int l, int i1)
    {
        mTileX = k;
        mTileY = l;
        mX = i;
        mY = j;
        mSize = i1;
        mMapimg = null;
        mMaplode = null;
    }

    public void setLayerInfo(LayerInfo layerinfo)
    {
        mLayerInfo = layerinfo;
    }

    public void setMapImage(Drawable drawable)
    {
        mMapimg = drawable;
        if (drawable != null)
        {
            mMaplode = drawable;
        }
    }
}

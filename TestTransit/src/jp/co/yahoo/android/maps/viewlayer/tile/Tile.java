// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.maps.viewlayer.tile;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import jp.co.yahoo.android.maps.DoublePoint;
import jp.co.yahoo.android.maps.viewlayer.LayerInfo;

// Referenced classes of package jp.co.yahoo.android.maps.viewlayer.tile:
//            TileRequest

public class Tile
{

    private static int ALPHA = 20;
    public boolean isReqest;
    private int mAlpha;
    private boolean mInScreen;
    private int mIndoorLevel;
    private LayerInfo mLayerInfo;
    private int mMapType;
    private Bitmap mMapimg;
    private Bitmap mMaplode;
    private Paint mPaint;
    private String mSeamless;
    private int mSize;
    private String mStyle;
    private TileRequest mTileRequest;
    private int mTileX;
    private int mTileY;
    private int mX;
    private int mY;

    public Tile(Bitmap bitmap)
    {
        mMapimg = null;
        mMaplode = null;
        mPaint = new Paint();
        mInScreen = true;
        isReqest = false;
        mAlpha = 255;
        mSeamless = "off";
        mTileRequest = null;
        mMaplode = bitmap;
    }

    public boolean changeTilePos(double d, double d1, int i, int j, DoublePoint doublepoint, 
            int k, int l)
    {
        boolean flag = false;
        boolean flag1 = true;
        do
        {
            if (!flag1)
            {
                int i1;
                int j1;
                if ((double)(mX + mSize) < doublepoint.x || (double)mX > doublepoint.x + (double)k || (double)(mY + mSize) < doublepoint.y || (double)mY > doublepoint.y + (double)l)
                {
                    mInScreen = false;
                } else
                {
                    mInScreen = true;
                }
                if (flag && mTileRequest != null && mTileRequest.getState() == TileRequest.TILEREQUEST_WAIT)
                {
                    mTileRequest.setState(TileRequest.TILEREQUEST_REMOVE);
                    mTileRequest = null;
                }
                return flag;
            }
            if (d > 0.0D && (double)(mX + mSize) < doublepoint.x)
            {
                mX = mX + i * mSize;
                mTileX = i + mTileX;
                flag = true;
                flag1 = true;
            } else
            {
                i1 = d != 0.0D;
                flag1 = false;
                if (i1 < 0)
                {
                    j1 = (double)mX != doublepoint.x + (double)k;
                    flag1 = false;
                    if (j1 > 0)
                    {
                        mX = mX - i * mSize;
                        mTileX = mTileX - i;
                        flag = true;
                        flag1 = true;
                    }
                }
            }
            if (d1 > 0.0D && (double)(mY + mSize) < doublepoint.y)
            {
                mY = mY + j * mSize;
                mTileY = mTileY - j;
                flag = true;
                flag1 = true;
            } else
            if (d1 < 0.0D && (double)mY > doublepoint.y + (double)l)
            {
                mY = mY - j * mSize;
                mTileY = j + mTileY;
                flag = true;
                flag1 = true;
            }
        } while (true);
    }

    public boolean draw(Canvas canvas, int i, int j, int k, double d, int l, 
            int i1, boolean flag)
    {
        int j1 = mX - j;
        int k1 = mY - i;
        if (mMapimg != null && !mMapimg.isRecycled())
        {
            if (mAlpha < 255)
            {
                mAlpha = mAlpha + ALPHA;
            }
            if (mAlpha > 255 || !flag)
            {
                mAlpha = 255;
            }
            mPaint.setColor(Color.argb(mAlpha, 255, 255, 255));
            canvas.drawBitmap(mMapimg, j1, k1, mPaint);
            return true;
        } else
        {
            return false;
        }
    }

    public boolean drawLoadImage(Canvas canvas, int i, int j, int k, double d, int l, 
            int i1)
    {
        int j1 = mX - j;
        int k1 = mY - i;
        if (mMaplode != null && mMapimg == null)
        {
            Rect rect = new Rect(0, 0, 256, 256);
            Rect rect1 = new Rect(j1, k1, j1 + mSize, k1 + mSize);
            mPaint.setColor(Color.argb(255, 255, 255, 255));
            canvas.drawBitmap(mMaplode, rect, rect1, mPaint);
            return true;
        } else
        {
            return false;
        }
    }

    public int getIndoorLevel()
    {
        return mIndoorLevel;
    }

    public LayerInfo getLayerInfo()
    {
        return mLayerInfo;
    }

    public Bitmap getMapImage()
    {
        return mMapimg;
    }

    public int getMapType()
    {
        return mMapType;
    }

    public int getScale()
    {
        return mLayerInfo.scale;
    }

    public String getSeamless()
    {
        return mSeamless;
    }

    public int getSize()
    {
        return mSize;
    }

    public String getStyle()
    {
        return mStyle;
    }

    public int getTieZ()
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

    public int getX()
    {
        return mX;
    }

    public int getY()
    {
        return mY;
    }

    public boolean inScreen()
    {
        return mInScreen;
    }

    public boolean isDraw()
    {
        return mAlpha == 255;
    }

    public boolean isReqest()
    {
        if (isReqest && mTileRequest != null)
        {
            try
            {
                if (mTileRequest.getState() == TileRequest.TILEREQUEST_REMOVE)
                {
                    isReqest = false;
                }
            }
            catch (Exception exception)
            {
                isReqest = false;
            }
        }
        return isReqest;
    }

    public void resetPos(int i, int j, int k, int l, int i1, int j1, int k1, 
            String s, String s1)
    {
        mTileX = k;
        mTileY = l;
        mX = i;
        mY = j;
        mMapType = j1;
        mStyle = s;
        mSize = i1;
        mIndoorLevel = k1;
        mInScreen = true;
        mAlpha = 0;
        mSeamless = s1;
    }

    public void setIndoorLevel(int i)
    {
        mIndoorLevel = i;
    }

    public void setLayerInfo(LayerInfo layerinfo)
    {
        mLayerInfo = layerinfo;
    }

    public void setMapImage(Bitmap bitmap)
    {
        Bitmap bitmap1;
        if (bitmap != null && mMapimg == null)
        {
            mAlpha = 0;
        } else
        {
            mAlpha = 255;
        }
        bitmap1 = mMapimg;
        mMapimg = bitmap;
        if (bitmap1 != null)
        {
            bitmap1.recycle();
        }
    }

    public void setSeamless(String s)
    {
        mSeamless = s;
    }

    public void setTileRequest(TileRequest tilerequest)
    {
        mTileRequest = tilerequest;
    }

}

// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.maps.viewlayer.tile;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import java.util.Vector;
import jp.co.yahoo.android.maps.DoublePoint;
import jp.co.yahoo.android.maps.MapView;
import jp.co.yahoo.android.maps.viewlayer.BaseViewCtrl;
import jp.co.yahoo.android.maps.viewlayer.Coordinate;
import jp.co.yahoo.android.maps.viewlayer.MapLayerManager;

// Referenced classes of package jp.co.yahoo.android.maps.viewlayer.tile:
//            Tile, TileRequest

public class TileManager
{
    public static interface TileManagerListener
    {

        public abstract void removeTile(Tile tile);

        public abstract void requestNewTiles(Vector vector);
    }


    private Bitmap mBackImage;
    private Rect mBackImageRect;
    private BaseViewCtrl mBaseMapCtrl;
    private Bitmap mMaplode;
    private double mMovedX;
    private double mMovedY;
    private String mSeamless;
    private TileManagerListener mTileManagerListener;
    private Vector mTiles;
    private Vector mWkTiles;
    private int mXnum;
    private int mYnum;

    public TileManager(BaseViewCtrl baseviewctrl, TileManagerListener tilemanagerlistener)
    {
        mTiles = new Vector();
        mMaplode = null;
        mBaseMapCtrl = null;
        mTileManagerListener = null;
        mSeamless = "off";
        mBackImageRect = null;
        mBackImage = null;
        mWkTiles = new Vector(20);
        mTileManagerListener = tilemanagerlistener;
        mBaseMapCtrl = baseviewctrl;
        mMovedX = 0.0D;
        mMovedY = 0.0D;
    }

    public boolean changeTilePos(double d, double d1)
    {
        mMovedX = d + mMovedX;
        mMovedY = d1 + mMovedY;
        return false;
    }

    public void clearBackImage()
    {
        if (mBackImage != null)
        {
            mBackImage.recycle();
            mBackImage = null;
        }
    }

    public void clearTiles()
    {
        int i = 0;
        do
        {
            if (i >= mTiles.size())
            {
                mTiles.clear();
                return;
            }
            ((Tile)mTiles.elementAt(i)).setMapImage(null);
            i++;
        } while (true);
    }

    public void createBackImage()
    {
        if (mBaseMapCtrl.getHeight() != 0 && mBaseMapCtrl.getWidth() != 0)
        {
            Bitmap bitmap = Bitmap.createBitmap(mBaseMapCtrl.getWidth(), mBaseMapCtrl.getHeight(), android.graphics.Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(bitmap);
            drawBackImage(canvas, null, false);
            if (mBackImage != null)
            {
                mBackImage.recycle();
                mBackImage = null;
            }
            mBackImage = bitmap;
            DoublePoint doublepoint = mBaseMapCtrl.getTopLeftPos();
            int i = 0;
            while (i < mTiles.size()) 
            {
                ((Tile)mTiles.elementAt(i)).draw(canvas, (int)doublepoint.getY(), (int)doublepoint.getX(), 0, mBaseMapCtrl.getFactor(), mBaseMapCtrl.getWidth(), mBaseMapCtrl.getHeight(), false);
                i++;
            }
        }
    }

    public boolean draw(Canvas canvas, MapView mapview, boolean flag)
    {
        boolean flag1 = true;
        if (mBaseMapCtrl.getMapView().getZoomLevel() > -2) goto _L2; else goto _L1
_L1:
        return flag1;
_L2:
        mWkTiles.clear();
        if (mBackImageRect != null)
        {
            Rect rect = mBackImageRect;
            rect.left = (int)((double)rect.left - mMovedX);
            Rect rect1 = mBackImageRect;
            rect1.top = (int)((double)rect1.top - mMovedY);
            Rect rect2 = mBackImageRect;
            rect2.right = (int)((double)rect2.right - mMovedX);
            Rect rect3 = mBackImageRect;
            rect3.bottom = (int)((double)rect3.bottom - mMovedY);
        }
        if (mMovedX == 0.0D && mMovedY == 0.0D) goto _L4; else goto _L3
_L3:
        int i = 0;
_L7:
        if (i < mTiles.size()) goto _L5; else goto _L4
_L4:
        DoublePoint doublepoint;
        mTileManagerListener.requestNewTiles(mWkTiles);
        mMovedX = 0.0D;
        mMovedY = 0.0D;
        doublepoint = mBaseMapCtrl.getTopLeftPos();
        if (mBaseMapCtrl.getFactor() != 1.0F)
        {
            drawBackImage(canvas, mapview, flag);
            return flag1;
        }
        break; /* Loop/switch isn't completed */
_L5:
        Tile tile = (Tile)mTiles.elementAt(i);
        if (tile.changeTilePos(mMovedX, mMovedY, mXnum, mYnum, mBaseMapCtrl.getTopLeftPos(), mBaseMapCtrl.getWidth(), mBaseMapCtrl.getHeight()))
        {
            tile.setMapImage(null);
            tile.isReqest = false;
        }
        if (tile.inScreen() && tile.getMapImage() == null && !tile.isReqest())
        {
            mWkTiles.add(tile);
            tile.isReqest = true;
        }
        i++;
        if (true) goto _L7; else goto _L6
_L6:
        boolean flag2;
        int j;
        flag2 = false;
        j = 0;
_L9:
label0:
        {
            if (j < mTiles.size())
            {
                break label0;
            }
            int k;
            if (!flag2)
            {
                mBackImageRect = null;
            } else
            {
                drawBackImage(canvas, mapview, flag);
            }
            k = 0;
            while (k < mTiles.size()) 
            {
                Tile tile1 = (Tile)mTiles.elementAt(k);
                tile1.draw(canvas, (int)doublepoint.getY(), (int)doublepoint.getX(), 0, mBaseMapCtrl.getFactor(), mBaseMapCtrl.getWidth(), mBaseMapCtrl.getHeight(), true);
                if (!tile1.isDraw() && true)
                {
                    flag1 = false;
                }
                k++;
            }
        }
        if (true) goto _L1; else goto _L8
_L8:
        if (((Tile)mTiles.elementAt(j)).drawLoadImage(canvas, (int)doublepoint.getY(), (int)doublepoint.getX(), 0, mBaseMapCtrl.getFactor(), mBaseMapCtrl.getWidth(), mBaseMapCtrl.getHeight()))
        {
            flag2 = true;
        }
        j++;
          goto _L9
    }

    public void drawBackImage(Canvas canvas, MapView mapview, boolean flag)
    {
        if (mBackImage != null && mBackImageRect != null)
        {
            DoublePoint doublepoint = mBaseMapCtrl.getTopLeftPos();
            Rect rect = new Rect(0, 0, mBackImage.getWidth(), mBackImage.getHeight());
            new Rect((int)((double)mBackImageRect.left - doublepoint.getX()), (int)((double)mBackImageRect.top - doublepoint.getY()), mBackImageRect.right, mBackImageRect.bottom);
            canvas.drawBitmap(mBackImage, rect, mBackImageRect, null);
        }
    }

    public Bitmap getMaploadImage()
    {
        return mMaplode;
    }

    public Vector getTiles()
    {
        return mTiles;
    }

    public void initTiles()
    {
        int i;
        int j;
        int k;
        int j1;
        MapLayerManager maplayermanager;
        DoublePoint doublepoint1;
        double d8;
        double d10;
        int k1;
        int l1;
        int i2;
        int i3;
        int j3;
        int k3;
        i = 0;
        j = 0;
        k = mBaseMapCtrl.getTileSize();
        int l = mBaseMapCtrl.getWidth();
        int i1 = mBaseMapCtrl.getHeight();
        j1 = mTiles.size();
        DoublePoint doublepoint = mBaseMapCtrl.getTopLeftPos();
        maplayermanager = mBaseMapCtrl.getMapLayerManager();
        double d = (double)l / (double)k - 0.01D;
        double d1 = (double)i1 / (double)k - 0.01D;
        mXnum = 1 + (int)Math.ceil(d);
        mYnum = 1 + (int)Math.ceil(d1);
        double d2 = -1D * doublepoint.y;
        double d3 = doublepoint.x + (double)l;
        double d4 = d2 - (double)i1;
        double d5 = (d3 + doublepoint.x) / 2D;
        double d6 = (d4 + d2) / 2D;
        doublepoint1 = new DoublePoint(Math.floor(d5 / (double)k), Math.floor(d6 / (double)k));
        new DoublePoint(Coordinate.round((double)(l / 2) + (doublepoint1.x * (double)k - d5)), Coordinate.round((double)(-i1 / 2) + (doublepoint1.y * (double)k - d6)));
        int j2;
        int l3;
        if (mXnum % 2 != 0)
        {
            double d12 = Math.floor(mXnum / 2);
            d8 = doublepoint1.x - d12;
            d12 + doublepoint1.x;
        } else
        {
            double d7 = Math.floor(mXnum / 2);
            if (d5 / (double)k - doublepoint1.x > 0.45000000000000001D)
            {
                d8 = doublepoint1.x - (d7 - 1.0D);
                d7 + doublepoint1.x;
            } else
            {
                d8 = doublepoint1.x - d7;
                doublepoint1.x + (d7 - 1.0D);
            }
        }
        if (mYnum % 2 != 0)
        {
            double d11 = Math.floor(mYnum / 2);
            doublepoint1.y - d11;
            d10 = d11 + doublepoint1.y;
        } else
        {
            double d9 = Math.floor(mYnum / 2);
            if (d6 / (double)k - doublepoint1.y > 0.45000000000000001D)
            {
                doublepoint1.y - (d9 - 1.0D);
                d10 = d9 + doublepoint1.y;
            } else
            {
                doublepoint1.y - d9;
                d10 = doublepoint1.y + (d9 - 1.0D);
            }
        }
        k1 = (int)Math.floor((doublepoint.x + (double)(l / 2)) / (double)k);
        l1 = (int)Math.floor((doublepoint.y + (double)(i1 / 2)) / (double)k);
        i2 = 0;
_L1:
        j2 = mYnum;
        if (i2 >= j2)
        {
            i3 = 0;
            j3 = 0;
            k3 = j;
            break MISSING_BLOCK_LABEL_381;
        }
        k2 = 0;
_L2:
label0:
        {
            int l2 = mXnum;
            if (k2 < l2)
            {
                break label0;
            }
            i2++;
        }
          goto _L1
        if ((double)Coordinate.round(d8 + (double)k2) == doublepoint1.x && (double)Coordinate.round(d10 - (double)i2) == doublepoint1.y)
        {
            i = k1 - k2;
            j = l1 - i2;
        }
        k2++;
          goto _L2
_L4:
        int i4;
        int j4;
        l3 = j + mYnum;
        int k2;
        if (k3 >= l3)
        {
            mTileManagerListener.requestNewTiles(mTiles);
            return;
        }
        i4 = 0;
        j4 = i;
_L5:
label1:
        {
            int k4 = i + mXnum;
            if (j4 < k4)
            {
                break label1;
            }
            i3++;
            k3++;
        }
        if (true) goto _L4; else goto _L3
_L3:
        Tile tile;
        if (j1 <= j3)
        {
            tile = new Tile(mMaplode);
            tile.setMapImage(null);
            tile.isReqest = false;
            tile.setTileRequest(null);
            mTiles.addElement(tile);
        } else
        {
            tile = (Tile)mTiles.elementAt(j3);
            tile.setMapImage(null);
            tile.isReqest = false;
            tile.setTileRequest(null);
        }
        j3++;
        tile.setLayerInfo(maplayermanager.getNowLayer());
        tile.resetPos(j4 * k, k3 * k, Coordinate.round(d8 + (double)i4), Coordinate.round(d10 - (double)i3), k, maplayermanager.getNowMapType(), maplayermanager.getNowIndoorLevel(), maplayermanager.getNowStyle(), mSeamless);
        i4++;
        j4++;
          goto _L5
    }

    public void setBackImagePos(int i, int j)
    {
        setBackImagePos(i, j, mBaseMapCtrl.getFactor());
    }

    public void setBackImagePos(int i, int j, float f)
    {
        if (mBackImage == null)
        {
            return;
        }
        int k = (int)(0.5D + (double)(f * (float)mBackImage.getWidth()));
        int l = (int)(0.5D + (double)(f * (float)mBackImage.getHeight()));
        double d = (f * (float)(0 - i) + (float)i) - (float)(i - mBaseMapCtrl.getWidth() / 2) * (f - 1.0F);
        double d1 = (f * (float)(0 - j) + (float)j) - (float)(j - mBaseMapCtrl.getHeight() / 2) * (f - 1.0F);
        int i1 = (int)(0.5D + d);
        int j1 = (int)(0.5D + d1);
        if (mBackImageRect == null)
        {
            mBackImageRect = new Rect(i1, j1, i1 + k, j1 + l);
            return;
        } else
        {
            mBackImageRect.left = i1;
            mBackImageRect.top = j1;
            mBackImageRect.right = i1 + k;
            mBackImageRect.bottom = j1 + l;
            return;
        }
    }

    public void setImage(TileRequest tilerequest, Bitmap bitmap)
    {
        mBaseMapCtrl.getMapLayerManager();
        int i = 0;
        do
        {
            if (i >= mTiles.size())
            {
                bitmap.recycle();
                return;
            }
            Tile tile = (Tile)mTiles.elementAt(i);
            if (tilerequest.cmpTileId(tile.getTileX(), tile.getTileY(), tile.getTieZ(), tile.getMapType(), tile.getSize(), tile.getIndoorLevel(), tile.getStyle(), tile.getSeamless()))
            {
                tile.setTileRequest(null);
                tile.setMapImage(bitmap);
                return;
            }
            i++;
        } while (true);
    }

    public void setMaploadImage(Bitmap bitmap)
    {
        mMaplode = bitmap;
    }

    public boolean setSeamless(String s)
    {
        if (!mSeamless.equals(s))
        {
            mSeamless = s;
            return true;
        } else
        {
            return false;
        }
    }
}

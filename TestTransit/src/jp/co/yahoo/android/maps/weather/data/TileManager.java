// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.maps.weather.data;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import java.util.Vector;
import jp.co.yahoo.android.maps.DoublePoint;
import jp.co.yahoo.android.maps.MapView;
import jp.co.yahoo.android.maps.viewlayer.BaseViewCtrl;
import jp.co.yahoo.android.maps.viewlayer.Coordinate;

// Referenced classes of package jp.co.yahoo.android.maps.weather.data:
//            Tile

public class TileManager
{
    public static interface TileManagerListener
    {

        public abstract void removeTile(Tile tile);

        public abstract void requestNewTiles(Vector vector);
    }


    private Bitmap mBackImage;
    private Rect mBackImageRect;
    private BaseViewCtrl mBaseViewCtrl;
    private Bitmap mMaplode;
    private TileManagerListener mTileManagerListener;
    private Vector mTiles;
    private int mXnum;
    private int mYnum;

    public TileManager(BaseViewCtrl baseviewctrl, TileManagerListener tilemanagerlistener)
    {
        mTiles = new Vector();
        mMaplode = null;
        mBaseViewCtrl = null;
        mTileManagerListener = null;
        mBackImageRect = null;
        mBackImage = null;
        mTileManagerListener = tilemanagerlistener;
        mBaseViewCtrl = baseviewctrl;
    }

    public boolean changeTilePos(double d, double d1)
    {
        DoublePoint doublepoint = mBaseViewCtrl.getTopLeftPos();
        double d2 = 10000000D;
        Vector vector = new Vector();
        if (mBackImageRect != null)
        {
            Rect rect = mBackImageRect;
            rect.left = (int)((double)rect.left - d);
            Rect rect1 = mBackImageRect;
            rect1.top = (int)((double)rect1.top - d1);
            Rect rect2 = mBackImageRect;
            rect2.right = (int)((double)rect2.right - d);
            Rect rect3 = mBackImageRect;
            rect3.bottom = (int)((double)rect3.bottom - d1);
        }
        int i = 0;
        do
        {
            if (i >= mTiles.size())
            {
                mTileManagerListener.requestNewTiles(vector);
                return false;
            }
            Tile tile = (Tile)mTiles.elementAt(i);
            if (tile.changeTilePos(d, d1, mXnum, mYnum, mBaseViewCtrl.getTopLeftPos(), mBaseViewCtrl.getWidth(), mBaseViewCtrl.getHeight()))
            {
                tile.clearMapImage();
            }
            if (tile.getMapImage() == null)
            {
                double d3 = (double)tile.getX() - doublepoint.getX();
                double d4 = (double)tile.getY() - doublepoint.getY();
                double d5 = mBaseViewCtrl.getWidth();
                double d6 = mBaseViewCtrl.getHeight();
                double d7 = Math.sqrt(Math.pow(d3 - d5 / 2D, 2D) + Math.pow(d4 - d6 / 2D, 2D));
                if (d2 < d7)
                {
                    vector.add(tile);
                } else
                {
                    d2 = d7;
                    vector.add(0, tile);
                }
            }
            i++;
        } while (true);
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
        Bitmap bitmap = Bitmap.createBitmap(mBaseViewCtrl.getWidth(), mBaseViewCtrl.getHeight(), android.graphics.Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawBackImage(canvas, null, false);
        if (mBackImage != null)
        {
            mBackImage.recycle();
            mBackImage = null;
        }
        mBackImage = bitmap;
        DoublePoint doublepoint = mBaseViewCtrl.getTopLeftPos();
        int i = 0;
        do
        {
            if (i >= mTiles.size())
            {
                return;
            }
            ((Tile)mTiles.elementAt(i)).draw(canvas, (int)doublepoint.getY(), (int)doublepoint.getX(), 0, mBaseViewCtrl.getFactor(), mBaseViewCtrl.getWidth(), mBaseViewCtrl.getHeight());
            i++;
        } while (true);
    }

    public void draw(Canvas canvas, MapView mapview, boolean flag)
    {
        DoublePoint doublepoint;
        boolean flag1;
        int i;
        doublepoint = mBaseViewCtrl.getTopLeftPos();
        flag1 = false;
        i = 0;
_L3:
        if (i < mTiles.size()) goto _L2; else goto _L1
_L1:
        int j;
        if (!flag1)
        {
            mBackImageRect = null;
        } else
        {
            drawBackImage(canvas, mapview, flag);
        }
        j = 0;
_L4:
        if (j >= mTiles.size())
        {
            return;
        }
        break MISSING_BLOCK_LABEL_126;
_L2:
        if (((Tile)mTiles.elementAt(i)).drawLoadImage(canvas, (int)doublepoint.getY(), (int)doublepoint.getX(), 0, mBaseViewCtrl.getFactor(), mBaseViewCtrl.getWidth(), mBaseViewCtrl.getHeight()))
        {
            flag1 = true;
        }
        i++;
          goto _L3
        ((Tile)mTiles.elementAt(j)).draw(canvas, (int)doublepoint.getY(), (int)doublepoint.getX(), 0, mBaseViewCtrl.getFactor(), mBaseViewCtrl.getWidth(), mBaseViewCtrl.getHeight());
        j++;
          goto _L4
    }

    public void drawBackImage(Canvas canvas, MapView mapview, boolean flag)
    {
    }

    public Bitmap getMaploadImage()
    {
        return mMaplode;
    }

    public void initTiles()
    {
        if (mBaseViewCtrl != null) goto _L2; else goto _L1
_L1:
        jp.co.yahoo.android.maps.viewlayer.LayerInfo layerinfo;
        return;
_L2:
        if ((layerinfo = mBaseViewCtrl.getNowLayer()) == null) goto _L1; else goto _L3
_L3:
        int i;
        int j;
        int k;
        int j1;
        DoublePoint doublepoint;
        DoublePoint doublepoint1;
        double d6;
        double d8;
        int k1;
        int l1;
        int i2;
        int i3;
        int j3;
        int k3;
        double d9;
        Vector vector;
        int l4;
        i = 0;
        j = 0;
        k = mBaseViewCtrl.getTileSize();
        int l = mBaseViewCtrl.getWidth();
        int i1 = mBaseViewCtrl.getHeight();
        j1 = mTiles.size();
        doublepoint = mBaseViewCtrl.getTopLeftPos();
        mXnum = 1 + (int)Math.ceil((double)l / (double)k);
        mYnum = 1 + (int)Math.ceil((double)i1 / (double)k);
        double d = -1D * doublepoint.y;
        double d1 = doublepoint.x + (double)l;
        double d2 = d - (double)i1;
        double d3 = (d1 + doublepoint.x) / 2D;
        double d4 = (d2 + d) / 2D;
        doublepoint1 = new DoublePoint(Math.floor(d3 / (double)k), Math.floor(d4 / (double)k));
        new DoublePoint(Coordinate.round((double)(l / 2) + (doublepoint1.x * (double)k - d3)), Coordinate.round((double)(-i1 / 2) + (doublepoint1.y * (double)k - d4)));
        int j2;
        int l3;
        int i5;
        if (mXnum % 2 != 0)
        {
            double d16 = Math.floor(mXnum / 2);
            d6 = doublepoint1.x - d16;
            d16 + doublepoint1.x;
        } else
        {
            double d5 = Math.floor(mXnum / 2);
            if (d3 / (double)k - doublepoint1.x > 0.45000000000000001D)
            {
                d6 = doublepoint1.x - (d5 - 1.0D);
                d5 + doublepoint1.x;
            } else
            {
                d6 = doublepoint1.x - d5;
                doublepoint1.x + (d5 - 1.0D);
            }
        }
        if (mYnum % 2 != 0)
        {
            double d15 = Math.floor(mYnum / 2);
            doublepoint1.y - d15;
            d8 = d15 + doublepoint1.y;
        } else
        {
            double d7 = Math.floor(mYnum / 2);
            if (d4 / (double)k - doublepoint1.y > 0.45000000000000001D)
            {
                doublepoint1.y - (d7 - 1.0D);
                d8 = d7 + doublepoint1.y;
            } else
            {
                doublepoint1.y - d7;
                d8 = doublepoint1.y + (d7 - 1.0D);
            }
        }
        k1 = (int)Math.floor((doublepoint.x + (double)(l / 2)) / (double)k);
        l1 = (int)Math.floor((doublepoint.y + (double)(i1 / 2)) / (double)k);
        i2 = 0;
_L8:
        j2 = mYnum;
        if (i2 < j2) goto _L5; else goto _L4
_L4:
        i3 = 0;
        j3 = 0;
        k3 = j;
_L10:
        l3 = j + mYnum;
        if (k3 < l3) goto _L7; else goto _L6
_L6:
        d9 = 1000000D;
        vector = new Vector();
        l4 = 0;
_L12:
        i5 = mTiles.size();
        if (l4 >= i5)
        {
            mTileManagerListener.requestNewTiles(vector);
            return;
        }
        break MISSING_BLOCK_LABEL_845;
_L5:
        int k2 = 0;
_L9:
label0:
        {
            int l2 = mXnum;
            if (k2 < l2)
            {
                break label0;
            }
            i2++;
        }
          goto _L8
        if ((double)Coordinate.round(d6 + (double)k2) == doublepoint1.x && (double)Coordinate.round(d8 - (double)i2) == doublepoint1.y)
        {
            i = k1 - k2;
            j = l1 - i2;
        }
        k2++;
          goto _L9
_L7:
        int i4;
        int j4;
        i4 = 0;
        j4 = i;
_L11:
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
          goto _L10
        (int)((double)(j4 * k) - doublepoint.x);
        (int)((double)(k3 * k) - doublepoint.y);
        Tile tile;
        if (j1 <= j3)
        {
            tile = new Tile(mMaplode, layerinfo);
            mTiles.addElement(tile);
        } else
        {
            tile = (Tile)mTiles.elementAt(j3);
            tile.setMapImage(null);
        }
        j3++;
        tile.resetPos(j4 * k, k3 * k, Coordinate.round(d6 + (double)i4), Coordinate.round(d8 - (double)i3), k);
        tile.setLayerInfo(layerinfo);
        i4++;
        j4++;
          goto _L11
        Tile tile1 = (Tile)mTiles.elementAt(l4);
        double d10 = (double)tile1.getX() - doublepoint.getX();
        double d11 = (double)tile1.getY() - doublepoint.getY();
        double d12 = mBaseViewCtrl.getWidth();
        double d13 = mBaseViewCtrl.getHeight();
        double d14 = Math.sqrt(Math.pow(d10 - d12 / 2D, 2D) + Math.pow(d11 - d13 / 2D, 2D));
        if (d9 < d14)
        {
            vector.add(tile1);
        } else
        {
            d9 = d14;
            vector.add(0, tile1);
        }
        l4++;
          goto _L12
    }

    public boolean resetImage()
    {
        DoublePoint doublepoint = mBaseViewCtrl.getTopLeftPos();
        if (mTiles.size() == 0)
        {
            return false;
        }
        double d = 1000000D;
        Vector vector = new Vector();
        int i = 0;
        do
        {
            if (i >= mTiles.size())
            {
                mTileManagerListener.requestNewTiles(vector);
                return true;
            }
            Tile tile = (Tile)mTiles.elementAt(i);
            tile.setMapImage(null);
            double d1 = (double)tile.getX() - doublepoint.getX();
            double d2 = (double)tile.getY() - doublepoint.getY();
            double d3 = mBaseViewCtrl.getWidth();
            double d4 = mBaseViewCtrl.getHeight();
            double d5 = Math.sqrt(Math.pow(d1 - d3 / 2D, 2D) + Math.pow(d2 - d4 / 2D, 2D));
            if (d < d5)
            {
                vector.add(tile);
            } else
            {
                d = d5;
                vector.add(0, tile);
            }
            i++;
        } while (true);
    }

    public void setBackImagePos(int i, int j)
    {
        setBackImagePos(i, j, mBaseViewCtrl.getFactor());
    }

    public void setBackImagePos(int i, int j, float f)
    {
        int k = (int)(0.5D + (double)(f * (float)mBackImage.getWidth()));
        int l = (int)(0.5D + (double)(f * (float)mBackImage.getHeight()));
        double d = (f * (float)(0 - i) + (float)i) - (float)(i - mBaseViewCtrl.getWidth() / 2) * (f - 1.0F);
        double d1 = (f * (float)(0 - j) + (float)j) - (float)(j - mBaseViewCtrl.getHeight() / 2) * (f - 1.0F);
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

    public void setImage(int i, int j, int k, Drawable drawable)
    {
        int l = 0;
        do
        {
            if (l >= mTiles.size())
            {
                return;
            }
            Tile tile = (Tile)mTiles.elementAt(l);
            if (tile.getTileX() == i && tile.getTileY() == j && tile.getTileSid() == k)
            {
                tile.setMapImage(drawable);
            }
            l++;
        } while (true);
    }

    public void setMaploadImage(Bitmap bitmap)
    {
        mMaplode = bitmap;
    }
}

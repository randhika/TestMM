// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.maps.indoormap.data;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import java.util.Iterator;
import java.util.Vector;
import jp.co.yahoo.android.maps.DoublePoint;
import jp.co.yahoo.android.maps.MapView;
import jp.co.yahoo.android.maps.viewlayer.BaseViewCtrl;
import jp.co.yahoo.android.maps.viewlayer.Coordinate;

// Referenced classes of package jp.co.yahoo.android.maps.indoormap.data:
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

    private static void clearBitmap(Bitmap bitmap)
    {
        if (bitmap != null && !bitmap.isRecycled())
        {
            bitmap.recycle();
        }
    }

    public boolean changeTilePos(double d, double d1)
    {
        DoublePoint doublepoint = mBaseViewCtrl.getTopLeftPos();
        double d2 = 10000000D;
        Vector vector;
        vector = new Vector();
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
          goto _L1
_L10:
        int i;
        if (i < mTiles.size()) goto _L3; else goto _L2
_L2:
        if (mTileManagerListener != null)
        {
            mTileManagerListener.requestNewTiles(vector);
        }
          goto _L4
_L3:
        Tile tile;
        tile = (Tile)mTiles.elementAt(i);
        if (tile.changeTilePos(d, d1, mXnum, mYnum, mBaseViewCtrl.getTopLeftPos(), mBaseViewCtrl.getWidth(), mBaseViewCtrl.getHeight()))
        {
            tile.clearMapImage();
        }
        if (tile.getMapImage() != null) goto _L6; else goto _L5
_L5:
        double d7;
        double d3 = (double)tile.getX() - doublepoint.getX();
        double d4 = (double)tile.getY() - doublepoint.getY();
        double d5 = mBaseViewCtrl.getWidth();
        double d6 = mBaseViewCtrl.getHeight();
        d7 = Math.sqrt(Math.pow(d3 - d5 / 2D, 2D) + Math.pow(d4 - d6 / 2D, 2D));
        if (d2 >= d7) goto _L8; else goto _L7
_L7:
        vector.add(tile);
          goto _L6
_L8:
        d2 = d7;
        vector.add(0, tile);
          goto _L6
        Exception exception;
        exception;
          goto _L4
_L1:
        i = 0;
        continue; /* Loop/switch isn't completed */
_L4:
        return false;
_L6:
        i++;
        if (true) goto _L10; else goto _L9
_L9:
    }

    public void clearTiles()
    {
        mTiles.clear();
    }

    public void createBackImage()
    {
        int i;
        try
        {
            Bitmap bitmap = Bitmap.createBitmap(mBaseViewCtrl.getWidth(), mBaseViewCtrl.getHeight(), android.graphics.Bitmap.Config.ARGB_8888);
            drawBackImage(new Canvas(bitmap), null, false);
            if (mBackImage != null)
            {
                mBackImage.recycle();
                mBackImage = null;
            }
            mBackImage = bitmap;
            mBaseViewCtrl.getTopLeftPos();
        }
        catch (Exception exception)
        {
            return;
        }
        i = 0;
        if (i >= mTiles.size())
        {
            return;
        }
        (Tile)mTiles.elementAt(i);
        i++;
        if (false)
        {
        } else
        {
            break MISSING_BLOCK_LABEL_70;
        }
    }

    public void dispose()
    {
        Iterator iterator;
        clearBitmap(mBackImage);
        clearBitmap(mMaplode);
        iterator = mTiles.iterator();
_L3:
        boolean flag = iterator.hasNext();
        if (flag) goto _L2; else goto _L1
_L1:
        mTiles.clear();
        return;
_L2:
        ((Tile)iterator.next()).dispose();
          goto _L3
        Exception exception;
        exception;
          goto _L1
    }

    public void draw(Canvas canvas, MapView mapview, boolean flag)
    {
        DoublePoint doublepoint;
        boolean flag1;
        int i;
        int j;
        try
        {
            doublepoint = mBaseViewCtrl.getTopLeftPos();
        }
        catch (Exception exception)
        {
            return;
        }
        flag1 = false;
        i = 0;
        if (i < mTiles.size()) goto _L2; else goto _L1
_L1:
        if (flag1) goto _L4; else goto _L3
_L3:
        mBackImageRect = null;
          goto _L5
_L8:
        if (j >= mTiles.size())
        {
            return;
        }
          goto _L6
_L2:
        if (((Tile)mTiles.elementAt(i)).drawLoadImage(canvas, (int)doublepoint.getY(), (int)doublepoint.getX(), 0, mBaseViewCtrl.getFactor(), mBaseViewCtrl.getWidth(), mBaseViewCtrl.getHeight()))
        {
            flag1 = true;
        }
        break; /* Loop/switch isn't completed */
_L4:
        drawBackImage(canvas, mapview, flag);
          goto _L5
_L6:
        ((Tile)mTiles.elementAt(j)).draw(canvas, (int)doublepoint.getY(), (int)doublepoint.getX(), 0, mBaseViewCtrl.getFactor(), mBaseViewCtrl.getWidth(), mBaseViewCtrl.getHeight());
        j++;
        continue; /* Loop/switch isn't completed */
_L5:
        j = 0;
        if (true) goto _L8; else goto _L7
_L7:
        i++;
        if (false)
        {
        } else
        {
            break MISSING_BLOCK_LABEL_15;
        }
    }

    public void drawBackImage(Canvas canvas, MapView mapview, boolean flag)
    {
        if (mBackImage != null && mBackImageRect != null)
        {
            DoublePoint doublepoint = mBaseViewCtrl.getTopLeftPos();
            Rect rect = new Rect(0, 0, mBackImage.getWidth(), mBackImage.getHeight());
            new Rect((int)((double)mBackImageRect.left - doublepoint.getX()), (int)((double)mBackImageRect.top - doublepoint.getY()), mBackImageRect.right, mBackImageRect.bottom);
            mBaseViewCtrl.getMapView().getZoomLevel();
            canvas.drawBitmap(mBackImage, rect, mBackImageRect, null);
        }
    }

    public Bitmap getMaploadImage()
    {
        return mMaplode;
    }

    public void initTiles()
    {
        jp.co.yahoo.android.maps.viewlayer.LayerInfo layerinfo;
        int i;
        int j;
        int k;
        int l;
        int i1;
        int j1;
        DoublePoint doublepoint;
        double d;
        double d1;
        double d2;
        double d3;
        double d4;
        DoublePoint doublepoint1;
        double d5;
        double d6;
        double d7;
        double d8;
        int k1;
        int l1;
        int i2;
        int j2;
        int k2;
        int l2;
        int i3;
        int j3;
        int k3;
        int l3;
        int i4;
        int j4;
        double d9;
        Vector vector;
        int l4;
        int i5;
        try
        {
            if (mBaseViewCtrl == null)
            {
                return;
            }
        }
        catch (Exception exception)
        {
            return;
        }
        layerinfo = mBaseViewCtrl.getNowLayer();
        if (layerinfo == null) goto _L2; else goto _L1
_L1:
        i = 0;
        j = 0;
        k = mBaseViewCtrl.getTileSize();
        l = mBaseViewCtrl.getWidth();
        i1 = mBaseViewCtrl.getHeight();
        j1 = mTiles.size();
        doublepoint = mBaseViewCtrl.getTopLeftPos();
        mXnum = 1 + (int)Math.ceil((double)l / (double)k);
        mYnum = 1 + (int)Math.ceil((double)i1 / (double)k);
        d = -1D * doublepoint.y;
        d1 = doublepoint.x + (double)l;
        d2 = d - (double)i1;
        d3 = (d1 + doublepoint.x) / 2D;
        d4 = (d2 + d) / 2D;
        doublepoint1 = new DoublePoint(Math.floor(d3 / (double)k), Math.floor(d4 / (double)k));
        new DoublePoint(Coordinate.round((double)(l / 2) + (doublepoint1.x * (double)k - d3)), Coordinate.round((double)(-i1 / 2) + (doublepoint1.y * (double)k - d4)));
        if (mXnum % 2 == 0) goto _L4; else goto _L3
_L3:
        double d16 = Math.floor(mXnum / 2);
        d6 = doublepoint1.x - d16;
        d16 + doublepoint1.x;
_L13:
        if (mYnum % 2 == 0) goto _L6; else goto _L5
_L5:
        double d15 = Math.floor(mYnum / 2);
        doublepoint1.y - d15;
        d8 = d15 + doublepoint1.y;
_L14:
        k1 = (int)Math.floor((doublepoint.x + (double)(l / 2)) / (double)k);
        l1 = (int)Math.floor((doublepoint.y + (double)(i1 / 2)) / (double)k);
        i2 = 0;
_L15:
        j2 = mYnum;
        if (i2 < j2) goto _L8; else goto _L7
_L7:
        i3 = 0;
        j3 = 0;
        k3 = j;
_L16:
        l3 = j + mYnum;
        if (k3 < l3) goto _L10; else goto _L9
_L9:
        d9 = 1000000D;
        vector = new Vector();
        l4 = 0;
_L20:
        i5 = mTiles.size();
        if (l4 < i5) goto _L12; else goto _L11
_L11:
        mTileManagerListener.requestNewTiles(vector);
        return;
_L4:
label0:
        {
            d5 = Math.floor(mXnum / 2);
            if (d3 / (double)k - doublepoint1.x <= 0.45000000000000001D)
            {
                break label0;
            }
            d6 = doublepoint1.x - (d5 - 1.0D);
            d5 + doublepoint1.x;
        }
          goto _L13
        d6 = doublepoint1.x - d5;
        doublepoint1.x + (d5 - 1.0D);
          goto _L13
_L6:
label1:
        {
            d7 = Math.floor(mYnum / 2);
            if (d4 / (double)k - doublepoint1.y <= 0.45000000000000001D)
            {
                break label1;
            }
            doublepoint1.y - (d7 - 1.0D);
            d8 = d7 + doublepoint1.y;
        }
          goto _L14
        doublepoint1.y - d7;
        d8 = doublepoint1.y + (d7 - 1.0D);
          goto _L14
_L19:
        l2 = mXnum;
label2:
        {
            if (k2 < l2)
            {
                break label2;
            }
            i2++;
        }
          goto _L15
_L17:
        k4 = i + mXnum;
label3:
        {
            if (i4 < k4)
            {
                break label3;
            }
            i3++;
            k3++;
        }
          goto _L16
        (int)((double)(i4 * k) - doublepoint.x);
        (int)((double)(k3 * k) - doublepoint.y);
        if (j1 > j3)
        {
            break MISSING_BLOCK_LABEL_814;
        }
        tile = new Tile(mMaplode, layerinfo);
        mTiles.addElement(tile);
_L18:
        j3++;
        tile.resetPos(i4 * k, k3 * k, Coordinate.round(d6 + (double)j4), Coordinate.round(d8 - (double)i3), k);
        tile.setLayerInfo(layerinfo);
        j4++;
        i4++;
          goto _L17
        tile = (Tile)mTiles.elementAt(j3);
        tile.setMapImage(null);
          goto _L18
_L12:
        tile1 = (Tile)mTiles.elementAt(l4);
        double d10 = (double)tile1.getX() - doublepoint.getX();
        double d11 = (double)tile1.getY() - doublepoint.getY();
        double d12 = mBaseViewCtrl.getWidth();
        double d13 = mBaseViewCtrl.getHeight();
        d14 = Math.sqrt(Math.pow(d10 - d12 / 2D, 2D) + Math.pow(d11 - d13 / 2D, 2D));
        if (d9 >= d14)
        {
            break MISSING_BLOCK_LABEL_954;
        }
        vector.add(tile1);
        break MISSING_BLOCK_LABEL_993;
        d9 = d14;
        vector.add(0, tile1);
        break MISSING_BLOCK_LABEL_993;
_L2:
        return;
_L8:
        k2 = 0;
          goto _L19
        int k4;
        Tile tile;
        Tile tile1;
        double d14;
        if ((double)Coordinate.round(d6 + (double)k2) == doublepoint1.x && (double)Coordinate.round(d8 - (double)i2) == doublepoint1.y)
        {
            i = k1 - k2;
            j = l1 - i2;
        }
        k2++;
          goto _L19
_L10:
        i4 = i;
        j4 = 0;
          goto _L17
        l4++;
          goto _L20
    }

    public boolean resetImage()
    {
        DoublePoint doublepoint = mBaseViewCtrl.getTopLeftPos();
        int i;
        if (mTiles.size() == 0)
        {
            return false;
        }
        double d = 1000000D;
        Vector vector;
        Tile tile;
        double d1;
        double d2;
        double d3;
        double d4;
        double d5;
        try
        {
            vector = new Vector();
        }
        catch (Exception exception)
        {
            return false;
        }
        i = 0;
_L2:
        if (i < mTiles.size())
        {
            break MISSING_BLOCK_LABEL_61;
        }
        mTileManagerListener.requestNewTiles(vector);
        return true;
        tile = (Tile)mTiles.elementAt(i);
        tile.setMapImage(null);
        d1 = (double)tile.getX() - doublepoint.getX();
        d2 = (double)tile.getY() - doublepoint.getY();
        d3 = mBaseViewCtrl.getWidth();
        d4 = mBaseViewCtrl.getHeight();
        d5 = Math.sqrt(Math.pow(d1 - d3 / 2D, 2D) + Math.pow(d2 - d4 / 2D, 2D));
        if (d >= d5)
        {
            break MISSING_BLOCK_LABEL_181;
        }
        vector.add(tile);
        break MISSING_BLOCK_LABEL_198;
        d = d5;
        vector.add(0, tile);
        i++;
        if (true) goto _L2; else goto _L1
_L1:
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

    public boolean setBitImage(int i, int j, int k, Bitmap bitmap)
    {
        boolean flag;
        int l;
        flag = false;
        l = 0;
        do
        {
label0:
            {
                if (l >= mTiles.size())
                {
                    return flag;
                }
                try
                {
                    Tile tile = (Tile)mTiles.elementAt(l);
                    if (tile.getTileX() != i || tile.getTileY() != j || tile.getTileSid() != k)
                    {
                        break label0;
                    }
                    tile.setMapBitImage(bitmap);
                }
                catch (Exception exception)
                {
                    return flag;
                }
                flag = true;
            }
            l++;
        } while (true);
    }

    public boolean setImage(int i, int j, int k, Drawable drawable)
    {
        boolean flag;
        int l;
        flag = false;
        l = 0;
        do
        {
label0:
            {
                if (l >= mTiles.size())
                {
                    return flag;
                }
                try
                {
                    Tile tile = (Tile)mTiles.elementAt(l);
                    if (tile.getTileX() != i || tile.getTileY() != j || tile.getTileSid() != k)
                    {
                        break label0;
                    }
                    tile.setMapImage(drawable);
                }
                catch (Exception exception)
                {
                    return flag;
                }
                flag = true;
            }
            l++;
        } while (true);
    }

    public void setMaploadImage(Bitmap bitmap)
    {
        Bitmap bitmap1 = mMaplode;
        mMaplode = bitmap;
        clearBitmap(bitmap1);
    }
}

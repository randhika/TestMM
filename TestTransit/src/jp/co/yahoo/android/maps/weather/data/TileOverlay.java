// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.maps.weather.data;

import android.app.Activity;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import java.util.Vector;
import jp.co.yahoo.android.maps.DoublePoint;
import jp.co.yahoo.android.maps.GeoPoint;
import jp.co.yahoo.android.maps.MapView;
import jp.co.yahoo.android.maps.Overlay;
import jp.co.yahoo.android.maps.viewlayer.BaseViewCtrl;

// Referenced classes of package jp.co.yahoo.android.maps.weather.data:
//            TileManager, Tile

public class TileOverlay extends Overlay
    implements TileManager.TileManagerListener
{

    private int mHeight;
    private MapView mMapView;
    private int mNowZLevel;
    private TileManager mTileManager;
    private DoublePoint mTopLeft;
    private int mWidth;

    public TileOverlay(Activity activity)
    {
        mTileManager = null;
        mHeight = 0;
        mWidth = 0;
        mTopLeft = new DoublePoint();
        mNowZLevel = 0;
        mMapView = null;
    }

    public void clearTiles()
    {
        if (mTileManager != null)
        {
            mTileManager.clearTiles();
        }
    }

    public void draw(Canvas canvas, MapView mapview, boolean flag)
    {
        mMapView = mapview;
        BaseViewCtrl baseviewctrl = (BaseViewCtrl)mapview.getProjection();
        int i = mapview.getZoomLevel();
        if (mHeight != canvas.getHeight() || mWidth != canvas.getWidth() || i != mNowZLevel)
        {
            mTopLeft.x = baseviewctrl.getTopLeftPos().getX();
            mTopLeft.y = baseviewctrl.getTopLeftPos().getY();
            mHeight = canvas.getHeight();
            mWidth = canvas.getWidth();
            if (mTileManager == null)
            {
                mTileManager = new TileManager(baseviewctrl, this);
            }
            mTileManager.initTiles();
        }
        DoublePoint doublepoint = baseviewctrl.getTopLeftPos();
        double d = doublepoint.getX() - mTopLeft.getX();
        double d1 = doublepoint.getY() - mTopLeft.getY();
        mTileManager.changeTilePos(d, d1);
        mTileManager.draw(canvas, mapview, flag);
        mTopLeft.x = doublepoint.getX();
        mTopLeft.y = doublepoint.getY();
        mNowZLevel = i;
    }

    public GeoPoint getCenter()
    {
        return null;
    }

    public int getLatSpanE6()
    {
        return 0;
    }

    public int getLonSpanE6()
    {
        return 0;
    }

    public void initTiles()
    {
        if (mTileManager != null)
        {
            mTileManager.initTiles();
        }
    }

    public boolean onTap(GeoPoint geopoint, MapView mapview)
    {
        return false;
    }

    public void removeTile(Tile tile)
    {
    }

    public void repaint()
    {
        if (mMapView != null)
        {
            mMapView.reDraw();
        }
    }

    public void requestNewTiles(Vector vector)
    {
    }

    public boolean resetTileImage()
    {
        if (mTileManager != null)
        {
            return mTileManager.resetImage();
        } else
        {
            return false;
        }
    }

    public void setTileImage(Drawable drawable, int i, int j, int k)
    {
        mTileManager.setImage(i, j, k, drawable);
    }
}

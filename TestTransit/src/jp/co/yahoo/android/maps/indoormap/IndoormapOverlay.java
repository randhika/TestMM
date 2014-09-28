// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.maps.indoormap;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import java.io.ByteArrayInputStream;
import java.util.Vector;
import jp.co.yahoo.android.maps.MapView;
import jp.co.yahoo.android.maps.indoormap.data.IndoormapHttpLoader;
import jp.co.yahoo.android.maps.indoormap.data.IndoormapRequest;
import jp.co.yahoo.android.maps.indoormap.data.Tile;
import jp.co.yahoo.android.maps.indoormap.data.TileManager;
import jp.co.yahoo.android.maps.indoormap.data.TileOverlay;
import jp.co.yahoo.android.maps.viewlayer.BaseViewCtrl;
import jp.co.yahoo.android.maps.viewlayer.ScaleUtils;

// Referenced classes of package jp.co.yahoo.android.maps.indoormap:
//            IndoorFloorList

public class IndoormapOverlay extends TileOverlay
    implements jp.co.yahoo.android.maps.indoormap.data.IndoormapHttpLoader.IndoormapHttpLoaderListener
{
    public static interface IndoormapOverlayListener
    {

        public abstract void errorUpdateIndoormap(IndoormapOverlay indoormapoverlay, int i);

        public abstract void finishUpdateIndoormap(IndoormapOverlay indoormapoverlay);
    }


    public static final String IndoorMapTypeGray = "idg";
    public static final String IndoorMapTypeStandard = "ids";
    public int displayZoomLevel;
    private boolean disposed;
    private IndoorFloorList indoorFloorList;
    private TileManager mIndoorTileManager;
    private IndoormapHttpLoader mIndoormapHttpLoader;
    private IndoormapOverlayListener mIndoormapOverlayListener;
    private int mTileSize;
    private MapView mapView;
    private boolean visible;

    public IndoormapOverlay(Activity activity, MapView mapview, BaseViewCtrl baseviewctrl)
    {
        super(activity);
        mTileSize = 256;
        mIndoormapHttpLoader = null;
        mIndoormapOverlayListener = null;
        indoorFloorList = new IndoorFloorList();
        visible = true;
        mapView = null;
        disposed = false;
        displayZoomLevel = 0;
        mapView = mapview;
        mIndoormapHttpLoader = new IndoormapHttpLoader(this, baseviewctrl);
        mIndoorTileManager = new TileManager(baseviewctrl, this);
        setTileSize(baseviewctrl.getTileSize());
    }

    private static void clearDrawable(Drawable drawable)
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

    private void setTileSize(int i)
    {
        mTileSize = i;
    }

    public void addFloor(int i, int j, String s)
    {
        indoorFloorList.addFloor(i, j, s);
    }

    public void dispose()
    {
        if (!disposed)
        {
            disposed = true;
            super.dispose();
            if (mIndoormapHttpLoader != null)
            {
                mIndoormapHttpLoader.dispose();
                mIndoormapHttpLoader = null;
            }
            indoorFloorList.removeAllFloors();
            mIndoormapOverlayListener = null;
            mapView = null;
        }
    }

    public boolean empty()
    {
        return indoorFloorList.size() == 0;
    }

    public boolean endAllIndoormapHttpLoader(IndoormapHttpLoader indoormaphttploader)
    {
        if (mIndoormapOverlayListener != null)
        {
            mIndoormapOverlayListener.finishUpdateIndoormap(this);
        }
        return false;
    }

    public boolean endIndoormapHttpLoader(byte abyte0[], IndoormapRequest indoormaprequest)
    {
        ByteArrayInputStream bytearrayinputstream;
        Drawable drawable;
        if (!getVisible())
        {
            break MISSING_BLOCK_LABEL_158;
        }
        bytearrayinputstream = new ByteArrayInputStream(abyte0);
        drawable = null;
        Bitmap bitmap;
        drawable = Drawable.createFromStream(bytearrayinputstream, "");
        android.graphics.BitmapFactory.Options options = new android.graphics.BitmapFactory.Options();
        options.inPurgeable = true;
        bitmap = BitmapFactory.decodeByteArray(abyte0, 0, abyte0.length, options);
        if (drawable == null)
        {
            break MISSING_BLOCK_LABEL_91;
        }
        int i;
        int j;
        i = indoormaprequest.getTileSize();
        j = indoormaprequest.getTileSize();
        if (i <= 0 || j <= 0)
        {
            break MISSING_BLOCK_LABEL_91;
        }
        drawable.setBounds(0, 0, i, j);
        if (bitmap != null)
        {
            try
            {
                setTileBitImage(bitmap, indoormaprequest.getTileX(), indoormaprequest.getTileY(), indoormaprequest.getTileZ());
            }
            catch (IllegalArgumentException illegalargumentexception) { }
        }
        if (bytearrayinputstream != null)
        {
            try
            {
                bytearrayinputstream.close();
            }
            catch (Exception exception) { }
        }
        if (drawable != null)
        {
            if (!setTileImage(drawable, indoormaprequest.getTileX(), indoormaprequest.getTileY(), indoormaprequest.getTileZ()))
            {
                clearDrawable(drawable);
            }
            repaint();
        }
        return false;
    }

    public boolean errorIndoormapHttpLoader(IndoormapHttpLoader indoormaphttploader)
    {
        if (mIndoormapOverlayListener != null)
        {
            mIndoormapOverlayListener.errorUpdateIndoormap(this, 1);
        }
        return false;
    }

    protected void finalize()
        throws Throwable
    {
        super.finalize();
    }

    public boolean getVisible()
    {
        return visible;
    }

    public void indoorclearTiles()
    {
        clearTiles();
    }

    public void removeAllFloors()
    {
        indoorFloorList.removeAllFloors();
    }

    public void removeFloor(int i, int j)
    {
        indoorFloorList.removeFloor(i, j);
    }

    public void removeFloors(int i)
    {
        indoorFloorList.removeFloors(i);
    }

    public void removeTile(Tile tile)
    {
    }

    public void requestNewTiles(Vector vector)
    {
        if (vector == null) goto _L2; else goto _L1
_L1:
        int i = 0;
_L5:
        if (i < vector.size()) goto _L3; else goto _L2
_L2:
        return;
_L3:
        Tile tile = (Tile)vector.get(i);
        if (tile != null)
        {
            try
            {
                if (!tile.isDisposed() && getVisible() && indoorFloorList.size() > 0 && ScaleUtils.isExistIndoormapZoomLevel(tile.getTileZ()))
                {
                    String s = indoorFloorList.getStyleString();
                    IndoormapRequest indoormaprequest = new IndoormapRequest(tile.getTileX(), tile.getTileY(), tile.getTileSid(), mTileSize, s);
                    if (mIndoormapHttpLoader != null)
                    {
                        mIndoormapHttpLoader.addTileRequest(indoormaprequest);
                    }
                }
            }
            catch (Exception exception) { }
        }
        i++;
        if (true) goto _L5; else goto _L4
_L4:
    }

    public void setIndoormapOverlayListener(IndoormapOverlayListener indoormapoverlaylistener)
    {
        mIndoormapOverlayListener = indoormapoverlaylistener;
    }

    public void setOnlyVisible(boolean flag)
    {
        visible = flag;
    }

    public void setVisible(boolean flag)
    {
        visible = flag;
        if (mapView != null)
        {
            mapView.updateIndoormapOverlay(flag);
        }
    }

    public void updateIndoormap()
    {
        updateIndoormap(false);
    }

    public void updateIndoormap(boolean flag)
    {
        if (mIndoormapHttpLoader != null)
        {
            mIndoormapHttpLoader.removeAllTileRequests();
        }
        if (flag || !visible)
        {
            resetTileImage();
            initTiles();
        }
        repaint();
    }
}

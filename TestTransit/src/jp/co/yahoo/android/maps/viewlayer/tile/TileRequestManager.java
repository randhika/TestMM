// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.maps.viewlayer.tile;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import java.util.Vector;
import jp.co.yahoo.android.maps.MapView;
import jp.co.yahoo.android.maps.test.TimeChecker;
import jp.co.yahoo.android.maps.viewlayer.Attestation;
import jp.co.yahoo.android.maps.viewlayer.BaseViewCtrl;
import jp.co.yahoo.android.maps.viewlayer.Coordinate;

// Referenced classes of package jp.co.yahoo.android.maps.viewlayer.tile:
//            TileCacheManager, Tile, TileRequest, TileHttpLoader

public class TileRequestManager extends Thread
    implements TileHttpLoader.TileHttpLoaderListener, TileCacheManager.TileCacheListener
{
    public static interface TileRequestManagerListener
    {

        public abstract void endLoadImage(Bitmap bitmap, TileRequest tilerequest);
    }


    private static int THREAD_MAX = 2;
    private Object lock;
    private BaseViewCtrl mMapCtrl;
    private boolean mThread;
    private TileCacheManager mTileCacheManager;
    private Vector mTileHttpLoaders;
    private TileRequestManagerListener mTileRequestManagerListener;
    private Vector mtileRequest;
    private Vector wkVect;

    public TileRequestManager(BaseViewCtrl baseviewctrl, TileRequestManagerListener tilerequestmanagerlistener)
    {
        super("TileRequestManager");
        mtileRequest = new Vector();
        mTileHttpLoaders = new Vector();
        mMapCtrl = null;
        mTileRequestManagerListener = null;
        mThread = false;
        lock = new Object();
        mTileCacheManager = null;
        wkVect = new Vector();
        mTileRequestManagerListener = tilerequestmanagerlistener;
        mMapCtrl = baseviewctrl;
        mTileCacheManager = new TileCacheManager(mMapCtrl.getContext(), this);
    }

    private TileRequest addRequest(int i, int j, int k, int l, int i1, int j1, String s, 
            String s1)
    {
        TileRequest tilerequest = getTileRequest(i, j, k, l, i1, j1, s, s1);
        if (tilerequest != null)
        {
            return tilerequest;
        }
        TileRequest tilerequest1;
        if (l == MapView.MapTypeOSM)
        {
            tilerequest1 = createRequest(i, j, k, l, i1, s, j1, (new StringBuilder(String.valueOf(i))).append("-").append(j).toString(), s1);
        } else
        {
            tilerequest1 = createRequest(i, j, k, l, i1, s, j1, (new StringBuilder(String.valueOf(i))).append("-").append(j).toString(), s1);
        }
        return tilerequest1;
    }

    private void startThread()
    {
        Object obj = lock;
        obj;
        JVM INSTR monitorenter ;
        if (getState() != Thread.State.NEW)
        {
            break MISSING_BLOCK_LABEL_29;
        }
        mThread = true;
        start();
_L2:
        return;
        lock.notify();
        if (true) goto _L2; else goto _L1
_L1:
        Exception exception;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public void addRequestTileList(Vector vector)
    {
        if (vector.size() <= 0) goto _L2; else goto _L1
_L1:
        int i = 0;
_L6:
        if (i < vector.size()) goto _L4; else goto _L3
_L3:
        removeRequestStateIdol();
        startThread();
_L2:
        return;
_L4:
        wkVect.clear();
        Tile tile = (Tile)vector.get(i);
        TileRequest tilerequest = getTileRequest(tile.getTileX(), tile.getTileY(), tile.getTieZ(), tile.getMapType(), tile.getIndoorLevel(), tile.getSize(), tile.getStyle(), tile.getSeamless());
        if (tilerequest != null)
        {
            tile.setTileRequest(tilerequest);
            if (tilerequest.getState() == TileRequest.TILEREQUEST_IDOL)
            {
                tilerequest.setState(TileRequest.TILEREQUEST_WAIT);
            }
        } else
        {
            TileRequest tilerequest1 = addRequest(tile.getTileX(), tile.getTileY(), tile.getTieZ(), tile.getMapType(), tile.getSize(), tile.getIndoorLevel(), tile.getStyle(), tile.getSeamless());
            tilerequest1.setPlaneXY(tile.getX(), tile.getY());
            tilerequest1.setLayerInfo(tile.getLayerInfo());
            tile.setTileRequest(tilerequest1);
        }
        i++;
        if (true) goto _L6; else goto _L5
_L5:
    }

    public boolean checkActiveRequest()
    {
        int i = 0;
        int j = 0;
        do
        {
            if (j >= mtileRequest.size())
            {
                return false;
            }
            if (((TileRequest)mtileRequest.get(j)).getState() == TileRequest.TILEREQUEST_WAIT)
            {
                i++;
            }
            if (i >= 2)
            {
                return true;
            }
            j++;
        } while (true);
    }

    public void clear()
    {
        mtileRequest.clear();
    }

    public TileRequest createRequest(int i, int j, int k, int l, int i1, String s, int j1, 
            String s1, String s2)
    {
        TileRequest tilerequest = getRemoveRequest();
        if (tilerequest == null)
        {
            tilerequest = new TileRequest(i, j, k, l, i1, s, j1, s1, s2);
            mtileRequest.add(tilerequest);
        } else
        {
            tilerequest.resetRequest(i, j, k, l, i1, s, j1, s1, s2);
        }
        tilerequest.setState(TileRequest.TILEREQUEST_WAIT);
        return tilerequest;
    }

    public boolean endAllTileHttpLoader(TileHttpLoader tilehttploader, Vector vector)
    {
        mTileHttpLoaders.remove(tilehttploader);
        startThread();
        return false;
    }

    public boolean endTileCacheLoad(Bitmap bitmap, TileRequest tilerequest)
    {
        if (bitmap == null)
        {
            tilerequest.setState(TileRequest.TILEREQUEST_WAIT);
        } else
        if (bitmap != null)
        {
            mTileRequestManagerListener.endLoadImage(bitmap, tilerequest);
            tilerequest.setState(TileRequest.TILEREQUEST_REMOVE);
            return false;
        }
        return false;
    }

    public boolean endTileHttpLoader(byte abyte0[], TileRequest tilerequest)
    {
        Bitmap bitmap;
        try
        {
            android.graphics.BitmapFactory.Options options = new android.graphics.BitmapFactory.Options();
            options.inPurgeable = true;
            bitmap = BitmapFactory.decodeByteArray(abyte0, 0, abyte0.length, options);
        }
        catch (IllegalArgumentException illegalargumentexception)
        {
            return false;
        }
        if (bitmap != null)
        {
            mTileRequestManagerListener.endLoadImage(bitmap, tilerequest);
            mTileCacheManager.saveCache(tilerequest, abyte0);
            tilerequest.setState(TileRequest.TILEREQUEST_REMOVE);
        }
        return false;
    }

    public boolean errorTileHttpLoader(TileHttpLoader tilehttploader, Vector vector)
    {
        int i = 0;
        do
        {
            if (i >= vector.size())
            {
                mTileHttpLoaders.remove(tilehttploader);
                startThread();
                return false;
            }
            ((TileRequest)vector.get(i)).setState(TileRequest.TILEREQUEST_WAIT);
            i++;
        } while (true);
    }

    public TileRequest getNearTileRequest(Vector vector)
    {
        TileRequest tilerequest = null;
        int i = mMapCtrl.getCenterWorldPixelX();
        int j = mMapCtrl.getCenterWorldPixelY();
        int k = 0;
        int l = 0;
        do
        {
            int i1;
            for (i1 = -1 + vector.size(); i1 < 0 || l >= 10;)
            {
                return tilerequest;
            }

            TileRequest tilerequest1 = (TileRequest)vector.get(i1);
            if (tilerequest1.getState() == TileRequest.TILEREQUEST_WAIT)
            {
                int j1 = (int)Math.sqrt(Coordinate.pow(i - (tilerequest1.getX() + tilerequest1.getTileSize() / 2), 2D) + Coordinate.pow(j - (tilerequest1.getY() + tilerequest1.getTileSize() / 2), 2D));
                if (tilerequest == null || j1 < k)
                {
                    k = j1;
                    tilerequest = tilerequest1;
                }
                l++;
            }
            i1--;
        } while (true);
    }

    public TileRequest getRemoveRequest()
    {
        int i = 0;
_L6:
        if (i < mtileRequest.size()) goto _L2; else goto _L1
_L1:
        TileRequest tilerequest = null;
_L4:
        return tilerequest;
_L2:
        tilerequest = (TileRequest)mtileRequest.get(i);
        if (tilerequest.getState() == TileRequest.TILEREQUEST_REMOVE) goto _L4; else goto _L3
_L3:
        i++;
        if (true) goto _L6; else goto _L5
_L5:
    }

    public int getRequestPack(Vector vector, Vector vector1, String s)
    {
        int i = 0;
        int j = 0;
        do
        {
            if (j >= vector.size())
            {
                return i;
            }
            TileRequest tilerequest = (TileRequest)vector.elementAt(j);
            if (tilerequest.getState() != TileRequest.TILEREQUEST_REMOVE && s.equals(tilerequest.getPack()))
            {
                if (tilerequest.getTileY() % 2 == 0)
                {
                    if (vector1.size() == 0)
                    {
                        vector1.add(tilerequest);
                    } else
                    {
                        vector1.add(0, tilerequest);
                    }
                } else
                {
                    vector1.add(tilerequest);
                }
                i++;
            }
            j++;
        } while (true);
    }

    public TileHttpLoader getStopHttp()
    {
        int i = 0;
_L6:
        if (i < mTileHttpLoaders.size()) goto _L2; else goto _L1
_L1:
        TileHttpLoader tilehttploader = null;
_L4:
        return tilehttploader;
_L2:
        tilehttploader = (TileHttpLoader)mTileHttpLoaders.get(i);
        if (!tilehttploader.getThreadEnd()) goto _L4; else goto _L3
_L3:
        i++;
        if (true) goto _L6; else goto _L5
_L5:
    }

    public TileRequest getTileRequest(int i, int j, int k, int l, int i1, int j1, String s, 
            String s1)
    {
        int k1 = 0;
_L6:
        if (k1 < mtileRequest.size()) goto _L2; else goto _L1
_L1:
        TileRequest tilerequest = null;
_L4:
        return tilerequest;
_L2:
        tilerequest = (TileRequest)mtileRequest.elementAt(k1);
        if (tilerequest != null && tilerequest.getState() != TileRequest.TILEREQUEST_REMOVE && tilerequest.cmpTileId(i, j, k, l, i1, j1, s, s1)) goto _L4; else goto _L3
_L3:
        k1++;
        if (true) goto _L6; else goto _L5
_L5:
    }

    public void removeReqest()
    {
        int i = 0;
        do
        {
            if (i >= mtileRequest.size())
            {
                return;
            }
            TileRequest tilerequest = (TileRequest)mtileRequest.elementAt(i);
            if (tilerequest.getState() != TileRequest.TILEREQUEST_HTTP && tilerequest.getState() != TileRequest.TILEREQUEST_CHACHE)
            {
                tilerequest.setState(TileRequest.TILEREQUEST_REMOVE);
            }
            i++;
        } while (true);
    }

    public void removeRequest(int i, int j, int k, int l, int i1, int j1, String s, 
            String s1)
    {
        TileRequest tilerequest = getTileRequest(i, j, k, l, i1, j1, s, s1);
        if (tilerequest != null)
        {
            tilerequest.setState(TileRequest.TILEREQUEST_REMOVE);
        }
    }

    public void removeRequest(TileRequest tilerequest)
    {
        removeRequest(tilerequest.getTileX(), tilerequest.getTileY(), tilerequest.getTileZ(), tilerequest.getMapType(), tilerequest.getTileSize(), tilerequest.getIndoorLevel(), tilerequest.getStyle(), tilerequest.getSeamless());
    }

    public void removeRequestStateIdol()
    {
        int i = 0;
        do
        {
            if (i >= mtileRequest.size())
            {
                return;
            }
            TileRequest tilerequest = (TileRequest)mtileRequest.get(i);
            if (tilerequest.getState() == TileRequest.TILEREQUEST_IDOL)
            {
                tilerequest.setState(TileRequest.TILEREQUEST_REMOVE);
            }
            i++;
        } while (true);
    }

    public void run()
    {
_L6:
        Object obj = lock;
        obj;
        JVM INSTR monitorenter ;
        int j;
        int k;
        if (!checkActiveRequest())
        {
            break MISSING_BLOCK_LABEL_35;
        }
        j = mTileHttpLoaders.size();
        k = THREAD_MAX;
        if (j < k)
        {
            break MISSING_BLOCK_LABEL_42;
        }
        lock.wait();
_L1:
        obj;
        JVM INSTR monitorexit ;
        if (!mThread)
        {
            return;
        }
        break MISSING_BLOCK_LABEL_65;
        InterruptedException interruptedexception;
        interruptedexception;
        interruptedexception.printStackTrace();
          goto _L1
        Exception exception;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
        TimeChecker timechecker;
        Vector vector;
        Attestation attestation;
        timechecker = TimeChecker.getInstance();
        timechecker.startPoint();
        vector = (Vector)mtileRequest.clone();
        attestation = mMapCtrl.getAttestation();
        if (attestation == null || !attestation.success) goto _L3; else goto _L2
_L2:
        TileRequest tilerequest = getNearTileRequest(vector);
        if (tilerequest == null) goto _L3; else goto _L4
_L4:
        Vector vector1;
        vector1 = new Vector();
        vector1.add(tilerequest);
        if (vector1.size() <= 0 || mTileHttpLoaders.size() >= THREAD_MAX) goto _L3; else goto _L5
_L5:
        int i = 0;
_L7:
        if (i < vector1.size())
        {
            break MISSING_BLOCK_LABEL_223;
        }
        TileHttpLoader tilehttploader = new TileHttpLoader(this);
        tilehttploader.setMapUrl(attestation.getMapUrl());
        tilehttploader.setTileRequest(vector1);
        tilehttploader.start();
        mTileHttpLoaders.add(tilehttploader);
_L3:
        timechecker.endPoint();
          goto _L6
        ((TileRequest)vector1.get(i)).setState(TileRequest.TILEREQUEST_HTTP);
        i++;
          goto _L7
    }

    public void setRequestStateIdol()
    {
        int i = 0;
        do
        {
            if (i >= mtileRequest.size())
            {
                return;
            }
            TileRequest tilerequest = (TileRequest)mtileRequest.get(i);
            if (tilerequest.getState() == TileRequest.TILEREQUEST_WAIT)
            {
                tilerequest.setState(TileRequest.TILEREQUEST_IDOL);
            }
            i++;
        } while (true);
    }

    public int size()
    {
        return mtileRequest.size();
    }

    public void stopThreadAll()
    {
        if (!mThread) goto _L2; else goto _L1
_L1:
        int i = 0;
_L6:
        if (i < mTileHttpLoaders.size()) goto _L4; else goto _L3
_L3:
        mTileCacheManager.stopThread();
        mThread = false;
        synchronized (lock)
        {
            lock.notify();
        }
        join();
_L2:
        return;
_L4:
        TileHttpLoader tilehttploader = (TileHttpLoader)mTileHttpLoaders.get(i);
        try
        {
            tilehttploader.join();
        }
        catch (Exception exception) { }
        i++;
        if (true) goto _L6; else goto _L5
_L5:
        exception1;
        obj;
        JVM INSTR monitorexit ;
        throw exception1;
        Exception exception2;
        exception2;
    }

}

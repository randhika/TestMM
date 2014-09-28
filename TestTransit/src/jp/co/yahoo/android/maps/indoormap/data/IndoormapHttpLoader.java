// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.maps.indoormap.data;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.concurrent.LinkedBlockingQueue;
import jp.co.yahoo.android.maps.HttpClient;
import jp.co.yahoo.android.maps.viewlayer.Attestation;
import jp.co.yahoo.android.maps.viewlayer.BaseViewCtrl;
import jp.co.yahoo.android.maps.viewlayer.ScaleUtils;

// Referenced classes of package jp.co.yahoo.android.maps.indoormap.data:
//            TileCacheManager, IndoormapRequest

public class IndoormapHttpLoader extends Thread
    implements TileCacheManager.TileCacheListener
{
    public static interface IndoormapHttpLoaderListener
    {

        public abstract boolean endAllIndoormapHttpLoader(IndoormapHttpLoader indoormaphttploader);

        public abstract boolean endIndoormapHttpLoader(byte abyte0[], IndoormapRequest indoormaprequest);

        public abstract boolean errorIndoormapHttpLoader(IndoormapHttpLoader indoormaphttploader);
    }


    private BaseViewCtrl mBaseViewCtrl;
    private HttpClient mHttpClient;
    private IndoormapHttpLoaderListener mIndoormapHttpLoaderListener;
    private TileCacheManager mTileCacheManager;
    private LinkedBlockingQueue requestQueue;

    public IndoormapHttpLoader(IndoormapHttpLoaderListener indoormaphttploaderlistener, BaseViewCtrl baseviewctrl)
    {
        mHttpClient = null;
        mIndoormapHttpLoaderListener = null;
        mBaseViewCtrl = null;
        requestQueue = null;
        mTileCacheManager = null;
        mIndoormapHttpLoaderListener = indoormaphttploaderlistener;
        mBaseViewCtrl = baseviewctrl;
        requestQueue = new LinkedBlockingQueue();
        mTileCacheManager = new TileCacheManager(baseviewctrl.getContext(), this);
    }

    private static byte[] getBytes(InputStream inputstream)
    {
        byte abyte0[];
        ByteArrayOutputStream bytearrayoutputstream;
        abyte0 = new byte[128];
        bytearrayoutputstream = new ByteArrayOutputStream();
_L1:
        int i = inputstream.read(abyte0);
        if (i <= 0)
        {
            byte abyte1[];
            try
            {
                abyte1 = bytearrayoutputstream.toByteArray();
                inputstream.close();
                bytearrayoutputstream.close();
            }
            catch (Exception exception)
            {
                return null;
            }
            return abyte1;
        }
        bytearrayoutputstream.write(abyte0, 0, i);
          goto _L1
    }

    private boolean getHttpTile(IndoormapRequest indoormaprequest)
    {
        int i;
        try
        {
            i = ScaleUtils.scidturn(indoormaprequest.getTileZ());
        }
        catch (Exception exception)
        {
            return false;
        }
        Attestation attestation;
        if (mBaseViewCtrl != null)
        {
            if ((attestation = mBaseViewCtrl.getAttestation()) != null && attestation.success)
            {
                String s = (new StringBuilder(String.valueOf(attestation.getMapUrl()))).append("?r=1").append("&mode=indoormap").append("&x=").append(indoormaprequest.getTileX()).append("&y=").append(indoormaprequest.getTileY()).append("&z=").append(i).append("&style=").append(indoormaprequest.getStyle()).append("&size=").append(indoormaprequest.getTileSize()).toString();
                mHttpClient = new HttpClient();
                InputStream inputstream = mHttpClient.httpGet(s);
                if (inputstream == null)
                {
                    mHttpClient.clear();
                    mHttpClient = null;
                    return false;
                }
                byte abyte0[] = getBytes(inputstream);
                if (mTileCacheManager != null)
                {
                    mTileCacheManager.saveCache(indoormaprequest, abyte0);
                }
                if (!createTileImg(abyte0, indoormaprequest))
                {
                    mHttpClient.clear();
                    mHttpClient = null;
                    return false;
                }
                try
                {
                    inputstream.close();
                }
                catch (Exception exception1) { }
                mHttpClient.clear();
                mHttpClient = null;
                return true;
            }
        }
        return false;
    }

    public void addTileRequest(IndoormapRequest indoormaprequest)
    {
        if (!requestQueue.contains(indoormaprequest))
        {
            try
            {
                requestQueue.put(indoormaprequest);
            }
            catch (InterruptedException interruptedexception)
            {
                interruptedexception.printStackTrace();
            }
            if (!isAlive())
            {
                start();
                return;
            }
        }
    }

    public boolean createTileImg(byte abyte0[], IndoormapRequest indoormaprequest)
    {
        if (mIndoormapHttpLoaderListener != null)
        {
            mIndoormapHttpLoaderListener.endIndoormapHttpLoader(abyte0, indoormaprequest);
        }
        return true;
    }

    public void dispose()
    {
        mIndoormapHttpLoaderListener = null;
        mBaseViewCtrl = null;
        requestQueue.clear();
        mTileCacheManager.dispose();
        mTileCacheManager = null;
    }

    public boolean endTileCacheLoad(byte abyte0[], IndoormapRequest indoormaprequest)
    {
        if (abyte0 == null)
        {
            indoormaprequest.setState(IndoormapRequest.TILEREQUEST_WAIT);
        } else
        if (abyte0 != null)
        {
            if (mIndoormapHttpLoaderListener != null)
            {
                mIndoormapHttpLoaderListener.endIndoormapHttpLoader(abyte0, indoormaprequest);
            }
            indoormaprequest.setState(IndoormapRequest.TILEREQUEST_REMOVE);
            return false;
        }
        return false;
    }

    public void removeAllTileRequests()
    {
        requestQueue.clear();
    }

    public void run()
    {
_L1:
        IndoormapRequest indoormaprequest;
        boolean flag;
        Exception exception;
        boolean flag1;
        try
        {
            indoormaprequest = (IndoormapRequest)requestQueue.take();
        }
        catch (InterruptedException interruptedexception)
        {
            indoormaprequest = null;
        }
        if (mTileCacheManager == null || indoormaprequest == null || !mTileCacheManager.existsCache(indoormaprequest))
        {
            break MISSING_BLOCK_LABEL_120;
        }
        flag1 = createTileImg(mTileCacheManager.getCache(indoormaprequest), indoormaprequest);
        if (flag1)
        {
            flag = false;
        } else
        {
            flag = true;
        }
_L2:
        if (!flag && mIndoormapHttpLoaderListener != null)
        {
            mIndoormapHttpLoaderListener.errorIndoormapHttpLoader(this);
        }
        if (requestQueue.size() == 0 && mIndoormapHttpLoaderListener != null)
        {
            mIndoormapHttpLoaderListener.endAllIndoormapHttpLoader(this);
        }
          goto _L1
        exception;
        flag = false;
          goto _L2
        flag = getHttpTile(indoormaprequest);
          goto _L2
    }
}

// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.maps.viewlayer.tile;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Vector;
import jp.co.yahoo.android.maps.HttpClient;
import jp.co.yahoo.android.maps.MapView;
import jp.co.yahoo.android.maps.viewlayer.LayerInfo;
import jp.co.yahoo.android.maps.viewlayer.MapLayerManager;
import jp.co.yahoo.android.maps.viewlayer.ScaleUtils;

// Referenced classes of package jp.co.yahoo.android.maps.viewlayer.tile:
//            TileRequest

public class TileHttpLoader extends Thread
{
    public static interface TileHttpLoaderListener
    {

        public abstract boolean endAllTileHttpLoader(TileHttpLoader tilehttploader, Vector vector);

        public abstract boolean endTileHttpLoader(byte abyte0[], TileRequest tilerequest);

        public abstract boolean errorTileHttpLoader(TileHttpLoader tilehttploader, Vector vector);
    }


    private HttpClient mHttpClient;
    private String mMapUrl;
    private Vector mRequest;
    private boolean mSuccess;
    private boolean mThreadEnd;
    private TileHttpLoaderListener mTileHttpLoaderListener;
    private boolean mTileRevStop;

    public TileHttpLoader(TileHttpLoaderListener tilehttploaderlistener)
    {
        super("TileHttpLoader");
        mMapUrl = null;
        mRequest = null;
        mHttpClient = null;
        mTileRevStop = false;
        mTileHttpLoaderListener = null;
        mSuccess = false;
        mThreadEnd = false;
        mTileHttpLoaderListener = tilehttploaderlistener;
    }

    private boolean getHttpTile()
    {
        String s = "";
        if (mRequest.size() == 0)
        {
            return true;
        }
        TileRequest tilerequest;
        String s1;
        int i;
        String s2;
        tilerequest = (TileRequest)mRequest.elementAt(0);
        s1 = tilerequest.getSeamless();
        i = scidturn(tilerequest.getTileZ());
        s2 = MapLayerManager.mapTypeToMode(tilerequest.getMapType(), tilerequest.getIndoorLevel());
        if (tilerequest.getMapType() != MapView.MapTypeStandard && tilerequest.getMapType() != MapView.MapTypeUnderground) goto _L2; else goto _L1
_L19:
        String s3;
        int j;
        int k;
        int l;
        String s6;
        try
        {
            l = mRequest.size();
        }
        catch (Exception exception)
        {
            return false;
        }
        if (j < l) goto _L4; else goto _L3
_L3:
        if (s3 == null)
        {
            break MISSING_BLOCK_LABEL_113;
        }
        s6 = URLEncoder.encode(s3, "UTF-8");
        s3 = s6;
        break MISSING_BLOCK_LABEL_113;
_L2:
        j1 = tilerequest.getMapType();
        k1 = MapView.MapTypeStyle;
        s3 = null;
        if (j1 != k1) goto _L6; else goto _L5
_L5:
        s3 = tilerequest.getStyle();
          goto _L6
_L4:
        tilerequest1 = (TileRequest)mRequest.elementAt(j);
        i1 = tilerequest1.getTileX();
        layerinfo = tilerequest1.getLayerInfo();
        if (layerinfo == null) goto _L8; else goto _L7
_L7:
        if (i1 <= layerinfo.maxTileX) goto _L10; else goto _L9
_L9:
        i1 %= 1 + layerinfo.maxTileX;
_L8:
        if (!s2.equals("osm")) goto _L12; else goto _L11
_L11:
        s = (new StringBuilder("x=")).append(i1).append("&y=").append(tilerequest1.getTileY()).toString();
        k = tilerequest1.getTileSize();
          goto _L13
_L10:
        if (i1 >= 0) goto _L8; else goto _L14
_L14:
        i1 %= 1 + layerinfo.maxTileX;
        if (i1 >= 0) goto _L8; else goto _L15
_L15:
        i1 += 1 + layerinfo.maxTileX;
          goto _L8
_L12:
        if (!s.equals("")) goto _L17; else goto _L16
_L16:
        k = tilerequest1.getTileSize();
        s = (new StringBuilder(String.valueOf(i1))).append("_").append(tilerequest1.getTileY()).toString();
          goto _L13
_L17:
        s4 = (new StringBuilder(String.valueOf(s))).append(",").append(i1).append("_").append(tilerequest1.getTileY()).toString();
        s = s4;
          goto _L13
        unsupportedencodingexception;
        s3 = null;
        TileRequest tilerequest1;
        int i1;
        LayerInfo layerinfo;
        String s4;
        String s5;
        InputStream inputstream;
        UnsupportedEncodingException unsupportedencodingexception;
        int j1;
        int k1;
        if (s2.equals("osm"))
        {
            s5 = (new StringBuilder("http://m.map.c.yimg.jp/m?mode=osm&r=1&")).append(s).append("&z=").append(i).toString();
        } else
        {
            if (i <= 10)
            {
                s1 = "off";
            }
            s5 = (new StringBuilder(String.valueOf(mMapUrl))).append("?output=").append("png").append("&seamless=").append(s1).append("&mode=").append(s2).append("&z=").append(i).append("&code=").append(s).toString();
            if (s3 != null && !s3.equals(""))
            {
                s5 = (new StringBuilder(String.valueOf(s5))).append("&style=").append(s3).toString();
            }
        }
        if (k != 256)
        {
            s5 = (new StringBuilder(String.valueOf(s5))).append("&size=").append(k).toString();
        }
        mHttpClient = new HttpClient();
        inputstream = mHttpClient.httpGet(s5);
        if (inputstream == null)
        {
            mHttpClient.clear();
            mHttpClient = null;
            return false;
        }
        if (s2.equals("osm"))
        {
            if (!createOsmTileImg(inputstream, mRequest))
            {
                mHttpClient.clear();
                mHttpClient = null;
                return false;
            }
        } else
        if (!createTiles(inputstream, mRequest))
        {
            mHttpClient.clear();
            mHttpClient = null;
            return false;
        }
        try
        {
            inputstream.close();
        }
        catch (Exception exception1)
        {
            exception1.printStackTrace();
        }
        mHttpClient.clear();
        mHttpClient = null;
        return true;
_L1:
        s3 = "base:smartphone";
_L6:
        j = 0;
        k = 0;
        continue; /* Loop/switch isn't completed */
_L13:
        j++;
        if (true) goto _L19; else goto _L18
_L18:
    }

    private int scidturn(int i)
    {
        return ScaleUtils.scidturn(i);
    }

    public boolean createOsmTileImg(InputStream inputstream, Vector vector)
    {
        if (vector != null) goto _L2; else goto _L1
_L1:
        return false;
_L2:
        TileRequest tilerequest;
        ByteArrayOutputStream bytearrayoutputstream;
        byte abyte0[];
        if (vector.size() != 1)
        {
            continue; /* Loop/switch isn't completed */
        }
        tilerequest = (TileRequest)vector.elementAt(0);
        bytearrayoutputstream = new ByteArrayOutputStream();
        abyte0 = new byte[1024];
_L3:
        int i = inputstream.read(abyte0);
        if (i != -1)
        {
            break MISSING_BLOCK_LABEL_147;
        }
        byte abyte1[];
        abyte1 = bytearrayoutputstream.toByteArray();
        bytearrayoutputstream.close();
        if (abyte1.length >= 4)
        {
            if ((abyte1[0] != 71 || abyte1[1] != 73 || abyte1[2] != 70) && (abyte1[1] != 80 || abyte1[2] != 78 || abyte1[3] != 71) && (abyte1[0] != -1 || abyte1[1] != -40))
            {
                break MISSING_BLOCK_LABEL_176;
            }
            break MISSING_BLOCK_LABEL_160;
        }
        continue; /* Loop/switch isn't completed */
        try
        {
            bytearrayoutputstream.write(abyte0, 0, i);
        }
        catch (Exception exception)
        {
            return false;
        }
          goto _L3
        mTileHttpLoaderListener.endTileHttpLoader(abyte1, tilerequest);
        return true;
        return false;
        if (true) goto _L1; else goto _L4
_L4:
    }

    public boolean createTileImg(InputStream inputstream, TileRequest tilerequest, int i)
    {
        if (i <= 0)
        {
            return false;
        }
        ByteArrayOutputStream bytearrayoutputstream;
        int j;
        byte abyte0[];
        int k;
        int l;
        byte abyte1[];
        try
        {
            bytearrayoutputstream = new ByteArrayOutputStream();
        }
        catch (Exception exception)
        {
            return false;
        }
        j = 32;
        if (j > i)
        {
            j = i;
        }
        abyte0 = new byte[j];
        k = 0;
_L5:
        l = inputstream.read(abyte0);
        if (l != -1) goto _L2; else goto _L1
_L1:
        abyte1 = bytearrayoutputstream.toByteArray();
        bytearrayoutputstream.close();
        if (abyte1.length < 4)
        {
            return false;
        }
        break; /* Loop/switch isn't completed */
_L2:
        bytearrayoutputstream.write(abyte0, 0, l);
        if (i <= (k += l)) goto _L1; else goto _L3
_L3:
        if (i - k >= i)
        {
            continue; /* Loop/switch isn't completed */
        }
        abyte0 = new byte[i - k];
        if (true) goto _L5; else goto _L4
_L4:
        if ((abyte1[0] != 71 || abyte1[1] != 73 || abyte1[2] != 70) && (abyte1[1] != 80 || abyte1[2] != 78 || abyte1[3] != 71) && (abyte1[0] != -1 || abyte1[1] != -40))
        {
            break MISSING_BLOCK_LABEL_207;
        }
        mTileHttpLoaderListener.endTileHttpLoader(abyte1, tilerequest);
        return true;
        return false;
    }

    public boolean createTiles(InputStream inputstream, Vector vector)
    {
        byte abyte0[] = new byte[2];
        try
        {
            inputstream.read(abyte0);
        }
        catch (Exception exception) { }
        return getDataPart(inputstream, vector, (short)(((0xff & abyte0[1]) << 8) + (0xff & abyte0[0])));
    }

    public boolean getDataPart(InputStream inputstream, Vector vector, int i)
    {
        int j = 0;
_L2:
        if (j >= i)
        {
            return true;
        }
        if (mTileRevStop)
        {
            return false;
        }
        if (vector.size() <= j)
        {
            return false;
        }
        short word0;
        byte abyte0[] = new byte[2];
        inputstream.read(abyte0);
        word0 = (short)(((0xff & abyte0[1]) << 8) + (0xff & abyte0[0]));
        if (word0 != inputstream.read(new byte[word0]))
        {
            return false;
        }
        byte abyte1[];
        int k;
        int l;
        int i1;
        int j1;
        int k1;
        boolean flag;
        try
        {
            abyte1 = new byte[4];
            inputstream.read(abyte1);
        }
        catch (Exception exception)
        {
            return false;
        }
        k = 4 - 1;
        l = 4 - 2;
        i1 = 4 - 3;
        j1 = 4 - 4;
        k1 = ((0xff & abyte1[k]) << 24) + ((0xff & abyte1[l]) << 16) + ((0xff & abyte1[i1]) << 8) + (0xff & abyte1[j1]);
        flag = createTileImg(inputstream, (TileRequest)vector.elementAt(j), k1);
        if (!flag)
        {
            return false;
        }
        j++;
        if (true) goto _L2; else goto _L1
_L1:
    }

    public boolean getThreadEnd()
    {
        return mThreadEnd;
    }

    public void run()
    {
        mSuccess = getHttpTile();
        if (mSuccess)
        {
            mTileHttpLoaderListener.endAllTileHttpLoader(this, mRequest);
        } else
        {
            mTileHttpLoaderListener.errorTileHttpLoader(this, mRequest);
        }
        mThreadEnd = false;
    }

    public void setMapUrl(String s)
    {
        mMapUrl = s;
    }

    public void setTileRequest(Vector vector)
    {
        mRequest = vector;
        mThreadEnd = true;
    }
}

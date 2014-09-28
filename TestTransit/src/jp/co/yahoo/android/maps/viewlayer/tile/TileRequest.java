// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.maps.viewlayer.tile;

import jp.co.yahoo.android.maps.viewlayer.LayerInfo;

// Referenced classes of package jp.co.yahoo.android.maps.viewlayer.tile:
//            TileCacheManager

public class TileRequest
{

    public static int TILEREQUEST_CHACHE = 3;
    public static int TILEREQUEST_HTTP = 1;
    public static int TILEREQUEST_IDOL = 0;
    public static int TILEREQUEST_REMOVE = 4;
    public static int TILEREQUEST_WAIT = 2;
    private String mCacheFileName;
    private int mIndoorLevel;
    LayerInfo mLayerInfo;
    private int mMapType;
    private String mPack;
    private String mSeamless;
    private int mState;
    private String mStyle;
    private int mTileSize;
    private int mTileX;
    private int mTileY;
    private int mTileZ;
    private int mX;
    private int mY;

    public TileRequest(int i, int j, int k, int l, int i1, String s, int j1, 
            String s1, String s2)
    {
        mSeamless = "off";
        mStyle = "";
        mState = TILEREQUEST_IDOL;
        mLayerInfo = null;
        mTileX = i;
        mTileY = j;
        mTileZ = k;
        mMapType = l;
        mTileSize = i1;
        mStyle = s;
        if (mStyle == null)
        {
            mStyle = "";
        }
        mIndoorLevel = j1;
        mPack = s1;
        mSeamless = s2;
        mCacheFileName = TileCacheManager.makeFileName(this);
        mX = 0;
        mY = 0;
    }

    public boolean cmpTileId(int i, int j, int k, int l, int i1, int j1, String s, 
            String s1)
    {
        if (s == null)
        {
            s = "";
        }
        return mTileX == i && mTileY == j && mTileZ == k && mMapType == l && mTileSize == i1 && mIndoorLevel == j1 && s.equals(mStyle) && s1.equals(mSeamless);
    }

    public String getCacheFileName()
    {
        return mCacheFileName;
    }

    public int getIndoorLevel()
    {
        return mIndoorLevel;
    }

    public LayerInfo getLayerInfo()
    {
        return mLayerInfo;
    }

    public int getMapType()
    {
        return mMapType;
    }

    public String getPack()
    {
        return mPack;
    }

    public String getSeamless()
    {
        return mSeamless;
    }

    public int getState()
    {
        return mState;
    }

    public String getStyle()
    {
        return mStyle;
    }

    public int getTileSize()
    {
        return mTileSize;
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
        return mTileZ;
    }

    public int getX()
    {
        return mX;
    }

    public int getY()
    {
        return mY;
    }

    public void resetRequest(int i, int j, int k, int l, int i1, String s, int j1, 
            String s1, String s2)
    {
        mTileX = i;
        mTileY = j;
        mTileZ = k;
        mMapType = l;
        mTileSize = i1;
        mStyle = s;
        if (mStyle == null)
        {
            mStyle = "";
        }
        mPack = s1;
        mSeamless = s2;
        mIndoorLevel = j1;
        mCacheFileName = TileCacheManager.makeFileName(this);
        mX = 0;
        mY = 0;
    }

    public void setIndoorLevel(int i)
    {
        mIndoorLevel = i;
    }

    public void setLayerInfo(LayerInfo layerinfo)
    {
        mLayerInfo = layerinfo;
    }

    public void setPlaneXY(int i, int j)
    {
        mX = i;
        mY = j;
    }

    public void setState(int i)
    {
        mState = i;
    }

    public void setTileX(int i)
    {
        mTileX = i;
    }

    public void setTileY(int i)
    {
        mTileY = i;
    }

    public void setTileZ(int i)
    {
        mTileZ = i;
    }

}

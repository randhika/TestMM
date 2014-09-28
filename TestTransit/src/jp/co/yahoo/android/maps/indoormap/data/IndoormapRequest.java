// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.maps.indoormap.data;


// Referenced classes of package jp.co.yahoo.android.maps.indoormap.data:
//            TileCacheManager

public class IndoormapRequest
{

    public static int TILEREQUEST_CHACHE = 3;
    public static int TILEREQUEST_HTTP = 1;
    public static int TILEREQUEST_IDOL = 0;
    public static int TILEREQUEST_REMOVE = 4;
    public static int TILEREQUEST_WAIT = 2;
    private String mCacheFileName;
    private int mState;
    private String mStyle;
    private int mTileSize;
    private int mTileX;
    private int mTileY;
    private int mTileZ;

    public IndoormapRequest(int i, int j, int k, int l, String s)
    {
        mState = TILEREQUEST_IDOL;
        mTileX = i;
        mTileY = j;
        mTileZ = k;
        mTileSize = l;
        mStyle = s;
        mCacheFileName = TileCacheManager.makeFileName(this);
    }

    public boolean cmpTileId(int i, int j, int k, int l, String s)
    {
        return mTileX == i && mTileY == j && mTileZ == k && mTileSize == l && s.equals(mStyle);
    }

    public boolean equals(Object obj)
    {
        if (this != obj)
        {
            if (obj == null)
            {
                return false;
            }
            if (getClass() != obj.getClass())
            {
                return false;
            }
            IndoormapRequest indoormaprequest = (IndoormapRequest)obj;
            if (mStyle == null)
            {
                if (indoormaprequest.mStyle != null)
                {
                    return false;
                }
            } else
            if (!mStyle.equals(indoormaprequest.mStyle))
            {
                return false;
            }
            if (mTileSize != indoormaprequest.mTileSize)
            {
                return false;
            }
            if (mTileX != indoormaprequest.mTileX)
            {
                return false;
            }
            if (mTileY != indoormaprequest.mTileY)
            {
                return false;
            }
            if (mTileZ != indoormaprequest.mTileZ)
            {
                return false;
            }
        }
        return true;
    }

    public String getCacheFileName()
    {
        return mCacheFileName;
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

    public int hashCode()
    {
        int i;
        if (mStyle == null)
        {
            i = 0;
        } else
        {
            i = mStyle.hashCode();
        }
        return 31 * (31 * (31 * (31 * (i + 31) + mTileSize) + mTileX) + mTileY) + mTileZ;
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

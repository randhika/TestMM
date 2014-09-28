// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.maps.weather.data;


public class WeatherRequest
{

    public static int TILEREQUEST_CHACHE = 3;
    public static int TILEREQUEST_HTTP = 1;
    public static int TILEREQUEST_IDOL = 0;
    public static int TILEREQUEST_WAIT = 2;
    private String mDate;
    private int mState;
    private int mTileSize;
    private int mTileX;
    private int mTileY;
    private int mTileZ;

    public WeatherRequest(int i, int j, int k, int l, String s)
    {
        mState = TILEREQUEST_IDOL;
        mTileX = i;
        mTileY = j;
        mTileZ = k;
        mTileSize = l;
        mDate = s;
    }

    public boolean cmpTileId(int i, int j, int k, int l, String s)
    {
        return mTileX == i && mTileY == j && mTileZ == k && mTileSize == l && s.equals(mDate);
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
            WeatherRequest weatherrequest = (WeatherRequest)obj;
            if (mDate == null)
            {
                if (weatherrequest.mDate != null)
                {
                    return false;
                }
            } else
            if (!mDate.equals(weatherrequest.mDate))
            {
                return false;
            }
            if (mTileSize != weatherrequest.mTileSize)
            {
                return false;
            }
            if (mTileX != weatherrequest.mTileX)
            {
                return false;
            }
            if (mTileY != weatherrequest.mTileY)
            {
                return false;
            }
            if (mTileZ != weatherrequest.mTileZ)
            {
                return false;
            }
        }
        return true;
    }

    public String getDate()
    {
        return mDate;
    }

    public int getState()
    {
        return mState;
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
        if (mDate == null)
        {
            i = 0;
        } else
        {
            i = mDate.hashCode();
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

// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.maps.viewlayer.tile;

import jp.co.yahoo.android.maps.DoublePoint;
import jp.co.yahoo.android.maps.GeoPoint;
import jp.co.yahoo.android.maps.viewlayer.BaseViewCtrl;

// Referenced classes of package jp.co.yahoo.android.maps.viewlayer.tile:
//            TileBaseLayer

public class mJumpToFinish
{

    private boolean mCanceled;
    private int mCount;
    private GeoPoint mGeoPoint;
    private boolean mJumpToFinish;
    private int mMaxCount;
    private int mNextLevel;
    private DoublePoint mPos;
    private float mfactor;
    final TileBaseLayer this$0;

    public void cancelMove(boolean flag)
    {
        mCanceled = true;
        mJumpToFinish = flag;
    }

    public boolean countUp()
    {
        if (mMaxCount <= mCount)
        {
            return false;
        } else
        {
            mCount = 1 + mCount;
            return true;
        }
    }

    public float getFactor()
    {
        return mfactor;
    }

    public GeoPoint getGeoPoint()
    {
        return mGeoPoint;
    }

    public DoublePoint getMovePos()
    {
        return new DoublePoint((mPos.x - (double)(TileBaseLayer.access$0(TileBaseLayer.this).getWidth() / 2)) / (double)mMaxCount, (mPos.y - (double)(TileBaseLayer.access$0(TileBaseLayer.this).getHeight() / 2)) / (double)mMaxCount);
    }

    public int getNextLevel()
    {
        return mNextLevel;
    }

    public float getNowFactor()
    {
        return 1.0F + mfactor * (float)mCount;
    }

    public DoublePoint getPos()
    {
        return mPos;
    }



    public (float f, int i, int j, DoublePoint doublepoint, GeoPoint geopoint)
    {
        this$0 = TileBaseLayer.this;
        super();
        mfactor = f / (float)i;
        mMaxCount = i;
        mCount = 0;
        mNextLevel = j;
        mPos = doublepoint;
        mGeoPoint = geopoint;
        mCanceled = false;
        mJumpToFinish = false;
    }
}

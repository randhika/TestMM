// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.maps;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

// Referenced classes of package jp.co.yahoo.android.maps:
//            MapView

public abstract class MapActivity extends Activity
{

    private MapView mMapView;

    public MapActivity()
    {
        mMapView = null;
    }

    protected boolean isLocationDisplayed()
    {
        return false;
    }

    protected abstract boolean isRouteDisplayed();

    public void onClick(View view)
    {
    }

    public void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
    }

    protected void onDestroy()
    {
        super.onDestroy();
        if (mMapView != null)
        {
            mMapView.onDestroy();
            mMapView = null;
        }
    }

    public void onNewIntent(Intent intent)
    {
    }

    protected void onPause()
    {
        super.onPause();
        if (mMapView != null)
        {
            mMapView.onPause();
        }
    }

    protected void onResume()
    {
        super.onResume();
        if (mMapView != null)
        {
            mMapView.onResume();
        }
    }

    public void onStart()
    {
        super.onStart();
    }

    public boolean onTouch(View view, MotionEvent motionevent)
    {
        return true;
    }

    public boolean onTouch(MapView mapview, MotionEvent motionevent)
    {
        return false;
    }

    public void setMapView(MapView mapview)
    {
        if (mMapView != null)
        {
            return;
        } else
        {
            mMapView = null;
            mMapView = mapview;
            return;
        }
    }
}

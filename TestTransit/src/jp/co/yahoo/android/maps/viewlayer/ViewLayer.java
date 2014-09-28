// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.maps.viewlayer;

import android.graphics.Canvas;
import jp.co.yahoo.android.maps.MapCtrlEvent;
import jp.co.yahoo.android.maps.MapView;

// Referenced classes of package jp.co.yahoo.android.maps.viewlayer:
//            BaseViewCtrl

public abstract class ViewLayer
{

    private String mLayerName;

    public ViewLayer()
    {
        mLayerName = (new StringBuilder("VIEWLAYER_")).append(System.currentTimeMillis()).toString();
    }

    public abstract void doEvent(MapCtrlEvent mapctrlevent, BaseViewCtrl baseviewctrl);

    public abstract void draw(Canvas canvas, MapView mapview, boolean flag);

    public String getName()
    {
        return mLayerName;
    }

    public void onDraw(Canvas canvas, MapView mapview)
    {
        draw(canvas, mapview, false);
    }

    public void setName(String s)
    {
        mLayerName = s;
    }
}

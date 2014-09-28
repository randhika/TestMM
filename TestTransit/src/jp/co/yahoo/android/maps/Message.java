// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.maps;

import android.graphics.Canvas;
import jp.co.yahoo.android.maps.viewlayer.BaseViewCtrl;

public class Message
{

    public Canvas mCanvas;
    public BaseViewCtrl mMapCtrl;

    public Message()
    {
    }

    public BaseViewCtrl getBaseViewCtrl()
    {
        return mMapCtrl;
    }

    public Canvas getCanvas()
    {
        return mCanvas;
    }

    public void seCanvas(Canvas canvas)
    {
        mCanvas = canvas;
    }

    public void setBaseViewCtrl(BaseViewCtrl baseviewctrl)
    {
        mMapCtrl = baseviewctrl;
    }
}

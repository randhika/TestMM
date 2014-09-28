// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.maps.routing;

import android.graphics.drawable.Drawable;
import jp.co.yahoo.android.maps.PinOverlay;

// Referenced classes of package jp.co.yahoo.android.maps.routing:
//            RouteOverlay, RouteNodeInfo

private class routePtInfo extends PinOverlay
{

    public RouteNodeInfo routePtInfo;
    final RouteOverlay this$0;
    public boolean visible;
    public int z;

    public (int i)
    {
        this$0 = RouteOverlay.this;
        super(i);
        z = 0;
        visible = true;
    }

    public visible(int i, RouteNodeInfo routenodeinfo)
    {
        this$0 = RouteOverlay.this;
        super(i);
        z = 0;
        visible = true;
        routePtInfo = routenodeinfo;
    }

    public routePtInfo(Drawable drawable)
    {
        this$0 = RouteOverlay.this;
        super(drawable);
        z = 0;
        visible = true;
    }

    public visible(Drawable drawable, RouteNodeInfo routenodeinfo)
    {
        this$0 = RouteOverlay.this;
        super(drawable);
        z = 0;
        visible = true;
        routePtInfo = routenodeinfo;
    }
}

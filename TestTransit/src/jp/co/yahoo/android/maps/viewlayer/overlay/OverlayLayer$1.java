// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.maps.viewlayer.overlay;

import java.util.List;
import jp.co.yahoo.android.maps.GeoPoint;
import jp.co.yahoo.android.maps.MapView;
import jp.co.yahoo.android.maps.Overlay;
import jp.co.yahoo.android.maps.PinOverlay;
import jp.co.yahoo.android.maps.PopupOverlay;

// Referenced classes of package jp.co.yahoo.android.maps.viewlayer.overlay:
//            OverlayLayer

class val.p
    implements Runnable
{

    final OverlayLayer this$0;
    private final MapView val$mapView;
    private final GeoPoint val$p;

    public void run()
    {
        boolean flag;
        int i;
        flag = false;
        i = -1 + mOverlayList.size();
_L6:
        if (i >= 0) goto _L2; else goto _L1
_L1:
        if (OverlayLayer.access$1(OverlayLayer.this) != null)
        {
            OverlayLayer.access$1(OverlayLayer.this).reDraw();
        }
_L4:
        return;
_L2:
        Overlay overlay;
        overlay = (Overlay)mOverlayList.get(i);
        if (overlay.displayZoomLevel < val$mapView.getZoomLevel())
        {
            break MISSING_BLOCK_LABEL_101;
        }
        if (flag)
        {
            break; /* Loop/switch isn't completed */
        }
        if (!overlay.onTap(val$p, val$mapView))
        {
            break MISSING_BLOCK_LABEL_101;
        }
        if (overlay instanceof PopupOverlay) goto _L4; else goto _L3
_L3:
        flag = true;
_L7:
        i--;
        if (true) goto _L6; else goto _L5
_L5:
        overlay instanceof PinOverlay;
          goto _L7
        if (true) goto _L6; else goto _L8
_L8:
    }

    ()
    {
        this$0 = final_overlaylayer;
        val$mapView = mapview;
        val$p = GeoPoint.this;
        super();
    }
}

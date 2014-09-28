// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.maps;


// Referenced classes of package jp.co.yahoo.android.maps:
//            Overlay, LabelTouchManager, GeoPoint, MapView

public abstract class LabelTouchOverlay extends Overlay
    implements LabelTouchManager.LabelTouchListener
{

    public LabelTouchOverlay()
    {
    }

    public abstract void onLabelTouch(LabelTouchManager.LabelInfo labelinfo);

    public boolean onTap(GeoPoint geopoint, MapView mapview)
    {
        LabelTouchManager labeltouchmanager = new LabelTouchManager(mapview);
        labeltouchmanager.addLabelTouchListener(this);
        labeltouchmanager.queryLabel(geopoint);
        return true;
    }
}

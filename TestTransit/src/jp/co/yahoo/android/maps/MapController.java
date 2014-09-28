// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.maps;

import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import java.util.List;

// Referenced classes of package jp.co.yahoo.android.maps:
//            GeoPoint

public interface MapController
{

    public abstract void animateTo(GeoPoint geopoint);

    public abstract void animateTo(GeoPoint geopoint, Message message);

    public abstract void animateTo(GeoPoint geopoint, Runnable runnable);

    public abstract int[] getCenterScale();

    public abstract GeoPoint getMapCenter();

    public abstract int getMaptype();

    public abstract boolean onKey(View view, int i, KeyEvent keyevent);

    public abstract void scrollBy(int i, int j);

    public abstract void setCenter(GeoPoint geopoint);

    public abstract void setMapType(int i);

    public abstract void setMaptype(int i, String s);

    public abstract void setMaptype(int i, String s, List list);

    public abstract int setZoom(int i);

    public abstract void stopAnimation(boolean flag);

    public abstract void stopPanning();

    public abstract boolean zoomIn();

    public abstract boolean zoomInFixing(int i, int j);

    public abstract boolean zoomOut();

    public abstract boolean zoomOutFixing(int i, int j);

    public abstract void zoomToSpan(int i, int j);
}

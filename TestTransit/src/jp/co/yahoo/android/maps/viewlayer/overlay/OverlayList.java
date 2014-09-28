// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.maps.viewlayer.overlay;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import jp.co.yahoo.android.maps.MapView;
import jp.co.yahoo.android.maps.Overlay;

public class OverlayList extends ArrayList
{

    private MapView mMapView;

    public OverlayList(MapView mapview)
    {
        mMapView = null;
        mMapView = mapview;
    }

    private void itemsChanged()
    {
        mMapView.reDraw();
    }

    private void removeOverlay()
    {
        int i = 0;
        do
        {
            if (i >= size())
            {
                return;
            }
            ((Overlay)get(i)).onRemove(mMapView);
            i++;
        } while (true);
    }

    public volatile void add(int i, Object obj)
    {
        add(i, (Overlay)obj);
    }

    public void add(int i, Overlay overlay)
    {
        super.add(i, overlay);
        overlay.setMapView(mMapView);
        itemsChanged();
    }

    public volatile boolean add(Object obj)
    {
        return add((Overlay)obj);
    }

    public boolean add(Overlay overlay)
    {
        boolean flag = super.add(overlay);
        overlay.setMapView(mMapView);
        itemsChanged();
        return flag;
    }

    public boolean addAll(int i, Collection collection)
    {
        boolean flag = super.addAll(i, collection);
        Iterator iterator = collection.iterator();
        do
        {
            if (!iterator.hasNext())
            {
                itemsChanged();
                return flag;
            }
            ((Overlay)iterator.next()).setMapView(mMapView);
        } while (true);
    }

    public boolean addAll(Collection collection)
    {
        boolean flag = super.addAll(collection);
        Iterator iterator = collection.iterator();
        do
        {
            if (!iterator.hasNext())
            {
                itemsChanged();
                return flag;
            }
            ((Overlay)iterator.next()).setMapView(mMapView);
        } while (true);
    }

    public void clear()
    {
        removeOverlay();
        super.clear();
        itemsChanged();
    }

    public volatile Object remove(int i)
    {
        return remove(i);
    }

    public Overlay remove(int i)
    {
        Overlay overlay = (Overlay)super.remove(i);
        itemsChanged();
        if (overlay != null)
        {
            overlay.onRemove(mMapView);
        }
        return overlay;
    }

    public boolean remove(Object obj)
    {
        boolean flag = super.remove(obj);
        itemsChanged();
        if (flag && obj != null)
        {
            ((Overlay)obj).onRemove(mMapView);
        }
        return flag;
    }

    public boolean removeAll(Collection collection)
    {
        Object aobj[] = collection.toArray();
        if (aobj == null) goto _L2; else goto _L1
_L1:
        int i = 0;
_L5:
        if (i < aobj.length) goto _L3; else goto _L2
_L2:
        boolean flag = super.removeAll(collection);
        itemsChanged();
        return flag;
_L3:
        ((Overlay)aobj[i]).onRemove(mMapView);
        i++;
        if (true) goto _L5; else goto _L4
_L4:
    }

    protected void removeRange(int i, int j)
    {
        super.removeRange(i, j);
        itemsChanged();
    }

    public boolean retainAll(Collection collection)
    {
        boolean flag = super.retainAll(collection);
        itemsChanged();
        return flag;
    }

    public volatile Object set(int i, Object obj)
    {
        return set(i, (Overlay)obj);
    }

    public Overlay set(int i, Overlay overlay)
    {
        Overlay overlay1 = (Overlay)super.set(i, overlay);
        itemsChanged();
        return overlay1;
    }
}

// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.maps.viewlayer;

import java.util.ArrayList;
import java.util.Iterator;
import jp.co.yahoo.android.maps.MapViewListener;

// Referenced classes of package jp.co.yahoo.android.maps.viewlayer:
//            BaseView

class this._cls0
    implements Runnable
{

    final BaseView this$0;

    public void run()
    {
        Iterator iterator = BaseView.access$5(BaseView.this).iterator();
        do
        {
            MapViewListener mapviewlistener;
            do
            {
                if (!iterator.hasNext())
                {
                    return;
                }
                mapviewlistener = (MapViewListener)iterator.next();
            } while (mapviewlistener == null);
            mapviewlistener.onSendAd();
        } while (true);
    }

    ()
    {
        this$0 = BaseView.this;
        super();
    }
}

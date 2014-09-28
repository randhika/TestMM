// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.maps.viewlayer;

import java.util.ArrayList;
import java.util.Iterator;
import jp.co.yahoo.android.maps.MapViewListener;

// Referenced classes of package jp.co.yahoo.android.maps.viewlayer:
//            BaseView

class this._cls1
    implements Runnable
{

    final this._cls1 this$1;

    public void run()
    {
        Iterator iterator = BaseView.access$5(cess._mth1(this._cls1.this)).iterator();
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
            mapviewlistener.onChangeScale(BaseView.access$6(cess._mth1(this._cls1.this)));
        } while (true);
    }

    ()
    {
        this$1 = this._cls1.this;
        super();
    }
}

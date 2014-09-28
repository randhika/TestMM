// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.common;

import android.graphics.Bitmap;
import android.support.v4.util.LruCache;

// Referenced classes of package jp.co.yahoo.android.apps.transit.common:
//            ImageCache

class this._cls0 extends LruCache
{

    final ImageCache this$0;

    protected volatile int sizeOf(Object obj, Object obj1)
    {
        return sizeOf((String)obj, (Bitmap)obj1);
    }

    protected int sizeOf(String s, Bitmap bitmap)
    {
        int i = ImageCache.getBitmapSize(bitmap) / 1024;
        if (i == 0)
        {
            i = 1;
        }
        return i;
    }

    (int i)
    {
        this$0 = ImageCache.this;
        super(i);
    }
}

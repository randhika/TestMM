// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.common;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import java.lang.ref.WeakReference;

// Referenced classes of package jp.co.yahoo.android.apps.transit.common:
//            ImageWorker

private static class frameBitmap extends BitmapDrawable
{

    private final WeakReference bitmapWorkerTaskReference;
    private Bitmap frameBitmap;

    public sk getBitmapWorkerTask()
    {
        return (sk)bitmapWorkerTaskReference.get();
    }

    public Bitmap getFrameBitmap()
    {
        return frameBitmap;
    }

    public sk(Resources resources, Bitmap bitmap, Bitmap bitmap1, sk sk)
    {
        super(resources, bitmap);
        bitmapWorkerTaskReference = new WeakReference(sk);
        frameBitmap = bitmap1;
    }
}

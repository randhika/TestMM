// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.common;

import android.graphics.Bitmap;
import android.widget.ImageView;
import java.io.File;
import java.lang.ref.WeakReference;
import java.util.HashMap;

// Referenced classes of package jp.co.yahoo.android.apps.transit.common:
//            AsyncTask, ImageWorker, ImageCache

private class imageViewReference extends AsyncTask
{

    private Object data;
    private final WeakReference imageViewReference;
    final ImageWorker this$0;

    private ImageView getAttachedImageView()
    {
        ImageView imageview = (ImageView)imageViewReference.get();
        if (this == ImageWorker.access$300(imageview))
        {
            return imageview;
        } else
        {
            return null;
        }
    }

    protected transient Bitmap doInBackground(Object aobj[])
    {
        Bitmap bitmap = null;
        String s;
        ImageCache imagecache;
        data = aobj[0];
        s = ((File)data).getPath();
        imagecache = mImageCache;
        bitmap = null;
        if (imagecache == null)
        {
            break MISSING_BLOCK_LABEL_50;
        }
        bitmap = mImageCache.getBitmapFromMemCache(s);
        Object obj = ImageWorker.access$100(ImageWorker.this);
        obj;
        JVM INSTR monitorenter ;
_L4:
        if (!mPauseWork) goto _L2; else goto _L1
_L1:
        boolean flag = isCancelled();
        if (flag) goto _L2; else goto _L3
_L3:
        Exception exception1;
        try
        {
            ImageWorker.access$100(ImageWorker.this).wait();
        }
        catch (InterruptedException interruptedexception) { }
        finally { }
        if (true) goto _L4; else goto _L2
_L2:
        obj;
        JVM INSTR monitorexit ;
        if (bitmap != null)
        {
            break MISSING_BLOCK_LABEL_143;
        }
        if (!isCancelled() && getAttachedImageView() != null && !mExitTasksEarly)
        {
            bitmap = processBitmap(aobj[0]);
        }
        if (bitmap != null) goto _L6; else goto _L5
_L5:
        if (mInvalidPathMap.containsKey(s)) goto _L8; else goto _L7
_L7:
        mInvalidPathMap.put(s, Integer.valueOf(1));
_L10:
        return bitmap;
        obj;
        JVM INSTR monitorexit ;
        try
        {
            throw exception1;
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
        }
        return bitmap;
_L8:
        int i = ((Integer)mInvalidPathMap.get(s)).intValue();
        mInvalidPathMap.put(s, Integer.valueOf(i + 1));
        return bitmap;
_L6:
        if (mImageCache == null) goto _L10; else goto _L9
_L9:
        String s1 = ((File)data).getPath();
        mImageCache.addBitmapToCache(s1, bitmap);
        return bitmap;
    }

    protected volatile Object doInBackground(Object aobj[])
    {
        return doInBackground(aobj);
    }

    protected void onCancelled(Bitmap bitmap)
    {
        super.onCancelled(bitmap);
        synchronized (ImageWorker.access$100(ImageWorker.this))
        {
            ImageWorker.access$100(ImageWorker.this).notifyAll();
        }
        return;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }

    protected volatile void onCancelled(Object obj)
    {
        onCancelled((Bitmap)obj);
    }

    protected void onPostExecute(Bitmap bitmap)
    {
        ImageView imageview;
        onCancelled oncancelled;
        Bitmap bitmap1;
        int i;
        if (isCancelled() || mExitTasksEarly)
        {
            bitmap = null;
        }
        try
        {
            imageview = getAttachedImageView();
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
            return;
        }
        if (bitmap == null || imageview == null)
        {
            break MISSING_BLOCK_LABEL_89;
        }
        oncancelled = (getAttachedImageView)imageview.getDrawable();
        bitmap1 = oncancelled.FrameBitmap();
        if (bitmap1 != null)
        {
            break MISSING_BLOCK_LABEL_61;
        }
        bitmap1 = oncancelled.Bitmap();
        i = ((Integer)imageview.getTag()).intValue();
        imageview.setImageDrawable(ImageWorker.access$200(ImageWorker.this, i, bitmap, bitmap1));
    }

    protected volatile void onPostExecute(Object obj)
    {
        onPostExecute((Bitmap)obj);
    }


    public (ImageView imageview)
    {
        this$0 = ImageWorker.this;
        super();
        imageViewReference = new WeakReference(imageview);
    }
}

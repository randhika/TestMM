// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.common;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.widget.ImageView;
import java.io.File;
import java.lang.ref.WeakReference;
import java.util.HashMap;

// Referenced classes of package jp.co.yahoo.android.apps.transit.common:
//            ImageCache, AsyncTask

public abstract class ImageWorker
{
    private static class AsyncDrawable extends BitmapDrawable
    {

        private final WeakReference bitmapWorkerTaskReference;
        private Bitmap frameBitmap;

        public BitmapWorkerTask getBitmapWorkerTask()
        {
            return (BitmapWorkerTask)bitmapWorkerTaskReference.get();
        }

        public Bitmap getFrameBitmap()
        {
            return frameBitmap;
        }

        public AsyncDrawable(Resources resources, Bitmap bitmap, Bitmap bitmap1, BitmapWorkerTask bitmapworkertask)
        {
            super(resources, bitmap);
            bitmapWorkerTaskReference = new WeakReference(bitmapworkertask);
            frameBitmap = bitmap1;
        }
    }

    private class BitmapWorkerTask extends AsyncTask
    {

        private Object data;
        private final WeakReference imageViewReference;
        final ImageWorker this$0;

        private ImageView getAttachedImageView()
        {
            ImageView imageview = (ImageView)imageViewReference.get();
            if (this == ImageWorker.getBitmapWorkerTask(imageview))
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
            Object obj = mPauseWorkLock;
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
                mPauseWorkLock.wait();
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
            synchronized (mPauseWorkLock)
            {
                mPauseWorkLock.notifyAll();
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
            AsyncDrawable asyncdrawable;
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
            asyncdrawable = (AsyncDrawable)imageview.getDrawable();
            bitmap1 = asyncdrawable.getFrameBitmap();
            if (bitmap1 != null)
            {
                break MISSING_BLOCK_LABEL_61;
            }
            bitmap1 = asyncdrawable.getBitmap();
            i = ((Integer)imageview.getTag()).intValue();
            imageview.setImageDrawable(getLayerDrawable(i, bitmap, bitmap1));
        }

        protected volatile void onPostExecute(Object obj)
        {
            onPostExecute((Bitmap)obj);
        }


        public BitmapWorkerTask(ImageView imageview)
        {
            this$0 = ImageWorker.this;
            super();
            imageViewReference = new WeakReference(imageview);
        }
    }

    protected class CacheAsyncTask extends AsyncTask
    {

        private Integer mAction;
        private File mFile;
        final ImageWorker this$0;

        protected volatile Object doInBackground(Object aobj[])
        {
            return doInBackground(aobj);
        }

        protected transient Void doInBackground(Object aobj[])
        {
            mAction = (Integer)aobj[0];
            ((Integer)aobj[0]).intValue();
            JVM INSTR tableswitch 0 4: default 52
        //                       0 54
        //                       1 64
        //                       2 97
        //                       3 107
        //                       4 117;
               goto _L1 _L2 _L3 _L4 _L5 _L6
_L1:
            return null;
_L2:
            clearCacheInternal();
            continue; /* Loop/switch isn't completed */
_L3:
            if (aobj.length > 1)
            {
                File file = (File)aobj[1];
                if (file != null)
                {
                    mFile = file;
                    removeCacheInternal(file);
                }
            }
            continue; /* Loop/switch isn't completed */
_L4:
            flushCacheInternal();
            continue; /* Loop/switch isn't completed */
_L5:
            closeCacheInternal();
            continue; /* Loop/switch isn't completed */
_L6:
            clearInvalidFileCacheInternal();
            if (true) goto _L1; else goto _L7
_L7:
        }

        protected volatile void onPostExecute(Object obj)
        {
            onPostExecute((Void)obj);
        }

        protected void onPostExecute(Void void1)
        {
            if (mCacheListener == null) goto _L2; else goto _L1
_L1:
            mAction.intValue();
            JVM INSTR tableswitch 0 1: default 40
        //                       0 41
        //                       1 54;
               goto _L2 _L3 _L4
_L2:
            return;
_L3:
            mCacheListener.onClearCache();
            return;
_L4:
            if (mFile != null)
            {
                mCacheListener.onRemoveCache(mFile);
                mFile = null;
                return;
            }
            if (true) goto _L2; else goto _L5
_L5:
        }

        protected CacheAsyncTask()
        {
            this$0 = ImageWorker.this;
            super();
            mFile = null;
        }
    }

    public static interface OnCacheListener
    {

        public abstract void onClearCache();

        public abstract void onRemoveCache(Object obj);
    }


    protected static final int FADE_IN_TIME = 200;
    protected static final int LIMIT_INVALID_COUNT = 3;
    private static final int MESSAGE_CLEAR = 0;
    private static final int MESSAGE_CLEAR_INVALIDFILE = 4;
    private static final int MESSAGE_CLOSE = 3;
    private static final int MESSAGE_FLUSH = 2;
    private static final int MESSAGE_REMOVE = 1;
    private OnCacheListener mCacheListener;
    protected boolean mExitTasksEarly;
    protected boolean mFadeInBitmap;
    protected ImageCache mImageCache;
    protected HashMap mInvalidPathMap;
    protected boolean mPauseWork;
    private final Object mPauseWorkLock;
    protected Resources mResources;

    protected ImageWorker()
    {
        mInvalidPathMap = new HashMap();
        mFadeInBitmap = true;
        mExitTasksEarly = false;
        mPauseWork = false;
        mPauseWorkLock = new Object();
        mCacheListener = null;
    }

    protected ImageWorker(Context context)
    {
        mInvalidPathMap = new HashMap();
        mFadeInBitmap = true;
        mExitTasksEarly = false;
        mPauseWork = false;
        mPauseWorkLock = new Object();
        mCacheListener = null;
        mResources = context.getResources();
    }

    public static boolean cancelPotentialWork(Object obj, ImageView imageview)
    {
label0:
        {
            BitmapWorkerTask bitmapworkertask = getBitmapWorkerTask(imageview);
            if (bitmapworkertask != null)
            {
                Object obj1 = bitmapworkertask.data;
                if (obj1 != null && obj1.equals(obj))
                {
                    break label0;
                }
                bitmapworkertask.cancel(true);
            }
            return true;
        }
        return false;
    }

    public static void cancelWork(ImageView imageview)
    {
        BitmapWorkerTask bitmapworkertask = getBitmapWorkerTask(imageview);
        if (bitmapworkertask != null)
        {
            bitmapworkertask.cancel(true);
        }
    }

    private static BitmapWorkerTask getBitmapWorkerTask(ImageView imageview)
    {
        if (imageview != null)
        {
            Drawable drawable = imageview.getDrawable();
            if (drawable instanceof AsyncDrawable)
            {
                return ((AsyncDrawable)drawable).getBitmapWorkerTask();
            }
        }
        return null;
    }

    private Drawable getLayerDrawable(int i, Bitmap bitmap, Bitmap bitmap1)
    {
        Bitmap bitmap2;
        switch (i)
        {
        default:
            return null;

        case 0: // '\0'
            return new BitmapDrawable(mResources, getThumbnailBitmap(bitmap, bitmap1));

        case 1: // '\001'
            Bitmap bitmap3 = getThumbnailBitmap(bitmap, bitmap1);
            Drawable adrawable2[] = new Drawable[2];
            adrawable2[0] = new BitmapDrawable(mResources, bitmap3);
            adrawable2[1] = new BitmapDrawable(mResources, bitmap1);
            return new LayerDrawable(adrawable2);

        case 2: // '\002'
            Drawable adrawable1[] = new Drawable[2];
            adrawable1[0] = new BitmapDrawable(mResources, bitmap1);
            adrawable1[1] = new BitmapDrawable(mResources, bitmap);
            LayerDrawable layerdrawable = new LayerDrawable(adrawable1);
            layerdrawable.setLayerInset(1, 10, 10, 10, 10);
            return layerdrawable;

        case 3: // '\003'
        case 4: // '\004'
            bitmap2 = getThumbnailBitmap(bitmap, bitmap1);
            break;
        }
        Drawable adrawable[] = new Drawable[2];
        adrawable[0] = new BitmapDrawable(mResources, bitmap2);
        adrawable[1] = new BitmapDrawable(mResources, bitmap1);
        return new LayerDrawable(adrawable);
    }

    private Bitmap getThumbnailBitmap(Bitmap bitmap, Bitmap bitmap1)
    {
        int i = bitmap.getWidth();
        int j = bitmap.getHeight();
        int k = bitmap1.getWidth();
        int l = bitmap1.getHeight();
        float f = Math.max((float)k / (float)i, (float)l / (float)j);
        Matrix matrix = new Matrix();
        matrix.postScale(f, f);
        Bitmap bitmap2;
        int i1;
        int j1;
        int k1;
        int l1;
        Bitmap bitmap3;
        try
        {
            bitmap2 = Bitmap.createBitmap(bitmap, 0, 0, i, j, matrix, true);
        }
        catch (OutOfMemoryError outofmemoryerror)
        {
            return null;
        }
        i1 = bitmap2.getWidth();
        j1 = bitmap2.getHeight();
        k1 = (i1 - k) / 2;
        l1 = (j1 - l) / 2;
        try
        {
            bitmap3 = Bitmap.createBitmap(bitmap2, k1, l1, k, l);
        }
        catch (OutOfMemoryError outofmemoryerror1)
        {
            return null;
        }
        return bitmap3;
    }

    private boolean preLoadImage(Object obj, ImageView imageview, Bitmap bitmap, int i)
    {
        if (imageview != null && bitmap != null)
        {
            if (obj == null)
            {
                imageview.setImageBitmap(bitmap);
                return false;
            }
            if (obj instanceof File)
            {
                android.view.ViewGroup.LayoutParams layoutparams = imageview.getLayoutParams();
                if (layoutparams != null)
                {
                    int j = layoutparams.width;
                    if (j == 0 || j == -2)
                    {
                        int k = bitmap.getWidth();
                        int l = bitmap.getHeight();
                        layoutparams.width = k;
                        layoutparams.height = l;
                    }
                }
                return true;
            }
        }
        return false;
    }

    public void clearCache()
    {
        CacheAsyncTask cacheasynctask = new CacheAsyncTask();
        Object aobj[] = new Object[1];
        aobj[0] = Integer.valueOf(0);
        cacheasynctask.execute(aobj);
    }

    protected void clearCacheInternal()
    {
        if (mImageCache != null)
        {
            mImageCache.clearCache();
        }
    }

    public void clearInvalidFileCache()
    {
        CacheAsyncTask cacheasynctask = new CacheAsyncTask();
        Object aobj[] = new Object[1];
        aobj[0] = Integer.valueOf(4);
        cacheasynctask.execute(aobj);
    }

    protected void clearInvalidFileCacheInternal()
    {
        if (mInvalidPathMap != null)
        {
            mInvalidPathMap.clear();
        }
    }

    public void closeCache()
    {
        CacheAsyncTask cacheasynctask = new CacheAsyncTask();
        Object aobj[] = new Object[1];
        aobj[0] = Integer.valueOf(3);
        cacheasynctask.execute(aobj);
    }

    protected void closeCacheInternal()
    {
        if (mImageCache != null)
        {
            mImageCache.close();
            mImageCache = null;
        }
    }

    public void flushCache()
    {
        CacheAsyncTask cacheasynctask = new CacheAsyncTask();
        Object aobj[] = new Object[1];
        aobj[0] = Integer.valueOf(2);
        cacheasynctask.execute(aobj);
    }

    protected void flushCacheInternal()
    {
        if (mImageCache != null)
        {
            mImageCache.flush();
        }
    }

    public void loadImage(Object obj, ImageView imageview, Bitmap bitmap, int i)
    {
        loadImage(obj, imageview, bitmap, bitmap, i);
    }

    public void loadImage(Object obj, ImageView imageview, Bitmap bitmap, Bitmap bitmap1, int i)
    {
        if (!preLoadImage(obj, imageview, bitmap, i))
        {
            return;
        }
        String s;
        ImageCache imagecache;
        s = ((File)obj).getPath();
        imagecache = mImageCache;
        Bitmap bitmap2 = null;
        if (imagecache == null) goto _L2; else goto _L1
_L1:
        bitmap2 = mImageCache.getBitmapFromMemCache(s);
          goto _L2
_L6:
        try
        {
            imageview.setImageDrawable(getLayerDrawable(i, bitmap2, bitmap1));
            return;
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
        }
        return;
_L4:
        if (cancelPotentialWork(obj, imageview))
        {
            BitmapWorkerTask bitmapworkertask = new BitmapWorkerTask(imageview);
            imageview.setImageDrawable(new AsyncDrawable(mResources, bitmap, bitmap1, bitmapworkertask));
            imageview.setTag(Integer.valueOf(i));
            if (!mInvalidPathMap.containsKey(s) || ((Integer)mInvalidPathMap.get(s)).intValue() < 3)
            {
                bitmapworkertask.executeOnExecutor(AsyncTask.DUAL_THREAD_EXECUTOR, new Object[] {
                    obj
                });
            }
        }
        return;
_L2:
        if (bitmap2 == null) goto _L4; else goto _L3
_L3:
        if (bitmap1 == null)
        {
            bitmap1 = bitmap;
        }
        if (true) goto _L6; else goto _L5
_L5:
    }

    protected abstract Bitmap processBitmap(Object obj);

    public void removeCache(Object obj)
    {
        if (obj != null && (obj instanceof File))
        {
            CacheAsyncTask cacheasynctask = new CacheAsyncTask();
            Object aobj[] = new Object[2];
            aobj[0] = Integer.valueOf(1);
            aobj[1] = obj;
            cacheasynctask.execute(aobj);
        }
    }

    protected void removeCacheInternal(Object obj)
    {
        if (mImageCache != null && (obj instanceof File))
        {
            mImageCache.removeBitmapToCache(((File)obj).getPath());
        }
    }

    public void setExitTasksEarly(boolean flag)
    {
        mExitTasksEarly = flag;
    }

    public void setImageCache(ImageCache imagecache)
    {
        mImageCache = imagecache;
    }

    public void setImageFadeIn(boolean flag)
    {
        mFadeInBitmap = flag;
    }

    public void setOnCacheListener(OnCacheListener oncachelistener)
    {
        mCacheListener = oncachelistener;
    }

    public void setPauseWork(boolean flag)
    {
        synchronized (mPauseWorkLock)
        {
            mPauseWork = flag;
            if (!mPauseWork)
            {
                mPauseWorkLock.notifyAll();
            }
        }
        return;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }




}

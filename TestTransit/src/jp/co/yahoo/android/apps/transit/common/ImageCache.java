// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.common;

import android.graphics.Bitmap;
import android.support.v4.util.LruCache;

public class ImageCache
{
    public static class ImageCacheParams
    {

        public int compressQuality;
        public int memCacheSize;
        public boolean memoryCacheEnabled;

        public void setMemCacheSizePercent(float f)
        {
            if (f < 0.05F || f > 0.8F)
            {
                throw new IllegalArgumentException("setMemCacheSizePercent - percent must be between 0.05 and 0.8 (inclusive)");
            } else
            {
                memCacheSize = Math.round((f * (float)Runtime.getRuntime().maxMemory()) / 1024F);
                return;
            }
        }

        public ImageCacheParams()
        {
            memCacheSize = 5120;
            compressQuality = 70;
            memoryCacheEnabled = true;
            setMemCacheSizePercent(0.1F);
        }
    }


    private static final int DEFAULT_COMPRESS_QUALITY = 70;
    private static final boolean DEFAULT_MEM_CACHE_ENABLED = true;
    private static final int DEFAULT_MEM_CACHE_SIZE = 5120;
    private static final float DEFAULT_MEM_CACHE_SIZE_PERCENT = 0.1F;
    private ImageCacheParams mCacheParams;
    private LruCache mMemoryCache;

    public ImageCache()
    {
        init(new ImageCacheParams());
    }

    public ImageCache(ImageCacheParams imagecacheparams)
    {
        init(imagecacheparams);
    }

    public static int getBitmapSize(Bitmap bitmap)
    {
        return bitmap.getRowBytes() * bitmap.getHeight();
    }

    private void init(ImageCacheParams imagecacheparams)
    {
        mCacheParams = imagecacheparams;
        if (mCacheParams.memoryCacheEnabled)
        {
            mMemoryCache = new LruCache(mCacheParams.memCacheSize) {

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

            
            {
                this$0 = ImageCache.this;
                super(i);
            }
            };
        }
    }

    public void addBitmapToCache(String s, Bitmap bitmap)
    {
        while (s == null || bitmap == null || mMemoryCache == null || mMemoryCache.get(s) != null) 
        {
            return;
        }
        mMemoryCache.put(s, bitmap);
    }

    public void clearCache()
    {
        if (mMemoryCache != null)
        {
            mMemoryCache.evictAll();
        }
    }

    public void close()
    {
    }

    public void flush()
    {
    }

    public Bitmap getBitmapFromMemCache(String s)
    {
        if (mMemoryCache != null)
        {
            Bitmap bitmap = (Bitmap)mMemoryCache.get(s);
            if (bitmap != null)
            {
                return bitmap;
            }
        }
        return null;
    }

    public void removeBitmapToCache(String s)
    {
        while (s == null || mMemoryCache == null) 
        {
            return;
        }
        mMemoryCache.remove(s);
    }
}

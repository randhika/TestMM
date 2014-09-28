// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.android.volley.toolbox;

import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Looper;
import android.widget.ImageView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

// Referenced classes of package com.android.volley.toolbox:
//            ImageRequest

public class ImageLoader
{
    private class BatchedImageRequest
    {

        private final LinkedList mContainers = new LinkedList();
        private VolleyError mError;
        private final Request mRequest;
        private Bitmap mResponseBitmap;
        final ImageLoader this$0;

        public void addContainer(ImageContainer imagecontainer)
        {
            mContainers.add(imagecontainer);
        }

        public VolleyError getError()
        {
            return mError;
        }

        public boolean removeContainerAndCancelIfNecessary(ImageContainer imagecontainer)
        {
            mContainers.remove(imagecontainer);
            if (mContainers.size() == 0)
            {
                mRequest.cancel();
                return true;
            } else
            {
                return false;
            }
        }

        public void setError(VolleyError volleyerror)
        {
            mError = volleyerror;
        }




        public BatchedImageRequest(Request request, ImageContainer imagecontainer)
        {
            this$0 = ImageLoader.this;
            super();
            mRequest = request;
            mContainers.add(imagecontainer);
        }
    }

    public static interface ImageCache
    {

        public abstract Bitmap getBitmap(String s);

        public abstract void putBitmap(String s, Bitmap bitmap);
    }

    public class ImageContainer
    {

        private Bitmap mBitmap;
        private final String mCacheKey;
        private final ImageListener mListener;
        private final String mRequestUrl;
        final ImageLoader this$0;

        public void cancelRequest()
        {
            if (mListener != null) goto _L2; else goto _L1
_L1:
            return;
_L2:
            BatchedImageRequest batchedimagerequest = (BatchedImageRequest)mInFlightRequests.get(mCacheKey);
            if (batchedimagerequest == null)
            {
                break; /* Loop/switch isn't completed */
            }
            if (batchedimagerequest.removeContainerAndCancelIfNecessary(this))
            {
                mInFlightRequests.remove(mCacheKey);
                return;
            }
            if (true) goto _L1; else goto _L3
_L3:
            BatchedImageRequest batchedimagerequest1 = (BatchedImageRequest)mBatchedResponses.get(mCacheKey);
            if (batchedimagerequest1 != null)
            {
                batchedimagerequest1.removeContainerAndCancelIfNecessary(this);
                if (batchedimagerequest1.mContainers.size() == 0)
                {
                    mBatchedResponses.remove(mCacheKey);
                    return;
                }
            }
            if (true) goto _L1; else goto _L4
_L4:
        }

        public Bitmap getBitmap()
        {
            return mBitmap;
        }

        public String getRequestUrl()
        {
            return mRequestUrl;
        }



        public ImageContainer(Bitmap bitmap, String s, String s1, ImageListener imagelistener)
        {
            this$0 = ImageLoader.this;
            super();
            mBitmap = bitmap;
            mRequestUrl = s;
            mCacheKey = s1;
            mListener = imagelistener;
        }
    }

    public static interface ImageListener
        extends com.android.volley.Response.ErrorListener
    {

        public abstract void onResponse(ImageContainer imagecontainer, boolean flag);
    }


    private int mBatchResponseDelayMs;
    private final HashMap mBatchedResponses = new HashMap();
    private final ImageCache mCache;
    private final Handler mHandler = new Handler(Looper.getMainLooper());
    private final HashMap mInFlightRequests = new HashMap();
    private final RequestQueue mRequestQueue;
    private Runnable mRunnable;

    public ImageLoader(RequestQueue requestqueue, ImageCache imagecache)
    {
        mBatchResponseDelayMs = 100;
        mRequestQueue = requestqueue;
        mCache = imagecache;
    }

    private void batchResponse(String s, BatchedImageRequest batchedimagerequest)
    {
        mBatchedResponses.put(s, batchedimagerequest);
        if (mRunnable == null)
        {
            mRunnable = new Runnable() {

                final ImageLoader this$0;

                public void run()
                {
                    Iterator iterator = mBatchedResponses.values().iterator();
                    do
                    {
                        if (!iterator.hasNext())
                        {
                            mBatchedResponses.clear();
                            mRunnable = null;
                            return;
                        }
                        BatchedImageRequest batchedimagerequest1 = (BatchedImageRequest)iterator.next();
                        Iterator iterator1 = batchedimagerequest1.mContainers.iterator();
                        while (iterator1.hasNext()) 
                        {
                            ImageContainer imagecontainer = (ImageContainer)iterator1.next();
                            if (imagecontainer.mListener != null)
                            {
                                if (batchedimagerequest1.getError() == null)
                                {
                                    imagecontainer.mBitmap = batchedimagerequest1.mResponseBitmap;
                                    imagecontainer.mListener.onResponse(imagecontainer, false);
                                } else
                                {
                                    imagecontainer.mListener.onErrorResponse(batchedimagerequest1.getError());
                                }
                            }
                        }
                    } while (true);
                }

            
            {
                this$0 = ImageLoader.this;
                super();
            }
            };
            mHandler.postDelayed(mRunnable, mBatchResponseDelayMs);
        }
    }

    private static String getCacheKey(String s, int i, int j)
    {
        return (new StringBuilder(12 + s.length())).append("#W").append(i).append("#H").append(j).append(s).toString();
    }

    public static ImageListener getImageListener(final ImageView view, final int defaultImageResId, final int errorImageResId)
    {
        return new ImageListener() {

            private final int val$defaultImageResId;
            private final int val$errorImageResId;
            private final ImageView val$view;

            public void onErrorResponse(VolleyError volleyerror)
            {
                if (errorImageResId != 0)
                {
                    view.setImageResource(errorImageResId);
                }
            }

            public void onResponse(ImageContainer imagecontainer, boolean flag)
            {
                if (imagecontainer.getBitmap() != null)
                {
                    view.setImageBitmap(imagecontainer.getBitmap());
                } else
                if (defaultImageResId != 0)
                {
                    view.setImageResource(defaultImageResId);
                    return;
                }
            }

            
            {
                errorImageResId = i;
                view = imageview;
                defaultImageResId = j;
                super();
            }
        };
    }

    private void onGetImageError(String s, VolleyError volleyerror)
    {
        BatchedImageRequest batchedimagerequest = (BatchedImageRequest)mInFlightRequests.remove(s);
        batchedimagerequest.setError(volleyerror);
        if (batchedimagerequest != null)
        {
            batchResponse(s, batchedimagerequest);
        }
    }

    private void onGetImageSuccess(String s, Bitmap bitmap)
    {
        mCache.putBitmap(s, bitmap);
        BatchedImageRequest batchedimagerequest = (BatchedImageRequest)mInFlightRequests.remove(s);
        if (batchedimagerequest != null)
        {
            batchedimagerequest.mResponseBitmap = bitmap;
            batchResponse(s, batchedimagerequest);
        }
    }

    private void throwIfNotOnMainThread()
    {
        if (Looper.myLooper() != Looper.getMainLooper())
        {
            throw new IllegalStateException("ImageLoader must be invoked from the main thread.");
        } else
        {
            return;
        }
    }

    public ImageContainer get(String s, ImageListener imagelistener)
    {
        return get(s, imagelistener, 0, 0);
    }

    public ImageContainer get(String s, ImageListener imagelistener, int i, int j)
    {
        throwIfNotOnMainThread();
        final String cacheKey = getCacheKey(s, i, j);
        Bitmap bitmap = mCache.getBitmap(cacheKey);
        if (bitmap != null)
        {
            ImageContainer imagecontainer = new ImageContainer(bitmap, s, null, null);
            imagelistener.onResponse(imagecontainer, true);
            return imagecontainer;
        }
        ImageContainer imagecontainer1 = new ImageContainer(null, s, cacheKey, imagelistener);
        imagelistener.onResponse(imagecontainer1, true);
        BatchedImageRequest batchedimagerequest = (BatchedImageRequest)mInFlightRequests.get(cacheKey);
        if (batchedimagerequest != null)
        {
            batchedimagerequest.addContainer(imagecontainer1);
            return imagecontainer1;
        } else
        {
            com.android.volley.Response.Listener listener = new com.android.volley.Response.Listener() {

                final ImageLoader this$0;
                private final String val$cacheKey;

                public void onResponse(Bitmap bitmap1)
                {
                    onGetImageSuccess(cacheKey, bitmap1);
                }

                public volatile void onResponse(Object obj)
                {
                    onResponse((Bitmap)obj);
                }

            
            {
                this$0 = ImageLoader.this;
                cacheKey = s;
                super();
            }
            };
            android.graphics.Bitmap.Config config = android.graphics.Bitmap.Config.RGB_565;
            com.android.volley.Response.ErrorListener errorlistener = new com.android.volley.Response.ErrorListener() {

                final ImageLoader this$0;
                private final String val$cacheKey;

                public void onErrorResponse(VolleyError volleyerror)
                {
                    onGetImageError(cacheKey, volleyerror);
                }

            
            {
                this$0 = ImageLoader.this;
                cacheKey = s;
                super();
            }
            };
            ImageRequest imagerequest = new ImageRequest(s, listener, i, j, config, errorlistener);
            mRequestQueue.add(imagerequest);
            mInFlightRequests.put(cacheKey, new BatchedImageRequest(imagerequest, imagecontainer1));
            return imagecontainer1;
        }
    }

    public boolean isCached(String s, int i, int j)
    {
        throwIfNotOnMainThread();
        String s1 = getCacheKey(s, i, j);
        return mCache.getBitmap(s1) != null;
    }

    public void setBatchedResponseDelay(int i)
    {
        mBatchResponseDelayMs = i;
    }





}

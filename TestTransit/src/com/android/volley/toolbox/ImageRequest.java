// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.android.volley.toolbox;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyLog;

// Referenced classes of package com.android.volley.toolbox:
//            HttpHeaderParser

public class ImageRequest extends Request
{

    private static final float IMAGE_BACKOFF_MULT = 2F;
    private static final int IMAGE_MAX_RETRIES = 2;
    private static final int IMAGE_TIMEOUT_MS = 1000;
    private static final Object sDecodeLock = new Object();
    private final android.graphics.Bitmap.Config mDecodeConfig;
    private final com.android.volley.Response.Listener mListener;
    private final int mMaxHeight;
    private final int mMaxWidth;

    public ImageRequest(String s, com.android.volley.Response.Listener listener, int i, int j, android.graphics.Bitmap.Config config, com.android.volley.Response.ErrorListener errorlistener)
    {
        super(0, s, errorlistener);
        setRetryPolicy(new DefaultRetryPolicy(1000, 2, 2.0F));
        mListener = listener;
        mDecodeConfig = config;
        mMaxWidth = i;
        mMaxHeight = j;
    }

    private Response doParse(NetworkResponse networkresponse)
    {
        byte abyte0[] = networkresponse.data;
        android.graphics.BitmapFactory.Options options = new android.graphics.BitmapFactory.Options();
        Bitmap bitmap1;
        if (mMaxWidth == 0 && mMaxHeight == 0)
        {
            options.inPreferredConfig = mDecodeConfig;
            bitmap1 = BitmapFactory.decodeByteArray(abyte0, 0, abyte0.length, options);
        } else
        {
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeByteArray(abyte0, 0, abyte0.length, options);
            int i = options.outWidth;
            int j = options.outHeight;
            int k = getResizedDimension(mMaxWidth, mMaxHeight, i, j);
            int l = getResizedDimension(mMaxHeight, mMaxWidth, j, i);
            options.inJustDecodeBounds = false;
            options.inSampleSize = findBestSampleSize(i, j, k, l);
            Bitmap bitmap = BitmapFactory.decodeByteArray(abyte0, 0, abyte0.length, options);
            if (bitmap != null && (bitmap.getWidth() > k || bitmap.getHeight() > l))
            {
                bitmap1 = Bitmap.createScaledBitmap(bitmap, k, l, true);
                bitmap.recycle();
            } else
            {
                bitmap1 = bitmap;
            }
        }
        if (bitmap1 == null)
        {
            return Response.error(new ParseError(networkresponse));
        } else
        {
            return Response.success(bitmap1, HttpHeaderParser.parseCacheHeaders(networkresponse));
        }
    }

    static int findBestSampleSize(int i, int j, int k, int l)
    {
        double d = Math.min((double)i / (double)k, (double)j / (double)l);
        float f = 1.0F;
        do
        {
            if ((double)(f * 2.0F) > d)
            {
                return (int)f;
            }
            f *= 2.0F;
        } while (true);
    }

    private static int getResizedDimension(int i, int j, int k, int l)
    {
        if (i == 0 && j == 0)
        {
            return k;
        }
        if (i == 0)
        {
            return (int)(((double)j / (double)l) * (double)k);
        }
        if (j == 0)
        {
            return i;
        }
        double d = (double)l / (double)k;
        int i1 = i;
        if (d * (double)i1 > (double)j)
        {
            i1 = (int)((double)j / d);
        }
        return i1;
    }

    protected void deliverResponse(Bitmap bitmap)
    {
        mListener.onResponse(bitmap);
    }

    protected volatile void deliverResponse(Object obj)
    {
        deliverResponse((Bitmap)obj);
    }

    public com.android.volley.Request.Priority getPriority()
    {
        return com.android.volley.Request.Priority.LOW;
    }

    protected Response parseNetworkResponse(NetworkResponse networkresponse)
    {
        obj;
        JVM INSTR monitorenter ;
        Response response1;
        synchronized (sDecodeLock)
        {
            response1 = doParse(networkresponse);
        }
        return response1;
        OutOfMemoryError outofmemoryerror;
        outofmemoryerror;
        Response response;
        Object aobj[] = new Object[2];
        aobj[0] = Integer.valueOf(networkresponse.data.length);
        aobj[1] = getUrl();
        VolleyLog.e("Caught OOM for %d byte image, url=%s", aobj);
        response = Response.error(new ParseError(outofmemoryerror));
        obj;
        JVM INSTR monitorexit ;
        return response;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }

}

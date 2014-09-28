// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.common.images;

import android.app.ActivityManager;
import android.content.ComponentCallbacks2;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.ParcelFileDescriptor;
import android.os.ResultReceiver;
import android.os.SystemClock;
import android.util.Log;
import android.widget.ImageView;
import com.google.android.gms.internal.gw;
import com.google.android.gms.internal.gx;
import com.google.android.gms.internal.hq;
import com.google.android.gms.internal.ip;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

// Referenced classes of package com.google.android.gms.common.images:
//            a

public final class ImageManager
{
    private final class ImageReceiver extends ResultReceiver
    {

        private final ArrayList Fh = new ArrayList();
        final ImageManager Fi;
        private final Uri mUri;

        static ArrayList a(ImageReceiver imagereceiver)
        {
            return imagereceiver.Fh;
        }

        public void b(com.google.android.gms.common.images.a a1)
        {
            gx.ay("ImageReceiver.addImageRequest() must be called in the main thread");
            Fh.add(a1);
        }

        public void c(com.google.android.gms.common.images.a a1)
        {
            gx.ay("ImageReceiver.removeImageRequest() must be called in the main thread");
            Fh.remove(a1);
        }

        public void ff()
        {
            Intent intent = new Intent("com.google.android.gms.common.images.LOAD_IMAGE");
            intent.putExtra("com.google.android.gms.extras.uri", mUri);
            intent.putExtra("com.google.android.gms.extras.resultReceiver", this);
            intent.putExtra("com.google.android.gms.extras.priority", 3);
            ImageManager.b(Fi).sendBroadcast(intent);
        }

        public void onReceiveResult(int i, Bundle bundle)
        {
            ParcelFileDescriptor parcelfiledescriptor = (ParcelFileDescriptor)bundle.getParcelable("com.google.android.gms.extra.fileDescriptor");
            ImageManager.f(Fi).execute(Fi. new c(mUri, parcelfiledescriptor));
        }

        ImageReceiver(Uri uri)
        {
            Fi = ImageManager.this;
            super(new Handler(Looper.getMainLooper()));
            mUri = uri;
        }
    }

    public static interface OnImageLoadedListener
    {

        public abstract void onImageLoaded(Uri uri, Drawable drawable, boolean flag);
    }

    private static final class a
    {

        static int a(ActivityManager activitymanager)
        {
            return activitymanager.getLargeMemoryClass();
        }
    }

    private static final class b extends hq
    {

        private static int D(Context context)
        {
            ActivityManager activitymanager = (ActivityManager)context.getSystemService("activity");
            boolean flag;
            int i;
            if ((0x100000 & context.getApplicationInfo().flags) != 0)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (flag && ip.gc())
            {
                i = a.a(activitymanager);
            } else
            {
                i = activitymanager.getMemoryClass();
            }
            return (int)(0.33F * (float)(i * 0x100000));
        }

        protected int a(a.a a1, Bitmap bitmap)
        {
            return bitmap.getHeight() * bitmap.getRowBytes();
        }

        protected void a(boolean flag, a.a a1, Bitmap bitmap, Bitmap bitmap1)
        {
            super.entryRemoved(flag, a1, bitmap, bitmap1);
        }

        protected void entryRemoved(boolean flag, Object obj, Object obj1, Object obj2)
        {
            a(flag, (a.a)obj, (Bitmap)obj1, (Bitmap)obj2);
        }

        protected int sizeOf(Object obj, Object obj1)
        {
            return a((a.a)obj, (Bitmap)obj1);
        }

        public b(Context context)
        {
            super(D(context));
        }
    }

    private final class c
        implements Runnable
    {

        final ImageManager Fi;
        private final ParcelFileDescriptor Fj;
        private final Uri mUri;

        public void run()
        {
            ParcelFileDescriptor parcelfiledescriptor;
            Bitmap bitmap;
            boolean flag;
            gx.az("LoadBitmapFromDiskRunnable can't be executed in the main thread");
            parcelfiledescriptor = Fj;
            bitmap = null;
            flag = false;
            if (parcelfiledescriptor == null) goto _L2; else goto _L1
_L1:
            Bitmap bitmap1 = BitmapFactory.decodeFileDescriptor(Fj.getFileDescriptor());
            bitmap = bitmap1;
_L3:
            CountDownLatch countdownlatch;
            OutOfMemoryError outofmemoryerror;
            try
            {
                Fj.close();
            }
            catch (IOException ioexception)
            {
                Log.e("ImageManager", "closed failed", ioexception);
            }
_L2:
            countdownlatch = new CountDownLatch(1);
            ImageManager.g(Fi).post(Fi. new f(mUri, bitmap, flag, countdownlatch));
            try
            {
                countdownlatch.await();
                return;
            }
            catch (InterruptedException interruptedexception)
            {
                Log.w("ImageManager", (new StringBuilder()).append("Latch interrupted while posting ").append(mUri).toString());
            }
            break MISSING_BLOCK_LABEL_170;
            outofmemoryerror;
            Log.e("ImageManager", (new StringBuilder()).append("OOM while loading bitmap for uri: ").append(mUri).toString(), outofmemoryerror);
            flag = true;
            bitmap = null;
              goto _L3
        }

        public c(Uri uri, ParcelFileDescriptor parcelfiledescriptor)
        {
            Fi = ImageManager.this;
            super();
            mUri = uri;
            Fj = parcelfiledescriptor;
        }
    }

    private final class d
        implements Runnable
    {

        final ImageManager Fi;
        private final com.google.android.gms.common.images.a Fk;

        public void run()
        {
            gx.ay("LoadImageRunnable must be executed on the main thread");
            ImageReceiver imagereceiver = (ImageReceiver)ImageManager.a(Fi).get(Fk);
            if (imagereceiver != null)
            {
                ImageManager.a(Fi).remove(Fk);
                imagereceiver.c(Fk);
            }
            a.a a1 = Fk.Fm;
            if (a1.uri == null)
            {
                Fk.a(ImageManager.b(Fi), ImageManager.c(Fi), true);
                return;
            }
            Bitmap bitmap = ImageManager.a(Fi, a1);
            if (bitmap != null)
            {
                Fk.a(ImageManager.b(Fi), bitmap, true);
                return;
            }
            Long long1 = (Long)ImageManager.d(Fi).get(a1.uri);
            if (long1 != null)
            {
                if (SystemClock.elapsedRealtime() - long1.longValue() < 0x36ee80L)
                {
                    Fk.a(ImageManager.b(Fi), ImageManager.c(Fi), true);
                    return;
                }
                ImageManager.d(Fi).remove(a1.uri);
            }
            Fk.a(ImageManager.b(Fi), ImageManager.c(Fi));
            ImageReceiver imagereceiver1 = (ImageReceiver)ImageManager.e(Fi).get(a1.uri);
            if (imagereceiver1 == null)
            {
                imagereceiver1 = Fi. new ImageReceiver(a1.uri);
                ImageManager.e(Fi).put(a1.uri, imagereceiver1);
            }
            imagereceiver1.b(Fk);
            if (!(Fk instanceof a.c))
            {
                ImageManager.a(Fi).put(Fk, imagereceiver1);
            }
            synchronized (ImageManager.fd())
            {
                if (!ImageManager.fe().contains(a1.uri))
                {
                    ImageManager.fe().add(a1.uri);
                    imagereceiver1.ff();
                }
            }
            return;
            exception;
            obj;
            JVM INSTR monitorexit ;
            throw exception;
        }

        public d(com.google.android.gms.common.images.a a1)
        {
            Fi = ImageManager.this;
            super();
            Fk = a1;
        }
    }

    private static final class e
        implements ComponentCallbacks2
    {

        private final b Fc;

        public void onConfigurationChanged(Configuration configuration)
        {
        }

        public void onLowMemory()
        {
            Fc.evictAll();
        }

        public void onTrimMemory(int i)
        {
            if (i >= 60)
            {
                Fc.evictAll();
            } else
            if (i >= 20)
            {
                Fc.trimToSize(Fc.size() / 2);
                return;
            }
        }

        public e(b b1)
        {
            Fc = b1;
        }
    }

    private final class f
        implements Runnable
    {

        final ImageManager Fi;
        private boolean Fl;
        private final CountDownLatch kK;
        private final Bitmap mBitmap;
        private final Uri mUri;

        private void a(ImageReceiver imagereceiver, boolean flag)
        {
            ArrayList arraylist = ImageReceiver.a(imagereceiver);
            int i = arraylist.size();
            int j = 0;
            while (j < i) 
            {
                com.google.android.gms.common.images.a a1 = (com.google.android.gms.common.images.a)arraylist.get(j);
                if (flag)
                {
                    a1.a(ImageManager.b(Fi), mBitmap, false);
                } else
                {
                    ImageManager.d(Fi).put(mUri, Long.valueOf(SystemClock.elapsedRealtime()));
                    a1.a(ImageManager.b(Fi), ImageManager.c(Fi), false);
                }
                if (!(a1 instanceof a.c))
                {
                    ImageManager.a(Fi).remove(a1);
                }
                j++;
            }
        }

        public void run()
        {
            gx.ay("OnBitmapLoadedRunnable must be executed in the main thread");
            boolean flag;
            if (mBitmap != null)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (ImageManager.h(Fi) != null)
            {
                if (Fl)
                {
                    ImageManager.h(Fi).evictAll();
                    System.gc();
                    Fl = false;
                    ImageManager.g(Fi).post(this);
                    return;
                }
                if (flag)
                {
                    ImageManager.h(Fi).put(new a.a(mUri), mBitmap);
                }
            }
            ImageReceiver imagereceiver = (ImageReceiver)ImageManager.e(Fi).remove(mUri);
            if (imagereceiver != null)
            {
                a(imagereceiver, flag);
            }
            kK.countDown();
            synchronized (ImageManager.fd())
            {
                ImageManager.fe().remove(mUri);
            }
            return;
            exception;
            obj;
            JVM INSTR monitorexit ;
            throw exception;
        }

        public f(Uri uri, Bitmap bitmap, boolean flag, CountDownLatch countdownlatch)
        {
            Fi = ImageManager.this;
            super();
            mUri = uri;
            mBitmap = bitmap;
            Fl = flag;
            kK = countdownlatch;
        }
    }


    private static final Object EX = new Object();
    private static HashSet EY = new HashSet();
    private static ImageManager EZ;
    private static ImageManager Fa;
    private final ExecutorService Fb = Executors.newFixedThreadPool(4);
    private final b Fc;
    private final gw Fd = new gw();
    private final Map Fe = new HashMap();
    private final Map Ff = new HashMap();
    private final Map Fg = new HashMap();
    private final Context mContext;
    private final Handler mHandler = new Handler(Looper.getMainLooper());

    private ImageManager(Context context, boolean flag)
    {
        mContext = context.getApplicationContext();
        if (flag)
        {
            Fc = new b(mContext);
            if (ip.gf())
            {
                fc();
            }
        } else
        {
            Fc = null;
        }
    }

    static Bitmap a(ImageManager imagemanager, a.a a1)
    {
        return imagemanager.a(a1);
    }

    private Bitmap a(a.a a1)
    {
        if (Fc == null)
        {
            return null;
        } else
        {
            return (Bitmap)Fc.get(a1);
        }
    }

    public static ImageManager a(Context context, boolean flag)
    {
        if (flag)
        {
            if (Fa == null)
            {
                Fa = new ImageManager(context, true);
            }
            return Fa;
        }
        if (EZ == null)
        {
            EZ = new ImageManager(context, false);
        }
        return EZ;
    }

    static Map a(ImageManager imagemanager)
    {
        return imagemanager.Fe;
    }

    static Context b(ImageManager imagemanager)
    {
        return imagemanager.mContext;
    }

    static gw c(ImageManager imagemanager)
    {
        return imagemanager.Fd;
    }

    public static ImageManager create(Context context)
    {
        return a(context, false);
    }

    static Map d(ImageManager imagemanager)
    {
        return imagemanager.Fg;
    }

    static Map e(ImageManager imagemanager)
    {
        return imagemanager.Ff;
    }

    static ExecutorService f(ImageManager imagemanager)
    {
        return imagemanager.Fb;
    }

    private void fc()
    {
        mContext.registerComponentCallbacks(new e(Fc));
    }

    static Object fd()
    {
        return EX;
    }

    static HashSet fe()
    {
        return EY;
    }

    static Handler g(ImageManager imagemanager)
    {
        return imagemanager.mHandler;
    }

    static b h(ImageManager imagemanager)
    {
        return imagemanager.Fc;
    }

    public void a(com.google.android.gms.common.images.a a1)
    {
        gx.ay("ImageManager.loadImage() must be called in the main thread");
        (new d(a1)).run();
    }

    public void loadImage(ImageView imageview, int i)
    {
        a(new a.b(imageview, i));
    }

    public void loadImage(ImageView imageview, Uri uri)
    {
        a(new a.b(imageview, uri));
    }

    public void loadImage(ImageView imageview, Uri uri, int i)
    {
        a.b b1 = new a.b(imageview, uri);
        b1.aj(i);
        a(b1);
    }

    public void loadImage(OnImageLoadedListener onimageloadedlistener, Uri uri)
    {
        a(new a.c(onimageloadedlistener, uri));
    }

    public void loadImage(OnImageLoadedListener onimageloadedlistener, Uri uri, int i)
    {
        a.c c1 = new a.c(onimageloadedlistener, uri);
        c1.aj(i);
        a(c1);
    }

}

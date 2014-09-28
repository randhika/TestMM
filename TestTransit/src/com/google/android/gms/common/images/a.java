// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.common.images;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.widget.ImageView;
import com.google.android.gms.internal.gt;
import com.google.android.gms.internal.gu;
import com.google.android.gms.internal.gv;
import com.google.android.gms.internal.gw;
import com.google.android.gms.internal.gx;
import com.google.android.gms.internal.hk;
import java.lang.ref.WeakReference;

public abstract class com.google.android.gms.common.images.a
{
    static final class a
    {

        public final Uri uri;

        public boolean equals(Object obj)
        {
            if (!(obj instanceof a))
            {
                return false;
            }
            if (this == obj)
            {
                return true;
            } else
            {
                return hk.equal(((a)obj).uri, uri);
            }
        }

        public int hashCode()
        {
            Object aobj[] = new Object[1];
            aobj[0] = uri;
            return hk.hashCode(aobj);
        }

        public a(Uri uri1)
        {
            uri = uri1;
        }
    }

    public static final class b extends com.google.android.gms.common.images.a
    {

        private WeakReference Fs;

        private void a(ImageView imageview, Drawable drawable, boolean flag, boolean flag1, boolean flag2)
        {
            boolean flag3;
            int j;
            if (!flag1 && !flag2)
            {
                flag3 = true;
            } else
            {
                flag3 = false;
            }
            if (!flag3 || !(imageview instanceof gv)) goto _L2; else goto _L1
_L1:
            j = ((gv)imageview).fi();
            if (Fo == 0 || j != Fo) goto _L2; else goto _L3
_L3:
            return;
_L2:
            boolean flag4 = b(flag, flag1);
            Object obj;
            if (flag4)
            {
                obj = a(imageview.getDrawable(), drawable);
            } else
            {
                obj = drawable;
            }
            imageview.setImageDrawable(((Drawable) (obj)));
            if (imageview instanceof gv)
            {
                gv gv1 = (gv)imageview;
                Uri uri;
                int i;
                if (flag2)
                {
                    uri = Fm.uri;
                } else
                {
                    uri = null;
                }
                gv1.g(uri);
                if (flag3)
                {
                    i = Fo;
                } else
                {
                    i = 0;
                }
                gv1.al(i);
            }
            if (flag4)
            {
                ((gt)obj).startTransition(250);
                return;
            }
            if (true) goto _L3; else goto _L4
_L4:
        }

        protected void a(Drawable drawable, boolean flag, boolean flag1, boolean flag2)
        {
            ImageView imageview = (ImageView)Fs.get();
            if (imageview != null)
            {
                a(imageview, drawable, flag, flag1, flag2);
            }
        }

        public boolean equals(Object obj)
        {
            if (!(obj instanceof b))
            {
                return false;
            }
            if (this == obj)
            {
                return true;
            }
            b b1 = (b)obj;
            ImageView imageview = (ImageView)Fs.get();
            ImageView imageview1 = (ImageView)b1.Fs.get();
            boolean flag;
            if (imageview1 != null && imageview != null && hk.equal(imageview1, imageview))
            {
                flag = true;
            } else
            {
                flag = false;
            }
            return flag;
        }

        public int hashCode()
        {
            return 0;
        }

        public b(ImageView imageview, int i)
        {
            super(null, i);
            gx.c(imageview);
            Fs = new WeakReference(imageview);
        }

        public b(ImageView imageview, Uri uri)
        {
            super(uri, 0);
            gx.c(imageview);
            Fs = new WeakReference(imageview);
        }
    }

    public static final class c extends com.google.android.gms.common.images.a
    {

        private WeakReference Ft;

        protected void a(Drawable drawable, boolean flag, boolean flag1, boolean flag2)
        {
            if (!flag1)
            {
                ImageManager.OnImageLoadedListener onimageloadedlistener = (ImageManager.OnImageLoadedListener)Ft.get();
                if (onimageloadedlistener != null)
                {
                    onimageloadedlistener.onImageLoaded(Fm.uri, drawable, flag2);
                }
            }
        }

        public boolean equals(Object obj)
        {
            if (!(obj instanceof c))
            {
                return false;
            }
            if (this == obj)
            {
                return true;
            }
            c c1 = (c)obj;
            ImageManager.OnImageLoadedListener onimageloadedlistener = (ImageManager.OnImageLoadedListener)Ft.get();
            ImageManager.OnImageLoadedListener onimageloadedlistener1 = (ImageManager.OnImageLoadedListener)c1.Ft.get();
            boolean flag;
            if (onimageloadedlistener1 != null && onimageloadedlistener != null && hk.equal(onimageloadedlistener1, onimageloadedlistener) && hk.equal(c1.Fm, Fm))
            {
                flag = true;
            } else
            {
                flag = false;
            }
            return flag;
        }

        public int hashCode()
        {
            Object aobj[] = new Object[1];
            aobj[0] = Fm;
            return hk.hashCode(aobj);
        }

        public c(ImageManager.OnImageLoadedListener onimageloadedlistener, Uri uri)
        {
            super(uri, 0);
            gx.c(onimageloadedlistener);
            Ft = new WeakReference(onimageloadedlistener);
        }
    }


    final a Fm;
    protected int Fn;
    protected int Fo;
    private boolean Fp;
    private boolean Fq;
    protected int Fr;

    public com.google.android.gms.common.images.a(Uri uri, int i)
    {
        Fn = 0;
        Fo = 0;
        Fp = true;
        Fq = false;
        Fm = new a(uri);
        Fo = i;
    }

    private Drawable a(Context context, gw gw1, int i)
    {
        Resources resources = context.getResources();
        if (Fr > 0)
        {
            com.google.android.gms.internal.gw.a a1 = new com.google.android.gms.internal.gw.a(i, Fr);
            Drawable drawable = (Drawable)gw1.get(a1);
            if (drawable == null)
            {
                drawable = resources.getDrawable(i);
                if ((1 & Fr) != 0)
                {
                    drawable = a(resources, drawable);
                }
                gw1.put(a1, drawable);
            }
            return drawable;
        } else
        {
            return resources.getDrawable(i);
        }
    }

    protected Drawable a(Resources resources, Drawable drawable)
    {
        return gu.a(resources, drawable);
    }

    protected gt a(Drawable drawable, Drawable drawable1)
    {
        if (drawable != null)
        {
            if (drawable instanceof gt)
            {
                drawable = ((gt)drawable).fg();
            }
        } else
        {
            drawable = null;
        }
        return new gt(drawable, drawable1);
    }

    void a(Context context, Bitmap bitmap, boolean flag)
    {
        gx.c(bitmap);
        if ((1 & Fr) != 0)
        {
            bitmap = gu.a(bitmap);
        }
        a(((Drawable) (new BitmapDrawable(context.getResources(), bitmap))), flag, false, true);
    }

    void a(Context context, gw gw1)
    {
        int i = Fn;
        Drawable drawable = null;
        if (i != 0)
        {
            drawable = a(context, gw1, Fn);
        }
        a(drawable, false, true, false);
    }

    void a(Context context, gw gw1, boolean flag)
    {
        int i = Fo;
        Drawable drawable = null;
        if (i != 0)
        {
            drawable = a(context, gw1, Fo);
        }
        a(drawable, flag, false, false);
    }

    protected abstract void a(Drawable drawable, boolean flag, boolean flag1, boolean flag2);

    public void aj(int i)
    {
        Fo = i;
    }

    protected boolean b(boolean flag, boolean flag1)
    {
        return Fp && !flag1 && (!flag || Fq);
    }
}

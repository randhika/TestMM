// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.common.images;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.graphics.Bitmap;
import com.google.android.gms.internal.hq;
import com.google.android.gms.internal.ip;

// Referenced classes of package com.google.android.gms.common.images:
//            ImageManager

private static final class D extends hq
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
            i = a(activitymanager);
        } else
        {
            i = activitymanager.getMemoryClass();
        }
        return (int)(0.33F * (float)(i * 0x100000));
    }

    protected int a(a a1, Bitmap bitmap)
    {
        return bitmap.getHeight() * bitmap.getRowBytes();
    }

    protected void a(boolean flag, a a1, Bitmap bitmap, Bitmap bitmap1)
    {
        super.entryRemoved(flag, a1, bitmap, bitmap1);
    }

    protected void entryRemoved(boolean flag, Object obj, Object obj1, Object obj2)
    {
        a(flag, (a)obj, (Bitmap)obj1, (Bitmap)obj2);
    }

    protected int sizeOf(Object obj, Object obj1)
    {
        return a((a)obj, (Bitmap)obj1);
    }

    public _cls9(Context context)
    {
        super(D(context));
    }
}

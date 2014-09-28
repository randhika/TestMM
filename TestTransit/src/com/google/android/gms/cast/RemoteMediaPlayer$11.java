// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.cast;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.internal.gh;
import com.google.android.gms.internal.go;
import java.io.IOException;

// Referenced classes of package com.google.android.gms.cast:
//            RemoteMediaPlayer

class init> extends init>
{

    final RemoteMediaPlayer Ba;
    final GoogleApiClient Bb;

    protected volatile void a(com.google.android.gms.common.api.ent_20_g_11_ ent_20_g_11_)
        throws RemoteException
    {
        a((gh)ent_20_g_11_);
    }

    protected void a(gh gh1)
    {
        Object obj = RemoteMediaPlayer.c(Ba);
        obj;
        JVM INSTR monitorenter ;
        RemoteMediaPlayer.d(Ba).(Bb);
        RemoteMediaPlayer.e(Ba).a(Bq);
        RemoteMediaPlayer.d(Ba).(null);
_L1:
        obj;
        JVM INSTR monitorexit ;
        return;
        IOException ioexception;
        ioexception;
        b(l(new Status(1)));
        RemoteMediaPlayer.d(Ba).(null);
          goto _L1
        Exception exception;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
        Exception exception1;
        exception1;
        RemoteMediaPlayer.d(Ba).(null);
        throw exception1;
    }

    (RemoteMediaPlayer remotemediaplayer, GoogleApiClient googleapiclient)
    {
        Ba = remotemediaplayer;
        Bb = googleapiclient;
        super();
    }
}

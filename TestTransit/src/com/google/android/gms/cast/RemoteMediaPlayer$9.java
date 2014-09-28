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
import org.json.JSONObject;

// Referenced classes of package com.google.android.gms.cast:
//            RemoteMediaPlayer

class <init> extends <init>
{

    final RemoteMediaPlayer Ba;
    final GoogleApiClient Bb;
    final JSONObject Bh;
    final double Bk;

    protected volatile void a(com.google.android.gms.common.api. )
        throws RemoteException
    {
        a((gh));
    }

    protected void a(gh gh1)
    {
        Object obj = RemoteMediaPlayer.c(Ba);
        obj;
        JVM INSTR monitorenter ;
        RemoteMediaPlayer.d(Ba).b(Bb);
        RemoteMediaPlayer.e(Ba).a(Bq, Bk, Bh);
        RemoteMediaPlayer.d(Ba).b(null);
_L1:
        obj;
        JVM INSTR monitorexit ;
        return;
        IllegalStateException illegalstateexception;
        illegalstateexception;
        b(l(new Status(1)));
        RemoteMediaPlayer.d(Ba).b(null);
          goto _L1
        Exception exception;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
        IllegalArgumentException illegalargumentexception;
        illegalargumentexception;
        b(l(new Status(1)));
        RemoteMediaPlayer.d(Ba).b(null);
          goto _L1
        IOException ioexception;
        ioexception;
        b(l(new Status(1)));
        RemoteMediaPlayer.d(Ba).b(null);
          goto _L1
        Exception exception1;
        exception1;
        RemoteMediaPlayer.d(Ba).b(null);
        throw exception1;
    }

    t(RemoteMediaPlayer remotemediaplayer, GoogleApiClient googleapiclient, double d, JSONObject jsonobject)
    {
        Ba = remotemediaplayer;
        Bb = googleapiclient;
        Bk = d;
        Bh = jsonobject;
        super();
    }
}

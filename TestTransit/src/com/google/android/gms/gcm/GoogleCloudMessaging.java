// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.gcm;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import java.io.IOException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class GoogleCloudMessaging
{

    public static final String ERROR_MAIN_THREAD = "MAIN_THREAD";
    public static final String ERROR_SERVICE_NOT_AVAILABLE = "SERVICE_NOT_AVAILABLE";
    public static final String MESSAGE_TYPE_DELETED = "deleted_messages";
    public static final String MESSAGE_TYPE_MESSAGE = "gcm";
    public static final String MESSAGE_TYPE_SEND_ERROR = "send_error";
    static GoogleCloudMessaging Us;
    private PendingIntent Ut;
    final BlockingQueue Uu = new LinkedBlockingQueue();
    private Handler Uv;
    private Messenger Uw;
    private Context kO;

    public GoogleCloudMessaging()
    {
        Uv = new Handler(Looper.getMainLooper()) {

            final GoogleCloudMessaging Ux;

            public void handleMessage(Message message)
            {
                Intent intent = (Intent)message.obj;
                Ux.Uu.add(intent);
            }

            
            {
                Ux = GoogleCloudMessaging.this;
                super(looper);
            }
        };
        Uw = new Messenger(Uv);
    }

    private void a(String s, String s1, long l, int i, Bundle bundle)
        throws IOException
    {
        if (Looper.getMainLooper() == Looper.myLooper())
        {
            throw new IOException("MAIN_THREAD");
        }
        if (s == null)
        {
            throw new IllegalArgumentException("Missing 'to'");
        } else
        {
            Intent intent = new Intent("com.google.android.gcm.intent.SEND");
            intent.putExtras(bundle);
            h(intent);
            intent.setPackage("com.google.android.gms");
            intent.putExtra("google.to", s);
            intent.putExtra("google.message_id", s1);
            intent.putExtra("google.ttl", Long.toString(l));
            intent.putExtra("google.delay", Integer.toString(i));
            kO.sendOrderedBroadcast(intent, "com.google.android.gtalkservice.permission.GTALK_SERVICE");
            return;
        }
    }

    private transient void c(String as[])
    {
        String s = d(as);
        Intent intent = new Intent("com.google.android.c2dm.intent.REGISTER");
        intent.setPackage("com.google.android.gms");
        intent.putExtra("google.messenger", Uw);
        h(intent);
        intent.putExtra("sender", s);
        kO.startService(intent);
    }

    public static GoogleCloudMessaging getInstance(Context context)
    {
        com/google/android/gms/gcm/GoogleCloudMessaging;
        JVM INSTR monitorenter ;
        GoogleCloudMessaging googlecloudmessaging;
        if (Us == null)
        {
            Us = new GoogleCloudMessaging();
            Us.kO = context;
        }
        googlecloudmessaging = Us;
        com/google/android/gms/gcm/GoogleCloudMessaging;
        JVM INSTR monitorexit ;
        return googlecloudmessaging;
        Exception exception;
        exception;
        throw exception;
    }

    private void iO()
    {
        Intent intent = new Intent("com.google.android.c2dm.intent.UNREGISTER");
        intent.setPackage("com.google.android.gms");
        Uu.clear();
        intent.putExtra("google.messenger", Uw);
        h(intent);
        kO.startService(intent);
    }

    public void close()
    {
        iP();
    }

    transient String d(String as[])
    {
        if (as == null || as.length == 0)
        {
            throw new IllegalArgumentException("No senderIds");
        }
        StringBuilder stringbuilder = new StringBuilder(as[0]);
        for (int i = 1; i < as.length; i++)
        {
            stringbuilder.append(',').append(as[i]);
        }

        return stringbuilder.toString();
    }

    public String getMessageType(Intent intent)
    {
        String s;
        if (!"com.google.android.c2dm.intent.RECEIVE".equals(intent.getAction()))
        {
            s = null;
        } else
        {
            s = intent.getStringExtra("message_type");
            if (s == null)
            {
                return "gcm";
            }
        }
        return s;
    }

    void h(Intent intent)
    {
        this;
        JVM INSTR monitorenter ;
        if (Ut == null)
        {
            Intent intent1 = new Intent();
            intent1.setPackage("com.google.example.invalidpackage");
            Ut = PendingIntent.getBroadcast(kO, 0, intent1, 0);
        }
        intent.putExtra("app", Ut);
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    void iP()
    {
        this;
        JVM INSTR monitorenter ;
        if (Ut != null)
        {
            Ut.cancel();
            Ut = null;
        }
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public transient String register(String as[])
        throws IOException
    {
        Intent intent;
        if (Looper.getMainLooper() == Looper.myLooper())
        {
            throw new IOException("MAIN_THREAD");
        }
        Uu.clear();
        c(as);
        try
        {
            intent = (Intent)Uu.poll(5000L, TimeUnit.MILLISECONDS);
        }
        catch (InterruptedException interruptedexception)
        {
            throw new IOException(interruptedexception.getMessage());
        }
        if (intent != null)
        {
            break MISSING_BLOCK_LABEL_79;
        }
        throw new IOException("SERVICE_NOT_AVAILABLE");
        String s = intent.getStringExtra("registration_id");
        if (s != null)
        {
            return s;
        }
        String s1;
        intent.getStringExtra("error");
        s1 = intent.getStringExtra("error");
        if (s1 == null)
        {
            break MISSING_BLOCK_LABEL_125;
        }
        throw new IOException(s1);
        throw new IOException("SERVICE_NOT_AVAILABLE");
    }

    public void send(String s, String s1, long l, Bundle bundle)
        throws IOException
    {
        a(s, s1, l, -1, bundle);
    }

    public void send(String s, String s1, Bundle bundle)
        throws IOException
    {
        send(s, s1, -1L, bundle);
    }

    public void unregister()
        throws IOException
    {
        Intent intent;
        if (Looper.getMainLooper() == Looper.myLooper())
        {
            throw new IOException("MAIN_THREAD");
        }
        iO();
        try
        {
            intent = (Intent)Uu.poll(5000L, TimeUnit.MILLISECONDS);
        }
        catch (InterruptedException interruptedexception)
        {
            throw new IOException(interruptedexception.getMessage());
        }
        if (intent != null)
        {
            break MISSING_BLOCK_LABEL_69;
        }
        throw new IOException("SERVICE_NOT_AVAILABLE");
        if (intent.getStringExtra("unregistered") != null)
        {
            return;
        }
        String s = intent.getStringExtra("error");
        if (s == null)
        {
            break MISSING_BLOCK_LABEL_99;
        }
        throw new IOException(s);
        throw new IOException("SERVICE_NOT_AVAILABLE");
    }
}

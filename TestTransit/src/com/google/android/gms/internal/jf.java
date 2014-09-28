// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import android.app.PendingIntent;
import android.content.ContentProviderClient;
import android.content.Context;
import android.location.Location;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

// Referenced classes of package com.google.android.gms.internal:
//            jj, je, hm

public class jf
{
    private static class a extends Handler
    {

        private final LocationListener VN;

        public void handleMessage(Message message)
        {
            Location location;
            switch (message.what)
            {
            default:
                Log.e("LocationClientHelper", "unknown message in LocationHandler.handleMessage");
                return;

            case 1: // '\001'
                location = new Location((Location)message.obj);
                break;
            }
            VN.onLocationChanged(location);
        }

        public a(LocationListener locationlistener)
        {
            VN = locationlistener;
        }

        public a(LocationListener locationlistener, Looper looper)
        {
            super(looper);
            VN = locationlistener;
        }
    }

    private static class b extends com.google.android.gms.location.a.a
    {

        private Handler VO;

        public void onLocationChanged(Location location)
        {
            if (VO == null)
            {
                Log.e("LocationClientHelper", "Received a location in client after calling removeLocationUpdates.");
                return;
            } else
            {
                Message message = Message.obtain();
                message.what = 1;
                message.obj = location;
                VO.sendMessage(message);
                return;
            }
        }

        public void release()
        {
            VO = null;
        }

        b(LocationListener locationlistener, Looper looper)
        {
            a a1;
            if (looper == null)
            {
                a1 = new a(locationlistener);
            } else
            {
                a1 = new a(locationlistener, looper);
            }
            VO = a1;
        }
    }


    private final jj VJ;
    private ContentProviderClient VK;
    private boolean VL;
    private HashMap VM;
    private final Context mContext;

    public jf(Context context, jj jj1)
    {
        VK = null;
        VL = false;
        VM = new HashMap();
        mContext = context;
        VJ = jj1;
    }

    public Location getLastLocation()
    {
        VJ.cn();
        Location location;
        try
        {
            location = ((je)VJ.ft()).bo(mContext.getPackageName());
        }
        catch (RemoteException remoteexception)
        {
            throw new IllegalStateException(remoteexception);
        }
        return location;
    }

    public void iY()
    {
        if (!VL)
        {
            break MISSING_BLOCK_LABEL_12;
        }
        setMockMode(false);
        return;
        RemoteException remoteexception;
        remoteexception;
        throw new IllegalStateException(remoteexception);
    }

    public void removeAllListeners()
    {
        HashMap hashmap = VM;
        hashmap;
        JVM INSTR monitorenter ;
        Iterator iterator = VM.values().iterator();
_L2:
        b b1;
        do
        {
            if (!iterator.hasNext())
            {
                break MISSING_BLOCK_LABEL_85;
            }
            b1 = (b)iterator.next();
        } while (b1 == null);
        ((je)VJ.ft()).a(b1);
        if (true) goto _L2; else goto _L1
_L1:
        Exception exception;
        exception;
        hashmap;
        JVM INSTR monitorexit ;
        try
        {
            throw exception;
        }
        catch (RemoteException remoteexception)
        {
            throw new IllegalStateException(remoteexception);
        }
        VM.clear();
        hashmap;
        JVM INSTR monitorexit ;
    }

    public void removeLocationUpdates(PendingIntent pendingintent)
        throws RemoteException
    {
        VJ.cn();
        ((je)VJ.ft()).a(pendingintent);
    }

    public void removeLocationUpdates(LocationListener locationlistener)
        throws RemoteException
    {
        VJ.cn();
        hm.b(locationlistener, "Invalid null listener");
        HashMap hashmap = VM;
        hashmap;
        JVM INSTR monitorenter ;
        b b1;
        b1 = (b)VM.remove(locationlistener);
        if (VK != null && VM.isEmpty())
        {
            VK.release();
            VK = null;
        }
        if (b1 == null)
        {
            break MISSING_BLOCK_LABEL_95;
        }
        b1.release();
        ((je)VJ.ft()).a(b1);
        hashmap;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        hashmap;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public void requestLocationUpdates(LocationRequest locationrequest, PendingIntent pendingintent)
        throws RemoteException
    {
        VJ.cn();
        ((je)VJ.ft()).a(locationrequest, pendingintent);
    }

    public void requestLocationUpdates(LocationRequest locationrequest, LocationListener locationlistener, Looper looper)
        throws RemoteException
    {
        VJ.cn();
        if (looper == null)
        {
            hm.b(Looper.myLooper(), "Can't create handler inside thread that has not called Looper.prepare()");
        }
        HashMap hashmap = VM;
        hashmap;
        JVM INSTR monitorenter ;
        b b1 = (b)VM.get(locationlistener);
        if (b1 != null)
        {
            break MISSING_BLOCK_LABEL_110;
        }
        b b2 = new b(locationlistener, looper);
_L1:
        VM.put(locationlistener, b2);
        ((je)VJ.ft()).a(locationrequest, b2, mContext.getPackageName());
        hashmap;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        hashmap;
        JVM INSTR monitorexit ;
        throw exception;
        b2 = b1;
          goto _L1
    }

    public void setMockLocation(Location location)
        throws RemoteException
    {
        VJ.cn();
        ((je)VJ.ft()).setMockLocation(location);
    }

    public void setMockMode(boolean flag)
        throws RemoteException
    {
        VJ.cn();
        ((je)VJ.ft()).setMockMode(flag);
        VL = flag;
    }
}

// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.maps.model;

import android.os.IBinder;
import android.os.RemoteException;
import com.google.android.gms.internal.hm;
import com.google.android.gms.maps.model.internal.d;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

// Referenced classes of package com.google.android.gms.maps.model:
//            RuntimeRemoteException, IndoorLevel

public final class IndoorBuilding
{

    private final d aaI;

    public IndoorBuilding(d d1)
    {
        aaI = (d)hm.f(d1);
    }

    public boolean equals(Object obj)
    {
        if (!(obj instanceof IndoorBuilding))
        {
            return false;
        }
        boolean flag;
        try
        {
            flag = aaI.b(((IndoorBuilding)obj).aaI);
        }
        catch (RemoteException remoteexception)
        {
            throw new RuntimeRemoteException(remoteexception);
        }
        return flag;
    }

    public int getActiveLevelIndex()
    {
        int i;
        try
        {
            i = aaI.getActiveLevelIndex();
        }
        catch (RemoteException remoteexception)
        {
            throw new RuntimeRemoteException(remoteexception);
        }
        return i;
    }

    public int getDefaultLevelIndex()
    {
        int i;
        try
        {
            i = aaI.getActiveLevelIndex();
        }
        catch (RemoteException remoteexception)
        {
            throw new RuntimeRemoteException(remoteexception);
        }
        return i;
    }

    public List getLevels()
    {
        ArrayList arraylist;
        try
        {
            List list = aaI.getLevels();
            arraylist = new ArrayList(list.size());
            for (Iterator iterator = list.iterator(); iterator.hasNext(); arraylist.add(new IndoorLevel(com.google.android.gms.maps.model.internal.e.a.bb((IBinder)iterator.next())))) { }
        }
        catch (RemoteException remoteexception)
        {
            throw new RuntimeRemoteException(remoteexception);
        }
        return arraylist;
    }

    public int hashCode()
    {
        int i;
        try
        {
            i = aaI.hashCodeRemote();
        }
        catch (RemoteException remoteexception)
        {
            throw new RuntimeRemoteException(remoteexception);
        }
        return i;
    }

    public boolean isUnderground()
    {
        boolean flag;
        try
        {
            flag = aaI.isUnderground();
        }
        catch (RemoteException remoteexception)
        {
            throw new RuntimeRemoteException(remoteexception);
        }
        return flag;
    }
}

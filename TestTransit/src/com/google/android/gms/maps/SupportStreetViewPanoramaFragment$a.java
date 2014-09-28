// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.maps;

import android.app.Activity;
import android.os.Bundle;
import android.os.RemoteException;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.gms.dynamic.LifecycleDelegate;
import com.google.android.gms.dynamic.e;
import com.google.android.gms.internal.hm;
import com.google.android.gms.maps.internal.IStreetViewPanoramaFragmentDelegate;
import com.google.android.gms.maps.internal.t;
import com.google.android.gms.maps.model.RuntimeRemoteException;

// Referenced classes of package com.google.android.gms.maps:
//            SupportStreetViewPanoramaFragment

static class FV
    implements LifecycleDelegate
{

    private final Fragment FV;
    private final IStreetViewPanoramaFragmentDelegate ZU;

    public IStreetViewPanoramaFragmentDelegate jC()
    {
        return ZU;
    }

    public void onCreate(Bundle bundle)
    {
        if (bundle != null)
        {
            break MISSING_BLOCK_LABEL_12;
        }
        bundle = new Bundle();
        Bundle bundle1 = FV.getArguments();
        if (bundle1 == null)
        {
            break MISSING_BLOCK_LABEL_45;
        }
        if (bundle1.containsKey("StreetViewPanoramaOptions"))
        {
            t.a(bundle, "StreetViewPanoramaOptions", bundle1.getParcelable("StreetViewPanoramaOptions"));
        }
        ZU.onCreate(bundle);
        return;
        RemoteException remoteexception;
        remoteexception;
        throw new RuntimeRemoteException(remoteexception);
    }

    public View onCreateView(LayoutInflater layoutinflater, ViewGroup viewgroup, Bundle bundle)
    {
        com.google.android.gms.dynamic.d d;
        try
        {
            d = ZU.onCreateView(e.h(layoutinflater), e.h(viewgroup), bundle);
        }
        catch (RemoteException remoteexception)
        {
            throw new RuntimeRemoteException(remoteexception);
        }
        return (View)e.e(d);
    }

    public void onDestroy()
    {
        try
        {
            ZU.onDestroy();
            return;
        }
        catch (RemoteException remoteexception)
        {
            throw new RuntimeRemoteException(remoteexception);
        }
    }

    public void onDestroyView()
    {
        try
        {
            ZU.onDestroyView();
            return;
        }
        catch (RemoteException remoteexception)
        {
            throw new RuntimeRemoteException(remoteexception);
        }
    }

    public void onInflate(Activity activity, Bundle bundle, Bundle bundle1)
    {
        try
        {
            ZU.onInflate(e.h(activity), null, bundle1);
            return;
        }
        catch (RemoteException remoteexception)
        {
            throw new RuntimeRemoteException(remoteexception);
        }
    }

    public void onLowMemory()
    {
        try
        {
            ZU.onLowMemory();
            return;
        }
        catch (RemoteException remoteexception)
        {
            throw new RuntimeRemoteException(remoteexception);
        }
    }

    public void onPause()
    {
        try
        {
            ZU.onPause();
            return;
        }
        catch (RemoteException remoteexception)
        {
            throw new RuntimeRemoteException(remoteexception);
        }
    }

    public void onResume()
    {
        try
        {
            ZU.onResume();
            return;
        }
        catch (RemoteException remoteexception)
        {
            throw new RuntimeRemoteException(remoteexception);
        }
    }

    public void onSaveInstanceState(Bundle bundle)
    {
        try
        {
            ZU.onSaveInstanceState(bundle);
            return;
        }
        catch (RemoteException remoteexception)
        {
            throw new RuntimeRemoteException(remoteexception);
        }
    }

    public void onStart()
    {
    }

    public void onStop()
    {
    }

    public Delegate(Fragment fragment, IStreetViewPanoramaFragmentDelegate istreetviewpanoramafragmentdelegate)
    {
        ZU = (IStreetViewPanoramaFragmentDelegate)hm.f(istreetviewpanoramafragmentdelegate);
        FV = (Fragment)hm.f(fragment);
    }
}

// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.games.internal;

import android.app.Activity;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.view.Display;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.Window;
import com.google.android.gms.internal.ip;
import java.lang.ref.WeakReference;

// Referenced classes of package com.google.android.gms.games.internal:
//            GamesClientImpl, GamesLog

public class PopupManager
{
    public static final class PopupLocationInfo
    {

        public IBinder Pa;
        public int Pb;
        public int bottom;
        public int gravity;
        public int left;
        public int right;
        public int top;

        public Bundle hO()
        {
            Bundle bundle = new Bundle();
            bundle.putInt("popupLocationInfo.gravity", gravity);
            bundle.putInt("popupLocationInfo.displayId", Pb);
            bundle.putInt("popupLocationInfo.left", left);
            bundle.putInt("popupLocationInfo.top", top);
            bundle.putInt("popupLocationInfo.right", right);
            bundle.putInt("popupLocationInfo.bottom", bottom);
            return bundle;
        }

        private PopupLocationInfo(int i, IBinder ibinder)
        {
            Pb = -1;
            left = 0;
            top = 0;
            right = 0;
            bottom = 0;
            gravity = i;
            Pa = ibinder;
        }

    }

    private static final class PopupManagerHCMR1 extends PopupManager
        implements android.view.View.OnAttachStateChangeListener, android.view.ViewTreeObserver.OnGlobalLayoutListener
    {

        private boolean Nv;
        private WeakReference Pc;

        private void h(View view)
        {
            int i = -1;
            if (ip.gh())
            {
                Display display = view.getDisplay();
                if (display != null)
                {
                    i = display.getDisplayId();
                }
            }
            IBinder ibinder = view.getWindowToken();
            int ai[] = new int[2];
            view.getLocationInWindow(ai);
            int j = view.getWidth();
            int k = view.getHeight();
            OZ.Pb = i;
            OZ.Pa = ibinder;
            OZ.left = ai[0];
            OZ.top = ai[1];
            OZ.right = j + ai[0];
            OZ.bottom = k + ai[1];
            if (Nv)
            {
                hL();
                Nv = false;
            }
        }

        protected void cl(int i)
        {
            OZ = new PopupLocationInfo(i, null);
        }

        public void g(View view)
        {
            OY.hw();
            if (Pc != null)
            {
                View view2 = (View)Pc.get();
                android.content.Context context1 = OY.getContext();
                if (view2 == null && (context1 instanceof Activity))
                {
                    view2 = ((Activity)context1).getWindow().getDecorView();
                }
                if (view2 != null)
                {
                    view2.removeOnAttachStateChangeListener(this);
                    ViewTreeObserver viewtreeobserver = view2.getViewTreeObserver();
                    android.content.Context context;
                    View view1;
                    if (ip.gg())
                    {
                        viewtreeobserver.removeOnGlobalLayoutListener(this);
                    } else
                    {
                        viewtreeobserver.removeGlobalOnLayoutListener(this);
                    }
                }
            }
            Pc = null;
            context = OY.getContext();
            if (view == null && (context instanceof Activity))
            {
                view1 = ((Activity)context).findViewById(0x1020002);
                if (view1 == null)
                {
                    view1 = ((Activity)context).getWindow().getDecorView();
                }
                GamesLog.j("PopupManager", "You have not specified a View to use as content view for popups. Falling back to the Activity content view which may not work properly in future versions of the API. Use setViewForPopups() to set your content view.");
                view = view1;
            }
            if (view != null)
            {
                h(view);
                Pc = new WeakReference(view);
                view.addOnAttachStateChangeListener(this);
                view.getViewTreeObserver().addOnGlobalLayoutListener(this);
                return;
            } else
            {
                GamesLog.k("PopupManager", "No content view usable to display popups. Popups will not be displayed in response to this client's calls. Use setViewForPopups() to set your content view.");
                return;
            }
        }

        public void hL()
        {
            if (OZ.Pa != null)
            {
                hL();
                return;
            }
            boolean flag;
            if (Pc != null)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            Nv = flag;
        }

        public void onGlobalLayout()
        {
            View view;
            if (Pc != null)
            {
                if ((view = (View)Pc.get()) != null)
                {
                    h(view);
                    return;
                }
            }
        }

        public void onViewAttachedToWindow(View view)
        {
            h(view);
        }

        public void onViewDetachedFromWindow(View view)
        {
            OY.hw();
            view.removeOnAttachStateChangeListener(this);
        }

        protected PopupManagerHCMR1(GamesClientImpl gamesclientimpl, int i)
        {
            super(gamesclientimpl, i, null);
            Nv = false;
        }
    }


    protected GamesClientImpl OY;
    protected PopupLocationInfo OZ;

    private PopupManager(GamesClientImpl gamesclientimpl, int i)
    {
        OY = gamesclientimpl;
        cl(i);
    }


    public static PopupManager a(GamesClientImpl gamesclientimpl, int i)
    {
        if (ip.gd())
        {
            return new PopupManagerHCMR1(gamesclientimpl, i);
        } else
        {
            return new PopupManager(gamesclientimpl, i);
        }
    }

    protected void cl(int i)
    {
        OZ = new PopupLocationInfo(i, new Binder());
    }

    public void g(View view)
    {
    }

    public void hL()
    {
        OY.a(OZ.Pa, OZ.hO());
    }

    public Bundle hM()
    {
        return OZ.hO();
    }

    public IBinder hN()
    {
        return OZ.Pa;
    }

    public void setGravity(int i)
    {
        OZ.gravity = i;
    }
}

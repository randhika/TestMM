// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.MutableContextWrapper;
import android.content.res.Resources;
import android.net.Uri;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.MotionEvent;
import android.view.WindowManager;
import android.webkit.DownloadListener;
import android.webkit.WebSettings;
import android.webkit.WebView;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

// Referenced classes of package com.google.android.gms.internal:
//            ev, eo, er, fa, 
//            fb, eq, ey, ez, 
//            al, eu, k, cf

public class ex extends WebView
    implements DownloadListener
{
    private static class a extends MutableContextWrapper
    {

        private Context lz;
        private Activity sG;

        public Context cf()
        {
            return sG;
        }

        public void setBaseContext(Context context)
        {
            lz = context.getApplicationContext();
            Activity activity;
            if (context instanceof Activity)
            {
                activity = (Activity)context;
            } else
            {
                activity = null;
            }
            sG = activity;
            super.setBaseContext(lz);
        }

        public void startActivity(Intent intent)
        {
            if (sG != null)
            {
                sG.startActivity(intent);
                return;
            } else
            {
                intent.setFlags(0x10000000);
                lz.startActivity(intent);
                return;
            }
        }

        public a(Context context)
        {
            super(context);
            setBaseContext(context);
        }
    }


    private final WindowManager lC = (WindowManager)getContext().getSystemService("window");
    private final Object ls = new Object();
    private al nF;
    private final ev nG;
    private final k pA;
    private final ey sA;
    private final a sB;
    private cf sC;
    private boolean sD;
    private boolean sE;
    private boolean sF;

    private ex(a a1, al al1, boolean flag, boolean flag1, k k1, ev ev1)
    {
        super(a1);
        sB = a1;
        nF = al1;
        sD = flag;
        pA = k1;
        nG = ev1;
        setBackgroundColor(0);
        WebSettings websettings = getSettings();
        websettings.setJavaScriptEnabled(true);
        websettings.setSavePassword(false);
        websettings.setSupportMultipleWindows(true);
        websettings.setJavaScriptCanOpenWindowsAutomatically(true);
        eo.a(a1, ev1.sw, websettings);
        if (android.os.Build.VERSION.SDK_INT >= 17)
        {
            er.a(getContext(), websettings);
        } else
        if (android.os.Build.VERSION.SDK_INT >= 11)
        {
            eq.a(getContext(), websettings);
        }
        setDownloadListener(this);
        if (android.os.Build.VERSION.SDK_INT >= 11)
        {
            sA = new fa(this, flag1);
        } else
        {
            sA = new ey(this, flag1);
        }
        setWebViewClient(sA);
        if (android.os.Build.VERSION.SDK_INT >= 14)
        {
            setWebChromeClient(new fb(this));
        } else
        if (android.os.Build.VERSION.SDK_INT >= 11)
        {
            setWebChromeClient(new ez(this));
        }
        cg();
    }

    public static ex a(Context context, al al1, boolean flag, boolean flag1, k k1, ev ev1)
    {
        return new ex(new a(context), al1, flag, flag1, k1, ev1);
    }

    private void cg()
    {
        Object obj = ls;
        obj;
        JVM INSTR monitorenter ;
        if (!sD && !nF.mf) goto _L2; else goto _L1
_L1:
        if (android.os.Build.VERSION.SDK_INT >= 14) goto _L4; else goto _L3
_L3:
        eu.z("Disabling hardware acceleration on an overlay.");
        ch();
_L5:
        return;
_L4:
        eu.z("Enabling hardware acceleration on an overlay.");
        ci();
          goto _L5
        Exception exception;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
_L2:
label0:
        {
            if (android.os.Build.VERSION.SDK_INT >= 18)
            {
                break label0;
            }
            eu.z("Disabling hardware acceleration on an AdView.");
            ch();
        }
          goto _L5
        eu.z("Enabling hardware acceleration on an AdView.");
        ci();
          goto _L5
    }

    private void ch()
    {
        synchronized (ls)
        {
            if (!sE && android.os.Build.VERSION.SDK_INT >= 11)
            {
                eq.d(this);
            }
            sE = true;
        }
        return;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }

    private void ci()
    {
        synchronized (ls)
        {
            if (sE && android.os.Build.VERSION.SDK_INT >= 11)
            {
                eq.e(this);
            }
            sE = false;
        }
        return;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }

    protected void E(String s)
    {
        Object obj = ls;
        obj;
        JVM INSTR monitorenter ;
        if (isDestroyed())
        {
            break MISSING_BLOCK_LABEL_22;
        }
        loadUrl(s);
_L2:
        return;
        eu.D("The webview is destroyed. Ignoring action.");
        if (true) goto _L2; else goto _L1
_L1:
        Exception exception;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public al V()
    {
        al al1;
        synchronized (ls)
        {
            al1 = nF;
        }
        return al1;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public void a(Context context, al al1)
    {
        synchronized (ls)
        {
            sB.setBaseContext(context);
            sC = null;
            nF = al1;
            sD = false;
            eo.b(this);
            loadUrl("about:blank");
            sA.reset();
        }
        return;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public void a(al al1)
    {
        synchronized (ls)
        {
            nF = al1;
            requestLayout();
        }
        return;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public void a(cf cf1)
    {
        synchronized (ls)
        {
            sC = cf1;
        }
        return;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public void a(String s, Map map)
    {
        JSONObject jsonobject;
        try
        {
            jsonobject = eo.o(map);
        }
        catch (JSONException jsonexception)
        {
            eu.D("Could not convert parameters to JSON.");
            return;
        }
        b(s, jsonobject);
    }

    public void a(String s, JSONObject jsonobject)
    {
        if (jsonobject == null)
        {
            jsonobject = new JSONObject();
        }
        String s1 = jsonobject.toString();
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append((new StringBuilder()).append("javascript:").append(s).append("(").toString());
        stringbuilder.append(s1);
        stringbuilder.append(");");
        E(stringbuilder.toString());
    }

    public void b(String s, JSONObject jsonobject)
    {
        if (jsonobject == null)
        {
            jsonobject = new JSONObject();
        }
        String s1 = jsonobject.toString();
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("javascript:AFMA_ReceiveMessage('");
        stringbuilder.append(s);
        stringbuilder.append("'");
        stringbuilder.append(",");
        stringbuilder.append(s1);
        stringbuilder.append(");");
        eu.C((new StringBuilder()).append("Dispatching AFMA event: ").append(stringbuilder).toString());
        E(stringbuilder.toString());
    }

    public void bX()
    {
        if (!cb().cj())
        {
            return;
        }
        DisplayMetrics displaymetrics = new DisplayMetrics();
        Display display = lC.getDefaultDisplay();
        display.getMetrics(displaymetrics);
        int i = eo.p(getContext());
        float f = 160F / (float)displaymetrics.densityDpi;
        int j = (int)(f * (float)displaymetrics.widthPixels);
        int l = (int)(f * (float)(displaymetrics.heightPixels - i));
        try
        {
            b("onScreenInfoChanged", (new JSONObject()).put("width", j).put("height", l).put("density", displaymetrics.density).put("rotation", display.getRotation()));
            return;
        }
        catch (JSONException jsonexception)
        {
            eu.b("Error occured while obtaining screen information.", jsonexception);
        }
    }

    public void bY()
    {
        HashMap hashmap = new HashMap(1);
        hashmap.put("version", nG.sw);
        a("onhide", hashmap);
    }

    public void bZ()
    {
        HashMap hashmap = new HashMap(1);
        hashmap.put("version", nG.sw);
        a("onshow", hashmap);
    }

    public cf ca()
    {
        cf cf1;
        synchronized (ls)
        {
            cf1 = sC;
        }
        return cf1;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public ey cb()
    {
        return sA;
    }

    public k cc()
    {
        return pA;
    }

    public ev cd()
    {
        return nG;
    }

    public boolean ce()
    {
        boolean flag;
        synchronized (ls)
        {
            flag = sD;
        }
        return flag;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public Context cf()
    {
        return sB.cf();
    }

    public void destroy()
    {
        synchronized (ls)
        {
            super.destroy();
            sF = true;
        }
        return;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public boolean isDestroyed()
    {
        boolean flag;
        synchronized (ls)
        {
            flag = sF;
        }
        return flag;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public void onDownloadStart(String s, String s1, String s2, String s3, long l)
    {
        try
        {
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.setDataAndType(Uri.parse(s), s3);
            getContext().startActivity(intent);
            return;
        }
        catch (ActivityNotFoundException activitynotfoundexception)
        {
            eu.z((new StringBuilder()).append("Couldn't find an Activity to view url/mimetype: ").append(s).append(" / ").append(s3).toString());
        }
    }

    protected void onMeasure(int i, int j)
    {
        int l;
label0:
        {
            l = 0x7fffffff;
            synchronized (ls)
            {
                if (!isInEditMode() && !sD)
                {
                    break label0;
                }
                super.onMeasure(i, j);
            }
            return;
        }
        int i1;
        int j1;
        int k1;
        int l1;
        i1 = android.view.View.MeasureSpec.getMode(i);
        j1 = android.view.View.MeasureSpec.getSize(i);
        k1 = android.view.View.MeasureSpec.getMode(j);
        l1 = android.view.View.MeasureSpec.getSize(j);
        break MISSING_BLOCK_LABEL_61;
_L3:
        if (nF.widthPixels <= i2 && nF.heightPixels <= l)
        {
            break MISSING_BLOCK_LABEL_243;
        }
        float f = sB.getResources().getDisplayMetrics().density;
        eu.D((new StringBuilder()).append("Not enough space to show ad. Needs ").append((int)((float)nF.widthPixels / f)).append("x").append((int)((float)nF.heightPixels / f)).append(" dp, but only has ").append((int)((float)j1 / f)).append("x").append((int)((float)l1 / f)).append(" dp.").toString());
        if (getVisibility() != 8)
        {
            setVisibility(4);
        }
        setMeasuredDimension(0, 0);
_L1:
        obj;
        JVM INSTR monitorexit ;
        return;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
        if (getVisibility() != 8)
        {
            setVisibility(0);
        }
        setMeasuredDimension(nF.widthPixels, nF.heightPixels);
          goto _L1
        int i2;
        if (i1 != 0x80000000 && i1 != 0x40000000)
        {
            i2 = l;
        } else
        {
            i2 = j1;
        }
        if (k1 == 0x80000000 || k1 == 0x40000000)
        {
            l = l1;
        }
        if (true) goto _L3; else goto _L2
_L2:
    }

    public boolean onTouchEvent(MotionEvent motionevent)
    {
        if (pA != null)
        {
            pA.a(motionevent);
        }
        return super.onTouchEvent(motionevent);
    }

    public void q(boolean flag)
    {
        synchronized (ls)
        {
            sD = flag;
            cg();
        }
        return;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public void setContext(Context context)
    {
        sB.setBaseContext(context);
    }
}

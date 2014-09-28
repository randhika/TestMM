// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.webkit.WebView;

// Referenced classes of package com.google.android.gms.internal:
//            ex, du, eo, fc, 
//            eu

public class do
    implements Runnable
{
    protected final class a extends AsyncTask
    {

        private final WebView pQ;
        private Bitmap pR;
        final do pS;

        protected transient Boolean a(Void avoid[])
        {
            this;
            JVM INSTR monitorenter ;
            int i;
            int j;
            i = pR.getWidth();
            j = pR.getHeight();
            if (i != 0 && j != 0) goto _L2; else goto _L1
_L1:
            Boolean boolean1 = Boolean.valueOf(false);
            Boolean boolean2 = boolean1;
_L7:
            this;
            JVM INSTR monitorexit ;
            return boolean2;
_L2:
            int k;
            int l;
            k = 0;
            l = 0;
_L9:
            if (k >= i) goto _L4; else goto _L3
_L3:
            int i1 = 0;
_L8:
            if (i1 >= j) goto _L6; else goto _L5
_L5:
            if (pR.getPixel(k, i1) != 0)
            {
                l++;
            }
            continue; /* Loop/switch isn't completed */
_L4:
            boolean flag;
            Boolean boolean3;
            if ((double)l / ((double)(i * j) / 100D) > 0.10000000000000001D)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            boolean3 = Boolean.valueOf(flag);
            boolean2 = boolean3;
              goto _L7
            Exception exception;
            exception;
            throw exception;
              goto _L8
_L6:
            k += 10;
              goto _L9
        }

        protected void a(Boolean boolean1)
        {
            do.c(pS);
            if (boolean1.booleanValue() || pS.bq() || do.d(pS) <= 0L)
            {
                pS.pP = boolean1.booleanValue();
                do.e(pS).a(pS.lN);
            } else
            if (do.d(pS) > 0L)
            {
                if (eu.p(2))
                {
                    eu.z("Ad not detected, scheduling another run.");
                }
                do.g(pS).postDelayed(pS, do.f(pS));
                return;
            }
        }

        protected Object doInBackground(Object aobj[])
        {
            return a((Void[])aobj);
        }

        protected void onPostExecute(Object obj)
        {
            a((Boolean)obj);
        }

        protected void onPreExecute()
        {
            this;
            JVM INSTR monitorenter ;
            pR = Bitmap.createBitmap(do.a(pS), do.b(pS), android.graphics.Bitmap.Config.ARGB_8888);
            pQ.setVisibility(0);
            pQ.measure(android.view.View.MeasureSpec.makeMeasureSpec(do.a(pS), 0), android.view.View.MeasureSpec.makeMeasureSpec(do.b(pS), 0));
            pQ.layout(0, 0, do.a(pS), do.b(pS));
            Canvas canvas = new Canvas(pR);
            pQ.draw(canvas);
            pQ.invalidate();
            this;
            JVM INSTR monitorexit ;
            return;
            Exception exception;
            exception;
            throw exception;
        }

        public a(WebView webview)
        {
            pS = do.this;
            super();
            pQ = webview;
        }
    }


    private final int ku;
    private final int kv;
    protected final ex lN;
    private final Handler pK;
    private final long pL;
    private long pM;
    private ey.a pN;
    protected boolean pO;
    protected boolean pP;

    public do(ey.a a1, ex ex1, int i, int j)
    {
        this(a1, ex1, i, j, 200L, 50L);
    }

    public do(ey.a a1, ex ex1, int i, int j, long l, long l1)
    {
        pL = l;
        pM = l1;
        pK = new Handler(Looper.getMainLooper());
        lN = ex1;
        pN = a1;
        pO = false;
        pP = false;
        kv = j;
        ku = i;
    }

    static int a(do do1)
    {
        return do1.ku;
    }

    static int b(do do1)
    {
        return do1.kv;
    }

    static long c(do do1)
    {
        long l = do1.pM - 1L;
        do1.pM = l;
        return l;
    }

    static long d(do do1)
    {
        return do1.pM;
    }

    static ey.a e(do do1)
    {
        return do1.pN;
    }

    static long f(do do1)
    {
        return do1.pL;
    }

    static Handler g(do do1)
    {
        return do1.pK;
    }

    public void a(du du1, fc fc1)
    {
        lN.setWebViewClient(fc1);
        ex ex1 = lN;
        String s;
        if (TextUtils.isEmpty(du1.oA))
        {
            s = null;
        } else
        {
            s = eo.v(du1.oA);
        }
        ex1.loadDataWithBaseURL(s, du1.qe, "text/html", "UTF-8", null);
    }

    public void b(du du1)
    {
        a(du1, new fc(this, lN, du1.qn));
    }

    public void bo()
    {
        pK.postDelayed(this, pL);
    }

    public void bp()
    {
        this;
        JVM INSTR monitorenter ;
        pO = true;
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public boolean bq()
    {
        this;
        JVM INSTR monitorenter ;
        boolean flag = pO;
        this;
        JVM INSTR monitorexit ;
        return flag;
        Exception exception;
        exception;
        throw exception;
    }

    public boolean br()
    {
        return pP;
    }

    public void run()
    {
        if (lN == null || bq())
        {
            pN.a(lN);
            return;
        } else
        {
            (new a(lN)).execute(new Void[0]);
            return;
        }
    }
}

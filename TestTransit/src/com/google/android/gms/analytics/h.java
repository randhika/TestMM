// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.analytics;

import android.content.Context;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

// Referenced classes of package com.google.android.gms.analytics:
//            m, aa

class h
    implements m
{

    private static h tH;
    private static final Object tq = new Object();
    private final Context mContext;
    private String tI;
    private boolean tJ;
    private final Object tK = new Object();

    protected h(Context context)
    {
        tJ = false;
        mContext = context;
        cz();
    }

    private boolean K(String s)
    {
        try
        {
            aa.C("Storing clientId.");
            FileOutputStream fileoutputstream = mContext.openFileOutput("gaClientId", 0);
            fileoutputstream.write(s.getBytes());
            fileoutputstream.close();
        }
        catch (FileNotFoundException filenotfoundexception)
        {
            aa.A("Error creating clientId file.");
            return false;
        }
        catch (IOException ioexception)
        {
            aa.A("Error writing to clientId file.");
            return false;
        }
        return true;
    }

    static Object a(h h1)
    {
        return h1.tK;
    }

    static String a(h h1, String s)
    {
        h1.tI = s;
        return s;
    }

    static boolean a(h h1, boolean flag)
    {
        h1.tJ = flag;
        return flag;
    }

    public static h cv()
    {
        h h1;
        synchronized (tq)
        {
            h1 = tH;
        }
        return h1;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }

    private String cx()
    {
        if (tJ) goto _L2; else goto _L1
_L1:
        Object obj = tK;
        obj;
        JVM INSTR monitorenter ;
        if (tJ) goto _L2; else goto _L3
_L3:
        aa.C("Waiting for clientId to load");
_L7:
        tK.wait();
_L5:
        if (!tJ)
        {
            break; /* Loop/switch isn't completed */
        }
_L2:
        aa.C("Loaded clientId");
        return tI;
        InterruptedException interruptedexception;
        interruptedexception;
        aa.A((new StringBuilder()).append("Exception while waiting for clientId: ").append(interruptedexception).toString());
        if (true) goto _L5; else goto _L4
_L4:
        if (true) goto _L7; else goto _L6
_L6:
        Exception exception;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }

    private void cz()
    {
        (new Thread("client_id_fetcher") {

            final h tL;

            public void run()
            {
                synchronized (h.a(tL))
                {
                    h.a(tL, tL.cA());
                    h.a(tL, true);
                    h.a(tL).notifyAll();
                }
                return;
                exception;
                obj;
                JVM INSTR monitorexit ;
                throw exception;
            }

            
            {
                tL = h.this;
                super(s);
            }
        }).start();
    }

    public static void u(Context context)
    {
        synchronized (tq)
        {
            if (tH == null)
            {
                tH = new h(context);
            }
        }
        return;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public boolean J(String s)
    {
        return "&cid".equals(s);
    }

    String cA()
    {
        String s = null;
        FileInputStream fileinputstream;
        byte abyte0[];
        int i;
        fileinputstream = mContext.openFileInput("gaClientId");
        abyte0 = new byte[128];
        i = fileinputstream.read(abyte0, 0, 128);
        if (fileinputstream.available() <= 0) goto _L2; else goto _L1
_L1:
        aa.A("clientId file seems corrupted, deleting it.");
        fileinputstream.close();
        mContext.deleteFile("gaClientId");
_L6:
        if (s == null)
        {
            s = cy();
        }
        return s;
_L2:
        if (i > 0)
        {
            break MISSING_BLOCK_LABEL_102;
        }
        aa.A("clientId file seems empty, deleting it.");
        fileinputstream.close();
        mContext.deleteFile("gaClientId");
        s = null;
        continue; /* Loop/switch isn't completed */
        String s1 = new String(abyte0, 0, i);
        fileinputstream.close();
        aa.C("Loaded client id from disk.");
        s = s1;
        continue; /* Loop/switch isn't completed */
        IOException ioexception;
        ioexception;
_L4:
        aa.A("Error reading clientId file, deleting it.");
        mContext.deleteFile("gaClientId");
        continue; /* Loop/switch isn't completed */
        IOException ioexception1;
        ioexception1;
        s = s1;
        if (true) goto _L4; else goto _L3
_L3:
        FileNotFoundException filenotfoundexception1;
        filenotfoundexception1;
        s = s1;
        continue; /* Loop/switch isn't completed */
        FileNotFoundException filenotfoundexception;
        filenotfoundexception;
        s = null;
        if (true) goto _L6; else goto _L5
_L5:
    }

    String cw()
    {
        String s;
        synchronized (tK)
        {
            tI = cy();
            s = tI;
        }
        return s;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }

    protected String cy()
    {
        String s = UUID.randomUUID().toString().toLowerCase();
        if (!K(s))
        {
            s = "0";
        }
        return s;
        Exception exception;
        exception;
        return null;
    }

    public String getValue(String s)
    {
        if ("&cid".equals(s))
        {
            return cx();
        } else
        {
            return null;
        }
    }

}

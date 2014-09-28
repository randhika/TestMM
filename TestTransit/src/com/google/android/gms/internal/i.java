// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import dalvik.system.DexClassLoader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.util.ArrayList;

// Referenced classes of package com.google.android.gms.internal:
//            h, m, o, q, 
//            n

public abstract class i extends h
{
    static class a extends Exception
    {

        public a()
        {
        }

        public a(Throwable throwable)
        {
            super(throwable);
        }
    }


    private static Method jS;
    private static Method jT;
    private static Method jU;
    private static Method jV;
    private static Method jW;
    private static Method jX;
    private static Method jY;
    private static Method jZ;
    private static String ka;
    private static o kb;
    static boolean kc = false;
    private static long startTime = 0L;

    protected i(Context context, m m1, n n)
    {
        super(context, m1, n);
    }

    static String a(Context context, m m1)
        throws a
    {
        if (jV == null)
        {
            throw new a();
        }
        ByteBuffer bytebuffer;
        String s;
        try
        {
            bytebuffer = (ByteBuffer)jV.invoke(null, new Object[] {
                context
            });
        }
        catch (IllegalAccessException illegalaccessexception)
        {
            throw new a(illegalaccessexception);
        }
        catch (InvocationTargetException invocationtargetexception)
        {
            throw new a(invocationtargetexception);
        }
        if (bytebuffer != null)
        {
            break MISSING_BLOCK_LABEL_57;
        }
        throw new a();
        s = m1.a(bytebuffer.array(), true);
        return s;
    }

    static ArrayList a(MotionEvent motionevent, DisplayMetrics displaymetrics)
        throws a
    {
        if (jW == null || motionevent == null)
        {
            throw new a();
        }
        ArrayList arraylist;
        try
        {
            arraylist = (ArrayList)jW.invoke(null, new Object[] {
                motionevent, displaymetrics
            });
        }
        catch (IllegalAccessException illegalaccessexception)
        {
            throw new a(illegalaccessexception);
        }
        catch (InvocationTargetException invocationtargetexception)
        {
            throw new a(invocationtargetexception);
        }
        return arraylist;
    }

    protected static void a(String s, Context context, m m1)
    {
        com/google/android/gms/internal/i;
        JVM INSTR monitorenter ;
        boolean flag = kc;
        Exception exception;
        if (!flag)
        {
            try
            {
                kb = new o(m1, null);
                ka = s;
                h(context);
                startTime = w().longValue();
                kc = true;
            }
            catch (a a1) { }
            catch (UnsupportedOperationException unsupportedoperationexception) { }
            finally
            {
                com/google/android/gms/internal/i;
            }
        }
        com/google/android/gms/internal/i;
        JVM INSTR monitorexit ;
        return;
        throw exception;
    }

    static String b(Context context, m m1)
        throws a
    {
        if (jY == null)
        {
            throw new a();
        }
        ByteBuffer bytebuffer;
        String s;
        try
        {
            bytebuffer = (ByteBuffer)jY.invoke(null, new Object[] {
                context
            });
        }
        catch (IllegalAccessException illegalaccessexception)
        {
            throw new a(illegalaccessexception);
        }
        catch (InvocationTargetException invocationtargetexception)
        {
            throw new a(invocationtargetexception);
        }
        if (bytebuffer != null)
        {
            break MISSING_BLOCK_LABEL_57;
        }
        throw new a();
        s = m1.a(bytebuffer.array(), true);
        return s;
    }

    private static String b(byte abyte0[], String s)
        throws a
    {
        String s1;
        try
        {
            s1 = new String(kb.c(abyte0, s), "UTF-8");
        }
        catch (o.a a1)
        {
            throw new a(a1);
        }
        catch (UnsupportedEncodingException unsupportedencodingexception)
        {
            throw new a(unsupportedencodingexception);
        }
        return s1;
    }

    static String f(Context context)
        throws a
    {
        String s;
        if (jX == null)
        {
            throw new a();
        }
        try
        {
            s = (String)jX.invoke(null, new Object[] {
                context
            });
        }
        catch (IllegalAccessException illegalaccessexception)
        {
            throw new a(illegalaccessexception);
        }
        catch (InvocationTargetException invocationtargetexception)
        {
            throw new a(invocationtargetexception);
        }
        if (s != null)
        {
            break MISSING_BLOCK_LABEL_65;
        }
        throw new a();
        return s;
    }

    static ArrayList g(Context context)
        throws a
    {
        ArrayList arraylist;
        if (jZ == null)
        {
            throw new a();
        }
        try
        {
            arraylist = (ArrayList)jZ.invoke(null, new Object[] {
                context
            });
        }
        catch (IllegalAccessException illegalaccessexception)
        {
            throw new a(illegalaccessexception);
        }
        catch (InvocationTargetException invocationtargetexception)
        {
            throw new a(invocationtargetexception);
        }
        if (arraylist == null)
        {
            break MISSING_BLOCK_LABEL_45;
        }
        if (arraylist.size() == 2)
        {
            break MISSING_BLOCK_LABEL_73;
        }
        throw new a();
        return arraylist;
    }

    private static void h(Context context)
        throws a
    {
        byte abyte0[];
        byte abyte1[];
        File file;
        File file1;
        File file2;
        FileOutputStream fileoutputstream;
        DexClassLoader dexclassloader;
        Exception exception;
        String s;
        Class class1;
        Class class2;
        Class class3;
        Class class4;
        Class class5;
        Class class6;
        Class class7;
        Class class8;
        String s1;
        try
        {
            abyte0 = kb.b(q.getKey());
            abyte1 = kb.c(abyte0, q.B());
            file = context.getCacheDir();
        }
        catch (FileNotFoundException filenotfoundexception)
        {
            throw new a(filenotfoundexception);
        }
        catch (IOException ioexception)
        {
            throw new a(ioexception);
        }
        catch (ClassNotFoundException classnotfoundexception)
        {
            throw new a(classnotfoundexception);
        }
        catch (o.a a1)
        {
            throw new a(a1);
        }
        catch (NoSuchMethodException nosuchmethodexception)
        {
            throw new a(nosuchmethodexception);
        }
        catch (NullPointerException nullpointerexception)
        {
            throw new a(nullpointerexception);
        }
        if (file != null)
        {
            break MISSING_BLOCK_LABEL_69;
        }
        file = context.getDir("dex", 0);
        if (file != null)
        {
            break MISSING_BLOCK_LABEL_69;
        }
        throw new a();
        file1 = file;
        file2 = File.createTempFile("ads", ".jar", file1);
        fileoutputstream = new FileOutputStream(file2);
        fileoutputstream.write(abyte1, 0, abyte1.length);
        fileoutputstream.close();
        dexclassloader = new DexClassLoader(file2.getAbsolutePath(), file1.getAbsolutePath(), null, context.getClassLoader());
        class1 = dexclassloader.loadClass(b(abyte0, q.C()));
        class2 = dexclassloader.loadClass(b(abyte0, q.O()));
        class3 = dexclassloader.loadClass(b(abyte0, q.I()));
        class4 = dexclassloader.loadClass(b(abyte0, q.G()));
        class5 = dexclassloader.loadClass(b(abyte0, q.Q()));
        class6 = dexclassloader.loadClass(b(abyte0, q.E()));
        class7 = dexclassloader.loadClass(b(abyte0, q.M()));
        class8 = dexclassloader.loadClass(b(abyte0, q.K()));
        jS = class1.getMethod(b(abyte0, q.D()), new Class[0]);
        jT = class2.getMethod(b(abyte0, q.P()), new Class[0]);
        jU = class3.getMethod(b(abyte0, q.J()), new Class[0]);
        jV = class4.getMethod(b(abyte0, q.H()), new Class[] {
            android/content/Context
        });
        jW = class5.getMethod(b(abyte0, q.R()), new Class[] {
            android/view/MotionEvent, android/util/DisplayMetrics
        });
        jX = class6.getMethod(b(abyte0, q.F()), new Class[] {
            android/content/Context
        });
        jY = class7.getMethod(b(abyte0, q.N()), new Class[] {
            android/content/Context
        });
        jZ = class8.getMethod(b(abyte0, q.L()), new Class[] {
            android/content/Context
        });
        s1 = file2.getName();
        file2.delete();
        (new File(file1, s1.replace(".jar", ".dex"))).delete();
        return;
        exception;
        s = file2.getName();
        file2.delete();
        (new File(file1, s.replace(".jar", ".dex"))).delete();
        throw exception;
    }

    static String v()
        throws a
    {
        if (ka == null)
        {
            throw new a();
        } else
        {
            return ka;
        }
    }

    static Long w()
        throws a
    {
        if (jS == null)
        {
            throw new a();
        }
        Long long1;
        try
        {
            long1 = (Long)jS.invoke(null, new Object[0]);
        }
        catch (IllegalAccessException illegalaccessexception)
        {
            throw new a(illegalaccessexception);
        }
        catch (InvocationTargetException invocationtargetexception)
        {
            throw new a(invocationtargetexception);
        }
        return long1;
    }

    static String x()
        throws a
    {
        if (jU == null)
        {
            throw new a();
        }
        String s;
        try
        {
            s = (String)jU.invoke(null, new Object[0]);
        }
        catch (IllegalAccessException illegalaccessexception)
        {
            throw new a(illegalaccessexception);
        }
        catch (InvocationTargetException invocationtargetexception)
        {
            throw new a(invocationtargetexception);
        }
        return s;
    }

    static Long y()
        throws a
    {
        if (jT == null)
        {
            throw new a();
        }
        Long long1;
        try
        {
            long1 = (Long)jT.invoke(null, new Object[0]);
        }
        catch (IllegalAccessException illegalaccessexception)
        {
            throw new a(illegalaccessexception);
        }
        catch (InvocationTargetException invocationtargetexception)
        {
            throw new a(invocationtargetexception);
        }
        return long1;
    }

    protected void c(Context context)
    {
        IOException ioexception;
        try
        {
            a(1, x());
        }
        catch (a a1) { }
        try
        {
            a(2, v());
        }
        catch (a a2) { }
        try
        {
            a(25, w().longValue());
        }
        catch (a a3) { }
        try
        {
            ArrayList arraylist = g(context);
            a(31, ((Long)arraylist.get(0)).longValue());
            a(32, ((Long)arraylist.get(1)).longValue());
        }
        catch (a a4) { }
        try
        {
            a(33, y().longValue());
            return;
        }
        catch (a a5)
        {
            return;
        }
        ioexception;
    }

    protected void e(Context context)
    {
        IOException ioexception;
        try
        {
            a(2, v());
        }
        catch (a a1) { }
        try
        {
            a(1, x());
        }
        catch (a a2) { }
        try
        {
            long l = w().longValue();
            a(25, l);
            if (startTime != 0L)
            {
                a(17, l - startTime);
                a(23, startTime);
            }
        }
        catch (a a3) { }
        try
        {
            ArrayList arraylist = a(jO, jP);
            a(14, ((Long)arraylist.get(0)).longValue());
            a(15, ((Long)arraylist.get(1)).longValue());
            if (arraylist.size() >= 3)
            {
                a(16, ((Long)arraylist.get(2)).longValue());
            }
        }
        catch (a a4) { }
        try
        {
            a(27, a(context, jQ));
        }
        catch (a a5) { }
        try
        {
            a(29, b(context, jQ));
            return;
        }
        catch (a a6)
        {
            return;
        }
        ioexception;
    }

}

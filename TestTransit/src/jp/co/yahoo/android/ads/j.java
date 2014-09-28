// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.ads;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamClass;
import java.io.OutputStream;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class j
{
    public static class a extends FilterOutputStream
    {

        private boolean a;
        private int b;
        private byte c[];
        private int d;
        private int e;
        private boolean f;
        private byte g[];
        private boolean h;
        private int i;
        private byte j[];

        public void a()
            throws IOException
        {
label0:
            {
                if (b > 0)
                {
                    if (!a)
                    {
                        break label0;
                    }
                    out.write(j.a(g, c, b, i));
                    b = 0;
                }
                return;
            }
            throw new IOException("Base64 input not properly padded.");
        }

        public void close()
            throws IOException
        {
            a();
            super.close();
            c = null;
            out = null;
        }

        public void write(int k)
            throws IOException
        {
            if (!h) goto _L2; else goto _L1
_L1:
            out.write(k);
_L4:
            return;
_L2:
            if (!a)
            {
                break; /* Loop/switch isn't completed */
            }
            byte abyte1[] = c;
            int j1 = b;
            b = j1 + 1;
            abyte1[j1] = (byte)k;
            if (b >= d)
            {
                out.write(j.a(g, c, d, i));
                e = 4 + e;
                if (f && e >= 76)
                {
                    out.write(10);
                    e = 0;
                }
                b = 0;
                return;
            }
            if (true) goto _L4; else goto _L3
_L3:
            if (j[k & 0x7f] <= -5)
            {
                continue; /* Loop/switch isn't completed */
            }
            byte abyte0[] = c;
            int l = b;
            b = l + 1;
            abyte0[l] = (byte)k;
            if (b < d) goto _L4; else goto _L5
_L5:
            int i1 = j.a(c, 0, g, 0, i);
            out.write(g, 0, i1);
            b = 0;
            return;
            if (j[k & 0x7f] == -5) goto _L4; else goto _L6
_L6:
            throw new IOException("Invalid character in Base64 data.");
        }

        public void write(byte abyte0[], int k, int l)
            throws IOException
        {
            if (h)
            {
                out.write(abyte0, k, l);
            } else
            {
                int i1 = 0;
                while (i1 < l) 
                {
                    write(abyte0[k + i1]);
                    i1++;
                }
            }
        }

        public a(OutputStream outputstream, int k)
        {
            boolean flag = true;
            super(outputstream);
            boolean flag1;
            int l;
            if ((k & 8) != 0)
            {
                flag1 = flag;
            } else
            {
                flag1 = false;
            }
            f = flag1;
            if ((k & 1) == 0)
            {
                flag = false;
            }
            a = flag;
            if (a)
            {
                l = 3;
            } else
            {
                l = 4;
            }
            d = l;
            c = new byte[d];
            b = 0;
            e = 0;
            h = false;
            g = new byte[4];
            i = k;
            j = j.a(k);
        }
    }


    static final boolean a;
    private static final byte b[] = {
        65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 
        75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 
        85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 
        101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 
        111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 
        121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 
        56, 57, 43, 47
    };
    private static final byte c[] = {
        -9, -9, -9, -9, -9, -9, -9, -9, -9, -5, 
        -5, -9, -9, -5, -9, -9, -9, -9, -9, -9, 
        -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, 
        -9, -9, -5, -9, -9, -9, -9, -9, -9, -9, 
        -9, -9, -9, 62, -9, -9, -9, 63, 52, 53, 
        54, 55, 56, 57, 58, 59, 60, 61, -9, -9, 
        -9, -1, -9, -9, -9, 0, 1, 2, 3, 4, 
        5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 
        15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 
        25, -9, -9, -9, -9, -9, -9, 26, 27, 28, 
        29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 
        39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 
        49, 50, 51, -9, -9, -9, -9, -9, -9, -9, 
        -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, 
        -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, 
        -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, 
        -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, 
        -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, 
        -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, 
        -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, 
        -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, 
        -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, 
        -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, 
        -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, 
        -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, 
        -9, -9, -9, -9, -9, -9
    };
    private static final byte d[] = {
        65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 
        75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 
        85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 
        101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 
        111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 
        121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 
        56, 57, 45, 95
    };
    private static final byte e[] = {
        -9, -9, -9, -9, -9, -9, -9, -9, -9, -5, 
        -5, -9, -9, -5, -9, -9, -9, -9, -9, -9, 
        -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, 
        -9, -9, -5, -9, -9, -9, -9, -9, -9, -9, 
        -9, -9, -9, -9, -9, 62, -9, -9, 52, 53, 
        54, 55, 56, 57, 58, 59, 60, 61, -9, -9, 
        -9, -1, -9, -9, -9, 0, 1, 2, 3, 4, 
        5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 
        15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 
        25, -9, -9, -9, -9, 63, -9, 26, 27, 28, 
        29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 
        39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 
        49, 50, 51, -9, -9, -9, -9, -9, -9, -9, 
        -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, 
        -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, 
        -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, 
        -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, 
        -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, 
        -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, 
        -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, 
        -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, 
        -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, 
        -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, 
        -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, 
        -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, 
        -9, -9, -9, -9, -9, -9
    };
    private static final byte f[] = {
        45, 48, 49, 50, 51, 52, 53, 54, 55, 56, 
        57, 65, 66, 67, 68, 69, 70, 71, 72, 73, 
        74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 
        84, 85, 86, 87, 88, 89, 90, 95, 97, 98, 
        99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 
        109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 
        119, 120, 121, 122
    };
    private static final byte g[] = {
        -9, -9, -9, -9, -9, -9, -9, -9, -9, -5, 
        -5, -9, -9, -5, -9, -9, -9, -9, -9, -9, 
        -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, 
        -9, -9, -5, -9, -9, -9, -9, -9, -9, -9, 
        -9, -9, -9, -9, -9, 0, -9, -9, 1, 2, 
        3, 4, 5, 6, 7, 8, 9, 10, -9, -9, 
        -9, -1, -9, -9, -9, 11, 12, 13, 14, 15, 
        16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 
        26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 
        36, -9, -9, -9, -9, 37, -9, 38, 39, 40, 
        41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 
        51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 
        61, 62, 63, -9, -9, -9, -9, -9, -9, -9, 
        -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, 
        -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, 
        -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, 
        -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, 
        -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, 
        -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, 
        -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, 
        -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, 
        -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, 
        -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, 
        -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, 
        -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, 
        -9, -9, -9, -9, -9, -9, -9
    };

    private j()
    {
    }

    static int a(byte abyte0[], int i, byte abyte1[], int k, int l)
    {
        return b(abyte0, i, abyte1, k, l);
    }

    public static Object a(String s, int i, ClassLoader classloader)
        throws IOException, ClassNotFoundException
    {
        Object obj;
        byte abyte0[];
        obj = null;
        abyte0 = a(s, i);
        ByteArrayInputStream bytearrayinputstream = new ByteArrayInputStream(abyte0);
        if (classloader != null) goto _L2; else goto _L1
_L1:
        obj = new ObjectInputStream(bytearrayinputstream);
_L3:
        Object obj1 = ((ObjectInputStream) (obj)).readObject();
        IOException ioexception;
        Exception exception;
        ClassNotFoundException classnotfoundexception;
        ObjectInputStream objectinputstream;
        try
        {
            bytearrayinputstream.close();
        }
        catch (Exception exception3) { }
        try
        {
            ((ObjectInputStream) (obj)).close();
        }
        catch (Exception exception4)
        {
            return obj1;
        }
        return obj1;
_L2:
        objectinputstream = new ObjectInputStream(bytearrayinputstream, classloader) {

            final ClassLoader a;

            public Class resolveClass(ObjectStreamClass objectstreamclass)
                throws IOException, ClassNotFoundException
            {
                Class class1 = Class.forName(objectstreamclass.getName(), false, a);
                if (class1 == null)
                {
                    class1 = super.resolveClass(objectstreamclass);
                }
                return class1;
            }

            
            {
                a = classloader;
                super(inputstream);
            }
        };
        obj = objectinputstream;
          goto _L3
        ioexception;
        bytearrayinputstream = null;
_L7:
        throw ioexception;
        exception;
_L4:
        try
        {
            bytearrayinputstream.close();
        }
        catch (Exception exception1) { }
        try
        {
            ((ObjectInputStream) (obj)).close();
        }
        catch (Exception exception2) { }
        throw exception;
        classnotfoundexception;
        bytearrayinputstream = null;
_L5:
        throw classnotfoundexception;
        exception;
        obj = null;
        bytearrayinputstream = null;
          goto _L4
        classnotfoundexception;
          goto _L5
        ioexception;
        if (true) goto _L7; else goto _L6
_L6:
    }

    public static String a(Serializable serializable)
        throws IOException
    {
        return a(serializable, 0);
    }

    public static String a(Serializable serializable, int i)
        throws IOException
    {
        ObjectOutputStream objectoutputstream;
        objectoutputstream = null;
        if (serializable == null)
        {
            throw new NullPointerException("Cannot serialize a null object.");
        }
        ByteArrayOutputStream bytearrayoutputstream = new ByteArrayOutputStream();
        a a1;
        GZIPOutputStream gzipoutputstream;
        IOException ioexception;
        Exception exception;
        try
        {
            a1 = new a(bytearrayoutputstream, i | 1);
        }
        // Misplaced declaration of an exception variable
        catch (IOException ioexception)
        {
            objectoutputstream = null;
            gzipoutputstream = null;
            a1 = null;
            continue; /* Loop/switch isn't completed */
        }
        finally
        {
            objectoutputstream = null;
            gzipoutputstream = null;
            a1 = null;
            continue; /* Loop/switch isn't completed */
        }
_L4:
        if ((i & 2) == 0) goto _L2; else goto _L1
_L1:
        try
        {
            gzipoutputstream = new GZIPOutputStream(a1);
        }
        // Misplaced declaration of an exception variable
        catch (IOException ioexception)
        {
            objectoutputstream = null;
            gzipoutputstream = null;
            continue; /* Loop/switch isn't completed */
        }
        finally
        {
            objectoutputstream = null;
            gzipoutputstream = null;
        }
        objectoutputstream = new ObjectOutputStream(gzipoutputstream);
_L3:
        objectoutputstream.writeObject(serializable);
        ObjectOutputStream objectoutputstream1;
        String s;
        try
        {
            objectoutputstream.close();
        }
        catch (Exception exception5) { }
        try
        {
            gzipoutputstream.close();
        }
        catch (Exception exception6) { }
        try
        {
            a1.close();
        }
        catch (Exception exception7) { }
        try
        {
            bytearrayoutputstream.close();
        }
        catch (Exception exception8) { }
        try
        {
            s = new String(bytearrayoutputstream.toByteArray(), "US-ASCII");
        }
        catch (UnsupportedEncodingException unsupportedencodingexception)
        {
            return new String(bytearrayoutputstream.toByteArray());
        }
        return s;
_L2:
        objectoutputstream1 = new ObjectOutputStream(a1);
        objectoutputstream = objectoutputstream1;
        gzipoutputstream = null;
          goto _L3
        ioexception;
        gzipoutputstream = null;
        a1 = null;
        bytearrayoutputstream = null;
_L7:
        throw ioexception;
        exception;
_L5:
        try
        {
            objectoutputstream.close();
        }
        catch (Exception exception1) { }
        try
        {
            gzipoutputstream.close();
        }
        catch (Exception exception2) { }
        try
        {
            a1.close();
        }
        catch (Exception exception3) { }
        try
        {
            bytearrayoutputstream.close();
        }
        catch (Exception exception4) { }
        throw exception;
        exception;
        objectoutputstream = null;
        gzipoutputstream = null;
        a1 = null;
        bytearrayoutputstream = null;
        continue; /* Loop/switch isn't completed */
        if (true) goto _L5; else goto _L4
        ioexception;
        if (true) goto _L7; else goto _L6
_L6:
    }

    public static String a(byte abyte0[])
    {
        String s1 = a(abyte0, 0, abyte0.length, 0);
        String s = s1;
_L1:
        IOException ioexception;
        if (!a && s == null)
        {
            throw new AssertionError();
        } else
        {
            return s;
        }
        ioexception;
        boolean flag = a;
        s = null;
        if (!flag)
        {
            throw new AssertionError(ioexception.getMessage());
        }
          goto _L1
    }

    public static String a(byte abyte0[], int i, int k, int l)
        throws IOException
    {
        byte abyte1[] = b(abyte0, i, k, l);
        String s;
        try
        {
            s = new String(abyte1, "US-ASCII");
        }
        catch (UnsupportedEncodingException unsupportedencodingexception)
        {
            return new String(abyte1);
        }
        return s;
    }

    static byte[] a(int i)
    {
        return c(i);
    }

    public static byte[] a(String s)
        throws IOException
    {
        return a(s, 0);
    }

    public static byte[] a(String s, int i)
        throws IOException
    {
        GZIPInputStream gzipinputstream;
        gzipinputstream = null;
        if (s == null)
        {
            throw new NullPointerException("Input string was null.");
        }
        byte abyte4[] = s.getBytes("US-ASCII");
        byte abyte0[] = abyte4;
_L6:
        byte abyte1[];
        ByteArrayOutputStream bytearrayoutputstream;
        ByteArrayInputStream bytearrayinputstream;
        GZIPInputStream gzipinputstream1;
        IOException ioexception;
        ByteArrayInputStream bytearrayinputstream1;
        abyte1 = c(abyte0, 0, abyte0.length, i);
        UnsupportedEncodingException unsupportedencodingexception;
        boolean flag;
        byte abyte2[];
        int k;
        if ((i & 4) != 0)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (abyte1 == null || abyte1.length < 4 || flag || 35615 != (0xff & abyte1[0] | 0xff00 & abyte1[1] << 8)) goto _L2; else goto _L1
_L1:
        abyte2 = new byte[2048];
        bytearrayoutputstream = new ByteArrayOutputStream();
        bytearrayinputstream = new ByteArrayInputStream(abyte1);
        gzipinputstream1 = new GZIPInputStream(bytearrayinputstream);
_L5:
        k = gzipinputstream1.read(abyte2);
        if (k < 0) goto _L4; else goto _L3
_L3:
        bytearrayoutputstream.write(abyte2, 0, k);
          goto _L5
        ioexception;
        gzipinputstream = gzipinputstream1;
        bytearrayinputstream1 = bytearrayinputstream;
_L9:
        ioexception.printStackTrace();
        Exception exception;
        byte abyte3[];
        Exception exception7;
        Exception exception8;
        Exception exception9;
        try
        {
            bytearrayoutputstream.close();
        }
        catch (Exception exception4) { }
        try
        {
            gzipinputstream.close();
        }
        catch (Exception exception5) { }
        try
        {
            bytearrayinputstream1.close();
        }
        catch (Exception exception6)
        {
            return abyte1;
        }
_L2:
        return abyte1;
        unsupportedencodingexception;
        abyte0 = s.getBytes();
          goto _L6
_L4:
        abyte3 = bytearrayoutputstream.toByteArray();
        try
        {
            bytearrayoutputstream.close();
        }
        // Misplaced declaration of an exception variable
        catch (Exception exception7) { }
        try
        {
            gzipinputstream1.close();
        }
        // Misplaced declaration of an exception variable
        catch (Exception exception8) { }
        try
        {
            bytearrayinputstream.close();
        }
        // Misplaced declaration of an exception variable
        catch (Exception exception9)
        {
            return abyte3;
        }
        return abyte3;
        exception;
        bytearrayoutputstream = null;
        bytearrayinputstream = null;
_L8:
        try
        {
            bytearrayoutputstream.close();
        }
        catch (Exception exception1) { }
        try
        {
            gzipinputstream.close();
        }
        catch (Exception exception2) { }
        try
        {
            bytearrayinputstream.close();
        }
        catch (Exception exception3) { }
        throw exception;
        exception;
        gzipinputstream = null;
        bytearrayinputstream = null;
        continue; /* Loop/switch isn't completed */
        exception;
        gzipinputstream = null;
        continue; /* Loop/switch isn't completed */
        exception;
        gzipinputstream = gzipinputstream1;
        continue; /* Loop/switch isn't completed */
        exception;
        bytearrayinputstream = bytearrayinputstream1;
        if (true) goto _L8; else goto _L7
_L7:
        ioexception;
        bytearrayoutputstream = null;
        gzipinputstream = null;
        bytearrayinputstream1 = null;
          goto _L9
        ioexception;
        gzipinputstream = null;
        bytearrayinputstream1 = null;
          goto _L9
        ioexception;
        bytearrayinputstream1 = bytearrayinputstream;
        gzipinputstream = null;
          goto _L9
    }

    private static byte[] a(byte abyte0[], int i, int k, byte abyte1[], int l, int i1)
    {
        byte abyte2[] = b(i1);
        int j1;
        int k1;
        int l1;
        int i2;
        int j2;
        if (k > 0)
        {
            j1 = (abyte0[i] << 24) >>> 8;
        } else
        {
            j1 = 0;
        }
        if (k > 1)
        {
            k1 = (abyte0[i + 1] << 24) >>> 16;
        } else
        {
            k1 = 0;
        }
        l1 = k1 | j1;
        i2 = 0;
        if (k > 2)
        {
            i2 = (abyte0[i + 2] << 24) >>> 24;
        }
        j2 = i2 | l1;
        switch (k)
        {
        default:
            return abyte1;

        case 3: // '\003'
            abyte1[l] = abyte2[j2 >>> 18];
            abyte1[l + 1] = abyte2[0x3f & j2 >>> 12];
            abyte1[l + 2] = abyte2[0x3f & j2 >>> 6];
            abyte1[l + 3] = abyte2[j2 & 0x3f];
            return abyte1;

        case 2: // '\002'
            abyte1[l] = abyte2[j2 >>> 18];
            abyte1[l + 1] = abyte2[0x3f & j2 >>> 12];
            abyte1[l + 2] = abyte2[0x3f & j2 >>> 6];
            abyte1[l + 3] = 61;
            return abyte1;

        case 1: // '\001'
            abyte1[l] = abyte2[j2 >>> 18];
            abyte1[l + 1] = abyte2[0x3f & j2 >>> 12];
            abyte1[l + 2] = 61;
            abyte1[l + 3] = 61;
            return abyte1;
        }
    }

    static byte[] a(byte abyte0[], byte abyte1[], int i, int k)
    {
        return b(abyte0, abyte1, i, k);
    }

    private static int b(byte abyte0[], int i, byte abyte1[], int k, int l)
    {
        if (abyte0 == null)
        {
            throw new NullPointerException("Source array was null.");
        }
        if (abyte1 == null)
        {
            throw new NullPointerException("Destination array was null.");
        }
        if (i < 0 || i + 3 >= abyte0.length)
        {
            Object aobj[] = new Object[2];
            aobj[0] = Integer.valueOf(abyte0.length);
            aobj[1] = Integer.valueOf(i);
            throw new IllegalArgumentException(String.format("Source array with length %d cannot have offset of %d and still process four bytes.", aobj));
        }
        if (k < 0 || k + 2 >= abyte1.length)
        {
            Object aobj1[] = new Object[2];
            aobj1[0] = Integer.valueOf(abyte1.length);
            aobj1[1] = Integer.valueOf(k);
            throw new IllegalArgumentException(String.format("Destination array with length %d cannot have offset of %d and still store three bytes.", aobj1));
        }
        byte abyte2[] = c(l);
        if (abyte0[i + 2] == 61)
        {
            abyte1[k] = (byte)(((0xff & abyte2[abyte0[i]]) << 18 | (0xff & abyte2[abyte0[i + 1]]) << 12) >>> 16);
            return 1;
        }
        if (abyte0[i + 3] == 61)
        {
            int j1 = (0xff & abyte2[abyte0[i]]) << 18 | (0xff & abyte2[abyte0[i + 1]]) << 12 | (0xff & abyte2[abyte0[i + 2]]) << 6;
            abyte1[k] = (byte)(j1 >>> 16);
            abyte1[k + 1] = (byte)(j1 >>> 8);
            return 2;
        } else
        {
            int i1 = (0xff & abyte2[abyte0[i]]) << 18 | (0xff & abyte2[abyte0[i + 1]]) << 12 | (0xff & abyte2[abyte0[i + 2]]) << 6 | 0xff & abyte2[abyte0[i + 3]];
            abyte1[k] = (byte)(i1 >> 16);
            abyte1[k + 1] = (byte)(i1 >> 8);
            abyte1[k + 2] = (byte)i1;
            return 3;
        }
    }

    public static Object b(String s)
        throws IOException, ClassNotFoundException
    {
        return a(s, 0, null);
    }

    private static final byte[] b(int i)
    {
        if ((i & 0x10) == 16)
        {
            return d;
        }
        if ((i & 0x20) == 32)
        {
            return f;
        } else
        {
            return b;
        }
    }

    public static byte[] b(byte abyte0[], int i, int k, int l)
        throws IOException
    {
        GZIPOutputStream gzipoutputstream;
        gzipoutputstream = null;
        if (abyte0 == null)
        {
            throw new NullPointerException("Cannot serialize a null array.");
        }
        if (i < 0)
        {
            throw new IllegalArgumentException((new StringBuilder()).append("Cannot have negative offset: ").append(i).toString());
        }
        if (k < 0)
        {
            throw new IllegalArgumentException((new StringBuilder()).append("Cannot have length offset: ").append(k).toString());
        }
        if (i + k > abyte0.length)
        {
            Object aobj[] = new Object[3];
            aobj[0] = Integer.valueOf(i);
            aobj[1] = Integer.valueOf(k);
            aobj[2] = Integer.valueOf(abyte0.length);
            throw new IllegalArgumentException(String.format("Cannot have offset of %d and length of %d with array of length %d", aobj));
        }
        if ((l & 2) == 0) goto _L2; else goto _L1
_L1:
        ByteArrayOutputStream bytearrayoutputstream = new ByteArrayOutputStream();
        GZIPOutputStream gzipoutputstream1;
        IOException ioexception;
        ByteArrayOutputStream bytearrayoutputstream1;
        boolean flag;
        int i1;
        byte byte0;
        int j1;
        byte abyte1[];
        int k1;
        int l1;
        int i2;
        int j2;
        byte abyte2[];
        int k2;
        int l2;
        a a1;
        Exception exception;
        Exception exception1;
        Exception exception2;
        Exception exception3;
        Exception exception4;
        Exception exception5;
        Exception exception6;
        try
        {
            a1 = new a(bytearrayoutputstream, l | 1);
        }
        // Misplaced declaration of an exception variable
        catch (IOException ioexception)
        {
            bytearrayoutputstream1 = bytearrayoutputstream;
            a1 = null;
            gzipoutputstream = null;
            continue; /* Loop/switch isn't completed */
        }
        finally
        {
            a1 = null;
            gzipoutputstream = null;
            continue; /* Loop/switch isn't completed */
        }
        try
        {
_L3:
            gzipoutputstream1 = new GZIPOutputStream(a1);
        }
        // Misplaced declaration of an exception variable
        catch (IOException ioexception)
        {
            bytearrayoutputstream1 = bytearrayoutputstream;
            gzipoutputstream = null;
            continue; /* Loop/switch isn't completed */
        }
        finally
        {
            gzipoutputstream = null;
            continue; /* Loop/switch isn't completed */
        }
        gzipoutputstream1.write(abyte0, i, k);
        gzipoutputstream1.close();
        try
        {
            gzipoutputstream1.close();
        }
        // Misplaced declaration of an exception variable
        catch (Exception exception4) { }
        try
        {
            a1.close();
        }
        // Misplaced declaration of an exception variable
        catch (Exception exception5) { }
        try
        {
            bytearrayoutputstream.close();
        }
        // Misplaced declaration of an exception variable
        catch (Exception exception6) { }
        abyte1 = bytearrayoutputstream.toByteArray();
        return abyte1;
        ioexception;
        a1 = null;
        bytearrayoutputstream1 = null;
_L6:
        throw ioexception;
        exception;
        bytearrayoutputstream = bytearrayoutputstream1;
_L4:
        try
        {
            gzipoutputstream.close();
        }
        // Misplaced declaration of an exception variable
        catch (Exception exception1) { }
        try
        {
            a1.close();
        }
        // Misplaced declaration of an exception variable
        catch (Exception exception2) { }
        try
        {
            bytearrayoutputstream.close();
        }
        // Misplaced declaration of an exception variable
        catch (Exception exception3) { }
        throw exception;
_L2:
        if ((l & 8) != 0)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        i1 = 4 * (k / 3);
        if (k % 3 > 0)
        {
            byte0 = 4;
        } else
        {
            byte0 = 0;
        }
        j1 = byte0 + i1;
        if (flag)
        {
            j1 += j1 / 76;
        }
        abyte1 = new byte[j1];
        k1 = k - 2;
        l1 = 0;
        i2 = 0;
        for (j2 = 0; j2 < k1; j2 = l2)
        {
            a(abyte0, j2 + i, 3, abyte1, i2, l);
            k2 = l1 + 4;
            if (flag && k2 >= 76)
            {
                abyte1[i2 + 4] = 10;
                i2++;
                k2 = 0;
            }
            l2 = j2 + 3;
            i2 += 4;
            l1 = k2;
        }

        if (j2 < k)
        {
            a(abyte0, j2 + i, k - j2, abyte1, i2, l);
            i2 += 4;
        }
        if (i2 <= -1 + abyte1.length)
        {
            abyte2 = new byte[i2];
            System.arraycopy(abyte1, 0, abyte2, 0, i2);
            return abyte2;
        } else
        {
            break MISSING_BLOCK_LABEL_212;
        }
        exception;
        a1 = null;
        gzipoutputstream = null;
        bytearrayoutputstream = null;
        continue; /* Loop/switch isn't completed */
        exception;
        gzipoutputstream = gzipoutputstream1;
        if (true) goto _L4; else goto _L3
        ioexception;
        gzipoutputstream = gzipoutputstream1;
        bytearrayoutputstream1 = bytearrayoutputstream;
        if (true) goto _L6; else goto _L5
_L5:
    }

    private static byte[] b(byte abyte0[], byte abyte1[], int i, int k)
    {
        a(abyte1, 0, i, abyte0, 0, k);
        return abyte0;
    }

    private static final byte[] c(int i)
    {
        if ((i & 0x10) == 16)
        {
            return e;
        }
        if ((i & 0x20) == 32)
        {
            return g;
        } else
        {
            return c;
        }
    }

    public static byte[] c(byte abyte0[], int i, int k, int l)
        throws IOException
    {
        byte abyte1[];
        byte abyte2[];
        byte abyte3[];
        int i1;
        int j1;
        int k1;
        if (abyte0 == null)
        {
            throw new NullPointerException("Cannot decode null source array.");
        }
        if (i < 0 || i + k > abyte0.length)
        {
            Object aobj[] = new Object[3];
            aobj[0] = Integer.valueOf(abyte0.length);
            aobj[1] = Integer.valueOf(i);
            aobj[2] = Integer.valueOf(k);
            throw new IllegalArgumentException(String.format("Source array with length %d cannot have offset of %d and process %d bytes.", aobj));
        }
        if (k == 0)
        {
            return new byte[0];
        }
        if (k < 4)
        {
            throw new IllegalArgumentException((new StringBuilder()).append("Base64-encoded string must have at least four characters, but length specified was ").append(k).toString());
        }
        abyte1 = c(l);
        abyte2 = new byte[(k * 3) / 4];
        abyte3 = new byte[4];
        i1 = i;
        j1 = 0;
        k1 = 0;
_L9:
        byte byte0;
        if (i1 >= i + k)
        {
            break MISSING_BLOCK_LABEL_329;
        }
        byte0 = abyte1[0xff & abyte0[i1]];
        if (byte0 < -5) goto _L2; else goto _L1
_L1:
        if (byte0 < -1) goto _L4; else goto _L3
_L3:
        int i2;
        i2 = j1 + 1;
        abyte3[j1] = abyte0[i1];
        if (i2 <= 3) goto _L6; else goto _L5
_L5:
        int l1 = k1 + b(abyte3, 0, abyte2, k1, l);
        if (abyte0[i1] != 61) goto _L8; else goto _L7
_L7:
        byte abyte4[] = new byte[l1];
        System.arraycopy(abyte2, 0, abyte4, 0, l1);
        return abyte4;
_L2:
        Object aobj1[] = new Object[2];
        aobj1[0] = Integer.valueOf(0xff & abyte0[i1]);
        aobj1[1] = Integer.valueOf(i1);
        throw new IOException(String.format("Bad Base64 input character decimal %d in array position %d", aobj1));
_L8:
        int j2;
        j2 = l1;
        i2 = 0;
_L10:
        i1++;
        k1 = j2;
        j1 = i2;
          goto _L9
_L6:
        j2 = k1;
          goto _L10
_L4:
        i2 = j1;
        j2 = k1;
          goto _L10
        l1 = k1;
          goto _L7
    }

    static 
    {
        boolean flag;
        if (!jp/co/yahoo/android/ads/j.desiredAssertionStatus())
        {
            flag = true;
        } else
        {
            flag = false;
        }
        a = flag;
    }
}

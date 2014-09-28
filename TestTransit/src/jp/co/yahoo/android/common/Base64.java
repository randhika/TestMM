// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.common;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilterInputStream;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamClass;
import java.io.OutputStream;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class Base64
{
    public static class InputStream extends FilterInputStream
    {

        private boolean breakLines;
        private byte buffer[];
        private int bufferLength;
        private byte decodabet[];
        private boolean encode;
        private int lineLength;
        private int numSigBytes;
        private int options;
        private int position;

        public int read()
            throws IOException
        {
            return -1;
            byte abyte1[] = new byte[4];
            int j = 0;
            do
            {
label0:
                {
                    int k;
                    if (j < 4)
                    {
                        do
                        {
                            k = in.read();
                        } while (k >= 0 && decodabet[k & 0x7f] <= -5);
                        if (k >= 0)
                        {
                            break label0;
                        }
                    }
                    if (j == 4)
                    {
                        numSigBytes = Base64.decode4to3(abyte1, 0, buffer, 0, options);
                        position = 0;
                    } else
                    if (j == 0)
                    {
                        return -1;
                    } else
                    {
                        throw new IOException("Improperly padded Base64 input.");
                    }
                    continue;
                }
                abyte1[j] = (byte)k;
                j++;
            } while (true);
            if (position < 0)
            {
                if (!encode)
                {
                    break MISSING_BLOCK_LABEL_117;
                }
                byte abyte2[] = new byte[3];
                int l = 0;
                int i1 = 0;
                do
                {
                    if (i1 >= 3)
                    {
                        break;
                    }
                    int j1 = in.read();
                    if (j1 < 0)
                    {
                        break;
                    }
                    abyte2[i1] = (byte)j1;
                    l++;
                    i1++;
                } while (true);
                if (l <= 0)
                {
                    break MISSING_BLOCK_LABEL_115;
                }
                Base64.encode3to4(abyte2, 0, l, buffer, 0, options);
                position = 0;
                numSigBytes = 4;
            }
            do
            {
                if (position >= 0)
                {
                    if (position >= numSigBytes)
                    {
                        return -1;
                    }
                    if (encode && breakLines && lineLength >= 76)
                    {
                        lineLength = 0;
                        return 10;
                    }
                    lineLength = 1 + lineLength;
                    byte abyte0[] = buffer;
                    int i = position;
                    position = i + 1;
                    byte byte0 = abyte0[i];
                    if (position >= bufferLength)
                    {
                        position = -1;
                    }
                    return byte0 & 0xff;
                }
                throw new IOException("Error in Base64 code reading stream.");
            } while (true);
        }

        public int read(byte abyte0[], int i, int j)
            throws IOException
        {
            int k;
label0:
            {
                k = 0;
                do
                {
                    if (k >= j)
                    {
                        break label0;
                    }
                    int l = read();
                    if (l < 0)
                    {
                        break;
                    }
                    abyte0[i + k] = (byte)l;
                    k++;
                } while (true);
                if (k == 0)
                {
                    k = -1;
                }
            }
            return k;
        }

        public InputStream(java.io.InputStream inputstream)
        {
            this(inputstream, 0);
        }

        public InputStream(java.io.InputStream inputstream, int i)
        {
            boolean flag = true;
            super(inputstream);
            options = i;
            boolean flag1;
            int j;
            if ((i & 8) > 0)
            {
                flag1 = flag;
            } else
            {
                flag1 = false;
            }
            breakLines = flag1;
            if ((i & 1) <= 0)
            {
                flag = false;
            }
            encode = flag;
            if (encode)
            {
                j = 4;
            } else
            {
                j = 3;
            }
            bufferLength = j;
            buffer = new byte[bufferLength];
            position = -1;
            lineLength = 0;
            decodabet = Base64.getDecodabet(i);
        }
    }

    public static class OutputStream extends FilterOutputStream
    {

        private byte b4[];
        private boolean breakLines;
        private byte buffer[];
        private int bufferLength;
        private byte decodabet[];
        private boolean encode;
        private int lineLength;
        private int options;
        private int position;
        private boolean suspendEncoding;

        public void close()
            throws IOException
        {
            flushBase64();
            super.close();
            buffer = null;
            out = null;
        }

        public void flushBase64()
            throws IOException
        {
label0:
            {
                if (position > 0)
                {
                    if (!encode)
                    {
                        break label0;
                    }
                    out.write(Base64.encode3to4(b4, buffer, position, options));
                    position = 0;
                }
                return;
            }
            throw new IOException("Base64 input not properly padded.");
        }

        public void resumeEncoding()
        {
            suspendEncoding = false;
        }

        public void suspendEncoding()
            throws IOException
        {
            flushBase64();
            suspendEncoding = true;
        }

        public void write(int i)
            throws IOException
        {
            if (!suspendEncoding) goto _L2; else goto _L1
_L1:
            out.write(i);
_L4:
            return;
_L2:
            if (!encode)
            {
                break; /* Loop/switch isn't completed */
            }
            byte abyte1[] = buffer;
            int l = position;
            position = l + 1;
            abyte1[l] = (byte)i;
            if (position >= bufferLength)
            {
                out.write(Base64.encode3to4(b4, buffer, bufferLength, options));
                lineLength = 4 + lineLength;
                if (breakLines && lineLength >= 76)
                {
                    out.write(10);
                    lineLength = 0;
                }
                position = 0;
                return;
            }
            if (true) goto _L4; else goto _L3
_L3:
            if (decodabet[i & 0x7f] <= -5)
            {
                continue; /* Loop/switch isn't completed */
            }
            byte abyte0[] = buffer;
            int j = position;
            position = j + 1;
            abyte0[j] = (byte)i;
            if (position < bufferLength) goto _L4; else goto _L5
_L5:
            int k = Base64.decode4to3(buffer, 0, b4, 0, options);
            out.write(b4, 0, k);
            position = 0;
            return;
            if (decodabet[i & 0x7f] == -5) goto _L4; else goto _L6
_L6:
            throw new IOException("Invalid character in Base64 data.");
        }

        public void write(byte abyte0[], int i, int j)
            throws IOException
        {
            if (suspendEncoding)
            {
                out.write(abyte0, i, j);
            } else
            {
                int k = 0;
                while (k < j) 
                {
                    write(abyte0[i + k]);
                    k++;
                }
            }
        }

        public OutputStream(java.io.OutputStream outputstream)
        {
            this(outputstream, 1);
        }

        public OutputStream(java.io.OutputStream outputstream, int i)
        {
            boolean flag = true;
            super(outputstream);
            boolean flag1;
            int j;
            if ((i & 8) != 0)
            {
                flag1 = flag;
            } else
            {
                flag1 = false;
            }
            breakLines = flag1;
            if ((i & 1) == 0)
            {
                flag = false;
            }
            encode = flag;
            if (encode)
            {
                j = 3;
            } else
            {
                j = 4;
            }
            bufferLength = j;
            buffer = new byte[bufferLength];
            position = 0;
            lineLength = 0;
            suspendEncoding = false;
            b4 = new byte[4];
            options = i;
            decodabet = Base64.getDecodabet(i);
        }
    }


    static final boolean $assertionsDisabled = false;
    public static final int DECODE = 0;
    public static final int DONT_GUNZIP = 4;
    public static final int DO_BREAK_LINES = 8;
    public static final int ENCODE = 1;
    private static final byte EQUALS_SIGN = 61;
    private static final byte EQUALS_SIGN_ENC = -1;
    public static final int GZIP = 2;
    private static final int MAX_LINE_LENGTH = 76;
    private static final byte NEW_LINE = 10;
    public static final int NO_OPTIONS = 0;
    public static final int ORDERED = 32;
    private static final String PREFERRED_ENCODING = "US-ASCII";
    public static final int URL_SAFE = 16;
    private static final byte WHITE_SPACE_ENC = -5;
    private static final byte _ORDERED_ALPHABET[] = {
        45, 48, 49, 50, 51, 52, 53, 54, 55, 56, 
        57, 65, 66, 67, 68, 69, 70, 71, 72, 73, 
        74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 
        84, 85, 86, 87, 88, 89, 90, 95, 97, 98, 
        99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 
        109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 
        119, 120, 121, 122
    };
    private static final byte _ORDERED_DECODABET[] = {
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
    private static final byte _STANDARD_ALPHABET[] = {
        65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 
        75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 
        85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 
        101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 
        111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 
        121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 
        56, 57, 43, 47
    };
    private static final byte _STANDARD_DECODABET[] = {
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
    private static final byte _URL_SAFE_ALPHABET[] = {
        65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 
        75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 
        85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 
        101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 
        111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 
        121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 
        56, 57, 45, 95
    };
    private static final byte _URL_SAFE_DECODABET[] = {
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

    private Base64()
    {
    }

    public static byte[] decode(String s)
        throws IOException
    {
        return decode(s, 0);
    }

    public static byte[] decode(String s, int i)
        throws IOException
    {
        if (s == null)
        {
            throw new NullPointerException("Input string was null.");
        }
        byte abyte4[] = s.getBytes("US-ASCII");
        byte abyte0[] = abyte4;
_L6:
        byte abyte1[];
        ByteArrayInputStream bytearrayinputstream;
        GZIPInputStream gzipinputstream;
        ByteArrayOutputStream bytearrayoutputstream;
        ByteArrayOutputStream bytearrayoutputstream1;
        ByteArrayInputStream bytearrayinputstream1;
        GZIPInputStream gzipinputstream1;
        IOException ioexception;
        abyte1 = decode(abyte0, 0, abyte0.length, i);
        UnsupportedEncodingException unsupportedencodingexception;
        boolean flag;
        byte abyte2[];
        int j;
        if ((i & 4) != 0)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (abyte1 == null || abyte1.length < 4 || flag || 35615 != (0xff & abyte1[0] | 0xff00 & abyte1[1] << 8)) goto _L2; else goto _L1
_L1:
        bytearrayinputstream = null;
        gzipinputstream = null;
        bytearrayoutputstream = null;
        abyte2 = new byte[2048];
        bytearrayoutputstream1 = new ByteArrayOutputStream();
        bytearrayinputstream1 = new ByteArrayInputStream(abyte1);
        gzipinputstream1 = new GZIPInputStream(bytearrayinputstream1);
_L5:
        j = gzipinputstream1.read(abyte2);
        if (j < 0) goto _L4; else goto _L3
_L3:
        bytearrayoutputstream1.write(abyte2, 0, j);
          goto _L5
        ioexception;
        bytearrayoutputstream = bytearrayoutputstream1;
        gzipinputstream = gzipinputstream1;
        bytearrayinputstream = bytearrayinputstream1;
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
            bytearrayinputstream.close();
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
        abyte3 = bytearrayoutputstream1.toByteArray();
        try
        {
            bytearrayoutputstream1.close();
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
            bytearrayinputstream1.close();
        }
        // Misplaced declaration of an exception variable
        catch (Exception exception9)
        {
            return abyte3;
        }
        return abyte3;
        exception;
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
        bytearrayoutputstream = bytearrayoutputstream1;
        bytearrayinputstream = null;
        gzipinputstream = null;
        continue; /* Loop/switch isn't completed */
        exception;
        bytearrayoutputstream = bytearrayoutputstream1;
        bytearrayinputstream = bytearrayinputstream1;
        gzipinputstream = null;
        continue; /* Loop/switch isn't completed */
        exception;
        bytearrayoutputstream = bytearrayoutputstream1;
        gzipinputstream = gzipinputstream1;
        bytearrayinputstream = bytearrayinputstream1;
        if (true) goto _L8; else goto _L7
_L7:
        ioexception;
        bytearrayinputstream = null;
        bytearrayoutputstream = null;
        gzipinputstream = null;
          goto _L9
        ioexception;
        bytearrayoutputstream = bytearrayoutputstream1;
        bytearrayinputstream = null;
        gzipinputstream = null;
          goto _L9
        ioexception;
        bytearrayoutputstream = bytearrayoutputstream1;
        bytearrayinputstream = bytearrayinputstream1;
        gzipinputstream = null;
          goto _L9
    }

    public static byte[] decode(byte abyte0[])
        throws IOException
    {
        return decode(abyte0, 0, abyte0.length, 0);
    }

    public static byte[] decode(byte abyte0[], int i, int j, int k)
        throws IOException
    {
        byte abyte1[];
        byte abyte2[];
        int l;
        byte abyte3[];
        int i1;
        int j1;
        if (abyte0 == null)
        {
            throw new NullPointerException("Cannot decode null source array.");
        }
        if (i < 0 || i + j > abyte0.length)
        {
            Object aobj[] = new Object[3];
            aobj[0] = Integer.valueOf(abyte0.length);
            aobj[1] = Integer.valueOf(i);
            aobj[2] = Integer.valueOf(j);
            throw new IllegalArgumentException(String.format("Source array with length %d cannot have offset of %d and process %d bytes.", aobj));
        }
        if (j == 0)
        {
            return new byte[0];
        }
        if (j < 4)
        {
            throw new IllegalArgumentException((new StringBuilder()).append("Base64-encoded string must have at least four characters, but length specified was ").append(j).toString());
        }
        abyte1 = getDecodabet(k);
        abyte2 = new byte[(j * 3) / 4];
        l = 0;
        abyte3 = new byte[4];
        i1 = i;
        j1 = 0;
_L10:
        if (i1 >= i + j) goto _L2; else goto _L1
_L1:
        byte byte0 = abyte1[0xff & abyte0[i1]];
        if (byte0 < -5) goto _L4; else goto _L3
_L3:
        if (byte0 < -1) goto _L6; else goto _L5
_L5:
        int k1;
        k1 = j1 + 1;
        abyte3[j1] = abyte0[i1];
        if (k1 <= 3) goto _L8; else goto _L7
_L7:
        byte byte1;
        l += decode4to3(abyte3, 0, abyte2, l, k);
        byte1 = abyte0[i1];
        k1 = 0;
        if (byte1 != 61) goto _L8; else goto _L9
_L9:
        byte abyte4[] = new byte[l];
        System.arraycopy(abyte2, 0, abyte4, 0, l);
        return abyte4;
_L4:
        Object aobj1[] = new Object[2];
        aobj1[0] = Integer.valueOf(0xff & abyte0[i1]);
        aobj1[1] = Integer.valueOf(i1);
        throw new IOException(String.format("Bad Base64 input character decimal %d in array position %d", aobj1));
_L6:
        k1 = j1;
_L8:
        i1++;
        j1 = k1;
          goto _L10
_L2:
        j1;
          goto _L9
    }

    private static int decode4to3(byte abyte0[], int i, byte abyte1[], int j, int k)
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
        if (j < 0 || j + 2 >= abyte1.length)
        {
            Object aobj1[] = new Object[2];
            aobj1[0] = Integer.valueOf(abyte1.length);
            aobj1[1] = Integer.valueOf(j);
            throw new IllegalArgumentException(String.format("Destination array with length %d cannot have offset of %d and still store three bytes.", aobj1));
        }
        byte abyte2[] = getDecodabet(k);
        if (abyte0[i + 2] == 61)
        {
            abyte1[j] = (byte)(((0xff & abyte2[abyte0[i]]) << 18 | (0xff & abyte2[abyte0[i + 1]]) << 12) >>> 16);
            return 1;
        }
        if (abyte0[i + 3] == 61)
        {
            int i1 = (0xff & abyte2[abyte0[i]]) << 18 | (0xff & abyte2[abyte0[i + 1]]) << 12 | (0xff & abyte2[abyte0[i + 2]]) << 6;
            abyte1[j] = (byte)(i1 >>> 16);
            abyte1[j + 1] = (byte)(i1 >>> 8);
            return 2;
        } else
        {
            int l = (0xff & abyte2[abyte0[i]]) << 18 | (0xff & abyte2[abyte0[i + 1]]) << 12 | (0xff & abyte2[abyte0[i + 2]]) << 6 | 0xff & abyte2[abyte0[i + 3]];
            abyte1[j] = (byte)(l >> 16);
            abyte1[j + 1] = (byte)(l >> 8);
            abyte1[j + 2] = (byte)l;
            return 3;
        }
    }

    public static void decodeFileToFile(String s, String s1)
        throws IOException
    {
        byte abyte0[];
        BufferedOutputStream bufferedoutputstream;
        abyte0 = decodeFromFile(s);
        bufferedoutputstream = null;
        BufferedOutputStream bufferedoutputstream1 = new BufferedOutputStream(new FileOutputStream(s1));
        bufferedoutputstream1.write(abyte0);
        IOException ioexception;
        Exception exception;
        try
        {
            bufferedoutputstream1.close();
            return;
        }
        catch (Exception exception2)
        {
            return;
        }
        ioexception;
_L4:
        throw ioexception;
        exception;
_L2:
        try
        {
            bufferedoutputstream.close();
        }
        catch (Exception exception1) { }
        throw exception;
        exception;
        bufferedoutputstream = bufferedoutputstream1;
        if (true) goto _L2; else goto _L1
_L1:
        ioexception;
        bufferedoutputstream = bufferedoutputstream1;
        if (true) goto _L4; else goto _L3
_L3:
    }

    public static byte[] decodeFromFile(String s)
        throws IOException
    {
        InputStream inputstream = null;
        File file = new File(s);
        int i = 0;
        int j = file.length() != 0x7fffffffL;
        inputstream = null;
        if (j <= 0) goto _L2; else goto _L1
_L1:
        throw new IOException((new StringBuilder()).append("File is too big for this convenience method (").append(file.length()).append(" bytes).").toString());
        IOException ioexception;
        ioexception;
_L8:
        throw ioexception;
        Exception exception;
        exception;
_L6:
        InputStream inputstream1;
        byte abyte0[];
        int k;
        byte abyte1[];
        Exception exception2;
        try
        {
            inputstream.close();
        }
        catch (Exception exception1) { }
        throw exception;
_L2:
        abyte0 = new byte[(int)file.length()];
        inputstream1 = new InputStream(new BufferedInputStream(new FileInputStream(file)), 0);
_L4:
        k = inputstream1.read(abyte0, i, 4096);
        if (k < 0)
        {
            break; /* Loop/switch isn't completed */
        }
        i += k;
        if (true) goto _L4; else goto _L3
_L3:
        abyte1 = new byte[i];
        System.arraycopy(abyte0, 0, abyte1, 0, i);
        try
        {
            inputstream1.close();
        }
        // Misplaced declaration of an exception variable
        catch (Exception exception2)
        {
            return abyte1;
        }
        return abyte1;
        exception;
        inputstream = inputstream1;
        if (true) goto _L6; else goto _L5
_L5:
        ioexception;
        inputstream = inputstream1;
        if (true) goto _L8; else goto _L7
_L7:
    }

    public static void decodeToFile(String s, String s1)
        throws IOException
    {
        OutputStream outputstream = null;
        OutputStream outputstream1 = new OutputStream(new FileOutputStream(s1), 0);
        outputstream1.write(s.getBytes("US-ASCII"));
        IOException ioexception;
        Exception exception;
        try
        {
            outputstream1.close();
            return;
        }
        catch (Exception exception2)
        {
            return;
        }
        ioexception;
_L4:
        throw ioexception;
        exception;
_L2:
        try
        {
            outputstream.close();
        }
        catch (Exception exception1) { }
        throw exception;
        exception;
        outputstream = outputstream1;
        if (true) goto _L2; else goto _L1
_L1:
        ioexception;
        outputstream = outputstream1;
        if (true) goto _L4; else goto _L3
_L3:
    }

    public static Object decodeToObject(String s)
        throws IOException, ClassNotFoundException
    {
        return decodeToObject(s, 0, null);
    }

    public static Object decodeToObject(String s, int i, ClassLoader classloader)
        throws IOException, ClassNotFoundException
    {
        byte abyte0[];
        ByteArrayInputStream bytearrayinputstream;
        Object obj;
        abyte0 = decode(s, i);
        bytearrayinputstream = null;
        obj = null;
        ByteArrayInputStream bytearrayinputstream1 = new ByteArrayInputStream(abyte0);
        if (classloader != null) goto _L2; else goto _L1
_L1:
        obj = new ObjectInputStream(bytearrayinputstream1);
_L3:
        Object obj1 = ((ObjectInputStream) (obj)).readObject();
        IOException ioexception;
        Exception exception;
        ClassNotFoundException classnotfoundexception;
        ObjectInputStream objectinputstream;
        try
        {
            bytearrayinputstream1.close();
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
        objectinputstream = new ObjectInputStream(bytearrayinputstream1, classloader) {

            final ClassLoader val$loader;

            public Class resolveClass(ObjectStreamClass objectstreamclass)
                throws IOException, ClassNotFoundException
            {
                Class class1 = Class.forName(objectstreamclass.getName(), false, loader);
                if (class1 == null)
                {
                    class1 = super.resolveClass(objectstreamclass);
                }
                return class1;
            }

            
            {
                loader = classloader;
                super(inputstream);
            }
        };
        obj = objectinputstream;
          goto _L3
        ioexception;
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
_L5:
        throw classnotfoundexception;
        exception;
        bytearrayinputstream = bytearrayinputstream1;
          goto _L4
        classnotfoundexception;
        bytearrayinputstream = bytearrayinputstream1;
          goto _L5
        ioexception;
        bytearrayinputstream = bytearrayinputstream1;
        if (true) goto _L7; else goto _L6
_L6:
    }

    public static void encode(ByteBuffer bytebuffer, ByteBuffer bytebuffer1)
    {
        byte abyte0[] = new byte[3];
        byte abyte1[] = new byte[4];
        for (; bytebuffer.hasRemaining(); bytebuffer1.put(abyte1))
        {
            int i = Math.min(3, bytebuffer.remaining());
            bytebuffer.get(abyte0, 0, i);
            encode3to4(abyte1, abyte0, i, 0);
        }

    }

    public static void encode(ByteBuffer bytebuffer, CharBuffer charbuffer)
    {
        byte abyte0[] = new byte[3];
        byte abyte1[] = new byte[4];
        while (bytebuffer.hasRemaining()) 
        {
            int i = Math.min(3, bytebuffer.remaining());
            bytebuffer.get(abyte0, 0, i);
            encode3to4(abyte1, abyte0, i, 0);
            int j = 0;
            while (j < 4) 
            {
                charbuffer.put((char)(0xff & abyte1[j]));
                j++;
            }
        }
    }

    private static byte[] encode3to4(byte abyte0[], int i, int j, byte abyte1[], int k, int l)
    {
        byte abyte2[] = getAlphabet(l);
        int i1;
        int j1;
        int k1;
        int l1;
        int i2;
        if (j > 0)
        {
            i1 = (abyte0[i] << 24) >>> 8;
        } else
        {
            i1 = 0;
        }
        if (j > 1)
        {
            j1 = (abyte0[i + 1] << 24) >>> 16;
        } else
        {
            j1 = 0;
        }
        k1 = j1 | i1;
        l1 = 0;
        if (j > 2)
        {
            l1 = (abyte0[i + 2] << 24) >>> 24;
        }
        i2 = k1 | l1;
        switch (j)
        {
        default:
            return abyte1;

        case 3: // '\003'
            abyte1[k] = abyte2[i2 >>> 18];
            abyte1[k + 1] = abyte2[0x3f & i2 >>> 12];
            abyte1[k + 2] = abyte2[0x3f & i2 >>> 6];
            abyte1[k + 3] = abyte2[i2 & 0x3f];
            return abyte1;

        case 2: // '\002'
            abyte1[k] = abyte2[i2 >>> 18];
            abyte1[k + 1] = abyte2[0x3f & i2 >>> 12];
            abyte1[k + 2] = abyte2[0x3f & i2 >>> 6];
            abyte1[k + 3] = 61;
            return abyte1;

        case 1: // '\001'
            abyte1[k] = abyte2[i2 >>> 18];
            abyte1[k + 1] = abyte2[0x3f & i2 >>> 12];
            abyte1[k + 2] = 61;
            abyte1[k + 3] = 61;
            return abyte1;
        }
    }

    private static byte[] encode3to4(byte abyte0[], byte abyte1[], int i, int j)
    {
        encode3to4(abyte1, 0, i, abyte0, 0, j);
        return abyte0;
    }

    public static String encodeBytes(byte abyte0[])
    {
        String s1 = encodeBytes(abyte0, 0, abyte0.length, 0);
        String s = s1;
_L1:
        IOException ioexception;
        if (!$assertionsDisabled && s == null)
        {
            throw new AssertionError();
        } else
        {
            return s;
        }
        ioexception;
        boolean flag = $assertionsDisabled;
        s = null;
        if (!flag)
        {
            throw new AssertionError(ioexception.getMessage());
        }
          goto _L1
    }

    public static String encodeBytes(byte abyte0[], int i)
        throws IOException
    {
        return encodeBytes(abyte0, 0, abyte0.length, i);
    }

    public static String encodeBytes(byte abyte0[], int i, int j)
    {
        String s1 = encodeBytes(abyte0, i, j, 0);
        String s = s1;
_L1:
        IOException ioexception;
        if (!$assertionsDisabled && s == null)
        {
            throw new AssertionError();
        } else
        {
            return s;
        }
        ioexception;
        boolean flag = $assertionsDisabled;
        s = null;
        if (!flag)
        {
            throw new AssertionError(ioexception.getMessage());
        }
          goto _L1
    }

    public static String encodeBytes(byte abyte0[], int i, int j, int k)
        throws IOException
    {
        byte abyte1[] = encodeBytesToBytes(abyte0, i, j, k);
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

    public static byte[] encodeBytesToBytes(byte abyte0[])
    {
        byte abyte2[] = encodeBytesToBytes(abyte0, 0, abyte0.length, 0);
        byte abyte1[] = abyte2;
_L2:
        return abyte1;
        IOException ioexception;
        ioexception;
        boolean flag = $assertionsDisabled;
        abyte1 = null;
        if (!flag)
        {
            throw new AssertionError((new StringBuilder()).append("IOExceptions only come from GZipping, which is turned off: ").append(ioexception.getMessage()).toString());
        }
        if (true) goto _L2; else goto _L1
_L1:
    }

    public static byte[] encodeBytesToBytes(byte abyte0[], int i, int j, int k)
        throws IOException
    {
        if (abyte0 == null)
        {
            throw new NullPointerException("Cannot serialize a null array.");
        }
        if (i < 0)
        {
            throw new IllegalArgumentException((new StringBuilder()).append("Cannot have negative offset: ").append(i).toString());
        }
        if (j < 0)
        {
            throw new IllegalArgumentException((new StringBuilder()).append("Cannot have length offset: ").append(j).toString());
        }
        if (i + j > abyte0.length)
        {
            Object aobj[] = new Object[3];
            aobj[0] = Integer.valueOf(i);
            aobj[1] = Integer.valueOf(j);
            aobj[2] = Integer.valueOf(abyte0.length);
            throw new IllegalArgumentException(String.format("Cannot have offset of %d and length of %d with array of length %d", aobj));
        }
        if ((k & 2) == 0) goto _L2; else goto _L1
_L1:
        ByteArrayOutputStream bytearrayoutputstream;
        GZIPOutputStream gzipoutputstream;
        OutputStream outputstream;
        bytearrayoutputstream = null;
        gzipoutputstream = null;
        outputstream = null;
        ByteArrayOutputStream bytearrayoutputstream1 = new ByteArrayOutputStream();
        OutputStream outputstream1;
        GZIPOutputStream gzipoutputstream1;
        IOException ioexception;
        boolean flag;
        int l;
        byte byte0;
        int i1;
        byte abyte1[];
        int j1;
        int k1;
        int l1;
        int i2;
        byte abyte2[];
        Exception exception;
        Exception exception1;
        Exception exception2;
        Exception exception3;
        Exception exception4;
        Exception exception5;
        Exception exception6;
        try
        {
            outputstream1 = new OutputStream(bytearrayoutputstream1, k | 1);
        }
        // Misplaced declaration of an exception variable
        catch (IOException ioexception)
        {
            bytearrayoutputstream = bytearrayoutputstream1;
            outputstream = null;
            gzipoutputstream = null;
            continue; /* Loop/switch isn't completed */
        }
        finally
        {
            bytearrayoutputstream = bytearrayoutputstream1;
            outputstream = null;
            gzipoutputstream = null;
            continue; /* Loop/switch isn't completed */
        }
        try
        {
_L3:
            gzipoutputstream1 = new GZIPOutputStream(outputstream1);
        }
        // Misplaced declaration of an exception variable
        catch (IOException ioexception)
        {
            outputstream = outputstream1;
            bytearrayoutputstream = bytearrayoutputstream1;
            gzipoutputstream = null;
            continue; /* Loop/switch isn't completed */
        }
        finally
        {
            outputstream = outputstream1;
            bytearrayoutputstream = bytearrayoutputstream1;
            gzipoutputstream = null;
            continue; /* Loop/switch isn't completed */
        }
        gzipoutputstream1.write(abyte0, i, j);
        gzipoutputstream1.close();
        try
        {
            gzipoutputstream1.close();
        }
        // Misplaced declaration of an exception variable
        catch (Exception exception4) { }
        try
        {
            outputstream1.close();
        }
        // Misplaced declaration of an exception variable
        catch (Exception exception5) { }
        try
        {
            bytearrayoutputstream1.close();
        }
        // Misplaced declaration of an exception variable
        catch (Exception exception6) { }
        return bytearrayoutputstream1.toByteArray();
        ioexception;
_L6:
        throw ioexception;
        exception;
_L4:
        try
        {
            gzipoutputstream.close();
        }
        // Misplaced declaration of an exception variable
        catch (Exception exception1) { }
        try
        {
            outputstream.close();
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
        if ((k & 8) != 0)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        l = 4 * (j / 3);
        if (j % 3 > 0)
        {
            byte0 = 4;
        } else
        {
            byte0 = 0;
        }
        i1 = l + byte0;
        if (flag)
        {
            i1 += i1 / 76;
        }
        abyte1 = new byte[i1];
        j1 = 0;
        k1 = 0;
        l1 = j - 2;
        i2 = 0;
        while (j1 < l1) 
        {
            encode3to4(abyte0, j1 + i, 3, abyte1, k1, k);
            i2 += 4;
            if (flag && i2 >= 76)
            {
                abyte1[k1 + 4] = 10;
                k1++;
                i2 = 0;
            }
            j1 += 3;
            k1 += 4;
        }
        if (j1 < j)
        {
            encode3to4(abyte0, j1 + i, j - j1, abyte1, k1, k);
            k1 += 4;
        }
        if (k1 <= -1 + abyte1.length)
        {
            abyte2 = new byte[k1];
            System.arraycopy(abyte1, 0, abyte2, 0, k1);
            return abyte2;
        } else
        {
            return abyte1;
        }
        exception;
        outputstream = outputstream1;
        gzipoutputstream = gzipoutputstream1;
        bytearrayoutputstream = bytearrayoutputstream1;
        if (true) goto _L4; else goto _L3
        ioexception;
        outputstream = outputstream1;
        gzipoutputstream = gzipoutputstream1;
        bytearrayoutputstream = bytearrayoutputstream1;
        if (true) goto _L6; else goto _L5
_L5:
    }

    public static void encodeFileToFile(String s, String s1)
        throws IOException
    {
        String s2;
        BufferedOutputStream bufferedoutputstream;
        s2 = encodeFromFile(s);
        bufferedoutputstream = null;
        BufferedOutputStream bufferedoutputstream1 = new BufferedOutputStream(new FileOutputStream(s1));
        bufferedoutputstream1.write(s2.getBytes("US-ASCII"));
        IOException ioexception;
        Exception exception;
        try
        {
            bufferedoutputstream1.close();
            return;
        }
        catch (Exception exception2)
        {
            return;
        }
        ioexception;
_L4:
        throw ioexception;
        exception;
_L2:
        try
        {
            bufferedoutputstream.close();
        }
        catch (Exception exception1) { }
        throw exception;
        exception;
        bufferedoutputstream = bufferedoutputstream1;
        if (true) goto _L2; else goto _L1
_L1:
        ioexception;
        bufferedoutputstream = bufferedoutputstream1;
        if (true) goto _L4; else goto _L3
_L3:
    }

    public static String encodeFromFile(String s)
        throws IOException
    {
        InputStream inputstream = null;
        File file;
        byte abyte0[];
        file = new File(s);
        abyte0 = new byte[Math.max((int)(1.0D + 1.3999999999999999D * (double)file.length()), 40)];
        int i = 0;
        InputStream inputstream1 = new InputStream(new BufferedInputStream(new FileInputStream(file)), 1);
_L2:
        int j = inputstream1.read(abyte0, i, 4096);
        if (j < 0)
        {
            break; /* Loop/switch isn't completed */
        }
        i += j;
        if (true) goto _L2; else goto _L1
_L1:
        String s1 = new String(abyte0, 0, i, "US-ASCII");
        Exception exception;
        IOException ioexception;
        try
        {
            inputstream1.close();
        }
        catch (Exception exception2)
        {
            return s1;
        }
        return s1;
        ioexception;
_L6:
        throw ioexception;
        exception;
_L4:
        try
        {
            inputstream.close();
        }
        catch (Exception exception1) { }
        throw exception;
        exception;
        inputstream = inputstream1;
        if (true) goto _L4; else goto _L3
_L3:
        ioexception;
        inputstream = inputstream1;
        if (true) goto _L6; else goto _L5
_L5:
    }

    public static String encodeObject(Serializable serializable)
        throws IOException
    {
        return encodeObject(serializable, 0);
    }

    public static String encodeObject(Serializable serializable, int i)
        throws IOException
    {
        ByteArrayOutputStream bytearrayoutputstream;
        OutputStream outputstream;
        GZIPOutputStream gzipoutputstream;
        ObjectOutputStream objectoutputstream;
        if (serializable == null)
        {
            throw new NullPointerException("Cannot serialize a null object.");
        }
        bytearrayoutputstream = null;
        outputstream = null;
        gzipoutputstream = null;
        objectoutputstream = null;
        ByteArrayOutputStream bytearrayoutputstream1 = new ByteArrayOutputStream();
        OutputStream outputstream1;
        IOException ioexception;
        Exception exception;
        GZIPOutputStream gzipoutputstream1;
        ObjectOutputStream objectoutputstream2;
        try
        {
            outputstream1 = new OutputStream(bytearrayoutputstream1, i | 1);
        }
        // Misplaced declaration of an exception variable
        catch (IOException ioexception)
        {
            bytearrayoutputstream = bytearrayoutputstream1;
            outputstream = null;
            gzipoutputstream = null;
            objectoutputstream = null;
            continue; /* Loop/switch isn't completed */
        }
        finally
        {
            bytearrayoutputstream = bytearrayoutputstream1;
            outputstream = null;
            gzipoutputstream = null;
            objectoutputstream = null;
            continue; /* Loop/switch isn't completed */
        }
        if ((i & 2) == 0) goto _L2; else goto _L1
_L1:
        try
        {
            gzipoutputstream1 = new GZIPOutputStream(outputstream1);
        }
        // Misplaced declaration of an exception variable
        catch (IOException ioexception)
        {
            outputstream = outputstream1;
            bytearrayoutputstream = bytearrayoutputstream1;
            continue; /* Loop/switch isn't completed */
        }
        finally
        {
            outputstream = outputstream1;
            bytearrayoutputstream = bytearrayoutputstream1;
            continue; /* Loop/switch isn't completed */
        }
        objectoutputstream2 = new ObjectOutputStream(gzipoutputstream1);
        objectoutputstream = objectoutputstream2;
        gzipoutputstream = gzipoutputstream1;
_L4:
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
            outputstream1.close();
        }
        catch (Exception exception7) { }
        try
        {
            bytearrayoutputstream1.close();
        }
        catch (Exception exception8) { }
        try
        {
            s = new String(bytearrayoutputstream1.toByteArray(), "US-ASCII");
        }
        catch (UnsupportedEncodingException unsupportedencodingexception)
        {
            return new String(bytearrayoutputstream1.toByteArray());
        }
        return s;
_L2:
        objectoutputstream1 = new ObjectOutputStream(outputstream1);
        objectoutputstream = objectoutputstream1;
        gzipoutputstream = null;
        if (true) goto _L4; else goto _L3
_L3:
        ioexception;
_L8:
        throw ioexception;
        exception;
_L6:
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
            outputstream.close();
        }
        catch (Exception exception3) { }
        try
        {
            bytearrayoutputstream.close();
        }
        catch (Exception exception4) { }
        throw exception;
        exception;
        gzipoutputstream = gzipoutputstream1;
        outputstream = outputstream1;
        bytearrayoutputstream = bytearrayoutputstream1;
        objectoutputstream = null;
        if (true) goto _L6; else goto _L5
_L5:
        break MISSING_BLOCK_LABEL_48;
        ioexception;
        gzipoutputstream = gzipoutputstream1;
        outputstream = outputstream1;
        bytearrayoutputstream = bytearrayoutputstream1;
        objectoutputstream = null;
        if (true) goto _L8; else goto _L7
_L7:
    }

    public static void encodeToFile(byte abyte0[], String s)
        throws IOException
    {
        OutputStream outputstream;
        if (abyte0 == null)
        {
            throw new NullPointerException("Data to encode was null.");
        }
        outputstream = null;
        OutputStream outputstream1 = new OutputStream(new FileOutputStream(s), 1);
        outputstream1.write(abyte0);
        IOException ioexception;
        Exception exception;
        try
        {
            outputstream1.close();
            return;
        }
        catch (Exception exception2)
        {
            return;
        }
        ioexception;
_L4:
        throw ioexception;
        exception;
_L2:
        try
        {
            outputstream.close();
        }
        catch (Exception exception1) { }
        throw exception;
        exception;
        outputstream = outputstream1;
        if (true) goto _L2; else goto _L1
_L1:
        ioexception;
        outputstream = outputstream1;
        if (true) goto _L4; else goto _L3
_L3:
    }

    private static final byte[] getAlphabet(int i)
    {
        if ((i & 0x10) == 16)
        {
            return _URL_SAFE_ALPHABET;
        }
        if ((i & 0x20) == 32)
        {
            return _ORDERED_ALPHABET;
        } else
        {
            return _STANDARD_ALPHABET;
        }
    }

    private static final byte[] getDecodabet(int i)
    {
        if ((i & 0x10) == 16)
        {
            return _URL_SAFE_DECODABET;
        }
        if ((i & 0x20) == 32)
        {
            return _ORDERED_DECODABET;
        } else
        {
            return _STANDARD_DECODABET;
        }
    }

    static 
    {
        boolean flag;
        if (!jp/co/yahoo/android/common/Base64.desiredAssertionStatus())
        {
            flag = true;
        } else
        {
            flag = false;
        }
        $assertionsDisabled = flag;
    }




}

// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.ads;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.util.Log;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.KeyFactory;
import java.security.MessageDigest;
import java.security.spec.X509EncodedKeySpec;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.crypto.Cipher;

// Referenced classes of package jp.co.yahoo.android.ads:
//            b, e, j

public final class h
{

    public static String a(long l)
    {
        String s;
        try
        {
            s = b.a.format(Long.valueOf(l));
        }
        catch (Exception exception)
        {
            a(3, (new StringBuilder()).append("formatLocationDate failed (").append(l).append("): ").append(exception.getClass().getName()).append(":").append(exception.getMessage()).toString());
            return "";
        }
        return s;
    }

    public static String a(String s, long l)
    {
        if (s == null)
        {
            return null;
        }
        String s1;
        try
        {
            Object aobj[] = new Object[1];
            aobj[0] = a(l);
            s1 = MessageFormat.format(s, aobj);
            a(3, (new StringBuilder()).append("[ALUtil] formatLocationData: ").append(s1).toString());
        }
        catch (Exception exception)
        {
            a(3, (new StringBuilder()).append("[ALUtil] formatLocationData failed (").append(s).append("): ").append(exception.getClass().getName()).append(":").append(exception.getMessage()).toString());
            return null;
        }
        return s1;
    }

    public static String a(String s, String s1)
    {
        Matcher matcher = Pattern.compile(s).matcher(s1);
        if (matcher.find())
        {
            return matcher.group(1);
        } else
        {
            return "-1";
        }
    }

    public static String a(String s, String s1, String s2)
    {
        String s3;
        s3 = null;
        if (s == null)
        {
            break MISSING_BLOCK_LABEL_73;
        }
        int i = s.length();
        s3 = null;
        if (i <= 0)
        {
            break MISSING_BLOCK_LABEL_73;
        }
        String s4;
        MessageDigest messagedigest = MessageDigest.getInstance(s1);
        messagedigest.update(s.getBytes(), 0, s.length());
        Object aobj[] = new Object[1];
        aobj[0] = new BigInteger(1, messagedigest.digest());
        s4 = String.format(s2, aobj);
        s3 = s4;
_L2:
        a(3, (new StringBuilder()).append("create hash : ").append(s3).toString());
        return s3;
        Exception exception;
        exception;
        a(5, (new StringBuilder()).append("Could not generate hash of ").append(s).toString(), ((Throwable) (exception)));
        s3 = null;
        if (true) goto _L2; else goto _L1
_L1:
    }

    public static void a(int i, String s)
    {
        a(i, s, ((Throwable) (null)));
    }

    public static void a(int i, String s, Throwable throwable)
    {
label0:
        {
label1:
            {
label2:
                {
label3:
                    {
label4:
                        {
                            if (i >= 4 || jp.co.yahoo.android.ads.e.j())
                            {
                                switch (i)
                                {
                                default:
                                    Log.e("YJAdsSDK", (new StringBuilder()).append("Not valid prameter kind : ").append(i).toString());
                                    break;

                                case 2: // '\002'
                                    break label4;

                                case 3: // '\003'
                                    break label3;

                                case 4: // '\004'
                                    break label2;

                                case 5: // '\005'
                                    break label1;

                                case 6: // '\006'
                                    break label0;
                                }
                            }
                            return;
                        }
                        Log.v("YJAdsSDK", s, throwable);
                        return;
                    }
                    Log.d("YJAdsSDK", s, throwable);
                    return;
                }
                Log.i("YJAdsSDK", s, throwable);
                return;
            }
            Log.w("YJAdsSDK", s, throwable);
            return;
        }
        Log.e("YJAdsSDK", s, throwable);
    }

    public static void a(Runnable runnable)
    {
        a(runnable, 0L);
    }

    public static void a(Runnable runnable, long l)
    {
        (new Thread(l, new Handler(), runnable) {

            final long a;
            final Handler b;
            final Runnable c;

            public void run()
            {
                if (a == 0L)
                {
                    b.post(c);
                    return;
                }
                try
                {
                    b.postDelayed(c, a);
                    return;
                }
                catch (Exception exception)
                {
                    h.a(6, "Unhandled exception requesting a fresh ad.", exception);
                }
                return;
            }

            
            {
                a = l;
                b = handler;
                c = runnable;
                super();
            }
        }).start();
    }

    public static void a(String s)
    {
        a(6, s);
        throw new IllegalArgumentException(s);
    }

    public static Bitmap b(String s)
    {
        byte abyte1[] = j.a(s);
        byte abyte0[] = abyte1;
_L2:
        return BitmapFactory.decodeByteArray(abyte0, 0, abyte0.length);
        IOException ioexception;
        ioexception;
        ioexception.printStackTrace();
        abyte0 = null;
        if (true) goto _L2; else goto _L1
_L1:
    }

    public static String b(String s, String s1)
    {
        String s2 = "";
        String s4;
        s2 = URLEncoder.encode(s, s1);
        s4 = s2.replace("+", "%2B");
        String s3 = s4;
        a(3, (new StringBuilder()).append("Encode URL \uFF1A ").append(s3).toString());
        return s3;
        UnsupportedEncodingException unsupportedencodingexception;
        unsupportedencodingexception;
        UnsupportedEncodingException unsupportedencodingexception1;
        s3 = s2;
        unsupportedencodingexception1 = unsupportedencodingexception;
_L2:
        a(5, (new StringBuilder()).append("Bat Encode URL \uFF1A ").append(s).append(unsupportedencodingexception1).toString());
        return s3;
        unsupportedencodingexception1;
        if (true) goto _L2; else goto _L1
_L1:
    }

    public static String c(String s)
    {
        String s1 = "";
        String s2 = URLDecoder.decode(s, "UTF-8");
        a(3, (new StringBuilder()).append("adcsc decode UTF-8 : ").append(s2).toString());
        String s3 = a("E=(.*?)/", s2);
        String s4 = a("C=(.*?)/", s2);
        String s5 = a("D=(.*?)/", s2);
        String s6 = a("B=([0-9]+)/?", s2);
        if ("-1".equals(s3) || "-1".equals(s4) || "-1".equals(s5) || "-1".equals(s6))
        {
            break MISSING_BLOCK_LABEL_186;
        }
        s1 = (new StringBuilder()).append("A=").append(s6).append("&M=").append(s4).append("&L=").append(s5).append("&S=").append(s3).toString();
        a(3, (new StringBuilder()).append("create rs log : ").append(s1).toString());
        return s1;
        try
        {
            a(3, "bat create rs log");
        }
        catch (UnsupportedEncodingException unsupportedencodingexception)
        {
            a(6, (new StringBuilder()).append("Bat adcsc decode UTF-8.").append(unsupportedencodingexception).toString());
            return s1;
        }
        return s1;
    }

    public static boolean d(String s)
    {
        boolean flag;
label0:
        {
            if (!"".equals(s))
            {
                flag = false;
                if (s != null)
                {
                    break label0;
                }
            }
            flag = true;
        }
        return flag;
    }

    public static int e(String s)
    {
        if (s == null)
        {
            return -1;
        }
        int i;
        try
        {
            i = Integer.parseInt(s);
        }
        catch (NumberFormatException numberformatexception)
        {
            a(3, (new StringBuilder()).append("parseInt failed: ").append(s).toString());
            return 0;
        }
        return i;
    }

    public static String f(String s)
    {
        if (s == null)
        {
            return null;
        }
        String s1;
        try
        {
            X509EncodedKeySpec x509encodedkeyspec = new X509EncodedKeySpec(j.a("MIICIjANBgkqhkiG9w0BAQEFAAOCAg8AMIICCgKCAgEA2rPQDcOcXXa79WsAUzCoLgbvZ4wLpyBYXJ/YpCRTJq+yRqhdOcstNqHkyKkv6jfcTHNZDWffKmvdGHGqdhwDdw7PJ36P44+hiPrhmWIh62LvGOXOu7s52KyMnIKMzRXb5Qg5htUYx99lWWtoTXpxZM3Ow2DzVj/0JaeFitiJKUu+xv0+caocqKAXYHp0Licp6/MOYGzZ7RseoF8j3gwc9cEZilRVRILgH7RfrLukQ/krYRnHRbGbxmmeR5gsq6qoeQgpHDg8UQebdgxVMhUdq57awEsjz55S8qYiC7KSZ93YCBQoNOidthGZuwoeUVBB1Lgi+K24e1FpLXQXkpXzUtB3Jv/PttbZm3BzvU4mqEF7H0jvUfn8qFejYKdT7FbCcW6burqP5PKaEu9O9E8ktgUokG2HvyQh4++1gkB62uQ2HnkAnHUmAhVk1gPu+OYQB6fCqKIkOaGtv9POXBcfjKwCDVZkDoJKOsiytlDaHVNFit0iaX3RLdarFckk/tU7DrK8XVT3Oq704PVItAUoz0B0NNCOUQYH6vE6x6A8M6J0qT80U+5XXl68HdIabWz+1uKLcE/5/Mghb68jsuiOBnkoAF+hcZqaJrwvcIMnu8rCvHUo91FvIMPxKyxddlcSWGZIgRzvnlHR882d/1EEGDPBs5etjhILr9cu7UIGgQsCAwEAAQ=="));
            java.security.PublicKey publickey = KeyFactory.getInstance("RSA").generatePublic(x509encodedkeyspec);
            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            cipher.init(1, publickey);
            s1 = j.a(cipher.doFinal(s.getBytes("UTF-8")));
        }
        catch (Exception exception)
        {
            a(3, (new StringBuilder()).append("[ALUtil] locationEncrypt failed: ").append(exception.getClass().getName()).append(":").append(exception.getMessage()).toString());
            return null;
        }
        return s1;
    }

    public static String g(String s)
    {
        if (!d(s))
        {
            String s1 = b(s, "UTF-8");
            if (!d(s) && !d(s1) && s1.length() <= 1000 && (s.startsWith("http://") || s.startsWith("https://")))
            {
                return s1;
            }
        }
        return null;
    }
}

// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.ads;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.net.ssl.HttpsURLConnection;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;

// Referenced classes of package jp.co.yahoo.android.ads:
//            g, h, e, d, 
//            j

public class f
{

    public static String a(Context context, String s, String s1, String s2, boolean flag, String s3, String s4, String s5, 
            String s6)
        throws g
    {
        if (context.checkCallingOrSelfPermission("android.permission.INTERNET") == -1)
        {
            h.a("Cannot request an ad without Internet permissions!  Open manifest.xml and just before the final </manifest> tag add:  <uses-permission android:name=\"android.permission.INTERNET\" />");
        }
        new StringBuilder();
        long l = System.currentTimeMillis();
        HashMap hashmap = new HashMap();
        hashmap.put("appid", "TjpGqlOkzaII.ZpcXi2Kqltt5SA9uNVnbcaQ_KhLQf10");
        hashmap.put("appli_id", s);
        hashmap.put("apos", s1);
        if (!h.d(s2))
        {
            hashmap.put("f", s2);
        }
        boolean flag1 = false;
        if (s4 != null)
        {
            if (hashmap.containsKey("appid"))
            {
                hashmap.remove("appid");
            }
            if (hashmap.containsKey("wssid"))
            {
                hashmap.remove("wssid");
            }
            flag1 = true;
        }
        if (s5 != null)
        {
            hashmap.put("query", s5);
        }
        if (s6 != null)
        {
            hashmap.put("ei", s6);
        }
        e.m();
        String s7 = a(((Map) (hashmap)), flag1);
        if ("".equals(s7))
        {
            return "";
        }
        HashMap hashmap1 = new HashMap();
        hashmap1.put("Host", "aag.yahooapis.jp");
        hashmap1.put("X-Ysma-Appname", e.b(context));
        hashmap1.put("X-Ysma-Lang", e.g());
        hashmap1.put("X-Ysma-Country", e.h());
        String s8 = d.c(context);
        if (s8 != null)
        {
            hashmap1.put("X-Ysma-G", h.f(s8));
        }
        String s9 = h.g(s3);
        if (s9 != null)
        {
            hashmap1.put("X-Ysma-CrawlUrl", s9);
        }
        String s10 = e.c(context);
        if (!h.d(s10))
        {
            hashmap1.put("X-Ysma-Hc", s10);
        }
        if (s4 != null)
        {
            hashmap1.put("Authorization", (new StringBuilder()).append("Bearer ").append(s4).toString());
        }
        com.google.android.gms.ads.identifier.AdvertisingIdClient.Info info = e.d(context);
        if (info != null)
        {
            hashmap1.put("X-Ysma-Idfa", info.getId());
            hashmap1.put("X-Ysma-Optout", String.valueOf(info.isLimitAdTrackingEnabled()));
        }
        StringBuilder stringbuilder = a(context, s7, ((Map) (hashmap1)), s2, flag, s4);
        long l1 = System.currentTimeMillis() - l;
        String s11;
        if (stringbuilder.length() == 0)
        {
            s11 = (new StringBuilder()).append("Server replied that no ads response are available (").append(l1).append("ms)").toString();
        } else
        {
            s11 = (new StringBuilder()).append("Ad response returned in ").append(l1).append("ms:  ").append(stringbuilder.toString()).toString();
        }
        h.a(3, s11);
        return stringbuilder.toString();
    }

    private static String a(String s)
    {
        String s2;
        SecretKeySpec secretkeyspec = new SecretKeySpec("BMWbGjIvOfqn1LT4eWZoroDpPiLN.o6AqoxFEDWb".getBytes("UTF-8"), "HmacSHA256");
        Mac mac = Mac.getInstance("HmacSHA256");
        mac.init(secretkeyspec);
        s2 = j.a(mac.doFinal(s.getBytes("UTF-8")));
        String s1 = s2;
        h.a(3, (new StringBuilder()).append("signature = ").append(s1).toString());
        return s1;
        UnsupportedEncodingException unsupportedencodingexception;
        unsupportedencodingexception;
        UnsupportedEncodingException unsupportedencodingexception1;
        s1 = "";
        unsupportedencodingexception1 = unsupportedencodingexception;
_L6:
        h.a(5, (new StringBuilder()).append("un support encoding : UTF-8").append(unsupportedencodingexception1).toString());
        return s1;
        NoSuchAlgorithmException nosuchalgorithmexception;
        nosuchalgorithmexception;
        NoSuchAlgorithmException nosuchalgorithmexception1;
        s1 = "";
        nosuchalgorithmexception1 = nosuchalgorithmexception;
_L4:
        h.a(5, (new StringBuilder()).append("indicates that a requested algorithm could not be found : ").append(nosuchalgorithmexception1).toString());
        return s1;
        InvalidKeyException invalidkeyexception;
        invalidkeyexception;
        InvalidKeyException invalidkeyexception1;
        s1 = "";
        invalidkeyexception1 = invalidkeyexception;
_L2:
        h.a(5, (new StringBuilder()).append("invalid key. ").append(invalidkeyexception1).toString());
        return s1;
        invalidkeyexception1;
        if (true) goto _L2; else goto _L1
_L1:
        nosuchalgorithmexception1;
        if (true) goto _L4; else goto _L3
_L3:
        unsupportedencodingexception1;
        if (true) goto _L6; else goto _L5
_L5:
    }

    private static String a(Map map, boolean flag)
    {
        StringBuilder stringbuilder;
        String s2;
        stringbuilder = new StringBuilder();
        String s = e.k();
        String s1;
        if (s == null || s.equals("beta"))
        {
            s1 = "http://aagtest.yahoo.co.jp/v1/ads/";
        } else
        if (s.equals("alpha"))
        {
            s1 = "http://aagfrontalpha01.ads.ssk.ynwm.yahoo.co.jp/v1/ads/";
        } else
        if (s.equals("yashima"))
        {
            s1 = "http://yashima01-jail10.sej.miniy.yahoo.co.jp/v1/ads/";
        } else
        {
            boolean flag1 = s.equals("toyoshima");
            s1 = null;
            if (flag1)
            {
                s1 = "http://toyoshima.rat.ogk.ynwm.yahoo.co.jp/v1/ads/";
            }
        }
        if (!e.j())
        {
            s1 = "http://aag.yahooapis.jp/v1/ads/";
        }
        if (flag)
        {
            s2 = (new StringBuilder()).append(s1).append("elookup").toString();
        } else
        {
            s2 = (new StringBuilder()).append(s1).append("lookup").toString();
        }
        try
        {
            java.util.Map.Entry entry;
            for (Iterator iterator = map.entrySet().iterator(); iterator.hasNext(); stringbuilder.append((String)entry.getKey()).append("=").append(URLEncoder.encode((String)entry.getValue(), "UTF-8")).append("&"))
            {
                entry = (java.util.Map.Entry)iterator.next();
            }

        }
        catch (UnsupportedEncodingException unsupportedencodingexception)
        {
            h.a(5, (new StringBuilder()).append("Unsupported Encoding : UTF-8").append(unsupportedencodingexception).toString());
            return "";
        }
        String s3 = stringbuilder.toString().substring(0, stringbuilder.toString().lastIndexOf("&"));
        h.a(3, (new StringBuilder()).append("parameter string : ").append(s3).toString());
        stringbuilder.append("appsig=").append(URLEncoder.encode(a(s3), "UTF-8"));
        return (new StringBuilder()).append(s2).append("?").append(stringbuilder.toString()).toString();
    }

    private static StringBuilder a(Context context, String s, Map map)
        throws g
    {
        return a(context, s, map, null, false, null);
    }

    private static StringBuilder a(Context context, String s, Map map, String s1, boolean flag, String s2)
        throws g
    {
        StringBuilder stringbuilder;
        BufferedReader bufferedreader;
        stringbuilder = new StringBuilder();
        bufferedreader = null;
        if (s2 != null)
        {
            s = s.replaceFirst("http", "https");
        }
        if (android.os.Build.VERSION.SDK_INT > 8) goto _L2; else goto _L1
_L1:
        DefaultHttpClient defaulthttpclient;
        HttpGet httpget;
        h.a(3, "HTTP Request with : DefaultHttpClient");
        defaulthttpclient = new DefaultHttpClient();
        org.apache.http.params.HttpParams httpparams = defaulthttpclient.getParams();
        httpget = new HttpGet(s);
        h.a(3, (new StringBuilder()).append("Requesting Ad Url = ").append(s).toString());
        HttpConnectionParams.setConnectionTimeout(httpparams, 20000);
        HttpConnectionParams.setSoTimeout(httpparams, 20000);
        httpget.setHeader("User-Agent", e.a());
        h.a(3, (new StringBuilder()).append("Set http header. User-Agent : ").append(e.a()).toString());
        java.util.Map.Entry entry1;
        for (Iterator iterator1 = map.entrySet().iterator(); iterator1.hasNext(); h.a(3, (new StringBuilder()).append("Set http header. ").append((String)entry1.getKey()).append(" : ").append((String)entry1.getValue()).toString()))
        {
            entry1 = (java.util.Map.Entry)iterator1.next();
            httpget.setHeader((String)entry1.getKey(), (String)entry1.getValue());
        }

        HttpResponse httpresponse;
        int l;
        httpresponse = defaulthttpclient.execute(httpget);
        l = httpresponse.getStatusLine().getStatusCode();
        if (l == 200)
        {
            break MISSING_BLOCK_LABEL_325;
        }
        if (httpget == null)
        {
            break MISSING_BLOCK_LABEL_302;
        }
        httpget.abort();
        if (false)
        {
            try
            {
                null.close();
            }
            catch (IOException ioexception7)
            {
                h.a(5, "Reander bat close");
            }
        }
        return stringbuilder;
        HashMap hashmap;
        Header aheader[];
        int i1;
        h.a(3, (new StringBuilder()).append("adHttpRequest response Code: ").append(httpresponse.getStatusLine().getStatusCode()).append(",").append("message: ").append(httpresponse.getStatusLine().getReasonPhrase()).toString());
        hashmap = new HashMap();
        aheader = httpresponse.getAllHeaders();
        i1 = aheader.length;
        int j1 = 0;
_L4:
        bufferedreader = null;
        if (j1 >= i1)
        {
            break; /* Loop/switch isn't completed */
        }
        Header header = aheader[j1];
        hashmap.put(header.getName(), header.getValue());
        j1++;
        if (true) goto _L4; else goto _L3
_L3:
        String s7 = (String)hashmap.get("X-Ysma-Bc2");
        bufferedreader = null;
        if (s7 == null)
        {
            break MISSING_BLOCK_LABEL_517;
        }
        e.b(s7);
        b(context, s7);
        h.a(3, (new StringBuilder()).append("Set Bcookie for userid = ").append(s7).toString());
        String s8;
        String s9;
        s8 = (String)hashmap.get("X-Ysma-Yc");
        s9 = (String)hashmap.get("X-Ysma-Tc");
        if (s8 == null)
        {
            break MISSING_BLOCK_LABEL_603;
        }
        bufferedreader = null;
        if (s9 == null)
        {
            break MISSING_BLOCK_LABEL_603;
        }
        e.a(s8, s9);
        h.a(3, (new StringBuilder()).append("Set ycookie = ").append(s8).append("; tcookie = ").append(s9).toString());
        boolean flag3 = h.d((String)map.get("X-Ysma-G"));
        bufferedreader = null;
        if (flag3)
        {
            break MISSING_BLOCK_LABEL_631;
        }
        d.a(context);
        int k1;
        k1 = h.e((String)hashmap.get("X-Ysma-GS"));
        h.a(3, (new StringBuilder()).append("Location flag: AAG GpsMode=").append(k1).append(", app locatoinmode=").append(flag).toString());
        if (k1 != 1 || !flag) goto _L6; else goto _L5
_L5:
        if (context.checkCallingOrSelfPermission("android.permission.ACCESS_FINE_LOCATION") != 0 && context.checkCallingOrSelfPermission("android.permission.ACCESS_COARSE_LOCATION") != 0) goto _L8; else goto _L7
_L7:
        d.a(context, s1);
_L6:
        BufferedReader bufferedreader2 = new BufferedReader(new InputStreamReader(httpresponse.getEntity().getContent()));
        h.a(3, (new StringBuilder()).append("reader.ready: ").append(bufferedreader2.ready()).toString());
_L11:
        String s10 = bufferedreader2.readLine();
        if (s10 == null) goto _L10; else goto _L9
_L9:
        stringbuilder.append(s10);
        h.a(3, "append contents.");
          goto _L11
        ClientProtocolException clientprotocolexception;
        clientprotocolexception;
_L12:
        throw new g((new StringBuilder()).append("Could not request from ").append(s).toString(), clientprotocolexception);
        Exception exception2;
        exception2;
        bufferedreader = bufferedreader2;
_L13:
        if (httpget == null)
        {
            break MISSING_BLOCK_LABEL_855;
        }
        httpget.abort();
        IOException ioexception5;
        IOException ioexception6;
        if (bufferedreader != null)
        {
            try
            {
                bufferedreader.close();
            }
            catch (IOException ioexception4)
            {
                h.a(5, "Reander bat close");
            }
        }
        throw exception2;
_L8:
        h.a(3, "No permission: android.permission.ACCESS_FINE_LOCATION or android.permission.ACCESS_COARSE_LOCATION");
          goto _L6
        clientprotocolexception;
        bufferedreader2 = null;
          goto _L12
_L10:
        if (httpget == null)
        {
            break MISSING_BLOCK_LABEL_896;
        }
        httpget.abort();
        if (bufferedreader2 != null)
        {
            try
            {
                bufferedreader2.close();
            }
            // Misplaced declaration of an exception variable
            catch (IOException ioexception6)
            {
                h.a(5, "Reander bat close");
            }
        }
_L29:
        return stringbuilder;
        ioexception5;
_L34:
        throw new g((new StringBuilder()).append("Could not request from ").append(s).toString(), ioexception5);
        exception2;
          goto _L13
_L2:
        h.a(3, "HTTP Request with : HttpURLConnection");
        URL url = new URL(s);
        h.a(3, (new StringBuilder()).append("Requesting Ad Url = ").append(url).toString());
        if (s2 != null) goto _L15; else goto _L14
_L14:
        HttpURLConnection httpurlconnection = (HttpURLConnection)url.openConnection();
        Object obj1 = httpurlconnection;
_L19:
        Iterator iterator;
        ((HttpURLConnection) (obj1)).setRequestMethod("GET");
        ((HttpURLConnection) (obj1)).setConnectTimeout(20000);
        ((HttpURLConnection) (obj1)).setReadTimeout(20000);
        ((HttpURLConnection) (obj1)).setRequestProperty("User-Agent", e.a());
        h.a(3, (new StringBuilder()).append("Set http header. User-Agent : ").append(e.a()).toString());
        iterator = map.entrySet().iterator();
_L18:
        boolean flag1 = iterator.hasNext();
        bufferedreader = null;
        if (!flag1) goto _L17; else goto _L16
_L16:
        java.util.Map.Entry entry = (java.util.Map.Entry)iterator.next();
        ((HttpURLConnection) (obj1)).setRequestProperty((String)entry.getKey(), (String)entry.getValue());
        h.a(3, (new StringBuilder()).append("Set http header. ").append((String)entry.getKey()).append(" : ").append((String)entry.getValue()).toString());
          goto _L18
        UnknownHostException unknownhostexception;
        unknownhostexception;
        URL url1;
        Object obj;
        obj = obj1;
        url1 = url;
_L27:
        throw new g((new StringBuilder()).append("Could not request from ").append(url1).toString(), unknownhostexception);
        Exception exception;
        exception;
        obj1 = obj;
_L28:
        if (obj1 == null)
        {
            break MISSING_BLOCK_LABEL_1271;
        }
        ((HttpURLConnection) (obj1)).disconnect();
        SocketException socketexception;
        SocketTimeoutException sockettimeoutexception;
        IOException ioexception1;
        Exception exception1;
        BufferedReader bufferedreader1;
        int i;
        String s3;
        String s4;
        String s5;
        boolean flag2;
        int k;
        String s6;
        IOException ioexception2;
        IOException ioexception3;
        HttpsURLConnection httpsurlconnection;
        if (bufferedreader != null)
        {
            try
            {
                bufferedreader.close();
            }
            catch (IOException ioexception)
            {
                h.a(5, "Reander bat close");
            }
        }
        throw exception;
_L15:
        httpsurlconnection = (HttpsURLConnection)url.openConnection();
        obj1 = httpsurlconnection;
          goto _L19
_L17:
        ((HttpURLConnection) (obj1)).connect();
        i = ((HttpURLConnection) (obj1)).getResponseCode();
        if (i == 200)
        {
            break MISSING_BLOCK_LABEL_1354;
        }
        if (obj1 == null)
        {
            break MISSING_BLOCK_LABEL_1331;
        }
        ((HttpURLConnection) (obj1)).disconnect();
        if (false)
        {
            try
            {
                null.close();
            }
            // Misplaced declaration of an exception variable
            catch (IOException ioexception3)
            {
                h.a(5, "Reander bat close");
            }
        }
        return stringbuilder;
        h.a(3, (new StringBuilder()).append("adHttpRequest response Code: ").append(((HttpURLConnection) (obj1)).getResponseCode()).append(",").append("message: ").append(((HttpURLConnection) (obj1)).getResponseMessage()).toString());
        s3 = ((HttpURLConnection) (obj1)).getHeaderField("X-Ysma-Bc2");
        bufferedreader = null;
        if (s3 == null)
        {
            break MISSING_BLOCK_LABEL_1456;
        }
        e.b(s3);
        b(context, s3);
        h.a(3, (new StringBuilder()).append("Set Bcookie for userid = ").append(s3).toString());
        s4 = ((HttpURLConnection) (obj1)).getHeaderField("X-Ysma-Yc");
        s5 = ((HttpURLConnection) (obj1)).getHeaderField("X-Ysma-Tc");
        if (s4 == null)
        {
            break MISSING_BLOCK_LABEL_1532;
        }
        bufferedreader = null;
        if (s5 == null)
        {
            break MISSING_BLOCK_LABEL_1532;
        }
        e.a(s4, s5);
        h.a(3, (new StringBuilder()).append("Set ycookie = ").append(s4).append("; tcookie = ").append(s5).toString());
        flag2 = h.d((String)map.get("X-Ysma-G"));
        bufferedreader = null;
        if (flag2)
        {
            break MISSING_BLOCK_LABEL_1560;
        }
        d.a(context);
        k = h.e(((HttpURLConnection) (obj1)).getHeaderField("X-Ysma-GS"));
        h.a(3, (new StringBuilder()).append("Location flag: AAG GpsMode=").append(k).append(", app locatoinmode=").append(flag).toString());
        if (k != 1 || !flag) goto _L21; else goto _L20
_L20:
        if (context.checkCallingOrSelfPermission("android.permission.ACCESS_FINE_LOCATION") != 0 && context.checkCallingOrSelfPermission("android.permission.ACCESS_COARSE_LOCATION") != 0) goto _L23; else goto _L22
_L22:
        d.a(context, s1);
_L21:
        bufferedreader1 = new BufferedReader(new InputStreamReader(((HttpURLConnection) (obj1)).getInputStream()));
        h.a(3, (new StringBuilder()).append("reader.ready: ").append(bufferedreader1.ready()).toString());
_L26:
        s6 = bufferedreader1.readLine();
        if (s6 == null) goto _L25; else goto _L24
_L24:
        stringbuilder.append(s6);
        h.a(3, "append contents.");
          goto _L26
        unknownhostexception;
        url1 = url;
        bufferedreader = bufferedreader1;
        obj = obj1;
          goto _L27
_L23:
        h.a(3, "No permission: android.permission.ACCESS_FINE_LOCATION or android.permission.ACCESS_COARSE_LOCATION");
          goto _L21
        socketexception;
_L33:
        throw new g((new StringBuilder()).append("Could not request from ").append(url).toString(), socketexception);
        exception;
          goto _L28
_L25:
        if (obj1 == null)
        {
            break MISSING_BLOCK_LABEL_1799;
        }
        ((HttpURLConnection) (obj1)).disconnect();
        if (bufferedreader1 != null)
        {
            try
            {
                bufferedreader1.close();
            }
            // Misplaced declaration of an exception variable
            catch (IOException ioexception2)
            {
                h.a(5, "Reander bat close");
            }
        }
          goto _L29
        sockettimeoutexception;
        obj1 = null;
        url = null;
_L32:
        throw new g((new StringBuilder()).append("Could not request from ").append(url).toString(), sockettimeoutexception);
_L31:
        throw new g((new StringBuilder()).append("Could not request from ").append(url).toString(), ioexception1);
_L30:
        throw new g((new StringBuilder()).append("Could not request from ").append(url).toString(), exception1);
        exception;
        bufferedreader = null;
        obj1 = null;
          goto _L28
        exception;
        bufferedreader = bufferedreader1;
          goto _L28
        exception1;
        bufferedreader = null;
        obj1 = null;
          goto _L30
        exception1;
        bufferedreader = null;
          goto _L30
        exception1;
        bufferedreader = bufferedreader1;
          goto _L30
        ioexception1;
        bufferedreader = null;
        obj1 = null;
          goto _L31
        ioexception1;
        bufferedreader = null;
          goto _L31
        ioexception1;
        bufferedreader = bufferedreader1;
          goto _L31
        sockettimeoutexception;
        bufferedreader = null;
        obj1 = null;
          goto _L32
        sockettimeoutexception;
        bufferedreader = null;
          goto _L32
        sockettimeoutexception;
        bufferedreader = bufferedreader1;
          goto _L32
        socketexception;
        bufferedreader = null;
        obj1 = null;
        url = null;
          goto _L33
        socketexception;
        bufferedreader = null;
        obj1 = null;
          goto _L33
        socketexception;
        bufferedreader = bufferedreader1;
          goto _L33
        unknownhostexception;
        obj = null;
        url1 = null;
        bufferedreader = null;
          goto _L27
        unknownhostexception;
        url1 = url;
        obj = null;
        bufferedreader = null;
          goto _L27
        ioexception5;
        bufferedreader = bufferedreader2;
          goto _L34
        ioexception1;
        bufferedreader = null;
        obj1 = null;
        url = null;
          goto _L31
        exception1;
        bufferedreader = null;
        obj1 = null;
        url = null;
          goto _L30
    }

    public static void a(Context context, String s)
    {
        if (context.checkCallingOrSelfPermission("android.permission.INTERNET") == -1)
        {
            h.a("Cannot request an ad without Internet permissions!  Open manifest.xml and just before the final </manifest> tag add:  <uses-permission android:name=\"android.permission.INTERNET\" />");
        }
        HashMap hashmap = new HashMap();
        String s1 = "";
        String s2 = e.i();
        if (!h.d(s2))
        {
            s1 = (new StringBuilder()).append("B=").append(s2).append("; ").toString();
        }
        String as[] = e.l();
        String s3 = as[0];
        String s4 = as[1];
        if (!h.d(s3) && !h.d(s4))
        {
            s1 = (new StringBuilder()).append(s1).append("Y=").append(s3).append("; T=").append(s4).toString();
        }
        if (!"".equals(s1))
        {
            hashmap.put("Cookie", s1);
        }
        h.a(3, "Request Beacon");
        try
        {
            a(context, s, ((Map) (hashmap)));
            return;
        }
        catch (g g1)
        {
            return;
        }
    }

    public static boolean a(String s, boolean flag)
    {
        boolean flag1;
        boolean flag2;
        flag1 = h.d(s);
        flag2 = false;
        if (!flag1) goto _L2; else goto _L1
_L1:
        return flag2;
_L2:
        InputStream inputstream;
        URLConnection urlconnection = (new URL(s)).openConnection();
        urlconnection.setConnectTimeout(0);
        urlconnection.setReadTimeout(0);
        urlconnection.setUseCaches(flag);
        urlconnection.connect();
        inputstream = urlconnection.getInputStream();
        flag2 = true;
        if (inputstream != null)
        {
            try
            {
                inputstream.close();
            }
            catch (IOException ioexception3)
            {
                h.a(5, (new StringBuilder()).append("Not close input stream : ").append(s).toString(), ioexception3);
                return flag2;
            }
            return flag2;
        }
          goto _L1
        IOException ioexception1;
        ioexception1;
        h.a(5, (new StringBuilder()).append("IO Exception getting Image:  ").append(s).toString(), ioexception1);
        flag2 = false;
        if (true) goto _L1; else goto _L3
_L3:
        try
        {
            null.close();
        }
        catch (IOException ioexception2)
        {
            h.a(5, (new StringBuilder()).append("Not close input stream : ").append(s).toString(), ioexception2);
            return false;
        }
        return false;
        Exception exception;
        exception;
        if (false)
        {
            try
            {
                null.close();
            }
            catch (IOException ioexception)
            {
                h.a(5, (new StringBuilder()).append("Not close input stream : ").append(s).toString(), ioexception);
            }
        }
        throw exception;
    }

    public static Bitmap b(String s, boolean flag)
    {
        boolean flag1;
        Bitmap bitmap;
        flag1 = h.d(s);
        bitmap = null;
        if (!flag1) goto _L2; else goto _L1
_L1:
        return bitmap;
_L2:
        InputStream inputstream1;
        URLConnection urlconnection = (new URL(s)).openConnection();
        urlconnection.setConnectTimeout(0);
        urlconnection.setReadTimeout(0);
        urlconnection.setUseCaches(flag);
        urlconnection.connect();
        inputstream1 = urlconnection.getInputStream();
        InputStream inputstream = inputstream1;
        Bitmap bitmap1 = BitmapFactory.decodeStream(inputstream);
        bitmap = bitmap1;
        if (inputstream != null)
        {
            try
            {
                inputstream.close();
            }
            catch (IOException ioexception3)
            {
                h.a(5, (new StringBuilder()).append("Not close input stream : ").append(s).toString(), ioexception3);
                return bitmap;
            }
            return bitmap;
        }
        if (true) goto _L1; else goto _L3
_L3:
        IOException ioexception1;
        ioexception1;
        inputstream = null;
_L7:
        h.a(5, (new StringBuilder()).append("IO Exception getting Image:  ").append(s).toString(), ioexception1);
        bitmap = null;
        if (inputstream == null) goto _L1; else goto _L4
_L4:
        try
        {
            inputstream.close();
        }
        catch (IOException ioexception2)
        {
            h.a(5, (new StringBuilder()).append("Not close input stream : ").append(s).toString(), ioexception2);
            return null;
        }
        return null;
        Exception exception;
        exception;
        Exception exception1;
        inputstream = null;
        exception1 = exception;
_L6:
        if (inputstream != null)
        {
            try
            {
                inputstream.close();
            }
            catch (IOException ioexception)
            {
                h.a(5, (new StringBuilder()).append("Not close input stream : ").append(s).toString(), ioexception);
            }
        }
        throw exception1;
        exception1;
        if (true) goto _L6; else goto _L5
_L5:
        ioexception1;
          goto _L7
    }

    private static void b(Context context, String s)
    {
        android.content.SharedPreferences.Editor editor = context.getSharedPreferences("YJADSDK", 0).edit();
        editor.putString("APPBCOOKIE", s);
        editor.commit();
    }
}

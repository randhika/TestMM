// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.common;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.res.Resources;
import android.os.Environment;
import android.util.Xml;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.http.client.methods.HttpRequestBase;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class YHosts
{
    private static class Hosts
    {

        public String host;
        public List ignore;
        public String ipaddr;
        public List path;

        private Hosts()
        {
            path = new ArrayList();
            ignore = new ArrayList();
        }

    }


    public static final int DISABLE_HOSTS = 0;
    public static final int ENABLE_RESOURCE_HOSTS = 2;
    public static final int ENABLE_SDCARD_HOSTS = 1;
    private static YHosts INSTANCE = null;
    private static int mEnable = 1;
    private Context mContext;
    private Map mHostsMap;

    private YHosts(Context context)
    {
        mContext = null;
        mHostsMap = new HashMap();
        mContext = context.getApplicationContext();
        loadHosts();
    }

    static void deleteInstance()
    {
        INSTANCE = null;
    }

    public static int getEnable()
    {
        return mEnable;
    }

    private XmlPullParser getHostsXML()
        throws FileNotFoundException, XmlPullParserException
    {
        int i;
        switch (mEnable)
        {
        default:
            throw new IllegalStateException();

        case 1: // '\001'
            File file = new File((new StringBuilder()).append(Environment.getExternalStorageDirectory().getPath()).append("/hosts.xml").toString());
            if (!file.exists())
            {
                return null;
            } else
            {
                FileInputStream fileinputstream = new FileInputStream(file);
                XmlPullParser xmlpullparser = Xml.newPullParser();
                xmlpullparser.setInput(fileinputstream, "utf-8");
                return xmlpullparser;
            }

        case 2: // '\002'
            i = mContext.getResources().getIdentifier("hosts", "xml", mContext.getPackageName());
            break;
        }
        if (i == 0)
        {
            return null;
        } else
        {
            return mContext.getResources().getXml(i);
        }
    }

    public static YHosts getInstance(Context context)
    {
        if (INSTANCE == null || INSTANCE.mContext != context)
        {
            INSTANCE = new YHosts(context);
            return INSTANCE;
        } else
        {
            return INSTANCE;
        }
    }

    public static boolean isDebuggable(Context context)
    {
        return (2 & context.getApplicationInfo().flags) == 2;
    }

    private void loadHosts()
    {
        mHostsMap = new HashMap();
        break MISSING_BLOCK_LABEL_11;
_L9:
        XmlPullParser xmlpullparser;
        boolean flag;
        int i;
        Hosts hosts;
        String s;
        do
        {
            do
            {
                return;
            } while (!isDebuggable(mContext) || mEnable == 0);
            try
            {
                xmlpullparser = getHostsXML();
            }
            catch (Exception exception)
            {
                exception.printStackTrace();
                return;
            }
        } while (xmlpullparser == null);
        flag = true;
        i = xmlpullparser.getEventType();
        hosts = null;
        continue; /* Loop/switch isn't completed */
_L3:
        i = xmlpullparser.next();
        continue; /* Loop/switch isn't completed */
_L10:
        s = xmlpullparser.getName();
        if (!"item".equals(s)) goto _L2; else goto _L1
_L1:
        flag = true;
        hosts = new Hosts();
          goto _L3
_L2:
        if (!flag) goto _L3; else goto _L4
_L4:
label0:
        {
            if (!"host".equals(s))
            {
                break label0;
            }
            hosts.host = xmlpullparser.nextText();
        }
          goto _L3
label1:
        {
            if (!"ip".equals(s))
            {
                break label1;
            }
            hosts.ipaddr = xmlpullparser.nextText();
        }
          goto _L3
label2:
        {
            if (!"path".equals(s))
            {
                break label2;
            }
            hosts.path.add(xmlpullparser.nextText());
        }
          goto _L3
        if (!"ignore".equals(s)) goto _L6; else goto _L5
_L5:
        hosts.ignore.add(xmlpullparser.nextText());
          goto _L3
_L6:
        if (!"item".equals(xmlpullparser.getName())) goto _L3; else goto _L7
_L7:
        if (InetAddress.getByName(hosts.ipaddr).isSiteLocalAddress())
        {
            mHostsMap.put(hosts.host, hosts);
        }
        flag = false;
          goto _L3
        if (i == 1) goto _L9; else goto _L8
_L8:
        i;
        JVM INSTR tableswitch 2 3: default 53
    //                   2 64
    //                   3 215;
           goto _L3 _L10 _L6
    }

    public static void setEnable(int i)
    {
        mEnable = i;
        if (INSTANCE != null)
        {
            INSTANCE.loadHosts();
        }
    }

    public void convertURL(HttpRequestBase httprequestbase)
    {
        String s = httprequestbase.getURI().toString();
        String s1 = getConvertedURL(s);
        if (s1.equals(s))
        {
            return;
        }
        try
        {
            httprequestbase.setHeader("Host", (new URL(s)).getHost());
            httprequestbase.setURI(new URI(s1));
            return;
        }
        catch (MalformedURLException malformedurlexception)
        {
            malformedurlexception.printStackTrace();
            return;
        }
        catch (URISyntaxException urisyntaxexception)
        {
            urisyntaxexception.printStackTrace();
        }
    }

    public String getConvertedURL(String s)
    {
        if (mHostsMap.size() != 0) goto _L2; else goto _L1
_L1:
        return s;
_L2:
        String s1;
        String s2;
        Hosts hosts;
        String s3;
        try
        {
            URL url = new URL(s);
            s1 = url.getHost();
            s2 = url.getPath();
            hosts = (Hosts)mHostsMap.get(s1);
        }
        catch (Exception exception)
        {
            return s;
        }
        if (hosts == null) goto _L1; else goto _L3
_L3:
        if (hosts.ignore.size() > 0 && hosts.ignore.contains(s2) || hosts.path.size() != 0 && !hosts.path.contains(s2)) goto _L1; else goto _L4
_L4:
        s3 = s.replace(s1, hosts.ipaddr);
        return s3;
    }

    public boolean isValidHosts(String s)
    {
        return !getConvertedURL(s).equals(s);
    }

}

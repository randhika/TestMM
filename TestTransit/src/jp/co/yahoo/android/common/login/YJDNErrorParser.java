// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.common.login;

import android.util.Xml;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.StringReader;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

// Referenced classes of package jp.co.yahoo.android.common.login:
//            YJDNErrorData

public class YJDNErrorParser
{

    public YJDNErrorParser()
    {
    }

    private YJDNErrorData getData(XmlPullParser xmlpullparser)
    {
        String s;
        String s1;
        s = "";
        s1 = "";
        int i = xmlpullparser.getEventType();
        boolean flag = false;
          goto _L1
_L4:
        i = xmlpullparser.next();
          goto _L1
_L8:
        String s2 = xmlpullparser.getName();
        if (!"Error".equals(s2)) goto _L3; else goto _L2
_L2:
        flag = true;
          goto _L4
_L3:
        if (!flag) goto _L4; else goto _L5
_L5:
label0:
        {
            if (!"Message".equals(s2))
            {
                break label0;
            }
            s = xmlpullparser.nextText();
        }
          goto _L4
label1:
        {
            if (!"Code".equals(s2))
            {
                break label1;
            }
            s1 = xmlpullparser.nextText();
        }
          goto _L4
_L9:
        boolean flag1 = "Error".equals(xmlpullparser.getName());
        if (flag1)
        {
            flag = false;
        }
          goto _L4
        Exception exception;
        exception;
_L7:
        if (s.equals(""))
        {
            return null;
        }
        YJDNErrorData yjdnerrordata = new YJDNErrorData();
        yjdnerrordata.setErrorMsg(s);
        yjdnerrordata.setErrorCode(s1);
        if (s.equals("\u30ED\u30B0\u30A4\u30F3\u3057\u3066\u304F\u3060\u3055\u3044\u3002"))
        {
            yjdnerrordata.setIsLoginExpired(true);
            return yjdnerrordata;
        } else
        {
            yjdnerrordata.setIsLoginExpired(false);
            return yjdnerrordata;
        }
_L1:
        if (i == 1) goto _L7; else goto _L6
_L6:
        i;
        JVM INSTR tableswitch 2 3: default 20
    //                   2 31
    //                   3 100;
           goto _L4 _L8 _L9
    }

    public YJDNErrorData getData(String s)
    {
        StringReader stringreader;
        XmlPullParser xmlpullparser;
        stringreader = null;
        xmlpullparser = Xml.newPullParser();
        StringReader stringreader1 = new StringReader(s);
        YJDNErrorData yjdnerrordata1;
        xmlpullparser.setInput(stringreader1);
        yjdnerrordata1 = getData(xmlpullparser);
        YJDNErrorData yjdnerrordata;
        yjdnerrordata = yjdnerrordata1;
        if (stringreader1 == null)
        {
            break MISSING_BLOCK_LABEL_92;
        }
        stringreader1.close();
_L2:
        return yjdnerrordata;
        XmlPullParserException xmlpullparserexception1;
        xmlpullparserexception1;
_L5:
        yjdnerrordata = null;
        if (stringreader == null) goto _L2; else goto _L1
_L1:
        stringreader.close();
        return null;
        Exception exception;
        exception;
_L4:
        if (stringreader != null)
        {
            stringreader.close();
        }
        throw exception;
        exception;
        stringreader = stringreader1;
        if (true) goto _L4; else goto _L3
_L3:
        XmlPullParserException xmlpullparserexception;
        xmlpullparserexception;
        stringreader = stringreader1;
          goto _L5
        return yjdnerrordata;
    }

    public YJDNErrorData getData(byte abyte0[])
    {
        ByteArrayInputStream bytearrayinputstream;
        XmlPullParser xmlpullparser;
        bytearrayinputstream = null;
        xmlpullparser = Xml.newPullParser();
        ByteArrayInputStream bytearrayinputstream1 = new ByteArrayInputStream(abyte0);
        YJDNErrorData yjdnerrordata1;
        xmlpullparser.setInput(bytearrayinputstream1, "UTF-8");
        yjdnerrordata1 = getData(xmlpullparser);
        YJDNErrorData yjdnerrordata;
        yjdnerrordata = yjdnerrordata1;
        if (bytearrayinputstream1 == null)
        {
            break MISSING_BLOCK_LABEL_108;
        }
        try
        {
            bytearrayinputstream1.close();
        }
        catch (IOException ioexception2)
        {
            return yjdnerrordata;
        }
_L2:
        return yjdnerrordata;
        XmlPullParserException xmlpullparserexception1;
        xmlpullparserexception1;
_L5:
        yjdnerrordata = null;
        if (bytearrayinputstream == null) goto _L2; else goto _L1
_L1:
        try
        {
            bytearrayinputstream.close();
        }
        catch (IOException ioexception)
        {
            return null;
        }
        return null;
        Exception exception;
        exception;
_L4:
        if (bytearrayinputstream != null)
        {
            try
            {
                bytearrayinputstream.close();
            }
            catch (IOException ioexception1) { }
        }
        throw exception;
        exception;
        bytearrayinputstream = bytearrayinputstream1;
        if (true) goto _L4; else goto _L3
_L3:
        XmlPullParserException xmlpullparserexception;
        xmlpullparserexception;
        bytearrayinputstream = bytearrayinputstream1;
          goto _L5
        return yjdnerrordata;
    }
}

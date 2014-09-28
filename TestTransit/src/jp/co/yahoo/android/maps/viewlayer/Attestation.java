// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.maps.viewlayer;

import android.util.Xml;
import java.io.InputStream;
import jp.co.yahoo.android.maps.HttpClient;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xmlpull.v1.XmlPullParser;

public class Attestation extends Thread
{
    public static interface AttestationListener
    {

        public abstract boolean endAttestation(Attestation attestation);
    }


    private String ATTESTATION_URL;
    private String labelTouchUrl;
    private String m_appid;
    private AttestationListener m_ls;
    private String mapUrl;
    public boolean success;
    private String ymlUrl;

    public Attestation()
    {
        super("Attestation");
        m_appid = "";
        m_ls = null;
        ATTESTATION_URL = "http://sdk.olp.yahooapis.jp/OpenLocalPlatform/V1/mapInfo";
    }

    public Attestation(String s)
    {
        m_appid = "";
        m_ls = null;
        ATTESTATION_URL = "http://sdk.olp.yahooapis.jp/OpenLocalPlatform/V1/mapInfo";
        m_appid = s;
        success = false;
    }

    private static String getAttribute(Node node, String s)
    {
        String s1 = ((Element)node).getAttribute(s);
        if (s1 == null)
        {
            return null;
        } else
        {
            return s1.trim();
        }
    }

    private static String getContent(Node node)
    {
        Node node1;
        String s;
        if (node != null)
        {
            if ((node1 = node.getFirstChild()) != null && (s = node1.getNodeValue()) != null)
            {
                return s.trim();
            }
        }
        return null;
    }

    private static Node getNode(Node node, String s)
    {
        NodeList nodelist = getNodeList(node, s);
        if (nodelist == null || nodelist.getLength() == 0)
        {
            return null;
        } else
        {
            return nodelist.item(0);
        }
    }

    private static String getNodeContent(Node node, String s)
    {
        Node node1 = getNode(node, s);
        if (node1 == null)
        {
            return null;
        } else
        {
            return getContent(node1);
        }
    }

    private static NodeList getNodeList(Node node, String s)
    {
        if (node == null || s == null || s.equals(""))
        {
            return null;
        } else
        {
            return ((Element)node).getElementsByTagName(s);
        }
    }

    private void setbunkai(InputStream inputstream)
    {
        XmlPullParser xmlpullparser;
        int i;
        boolean flag;
        String s;
        String s1;
        try
        {
            xmlpullparser = Xml.newPullParser();
            xmlpullparser.setInput(inputstream, "UTF-8");
            i = xmlpullparser.getEventType();
        }
        catch (Exception exception)
        {
            return;
        }
        flag = false;
          goto _L1
_L8:
        if (i != 2)
        {
            break MISSING_BLOCK_LABEL_76;
        }
        if (!xmlpullparser.getName().equals("Result"))
        {
            break MISSING_BLOCK_LABEL_76;
        }
        s1 = xmlpullparser.getAttributeValue(null, "type");
        if (s1 == null)
        {
            break MISSING_BLOCK_LABEL_76;
        }
        if (s1.equals("android"))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag || i != 2) goto _L3; else goto _L2
_L2:
        if (!xmlpullparser.getName().equals("Url")) goto _L3; else goto _L4
_L4:
        s = xmlpullparser.getAttributeValue(null, "type");
        if (!s.equals("yml")) goto _L6; else goto _L5
_L5:
        ymlUrl = xmlpullparser.nextText();
_L3:
        i = xmlpullparser.next();
        continue; /* Loop/switch isn't completed */
_L6:
        if (s.equals("yta"))
        {
            mapUrl = xmlpullparser.nextText();
            success = true;
            continue; /* Loop/switch isn't completed */
        }
        if (s.equals("bounds"))
        {
            labelTouchUrl = xmlpullparser.nextText();
        }
        if (true) goto _L3; else goto _L1
_L1:
        if (i != 1) goto _L8; else goto _L7
_L7:
    }

    public String getLabelTouchUrl()
    {
        return labelTouchUrl;
    }

    public String getMapUrl()
    {
        return mapUrl;
    }

    public String getYmlUrl()
    {
        return "http://layer.map.yahoo.co.jp/ml";
    }

    public void run()
    {
_L2:
        InputStream inputstream;
        do
        {
            inputstream = HttpClient.httprun((new StringBuilder(String.valueOf(ATTESTATION_URL))).append("?device=a&appid=").append(m_appid).append("&v=4").toString());
        } while (inputstream == null);
        setbunkai(inputstream);
        inputstream.close();
        if (m_ls != null)
        {
            m_ls.endAttestation(this);
        }
        return;
        Exception exception;
        exception;
        if (true) goto _L2; else goto _L1
_L1:
    }

    public void setMapTouchListener(AttestationListener attestationlistener)
    {
        m_ls = attestationlistener;
    }
}

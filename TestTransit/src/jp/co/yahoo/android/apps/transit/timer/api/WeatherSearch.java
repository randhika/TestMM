// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.timer.api;

import android.content.Context;
import java.io.ByteArrayInputStream;
import java.util.HashMap;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import jp.co.yahoo.android.apps.transit.timer.api.data.WeatherData;
import jp.co.yahoo.android.yolp.common.ApiBase;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class WeatherSearch extends ApiBase
{

    public WeatherSearch(Context context)
    {
        super(context);
        setUri(context.getString(0x7f0d004f));
        param.put("appid", context.getString(0x7f0d0050));
    }

    protected void analyze(String s)
    {
        WeatherData weatherdata = new WeatherData();
        try
        {
            Element element = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new ByteArrayInputStream(s.getBytes())).getDocumentElement();
            Node node = element.getElementsByTagName("name").item(0);
            Node node1 = element.getElementsByTagName("url").item(1);
            Node node2 = element.getElementsByTagName("min").item(0);
            Node node3 = element.getElementsByTagName("max").item(0);
            Node node4 = element.getElementsByTagName("icon").item(0);
            Node node5 = element.getElementsByTagName("max_precip").item(0);
            weatherdata.setAreaname(node.getChildNodes().item(0).getNodeValue());
            weatherdata.setUrl(node1.getChildNodes().item(0).getNodeValue());
            weatherdata.setIcon(node4.getChildNodes().item(0).getNodeValue());
            weatherdata.setMinTemp(node2.getChildNodes().item(0).getNodeValue());
            weatherdata.setMaxTemp(node3.getChildNodes().item(0).getNodeValue());
            weatherdata.setPrecip(node5.getChildNodes().item(0).getNodeValue());
            setResult(weatherdata);
            return;
        }
        catch (Exception exception)
        {
            setResult(weatherdata);
            exception.printStackTrace();
            return;
        }
    }

    public String request()
    {
        param.remove("output");
        String s = super.request();
        analyze(s);
        return s;
    }

    public void setAreaCode(String s)
    {
        param.put("jis", s);
    }
}

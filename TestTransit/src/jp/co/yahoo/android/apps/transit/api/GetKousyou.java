// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.api;

import android.content.Context;
import com.android.volley.VolleyError;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import jp.co.yahoo.android.apps.transit.common.StringUtil;
import org.json.JSONArray;
import org.json.JSONObject;

// Referenced classes of package jp.co.yahoo.android.apps.transit.api:
//            ApiBase

public class GetKousyou extends ApiBase
{
    class Kousyou
        implements Serializable
    {

        private static final long serialVersionUID = 0x6b1387e8b91b6efaL;
        private String attention;
        private String text;
        final GetKousyou this$0;

        public String getAttention()
        {
            return attention;
        }

        public String getText()
        {
            return text;
        }

        public void setAttention(String s)
        {
            attention = s;
        }

        public void setText(String s)
        {
            text = s;
        }

        Kousyou()
        {
            this$0 = GetKousyou.this;
            super();
        }
    }


    private static final String ATTENTION_AIR = "Air";
    private static final String ATTENTION_BUS = "Bus";
    private static final String ATTENTION_JR = "JR";
    private static final String ATTENTION_KOUSYOU = "Kousyou";
    private static final String ATTENTION_SHIP = "Ship";
    private static final String ATTENTION_SHITETSU = "Shitetsu";
    private Map kousyouResults;

    public GetKousyou(Context context, ApiBase.ApiListener apilistener)
    {
        super(context, apilistener);
        kousyouResults = new HashMap();
        setUri(getContext().getString(0x7f0d003e));
        setParameter("appid", getContext().getString(0x7f0d003b));
        setParameter("output", "json");
    }

    private String getText(String s)
    {
        if (kousyouResults.get(s) == null)
        {
            return "";
        }
        if (s == "Kousyou")
        {
            return ((Kousyou)kousyouResults.get(s)).getText().replaceAll("<br>", "\n");
        } else
        {
            return ((Kousyou)kousyouResults.get(s)).getText();
        }
    }

    protected boolean analyze(JSONObject jsonobject)
    {
        JSONArray jsonarray;
        if (jsonobject != null)
        {
            if ((jsonarray = jsonobject.optJSONArray("Result")) != null && jsonarray.length() >= 1)
            {
                for (int i = 0; i < jsonarray.length(); i++)
                {
                    JSONObject jsonobject1 = jsonarray.optJSONObject(i);
                    Kousyou kousyou1 = new Kousyou();
                    kousyou1.setAttention(jsonobject1.optString("Attention"));
                    kousyou1.setText(jsonobject1.optString("Text"));
                    kousyouResults.put(kousyou1.getAttention(), kousyou1);
                }

                return true;
            }
        }
        return false;
    }

    protected void analyzeError(VolleyError volleyerror)
    {
    }

    public String getAir()
    {
        return getText("Air");
    }

    public String getBus()
    {
        return getText("Bus");
    }

    public String getJR()
    {
        return getText("JR");
    }

    public String getKousyou()
    {
        return getText("Kousyou");
    }

    public String getShip()
    {
        return getText("Ship");
    }

    public String getShitetsu()
    {
        return getText("Shitetsu");
    }

    public String kousyou()
    {
label0:
        {
            String s = "";
            String s6;
            try
            {
                if (StringUtil.isEmpty(getKousyou()))
                {
                    break label0;
                }
                String s1 = (new StringBuilder()).append(getKousyou()).append("\n\n").toString();
                String s2 = (new StringBuilder()).append(s1).append("\u30FB").append(getJR()).append("\n").toString();
                String s3 = (new StringBuilder()).append(s2).append("\u30FB").append(getShitetsu()).append("\n").toString();
                String s4 = (new StringBuilder()).append(s3).append("\u30FB").append(getAir()).append("\n").toString();
                String s5 = (new StringBuilder()).append(s4).append("\u30FB").append(getBus()).append("\n").toString();
                s6 = (new StringBuilder()).append(s5).append("\u30FB").append(getShip()).toString();
            }
            catch (Exception exception)
            {
                exception.printStackTrace();
                return s;
            }
            s = s6;
        }
        return s;
    }
}

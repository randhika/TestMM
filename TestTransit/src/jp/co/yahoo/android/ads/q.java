// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.ads;

import android.content.Context;
import org.json.JSONException;
import org.json.JSONObject;

// Referenced classes of package jp.co.yahoo.android.ads:
//            k, a, h, AdResponse

public class q
    implements k
{

    private final Context a;
    private a b;
    private JSONObject c;

    public q(Context context, a a1, JSONObject jsonobject)
    {
        a = context;
        b = a1;
        c = jsonobject;
    }

    public void a()
    {
        try
        {
            if (c.has("xml"))
            {
                b.o(c.getString("xml"));
            }
            return;
        }
        catch (JSONException jsonexception)
        {
            h.a(6, (new StringBuilder()).append("JSONException Failed to parse vast xml response:  ").append(c).toString(), jsonexception);
        }
    }

    public AdResponse b()
    {
        AdResponse adresponse = new AdResponse();
        adresponse.setXmlString(b.q());
        adresponse.setCode("200");
        adresponse.setMessage("AdView Success");
        return adresponse;
    }
}

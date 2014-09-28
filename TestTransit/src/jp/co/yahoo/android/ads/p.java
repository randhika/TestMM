// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.ads;

import android.content.Context;
import org.json.JSONException;
import org.json.JSONObject;

// Referenced classes of package jp.co.yahoo.android.ads:
//            k, a, h, f, 
//            AdResponse

public class p
    implements k
{

    private final Context a;
    private a b;
    private JSONObject c;

    public p(Context context, a a1, JSONObject jsonobject)
    {
        a = context;
        b = a1;
        c = jsonobject;
    }

    public void a()
    {
        try
        {
            if (c.has("csc"))
            {
                b.j(c.getString("csc"));
            }
            return;
        }
        catch (JSONException jsonexception)
        {
            h.a(6, (new StringBuilder()).append("JSONException Failed to parse pv count ad response:  ").append(c).toString(), jsonexception);
        }
    }

    public AdResponse b()
    {
        if (!h.d(b.l()))
        {
            f.a(a, b.l());
        }
        AdResponse adresponse = new AdResponse();
        adresponse.setCode("201");
        adresponse.setMessage("Adview is Null");
        return adresponse;
    }
}

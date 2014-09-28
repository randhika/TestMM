// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

// Referenced classes of package com.google.android.gms.internal:
//            br

public final class bl
{

    public final String nk;
    public final String nl;
    public final List nm;
    public final String nn;
    public final String no;
    public final List np;
    public final String nq;

    public bl(JSONObject jsonobject)
        throws JSONException
    {
        nl = jsonobject.getString("id");
        JSONArray jsonarray = jsonobject.getJSONArray("adapters");
        ArrayList arraylist = new ArrayList(jsonarray.length());
        for (int i = 0; i < jsonarray.length(); i++)
        {
            arraylist.add(jsonarray.getString(i));
        }

        nm = Collections.unmodifiableList(arraylist);
        nn = jsonobject.optString("allocation_id", null);
        np = br.a(jsonobject, "imp_urls");
        JSONObject jsonobject1 = jsonobject.optJSONObject("ad");
        String s;
        JSONObject jsonobject2;
        String s1;
        String s2;
        if (jsonobject1 != null)
        {
            s = jsonobject1.toString();
        } else
        {
            s = null;
        }
        nk = s;
        jsonobject2 = jsonobject.optJSONObject("data");
        if (jsonobject2 != null)
        {
            s1 = jsonobject2.toString();
        } else
        {
            s1 = null;
        }
        nq = s1;
        s2 = null;
        if (jsonobject2 != null)
        {
            s2 = jsonobject2.optString("class_name");
        }
        no = s2;
    }
}

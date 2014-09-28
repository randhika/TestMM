// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import android.content.Context;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

// Referenced classes of package com.google.android.gms.internal:
//            ef, bm, eh, bl, 
//            es

public final class br
{

    public static List a(JSONObject jsonobject, String s)
        throws JSONException
    {
        JSONArray jsonarray = jsonobject.optJSONArray(s);
        if (jsonarray != null)
        {
            ArrayList arraylist = new ArrayList(jsonarray.length());
            for (int i = 0; i < jsonarray.length(); i++)
            {
                arraylist.add(jsonarray.getString(i));
            }

            return Collections.unmodifiableList(arraylist);
        } else
        {
            return null;
        }
    }

    public static void a(Context context, String s, ef ef1, String s1, boolean flag, List list)
    {
        String s2;
        Iterator iterator;
        String s3;
        if (flag)
        {
            s2 = "1";
        } else
        {
            s2 = "0";
        }
        for (iterator = list.iterator(); iterator.hasNext(); (new es(context, s, s3)).start())
        {
            s3 = ((String)iterator.next()).replaceAll("@gw_adlocid@", s1).replaceAll("@gw_adnetrefresh@", s2).replaceAll("@gw_qdata@", ef1.rz.nw).replaceAll("@gw_sdkver@", s).replaceAll("@gw_sessid@", eh.rQ).replaceAll("@gw_seqnum@", ef1.qa);
            if (ef1.nM != null)
            {
                s3 = s3.replaceAll("@gw_adnetid@", ef1.nM.nl).replaceAll("@gw_allocid@", ef1.nM.nn);
            }
        }

    }
}

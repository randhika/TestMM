// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.tagmanager;

import android.content.Context;
import com.google.android.gms.analytics.Tracker;
import com.google.android.gms.internal.a;
import com.google.android.gms.internal.b;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

// Referenced classes of package com.google.android.gms.tagmanager:
//            df, de, bh, dh, 
//            DataLayer

class di extends df
{

    private static final String ID;
    private static final String aiA;
    private static final String aiB;
    private static Map aiC;
    private static Map aiD;
    private static final String aiw;
    private static final String aix;
    private static final String aiy;
    private static final String aiz;
    private final DataLayer aeu;
    private final Set aiE;
    private final de aiF;

    public di(Context context, DataLayer datalayer)
    {
        this(context, datalayer, new de(context));
    }

    di(Context context, DataLayer datalayer, de de1)
    {
        super(ID, new String[0]);
        aeu = datalayer;
        aiF = de1;
        aiE = new HashSet();
        aiE.add("");
        aiE.add("0");
        aiE.add("false");
    }

    private Map G(Map map)
    {
        com.google.android.gms.internal.d.a a1 = (com.google.android.gms.internal.d.a)map.get(aiA);
        if (a1 != null)
        {
            return c(a1);
        }
        if (aiC == null)
        {
            HashMap hashmap = new HashMap();
            hashmap.put("transactionId", "&ti");
            hashmap.put("transactionAffiliation", "&ta");
            hashmap.put("transactionTax", "&tt");
            hashmap.put("transactionShipping", "&ts");
            hashmap.put("transactionTotal", "&tr");
            hashmap.put("transactionCurrency", "&cu");
            aiC = hashmap;
        }
        return aiC;
    }

    private Map H(Map map)
    {
        com.google.android.gms.internal.d.a a1 = (com.google.android.gms.internal.d.a)map.get(aiB);
        if (a1 != null)
        {
            return c(a1);
        }
        if (aiD == null)
        {
            HashMap hashmap = new HashMap();
            hashmap.put("name", "&in");
            hashmap.put("sku", "&ic");
            hashmap.put("category", "&iv");
            hashmap.put("price", "&ip");
            hashmap.put("quantity", "&iq");
            hashmap.put("currency", "&cu");
            aiD = hashmap;
        }
        return aiD;
    }

    private void a(Tracker tracker, Map map)
    {
        String s = cu("transactionId");
        if (s != null) goto _L2; else goto _L1
_L1:
        bh.A("Cannot find transactionId in data layer.");
_L4:
        return;
_L2:
        LinkedList linkedlist;
        Map map1;
        linkedlist = new LinkedList();
        try
        {
            map1 = p((com.google.android.gms.internal.d.a)map.get(aiy));
            map1.put("&t", "transaction");
            java.util.Map.Entry entry1;
            for (Iterator iterator = G(map).entrySet().iterator(); iterator.hasNext(); b(map1, (String)entry1.getValue(), cu((String)entry1.getKey())))
            {
                entry1 = (java.util.Map.Entry)iterator.next();
            }

        }
        catch (IllegalArgumentException illegalargumentexception)
        {
            com.google.android.gms.tagmanager.bh.b("Unable to send transaction", illegalargumentexception);
            return;
        }
        List list;
        linkedlist.add(map1);
        list = ne();
        if (list == null)
        {
            break MISSING_BLOCK_LABEL_338;
        }
        Iterator iterator1 = list.iterator();
_L3:
        Map map2;
        if (!iterator1.hasNext())
        {
            break MISSING_BLOCK_LABEL_338;
        }
        map2 = (Map)iterator1.next();
        if (map2.get("name") == null)
        {
            bh.A("Unable to send transaction item hit due to missing 'name' field.");
            return;
        }
        Map map3;
        map3 = p((com.google.android.gms.internal.d.a)map.get(aiy));
        map3.put("&t", "item");
        map3.put("&ti", s);
        java.util.Map.Entry entry;
        for (Iterator iterator3 = H(map).entrySet().iterator(); iterator3.hasNext(); b(map3, (String)entry.getValue(), (String)map2.get(entry.getKey())))
        {
            entry = (java.util.Map.Entry)iterator3.next();
        }

        linkedlist.add(map3);
          goto _L3
        Iterator iterator2 = linkedlist.iterator();
        while (iterator2.hasNext()) 
        {
            tracker.send((Map)iterator2.next());
        }
          goto _L4
    }

    private void b(Map map, String s, String s1)
    {
        if (s1 != null)
        {
            map.put(s, s1);
        }
    }

    private Map c(com.google.android.gms.internal.d.a a1)
    {
        Object obj = dh.o(a1);
        if (!(obj instanceof Map))
        {
            return null;
        }
        Map map = (Map)obj;
        LinkedHashMap linkedhashmap = new LinkedHashMap();
        java.util.Map.Entry entry;
        for (Iterator iterator = map.entrySet().iterator(); iterator.hasNext(); linkedhashmap.put(entry.getKey().toString(), entry.getValue().toString()))
        {
            entry = (java.util.Map.Entry)iterator.next();
        }

        return linkedhashmap;
    }

    private String cu(String s)
    {
        Object obj = aeu.get(s);
        if (obj == null)
        {
            return null;
        } else
        {
            return obj.toString();
        }
    }

    private boolean e(Map map, String s)
    {
        com.google.android.gms.internal.d.a a1 = (com.google.android.gms.internal.d.a)map.get(s);
        if (a1 == null)
        {
            return false;
        } else
        {
            return dh.n(a1).booleanValue();
        }
    }

    private List ne()
    {
        Object obj = aeu.get("transactionProducts");
        if (obj == null)
        {
            return null;
        }
        if (!(obj instanceof List))
        {
            throw new IllegalArgumentException("transactionProducts should be of type List.");
        }
        for (Iterator iterator = ((List)obj).iterator(); iterator.hasNext();)
        {
            if (!(iterator.next() instanceof Map))
            {
                throw new IllegalArgumentException("Each element of transactionProducts should be of type Map.");
            }
        }

        return (List)obj;
    }

    private Map p(com.google.android.gms.internal.d.a a1)
    {
        if (a1 == null)
        {
            return new HashMap();
        }
        Map map = c(a1);
        if (map == null)
        {
            return new HashMap();
        }
        String s = (String)map.get("&aip");
        if (s != null && aiE.contains(s.toLowerCase()))
        {
            map.remove("&aip");
        }
        return map;
    }

    public void y(Map map)
    {
        Tracker tracker = aiF.cm("_GTM_DEFAULT_TRACKER_");
        if (e(map, aix))
        {
            tracker.send(p((com.google.android.gms.internal.d.a)map.get(aiy)));
            return;
        }
        if (e(map, aiz))
        {
            a(tracker, map);
            return;
        } else
        {
            bh.D("Ignoring unknown tag.");
            return;
        }
    }

    static 
    {
        ID = a.aF.toString();
        aiw = b.aV.toString();
        aix = b.be.toString();
        aiy = b.bd.toString();
        aiz = b.eg.toString();
        aiA = b.ei.toString();
        aiB = b.ek.toString();
    }
}

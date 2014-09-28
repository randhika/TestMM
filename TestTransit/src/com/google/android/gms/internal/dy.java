// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.text.TextUtils;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

// Referenced classes of package com.google.android.gms.internal:
//            eo, eu, du, ds, 
//            ev, dx, al, ec, 
//            ai, el, aw

public final class dy
{

    private static final SimpleDateFormat qA = new SimpleDateFormat("yyyyMMdd");

    public static du a(Context context, ds ds1, String s)
    {
        JSONObject jsonobject;
        String s1;
        String s3;
        String s4;
        long l;
        String s5;
        long l1;
        int i;
        du du3;
        Object obj;
        int i1;
        String s2;
        String s6;
        du du1;
        du du2;
        JSONArray jsonarray;
        try
        {
            jsonobject = new JSONObject(s);
            s1 = jsonobject.optString("ad_base_url", null);
            s2 = jsonobject.optString("ad_url", null);
            s3 = jsonobject.optString("ad_size", null);
            s4 = jsonobject.optString("ad_html", null);
        }
        catch (JSONException jsonexception)
        {
            eu.D((new StringBuilder()).append("Could not parse the mediation config: ").append(jsonexception.getMessage()).toString());
            return new du(0);
        }
        l = -1L;
        s5 = jsonobject.optString("debug_dialog", null);
        if (!jsonobject.has("interstitial_timeout")) goto _L2; else goto _L1
_L1:
        l1 = (long)(1000D * jsonobject.getDouble("interstitial_timeout"));
_L42:
        s6 = jsonobject.optString("orientation", null);
        i = -1;
        if (!"portrait".equals(s6)) goto _L4; else goto _L3
_L3:
        i = eo.bS();
_L7:
        if (TextUtils.isEmpty(s4))
        {
            break; /* Loop/switch isn't completed */
        }
        if (TextUtils.isEmpty(s1))
        {
            eu.D("Could not parse the mediation config: Missing required ad_base_url field");
            return new du(0);
        }
          goto _L5
_L4:
        if ("landscape".equals(s6))
        {
            i = eo.bR();
        }
        if (true) goto _L7; else goto _L6
_L6:
        if (TextUtils.isEmpty(s2)) goto _L9; else goto _L8
_L8:
        du2 = dx.a(context, ds1.kQ.sw, s2, null, null);
        s1 = du2.oA;
        s4 = du2.qe;
        l = du2.qk;
        du3 = du2;
_L41:
        jsonarray = jsonobject.optJSONArray("click_urls");
        if (du3 != null) goto _L11; else goto _L10
_L10:
        obj = null;
_L19:
        if (jsonarray == null) goto _L13; else goto _L12
_L12:
        if (obj != null) goto _L15; else goto _L14
_L14:
        obj = new LinkedList();
          goto _L15
_L18:
        if (i1 >= jsonarray.length()) goto _L17; else goto _L16
_L16:
        ((List) (obj)).add(jsonarray.getString(i1));
        i1++;
          goto _L18
_L9:
        eu.D("Could not parse the mediation config: Missing required ad_html or ad_url field.");
        du1 = new du(0);
        return du1;
_L11:
        obj = du3.nt;
          goto _L19
_L40:
        JSONArray jsonarray1 = jsonobject.optJSONArray("impression_urls");
        if (du3 != null) goto _L21; else goto _L20
_L20:
        Object obj2 = null;
_L29:
        if (jsonarray1 == null) goto _L23; else goto _L22
_L22:
        if (obj2 != null) goto _L25; else goto _L24
_L24:
        obj2 = new LinkedList();
          goto _L25
_L28:
        int k;
        if (k >= jsonarray1.length()) goto _L27; else goto _L26
_L26:
        ((List) (obj2)).add(jsonarray1.getString(k));
        k++;
          goto _L28
_L21:
        obj2 = du3.nu;
          goto _L29
_L39:
        JSONArray jsonarray2 = jsonobject.optJSONArray("manual_impression_urls");
        if (du3 != null) goto _L31; else goto _L30
_L30:
        Object obj4 = null;
_L37:
        if (jsonarray2 == null) goto _L33; else goto _L32
_L32:
        if (obj4 != null) goto _L35; else goto _L34
_L34:
        obj4 = new LinkedList();
          goto _L35
_L36:
        int j;
        if (j >= jsonarray2.length())
        {
            break MISSING_BLOCK_LABEL_682;
        }
        ((List) (obj4)).add(jsonarray2.getString(j));
        j++;
          goto _L36
_L31:
        obj4 = du3.qi;
          goto _L37
_L38:
        if (du3 == null)
        {
            break MISSING_BLOCK_LABEL_536;
        }
        if (du3.orientation != -1)
        {
            i = du3.orientation;
        }
        if (du3.qf > 0L)
        {
            l1 = du3.qf;
        }
        String s7;
        boolean flag;
        s7 = jsonobject.optString("active_view");
        flag = jsonobject.optBoolean("ad_is_javascript", false);
        String s8;
        s8 = null;
        if (!flag)
        {
            break MISSING_BLOCK_LABEL_570;
        }
        s8 = jsonobject.optString("ad_passback_url", null);
        Object obj1;
        Object obj3;
        Object obj5;
        du du4 = new du(s1, s4, ((List) (obj1)), ((List) (obj3)), l1, false, -1L, ((List) (obj5)), -1L, i, s3, l, s5, flag, s8, s7);
        return du4;
_L33:
        obj5 = obj4;
          goto _L38
_L23:
        obj3 = obj2;
          goto _L39
_L13:
        obj1 = obj;
          goto _L40
_L5:
        du3 = null;
          goto _L41
_L2:
        l1 = -1L;
          goto _L42
_L15:
        i1 = 0;
          goto _L18
_L17:
        obj1 = obj;
          goto _L40
_L25:
        k = 0;
          goto _L28
_L27:
        obj3 = obj2;
          goto _L39
_L35:
        j = 0;
          goto _L36
        obj5 = obj4;
          goto _L38
    }

    public static String a(ds ds1, ec ec1, Location location, String s)
    {
        HashMap hashmap = new HashMap();
        if (s == null)
        {
            break MISSING_BLOCK_LABEL_31;
        }
        if (s.trim() != "")
        {
            hashmap.put("eid", s);
        }
        if (ds1.pW != null)
        {
            hashmap.put("ad_pos", ds1.pW);
        }
        a(hashmap, ds1.pX);
        hashmap.put("format", ds1.kT.me);
        if (ds1.kT.width == -1)
        {
            hashmap.put("smart_w", "full");
        }
        if (ds1.kT.height == -2)
        {
            hashmap.put("smart_h", "auto");
        }
        if (ds1.kT.mg == null) goto _L2; else goto _L1
_L1:
        StringBuilder stringbuilder;
        al aal[];
        int i;
        stringbuilder = new StringBuilder();
        aal = ds1.kT.mg;
        i = aal.length;
        int j = 0;
_L8:
        if (j >= i)
        {
            break MISSING_BLOCK_LABEL_282;
        }
        al al1;
        al1 = aal[j];
        if (stringbuilder.length() != 0)
        {
            stringbuilder.append("|");
        }
        if (al1.width != -1) goto _L4; else goto _L3
_L3:
        int k = (int)((float)al1.widthPixels / ec1.rr);
_L5:
        int l;
        stringbuilder.append(k);
        stringbuilder.append("x");
        if (al1.height != -2)
        {
            break MISSING_BLOCK_LABEL_272;
        }
        l = (int)((float)al1.heightPixels / ec1.rr);
_L6:
        stringbuilder.append(l);
        j++;
        continue; /* Loop/switch isn't completed */
_L4:
        k = al1.width;
          goto _L5
        l = al1.height;
          goto _L6
        hashmap.put("sz", stringbuilder);
_L2:
        String s1;
        hashmap.put("slotname", ds1.kN);
        hashmap.put("pn", ds1.applicationInfo.packageName);
        if (ds1.pY != null)
        {
            hashmap.put("vc", Integer.valueOf(ds1.pY.versionCode));
        }
        hashmap.put("ms", ds1.pZ);
        hashmap.put("ms2", ds1.qd);
        hashmap.put("seq_num", ds1.qa);
        hashmap.put("session_id", ds1.qb);
        hashmap.put("js", ds1.kQ.sw);
        a(hashmap, ec1);
        if (ds1.pX.versionCode >= 2 && ds1.pX.ma != null)
        {
            a(hashmap, ds1.pX.ma);
        }
        if (ds1.versionCode >= 2)
        {
            hashmap.put("quality_signals", ds1.qc);
        }
        if (eu.p(2))
        {
            String s2 = eo.o(hashmap).toString(2);
            eu.C((new StringBuilder()).append("Ad Request JSON: ").append(s2).toString());
        }
        s1 = eo.o(hashmap).toString();
        return s1;
        JSONException jsonexception;
        jsonexception;
        eu.D((new StringBuilder()).append("Problem serializing ad request to JSON: ").append(jsonexception.getMessage()).toString());
        return null;
        if (true) goto _L8; else goto _L7
_L7:
    }

    private static void a(HashMap hashmap, Location location)
    {
        HashMap hashmap1 = new HashMap();
        Float float1 = Float.valueOf(1000F * location.getAccuracy());
        Long long1 = Long.valueOf(1000L * location.getTime());
        Long long2 = Long.valueOf((long)(10000000D * location.getLatitude()));
        Long long3 = Long.valueOf((long)(10000000D * location.getLongitude()));
        hashmap1.put("radius", float1);
        hashmap1.put("lat", long2);
        hashmap1.put("long", long3);
        hashmap1.put("time", long1);
        hashmap.put("uule", hashmap1);
    }

    private static void a(HashMap hashmap, ai ai1)
    {
        String s = el.bP();
        if (s != null)
        {
            hashmap.put("abf", s);
        }
        if (ai1.lS != -1L)
        {
            hashmap.put("cust_age", qA.format(new Date(ai1.lS)));
        }
        if (ai1.extras != null)
        {
            hashmap.put("extras", ai1.extras);
        }
        if (ai1.lT != -1)
        {
            hashmap.put("cust_gender", Integer.valueOf(ai1.lT));
        }
        if (ai1.lU != null)
        {
            hashmap.put("kw", ai1.lU);
        }
        if (ai1.lW != -1)
        {
            hashmap.put("tag_for_child_directed_treatment", Integer.valueOf(ai1.lW));
        }
        if (ai1.lV)
        {
            hashmap.put("adtest", "on");
        }
        if (ai1.versionCode >= 2)
        {
            if (ai1.lX)
            {
                hashmap.put("d_imp_hdr", Integer.valueOf(1));
            }
            if (!TextUtils.isEmpty(ai1.lY))
            {
                hashmap.put("ppid", ai1.lY);
            }
            if (ai1.lZ != null)
            {
                a(hashmap, ai1.lZ);
            }
        }
        if (ai1.versionCode >= 3 && ai1.mb != null)
        {
            hashmap.put("url", ai1.mb);
        }
    }

    private static void a(HashMap hashmap, aw aw1)
    {
        if (Color.alpha(aw1.mD) != 0)
        {
            hashmap.put("acolor", o(aw1.mD));
        }
        if (Color.alpha(aw1.backgroundColor) != 0)
        {
            hashmap.put("bgcolor", o(aw1.backgroundColor));
        }
        if (Color.alpha(aw1.mE) != 0 && Color.alpha(aw1.mF) != 0)
        {
            hashmap.put("gradientto", o(aw1.mE));
            hashmap.put("gradientfrom", o(aw1.mF));
        }
        if (Color.alpha(aw1.mG) != 0)
        {
            hashmap.put("bcolor", o(aw1.mG));
        }
        hashmap.put("bthick", Integer.toString(aw1.mH));
        aw1.mI;
        JVM INSTR tableswitch 0 3: default 176
    //                   0 366
    //                   1 373
    //                   2 380
    //                   3 387;
           goto _L1 _L2 _L3 _L4 _L5
_L1:
        String s = null;
_L10:
        int i;
        String s1;
        if (s != null)
        {
            hashmap.put("btype", s);
        }
        i = aw1.mJ;
        s1 = null;
        i;
        JVM INSTR tableswitch 0 2: default 228
    //                   0 402
    //                   1 410
    //                   2 394;
           goto _L6 _L7 _L8 _L9
_L6:
        break; /* Loop/switch isn't completed */
_L8:
        break MISSING_BLOCK_LABEL_410;
_L11:
        if (s1 != null)
        {
            hashmap.put("callbuttoncolor", s1);
        }
        if (aw1.mK != null)
        {
            hashmap.put("channel", aw1.mK);
        }
        if (Color.alpha(aw1.mL) != 0)
        {
            hashmap.put("dcolor", o(aw1.mL));
        }
        if (aw1.mM != null)
        {
            hashmap.put("font", aw1.mM);
        }
        if (Color.alpha(aw1.mN) != 0)
        {
            hashmap.put("hcolor", o(aw1.mN));
        }
        hashmap.put("headersize", Integer.toString(aw1.mO));
        if (aw1.mP != null)
        {
            hashmap.put("q", aw1.mP);
        }
        return;
_L2:
        s = "none";
          goto _L10
_L3:
        s = "dashed";
          goto _L10
_L4:
        s = "dotted";
          goto _L10
_L5:
        s = "solid";
          goto _L10
_L9:
        s1 = "dark";
          goto _L11
_L7:
        s1 = "light";
          goto _L11
        s1 = "medium";
          goto _L11
    }

    private static void a(HashMap hashmap, ec ec1)
    {
        hashmap.put("am", Integer.valueOf(ec1.rb));
        hashmap.put("cog", m(ec1.rc));
        hashmap.put("coh", m(ec1.rd));
        if (!TextUtils.isEmpty(ec1.re))
        {
            hashmap.put("carrier", ec1.re);
        }
        hashmap.put("gl", ec1.rf);
        if (ec1.rg)
        {
            hashmap.put("simulator", Integer.valueOf(1));
        }
        hashmap.put("ma", m(ec1.rh));
        hashmap.put("sp", m(ec1.ri));
        hashmap.put("hl", ec1.rj);
        if (!TextUtils.isEmpty(ec1.rk))
        {
            hashmap.put("mv", ec1.rk);
        }
        hashmap.put("muv", Integer.valueOf(ec1.rl));
        if (ec1.rm != -2)
        {
            hashmap.put("cnt", Integer.valueOf(ec1.rm));
        }
        hashmap.put("gnt", Integer.valueOf(ec1.rn));
        hashmap.put("pt", Integer.valueOf(ec1.ro));
        hashmap.put("rm", Integer.valueOf(ec1.rp));
        hashmap.put("riv", Integer.valueOf(ec1.rq));
        hashmap.put("u_sd", Float.valueOf(ec1.rr));
        hashmap.put("sh", Integer.valueOf(ec1.rt));
        hashmap.put("sw", Integer.valueOf(ec1.rs));
        Bundle bundle = new Bundle();
        bundle.putInt("active_network_state", ec1.rx);
        bundle.putBoolean("active_network_metered", ec1.rw);
        hashmap.put("connectivity", bundle);
        Bundle bundle1 = new Bundle();
        bundle1.putBoolean("is_charging", ec1.rv);
        bundle1.putDouble("battery_level", ec1.ru);
        hashmap.put("battery", bundle1);
    }

    private static Integer m(boolean flag)
    {
        int i;
        if (flag)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        return Integer.valueOf(i);
    }

    private static String o(int i)
    {
        Locale locale = Locale.US;
        Object aobj[] = new Object[1];
        aobj[0] = Integer.valueOf(0xffffff & i);
        return String.format(locale, "#%06x", aobj);
    }

}

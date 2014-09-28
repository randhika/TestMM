// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.maps;

import org.json.JSONException;
import org.json.JSONObject;

// Referenced classes of package jp.co.yahoo.android.maps:
//            LabelTouchManager

class e
    implements Runnable
{

    final labelInfo labelInfo;
    final LabelTouchManager this$0;

    public void run()
    {
        LabelTouchManager.access$2(LabelTouchManager.this, labelInfo);
    }

    (JSONObject jsonobject)
        throws JSONException
    {
         ;
        this$0 = LabelTouchManager.this;
        super();
         = new it>();
        jsonobject.getInt("type");
        JVM INSTR tableswitch 1 2: default 44
    //                   1 90
    //                   2 99;
           goto _L1 _L2 _L3
_L1:
        .el = jsonobject.getString("label");
        .e = jsonobject.getString("name");
        .le = jsonobject.getDouble("angle");
        .tical = jsonobject.getBoolean("vertical");
        labelInfo = ;
        return;
_L2:
        .e = "icon";
        continue; /* Loop/switch isn't completed */
_L3:
        .e = "text";
        if (true) goto _L1; else goto _L4
_L4:
    }
}

// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.kakao;

import org.json.JSONObject;

// Referenced classes of package jp.co.yahoo.android.apps.transit.kakao:
//            Kakao

class kaoListener
    implements kaoListener
{

    final Kakao this$0;
    final kaoListener val$listener;

    public void onResult(atus atus, JSONObject jsonobject)
    {
        if (atus.isSuccess())
        {
            setTokens(null, null);
            if (Kakao.access$000(Kakao.this) != null)
            {
                Kakao.access$000(Kakao.this).onSetTokens(null, null);
            }
        }
        if (val$listener != null)
        {
            val$listener.onResult(atus, jsonobject);
        }
    }

    kaoListener()
    {
        this$0 = final_kakao;
        val$listener = kaoListener.this;
        super();
    }
}

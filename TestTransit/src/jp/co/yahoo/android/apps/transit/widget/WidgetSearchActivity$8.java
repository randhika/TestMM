// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.widget;

import jp.co.yahoo.android.yjvoice.YJVO_STATE;

// Referenced classes of package jp.co.yahoo.android.apps.transit.widget:
//            WidgetSearchActivity

static class 
{

    static final int $SwitchMap$jp$co$yahoo$android$yjvoice$YJVO_STATE[];

    static 
    {
        $SwitchMap$jp$co$yahoo$android$yjvoice$YJVO_STATE = new int[YJVO_STATE.values().length];
        try
        {
            $SwitchMap$jp$co$yahoo$android$yjvoice$YJVO_STATE[YJVO_STATE.RECOGNIZE_CANCEL.ordinal()] = 1;
        }
        catch (NoSuchFieldError nosuchfielderror) { }
        try
        {
            $SwitchMap$jp$co$yahoo$android$yjvoice$YJVO_STATE[YJVO_STATE.RECOGNIZE_ERROR.ordinal()] = 2;
        }
        catch (NoSuchFieldError nosuchfielderror1) { }
        try
        {
            $SwitchMap$jp$co$yahoo$android$yjvoice$YJVO_STATE[YJVO_STATE.VOICE_TOO_LONG.ordinal()] = 3;
        }
        catch (NoSuchFieldError nosuchfielderror2)
        {
            return;
        }
    }
}

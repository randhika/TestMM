// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.yjvoice.screen;


// Referenced classes of package jp.co.yahoo.android.yjvoice.screen:
//            RecognizerScreen

static class ATE
{

    static final int $SwitchMap$jp$co$yahoo$android$yjvoice$screen$RecognizerScreen$STATE[];

    static 
    {
        $SwitchMap$jp$co$yahoo$android$yjvoice$screen$RecognizerScreen$STATE = new int[ATE.values().length];
        try
        {
            $SwitchMap$jp$co$yahoo$android$yjvoice$screen$RecognizerScreen$STATE[ATE.HIDE.ordinal()] = 1;
        }
        catch (NoSuchFieldError nosuchfielderror) { }
        try
        {
            $SwitchMap$jp$co$yahoo$android$yjvoice$screen$RecognizerScreen$STATE[ATE.INIT.ordinal()] = 2;
        }
        catch (NoSuchFieldError nosuchfielderror1) { }
        try
        {
            $SwitchMap$jp$co$yahoo$android$yjvoice$screen$RecognizerScreen$STATE[ATE.PHRASE.ordinal()] = 3;
        }
        catch (NoSuchFieldError nosuchfielderror2) { }
        try
        {
            $SwitchMap$jp$co$yahoo$android$yjvoice$screen$RecognizerScreen$STATE[ATE.RECOG.ordinal()] = 4;
        }
        catch (NoSuchFieldError nosuchfielderror3) { }
        try
        {
            $SwitchMap$jp$co$yahoo$android$yjvoice$screen$RecognizerScreen$STATE[ATE.ERROR.ordinal()] = 5;
        }
        catch (NoSuchFieldError nosuchfielderror4)
        {
            return;
        }
    }
}

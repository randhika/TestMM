// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.yjvoice.screen;


// Referenced classes of package jp.co.yahoo.android.yjvoice.screen:
//            YJVOVRecognizer

static class TATE
{

    static final int $SwitchMap$jp$co$yahoo$android$yjvoice$screen$RecognizerScreen$STATE[];

    static 
    {
        $SwitchMap$jp$co$yahoo$android$yjvoice$screen$RecognizerScreen$STATE = new int[TATE.values().length];
        try
        {
            $SwitchMap$jp$co$yahoo$android$yjvoice$screen$RecognizerScreen$STATE[TATE.HIDE.ordinal()] = 1;
        }
        catch (NoSuchFieldError nosuchfielderror) { }
        try
        {
            $SwitchMap$jp$co$yahoo$android$yjvoice$screen$RecognizerScreen$STATE[TATE.INIT.ordinal()] = 2;
        }
        catch (NoSuchFieldError nosuchfielderror1) { }
        try
        {
            $SwitchMap$jp$co$yahoo$android$yjvoice$screen$RecognizerScreen$STATE[TATE.PHRASE.ordinal()] = 3;
        }
        catch (NoSuchFieldError nosuchfielderror2) { }
        try
        {
            $SwitchMap$jp$co$yahoo$android$yjvoice$screen$RecognizerScreen$STATE[TATE.RECOG.ordinal()] = 4;
        }
        catch (NoSuchFieldError nosuchfielderror3) { }
        try
        {
            $SwitchMap$jp$co$yahoo$android$yjvoice$screen$RecognizerScreen$STATE[TATE.ERROR.ordinal()] = 5;
        }
        catch (NoSuchFieldError nosuchfielderror4)
        {
            return;
        }
    }
}

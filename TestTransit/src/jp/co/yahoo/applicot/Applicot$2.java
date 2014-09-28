// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.applicot;


// Referenced classes of package jp.co.yahoo.applicot:
//            Applicot, ReportingInteractionMode

static class teractionMode
{

    static final int $SwitchMap$jp$co$yahoo$applicot$ReportingInteractionMode[];

    static 
    {
        $SwitchMap$jp$co$yahoo$applicot$ReportingInteractionMode = new int[ReportingInteractionMode.values().length];
        try
        {
            $SwitchMap$jp$co$yahoo$applicot$ReportingInteractionMode[ReportingInteractionMode.TOAST.ordinal()] = 1;
        }
        catch (NoSuchFieldError nosuchfielderror) { }
        try
        {
            $SwitchMap$jp$co$yahoo$applicot$ReportingInteractionMode[ReportingInteractionMode.NOTIFICATION.ordinal()] = 2;
        }
        catch (NoSuchFieldError nosuchfielderror1) { }
        try
        {
            $SwitchMap$jp$co$yahoo$applicot$ReportingInteractionMode[ReportingInteractionMode.DIALOG.ordinal()] = 3;
        }
        catch (NoSuchFieldError nosuchfielderror2)
        {
            return;
        }
    }
}

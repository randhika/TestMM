// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.applicot.sender;


// Referenced classes of package jp.co.yahoo.applicot.sender:
//            HttpSender

static class pe
{

    static final int $SwitchMap$jp$co$yahoo$applicot$sender$HttpSender$Method[];
    static final int $SwitchMap$jp$co$yahoo$applicot$sender$HttpSender$Type[];

    static 
    {
        $SwitchMap$jp$co$yahoo$applicot$sender$HttpSender$Method = new int[thod.values().length];
        try
        {
            $SwitchMap$jp$co$yahoo$applicot$sender$HttpSender$Method[thod.POST.ordinal()] = 1;
        }
        catch (NoSuchFieldError nosuchfielderror) { }
        try
        {
            $SwitchMap$jp$co$yahoo$applicot$sender$HttpSender$Method[thod.PUT.ordinal()] = 2;
        }
        catch (NoSuchFieldError nosuchfielderror1) { }
        $SwitchMap$jp$co$yahoo$applicot$sender$HttpSender$Type = new int[pe.values().length];
        try
        {
            $SwitchMap$jp$co$yahoo$applicot$sender$HttpSender$Type[pe.JSON.ordinal()] = 1;
        }
        catch (NoSuchFieldError nosuchfielderror2) { }
        try
        {
            $SwitchMap$jp$co$yahoo$applicot$sender$HttpSender$Type[pe.FORM.ordinal()] = 2;
        }
        catch (NoSuchFieldError nosuchfielderror3)
        {
            return;
        }
    }
}

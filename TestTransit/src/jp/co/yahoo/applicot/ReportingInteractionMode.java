// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.applicot;


public final class ReportingInteractionMode extends Enum
{

    private static final ReportingInteractionMode $VALUES[];
    public static final ReportingInteractionMode DIALOG;
    public static final ReportingInteractionMode NOTIFICATION;
    public static final ReportingInteractionMode SILENT;
    public static final ReportingInteractionMode TOAST;

    private ReportingInteractionMode(String s, int i)
    {
        super(s, i);
    }

    public static ReportingInteractionMode valueOf(String s)
    {
        return (ReportingInteractionMode)Enum.valueOf(jp/co/yahoo/applicot/ReportingInteractionMode, s);
    }

    public static ReportingInteractionMode[] values()
    {
        return (ReportingInteractionMode[])$VALUES.clone();
    }

    static 
    {
        SILENT = new ReportingInteractionMode("SILENT", 0);
        NOTIFICATION = new ReportingInteractionMode("NOTIFICATION", 1);
        TOAST = new ReportingInteractionMode("TOAST", 2);
        DIALOG = new ReportingInteractionMode("DIALOG", 3);
        ReportingInteractionMode areportinginteractionmode[] = new ReportingInteractionMode[4];
        areportinginteractionmode[0] = SILENT;
        areportinginteractionmode[1] = NOTIFICATION;
        areportinginteractionmode[2] = TOAST;
        areportinginteractionmode[3] = DIALOG;
        $VALUES = areportinginteractionmode;
    }
}

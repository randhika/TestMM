// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.yjvoice.screen;


// Referenced classes of package jp.co.yahoo.android.yjvoice.screen:
//            RecognizerScreen

public static final class  extends Enum
{

    private static final .VALUES $VALUES[];
    public static final .VALUES ERROR;
    public static final .VALUES HIDE;
    public static final .VALUES INIT;
    public static final .VALUES PHRASE;
    public static final .VALUES RECOG;

    public static  valueOf(String s)
    {
        return ()Enum.valueOf(jp/co/yahoo/android/yjvoice/screen/RecognizerScreen$STATE, s);
    }

    public static [] values()
    {
        return ([])$VALUES.clone();
    }

    static 
    {
        HIDE = new <init>("HIDE", 0);
        INIT = new <init>("INIT", 1);
        PHRASE = new <init>("PHRASE", 2);
        RECOG = new <init>("RECOG", 3);
        ERROR = new <init>("ERROR", 4);
        E_3B_.clone aclone[] = new <init>[5];
        aclone[0] = HIDE;
        aclone[1] = INIT;
        aclone[2] = PHRASE;
        aclone[3] = RECOG;
        aclone[4] = ERROR;
        $VALUES = aclone;
    }

    private (String s, int i)
    {
        super(s, i);
    }
}

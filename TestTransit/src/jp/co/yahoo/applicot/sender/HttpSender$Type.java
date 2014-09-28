// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.applicot.sender;


// Referenced classes of package jp.co.yahoo.applicot.sender:
//            HttpSender

public static abstract class <init> extends Enum
{

    private static final .VALUES $VALUES[];
    public static final .VALUES FORM;
    public static final .VALUES JSON;

    public static <init> valueOf(String s)
    {
        return (<init>)Enum.valueOf(jp/co/yahoo/applicot/sender/HttpSender$Type, s);
    }

    public static <init>[] values()
    {
        return (<init>[])$VALUES.clone();
    }

    public abstract String getContentType();

    static 
    {
        FORM = new HttpSender.Type("FORM", 0) {

            public String getContentType()
            {
                return "application/x-www-form-urlencoded";
            }

        };
        JSON = new HttpSender.Type("JSON", 1) {

            public String getContentType()
            {
                return "application/json";
            }

        };
        e_3B_.clone aclone[] = new _cls2[2];
        aclone[0] = FORM;
        aclone[1] = JSON;
        $VALUES = aclone;
    }

    private _cls2(String s, int i)
    {
        super(s, i);
    }

    _cls2(String s, int i, _cls2 _pcls2)
    {
        this(s, i);
    }
}

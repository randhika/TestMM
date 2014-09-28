// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.kakao;


// Referenced classes of package jp.co.yahoo.android.apps.transit.kakao:
//            Kakao

private static final class _cls9 extends Enum
{

    private static final .VALUES $VALUES[];
    public static final .VALUES GET;
    public static final .VALUES POST;

    public static _cls9 valueOf(String s)
    {
        return (_cls9)Enum.valueOf(jp/co/yahoo/android/apps/transit/kakao/Kakao$Method, s);
    }

    public static _cls9[] values()
    {
        return (_cls9[])$VALUES.clone();
    }

    static 
    {
        GET = new <init>("GET", 0);
        POST = new <init>("POST", 1);
        d_3B_.clone aclone[] = new <init>[2];
        aclone[0] = GET;
        aclone[1] = POST;
        $VALUES = aclone;
    }

    private _cls9(String s, int i)
    {
        super(s, i);
    }
}

// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.kakao;


// Referenced classes of package jp.co.yahoo.android.apps.transit.kakao:
//            KakaoLinkHelper

public static final class key extends Enum
{

    private static final .VALUES $VALUES[];
    public static final .VALUES CHATROOM_TITLE;
    public static final .VALUES CLIENT_TOKEN;
    public static final .VALUES SERVER_TOKEN;
    private final String key;

    public static key valueOf(String s)
    {
        return (key)Enum.valueOf(jp/co/yahoo/android/apps/transit/kakao/KakaoLinkHelper$Key, s);
    }

    public static key[] values()
    {
        return (key[])$VALUES.clone();
    }

    public String toString()
    {
        return key;
    }

    static 
    {
        CLIENT_TOKEN = new <init>("CLIENT_TOKEN", 0, "clientToken");
        SERVER_TOKEN = new <init>("SERVER_TOKEN", 1, "serverToken");
        CHATROOM_TITLE = new <init>("CHATROOM_TITLE", 2, "title");
        key akey[] = new <init>[3];
        akey[0] = CLIENT_TOKEN;
        akey[1] = SERVER_TOKEN;
        akey[2] = CHATROOM_TITLE;
        $VALUES = akey;
    }

    private (String s, int i, String s1)
    {
        super(s, i);
        key = s1;
    }
}

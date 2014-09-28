// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.kakao;

import android.content.Intent;
import android.net.Uri;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class KakaoLinkHelper
{
    public static final class Key extends Enum
    {

        private static final Key $VALUES[];
        public static final Key CHATROOM_TITLE;
        public static final Key CLIENT_TOKEN;
        public static final Key SERVER_TOKEN;
        private final String key;

        public static Key valueOf(String s)
        {
            return (Key)Enum.valueOf(jp/co/yahoo/android/apps/transit/kakao/KakaoLinkHelper$Key, s);
        }

        public static Key[] values()
        {
            return (Key[])$VALUES.clone();
        }

        public String toString()
        {
            return key;
        }

        static 
        {
            CLIENT_TOKEN = new Key("CLIENT_TOKEN", 0, "clientToken");
            SERVER_TOKEN = new Key("SERVER_TOKEN", 1, "serverToken");
            CHATROOM_TITLE = new Key("CHATROOM_TITLE", 2, "title");
            Key akey[] = new Key[3];
            akey[0] = CLIENT_TOKEN;
            akey[1] = SERVER_TOKEN;
            akey[2] = CHATROOM_TITLE;
            $VALUES = akey;
        }

        private Key(String s, int i, String s1)
        {
            super(s, i);
            key = s1;
        }
    }


    private final Map params = new HashMap();

    public KakaoLinkHelper(Intent intent)
    {
        if (intent != null)
        {
            parse(intent.getData());
        }
    }

    private void parse(Uri uri)
    {
        if (uri != null) goto _L2; else goto _L1
_L1:
        String s;
        return;
_L2:
        if ((s = uri.getQuery()) == null || s.trim().length() <= 0) goto _L1; else goto _L3
_L3:
        StringTokenizer stringtokenizer = new StringTokenizer(s, "&");
_L5:
        if (!stringtokenizer.hasMoreTokens()) goto _L1; else goto _L4
_L4:
        String s2;
        String s3;
        String s1 = stringtokenizer.nextToken();
        int i = s1.indexOf("=");
        s2 = s1.substring(0, i);
        s3 = s1.substring(i + 1);
        String s4 = URLDecoder.decode(s3, "UTF-8");
        s3 = s4;
_L6:
        params.put(s2, s3);
          goto _L5
          goto _L1
        UnsupportedEncodingException unsupportedencodingexception;
        unsupportedencodingexception;
          goto _L6
    }

    public String get(Key key)
    {
        return (String)params.get(key.toString());
    }

    public Map getParams()
    {
        return params;
    }
}

// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.kakao;

import android.content.Intent;
import android.net.Uri;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ChattingPlusHelper
{
    public static final class Key extends Enum
    {

        private static final Key $VALUES[];
        public static final Key CHATROOM_ID;
        public static final Key CHATROOM_TITLE;
        public static final Key CLIENT_TOKEN;
        public static final Key SERVER_TOKEN;
        private final String key;

        public static Key valueOf(String s)
        {
            return (Key)Enum.valueOf(jp/co/yahoo/android/apps/transit/kakao/ChattingPlusHelper$Key, s);
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
            CHATROOM_ID = new Key("CHATROOM_ID", 3, "chatRoomId");
            Key akey[] = new Key[4];
            akey[0] = CLIENT_TOKEN;
            akey[1] = SERVER_TOKEN;
            akey[2] = CHATROOM_TITLE;
            akey[3] = CHATROOM_ID;
            $VALUES = akey;
        }

        private Key(String s, int i, String s1)
        {
            super(s, i);
            key = s1;
        }
    }


    private final Map params;

    public ChattingPlusHelper(Intent intent)
    {
        HashMap hashmap = new HashMap();
        if (intent != null)
        {
            Uri uri = intent.getData();
            if (uri == null)
            {
                params = null;
                return;
            }
            Key akey[] = Key.values();
            int i = akey.length;
            int j = 0;
            while (j < i) 
            {
                Key key = akey[j];
                String s = uri.getQueryParameter(key.toString());
                if (s != null)
                {
                    hashmap.put(key.toString(), s);
                }
                j++;
            }
        }
        params = Collections.unmodifiableMap(hashmap);
    }

    public String get(Key key)
    {
        Map map = params;
        String s = null;
        if (map != null)
        {
            s = (String)params.get(key.toString());
        }
        return s;
    }

    public Map getParams()
    {
        return params;
    }
}

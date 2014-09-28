// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.common;

import android.content.ContentValues;
import android.os.Bundle;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class YDatabaseUtils
{

    public YDatabaseUtils()
    {
    }

    public static Bundle contentValuesToBundle(ContentValues contentvalues)
    {
        Bundle bundle = new Bundle();
        for (Iterator iterator = contentvalues.valueSet().iterator(); iterator.hasNext();)
        {
            java.util.Map.Entry entry = (java.util.Map.Entry)iterator.next();
            String s = (String)entry.getKey();
            Object obj = entry.getValue();
            if (obj instanceof Boolean)
            {
                bundle.putBoolean(s, ((Boolean)obj).booleanValue());
            } else
            if (obj instanceof CharSequence)
            {
                bundle.putCharSequence(s, (CharSequence)obj);
            } else
            if (obj instanceof Integer)
            {
                bundle.putInt(s, ((Integer)obj).intValue());
            } else
            if (obj instanceof Long)
            {
                bundle.putLong(s, ((Long)obj).longValue());
            } else
            {
                throw new IllegalArgumentException((new StringBuilder()).append("not support: ").append(obj.getClass().getName()).toString());
            }
        }

        return bundle;
    }

    public static void putProjectionMapEntry(HashMap hashmap, String s, String s1, String s2)
    {
        hashmap.put(s2, (new StringBuilder()).append(s).append(".").append(s1).append(" AS ").append(s2).toString());
    }
}

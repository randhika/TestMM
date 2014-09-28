// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.common;

import java.util.HashMap;
import java.util.Map;

public class SingletonMap extends HashMap
{

    private static SingletonMap instance = new SingletonMap();
    private static final long serialVersionUID = 1L;

    private SingletonMap()
    {
    }

    private SingletonMap(int i)
    {
    }

    private SingletonMap(int i, float f)
    {
    }

    private SingletonMap(Map map)
    {
    }

    public static SingletonMap getInstance()
    {
        return instance;
    }

}

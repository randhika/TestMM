// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.api.data;

import java.io.Serializable;

public class RailDirectionData
    implements Serializable
{

    private static final long serialVersionUID = 0xb98ce82791f771c7L;
    private String direction;
    private String drivedayKind;
    private String groupid;
    private int nRailTartget;
    private String source;

    public RailDirectionData()
    {
        nRailTartget = 0;
    }

    public String getDirection()
    {
        return direction;
    }

    public String getDrivedayKind()
    {
        return drivedayKind;
    }

    public String getGroupid()
    {
        return groupid;
    }

    public int getRailTartget()
    {
        return nRailTartget;
    }

    public String getSource()
    {
        return source;
    }

    public void setDirection(String s)
    {
        direction = s;
    }

    public void setDrivedayKind(String s)
    {
        drivedayKind = s;
    }

    public void setGroupid(String s)
    {
        groupid = s;
    }

    public void setRailTartget(int i)
    {
        nRailTartget = i;
    }

    public void setSource(String s)
    {
        source = s;
    }

    public String toString()
    {
        return (new StringBuilder()).append(direction).append("\u65B9\u9762").toString();
    }
}

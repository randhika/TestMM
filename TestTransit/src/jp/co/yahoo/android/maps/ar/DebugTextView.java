// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.maps.ar;

import android.content.Context;
import android.widget.TextView;

// Referenced classes of package jp.co.yahoo.android.maps.ar:
//            NavigationMgr

public class DebugTextView extends TextView
{

    private NavigationMgr naviMgr;

    public DebugTextView(Context context, NavigationMgr navigationmgr)
    {
        super(context);
        naviMgr = navigationmgr;
    }

    public void update()
    {
        double d = naviMgr.getDist();
        StringBuilder stringbuilder = new StringBuilder("\u76EE\u7684\u5730\u307E\u3067 ");
        Object aobj[] = new Object[1];
        aobj[0] = Double.valueOf(d);
        StringBuilder stringbuilder1 = (new StringBuilder(String.valueOf(stringbuilder.append(String.format("%.2f ", aobj)).append(" m\n").toString()))).append("\u4F4D\u7F6E\u7CBE\u5EA6 ");
        Object aobj1[] = new Object[1];
        aobj1[0] = Float.valueOf(naviMgr.getAccuracy());
        setText(stringbuilder1.append(String.format("%.2f ", aobj1)).append(" m").toString());
    }
}

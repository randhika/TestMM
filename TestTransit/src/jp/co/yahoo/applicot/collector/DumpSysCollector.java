// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.applicot.collector;

import android.os.Process;
import android.util.Log;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import jp.co.yahoo.applicot.Applicot;

final class DumpSysCollector
{

    DumpSysCollector()
    {
    }

    public static String collectMemInfo()
    {
        StringBuilder stringbuilder = new StringBuilder();
        BufferedReader bufferedreader;
        ArrayList arraylist = new ArrayList();
        arraylist.add("dumpsys");
        arraylist.add("meminfo");
        arraylist.add(Integer.toString(Process.myPid()));
        bufferedreader = new BufferedReader(new InputStreamReader(Runtime.getRuntime().exec((String[])arraylist.toArray(new String[arraylist.size()])).getInputStream()), 8192);
_L3:
        String s = bufferedreader.readLine();
        if (s != null) goto _L2; else goto _L1
_L1:
        return stringbuilder.toString();
_L2:
        stringbuilder.append(s);
        stringbuilder.append("\n");
          goto _L3
        IOException ioexception;
        ioexception;
        Log.e(Applicot.LOG_TAG, "DumpSysCollector.meminfo could not retrieve data", ioexception);
          goto _L1
    }
}

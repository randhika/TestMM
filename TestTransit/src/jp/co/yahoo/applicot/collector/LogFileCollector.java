// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.applicot.collector;

import android.content.Context;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import jp.co.yahoo.applicot.util.BoundedLinkedList;

class LogFileCollector
{

    private LogFileCollector()
    {
    }

    public static String collectLogFile(Context context, String s, int i)
        throws IOException
    {
        BoundedLinkedList boundedlinkedlist = new BoundedLinkedList(i);
        BufferedReader bufferedreader;
        String s1;
        if (s.contains("/"))
        {
            bufferedreader = new BufferedReader(new InputStreamReader(new FileInputStream(s)), 1024);
        } else
        {
            bufferedreader = new BufferedReader(new InputStreamReader(context.openFileInput(s)), 1024);
        }
        for (s1 = bufferedreader.readLine(); s1 != null; s1 = bufferedreader.readLine())
        {
            boundedlinkedlist.add((new StringBuilder()).append(s1).append("\n").toString());
        }

        return boundedlinkedlist.toString();
    }
}

// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.common.agreementlib;

import android.content.Context;
import android.content.res.Resources;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class YCSVReader
{

    private static final String COMMA = ",";

    public YCSVReader()
    {
    }

    public ArrayList readCSV(Context context, int i)
        throws IOException
    {
        ArrayList arraylist = new ArrayList();
        BufferedReader bufferedreader = new BufferedReader(new InputStreamReader(context.getResources().openRawResource(i)));
        ArrayList arraylist1 = new ArrayList();
        do
        {
            String s = bufferedreader.readLine();
            if (s == null)
            {
                break;
            }
            arraylist1.add(s);
        } while (true);
        bufferedreader.close();
        for (Iterator iterator = arraylist1.iterator(); iterator.hasNext(); arraylist.add(((String)iterator.next()).split(","))) { }
        return arraylist;
    }
}

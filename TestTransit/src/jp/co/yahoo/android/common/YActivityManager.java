// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.common;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class YActivityManager
{

    private static final Intent HOME_INTENT;

    public YActivityManager()
    {
    }

    public static ComponentName getForegroundActivities(Context context)
    {
        ActivityManager activitymanager = (ActivityManager)context.getSystemService("activity");
        PackageManager packagemanager = context.getPackageManager();
        List list = activitymanager.getRunningTasks(1000);
        LinkedList linkedlist = new LinkedList();
        for (Iterator iterator = list.iterator(); iterator.hasNext(); linkedlist.add(((android.app.ActivityManager.RunningTaskInfo)iterator.next()).topActivity)) { }
        List list1 = activitymanager.getRunningAppProcesses();
        LinkedList linkedlist1 = new LinkedList();
        Iterator iterator1 = list1.iterator();
        do
        {
            if (!iterator1.hasNext())
            {
                break;
            }
            android.app.ActivityManager.RunningAppProcessInfo runningappprocessinfo = (android.app.ActivityManager.RunningAppProcessInfo)iterator1.next();
            if (runningappprocessinfo.importance == 100)
            {
                int i = 0;
                while (i < runningappprocessinfo.pkgList.length) 
                {
                    linkedlist1.add(runningappprocessinfo.pkgList[i]);
                    i++;
                }
            }
        } while (true);
        List list2 = packagemanager.queryIntentActivities(HOME_INTENT, 0x10000);
        LinkedList linkedlist2 = new LinkedList();
        ResolveInfo resolveinfo;
        for (Iterator iterator2 = list2.iterator(); iterator2.hasNext(); linkedlist2.add(new ComponentName(resolveinfo.activityInfo.packageName, resolveinfo.activityInfo.name)))
        {
            resolveinfo = (ResolveInfo)iterator2.next();
        }

        Iterator iterator3 = linkedlist.iterator();
        ComponentName componentname;
        do
        {
            boolean flag = iterator3.hasNext();
            componentname = null;
            if (!flag)
            {
                break;
            }
            ComponentName componentname1 = (ComponentName)iterator3.next();
            if (!linkedlist1.contains(componentname1.getPackageName()))
            {
                continue;
            }
            boolean flag1 = linkedlist2.contains(componentname1);
            componentname = null;
            if (!flag1)
            {
                componentname = componentname1;
            }
            break;
        } while (true);
        return componentname;
    }

    static 
    {
        HOME_INTENT = new Intent();
        HOME_INTENT.setAction("android.intent.action.MAIN");
        HOME_INTENT.addCategory("android.intent.category.HOME");
        HOME_INTENT.addCategory("android.intent.category.DEFAULT");
    }
}

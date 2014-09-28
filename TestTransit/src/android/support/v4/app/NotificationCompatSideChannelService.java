// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.app;

import android.app.Notification;
import android.app.Service;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.IBinder;
import android.os.RemoteException;

public abstract class NotificationCompatSideChannelService extends Service
{
    private class NotificationSideChannelStub extends INotificationSideChannel.Stub
    {

        final NotificationCompatSideChannelService this$0;

        public void cancel(String s, int i, String s1)
            throws RemoteException
        {
            long l;
            checkPermission(getCallingUid(), s);
            l = clearCallingIdentity();
            NotificationCompatSideChannelService.this.cancel(s, i, s1);
            restoreCallingIdentity(l);
            return;
            Exception exception;
            exception;
            restoreCallingIdentity(l);
            throw exception;
        }

        public void cancelAll(String s)
        {
            long l;
            checkPermission(getCallingUid(), s);
            l = clearCallingIdentity();
            NotificationCompatSideChannelService.this.cancelAll(s);
            restoreCallingIdentity(l);
            return;
            Exception exception;
            exception;
            restoreCallingIdentity(l);
            throw exception;
        }

        public void notify(String s, int i, String s1, Notification notification)
            throws RemoteException
        {
            long l;
            checkPermission(getCallingUid(), s);
            l = clearCallingIdentity();
            NotificationCompatSideChannelService.this.notify(s, i, s1, notification);
            restoreCallingIdentity(l);
            return;
            Exception exception;
            exception;
            restoreCallingIdentity(l);
            throw exception;
        }

        private NotificationSideChannelStub()
        {
            this$0 = NotificationCompatSideChannelService.this;
            super();
        }

    }


    private static final int BUILD_VERSION_CODE_KITKAT_WATCH = 20;

    public NotificationCompatSideChannelService()
    {
    }

    private void checkPermission(int i, String s)
    {
        String as[] = getPackageManager().getPackagesForUid(i);
        int j = as.length;
        for (int k = 0; k < j; k++)
        {
            if (as[k].equals(s))
            {
                return;
            }
        }

        throw new SecurityException((new StringBuilder()).append("NotificationSideChannelService: Uid ").append(i).append(" is not authorized for package ").append(s).toString());
    }

    public abstract void cancel(String s, int i, String s1);

    public abstract void cancelAll(String s);

    public abstract void notify(String s, int i, String s1, Notification notification);

    public IBinder onBind(Intent intent)
    {
        if (!intent.getAction().equals("android.support.BIND_NOTIFICATION_SIDE_CHANNEL") || android.os.Build.VERSION.SDK_INT >= 20)
        {
            return null;
        } else
        {
            return new NotificationSideChannelStub();
        }
    }

}

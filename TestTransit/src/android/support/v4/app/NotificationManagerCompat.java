// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.app;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteException;
import android.util.Log;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

// Referenced classes of package android.support.v4.app:
//            NotificationCompat, INotificationSideChannel, NotificationManagerCompatEclair

public class NotificationManagerCompat
{
    private static class CancelTask
        implements Task
    {

        final boolean all;
        final int id;
        final String packageName;
        final String tag;

        public void send(INotificationSideChannel inotificationsidechannel)
            throws RemoteException
        {
            if (all)
            {
                inotificationsidechannel.cancelAll(packageName);
                return;
            } else
            {
                inotificationsidechannel.cancel(packageName, id, tag);
                return;
            }
        }

        public String toString()
        {
            StringBuilder stringbuilder = new StringBuilder("CancelTask[");
            stringbuilder.append("packageName:").append(packageName);
            stringbuilder.append(", id:").append(id);
            stringbuilder.append(", tag:").append(tag);
            stringbuilder.append(", all:").append(all);
            stringbuilder.append("]");
            return stringbuilder.toString();
        }

        public CancelTask(String s)
        {
            packageName = s;
            id = 0;
            tag = null;
            all = true;
        }

        public CancelTask(String s, int i, String s1)
        {
            packageName = s;
            id = i;
            tag = s1;
            all = false;
        }
    }

    static interface Impl
    {

        public abstract void cancelNotification(NotificationManager notificationmanager, String s, int i);

        public abstract int getSideChannelBindFlags();

        public abstract void postNotification(NotificationManager notificationmanager, String s, int i, Notification notification);
    }

    static class ImplBase
        implements Impl
    {

        public void cancelNotification(NotificationManager notificationmanager, String s, int i)
        {
            notificationmanager.cancel(i);
        }

        public int getSideChannelBindFlags()
        {
            return 1;
        }

        public void postNotification(NotificationManager notificationmanager, String s, int i, Notification notification)
        {
            notificationmanager.notify(i, notification);
        }

        ImplBase()
        {
        }
    }

    static class ImplEclair extends ImplBase
    {

        public void cancelNotification(NotificationManager notificationmanager, String s, int i)
        {
            NotificationManagerCompatEclair.cancelNotification(notificationmanager, s, i);
        }

        public void postNotification(NotificationManager notificationmanager, String s, int i, Notification notification)
        {
            NotificationManagerCompatEclair.postNotification(notificationmanager, s, i, notification);
        }

        ImplEclair()
        {
        }
    }

    static class ImplIceCreamSandwich extends ImplEclair
    {

        public int getSideChannelBindFlags()
        {
            return 33;
        }

        ImplIceCreamSandwich()
        {
        }
    }

    private static class NotifyTask
        implements Task
    {

        final int id;
        final Notification notif;
        final String packageName;
        final String tag;

        public void send(INotificationSideChannel inotificationsidechannel)
            throws RemoteException
        {
            inotificationsidechannel.notify(packageName, id, tag, notif);
        }

        public String toString()
        {
            StringBuilder stringbuilder = new StringBuilder("NotifyTask[");
            stringbuilder.append("packageName:").append(packageName);
            stringbuilder.append(", id:").append(id);
            stringbuilder.append(", tag:").append(tag);
            stringbuilder.append("]");
            return stringbuilder.toString();
        }

        public NotifyTask(String s, int i, String s1, Notification notification)
        {
            packageName = s;
            id = i;
            tag = s1;
            notif = notification;
        }
    }

    private static class ServiceConnectedEvent
    {

        final ComponentName componentName;
        final IBinder iBinder;

        public ServiceConnectedEvent(ComponentName componentname, IBinder ibinder)
        {
            componentName = componentname;
            iBinder = ibinder;
        }
    }

    private static class SideChannelManager
        implements android.os.Handler.Callback, ServiceConnection
    {

        private static final String KEY_BINDER = "binder";
        private static final int MSG_QUEUE_TASK = 0;
        private static final int MSG_RETRY_LISTENER_QUEUE = 3;
        private static final int MSG_SERVICE_CONNECTED = 1;
        private static final int MSG_SERVICE_DISCONNECTED = 2;
        private Set mCachedEnabledPackages;
        private final Context mContext;
        private final Handler mHandler;
        private final HandlerThread mHandlerThread = new HandlerThread("NotificationManagerCompat");
        private final Map mRecordMap = new HashMap();

        private boolean ensureServiceBound(ListenerRecord listenerrecord)
        {
            if (listenerrecord.bound)
            {
                return true;
            }
            Intent intent = (new Intent("android.support.BIND_NOTIFICATION_SIDE_CHANNEL")).setComponent(listenerrecord.componentName);
            listenerrecord.bound = mContext.bindService(intent, this, NotificationManagerCompat.SIDE_CHANNEL_BIND_FLAGS);
            if (listenerrecord.bound)
            {
                listenerrecord.retryCount = 0;
            } else
            {
                Log.w("NotifManCompat", (new StringBuilder()).append("Unable to bind to listener ").append(listenerrecord.componentName).toString());
                mContext.unbindService(this);
            }
            return listenerrecord.bound;
        }

        private void ensureServiceUnbound(ListenerRecord listenerrecord)
        {
            if (listenerrecord.bound)
            {
                mContext.unbindService(this);
                listenerrecord.bound = false;
            }
            listenerrecord.service = null;
        }

        private void handleQueueTask(Task task)
        {
            updateListenerMap();
            ListenerRecord listenerrecord;
            for (Iterator iterator = mRecordMap.values().iterator(); iterator.hasNext(); processListenerQueue(listenerrecord))
            {
                listenerrecord = (ListenerRecord)iterator.next();
                listenerrecord.taskQueue.add(task);
            }

        }

        private void handleRetryListenerQueue(ComponentName componentname)
        {
            ListenerRecord listenerrecord = (ListenerRecord)mRecordMap.get(componentname);
            if (listenerrecord != null)
            {
                processListenerQueue(listenerrecord);
            }
        }

        private void handleServiceConnected(ComponentName componentname, IBinder ibinder)
        {
            ListenerRecord listenerrecord = (ListenerRecord)mRecordMap.get(componentname);
            if (listenerrecord != null)
            {
                listenerrecord.service = INotificationSideChannel.Stub.asInterface(ibinder);
                listenerrecord.retryCount = 0;
                processListenerQueue(listenerrecord);
            }
        }

        private void handleServiceDisconnected(ComponentName componentname)
        {
            ListenerRecord listenerrecord = (ListenerRecord)mRecordMap.get(componentname);
            if (listenerrecord != null)
            {
                ensureServiceUnbound(listenerrecord);
            }
        }

        private void processListenerQueue(ListenerRecord listenerrecord)
        {
            if (Log.isLoggable("NotifManCompat", 3))
            {
                Log.d("NotifManCompat", (new StringBuilder()).append("Processing component ").append(listenerrecord.componentName).append(", ").append(listenerrecord.taskQueue.size()).append(" queued tasks").toString());
            }
            if (!listenerrecord.taskQueue.isEmpty()) goto _L2; else goto _L1
_L1:
            return;
_L2:
            if (!ensureServiceBound(listenerrecord) || listenerrecord.service == null)
            {
                scheduleListenerRetry(listenerrecord);
                return;
            }
              goto _L3
_L5:
            Task task;
            if (Log.isLoggable("NotifManCompat", 3))
            {
                Log.d("NotifManCompat", (new StringBuilder()).append("Sending task ").append(task).toString());
            }
            task.send(listenerrecord.service);
            listenerrecord.taskQueue.remove();
_L3:
            if ((task = (Task)listenerrecord.taskQueue.peek()) != null) goto _L5; else goto _L4
_L4:
            if (!listenerrecord.taskQueue.isEmpty())
            {
                scheduleListenerRetry(listenerrecord);
                return;
            }
            if (true) goto _L1; else goto _L6
_L6:
            DeadObjectException deadobjectexception;
            deadobjectexception;
            if (Log.isLoggable("NotifManCompat", 3))
            {
                Log.d("NotifManCompat", (new StringBuilder()).append("Remote service has died: ").append(listenerrecord.componentName).toString());
            }
              goto _L4
            RemoteException remoteexception;
            remoteexception;
            Log.w("NotifManCompat", (new StringBuilder()).append("RemoteException communicating with ").append(listenerrecord.componentName).toString(), remoteexception);
              goto _L4
        }

        private void scheduleListenerRetry(ListenerRecord listenerrecord)
        {
            if (mHandler.hasMessages(3, listenerrecord.componentName))
            {
                return;
            }
            listenerrecord.retryCount = 1 + listenerrecord.retryCount;
            if (listenerrecord.retryCount > 6)
            {
                Log.w("NotifManCompat", (new StringBuilder()).append("Giving up on delivering ").append(listenerrecord.taskQueue.size()).append(" tasks to ").append(listenerrecord.componentName).append(" after ").append(listenerrecord.retryCount).append(" retries").toString());
                listenerrecord.taskQueue.clear();
                return;
            }
            int i = 1000 * (1 << -1 + listenerrecord.retryCount);
            if (Log.isLoggable("NotifManCompat", 3))
            {
                Log.d("NotifManCompat", (new StringBuilder()).append("Scheduling retry for ").append(i).append(" ms").toString());
            }
            Message message = mHandler.obtainMessage(3, listenerrecord.componentName);
            mHandler.sendMessageDelayed(message, i);
        }

        private void updateListenerMap()
        {
            Set set = NotificationManagerCompat.getEnabledListenerPackages(mContext);
            if (!set.equals(mCachedEnabledPackages))
            {
                mCachedEnabledPackages = set;
                List list = mContext.getPackageManager().queryIntentServices((new Intent()).setAction("android.support.BIND_NOTIFICATION_SIDE_CHANNEL"), 4);
                HashSet hashset = new HashSet();
                Iterator iterator = list.iterator();
                do
                {
                    if (!iterator.hasNext())
                    {
                        break;
                    }
                    ResolveInfo resolveinfo = (ResolveInfo)iterator.next();
                    if (set.contains(resolveinfo.serviceInfo.packageName))
                    {
                        ComponentName componentname1 = new ComponentName(resolveinfo.serviceInfo.packageName, resolveinfo.serviceInfo.name);
                        if (resolveinfo.serviceInfo.permission != null)
                        {
                            Log.w("NotifManCompat", (new StringBuilder()).append("Permission present on component ").append(componentname1).append(", not adding listener record.").toString());
                        } else
                        {
                            hashset.add(componentname1);
                        }
                    }
                } while (true);
                Iterator iterator1 = hashset.iterator();
                do
                {
                    if (!iterator1.hasNext())
                    {
                        break;
                    }
                    ComponentName componentname = (ComponentName)iterator1.next();
                    if (!mRecordMap.containsKey(componentname))
                    {
                        if (Log.isLoggable("NotifManCompat", 3))
                        {
                            Log.d("NotifManCompat", (new StringBuilder()).append("Adding listener record for ").append(componentname).toString());
                        }
                        mRecordMap.put(componentname, new ListenerRecord(componentname));
                    }
                } while (true);
                Iterator iterator2 = mRecordMap.entrySet().iterator();
                while (iterator2.hasNext()) 
                {
                    java.util.Map.Entry entry = (java.util.Map.Entry)iterator2.next();
                    if (!hashset.contains(entry.getKey()))
                    {
                        if (Log.isLoggable("NotifManCompat", 3))
                        {
                            Log.d("NotifManCompat", (new StringBuilder()).append("Removing listener record for ").append(entry.getKey()).toString());
                        }
                        ensureServiceUnbound((ListenerRecord)entry.getValue());
                        iterator2.remove();
                    }
                }
            }
        }

        public boolean handleMessage(Message message)
        {
            switch (message.what)
            {
            default:
                return false;

            case 0: // '\0'
                handleQueueTask((Task)message.obj);
                return true;

            case 1: // '\001'
                ServiceConnectedEvent serviceconnectedevent = (ServiceConnectedEvent)message.obj;
                handleServiceConnected(serviceconnectedevent.componentName, serviceconnectedevent.iBinder);
                return true;

            case 2: // '\002'
                handleServiceDisconnected((ComponentName)message.obj);
                return true;

            case 3: // '\003'
                handleRetryListenerQueue((ComponentName)message.obj);
                break;
            }
            return true;
        }

        public void onServiceConnected(ComponentName componentname, IBinder ibinder)
        {
            if (Log.isLoggable("NotifManCompat", 3))
            {
                Log.d("NotifManCompat", (new StringBuilder()).append("Connected to service ").append(componentname).toString());
            }
            mHandler.obtainMessage(1, new ServiceConnectedEvent(componentname, ibinder)).sendToTarget();
        }

        public void onServiceDisconnected(ComponentName componentname)
        {
            if (Log.isLoggable("NotifManCompat", 3))
            {
                Log.d("NotifManCompat", (new StringBuilder()).append("Disconnected from service ").append(componentname).toString());
            }
            mHandler.obtainMessage(2, componentname).sendToTarget();
        }

        public void queueTask(Task task)
        {
            mHandler.obtainMessage(0, task).sendToTarget();
        }

        public SideChannelManager(Context context)
        {
            mCachedEnabledPackages = new HashSet();
            mContext = context;
            mHandlerThread.start();
            mHandler = new Handler(mHandlerThread.getLooper(), this);
        }
    }

    private static class SideChannelManager.ListenerRecord
    {

        public boolean bound;
        public final ComponentName componentName;
        public int retryCount;
        public INotificationSideChannel service;
        public LinkedList taskQueue;

        public SideChannelManager.ListenerRecord(ComponentName componentname)
        {
            bound = false;
            taskQueue = new LinkedList();
            retryCount = 0;
            componentName = componentname;
        }
    }

    private static interface Task
    {

        public abstract void send(INotificationSideChannel inotificationsidechannel)
            throws RemoteException;
    }


    public static final String ACTION_BIND_SIDE_CHANNEL = "android.support.BIND_NOTIFICATION_SIDE_CHANNEL";
    public static final String EXTRA_USE_SIDE_CHANNEL = "android.support.useSideChannel";
    private static final Impl IMPL;
    private static final String SETTING_ENABLED_NOTIFICATION_LISTENERS = "enabled_notification_listeners";
    private static final int SIDE_CHANNEL_BIND_FLAGS = 0;
    private static final int SIDE_CHANNEL_RETRY_BASE_INTERVAL_MS = 1000;
    private static final int SIDE_CHANNEL_RETRY_MAX_COUNT = 6;
    private static final String TAG = "NotifManCompat";
    private static Set sEnabledNotificationListenerPackages = new HashSet();
    private static String sEnabledNotificationListeners;
    private static final Object sEnabledNotificationListenersLock = new Object();
    private static final Object sLock = new Object();
    private static SideChannelManager sSideChannelManager;
    private final Context mContext;
    private final NotificationManager mNotificationManager;

    private NotificationManagerCompat(Context context)
    {
        mContext = context;
        mNotificationManager = (NotificationManager)mContext.getSystemService("notification");
    }

    public static NotificationManagerCompat from(Context context)
    {
        return new NotificationManagerCompat(context);
    }

    public static Set getEnabledListenerPackages(Context context)
    {
        String s = android.provider.Settings.Secure.getString(context.getContentResolver(), "enabled_notification_listeners");
        if (s != null && !s.equals(sEnabledNotificationListeners))
        {
            String as[] = s.split(":");
            HashSet hashset = new HashSet(as.length);
            int i = as.length;
            for (int j = 0; j < i; j++)
            {
                ComponentName componentname = ComponentName.unflattenFromString(as[j]);
                if (componentname != null)
                {
                    hashset.add(componentname.getPackageName());
                }
            }

            synchronized (sEnabledNotificationListenersLock)
            {
                sEnabledNotificationListenerPackages = hashset;
                sEnabledNotificationListeners = s;
            }
        }
        return sEnabledNotificationListenerPackages;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }

    private void pushSideChannelQueue(Task task)
    {
        synchronized (sLock)
        {
            if (sSideChannelManager == null)
            {
                sSideChannelManager = new SideChannelManager(mContext.getApplicationContext());
            }
        }
        sSideChannelManager.queueTask(task);
        return;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }

    private static boolean useSideChannelForNotification(Notification notification)
    {
        Bundle bundle = NotificationCompat.getExtras(notification);
        return bundle != null && bundle.getBoolean("android.support.useSideChannel");
    }

    public void cancel(int i)
    {
        cancel(null, i);
    }

    public void cancel(String s, int i)
    {
        IMPL.cancelNotification(mNotificationManager, s, i);
        pushSideChannelQueue(new CancelTask(mContext.getPackageName(), i, s));
    }

    public void cancelAll()
    {
        mNotificationManager.cancelAll();
        pushSideChannelQueue(new CancelTask(mContext.getPackageName()));
    }

    public void notify(int i, Notification notification)
    {
        notify(null, i, notification);
    }

    public void notify(String s, int i, Notification notification)
    {
        if (useSideChannelForNotification(notification))
        {
            pushSideChannelQueue(new NotifyTask(mContext.getPackageName(), i, s, notification));
            return;
        } else
        {
            IMPL.postNotification(mNotificationManager, s, i, notification);
            return;
        }
    }

    static 
    {
        if (android.os.Build.VERSION.SDK_INT >= 14)
        {
            IMPL = new ImplIceCreamSandwich();
        } else
        if (android.os.Build.VERSION.SDK_INT >= 5)
        {
            IMPL = new ImplEclair();
        } else
        {
            IMPL = new ImplBase();
        }
        SIDE_CHANNEL_BIND_FLAGS = IMPL.getSideChannelBindFlags();
    }

}

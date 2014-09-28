// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.app;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.widget.RemoteViews;
import java.util.ArrayList;

// Referenced classes of package android.support.v4.app:
//            NotificationCompat

public static class mPriority
{

    ArrayList mActions;
    CharSequence mContentInfo;
    PendingIntent mContentIntent;
    CharSequence mContentText;
    CharSequence mContentTitle;
    Context mContext;
    Bundle mExtras;
    PendingIntent mFullScreenIntent;
    String mGroupKey;
    boolean mGroupSummary;
    Bitmap mLargeIcon;
    boolean mLocalOnly;
    Notification mNotification;
    int mNumber;
    int mPriority;
    int mProgress;
    boolean mProgressIndeterminate;
    int mProgressMax;
    String mSortKey;
    mNotification mStyle;
    CharSequence mSubText;
    RemoteViews mTickerView;
    boolean mUseChronometer;

    private void setFlag(int i, boolean flag)
    {
        if (flag)
        {
            Notification notification1 = mNotification;
            notification1.flags = i | notification1.flags;
            return;
        } else
        {
            Notification notification = mNotification;
            notification.flags = notification.flags & ~i;
            return;
        }
    }

    public mNotification addAction(int i, CharSequence charsequence, PendingIntent pendingintent)
    {
        mActions.add(new init>(i, charsequence, pendingintent));
        return this;
    }

    public init> addAction(init> init>)
    {
        mActions.add(init>);
        return this;
    }

    public mActions addExtras(Bundle bundle)
    {
label0:
        {
            if (bundle != null)
            {
                if (mExtras != null)
                {
                    break label0;
                }
                mExtras = new Bundle(bundle);
            }
            return this;
        }
        mExtras.putAll(bundle);
        return this;
    }

    public Notification build()
    {
        return NotificationCompat.access$200().build(this);
    }

    public  extend( )
    {
        .extend(this);
        return this;
    }

    public Bundle getExtras()
    {
        if (mExtras == null)
        {
            mExtras = new Bundle();
        }
        return mExtras;
    }

    public Notification getNotification()
    {
        return NotificationCompat.access$200().build(this);
    }

    public tionCompatImpl.build setAutoCancel(boolean flag)
    {
        setFlag(16, flag);
        return this;
    }

    public setFlag setContent(RemoteViews remoteviews)
    {
        mNotification.contentView = remoteviews;
        return this;
    }

    public mNotification setContentInfo(CharSequence charsequence)
    {
        mContentInfo = charsequence;
        return this;
    }

    public mContentInfo setContentIntent(PendingIntent pendingintent)
    {
        mContentIntent = pendingintent;
        return this;
    }

    public mContentIntent setContentText(CharSequence charsequence)
    {
        mContentText = charsequence;
        return this;
    }

    public mContentText setContentTitle(CharSequence charsequence)
    {
        mContentTitle = charsequence;
        return this;
    }

    public mContentTitle setDefaults(int i)
    {
        mNotification.defaults = i;
        if ((i & 4) != 0)
        {
            Notification notification = mNotification;
            notification.flags = 1 | notification.flags;
        }
        return this;
    }

    public mNotification setDeleteIntent(PendingIntent pendingintent)
    {
        mNotification.deleteIntent = pendingintent;
        return this;
    }

    public mNotification setExtras(Bundle bundle)
    {
        mExtras = bundle;
        return this;
    }

    public mExtras setFullScreenIntent(PendingIntent pendingintent, boolean flag)
    {
        mFullScreenIntent = pendingintent;
        setFlag(128, flag);
        return this;
    }

    public setFlag setGroup(String s)
    {
        mGroupKey = s;
        return this;
    }

    public mGroupKey setGroupSummary(boolean flag)
    {
        mGroupSummary = flag;
        return this;
    }

    public mGroupSummary setLargeIcon(Bitmap bitmap)
    {
        mLargeIcon = bitmap;
        return this;
    }

    public mLargeIcon setLights(int i, int j, int k)
    {
        boolean flag = true;
        mNotification.ledARGB = i;
        mNotification.ledOnMS = j;
        mNotification.ledOffMS = k;
        boolean flag1;
        Notification notification;
        int l;
        if (mNotification.ledOnMS != 0 && mNotification.ledOffMS != 0)
        {
            flag1 = flag;
        } else
        {
            flag1 = false;
        }
        notification = mNotification;
        l = -2 & mNotification.flags;
        if (!flag1)
        {
            flag = false;
        }
        notification.flags = flag | l;
        return this;
    }

    public mNotification setLocalOnly(boolean flag)
    {
        mLocalOnly = flag;
        return this;
    }

    public mLocalOnly setNumber(int i)
    {
        mNumber = i;
        return this;
    }

    public mNumber setOngoing(boolean flag)
    {
        setFlag(2, flag);
        return this;
    }

    public setFlag setOnlyAlertOnce(boolean flag)
    {
        setFlag(8, flag);
        return this;
    }

    public setFlag setPriority(int i)
    {
        mPriority = i;
        return this;
    }

    public mPriority setProgress(int i, int j, boolean flag)
    {
        mProgressMax = i;
        mProgress = j;
        mProgressIndeterminate = flag;
        return this;
    }

    public mProgressIndeterminate setSmallIcon(int i)
    {
        mNotification.icon = i;
        return this;
    }

    public mNotification setSmallIcon(int i, int j)
    {
        mNotification.icon = i;
        mNotification.iconLevel = j;
        return this;
    }

    public mNotification setSortKey(String s)
    {
        mSortKey = s;
        return this;
    }

    public mSortKey setSound(Uri uri)
    {
        mNotification.sound = uri;
        mNotification.audioStreamType = -1;
        return this;
    }

    public mNotification setSound(Uri uri, int i)
    {
        mNotification.sound = uri;
        mNotification.audioStreamType = i;
        return this;
    }

    public mNotification setStyle(mNotification mnotification)
    {
        if (mStyle != mnotification)
        {
            mStyle = mnotification;
            if (mStyle != null)
            {
                mStyle.tBuilder(this);
            }
        }
        return this;
    }

    public tBuilder setSubText(CharSequence charsequence)
    {
        mSubText = charsequence;
        return this;
    }

    public mSubText setTicker(CharSequence charsequence)
    {
        mNotification.tickerText = charsequence;
        return this;
    }

    public mNotification setTicker(CharSequence charsequence, RemoteViews remoteviews)
    {
        mNotification.tickerText = charsequence;
        mTickerView = remoteviews;
        return this;
    }

    public mTickerView setUsesChronometer(boolean flag)
    {
        mUseChronometer = flag;
        return this;
    }

    public mUseChronometer setVibrate(long al[])
    {
        mNotification.vibrate = al;
        return this;
    }

    public mNotification setWhen(long l)
    {
        mNotification.when = l;
        return this;
    }

    public (Context context)
    {
        mActions = new ArrayList();
        mLocalOnly = false;
        mNotification = new Notification();
        mContext = context;
        mNotification.when = System.currentTimeMillis();
        mNotification.audioStreamType = -1;
        mPriority = 0;
    }
}

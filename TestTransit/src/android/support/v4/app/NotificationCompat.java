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
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

// Referenced classes of package android.support.v4.app:
//            NotificationBuilderWithActions, NotificationCompatJellybean, NotificationBuilderWithBuilderAccessor, RemoteInput, 
//            NotificationCompatApi20, NotificationCompatKitKat, NotificationCompatGingerbread, NotificationCompatHoneycomb, 
//            NotificationCompatIceCreamSandwich

public class NotificationCompat
{
    public static class Action extends NotificationCompatBase.Action
    {

        public static final NotificationCompatBase.Action.Factory FACTORY = new NotificationCompatBase.Action.Factory() {

            public Action build(int i, CharSequence charsequence, PendingIntent pendingintent, Bundle bundle, RemoteInputCompatBase.RemoteInput aremoteinput[])
            {
                return new Action(i, charsequence, pendingintent, bundle, (RemoteInput[])(RemoteInput[])aremoteinput);
            }

            public volatile NotificationCompatBase.Action build(int i, CharSequence charsequence, PendingIntent pendingintent, Bundle bundle, RemoteInputCompatBase.RemoteInput aremoteinput[])
            {
                return build(i, charsequence, pendingintent, bundle, aremoteinput);
            }

            public Action[] newArray(int i)
            {
                return new Action[i];
            }

            public volatile NotificationCompatBase.Action[] newArray(int i)
            {
                return newArray(i);
            }

        };
        public PendingIntent actionIntent;
        public int icon;
        private final Bundle mExtras;
        private final RemoteInput mRemoteInputs[];
        public CharSequence title;

        protected PendingIntent getActionIntent()
        {
            return actionIntent;
        }

        public Bundle getExtras()
        {
            return mExtras;
        }

        protected int getIcon()
        {
            return icon;
        }

        public RemoteInput[] getRemoteInputs()
        {
            return mRemoteInputs;
        }

        public volatile RemoteInputCompatBase.RemoteInput[] getRemoteInputs()
        {
            return getRemoteInputs();
        }

        protected CharSequence getTitle()
        {
            return title;
        }



        public Action(int i, CharSequence charsequence, PendingIntent pendingintent)
        {
            this(i, charsequence, pendingintent, new Bundle(), null);
        }

        private Action(int i, CharSequence charsequence, PendingIntent pendingintent, Bundle bundle, RemoteInput aremoteinput[])
        {
            icon = i;
            title = charsequence;
            actionIntent = pendingintent;
            if (bundle == null)
            {
                bundle = new Bundle();
            }
            mExtras = bundle;
            mRemoteInputs = aremoteinput;
        }

    }

    public static final class Action.Builder
    {

        private final Bundle mExtras;
        private final int mIcon;
        private final PendingIntent mIntent;
        private ArrayList mRemoteInputs;
        private final CharSequence mTitle;

        public Action.Builder addExtras(Bundle bundle)
        {
            if (bundle != null)
            {
                mExtras.putAll(bundle);
            }
            return this;
        }

        public Action.Builder addRemoteInput(RemoteInput remoteinput)
        {
            if (mRemoteInputs == null)
            {
                mRemoteInputs = new ArrayList();
            }
            mRemoteInputs.add(remoteinput);
            return this;
        }

        public Action build()
        {
            RemoteInput aremoteinput[];
            if (mRemoteInputs != null)
            {
                aremoteinput = (RemoteInput[])mRemoteInputs.toArray(new RemoteInput[mRemoteInputs.size()]);
            } else
            {
                aremoteinput = null;
            }
            return new Action(mIcon, mTitle, mIntent, mExtras, aremoteinput);
        }

        public Action.Builder extend(Action.Extender extender)
        {
            extender.extend(this);
            return this;
        }

        public Bundle getExtras()
        {
            return mExtras;
        }

        public Action.Builder(int i, CharSequence charsequence, PendingIntent pendingintent)
        {
            this(i, charsequence, pendingintent, new Bundle());
        }

        private Action.Builder(int i, CharSequence charsequence, PendingIntent pendingintent, Bundle bundle)
        {
            mIcon = i;
            mTitle = charsequence;
            mIntent = pendingintent;
            mExtras = bundle;
        }

        public Action.Builder(Action action)
        {
            this(action.icon, action.title, action.actionIntent, new Bundle(action.mExtras));
        }
    }

    public static interface Action.Extender
    {

        public abstract Action.Builder extend(Action.Builder builder);
    }

    public static final class Action.WearableExtender
        implements Action.Extender
    {

        private static final int DEFAULT_FLAGS = 1;
        private static final String EXTRA_WEARABLE_EXTENSIONS = "android.wearable.EXTENSIONS";
        private static final int FLAG_AVAILABLE_OFFLINE = 1;
        private static final String KEY_FLAGS = "flags";
        private int mFlags;

        private void setFlag(int i, boolean flag)
        {
            if (flag)
            {
                mFlags = i | mFlags;
                return;
            } else
            {
                mFlags = mFlags & ~i;
                return;
            }
        }

        public Action.WearableExtender clone()
        {
            Action.WearableExtender wearableextender = new Action.WearableExtender();
            wearableextender.mFlags = mFlags;
            return wearableextender;
        }

        public volatile Object clone()
            throws CloneNotSupportedException
        {
            return clone();
        }

        public Action.Builder extend(Action.Builder builder)
        {
            Bundle bundle = new Bundle();
            if (mFlags != 1)
            {
                bundle.putInt("flags", mFlags);
            }
            builder.getExtras().putBundle("android.wearable.EXTENSIONS", bundle);
            return builder;
        }

        public boolean isAvailableOffline()
        {
            return (1 & mFlags) != 0;
        }

        public Action.WearableExtender setAvailableOffline(boolean flag)
        {
            setFlag(1, flag);
            return this;
        }

        public Action.WearableExtender()
        {
            mFlags = 1;
        }

        public Action.WearableExtender(Action action)
        {
            mFlags = 1;
            Bundle bundle = action.getExtras().getBundle("android.wearable.EXTENSIONS");
            if (bundle != null)
            {
                mFlags = bundle.getInt("flags", 1);
            }
        }
    }

    public static class BigPictureStyle extends Style
    {

        Bitmap mBigLargeIcon;
        boolean mBigLargeIconSet;
        Bitmap mPicture;

        public BigPictureStyle bigLargeIcon(Bitmap bitmap)
        {
            mBigLargeIcon = bitmap;
            mBigLargeIconSet = true;
            return this;
        }

        public BigPictureStyle bigPicture(Bitmap bitmap)
        {
            mPicture = bitmap;
            return this;
        }

        public BigPictureStyle setBigContentTitle(CharSequence charsequence)
        {
            mBigContentTitle = charsequence;
            return this;
        }

        public BigPictureStyle setSummaryText(CharSequence charsequence)
        {
            mSummaryText = charsequence;
            mSummaryTextSet = true;
            return this;
        }

        public BigPictureStyle()
        {
        }

        public BigPictureStyle(Builder builder)
        {
            setBuilder(builder);
        }
    }

    public static class BigTextStyle extends Style
    {

        CharSequence mBigText;

        public BigTextStyle bigText(CharSequence charsequence)
        {
            mBigText = charsequence;
            return this;
        }

        public BigTextStyle setBigContentTitle(CharSequence charsequence)
        {
            mBigContentTitle = charsequence;
            return this;
        }

        public BigTextStyle setSummaryText(CharSequence charsequence)
        {
            mSummaryText = charsequence;
            mSummaryTextSet = true;
            return this;
        }

        public BigTextStyle()
        {
        }

        public BigTextStyle(Builder builder)
        {
            setBuilder(builder);
        }
    }

    public static class Builder
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
        Style mStyle;
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

        public Builder addAction(int i, CharSequence charsequence, PendingIntent pendingintent)
        {
            mActions.add(new Action(i, charsequence, pendingintent));
            return this;
        }

        public Builder addAction(Action action)
        {
            mActions.add(action);
            return this;
        }

        public Builder addExtras(Bundle bundle)
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
            return NotificationCompat.IMPL.build(this);
        }

        public Builder extend(Extender extender)
        {
            extender.extend(this);
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
            return NotificationCompat.IMPL.build(this);
        }

        public Builder setAutoCancel(boolean flag)
        {
            setFlag(16, flag);
            return this;
        }

        public Builder setContent(RemoteViews remoteviews)
        {
            mNotification.contentView = remoteviews;
            return this;
        }

        public Builder setContentInfo(CharSequence charsequence)
        {
            mContentInfo = charsequence;
            return this;
        }

        public Builder setContentIntent(PendingIntent pendingintent)
        {
            mContentIntent = pendingintent;
            return this;
        }

        public Builder setContentText(CharSequence charsequence)
        {
            mContentText = charsequence;
            return this;
        }

        public Builder setContentTitle(CharSequence charsequence)
        {
            mContentTitle = charsequence;
            return this;
        }

        public Builder setDefaults(int i)
        {
            mNotification.defaults = i;
            if ((i & 4) != 0)
            {
                Notification notification = mNotification;
                notification.flags = 1 | notification.flags;
            }
            return this;
        }

        public Builder setDeleteIntent(PendingIntent pendingintent)
        {
            mNotification.deleteIntent = pendingintent;
            return this;
        }

        public Builder setExtras(Bundle bundle)
        {
            mExtras = bundle;
            return this;
        }

        public Builder setFullScreenIntent(PendingIntent pendingintent, boolean flag)
        {
            mFullScreenIntent = pendingintent;
            setFlag(128, flag);
            return this;
        }

        public Builder setGroup(String s)
        {
            mGroupKey = s;
            return this;
        }

        public Builder setGroupSummary(boolean flag)
        {
            mGroupSummary = flag;
            return this;
        }

        public Builder setLargeIcon(Bitmap bitmap)
        {
            mLargeIcon = bitmap;
            return this;
        }

        public Builder setLights(int i, int j, int k)
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

        public Builder setLocalOnly(boolean flag)
        {
            mLocalOnly = flag;
            return this;
        }

        public Builder setNumber(int i)
        {
            mNumber = i;
            return this;
        }

        public Builder setOngoing(boolean flag)
        {
            setFlag(2, flag);
            return this;
        }

        public Builder setOnlyAlertOnce(boolean flag)
        {
            setFlag(8, flag);
            return this;
        }

        public Builder setPriority(int i)
        {
            mPriority = i;
            return this;
        }

        public Builder setProgress(int i, int j, boolean flag)
        {
            mProgressMax = i;
            mProgress = j;
            mProgressIndeterminate = flag;
            return this;
        }

        public Builder setSmallIcon(int i)
        {
            mNotification.icon = i;
            return this;
        }

        public Builder setSmallIcon(int i, int j)
        {
            mNotification.icon = i;
            mNotification.iconLevel = j;
            return this;
        }

        public Builder setSortKey(String s)
        {
            mSortKey = s;
            return this;
        }

        public Builder setSound(Uri uri)
        {
            mNotification.sound = uri;
            mNotification.audioStreamType = -1;
            return this;
        }

        public Builder setSound(Uri uri, int i)
        {
            mNotification.sound = uri;
            mNotification.audioStreamType = i;
            return this;
        }

        public Builder setStyle(Style style)
        {
            if (mStyle != style)
            {
                mStyle = style;
                if (mStyle != null)
                {
                    mStyle.setBuilder(this);
                }
            }
            return this;
        }

        public Builder setSubText(CharSequence charsequence)
        {
            mSubText = charsequence;
            return this;
        }

        public Builder setTicker(CharSequence charsequence)
        {
            mNotification.tickerText = charsequence;
            return this;
        }

        public Builder setTicker(CharSequence charsequence, RemoteViews remoteviews)
        {
            mNotification.tickerText = charsequence;
            mTickerView = remoteviews;
            return this;
        }

        public Builder setUsesChronometer(boolean flag)
        {
            mUseChronometer = flag;
            return this;
        }

        public Builder setVibrate(long al[])
        {
            mNotification.vibrate = al;
            return this;
        }

        public Builder setWhen(long l)
        {
            mNotification.when = l;
            return this;
        }

        public Builder(Context context)
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

    public static interface Extender
    {

        public abstract Builder extend(Builder builder);
    }

    public static class InboxStyle extends Style
    {

        ArrayList mTexts;

        public InboxStyle addLine(CharSequence charsequence)
        {
            mTexts.add(charsequence);
            return this;
        }

        public InboxStyle setBigContentTitle(CharSequence charsequence)
        {
            mBigContentTitle = charsequence;
            return this;
        }

        public InboxStyle setSummaryText(CharSequence charsequence)
        {
            mSummaryText = charsequence;
            mSummaryTextSet = true;
            return this;
        }

        public InboxStyle()
        {
            mTexts = new ArrayList();
        }

        public InboxStyle(Builder builder)
        {
            mTexts = new ArrayList();
            setBuilder(builder);
        }
    }

    static interface NotificationCompatImpl
    {

        public abstract Notification build(Builder builder);

        public abstract Action getAction(Notification notification, int i);

        public abstract int getActionCount(Notification notification);

        public abstract Action[] getActionsFromParcelableArrayList(ArrayList arraylist);

        public abstract Bundle getExtras(Notification notification);

        public abstract String getGroup(Notification notification);

        public abstract boolean getLocalOnly(Notification notification);

        public abstract ArrayList getParcelableArrayListForActions(Action aaction[]);

        public abstract String getSortKey(Notification notification);

        public abstract boolean isGroupSummary(Notification notification);
    }

    static class NotificationCompatImplApi20 extends NotificationCompatImplKitKat
    {

        public Notification build(Builder builder)
        {
            NotificationCompatApi20.Builder builder1 = new NotificationCompatApi20.Builder(builder.mContext, builder.mNotification, builder.mContentTitle, builder.mContentText, builder.mContentInfo, builder.mTickerView, builder.mNumber, builder.mContentIntent, builder.mFullScreenIntent, builder.mLargeIcon, builder.mProgressMax, builder.mProgress, builder.mProgressIndeterminate, builder.mUseChronometer, builder.mPriority, builder.mSubText, builder.mLocalOnly, builder.mExtras, builder.mGroupKey, builder.mGroupSummary, builder.mSortKey);
            NotificationCompat.addActionsToBuilder(builder1, builder.mActions);
            NotificationCompat.addStyleToBuilderJellybean(builder1, builder.mStyle);
            return builder1.build();
        }

        public Action getAction(Notification notification, int i)
        {
            return (Action)NotificationCompatApi20.getAction(notification, i, Action.FACTORY, RemoteInput.FACTORY);
        }

        public Action[] getActionsFromParcelableArrayList(ArrayList arraylist)
        {
            return (Action[])(Action[])NotificationCompatApi20.getActionsFromParcelableArrayList(arraylist, Action.FACTORY, RemoteInput.FACTORY);
        }

        public String getGroup(Notification notification)
        {
            return NotificationCompatApi20.getGroup(notification);
        }

        public boolean getLocalOnly(Notification notification)
        {
            return NotificationCompatApi20.getLocalOnly(notification);
        }

        public ArrayList getParcelableArrayListForActions(Action aaction[])
        {
            return NotificationCompatApi20.getParcelableArrayListForActions(aaction);
        }

        public String getSortKey(Notification notification)
        {
            return NotificationCompatApi20.getSortKey(notification);
        }

        public boolean isGroupSummary(Notification notification)
        {
            return NotificationCompatApi20.isGroupSummary(notification);
        }

        NotificationCompatImplApi20()
        {
        }
    }

    static class NotificationCompatImplBase
        implements NotificationCompatImpl
    {

        public Notification build(Builder builder)
        {
            Notification notification = builder.mNotification;
            notification.setLatestEventInfo(builder.mContext, builder.mContentTitle, builder.mContentText, builder.mContentIntent);
            if (builder.mPriority > 0)
            {
                notification.flags = 0x80 | notification.flags;
            }
            return notification;
        }

        public Action getAction(Notification notification, int i)
        {
            return null;
        }

        public int getActionCount(Notification notification)
        {
            return 0;
        }

        public Action[] getActionsFromParcelableArrayList(ArrayList arraylist)
        {
            return null;
        }

        public Bundle getExtras(Notification notification)
        {
            return null;
        }

        public String getGroup(Notification notification)
        {
            return null;
        }

        public boolean getLocalOnly(Notification notification)
        {
            return false;
        }

        public ArrayList getParcelableArrayListForActions(Action aaction[])
        {
            return null;
        }

        public String getSortKey(Notification notification)
        {
            return null;
        }

        public boolean isGroupSummary(Notification notification)
        {
            return false;
        }

        NotificationCompatImplBase()
        {
        }
    }

    static class NotificationCompatImplGingerbread extends NotificationCompatImplBase
    {

        public Notification build(Builder builder)
        {
            Notification notification = builder.mNotification;
            notification.setLatestEventInfo(builder.mContext, builder.mContentTitle, builder.mContentText, builder.mContentIntent);
            Notification notification1 = NotificationCompatGingerbread.add(notification, builder.mContext, builder.mContentTitle, builder.mContentText, builder.mContentIntent, builder.mFullScreenIntent);
            if (builder.mPriority > 0)
            {
                notification1.flags = 0x80 | notification1.flags;
            }
            return notification1;
        }

        NotificationCompatImplGingerbread()
        {
        }
    }

    static class NotificationCompatImplHoneycomb extends NotificationCompatImplBase
    {

        public Notification build(Builder builder)
        {
            return NotificationCompatHoneycomb.add(builder.mContext, builder.mNotification, builder.mContentTitle, builder.mContentText, builder.mContentInfo, builder.mTickerView, builder.mNumber, builder.mContentIntent, builder.mFullScreenIntent, builder.mLargeIcon);
        }

        NotificationCompatImplHoneycomb()
        {
        }
    }

    static class NotificationCompatImplIceCreamSandwich extends NotificationCompatImplBase
    {

        public Notification build(Builder builder)
        {
            return NotificationCompatIceCreamSandwich.add(builder.mContext, builder.mNotification, builder.mContentTitle, builder.mContentText, builder.mContentInfo, builder.mTickerView, builder.mNumber, builder.mContentIntent, builder.mFullScreenIntent, builder.mLargeIcon, builder.mProgressMax, builder.mProgress, builder.mProgressIndeterminate);
        }

        NotificationCompatImplIceCreamSandwich()
        {
        }
    }

    static class NotificationCompatImplJellybean extends NotificationCompatImplBase
    {

        public Notification build(Builder builder)
        {
            NotificationCompatJellybean.Builder builder1 = new NotificationCompatJellybean.Builder(builder.mContext, builder.mNotification, builder.mContentTitle, builder.mContentText, builder.mContentInfo, builder.mTickerView, builder.mNumber, builder.mContentIntent, builder.mFullScreenIntent, builder.mLargeIcon, builder.mProgressMax, builder.mProgress, builder.mProgressIndeterminate, builder.mUseChronometer, builder.mPriority, builder.mSubText, builder.mLocalOnly, builder.mExtras, builder.mGroupKey, builder.mGroupSummary, builder.mSortKey);
            NotificationCompat.addActionsToBuilder(builder1, builder.mActions);
            NotificationCompat.addStyleToBuilderJellybean(builder1, builder.mStyle);
            return builder1.build();
        }

        public Action getAction(Notification notification, int i)
        {
            return (Action)NotificationCompatJellybean.getAction(notification, i, Action.FACTORY, RemoteInput.FACTORY);
        }

        public int getActionCount(Notification notification)
        {
            return NotificationCompatJellybean.getActionCount(notification);
        }

        public Action[] getActionsFromParcelableArrayList(ArrayList arraylist)
        {
            return (Action[])(Action[])NotificationCompatJellybean.getActionsFromParcelableArrayList(arraylist, Action.FACTORY, RemoteInput.FACTORY);
        }

        public Bundle getExtras(Notification notification)
        {
            return NotificationCompatJellybean.getExtras(notification);
        }

        public String getGroup(Notification notification)
        {
            return NotificationCompatJellybean.getGroup(notification);
        }

        public boolean getLocalOnly(Notification notification)
        {
            return NotificationCompatJellybean.getLocalOnly(notification);
        }

        public ArrayList getParcelableArrayListForActions(Action aaction[])
        {
            return NotificationCompatJellybean.getParcelableArrayListForActions(aaction);
        }

        public String getSortKey(Notification notification)
        {
            return NotificationCompatJellybean.getSortKey(notification);
        }

        public boolean isGroupSummary(Notification notification)
        {
            return NotificationCompatJellybean.isGroupSummary(notification);
        }

        NotificationCompatImplJellybean()
        {
        }
    }

    static class NotificationCompatImplKitKat extends NotificationCompatImplJellybean
    {

        public Notification build(Builder builder)
        {
            NotificationCompatKitKat.Builder builder1 = new NotificationCompatKitKat.Builder(builder.mContext, builder.mNotification, builder.mContentTitle, builder.mContentText, builder.mContentInfo, builder.mTickerView, builder.mNumber, builder.mContentIntent, builder.mFullScreenIntent, builder.mLargeIcon, builder.mProgressMax, builder.mProgress, builder.mProgressIndeterminate, builder.mUseChronometer, builder.mPriority, builder.mSubText, builder.mLocalOnly, builder.mExtras, builder.mGroupKey, builder.mGroupSummary, builder.mSortKey);
            NotificationCompat.addActionsToBuilder(builder1, builder.mActions);
            NotificationCompat.addStyleToBuilderJellybean(builder1, builder.mStyle);
            return builder1.build();
        }

        public Action getAction(Notification notification, int i)
        {
            return (Action)NotificationCompatKitKat.getAction(notification, i, Action.FACTORY, RemoteInput.FACTORY);
        }

        public int getActionCount(Notification notification)
        {
            return NotificationCompatKitKat.getActionCount(notification);
        }

        public Bundle getExtras(Notification notification)
        {
            return NotificationCompatKitKat.getExtras(notification);
        }

        public String getGroup(Notification notification)
        {
            return NotificationCompatKitKat.getGroup(notification);
        }

        public boolean getLocalOnly(Notification notification)
        {
            return NotificationCompatKitKat.getLocalOnly(notification);
        }

        public String getSortKey(Notification notification)
        {
            return NotificationCompatKitKat.getSortKey(notification);
        }

        public boolean isGroupSummary(Notification notification)
        {
            return NotificationCompatKitKat.isGroupSummary(notification);
        }

        NotificationCompatImplKitKat()
        {
        }
    }

    public static abstract class Style
    {

        CharSequence mBigContentTitle;
        Builder mBuilder;
        CharSequence mSummaryText;
        boolean mSummaryTextSet;

        public Notification build()
        {
            Builder builder = mBuilder;
            Notification notification = null;
            if (builder != null)
            {
                notification = mBuilder.build();
            }
            return notification;
        }

        public void setBuilder(Builder builder)
        {
            if (mBuilder != builder)
            {
                mBuilder = builder;
                if (mBuilder != null)
                {
                    mBuilder.setStyle(this);
                }
            }
        }

        public Style()
        {
            mSummaryTextSet = false;
        }
    }

    public static final class WearableExtender
        implements Extender
    {

        private static final int DEFAULT_CONTENT_ICON_GRAVITY = 0x800005;
        private static final int DEFAULT_FLAGS = 1;
        private static final int DEFAULT_GRAVITY = 80;
        private static final String EXTRA_WEARABLE_EXTENSIONS = "android.wearable.EXTENSIONS";
        private static final int FLAG_CONTENT_INTENT_AVAILABLE_OFFLINE = 1;
        private static final int FLAG_HINT_HIDE_ICON = 2;
        private static final int FLAG_HINT_SHOW_BACKGROUND_ONLY = 4;
        private static final int FLAG_START_SCROLL_BOTTOM = 8;
        private static final String KEY_ACTIONS = "actions";
        private static final String KEY_BACKGROUND = "background";
        private static final String KEY_CONTENT_ACTION_INDEX = "contentActionIndex";
        private static final String KEY_CONTENT_ICON = "contentIcon";
        private static final String KEY_CONTENT_ICON_GRAVITY = "contentIconGravity";
        private static final String KEY_CUSTOM_CONTENT_HEIGHT = "customContentHeight";
        private static final String KEY_CUSTOM_SIZE_PRESET = "customSizePreset";
        private static final String KEY_DISPLAY_INTENT = "displayIntent";
        private static final String KEY_FLAGS = "flags";
        private static final String KEY_GRAVITY = "gravity";
        private static final String KEY_PAGES = "pages";
        public static final int SIZE_DEFAULT = 0;
        public static final int SIZE_FULL_SCREEN = 5;
        public static final int SIZE_LARGE = 4;
        public static final int SIZE_MEDIUM = 3;
        public static final int SIZE_SMALL = 2;
        public static final int SIZE_XSMALL = 1;
        public static final int UNSET_ACTION_INDEX = -1;
        private ArrayList mActions;
        private Bitmap mBackground;
        private int mContentActionIndex;
        private int mContentIcon;
        private int mContentIconGravity;
        private int mCustomContentHeight;
        private int mCustomSizePreset;
        private PendingIntent mDisplayIntent;
        private int mFlags;
        private int mGravity;
        private ArrayList mPages;

        private void setFlag(int i, boolean flag)
        {
            if (flag)
            {
                mFlags = i | mFlags;
                return;
            } else
            {
                mFlags = mFlags & ~i;
                return;
            }
        }

        public WearableExtender addAction(Action action)
        {
            mActions.add(action);
            return this;
        }

        public WearableExtender addActions(List list)
        {
            mActions.addAll(list);
            return this;
        }

        public WearableExtender addPage(Notification notification)
        {
            mPages.add(notification);
            return this;
        }

        public WearableExtender addPages(List list)
        {
            mPages.addAll(list);
            return this;
        }

        public WearableExtender clearActions()
        {
            mActions.clear();
            return this;
        }

        public WearableExtender clearPages()
        {
            mPages.clear();
            return this;
        }

        public WearableExtender clone()
        {
            WearableExtender wearableextender = new WearableExtender();
            wearableextender.mActions = new ArrayList(mActions);
            wearableextender.mFlags = mFlags;
            wearableextender.mDisplayIntent = mDisplayIntent;
            wearableextender.mPages = new ArrayList(mPages);
            wearableextender.mBackground = mBackground;
            wearableextender.mContentIcon = mContentIcon;
            wearableextender.mContentIconGravity = mContentIconGravity;
            wearableextender.mContentActionIndex = mContentActionIndex;
            wearableextender.mCustomSizePreset = mCustomSizePreset;
            wearableextender.mCustomContentHeight = mCustomContentHeight;
            wearableextender.mGravity = mGravity;
            return wearableextender;
        }

        public volatile Object clone()
            throws CloneNotSupportedException
        {
            return clone();
        }

        public Builder extend(Builder builder)
        {
            Bundle bundle = new Bundle();
            if (!mActions.isEmpty())
            {
                bundle.putParcelableArrayList("actions", NotificationCompat.IMPL.getParcelableArrayListForActions((Action[])mActions.toArray(new Action[mActions.size()])));
            }
            if (mFlags != 1)
            {
                bundle.putInt("flags", mFlags);
            }
            if (mDisplayIntent != null)
            {
                bundle.putParcelable("displayIntent", mDisplayIntent);
            }
            if (!mPages.isEmpty())
            {
                bundle.putParcelableArray("pages", (android.os.Parcelable[])mPages.toArray(new Notification[mPages.size()]));
            }
            if (mBackground != null)
            {
                bundle.putParcelable("background", mBackground);
            }
            if (mContentIcon != 0)
            {
                bundle.putInt("contentIcon", mContentIcon);
            }
            if (mContentIconGravity != 0x800005)
            {
                bundle.putInt("contentIconGravity", mContentIconGravity);
            }
            if (mContentActionIndex != -1)
            {
                bundle.putInt("contentActionIndex", mContentActionIndex);
            }
            if (mCustomSizePreset != 0)
            {
                bundle.putInt("customSizePreset", mCustomSizePreset);
            }
            if (mCustomContentHeight != 0)
            {
                bundle.putInt("customContentHeight", mCustomContentHeight);
            }
            if (mGravity != 80)
            {
                bundle.putInt("gravity", mGravity);
            }
            builder.getExtras().putBundle("android.wearable.EXTENSIONS", bundle);
            return builder;
        }

        public List getActions()
        {
            return mActions;
        }

        public Bitmap getBackground()
        {
            return mBackground;
        }

        public int getContentAction()
        {
            return mContentActionIndex;
        }

        public int getContentIcon()
        {
            return mContentIcon;
        }

        public int getContentIconGravity()
        {
            return mContentIconGravity;
        }

        public boolean getContentIntentAvailableOffline()
        {
            return (1 & mFlags) != 0;
        }

        public int getCustomContentHeight()
        {
            return mCustomContentHeight;
        }

        public int getCustomSizePreset()
        {
            return mCustomSizePreset;
        }

        public PendingIntent getDisplayIntent()
        {
            return mDisplayIntent;
        }

        public int getGravity()
        {
            return mGravity;
        }

        public boolean getHintHideIcon()
        {
            return (2 & mFlags) != 0;
        }

        public boolean getHintShowBackgroundOnly()
        {
            return (4 & mFlags) != 0;
        }

        public List getPages()
        {
            return mPages;
        }

        public boolean getStartScrollBottom()
        {
            return (8 & mFlags) != 0;
        }

        public WearableExtender setBackground(Bitmap bitmap)
        {
            mBackground = bitmap;
            return this;
        }

        public WearableExtender setContentAction(int i)
        {
            mContentActionIndex = i;
            return this;
        }

        public WearableExtender setContentIcon(int i)
        {
            mContentIcon = i;
            return this;
        }

        public WearableExtender setContentIconGravity(int i)
        {
            mContentIconGravity = i;
            return this;
        }

        public WearableExtender setContentIntentAvailableOffline(boolean flag)
        {
            setFlag(1, flag);
            return this;
        }

        public WearableExtender setCustomContentHeight(int i)
        {
            mCustomContentHeight = i;
            return this;
        }

        public WearableExtender setCustomSizePreset(int i)
        {
            mCustomSizePreset = i;
            return this;
        }

        public WearableExtender setDisplayIntent(PendingIntent pendingintent)
        {
            mDisplayIntent = pendingintent;
            return this;
        }

        public WearableExtender setGravity(int i)
        {
            mGravity = i;
            return this;
        }

        public WearableExtender setHintHideIcon(boolean flag)
        {
            setFlag(2, flag);
            return this;
        }

        public WearableExtender setHintShowBackgroundOnly(boolean flag)
        {
            setFlag(4, flag);
            return this;
        }

        public WearableExtender setStartScrollBottom(boolean flag)
        {
            setFlag(8, flag);
            return this;
        }

        public WearableExtender()
        {
            mActions = new ArrayList();
            mFlags = 1;
            mPages = new ArrayList();
            mContentIconGravity = 0x800005;
            mContentActionIndex = -1;
            mCustomSizePreset = 0;
            mGravity = 80;
        }

        public WearableExtender(Notification notification)
        {
            mActions = new ArrayList();
            mFlags = 1;
            mPages = new ArrayList();
            mContentIconGravity = 0x800005;
            mContentActionIndex = -1;
            mCustomSizePreset = 0;
            mGravity = 80;
            Bundle bundle = NotificationCompat.getExtras(notification);
            Bundle bundle1;
            if (bundle != null)
            {
                bundle1 = bundle.getBundle("android.wearable.EXTENSIONS");
            } else
            {
                bundle1 = null;
            }
            if (bundle1 != null)
            {
                Action aaction[] = NotificationCompat.IMPL.getActionsFromParcelableArrayList(bundle1.getParcelableArrayList("actions"));
                if (aaction != null)
                {
                    Collections.addAll(mActions, aaction);
                }
                mFlags = bundle1.getInt("flags", 1);
                mDisplayIntent = (PendingIntent)bundle1.getParcelable("displayIntent");
                Notification anotification[] = NotificationCompat.getNotificationArrayFromBundle(bundle1, "pages");
                if (anotification != null)
                {
                    Collections.addAll(mPages, anotification);
                }
                mBackground = (Bitmap)bundle1.getParcelable("background");
                mContentIcon = bundle1.getInt("contentIcon");
                mContentIconGravity = bundle1.getInt("contentIconGravity", 0x800005);
                mContentActionIndex = bundle1.getInt("contentActionIndex", -1);
                mCustomSizePreset = bundle1.getInt("customSizePreset", 0);
                mCustomContentHeight = bundle1.getInt("customContentHeight");
                mGravity = bundle1.getInt("gravity", 80);
            }
        }
    }


    public static final int DEFAULT_ALL = -1;
    public static final int DEFAULT_LIGHTS = 4;
    public static final int DEFAULT_SOUND = 1;
    public static final int DEFAULT_VIBRATE = 2;
    public static final String EXTRA_INFO_TEXT = "android.infoText";
    public static final String EXTRA_LARGE_ICON = "android.largeIcon";
    public static final String EXTRA_LARGE_ICON_BIG = "android.largeIcon.big";
    public static final String EXTRA_PEOPLE = "android.people";
    public static final String EXTRA_PICTURE = "android.picture";
    public static final String EXTRA_PROGRESS = "android.progress";
    public static final String EXTRA_PROGRESS_INDETERMINATE = "android.progressIndeterminate";
    public static final String EXTRA_PROGRESS_MAX = "android.progressMax";
    public static final String EXTRA_SHOW_CHRONOMETER = "android.showChronometer";
    public static final String EXTRA_SMALL_ICON = "android.icon";
    public static final String EXTRA_SUB_TEXT = "android.subText";
    public static final String EXTRA_SUMMARY_TEXT = "android.summaryText";
    public static final String EXTRA_TEXT = "android.text";
    public static final String EXTRA_TEXT_LINES = "android.textLines";
    public static final String EXTRA_TITLE = "android.title";
    public static final String EXTRA_TITLE_BIG = "android.title.big";
    public static final int FLAG_AUTO_CANCEL = 16;
    public static final int FLAG_FOREGROUND_SERVICE = 64;
    public static final int FLAG_GROUP_SUMMARY = 512;
    public static final int FLAG_HIGH_PRIORITY = 128;
    public static final int FLAG_INSISTENT = 4;
    public static final int FLAG_LOCAL_ONLY = 256;
    public static final int FLAG_NO_CLEAR = 32;
    public static final int FLAG_ONGOING_EVENT = 2;
    public static final int FLAG_ONLY_ALERT_ONCE = 8;
    public static final int FLAG_SHOW_LIGHTS = 1;
    private static final NotificationCompatImpl IMPL;
    public static final int PRIORITY_DEFAULT = 0;
    public static final int PRIORITY_HIGH = 1;
    public static final int PRIORITY_LOW = -1;
    public static final int PRIORITY_MAX = 2;
    public static final int PRIORITY_MIN = -2;
    public static final int STREAM_DEFAULT = -1;

    public NotificationCompat()
    {
    }

    private static void addActionsToBuilder(NotificationBuilderWithActions notificationbuilderwithactions, ArrayList arraylist)
    {
        for (Iterator iterator = arraylist.iterator(); iterator.hasNext(); notificationbuilderwithactions.addAction((Action)iterator.next())) { }
    }

    private static void addStyleToBuilderJellybean(NotificationBuilderWithBuilderAccessor notificationbuilderwithbuilderaccessor, Style style)
    {
        if (style != null)
        {
            if (style instanceof BigTextStyle)
            {
                BigTextStyle bigtextstyle = (BigTextStyle)style;
                NotificationCompatJellybean.addBigTextStyle(notificationbuilderwithbuilderaccessor, bigtextstyle.mBigContentTitle, bigtextstyle.mSummaryTextSet, bigtextstyle.mSummaryText, bigtextstyle.mBigText);
            } else
            {
                if (style instanceof InboxStyle)
                {
                    InboxStyle inboxstyle = (InboxStyle)style;
                    NotificationCompatJellybean.addInboxStyle(notificationbuilderwithbuilderaccessor, inboxstyle.mBigContentTitle, inboxstyle.mSummaryTextSet, inboxstyle.mSummaryText, inboxstyle.mTexts);
                    return;
                }
                if (style instanceof BigPictureStyle)
                {
                    BigPictureStyle bigpicturestyle = (BigPictureStyle)style;
                    NotificationCompatJellybean.addBigPictureStyle(notificationbuilderwithbuilderaccessor, bigpicturestyle.mBigContentTitle, bigpicturestyle.mSummaryTextSet, bigpicturestyle.mSummaryText, bigpicturestyle.mPicture, bigpicturestyle.mBigLargeIcon, bigpicturestyle.mBigLargeIconSet);
                    return;
                }
            }
        }
    }

    public static Action getAction(Notification notification, int i)
    {
        return IMPL.getAction(notification, i);
    }

    public static int getActionCount(Notification notification)
    {
        return IMPL.getActionCount(notification);
    }

    public static Bundle getExtras(Notification notification)
    {
        return IMPL.getExtras(notification);
    }

    public static String getGroup(Notification notification)
    {
        return IMPL.getGroup(notification);
    }

    public static boolean getLocalOnly(Notification notification)
    {
        return IMPL.getLocalOnly(notification);
    }

    private static Notification[] getNotificationArrayFromBundle(Bundle bundle, String s)
    {
        android.os.Parcelable aparcelable[] = bundle.getParcelableArray(s);
        if ((aparcelable instanceof Notification[]) || aparcelable == null)
        {
            return (Notification[])(Notification[])aparcelable;
        }
        Notification anotification[] = new Notification[aparcelable.length];
        for (int i = 0; i < aparcelable.length; i++)
        {
            anotification[i] = (Notification)aparcelable[i];
        }

        bundle.putParcelableArray(s, anotification);
        return anotification;
    }

    public static String getSortKey(Notification notification)
    {
        return IMPL.getSortKey(notification);
    }

    public static boolean isGroupSummary(Notification notification)
    {
        return IMPL.isGroupSummary(notification);
    }

    static 
    {
        if (android.os.Build.VERSION.SDK_INT >= 20)
        {
            IMPL = new NotificationCompatImplApi20();
        } else
        if (android.os.Build.VERSION.SDK_INT >= 19)
        {
            IMPL = new NotificationCompatImplKitKat();
        } else
        if (android.os.Build.VERSION.SDK_INT >= 16)
        {
            IMPL = new NotificationCompatImplJellybean();
        } else
        if (android.os.Build.VERSION.SDK_INT >= 14)
        {
            IMPL = new NotificationCompatImplIceCreamSandwich();
        } else
        if (android.os.Build.VERSION.SDK_INT >= 11)
        {
            IMPL = new NotificationCompatImplHoneycomb();
        } else
        if (android.os.Build.VERSION.SDK_INT >= 9)
        {
            IMPL = new NotificationCompatImplGingerbread();
        } else
        {
            IMPL = new NotificationCompatImplBase();
        }
    }




}

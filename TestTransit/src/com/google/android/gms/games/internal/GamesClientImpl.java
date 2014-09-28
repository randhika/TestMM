// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.games.internal;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.net.LocalSocket;
import android.net.LocalSocketAddress;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.RemoteException;
import android.view.View;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.b;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.a;
import com.google.android.gms.drive.Contents;
import com.google.android.gms.games.Game;
import com.google.android.gms.games.GameBuffer;
import com.google.android.gms.games.GameEntity;
import com.google.android.gms.games.Player;
import com.google.android.gms.games.PlayerBuffer;
import com.google.android.gms.games.PlayerEntity;
import com.google.android.gms.games.achievement.AchievementBuffer;
import com.google.android.gms.games.event.EventBuffer;
import com.google.android.gms.games.internal.constants.RequestType;
import com.google.android.gms.games.internal.events.EventIncrementCache;
import com.google.android.gms.games.internal.events.EventIncrementManager;
import com.google.android.gms.games.internal.experience.ExperienceEventBuffer;
import com.google.android.gms.games.internal.game.ExtendedGameBuffer;
import com.google.android.gms.games.internal.game.GameInstanceBuffer;
import com.google.android.gms.games.internal.request.RequestUpdateOutcomes;
import com.google.android.gms.games.leaderboard.Leaderboard;
import com.google.android.gms.games.leaderboard.LeaderboardBuffer;
import com.google.android.gms.games.leaderboard.LeaderboardEntity;
import com.google.android.gms.games.leaderboard.LeaderboardScore;
import com.google.android.gms.games.leaderboard.LeaderboardScoreBuffer;
import com.google.android.gms.games.leaderboard.LeaderboardScoreBufferHeader;
import com.google.android.gms.games.leaderboard.LeaderboardScoreEntity;
import com.google.android.gms.games.leaderboard.ScoreSubmissionData;
import com.google.android.gms.games.multiplayer.Invitation;
import com.google.android.gms.games.multiplayer.InvitationBuffer;
import com.google.android.gms.games.multiplayer.OnInvitationReceivedListener;
import com.google.android.gms.games.multiplayer.ParticipantResult;
import com.google.android.gms.games.multiplayer.ParticipantUtils;
import com.google.android.gms.games.multiplayer.realtime.RealTimeMessage;
import com.google.android.gms.games.multiplayer.realtime.RealTimeMessageReceivedListener;
import com.google.android.gms.games.multiplayer.realtime.RealTimeSocket;
import com.google.android.gms.games.multiplayer.realtime.Room;
import com.google.android.gms.games.multiplayer.realtime.RoomBuffer;
import com.google.android.gms.games.multiplayer.realtime.RoomConfig;
import com.google.android.gms.games.multiplayer.realtime.RoomEntity;
import com.google.android.gms.games.multiplayer.realtime.RoomStatusUpdateListener;
import com.google.android.gms.games.multiplayer.realtime.RoomUpdateListener;
import com.google.android.gms.games.multiplayer.turnbased.LoadMatchesResponse;
import com.google.android.gms.games.multiplayer.turnbased.OnTurnBasedMatchUpdateReceivedListener;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMatch;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMatchBuffer;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMatchConfig;
import com.google.android.gms.games.quest.Milestone;
import com.google.android.gms.games.quest.Quest;
import com.google.android.gms.games.quest.QuestBuffer;
import com.google.android.gms.games.quest.QuestEntity;
import com.google.android.gms.games.quest.QuestUpdateListener;
import com.google.android.gms.games.request.GameRequest;
import com.google.android.gms.games.request.GameRequestBuffer;
import com.google.android.gms.games.request.OnRequestReceivedListener;
import com.google.android.gms.games.snapshot.Snapshot;
import com.google.android.gms.games.snapshot.SnapshotEntity;
import com.google.android.gms.games.snapshot.SnapshotMetadata;
import com.google.android.gms.games.snapshot.SnapshotMetadataBuffer;
import com.google.android.gms.games.snapshot.SnapshotMetadataChange;
import com.google.android.gms.games.snapshot.SnapshotMetadataEntity;
import com.google.android.gms.internal.gx;
import com.google.android.gms.internal.hb;
import com.google.android.gms.internal.hi;
import com.google.android.gms.internal.hm;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

// Referenced classes of package com.google.android.gms.games.internal:
//            PopupManager, IGamesService, GamesLog, LibjingleNativeSocket, 
//            RealTimeSocketImpl, AbstractGamesCallbacks

public final class GamesClientImpl extends hb
    implements com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks, com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener
{
    private abstract class AbstractPeerStatusCallback extends AbstractRoomStatusCallback
    {

        final GamesClientImpl NE;
        private final ArrayList NF = new ArrayList();

        protected void a(RoomStatusUpdateListener roomstatusupdatelistener, Room room)
        {
            a(roomstatusupdatelistener, room, NF);
        }

        protected abstract void a(RoomStatusUpdateListener roomstatusupdatelistener, Room room, ArrayList arraylist);

        AbstractPeerStatusCallback(RoomStatusUpdateListener roomstatusupdatelistener, DataHolder dataholder, String as[])
        {
            NE = GamesClientImpl.this;
            super(roomstatusupdatelistener, dataholder);
            int i1 = 0;
            for (int j1 = as.length; i1 < j1; i1++)
            {
                NF.add(as[i1]);
            }

        }
    }

    private abstract class AbstractRoomCallback extends com.google.android.gms.internal.hb.d
    {

        final GamesClientImpl NE;

        protected void a(RoomUpdateListener roomupdatelistener, DataHolder dataholder)
        {
            a(roomupdatelistener, com.google.android.gms.games.internal.GamesClientImpl.a(NE, dataholder), dataholder.getStatusCode());
        }

        protected abstract void a(RoomUpdateListener roomupdatelistener, Room room, int i1);

        protected volatile void a(Object obj, DataHolder dataholder)
        {
            a((RoomUpdateListener)obj, dataholder);
        }

        AbstractRoomCallback(RoomUpdateListener roomupdatelistener, DataHolder dataholder)
        {
            NE = GamesClientImpl.this;
            super(GamesClientImpl.this, roomupdatelistener, dataholder);
        }
    }

    private abstract class AbstractRoomStatusCallback extends com.google.android.gms.internal.hb.d
    {

        final GamesClientImpl NE;

        protected void a(RoomStatusUpdateListener roomstatusupdatelistener, DataHolder dataholder)
        {
            a(roomstatusupdatelistener, com.google.android.gms.games.internal.GamesClientImpl.a(NE, dataholder));
        }

        protected abstract void a(RoomStatusUpdateListener roomstatusupdatelistener, Room room);

        protected volatile void a(Object obj, DataHolder dataholder)
        {
            a((RoomStatusUpdateListener)obj, dataholder);
        }

        AbstractRoomStatusCallback(RoomStatusUpdateListener roomstatusupdatelistener, DataHolder dataholder)
        {
            NE = GamesClientImpl.this;
            super(GamesClientImpl.this, roomstatusupdatelistener, dataholder);
        }
    }

    private static final class AcceptQuestResultImpl extends b
        implements com.google.android.gms.games.quest.Quests.AcceptQuestResult
    {

        private final Quest NG = null;

        public Quest getQuest()
        {
            return NG;
        }

        AcceptQuestResultImpl(DataHolder dataholder)
        {
            QuestBuffer questbuffer;
            super(dataholder);
            questbuffer = new QuestBuffer(dataholder);
            if (questbuffer.getCount() <= 0) goto _L2; else goto _L1
_L1:
            NG = new QuestEntity((Quest)questbuffer.get(0));
_L4:
            questbuffer.close();
            return;
_L2:
            if (true) goto _L4; else goto _L3
_L3:
            Exception exception;
            exception;
            questbuffer.close();
            throw exception;
        }
    }

    private final class AchievementUpdatedBinderCallback extends AbstractGamesCallbacks
    {

        final GamesClientImpl NE;
        private final com.google.android.gms.common.api.a.d yR;

        public void e(int i1, String s)
        {
            yR.a(new UpdateAchievementResultImpl(i1, s));
        }

        AchievementUpdatedBinderCallback(com.google.android.gms.common.api.a.d d1)
        {
            NE = GamesClientImpl.this;
            super();
            yR = (com.google.android.gms.common.api.a.d)com.google.android.gms.internal.hm.b(d1, "Holder must not be null");
        }
    }

    private final class AchievementsLoadedBinderCallback extends AbstractGamesCallbacks
    {

        final GamesClientImpl NE;
        private final com.google.android.gms.common.api.a.d yR;

        public void c(DataHolder dataholder)
        {
            yR.a(new LoadAchievementsResultImpl(dataholder));
        }

        AchievementsLoadedBinderCallback(com.google.android.gms.common.api.a.d d1)
        {
            NE = GamesClientImpl.this;
            super();
            yR = (com.google.android.gms.common.api.a.d)com.google.android.gms.internal.hm.b(d1, "Holder must not be null");
        }
    }

    private static final class CancelMatchResultImpl
        implements com.google.android.gms.games.multiplayer.turnbased.TurnBasedMultiplayer.CancelMatchResult
    {

        private final String NH;
        private final Status yz;

        public String getMatchId()
        {
            return NH;
        }

        public Status getStatus()
        {
            return yz;
        }

        CancelMatchResultImpl(Status status, String s)
        {
            yz = status;
            NH = s;
        }
    }

    private static final class ClaimMilestoneResultImpl extends b
        implements com.google.android.gms.games.quest.Quests.ClaimMilestoneResult
    {

        private final Quest NG;
        private final Milestone NI = null;

        public Milestone getMilestone()
        {
            return NI;
        }

        public Quest getQuest()
        {
            return NG;
        }

        ClaimMilestoneResultImpl(DataHolder dataholder, String s)
        {
            int i1;
            QuestBuffer questbuffer;
            i1 = 0;
            super(dataholder);
            questbuffer = new QuestBuffer(dataholder);
            if (questbuffer.getCount() <= 0) goto _L2; else goto _L1
_L1:
            List list;
            int j1;
            NG = new QuestEntity((Quest)questbuffer.get(0));
            list = NG.iJ();
            j1 = list.size();
_L4:
            if (i1 >= j1)
            {
                break; /* Loop/switch isn't completed */
            }
            if (!((Milestone)list.get(i1)).getMilestoneId().equals(s))
            {
                break MISSING_BLOCK_LABEL_115;
            }
            NI = (Milestone)list.get(i1);
            questbuffer.close();
            return;
            i1++;
            if (true) goto _L4; else goto _L3
_L3:
_L6:
            questbuffer.close();
            return;
_L2:
            NI = null;
            NG = null;
            if (true) goto _L6; else goto _L5
_L5:
            Exception exception;
            exception;
            questbuffer.close();
            throw exception;
        }
    }

    private static final class CommitSnapshotResultImpl extends b
        implements com.google.android.gms.games.snapshot.Snapshots.CommitSnapshotResult
    {

        private final SnapshotMetadata NJ = null;

        public SnapshotMetadata getSnapshotMetadata()
        {
            return NJ;
        }

        CommitSnapshotResultImpl(DataHolder dataholder)
        {
            SnapshotMetadataBuffer snapshotmetadatabuffer;
            super(dataholder);
            snapshotmetadatabuffer = new SnapshotMetadataBuffer(dataholder);
            if (snapshotmetadatabuffer.getCount() <= 0) goto _L2; else goto _L1
_L1:
            NJ = new SnapshotMetadataEntity(snapshotmetadatabuffer.get(0));
_L4:
            snapshotmetadatabuffer.close();
            return;
_L2:
            if (true) goto _L4; else goto _L3
_L3:
            Exception exception;
            exception;
            snapshotmetadatabuffer.close();
            throw exception;
        }
    }

    private final class ConnectedToRoomCallback extends AbstractRoomStatusCallback
    {

        final GamesClientImpl NE;

        public void a(RoomStatusUpdateListener roomstatusupdatelistener, Room room)
        {
            roomstatusupdatelistener.onConnectedToRoom(room);
        }

        ConnectedToRoomCallback(RoomStatusUpdateListener roomstatusupdatelistener, DataHolder dataholder)
        {
            NE = GamesClientImpl.this;
            super(roomstatusupdatelistener, dataholder);
        }
    }

    private static final class ContactSettingLoadResultImpl extends b
        implements com.google.android.gms.games.Notifications.ContactSettingLoadResult
    {

        ContactSettingLoadResultImpl(DataHolder dataholder)
        {
            super(dataholder);
        }
    }

    private final class ContactSettingsLoadedBinderCallback extends AbstractGamesCallbacks
    {

        final GamesClientImpl NE;
        private final com.google.android.gms.common.api.a.d yR;

        public void D(DataHolder dataholder)
        {
            yR.a(new ContactSettingLoadResultImpl(dataholder));
        }

        ContactSettingsLoadedBinderCallback(com.google.android.gms.common.api.a.d d1)
        {
            NE = GamesClientImpl.this;
            super();
            yR = (com.google.android.gms.common.api.a.d)com.google.android.gms.internal.hm.b(d1, "Holder must not be null");
        }
    }

    private final class ContactSettingsUpdatedBinderCallback extends AbstractGamesCallbacks
    {

        final GamesClientImpl NE;
        private final com.google.android.gms.common.api.a.d yR;

        public void ce(int i1)
        {
            yR.a(new Status(i1));
        }

        ContactSettingsUpdatedBinderCallback(com.google.android.gms.common.api.a.d d1)
        {
            NE = GamesClientImpl.this;
            super();
            yR = (com.google.android.gms.common.api.a.d)com.google.android.gms.internal.hm.b(d1, "Holder must not be null");
        }
    }

    private static final class DeleteSnapshotResultImpl
        implements com.google.android.gms.games.snapshot.Snapshots.DeleteSnapshotResult
    {

        private final String NK;
        private final Status yz;

        public String getSnapshotId()
        {
            return NK;
        }

        public Status getStatus()
        {
            return yz;
        }

        DeleteSnapshotResultImpl(int i1, String s)
        {
            yz = new Status(i1);
            NK = s;
        }
    }

    private final class DisconnectedFromRoomCallback extends AbstractRoomStatusCallback
    {

        final GamesClientImpl NE;

        public void a(RoomStatusUpdateListener roomstatusupdatelistener, Room room)
        {
            roomstatusupdatelistener.onDisconnectedFromRoom(room);
        }

        DisconnectedFromRoomCallback(RoomStatusUpdateListener roomstatusupdatelistener, DataHolder dataholder)
        {
            NE = GamesClientImpl.this;
            super(roomstatusupdatelistener, dataholder);
        }
    }

    private final class EventsLoadedBinderCallback extends AbstractGamesCallbacks
    {

        final GamesClientImpl NE;
        private final com.google.android.gms.common.api.a.d yR;

        public void d(DataHolder dataholder)
        {
            yR.a(new LoadEventResultImpl(dataholder));
        }

        EventsLoadedBinderCallback(com.google.android.gms.common.api.a.d d1)
        {
            NE = GamesClientImpl.this;
            super();
            yR = (com.google.android.gms.common.api.a.d)com.google.android.gms.internal.hm.b(d1, "Holder must not be null");
        }
    }

    private final class ExtendedGamesLoadedBinderCallback extends AbstractGamesCallbacks
    {

        final GamesClientImpl NE;
        private final com.google.android.gms.common.api.a.d yR;

        public void j(DataHolder dataholder)
        {
            yR.a(new LoadExtendedGamesResultImpl(dataholder));
        }

        ExtendedGamesLoadedBinderCallback(com.google.android.gms.common.api.a.d d1)
        {
            NE = GamesClientImpl.this;
            super();
            yR = (com.google.android.gms.common.api.a.d)com.google.android.gms.internal.hm.b(d1, "Holder must not be null");
        }
    }

    private class GameClientEventIncrementCache extends EventIncrementCache
    {

        final GamesClientImpl NE;

        protected void o(String s, int i1)
        {
            try
            {
                ((IGamesService)NE.ft()).l(s, i1);
                return;
            }
            catch (RemoteException remoteexception)
            {
                GamesLog.j("GamesClientImpl", "service died");
            }
        }

        public GameClientEventIncrementCache()
        {
            NE = GamesClientImpl.this;
            super(getContext().getMainLooper(), 1000);
        }
    }

    private final class GameInstancesLoadedBinderCallback extends AbstractGamesCallbacks
    {

        final GamesClientImpl NE;
        private final com.google.android.gms.common.api.a.d yR;

        public void k(DataHolder dataholder)
        {
            yR.a(new LoadGameInstancesResultImpl(dataholder));
        }

        GameInstancesLoadedBinderCallback(com.google.android.gms.common.api.a.d d1)
        {
            NE = GamesClientImpl.this;
            super();
            yR = (com.google.android.gms.common.api.a.d)com.google.android.gms.internal.hm.b(d1, "Holder must not be null");
        }
    }

    private static final class GameMuteStatusChangeResultImpl
        implements com.google.android.gms.games.Notifications.GameMuteStatusChangeResult
    {

        private final String NL;
        private final boolean NM;
        private final Status yz;

        public Status getStatus()
        {
            return yz;
        }

        public GameMuteStatusChangeResultImpl(int i1, String s, boolean flag)
        {
            yz = new Status(i1);
            NL = s;
            NM = flag;
        }
    }

    private final class GameMuteStatusChangedBinderCallback extends AbstractGamesCallbacks
    {

        final GamesClientImpl NE;
        private final com.google.android.gms.common.api.a.d yR;

        public void a(int i1, String s, boolean flag)
        {
            yR.a(new GameMuteStatusChangeResultImpl(i1, s, flag));
        }

        GameMuteStatusChangedBinderCallback(com.google.android.gms.common.api.a.d d1)
        {
            NE = GamesClientImpl.this;
            super();
            yR = (com.google.android.gms.common.api.a.d)com.google.android.gms.internal.hm.b(d1, "Holder must not be null");
        }
    }

    private static final class GameMuteStatusLoadResultImpl
        implements com.google.android.gms.games.Notifications.GameMuteStatusLoadResult
    {

        private final String NL;
        private final boolean NM;
        private final Status yz;

        public Status getStatus()
        {
            return yz;
        }

        public GameMuteStatusLoadResultImpl(DataHolder dataholder)
        {
            yz = new Status(dataholder.getStatusCode());
            if (dataholder.getCount() <= 0) goto _L2; else goto _L1
_L1:
            NL = dataholder.c("external_game_id", 0, 0);
            NM = dataholder.d("muted", 0, 0);
_L4:
            dataholder.close();
            return;
_L2:
            NL = null;
            NM = false;
            if (true) goto _L4; else goto _L3
_L3:
            Exception exception;
            exception;
            dataholder.close();
            throw exception;
        }
    }

    private final class GameMuteStatusLoadedBinderCallback extends AbstractGamesCallbacks
    {

        final GamesClientImpl NE;
        private final com.google.android.gms.common.api.a.d yR;

        public void B(DataHolder dataholder)
        {
            yR.a(new GameMuteStatusLoadResultImpl(dataholder));
        }

        GameMuteStatusLoadedBinderCallback(com.google.android.gms.common.api.a.d d1)
        {
            NE = GamesClientImpl.this;
            super();
            yR = (com.google.android.gms.common.api.a.d)com.google.android.gms.internal.hm.b(d1, "Holder must not be null");
        }
    }

    private final class GameSearchSuggestionsLoadedBinderCallback extends AbstractGamesCallbacks
    {

        final GamesClientImpl NE;
        private final com.google.android.gms.common.api.a.d yR;

        public void l(DataHolder dataholder)
        {
            yR.a(new LoadGameSearchSuggestionsResultImpl(dataholder));
        }

        GameSearchSuggestionsLoadedBinderCallback(com.google.android.gms.common.api.a.d d1)
        {
            NE = GamesClientImpl.this;
            super();
            yR = (com.google.android.gms.common.api.a.d)com.google.android.gms.internal.hm.b(d1, "Holder must not be null");
        }
    }

    private final class GamesLoadedBinderCallback extends AbstractGamesCallbacks
    {

        final GamesClientImpl NE;
        private final com.google.android.gms.common.api.a.d yR;

        public void i(DataHolder dataholder)
        {
            yR.a(new LoadGamesResultImpl(dataholder));
        }

        GamesLoadedBinderCallback(com.google.android.gms.common.api.a.d d1)
        {
            NE = GamesClientImpl.this;
            super();
            yR = (com.google.android.gms.common.api.a.d)com.google.android.gms.internal.hm.b(d1, "Holder must not be null");
        }
    }

    private static final class InboxCountResultImpl
        implements com.google.android.gms.games.Notifications.InboxCountResult
    {

        private final Bundle NN;
        private final Status yz;

        public Status getStatus()
        {
            return yz;
        }

        InboxCountResultImpl(Status status, Bundle bundle)
        {
            yz = status;
            NN = bundle;
        }
    }

    private final class InboxCountsLoadedBinderCallback extends AbstractGamesCallbacks
    {

        final GamesClientImpl NE;
        private final com.google.android.gms.common.api.a.d yR;

        public void f(int i1, Bundle bundle)
        {
            bundle.setClassLoader(getClass().getClassLoader());
            Status status = new Status(i1);
            yR.a(new InboxCountResultImpl(status, bundle));
        }

        InboxCountsLoadedBinderCallback(com.google.android.gms.common.api.a.d d1)
        {
            NE = GamesClientImpl.this;
            super();
            yR = (com.google.android.gms.common.api.a.d)com.google.android.gms.internal.hm.b(d1, "Holder must not be null");
        }
    }

    private static final class InitiateMatchResultImpl extends TurnBasedMatchResult
        implements com.google.android.gms.games.multiplayer.turnbased.TurnBasedMultiplayer.InitiateMatchResult
    {

        InitiateMatchResultImpl(DataHolder dataholder)
        {
            super(dataholder);
        }
    }

    private final class InvitationReceivedBinderCallback extends AbstractGamesCallbacks
    {

        final GamesClientImpl NE;
        private final OnInvitationReceivedListener NO;

        public void n(DataHolder dataholder)
        {
            InvitationBuffer invitationbuffer = new InvitationBuffer(dataholder);
            int i1 = invitationbuffer.getCount();
            Invitation invitation;
            invitation = null;
            if (i1 <= 0)
            {
                break MISSING_BLOCK_LABEL_41;
            }
            invitation = (Invitation)((Invitation)invitationbuffer.get(0)).freeze();
            invitationbuffer.close();
            if (invitation != null)
            {
                NE.a(NE. new InvitationReceivedCallback(NO, invitation));
            }
            return;
            Exception exception;
            exception;
            invitationbuffer.close();
            throw exception;
        }

        public void onInvitationRemoved(String s)
        {
            NE.a(NE. new InvitationRemovedCallback(NO, s));
        }

        InvitationReceivedBinderCallback(OnInvitationReceivedListener oninvitationreceivedlistener)
        {
            NE = GamesClientImpl.this;
            super();
            NO = oninvitationreceivedlistener;
        }
    }

    private final class InvitationReceivedCallback extends com.google.android.gms.internal.hb.b
    {

        final GamesClientImpl NE;
        private final Invitation NP;

        protected void b(OnInvitationReceivedListener oninvitationreceivedlistener)
        {
            oninvitationreceivedlistener.onInvitationReceived(NP);
        }

        protected void d(Object obj)
        {
            b((OnInvitationReceivedListener)obj);
        }

        protected void fu()
        {
        }

        InvitationReceivedCallback(OnInvitationReceivedListener oninvitationreceivedlistener, Invitation invitation)
        {
            NE = GamesClientImpl.this;
            super(GamesClientImpl.this, oninvitationreceivedlistener);
            NP = invitation;
        }
    }

    private final class InvitationRemovedCallback extends com.google.android.gms.internal.hb.b
    {

        final GamesClientImpl NE;
        private final String NQ;

        protected void b(OnInvitationReceivedListener oninvitationreceivedlistener)
        {
            oninvitationreceivedlistener.onInvitationRemoved(NQ);
        }

        protected void d(Object obj)
        {
            b((OnInvitationReceivedListener)obj);
        }

        protected void fu()
        {
        }

        InvitationRemovedCallback(OnInvitationReceivedListener oninvitationreceivedlistener, String s)
        {
            NE = GamesClientImpl.this;
            super(GamesClientImpl.this, oninvitationreceivedlistener);
            NQ = s;
        }
    }

    private final class InvitationsLoadedBinderCallback extends AbstractGamesCallbacks
    {

        final GamesClientImpl NE;
        private final com.google.android.gms.common.api.a.d yR;

        public void m(DataHolder dataholder)
        {
            yR.a(new LoadInvitationsResultImpl(dataholder));
        }

        InvitationsLoadedBinderCallback(com.google.android.gms.common.api.a.d d1)
        {
            NE = GamesClientImpl.this;
            super();
            yR = (com.google.android.gms.common.api.a.d)com.google.android.gms.internal.hm.b(d1, "Holder must not be null");
        }
    }

    private final class JoinedRoomCallback extends AbstractRoomCallback
    {

        final GamesClientImpl NE;

        public void a(RoomUpdateListener roomupdatelistener, Room room, int i1)
        {
            roomupdatelistener.onJoinedRoom(i1, room);
        }

        public JoinedRoomCallback(RoomUpdateListener roomupdatelistener, DataHolder dataholder)
        {
            NE = GamesClientImpl.this;
            super(roomupdatelistener, dataholder);
        }
    }

    private static final class LeaderboardMetadataResultImpl extends b
        implements com.google.android.gms.games.leaderboard.Leaderboards.LeaderboardMetadataResult
    {

        private final LeaderboardBuffer NR;

        public LeaderboardBuffer getLeaderboards()
        {
            return NR;
        }

        LeaderboardMetadataResultImpl(DataHolder dataholder)
        {
            super(dataholder);
            NR = new LeaderboardBuffer(dataholder);
        }
    }

    private final class LeaderboardScoresLoadedBinderCallback extends AbstractGamesCallbacks
    {

        final GamesClientImpl NE;
        private final com.google.android.gms.common.api.a.d yR;

        public void a(DataHolder dataholder, DataHolder dataholder1)
        {
            yR.a(new LoadScoresResultImpl(dataholder, dataholder1));
        }

        LeaderboardScoresLoadedBinderCallback(com.google.android.gms.common.api.a.d d1)
        {
            NE = GamesClientImpl.this;
            super();
            yR = (com.google.android.gms.common.api.a.d)com.google.android.gms.internal.hm.b(d1, "Holder must not be null");
        }
    }

    private final class LeaderboardsLoadedBinderCallback extends AbstractGamesCallbacks
    {

        final GamesClientImpl NE;
        private final com.google.android.gms.common.api.a.d yR;

        public void e(DataHolder dataholder)
        {
            yR.a(new LeaderboardMetadataResultImpl(dataholder));
        }

        LeaderboardsLoadedBinderCallback(com.google.android.gms.common.api.a.d d1)
        {
            NE = GamesClientImpl.this;
            super();
            yR = (com.google.android.gms.common.api.a.d)com.google.android.gms.internal.hm.b(d1, "Holder must not be null");
        }
    }

    private static final class LeaveMatchResultImpl extends TurnBasedMatchResult
        implements com.google.android.gms.games.multiplayer.turnbased.TurnBasedMultiplayer.LeaveMatchResult
    {

        LeaveMatchResultImpl(DataHolder dataholder)
        {
            super(dataholder);
        }
    }

    private final class LeftRoomCallback extends com.google.android.gms.internal.hb.b
    {

        private final int CT;
        final GamesClientImpl NE;
        private final String NS;

        public void a(RoomUpdateListener roomupdatelistener)
        {
            roomupdatelistener.onLeftRoom(CT, NS);
        }

        public void d(Object obj)
        {
            a((RoomUpdateListener)obj);
        }

        protected void fu()
        {
        }

        LeftRoomCallback(RoomUpdateListener roomupdatelistener, int i1, String s)
        {
            NE = GamesClientImpl.this;
            super(GamesClientImpl.this, roomupdatelistener);
            CT = i1;
            NS = s;
        }
    }

    private static final class LoadAchievementsResultImpl extends b
        implements com.google.android.gms.games.achievement.Achievements.LoadAchievementsResult
    {

        private final AchievementBuffer NT;

        public AchievementBuffer getAchievements()
        {
            return NT;
        }

        LoadAchievementsResultImpl(DataHolder dataholder)
        {
            super(dataholder);
            NT = new AchievementBuffer(dataholder);
        }
    }

    private static final class LoadAclResultImpl extends b
        implements com.google.android.gms.games.internal.game.Acls.LoadAclResult
    {

        LoadAclResultImpl(DataHolder dataholder)
        {
            super(dataholder);
        }
    }

    private static final class LoadEventResultImpl extends b
        implements com.google.android.gms.games.event.Events.LoadEventsResult
    {

        private final EventBuffer NU;

        public EventBuffer getEvents()
        {
            return NU;
        }

        LoadEventResultImpl(DataHolder dataholder)
        {
            super(dataholder);
            NU = new EventBuffer(dataholder);
        }
    }

    private static final class LoadExtendedGamesResultImpl extends b
        implements com.google.android.gms.games.GamesMetadata.LoadExtendedGamesResult
    {

        private final ExtendedGameBuffer NV;

        LoadExtendedGamesResultImpl(DataHolder dataholder)
        {
            super(dataholder);
            NV = new ExtendedGameBuffer(dataholder);
        }
    }

    private static final class LoadGameInstancesResultImpl extends b
        implements com.google.android.gms.games.GamesMetadata.LoadGameInstancesResult
    {

        private final GameInstanceBuffer NW;

        LoadGameInstancesResultImpl(DataHolder dataholder)
        {
            super(dataholder);
            NW = new GameInstanceBuffer(dataholder);
        }
    }

    private static final class LoadGameSearchSuggestionsResultImpl extends b
        implements com.google.android.gms.games.GamesMetadata.LoadGameSearchSuggestionsResult
    {

        LoadGameSearchSuggestionsResultImpl(DataHolder dataholder)
        {
            super(dataholder);
        }
    }

    private static final class LoadGamesResultImpl extends b
        implements com.google.android.gms.games.GamesMetadata.LoadGamesResult
    {

        private final GameBuffer NX;

        public GameBuffer getGames()
        {
            return NX;
        }

        LoadGamesResultImpl(DataHolder dataholder)
        {
            super(dataholder);
            NX = new GameBuffer(dataholder);
        }
    }

    private static final class LoadInvitationsResultImpl extends b
        implements com.google.android.gms.games.multiplayer.Invitations.LoadInvitationsResult
    {

        private final InvitationBuffer NY;

        public InvitationBuffer getInvitations()
        {
            return NY;
        }

        LoadInvitationsResultImpl(DataHolder dataholder)
        {
            super(dataholder);
            NY = new InvitationBuffer(dataholder);
        }
    }

    private static final class LoadMatchResultImpl extends TurnBasedMatchResult
        implements com.google.android.gms.games.multiplayer.turnbased.TurnBasedMultiplayer.LoadMatchResult
    {

        LoadMatchResultImpl(DataHolder dataholder)
        {
            super(dataholder);
        }
    }

    private static final class LoadMatchesResultImpl
        implements com.google.android.gms.games.multiplayer.turnbased.TurnBasedMultiplayer.LoadMatchesResult
    {

        private final LoadMatchesResponse NZ;
        private final Status yz;

        public LoadMatchesResponse getMatches()
        {
            return NZ;
        }

        public Status getStatus()
        {
            return yz;
        }

        public void release()
        {
            NZ.close();
        }

        LoadMatchesResultImpl(Status status, Bundle bundle)
        {
            yz = status;
            NZ = new LoadMatchesResponse(bundle);
        }
    }

    private static final class LoadOwnerCoverPhotoUrisResultImpl
        implements com.google.android.gms.games.Players.LoadOwnerCoverPhotoUrisResult
    {

        private final Bundle HM;
        private final Status yz;

        public Status getStatus()
        {
            return yz;
        }

        LoadOwnerCoverPhotoUrisResultImpl(int i1, Bundle bundle)
        {
            yz = new Status(i1);
            HM = bundle;
        }
    }

    private static final class LoadPlayerScoreResultImpl extends b
        implements com.google.android.gms.games.leaderboard.Leaderboards.LoadPlayerScoreResult
    {

        private final LeaderboardScoreEntity Oa = null;

        public LeaderboardScore getScore()
        {
            return Oa;
        }

        LoadPlayerScoreResultImpl(DataHolder dataholder)
        {
            LeaderboardScoreBuffer leaderboardscorebuffer;
            super(dataholder);
            leaderboardscorebuffer = new LeaderboardScoreBuffer(dataholder);
            if (leaderboardscorebuffer.getCount() <= 0) goto _L2; else goto _L1
_L1:
            Oa = (LeaderboardScoreEntity)leaderboardscorebuffer.get(0).freeze();
_L4:
            leaderboardscorebuffer.close();
            return;
_L2:
            if (true) goto _L4; else goto _L3
_L3:
            Exception exception;
            exception;
            leaderboardscorebuffer.close();
            throw exception;
        }
    }

    private static final class LoadPlayersResultImpl extends b
        implements com.google.android.gms.games.Players.LoadPlayersResult
    {

        private final PlayerBuffer Ob;

        public PlayerBuffer getPlayers()
        {
            return Ob;
        }

        LoadPlayersResultImpl(DataHolder dataholder)
        {
            super(dataholder);
            Ob = new PlayerBuffer(dataholder);
        }
    }

    private static final class LoadQuestsResultImpl extends b
        implements com.google.android.gms.games.quest.Quests.LoadQuestsResult
    {

        private final DataHolder DG;

        public QuestBuffer getQuests()
        {
            return new QuestBuffer(DG);
        }

        LoadQuestsResultImpl(DataHolder dataholder)
        {
            super(dataholder);
            DG = dataholder;
        }
    }

    private static final class LoadRequestSummariesResultImpl extends b
        implements com.google.android.gms.games.request.Requests.LoadRequestSummariesResult
    {

        LoadRequestSummariesResultImpl(DataHolder dataholder)
        {
            super(dataholder);
        }
    }

    private static final class LoadRequestsResultImpl
        implements com.google.android.gms.games.request.Requests.LoadRequestsResult
    {

        private final Bundle Oc;
        private final Status yz;

        public GameRequestBuffer getRequests(int i1)
        {
            String s = RequestType.cm(i1);
            if (!Oc.containsKey(s))
            {
                return null;
            } else
            {
                return new GameRequestBuffer((DataHolder)Oc.get(s));
            }
        }

        public Status getStatus()
        {
            return yz;
        }

        public void release()
        {
            Iterator iterator = Oc.keySet().iterator();
            do
            {
                if (!iterator.hasNext())
                {
                    break;
                }
                String s = (String)iterator.next();
                DataHolder dataholder = (DataHolder)Oc.getParcelable(s);
                if (dataholder != null)
                {
                    dataholder.close();
                }
            } while (true);
        }

        LoadRequestsResultImpl(Status status, Bundle bundle)
        {
            yz = status;
            Oc = bundle;
        }
    }

    private static final class LoadScoresResultImpl extends b
        implements com.google.android.gms.games.leaderboard.Leaderboards.LoadScoresResult
    {

        private final LeaderboardEntity Od = null;
        private final LeaderboardScoreBuffer Oe;

        public Leaderboard getLeaderboard()
        {
            return Od;
        }

        public LeaderboardScoreBuffer getScores()
        {
            return Oe;
        }

        LoadScoresResultImpl(DataHolder dataholder, DataHolder dataholder1)
        {
            LeaderboardBuffer leaderboardbuffer;
            super(dataholder1);
            leaderboardbuffer = new LeaderboardBuffer(dataholder);
            if (leaderboardbuffer.getCount() <= 0) goto _L2; else goto _L1
_L1:
            Od = (LeaderboardEntity)((Leaderboard)leaderboardbuffer.get(0)).freeze();
_L4:
            leaderboardbuffer.close();
            Oe = new LeaderboardScoreBuffer(dataholder1);
            return;
_L2:
            if (true) goto _L4; else goto _L3
_L3:
            Exception exception;
            exception;
            leaderboardbuffer.close();
            throw exception;
        }
    }

    private static final class LoadSnapshotsResultImpl extends b
        implements com.google.android.gms.games.snapshot.Snapshots.LoadSnapshotsResult
    {

        public SnapshotMetadataBuffer getSnapshots()
        {
            return new SnapshotMetadataBuffer(DG);
        }

        LoadSnapshotsResultImpl(DataHolder dataholder)
        {
            super(dataholder);
        }
    }

    private static final class LoadXpForGameCategoriesResultImpl
        implements com.google.android.gms.games.Players.LoadXpForGameCategoriesResult
    {

        private final List Of;
        private final Bundle Og;
        private final Status yz;

        public Status getStatus()
        {
            return yz;
        }

        LoadXpForGameCategoriesResultImpl(Status status, Bundle bundle)
        {
            yz = status;
            Of = bundle.getStringArrayList("game_category_list");
            Og = bundle;
        }
    }

    private static final class LoadXpStreamResultImpl extends b
        implements com.google.android.gms.games.Players.LoadXpStreamResult
    {

        private final ExperienceEventBuffer Oh;

        LoadXpStreamResultImpl(DataHolder dataholder)
        {
            super(dataholder);
            Oh = new ExperienceEventBuffer(dataholder);
        }
    }

    private final class MatchRemovedCallback extends com.google.android.gms.internal.hb.b
    {

        final GamesClientImpl NE;
        private final String Oi;

        protected void b(OnTurnBasedMatchUpdateReceivedListener onturnbasedmatchupdatereceivedlistener)
        {
            onturnbasedmatchupdatereceivedlistener.onTurnBasedMatchRemoved(Oi);
        }

        protected void d(Object obj)
        {
            b((OnTurnBasedMatchUpdateReceivedListener)obj);
        }

        protected void fu()
        {
        }

        MatchRemovedCallback(OnTurnBasedMatchUpdateReceivedListener onturnbasedmatchupdatereceivedlistener, String s)
        {
            NE = GamesClientImpl.this;
            super(GamesClientImpl.this, onturnbasedmatchupdatereceivedlistener);
            Oi = s;
        }
    }

    private final class MatchUpdateReceivedBinderCallback extends AbstractGamesCallbacks
    {

        final GamesClientImpl NE;
        private final OnTurnBasedMatchUpdateReceivedListener Oj;

        public void onTurnBasedMatchRemoved(String s)
        {
            NE.a(NE. new MatchRemovedCallback(Oj, s));
        }

        public void t(DataHolder dataholder)
        {
            TurnBasedMatchBuffer turnbasedmatchbuffer = new TurnBasedMatchBuffer(dataholder);
            int i1 = turnbasedmatchbuffer.getCount();
            TurnBasedMatch turnbasedmatch;
            turnbasedmatch = null;
            if (i1 <= 0)
            {
                break MISSING_BLOCK_LABEL_41;
            }
            turnbasedmatch = (TurnBasedMatch)((TurnBasedMatch)turnbasedmatchbuffer.get(0)).freeze();
            turnbasedmatchbuffer.close();
            if (turnbasedmatch != null)
            {
                NE.a(NE. new MatchUpdateReceivedCallback(Oj, turnbasedmatch));
            }
            return;
            Exception exception;
            exception;
            turnbasedmatchbuffer.close();
            throw exception;
        }

        MatchUpdateReceivedBinderCallback(OnTurnBasedMatchUpdateReceivedListener onturnbasedmatchupdatereceivedlistener)
        {
            NE = GamesClientImpl.this;
            super();
            Oj = onturnbasedmatchupdatereceivedlistener;
        }
    }

    private final class MatchUpdateReceivedCallback extends com.google.android.gms.internal.hb.b
    {

        final GamesClientImpl NE;
        private final TurnBasedMatch Ok;

        protected void b(OnTurnBasedMatchUpdateReceivedListener onturnbasedmatchupdatereceivedlistener)
        {
            onturnbasedmatchupdatereceivedlistener.onTurnBasedMatchReceived(Ok);
        }

        protected void d(Object obj)
        {
            b((OnTurnBasedMatchUpdateReceivedListener)obj);
        }

        protected void fu()
        {
        }

        MatchUpdateReceivedCallback(OnTurnBasedMatchUpdateReceivedListener onturnbasedmatchupdatereceivedlistener, TurnBasedMatch turnbasedmatch)
        {
            NE = GamesClientImpl.this;
            super(GamesClientImpl.this, onturnbasedmatchupdatereceivedlistener);
            Ok = turnbasedmatch;
        }
    }

    private final class MessageReceivedCallback extends com.google.android.gms.internal.hb.b
    {

        final GamesClientImpl NE;
        private final RealTimeMessage Ol;

        public void a(RealTimeMessageReceivedListener realtimemessagereceivedlistener)
        {
            if (realtimemessagereceivedlistener != null)
            {
                realtimemessagereceivedlistener.onRealTimeMessageReceived(Ol);
            }
        }

        public void d(Object obj)
        {
            a((RealTimeMessageReceivedListener)obj);
        }

        protected void fu()
        {
        }

        MessageReceivedCallback(RealTimeMessageReceivedListener realtimemessagereceivedlistener, RealTimeMessage realtimemessage)
        {
            NE = GamesClientImpl.this;
            super(GamesClientImpl.this, realtimemessagereceivedlistener);
            Ol = realtimemessage;
        }
    }

    private final class NotifyAclLoadedBinderCallback extends AbstractGamesCallbacks
    {

        final GamesClientImpl NE;
        private final com.google.android.gms.common.api.a.d yR;

        public void C(DataHolder dataholder)
        {
            yR.a(new LoadAclResultImpl(dataholder));
        }

        NotifyAclLoadedBinderCallback(com.google.android.gms.common.api.a.d d1)
        {
            NE = GamesClientImpl.this;
            super();
            yR = (com.google.android.gms.common.api.a.d)com.google.android.gms.internal.hm.b(d1, "Holder must not be null");
        }
    }

    private final class NotifyAclUpdatedBinderCallback extends AbstractGamesCallbacks
    {

        final GamesClientImpl NE;
        private final com.google.android.gms.common.api.a.d yR;

        public void cd(int i1)
        {
            yR.a(new Status(i1));
        }

        NotifyAclUpdatedBinderCallback(com.google.android.gms.common.api.a.d d1)
        {
            NE = GamesClientImpl.this;
            super();
            yR = (com.google.android.gms.common.api.a.d)com.google.android.gms.internal.hm.b(d1, "Holder must not be null");
        }
    }

    private static final class OpenSnapshotResultImpl extends b
        implements com.google.android.gms.games.snapshot.Snapshots.OpenSnapshotResult
    {

        private final Snapshot Om;
        private final String On;
        private final Snapshot Oo;
        private final Contents Op;

        public String getConflictId()
        {
            return On;
        }

        public Snapshot getConflictingSnapshot()
        {
            return Oo;
        }

        public Contents getResolutionContents()
        {
            return Op;
        }

        public Snapshot getSnapshot()
        {
            return Om;
        }

        OpenSnapshotResultImpl(DataHolder dataholder, Contents contents)
        {
            this(dataholder, null, contents, null, null);
        }

        OpenSnapshotResultImpl(DataHolder dataholder, String s, Contents contents, Contents contents1, Contents contents2)
        {
            boolean flag;
            SnapshotMetadataBuffer snapshotmetadatabuffer;
            flag = true;
            super(dataholder);
            snapshotmetadatabuffer = new SnapshotMetadataBuffer(dataholder);
            if (snapshotmetadatabuffer.getCount() != 0) goto _L2; else goto _L1
_L1:
            Om = null;
            Oo = null;
_L3:
            snapshotmetadatabuffer.close();
            On = s;
            Op = contents2;
            return;
_L2:
            if (snapshotmetadatabuffer.getCount() != flag)
            {
                break MISSING_BLOCK_LABEL_127;
            }
            Exception exception;
            if (dataholder.getStatusCode() == 4004)
            {
                flag = false;
            }
            gx.A(flag);
            Om = new SnapshotEntity(new SnapshotMetadataEntity(snapshotmetadatabuffer.get(0)), contents);
            Oo = null;
              goto _L3
            exception;
            snapshotmetadatabuffer.close();
            throw exception;
            Om = new SnapshotEntity(new SnapshotMetadataEntity(snapshotmetadatabuffer.get(0)), contents);
            Oo = new SnapshotEntity(new SnapshotMetadataEntity(snapshotmetadatabuffer.get(1)), contents1);
              goto _L3
        }
    }

    private final class OwnerCoverPhotoUrisLoadedBinderCallback extends AbstractGamesCallbacks
    {

        final GamesClientImpl NE;
        private final com.google.android.gms.common.api.a.d yR;

        public void d(int i1, Bundle bundle)
        {
            bundle.setClassLoader(getClass().getClassLoader());
            yR.a(new LoadOwnerCoverPhotoUrisResultImpl(i1, bundle));
        }

        OwnerCoverPhotoUrisLoadedBinderCallback(com.google.android.gms.common.api.a.d d1)
        {
            NE = GamesClientImpl.this;
            super();
            yR = (com.google.android.gms.common.api.a.d)com.google.android.gms.internal.hm.b(d1, "Holder must not be null");
        }
    }

    private final class P2PConnectedCallback extends com.google.android.gms.internal.hb.b
    {

        final GamesClientImpl NE;
        private final String Oq;

        public void a(RoomStatusUpdateListener roomstatusupdatelistener)
        {
            if (roomstatusupdatelistener != null)
            {
                roomstatusupdatelistener.onP2PConnected(Oq);
            }
        }

        public void d(Object obj)
        {
            a((RoomStatusUpdateListener)obj);
        }

        protected void fu()
        {
        }

        P2PConnectedCallback(RoomStatusUpdateListener roomstatusupdatelistener, String s)
        {
            NE = GamesClientImpl.this;
            super(GamesClientImpl.this, roomstatusupdatelistener);
            Oq = s;
        }
    }

    private final class P2PDisconnectedCallback extends com.google.android.gms.internal.hb.b
    {

        final GamesClientImpl NE;
        private final String Oq;

        public void a(RoomStatusUpdateListener roomstatusupdatelistener)
        {
            if (roomstatusupdatelistener != null)
            {
                roomstatusupdatelistener.onP2PDisconnected(Oq);
            }
        }

        public void d(Object obj)
        {
            a((RoomStatusUpdateListener)obj);
        }

        protected void fu()
        {
        }

        P2PDisconnectedCallback(RoomStatusUpdateListener roomstatusupdatelistener, String s)
        {
            NE = GamesClientImpl.this;
            super(GamesClientImpl.this, roomstatusupdatelistener);
            Oq = s;
        }
    }

    private final class PeerConnectedCallback extends AbstractPeerStatusCallback
    {

        final GamesClientImpl NE;

        protected void a(RoomStatusUpdateListener roomstatusupdatelistener, Room room, ArrayList arraylist)
        {
            roomstatusupdatelistener.onPeersConnected(room, arraylist);
        }

        PeerConnectedCallback(RoomStatusUpdateListener roomstatusupdatelistener, DataHolder dataholder, String as[])
        {
            NE = GamesClientImpl.this;
            super(roomstatusupdatelistener, dataholder, as);
        }
    }

    private final class PeerDeclinedCallback extends AbstractPeerStatusCallback
    {

        final GamesClientImpl NE;

        protected void a(RoomStatusUpdateListener roomstatusupdatelistener, Room room, ArrayList arraylist)
        {
            roomstatusupdatelistener.onPeerDeclined(room, arraylist);
        }

        PeerDeclinedCallback(RoomStatusUpdateListener roomstatusupdatelistener, DataHolder dataholder, String as[])
        {
            NE = GamesClientImpl.this;
            super(roomstatusupdatelistener, dataholder, as);
        }
    }

    private final class PeerDisconnectedCallback extends AbstractPeerStatusCallback
    {

        final GamesClientImpl NE;

        protected void a(RoomStatusUpdateListener roomstatusupdatelistener, Room room, ArrayList arraylist)
        {
            roomstatusupdatelistener.onPeersDisconnected(room, arraylist);
        }

        PeerDisconnectedCallback(RoomStatusUpdateListener roomstatusupdatelistener, DataHolder dataholder, String as[])
        {
            NE = GamesClientImpl.this;
            super(roomstatusupdatelistener, dataholder, as);
        }
    }

    private final class PeerInvitedToRoomCallback extends AbstractPeerStatusCallback
    {

        final GamesClientImpl NE;

        protected void a(RoomStatusUpdateListener roomstatusupdatelistener, Room room, ArrayList arraylist)
        {
            roomstatusupdatelistener.onPeerInvitedToRoom(room, arraylist);
        }

        PeerInvitedToRoomCallback(RoomStatusUpdateListener roomstatusupdatelistener, DataHolder dataholder, String as[])
        {
            NE = GamesClientImpl.this;
            super(roomstatusupdatelistener, dataholder, as);
        }
    }

    private final class PeerJoinedRoomCallback extends AbstractPeerStatusCallback
    {

        final GamesClientImpl NE;

        protected void a(RoomStatusUpdateListener roomstatusupdatelistener, Room room, ArrayList arraylist)
        {
            roomstatusupdatelistener.onPeerJoined(room, arraylist);
        }

        PeerJoinedRoomCallback(RoomStatusUpdateListener roomstatusupdatelistener, DataHolder dataholder, String as[])
        {
            NE = GamesClientImpl.this;
            super(roomstatusupdatelistener, dataholder, as);
        }
    }

    private final class PeerLeftRoomCallback extends AbstractPeerStatusCallback
    {

        final GamesClientImpl NE;

        protected void a(RoomStatusUpdateListener roomstatusupdatelistener, Room room, ArrayList arraylist)
        {
            roomstatusupdatelistener.onPeerLeft(room, arraylist);
        }

        PeerLeftRoomCallback(RoomStatusUpdateListener roomstatusupdatelistener, DataHolder dataholder, String as[])
        {
            NE = GamesClientImpl.this;
            super(roomstatusupdatelistener, dataholder, as);
        }
    }

    private final class PlayerLeaderboardScoreLoadedBinderCallback extends AbstractGamesCallbacks
    {

        final GamesClientImpl NE;
        private final com.google.android.gms.common.api.a.d yR;

        public void E(DataHolder dataholder)
        {
            yR.a(new LoadPlayerScoreResultImpl(dataholder));
        }

        PlayerLeaderboardScoreLoadedBinderCallback(com.google.android.gms.common.api.a.d d1)
        {
            NE = GamesClientImpl.this;
            super();
            yR = (com.google.android.gms.common.api.a.d)com.google.android.gms.internal.hm.b(d1, "Holder must not be null");
        }
    }

    private final class PlayerXpForGameCategoriesLoadedBinderCallback extends AbstractGamesCallbacks
    {

        final GamesClientImpl NE;
        private final com.google.android.gms.common.api.a.d yR;

        public void e(int i1, Bundle bundle)
        {
            bundle.setClassLoader(getClass().getClassLoader());
            Status status = new Status(i1);
            yR.a(new LoadXpForGameCategoriesResultImpl(status, bundle));
        }

        PlayerXpForGameCategoriesLoadedBinderCallback(com.google.android.gms.common.api.a.d d1)
        {
            NE = GamesClientImpl.this;
            super();
            yR = (com.google.android.gms.common.api.a.d)com.google.android.gms.internal.hm.b(d1, "Holder must not be null");
        }
    }

    final class PlayerXpStreamLoadedBinderCallback extends AbstractGamesCallbacks
    {

        final GamesClientImpl NE;
        private final com.google.android.gms.common.api.a.d yR;

        public void P(DataHolder dataholder)
        {
            yR.a(new LoadXpStreamResultImpl(dataholder));
        }

        PlayerXpStreamLoadedBinderCallback(com.google.android.gms.common.api.a.d d1)
        {
            NE = GamesClientImpl.this;
            super();
            yR = (com.google.android.gms.common.api.a.d)com.google.android.gms.internal.hm.b(d1, "Holder must not be null");
        }
    }

    private final class PlayersLoadedBinderCallback extends AbstractGamesCallbacks
    {

        final GamesClientImpl NE;
        private final com.google.android.gms.common.api.a.d yR;

        public void g(DataHolder dataholder)
        {
            yR.a(new LoadPlayersResultImpl(dataholder));
        }

        public void h(DataHolder dataholder)
        {
            yR.a(new LoadPlayersResultImpl(dataholder));
        }

        PlayersLoadedBinderCallback(com.google.android.gms.common.api.a.d d1)
        {
            NE = GamesClientImpl.this;
            super();
            yR = (com.google.android.gms.common.api.a.d)com.google.android.gms.internal.hm.b(d1, "Holder must not be null");
        }
    }

    private final class QuestAcceptedBinderCallbacks extends AbstractGamesCallbacks
    {

        final GamesClientImpl NE;
        private final com.google.android.gms.common.api.a.d Or;

        public void L(DataHolder dataholder)
        {
            Or.a(new AcceptQuestResultImpl(dataholder));
        }

        public QuestAcceptedBinderCallbacks(com.google.android.gms.common.api.a.d d1)
        {
            NE = GamesClientImpl.this;
            super();
            Or = (com.google.android.gms.common.api.a.d)com.google.android.gms.internal.hm.b(d1, "Holder must not be null");
        }
    }

    private final class QuestCompletedCallback extends com.google.android.gms.internal.hb.b
    {

        final GamesClientImpl NE;
        private final Quest NG;

        protected void b(QuestUpdateListener questupdatelistener)
        {
            questupdatelistener.onQuestCompleted(NG);
        }

        protected void d(Object obj)
        {
            b((QuestUpdateListener)obj);
        }

        protected void fu()
        {
        }

        QuestCompletedCallback(QuestUpdateListener questupdatelistener, Quest quest)
        {
            NE = GamesClientImpl.this;
            super(GamesClientImpl.this, questupdatelistener);
            NG = quest;
        }
    }

    private final class QuestMilestoneClaimBinderCallbacks extends AbstractGamesCallbacks
    {

        final GamesClientImpl NE;
        private final com.google.android.gms.common.api.a.d Os;
        private final String Ot;

        public void K(DataHolder dataholder)
        {
            Os.a(new ClaimMilestoneResultImpl(dataholder, Ot));
        }

        public QuestMilestoneClaimBinderCallbacks(com.google.android.gms.common.api.a.d d1, String s)
        {
            NE = GamesClientImpl.this;
            super();
            Os = (com.google.android.gms.common.api.a.d)com.google.android.gms.internal.hm.b(d1, "Holder must not be null");
            Ot = (String)com.google.android.gms.internal.hm.b(s, "MilestoneId must not be null");
        }
    }

    private final class QuestUpdateBinderCallback extends AbstractGamesCallbacks
    {

        final GamesClientImpl NE;
        private final QuestUpdateListener Ou;

        private Quest R(DataHolder dataholder)
        {
            QuestBuffer questbuffer = new QuestBuffer(dataholder);
            int i1 = questbuffer.getCount();
            Quest quest;
            quest = null;
            if (i1 <= 0)
            {
                break MISSING_BLOCK_LABEL_41;
            }
            quest = (Quest)((Quest)questbuffer.get(0)).freeze();
            questbuffer.close();
            return quest;
            Exception exception;
            exception;
            questbuffer.close();
            throw exception;
        }

        public void M(DataHolder dataholder)
        {
            Quest quest = R(dataholder);
            if (quest != null)
            {
                NE.a(NE. new QuestCompletedCallback(Ou, quest));
            }
        }

        QuestUpdateBinderCallback(QuestUpdateListener questupdatelistener)
        {
            NE = GamesClientImpl.this;
            super();
            Ou = questupdatelistener;
        }
    }

    private final class QuestsLoadedBinderCallbacks extends AbstractGamesCallbacks
    {

        final GamesClientImpl NE;
        private final com.google.android.gms.common.api.a.d Ov;

        public void O(DataHolder dataholder)
        {
            Ov.a(new LoadQuestsResultImpl(dataholder));
        }

        public QuestsLoadedBinderCallbacks(com.google.android.gms.common.api.a.d d1)
        {
            NE = GamesClientImpl.this;
            super();
            Ov = (com.google.android.gms.common.api.a.d)com.google.android.gms.internal.hm.b(d1, "Holder must not be null");
        }
    }

    private final class RealTimeMessageSentCallback extends com.google.android.gms.internal.hb.b
    {

        private final int CT;
        final GamesClientImpl NE;
        private final String Ow;
        private final int Ox;

        public void a(com.google.android.gms.games.multiplayer.realtime.RealTimeMultiplayer.ReliableMessageSentCallback reliablemessagesentcallback)
        {
            if (reliablemessagesentcallback != null)
            {
                reliablemessagesentcallback.onRealTimeMessageSent(CT, Ox, Ow);
            }
        }

        public void d(Object obj)
        {
            a((com.google.android.gms.games.multiplayer.realtime.RealTimeMultiplayer.ReliableMessageSentCallback)obj);
        }

        protected void fu()
        {
        }

        RealTimeMessageSentCallback(com.google.android.gms.games.multiplayer.realtime.RealTimeMultiplayer.ReliableMessageSentCallback reliablemessagesentcallback, int i1, int j1, String s)
        {
            NE = GamesClientImpl.this;
            super(GamesClientImpl.this, reliablemessagesentcallback);
            CT = i1;
            Ox = j1;
            Ow = s;
        }
    }

    private final class RealTimeReliableMessageBinderCallbacks extends AbstractGamesCallbacks
    {

        final GamesClientImpl NE;
        final com.google.android.gms.games.multiplayer.realtime.RealTimeMultiplayer.ReliableMessageSentCallback Oy;

        public void b(int i1, int j1, String s)
        {
            NE.a(NE. new RealTimeMessageSentCallback(Oy, i1, j1, s));
        }

        public RealTimeReliableMessageBinderCallbacks(com.google.android.gms.games.multiplayer.realtime.RealTimeMultiplayer.ReliableMessageSentCallback reliablemessagesentcallback)
        {
            NE = GamesClientImpl.this;
            super();
            Oy = reliablemessagesentcallback;
        }
    }

    private final class RequestReceivedBinderCallback extends AbstractGamesCallbacks
    {

        final GamesClientImpl NE;
        private final OnRequestReceivedListener Oz;

        public void o(DataHolder dataholder)
        {
            GameRequestBuffer gamerequestbuffer = new GameRequestBuffer(dataholder);
            int i1 = gamerequestbuffer.getCount();
            GameRequest gamerequest;
            gamerequest = null;
            if (i1 <= 0)
            {
                break MISSING_BLOCK_LABEL_41;
            }
            gamerequest = (GameRequest)((GameRequest)gamerequestbuffer.get(0)).freeze();
            gamerequestbuffer.close();
            if (gamerequest != null)
            {
                NE.a(NE. new RequestReceivedCallback(Oz, gamerequest));
            }
            return;
            Exception exception;
            exception;
            gamerequestbuffer.close();
            throw exception;
        }

        public void onRequestRemoved(String s)
        {
            NE.a(NE. new RequestRemovedCallback(Oz, s));
        }

        RequestReceivedBinderCallback(OnRequestReceivedListener onrequestreceivedlistener)
        {
            NE = GamesClientImpl.this;
            super();
            Oz = onrequestreceivedlistener;
        }
    }

    private final class RequestReceivedCallback extends com.google.android.gms.internal.hb.b
    {

        final GamesClientImpl NE;
        private final GameRequest OA;

        protected void b(OnRequestReceivedListener onrequestreceivedlistener)
        {
            onrequestreceivedlistener.onRequestReceived(OA);
        }

        protected void d(Object obj)
        {
            b((OnRequestReceivedListener)obj);
        }

        protected void fu()
        {
        }

        RequestReceivedCallback(OnRequestReceivedListener onrequestreceivedlistener, GameRequest gamerequest)
        {
            NE = GamesClientImpl.this;
            super(GamesClientImpl.this, onrequestreceivedlistener);
            OA = gamerequest;
        }
    }

    private final class RequestRemovedCallback extends com.google.android.gms.internal.hb.b
    {

        final GamesClientImpl NE;
        private final String OB;

        protected void b(OnRequestReceivedListener onrequestreceivedlistener)
        {
            onrequestreceivedlistener.onRequestRemoved(OB);
        }

        protected void d(Object obj)
        {
            b((OnRequestReceivedListener)obj);
        }

        protected void fu()
        {
        }

        RequestRemovedCallback(OnRequestReceivedListener onrequestreceivedlistener, String s)
        {
            NE = GamesClientImpl.this;
            super(GamesClientImpl.this, onrequestreceivedlistener);
            OB = s;
        }
    }

    private final class RequestSentBinderCallbacks extends AbstractGamesCallbacks
    {

        final GamesClientImpl NE;
        private final com.google.android.gms.common.api.a.d OC;

        public void G(DataHolder dataholder)
        {
            OC.a(new SendRequestResultImpl(dataholder));
        }

        public RequestSentBinderCallbacks(com.google.android.gms.common.api.a.d d1)
        {
            NE = GamesClientImpl.this;
            super();
            OC = (com.google.android.gms.common.api.a.d)com.google.android.gms.internal.hm.b(d1, "Holder must not be null");
        }
    }

    private final class RequestSummariesLoadedBinderCallbacks extends AbstractGamesCallbacks
    {

        final GamesClientImpl NE;
        private final com.google.android.gms.common.api.a.d OD;

        public void H(DataHolder dataholder)
        {
            OD.a(new LoadRequestSummariesResultImpl(dataholder));
        }

        public RequestSummariesLoadedBinderCallbacks(com.google.android.gms.common.api.a.d d1)
        {
            NE = GamesClientImpl.this;
            super();
            OD = (com.google.android.gms.common.api.a.d)com.google.android.gms.internal.hm.b(d1, "Holder must not be null");
        }
    }

    private final class RequestsLoadedBinderCallbacks extends AbstractGamesCallbacks
    {

        final GamesClientImpl NE;
        private final com.google.android.gms.common.api.a.d OE;

        public void c(int i1, Bundle bundle)
        {
            bundle.setClassLoader(getClass().getClassLoader());
            Status status = new Status(i1);
            OE.a(new LoadRequestsResultImpl(status, bundle));
        }

        public RequestsLoadedBinderCallbacks(com.google.android.gms.common.api.a.d d1)
        {
            NE = GamesClientImpl.this;
            super();
            OE = (com.google.android.gms.common.api.a.d)com.google.android.gms.internal.hm.b(d1, "Holder must not be null");
        }
    }

    private final class RequestsUpdatedBinderCallbacks extends AbstractGamesCallbacks
    {

        final GamesClientImpl NE;
        private final com.google.android.gms.common.api.a.d OF;

        public void F(DataHolder dataholder)
        {
            OF.a(new UpdateRequestsResultImpl(dataholder));
        }

        public RequestsUpdatedBinderCallbacks(com.google.android.gms.common.api.a.d d1)
        {
            NE = GamesClientImpl.this;
            super();
            OF = (com.google.android.gms.common.api.a.d)com.google.android.gms.internal.hm.b(d1, "Holder must not be null");
        }
    }

    private final class RoomAutoMatchingCallback extends AbstractRoomStatusCallback
    {

        final GamesClientImpl NE;

        public void a(RoomStatusUpdateListener roomstatusupdatelistener, Room room)
        {
            roomstatusupdatelistener.onRoomAutoMatching(room);
        }

        RoomAutoMatchingCallback(RoomStatusUpdateListener roomstatusupdatelistener, DataHolder dataholder)
        {
            NE = GamesClientImpl.this;
            super(roomstatusupdatelistener, dataholder);
        }
    }

    private final class RoomBinderCallbacks extends AbstractGamesCallbacks
    {

        final GamesClientImpl NE;
        private final RoomUpdateListener OG;
        private final RoomStatusUpdateListener OH;
        private final RealTimeMessageReceivedListener OI;

        public void A(DataHolder dataholder)
        {
            NE.a(NE. new DisconnectedFromRoomCallback(OH, dataholder));
        }

        public void a(DataHolder dataholder, String as[])
        {
            NE.a(NE. new PeerInvitedToRoomCallback(OH, dataholder, as));
        }

        public void b(DataHolder dataholder, String as[])
        {
            NE.a(NE. new PeerJoinedRoomCallback(OH, dataholder, as));
        }

        public void c(DataHolder dataholder, String as[])
        {
            NE.a(NE. new PeerLeftRoomCallback(OH, dataholder, as));
        }

        public void d(DataHolder dataholder, String as[])
        {
            NE.a(NE. new PeerDeclinedCallback(OH, dataholder, as));
        }

        public void e(DataHolder dataholder, String as[])
        {
            NE.a(NE. new PeerConnectedCallback(OH, dataholder, as));
        }

        public void f(DataHolder dataholder, String as[])
        {
            NE.a(NE. new PeerDisconnectedCallback(OH, dataholder, as));
        }

        public void onLeftRoom(int i1, String s)
        {
            NE.a(NE. new LeftRoomCallback(OG, i1, s));
        }

        public void onP2PConnected(String s)
        {
            NE.a(NE. new P2PConnectedCallback(OH, s));
        }

        public void onP2PDisconnected(String s)
        {
            NE.a(NE. new P2PDisconnectedCallback(OH, s));
        }

        public void onRealTimeMessageReceived(RealTimeMessage realtimemessage)
        {
            NE.a(NE. new MessageReceivedCallback(OI, realtimemessage));
        }

        public void u(DataHolder dataholder)
        {
            NE.a(NE. new RoomCreatedCallback(OG, dataholder));
        }

        public void v(DataHolder dataholder)
        {
            NE.a(NE. new JoinedRoomCallback(OG, dataholder));
        }

        public void w(DataHolder dataholder)
        {
            NE.a(NE. new RoomConnectingCallback(OH, dataholder));
        }

        public void x(DataHolder dataholder)
        {
            NE.a(NE. new RoomAutoMatchingCallback(OH, dataholder));
        }

        public void y(DataHolder dataholder)
        {
            NE.a(NE. new RoomConnectedCallback(OG, dataholder));
        }

        public void z(DataHolder dataholder)
        {
            NE.a(NE. new ConnectedToRoomCallback(OH, dataholder));
        }

        public RoomBinderCallbacks(RoomUpdateListener roomupdatelistener)
        {
            NE = GamesClientImpl.this;
            super();
            OG = (RoomUpdateListener)com.google.android.gms.internal.hm.b(roomupdatelistener, "Callbacks must not be null");
            OH = null;
            OI = null;
        }

        public RoomBinderCallbacks(RoomUpdateListener roomupdatelistener, RoomStatusUpdateListener roomstatusupdatelistener, RealTimeMessageReceivedListener realtimemessagereceivedlistener)
        {
            NE = GamesClientImpl.this;
            super();
            OG = (RoomUpdateListener)com.google.android.gms.internal.hm.b(roomupdatelistener, "Callbacks must not be null");
            OH = roomstatusupdatelistener;
            OI = realtimemessagereceivedlistener;
        }
    }

    private final class RoomConnectedCallback extends AbstractRoomCallback
    {

        final GamesClientImpl NE;

        public void a(RoomUpdateListener roomupdatelistener, Room room, int i1)
        {
            roomupdatelistener.onRoomConnected(i1, room);
        }

        RoomConnectedCallback(RoomUpdateListener roomupdatelistener, DataHolder dataholder)
        {
            NE = GamesClientImpl.this;
            super(roomupdatelistener, dataholder);
        }
    }

    private final class RoomConnectingCallback extends AbstractRoomStatusCallback
    {

        final GamesClientImpl NE;

        public void a(RoomStatusUpdateListener roomstatusupdatelistener, Room room)
        {
            roomstatusupdatelistener.onRoomConnecting(room);
        }

        RoomConnectingCallback(RoomStatusUpdateListener roomstatusupdatelistener, DataHolder dataholder)
        {
            NE = GamesClientImpl.this;
            super(roomstatusupdatelistener, dataholder);
        }
    }

    private final class RoomCreatedCallback extends AbstractRoomCallback
    {

        final GamesClientImpl NE;

        public void a(RoomUpdateListener roomupdatelistener, Room room, int i1)
        {
            roomupdatelistener.onRoomCreated(i1, room);
        }

        public RoomCreatedCallback(RoomUpdateListener roomupdatelistener, DataHolder dataholder)
        {
            NE = GamesClientImpl.this;
            super(roomupdatelistener, dataholder);
        }
    }

    private static final class SendRequestResultImpl extends b
        implements com.google.android.gms.games.request.Requests.SendRequestResult
    {

        private final GameRequest OA = null;

        SendRequestResultImpl(DataHolder dataholder)
        {
            GameRequestBuffer gamerequestbuffer;
            super(dataholder);
            gamerequestbuffer = new GameRequestBuffer(dataholder);
            if (gamerequestbuffer.getCount() <= 0) goto _L2; else goto _L1
_L1:
            OA = (GameRequest)((GameRequest)gamerequestbuffer.get(0)).freeze();
_L4:
            gamerequestbuffer.close();
            return;
_L2:
            if (true) goto _L4; else goto _L3
_L3:
            Exception exception;
            exception;
            gamerequestbuffer.close();
            throw exception;
        }
    }

    private final class SignOutCompleteBinderCallbacks extends AbstractGamesCallbacks
    {

        final GamesClientImpl NE;
        private final com.google.android.gms.common.api.a.d yR;

        public void dT()
        {
            Status status = new Status(0);
            yR.a(status);
        }

        public SignOutCompleteBinderCallbacks(com.google.android.gms.common.api.a.d d1)
        {
            NE = GamesClientImpl.this;
            super();
            yR = (com.google.android.gms.common.api.a.d)com.google.android.gms.internal.hm.b(d1, "Holder must not be null");
        }
    }

    private final class SnapshotCommittedBinderCallbacks extends AbstractGamesCallbacks
    {

        final GamesClientImpl NE;
        private final com.google.android.gms.common.api.a.d OJ;

        public void J(DataHolder dataholder)
        {
            OJ.a(new CommitSnapshotResultImpl(dataholder));
        }

        public SnapshotCommittedBinderCallbacks(com.google.android.gms.common.api.a.d d1)
        {
            NE = GamesClientImpl.this;
            super();
            OJ = (com.google.android.gms.common.api.a.d)com.google.android.gms.internal.hm.b(d1, "Holder must not be null");
        }
    }

    final class SnapshotDeletedBinderCallbacks extends AbstractGamesCallbacks
    {

        final GamesClientImpl NE;
        private final com.google.android.gms.common.api.a.d yR;

        public void g(int i1, String s)
        {
            yR.a(new DeleteSnapshotResultImpl(i1, s));
        }

        public SnapshotDeletedBinderCallbacks(com.google.android.gms.common.api.a.d d1)
        {
            NE = GamesClientImpl.this;
            super();
            yR = (com.google.android.gms.common.api.a.d)com.google.android.gms.internal.hm.b(d1, "Holder must not be null");
        }
    }

    private final class SnapshotOpenedBinderCallbacks extends AbstractGamesCallbacks
    {

        final GamesClientImpl NE;
        private final com.google.android.gms.common.api.a.d OK;

        public void a(DataHolder dataholder, Contents contents)
        {
            OK.a(new OpenSnapshotResultImpl(dataholder, contents));
        }

        public void a(DataHolder dataholder, String s, Contents contents, Contents contents1, Contents contents2)
        {
            OK.a(new OpenSnapshotResultImpl(dataholder, s, contents, contents1, contents2));
        }

        public SnapshotOpenedBinderCallbacks(com.google.android.gms.common.api.a.d d1)
        {
            NE = GamesClientImpl.this;
            super();
            OK = (com.google.android.gms.common.api.a.d)com.google.android.gms.internal.hm.b(d1, "Holder must not be null");
        }
    }

    private final class SnapshotsLoadedBinderCallbacks extends AbstractGamesCallbacks
    {

        final GamesClientImpl NE;
        private final com.google.android.gms.common.api.a.d OL;

        public void I(DataHolder dataholder)
        {
            OL.a(new LoadSnapshotsResultImpl(dataholder));
        }

        public SnapshotsLoadedBinderCallbacks(com.google.android.gms.common.api.a.d d1)
        {
            NE = GamesClientImpl.this;
            super();
            OL = (com.google.android.gms.common.api.a.d)com.google.android.gms.internal.hm.b(d1, "Holder must not be null");
        }
    }

    private final class SubmitScoreBinderCallbacks extends AbstractGamesCallbacks
    {

        final GamesClientImpl NE;
        private final com.google.android.gms.common.api.a.d yR;

        public void f(DataHolder dataholder)
        {
            yR.a(new SubmitScoreResultImpl(dataholder));
        }

        public SubmitScoreBinderCallbacks(com.google.android.gms.common.api.a.d d1)
        {
            NE = GamesClientImpl.this;
            super();
            yR = (com.google.android.gms.common.api.a.d)com.google.android.gms.internal.hm.b(d1, "Holder must not be null");
        }
    }

    private static final class SubmitScoreResultImpl extends b
        implements com.google.android.gms.games.leaderboard.Leaderboards.SubmitScoreResult
    {

        private final ScoreSubmissionData OM;

        public ScoreSubmissionData getScoreData()
        {
            return OM;
        }

        public SubmitScoreResultImpl(DataHolder dataholder)
        {
            super(dataholder);
            OM = new ScoreSubmissionData(dataholder);
            dataholder.close();
            return;
            Exception exception;
            exception;
            dataholder.close();
            throw exception;
        }
    }

    private final class TurnBasedMatchCanceledBinderCallbacks extends AbstractGamesCallbacks
    {

        final GamesClientImpl NE;
        private final com.google.android.gms.common.api.a.d ON;

        public void f(int i1, String s)
        {
            Status status = new Status(i1);
            ON.a(new CancelMatchResultImpl(status, s));
        }

        public TurnBasedMatchCanceledBinderCallbacks(com.google.android.gms.common.api.a.d d1)
        {
            NE = GamesClientImpl.this;
            super();
            ON = (com.google.android.gms.common.api.a.d)com.google.android.gms.internal.hm.b(d1, "Holder must not be null");
        }
    }

    private final class TurnBasedMatchInitiatedBinderCallbacks extends AbstractGamesCallbacks
    {

        final GamesClientImpl NE;
        private final com.google.android.gms.common.api.a.d OO;

        public void q(DataHolder dataholder)
        {
            OO.a(new InitiateMatchResultImpl(dataholder));
        }

        public TurnBasedMatchInitiatedBinderCallbacks(com.google.android.gms.common.api.a.d d1)
        {
            NE = GamesClientImpl.this;
            super();
            OO = (com.google.android.gms.common.api.a.d)com.google.android.gms.internal.hm.b(d1, "Holder must not be null");
        }
    }

    private final class TurnBasedMatchLeftBinderCallbacks extends AbstractGamesCallbacks
    {

        final GamesClientImpl NE;
        private final com.google.android.gms.common.api.a.d OP;

        public void s(DataHolder dataholder)
        {
            OP.a(new LeaveMatchResultImpl(dataholder));
        }

        public TurnBasedMatchLeftBinderCallbacks(com.google.android.gms.common.api.a.d d1)
        {
            NE = GamesClientImpl.this;
            super();
            OP = (com.google.android.gms.common.api.a.d)com.google.android.gms.internal.hm.b(d1, "Holder must not be null");
        }
    }

    private final class TurnBasedMatchLoadedBinderCallbacks extends AbstractGamesCallbacks
    {

        final GamesClientImpl NE;
        private final com.google.android.gms.common.api.a.d OQ;

        public void p(DataHolder dataholder)
        {
            OQ.a(new LoadMatchResultImpl(dataholder));
        }

        public TurnBasedMatchLoadedBinderCallbacks(com.google.android.gms.common.api.a.d d1)
        {
            NE = GamesClientImpl.this;
            super();
            OQ = (com.google.android.gms.common.api.a.d)com.google.android.gms.internal.hm.b(d1, "Holder must not be null");
        }
    }

    private static abstract class TurnBasedMatchResult extends b
    {

        final TurnBasedMatch Ok = null;

        public TurnBasedMatch getMatch()
        {
            return Ok;
        }

        TurnBasedMatchResult(DataHolder dataholder)
        {
            TurnBasedMatchBuffer turnbasedmatchbuffer;
            super(dataholder);
            turnbasedmatchbuffer = new TurnBasedMatchBuffer(dataholder);
            if (turnbasedmatchbuffer.getCount() <= 0) goto _L2; else goto _L1
_L1:
            Ok = (TurnBasedMatch)((TurnBasedMatch)turnbasedmatchbuffer.get(0)).freeze();
_L4:
            turnbasedmatchbuffer.close();
            return;
_L2:
            if (true) goto _L4; else goto _L3
_L3:
            Exception exception;
            exception;
            turnbasedmatchbuffer.close();
            throw exception;
        }
    }

    private final class TurnBasedMatchUpdatedBinderCallbacks extends AbstractGamesCallbacks
    {

        final GamesClientImpl NE;
        private final com.google.android.gms.common.api.a.d OR;

        public void r(DataHolder dataholder)
        {
            OR.a(new UpdateMatchResultImpl(dataholder));
        }

        public TurnBasedMatchUpdatedBinderCallbacks(com.google.android.gms.common.api.a.d d1)
        {
            NE = GamesClientImpl.this;
            super();
            OR = (com.google.android.gms.common.api.a.d)com.google.android.gms.internal.hm.b(d1, "Holder must not be null");
        }
    }

    private final class TurnBasedMatchesLoadedBinderCallbacks extends AbstractGamesCallbacks
    {

        final GamesClientImpl NE;
        private final com.google.android.gms.common.api.a.d OS;

        public void b(int i1, Bundle bundle)
        {
            bundle.setClassLoader(getClass().getClassLoader());
            Status status = new Status(i1);
            OS.a(new LoadMatchesResultImpl(status, bundle));
        }

        public TurnBasedMatchesLoadedBinderCallbacks(com.google.android.gms.common.api.a.d d1)
        {
            NE = GamesClientImpl.this;
            super();
            OS = (com.google.android.gms.common.api.a.d)com.google.android.gms.internal.hm.b(d1, "Holder must not be null");
        }
    }

    private static final class UpdateAchievementResultImpl
        implements com.google.android.gms.games.achievement.Achievements.UpdateAchievementResult
    {

        private final String OT;
        private final Status yz;

        public String getAchievementId()
        {
            return OT;
        }

        public Status getStatus()
        {
            return yz;
        }

        UpdateAchievementResultImpl(int i1, String s)
        {
            yz = new Status(i1);
            OT = s;
        }
    }

    private static final class UpdateMatchResultImpl extends TurnBasedMatchResult
        implements com.google.android.gms.games.multiplayer.turnbased.TurnBasedMultiplayer.UpdateMatchResult
    {

        UpdateMatchResultImpl(DataHolder dataholder)
        {
            super(dataholder);
        }
    }

    private static final class UpdateRequestsResultImpl extends b
        implements com.google.android.gms.games.request.Requests.UpdateRequestsResult
    {

        private final RequestUpdateOutcomes OU;

        public Set getRequestIds()
        {
            return OU.getRequestIds();
        }

        public int getRequestOutcome(String s)
        {
            return OU.getRequestOutcome(s);
        }

        UpdateRequestsResultImpl(DataHolder dataholder)
        {
            super(dataholder);
            OU = RequestUpdateOutcomes.U(dataholder);
        }
    }


    private final boolean NA;
    private final int NB;
    private final boolean NC;
    private final String ND;
    EventIncrementManager Np;
    private final String Nq;
    private final Map Nr = new HashMap();
    private PlayerEntity Ns;
    private GameEntity Nt;
    private final PopupManager Nu;
    private boolean Nv;
    private boolean Nw;
    private int Nx;
    private final Binder Ny = new Binder();
    private final long Nz = (long)hashCode();
    private final String yQ;

    public GamesClientImpl(Context context, Looper looper, String s, String s1, com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks connectioncallbacks, com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener onconnectionfailedlistener, String as[], 
            int i1, View view, boolean flag, boolean flag1, int j1, boolean flag2, int k1, 
            String s2)
    {
        super(context, looper, connectioncallbacks, onconnectionfailedlistener, as);
        Np = new EventIncrementManager() {

            final GamesClientImpl NE;

            public EventIncrementCache hx()
            {
                return NE. new GameClientEventIncrementCache();
            }

            
            {
                NE = GamesClientImpl.this;
                super();
            }
        };
        Nv = false;
        Nw = false;
        Nq = s;
        yQ = (String)com.google.android.gms.internal.hm.f(s1);
        Nu = com.google.android.gms.games.internal.PopupManager.a(this, i1);
        f(view);
        Nw = flag1;
        Nx = j1;
        NA = flag;
        NC = flag2;
        NB = k1;
        ND = s2;
        registerConnectionCallbacks(this);
        registerConnectionFailedListener(this);
    }

    private Room Q(DataHolder dataholder)
    {
        RoomBuffer roombuffer = new RoomBuffer(dataholder);
        int i1 = roombuffer.getCount();
        Room room;
        room = null;
        if (i1 <= 0)
        {
            break MISSING_BLOCK_LABEL_41;
        }
        room = (Room)((Room)roombuffer.get(0)).freeze();
        roombuffer.close();
        return room;
        Exception exception;
        exception;
        roombuffer.close();
        throw exception;
    }

    static Room a(GamesClientImpl gamesclientimpl, DataHolder dataholder)
    {
        return gamesclientimpl.Q(dataholder);
    }

    private RealTimeSocket aT(String s)
    {
        android.os.ParcelFileDescriptor parcelfiledescriptor;
        LibjingleNativeSocket libjinglenativesocket;
        String s1;
        LocalSocket localsocket;
        RealTimeSocketImpl realtimesocketimpl;
        try
        {
            parcelfiledescriptor = ((IGamesService)ft()).bb(s);
        }
        catch (RemoteException remoteexception)
        {
            GamesLog.k("GamesClientImpl", "Unable to create socket. Service died.");
            return null;
        }
        if (parcelfiledescriptor == null)
        {
            break MISSING_BLOCK_LABEL_51;
        }
        GamesLog.i("GamesClientImpl", "Created native libjingle socket.");
        libjinglenativesocket = new LibjingleNativeSocket(parcelfiledescriptor);
        Nr.put(s, libjinglenativesocket);
        return libjinglenativesocket;
        GamesLog.i("GamesClientImpl", "Unable to create native libjingle socket, resorting to old socket.");
        s1 = ((IGamesService)ft()).aW(s);
        if (s1 == null)
        {
            return null;
        }
        localsocket = new LocalSocket();
        localsocket.connect(new LocalSocketAddress(s1));
        realtimesocketimpl = new RealTimeSocketImpl(localsocket, s);
        Nr.put(s, realtimesocketimpl);
        return realtimesocketimpl;
        IOException ioexception;
        ioexception;
        GamesLog.k("GamesClientImpl", (new StringBuilder()).append("connect() call failed on socket: ").append(ioexception.getMessage()).toString());
        return null;
    }

    private void gY()
    {
        Ns = null;
    }

    private void hv()
    {
        for (Iterator iterator = Nr.values().iterator(); iterator.hasNext();)
        {
            RealTimeSocket realtimesocket = (RealTimeSocket)iterator.next();
            try
            {
                realtimesocket.close();
            }
            catch (IOException ioexception)
            {
                com.google.android.gms.games.internal.GamesLog.b("GamesClientImpl", "IOException:", ioexception);
            }
        }

        Nr.clear();
    }

    public int a(com.google.android.gms.games.multiplayer.realtime.RealTimeMultiplayer.ReliableMessageSentCallback reliablemessagesentcallback, byte abyte0[], String s, String s1)
    {
        int i1;
        try
        {
            i1 = ((IGamesService)ft()).a(new RealTimeReliableMessageBinderCallbacks(reliablemessagesentcallback), abyte0, s, s1);
        }
        catch (RemoteException remoteexception)
        {
            GamesLog.j("GamesClientImpl", "service died");
            return -1;
        }
        return i1;
    }

    public int a(byte abyte0[], String s, String as[])
    {
        com.google.android.gms.internal.hm.b(as, "Participant IDs must not be null");
        int i1;
        try
        {
            i1 = ((IGamesService)ft()).b(abyte0, s, as);
        }
        catch (RemoteException remoteexception)
        {
            GamesLog.j("GamesClientImpl", "service died");
            return -1;
        }
        return i1;
    }

    public Intent a(int i1, int j1, boolean flag)
    {
        Intent intent;
        try
        {
            intent = ((IGamesService)ft()).a(i1, j1, flag);
        }
        catch (RemoteException remoteexception)
        {
            GamesLog.j("GamesClientImpl", "service died");
            return null;
        }
        return intent;
    }

    public Intent a(int i1, byte abyte0[], int j1, Bitmap bitmap, String s)
    {
        Intent intent;
        try
        {
            intent = ((IGamesService)ft()).a(i1, abyte0, j1, s);
            com.google.android.gms.internal.hm.b(bitmap, "Must provide a non null icon");
            intent.putExtra("com.google.android.gms.games.REQUEST_ITEM_ICON", bitmap);
        }
        catch (RemoteException remoteexception)
        {
            GamesLog.j("GamesClientImpl", "service died");
            return null;
        }
        return intent;
    }

    public Intent a(Room room, int i1)
    {
        Intent intent;
        try
        {
            intent = ((IGamesService)ft()).a((RoomEntity)room.freeze(), i1);
        }
        catch (RemoteException remoteexception)
        {
            GamesLog.j("GamesClientImpl", "service died");
            return null;
        }
        return intent;
    }

    public Intent a(String s, boolean flag, boolean flag1, int i1)
    {
        Intent intent;
        try
        {
            intent = ((IGamesService)ft()).a(s, flag, flag1, i1);
        }
        catch (RemoteException remoteexception)
        {
            GamesLog.j("GamesClientImpl", "service died");
            return null;
        }
        return intent;
    }

    public Intent a(int ai[])
    {
        Intent intent;
        try
        {
            intent = ((IGamesService)ft()).a(ai);
        }
        catch (RemoteException remoteexception)
        {
            GamesLog.j("GamesClientImpl", "service died");
            return null;
        }
        return intent;
    }

    protected void a(int i1, IBinder ibinder, Bundle bundle)
    {
        if (i1 == 0 && bundle != null)
        {
            Nv = bundle.getBoolean("show_welcome_popup");
        }
        super.a(i1, ibinder, bundle);
    }

    public void a(IBinder ibinder, Bundle bundle)
    {
        if (!isConnected())
        {
            break MISSING_BLOCK_LABEL_21;
        }
        ((IGamesService)ft()).a(ibinder, bundle);
        return;
        RemoteException remoteexception;
        remoteexception;
        GamesLog.j("GamesClientImpl", "service died");
        return;
    }

    public void a(com.google.android.gms.common.api.a.d d1, int i1, int j1, int k1)
    {
        try
        {
            ((IGamesService)ft()).a(new RequestsLoadedBinderCallbacks(d1), i1, j1, k1);
            return;
        }
        catch (RemoteException remoteexception)
        {
            GamesLog.j("GamesClientImpl", "service died");
        }
    }

    public void a(com.google.android.gms.common.api.a.d d1, int i1, int j1, boolean flag, boolean flag1)
    {
        try
        {
            ((IGamesService)ft()).a(new ExtendedGamesLoadedBinderCallback(d1), i1, j1, flag, flag1);
            return;
        }
        catch (RemoteException remoteexception)
        {
            GamesLog.j("GamesClientImpl", "service died");
        }
    }

    public void a(com.google.android.gms.common.api.a.d d1, int i1, boolean flag, boolean flag1)
    {
        try
        {
            ((IGamesService)ft()).a(new PlayersLoadedBinderCallback(d1), i1, flag, flag1);
            return;
        }
        catch (RemoteException remoteexception)
        {
            GamesLog.j("GamesClientImpl", "service died");
        }
    }

    public void a(com.google.android.gms.common.api.a.d d1, int i1, int ai[])
    {
        try
        {
            ((IGamesService)ft()).a(new TurnBasedMatchesLoadedBinderCallbacks(d1), i1, ai);
            return;
        }
        catch (RemoteException remoteexception)
        {
            GamesLog.j("GamesClientImpl", "service died");
        }
    }

    public void a(com.google.android.gms.common.api.a.d d1, LeaderboardScoreBuffer leaderboardscorebuffer, int i1, int j1)
    {
        try
        {
            ((IGamesService)ft()).a(new LeaderboardScoresLoadedBinderCallback(d1), leaderboardscorebuffer.iA().iB(), i1, j1);
            return;
        }
        catch (RemoteException remoteexception)
        {
            GamesLog.j("GamesClientImpl", "service died");
        }
    }

    public void a(com.google.android.gms.common.api.a.d d1, TurnBasedMatchConfig turnbasedmatchconfig)
    {
        try
        {
            ((IGamesService)ft()).a(new TurnBasedMatchInitiatedBinderCallbacks(d1), turnbasedmatchconfig.getVariant(), turnbasedmatchconfig.iH(), turnbasedmatchconfig.getInvitedPlayerIds(), turnbasedmatchconfig.getAutoMatchCriteria());
            return;
        }
        catch (RemoteException remoteexception)
        {
            GamesLog.j("GamesClientImpl", "service died");
        }
    }

    public void a(com.google.android.gms.common.api.a.d d1, Snapshot snapshot, SnapshotMetadataChange snapshotmetadatachange)
    {
        Contents contents = snapshot.getContents();
        com.google.android.gms.internal.hm.b(contents, "Must provide a previously opened Snapshot");
        a a1 = snapshotmetadatachange.iN();
        if (a1 != null)
        {
            a1.a(getContext().getCacheDir());
        }
        snapshot.iM();
        try
        {
            ((IGamesService)ft()).a(new SnapshotCommittedBinderCallbacks(d1), snapshot.getMetadata().getSnapshotId(), snapshotmetadatachange, contents);
            return;
        }
        catch (RemoteException remoteexception)
        {
            GamesLog.j("GamesClientImpl", "service died");
        }
    }

    public void a(com.google.android.gms.common.api.a.d d1, String s)
    {
        try
        {
            ((IGamesService)ft()).a(new PlayersLoadedBinderCallback(d1), s);
            return;
        }
        catch (RemoteException remoteexception)
        {
            GamesLog.j("GamesClientImpl", "service died");
        }
    }

    public void a(com.google.android.gms.common.api.a.d d1, String s, int i1)
    {
        Object obj;
        if (d1 != null)
        {
            break MISSING_BLOCK_LABEL_38;
        }
        obj = null;
_L1:
        try
        {
            ((IGamesService)ft()).a(((IGamesCallbacks) (obj)), s, i1, Nu.hN(), Nu.hM());
            return;
        }
        catch (RemoteException remoteexception)
        {
            GamesLog.j("GamesClientImpl", "service died");
        }
        break MISSING_BLOCK_LABEL_62;
        obj = new AchievementUpdatedBinderCallback(d1);
          goto _L1
    }

    public void a(com.google.android.gms.common.api.a.d d1, String s, int i1, int j1, int k1, boolean flag)
    {
        try
        {
            ((IGamesService)ft()).a(new LeaderboardScoresLoadedBinderCallback(d1), s, i1, j1, k1, flag);
            return;
        }
        catch (RemoteException remoteexception)
        {
            GamesLog.j("GamesClientImpl", "service died");
        }
    }

    public void a(com.google.android.gms.common.api.a.d d1, String s, int i1, boolean flag)
    {
        try
        {
            ((IGamesService)ft()).a(new PlayersLoadedBinderCallback(d1), s, i1, flag);
            return;
        }
        catch (RemoteException remoteexception)
        {
            GamesLog.j("GamesClientImpl", "service died");
        }
    }

    public void a(com.google.android.gms.common.api.a.d d1, String s, int i1, boolean flag, boolean flag1)
    {
        if (!s.equals("played_with"))
        {
            throw new IllegalArgumentException((new StringBuilder()).append("Invalid player collection: ").append(s).toString());
        }
        try
        {
            ((IGamesService)ft()).d(new PlayersLoadedBinderCallback(d1), s, i1, flag, flag1);
            return;
        }
        catch (RemoteException remoteexception)
        {
            GamesLog.j("GamesClientImpl", "service died");
        }
    }

    public void a(com.google.android.gms.common.api.a.d d1, String s, int i1, boolean flag, boolean flag1, boolean flag2, boolean flag3)
    {
        try
        {
            ((IGamesService)ft()).a(new ExtendedGamesLoadedBinderCallback(d1), s, i1, flag, flag1, flag2, flag3);
            return;
        }
        catch (RemoteException remoteexception)
        {
            GamesLog.j("GamesClientImpl", "service died");
        }
    }

    public void a(com.google.android.gms.common.api.a.d d1, String s, int i1, int ai[])
    {
        try
        {
            ((IGamesService)ft()).a(new TurnBasedMatchesLoadedBinderCallbacks(d1), s, i1, ai);
            return;
        }
        catch (RemoteException remoteexception)
        {
            GamesLog.j("GamesClientImpl", "service died");
        }
    }

    public void a(com.google.android.gms.common.api.a.d d1, String s, long l1, String s1)
    {
        Object obj;
        if (d1 != null)
        {
            break MISSING_BLOCK_LABEL_26;
        }
        obj = null;
_L1:
        try
        {
            ((IGamesService)ft()).a(((IGamesCallbacks) (obj)), s, l1, s1);
            return;
        }
        catch (RemoteException remoteexception)
        {
            GamesLog.j("GamesClientImpl", "service died");
        }
        break MISSING_BLOCK_LABEL_50;
        obj = new SubmitScoreBinderCallbacks(d1);
          goto _L1
    }

    public void a(com.google.android.gms.common.api.a.d d1, String s, String s1)
    {
        try
        {
            ((IGamesService)ft()).c(new TurnBasedMatchLeftBinderCallbacks(d1), s, s1);
            return;
        }
        catch (RemoteException remoteexception)
        {
            GamesLog.j("GamesClientImpl", "service died");
        }
    }

    public void a(com.google.android.gms.common.api.a.d d1, String s, String s1, int i1, int j1)
    {
        try
        {
            ((IGamesService)ft()).a(new PlayerLeaderboardScoreLoadedBinderCallback(d1), s, s1, i1, j1);
            return;
        }
        catch (RemoteException remoteexception)
        {
            GamesLog.j("GamesClientImpl", "service died");
        }
    }

    public void a(com.google.android.gms.common.api.a.d d1, String s, String s1, int i1, int j1, int k1)
    {
        try
        {
            ((IGamesService)ft()).a(new RequestsLoadedBinderCallbacks(d1), s, s1, i1, j1, k1);
            return;
        }
        catch (RemoteException remoteexception)
        {
            GamesLog.j("GamesClientImpl", "service died");
        }
    }

    public void a(com.google.android.gms.common.api.a.d d1, String s, String s1, int i1, int j1, int k1, boolean flag)
    {
        try
        {
            ((IGamesService)ft()).a(new LeaderboardScoresLoadedBinderCallback(d1), s, s1, i1, j1, k1, flag);
            return;
        }
        catch (RemoteException remoteexception)
        {
            GamesLog.j("GamesClientImpl", "service died");
        }
    }

    public void a(com.google.android.gms.common.api.a.d d1, String s, String s1, int i1, boolean flag, boolean flag1)
    {
        if (!s.equals("played_with") && !s.equals("circled"))
        {
            throw new IllegalArgumentException((new StringBuilder()).append("Invalid player collection: ").append(s).toString());
        }
        try
        {
            ((IGamesService)ft()).a(new PlayersLoadedBinderCallback(d1), s, s1, i1, flag, flag1);
            return;
        }
        catch (RemoteException remoteexception)
        {
            GamesLog.j("GamesClientImpl", "service died");
        }
    }

    public void a(com.google.android.gms.common.api.a.d d1, String s, String s1, SnapshotMetadataChange snapshotmetadatachange, Contents contents)
    {
        a a1 = snapshotmetadatachange.iN();
        if (a1 != null)
        {
            a1.a(getContext().getCacheDir());
        }
        try
        {
            ((IGamesService)ft()).a(new SnapshotOpenedBinderCallbacks(d1), s, s1, snapshotmetadatachange, contents);
            return;
        }
        catch (RemoteException remoteexception)
        {
            GamesLog.j("GamesClientImpl", "service died");
        }
    }

    public void a(com.google.android.gms.common.api.a.d d1, String s, String s1, boolean flag)
    {
        try
        {
            ((IGamesService)ft()).b(new LeaderboardsLoadedBinderCallback(d1), s, s1, flag);
            return;
        }
        catch (RemoteException remoteexception)
        {
            GamesLog.j("GamesClientImpl", "service died");
        }
    }

    public void a(com.google.android.gms.common.api.a.d d1, String s, String s1, boolean flag, String as[])
    {
        try
        {
            ((IGamesService)ft()).a(new QuestsLoadedBinderCallbacks(d1), s, s1, as, flag);
            return;
        }
        catch (RemoteException remoteexception)
        {
            GamesLog.j("GamesClientImpl", "service died");
        }
    }

    public void a(com.google.android.gms.common.api.a.d d1, String s, String s1, int ai[], int i1, boolean flag)
    {
        try
        {
            ((IGamesService)ft()).a(new QuestsLoadedBinderCallbacks(d1), s, s1, ai, i1, flag);
            return;
        }
        catch (RemoteException remoteexception)
        {
            GamesLog.j("GamesClientImpl", "service died");
        }
    }

    public void a(com.google.android.gms.common.api.a.d d1, String s, String s1, String as[])
    {
        try
        {
            ((IGamesService)ft()).a(new RequestsUpdatedBinderCallbacks(d1), s, s1, as);
            return;
        }
        catch (RemoteException remoteexception)
        {
            GamesLog.j("GamesClientImpl", "service died");
        }
    }

    public void a(com.google.android.gms.common.api.a.d d1, String s, boolean flag)
    {
        try
        {
            ((IGamesService)ft()).c(new LeaderboardsLoadedBinderCallback(d1), s, flag);
            return;
        }
        catch (RemoteException remoteexception)
        {
            GamesLog.j("GamesClientImpl", "service died");
        }
    }

    public void a(com.google.android.gms.common.api.a.d d1, String s, byte abyte0[], String s1, ParticipantResult aparticipantresult[])
    {
        try
        {
            ((IGamesService)ft()).a(new TurnBasedMatchUpdatedBinderCallbacks(d1), s, abyte0, s1, aparticipantresult);
            return;
        }
        catch (RemoteException remoteexception)
        {
            GamesLog.j("GamesClientImpl", "service died");
        }
    }

    public void a(com.google.android.gms.common.api.a.d d1, String s, byte abyte0[], ParticipantResult aparticipantresult[])
    {
        try
        {
            ((IGamesService)ft()).a(new TurnBasedMatchUpdatedBinderCallbacks(d1), s, abyte0, aparticipantresult);
            return;
        }
        catch (RemoteException remoteexception)
        {
            GamesLog.j("GamesClientImpl", "service died");
        }
    }

    public void a(com.google.android.gms.common.api.a.d d1, String s, String as[], int i1, byte abyte0[], int j1)
    {
        try
        {
            ((IGamesService)ft()).a(new RequestSentBinderCallbacks(d1), s, as, i1, abyte0, j1);
            return;
        }
        catch (RemoteException remoteexception)
        {
            GamesLog.j("GamesClientImpl", "service died");
        }
    }

    public void a(com.google.android.gms.common.api.a.d d1, boolean flag)
    {
        try
        {
            ((IGamesService)ft()).c(new PlayersLoadedBinderCallback(d1), flag);
            return;
        }
        catch (RemoteException remoteexception)
        {
            GamesLog.j("GamesClientImpl", "service died");
        }
    }

    public void a(com.google.android.gms.common.api.a.d d1, boolean flag, Bundle bundle)
    {
        try
        {
            ((IGamesService)ft()).a(new ContactSettingsUpdatedBinderCallback(d1), flag, bundle);
            return;
        }
        catch (RemoteException remoteexception)
        {
            GamesLog.j("GamesClientImpl", "service died");
        }
    }

    public transient void a(com.google.android.gms.common.api.a.d d1, boolean flag, String as[])
    {
        try
        {
            Np.flush();
            ((IGamesService)ft()).a(new EventsLoadedBinderCallback(d1), flag, as);
            return;
        }
        catch (RemoteException remoteexception)
        {
            GamesLog.j("GamesClientImpl", "service died");
        }
    }

    public void a(com.google.android.gms.common.api.a.d d1, int ai[], int i1, boolean flag)
    {
        try
        {
            Np.flush();
            ((IGamesService)ft()).a(new QuestsLoadedBinderCallbacks(d1), ai, i1, flag);
            return;
        }
        catch (RemoteException remoteexception)
        {
            GamesLog.j("GamesClientImpl", "service died");
        }
    }

    public void a(com.google.android.gms.common.api.a.d d1, String as[])
    {
        try
        {
            ((IGamesService)ft()).c(new PlayersLoadedBinderCallback(d1), as);
            return;
        }
        catch (RemoteException remoteexception)
        {
            GamesLog.j("GamesClientImpl", "service died");
        }
    }

    public void a(OnInvitationReceivedListener oninvitationreceivedlistener)
    {
        try
        {
            InvitationReceivedBinderCallback invitationreceivedbindercallback = new InvitationReceivedBinderCallback(oninvitationreceivedlistener);
            ((IGamesService)ft()).a(invitationreceivedbindercallback, Nz);
            return;
        }
        catch (RemoteException remoteexception)
        {
            GamesLog.j("GamesClientImpl", "service died");
        }
    }

    public void a(RoomConfig roomconfig)
    {
        try
        {
            RoomBinderCallbacks roombindercallbacks = new RoomBinderCallbacks(roomconfig.getRoomUpdateListener(), roomconfig.getRoomStatusUpdateListener(), roomconfig.getMessageReceivedListener());
            ((IGamesService)ft()).a(roombindercallbacks, Ny, roomconfig.getVariant(), roomconfig.getInvitedPlayerIds(), roomconfig.getAutoMatchCriteria(), roomconfig.isSocketEnabled(), Nz);
            return;
        }
        catch (RemoteException remoteexception)
        {
            GamesLog.j("GamesClientImpl", "service died");
        }
    }

    public void a(RoomUpdateListener roomupdatelistener, String s)
    {
        try
        {
            ((IGamesService)ft()).c(new RoomBinderCallbacks(roomupdatelistener), s);
            hv();
            return;
        }
        catch (RemoteException remoteexception)
        {
            GamesLog.j("GamesClientImpl", "service died");
        }
    }

    public void a(OnTurnBasedMatchUpdateReceivedListener onturnbasedmatchupdatereceivedlistener)
    {
        try
        {
            MatchUpdateReceivedBinderCallback matchupdatereceivedbindercallback = new MatchUpdateReceivedBinderCallback(onturnbasedmatchupdatereceivedlistener);
            ((IGamesService)ft()).b(matchupdatereceivedbindercallback, Nz);
            return;
        }
        catch (RemoteException remoteexception)
        {
            GamesLog.j("GamesClientImpl", "service died");
        }
    }

    public void a(QuestUpdateListener questupdatelistener)
    {
        try
        {
            QuestUpdateBinderCallback questupdatebindercallback = new QuestUpdateBinderCallback(questupdatelistener);
            ((IGamesService)ft()).d(questupdatebindercallback, Nz);
            return;
        }
        catch (RemoteException remoteexception)
        {
            GamesLog.j("GamesClientImpl", "service died");
        }
    }

    public void a(OnRequestReceivedListener onrequestreceivedlistener)
    {
        try
        {
            RequestReceivedBinderCallback requestreceivedbindercallback = new RequestReceivedBinderCallback(onrequestreceivedlistener);
            ((IGamesService)ft()).c(requestreceivedbindercallback, Nz);
            return;
        }
        catch (RemoteException remoteexception)
        {
            GamesLog.j("GamesClientImpl", "service died");
        }
    }

    public void a(Snapshot snapshot)
    {
        Contents contents = snapshot.getContents();
        com.google.android.gms.internal.hm.b(contents, "Must provide a previously opened Snapshot");
        snapshot.iM();
        try
        {
            ((IGamesService)ft()).a(contents);
            return;
        }
        catch (RemoteException remoteexception)
        {
            GamesLog.j("GamesClientImpl", "service died");
        }
    }

    protected void a(hi hi1, com.google.android.gms.internal.hb.e e1)
        throws RemoteException
    {
        String s = getContext().getResources().getConfiguration().locale.toString();
        Bundle bundle = new Bundle();
        bundle.putBoolean("com.google.android.gms.games.key.isHeadless", NA);
        bundle.putBoolean("com.google.android.gms.games.key.showConnectingPopup", Nw);
        bundle.putInt("com.google.android.gms.games.key.connectingPopupGravity", Nx);
        bundle.putBoolean("com.google.android.gms.games.key.retryingSignIn", NC);
        bundle.putInt("com.google.android.gms.games.key.sdkVariant", NB);
        bundle.putString("com.google.android.gms.games.key.forceResolveAccountKey", ND);
        hi1.a(e1, 0x4da6e8, getContext().getPackageName(), yQ, fs(), Nq, Nu.hN(), s, bundle);
    }

    public Intent aR(String s)
    {
        Intent intent;
        try
        {
            intent = ((IGamesService)ft()).aR(s);
        }
        catch (RemoteException remoteexception)
        {
            GamesLog.j("GamesClientImpl", "service died");
            return null;
        }
        return intent;
    }

    public void aS(String s)
    {
        try
        {
            ((IGamesService)ft()).ba(s);
            return;
        }
        catch (RemoteException remoteexception)
        {
            GamesLog.j("GamesClientImpl", "service died");
        }
    }

    public Intent aU(String s)
    {
        Intent intent;
        try
        {
            intent = ((IGamesService)ft()).aU(s);
        }
        catch (RemoteException remoteexception)
        {
            GamesLog.j("GamesClientImpl", "service died");
            return null;
        }
        return intent;
    }

    protected IGamesService ah(IBinder ibinder)
    {
        return IGamesService.Stub.aj(ibinder);
    }

    public Intent b(int i1, int j1, boolean flag)
    {
        Intent intent;
        try
        {
            intent = ((IGamesService)ft()).b(i1, j1, flag);
        }
        catch (RemoteException remoteexception)
        {
            GamesLog.j("GamesClientImpl", "service died");
            return null;
        }
        return intent;
    }

    public void b(com.google.android.gms.common.api.a.d d1)
    {
        try
        {
            Np.flush();
            ((IGamesService)ft()).a(new SignOutCompleteBinderCallbacks(d1));
            return;
        }
        catch (RemoteException remoteexception)
        {
            GamesLog.j("GamesClientImpl", "service died");
        }
    }

    public void b(com.google.android.gms.common.api.a.d d1, int i1, boolean flag, boolean flag1)
    {
        try
        {
            ((IGamesService)ft()).b(new PlayersLoadedBinderCallback(d1), i1, flag, flag1);
            return;
        }
        catch (RemoteException remoteexception)
        {
            GamesLog.j("GamesClientImpl", "service died");
        }
    }

    public void b(com.google.android.gms.common.api.a.d d1, String s)
    {
        if (d1 != null) goto _L2; else goto _L1
_L1:
        Object obj = null;
_L3:
        AchievementUpdatedBinderCallback achievementupdatedbindercallback;
        try
        {
            ((IGamesService)ft()).a(((IGamesCallbacks) (obj)), s, Nu.hN(), Nu.hM());
            return;
        }
        catch (RemoteException remoteexception)
        {
            GamesLog.j("GamesClientImpl", "service died");
        }
        break MISSING_BLOCK_LABEL_63;
_L2:
        achievementupdatedbindercallback = new AchievementUpdatedBinderCallback(d1);
        obj = achievementupdatedbindercallback;
          goto _L3
    }

    public void b(com.google.android.gms.common.api.a.d d1, String s, int i1)
    {
        Object obj;
        if (d1 != null)
        {
            break MISSING_BLOCK_LABEL_38;
        }
        obj = null;
_L1:
        try
        {
            ((IGamesService)ft()).b(((IGamesCallbacks) (obj)), s, i1, Nu.hN(), Nu.hM());
            return;
        }
        catch (RemoteException remoteexception)
        {
            GamesLog.j("GamesClientImpl", "service died");
        }
        break MISSING_BLOCK_LABEL_62;
        obj = new AchievementUpdatedBinderCallback(d1);
          goto _L1
    }

    public void b(com.google.android.gms.common.api.a.d d1, String s, int i1, int j1, int k1, boolean flag)
    {
        try
        {
            ((IGamesService)ft()).b(new LeaderboardScoresLoadedBinderCallback(d1), s, i1, j1, k1, flag);
            return;
        }
        catch (RemoteException remoteexception)
        {
            GamesLog.j("GamesClientImpl", "service died");
        }
    }

    public void b(com.google.android.gms.common.api.a.d d1, String s, int i1, boolean flag, boolean flag1)
    {
        try
        {
            ((IGamesService)ft()).a(new ExtendedGamesLoadedBinderCallback(d1), s, i1, flag, flag1);
            return;
        }
        catch (RemoteException remoteexception)
        {
            GamesLog.j("GamesClientImpl", "service died");
        }
    }

    public void b(com.google.android.gms.common.api.a.d d1, String s, String s1)
    {
        try
        {
            Np.flush();
            ((IGamesService)ft()).f(new QuestMilestoneClaimBinderCallbacks(d1, s1), s, s1);
            return;
        }
        catch (RemoteException remoteexception)
        {
            GamesLog.j("GamesClientImpl", "service died");
        }
    }

    public void b(com.google.android.gms.common.api.a.d d1, String s, String s1, int i1, int j1, int k1, boolean flag)
    {
        try
        {
            ((IGamesService)ft()).b(new LeaderboardScoresLoadedBinderCallback(d1), s, s1, i1, j1, k1, flag);
            return;
        }
        catch (RemoteException remoteexception)
        {
            GamesLog.j("GamesClientImpl", "service died");
        }
    }

    public void b(com.google.android.gms.common.api.a.d d1, String s, String s1, boolean flag)
    {
        try
        {
            ((IGamesService)ft()).a(new AchievementsLoadedBinderCallback(d1), s, s1, flag);
            return;
        }
        catch (RemoteException remoteexception)
        {
            GamesLog.j("GamesClientImpl", "service died");
        }
    }

    public void b(com.google.android.gms.common.api.a.d d1, String s, boolean flag)
    {
        try
        {
            ((IGamesService)ft()).e(new SnapshotOpenedBinderCallbacks(d1), s, flag);
            return;
        }
        catch (RemoteException remoteexception)
        {
            GamesLog.j("GamesClientImpl", "service died");
        }
    }

    public void b(com.google.android.gms.common.api.a.d d1, boolean flag)
    {
        try
        {
            ((IGamesService)ft()).b(new LeaderboardsLoadedBinderCallback(d1), flag);
            return;
        }
        catch (RemoteException remoteexception)
        {
            GamesLog.j("GamesClientImpl", "service died");
        }
    }

    public void b(com.google.android.gms.common.api.a.d d1, boolean flag, String as[])
    {
        try
        {
            ((IGamesService)ft()).a(new QuestsLoadedBinderCallbacks(d1), as, flag);
            return;
        }
        catch (RemoteException remoteexception)
        {
            GamesLog.j("GamesClientImpl", "service died");
        }
    }

    public void b(com.google.android.gms.common.api.a.d d1, String as[])
    {
        try
        {
            ((IGamesService)ft()).a(new RequestsUpdatedBinderCallbacks(d1), as);
            return;
        }
        catch (RemoteException remoteexception)
        {
            GamesLog.j("GamesClientImpl", "service died");
        }
    }

    public void b(RoomConfig roomconfig)
    {
        try
        {
            RoomBinderCallbacks roombindercallbacks = new RoomBinderCallbacks(roomconfig.getRoomUpdateListener(), roomconfig.getRoomStatusUpdateListener(), roomconfig.getMessageReceivedListener());
            ((IGamesService)ft()).a(roombindercallbacks, Ny, roomconfig.getInvitationId(), roomconfig.isSocketEnabled(), Nz);
            return;
        }
        catch (RemoteException remoteexception)
        {
            GamesLog.j("GamesClientImpl", "service died");
        }
    }

    protected transient void b(String as[])
    {
        int i1 = 0;
        boolean flag = false;
        boolean flag1 = false;
        while (i1 < as.length) 
        {
            String s = as[i1];
            if (s.equals("https://www.googleapis.com/auth/games"))
            {
                flag1 = true;
            } else
            if (s.equals("https://www.googleapis.com/auth/games.firstparty"))
            {
                flag = true;
            }
            i1++;
        }
        if (flag)
        {
            boolean flag2;
            if (!flag1)
            {
                flag2 = true;
            } else
            {
                flag2 = false;
            }
            com.google.android.gms.internal.hm.a(flag2, "Cannot have both %s and %s!", new Object[] {
                "https://www.googleapis.com/auth/games", "https://www.googleapis.com/auth/games.firstparty"
            });
            return;
        } else
        {
            com.google.android.gms.internal.hm.a(flag1, "Games APIs requires %s to function.", new Object[] {
                "https://www.googleapis.com/auth/games"
            });
            return;
        }
    }

    protected String bu()
    {
        return "com.google.android.gms.games.service.START";
    }

    protected String bv()
    {
        return "com.google.android.gms.games.internal.IGamesService";
    }

    public void c(com.google.android.gms.common.api.a.d d1, int i1)
    {
        try
        {
            ((IGamesService)ft()).a(new InvitationsLoadedBinderCallback(d1), i1);
            return;
        }
        catch (RemoteException remoteexception)
        {
            GamesLog.j("GamesClientImpl", "service died");
        }
    }

    public void c(com.google.android.gms.common.api.a.d d1, int i1, boolean flag, boolean flag1)
    {
        try
        {
            ((IGamesService)ft()).c(new PlayersLoadedBinderCallback(d1), i1, flag, flag1);
            return;
        }
        catch (RemoteException remoteexception)
        {
            GamesLog.j("GamesClientImpl", "service died");
        }
    }

    public void c(com.google.android.gms.common.api.a.d d1, String s)
    {
        if (d1 != null) goto _L2; else goto _L1
_L1:
        Object obj = null;
_L3:
        AchievementUpdatedBinderCallback achievementupdatedbindercallback;
        try
        {
            ((IGamesService)ft()).b(((IGamesCallbacks) (obj)), s, Nu.hN(), Nu.hM());
            return;
        }
        catch (RemoteException remoteexception)
        {
            GamesLog.j("GamesClientImpl", "service died");
        }
        break MISSING_BLOCK_LABEL_63;
_L2:
        achievementupdatedbindercallback = new AchievementUpdatedBinderCallback(d1);
        obj = achievementupdatedbindercallback;
          goto _L3
    }

    public void c(com.google.android.gms.common.api.a.d d1, String s, int i1)
    {
        try
        {
            ((IGamesService)ft()).b(new PlayerXpStreamLoadedBinderCallback(d1), s, i1);
            return;
        }
        catch (RemoteException remoteexception)
        {
            GamesLog.j("GamesClientImpl", "service died");
        }
    }

    public void c(com.google.android.gms.common.api.a.d d1, String s, int i1, boolean flag, boolean flag1)
    {
        try
        {
            ((IGamesService)ft()).e(new ExtendedGamesLoadedBinderCallback(d1), s, i1, flag, flag1);
            return;
        }
        catch (RemoteException remoteexception)
        {
            GamesLog.j("GamesClientImpl", "service died");
        }
    }

    public void c(com.google.android.gms.common.api.a.d d1, String s, String s1)
    {
        try
        {
            ((IGamesService)ft()).d(new TurnBasedMatchInitiatedBinderCallbacks(d1), s, s1);
            return;
        }
        catch (RemoteException remoteexception)
        {
            GamesLog.j("GamesClientImpl", "service died");
        }
    }

    public void c(com.google.android.gms.common.api.a.d d1, String s, String s1, boolean flag)
    {
        try
        {
            ((IGamesService)ft()).c(new SnapshotsLoadedBinderCallbacks(d1), s, s1, flag);
            return;
        }
        catch (RemoteException remoteexception)
        {
            GamesLog.j("GamesClientImpl", "service died");
        }
    }

    public void c(com.google.android.gms.common.api.a.d d1, String s, boolean flag)
    {
        try
        {
            ((IGamesService)ft()).d(new LeaderboardsLoadedBinderCallback(d1), s, flag);
            return;
        }
        catch (RemoteException remoteexception)
        {
            GamesLog.j("GamesClientImpl", "service died");
        }
    }

    public void c(com.google.android.gms.common.api.a.d d1, boolean flag)
    {
        try
        {
            ((IGamesService)ft()).a(new AchievementsLoadedBinderCallback(d1), flag);
            return;
        }
        catch (RemoteException remoteexception)
        {
            GamesLog.j("GamesClientImpl", "service died");
        }
    }

    public void c(com.google.android.gms.common.api.a.d d1, String as[])
    {
        try
        {
            ((IGamesService)ft()).b(new RequestsUpdatedBinderCallbacks(d1), as);
            return;
        }
        catch (RemoteException remoteexception)
        {
            GamesLog.j("GamesClientImpl", "service died");
        }
    }

    public void cg(int i1)
    {
        Nu.setGravity(i1);
    }

    public void ch(int i1)
    {
        try
        {
            ((IGamesService)ft()).ch(i1);
            return;
        }
        catch (RemoteException remoteexception)
        {
            GamesLog.j("GamesClientImpl", "service died");
        }
    }

    public void connect()
    {
        gY();
        super.connect();
    }

    public int d(byte abyte0[], String s)
    {
        int i1;
        try
        {
            i1 = ((IGamesService)ft()).b(abyte0, s, null);
        }
        catch (RemoteException remoteexception)
        {
            GamesLog.j("GamesClientImpl", "service died");
            return -1;
        }
        return i1;
    }

    public void d(com.google.android.gms.common.api.a.d d1, int i1, boolean flag, boolean flag1)
    {
        try
        {
            ((IGamesService)ft()).e(new PlayersLoadedBinderCallback(d1), i1, flag, flag1);
            return;
        }
        catch (RemoteException remoteexception)
        {
            GamesLog.j("GamesClientImpl", "service died");
        }
    }

    public void d(com.google.android.gms.common.api.a.d d1, String s)
    {
        try
        {
            ((IGamesService)ft()).l(new TurnBasedMatchInitiatedBinderCallbacks(d1), s);
            return;
        }
        catch (RemoteException remoteexception)
        {
            GamesLog.j("GamesClientImpl", "service died");
        }
    }

    public void d(com.google.android.gms.common.api.a.d d1, String s, int i1)
    {
        try
        {
            ((IGamesService)ft()).c(new PlayerXpStreamLoadedBinderCallback(d1), s, i1);
            return;
        }
        catch (RemoteException remoteexception)
        {
            GamesLog.j("GamesClientImpl", "service died");
        }
    }

    public void d(com.google.android.gms.common.api.a.d d1, String s, int i1, boolean flag, boolean flag1)
    {
        try
        {
            ((IGamesService)ft()).f(new ExtendedGamesLoadedBinderCallback(d1), s, i1, flag, flag1);
            return;
        }
        catch (RemoteException remoteexception)
        {
            GamesLog.j("GamesClientImpl", "service died");
        }
    }

    public void d(com.google.android.gms.common.api.a.d d1, String s, String s1)
    {
        try
        {
            ((IGamesService)ft()).e(new TurnBasedMatchInitiatedBinderCallbacks(d1), s, s1);
            return;
        }
        catch (RemoteException remoteexception)
        {
            GamesLog.j("GamesClientImpl", "service died");
        }
    }

    public void d(com.google.android.gms.common.api.a.d d1, String s, boolean flag)
    {
        try
        {
            ((IGamesService)ft()).a(new GameMuteStatusChangedBinderCallback(d1), s, flag);
            return;
        }
        catch (RemoteException remoteexception)
        {
            GamesLog.j("GamesClientImpl", "service died");
        }
    }

    public void d(com.google.android.gms.common.api.a.d d1, boolean flag)
    {
        try
        {
            Np.flush();
            ((IGamesService)ft()).f(new EventsLoadedBinderCallback(d1), flag);
            return;
        }
        catch (RemoteException remoteexception)
        {
            GamesLog.j("GamesClientImpl", "service died");
        }
    }

    public void disconnect()
    {
        Nv = false;
        if (isConnected())
        {
            try
            {
                IGamesService igamesservice = (IGamesService)ft();
                igamesservice.hw();
                Np.flush();
                igamesservice.q(Nz);
            }
            catch (RemoteException remoteexception)
            {
                GamesLog.j("GamesClientImpl", "Failed to notify client disconnect.");
            }
        }
        hv();
        super.disconnect();
    }

    public void e(com.google.android.gms.common.api.a.d d1, int i1, boolean flag, boolean flag1)
    {
        try
        {
            ((IGamesService)ft()).d(new PlayersLoadedBinderCallback(d1), i1, flag, flag1);
            return;
        }
        catch (RemoteException remoteexception)
        {
            GamesLog.j("GamesClientImpl", "service died");
        }
    }

    public void e(com.google.android.gms.common.api.a.d d1, String s)
    {
        try
        {
            ((IGamesService)ft()).m(new TurnBasedMatchInitiatedBinderCallbacks(d1), s);
            return;
        }
        catch (RemoteException remoteexception)
        {
            GamesLog.j("GamesClientImpl", "service died");
        }
    }

    public void e(com.google.android.gms.common.api.a.d d1, String s, int i1)
    {
        try
        {
            ((IGamesService)ft()).b(new InvitationsLoadedBinderCallback(d1), s, i1, false);
            return;
        }
        catch (RemoteException remoteexception)
        {
            GamesLog.j("GamesClientImpl", "service died");
        }
    }

    public void e(com.google.android.gms.common.api.a.d d1, String s, int i1, boolean flag, boolean flag1)
    {
        try
        {
            ((IGamesService)ft()).c(new ExtendedGamesLoadedBinderCallback(d1), s, i1, flag, flag1);
            return;
        }
        catch (RemoteException remoteexception)
        {
            GamesLog.j("GamesClientImpl", "service died");
        }
    }

    public void e(com.google.android.gms.common.api.a.d d1, boolean flag)
    {
        try
        {
            ((IGamesService)ft()).d(new SnapshotsLoadedBinderCallbacks(d1), flag);
            return;
        }
        catch (RemoteException remoteexception)
        {
            GamesLog.j("GamesClientImpl", "service died");
        }
    }

    public Bundle ef()
    {
        Bundle bundle;
        try
        {
            bundle = ((IGamesService)ft()).ef();
        }
        catch (RemoteException remoteexception)
        {
            GamesLog.j("GamesClientImpl", "service died");
            return null;
        }
        if (bundle == null)
        {
            break MISSING_BLOCK_LABEL_26;
        }
        bundle.setClassLoader(com/google/android/gms/games/internal/GamesClientImpl.getClassLoader());
        return bundle;
    }

    public void f(View view)
    {
        Nu.g(view);
    }

    public void f(com.google.android.gms.common.api.a.d d1)
    {
        try
        {
            ((IGamesService)ft()).d(new GamesLoadedBinderCallback(d1));
            return;
        }
        catch (RemoteException remoteexception)
        {
            GamesLog.j("GamesClientImpl", "service died");
        }
    }

    public void f(com.google.android.gms.common.api.a.d d1, String s)
    {
        try
        {
            ((IGamesService)ft()).o(new TurnBasedMatchLeftBinderCallbacks(d1), s);
            return;
        }
        catch (RemoteException remoteexception)
        {
            GamesLog.j("GamesClientImpl", "service died");
        }
    }

    public void f(com.google.android.gms.common.api.a.d d1, String s, int i1)
    {
        try
        {
            ((IGamesService)ft()).a(new RequestSummariesLoadedBinderCallbacks(d1), s, i1);
            return;
        }
        catch (RemoteException remoteexception)
        {
            GamesLog.j("GamesClientImpl", "service died");
        }
    }

    public void f(com.google.android.gms.common.api.a.d d1, String s, int i1, boolean flag, boolean flag1)
    {
        try
        {
            ((IGamesService)ft()).b(new PlayersLoadedBinderCallback(d1), s, i1, flag, flag1);
            return;
        }
        catch (RemoteException remoteexception)
        {
            GamesLog.j("GamesClientImpl", "service died");
        }
    }

    public void f(com.google.android.gms.common.api.a.d d1, boolean flag)
    {
        try
        {
            ((IGamesService)ft()).e(new ContactSettingsLoadedBinderCallback(d1), flag);
            return;
        }
        catch (RemoteException remoteexception)
        {
            GamesLog.j("GamesClientImpl", "service died");
        }
    }

    public void g(com.google.android.gms.common.api.a.d d1)
    {
        try
        {
            ((IGamesService)ft()).j(new OwnerCoverPhotoUrisLoadedBinderCallback(d1));
            return;
        }
        catch (RemoteException remoteexception)
        {
            GamesLog.j("GamesClientImpl", "service died");
        }
    }

    public void g(com.google.android.gms.common.api.a.d d1, String s)
    {
        try
        {
            ((IGamesService)ft()).n(new TurnBasedMatchCanceledBinderCallbacks(d1), s);
            return;
        }
        catch (RemoteException remoteexception)
        {
            GamesLog.j("GamesClientImpl", "service died");
        }
    }

    public void g(com.google.android.gms.common.api.a.d d1, String s, int i1, boolean flag, boolean flag1)
    {
        try
        {
            ((IGamesService)ft()).b(new PlayersLoadedBinderCallback(d1), s, null, i1, flag, flag1);
            return;
        }
        catch (RemoteException remoteexception)
        {
            GamesLog.j("GamesClientImpl", "service died");
        }
    }

    public String gZ()
    {
        String s;
        try
        {
            s = ((IGamesService)ft()).gZ();
        }
        catch (RemoteException remoteexception)
        {
            GamesLog.j("GamesClientImpl", "service died");
            return null;
        }
        return s;
    }

    public void h(com.google.android.gms.common.api.a.d d1)
    {
        try
        {
            ((IGamesService)ft()).h(new NotifyAclLoadedBinderCallback(d1));
            return;
        }
        catch (RemoteException remoteexception)
        {
            GamesLog.j("GamesClientImpl", "service died");
        }
    }

    public void h(com.google.android.gms.common.api.a.d d1, String s)
    {
        try
        {
            ((IGamesService)ft()).p(new TurnBasedMatchLoadedBinderCallbacks(d1), s);
            return;
        }
        catch (RemoteException remoteexception)
        {
            GamesLog.j("GamesClientImpl", "service died");
        }
    }

    public String ha()
    {
        String s;
        try
        {
            s = ((IGamesService)ft()).ha();
        }
        catch (RemoteException remoteexception)
        {
            GamesLog.j("GamesClientImpl", "service died");
            return null;
        }
        return s;
    }

    public Player hb()
    {
        cn();
        this;
        JVM INSTR monitorenter ;
        PlayerEntity playerentity = Ns;
        if (playerentity != null)
        {
            break MISSING_BLOCK_LABEL_63;
        }
        PlayerBuffer playerbuffer = new PlayerBuffer(((IGamesService)ft()).hy());
        if (playerbuffer.getCount() > 0)
        {
            Ns = (PlayerEntity)playerbuffer.get(0).freeze();
        }
        playerbuffer.close();
_L1:
        this;
        JVM INSTR monitorexit ;
        return Ns;
        Exception exception1;
        exception1;
        Exception exception;
        try
        {
            playerbuffer.close();
            throw exception1;
        }
        catch (RemoteException remoteexception) { }
        finally { }
        GamesLog.j("GamesClientImpl", "service died");
          goto _L1
        this;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public Game hc()
    {
        cn();
        this;
        JVM INSTR monitorenter ;
        GameEntity gameentity = Nt;
        if (gameentity != null)
        {
            break MISSING_BLOCK_LABEL_63;
        }
        GameBuffer gamebuffer = new GameBuffer(((IGamesService)ft()).hA());
        if (gamebuffer.getCount() > 0)
        {
            Nt = (GameEntity)gamebuffer.get(0).freeze();
        }
        gamebuffer.close();
_L1:
        this;
        JVM INSTR monitorexit ;
        return Nt;
        Exception exception1;
        exception1;
        Exception exception;
        try
        {
            gamebuffer.close();
            throw exception1;
        }
        catch (RemoteException remoteexception) { }
        finally { }
        GamesLog.j("GamesClientImpl", "service died");
          goto _L1
        this;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public Intent hd()
    {
        Intent intent;
        try
        {
            intent = ((IGamesService)ft()).hd();
        }
        catch (RemoteException remoteexception)
        {
            GamesLog.j("GamesClientImpl", "service died");
            return null;
        }
        return intent;
    }

    public Intent he()
    {
        Intent intent;
        try
        {
            intent = ((IGamesService)ft()).he();
        }
        catch (RemoteException remoteexception)
        {
            GamesLog.j("GamesClientImpl", "service died");
            return null;
        }
        return intent;
    }

    public Intent hf()
    {
        Intent intent;
        try
        {
            intent = ((IGamesService)ft()).hf();
        }
        catch (RemoteException remoteexception)
        {
            GamesLog.j("GamesClientImpl", "service died");
            return null;
        }
        return intent;
    }

    public Intent hg()
    {
        Intent intent;
        try
        {
            intent = ((IGamesService)ft()).hg();
        }
        catch (RemoteException remoteexception)
        {
            GamesLog.j("GamesClientImpl", "service died");
            return null;
        }
        return intent;
    }

    public void hh()
    {
        try
        {
            ((IGamesService)ft()).r(Nz);
            return;
        }
        catch (RemoteException remoteexception)
        {
            GamesLog.j("GamesClientImpl", "service died");
        }
    }

    public void hi()
    {
        try
        {
            ((IGamesService)ft()).s(Nz);
            return;
        }
        catch (RemoteException remoteexception)
        {
            GamesLog.j("GamesClientImpl", "service died");
        }
    }

    public void hj()
    {
        try
        {
            ((IGamesService)ft()).u(Nz);
            return;
        }
        catch (RemoteException remoteexception)
        {
            GamesLog.j("GamesClientImpl", "service died");
        }
    }

    public void hk()
    {
        try
        {
            ((IGamesService)ft()).t(Nz);
            return;
        }
        catch (RemoteException remoteexception)
        {
            GamesLog.j("GamesClientImpl", "service died");
        }
    }

    public Intent hl()
    {
        Intent intent;
        try
        {
            intent = ((IGamesService)ft()).hl();
        }
        catch (RemoteException remoteexception)
        {
            GamesLog.j("GamesClientImpl", "service died");
            return null;
        }
        return intent;
    }

    public Intent hm()
    {
        Intent intent;
        try
        {
            intent = ((IGamesService)ft()).hm();
        }
        catch (RemoteException remoteexception)
        {
            GamesLog.j("GamesClientImpl", "service died");
            return null;
        }
        return intent;
    }

    public int hn()
    {
        int i1;
        try
        {
            i1 = ((IGamesService)ft()).hn();
        }
        catch (RemoteException remoteexception)
        {
            GamesLog.j("GamesClientImpl", "service died");
            return 4368;
        }
        return i1;
    }

    public String ho()
    {
        String s;
        try
        {
            s = ((IGamesService)ft()).ho();
        }
        catch (RemoteException remoteexception)
        {
            GamesLog.j("GamesClientImpl", "service died");
            return null;
        }
        return s;
    }

    public int hp()
    {
        int i1;
        try
        {
            i1 = ((IGamesService)ft()).hp();
        }
        catch (RemoteException remoteexception)
        {
            GamesLog.j("GamesClientImpl", "service died");
            return -1;
        }
        return i1;
    }

    public Intent hq()
    {
        Intent intent;
        try
        {
            intent = ((IGamesService)ft()).hq();
        }
        catch (RemoteException remoteexception)
        {
            GamesLog.j("GamesClientImpl", "service died");
            return null;
        }
        return intent;
    }

    public int hr()
    {
        int i1;
        try
        {
            i1 = ((IGamesService)ft()).hr();
        }
        catch (RemoteException remoteexception)
        {
            GamesLog.j("GamesClientImpl", "service died");
            return -1;
        }
        return i1;
    }

    public int hs()
    {
        int i1;
        try
        {
            i1 = ((IGamesService)ft()).hs();
        }
        catch (RemoteException remoteexception)
        {
            GamesLog.j("GamesClientImpl", "service died");
            return -1;
        }
        return i1;
    }

    public int ht()
    {
        int i1;
        try
        {
            i1 = ((IGamesService)ft()).ht();
        }
        catch (RemoteException remoteexception)
        {
            GamesLog.j("GamesClientImpl", "service died");
            return -1;
        }
        return i1;
    }

    public int hu()
    {
        int i1;
        try
        {
            i1 = ((IGamesService)ft()).hu();
        }
        catch (RemoteException remoteexception)
        {
            GamesLog.j("GamesClientImpl", "service died");
            return -1;
        }
        return i1;
    }

    public void hw()
    {
        if (!isConnected())
        {
            break MISSING_BLOCK_LABEL_19;
        }
        ((IGamesService)ft()).hw();
        return;
        RemoteException remoteexception;
        remoteexception;
        GamesLog.j("GamesClientImpl", "service died");
        return;
    }

    public void i(com.google.android.gms.common.api.a.d d1)
    {
        try
        {
            ((IGamesService)ft()).e(new ContactSettingsLoadedBinderCallback(d1), false);
            return;
        }
        catch (RemoteException remoteexception)
        {
            GamesLog.j("GamesClientImpl", "service died");
        }
    }

    public void i(com.google.android.gms.common.api.a.d d1, String s)
    {
        try
        {
            Np.flush();
            ((IGamesService)ft()).u(new QuestAcceptedBinderCallbacks(d1), s);
            return;
        }
        catch (RemoteException remoteexception)
        {
            GamesLog.j("GamesClientImpl", "service died");
        }
    }

    public void j(com.google.android.gms.common.api.a.d d1)
    {
        try
        {
            ((IGamesService)ft()).t(new InboxCountsLoadedBinderCallback(d1), null);
            return;
        }
        catch (RemoteException remoteexception)
        {
            GamesLog.j("GamesClientImpl", "service died");
        }
    }

    public void j(com.google.android.gms.common.api.a.d d1, String s)
    {
        try
        {
            ((IGamesService)ft()).r(new SnapshotDeletedBinderCallbacks(d1), s);
            return;
        }
        catch (RemoteException remoteexception)
        {
            GamesLog.j("GamesClientImpl", "service died");
        }
    }

    public void k(com.google.android.gms.common.api.a.d d1, String s)
    {
        try
        {
            ((IGamesService)ft()).e(new ExtendedGamesLoadedBinderCallback(d1), s);
            return;
        }
        catch (RemoteException remoteexception)
        {
            GamesLog.j("GamesClientImpl", "service died");
        }
    }

    public RealTimeSocket l(String s, String s1)
    {
        if (s1 == null || !ParticipantUtils.bn(s1))
        {
            throw new IllegalArgumentException("Bad participant ID");
        }
        RealTimeSocket realtimesocket = (RealTimeSocket)Nr.get(s1);
        if (realtimesocket == null || realtimesocket.isClosed())
        {
            realtimesocket = aT(s1);
        }
        return realtimesocket;
    }

    public void l(com.google.android.gms.common.api.a.d d1, String s)
    {
        try
        {
            ((IGamesService)ft()).f(new GameInstancesLoadedBinderCallback(d1), s);
            return;
        }
        catch (RemoteException remoteexception)
        {
            GamesLog.j("GamesClientImpl", "service died");
        }
    }

    public void l(String s, int i1)
    {
        Np.l(s, i1);
    }

    public void m(com.google.android.gms.common.api.a.d d1, String s)
    {
        try
        {
            ((IGamesService)ft()).q(new GameSearchSuggestionsLoadedBinderCallback(d1), s);
            return;
        }
        catch (RemoteException remoteexception)
        {
            GamesLog.j("GamesClientImpl", "service died");
        }
    }

    public void m(String s, int i1)
    {
        try
        {
            ((IGamesService)ft()).m(s, i1);
            return;
        }
        catch (RemoteException remoteexception)
        {
            GamesLog.j("GamesClientImpl", "service died");
        }
    }

    public void n(com.google.android.gms.common.api.a.d d1, String s)
    {
        try
        {
            ((IGamesService)ft()).s(new PlayerXpForGameCategoriesLoadedBinderCallback(d1), s);
            return;
        }
        catch (RemoteException remoteexception)
        {
            GamesLog.j("GamesClientImpl", "service died");
        }
    }

    public void n(String s, int i1)
    {
        try
        {
            ((IGamesService)ft()).n(s, i1);
            return;
        }
        catch (RemoteException remoteexception)
        {
            GamesLog.j("GamesClientImpl", "service died");
        }
    }

    public void o(com.google.android.gms.common.api.a.d d1, String s)
    {
        try
        {
            ((IGamesService)ft()).k(new InvitationsLoadedBinderCallback(d1), s);
            return;
        }
        catch (RemoteException remoteexception)
        {
            GamesLog.j("GamesClientImpl", "service died");
        }
    }

    public void onConnected(Bundle bundle)
    {
        if (Nv)
        {
            Nu.hL();
            Nv = false;
        }
    }

    public void onConnectionFailed(ConnectionResult connectionresult)
    {
        Nv = false;
    }

    public void onConnectionSuspended(int i1)
    {
    }

    public void p(com.google.android.gms.common.api.a.d d1, String s)
    {
        try
        {
            ((IGamesService)ft()).j(new NotifyAclUpdatedBinderCallback(d1), s);
            return;
        }
        catch (RemoteException remoteexception)
        {
            GamesLog.j("GamesClientImpl", "service died");
        }
    }

    public void q(com.google.android.gms.common.api.a.d d1, String s)
    {
        try
        {
            ((IGamesService)ft()).i(new GameMuteStatusLoadedBinderCallback(d1), s);
            return;
        }
        catch (RemoteException remoteexception)
        {
            GamesLog.j("GamesClientImpl", "service died");
        }
    }

    protected IInterface x(IBinder ibinder)
    {
        return ah(ibinder);
    }
}

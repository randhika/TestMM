// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.plus.internal;

import android.app.PendingIntent;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.internal.hb;
import com.google.android.gms.internal.hg;
import com.google.android.gms.internal.hi;
import com.google.android.gms.internal.ie;
import com.google.android.gms.internal.kp;
import com.google.android.gms.internal.ks;
import com.google.android.gms.plus.model.moments.Moment;
import com.google.android.gms.plus.model.moments.MomentBuffer;
import com.google.android.gms.plus.model.people.Person;
import com.google.android.gms.plus.model.people.PersonBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

// Referenced classes of package com.google.android.gms.plus.internal:
//            h, d, a

public class com.google.android.gms.plus.internal.e extends hb
{
    final class a extends com.google.android.gms.plus.internal.a
    {

        private final com.google.android.gms.common.api.a.d abL;
        final com.google.android.gms.plus.internal.e abM;

        public void am(Status status)
        {
            abM.a(abM. new d(abL, status));
        }

        public a(com.google.android.gms.common.api.a.d d1)
        {
            abM = com.google.android.gms.plus.internal.e.this;
            super();
            abL = d1;
        }
    }

    final class b extends com.google.android.gms.plus.internal.a
    {

        private final com.google.android.gms.common.api.a.d abL;
        final com.google.android.gms.plus.internal.e abM;

        public void a(DataHolder dataholder, String s, String s1)
        {
            PendingIntent pendingintent;
            Status status;
            DataHolder dataholder1;
            if (dataholder.eU() != null)
            {
                pendingintent = (PendingIntent)dataholder.eU().getParcelable("pendingIntent");
            } else
            {
                pendingintent = null;
            }
            status = new Status(dataholder.getStatusCode(), null, pendingintent);
            if (!status.isSuccess() && dataholder != null)
            {
                if (!dataholder.isClosed())
                {
                    dataholder.close();
                }
                dataholder1 = null;
            } else
            {
                dataholder1 = dataholder;
            }
            abM.a(abM. new c(abL, status, dataholder1, s, s1));
        }

        public b(com.google.android.gms.common.api.a.d d1)
        {
            abM = com.google.android.gms.plus.internal.e.this;
            super();
            abL = d1;
        }
    }

    final class c extends com.google.android.gms.internal.hb.d
        implements com.google.android.gms.plus.Moments.LoadMomentsResult
    {

        private final String HS;
        final com.google.android.gms.plus.internal.e abM;
        private final String abN;
        private MomentBuffer abO;
        private final Status yz;

        protected void a(com.google.android.gms.common.api.a.d d1, DataHolder dataholder)
        {
            MomentBuffer momentbuffer;
            if (dataholder != null)
            {
                momentbuffer = new MomentBuffer(dataholder);
            } else
            {
                momentbuffer = null;
            }
            abO = momentbuffer;
            d1.a(this);
        }

        protected volatile void a(Object obj, DataHolder dataholder)
        {
            a((com.google.android.gms.common.api.a.d)obj, dataholder);
        }

        public MomentBuffer getMomentBuffer()
        {
            return abO;
        }

        public String getNextPageToken()
        {
            return HS;
        }

        public Status getStatus()
        {
            return yz;
        }

        public String getUpdated()
        {
            return abN;
        }

        public void release()
        {
            if (abO != null)
            {
                abO.close();
            }
        }

        public c(com.google.android.gms.common.api.a.d d1, Status status, DataHolder dataholder, String s, String s1)
        {
            abM = com.google.android.gms.plus.internal.e.this;
            super(com.google.android.gms.plus.internal.e.this, d1, dataholder);
            yz = status;
            HS = s;
            abN = s1;
        }
    }

    final class d extends com.google.android.gms.internal.hb.b
    {

        final com.google.android.gms.plus.internal.e abM;
        private final Status yz;

        protected void d(Object obj)
        {
            n((com.google.android.gms.common.api.a.d)obj);
        }

        protected void fu()
        {
        }

        protected void n(com.google.android.gms.common.api.a.d d1)
        {
            if (d1 != null)
            {
                d1.a(yz);
            }
        }

        public d(com.google.android.gms.common.api.a.d d1, Status status)
        {
            abM = com.google.android.gms.plus.internal.e.this;
            super(com.google.android.gms.plus.internal.e.this, d1);
            yz = status;
        }
    }

    final class e extends com.google.android.gms.plus.internal.a
    {

        private final com.google.android.gms.common.api.a.d abL;
        final com.google.android.gms.plus.internal.e abM;

        public void a(DataHolder dataholder, String s)
        {
            PendingIntent pendingintent;
            Status status;
            DataHolder dataholder1;
            if (dataholder.eU() != null)
            {
                pendingintent = (PendingIntent)dataholder.eU().getParcelable("pendingIntent");
            } else
            {
                pendingintent = null;
            }
            status = new Status(dataholder.getStatusCode(), null, pendingintent);
            if (!status.isSuccess() && dataholder != null)
            {
                if (!dataholder.isClosed())
                {
                    dataholder.close();
                }
                dataholder1 = null;
            } else
            {
                dataholder1 = dataholder;
            }
            abM.a(abM. new f(abL, status, dataholder1, s));
        }

        public e(com.google.android.gms.common.api.a.d d1)
        {
            abM = com.google.android.gms.plus.internal.e.this;
            super();
            abL = d1;
        }
    }

    final class f extends com.google.android.gms.internal.hb.d
        implements com.google.android.gms.plus.People.LoadPeopleResult
    {

        private final String HS;
        final com.google.android.gms.plus.internal.e abM;
        private PersonBuffer abP;
        private final Status yz;

        protected void a(com.google.android.gms.common.api.a.d d1, DataHolder dataholder)
        {
            PersonBuffer personbuffer;
            if (dataholder != null)
            {
                personbuffer = new PersonBuffer(dataholder);
            } else
            {
                personbuffer = null;
            }
            abP = personbuffer;
            d1.a(this);
        }

        protected volatile void a(Object obj, DataHolder dataholder)
        {
            a((com.google.android.gms.common.api.a.d)obj, dataholder);
        }

        public String getNextPageToken()
        {
            return HS;
        }

        public PersonBuffer getPersonBuffer()
        {
            return abP;
        }

        public Status getStatus()
        {
            return yz;
        }

        public void release()
        {
            if (abP != null)
            {
                abP.close();
            }
        }

        public f(com.google.android.gms.common.api.a.d d1, Status status, DataHolder dataholder, String s)
        {
            abM = com.google.android.gms.plus.internal.e.this;
            super(com.google.android.gms.plus.internal.e.this, d1, dataholder);
            yz = status;
            HS = s;
        }
    }

    final class g extends com.google.android.gms.plus.internal.a
    {

        private final com.google.android.gms.common.api.a.d abL;
        final com.google.android.gms.plus.internal.e abM;

        public void h(int i, Bundle bundle)
        {
            PendingIntent pendingintent;
            Status status;
            if (bundle != null)
            {
                pendingintent = (PendingIntent)bundle.getParcelable("pendingIntent");
            } else
            {
                pendingintent = null;
            }
            status = new Status(i, null, pendingintent);
            abM.a(abM. new h(abL, status));
        }

        public g(com.google.android.gms.common.api.a.d d1)
        {
            abM = com.google.android.gms.plus.internal.e.this;
            super();
            abL = d1;
        }
    }

    final class h extends com.google.android.gms.internal.hb.b
    {

        final com.google.android.gms.plus.internal.e abM;
        private final Status yz;

        protected void d(Object obj)
        {
            n((com.google.android.gms.common.api.a.d)obj);
        }

        protected void fu()
        {
        }

        protected void n(com.google.android.gms.common.api.a.d d1)
        {
            abM.disconnect();
            if (d1 != null)
            {
                d1.a(yz);
            }
        }

        public h(com.google.android.gms.common.api.a.d d1, Status status)
        {
            abM = com.google.android.gms.plus.internal.e.this;
            super(com.google.android.gms.plus.internal.e.this, d1);
            yz = status;
        }
    }


    private Person abJ;
    private final com.google.android.gms.plus.internal.h abK;

    public com.google.android.gms.plus.internal.e(Context context, Looper looper, com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks connectioncallbacks, com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener onconnectionfailedlistener, com.google.android.gms.plus.internal.h h1)
    {
        super(context, looper, connectioncallbacks, onconnectionfailedlistener, h1.jZ());
        abK = h1;
    }

    public com.google.android.gms.plus.internal.e(Context context, com.google.android.gms.common.GooglePlayServicesClient.ConnectionCallbacks connectioncallbacks, com.google.android.gms.common.GooglePlayServicesClient.OnConnectionFailedListener onconnectionfailedlistener, com.google.android.gms.plus.internal.h h1)
    {
        this(context, context.getMainLooper(), ((com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks) (new com.google.android.gms.internal.hb.c(connectioncallbacks))), ((com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener) (new com.google.android.gms.internal.hb.g(onconnectionfailedlistener))), h1);
    }

    public hg a(com.google.android.gms.common.api.a.d d1, int i, String s)
    {
        cn();
        e e1 = new e(d1);
        hg hg;
        try
        {
            hg = ((com.google.android.gms.plus.internal.d)ft()).a(e1, 1, i, -1, s);
        }
        catch (RemoteException remoteexception)
        {
            e1.a(DataHolder.af(8), null);
            return null;
        }
        return hg;
    }

    protected void a(int i, IBinder ibinder, Bundle bundle)
    {
        if (i == 0 && bundle != null && bundle.containsKey("loaded_person"))
        {
            abJ = ks.i(bundle.getByteArray("loaded_person"));
        }
        super.a(i, ibinder, bundle);
    }

    public void a(com.google.android.gms.common.api.a.d d1, int i, String s, Uri uri, String s1, String s2)
    {
        cn();
        b b1;
        if (d1 != null)
        {
            b1 = new b(d1);
        } else
        {
            b1 = null;
        }
        try
        {
            ((com.google.android.gms.plus.internal.d)ft()).a(b1, i, s, uri, s1, s2);
            return;
        }
        catch (RemoteException remoteexception)
        {
            b1.a(DataHolder.af(8), null, null);
        }
    }

    public void a(com.google.android.gms.common.api.a.d d1, Moment moment)
    {
        cn();
        a a1;
        if (d1 != null)
        {
            a1 = new a(d1);
        } else
        {
            a1 = null;
        }
        try
        {
            ie ie1 = ie.a((kp)moment);
            ((com.google.android.gms.plus.internal.d)ft()).a(a1, ie1);
            return;
        }
        catch (RemoteException remoteexception)
        {
            if (a1 == null)
            {
                throw new IllegalStateException(remoteexception);
            } else
            {
                a1.am(new Status(8, null, null));
                return;
            }
        }
    }

    public void a(com.google.android.gms.common.api.a.d d1, Collection collection)
    {
        cn();
        e e1 = new e(d1);
        try
        {
            ((com.google.android.gms.plus.internal.d)ft()).a(e1, new ArrayList(collection));
            return;
        }
        catch (RemoteException remoteexception)
        {
            e1.a(DataHolder.af(8), null);
        }
    }

    protected void a(hi hi1, com.google.android.gms.internal.hb.e e1)
        throws RemoteException
    {
        Bundle bundle = abK.kh();
        bundle.putStringArray("request_visible_actions", abK.ka());
        hi1.a(e1, 0x4da6e8, abK.kd(), abK.kc(), fs(), abK.getAccountName(), bundle);
    }

    protected com.google.android.gms.plus.internal.d bn(IBinder ibinder)
    {
        return com.google.android.gms.plus.internal.d.a.bm(ibinder);
    }

    protected String bu()
    {
        return "com.google.android.gms.plus.service.START";
    }

    protected String bv()
    {
        return "com.google.android.gms.plus.internal.IPlusService";
    }

    public boolean by(String s)
    {
        return Arrays.asList(fs()).contains(s);
    }

    public void clearDefaultAccount()
    {
        cn();
        try
        {
            abJ = null;
            ((com.google.android.gms.plus.internal.d)ft()).clearDefaultAccount();
            return;
        }
        catch (RemoteException remoteexception)
        {
            throw new IllegalStateException(remoteexception);
        }
    }

    public void d(com.google.android.gms.common.api.a.d d1, String as[])
    {
        a(d1, Arrays.asList(as));
    }

    public String getAccountName()
    {
        cn();
        String s;
        try
        {
            s = ((com.google.android.gms.plus.internal.d)ft()).getAccountName();
        }
        catch (RemoteException remoteexception)
        {
            throw new IllegalStateException(remoteexception);
        }
        return s;
    }

    public Person getCurrentPerson()
    {
        cn();
        return abJ;
    }

    public void k(com.google.android.gms.common.api.a.d d1)
    {
        a(d1, 20, null, null, null, "me");
    }

    public void l(com.google.android.gms.common.api.a.d d1)
    {
        cn();
        e e1 = new e(d1);
        try
        {
            ((com.google.android.gms.plus.internal.d)ft()).a(e1, 2, 1, -1, null);
            return;
        }
        catch (RemoteException remoteexception)
        {
            e1.a(DataHolder.af(8), null);
        }
    }

    public void m(com.google.android.gms.common.api.a.d d1)
    {
        cn();
        clearDefaultAccount();
        g g1 = new g(d1);
        try
        {
            ((com.google.android.gms.plus.internal.d)ft()).b(g1);
            return;
        }
        catch (RemoteException remoteexception)
        {
            g1.h(8, null);
        }
    }

    public hg r(com.google.android.gms.common.api.a.d d1, String s)
    {
        return a(d1, 0, s);
    }

    public void removeMoment(String s)
    {
        cn();
        try
        {
            ((com.google.android.gms.plus.internal.d)ft()).removeMoment(s);
            return;
        }
        catch (RemoteException remoteexception)
        {
            throw new IllegalStateException(remoteexception);
        }
    }

    protected IInterface x(IBinder ibinder)
    {
        return bn(ibinder);
    }
}

// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.common.data;

import android.database.CharArrayBuffer;
import android.database.CursorIndexOutOfBoundsException;
import android.database.CursorWindow;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.util.Log;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.hm;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Referenced classes of package com.google.android.gms.common.data:
//            f

public final class DataHolder
    implements SafeParcelable
{
    public static class a
    {

        private final String EG[];
        private final ArrayList EP;
        private final String EQ;
        private final HashMap ER;
        private boolean ES;
        private String ET;

        static String[] a(a a1)
        {
            return a1.EG;
        }

        static ArrayList b(a a1)
        {
            return a1.EP;
        }

        private a(String as[], String s)
        {
            EG = (String[])hm.f(as);
            EP = new ArrayList();
            EQ = s;
            ER = new HashMap();
            ES = false;
            ET = null;
        }

    }


    public static final f CREATOR = new f();
    private static final a EO = new a(new String[0], null) {

    };
    private final int CT;
    private final String EG[];
    Bundle EH;
    private final CursorWindow EI[];
    private final Bundle EJ;
    int EK[];
    int EL;
    private Object EM;
    private boolean EN;
    boolean mClosed;
    private final int xM;

    DataHolder(int i, String as[], CursorWindow acursorwindow[], int j, Bundle bundle)
    {
        mClosed = false;
        EN = true;
        xM = i;
        EG = as;
        EI = acursorwindow;
        CT = j;
        EJ = bundle;
    }

    private DataHolder(a a1, int i, Bundle bundle)
    {
        this(a.a(a1), a(a1, -1), i, bundle);
    }

    public DataHolder(String as[], CursorWindow acursorwindow[], int i, Bundle bundle)
    {
        mClosed = false;
        EN = true;
        xM = 1;
        EG = (String[])hm.f(as);
        EI = (CursorWindow[])hm.f(acursorwindow);
        CT = i;
        EJ = bundle;
        eW();
    }

    public static DataHolder a(int i, Bundle bundle)
    {
        return new DataHolder(EO, i, bundle);
    }

    private static CursorWindow[] a(a a1, int i)
    {
        int j;
        Object obj;
        CursorWindow cursorwindow;
        ArrayList arraylist;
        int l;
        boolean flag;
        j = 0;
        if (a.a(a1).length == 0)
        {
            return new CursorWindow[0];
        }
        int k;
        CursorWindow acursorwindow[];
        if (i < 0 || i >= a.b(a1).size())
        {
            obj = a.b(a1);
        } else
        {
            obj = a.b(a1).subList(0, i);
        }
        k = ((List) (obj)).size();
        cursorwindow = new CursorWindow(false);
        arraylist = new ArrayList();
        arraylist.add(cursorwindow);
        cursorwindow.setNumColumns(a.a(a1).length);
        l = 0;
        flag = false;
_L3:
        if (l >= k) goto _L2; else goto _L1
_L1:
        if (cursorwindow.allocRow())
        {
            break MISSING_BLOCK_LABEL_732;
        }
        Log.d("DataHolder", (new StringBuilder()).append("Allocating additional cursor window for large data set (row ").append(l).append(")").toString());
        cursorwindow = new CursorWindow(false);
        cursorwindow.setStartPosition(l);
        cursorwindow.setNumColumns(a.a(a1).length);
        arraylist.add(cursorwindow);
        if (cursorwindow.allocRow())
        {
            break MISSING_BLOCK_LABEL_227;
        }
        Log.e("DataHolder", "Unable to allocate row to hold data.");
        arraylist.remove(cursorwindow);
        acursorwindow = (CursorWindow[])arraylist.toArray(new CursorWindow[arraylist.size()]);
        return acursorwindow;
        int j1 = 0;
_L5:
        Map map = (Map)((List) (obj)).get(l);
        boolean flag1;
        flag1 = true;
        RuntimeException runtimeexception;
        int i1;
        String s;
        Object obj1;
        long l1;
        int i2;
        int j2;
        CursorWindow cursorwindow1;
        int k2;
        CursorWindow cursorwindow2;
        for (int k1 = 0; k1 >= a.a(a1).length || !flag1; k1++)
        {
            break MISSING_BLOCK_LABEL_591;
        }

        s = a.a(a1)[k1];
        obj1 = map.get(s);
        if (obj1 != null)
        {
            break MISSING_BLOCK_LABEL_303;
        }
        flag1 = cursorwindow.putNull(j1, k1);
        break MISSING_BLOCK_LABEL_739;
        if (obj1 instanceof String)
        {
            flag1 = cursorwindow.putString((String)obj1, j1, k1);
            break MISSING_BLOCK_LABEL_739;
        }
        if (obj1 instanceof Long)
        {
            flag1 = cursorwindow.putLong(((Long)obj1).longValue(), j1, k1);
            break MISSING_BLOCK_LABEL_739;
        }
        if (obj1 instanceof Integer)
        {
            flag1 = cursorwindow.putLong(((Integer)obj1).intValue(), j1, k1);
            break MISSING_BLOCK_LABEL_739;
        }
        if (!(obj1 instanceof Boolean))
        {
            break MISSING_BLOCK_LABEL_429;
        }
        if (((Boolean)obj1).booleanValue())
        {
            l1 = 1L;
        } else
        {
            l1 = 0L;
        }
        flag1 = cursorwindow.putLong(l1, j1, k1);
        break MISSING_BLOCK_LABEL_739;
        if (obj1 instanceof byte[])
        {
            flag1 = cursorwindow.putBlob((byte[])(byte[])obj1, j1, k1);
            break MISSING_BLOCK_LABEL_739;
        }
        if (obj1 instanceof Double)
        {
            flag1 = cursorwindow.putDouble(((Double)obj1).doubleValue(), j1, k1);
            break MISSING_BLOCK_LABEL_739;
        }
        if (obj1 instanceof Float)
        {
            flag1 = cursorwindow.putDouble(((Float)obj1).floatValue(), j1, k1);
            break MISSING_BLOCK_LABEL_739;
        }
        throw new IllegalArgumentException((new StringBuilder()).append("Unsupported object for column ").append(s).append(": ").append(obj1).toString());
        runtimeexception;
        for (i1 = arraylist.size(); j < i1; j++)
        {
            ((CursorWindow)arraylist.get(j)).close();
        }

        break MISSING_BLOCK_LABEL_712;
        if (flag1)
        {
            break MISSING_BLOCK_LABEL_695;
        }
        Log.d("DataHolder", (new StringBuilder()).append("Couldn't populate window data for row ").append(l).append(" - allocating new window.").toString());
        cursorwindow.freeLastRow();
        cursorwindow2 = new CursorWindow(false);
        cursorwindow2.setNumColumns(a.a(a1).length);
        arraylist.add(cursorwindow2);
        j2 = l - 1;
        cursorwindow1 = cursorwindow2;
        i2 = 0;
_L4:
        k2 = j2 + 1;
        cursorwindow = cursorwindow1;
        l = k2;
        flag = i2;
          goto _L3
        i2 = j1 + 1;
        j2 = l;
        cursorwindow1 = cursorwindow;
          goto _L4
        throw runtimeexception;
          goto _L3
_L2:
        return (CursorWindow[])arraylist.toArray(new CursorWindow[arraylist.size()]);
        j1 = ((flag) ? 1 : 0);
          goto _L5
    }

    public static DataHolder af(int i)
    {
        return a(i, ((Bundle) (null)));
    }

    private void e(String s, int i)
    {
        if (EH == null || !EH.containsKey(s))
        {
            throw new IllegalArgumentException((new StringBuilder()).append("No such column: ").append(s).toString());
        }
        if (isClosed())
        {
            throw new IllegalArgumentException("Buffer is closed.");
        }
        if (i < 0 || i >= EL)
        {
            throw new CursorIndexOutOfBoundsException(i, EL);
        } else
        {
            return;
        }
    }

    public long a(String s, int i, int j)
    {
        e(s, i);
        return EI[j].getLong(i, EH.getInt(s));
    }

    public void a(String s, int i, int j, CharArrayBuffer chararraybuffer)
    {
        e(s, i);
        EI[j].copyStringToBuffer(i, EH.getInt(s), chararraybuffer);
    }

    public int ae(int i)
    {
        int j = 0;
        boolean flag;
        if (i >= 0 && i < EL)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        hm.A(flag);
        do
        {
label0:
            {
                if (j < EK.length)
                {
                    if (i >= EK[j])
                    {
                        break label0;
                    }
                    j--;
                }
                if (j == EK.length)
                {
                    j--;
                }
                return j;
            }
            j++;
        } while (true);
    }

    public boolean av(String s)
    {
        return EH.containsKey(s);
    }

    public int b(String s, int i, int j)
    {
        e(s, i);
        return EI[j].getInt(i, EH.getInt(s));
    }

    public void b(Object obj)
    {
        EM = obj;
    }

    public String c(String s, int i, int j)
    {
        e(s, i);
        return EI[j].getString(i, EH.getInt(s));
    }

    public void close()
    {
        this;
        JVM INSTR monitorenter ;
        if (mClosed) goto _L2; else goto _L1
_L1:
        mClosed = true;
        int i = 0;
_L3:
        if (i >= EI.length)
        {
            break; /* Loop/switch isn't completed */
        }
        EI[i].close();
        i++;
        if (true) goto _L3; else goto _L2
_L2:
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public boolean d(String s, int i, int j)
    {
        e(s, i);
        return Long.valueOf(EI[j].getLong(i, EH.getInt(s))).longValue() == 1L;
    }

    public int describeContents()
    {
        return 0;
    }

    public float e(String s, int i, int j)
    {
        e(s, i);
        return EI[j].getFloat(i, EH.getInt(s));
    }

    public Bundle eU()
    {
        return EJ;
    }

    public void eW()
    {
        int i = 0;
        EH = new Bundle();
        for (int j = 0; j < EG.length; j++)
        {
            EH.putInt(EG[j], j);
        }

        EK = new int[EI.length];
        int k = 0;
        for (; i < EI.length; i++)
        {
            EK[i] = k;
            int l = k - EI[i].getStartPosition();
            k += EI[i].getNumRows() - l;
        }

        EL = k;
    }

    String[] eX()
    {
        return EG;
    }

    CursorWindow[] eY()
    {
        return EI;
    }

    public byte[] f(String s, int i, int j)
    {
        e(s, i);
        return EI[j].getBlob(i, EH.getInt(s));
    }

    protected void finalize()
        throws Throwable
    {
        if (!EN || EI.length <= 0 || isClosed()) goto _L2; else goto _L1
_L1:
        if (EM != null) goto _L4; else goto _L3
_L3:
        String s1 = (new StringBuilder()).append("internal object: ").append(toString()).toString();
_L5:
        Log.e("DataBuffer", (new StringBuilder()).append("Internal data leak within a DataBuffer object detected!  Be sure to explicitly call close() on all DataBuffer extending objects when you are done with them. (").append(s1).append(")").toString());
        close();
_L2:
        super.finalize();
        return;
_L4:
        String s = EM.toString();
        s1 = s;
          goto _L5
        Exception exception;
        exception;
        super.finalize();
        throw exception;
    }

    public Uri g(String s, int i, int j)
    {
        String s1 = c(s, i, j);
        if (s1 == null)
        {
            return null;
        } else
        {
            return Uri.parse(s1);
        }
    }

    public int getCount()
    {
        return EL;
    }

    public int getStatusCode()
    {
        return CT;
    }

    int getVersionCode()
    {
        return xM;
    }

    public boolean h(String s, int i, int j)
    {
        e(s, i);
        return EI[j].isNull(i, EH.getInt(s));
    }

    public boolean isClosed()
    {
        this;
        JVM INSTR monitorenter ;
        boolean flag = mClosed;
        this;
        JVM INSTR monitorexit ;
        return flag;
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        com.google.android.gms.common.data.f.a(this, parcel, i);
    }

}

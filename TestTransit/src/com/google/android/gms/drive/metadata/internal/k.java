// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.drive.metadata.internal;

import android.os.Bundle;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.drive.metadata.b;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import org.json.JSONArray;
import org.json.JSONException;

public class k extends b
{

    public k(String s, int i)
    {
        super(s, i);
    }

    public static final Collection aO(String s)
        throws JSONException
    {
        if (s == null)
        {
            return null;
        }
        ArrayList arraylist = new ArrayList();
        JSONArray jsonarray = new JSONArray(s);
        for (int i = 0; i < jsonarray.length(); i++)
        {
            arraylist.add(jsonarray.getString(i));
        }

        return Collections.unmodifiableCollection(arraylist);
    }

    protected volatile void a(Bundle bundle, Object obj)
    {
        a(bundle, (Collection)obj);
    }

    protected void a(Bundle bundle, Collection collection)
    {
        bundle.putStringArrayList(getName(), new ArrayList(collection));
    }

    protected Object b(DataHolder dataholder, int i, int j)
    {
        return c(dataholder, i, j);
    }

    protected Collection c(DataHolder dataholder, int i, int j)
    {
        Collection collection;
        try
        {
            collection = aO(dataholder.c(getName(), i, j));
        }
        catch (JSONException jsonexception)
        {
            throw new IllegalStateException("DataHolder supplied invalid JSON", jsonexception);
        }
        return collection;
    }

    protected Object f(Bundle bundle)
    {
        return k(bundle);
    }

    protected Collection k(Bundle bundle)
    {
        return bundle.getStringArrayList(getName());
    }
}

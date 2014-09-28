// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.drive.query.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.drive.query.Filter;

// Referenced classes of package com.google.android.gms.drive.query.internal:
//            d, ComparisonFilter, FieldOnlyFilter, LogicalFilter, 
//            NotFilter, InFilter, MatchAllFilter, HasFilter

public class FilterHolder
    implements SafeParcelable
{

    public static final android.os.Parcelable.Creator CREATOR = new d();
    final ComparisonFilter KP;
    final FieldOnlyFilter KQ;
    final LogicalFilter KR;
    final NotFilter KS;
    final InFilter KT;
    final MatchAllFilter KU;
    final HasFilter KV;
    private final Filter KW;
    final int xM;

    FilterHolder(int i, ComparisonFilter comparisonfilter, FieldOnlyFilter fieldonlyfilter, LogicalFilter logicalfilter, NotFilter notfilter, InFilter infilter, MatchAllFilter matchallfilter, 
            HasFilter hasfilter)
    {
        xM = i;
        KP = comparisonfilter;
        KQ = fieldonlyfilter;
        KR = logicalfilter;
        KS = notfilter;
        KT = infilter;
        KU = matchallfilter;
        KV = hasfilter;
        if (KP != null)
        {
            KW = KP;
            return;
        }
        if (KQ != null)
        {
            KW = KQ;
            return;
        }
        if (KR != null)
        {
            KW = KR;
            return;
        }
        if (KS != null)
        {
            KW = KS;
            return;
        }
        if (KT != null)
        {
            KW = KT;
            return;
        }
        if (KU != null)
        {
            KW = KU;
            return;
        }
        if (KV != null)
        {
            KW = KV;
            return;
        } else
        {
            throw new IllegalArgumentException("At least one filter must be set.");
        }
    }

    public FilterHolder(Filter filter)
    {
        xM = 2;
        ComparisonFilter comparisonfilter;
        FieldOnlyFilter fieldonlyfilter;
        LogicalFilter logicalfilter;
        NotFilter notfilter;
        InFilter infilter;
        MatchAllFilter matchallfilter;
        HasFilter hasfilter;
        if (filter instanceof ComparisonFilter)
        {
            comparisonfilter = (ComparisonFilter)filter;
        } else
        {
            comparisonfilter = null;
        }
        KP = comparisonfilter;
        if (filter instanceof FieldOnlyFilter)
        {
            fieldonlyfilter = (FieldOnlyFilter)filter;
        } else
        {
            fieldonlyfilter = null;
        }
        KQ = fieldonlyfilter;
        if (filter instanceof LogicalFilter)
        {
            logicalfilter = (LogicalFilter)filter;
        } else
        {
            logicalfilter = null;
        }
        KR = logicalfilter;
        if (filter instanceof NotFilter)
        {
            notfilter = (NotFilter)filter;
        } else
        {
            notfilter = null;
        }
        KS = notfilter;
        if (filter instanceof InFilter)
        {
            infilter = (InFilter)filter;
        } else
        {
            infilter = null;
        }
        KT = infilter;
        if (filter instanceof MatchAllFilter)
        {
            matchallfilter = (MatchAllFilter)filter;
        } else
        {
            matchallfilter = null;
        }
        KU = matchallfilter;
        if (filter instanceof HasFilter)
        {
            hasfilter = (HasFilter)filter;
        } else
        {
            hasfilter = null;
        }
        KV = hasfilter;
        if (KP == null && KQ == null && KR == null && KS == null && KT == null && KU == null && KV == null)
        {
            throw new IllegalArgumentException("Invalid filter type or null filter.");
        } else
        {
            KW = filter;
            return;
        }
    }

    public int describeContents()
    {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        d.a(this, parcel, i);
    }

}

// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.internal.widget;

import android.content.pm.ResolveInfo;
import java.math.BigDecimal;

// Referenced classes of package android.support.v7.internal.widget:
//            ActivityChooserModel

public final class resolveInfo
    implements Comparable
{

    public final ResolveInfo resolveInfo;
    final ActivityChooserModel this$0;
    public float weight;

    public int compareTo(resolveInfo resolveinfo)
    {
        return Float.floatToIntBits(resolveinfo.weight) - Float.floatToIntBits(weight);
    }

    public volatile int compareTo(Object obj)
    {
        return compareTo((compareTo)obj);
    }

    public boolean equals(Object obj)
    {
        if (this != obj)
        {
            if (obj == null)
            {
                return false;
            }
            if (getClass() != obj.getClass())
            {
                return false;
            }
            compareTo compareto = (compareTo)obj;
            if (Float.floatToIntBits(weight) != Float.floatToIntBits(compareto.weight))
            {
                return false;
            }
        }
        return true;
    }

    public int hashCode()
    {
        return 31 + Float.floatToIntBits(weight);
    }

    public String toString()
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("[");
        stringbuilder.append("resolveInfo:").append(resolveInfo.toString());
        stringbuilder.append("; weight:").append(new BigDecimal(weight));
        stringbuilder.append("]");
        return stringbuilder.toString();
    }

    public Q(ResolveInfo resolveinfo)
    {
        this$0 = ActivityChooserModel.this;
        super();
        resolveInfo = resolveinfo;
    }
}

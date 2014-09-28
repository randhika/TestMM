// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.wallet.fragment;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.os.Parcel;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

// Referenced classes of package com.google.android.gms.wallet.fragment:
//            c, Dimension

public final class WalletFragmentStyle
    implements SafeParcelable
{

    public static final android.os.Parcelable.Creator CREATOR = new c();
    Bundle akE;
    int akF;
    final int xM;

    public WalletFragmentStyle()
    {
        xM = 1;
        akE = new Bundle();
    }

    WalletFragmentStyle(int i, Bundle bundle, int j)
    {
        xM = i;
        akE = bundle;
        akF = j;
    }

    private void a(TypedArray typedarray, int i, String s)
    {
        TypedValue typedvalue;
        if (!akE.containsKey(s))
        {
            if ((typedvalue = typedarray.peekValue(i)) != null)
            {
                akE.putLong(s, Dimension.a(typedvalue));
                return;
            }
        }
    }

    private void a(TypedArray typedarray, int i, String s, String s1)
    {
        TypedValue typedvalue;
        if (!akE.containsKey(s) && !akE.containsKey(s1))
        {
            if ((typedvalue = typedarray.peekValue(i)) != null)
            {
                if (typedvalue.type >= 28 && typedvalue.type <= 31)
                {
                    akE.putInt(s, typedvalue.data);
                    return;
                } else
                {
                    akE.putInt(s1, typedvalue.resourceId);
                    return;
                }
            }
        }
    }

    private void b(TypedArray typedarray, int i, String s)
    {
        TypedValue typedvalue;
        if (!akE.containsKey(s))
        {
            if ((typedvalue = typedarray.peekValue(i)) != null)
            {
                akE.putInt(s, typedvalue.data);
                return;
            }
        }
    }

    public void Q(Context context)
    {
        int i;
        TypedArray typedarray;
        if (akF <= 0)
        {
            i = com.google.android.gms.R.style.WalletFragmentDefaultStyle;
        } else
        {
            i = akF;
        }
        typedarray = context.obtainStyledAttributes(i, com.google.android.gms.R.styleable.WalletFragmentStyle);
        a(typedarray, 1, "buyButtonWidth");
        a(typedarray, 0, "buyButtonHeight");
        b(typedarray, 2, "buyButtonText");
        b(typedarray, 3, "buyButtonAppearance");
        b(typedarray, 4, "maskedWalletDetailsTextAppearance");
        b(typedarray, 5, "maskedWalletDetailsHeaderTextAppearance");
        a(typedarray, 6, "maskedWalletDetailsBackgroundColor", "maskedWalletDetailsBackgroundResource");
        b(typedarray, 7, "maskedWalletDetailsButtonTextAppearance");
        a(typedarray, 8, "maskedWalletDetailsButtonBackgroundColor", "maskedWalletDetailsButtonBackgroundResource");
        b(typedarray, 9, "maskedWalletDetailsLogoTextColor");
        b(typedarray, 10, "maskedWalletDetailsLogoImageType");
        typedarray.recycle();
    }

    public int a(String s, DisplayMetrics displaymetrics, int i)
    {
        if (akE.containsKey(s))
        {
            i = Dimension.a(akE.getLong(s), displaymetrics);
        }
        return i;
    }

    public int describeContents()
    {
        return 0;
    }

    public WalletFragmentStyle setBuyButtonAppearance(int i)
    {
        akE.putInt("buyButtonAppearance", i);
        return this;
    }

    public WalletFragmentStyle setBuyButtonHeight(int i)
    {
        akE.putLong("buyButtonHeight", Dimension.dM(i));
        return this;
    }

    public WalletFragmentStyle setBuyButtonHeight(int i, float f)
    {
        akE.putLong("buyButtonHeight", Dimension.a(i, f));
        return this;
    }

    public WalletFragmentStyle setBuyButtonText(int i)
    {
        akE.putInt("buyButtonText", i);
        return this;
    }

    public WalletFragmentStyle setBuyButtonWidth(int i)
    {
        akE.putLong("buyButtonWidth", Dimension.dM(i));
        return this;
    }

    public WalletFragmentStyle setBuyButtonWidth(int i, float f)
    {
        akE.putLong("buyButtonWidth", Dimension.a(i, f));
        return this;
    }

    public WalletFragmentStyle setMaskedWalletDetailsBackgroundColor(int i)
    {
        akE.remove("maskedWalletDetailsBackgroundResource");
        akE.putInt("maskedWalletDetailsBackgroundColor", i);
        return this;
    }

    public WalletFragmentStyle setMaskedWalletDetailsBackgroundResource(int i)
    {
        akE.remove("maskedWalletDetailsBackgroundColor");
        akE.putInt("maskedWalletDetailsBackgroundResource", i);
        return this;
    }

    public WalletFragmentStyle setMaskedWalletDetailsButtonBackgroundColor(int i)
    {
        akE.remove("maskedWalletDetailsButtonBackgroundResource");
        akE.putInt("maskedWalletDetailsButtonBackgroundColor", i);
        return this;
    }

    public WalletFragmentStyle setMaskedWalletDetailsButtonBackgroundResource(int i)
    {
        akE.remove("maskedWalletDetailsButtonBackgroundColor");
        akE.putInt("maskedWalletDetailsButtonBackgroundResource", i);
        return this;
    }

    public WalletFragmentStyle setMaskedWalletDetailsButtonTextAppearance(int i)
    {
        akE.putInt("maskedWalletDetailsButtonTextAppearance", i);
        return this;
    }

    public WalletFragmentStyle setMaskedWalletDetailsHeaderTextAppearance(int i)
    {
        akE.putInt("maskedWalletDetailsHeaderTextAppearance", i);
        return this;
    }

    public WalletFragmentStyle setMaskedWalletDetailsLogoImageType(int i)
    {
        akE.putInt("maskedWalletDetailsLogoImageType", i);
        return this;
    }

    public WalletFragmentStyle setMaskedWalletDetailsLogoTextColor(int i)
    {
        akE.putInt("maskedWalletDetailsLogoTextColor", i);
        return this;
    }

    public WalletFragmentStyle setMaskedWalletDetailsTextAppearance(int i)
    {
        akE.putInt("maskedWalletDetailsTextAppearance", i);
        return this;
    }

    public WalletFragmentStyle setStyleResourceId(int i)
    {
        akF = i;
        return this;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        c.a(this, parcel, i);
    }

}

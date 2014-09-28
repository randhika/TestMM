// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.common;


// Referenced classes of package jp.co.yahoo.android.common:
//            YDateRollerPickerHelper

class this._cls0
    implements seTimeRollerPickerListener
{

    final YDateRollerPickerHelper this$0;

    public void onScrollStateChanged()
    {
        sendToOnDateChangedListener(true);
    }

    seTimeRollerPickerListener()
    {
        this$0 = YDateRollerPickerHelper.this;
        super();
    }
}

// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.common;


// Referenced classes of package jp.co.yahoo.android.common:
//            YTimeRollerPickerHelper

class this._cls0
    implements seTimeRollerPickerListener
{

    final YTimeRollerPickerHelper this$0;

    public void onScrollStateChanged()
    {
        sendToOnTimeChangedListener();
    }

    seTimeRollerPickerListener()
    {
        this$0 = YTimeRollerPickerHelper.this;
        super();
    }
}

// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.yjvoice;


// Referenced classes of package jp.co.yahoo.android.yjvoice:
//            YJVORecognizeResult, YJVO_STATE

public interface YJVORecognizeListener
{

    public abstract void onRecognizeResult(int i, YJVORecognizeResult yjvorecognizeresult);

    public abstract void onRecognizeState(YJVO_STATE yjvo_state);

    public abstract void onRecordingStart();

    public abstract void onVolumeChanged(short word0);
}

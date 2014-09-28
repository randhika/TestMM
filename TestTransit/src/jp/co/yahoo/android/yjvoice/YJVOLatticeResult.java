// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.yjvoice;


// Referenced classes of package jp.co.yahoo.android.yjvoice:
//            YJVORecognizeResult, YJVO_TYPE

public class YJVOLatticeResult extends YJVORecognizeResult
{

    public String xmlResult;

    public YJVOLatticeResult(int i, String s)
    {
        i;
        JVM INSTR tableswitch 1 1: default 24
    //                   1 42;
           goto _L1 _L2
_L1:
        super.type = YJVO_TYPE.NBEST;
_L4:
        super.result = this;
        xmlResult = s;
        return;
_L2:
        super.type = YJVO_TYPE.LATTICE;
        if (true) goto _L4; else goto _L3
_L3:
    }
}

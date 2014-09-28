// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.yjvoice;


// Referenced classes of package jp.co.yahoo.android.yjvoice:
//            YJVORecorder

public static final class _cls9 extends Enum
{

    private static final .VALUES $VALUES[];
    public static final .VALUES K11;
    public static final .VALUES K16;
    public static final .VALUES K22;
    public static final .VALUES K32;
    public static final .VALUES K44;
    public static final .VALUES K48;
    public static final .VALUES K8;
    public static final .VALUES K88;
    public static final .VALUES K96;

    public static _cls9 valueOf(String s)
    {
        return (_cls9)Enum.valueOf(jp/co/yahoo/android/yjvoice/YJVORecorder$SAMPLERATE, s);
    }

    public static _cls9[] values()
    {
        return (_cls9[])$VALUES.clone();
    }

    static 
    {
        K8 = new <init>("K8", 0);
        K11 = new <init>("K11", 1);
        K16 = new <init>("K16", 2);
        K22 = new <init>("K22", 3);
        K32 = new <init>("K32", 4);
        K44 = new <init>("K44", 5);
        K48 = new <init>("K48", 6);
        K88 = new <init>("K88", 7);
        K96 = new <init>("K96", 8);
        E_3B_.clone aclone[] = new <init>[9];
        aclone[0] = K8;
        aclone[1] = K11;
        aclone[2] = K16;
        aclone[3] = K22;
        aclone[4] = K32;
        aclone[5] = K44;
        aclone[6] = K48;
        aclone[7] = K88;
        aclone[8] = K96;
        $VALUES = aclone;
    }

    private _cls9(String s, int i)
    {
        super(s, i);
    }
}

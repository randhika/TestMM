// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.applicot.collector;


// Referenced classes of package jp.co.yahoo.applicot.collector:
//            MediaCodecListCollector

private static final class  extends Enum
{

    private static final .VALUES $VALUES[];
    public static final .VALUES AAC;
    public static final .VALUES AVC;
    public static final .VALUES H263;
    public static final .VALUES MPEG4;

    public static  valueOf(String s)
    {
        return ()Enum.valueOf(jp/co/yahoo/applicot/collector/MediaCodecListCollector$CodecType, s);
    }

    public static [] values()
    {
        return ([])$VALUES.clone();
    }

    static 
    {
        AVC = new <init>("AVC", 0);
        H263 = new <init>("H263", 1);
        MPEG4 = new <init>("MPEG4", 2);
        AAC = new <init>("AAC", 3);
        e_3B_.clone aclone[] = new <init>[4];
        aclone[0] = AVC;
        aclone[1] = H263;
        aclone[2] = MPEG4;
        aclone[3] = AAC;
        $VALUES = aclone;
    }

    private (String s, int i)
    {
        super(s, i);
    }
}

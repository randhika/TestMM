// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.ads;


// Referenced classes of package com.google.ads:
//            AdRequest

public static final class description extends Enum
{

    public static final a INTERNAL_ERROR;
    public static final a INVALID_REQUEST;
    public static final a NETWORK_ERROR;
    public static final a NO_FILL;
    private static final a a[];
    private final String description;

    public static description valueOf(String s)
    {
        return (description)Enum.valueOf(com/google/ads/AdRequest$ErrorCode, s);
    }

    public static description[] values()
    {
        return (description[])a.clone();
    }

    public String toString()
    {
        return description;
    }

    static 
    {
        INVALID_REQUEST = new <init>("INVALID_REQUEST", 0, "Invalid Ad request.");
        NO_FILL = new <init>("NO_FILL", 1, "Ad request successful, but no ad returned due to lack of ad inventory.");
        NETWORK_ERROR = new <init>("NETWORK_ERROR", 2, "A network error occurred.");
        INTERNAL_ERROR = new <init>("INTERNAL_ERROR", 3, "There was an internal error.");
        description adescription[] = new <init>[4];
        adescription[0] = INVALID_REQUEST;
        adescription[1] = NO_FILL;
        adescription[2] = NETWORK_ERROR;
        adescription[3] = INTERNAL_ERROR;
        a = adescription;
    }

    private (String s, int i, String s1)
    {
        super(s, i);
        description = s1;
    }
}

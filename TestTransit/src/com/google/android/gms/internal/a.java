// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;


public final class a extends Enum
{

    public static final a A;
    public static final a B;
    public static final a C;
    public static final a D;
    public static final a E;
    public static final a F;
    public static final a G;
    public static final a H;
    public static final a I;
    public static final a J;
    public static final a K;
    public static final a L;
    public static final a M;
    public static final a N;
    public static final a O;
    public static final a P;
    public static final a Q;
    public static final a R;
    public static final a S;
    public static final a T;
    public static final a U;
    public static final a V;
    public static final a W;
    public static final a X;
    public static final a Y;
    public static final a Z;
    public static final a aA;
    public static final a aB;
    public static final a aC;
    public static final a aD;
    public static final a aE;
    public static final a aF;
    public static final a aG;
    public static final a aH;
    public static final a aI;
    public static final a aJ;
    public static final a aK;
    public static final a aL;
    public static final a aM;
    public static final a aN;
    public static final a aO;
    public static final a aP;
    public static final a aQ;
    public static final a aR;
    public static final a aS;
    public static final a aT;
    private static final a aU[];
    public static final a aa;
    public static final a ab;
    public static final a ac;
    public static final a ad;
    public static final a ae;
    public static final a af;
    public static final a ag;
    public static final a ah;
    public static final a ai;
    public static final a aj;
    public static final a ak;
    public static final a al;
    public static final a am;
    public static final a an;
    public static final a ao;
    public static final a ap;
    public static final a aq;
    public static final a ar;
    public static final a as;
    public static final a at;
    public static final a au;
    public static final a av;
    public static final a aw;
    public static final a ax;
    public static final a ay;
    public static final a az;
    public static final a u;
    public static final a v;
    public static final a w;
    public static final a x;
    public static final a y;
    public static final a z;
    private final String name;

    private a(String s, int i, String s1)
    {
        super(s, i);
        name = s1;
    }

    public static a valueOf(String s)
    {
        return (a)Enum.valueOf(com/google/android/gms/internal/a, s);
    }

    public static a[] values()
    {
        return (a[])aU.clone();
    }

    public String toString()
    {
        return name;
    }

    static 
    {
        u = new a("ADVERTISER_ID", 0, "_aid");
        v = new a("ADVERTISING_TRACKING_ENABLED", 1, "_ate");
        w = new a("APP_ID", 2, "_ai");
        x = new a("APP_NAME", 3, "_an");
        y = new a("APP_VERSION", 4, "_av");
        z = new a("ARBITRARY_JAVASCRIPT", 5, "_jsm");
        A = new a("CONSTANT", 6, "_c");
        B = new a("COOKIE", 7, "_k");
        C = new a("CUSTOM_VAR", 8, "_v");
        D = new a("CONTAINER_VERSION", 9, "_ctv");
        E = new a("DEBUG_MODE", 10, "_dbg");
        F = new a("DEVICE_NAME", 11, "_dn");
        G = new a("DEVICE_TYPE", 12, "_dt");
        H = new a("DOM_ELEMENT", 13, "_d");
        I = new a("EVENT", 14, "_e");
        J = new a("FUNCTION_CALL", 15, "_func");
        K = new a("JS_GLOBAL", 16, "_j");
        L = new a("LANGUAGE", 17, "_l");
        M = new a("OS_VERSION", 18, "_ov");
        N = new a("PLATFORM", 19, "_p");
        O = new a("RANDOM", 20, "_r");
        P = new a("REFERRER", 21, "_f");
        Q = new a("RESOLUTION", 22, "_rs");
        R = new a("RUNTIME_VERSION", 23, "_rv");
        S = new a("SDK_VERSION", 24, "_sv");
        T = new a("SIMPLE_MAP", 25, "_smm");
        U = new a("TIME", 26, "_t");
        V = new a("URL", 27, "_u");
        W = new a("ADWORDS_CLICK_REFERRER", 28, "_awcr");
        X = new a("DEVICE_ID", 29, "_did");
        Y = new a("ENCODE", 30, "_enc");
        Z = new a("GTM_VERSION", 31, "_gtmv");
        aa = new a("HASH", 32, "_hsh");
        ab = new a("INSTALL_REFERRER", 33, "_ir");
        ac = new a("JOINER", 34, "_jn");
        ad = new a("MOBILE_ADWORDS_UNIQUE_ID", 35, "_awid");
        ae = new a("REGEX_GROUP", 36, "_reg");
        af = new a("DATA_LAYER_WRITE", 37, "_dlw");
        ag = new a("REGEX", 38, "_re");
        ah = new a("STARTS_WITH", 39, "_sw");
        ai = new a("ENDS_WITH", 40, "_ew");
        aj = new a("CONTAINS", 41, "_cn");
        ak = new a("EQUALS", 42, "_eq");
        al = new a("LESS_THAN", 43, "_lt");
        am = new a("LESS_EQUALS", 44, "_le");
        an = new a("GREATER_THAN", 45, "_gt");
        ao = new a("GREATER_EQUALS", 46, "_ge");
        ap = new a("ARBITRARY_PIXEL", 47, "_img");
        aq = new a("ARBITRARY_HTML", 48, "_html");
        ar = new a("GOOGLE_TAG_MANAGER", 49, "_gtm");
        as = new a("GOOGLE_ANALYTICS", 50, "_ga");
        at = new a("ADWORDS_CONVERSION", 51, "_awct");
        au = new a("SMART_PIXEL", 52, "_sp");
        av = new a("FLOODLIGHT_COUNTER", 53, "_flc");
        aw = new a("FLOODLIGHT_SALES", 54, "_fls");
        ax = new a("BIZO_INSIGHT", 55, "_bzi");
        ay = new a("QUANTCAST_MEASUREMENT", 56, "_qcm");
        az = new a("TARGUS_ADVISOR", 57, "_ta");
        aA = new a("MEDIAPLEX_ROI", 58, "_mpr");
        aB = new a("COMSCORE_MEASUREMENT", 59, "_csm");
        aC = new a("TURN_CONVERSION", 60, "_tc");
        aD = new a("TURN_DATA_COLLECTION", 61, "_tdc");
        aE = new a("MEDIA6DEGREES_UNIVERSAL_PIXEL", 62, "_m6d");
        aF = new a("UNIVERSAL_ANALYTICS", 63, "_ua");
        aG = new a("MEDIAPLEX_MCT", 64, "_mpm");
        aH = new a("VISUAL_DNA_CONVERSION", 65, "_vdc");
        aI = new a("GOOGLE_AFFILIATE_NETWORK", 66, "_gan");
        aJ = new a("MARIN_SOFTWARE", 67, "_ms");
        aK = new a("ADROLL_SMART_PIXEL", 68, "_asp");
        aL = new a("CONFIGURATION_VALUE", 69, "_cv");
        aM = new a("CRITEO", 70, "_crt");
        aN = new a("TRUSTED_STORES", 71, "_ts");
        aO = new a("CLICK_TALE_STANDARD", 72, "_cts");
        aP = new a("LINK_CLICK_LISTENER", 73, "_lcl");
        aQ = new a("FORM_SUBMIT_LISTENER", 74, "_fsl");
        aR = new a("TIMER_LISTENER", 75, "_tl");
        aS = new a("CLICK_LISTENER", 76, "_cl");
        aT = new a("JS_ERROR_LISTENER", 77, "_jel");
        a aa1[] = new a[78];
        aa1[0] = u;
        aa1[1] = v;
        aa1[2] = w;
        aa1[3] = x;
        aa1[4] = y;
        aa1[5] = z;
        aa1[6] = A;
        aa1[7] = B;
        aa1[8] = C;
        aa1[9] = D;
        aa1[10] = E;
        aa1[11] = F;
        aa1[12] = G;
        aa1[13] = H;
        aa1[14] = I;
        aa1[15] = J;
        aa1[16] = K;
        aa1[17] = L;
        aa1[18] = M;
        aa1[19] = N;
        aa1[20] = O;
        aa1[21] = P;
        aa1[22] = Q;
        aa1[23] = R;
        aa1[24] = S;
        aa1[25] = T;
        aa1[26] = U;
        aa1[27] = V;
        aa1[28] = W;
        aa1[29] = X;
        aa1[30] = Y;
        aa1[31] = Z;
        aa1[32] = aa;
        aa1[33] = ab;
        aa1[34] = ac;
        aa1[35] = ad;
        aa1[36] = ae;
        aa1[37] = af;
        aa1[38] = ag;
        aa1[39] = ah;
        aa1[40] = ai;
        aa1[41] = aj;
        aa1[42] = ak;
        aa1[43] = al;
        aa1[44] = am;
        aa1[45] = an;
        aa1[46] = ao;
        aa1[47] = ap;
        aa1[48] = aq;
        aa1[49] = ar;
        aa1[50] = as;
        aa1[51] = at;
        aa1[52] = au;
        aa1[53] = av;
        aa1[54] = aw;
        aa1[55] = ax;
        aa1[56] = ay;
        aa1[57] = az;
        aa1[58] = aA;
        aa1[59] = aB;
        aa1[60] = aC;
        aa1[61] = aD;
        aa1[62] = aE;
        aa1[63] = aF;
        aa1[64] = aG;
        aa1[65] = aH;
        aa1[66] = aI;
        aa1[67] = aJ;
        aa1[68] = aK;
        aa1[69] = aL;
        aa1[70] = aM;
        aa1[71] = aN;
        aa1[72] = aO;
        aa1[73] = aP;
        aa1[74] = aQ;
        aa1[75] = aR;
        aa1[76] = aS;
        aa1[77] = aT;
        aU = aa1;
    }
}

// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;


// Referenced classes of package com.google.android.gms.internal:
//            f

private final class <init>
    implements <init>
{

    final f jN;

    public void b(byte abyte0[], byte abyte1[])
    {
        jN.hL = jN.hP ^ jN.hL;
        jN.hL = jN.hL & (-1 ^ jN.iq);
        jN.hL = jN.gA ^ jN.hL;
        jN.hm = jN.hL ^ jN.hm;
        jN.hm = jN.is & (-1 ^ jN.hm);
        jN.hm = jN.ju ^ jN.hm;
        jN.gS = jN.hm ^ jN.gS;
        jN.hm = jN.gS & (-1 ^ jN.jh);
        jN.ju = jN.gK & jN.hm;
        jN.is = jN.gK & (-1 ^ jN.gS);
        jN.hL = jN.gK & jN.gS;
        jN.gA = jN.gS & jN.jh;
        jN.hP = jN.jh & (-1 ^ jN.gA);
        jN.iA = jN.gA ^ jN.iA;
        jN.hL = jN.gA ^ jN.hL;
        jN.gF = jN.hw & jN.hL;
        jN.hL = jN.hL | jN.hw;
        jN.jC = jN.gK & jN.gA;
        jN.jC = jN.gS ^ jN.jC;
        jN.jC = jN.jC | jN.hw;
        jN.jq = jN.gA ^ jN.jq;
        jN.hu = jN.hw & jN.jq;
        jN.hu = jN.iA ^ jN.hu;
        jN.hF = jN.jq | jN.hw;
        jN.hF = jN.iA ^ jN.hF;
        jN.he = jN.gS ^ jN.he;
        jN.he = jN.hw & jN.he;
        jN.he = jN.jq ^ jN.he;
        jN.he = jN.he & (-1 ^ jN.gC);
        jN.jq = jN.gS | jN.jh;
        jN.iA = jN.gK & (-1 ^ jN.jq);
        jN.iA = jN.jh ^ jN.iA;
        jN.iA = jN.hw & jN.iA;
        jN.jE = jN.gK & jN.jq;
        jN.jE = jN.hP ^ jN.jE;
        jN.hO = jN.hw | jN.jE;
        jN.hO = jN.io ^ jN.hO;
        jN.iA = jN.jE ^ jN.iA;
        jN.iA = jN.iA & (-1 ^ jN.gC);
        jN.jE = jN.gK & jN.jq;
        jN.jq = jN.jq & (-1 ^ jN.jh);
        jN.jE = jN.jq ^ jN.jE;
        jN.jE = jN.hw | jN.jE;
        jN.io = jN.gK & (-1 ^ jN.jq);
        jN.io = jN.hm ^ jN.io;
        jN.io = jN.io & (-1 ^ jN.hw);
        jN.hT = jN.jh & (-1 ^ jN.gS);
        jN.ge = jN.hT ^ jN.ge;
        jN.ht = jN.ge & (-1 ^ jN.hw);
        jN.ht = jN.is ^ jN.ht;
        jN.ht = jN.gC | jN.ht;
        jN.ht = jN.hO ^ jN.ht;
        jN.hO = jN.ge & (-1 ^ jN.hw);
        jN.hO = jN.gS ^ jN.hO;
        jN.hO = jN.gC | jN.hO;
        jN.is = jN.gK & jN.hT;
        jN.is = jN.jq ^ jN.is;
        jN.io = jN.is ^ jN.io;
        jN.io = jN.gC | jN.io;
        jN.jq = jN.gS ^ jN.jh;
        jN.ju = jN.jq ^ jN.ju;
        jN.jC = jN.ju ^ jN.jC;
        jN.he = jN.jC ^ jN.he;
        jN.jC = jN.gK & (-1 ^ jN.jq);
        jN.jC = jN.hP ^ jN.jC;
        jN.jE = jN.jC ^ jN.jE;
        jN.io = jN.jE ^ jN.io;
        jN.jE = jN.gK & (-1 ^ jN.jq);
        jN.jE = jN.hm ^ jN.jE;
        jN.jE = jN.hw & jN.jE;
        jN.jE = jN.ge ^ jN.jE;
        jN.jE = jN.jE & (-1 ^ jN.gC);
        jN.jE = jN.hu ^ jN.jE;
        jN.jE = jN.ha & (-1 ^ jN.jE);
        jN.hu = jN.gK & jN.jq;
        jN.hu = jN.hm ^ jN.hu;
        jN.hu = jN.hu & (-1 ^ jN.hw);
        jN.hu = jN.gA ^ jN.hu;
        jN.hu = jN.hu & (-1 ^ jN.gC);
        jN.hu = jN.hF ^ jN.hu;
        jN.hu = jN.hu & jN.ha;
        jN.hu = jN.io ^ jN.hu;
        jN.iy = jN.hu ^ jN.iy;
        jN.iM = jN.iM & jN.iy;
        jN.iM = jN.gY ^ jN.iM;
        jN.gY = jN.iy & jN.iv;
        jN.gY = jN.hK ^ jN.gY;
        jN.gV = jN.iy & jN.gV;
        jN.gV = jN.gg ^ jN.gV;
        jN.gV = jN.je & jN.gV;
        jN.hg = jN.iy | jN.hg;
        jN.hg = jN.iv ^ jN.hg;
        jN.gV = jN.hg ^ jN.gV;
        jN.gV = jN.gV ^ jN.gK;
        jN.hg = jN.gV & jN.hk;
        jN.iv = jN.hk & (-1 ^ jN.hg);
        jN.gg = jN.gV & (-1 ^ jN.hk);
        jN.hK = jN.gm & jN.gV;
        jN.hu = jN.gV ^ jN.hk;
        jN.io = jN.hk | jN.gV;
        jN.hF = jN.hk & (-1 ^ jN.gV);
        jN.ix = jN.iy & (-1 ^ jN.ix);
        jN.ix = jN.gb ^ jN.ix;
        jN.ix = jN.je & (-1 ^ jN.ix);
        jN.ix = jN.iM ^ jN.ix;
        jN.gw = jN.ix ^ jN.gw;
        jN.ix = jN.gw | jN.hG;
        jN.ix = jN.hG ^ jN.ix;
        jN.ix = jN.ix & (-1 ^ jN.gQ);
        jN.iM = -1 ^ jN.gw;
        jN.gb = jN.hG & (-1 ^ jN.gw);
        jN.gb = jN.gQ | jN.gb;
        jN.hm = jN.hG ^ jN.gw;
        jN.ge = jN.hm & jN.gQ;
        jN.jC = jN.gw | jN.hG;
        jN.iT = jN.iT & jN.iy;
        jN.iT = jN.jl ^ jN.iT;
        jN.iT = jN.je & (-1 ^ jN.iT);
        jN.hl = jN.iy & (-1 ^ jN.hl);
        jN.hl = jN.gp ^ jN.hl;
        jN.iT = jN.hl ^ jN.iT;
        jN.hN = jN.iT ^ jN.hN;
        jN.iT = jN.hN & (-1 ^ jN.ir);
        jN.hl = jN.hN & (-1 ^ jN.ir);
        jN.iP = jN.iP & jN.iy;
        jN.iP = jN.ji ^ jN.iP;
        jN.iP = jN.je & (-1 ^ jN.iP);
        jN.iP = jN.gY ^ jN.iP;
        jN.gO = jN.iP ^ jN.gO;
        jN.iP = jN.it & (-1 ^ jN.gO);
        jN.gY = -1 ^ jN.gO;
        jN.ji = jN.gK & (-1 ^ jN.jq);
        jN.ji = jN.jh ^ jN.ji;
        jN.ji = jN.ji | jN.hw;
        jN.ji = jN.is ^ jN.ji;
        jN.iA = jN.ji ^ jN.iA;
        jN.jE = jN.iA ^ jN.jE;
        jN.iq = jN.jE ^ jN.iq;
        jN.jA = jN.jA & (-1 ^ jN.iq);
        jN.jA = jN.jw ^ jN.jA;
        jN.jw = jN.iq | jN.hI;
        jN.jw = jN.iu ^ jN.jw;
        jN.jw = jN.hR & (-1 ^ jN.jw);
        jN.jw = jN.fY ^ jN.jw;
        jN.jw = jN.jw | jN.hD;
        jN.hQ = jN.hQ & (-1 ^ jN.iq);
        jN.hQ = jN.iH ^ jN.hQ;
        jN.hQ = jN.hR & (-1 ^ jN.hQ);
        jN.iL = jN.iq & (-1 ^ jN.iL);
        jN.iL = jN.jg ^ jN.iL;
        jN.iH = jN.hI & (-1 ^ jN.iq);
        jN.iH = jN.jg ^ jN.iH;
        jN.hM = jN.iq | jN.hM;
        jN.hM = jN.jx ^ jN.hM;
        jN.hM = jN.hR & (-1 ^ jN.hM);
        jN.hM = jN.iL ^ jN.hM;
        jN.gU = jN.iq | jN.gU;
        jN.gU = jN.gP ^ jN.gU;
        jN.gU = jN.gU & jN.hR;
        jN.gU = jN.iH ^ jN.gU;
        jN.gU = jN.gU | jN.hD;
        jN.gU = jN.hM ^ jN.gU;
        jN.iB = jN.gU ^ jN.iB;
        jN.gU = jN.iB & (-1 ^ jN.gV);
        jN.gU = jN.hF ^ jN.gU;
        jN.gU = jN.gm & jN.gU;
        jN.hM = jN.iB & (-1 ^ jN.gV);
        jN.hM = jN.gV ^ jN.hM;
        jN.gU = jN.hM ^ jN.gU;
        jN.iH = jN.iB & jN.hg;
        jN.iH = jN.hu ^ jN.iH;
        jN.hK = jN.iH ^ jN.hK;
        jN.gP = jN.iB & (-1 ^ jN.hg);
        jN.iL = jN.gP & (-1 ^ jN.gm);
        jN.iL = jN.iH ^ jN.iL;
        jN.gP = jN.gm | jN.gP;
        jN.gP = jN.hM ^ jN.gP;
        jN.hM = jN.iB & (-1 ^ jN.io);
        jN.hM = jN.io ^ jN.hM;
        jN.hM = jN.hM | jN.gm;
        jN.iH = jN.iB & jN.io;
        jN.iH = jN.gg ^ jN.iH;
        jN.jx = jN.iB & jN.gV;
        jN.jx = jN.hu ^ jN.jx;
        jN.hu = jN.iB & (-1 ^ jN.hk);
        jN.hu = jN.hk ^ jN.hu;
        jN.jg = jN.iB & (-1 ^ jN.hk);
        jN.jg = jN.jg & (-1 ^ jN.gm);
        jN.jg = jN.hu ^ jN.jg;
        jN.hu = jN.iB & jN.hg;
        jN.fY = jN.iB & jN.hF;
        jN.fY = jN.hk ^ jN.fY;
        jN.iu = jN.iB & (-1 ^ jN.hg);
        jN.iu = jN.hF ^ jN.iu;
        jN.jE = jN.iB & jN.gg;
        jN.iA = jN.iB & jN.hk;
        jN.iA = jN.gg ^ jN.iA;
        jN.iA = jN.iA & (-1 ^ jN.gm);
        jN.iA = jN.jx ^ jN.iA;
        jN.jx = jN.gV ^ jN.iB;
        jN.jx = jN.jx ^ jN.gm;
        jN.iv = jN.iv ^ jN.iB;
        jN.iv = jN.gm & (-1 ^ jN.iv);
        jN.iv = jN.hu ^ jN.iv;
        jN.io = jN.iB & (-1 ^ jN.io);
        jN.io = jN.gm | jN.io;
        jN.io = jN.fY ^ jN.io;
        jN.hg = jN.iB & jN.hg;
        jN.hg = jN.hk ^ jN.hg;
        jN.hg = jN.gm & jN.hg;
        jN.hg = jN.hu ^ jN.hg;
        jN.hI = jN.hI ^ jN.iq;
        jN.hQ = jN.hI ^ jN.hQ;
        jN.hI = jN.iq | jN.jy;
        jN.hI = jN.gq ^ jN.hI;
        jN.iK = jN.hI ^ jN.iK;
        jN.iK = jN.iK & (-1 ^ jN.hD);
        jN.hI = jN.jz & (-1 ^ jN.iq);
        jN.hI = jN.jB ^ jN.hI;
        jN.jh = jN.hI ^ jN.jh;
        jN.hI = -1 ^ jN.jh;
        jN.gq = jN.hH & jN.iq;
        jN.gq = jN.hd ^ jN.gq;
        jN.gG = jN.gq ^ jN.gG;
        jN.gq = jN.gG & (-1 ^ jN.it);
        jN.fY = jN.it & (-1 ^ jN.gG);
        jN.gg = jN.gG & jN.it;
        jN.ji = jN.it & (-1 ^ jN.gg);
        jN.is = jN.gG ^ jN.it;
        jN.gp = jN.is & jN.gO;
        jN.jl = -1 ^ jN.gG;
        jN.hP = jN.gG | jN.it;
        jN.ju = jN.hP & (-1 ^ jN.it);
        jN.ja = jN.iq | jN.ja;
        jN.ja = jN.ij ^ jN.ja;
        jN.ja = jN.ja & jN.hR;
        jN.jz = jN.iq & (-1 ^ jN.jz);
        jN.jz = jN.jB ^ jN.jz;
        jN.ic = jN.jz ^ jN.ic;
        jN.jz = jN.ic & jN.ir;
        jN.jB = jN.ic & (-1 ^ jN.hN);
        jN.jj = jN.iq | jN.jj;
        jN.jj = jN.jf ^ jN.jj;
        jN.iN = jN.jj ^ jN.iN;
        jN.iK = jN.iN ^ jN.iK;
        jN.gs = jN.iK ^ jN.gs;
        jN.gr = jN.gr & jN.iq;
        jN.gr = jN.jy ^ jN.gr;
        jN.gr = jN.hR & (-1 ^ jN.gr);
        jN.gr = jN.jA ^ jN.gr;
        jN.gr = jN.gr & (-1 ^ jN.hD);
        jN.gr = jN.hQ ^ jN.gr;
        jN.iE = jN.gr ^ jN.iE;
        jN.gr = jN.iE & jN.gw;
        jN.jf = jN.jf & (-1 ^ jN.iq);
        jN.jf = jN.hq ^ jN.jf;
        jN.ja = jN.jf ^ jN.ja;
        jN.jw = jN.ja ^ jN.jw;
        jN.jw = jN.jw ^ jN.gS;
        jN.ja = jN.jw | jN.gQ;
        jN.jf = jN.jw & jN.gQ;
        jN.hq = -1 ^ jN.jw;
        jN.hH = jN.iq | jN.hH;
        jN.hH = jN.hd ^ jN.hH;
        jN.iS = jN.hH ^ jN.iS;
        jN.hH = -1 ^ jN.iS;
        jN.jq = jN.gK & jN.jq;
        jN.jq = jN.hT ^ jN.jq;
        jN.jq = jN.hw & jN.jq;
        jN.gS = jN.gK & (-1 ^ jN.gS);
        jN.gS = jN.gA ^ jN.gS;
        jN.jq = jN.gS ^ jN.jq;
        jN.jq = jN.gC | jN.jq;
        jN.gF = jN.gS ^ jN.gF;
        jN.jq = jN.gF ^ jN.jq;
        jN.jq = jN.ha & (-1 ^ jN.jq);
        jN.jq = jN.he ^ jN.jq;
        jN.gh = jN.jq ^ jN.gh;
        jN.jq = jN.gh | jN.hJ;
        jN.he = jN.ik | jN.jq;
        jN.he = jN.gh ^ jN.he;
        jN.gF = jN.jq & jN.ih;
        jN.jq = jN.ih & (-1 ^ jN.jq);
        jN.jq = jN.he ^ jN.jq;
        jN.he = jN.hJ & (-1 ^ jN.gh);
        jN.gA = jN.hJ & (-1 ^ jN.he);
        jN.iC = jN.he ^ jN.iC;
        jN.iC = jN.iC & (-1 ^ jN.ih);
        jN.iC = jN.jb ^ jN.iC;
        jN.in = jN.he ^ jN.in;
        jN.gK = jN.gh & (-1 ^ jN.hJ);
        jN.hw = jN.ih & (-1 ^ jN.gK);
        jN.hw = jN.in ^ jN.hw;
        jN.gH = jN.hw ^ jN.gH;
        jN.iW = jN.gK ^ jN.iW;
        jN.iW = jN.ih & (-1 ^ jN.iW);
        jN.iW = jN.gA ^ jN.iW;
        jN.iW = jN.iD & (-1 ^ jN.iW);
        jN.gA = jN.hJ | jN.gK;
        jN.hw = jN.gh ^ jN.hJ;
        jN.in = jN.hw & (-1 ^ jN.ik);
        jN.in = jN.hw ^ jN.in;
        jN.in = jN.in & jN.ih;
        jN.in = jN.jb ^ jN.in;
        jN.in = jN.iD & (-1 ^ jN.in);
        jN.in = jN.jq ^ jN.in;
        jN.in = jN.hj | jN.in;
        jN.ie = jN.hw ^ jN.ie;
        jN.jq = jN.ie & (-1 ^ jN.ih);
        jN.jq = jN.ie ^ jN.jq;
        jN.jb = jN.ie & jN.ih;
        jN.ie = jN.ih & (-1 ^ jN.ie);
        jN.ie = jN.he ^ jN.ie;
        jN.he = jN.ik | jN.hw;
        jN.he = jN.gK ^ jN.he;
        jN.he = jN.ih & (-1 ^ jN.he);
        jN.gK = jN.hw ^ jN.ik;
        jN.gF = jN.gK ^ jN.gF;
        jN.iW = jN.gF ^ jN.iW;
        jN.in = jN.iW ^ jN.in;
        jN.gy = jN.in ^ jN.gy;
        jN.in = jN.iE & (-1 ^ jN.gy);
        jN.gF = jN.gy & jN.hP;
        jN.gK = jN.gy & jN.gq;
        jN.gK = jN.ju ^ jN.gK;
        jN.hT = jN.gy & jN.gG;
        jN.hT = jN.is ^ jN.hT;
        jN.hT = jN.hT & jN.gO;
        jN.hd = jN.gy ^ jN.iE;
        jN.hQ = jN.gy & jN.it;
        jN.hQ = jN.gG ^ jN.hQ;
        jN.jA = jN.hQ & (-1 ^ jN.gO);
        jN.jy = jN.gy & (-1 ^ jN.gG);
        jN.jy = jN.gq ^ jN.jy;
        jN.jy = jN.jy & (-1 ^ jN.gO);
        jN.ju = jN.gy & (-1 ^ jN.ju);
        jN.ju = jN.fY ^ jN.ju;
        jN.ju = jN.ju & (-1 ^ jN.gO);
        jN.gq = jN.gy & (-1 ^ jN.gg);
        jN.gq = jN.hP ^ jN.gq;
        jN.iK = jN.gy & jN.it;
        jN.jA = jN.iK ^ jN.jA;
        jN.iK = jN.gy & (-1 ^ jN.ji);
        jN.iK = jN.is ^ jN.iK;
        jN.iN = jN.gO | jN.iK;
        jN.jj = jN.gy & (-1 ^ jN.gG);
        jN.jj = jN.it ^ jN.jj;
        jN.ij = jN.gO | jN.jj;
        jN.jj = jN.gO | jN.jj;
        jN.fY = jN.gy & jN.fY;
        jN.fY = jN.gO | jN.fY;
        jN.js = jN.gy & jN.iE;
        jN.hr = jN.iE & (-1 ^ jN.js);
        jN.hY = jN.hZ & jN.js;
        jN.iw = jN.gy | jN.iE;
        jN.hV = jN.iw & (-1 ^ jN.iE);
        jN.iI = jN.gy & (-1 ^ jN.iE);
        jN.iQ = jN.hZ & jN.iI;
        jN.iX = jN.hZ & jN.iI;
        jN.iZ = jN.gy & (-1 ^ jN.ji);
        jN.iZ = jN.hP ^ jN.iZ;
        jN.fY = jN.iZ ^ jN.fY;
        jN.iN = jN.iZ ^ jN.iN;
        jN.iZ = jN.gy & (-1 ^ jN.hP);
        jN.iZ = jN.hP ^ jN.iZ;
        jN.iZ = jN.gO | jN.iZ;
        jN.iZ = jN.gF ^ jN.iZ;
        jN.gF = jN.gy & (-1 ^ jN.ji);
        jN.hs = jN.gO | jN.gF;
        jN.hs = jN.iK ^ jN.hs;
        jN.ij = jN.gF ^ jN.ij;
        jN.gF = jN.gy & (-1 ^ jN.is);
        jN.iK = jN.gF & (-1 ^ jN.gO);
        jN.iK = jN.gq ^ jN.iK;
        jN.gp = jN.gF ^ jN.gp;
        jN.gF = jN.gy & (-1 ^ jN.it);
        jN.gF = jN.it ^ jN.gF;
        jN.gF = jN.gF & (-1 ^ jN.gO);
        jN.gF = jN.hQ ^ jN.gF;
        jN.hQ = jN.gy & (-1 ^ jN.gG);
        jN.hQ = jN.is ^ jN.hQ;
        jN.jj = jN.hQ ^ jN.jj;
        jN.gO = jN.hQ & (-1 ^ jN.gO);
        jN.gO = jN.gK ^ jN.gO;
        jN.ju = jN.hQ ^ jN.ju;
        jN.hP = jN.gy & jN.hP;
        jN.hP = jN.gg ^ jN.hP;
        jN.iP = jN.hP ^ jN.iP;
        jN.hP = jN.gh & (-1 ^ jN.ik);
        jN.gg = jN.hP & jN.ih;
        jN.gg = jN.iD & jN.gg;
        jN.gg = jN.jq ^ jN.gg;
        jN.gg = jN.hj | jN.gg;
        jN.jq = jN.gh & jN.hJ;
        jN.gI = jN.jq ^ jN.gI;
        jN.gI = jN.gI & jN.ih;
        jN.jq = jN.gh & (-1 ^ jN.ik);
        jN.jq = jN.hw ^ jN.jq;
        jN.he = jN.jq ^ jN.he;
        jN.he = jN.iD & (-1 ^ jN.he);
        jN.he = jN.ie ^ jN.he;
        jN.he = jN.hj & jN.he;
        jN.he = jN.iW ^ jN.he;
        jN.gC = jN.he ^ jN.gC;
        jN.he = jN.gQ ^ jN.gC;
        jN.iW = jN.gQ & jN.gC;
        jN.ie = jN.gQ & (-1 ^ jN.gC);
        jN.jq = jN.gC | jN.ie;
        jN.jq = jN.jq & (-1 ^ jN.jw);
        jN.hw = jN.gC & (-1 ^ jN.gQ);
        jN.hQ = jN.gC & (-1 ^ jN.hw);
        jN.gK = jN.hQ & (-1 ^ jN.jw);
        jN.is = jN.jw | jN.hQ;
        jN.gq = jN.gQ | jN.gC;
        jN.ig = -1 ^ jN.gC;
        jN.hp = jN.ik | jN.gh;
        jN.hp = jN.gA ^ jN.hp;
        jN.gA = jN.ih | jN.hp;
        jN.gA = jN.hP ^ jN.gA;
        jN.gA = jN.iD & jN.gA;
        jN.gA = jN.iC ^ jN.gA;
        jN.gg = jN.gA ^ jN.gg;
        jN.gM = jN.gg ^ jN.gM;
        jN.gg = -1 ^ jN.gM;
        jN.gI = jN.hp ^ jN.gI;
        jN.gI = jN.iD & jN.gI;
        jN.jb = jN.hp ^ jN.jb;
        jN.gI = jN.jb ^ jN.gI;
        jN.gI = jN.gI & (-1 ^ jN.hj);
        jN.gI = jN.gH ^ jN.gI;
        jN.gk = jN.gI ^ jN.gk;
        jN.gI = jN.gs & (-1 ^ jN.gk);
        jN.gH = jN.gI & (-1 ^ jN.ir);
        jN.jb = jN.gk ^ jN.gs;
        jN.hp = jN.gk & (-1 ^ jN.gs);
        jN.gA = jN.hp | jN.gs;
        jN.iC = -1 ^ jN.gk;
        jN.hP = jN.gs & jN.gk;
        jN.hL = jN.gS ^ jN.hL;
        jN.hO = jN.hL ^ jN.hO;
        jN.hO = jN.ha & jN.hO;
        jN.hO = jN.ht ^ jN.hO;
        jN.hh = jN.hO ^ jN.hh;
        jN.hO = jN.hh & (-1 ^ jN.hU);
        jN.hO = jN.iF ^ jN.hO;
        jN.ht = jN.hh & (-1 ^ jN.gL);
        jN.ht = jN.fZ ^ jN.ht;
        jN.ht = jN.ht & (-1 ^ jN.gn);
        jN.ht = jN.hB ^ jN.ht;
        jN.hL = jN.hh & (-1 ^ jN.gL);
        jN.hL = jN.gL ^ jN.hL;
        jN.hL = jN.gD | jN.hL;
        jN.gS = jN.hh & jN.iF;
        jN.gS = jN.gL ^ jN.gS;
        jN.hS = jN.hh & jN.jo;
        jN.hS = jN.iY ^ jN.hS;
        jN.ii = jN.hS ^ jN.ii;
        jN.hS = jN.hh & (-1 ^ jN.iF);
        jN.hS = jN.fZ ^ jN.hS;
        jN.ho = jN.hh & jN.jo;
        jN.hL = jN.ho ^ jN.hL;
        jN.hL = jN.gv & jN.hL;
        jN.ho = jN.hh & (-1 ^ jN.jD);
        jN.ho = jN.gn | jN.ho;
        jN.ho = jN.gS ^ jN.ho;
        jN.gS = jN.hh & (-1 ^ jN.hU);
        jN.gS = jN.iY ^ jN.gS;
        jN.gl = jN.hh & jN.jo;
        jN.gl = jN.hU ^ jN.gl;
        jN.gl = jN.gD | jN.gl;
        jN.iU = jN.hh & (-1 ^ jN.iU);
        jN.iU = jN.iz ^ jN.iU;
        jN.jc = jN.iU ^ jN.jc;
        jN.iU = jN.jc & jN.hG;
        jN.iU = jN.iU & (-1 ^ jN.gw);
        jN.iU = jN.hG ^ jN.iU;
        jN.ge = jN.iU ^ jN.ge;
        jN.iz = jN.hG & (-1 ^ jN.jc);
        jN.iz = jN.iz & (-1 ^ jN.gw);
        jN.iz = jN.hG ^ jN.iz;
        jN.iG = jN.jc & (-1 ^ jN.hG);
        jN.il = jN.iG & (-1 ^ jN.gw);
        jN.il = jN.iG ^ jN.il;
        jN.ix = jN.il ^ jN.ix;
        jN.il = jN.gw | jN.iG;
        jN.il = jN.hG ^ jN.il;
        jN.iG = jN.gw | jN.iG;
        jN.hz = jN.jc | jN.hG;
        jN.hz = jN.hz ^ jN.gw;
        jN.hz = jN.hz & jN.gQ;
        jN.ia = jN.jc & (-1 ^ jN.gw);
        jN.ia = jN.hG ^ jN.ia;
        jN.iV = jN.gQ | jN.ia;
        jN.iV = jN.iU ^ jN.iV;
        jN.iU = jN.ia & (-1 ^ jN.gQ);
        jN.iU = jN.hG ^ jN.iU;
        jN.hz = jN.ia ^ jN.hz;
        jN.ia = -1 ^ jN.jc;
        jN.hA = jN.jc ^ jN.hG;
        jN.jC = jN.hA ^ jN.jC;
        jN.hf = jN.gQ | jN.jC;
        jN.hf = jN.iG ^ jN.hf;
        jN.jC = jN.jC & (-1 ^ jN.gQ);
        jN.iG = jN.gw | jN.hA;
        jN.iG = jN.jc ^ jN.iG;
        jN.go = jN.gQ | jN.iG;
        jN.go = jN.hG ^ jN.go;
        jN.iG = jN.gQ | jN.iG;
        jN.iG = jN.il ^ jN.iG;
        jN.il = jN.gw | jN.hA;
        jN.il = jN.hG ^ jN.il;
        jN.hG = jN.il & (-1 ^ jN.gQ);
        jN.hG = jN.il ^ jN.hG;
        jN.il = jN.hA ^ jN.gw;
        jN.gb = jN.il ^ jN.gb;
        jN.il = jN.hA & (-1 ^ jN.gw);
        jN.il = jN.jc ^ jN.il;
        jN.jC = jN.il ^ jN.jC;
        jN.hA = jN.hA & jN.gQ;
        jN.hA = jN.hm ^ jN.hA;
        jN.jc = jN.jc & (-1 ^ jN.gQ);
        jN.jc = jN.iz ^ jN.jc;
        jN.iR = jN.hh & (-1 ^ jN.iR);
        jN.iR = jN.jp ^ jN.iR;
        jN.ga = jN.iR ^ jN.ga;
        jN.iR = jN.hZ & jN.ga;
        jN.iR = jN.hd ^ jN.iR;
        jN.jp = jN.iE & jN.ga;
        jN.hV = jN.ga | jN.hV;
        jN.hV = jN.in ^ jN.hV;
        jN.iz = jN.ga & (-1 ^ jN.gw);
        jN.hm = jN.iz ^ jN.iE;
        jN.il = jN.iE & jN.iz;
        jN.il = jN.iz ^ jN.il;
        jN.il = jN.il & jN.iS;
        jN.iz = jN.iw & (-1 ^ jN.ga);
        jN.iz = jN.iI ^ jN.iz;
        jN.hv = jN.ga | jN.hr;
        jN.hv = jN.gi & (-1 ^ jN.hv);
        jN.js = jN.js ^ jN.ga;
        jN.hY = jN.js ^ jN.hY;
        jN.hY = jN.gi & (-1 ^ jN.hY);
        jN.hY = jN.iR ^ jN.hY;
        jN.iR = jN.ga | jN.iw;
        jN.iR = jN.iw ^ jN.iR;
        jN.iQ = jN.iR ^ jN.iQ;
        jN.iQ = jN.gi & jN.iQ;
        jN.js = jN.ga | jN.gw;
        jN.jm = jN.iE & (-1 ^ jN.js);
        jN.jm = jN.js ^ jN.jm;
        jN.jm = jN.iS | jN.jm;
        jN.il = jN.js ^ jN.il;
        jN.ip = jN.iE & jN.js;
        jN.hX = jN.iS | jN.js;
        jN.gx = jN.iE & jN.js;
        jN.gx = jN.gw ^ jN.gx;
        jN.jm = jN.gx ^ jN.jm;
        jN.jm = jN.jm & (-1 ^ jN.gM);
        jN.js = jN.js & (-1 ^ jN.gw);
        jN.gx = jN.in & (-1 ^ jN.ga);
        jN.gx = jN.hd ^ jN.gx;
        jN.iO = jN.hZ & (-1 ^ jN.gx);
        jN.hx = jN.hZ & jN.gx;
        jN.ib = -1 ^ jN.ga;
        jN.jF = jN.ga | jN.iw;
        jN.jF = jN.hr ^ jN.jF;
        jN.jF = jN.hZ & jN.jF;
        jN.jF = jN.hV ^ jN.jF;
        jN.hV = jN.ga | jN.iE;
        jN.hV = jN.iw ^ jN.hV;
        jN.jn = jN.hZ & (-1 ^ jN.hV);
        jN.hi = jN.gw & (-1 ^ jN.ga);
        jN.jG = jN.iE & jN.hi;
        jN.jG = jN.jG & (-1 ^ jN.iS);
        jN.jH = jN.iS | jN.hi;
        jN.jH = jN.hm ^ jN.jH;
        jN.hm = jN.iE & jN.hi;
        jN.hi = jN.hi & jN.iS;
        jN.jI = jN.iI & (-1 ^ jN.ga);
        jN.jI = jN.gi & (-1 ^ jN.jI);
        jN.jJ = jN.ga ^ jN.gw;
        jN.jK = jN.jJ & (-1 ^ jN.iS);
        jN.jL = jN.iE & (-1 ^ jN.jJ);
        jN.jL = jN.gw ^ jN.jL;
        jN.hi = jN.jL ^ jN.hi;
        jN.hi = jN.gM | jN.hi;
        jN.hi = jN.il ^ jN.hi;
        jN.hm = jN.jJ ^ jN.hm;
        jN.jK = jN.hm ^ jN.jK;
        jN.jK = jN.jK & (-1 ^ jN.gM);
        jN.hm = jN.iw & (-1 ^ jN.ga);
        jN.hm = jN.hd ^ jN.hm;
        jN.hm = jN.hZ & jN.hm;
        jN.hm = jN.gx ^ jN.hm;
        jN.hv = jN.hm ^ jN.hv;
        jN.hv = jN.gG | jN.hv;
        jN.hd = jN.hd & (-1 ^ jN.ga);
        jN.hx = jN.hd ^ jN.hx;
        jN.hr = jN.ga | jN.hr;
        jN.hr = jN.iE ^ jN.hr;
        jN.hm = jN.hr | jN.hZ;
        jN.hm = jN.gx ^ jN.hm;
        jN.jI = jN.hm ^ jN.jI;
        jN.hv = jN.jI ^ jN.hv;
        jN.gR = jN.hv ^ jN.gR;
        jN.iO = jN.hr ^ jN.iO;
        jN.iw = jN.iw & (-1 ^ jN.ga);
        jN.iw = jN.iE ^ jN.iw;
        jN.jn = jN.iw ^ jN.jn;
        jN.iw = jN.iw & (-1 ^ jN.hZ);
        jN.iw = jN.hV ^ jN.iw;
        jN.iQ = jN.iw ^ jN.iQ;
        jN.iQ = jN.gG | jN.iQ;
        jN.iw = jN.ga & jN.gw;
        jN.hV = jN.iS | jN.iw;
        jN.hV = jN.gr ^ jN.hV;
        jN.hV = jN.gM | jN.hV;
        jN.ip = jN.iw ^ jN.ip;
        jN.gr = jN.iE & jN.iw;
        jN.hr = jN.gM | jN.gr;
        jN.hV = jN.gr ^ jN.hV;
        jN.gr = jN.iE & (-1 ^ jN.iw);
        jN.gr = jN.ga ^ jN.gr;
        jN.gr = jN.gr & (-1 ^ jN.iS);
        jN.gr = jN.ip ^ jN.gr;
        jN.ip = jN.iE & (-1 ^ jN.iw);
        jN.ip = jN.iS | jN.ip;
        jN.ip = jN.jJ ^ jN.ip;
        jN.hr = jN.ip ^ jN.hr;
        jN.jp = jN.iw ^ jN.jp;
        jN.ip = jN.iE & (-1 ^ jN.iw);
        jN.ip = jN.gw ^ jN.ip;
        jN.jG = jN.ip ^ jN.jG;
        jN.jG = jN.jG & (-1 ^ jN.gM);
        jN.jG = jN.jH ^ jN.jG;
        jN.iw = jN.gw & (-1 ^ jN.iw);
        jN.iw = jN.iE & (-1 ^ jN.iw);
        jN.iw = jN.js ^ jN.iw;
        jN.iw = jN.iS & (-1 ^ jN.iw);
        jN.js = jN.iE & jN.ga;
        jN.js = jN.ga ^ jN.js;
        jN.js = jN.js & (-1 ^ jN.iS);
        jN.js = jN.jp ^ jN.js;
        jN.jp = jN.iE & (-1 ^ jN.ga);
        jN.jp = jN.iE ^ jN.jp;
        jN.gw = jN.jp & (-1 ^ jN.hZ);
        jN.gw = jN.iR ^ jN.gw;
        jN.gw = jN.gi & (-1 ^ jN.gw);
        jN.gw = jN.jF ^ jN.gw;
        jN.iQ = jN.gw ^ jN.iQ;
        jN.iQ = jN.iQ ^ jN.gL;
        jN.jp = jN.hZ & jN.jp;
        jN.jp = jN.iz ^ jN.jp;
        jN.jp = jN.gi & jN.jp;
        jN.jp = jN.jn ^ jN.jp;
        jN.jp = jN.jp & (-1 ^ jN.gG);
        jN.jp = jN.hY ^ jN.jp;
        jN.hc = jN.jp ^ jN.hc;
        jN.hc = -1 ^ jN.hc;
        jN.in = jN.in & (-1 ^ jN.ga);
        jN.in = jN.gy ^ jN.in;
        jN.iX = jN.in ^ jN.iX;
        jN.iX = jN.gi & (-1 ^ jN.iX);
        jN.iX = jN.iO ^ jN.iX;
        jN.iO = jN.iE & jN.ga;
        jN.iO = jN.jJ ^ jN.iO;
        jN.iw = jN.iO ^ jN.iw;
        jN.jm = jN.iw ^ jN.jm;
        jN.hX = jN.iO ^ jN.hX;
        jN.hX = jN.hX & (-1 ^ jN.gM);
        jN.hX = jN.js ^ jN.hX;
        jN.iS = jN.iO & (-1 ^ jN.iS);
        jN.iS = jN.jL ^ jN.iS;
        jN.iS = jN.gM | jN.iS;
        jN.iS = jN.gr ^ jN.iS;
        jN.ga = jN.iI ^ jN.ga;
        jN.ga = jN.hZ & (-1 ^ jN.ga);
        jN.ga = jN.hd ^ jN.ga;
        jN.ga = jN.gi & jN.ga;
        jN.ga = jN.hx ^ jN.ga;
        jN.ga = jN.gG | jN.ga;
        jN.ga = jN.iX ^ jN.ga;
        jN.iD = jN.ga ^ jN.iD;
        jN.iF = jN.hh & (-1 ^ jN.iF);
        jN.hB = jN.hh & (-1 ^ jN.hB);
        jN.hB = jN.gf ^ jN.hB;
        jN.hB = jN.gD | jN.hB;
        jN.hB = jN.hS ^ jN.hB;
        jN.hB = jN.gv & (-1 ^ jN.hB);
        jN.hS = jN.hh & (-1 ^ jN.gf);
        jN.ga = jN.hS & (-1 ^ jN.gn);
        jN.ga = jN.hh ^ jN.ga;
        jN.gl = jN.ga ^ jN.gl;
        jN.gl = jN.gv & (-1 ^ jN.gl);
        jN.hS = jN.gn | jN.hS;
        jN.fZ = jN.hh & jN.fZ;
        jN.fZ = jN.gL ^ jN.fZ;
        jN.fZ = jN.fZ & jN.gn;
        jN.gL = jN.hh & (-1 ^ jN.iY);
        jN.gL = jN.gf ^ jN.gL;
        jN.gL = jN.gn | jN.gL;
        jN.gL = jN.hO ^ jN.gL;
        jN.gL = jN.gD | jN.gL;
        jN.gL = jN.ho ^ jN.gL;
        jN.hB = jN.gL ^ jN.hB;
        jN.gW = jN.hB ^ jN.gW;
        jN.hB = -1 ^ jN.gW;
        jN.iY = jN.iY ^ jN.hh;
        jN.hS = jN.iY ^ jN.hS;
        jN._fldif = jN.hh & (-1 ^ jN._fldif);
        jN._fldif = jN.hy ^ jN._fldif;
        jN.gu = jN._fldif ^ jN.gu;
        jN.jE = jN.gu & (-1 ^ jN.jE);
        jN.jE = jN.iL ^ jN.jE;
        jN.hK = jN.gu & (-1 ^ jN.hK);
        jN.hK = jN.hM ^ jN.hK;
        jN.hK = jN.gC & jN.hK;
        jN.gU = jN.gu & jN.gU;
        jN.gU = jN.iv ^ jN.gU;
        jN.hK = jN.gU ^ jN.hK;
        jN.hb = jN.hK ^ jN.hb;
        jN.hb = -1 ^ jN.hb;
        jN.iu = jN.gu & jN.iu;
        jN.iu = jN.io ^ jN.iu;
        jN.hF = jN.gu & jN.hF;
        jN.hF = jN.gP ^ jN.hF;
        jN.hF = jN.gC & (-1 ^ jN.hF);
        jN.hF = jN.jE ^ jN.hF;
        jN.gB = jN.hF ^ jN.gB;
        jN.jg = jN.gu & (-1 ^ jN.jg);
        jN.jg = jN.jx ^ jN.jg;
        jN.hu = jN.gu & (-1 ^ jN.hu);
        jN.hu = jN.hg ^ jN.hu;
        jN.hu = jN.hu & jN.gC;
        jN.hu = jN.jg ^ jN.hu;
        jN.ih = jN.hu ^ jN.ih;
        jN.iH = jN.gu & jN.iH;
        jN.iH = jN.iA ^ jN.iH;
        jN.iH = jN.gC & (-1 ^ jN.iH);
        jN.iH = jN.iu ^ jN.iH;
        jN.gX = jN.iH ^ jN.gX;
        jN.gX = -1 ^ jN.gX;
        jN.hW = jN.hh & jN.hW;
        jN.hW = jN.gz ^ jN.hW;
        jN.gc = jN.hW ^ jN.gc;
        jN.iZ = jN.gc & (-1 ^ jN.iZ);
        jN.iZ = jN.hs ^ jN.iZ;
        jN.gp = jN.gc & jN.gp;
        jN.gp = jN.gO ^ jN.gp;
        jN.gp = jN.gW | jN.gp;
        jN.ij = jN.gc & (-1 ^ jN.ij);
        jN.ij = jN.iP ^ jN.ij;
        jN.ij = jN.ij & (-1 ^ jN.gW);
        jN.iN = jN.gc & (-1 ^ jN.iN);
        jN.iN = jN.ju ^ jN.iN;
        jN.gp = jN.iN ^ jN.gp;
        jN.gf = jN.gp ^ jN.gf;
        jN.gf = -1 ^ jN.gf;
        jN.hT = jN.gc & (-1 ^ jN.hT);
        jN.hT = jN.gF ^ jN.hT;
        jN.iK = jN.gc & jN.iK;
        jN.iK = jN.ji ^ jN.iK;
        jN.iK = jN.gW | jN.iK;
        jN.iK = jN.iZ ^ jN.iK;
        jN.je = jN.iK ^ jN.je;
        jN.je = -1 ^ jN.je;
        jN.jy = jN.gc & (-1 ^ jN.jy);
        jN.jy = jN.fY ^ jN.jy;
        jN.ij = jN.jy ^ jN.ij;
        jN.gT = jN.ij ^ jN.gT;
        jN.jA = jN.gc & jN.jA;
        jN.jA = jN.jj ^ jN.jA;
        jN.jA = jN.gW | jN.jA;
        jN.jA = jN.hT ^ jN.jA;
        jN.gt = jN.jA ^ jN.gt;
        jN.jA = jN.hh & (-1 ^ jN.hU);
        jN.jA = jN.hU ^ jN.jA;
        jN.jt = jN.jA ^ jN.jt;
        jN.jt = jN.jt & (-1 ^ jN.gD);
        jN.jt = jN.ht ^ jN.jt;
        jN.jt = jN.gv & (-1 ^ jN.jt);
        jN.fZ = jN.jA ^ jN.fZ;
        jN.fZ = jN.gD | jN.fZ;
        jN.fZ = jN.ii ^ jN.fZ;
        jN.jt = jN.fZ ^ jN.jt;
        jN.ha = jN.jt ^ jN.ha;
        jN.jt = jN.gQ ^ jN.ha;
        jN.fZ = jN.ha | jN.gq;
        jN.fZ = jN.he ^ jN.fZ;
        jN.fZ = jN.jw | jN.fZ;
        jN.ii = jN.ha | jN.gC;
        jN.ii = jN.he ^ jN.ii;
        jN.gK = jN.ii ^ jN.gK;
        jN.ii = jN.hw & (-1 ^ jN.ha);
        jN.jq = jN.ii ^ jN.jq;
        jN.jq = jN.jh | jN.jq;
        jN.ht = jN.ha | jN.hw;
        jN.ht = jN.gC ^ jN.ht;
        jN.hU = jN.he & (-1 ^ jN.ha);
        jN.gq = jN.ha | jN.gq;
        jN.gq = jN.gQ ^ jN.gq;
        jN.hT = jN.jw | jN.gq;
        jN.ja = jN.gq ^ jN.ja;
        jN.ja = jN.jh | jN.ja;
        jN.gW = jN.iW & (-1 ^ jN.ha);
        jN.gW = jN.gW & (-1 ^ jN.jw);
        jN.gW = jN.jh | jN.gW;
        jN.jj = jN.ha | jN.gC;
        jN.jj = jN.hQ ^ jN.jj;
        jN.hQ = jN.hw & (-1 ^ jN.ha);
        jN.hQ = jN.hw ^ jN.hQ;
        jN.hQ = jN.jw | jN.hQ;
        jN.ij = jN.gC & (-1 ^ jN.ha);
        jN.hQ = jN.ij ^ jN.hQ;
        jN.ij = jN.ha | jN.gC;
        jN.ij = jN.gC ^ jN.ij;
        jN.ij = jN.ij & (-1 ^ jN.jw);
        jN.ij = jN.hU ^ jN.ij;
        jN.hU = jN.ie & (-1 ^ jN.ha);
        jN.hU = jN.ie ^ jN.hU;
        jN.jy = jN.jw | jN.hU;
        jN.jy = jN.jt ^ jN.jy;
        jN.jt = jN.gQ & (-1 ^ jN.ha);
        jN.jt = jN.gC ^ jN.jt;
        jN.fZ = jN.jt ^ jN.fZ;
        jN.gW = jN.fZ ^ jN.gW;
        jN.jt = jN.jw & jN.jt;
        jN.jt = jN.ii ^ jN.jt;
        jN.ja = jN.jt ^ jN.ja;
        jN.ja = jN.ja & (-1 ^ jN.gV);
        jN.gQ = jN.ha | jN.gQ;
        jN.gQ = jN.hw ^ jN.gQ;
        jN.hT = jN.gQ ^ jN.hT;
        jN.gQ = jN.ha | jN.hw;
        jN.gQ = jN.gQ & (-1 ^ jN.jw);
        jN.gQ = jN.gq ^ jN.gQ;
        jN.gQ = jN.jh | jN.gQ;
        jN.gQ = jN.ij ^ jN.gQ;
        jN.ja = jN.gQ ^ jN.ja;
        jN.ja = jN.ja ^ jN.hh;
        jN.ja = -1 ^ jN.ja;
        jN.gQ = -1 ^ jN.ha;
        jN.ij = jN.hw & (-1 ^ jN.ha);
        jN.ij = jN.iW ^ jN.ij;
        jN.iW = jN.jw | jN.ij;
        jN.iW = jN.hU ^ jN.iW;
        jN.iW = jN.iW & (-1 ^ jN.jh);
        jN.iW = jN.gK ^ jN.iW;
        jN.ij = jN.ij & (-1 ^ jN.jw);
        jN.ij = jN.jj ^ jN.ij;
        jN.ij = jN.ij & (-1 ^ jN.jh);
        jN.ij = jN.jy ^ jN.ij;
        jN.jy = jN.ie & (-1 ^ jN.ha);
        jN.jy = jN.he ^ jN.jy;
        jN.jf = jN.jy ^ jN.jf;
        jN.jf = jN.jh | jN.jf;
        jN.jf = jN.hQ ^ jN.jf;
        jN.jf = jN.gV | jN.jf;
        jN.jf = jN.iW ^ jN.jf;
        jN.gh = jN.jf ^ jN.gh;
        jN.jw = jN.jy & (-1 ^ jN.jw);
        jN.jw = jN.ht ^ jN.jw;
        jN.jq = jN.jw ^ jN.jq;
        jN.jq = jN.jq & (-1 ^ jN.gV);
        jN.jq = jN.ij ^ jN.jq;
        jN.iq = jN.jq ^ jN.iq;
        jN.hw = jN.ha | jN.hw;
        jN.hw = jN.ie ^ jN.hw;
        jN.is = jN.hw ^ jN.is;
        jN.is = jN.is & (-1 ^ jN.jh);
        jN.is = jN.hT ^ jN.is;
        jN.is = jN.gV | jN.is;
        jN.is = jN.gW ^ jN.is;
        jN.iy = jN.is ^ jN.iy;
        jN.iy = -1 ^ jN.iy;
        jN.jA = jN.gn | jN.jA;
        jN.jA = jN.jA & (-1 ^ jN.gD);
        jN.jA = jN.hS ^ jN.jA;
        jN.gl = jN.jA ^ jN.gl;
        jN.gE = jN.gl ^ jN.gE;
        jN.jK = jN.gE | jN.jK;
        jN.jK = jN.iS ^ jN.jK;
        jN.gJ = jN.jK ^ jN.gJ;
        jN.iG = jN.gE & (-1 ^ jN.iG);
        jN.iG = jN.ge ^ jN.iG;
        jN.iG = jN.jh & (-1 ^ jN.iG);
        jN.iV = jN.gE & jN.iV;
        jN.iV = jN.hz ^ jN.iV;
        jN.iV = jN.iV & (-1 ^ jN.jh);
        jN.jc = jN.gE & jN.jc;
        jN.jc = jN.jh | jN.jc;
        jN.hX = jN.hX | jN.gE;
        jN.hX = jN.jm ^ jN.hX;
        jN.hJ = jN.hX ^ jN.hJ;
        jN.hG = jN.gE & (-1 ^ jN.hG);
        jN.hG = jN.gb ^ jN.hG;
        jN.iV = jN.hG ^ jN.iV;
        jN.hD = jN.iV ^ jN.hD;
        jN.hD = -1 ^ jN.hD;
        jN.iG = jN.hG ^ jN.iG;
        jN.iG = jN.iG ^ jN.gD;
        jN.iG = -1 ^ jN.iG;
        jN.hf = jN.gE & jN.hf;
        jN.hf = jN.jC ^ jN.hf;
        jN.jc = jN.hf ^ jN.jc;
        jN.gN = jN.jc ^ jN.gN;
        jN.hi = jN.hi & (-1 ^ jN.gE);
        jN.hi = jN.jG ^ jN.hi;
        jN.hn = jN.hi ^ jN.hn;
        jN.go = jN.gE & (-1 ^ jN.go);
        jN.go = jN.ix ^ jN.go;
        jN.jh = jN.go & (-1 ^ jN.jh);
        jN.iU = jN.gE & (-1 ^ jN.iU);
        jN.iU = jN.hA ^ jN.iU;
        jN.jh = jN.iU ^ jN.jh;
        jN.hE = jN.jh ^ jN.hE;
        jN.hV = jN.hV & (-1 ^ jN.gE);
        jN.hV = jN.hr ^ jN.hV;
        jN.hV = jN.hV ^ jN.gn;
        jN.hV = -1 ^ jN.hV;
        jN.jo = jN.hh & jN.jo;
        jN.jo = jN.jD ^ jN.jo;
        jN.jD = jN.jo & (-1 ^ jN.gn);
        jN.jD = jN.iF ^ jN.jD;
        jN.gD = jN.jD & (-1 ^ jN.gD);
        jN.jo = jN.gn | jN.jo;
        jN.jo = jN.gS ^ jN.jo;
        jN.gD = jN.jo ^ jN.gD;
        jN.hL = jN.gD ^ jN.hL;
        jN.iJ = jN.hL ^ jN.iJ;
        jN.hL = jN.ic & (-1 ^ jN.iJ);
        jN.hP = jN.hP & (-1 ^ jN.iJ);
        jN.hP = jN.gs ^ jN.hP;
        jN.hP = jN.hP & jN.ir;
        jN.gD = jN.hN & jN.iJ;
        jN.jo = jN.gD & (-1 ^ jN.ir);
        jN.jo = jN.ic & jN.jo;
        jN.jo = jN.iB & (-1 ^ jN.jo);
        jN.gS = jN.gA & (-1 ^ jN.iJ);
        jN.gS = jN.gs ^ jN.gS;
        jN.gn = jN.ir | jN.iJ;
        jN.jD = jN.gn & (-1 ^ jN.ic);
        jN.gn = jN.gn | jN.ic;
        jN.iF = jN.gs & (-1 ^ jN.iJ);
        jN.iF = jN.hp ^ jN.iF;
        jN.iF = jN.ir & (-1 ^ jN.iF);
        jN.hh = jN.gk & (-1 ^ jN.iJ);
        jN.hh = jN.gs ^ jN.hh;
        jN.hh = jN.ir | jN.hh;
        jN.hh = jN.jb ^ jN.hh;
        jN.hh = jN.gc & (-1 ^ jN.hh);
        jN.hr = jN.iJ | jN.gs;
        jN.hr = jN.jb ^ jN.hr;
        jN.jh = jN.hr & (-1 ^ jN.ir);
        jN.jh = jN.gS ^ jN.jh;
        jN.hP = jN.hr ^ jN.hP;
        jN.hh = jN.hP ^ jN.hh;
        jN.hP = jN.hN & (-1 ^ jN.iJ);
        jN.hr = jN.hP & (-1 ^ jN.ir);
        jN.hr = jN.gD ^ jN.hr;
        jN.hL = jN.hr ^ jN.hL;
        jN.hr = jN.ic & jN.hP;
        jN.gS = jN.hP & (-1 ^ jN.ir);
        jN.jB = jN.gS ^ jN.jB;
        jN.jB = jN.iB & jN.jB;
        jN.jB = jN.jD ^ jN.jB;
        jN.jB = jN.jB & (-1 ^ jN.hk);
        jN.hP = jN.hP ^ jN.ir;
        jN.hP = jN.hP ^ jN.ic;
        jN.jD = jN.iJ | jN.jb;
        jN.jD = jN.gs ^ jN.jD;
        jN.gS = jN.iJ | jN.gs;
        jN.gS = jN.gI ^ jN.gS;
        jN.gS = jN.gS & (-1 ^ jN.ir);
        jN.iU = jN.iJ | jN.gk;
        jN.iU = jN.gs ^ jN.iU;
        jN.iU = jN.ir & (-1 ^ jN.iU);
        jN.iU = jN.jb ^ jN.iU;
        jN.iU = jN.gc & jN.iU;
        jN.hA = jN.iJ & (-1 ^ jN.ir);
        jN.hA = jN.gD ^ jN.hA;
        jN.gI = jN.gI & (-1 ^ jN.iJ);
        jN.gI = jN.ir | jN.gI;
        jN.gI = jN.jD ^ jN.gI;
        jN.iU = jN.gI ^ jN.iU;
        jN.jb = jN.iJ | jN.jb;
        jN.jb = jN.gk ^ jN.jb;
        jN.gS = jN.jb ^ jN.gS;
        jN.gS = jN.gc & jN.gS;
        jN.gS = jN.jh ^ jN.gS;
        jN.jh = jN.gS & jN.it;
        jN.jh = jN.hh ^ jN.jh;
        jN.gj = jN.jh ^ jN.gj;
        jN.gS = jN.it | jN.gS;
        jN.gS = jN.hh ^ jN.gS;
        jN.hR = jN.gS ^ jN.hR;
        jN.iF = jN.jb ^ jN.iF;
        jN.iF = jN.gc & (-1 ^ jN.iF);
        jN.jb = jN.ir | jN.iJ;
        jN.gS = -1 ^ jN.iJ;
    }

    private (f f1)
    {
        jN = f1;
        super();
    }

    jN(f f1, jN jn)
    {
        this(f1);
    }
}

// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.maps.viewlayer;

import java.util.Hashtable;
import java.util.Vector;
import jp.co.yahoo.android.maps.DoublePoint;

// Referenced classes of package jp.co.yahoo.android.maps.viewlayer:
//            Coordinate, LLCalculation

public class LayerInfo
{

    private static final Hashtable info;
    private static final String info_b1[] = {
        "1/188\t745\t3\t0/0/0,0/0/0\t0\t0\t-3\t0\t2097150\tyahoosplit\t-\t1\tpng\t2\t0\t0\t-1\t7\t0", "1/375\t1490\t3\t0/0/0,0/0/0\t0\t0\t-2\t0\t1048575\tyahoosplit\t-\t1\tpng\t2\t0\t0\t0\t10", "1/750\t2980\t3\t0/0/0,0/0/0\t0\t0\t-1\t0\t524287\tyahoosplit\t-\t1\tpng\t2\t0\t0\t1\t20", "1/1500\t5960\t3\t0/0/0,0/0/0\t0\t0\t0\t0\t262143\tyahoosplit\t-\t1\tpng\t2\t0\t0\t2\t50"
    };
    private static final String info_hybrid[] = {
        "1/188\t745\t3\t0/0/0,0/0/0\t0\t0\t-3\t0\t2097150\tyahoosplit\t-\t1\tpng\t2\t0\t0\t-1\t7\t0", "1/375\t1490\t3\t0/0/0,0/0/0\t0\t0\t-2\t0\t1049574\tyahoosplit\t-\t1\tpng\t2\t0\t0\t0\t10\t0", "1/750\t2980\t3\t0/0/0,0/0/0\t0\t0\t-1\t0\t524287\tyahoosplit\t-\t1\tgif\t2\t0\t0\t1\t20", "1/1500\t5960\t3\t0/0/0,0/0/0\t0\t0\t0\t0\t262143\tyahoosplit\t-\t1\tgif\t2\t0\t0\t2\t50", "1/3000\t11921\t3\t0/0/0,0/0/0\t0\t0\t1\t0\t131071\tyahoosplit\t-\t1\tgif\t2\t0\t0\t3\t100", "1/6000\t23842\t3\t0/0/0,0/0/0\t0\t0\t2\t0\t65535\tyahoosplit\t-\t1\tgif\t2\t0\t0\t4\t200", "1/12000\t47684\t3\t0/0/0,0/0/0\t0\t0\t3\t0\t32767\tyahoosplit\t-\t1\tgif\t2\t0\t0\t5\t400", "1/24000\t95367\t3\t0/0/0,0/0/0\t0\t0\t4\t0\t16383\tyahoosplit\t-\t1\tgif\t2\t0\t0\t6\t1000", "1/48000\t190735\t3\t0/0/0,0/0/0\t0\t0\t5\t0\t8191\tyahoosplit\t-\t1\tgif\t2\t0\t0\t7\t1000", "1/96000\t381469\t3\t0/0/0,0/0/0\t0\t0\t6\t0\t4095\tyahoosplit\t-\t1\tgif\t2\t0\t0\t8\t3000", 
        "1/19\u4E07\t762939\t3\t0/0/0,0/0/0\t0\t0\t7\t0\t2047\tyahoosplit\t-\t1\tgif\t2\t0\t0\t9\t7000", "1/38\u4E07\t1525877\t3\t0/0/0,0/0/0\t0\t0\t8\t0\t1023\tyahoosplit\t-\t1\tgif\t2\t0\t0\t10\t10000", "1/75\u4E07\t3051755\t3\t0/0/0,0/0/0\t0\t0\t9\t0\t511\tyahoosplit\t-\t1\tgif\t2\t0\t0\t11\t30000", "1/150\u4E07\t6103510\t3\t0/0/0,0/0/0\t0\t0\t10\t0\t255\tyahoosplit\t-\t1\tgif\t2\t84/59/55,0/0/0\t-84/59/57,0/0/0\t12\t60000", "1/300\u4E07\t12207019\t3\t0/0/0,0/0/0\t0\t0\t11\t0\t127\tyahoosplit\t-\t1\tgif\t2\t84/59/55,0/0/0\t-84/59/57,0/0/0\t13\t100000", "1/600\u4E07\t24414038\t3\t0/0/0,0/0/0\t0\t0\t12\t0\t63\tyahoosplit\t-\t1\tgif\t2\t84/59/55,0/0/0\t-84/59/57,0/0/0\t14\t200000", "1/1200\u4E07\t48828076\t3\t0/0/0,0/0/0\t0\t0\t13\t0\t31\tyahoosplit\t-\t1\tgif\t2\t84/59/55,0/0/0\t-84/59/57,0/0/0\t15\t400000", "1/2400\u4E07\t97656152\t3\t0/0/0,0/0/0\t0\t0\t14\t0\t15\tyahoosplit\t-\t1\tgif\t2\t84/59/55,0/0/0\t-84/59/57,0/0/0\t16\t900000", "1/4800\u4E07\t195312305\t3\t0/0/0,0/0/0\t0\t0\t15\t0\t7\tyahoosplit\t-\t1\tgif\t2\t84/59/55,0/0/0\t-84/59/57,0/0/0\t17\t1000000", "\u4E16\u754C\t390624609\t3\t0/0/0,0/0/0\t0\t0\t16\t0\t3\tyahoosplit\t-\t1\tgif\t2\t84/59/55,0/0/0\t-84/59/57,0/0/0\t18\t3000000"
    };
    private static final String info_indoor[] = {
        "1/375\t745\t3\t0/0/0,0/0/0\t0\t0\t-3\t0\t2097150\tyahoosplit\t-\t1\tpng\t2\t0\t0\t-1\t7", "1/750\t1490\t3\t0/0/0,0/0/0\t0\t0\t-2\t0\t1049574\tyahoosplit\t-\t1\tpng\t2\t0\t0\t0\t10", "1/1500\t2980\t3\t0/0/0,0/0/0\t0\t0\t-1\t0\t5424286\tyahoosplit\t-\t1\tpng\t2\t0\t0\t1\t20", "1/5000\t5960\t3\t0/0/0,0/0/0\t0\t0\t0\t0\t262143\tyahoosplit\t-\t1\tpng\t2\t0\t0\t2\t50", "1/10000\t11921\t3\t0/0/0,0/0/0\t0\t0\t1\t0\t131071\tyahoosplit\t-\t1\tpng\t2\t0\t0\t3\t100"
    };
    private static final String info_map[] = {
        "1/188\t745\t3\t0/0/0,0/0/0\t0\t0\t-3\t0\t2097150\tyahoosplit\t-\t1\tpng\t2\t0\t0\t-1\t7\t0", "1/375\t1490\t3\t0/0/0,0/0/0\t0\t0\t-2\t0\t1049574\tyahoosplit\t-\t1\tpng\t2\t0\t0\t0\t10\t0", "1/750\t2980\t3\t0/0/0,0/0/0\t0\t0\t-1\t0\t524287\tyahoosplit\t-\t1\tgif\t2\t0\t0\t1\t20", "1/1500\t5960\t3\t0/0/0,0/0/0\t0\t0\t0\t0\t262143\tyahoosplit\t-\t1\tgif\t2\t0\t0\t2\t50", "1/3000\t11921\t3\t0/0/0,0/0/0\t0\t0\t1\t0\t131071\tyahoosplit\t-\t1\tgif\t2\t0\t0\t3\t100", "1/6000\t23842\t3\t0/0/0,0/0/0\t0\t0\t2\t0\t65535\tyahoosplit\t-\t1\tgif\t2\t0\t0\t4\t200", "1/12000\t47684\t3\t0/0/0,0/0/0\t0\t0\t3\t0\t32767\tyahoosplit\t-\t1\tgif\t2\t0\t0\t5\t400", "1/24000\t95367\t3\t0/0/0,0/0/0\t0\t0\t4\t0\t16383\tyahoosplit\t-\t1\tgif\t2\t0\t0\t6\t1000", "1/48000\t190735\t3\t0/0/0,0/0/0\t0\t0\t5\t0\t8191\tyahoosplit\t-\t1\tgif\t2\t0\t0\t7\t1000", "1/96000\t381469\t3\t0/0/0,0/0/0\t0\t0\t6\t0\t4095\tyahoosplit\t-\t1\tgif\t2\t0\t0\t8\t3000", 
        "1/19\u4E07\t762939\t3\t0/0/0,0/0/0\t0\t0\t7\t0\t2047\tyahoosplit\t-\t1\tgif\t2\t0\t0\t9\t7000", "1/38\u4E07\t1525877\t3\t0/0/0,0/0/0\t0\t0\t8\t0\t1023\tyahoosplit\t-\t1\tgif\t2\t0\t0\t10\t10000", "1/75\u4E07\t3051755\t3\t0/0/0,0/0/0\t0\t0\t9\t0\t511\tyahoosplit\t-\t1\tgif\t2\t0\t0\t11\t30000", "1/150\u4E07\t6103510\t3\t0/0/0,0/0/0\t0\t0\t10\t0\t255\tyahoosplit\t-\t1\tgif\t2\t84/59/55,0/0/0\t-84/59/57,0/0/0\t12\t60000", "1/300\u4E07\t12207019\t3\t0/0/0,0/0/0\t0\t0\t11\t0\t127\tyahoosplit\t-\t1\tgif\t2\t84/59/55,0/0/0\t-84/59/57,0/0/0\t13\t100000", "1/600\u4E07\t24414038\t3\t0/0/0,0/0/0\t0\t0\t12\t0\t63\tyahoosplit\t-\t1\tgif\t2\t84/59/55,0/0/0\t-84/59/57,0/0/0\t14\t200000", "1/1200\u4E07\t48828076\t3\t0/0/0,0/0/0\t0\t0\t13\t0\t31\tyahoosplit\t-\t1\tgif\t2\t84/59/55,0/0/0\t-84/59/57,0/0/0\t15\t400000", "1/2400\u4E07\t97656152\t3\t0/0/0,0/0/0\t0\t0\t14\t0\t15\tyahoosplit\t-\t1\tgif\t2\t84/59/55,0/0/0\t-84/59/57,0/0/0\t16\t900000", "1/4800\u4E07\t195312305\t3\t0/0/0,0/0/0\t0\t0\t15\t0\t7\tyahoosplit\t-\t1\tgif\t2\t84/59/55,0/0/0\t-84/59/57,0/0/0\t17\t1000000", "\u4E16\u754C\t390624609\t3\t0/0/0,0/0/0\t0\t0\t16\t0\t3\tyahoosplit\t-\t1\tgif\t2\t84/59/55,0/0/0\t-84/59/57,0/0/0\t18\t3000000"
    };
    private static final String info_metrob1[] = {
        "1/188\t745\t3\t0/0/0,0/0/0\t0\t0\t-3\t0\t2097150\tyahoosplit\t-\t1\tpng\t2\t0\t0\t-1\t7\t0", "1/375\t745\t3\t0/0/0,0/0/0\t0\t0\t-3\t0\t2097150\tyahoosplit\t-\t1\tpng\t2\t0\t0\t22\t7", "1/750\t1490\t3\t0/0/0,0/0/0\t0\t0\t-2\t0\t1049574\tyahoosplit\t-\t1\tpng\t2\t0\t0\t21\t10", "1/1500\t2980\t3\t0/0/0,0/0/0\t0\t0\t-1\t0\t5424286\tyahoosplit\t-\t1\tpng\t2\t0\t0\t20\t20", "1/5000\t5960\t3\t0/0/0,0/0/0\t0\t0\t0\t0\t262143\tyahoosplit\t-\t1\tpng\t2\t0\t0\t19\t50", "1/10000\t11921\t3\t0/0/0,0/0/0\t0\t0\t1\t0\t131071\tyahoosplit\t-\t1\tpng\t2\t0\t0\t18\t100"
    };
    private static final String info_metrob2[] = {
        "1/188\t745\t3\t0/0/0,0/0/0\t0\t0\t-3\t0\t2097150\tyahoosplit\t-\t1\tpng\t2\t0\t0\t-1\t7\t0", "1/375\t745\t3\t0/0/0,0/0/0\t0\t0\t-3\t0\t2097150\tyahoosplit\t-\t1\tpng\t2\t0\t0\t22\t7", "1/750\t1490\t3\t0/0/0,0/0/0\t0\t0\t-2\t0\t1049574\tyahoosplit\t-\t1\tpng\t2\t0\t0\t21\t10", "1/1500\t2980\t3\t0/0/0,0/0/0\t0\t0\t-1\t0\t5424286\tyahoosplit\t-\t1\tpng\t2\t0\t0\t20\t20", "1/5000\t5960\t3\t0/0/0,0/0/0\t0\t0\t0\t0\t262143\tyahoosplit\t-\t1\tpng\t2\t0\t0\t19\t50", "1/10000\t11921\t3\t0/0/0,0/0/0\t0\t0\t1\t0\t131071\tyahoosplit\t-\t1\tpng\t2\t0\t0\t18\t100"
    };
    private static final String info_metrob3[] = {
        "1/188\t745\t3\t0/0/0,0/0/0\t0\t0\t-3\t0\t2097150\tyahoosplit\t-\t1\tpng\t2\t0\t0\t-1\t7\t0", "1/375\t745\t3\t0/0/0,0/0/0\t0\t0\t-3\t0\t2097150\tyahoosplit\t-\t1\tpng\t2\t0\t0\t22\t7", "1/750\t1490\t3\t0/0/0,0/0/0\t0\t0\t-2\t0\t1049574\tyahoosplit\t-\t1\tpng\t2\t0\t0\t21\t10", "1/1500\t2980\t3\t0/0/0,0/0/0\t0\t0\t-1\t0\t5424286\tyahoosplit\t-\t1\tpng\t2\t0\t0\t20\t20", "1/5000\t5960\t3\t0/0/0,0/0/0\t0\t0\t0\t0\t262143\tyahoosplit\t-\t1\tpng\t2\t0\t0\t19\t50", "1/10000\t11921\t3\t0/0/0,0/0/0\t0\t0\t1\t0\t131071\tyahoosplit\t-\t1\tpng\t2\t0\t0\t18\t100"
    };
    private static final String info_osm[] = {
        "1/188\t745\t3\t0/0/0,0/0/0\t0\t0\t-3\t0\t2097150\tyahoosplit\t-\t1\tpng\t2\t0\t0\t-1\t7\t0", "1/375\t1490\t3\t0/0/0,0/0/0\t0\t0\t-2\t0\t1049574\tyahoosplit\t-\t1\tpng\t2\t0\t0\t0\t10\t0", "1/750\t2980\t3\t0/0/0,0/0/0\t0\t0\t-1\t0\t524287\tyahoosplit\t-\t1\tgif\t2\t0\t0\t1\t20", "1/1500\t5960\t3\t0/0/0,0/0/0\t0\t0\t0\t0\t262143\tyahoosplit\t-\t1\tgif\t2\t0\t0\t2\t50", "1/3000\t11921\t3\t0/0/0,0/0/0\t0\t0\t1\t0\t131071\tyahoosplit\t-\t1\tgif\t2\t0\t0\t3\t100", "1/6000\t23842\t3\t0/0/0,0/0/0\t0\t0\t2\t0\t65535\tyahoosplit\t-\t1\tgif\t2\t0\t0\t4\t200", "1/12000\t47684\t3\t0/0/0,0/0/0\t0\t0\t3\t0\t32767\tyahoosplit\t-\t1\tgif\t2\t0\t0\t5\t400", "1/24000\t95367\t3\t0/0/0,0/0/0\t0\t0\t4\t0\t16383\tyahoosplit\t-\t1\tgif\t2\t0\t0\t6\t1000", "1/48000\t190735\t3\t0/0/0,0/0/0\t0\t0\t5\t0\t8191\tyahoosplit\t-\t1\tgif\t2\t0\t0\t7\t1000", "1/96000\t381469\t3\t0/0/0,0/0/0\t0\t0\t6\t0\t4095\tyahoosplit\t-\t1\tgif\t2\t0\t0\t8\t3000", 
        "1/19\u4E07\t762939\t3\t0/0/0,0/0/0\t0\t0\t7\t0\t2047\tyahoosplit\t-\t1\tgif\t2\t0\t0\t9\t7000", "1/38\u4E07\t1525877\t3\t0/0/0,0/0/0\t0\t0\t8\t0\t1023\tyahoosplit\t-\t1\tgif\t2\t0\t0\t10\t10000", "1/75\u4E07\t3051755\t3\t0/0/0,0/0/0\t0\t0\t9\t0\t511\tyahoosplit\t-\t1\tgif\t2\t0\t0\t11\t30000", "1/150\u4E07\t6103510\t3\t0/0/0,0/0/0\t0\t0\t10\t0\t255\tyahoosplit\t-\t1\tgif\t2\t84/59/55,0/0/0\t-84/59/57,0/0/0\t12\t60000", "1/300\u4E07\t12207019\t3\t0/0/0,0/0/0\t0\t0\t11\t0\t127\tyahoosplit\t-\t1\tgif\t2\t84/59/55,0/0/0\t-84/59/57,0/0/0\t13\t100000", "1/600\u4E07\t24414038\t3\t0/0/0,0/0/0\t0\t0\t12\t0\t63\tyahoosplit\t-\t1\tgif\t2\t84/59/55,0/0/0\t-84/59/57,0/0/0\t14\t200000", "1/1200\u4E07\t48828076\t3\t0/0/0,0/0/0\t0\t0\t13\t0\t31\tyahoosplit\t-\t1\tgif\t2\t84/59/55,0/0/0\t-84/59/57,0/0/0\t15\t400000", "1/2400\u4E07\t97656152\t3\t0/0/0,0/0/0\t0\t0\t14\t0\t15\tyahoosplit\t-\t1\tgif\t2\t84/59/55,0/0/0\t-84/59/57,0/0/0\t16\t900000", "1/4800\u4E07\t195312305\t3\t0/0/0,0/0/0\t0\t0\t15\t0\t7\tyahoosplit\t-\t1\tgif\t2\t84/59/55,0/0/0\t-84/59/57,0/0/0\t17\t1000000", "\u4E16\u754C\t390624609\t3\t0/0/0,0/0/0\t0\t0\t16\t0\t3\tyahoosplit\t-\t1\tgif\t2\t84/59/55,0/0/0\t-84/59/57,0/0/0\t18\t3000000"
    };
    private static final String info_photo[] = {
        "1/188\t745\t3\t0/0/0,0/0/0\t0\t0\t-3\t0\t2097150\tyahoosplit\t-\t1\tpng\t2\t0\t0\t-1\t7\t0", "1/375\t1490\t3\t0/0/0,0/0/0\t0\t0\t-2\t0\t1049574\tyahoosplit\t-\t1\tpng\t2\t0\t0\t0\t10\t0", "1/750\t2980\t3\t0/0/0,0/0/0\t0\t0\t-1\t0\t524287\tyahoosplit\t-\t1\tjpg\t2\t0\t0\t1\t20", "1/1500\t5960\t3\t0/0/0,0/0/0\t0\t0\t0\t0\t262143\tyahoosplit\t-\t1\tjpg\t2\t0\t0\t2\t50", "1/3000\t11921\t3\t0/0/0,0/0/0\t0\t0\t1\t0\t131071\tyahoosplit\t-\t1\tjpg\t2\t0\t0\t3\t100", "1/6000\t23842\t3\t0/0/0,0/0/0\t0\t0\t2\t0\t65535\tyahoosplit\t-\t1\tjpg\t2\t0\t0\t4\t200", "1/12000\t47684\t3\t0/0/0,0/0/0\t0\t0\t3\t0\t32767\tyahoosplit\t-\t1\tjpg\t2\t0\t0\t5\t400", "1/24000\t95367\t3\t0/0/0,0/0/0\t0\t0\t4\t0\t16383\tyahoosplit\t-\t1\tjpg\t2\t0\t0\t6\t1000", "1/48000\t190735\t3\t0/0/0,0/0/0\t0\t0\t5\t0\t8191\tyahoosplit\t-\t1\tjpg\t2\t0\t0\t7\t1000", "1/96000\t381469\t3\t0/0/0,0/0/0\t0\t0\t6\t0\t4095\tyahoosplit\t-\t1\tjpg\t2\t0\t0\t8\t3000", 
        "1/19\u4E07\t762939\t3\t0/0/0,0/0/0\t0\t0\t7\t0\t2047\tyahoosplit\t-\t1\tjpg\t2\t0\t0\t9\t7000", "1/38\u4E07\t1525877\t3\t0/0/0,0/0/0\t0\t0\t8\t0\t1023\tyahoosplit\t-\t1\tjpg\t2\t0\t0\t10\t10000", "1/75\u4E07\t3051755\t3\t0/0/0,0/0/0\t0\t0\t9\t0\t511\tyahoosplit\t-\t1\tjpg\t2\t0\t0\t11\t30000", "1/150\u4E07\t6103510\t3\t0/0/0,0/0/0\t0\t0\t10\t0\t255\tyahoosplit\t-\t1\tjpg\t2\t0\t0\t12\t60000", "1/300\u4E07\t12207019\t3\t0/0/0,0/0/0\t0\t0\t11\t0\t127\tyahoosplit\t-\t1\tjpg\t2\t0\t0\t13\t100000", "1/600\u4E07\t24414038\t3\t0/0/0,0/0/0\t0\t0\t12\t0\t63\tyahoosplit\t-\t1\tjpg\t2\t0\t0\t14\t200000", "1/1200\u4E07\t48828076\t3\t0/0/0,0/0/0\t0\t0\t13\t0\t31\tyahoosplit\t-\t1\tjpg\t2\t84/59/55,0/0/0\t-84/59/57,0/0/0\t15\t400000", "1/2400\u4E07\t97656152\t3\t0/0/0,0/0/0\t0\t0\t14\t0\t15\tyahoosplit\t-\t1\tjpg\t2\t84/59/55,0/0/0\t-84/59/57,0/0/0\t16\t900000", "1/4800\u4E07\t195312305\t3\t0/0/0,0/0/0\t0\t0\t15\t0\t7\tyahoosplit\t-\t1\tjpg\t2\t84/59/55,0/0/0\t-84/59/57,0/0/0\t17\t1000000", "\u5168\u4E16\u754C1\t390624609\t3\t0/0/0,0/0/0\t0\t0\t16\t0\t3\tyahoosplit\t-\t1\tjpg\t2\t84/59/55,0/0/0\t-84/59/57,0/0/0\t18\t3000000"
    };
    public final double bottomRng;
    public final int datum;
    public int doReqMap;
    public final double fac;
    public final String format;
    public final int id;
    public final int level;
    public double magnification;
    public final int maxTileX;
    public final int meshcode;
    public final int minTileX;
    public final String name;
    public final Coordinate org;
    public final int prj;
    public final int scale;
    public final int scaleline;
    public final int scid;
    public final boolean select_ok;
    public final String srvtype;
    public final double topRng;
    public final String type;
    public final String url;

    private LayerInfo(String s, int i, String s1, double d)
    {
        magnification = d;
        type = s;
        id = i;
        String as[] = Coordinate.split('\t', s1);
        name = as[0];
        scale = Integer.parseInt(as[1]);
        prj = Integer.parseInt(as[2]);
        org = Coordinate.parse(as[3])[0];
        fac = Double.parseDouble(as[4]);
        int j;
        if (as.length > 5)
        {
            j = Integer.parseInt(as[5]);
        } else
        {
            j = 0;
        }
        meshcode = j;
        level = Integer.parseInt(as[6]);
        minTileX = Integer.parseInt(as[7]);
        maxTileX = Integer.parseInt(as[8]);
        srvtype = as[9];
        url = as[10];
        if (Integer.parseInt(as[11]) == 1)
        {
            select_ok = true;
        } else
        {
            select_ok = false;
        }
        format = as[12];
        datum = Integer.parseInt(as[13]);
        if (!as[14].equals("0"))
        {
            Coordinate coordinate1 = Coordinate.parse(as[14])[0];
            topRng = latLon2Y(coordinate1.lat, coordinate1.lon);
        } else
        {
            topRng = 0.0D;
        }
        if (!as[15].equals("0"))
        {
            Coordinate coordinate = Coordinate.parse(as[15])[0];
            bottomRng = latLon2Y(coordinate.lat, coordinate.lon);
        } else
        {
            bottomRng = 0.0D;
        }
        scid = Integer.parseInt(as[16]);
        scaleline = Integer.parseInt(as[17]);
        if (as.length > 18)
        {
            doReqMap = Integer.parseInt(as[18]);
            return;
        } else
        {
            doReqMap = 1;
            return;
        }
    }

    public static LayerInfo[] getLayers(String s, double d)
    {
        String as[] = (String[])info.get(s);
        if (as == null)
        {
            return null;
        }
        Vector vector = new Vector();
        int i = 0;
        do
        {
            if (i >= as.length)
            {
                LayerInfo alayerinfo[] = new LayerInfo[vector.size()];
                vector.copyInto(alayerinfo);
                return alayerinfo;
            }
            if (as[i] != null)
            {
                vector.addElement(new LayerInfo(s, i, as[i], d));
            }
            i++;
        } while (true);
    }

    public boolean chackMetroMapMove(int i, int j)
    {
        if (type.equals("metrob1") || type.equals("metrob2") || type.equals("metrob3")) goto _L2; else goto _L1
_L1:
        return true;
_L2:
        if (scale != 745) goto _L4; else goto _L3
_L3:
        if (i > 0x1c6b2fff && i < 0x1c6b5000 && j < 0xfc9a6000 && j > 0xfc9a4001 || i > 0x1c6c5000 && i < 0x1c6c7fff && j < 0xfc9a2001 && j > 0xfc9a0000) goto _L1; else goto _L5
_L5:
        return false;
_L4:
        if (scale != 1490)
        {
            break MISSING_BLOCK_LABEL_177;
        }
        if (i > 0xe3597ff && i < 0xe35a800 && j < 0xfe4d3000 && j > 0xfe4d2001) goto _L1; else goto _L6
_L6:
        if (i > 0xe362800 && i < 0xe363fff && j < 0xfe4d1001 && j > 0xfe4d0000)
        {
            return true;
        }
          goto _L5
        if (scale != 2980)
        {
            break MISSING_BLOCK_LABEL_245;
        }
        if (i > 0x71acbff && i < 0x71ad400 && j < 0xff269800 && j > 0xff269001) goto _L1; else goto _L7
_L7:
        if (i > 0x71b1400 && i < 0x71b1fff && j < 0xff268801 && j > 0xff268000)
        {
            return true;
        }
          goto _L5
        if (scale != 5960)
        {
            continue; /* Loop/switch isn't completed */
        }
        if (i > 0x38d65ff && i < 0x38d6a00 && j < 0xff934c00 && j > 0xff934801) goto _L1; else goto _L8
_L8:
        if (i <= 0x38d8a00 || i >= 0x38d8fff || j >= 0xff934401 || j <= 0xff934000) goto _L5; else goto _L9
_L9:
        return true;
        if (scale != 11921) goto _L5; else goto _L10
_L10:
        if (i > 0x1c6b2ff && i < 0x1c6b500 && j < 0xffc9a600 && j > 0xffc9a401) goto _L1; else goto _L11
_L11:
        if (i > 0x1c6c500 && i < 0x1c6c7ff && j < 0xffc9a201 && j > 0xffc9a000)
        {
            return true;
        }
          goto _L5
    }

    public void changeVender(String s)
    {
    }

    public double latLon2X(double d, double d1)
    {
        return LLCalculation.latLon2X(d, d1, scale, prj, org.lat, org.lon, fac, level, datum) * magnification;
    }

    public double latLon2Y(double d, double d1)
    {
        return LLCalculation.latLon2Y(d, d1, scale, prj, org.lat, org.lon, fac, level, datum) * magnification;
    }

    public double log2Lat(double d, double d1)
    {
        return LLCalculation.log2Lat(d / magnification, d1 / magnification, scale, prj, org.lat, org.lon, fac, level, datum);
    }

    public Coordinate log2LatLon(double d, double d1)
    {
        return Coordinate.log2LatLon(d / magnification, d1 / magnification, scale, prj, org, fac, level, datum);
    }

    public Coordinate log2LatLon(DoublePoint doublepoint)
    {
        DoublePoint doublepoint1 = new DoublePoint();
        doublepoint1.x = doublepoint.x / magnification;
        doublepoint1.y = doublepoint.y / magnification;
        return log2LatLon(doublepoint.x, doublepoint.y);
    }

    public double log2Lon(double d, double d1)
    {
        return LLCalculation.log2Lon(d / magnification, d1 / magnification, scale, prj, org.lat, org.lon, fac, level, datum);
    }

    static 
    {
        info = new Hashtable();
        info.put("map", info_map);
        info.put("photo", info_photo);
        info.put("map-b1", info_b1);
        info.put("metro-b1", info_metrob1);
        info.put("metro-b2", info_metrob2);
        info.put("metro-b3", info_metrob3);
        info.put("osm", info_osm);
        info.put("hybrid", info_hybrid);
        info.put("indoor-iks", info_indoor);
    }
}

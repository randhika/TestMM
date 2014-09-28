// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.maps.indoormap;

import java.util.ArrayList;

// Referenced classes of package jp.co.yahoo.android.maps.indoormap:
//            IndoorData

public static class floorAreas
{

    public ArrayList floorAreas;
    public int floorIds[];
    public ArrayList shapes;
    public String type;

    public String toString()
    {
        StringBuffer stringbuffer;
        int i;
        stringbuffer = new StringBuffer();
        stringbuffer.append("FloorShape={");
        stringbuffer.append("\n");
        stringbuffer.append((new StringBuilder("type=")).append(type).toString());
        stringbuffer.append("\n");
        stringbuffer.append((new StringBuilder("floorIds=")).append(IndoorData.access$0(floorIds, ",")).toString());
        stringbuffer.append("\n");
        stringbuffer.append((new StringBuilder("ArrayList<Shape>.size()=")).append(shapes.size()).toString());
        stringbuffer.append("\n");
        stringbuffer.append("ArrayList<Shape>={");
        stringbuffer.append("\n");
        i = 0;
_L3:
        if (i < shapes.size()) goto _L2; else goto _L1
_L1:
        int j;
        stringbuffer.append("}");
        stringbuffer.append("\n");
        stringbuffer.append((new StringBuilder("ArrayList<FloorArea>.size()=")).append(floorAreas.size()).toString());
        stringbuffer.append("\n");
        stringbuffer.append("ArrayList<FloorArea>={");
        stringbuffer.append("\n");
        j = 0;
_L4:
        if (j >= floorAreas.size())
        {
            stringbuffer.append("}");
            stringbuffer.append("\n");
            stringbuffer.append("}");
            return stringbuffer.toString();
        }
        break MISSING_BLOCK_LABEL_302;
_L2:
        floorAreas floorareas = (floorAreas)shapes.get(i);
        if (floorareas != null)
        {
            if (i != 0)
            {
                stringbuffer.append("\n");
            }
            stringbuffer.append(floorareas.ing());
        }
        i++;
          goto _L3
        floorAreas floorareas1 = (ing)floorAreas.get(j);
        if (floorareas1 != null)
        {
            if (j != 0)
            {
                stringbuffer.append("\n");
            }
            stringbuffer.append(floorareas1.oString());
        }
        j++;
          goto _L4
    }

    public ()
    {
        shapes = new ArrayList();
        floorAreas = new ArrayList();
    }
}

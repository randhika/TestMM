// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.cast;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.accessibility.CaptioningManager;
import com.google.android.gms.internal.gi;
import com.google.android.gms.internal.hk;
import com.google.android.gms.internal.in;
import com.google.android.gms.internal.ip;
import org.json.JSONException;
import org.json.JSONObject;

public final class TextTrackStyle
{

    public static final int COLOR_UNSPECIFIED = 0;
    public static final float DEFAULT_FONT_SCALE = 1F;
    public static final int EDGE_TYPE_DEPRESSED = 4;
    public static final int EDGE_TYPE_DROP_SHADOW = 2;
    public static final int EDGE_TYPE_NONE = 0;
    public static final int EDGE_TYPE_OUTLINE = 1;
    public static final int EDGE_TYPE_RAISED = 3;
    public static final int EDGE_TYPE_UNSPECIFIED = -1;
    public static final int FONT_FAMILY_CASUAL = 4;
    public static final int FONT_FAMILY_CURSIVE = 5;
    public static final int FONT_FAMILY_MONOSPACED_SANS_SERIF = 1;
    public static final int FONT_FAMILY_MONOSPACED_SERIF = 3;
    public static final int FONT_FAMILY_SANS_SERIF = 0;
    public static final int FONT_FAMILY_SERIF = 2;
    public static final int FONT_FAMILY_SMALL_CAPITALS = 6;
    public static final int FONT_FAMILY_UNSPECIFIED = -1;
    public static final int FONT_STYLE_BOLD = 1;
    public static final int FONT_STYLE_BOLD_ITALIC = 3;
    public static final int FONT_STYLE_ITALIC = 2;
    public static final int FONT_STYLE_NORMAL = 0;
    public static final int FONT_STYLE_UNSPECIFIED = -1;
    public static final int WINDOW_TYPE_NONE = 0;
    public static final int WINDOW_TYPE_NORMAL = 1;
    public static final int WINDOW_TYPE_ROUNDED = 2;
    public static final int WINDOW_TYPE_UNSPECIFIED = -1;
    private JSONObject AA;
    private int BA;
    private int BB;
    private float Bs;
    private int Bt;
    private int Bu;
    private int Bv;
    private int Bw;
    private int Bx;
    private int By;
    private String Bz;
    private int td;

    public TextTrackStyle()
    {
        clear();
    }

    private int ah(String s)
    {
        int i = 0;
        if (s != null)
        {
            int j = s.length();
            i = 0;
            if (j == 9)
            {
                char c = s.charAt(0);
                i = 0;
                if (c == '#')
                {
                    int j1;
                    try
                    {
                        int k = Integer.parseInt(s.substring(1, 3), 16);
                        int l = Integer.parseInt(s.substring(3, 5), 16);
                        int i1 = Integer.parseInt(s.substring(5, 7), 16);
                        j1 = Color.argb(Integer.parseInt(s.substring(7, 9), 16), k, l, i1);
                    }
                    catch (NumberFormatException numberformatexception)
                    {
                        return 0;
                    }
                    i = j1;
                }
            }
        }
        return i;
    }

    private void clear()
    {
        Bs = 1.0F;
        Bt = 0;
        td = 0;
        Bu = -1;
        Bv = 0;
        Bw = -1;
        Bx = 0;
        By = 0;
        Bz = null;
        BA = -1;
        BB = -1;
        AA = null;
    }

    public static TextTrackStyle fromSystemSettings(Context context)
    {
        TextTrackStyle texttrackstyle;
        android.view.accessibility.CaptioningManager.CaptionStyle captionstyle;
        texttrackstyle = new TextTrackStyle();
        if (!ip.gi())
        {
            return texttrackstyle;
        }
        CaptioningManager captioningmanager = (CaptioningManager)context.getSystemService("captioning");
        texttrackstyle.setFontScale(captioningmanager.getFontScale());
        captionstyle = captioningmanager.getUserStyle();
        texttrackstyle.setBackgroundColor(captionstyle.backgroundColor);
        texttrackstyle.setForegroundColor(captionstyle.foregroundColor);
        captionstyle.edgeType;
        JVM INSTR tableswitch 1 2: default 80
    //                   1 151
    //                   2 159;
           goto _L1 _L2 _L3
_L3:
        break MISSING_BLOCK_LABEL_159;
_L1:
        texttrackstyle.setEdgeType(0);
_L4:
        texttrackstyle.setEdgeColor(captionstyle.edgeColor);
        Typeface typeface = captionstyle.getTypeface();
        if (typeface != null)
        {
            boolean flag;
            boolean flag1;
            if (Typeface.MONOSPACE.equals(typeface))
            {
                texttrackstyle.setFontGenericFamily(1);
            } else
            if (Typeface.SANS_SERIF.equals(typeface))
            {
                texttrackstyle.setFontGenericFamily(0);
            } else
            if (Typeface.SERIF.equals(typeface))
            {
                texttrackstyle.setFontGenericFamily(2);
            } else
            {
                texttrackstyle.setFontGenericFamily(0);
            }
            flag = typeface.isBold();
            flag1 = typeface.isItalic();
            if (flag && flag1)
            {
                texttrackstyle.setFontStyle(3);
            } else
            if (flag)
            {
                texttrackstyle.setFontStyle(1);
            } else
            if (flag1)
            {
                texttrackstyle.setFontStyle(2);
            } else
            {
                texttrackstyle.setFontStyle(0);
            }
        }
        return texttrackstyle;
_L2:
        texttrackstyle.setEdgeType(1);
          goto _L4
        texttrackstyle.setEdgeType(2);
          goto _L4
    }

    private String o(int i)
    {
        Object aobj[] = new Object[4];
        aobj[0] = Integer.valueOf(Color.red(i));
        aobj[1] = Integer.valueOf(Color.green(i));
        aobj[2] = Integer.valueOf(Color.blue(i));
        aobj[3] = Integer.valueOf(Color.alpha(i));
        return String.format("#%02X%02X%02X%02X", aobj);
    }

    public void b(JSONObject jsonobject)
        throws JSONException
    {
        clear();
        Bs = (float)jsonobject.optDouble("fontScale", 1.0D);
        Bt = ah(jsonobject.optString("foregroundColor"));
        td = ah(jsonobject.optString("backgroundColor"));
        if (jsonobject.has("edgeType"))
        {
            String s3 = jsonobject.getString("edgeType");
            if ("NONE".equals(s3))
            {
                Bu = 0;
            } else
            if ("OUTLINE".equals(s3))
            {
                Bu = 1;
            } else
            if ("DROP_SHADOW".equals(s3))
            {
                Bu = 2;
            } else
            if ("RAISED".equals(s3))
            {
                Bu = 3;
            } else
            if ("DEPRESSED".equals(s3))
            {
                Bu = 4;
            }
        }
        Bv = ah(jsonobject.optString("edgeColor"));
        if (jsonobject.has("windowType"))
        {
            String s2 = jsonobject.getString("windowType");
            if ("NONE".equals(s2))
            {
                Bw = 0;
            } else
            if ("NORMAL".equals(s2))
            {
                Bw = 1;
            } else
            if ("ROUNDED_CORNERS".equals(s2))
            {
                Bw = 2;
            }
        }
        Bx = ah(jsonobject.optString("windowColor"));
        if (Bw == 2)
        {
            By = jsonobject.optInt("windowRoundedCornerRadius", 0);
        }
        Bz = jsonobject.optString("fontFamily", null);
        if (jsonobject.has("fontGenericFamily"))
        {
            String s1 = jsonobject.getString("fontGenericFamily");
            if ("SANS_SERIF".equals(s1))
            {
                BA = 0;
            } else
            if ("MONOSPACED_SANS_SERIF".equals(s1))
            {
                BA = 1;
            } else
            if ("SERIF".equals(s1))
            {
                BA = 2;
            } else
            if ("MONOSPACED_SERIF".equals(s1))
            {
                BA = 3;
            } else
            if ("CASUAL".equals(s1))
            {
                BA = 4;
            } else
            if ("CURSIVE".equals(s1))
            {
                BA = 5;
            } else
            if ("SMALL_CAPITALS".equals(s1))
            {
                BA = 6;
            }
        }
        if (jsonobject.has("fontStyle"))
        {
            String s = jsonobject.getString("fontStyle");
            if ("NORMAL".equals(s))
            {
                BB = 0;
            } else
            if ("BOLD".equals(s))
            {
                BB = 1;
            } else
            if ("ITALIC".equals(s))
            {
                BB = 2;
            } else
            if ("BOLD_ITALIC".equals(s))
            {
                BB = 3;
            }
        }
        AA = jsonobject.optJSONObject("customData");
    }

    public JSONObject dZ()
    {
        JSONObject jsonobject = new JSONObject();
        jsonobject.put("fontScale", Bs);
        if (Bt != 0)
        {
            jsonobject.put("foregroundColor", o(Bt));
        }
        if (td != 0)
        {
            jsonobject.put("backgroundColor", o(td));
        }
        Bu;
        JVM INSTR tableswitch 0 4: default 104
    //                   0 321
    //                   1 334
    //                   2 347
    //                   3 360
    //                   4 373;
           goto _L1 _L2 _L3 _L4 _L5 _L6
_L1:
        if (Bv != 0)
        {
            jsonobject.put("edgeColor", o(Bv));
        }
        Bw;
        JVM INSTR tableswitch 0 2: default 156
    //                   0 386
    //                   1 400
    //                   2 414;
           goto _L7 _L8 _L9 _L10
_L7:
        if (Bx != 0)
        {
            jsonobject.put("windowColor", o(Bx));
        }
        if (Bw == 2)
        {
            jsonobject.put("windowRoundedCornerRadius", By);
        }
        if (Bz != null)
        {
            jsonobject.put("fontFamily", Bz);
        }
        BA;
        JVM INSTR tableswitch 0 6: default 264
    //                   0 428
    //                   1 442
    //                   2 456
    //                   3 470
    //                   4 484
    //                   5 498
    //                   6 512;
           goto _L11 _L12 _L13 _L14 _L15 _L16 _L17 _L18
_L11:
        BB;
        JVM INSTR tableswitch 0 3: default 300
    //                   0 526
    //                   1 540
    //                   2 554
    //                   3 568;
           goto _L19 _L20 _L21 _L22 _L23
_L19:
        if (AA == null) goto _L25; else goto _L24
_L24:
        jsonobject.put("customData", AA);
        return jsonobject;
_L2:
        try
        {
            jsonobject.put("edgeType", "NONE");
        }
        catch (JSONException jsonexception)
        {
            return jsonobject;
        }
        continue; /* Loop/switch isn't completed */
_L3:
        jsonobject.put("edgeType", "OUTLINE");
        continue; /* Loop/switch isn't completed */
_L4:
        jsonobject.put("edgeType", "DROP_SHADOW");
        continue; /* Loop/switch isn't completed */
_L5:
        jsonobject.put("edgeType", "RAISED");
        continue; /* Loop/switch isn't completed */
_L6:
        jsonobject.put("edgeType", "DEPRESSED");
        continue; /* Loop/switch isn't completed */
_L8:
        jsonobject.put("windowType", "NONE");
          goto _L7
_L9:
        jsonobject.put("windowType", "NORMAL");
          goto _L7
_L10:
        jsonobject.put("windowType", "ROUNDED_CORNERS");
          goto _L7
_L12:
        jsonobject.put("fontGenericFamily", "SANS_SERIF");
          goto _L11
_L13:
        jsonobject.put("fontGenericFamily", "MONOSPACED_SANS_SERIF");
          goto _L11
_L14:
        jsonobject.put("fontGenericFamily", "SERIF");
          goto _L11
_L15:
        jsonobject.put("fontGenericFamily", "MONOSPACED_SERIF");
          goto _L11
_L16:
        jsonobject.put("fontGenericFamily", "CASUAL");
          goto _L11
_L17:
        jsonobject.put("fontGenericFamily", "CURSIVE");
          goto _L11
_L18:
        jsonobject.put("fontGenericFamily", "SMALL_CAPITALS");
          goto _L11
_L20:
        jsonobject.put("fontStyle", "NORMAL");
          goto _L19
_L21:
        jsonobject.put("fontStyle", "BOLD");
          goto _L19
_L22:
        jsonobject.put("fontStyle", "ITALIC");
          goto _L19
_L23:
        jsonobject.put("fontStyle", "BOLD_ITALIC");
          goto _L19
_L25:
        return jsonobject;
        if (true) goto _L1; else goto _L26
_L26:
    }

    public boolean equals(Object obj)
    {
        boolean flag = true;
        if (this != obj) goto _L2; else goto _L1
_L1:
        boolean flag2 = flag;
_L4:
        return flag2;
_L2:
        boolean flag1;
        flag1 = obj instanceof TextTrackStyle;
        flag2 = false;
        if (!flag1) goto _L4; else goto _L3
_L3:
        TextTrackStyle texttrackstyle = (TextTrackStyle)obj;
        boolean flag3;
        boolean flag4;
        boolean flag5;
        if (AA == null)
        {
            flag3 = flag;
        } else
        {
            flag3 = false;
        }
        if (texttrackstyle.AA == null)
        {
            flag4 = flag;
        } else
        {
            flag4 = false;
        }
        flag2 = false;
        if (flag3 != flag4) goto _L4; else goto _L5
_L5:
        if (AA == null || texttrackstyle.AA == null)
        {
            break; /* Loop/switch isn't completed */
        }
        flag5 = in.d(AA, texttrackstyle.AA);
        flag2 = false;
        if (!flag5) goto _L4; else goto _L6
_L6:
        if (Bs != texttrackstyle.Bs || Bt != texttrackstyle.Bt || td != texttrackstyle.td || Bu != texttrackstyle.Bu || Bv != texttrackstyle.Bv || Bw != texttrackstyle.Bw || By != texttrackstyle.By || !gi.a(Bz, texttrackstyle.Bz) || BA != texttrackstyle.BA || BB != texttrackstyle.BB)
        {
            flag = false;
        }
        return flag;
    }

    public int getBackgroundColor()
    {
        return td;
    }

    public JSONObject getCustomData()
    {
        return AA;
    }

    public int getEdgeColor()
    {
        return Bv;
    }

    public int getEdgeType()
    {
        return Bu;
    }

    public String getFontFamily()
    {
        return Bz;
    }

    public int getFontGenericFamily()
    {
        return BA;
    }

    public float getFontScale()
    {
        return Bs;
    }

    public int getFontStyle()
    {
        return BB;
    }

    public int getForegroundColor()
    {
        return Bt;
    }

    public int getWindowColor()
    {
        return Bx;
    }

    public int getWindowCornerRadius()
    {
        return By;
    }

    public int getWindowType()
    {
        return Bw;
    }

    public int hashCode()
    {
        Object aobj[] = new Object[12];
        aobj[0] = Float.valueOf(Bs);
        aobj[1] = Integer.valueOf(Bt);
        aobj[2] = Integer.valueOf(td);
        aobj[3] = Integer.valueOf(Bu);
        aobj[4] = Integer.valueOf(Bv);
        aobj[5] = Integer.valueOf(Bw);
        aobj[6] = Integer.valueOf(Bx);
        aobj[7] = Integer.valueOf(By);
        aobj[8] = Bz;
        aobj[9] = Integer.valueOf(BA);
        aobj[10] = Integer.valueOf(BB);
        aobj[11] = AA;
        return hk.hashCode(aobj);
    }

    public void setBackgroundColor(int i)
    {
        td = i;
    }

    public void setCustomData(JSONObject jsonobject)
    {
        AA = jsonobject;
    }

    public void setEdgeColor(int i)
    {
        Bv = i;
    }

    public void setEdgeType(int i)
    {
        if (i < 0 || i > 4)
        {
            throw new IllegalArgumentException("invalid edgeType");
        } else
        {
            Bu = i;
            return;
        }
    }

    public void setFontFamily(String s)
    {
        Bz = s;
    }

    public void setFontGenericFamily(int i)
    {
        if (i < 0 || i > 6)
        {
            throw new IllegalArgumentException("invalid fontGenericFamily");
        } else
        {
            BA = i;
            return;
        }
    }

    public void setFontScale(float f)
    {
        Bs = f;
    }

    public void setFontStyle(int i)
    {
        if (i < 0 || i > 3)
        {
            throw new IllegalArgumentException("invalid fontStyle");
        } else
        {
            BB = i;
            return;
        }
    }

    public void setForegroundColor(int i)
    {
        Bt = i;
    }

    public void setWindowColor(int i)
    {
        Bx = i;
    }

    public void setWindowCornerRadius(int i)
    {
        if (i < 0)
        {
            throw new IllegalArgumentException("invalid windowCornerRadius");
        } else
        {
            By = i;
            return;
        }
    }

    public void setWindowType(int i)
    {
        if (i < 0 || i > 2)
        {
            throw new IllegalArgumentException("invalid windowType");
        } else
        {
            Bw = i;
            return;
        }
    }
}

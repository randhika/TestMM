// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.common;

import javax.xml.parsers.SAXParserFactory;

public class YSaxUtils
{

    public static final SAXParserFactory PARSER_FACTORY;

    public YSaxUtils()
    {
    }

    static 
    {
        PARSER_FACTORY = SAXParserFactory.newInstance();
        PARSER_FACTORY.setNamespaceAware(true);
    }
}

// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.common;

import javax.xml.parsers.DocumentBuilderFactory;

public class YDomUtils
{

    public static final DocumentBuilderFactory BUILDER_FACTORY;
    private static final String TAG = "YDomUtils";

    public YDomUtils()
    {
    }

    static 
    {
        BUILDER_FACTORY = DocumentBuilderFactory.newInstance();
        BUILDER_FACTORY.setNamespaceAware(true);
        BUILDER_FACTORY.setIgnoringComments(true);
        BUILDER_FACTORY.setIgnoringElementContentWhitespace(true);
        BUILDER_FACTORY.setCoalescing(true);
    }
}

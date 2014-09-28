// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.regex.Pattern;
import org.codehaus.jackson.Version;

public class VersionUtil
{

    public static final String VERSION_FILE = "VERSION.txt";
    private static final Pattern VERSION_SEPARATOR = Pattern.compile("[-_./;:]");

    public VersionUtil()
    {
    }

    public static Version parseVersion(String s)
    {
        String s1;
        String as[];
        if (s != null)
        {
            if ((s1 = s.trim()).length() != 0 && (as = VERSION_SEPARATOR.split(s1)).length >= 2)
            {
                int i = parseVersionPart(as[0]);
                int j = parseVersionPart(as[1]);
                int k = as.length;
                int l = 0;
                if (k > 2)
                {
                    l = parseVersionPart(as[2]);
                }
                int i1 = as.length;
                String s2 = null;
                if (i1 > 3)
                {
                    s2 = as[3];
                }
                return new Version(i, j, l, s2);
            }
        }
        return null;
    }

    protected static int parseVersionPart(String s)
    {
        String s1 = s.toString();
        int i = s1.length();
        int j = 0;
        int k = 0;
        do
        {
            char c;
label0:
            {
                if (k < i)
                {
                    c = s1.charAt(k);
                    if (c <= '9' && c >= '0')
                    {
                        break label0;
                    }
                }
                return j;
            }
            j = j * 10 + (c - 48);
            k++;
        } while (true);
    }

    public static Version versionFor(Class class1)
    {
        Version version = null;
        InputStream inputstream = class1.getResourceAsStream("VERSION.txt");
        version = null;
        if (inputstream == null)
        {
            break MISSING_BLOCK_LABEL_47;
        }
        Version version1 = parseVersion((new BufferedReader(new InputStreamReader(inputstream, "UTF-8"))).readLine());
        version = version1;
        try
        {
            inputstream.close();
        }
        catch (IOException ioexception2)
        {
            try
            {
                throw new RuntimeException(ioexception2);
            }
            catch (IOException ioexception) { }
        }
        if (version == null)
        {
            version = Version.unknownVersion();
        }
        return version;
        Exception exception;
        exception;
        inputstream.close();
        throw exception;
        IOException ioexception1;
        ioexception1;
        throw new RuntimeException(ioexception1);
    }

}

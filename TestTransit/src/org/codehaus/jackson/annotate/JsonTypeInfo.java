// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.annotate;

import java.lang.annotation.Annotation;

public interface JsonTypeInfo
    extends Annotation
{
    public static final class As extends Enum
    {

        private static final As $VALUES[];
        public static final As PROPERTY;
        public static final As WRAPPER_ARRAY;
        public static final As WRAPPER_OBJECT;

        public static As valueOf(String s)
        {
            return (As)Enum.valueOf(org/codehaus/jackson/annotate/JsonTypeInfo$As, s);
        }

        public static As[] values()
        {
            return (As[])$VALUES.clone();
        }

        static 
        {
            PROPERTY = new As("PROPERTY", 0);
            WRAPPER_OBJECT = new As("WRAPPER_OBJECT", 1);
            WRAPPER_ARRAY = new As("WRAPPER_ARRAY", 2);
            As aas[] = new As[3];
            aas[0] = PROPERTY;
            aas[1] = WRAPPER_OBJECT;
            aas[2] = WRAPPER_ARRAY;
            $VALUES = aas;
        }

        private As(String s, int i)
        {
            super(s, i);
        }
    }

    public static final class Id extends Enum
    {

        private static final Id $VALUES[];
        public static final Id CLASS;
        public static final Id CUSTOM;
        public static final Id MINIMAL_CLASS;
        public static final Id NAME;
        public static final Id NONE;
        private final String _defaultPropertyName;

        public static Id valueOf(String s)
        {
            return (Id)Enum.valueOf(org/codehaus/jackson/annotate/JsonTypeInfo$Id, s);
        }

        public static Id[] values()
        {
            return (Id[])$VALUES.clone();
        }

        public String getDefaultPropertyName()
        {
            return _defaultPropertyName;
        }

        static 
        {
            NONE = new Id("NONE", 0, null);
            CLASS = new Id("CLASS", 1, "@class");
            MINIMAL_CLASS = new Id("MINIMAL_CLASS", 2, "@c");
            NAME = new Id("NAME", 3, "@type");
            CUSTOM = new Id("CUSTOM", 4, null);
            Id aid[] = new Id[5];
            aid[0] = NONE;
            aid[1] = CLASS;
            aid[2] = MINIMAL_CLASS;
            aid[3] = NAME;
            aid[4] = CUSTOM;
            $VALUES = aid;
        }

        private Id(String s, int i, String s1)
        {
            super(s, i);
            _defaultPropertyName = s1;
        }
    }


    public abstract As include();

    public abstract String property();

    public abstract Id use();
}

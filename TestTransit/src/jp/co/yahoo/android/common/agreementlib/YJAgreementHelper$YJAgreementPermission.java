// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.common.agreementlib;


// Referenced classes of package jp.co.yahoo.android.common.agreementlib:
//            YJAgreementHelper

private static class _description
{

    public String _description;
    public String _label;

    public boolean equals(_description _pdescription)
    {
        while (_label == null || _description == null || !_label.equals(_pdescription._label) || !_description.equals(_pdescription._description)) 
        {
            return false;
        }
        return true;
    }

    public (String s, String s1)
    {
        _label = s;
        _description = s1;
    }
}

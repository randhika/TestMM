// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.common.hamburger;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import android.util.Log;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import org.apache.http.conn.ClientConnectionManager;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

// Referenced classes of package jp.co.yahoo.android.common.hamburger:
//            YHttpGet, YHBGUtils

public class YHBGSearchAssistProvider extends ContentProvider
{

    private static final String APPID = "dj0zaiZpPTNNeDVhYWhpV3dhcyZkPVlXazlNbE5NU0VkdU5UUW1jR285TUEtLSZzPWNvbnN1bWVyc2VjcmV0Jng9ZWQ-";
    public static final String AUTHORITY = "jp.co.yahoo.android.common.hamburger";
    private static final String COLUMNS[] = {
        "_id", "suggest_text_1", "suggest_text_2", "suggest_intent_query"
    };
    protected static final int MATCH_CHIE = 4;
    protected static final int MATCH_IMAGE = 3;
    protected static final int MATCH_SEARCH = 2;
    protected static final int MATCH_SEARCH_WORD = 1;
    public static final String SEARCH_AUTHORITY = "jp.co.yahoo.android.common.hamburger.search";
    public static final Uri URL_SEARCH = Uri.parse("content://jp.co.yahoo.android.common.hamburger.search/search_suggest_query");
    public static final Uri URL_SEARCH_CHIE = Uri.parse("content://jp.co.yahoo.android.common.hamburger.search/search_suggest_query_chie");
    public static final Uri URL_SEARCH_IMAGE = Uri.parse("content://jp.co.yahoo.android.common.hamburger.search/search_suggest_query_image");
    private static final String URL_SEARCH_SUGGEST = "http://search.yahooapis.jp/SuggestSearchService/V4/webassistSearch?appid=dj0zaiZpPTNNeDVhYWhpV3dhcyZkPVlXazlNbE5NU0VkdU5UUW1jR285TUEtLSZzPWNvbnN1bWVyc2VjcmV0Jng9ZWQ-&.src=srch&query=";
    protected static final UriMatcher sURIMatcher;

    public YHBGSearchAssistProvider()
    {
    }

    private String[] getSuggest(String s)
    {
        YHttpGet yhttpget = null;
        String s1;
        YHttpGet yhttpget1;
        s1 = (new StringBuilder("http://search.yahooapis.jp/SuggestSearchService/V4/webassistSearch?appid=dj0zaiZpPTNNeDVhYWhpV3dhcyZkPVlXazlNbE5NU0VkdU5UUW1jR285TUEtLSZzPWNvbnN1bWVyc2VjcmV0Jng9ZWQ-&.src=srch&query=")).append(URLEncoder.encode(s, "UTF-8")).toString();
        yhttpget1 = new YHttpGet();
        JSONObject jsonobject;
        yhttpget1.addHeader("User-Agent", YHBGUtils.getBrowserUserAgent(getContext()));
        yhttpget1.addHeader("Content-Type", "application/json; charset=utf-8");
        jsonobject = yhttpget1.getJson(s1);
        if (jsonobject == null)
        {
            Exception exception;
            Exception exception1;
            UnsupportedEncodingException unsupportedencodingexception;
            Exception exception2;
            JSONException jsonexception;
            Exception exception3;
            JSONArray jsonarray;
            String as[];
            int i;
            int j;
            Exception exception4;
            if (yhttpget1 != null)
            {
                try
                {
                    yhttpget1.getConnectionManager().shutdown();
                }
                catch (Exception exception5) { }
            }
            return null;
        }
        jsonarray = jsonobject.getJSONArray("Result");
        as = new String[jsonarray.length()];
        i = 0;
_L2:
        j = jsonarray.length();
        if (i >= j)
        {
            if (yhttpget1 != null)
            {
                try
                {
                    yhttpget1.getConnectionManager().shutdown();
                }
                // Misplaced declaration of an exception variable
                catch (Exception exception4) { }
            }
            return as;
        }
        as[i] = jsonarray.getString(i);
        i++;
        if (true) goto _L2; else goto _L1
_L1:
        jsonexception;
_L8:
        jsonexception.printStackTrace();
        if (yhttpget != null)
        {
            try
            {
                yhttpget.getConnectionManager().shutdown();
            }
            // Misplaced declaration of an exception variable
            catch (Exception exception3) { }
        }
        return null;
        unsupportedencodingexception;
_L6:
        unsupportedencodingexception.printStackTrace();
        if (yhttpget != null)
        {
            try
            {
                yhttpget.getConnectionManager().shutdown();
            }
            // Misplaced declaration of an exception variable
            catch (Exception exception2) { }
        }
        break MISSING_BLOCK_LABEL_180;
        exception;
_L4:
        if (yhttpget != null)
        {
            try
            {
                yhttpget.getConnectionManager().shutdown();
            }
            // Misplaced declaration of an exception variable
            catch (Exception exception1) { }
        }
        throw exception;
        exception;
        yhttpget = yhttpget1;
        if (true) goto _L4; else goto _L3
_L3:
        unsupportedencodingexception;
        yhttpget = yhttpget1;
        if (true) goto _L6; else goto _L5
_L5:
        jsonexception;
        yhttpget = yhttpget1;
        if (true) goto _L8; else goto _L7
_L7:
    }

    public int delete(Uri uri, String s, String as[])
    {
        return 0;
    }

    public String getType(Uri uri)
    {
        return "vnd.android.cursor.dir/vnd.android.search.suggest";
    }

    public Uri insert(Uri uri, ContentValues contentvalues)
    {
        return null;
    }

    public boolean onCreate()
    {
        Log.d("YHamburger", "YHBGSearchAssistProvider: onCreate");
        return true;
    }

    public Cursor query(Uri uri, String as[], String s, String as1[], String s1)
    {
        sURIMatcher.match(uri);
        JVM INSTR tableswitch 1 4: default 36
    //                   1 75
    //                   2 126
    //                   3 140
    //                   4 154;
           goto _L1 _L2 _L3 _L4 _L5
_L1:
        YHBGUtils.error((new StringBuilder("Invalid uri : ")).append(sURIMatcher.match(uri)).append(" : ").append(uri.toString()).toString());
_L7:
        return null;
_L2:
        String s2 = uri.getLastPathSegment();
_L9:
        String as2[] = getSuggest(s2);
        if (as2 == null) goto _L7; else goto _L6
_L3:
        if (as1 == null) goto _L7; else goto _L8
_L8:
        s2 = as1[0];
          goto _L9
_L4:
        if (as1 == null) goto _L7; else goto _L10
_L10:
        s2 = as1[0];
          goto _L9
_L5:
        if (as1 == null) goto _L7; else goto _L11
_L11:
        s2 = as1[0];
          goto _L9
_L6:
        MatrixCursor matrixcursor = new MatrixCursor(COLUMNS);
        int i = 0;
        do
        {
            if (i >= as2.length)
            {
                matrixcursor.moveToFirst();
                return matrixcursor;
            }
            matrixcursor.newRow().add(Integer.valueOf(i)).add(as2[i]).add("").add(as2[i]);
            i++;
        } while (true);
    }

    public int update(Uri uri, ContentValues contentvalues, String s, String as[])
    {
        return 0;
    }

    static 
    {
        sURIMatcher = new UriMatcher(-1);
        sURIMatcher.addURI("jp.co.yahoo.android.common.hamburger.search", "search_suggest_query/*", 1);
        sURIMatcher.addURI("jp.co.yahoo.android.common.hamburger.search", "search_suggest_query", 2);
        sURIMatcher.addURI("jp.co.yahoo.android.common.hamburger.search", "search_suggest_query_image", 3);
        sURIMatcher.addURI("jp.co.yahoo.android.common.hamburger.search", "search_suggest_query_chie", 4);
    }
}

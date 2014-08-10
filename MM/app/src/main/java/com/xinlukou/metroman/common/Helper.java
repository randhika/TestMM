package com.xinlukou.metroman.common;

import android.content.res.AssetManager;

import org.apache.http.util.EncodingUtils;

import java.io.InputStream;

public class Helper {
    public static AssetManager assetManager = null;

    public static String[] getCsv(String filePath) {
        String[] rowArray = null;
        try {
            InputStream is = assetManager.open(filePath);
            byte[] bytes = new byte[is.available()];
            is.read(bytes);
            String content = EncodingUtils.getString(bytes, "utf-8");
            rowArray = content.split("\r\n");
        } catch (Exception ex) {
        }
        return rowArray;
    }
}

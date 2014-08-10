package com.xinlukou.metroman.metroman;

import com.xinlukou.metroman.common.Helper;
import com.xinlukou.metroman.engine.DataManage;
import com.xinlukou.metroman.engine.SrcUNO;

import java.util.ArrayList;
import java.util.List;

public class Global {
    public static List<City> cityList;
    public static City city;

    public static void reloadData(boolean flag) {
        loadCity();
        //TODO
        city = getCity(2);
        //LoadLang
        if(flag) {
            DataManage.releaseData();
            DataManage.initData(city);
        }
    }

    private static void loadCity() {
        String[] rowArray = Helper.getCsv("data/mmcity.csv");
        cityList = new ArrayList<City>(rowArray.length);
        for (String str : rowArray) {
            if(str.isEmpty()) continue;
            cityList.add(new City(str));
        }
    }

    private static City getCity(Integer cityID) {
        City result = null;
        for (City obj : cityList) {
            if(cityID == obj.cityID) {
                result = obj;
                break;
            }
        }
        return result;
    }
}
